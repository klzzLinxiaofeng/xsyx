<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页</title>
</head>
<body>
	<script>
		var dm = document.domain;
		var url = "${pageContext.request.contextPath}";
		var currentSystemId = "${param.currentSystemId}";
		if(dm.indexOf("127.0.0.1") != -1) {
			window.location = url + "/login";
		} else {
			window.location = url + "/login";
		}
	</script>
</body>
</html>