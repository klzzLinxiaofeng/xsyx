<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pc端下载</title>
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="pragram" content="no-cache">
<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_left.css" rel="stylesheet">
</head>
<style>
</style>
<body>
<div class="rwgl_bg"></div>
<div class="rwgl_btn">
    <a href="http://test.studyo.cn/cr/learningPlan/index" class="a1" target="_blank">导学案</a>
    <a href="http://test.studyo.cn/cr/paper/index" class="a2" target="_blank">试卷</a>
    <a href="${pageContext.request.contextPath}/assessment/teacher/menu" class="a3" target="_blank">教学反馈</a>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/statistics/jquery-2.1.1.js"></script>
<script>
$(function(){
    $.ajax({
        type : "POST",
        async:false,
        url : "${pageContext.request.contextPath}/getCrPath",
        dataType : "text",
        success : function(data){
            // vat data="http://test.studyo.cn/cr";
            $(".a1").attr('href',data+'/learningPlan/index?dm=YUN_JIAO_XUE_DAO_XUE_AN');
            $(".a2").attr('href',data+'/paper/index?dm=YUN_JIAO_XUE_SHI_JUAN');
        },
        error:function(){
            // alert('fail');
        }
    });
})

</script>
</html>