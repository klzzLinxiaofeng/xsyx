<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/extra/udemy-blessed3_css.css" />
<title>浏览器下载</title>
<style>
	body{
		background-color:#fff;
	}
</style>

</head>
<body style="background-color:#fff;">
	<div >
	<div id="big-logo">
			<%-- <img class="logo1" src="${pageContext.request.contextPath}/res/css/extra/images/logo1.jpg"> --%>
			<img class="old" src="${pageContext.request.contextPath}/res/css/extra/images/old.jpg">
			</div>
		<div class="container" id="upgrade-browser">
			
			<p id="we">我们发现您的浏览器有些陈旧！很遗憾我们不支持此浏览器。 
			</p>
			<p id="please">请使用以下浏览器之一：
			</p>
			<div id="browsers">
			<a class="br1" href="https://www.google.com/intl/cn/chrome/browser/">Chrome</a>
			<a class="br2" href="http://www.mozilla.org/zh-CN/firefox/new/">Firefox</a>
			<a class="br3" href="http://www.apple.com/safari/">Safari</a>
			<a class="br4" href="http://windows.microsoft.com/zh-CN/internet-explorer/download-ie">IE 8/9/10</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
  document.getElementsByTagName('body').style.background="#fff";
	</script>
</body>
</html>