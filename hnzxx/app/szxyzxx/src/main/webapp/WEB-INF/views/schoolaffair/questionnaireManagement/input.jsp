<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <%@ include file="/views/embedded/plugin/uploadify.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <style>
        .row-fluid .span13 {
            width: 75%;
        }

        .row-fluid .span4 {
            width: 227px;
        }

        .myerror {
            color: red !important;
            width: 30%;
            display: inline-block;
            padding-left: 10px;
        }

        .control-group {


            width: 100%;
            margin-left: auto;
            margin-right: auto;
        }


        label {
            display: inline;
        }

        .form-horizontal .control-label {
            text-align: left;
            margin-left: 40px;
            width: 83px;
        }

        .form-horizontal .controls {
            margin-left: 10px !important;
        }

        .up_img {
            background: url("${pageContext.request.contextPath}/res/css/extra/images/up_img.jpg") no-repeat;
            display: block;
            height: 30px;
            width: 98px;
            position: absolute;
            bottom: 10px;
            left: 30px;
        }
        .move {
            margin-left: 16%;
        }
        .uploadify {
            margin-left: 16%;
        }

    </style>
</head>
<body style="background-color: cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_form" action="javascript:void(0);">
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            问卷名称：
                        </label>
                        <div class="controls">
                            <input type="text" id="name" name="name" class="span4" placeholder=""
                                   value="${publicClass.name}" style="width: 70%">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            调研对象：
                        </label>
                        <div class="controls" style="margin-top: 5px">


                            <input type="radio"
                                   <c:if test="${publicClass.object== 1}">checked="checked"</c:if> name="object"
                                   id="male" value="1" checked/>
                            <label for="male" style="margin-left: 5px">教师</label>

                            <input type="radio"
                                   <c:if test="${publicClass.object== 2}">checked="checked"</c:if> name="object"
                                   id="male2" value="2"/>
                            <label for="male2" style="margin-left: 5px">学生/家长</label>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            问卷星URL：
                        </label>
                        <div class="controls">
                            <input type="text" id="url" name="url" class="span4" placeholder=""
                                   data-id="${publicClass.url}" value="${publicClass.url}" style="width: 70%">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            截止日期：
                        </label>
                        <div class="controls">
                            <input type="text" id="expiryDate" name="expiryDate" class="span4"
                                   onFocus="WdatePicker({minDate:'#F{$dp.$D(\'expiryDate\')}'})"
                                   placeholder="xxxx-xx-xx" style="width: 70%"
                                   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${publicClass.expiryDate}"></fmt:formatDate>'>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            <span style="color: red;">*</span>缩略图：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="uuid" name="uuid" value="${publicClass.uuid}">
                            </div>
                            <div id="zp" style="display:inline-block;">
                                <div class="img_1">
                                    <c:if test="${(isCK==''||isCK==null) && (publicClass.picUrl !=null && publicClass.picUrl != '') }">
                                        <a data-id="${publicClass.picUrl}" onclick="reMove(this);">取消</a>
                                    </c:if>
                                    <c:if test="${publicClass.picUrl !=null && publicClass.picUrl != ''}">
                                        <img src="${publicClass.picUrl}" onclick="Change(this);"
                                             style="width: 260px; height: 130px;">
                                    </c:if>
                                    <c:if test="${publicClass.picUrl ==null || publicClass.picUrl == ''}">
                                        <img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);"
                                             style="width: 233px; height: 130px;">
                                    </c:if>
                                </div>
                            </div>
                            <div class="move"><span>展示图比例宽高690：300</span></div>
                            <c:if test="${isCK==''||isCK==null}">
                                <input type="hidden" id="uploader" name="uploader">
                            </c:if>
                            <span id="tp_queue"></span>

                            <div class="clear"></div>
                        </div>
                    </div>

                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${publicClass.id}"/>
                        <button class="btn btn-warning" type="button"
                                onclick="saveOrUpdate();">确定
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    var checker;

    $(function () {
        uploadFile();
        checker = initValidator();
        $.createMemberSelector({
            "inputIdSelector": "#teacherId",
            "isOnTopWindow": true,
            "enableBatch": false
        });
    });


    function uploadFile() {
        var obj = $("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            fileObjName: 'file',
            fileTypeDesc: "文件上传",
            fileTypeExts: '*.gif; *.jpg; *.png;*.jpeg;*.bmp',
            method: 'post',
            multi: false, // 是否能选择多个文件
            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout: 1,
            queueSizeLimit: 1,
            fileSizeLimit: 4 * 1024,
            buttonText: "上传封面",
            requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
            height: 20,
            width: 70,
            onUploadSuccess: function (file, data, response) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"><a data-id="'
                    + $jsonObj.uuid
                    + '" onclick="reMove(this);">取消</a><img style="width:233px;height:130px;" class="ims" onclick="Change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                $("#uuid").val($jsonObj.uuid);
                $.success("上传成功!", 9);
                $("#zp").html(img);
            },
            onUploadStart: function (file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onUploadError: function (file, errorCode, errorMsg,
                                     errorString) {
                $.alert('The file ' + file.name
                    + ' could not be uploaded: '
                    + errorString);
            }
        });

    }

    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
        $("#uuid").val("");
        $("#zp").append('<div class="img_1"><img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);" style="width: 233px; height: 130px;"></div>');
    }

    //获取文件的缩略图路径
    function getFileUrls() {
        var imgs = $(".img_1");
        var urls = "";
        $.each(imgs, function (index, value) {
            urls += ($(value).find("img").attr("src") + ",");
        });
        if (urls != "") {
            urls = urls.substring(0, urls.length - 1);
        }
        return urls;
    }


    function initValidator() {
        return $("#publicClass_form").validate({
            errorClass: "myerror",
            rules: {
                "name": {
                    required: true,
                    maxlength: 15,
// 					stringCheck : true
                    isContainsSpecialChar: true
                },
                "expiryDate": {
                    required: true
                },
                "url": {
                    required: true
                },"uuid": {
                    required: true
                },
            },
            messages: {
                "uuid" : {
                    required : "问卷封面必须添加"
                }
            }
        });
    }

    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            var loader = new loadLayer();
            var $id = $("#id").val();
            var $requestData = formData2JSONObj("#publicClass_form");
            var url = "${ctp}/questionnaire/creator";
            if ("" != $id) {
                url = "${ctp}/questionnaire/update";
            }
            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    $.success('操作成功');
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        if (parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else {
                        $.error("操作失败");
                    }
                } else {
                    $.error("操作失败");
                }
                loader.close();
            });
        }
    }

</script>
</html>