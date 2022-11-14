<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="cache-control" content="max-age=0"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="/res/css/bp/h5/login/style.css">
    <%@ include file="/views/embedded/common.jsp"%>
    <title>登录</title>
</head>
<style>
    html{ max-width: 640px; margin: 0 auto}
    .head{ position: relative}
    .text{ position: absolute; top: 0;left: 0; height: 100%}
    .text p { color: #fff;}
    .text table td{padding-left:20px;}
    .text table{ height: 100%; padding-left: 20px}
    .text table td{ vertical-align: middle}
    .user{ font-size: 15px; padding:8px 0}
    .list ul{ list-style: none; margin: 0 ; padding: 0}
    .user span{ font-size: 15px; font-weight: bold;}
    .list ul li{ width: 25%; float: left; margin: 10px 0px}
    .list ul li a{ width: 80%; margin: 0 auto; display: block}
</style>
<body>
<div class="head">
    <div class="head_bg">
    	<img src="${ctp}/res/images/bp/h5/login/index_bg.png" alt="" width="100%"> 	
    </div>
    <div class="text">
        <table>
            <tr><td><p class="user">亲爱的 <span>${userName}</span> :</p><p style=" font-size: 24px;">你好，欢迎登录</p></td></tr>
        </table>
    </div>
</div>
<div class="list">
    <ul>
    	<c:if test="${type == 4}">
    		 <li><a href="${ctp}/bp/h5/courseStudent/index"><img src="${ctp}/res/images/bp/h5/login/icon01.png" alt="" width="100%"></a></li>
        	<li><a><img src="${ctp}/res/images/bp/h5/login/icon_no.png" alt="" width="100%"></a></li>
        	<li><a><img src="${ctp}/res/images/bp/h5/login/icon_no.png" alt="" width="100%"></a></li>
       	 	<li><a><img src="${ctp}/res/images/bp/h5/login/icon_no.png" alt="" width="100%"></a></li>
    	</c:if>    
    </ul>
</div>
</body>
</html>