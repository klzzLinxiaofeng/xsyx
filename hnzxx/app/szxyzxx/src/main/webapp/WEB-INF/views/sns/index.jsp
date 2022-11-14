<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants" %>
<%@include file="/views/embedded/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	#alert_info{position:relative;height:164px;background:url("${pageContext.request.contextPath}/res/css/extra/images/sns_login.png") no-repeat center center;text-align:center;margin-top:100px;padding-top:470px;font-size:30px;font-family:"微软雅黑"}
	#alert_info span{color:#ED5565;padding:0 5px;}
	.book{background:url("${pageContext.request.contextPath}/res/css/extra/images/book.gif") no-repeat center center;z-index:0;height:256px;position:absolute;top:242px;width:100%;}
	#error_info{position:relative;height:164px;background:url("${pageContext.request.contextPath}/res/css/extra/images/sns_login.png") no-repeat center center;text-align:center;margin-top:100px;padding-top:470px;font-size:30px;font-family:"微软雅黑"}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
	<c:choose>
		<c:when test='${repoInfo == "success"}'>
			正在为您跳转。。。
		</c:when>
		<c:otherwise>
			通讯错误
		</c:otherwise>
	</c:choose>
</title>
</head>
<body style="background-color:#E9E7E8">
	<div class="book"></div>
	<c:choose>
		<c:when test='${repoInfo == "success"}'>
			<div id="alert_info" >
				请稍等，<span>3</span>秒后为您跳转。
			</div>
		</c:when>
		<c:otherwise>
			<div id="error_info" >
				${msg}
			</div>
		</c:otherwise>
	</c:choose>
</body>
<c:choose>
	<c:when test='${repoInfo == "success"}'>
		<script type="text/javascript">
			var s = 3;
			var interVal = window.setInterval(showalert, 1000); 
			function showalert() { 
				s--;
				$("#alert_info").html("请稍等，<span>" + s + "</span>秒后为您跳转。");
			}
			//定时执行，5秒后执行show() 
			window.setTimeout(show, 3000); 
			function show() { 
				window.clearInterval(interVal);
				window.open("<%= SysContants.SNS_BASE_PATH%>", "_self");
			} 
		</script>
	</c:when>
</c:choose>
</html>
<c:if test='${repoInfo == "success" }'>
	${msg}
</c:if>