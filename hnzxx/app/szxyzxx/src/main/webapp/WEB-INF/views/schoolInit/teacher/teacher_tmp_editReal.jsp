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
<form id="signupForm" class="form-horizontal">
	<div class="select_div">
		<span><i style="font-weight: normal;color:red">*</i>姓名：</span>
		<input type="text" id="name" name="name" value="${result.name}" maxlength="30">
	</div>
	<div class="select_div">
		<span>别名：</span>
		<input type="text" value="${result.alias}" id="alias" maxlength="6" name="alias">
	</div>
	<div class="select_div">
		<span>性别：</span>
		<c:if test="${result.sex=='男' }">
			<input type="radio" name="sex" checked="checked" value="男">男
			<input type="radio" name="sex" style="margin-left: 50px;" value="女">女
		</c:if>
		<c:if test="${result.sex=='女' }">
			<input type="radio" name="sex" value="男">男
			<input type="radio" name="sex" style="margin-left: 50px;" checked="checked" value="女">女
		</c:if>
		<c:if test="${empty result.sex }">
			<input type="radio" name="sex" value="男">男
			<input type="radio" name="sex" style="margin-left: 50px;" value="女">女
		</c:if>
	</div>
	<div class="select_div">
		<span><i style="font-weight: normal;color:red">*</i>手机号码：</span>
		<input type="text" id="phone" name="phone" value="${result.phone }">
	</div>
	<div style="text-align:center;">
		<input class="btn btn-blue" type="submit" value="保存"></input>
	</div>
</form>
</div>
<script>
jQuery.validator.addMethod("isMobile", function(value, element) {  
    var length = value.length;  
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;  
    return this.optional(element) || (length == 11 && mobile.test(value));  
}, "请正确填写手机号码"); 

$("#signupForm").validate({
	rules: {
	    teachername: {
	        required:true,
	        digits:true 
	    },
	    phone : {  
	        required : true,  
	        minlength : 11,  
	        isMobile : true  
	    }
	},
	messages: {
		teachername: "请输入正确名字",
		phone : {  
		     required : "请输入手机号",  
		     minlength : "不能小于11个字符",  
		     isMobile : "请正确填写手机号码"  
		 }
	},
	submitHandler : function() {
		var label1 = " <label class='error1'>";
		var label2 = "</label>"; 
		var id = "${result.id}";
		var teacherId = "${result.teacherId}";
		var teachername = $("#name").val();
		var sex = $("input[name='sex']:checked").val();
		var phone = $("#phone").val();
		var alias = $("#alias").val();
		
		$.ajax({
	        url: "${pageContext.request.contextPath}/tmp/teacher/updateReal",
	        type: "POST",
	        data: {"id":id, "name":teachername, "sex":sex, "phone":phone,
	        	"teacherId":teacherId, "alias":alias},
	        async: true,
	        success: function(result) {
	        	if(result=="SUCCESS") {
	        		$(window.parent.document).find("#tii_daoru_ok").click();
		        	$.closeWindow();
	        	} else if(result="PHONE_REPEAT") {
	        		$('input[name="phone"]').after(label1 + "电话号码重复" + label2);
	        		//alert("电话号码重复");
	        	} else if(result="NAME_REPEAT") {
	        		$('input[name="name"]').after(label1 + "姓名重复，无别名" + label2);
	        		//alert("姓名重复，无别名");
	        	} else if(result="ALIAS_REPEAT") {
	        		$('input[name="alias"]').after(label1 + "别名已经存在" + label2);
	        		//alert("别名已经存在");
	        	}
	        }
	    });
	}
});
</script>
</body>
</html>