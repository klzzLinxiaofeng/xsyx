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
		<input type="text" id="num" value="${result.num }" readonly="readonly" name="num">
	</div>
	<div class="select_div">
		<span><i style="font-weight: normal;color:red">*</i>姓名：</span>
		<input type="text" id="name" name="name" value="${result.name }" readonly="readonly">
	</div>
	<div class="select_div">
		<span><i style="font-weight: normal;color:red">*</i>年级：</span>
		<input type="text" id="grade" name="grade" value="${result.grade }" readonly="readonly">
	</div>
	<div class="select_div">
		<span><i style="font-weight: normal;color:red">*</i>班级：</span>
		<input type="text" id="team" name="team" value="${result.team }" readonly="readonly">
	</div>
	<div class="select_div">
		<span>监护人：</span>
		<input type="text" id="guardian" value="${result.guardian }" name="guardian">
	</div>
	<div class="select_div">
		<span>监护人手机：</span>
		<input type="text" id="guardianPhone" value="${result.guardianPhone }" name="guardianPhone">
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
       	$('label.error1').remove();
    	    var id = "${result.id}";
    	    var oldMobile = "${result.guardianPhone}";
    	    var studentId = "${studentId.id}";
    		var guardian = $("#guardian").val();
    		var guardianPhone = $("#guardianPhone").val();
    		
    		$.ajax({
    	        url: "${pageContext.request.contextPath}/tmp/student/updateReal",
    	        type: "POST",
    	        data: {"tmpId":id, "name":guardian, "newMobile":guardianPhone,
    	        	"studentId":studentId, "oldMobile":oldMobile},
    	        async: true,
    	        success: function(result) {
    	        	$(window.parent.document).find("#spi_daoru_ok").click();
		        	$.closeWindow();
    	        }
    	    });
	   }
});

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