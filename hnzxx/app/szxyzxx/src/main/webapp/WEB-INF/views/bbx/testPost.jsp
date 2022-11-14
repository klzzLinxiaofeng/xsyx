<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-fileupload.js"></script>
<style type="text/css">
.tab-buttons {
	display: none;
}
</style>

</head>
<body>
	<div class="container-fluid">
		<button onclick="examCode();">点击</button>
	</div>
	<script>
		
		function examCode() {
			 var depExamCode={};
			
				 depExamCode={
						 'userId':15425,
						'schoolId':83
						
					 }
				
			
			$.ajax({
				type : "post",
				url : "${ctp}/school/user/getRolesOfSchool",
				data : depExamCode,
				success : function(data) {
					alert(JSON.stringify(data));
					
						
					
				}
			});
		
		}

		
	</script>
</body>
</html>
