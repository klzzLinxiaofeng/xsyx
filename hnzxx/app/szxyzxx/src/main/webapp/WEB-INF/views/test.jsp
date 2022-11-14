<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/newSchoolInfoImport.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/massagePush.js"></script>  
<title>导学案</title>
</head>
<body>
<div>
	<input type="file" id="student" name="student">
	<button onclick="importStudentByFile();">点击</button>
</div>
<div>
	<input type="file" id="teacher" name="teacher">
	<button onclick="importTeacherByFile();">点击</button>
</div>
</body>
<script type="text/javascript">
newSchoolInfoImport.onPageLoad("116001");

function getMessage() {
	console.log("123456");
	newSchoolInfoImport.getMessage();
}

function importStudentByFile() {
	var file=dwr.util.getValue("student");
	newSchoolInfoImport.sendMessage("116001");
}

function importTeacherByFile() {
	var file=dwr.util.getValue("teacher");
	newSchoolInfoImport.importTeacherByFile(file, file.value, "166", "2017", "30059", "showMessage", function(result) {
		var data = JSON.parse(result);
		console.log(data);
	});
}

function showMessage(message) {
	console.log(message);
}
</script>
</html>