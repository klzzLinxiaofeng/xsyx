<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-fileupload.js"></script>
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
span{
/* background-color:#fc0; */
display:-moz-inline-box;
display:inline-block;
float:left;
font-weight: bold;
width:70px; 
}
</style>

</head>
<body>
	<jc:cache tableName='pj_team_student' echoField='name' value='262' paramName='student_id'></jc:cache>
</body>
</html>
