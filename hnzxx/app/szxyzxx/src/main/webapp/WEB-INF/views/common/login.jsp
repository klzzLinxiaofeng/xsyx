<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link href="${pageContext.request.contextPath}/res/css/common/login.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/login/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/num0/current_u.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/login/jquery.placeholder.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/login/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/login/prettify.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/login/jquery.ua.js"></script>
<style>
	body{
		background-color:#A0D468;
	}
</style>
<script type="text/javascript">
var h = document.documentElement.clientHeight;

	$(function(){
		$(".layout").css("height",h);
		$('input, textarea').placeholder();
	});
</script>
</head>
<body id="lgn_body" >
	<div class="layout" style="overflow:hidden">
		<div class="main_contain" >
		<div class="login_top">
			<div class="lt_main">
				<div class="logo"></div>
				<p style="display:none">客服电话：<span>400-608-8260</span></p>
			</div>
			</div>

			<div class="login">

				<div class="login-form">
					<form>
						<div class="control-group" style="margin-bottom:10px;">
							<input type="text" id="lgn_username" placeholder="用户名">
<!-- 							手机号/用户名/邮箱 -->
						</div>
						<div class="control-group">
							<input type="password" id="lgn_password" placeholder="请输入您的密码">
							<!-- 					<a href="javascript:void(0)">忘记密码？</a> -->
						</div>
						<div class="control-remember" style="display : none">
							<input type="checkbox" id="lgn_remember">自动登录
						</div>
						<div class="control-login" style="">
							<a href="javascript:void(0)" class="login"
								onclick='user_login("${pageContext.request.contextPath}", "#lgn_username", "#lgn_password", "#lgn_remember", "#lgn_info")'></a>
							<div style="position:absolute;top:48px;left:103px;color:red">
								<span id="lgn_info"></span>
							</div>
						</div>
					</form>
				</div>
				<div class="login_bottom" style="display:none">
					<p>&copy;2015 广州迅云教育科技有限公司  版权所有 粤ICP备09109031号</p>
					<p>公司地址：广州市番禺区大石镇石北大道大维工业区自编138号A3栋4楼</p>
				</div>
			</div>
			<div class="login_bottom"></div>
		</div>
	</div>
</body>
<script type="text/javascript">


	$(function() {

		$("#lgn_password").on("keyup", function(event) {
			if(event.keyCode == 13) {
				user_login("${pageContext.request.contextPath}", "#lgn_username", "#lgn_password", "#lgn_remember", "#lgn_info");
			}
		});

		if(window != top){
			$("#lgn_body").hide();
			$.errorDialog("您长时间未操作平台，为确保您安全使用，请重新登录", function() {
				top.location.href=location.href;
			}, function(index) {
				top.location.href=location.href;
			})
		}
	});
</script>
<script type="text/javascript">
/*判断浏览器版本是否过低*/
$(function(){
	var b_name = navigator.appName;
	var b_version = navigator.appVersion;
	var version = b_version.split(";");
	if (b_name == "Microsoft Internet Explorer") {
	var trim_version = version[1].replace(/[ ]/g, "");
		/*如果是IE6或者IE7*/
		if (trim_version == "MSIE7.0" || trim_version == "MSIE6.0" || trim_version == "MSIE8.0") {
			$("#lgn_body").css("background-color","#E9E7E8");
			$("#lgn_body").load("${pageContext.request.contextPath}/views/embedded/download.jsp");
		}
	}
 	 if($.ua.is360se||$.ua.isLiebao||$.ua.isMaxthon||$.ua.isQQ||$.ua.isSougou){
 		$("#lgn_body").css("background-color","#E9E7E8");
 		$("#lgn_body").load("${pageContext.request.contextPath}/views/embedded/download.jsp");
 	}

});


$.ajax({
	type : "GET",
	url : "http://127.0.0.1:2222/school/basic/term/listAll/jsonp",
	dataType : "jsonp",//数据类型为jsonp
	jsonp : "jsonpCallback",//服务端用于接收callback调用的function名的参数
	data:{
		schoolId  : 1
	},
	success : function(data){
		alert(data);
	},
	error:function(){

	}
});


</script>
</html>