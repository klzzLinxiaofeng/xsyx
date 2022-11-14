<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网络会议</title>
<%@ include file="/views/embedded/common.jsp"%>
</head>
<body>
	
	<iframe style="display: none" id="login_frm" src="http://jiaoxueyun.com/login.action?formMap.username=chenwenguang&formMap.password=123456"></iframe>
	<iframe id="meeting_frm" src="" style="width:100%;"></iframe>
	
	<script type="text/javascript">
		$(function() {
			var login_frm = document.getElementById("login_frm");
			$(login_frm).load(function() {
				$("#meeting_frm").attr("src", "http://jiaoxueyun.com/teacher/lesson/breezeAction_go.action?url=/r3ijnmjsd40/?1=1");
			});
			var h = document.documentElement.clientHeight;
			$("#meeting_frm").css("height",h);
			
		});
	</script>
	
</body>
</html>