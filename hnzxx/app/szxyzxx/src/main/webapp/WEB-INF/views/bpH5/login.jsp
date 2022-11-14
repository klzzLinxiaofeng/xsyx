<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="cache-control" content="max-age=0" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <link rel="stylesheet" href="${ctp}/res/css/bp/h5/login/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/login/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/num0/current_u.js"></script>
    <title>登录</title>
</head>
<style>
    html{ background: url("${ctp}/res/images/bp/h5/login/bg.png") no-repeat 0 bottom; background-size: 100%; height: 100%; max-width: 640px; margin: 0 auto}
    .titlelogin_logo{ text-align: center; margin-top: 16%; margin-bottom: 5%}
    .center{ width: 90%; margin: 0 auto}
    .input_style{ border: 1px solid #b5deff;  text-align: center; color: #2289c0; font-size: 16px; line-height: 45px; line-height: 45px; margin-bottom: 15px; width: 100%;}
    .but{ text-align: center; color: #fff; font-size: 16px; line-height: 47px; line-height: 47px; margin-bottom: 15px; width: 100%; border: none;
        background: -webkit-linear-gradient(115deg, #75cfff, #06a2f5); /* Safari 5.1 - 6.0 */
        background: -o-linear-gradient(115deg,#75cfff, #06a2f5); /* Opera 11.1 - 12.0 */
        background: -moz-linear-gradient(115deg, #75cfff, #06a2f5); /* Firefox 3.6 - 15 */
        background: linear-gradient(115deg,#75cfff, #06a2f5); /* 标准的语法（必须放在最后） */}
    input::-webkit-input-placeholder { /* WebKit browsers */
        color: #b5deff;
    }
    input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
        color: #b5deff;
    }
    input::-moz-placeholder { /* Mozilla Firefox 19+ */
        color: #b5deff;
    }
    input:-ms-input-placeholder { /* Internet Explorer 10+ */
        color: #b5deff;
    }
</style>
<body>
	<div class="titlelogin_logo"><img src="${ctp}/res/images/bp/h5/login/login_text.png" alt="" width="110px"></div>
	<div class="center">
	    <p><input type="text" id="username" class="input_style" placeholder="请输入帐号"></p>
	    <p><input type="text" id="password" class="input_style" placeholder="请输入密码"></p>
	    <p><span id="lgn_info"></span><p>
	    <p><input type="button" value="登录" class="but" 
	    	onclick="bp_login('${pageContext.request.contextPath}', '#username', '#password', '#lgn_remember', '#lgn_info')"></p>  	
	</div>
</body>
</html>