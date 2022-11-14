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
<form id="signupForm" class="form-horizontal">
	<div class="select_div">
		<span>班内学号：</span>
		<input type="text" id="num" name="num" value="${result.num }">
	</div>
	<div class="select_div">
		<span><i style="font-weight: normal;color:red">*</i>姓名：</span>
		<input type="text" id="name" name="name" value="${result.name }">
	</div>
	<div class="select_div">
		<span><i style="font-weight: normal;color:red">*</i>年级：</span>
		<input type="text" id="grade" name="grade" value="${result.grade }">
	</div>
	<div class="select_div">
		<span><i style="font-weight: normal;color:red">*</i>班级：</span>
		<input type="text" id="team" name="team" value="${result.team }">
	</div>
	<div class="select_div">
		<span>监护人：</span>
		<input type="text" id="guardian" name="guardian" value="${result.guardian }">
	</div>
	<div class="select_div">
		<span>监护人手机：</span>
		<input type="text" id="guardianPhone" name="guardianPhone" value="${result.guardianPhone }">
	</div>
	<div style="text-align:center;">
		<input class="btn btn-blue" type="submit" value="保存"></input>
	</div>
</form>
</div>
</body>
<script>
$("input[name='guardianPhone']").focus(function(){
$('label.error1').remove();
});
jQuery.validator.addMethod("isMobile", function(value, element) {  
    var length = value.length;  
    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;  
    return this.optional(element) || (length == 11 && mobile.test(value));  
}, "请正确填写手机号码");

$("#signupForm").validate({
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
	$('label.error1').remove();
	var id = "${result.id}";
	var num = $("#num").val();
	var grade = $("#grade").val();
	var team = $("#team").val();
	var guardian = $("#guardian").val();
	var guardianPhone = $("#guardianPhone").val();
	var name = $("#name").val();
	
	var data = {"id":id, "num":num, "grade":grade, "team":team,
			"guardian":guardian, "guardianPhone":guardianPhone, "name":name};
	
	$.ajax({
        url: "${pageContext.request.contextPath}/student/init/add",
        type: "POST",
        data: {"data": JSON.stringify(data)},
        async: true,
        success: function(result) {
        	var data = JSON.parse(result);
        	var status = data.status;
        	if("0"==status) {
        		updateStatus(0, data.studentId, data.num);
        	} else if("1"==status) {
        		alert(data.errorInfo);
        	} else if("2"==status){
        		showError(data);
        	}
        }
    });
}

function updateStatus(status, studentId, num){
	var id = "${result.id}";
	var grade = $("#grade").val();
	var team = $("#team").val();
	var guardian = $("#guardian").val();
	var guardianPhone = $("#guardianPhone").val();
	var name = $("#name").val();
	
	$.ajax({
        url: "${pageContext.request.contextPath}/tmp/student/update",
        type: "POST",
        data: {"id":id, "num":num, "grade":grade, "team":team, "status":status,
			"guardian":guardian, "guardianPhone":guardianPhone, "name":name, "studentId":studentId},
        async: true,
        success: function(result) {
        	document.cookie="close_tab=close";
        	$.closeWindow();
        }
    });
}

function showError(data) {
	var label1 = " <label class='error1'>";
	var label2 = "</label>"; 
	if(data.errorFiled=="num") {
		$('input[name="num"]').after(label1 + data.errorInfo + label2);
		//alert(data.errorInfo);
	} else if(data.errorFiled=="grade") {
		$('input[name="grade"]').after(label1 + data.errorInfo + label2);
		//alert(data.errorInfo);
	} else if(data.errorFiled=="team") {
		$('input[name="team"]').after(label1 + data.errorInfo + label2);
		//alert(data.errorInfo);
	} else if(data.errorFiled=="guardian") {
		$('input[name="guardian"]').after(label1 + data.errorInfo + label2);
		//alert(data.errorInfo);
	}else if(data.errorFiled=="guardianPhone") {
		$('input[name="guardianPhone"]').after(label1 + data.errorInfo + label2);
		//alert(data.errorInfo);
	} else if(data.errorFiled=="name") {
		$('input[name="name"]').after(label1 + data.errorInfo + label2);
		//alert(data.errorInfo);
	}
}
</script>
</html>