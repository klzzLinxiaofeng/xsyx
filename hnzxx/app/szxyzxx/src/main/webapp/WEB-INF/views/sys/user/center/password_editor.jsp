<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>个人信息</title>
<style>
.form-horizontal .control-label {
	width: 80px;
}

.form-horizontal .controls {
	margin-left: 100px;
}

.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}

input[type="radio"] {
	position: relative;
	top: -1px;
	margin: 0 8px;
}

body {
	background-color: #fff;
}
</style>
</head>
<body>

	<form class="form-horizontal" id="user_pwd_form" style="padding:0;margin-bottom:0;">
		<div class="control-group">
			<label class="control-label">旧密码</label>
			<div class="controls"> 
				<input type="password" placeholder="旧密码" value="" id="oldPwd" name="oldPwd">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">新密码</label>
			<div class="controls">
				<input type="password" placeholder="新密码" value="" id="newPwd" name="newPwd">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">密码确认</label>
			<div class="controls">
				<input type="password" placeholder="密码确认" value="" id="confirmPwd" name="confirmPwd">
			</div>
		</div>
		<div class="control-group" style="margin-bottom:0">
			<label class="control-label"></label>
			<div class="controls">
				<a href="javascript:void(0)" class="save_btn" onclick="saveOrUpdate();">保存</a>
			</div>
		</div>
	</form>

</body>
<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#user_pwd_form").validate({
			errorClass : "myerror",
			rules : {
				"oldPwd" : {
					required : true,
					minlength : 6,
					maxlength : 20,
					accCheck : true
				},
				"newPwd" : {
					required : true,
					minlength : 6,
					maxlength : 20,
					accCheck : true
				}, 
				"confirmPwd" : {
					required : true,
					minlength : 6,
					maxlength : 20,
					accCheck : true,
					equalTo : "#newPwd"
				}
			},
			messages : {
				"confirmPwd" : {
					equalTo : "两次密码输入必须一致"
				}
			}
		});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = {};
			$requestData.newPwd = $("#newPwd").val();
			$requestData.oldPwd = $("#oldPwd").val();
			var url = "${pageContext.request.contextPath}/user/center/password/editor";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('操作成功');
					if ("success" === data) {
						$.success("操作成功");
					} else if ("pmf" === data) {
						$.error("旧密码错误");
					} else {
						$.error('操作失败');
					}
				} else {
					$.error('操作失败');
				}
				loader.close();
			});
		}
	}
	
</script>
</html>