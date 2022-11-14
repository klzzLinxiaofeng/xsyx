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
					<form class="form-horizontal tan_form" id="jctextbookpublisher_form" action="javascript:void(0);">
							
							<div class="control-group">
								<label class="control-label">
									名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13"
									placeholder="" value="${jctextbookpublisher.name}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									简称：
								</label>
								<div class="controls">
								<input type="text" id="shortName" name="shortName" class="span13"
									placeholder="" value="${jctextbookpublisher.shortName}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									排序：
								</label>
								<div class="controls">
								<input type="text" id="sortOrder" name="sortOrder" class="span13"
									placeholder="" value="${jctextbookpublisher.sortOrder}">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									说明：
								</label>
								<div class="controls">
								<input type="text" id="description" name="description" class="span13"
									placeholder="" value="${jctextbookpublisher.description}">
								</div>
								
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${jctextbookpublisher.id}" />
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
		return $("#jctextbookpublisher_form").validate({
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
			var $requestData = formData2JSONObj("#jctextbookpublisher_form");
			var url = "${ctp}/teach/textBookMaster/textBookPublisher/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/textBookMaster/textBookPublisher/" + $id;
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