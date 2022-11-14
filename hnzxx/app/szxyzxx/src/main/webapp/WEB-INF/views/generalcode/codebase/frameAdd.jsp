<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>代码规范</title>
<style>
@media (max-width: 979px) and (min-width: 768px)
{
.row-fluid .span4{width:450px;}
}
.form-horizontal .controls label.myerror {
    color: red;
    margin: 0 10px;
    display:inline-block;
}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="user_form">
						<div class="control-group" style="display: none;">
							<label class="control-label">id：</label>
							<div class="controls">
								<input type="text" id="id" name="id" class="span4" value="${framework.id }">
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">名称：</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span4" value="${framework.name }">
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">编号：</label>
							<div class="controls">
								<input type="text" id="code" name="code" class="span4" value="${framework.code }">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">代码表命名前缀：</label>
							<div class="controls">
								<input type="text" id="tablePrefix" name="tablePrefix" class="span4" value="${framework.tablePrefix }">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">发布机构</label>
							<div class="controls">
								<input type="text" id="publisher" name="publisher" class="span4" value="${framework.publisher }">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">版本</label>
							<div class="controls">
								<input type="text" id="version" name="version" class="span4" value="${framework.version }">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">创建时间</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" onclick="WdatePicker();">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">说明</label>
							<div class="controls">
								<textarea id="description" name="description" class="span4" style="height:180px;    resize: none;" value="${framework.description }">${framework.description }</textarea>
							</div>
						</div>
						<div class="form-actions tan_bottom">
								<button class="btn btn-success" type="button" onclick="saveOrUpdate();">保存</button>
								<button class="btn" type="button" onclick="back();">返回</button>
								<button class="btn btn-danger" type="button" onclick="deleteFramework(${framework.id });">删除</button>
								<button class="btn btn-primary" type="button" onclick="showTable(${framework.id });">查看代码表</button>
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
		return $("#user_form").validate({
			errorClass : "myerror",
			rules : {
				name :{
					required:true
				}
			},
			messages : {
				name : "这是必填字段"
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#user_form");
			var url = "${ctp}/gc/codebase/createFrameWork";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/gc/codebase/editFrameWork/" + $id;
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
	
	function back() {
		$.closeWindow();
	}
	
	// 	删除对话框
	function deleteFramework(id) {
		$.confirm("确定执行此次操作？", function() {
			executeDelFramework(id);
		});
	}
	
	// 	执行删除
	function executeDelFramework(id) {
		//alert(id);
		$.post("${ctp}/gc/codebase/editFrameWork/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					//$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
		parent.window.location.reload();
		$.closeWindow();
	}
	
	//加载创建对话框
	function showTable(frameworkId) {
		var fd = 1;
		fd = frameworkId;
		$.initWinOnTopFromLeft('编辑代码表', '${ctp}/gc/codebase/showTable?frameworkId=' + fd, '850', '650');
	}
</script>
</html>