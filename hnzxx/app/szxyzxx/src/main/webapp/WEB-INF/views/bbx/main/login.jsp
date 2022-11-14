<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/bbx/ban.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/login/jquery-1.7.2.min.js"></script>
</head>
<body style="overflow-x:hidden">
<div class="top w1200">
	<h1><img src="${pageContext.request.contextPath}/res/images/bbx/main/logo.png"/></h1>
	<ul>
		<li  class="on"><a href="javascript:void(0)">首页</a></li>
		<li><a href="${pageContext.request.contextPath}/bbx/download">下载</a></li>
	</ul>
	<div class="tel"><span>400-6088260</span><i>客服电话</i></div>
</div>
<div class="b-wrap wrap">
	<div class="img bg-aa">
		
		
			<div class="login">
				<div><input type="text"  class="number" placeholder="账号" id="lgn_username"/></div>
				<div><input type="password"  placeholder="密码" class="password" id="lgn_password" /></div>
				<div><span class="remer"><input type="checkbox"  id="lgn_remember"></span><a href="#" class="aa">忘记密码?</a></div>
				<a href="javascript:void(0)" class="btn-login" onclick='bbx_login("${pageContext.request.contextPath}", "#lgn_username", "#lgn_password", "#lgn_remember", "#lgn_info")'>登录</a>
				<div style="position:absolute;top:48px;left:103px;color:red">
					<span id="lgn_info"></span>
				</div>
			</div>
	
	</div>
	
</div>

<div class="x-wrap">
	
	<div class="img bg-bb"></div>
	<div class="img bg-cc"></div>
	<div class="img bg-dd"></div>
	<div class="img bg-ee"></div>
	<div class="img bg-ff"></div>
	
</div>


<div class="footer">
	<div class="w1200 bg_img">
		<a class="qq" href="#">在线客服</a>
	</div>
<div>

</body>
<script type="text/javascript">

function bbx_login(url, username, password, remember, info_panel) {
	$.post(url+"/bbx/loginbbx", {
		username : $(username).val(),
		password : $(password).val(),
		isRememberMe : $(remember).prop("checked")
	}, function(data, status) {
		if("success" === status) {
			if("1002" === data) {
				location.href = url + "/bbx/role/selector";
			} else if ("1004" === data){
				$(info_panel).html("<front style=''>*账号不存在</>");
			} else if("2002" === data ) {
				$(info_panel).html("<front style=''>*您的账号被禁用</>");
			} else if("2003" === data) {
				$(info_panel).html("<front style=''>*您还未激活账号</>");
			} else if("2004" === data) {
				$(info_panel).html("<front style=''>*您的账号已失效</>");
			}else {
				$(info_panel).html("<front style=' '>*账号或者密码错误</>");
			}
		} else {
			$(info_panel).html("<front style=' '>*系统异常 无响应</>");
		}
	});
}
</script>
</html>