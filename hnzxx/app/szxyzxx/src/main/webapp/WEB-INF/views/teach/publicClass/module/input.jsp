<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
<%--    <%@ include file="/views/embedded/plugin/uploadify.jsp" %>--%>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <style>

    </style>
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 20px">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_form" action="javascript:void(0);">
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            姓名：
                        </label>
                        <div class="controls">
                            <input type="text" id="name" name="name" class="span4" placeholder=""
                                   value="${publicClass.name}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            图片：
                        </label>

                        <div class="controls">
                            <div>
                                <input type="hidden" id="coverUuid" name="coverUuid" value="${publicClass.coverUuid}">
                            </div>
                            <div id="zp" style="display:inline-block;">
                                <div class="img_1">
                                    <c:if test="${(isCK==''||isCK==null) && (publicClass.coverUrl !=null && publicClass.coverUrl != '') }">
                                        <a data-id="${publicClass.coverUrl}" onclick="reMove(this);">取消</a>
                                    </c:if>
                                    <c:if test="${publicClass.coverUrl !=null && publicClass.coverUrl != ''}">
                                        <img src="${publicClass.coverUrl}" onclick="Change(this);"
                                             style="width: 260px; height: 130px;">
                                    </c:if>
                                    <c:if test="${publicClass.coverUrl ==null || publicClass.coverUrl == ''}">
                                        <img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);"
                                             style="width: 233px; height: 130px;">
                                    </c:if>
                                </div>
                            </div>
                            <div><span>支持jpg、gif、png、bmp格式，宽高3:4</span></div>
                            <c:if test="${isCK==''||isCK==null}">
                                <input type="hidden" id="uploader" name="uploader">
                            </c:if>
                            <span id="tp_queue"></span>

                            <div class="clear"></div>
                        </div>


                     <%--   <div class="controls">
                            <div>
                                <input type="hidden" id="coverUuid" name="coverUuid" value="${publicClass.coverUuid}">
                            </div>
                            <div id="zp" style="display:inline-block;">
                                <div class="img_1">
                                    <c:if test="${(isCK==''||isCK==null) && (publicClass.coverUrl !=null && publicClass.coverUrl != '') }">
                                        <a data-id="${publicClass.coverUrl}" onclick="reMove(this);">取消</a>
                                    </c:if>
                                    <c:if test="${publicClass.coverUrl !=null && publicClass.coverUrl != ''}">
                                        <img src="${publicClass.coverUrl}" onclick="Change(this);"
                                             style="width: 260px; height: 130px;">
                                    </c:if>
                                    <c:if test="${publicClass.coverUrl ==null || publicClass.coverUrl == ''}">
                                        <img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);"
                                             style="width: 233px; height: 130px;">
                                    </c:if>
                                </div>
                            </div>
                            <div><span>支持jpg、gif、png、bmp格式，宽高16:9</span></div>
                            <c:if test="${isCK==''||isCK==null}">
                                <input type="hidden" id="uploader" name="uploader">
                            </c:if>
                            <span id="tp_queue"></span>

                            <div class="clear"></div>
                        </div>
--%>

                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            简介：
                        </label>
                        <div class="controls">
                            <textarea id="classDesc" name="teacherDesc" class="span4" placeholder="字符不可超过500" rows="3"
                                      maxlength="500"
                                      cols="1">${publicClass.teacherDesc}</textarea>
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
    });
    function uploadFile() {
        $('#uploader').uploadifive({
            'auto': true,
            'fileObjName': 'file',
            //'queueID': 'queue',
            'buttonText': '上传封面',
            'removeCompleted': true,
            'height': 20,
            'width': 70,
            'formData': {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file, data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"><a data-id="'
                    + $jsonObj.uuid
                    + '" onclick="reMove(this);">取消</a><img style="width:233px;height:130px;" class="ims" onclick="Change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                $("#coverUuid").val($jsonObj.uuid);
                $.success("上传成功!", 9);
                $("#zp").html(img);
            },
            onUpload: function (file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onFallback: function () {
                alert("该浏览器无法使用!");
            },
        });
    }

  /*  function uploadFile() {
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
                $("#coverUuid").val($jsonObj.uuid);
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

    }*/

    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
        $("#coverUuid").val("");
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
                    maxlength: 10,
					stringCheck : true,
                    isContainsSpecialChar: true
                },
                "teacherDesc": {
                    required: true,
                    maxlength: 500
                },
                "coverUuid": {
                    required: true
                }

            },
            messages: {
                "coverUuid": "请上传封面"
            }
        });
    }

    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            var loader = new loadLayer();
            var $id = $("#id").val();
            var $requestData = formData2JSONObj("#publicClass_form");
            var url = "${ctp}/teach/publicClass/teacher/creator";
            if ("" != $id) {
                url = "${ctp}/teach/publicClass/teacher/update";
            }
            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    $.success('操作成功');
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        parent.$("iframe").each(function () {
                            $(this).attr('src', $(this).attr('src'));//需要引用jquery
                        })
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