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
					<form class="form-horizontal tan_form" id="jctextbookcatalog_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									主键：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13"
									placeholder="" value="${jctextbookcatalog.id}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									对应教材：
								</label>
								<div class="controls">
								<input type="text" id="testBookId" name="testBookId" class="span13"
									placeholder="" value="${jctextbookcatalog.testBookId}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13"
									placeholder="" value="${jctextbookcatalog.name}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									树节点的层次深度：
								</label>
								<div class="controls">
								<input type="text" id="level" name="level" class="span13"
									placeholder="" value="${jctextbookcatalog.level}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									XY-JY-JCMLJDLX 02==编 03==章 04==节 05 ==条 06==款 07 == 目：
								</label>
								<div class="controls">
								<input type="text" id="catalogtype" name="catalogtype" class="span13"
									placeholder="" value="${jctextbookcatalog.catalogtype}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									页码：
								</label>
								<div class="controls">
								<input type="text" id="page" name="page" class="span13"
									placeholder="" value="${jctextbookcatalog.page}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									自关联父节点 根为0：
								</label>
								<div class="controls">
								<input type="text" id="parentId" name="parentId" class="span13"
									placeholder="" value="${jctextbookcatalog.parentId}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									是否删除，0==不删除，1===删除：
								</label>
								<div class="controls">
								<input type="text" id="isDelete" name="isDelete" class="span13"
									placeholder="" value="${jctextbookcatalog.isDelete}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createTime" name="createTime" class="span13"
									placeholder="" value="${jctextbookcatalog.createTime}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyTime" name="modifyTime" class="span13"
									placeholder="" value="${jctextbookcatalog.modifyTime}">
								</div>
								
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${jctextbookcatalog.id}" />
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
		return $("#jctextbookcatalog_form").validate({
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
			var $requestData = formData2JSONObj("#jctextbookcatalog_form");
			var url = "${ctp}/generalTeachingAffair/jctextbookcatalog/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/generalTeachingAffair/jctextbookcatalog/" + $id;
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