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

	<form class="form-horizontal" id="user_profile_form" style="padding:0;margin-bottom:0;">
		<div class="control-group">
			<label class="control-label">用户名</label>
			<div class="controls"> 
				<input type="text" placeholder="用户名" value="${profile.userName != null ? profile.userName : sessionScope[sca:currentUserKey()].userName}" id="userName" name="userName" disabled="disabled">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">昵称</label>
			<div class="controls">
				<input type="text" placeholder="昵称" value="${profile.nickname}" id="nickname" name="nickname">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">真实姓名</label>
			<div class="controls">
				<input type="text" placeholder="真实姓名" value="${profile.name}" id="name" name="name">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别</label>
			<div class="controls" style="height: 30px; line-height: 30px;">
<!-- 				<input type="radio" name="sex" value="1">男 -->
<!-- 				<input type="radio" name="sex" value="0">女 -->
				<select id="sex" name="sex">
				
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">籍贯</label>
			<div class="controls">
				<select id="province" name="province" style="width: 110px;">
				
				</select>
				<select id="city" name="city" style="width: 110px;">
				
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">现居地址</label>
			<div class="controls">
				<input type="text" placeholder="现居地址" value="${profile.address}" id="address" name="address" style="width: 400px;">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">个人简介</label>
			<div class="controls">
				<textarea rows="3" id="remark" name="remark" cols="" style="width: 400px;">${profile.remark}</textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机</label>
			<div class="controls">
				<input type="text" placeholder="手机" value="${profile.mobile}" id="mobile" name="mobile">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱</label>
			<div class="controls">
				<input type="text" placeholder="邮箱" value="${profile.email}" id="email" name="email">
			</div>
		</div>
		<div class="control-group" style="margin-bottom:0">
			<label class="control-label"></label>
			<div class="controls">
				<input type="hidden" id="id" name="id" value="${profile.id}">
				<input type="hidden" id="userId" name="userId" value="${profile.userId != null ? profile.userId : sessionScope[sca:currentUserKey()].id}">
				<a href="javascript:void(0)" class="save_btn" onclick="saveOrUpdate();">保存</a>
			</div>
		</div>
	</form>

</body>
<script type="text/javascript">
	var checker;
	$(function() {
		$.initRegionSelector({
			lv : "2",
			isUseChosen : false,
			sjSelector : "province",
			shijSelector : "city",
// 			qxjSelector : "qx",
			
			isEchoSelected : true,
			sjSelectedVal : "${profile.province}",
			shijSelectedVal : "${profile.city}",
// 			qxjSelectedVal : "",
		});
		checker = initValidator();
		
		$.jcGcSelector("#sex", {"tc" : "GB-XB"}, "${profile.sex}", function(){});
	});
	
	function initValidator() {
		return $("#user_profile_form").validate({
			errorClass : "myerror",
			rules : {
				"nickname" : {
					maxlength : 10
				},
				"name" : {
					maxlength : 10
				},
				"address" : {
					maxlength : 20
				},
				"remark" : {
					maxlength : 150
				},
				"mobile" : {
					isTel : true
				},
				"email" : {
					email : true
				}
			},
			messages : {
				
			}
		});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#user_profile_form");
			var url = "${pageContext.request.contextPath}/user/center/profile/editor";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						$.success("操作成功")
					} else if("noDelete" === data.info){
						$.error('没有修改权限');
					}else if ("dataRepeat" === data.info){
						$.error('重复老师名，请重新输入');
					}else{
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