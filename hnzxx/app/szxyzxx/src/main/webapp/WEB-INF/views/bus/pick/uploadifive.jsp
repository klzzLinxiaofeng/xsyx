<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${pageContext.request.contextPath}/res/plugin/falgun/js/jquery.js" type="text/javascript"></script>
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
</head>
<body style="background-color: #cdd4d7 !important">
<div id="zpi" style="display:inline-block;">
    <div class="img_1">
        <c:if test="${(isCK==''||isCK==null) && (aestheticPojo.gameWorksUrl !=null && aestheticPojo.gameWorksUrl != '') }">
            <a data-id="${aestheticPojo.gameWorksUrl}" onclick="reMove1(this);">取消</a>
        </c:if>
        <c:if test="${aestheticPojo.gameWorksUrl !=null && aestheticPojo.gameWorksUrl != ''}">
            <img src="${aestheticPojo.gameWorksUrl}" onclick="change(this);"
                 style="width: 260px; height: 130px;">
        </c:if>
        <c:if test="${aestheticPojo.gameWorksUrl ==null || aestheticPojo.gameWorksUrl == ''}">
            <img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);"
                 style="width: 233px; height: 130px;">
        </c:if>
    </div>
</div>
    <input type="file" id="file_upload" name="file_upload" />
</body>

<script type="text/javascript">
    $(function () {
        $('#file_upload').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传文件',
            removeCompleted:true,
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                        alert($jsonObj.uuid)
                alert($jsonObj.url)
                var img = '<div class="img_1"><a data-id="'
                    + $jsonObj.uuid
                    + '" onclick="reMove(this);">取消</a><img style="width:233px;height:130px;" class="ims" onclick="change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                $("#gameWorksId").val($jsonObj.uuid);
                alert("上传成功!");
                $("#zpi").html(img);
            }
        });
    });

    function change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
        $("#fineArtId").val("");
        $("#zpi").append('<div class="img_1"><img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);" style="width: 233px; height: 130px;"></div>');
    }
</script>
</html>