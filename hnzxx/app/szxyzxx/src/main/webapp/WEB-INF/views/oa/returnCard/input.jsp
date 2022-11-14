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
					<input type="hidden" id="isCK" value=""/>
					<form class="form-horizontal tan_form" id="returncard_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									主键：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${returncard.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									借出车ID：
								</label>
								<div class="controls">
								<input type="text" id="usecardId" name="usecardId" class="span13" placeholder="" value="${returncard.usecardId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									还车日期：
								</label>
								<div class="controls">
								<input type="text" id="returnDate" name="returnDate" class="span13" placeholder="" value="${returncard.returnDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									还车说明：
								</label>
								<div class="controls">
								<input type="text" id="returnExplain" name="returnExplain" class="span13" placeholder="" value="${returncard.returnExplain}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									还车状态：
								</label>
								<div class="controls">
								<input type="text" id="returnStatus" name="returnStatus" class="span13" placeholder="" value="${returncard.returnStatus}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${returncard.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${returncard.modifyDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否删除：
								</label>
								<div class="controls">
								<input type="text" id="isDelete" name="isDelete" class="span13" placeholder="" value="${returncard.isDelete}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${returncard.id}" />
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
	var isCK = $("#isCK").val();
	if(isCK!=""){
		$(".controls").disable();
		$(".form-actions").hide();
	}
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#returncard_form").validate({
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
			var $requestData = formData2JSONObj("#returncard_form");
			var url = "${ctp}/oa/returncard/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/oa/returncard/" + $id;
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