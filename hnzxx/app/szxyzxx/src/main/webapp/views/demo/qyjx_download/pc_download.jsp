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
<link href="${pageContext.request.contextPath}/res/css/sygb/download.css" rel="stylesheet">
</head>
<style>
</style>
<body>
<div class="w1200">
<div class="one">
    <div class="leftFly">
        <p>一款<span style="color:#00b0ff">智能化</span>的</p>
        <p>备课系统</p>
        <a href="javascript:void(0)" class="btn nowDownload">立即下载</a>
    </div>
    <span class="cpt rightFly"></span>
</div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/statistics/jquery-2.1.1.js"></script>
<script>

    //滚动高度

    $(window).on("load",function(){
        $.ajax({
            type : "POST",
            async:false,
            url : "http://api.studyo.cn/public/app/release/getCurrent/jsonp",
            dataType : "jsonp",//数据类型为jsonp
            jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
            data:{
                appKey : "maiqituo#qyjx#educloud#mobile#pc",
            },
            success : function(data){

                $(".nowDownload").attr("href",data.data[0].setupFile);
            },
            error:function(){
                alert('fail');
            }
        });
    })

</script>
</html>