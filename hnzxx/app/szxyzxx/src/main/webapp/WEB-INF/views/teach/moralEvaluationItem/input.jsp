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

.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}

.row-fluid .span4 {
	width: 227px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form"
						id="moralEvaluationItem_form" action="javascript:void(0);">

						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font> 评价项目分类： </label>
							<div class="controls">
								<input type="text" id="classification" name="classification"
									class="span4" placeholder=""
									value="${moralEvaluationItem.classification}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font> 评价项目： </label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span4"
									placeholder="" value="${moralEvaluationItem.name}"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label"> 备注： </label>
							<div class="controls">
								<textarea id="remark" name="remark" class="span4" placeholder=""
									rows="3" cols="1"
									${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${moralEvaluationItem.remark}</textarea>
							</div>
						</div>

						<div class="form-actions tan_bottom">
							<c:if test="${isCK == null || isCk == ''}">
								<input type="hidden" id="id" name="id"
									value="${moralEvaluationItem.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
							</c:if>
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
		return $("#moralEvaluationItem_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
					maxlength : 15,
					remote : {
						async : false,
						type : "POST",
						url : "${ctp}/teach/moralEvaluationItem/checker",
						data : {
							'dxlx' : 'name',
							'name' : function() {
								return $("#name").val();
							},
							'id' : function() {
								return $("#id").val();
							}
						}
					}
				},
				"classification" : {
					required : true,
					maxlength : 15
				},
				"remark" : {
					maxlength : 200
				}
			},
			messages : {
				"name" : {
					remote : "该评价项目已存在"
				}
			}
		});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#moralEvaluationItem_form");
			var url = "${ctp}/teach/moralEvaluationItem/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/moralEvaluationItem/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('保存成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					} else {

					}
				} else {
					$.error("保存失败");
				}
				loader.close();
			});
		}
	}
</script>
</html>