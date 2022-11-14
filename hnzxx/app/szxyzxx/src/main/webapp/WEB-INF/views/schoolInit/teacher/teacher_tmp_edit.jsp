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
	<div class="select_div">
		<span>部门：</span>
		<input type="text" value="${result.department }" id="department" maxlength="10">
	</div>
	<div class="select_div">
		<span>职务：</span>
		<input type="text" value="${result.position }" id="position" maxlength="20">
	</div>
	<div style="text-align:center;">
		<input class="btn btn-blue" type="submit"></input>
	</div>
	<div class="tis_error" style="display:none;text-align: center;padding-top:36px;">
		<p>修改数据有问题，无法保存</p>
		<p style="color:#ff5252">请确定导入文件是否正确</p>
	</div>
</form>
</div>
<script>
$("input[name='phone']").focus(function(){
$('label.error1').remove();
});
var teacherid = "${result.id}";

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
	         digits:true,
	         rangelength:[11,11] 
	    }
	},
	messages: {
		teachername: "请输入正确名字",
		phone : {  
			required : "请输入手机号", 
		    digits:"请输入数字",
		    rangelength:"请输入正确号码" 
		 }
	},
	submitHandler : function() {
		var status = "${result.status}";
		if("1"==status) {
			handleWarn();
		} else {
			handleError();
		}
	}
});

function handleError(){
	$('.tis_edit label.error1').remove();
	var id = "${result.id}";
	var teachername = $("#name").val();
	var sex = $("input[name='sex']:checked").val();
	var phone = $("#phone").val();
	var department = $("#department").val();
	var position = $("#position").val();
	var alias = $("#alias").val();
	
	var data = {"name":teachername, "sex":sex, "phone":phone,
			"department":department, "position":position, "alias":alias};
	
	var label1 = " <label class='error1'>";
	var label2 = "</label>"; 
	
	$.ajax({
        url: "${pageContext.request.contextPath}/teacher/init/add",
        type: "POST",
        data: {"data": JSON.stringify(data)},
        async: true,
        success: function(result) {
        	var data = JSON.parse(result);
        	var status = data.status;
        	if("0"==status) {
        		updateStatus(0, data.teacherId);
        	} else if("1"==status) {
        		alert(data.errorInfo);
        	} else if("2"==status){
        		if(data.errorFiled=="phone") {
        			$('input[name="phone"]').after(label1 + data.errorInfo + label2);
        			//alert(data.errorInfo);
        		} else if(data.errorFiled=="name") {
        			$('input[name="name"]').after(label1 + data.errorInfo + label2);
        			//alert(data.errorInfo);
        		} else if(data.errorFiled=="alias") {
        			$('input[name="alias"]').after(label1 + data.errorInfo + label2);
        			//alert(data.errorInfo);
        		}
        	}
        	
        }
    });
}

function updateStatus(status, teacherId){
	var id = "${result.id}";
	var teachername = $("#name").val();
	var sex = $("input[name='sex']:checked").val();
	var phone = $("#phone").val();
	var department = $("#department").val();
	var position = $("#position").val();
	var alias = $("#alias").val();
	
	$.ajax({
        url: "${pageContext.request.contextPath}/tmp/teacher/update",
        type: "POST",
        data: {"id":id, "name":teachername, "sex":sex, "phone":phone, "status":status,
			"department":department, "position":position, "alias":alias, "teacherId":teacherId},
        async: true,
        success: function(result) {
        	document.cookie="close_tab=close";
        	$.closeWindow();
        }
    });
}

function handleWarn(){
	var id = "${result.id}";
	var teachername = $("#name").val();
	var sex = $("input[name='sex']:checked").val();
	var phone = $("#phone").val();
	var department = $("#department").val();
	var position = $("#position").val();
	var alias = $("#alias").val();
	
	$.ajax({
        url: "${pageContext.request.contextPath}/tmp/teacher/update",
        type: "POST",
        data: {"id":id, "name":teachername, "sex":sex, "phone":phone,
			"department":department, "position":position, "alias":alias},
        async: true,
        success: function(result) {
        	$(window.parent.document).find("#tii_warn_data").click();
        	$.closeWindow();
        }
    });
}
</script>
</body>
</html>