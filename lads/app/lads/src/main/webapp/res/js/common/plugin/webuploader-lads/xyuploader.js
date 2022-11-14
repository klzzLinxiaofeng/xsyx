(function ($) {
    $.fn.xyUploader = function (options) {
        var defaults = {
            basePath: "",
            picker: {
                id: "#picker",
                innerHTML: "文件上传",
                multiple: false
            },
            accept: {
                title: 'MEDIA',
                extensions: '*.mp4',
                mimeTypes: 'video/mp4',
                allowExts: "mp4"
            },
            swf: "/res/js/common/plugin/webuploader-lads/Uploader.swf",
            md5Script: "/res/js/common/plugin/webuploader-lads/xy_md5_worker.js",
            formData: {},
            chunkSize: 5242880,
            fileSize: 4 * 1024 * 1024 * 1024,
            fileVal: "file",
            removeTimeout: 2,
            cuServer: "/lads/common",
            bpruServer: "/lads/common/bpru",
            validFileExistServer: "/lads/common/exist",
            progress: "progress",
            infoBox: "infoBox",
            uploadSuccess: function () {
            },
            uploadError: function () {
            },
            uploadComplete: function () {
            }
        };

        var settings = $.extend({}, defaults, options || {});

        var isChunked = true;

        var requestServer = settings.basePath + settings.bpruServer;

        if (typeof (Worker) !== "undefined") {
            var uploadedChunks = 0;
            var currentChunkCount = 1;
            WebUploader.Uploader.register({
                'before-send-file': 'preuploadFile'
            }, {
                preuploadFile: function (file) {
                    currentChunkCount = 1;
                    var me = this, deferred = WebUploader.Deferred(), blob = file.source.getSource();
                    md5Blob(blob).fail(function () {
                        deferred.reject();
                    }).then(function (md5) {
                        me.options.formData.md5 = md5;
                        $.ajax(settings.basePath + settings.validFileExistServer, {
//                        	dataType: 'json',
                            async: true,
                            data: {
                                md5: md5
                            },
                            success: function (response) {
                                if (response !== null) {
                                    if ("no_exist" === response) {
                                        uploadedChunks = 0;
                                    } else {
                                        response = eval("("+response+")")
                                        if (1 == response.finishedFlag) {
                                            uploader.quicklyUploadFile = response;
                                        }
                                        var uploadedFileSize = response.size;
                                        uploadedChunks = parseInt(uploadedFileSize / me.options.chunkSize) + (uploadedFileSize % me.options.chunkSize > 0 ? 1 : 0);
                                    }
                                }
                                deferred.resolve();
                            }
                        });
                    });
                    return deferred.promise();
                }
            });

            WebUploader.Uploader.register({
                'before-send': 'preupload'
            }, {
                preupload: function (block) {
                    var deferred = WebUploader.Deferred();
                    if (currentChunkCount <= uploadedChunks) {
                        currentChunkCount++;
                        deferred.reject();
                    } else {
                        deferred.resolve();
                    }
                    return deferred.promise();
                }
            });
        } else {
            requestServer = settings.basePath + settings.cuServer;
            isChunked = false;
        }
        var uploader = WebUploader.create({
            pick: settings.picker,
            accept: settings.accept,
            swf: settings.basePath + settings.swf,
            formData: settings.formData,
            chunked: isChunked,
            chunkSize: settings.chunkSize,
            multiple: false,
            threads: 1,
            fileVal: settings.fileVal,
            method: "post",
            server: requestServer,
            fileNumLimit: 4,
            fileSizeLimit: settings.fileSize,
            fileSingleSizeLimit: settings.fileSize
        });
        var currentFile = null;
        var $progress = $("#" + settings.progress);
        var $infoBox = $("#" + settings.infoBox);
        var $referId = settings.referId;
        $progress.addClass("progress").css("display", "none");
        var addFile = function (file) {
            var $info = $('<p class="error"></p>');
            showError = function (code) {
                switch (code) {
                    case 'exceed_size':
                        text = '文件大小超出';
                        break;
                    case 'interrupt':
                        text = '上传暂停';
                        break;
                    default:
                        text = '上传失败，请重传';
                        break;
                }
                $info.text(text).appendTo($progress);
            };

            if (file.getStatus() === 'invalid') {
                showError(file.statusText);
            } else {
                currentFile = file;
                $infoBox.prev("p").css("display", "none");
                $infoBox.css("display", "block");
                $progress.prev("div").css("display", "none");
                $progress.html('<div id="' + file.id + '" class="item">'
                        + '<h4 class="file_name">' + file.name + '</h4>'
                        + '<p class="state">文件预处理中，请耐心等待...</p>' + '</div> '
                        ).fadeIn('slow');
            }

            file.on('statuschange', function (cur, prev) {
                if (prev === 'progress') {
                } else if (prev === 'queued') {

                }
                if (cur === 'error' || cur === 'invalid') {
                    showError(file.statusText);
                } else if (cur === 'interrupt') {
                    showError('interrupt');
                } else if (cur === 'queued') {

                } else if (cur === 'progress') {
                    $info.remove();
                } else if (cur === 'complete') {

                }
            });
        };

        uploader.on("uploadProgress", function (file, percentage) {
            if ($progress.find(".progress-striped").length <= 0) {
                $progress.html("<div class='file_name'>" + file.name
                        + "</div><div class='progress-striped'><div id='progress-" + file.id
                        + "' style='width: " + percentage * 100
                        + "%;' class='bar'></div></div><div class='precent'>" + Math.round(percentage * 100)
                        + "%</div>");
            } else {
                $progress.find("#progress-" + file.id).css("width", percentage * 100 + "%");
                $progress.find(".precent").html(Math.round(percentage * 100) + "%");

            }
        });

        uploader.on("beforeFileQueued", function (file) {
            if (settings.accept.allowExts.indexOf(file.ext) === -1) {
                alert("不支持此文件类型");
                return false;
            }
            if (uploader.isInProgress()) {
                alert("存在文件在当前上传任务中， 如若想添加新任务，可关闭当前进行中的任务");
                return false;
            }
//          if (currentFile != null) {
//		uploader.removeFile(currentFile);
//          }
            addFile(file);
        });

        uploader.on("fileQueued", function (file) {
            $("#mediaUploadDivPart1_"+$referId).hide();
            $("#mediaUploadDivPart2_"+$referId).show();
            uploader.upload();
        });

        uploader.on("fileDequeued", function (file) {

        });

        uploader.on("uploadComplete", function (file) {
            settings.uploadComplete();
            setTimeout(function () {
                uploader.removeFile(currentFile);
//                $progress.fadeOut('slow');
            }, (settings.removeTimeout * 1000));
        });

        uploader.on("uploadSuccess", function (file, response) {
            //若是秒传  获取上传后的服务器文件信息	
            var quickFlag =false;
            if (uploader.quicklyUploadFile !== null && uploader.quicklyUploadFile !== undefined && uploader.quicklyUploadFile !== "undefined") {
                response = uploader.quicklyUploadFile;
                uploader.quicklyUploadFile = null;
                quickFlag = true;
            }
            var isSuc = settings.uploadSuccess(file, response);
            if (isSuc === false) {
                $progress.html('<div id="' + file.id + '" class="item"><h4 class="file_name">' + file.name + '</h4><p class="state">上传失败</p></div>').fadeIn('slow');
            } else {
                //若是秒传,进度条直接显示100%	
                if (quickFlag) {
                       $progress.html("<div class='file_name'>" + file.name
                        + "</div><div class='progress-striped'><div id='progress-" + file.id
                        + "' style='width: 100" 
                        + "%;' class='bar'></div></div><div class='precent'>"
                        + "100%</div>");
                }
                $progress.after("<a class=\"yes\" onclick=\"saveMediaInfo("+settings.referId+")\" href=\"javascript:void(0)\">确认上传</a>");
            }
        });

        uploader.on("error", function (code) {
            settings.uploadError(code);
        });

        $progress.on("click", ".close", function () {
            uploader.removeFile(currentFile);
            $progress.fadeOut('slow');
        });

    };
})(jQuery);