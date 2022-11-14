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
					<form class="form-horizontal tan_form" id="applycard_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									主键：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${applycard.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									申请标题：
								</label>
								<div class="controls">
								<input type="text" id="title" name="title" class="span13" placeholder="" value="${applycard.title}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									所申请车辆：
								</label>
								<div class="controls">
								<input type="text" id="cardId" name="cardId" class="span13" placeholder="" value="${applycard.cardId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									申请人：
								</label>
								<div class="controls">
								<input type="text" id="proposer" name="proposer" class="span13" placeholder="" value="${applycard.proposer}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									联系电话：
								</label>
								<div class="controls">
								<input type="text" id="phone" name="phone" class="span13" placeholder="" value="${applycard.phone}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									使用开始时间：
								</label>
								<div class="controls">
								<input type="text" id="beginDate" name="beginDate" class="span13" placeholder="" value="${applycard.beginDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									使用结束时间：
								</label>
								<div class="controls">
								<input type="text" id="endDate" name="endDate" class="span13" placeholder="" value="${applycard.endDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									发布时间：
								</label>
								<div class="controls">
								<input type="text" id="releaseDate" name="releaseDate" class="span13" placeholder="" value="${applycard.releaseDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									审批人：
								</label>
								<div class="controls">
								<input type="text" id="auditUser" name="auditUser" class="span13" placeholder="" value="${applycard.auditUser}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									审批状态：
								</label>
								<div class="controls">
								<input type="text" id="auditStatus" name="auditStatus" class="span13" placeholder="" value="${applycard.auditStatus}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<input type="text" id="remark" name="remark" class="span13" placeholder="" value="${applycard.remark}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${applycard.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${applycard.modifyDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否删除：
								</label>
								<div class="controls">
								<input type="text" id="isDelete" name="isDelete" class="span13" placeholder="" value="${applycard.isDelete}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${applycard.id}" />
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
		return $("#applycard_form").validate({
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
			var $requestData = formData2JSONObj("#applycard_form");
			var url = "${ctp}/oa/applycard/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/oa/applycard/" + $id;
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