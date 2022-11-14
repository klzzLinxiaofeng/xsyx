<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<html>
<head>
<title>部门创建</title>
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
.row-fluid .span7, .row-fluid .span4 {
	width: 227px;
}

input[class*="span"], select[class*="span"], textarea[class*="span"],
	.uneditable-input[class*="span"], .row-fluid input[class*="span"],
	.row-fluid select[class*="span"], .row-fluid textarea[class*="span"],
	.row-fluid .uneditable-input[class*="span"] {
	width: 227px;
}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets"  style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="dept_form">
						<div class="control-group">
							<label class="control-label">
								<font style="color: red">*</font>
								部门名称
							</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span7"
									placeholder="请输入部门名称, 不能为空" value="${dept.name}">
							</div>
						</div>
<!-- 						<div class="control-group"> -->
<!-- 							<label class="control-label"> -->
<!-- 								<font style="color: red">*</font> -->
<!-- 								排序号 -->
<!-- 							</label> -->
<!-- 							<div class="controls"> -->
<!-- 								<input type="text" id="listOrder" name="listOrder" class="span7 left_red {required:true,isInteger:true,maxlength:11}" -->
<%-- 									placeholder="请输入排序号, 必须为数字且不为空" value="${dept.listOrder}"> --%>
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="control-group">
							<label class="control-label">父机构</label>
							<div class="controls">
								<input type="text" class="span13" disabled="disabled" placeholder="" value="${parent != null ? parent.name : dept.parent.name}">
							</div>
						</div>
						<div class="control-group" style="display:none;">
							<label class="control-label">等级</label>
							<div class="controls">
								<input type="text" class="span13" disabled="disabled" id="level" placeholder="" value="${parent != null ? parent.level : 1}">
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${dept.id}" /> 
							<input type="hidden" id="parentId" name="parentId" value="${parent != null ? parent.id : dept.parent.id}">
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
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
		return $("#dept_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
					maxlength : 15
				}
			},
			messages : {
				"name" : {
					required:"学校名称不能为空",
					maxlength:"名称不能超过15个汉字"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		var caller = "${param.caller}";
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = {};
			$requestData.name = $("#name").val();
			$requestData.level = $("#level").val();
// 			$requestData.listOrder= $("#listOrder").val();
			$requestData.parentId = $("#parentId").val();
			var isUpdate = false;
			var url = "${pageContext.request.contextPath}/teach/dept/creator";
			
			if ("" != $id && $id != null) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/teach/dept/" + $id;
				isUpdate = true;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
							if("tree" === caller) {
								var seletedNode = parent.core_iframe.window.zTree.getSelectedNodes()[0];
								if(isUpdate) {
									$.success("修改成功");
									seletedNode.name = $("#name").val();
									parent.core_iframe.window.zTree.updateNode(seletedNode, false);
								} else {
									$.success("创建成功");
									seletedNode.isParent = true;
									parent.core_iframe.window.zTree.updateNode(seletedNode, false);
									parent.core_iframe.window.zTree.reAsyncChildNodes(seletedNode, "refresh", true);
								}
							} else {
								parent.core_iframe.window.location.reload();
							}
						} else {
							if("tree" === caller) {
								var seletedNode = parent.window.zTree.getSelectedNodes()[0];
								if(isUpdate) {
									$.success("修改成功");
									seletedNode.name = $("#name").val();
									parent.window.zTree.updateNode(seletedNode, false);
								} else {
									$.success("创建成功");
									seletedNode.isParent = true;
									parent.window.zTree.updateNode(selectedNode, false);
									parent.window.zTree.reAsyncChildNodes(seletedNode, "refresh", true);
								}
							} else {
								parent.window.location.reload();
							}
						}
						$.closeWindow();
					}
				}
				loader.close();
			});
		}
	}
</script>
</html>