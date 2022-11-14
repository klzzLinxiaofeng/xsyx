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
					<form class="form-horizontal tan_form" id="praiseuser_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${praiseuser.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									所属的消息主体：
								</label>
								<div class="controls">
								<input type="text" id="praiseId" name="praiseId" class="span13" placeholder="" value="${praiseuser.praiseId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									所在学校：
								</label>
								<div class="controls">
								<input type="text" id="schoolId" name="schoolId" class="span13" placeholder="" value="${praiseuser.schoolId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									接收者所在的班级：
								</label>
								<div class="controls">
								<input type="text" id="teamId" name="teamId" class="span13" placeholder="" value="${praiseuser.teamId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									学生userId：
								</label>
								<div class="controls">
								<input type="text" id="userId" name="userId" class="span13" placeholder="" value="${praiseuser.userId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${praiseuser.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否删除的标志：
								</label>
								<div class="controls">
								<input type="text" id="isDeleted" name="isDeleted" class="span13" placeholder="" value="${praiseuser.isDeleted}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${praiseuser.id}" />
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
		return $("#praiseuser_form").validate({
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
			var $requestData = formData2JSONObj("#praiseuser_form");
			var url = "${ctp}/bbx/praiseuser/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/praiseuser/" + $id;
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