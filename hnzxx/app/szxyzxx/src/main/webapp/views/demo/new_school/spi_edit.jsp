<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title></title>
<style>
input[type="submit"]{
	width: 100px;
    height: 40px;
}
.tis_edit span {
    width: 86px;
}
</style>
</head>
<body style="background-color: #ffffff;">
<div class="tis_edit" style=" margin-right: 0;margin-left: 18px;">
<form id="signupForm" class="form-horizontal" method="get" action="">
	<div class="select_div">
		<span>班内学号：</span>
		<input type="text">
	</div>
	<div class="select_div">
		<span><i style="font-weight: normal;color:red">*</i>姓名：</span>
		<input type="text" id="studentname" name="studentname">
	</div>
	<div class="select_div">
		<span><i style="font-weight: normal;color:red">*</i>年级：</span>
		<input type="text" id="nianji" name="nianji">
	</div>
	<div class="select_div">
		<span><i style="font-weight: normal;color:red">*</i>班级：</span>
		<input type="text" id="banji" name="banji" >
	</div>
	<div class="select_div">
		<span>监护人：</span>
		<input type="text">
	</div>
	<div class="select_div">
		<span>监护人手机：</span>
		<input type="text">
	</div>
	<div style="text-align:center;">
		<input class="btn btn-blue" type="submit" value="保存"></input>
	</div>
</form>
</div>
<script>
$("#signupForm").validate({
	   submitHandler : function() {
	            
	        }, 
	    rules: {
	    	studentname: {
	            required:true,
	            minlength: 2

	        },
	        nianji : {  
	            required : true 
	        }, 
	        banji : {  
	            required : true,
	            number:true,
	            digits:true,
	            maxlength:2
	        }
	                 },
	   messages: {
		   studentname: {
               required: '请输入姓名',
               minlength: '请至少输入两个字符'
     	  },
		   nianji : {  
                 required : "请输入年级",  
            },
            banji : {  
                required : "请输入班级",  
                number:"请输入数字",
	            digits:"必须输入整数",
                maxlength : "不能大于2个字符",  
            }
             }

	    });

</script>
</body>
</html>