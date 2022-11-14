<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <title></title>
</head>
<body style="background-color: #e3e3e3;" onload="IFrameResize()">
    <div class="sjcsh_xx">
        <a href="javascript:void(0)" id="import" class="on" onclick="importPage();">教师任课导入</a>
        <a href="javascript:void(0)" id="manage">教师任课管理</a>
        <a href="javascript:void(0)" id="subject">科目信息管理</a>
        <a href="javascript:void(0)" id="subjectGrade">年级科目管理</a>
        <%--<a href="javascript:void(0)" id="subjectTeacher">科任教师管理</a>--%>
    </div>
    <iframe src="${pageContext.request.contextPath}/team/teacher/init/import" style="" width="100%" frameborder="0" id="iframe_sencond" scrolling="no"></iframe>
<script>
$('.sjcsh_xx a').click(function(){
    $(this).siblings().removeClass('on');
    $(this).addClass('on');
    var id = $(this).attr('id');
    $('#iframe_sencond').attr('src', "${pageContext.request.contextPath}/team/teacher/init/" + id);
    var t=setTimeout("IFrameResize()",1000);

});

function IFrameResize(){
    var obj = parent.document.getElementById("iframe_first");  //取得父页面IFrame对象
    obj.height = this.document.body.scrollHeight;  //调整父页面中IFrame的高度为此页面的高度
}

function importPage() {
    $("#iframe_sencond").attr("src","${pageContext.request.contextPath}/team/teacher/init/import");
}
</script>
</body>
</html>