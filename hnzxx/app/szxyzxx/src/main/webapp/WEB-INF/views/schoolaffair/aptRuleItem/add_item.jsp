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
					<form class="form-horizontal tan_form" id="aptrule_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>
									名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${aptrule.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>适用范围 ：
								</label>
								<div class="controls">
<%-- 								<input type="text" id="scoreType" name="scoreType" class="span13" placeholder="" value="${aptrule.scoreType}"> --%>
								<select name="scoreType" id="scoreType" class="span13" placeholder="请选择">
									<option value="">请选择</option>
									<option value="1">全校</option>
									<option value="2">部门</option>
									<option value="9">自定义</option>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
<%-- 								<input type="text" id="description" name="description" class="span13" placeholder="" value="${aptrule.description}"> --%>
									<textarea rows="5" cols="5" id="description" name="description" class="span13" data-placeholder="备注">${aptrule.description}</textarea>
								</div>
							</div>
						<div class="form-actions tan_bottom">
<%-- 							<input type="hidden" id="id" name="id" value="${aptrule.id}" /> --%>
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
		$("#scoreType").chosen();
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#aptrule_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
					accCheck : false,
					maxlength : 30,
					remote : {
						async : false,
						type : "GET",
						url : "${pageContext.request.contextPath}/schoolAffair/aptRuleItem/checker",
						data : {
							'name' : function() {
								return encodeURI($("#name").val());
							}
						}
					}
				},
				"scoreType" : {
					selectNone : true
				},
				"description" : {
					maxlength : 500
				}
			},
			messages : {
				"name" : {
					remote : "名称已存在"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#aptrule_form");
			var url = "${ctp}/schoolAffair/aptRuleItem/ruleCreator";
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