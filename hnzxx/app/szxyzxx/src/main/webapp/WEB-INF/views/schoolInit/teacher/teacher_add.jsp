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
				<input type="text" id="teachername" name="teachername">
			</div>

			<div class="select_div">
				<span><i style="font-weight: normal;color:red">*</i>工号：</span>
				<input type="text" id="gh" name="gh">
			</div>

			<div class="select_div">
				<span><i style="font-weight: normal;color:red">*</i>卡号：</span>
				<input type="text" id="kh" name="kh">
			</div>


			<div class="select_div">
				<span>别名：</span>
				<input type="text" id="alias" name="alias">
			</div>
			<div class="select_div">
				<span>性别：</span>
				<input type="radio" name="sex" value="男">男
				<input type="radio" name="sex" style="margin-left: 50px;" value="女">女
			</div>
			<div class="select_div">
				<span><i style="font-weight: normal;color:red">*</i>手机号码：</span>
				<input type="text" id="phone" name="phone">
			</div>
			<div class="select_div">
				<span>部门：</span>
				<input type="text" id="department">
			</div>
			<div class="select_div" id="position">
				<span>职务：</span>
				<input type="text">
			</div>



			<div style="text-align:center;">
				<input class="btn btn-blue" type="submit"></input>
			</div>
		</form>
	</div>
</body>
<script>
$("input[name='phone']").focus(function(){
	$('label.error1').remove();
	});
//手机号码验证  
jQuery.validator.addMethod("isMobile", function(value, element) {  
    var length = value.length;  
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;  
    return this.optional(element) || (length == 11 && mobile.test(value));  
}, "请正确填写手机号码");  

$("#signupForm").validate({
	   submitHandler : function() {
		   addTeacher();
	    }, 
	    rules: {
	        teachername: {
	            required:true,
	        },
	        phone : {  
	            required : true,  
	            minlength : 11, 
	            isMobile : true  
	        },
			gh : {
				required : true
			},
			kh : {
				required : true
			}
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

function addTeacher() {
	$('label.error1').remove();
	var teachername = $("#teachername").val();
	var sex = $("input[name='sex']:checked").val();
	var phone = $("#phone").val();
	var department = $("#department").val();
	var position = $("#position").val();
	var alias = $("#alias").val();
	var gh = $("#gh").val();
	var kh = $("#kh").val();
	var data = {"name":teachername, "sex":sex, "phone":phone,
			"department":department, "position":position, "alias":alias, "gh":gh, "kh":kh};
	
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
        		document.cookie="close_tab=close";
        		$.closeWindow();
        	} else if("1"==status) {
        		alert(data.errorInfo);
        	} else if("2"==status){
        		if(data.errorFiled=="phone") {
        			$('input[name="phone"]').after(label1 + data.errorInfo + label2);
        			//alert(data.errorInfo);
        		} else if(data.errorFiled=="name") {
        			$('input[name="teachername"]').after(label1 + data.errorInfo + label2);
        			//alert(data.errorInfo);
        		} else if(data.errorFiled=="alias") {
        			$('input[name="alias"]').after(label1 + data.errorInfo + label2);
        			//alert(data.errorInfo);
        		}
        	}
        }
    });
}
</script>
</html>