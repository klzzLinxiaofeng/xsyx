<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="apple-touch-fullscreen" content="YES">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta http-equiv="Expires" content="-1">
        <meta http-equiv="pragram" content="no-cache">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>登录</title>
        <link rel="stylesheet" href="${ctp}/res/css/statistics/parentlogin.css">
        <meta name="viewport" content="width=1080, user-scalable=no, target-densitydpi=device-dpi">
</head>
<body>
<div class="parent_logo"></div>
<div class="plogin_form">
    <div class="pass-form-item">
        <input type="text" id="username" placeholder="请输入孩子的登录账号">
    </div>
    <div class="pass-form-item">
        <input type="password" id="password" placeholder="请输入孩子账号的登录密码">
    </div>
    <div class="pass-form-item">
        <button onclick="login();" class="login_btn">登录</button>
    </div>
    <p class="error_message"></p>
</div>
<script type="text/javascript">
function login(){
	var username=$('#username').val();
	var password=$('#password').val();
	if(username==''){
		  $('#username').addClass('red_warning');
     	 $('.error_message').text("账号不能为空");
     	 return false;
	}
	if(password==''){
	 $('#password').addClass('red_warning');
   	 $('.error_message').text("密码不能为空");
   	 return false;
	}
	var data={};
	data.username=username;
	data.password=password;
    $.ajax({
        url: "${pageContext.request.contextPath}/parent/login/in",
        type: "POST",
        data: data,
        async: false,
        success: function(data) {
            $('.error_message').text('');
            $('#username,#password').addClass('red_warning');
         if(data=='-1'){
             $('#username').addClass('red_warning');
        	 $('.error_message').text("账号不存在");
         }else if(data=='-2'){
             $('#password').addClass('red_warning');
             $('.error_message').text("密码不正确");
         }else if(data=='-3'){
             $('.error_message').text("账号异常，请联系管理员");
         }else if(data=='-4'){
             $('.error_message').text("本学期暂无该学生信息");
         }else{
             $('.error_message').text("登录成功");
        	 var userId=parseInt(data);
        	 window.location.href="${pageContext.request.contextPath}/course/action/index?userId="+userId;
         }
        }
    });
}
</script>
</body>

</html>