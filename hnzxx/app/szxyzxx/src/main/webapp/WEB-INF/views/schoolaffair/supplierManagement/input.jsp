<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity" %>
<html>
<head>
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <%@ include file="/views/embedded/common.jsp" %>
    <%@ include file="/views/embedded/plugin/zTree.jsp" %>
    <%@ include file="/views/embedded/plugin/avatar_js.jsp" %>
    <%--<%@ include file="/views/embedded/plugin/uploadify.jsp" %>--%>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>

    <%@include file="/views/embedded/plugin/grade_selector_js.jsp" %>
    <link type="text/css" href="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.css"
          rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/extra/micro/font-awesome-ie7.min.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/xyuploader.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/md5_md5blob.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">

    <title></title>
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


        .form-horizontal .conss {
            width: 65%;
            display: inline-flex;
        }

        .form-horizontal .conss3 {
            width: 65%;
            display: inline-flex;
        }
    </style>
</head>
<body style="background-color: cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="supplier_form" action="javascript:void(0);">


                    <div class="control-group">
                        <label class="control-label" style="width: 20%;"><font style="color: red">*</font>
                            供应商名称：
                        </label>
                        <div class="controls">
                            <input type="text" id="name" name="name" class="span4" placeholder=""
                                   value="${supplier.name}" style="width: 40%">
                        </div>
                    </div>

                    <div class="control-group conss">
                        <label class="control-label" style="width: 15%;"><font style="color: red">*</font>
                            供应商资质图片：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="uuid" name="uuid" value="${supplier.uuid}">
                            </div>
                            <div id="zp" style="display:inline-block;">
                                <div class="img_1">
                                    <c:if test="${(isCK==''||isCK==null) && (supplier.url !=null && supplier.url != '') }">
                                        <a data-id="${supplier.url}" onclick="reMove(this);">取消</a>
                                    </c:if>
                                    <c:if test="${supplier.url !=null && supplier.url != ''}">
                                        <img src="${supplier.url}" onclick="Change(this);"
                                             style="width: 260px; height: 130px;">
                                    </c:if>
                                    <c:if test="${supplier.url ==null || supplier.url == ''}">
                                        <img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);"
                                             style="width: 233px; height: 130px;">
                                    </c:if>
                                </div>
                            </div>
                            <c:if test="${isCK==''||isCK==null}">
                                <input type="hidden" id="uploaderImage" name="uploaderImage">
                            </c:if>
                            <span id="tp_queue"></span>

                            <div class="clear"></div>
                        </div>


                    </div>

                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${supplier.id}"/>
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
    var catalogEnd = 0;

    $(function () {
        checker = initValidator();
        $.createMemberSelector({
            "inputIdSelector": "#teacherId",
            "isOnTopWindow": true,
            "enableBatch": false
        });

        uploadImageFile();
        // 获取类别
    });


    function uploadImageFile() {
        $('#uploaderImage').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传封面',
            'removeCompleted':true,
            'height' : 20,
            'width' : 70,
            'formData': {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
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
            onUpload:function (file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onFallback : function() {
                alert("该浏览器无法使用!");
            },
        });



     /*   var obj = $("#uploaderImage").uploadify({
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
*/
    }

    function initValidator() {
        return $("#supplier_form").validate({
            errorClass: "myerror",
            rules: {
                "name": {
                    required: true,
                    maxlength: 255,
                }
            },
            messages: {}
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


    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            var uuid = $("#uuid").val();
            var name = $("#name").val();
            var id = $("#id").val();
            if (uuid == null || uuid == '' || uuid == undefined) {
                $.alert("请上传图片");
                return;
            }
            var count = 0;
            if (id == null || id == '' || id == undefined) {
                // 判断添加的数量
                $.ajax({
                    type: "get",
                    url: "${ctp}/schoolaffair/recipes/supplier/findCount?type=1",
                    dataType: "json",
                    async: false,
                    success: function (data) {
                        console.log(parseInt(data));
                        if (parseInt(data) > 5) {
                            $.alert("供应商资质仅可添加6张图片！");
                            count = parseInt(data);
                            return false;
                        }
                    }, error: function () {
                        $.error("操作失败");
                        return false;
                    }
                });
            }
            if (count > 5) {
                return;
            }

            var url = "${ctp}/schoolaffair/recipes/supplier/modifySupplier?type=1&uuid=" + uuid + "&name=" + name;
            if ("" != id) {
                url = "${ctp}/schoolaffair/recipes/supplier/modifySupplier?type=1&uuid=" + uuid + "&id=" + id + "&name=" + name;
            }
            $.post(url, function (data, status) {
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
                loader.close();
            });
        }
    }


</script>
</html>