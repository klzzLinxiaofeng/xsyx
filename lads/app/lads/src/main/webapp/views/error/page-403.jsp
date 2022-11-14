<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>403</title>
</head>
<body>
	<div class="layout errorPage-container">
		<!-- Navbar================================================== -->
		<!-- 		<div class="navbar navbar-inverse top-nav"> -->
		<!-- 			<div class="navbar-inner"> -->
		<!-- 				<div class="container"> -->
		<!-- 					<span class="home-link"><a href="index.html" -->
		<!-- 						class="icon-home"></a></span><a class="brand" href="./index.html"><img -->
		<!-- 						src="images/logo-falgun.png" width="103" height="50" alt="Falgun"></a> -->
		<!-- 					<div class="btn-toolbar pull-right notification-nav"> -->
		<!-- 						<div class="btn-group"> -->
		<!-- 							<div class="dropdown"> -->
		<!-- 								<a class="btn btn-notification"><i class="icon-reply"></i></a> -->
		<!-- 							</div> -->
		<!-- 						</div> -->
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
		<div>
			<div class="container error-wrapper">
				<div class="row">
					<div class="span4 offset2">
						<div class="error-code">
							403
							<div></div>
						</div>
					</div>
					<div class="span4">
						<div class="error-message">
							<h4>哎 哟! 服 务 器 拒 绝 您 的 请 求...</h4>
							<p>我 们 很 抱 歉 您 无 权 限 访 问:(</p>
							<ul class="error-suggestion">
								<li>检查确定您有一个正确的地址。如果这是一个链接,它可以过时,在网站上不再可用.</li>
								<li>检查确定您登录的账号对此链接是否有操作权限</li>
<!-- 								<li>访问我们的完整的网站 <a href="#">网站地图在这里.</a></li> -->
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>