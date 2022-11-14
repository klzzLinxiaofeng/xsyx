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
					<form class="form-horizontal tan_form" id="circle_form" action="javascript:void(0);">
					<input type="hidden" id="id" name="id" value="${circle.id}">
					<input type="hidden" id="uuid" name="uuid" value="${circle.uuid}">
					<input type="hidden" id="appId" name="appId" value="${circle.appId}">
					<input type="hidden" id="ownerId" name="ownerId" value="${circle.ownerId}">
					<input type="hidden" id="objectId" name="objectId" value="${circle.objectId}">
					<input type="hidden" id="createId" name="createId" value="${circle.createId}">
					
					<input type="hidden" id="adminId" name="adminId" value="${circle.adminId}">
					<input type="hidden" id="status" name="status" value="${circle.status}">
					<input type="hidden" id="memberCount" disabled="disabled" name="memberCount" value="${circle.memberCount}">
							<div class="control-group">
								<label class="control-label">
									群组名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${circle.name}">
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">
									群类型：
								</label>
								<div class="controls">
								<select id="objectType" name="objectType" class="">
								<option value="1">班级群</option>
								<option value="2">部门群</option>
								</select>
								<input type="text" id="objectType" name="objectType" class="span13" placeholder="" value="${circle.objectType}">
								</div>
							</div> --%>
<%-- 							<div class="control-group">
								<label class="control-label">
									创建者userId：
								</label>
								<div class="controls">
								<input type="text" id="createId" name="createId" class="span13" placeholder="" value="${circle.createId}">
								</div>
							</div>
 --%>							<%-- <div class="control-group">
								<label class="control-label">
									adminId：
								</label>
								<div class="controls">
								<input type="text" id="adminId" name="adminId" class="span13" placeholder="" value="${circle.adminId}">
								</div>
							</div> --%>
							<%-- <div class="control-group">
								<label class="control-label">
									status：
								</label>
								<div class="controls">
								<input type="text" id="status" name="status" class="span13" placeholder="" value="${circle.status}">
								</div>
							</div> --%>
							<%-- <div class="control-group">
								<label class="control-label">
									memberCount：
								</label>
								<div class="controls">
								<input type="text" id="memberCount" name="memberCount" class="span13" placeholder="" value="${circle.memberCount}">
								</div>
							</div> --%>
						<div class="form-actions tan_bottom">
								<button id="confirmBtn" class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
								<button id="cancelBtn" class="btn btn-warning" type="button"
									onclick="$.closeWindow();">取消</button>
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
		// 部门群和班级群不可修改信息
		if('${circle.id}'!='' && '${circle.objectType}'!=3)
		{
			$('form').find('input').prop('disabled', true);
			$('#confirmBtn').hide();
		}
	});
	
	function initValidator() {
		return $("#circle_form").validate({
			errorClass : "myerror",
			rules : {
				name : {required:true/* , max:'20', min:'1' */}
				
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
			var $requestData = formData2JSONObj("#circle_form");
			var url = "${ctp}/bbx/circle/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/circle/" + $id;
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