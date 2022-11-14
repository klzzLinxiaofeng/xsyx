<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
<link href="${pageContext.request.contextPath}/res/css/extra/cjfxxz.css" rel="stylesheet">
<title>成绩分析下载</title>
</head>
<div class="main">
    <div class="w1200">
        <div class="logo"></div>
        <p>一款帮助老师及家长</br><span class="span1">多维度</span><span class="span2">分析学生成绩</span></br>的分析系统</p>
        <a href="javascript:void(0)" class="ljxz" id="ljxz">立即下载</a>   
    </div>
</div>
<div class="footer">
    <p>
        <a href="javascript:void(0)">用户协议</a>    |   <a href="javascript:void(0)"> 联系我们</a>
    </p>
    <p>版权所有&copy;广州迈奇拓网络技术有限公司 2016-2017 粤ICP备17055136号-1</p>
</div>
<script>
$(window).on("load",function(){
    $.ajax({
        type : "POST",
        async:false,
        url : "https://api.studyo.cn/public/app/release/getCurrent/jsonp",
        dataType : "jsonp",//数据类型为jsonp
        jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
        data:{
            appKey : "maiqituo#educloud#jw#qycj",
        },
        success : function(data){
            $("#ljxz").attr("href",data.data[0].setupFile);
        },
        error:function(){
            alert('fail');
        }
    });
})
</script>
</html>