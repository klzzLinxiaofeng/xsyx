<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--  <%@ include file="/views/embedded/plugin/uploadify.jsp" %>--%>
    <%@ include file="/views/embedded/common.jsp" %>
    <%@ include file="/views/embedded/plugin/zTree.jsp" %>
    <%@ include file="/views/embedded/plugin/avatar_js.jsp" %>
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
</head>

<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="图书馆图片管理" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">

            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        图书馆图片上传
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" class="a3"
                               onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新</a>
                        </p>
                    </h3>
                </div>
                <div class="light_grey"></div>


                <div class="content-widgets" style="margin-bottom: 0">
                    <div class="widget-container" style="padding: 20px 0 0;">
                        <form class="form-horizontal tan_form" id="supplier_form" action="javascript:void(0);">

                            <div class="control-group conss">
                                <label class="control-label" style="width: 15%;"><font
                                        style="color: red">&nbsp;&nbsp;*</font>
                                    图片：
                                </label>
                                <div class="controls">
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
                                    <span id="tp_queue"> 图片将会自动上传到服务器</span>
                                    <div class="clear"></div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        uploadImageFile();
    });


    function uploadImageFile() {
        $('#uploaderImage').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传封面',
            removeCompleted:true,
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/commonLibrary',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"><a data-id="" onclick="reMove(this);">取消</a><img style="width:233px;height:130px;" class="ims" onclick="Change(this);" src="'
                    + $jsonObj.httpUrl
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
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





      /*  var obj = $("#uploaderImage").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/commonLibrary',
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
                var img = '<div class="img_1"><a data-id="" onclick="reMove(this);">取消</a><img style="width:233px;height:130px;" class="ims" onclick="Change(this);" src="'
                    + $jsonObj.httpUrl
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
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

    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
        $("#uuid").val("");
        $("#zp").append('<div class="img_1"><img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);" style="width: 233px; height: 130px;"></div>');
    }
</script>
</html>