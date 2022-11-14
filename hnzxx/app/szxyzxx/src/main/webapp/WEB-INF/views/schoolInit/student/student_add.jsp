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
				<span><i style="font-weight: normal;color:red">*</i>班内学号：</span>
				<input type="text" id="num" name="num">
			</div>

			<div class="select_div">
				<span><i style="font-weight: normal;color:red">*</i>卡号：</span>
				<input type="text" id="kh" name="kh">
			</div>

			<div class="select_div">
				<span><i style="font-weight: normal;color:red">*</i>姓名：</span>
				<input type="text" id="name" name="name">
			</div>
			<div class="select_div">
				<span><i style="font-weight: normal;color:red">*</i>年级：</span>
				<input type="text" id="grade" name="grade">
			</div>
			<div class="select_div">
				<span><i style="font-weight: normal;color:red">*</i>班级：</span>
				<input type="text" id="team" name="team" >
			</div>
			<div class="select_div">
				<span><i style="font-weight: normal;color:red">*</i>监护人：</span>
				<input type="text" id="guardian" name="guardian">
			</div>
			<div class="select_div">
				<span><i style="font-weight: normal;color:red">*</i>监护人手机：</span>
				<input type="text" id="guardianPhone" name="guardianPhone">
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
$("#signupForm").validate({
	    submitHandler : function() {
	    	addStudent();  
	    }, 
	    rules: {
	    	name: {
	            required:true,
	            minlength: 2
	        },
	        grade : {  
	            required : true 
	        },
			num : {
				required : true
			},
			kh : {
				required : true
			},
			guardian : {
				required : true
			},
			guardianPhone : {
				required : true
			},
	        team : {  
	            required : true,
	            number:true,
	            digits:true
	        }
	   },
	   messages: {
		   name: {
               required: '请输入姓名',
               minlength: '请至少输入两个字符'
     	  },
		  grade : {  
                 required : "请输入年级",  
          },
		   guardian : {
			   required : '请输入监护人姓名'
		   },
		   guardianPhone : {
			   required :'请输入监护人手机号'
		   },
          team : {  
                required : "请输入班级",  
                number:"请输入数字",
	            digits:"必须输入整数",
          }
       }
});

function addStudent(){
	$('label.error1').remove();
	var num = $("#num").val();
	var grade = $("#grade").val();
	var team = $("#team").val();
	var guardian = $("#guardian").val();
	var guardianPhone = $("#guardianPhone").val();
	var name = $("#name").val();
	var kh = $("#kh").val();
	var data = {"num":num, "grade":grade, "team":team,
			"guardian":guardian, "guardianPhone":guardianPhone, "name":name, "kh":kh};
	
	$.ajax({
        url: "${pageContext.request.contextPath}/student/init/add",
        type: "POST",
        data: {"data": JSON.stringify(data)},
        async: true,
        success: function(result) {
        	var data = JSON.parse(result);
        	var status = data.status;
        	if(0==status) {
        		document.cookie="close_tab=close";
        		$.closeWindow();
        	} else{
        		layer.alert(data.errorInfo);
        	}
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