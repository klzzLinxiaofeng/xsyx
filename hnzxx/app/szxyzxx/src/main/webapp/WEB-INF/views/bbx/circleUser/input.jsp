<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="circleuser_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${circleuser.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									对应的群 circle.id：
								</label>
								<div class="controls">
								<input type="text" id="circleId" name="circleId" class="span13" placeholder="" value="${circleuser.circleId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									用户ID user.id：
								</label>
								<div class="controls">
								<input type="text" id="userId" name="userId" class="span13" placeholder="" value="${circleuser.userId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									用户在群的角色 circle_role.code：
								</label>
								<div class="controls">
								<input type="text" id="roleCode" name="roleCode" class="span13" placeholder="" value="${circleuser.roleCode}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									加入时间：
								</label>
								<div class="controls">
								<input type="text" id="joinDate" name="joinDate" class="span13" placeholder="" value="${circleuser.joinDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									离开时间：
								</label>
								<div class="controls">
								<input type="text" id="leaveDate" name="leaveDate" class="span13" placeholder="" value="${circleuser.leaveDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									status：
								</label>
								<div class="controls">
								<input type="text" id="status" name="status" class="span13" placeholder="" value="${circleuser.status}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									0  =否，1=是：
								</label>
								<div class="controls">
								<input type="text" id="isAdmin" name="isAdmin" class="span13" placeholder="" value="${circleuser.isAdmin}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${circleuser.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#circleuser_form").validate({
			errorClass : "myerror",
			rules : {
				
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
			var $requestData = formData2JSONObj("#circleuser_form");
			var url = "${ctp}/bbx/circleuser/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/circleuser/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	
</script>
</html>