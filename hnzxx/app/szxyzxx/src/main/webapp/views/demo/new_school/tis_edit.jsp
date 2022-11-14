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
</head>
<body style="background-color: #ffffff;">
<div class="tis_edit">
<form id="signupForm" class="form-horizontal" method="get" action="">
	<div class="select_div">
		<span><i style="font-weight: normal;color:red">*</i>姓名：</span>
		<input type="text" id="teachername" name="teachername">
	</div>
	<div class="select_div">
		<span>别名：</span>
		<input type="text">
	</div>
	<div class="select_div">
		<span>性别：</span>
		<input type="radio" name="sex">男
		<input type="radio" name="sex" style="margin-left: 50px;">女
	</div>
	<div class="select_div">
		<span><i style="font-weight: normal;color:red">*</i>手机号码：</span>
		<input type="text" id="phone" name="phone">
	</div>
	<div class="select_div">
		<span>部门：</span>
		<input type="text">
	</div>
	<div class="select_div">
		<span>职务：</span>
		<input type="text">
	</div>
	<div class="select_div">
		<span>编制：</span>
		<input type="radio" name="sex">在编
		<input type="radio" name="sex" style="margin-left: 50px;">临聘
	</div>
	<div style="text-align:center;">
		<input class="btn btn-blue" type="submit"></input>
	</div>
</form>
</div>
<script>
$("#signupForm").validate({
	   submitHandler : function() {
	            
	        }, 
	    rules: {
	        teachername: {
	            required:true,
	            digits:true 
	        },
	        phone : {  
	            required : true,  
	            minlength : 11, 
	            isMobile : true  
	        }, 
	                 },
	   messages: {
        	teachername: "请输入正确名字",
        	 phone : {  
                 required : "请输入手机号",  
                 minlength : "不能小于11个字符",  
                 isMobile : "请正确填写手机号码"  
             }
             }
	             

});
// 手机号码验证  
jQuery.validator.addMethod("isMobile", function(value, element) {  
    var length = value.length;  
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;  
    return this.optional(element) || (length == 11 && mobile.test(value));  
}, "请正确填写手机号码");  
</script>
</body>
</html>