<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改菜单地址</title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/plugin/falgun/css/add.css" rel="stylesheet">
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
					<form class="form-horizontal tan_form" id="custom_form" action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label">菜单编号</label>
							<div class="controls">
								<input type="text" id="code" name="code" value="${pjPermission.code }" readonly="readonly">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">菜单名称</label>
							<div class="controls">
								<input type="text" id="name" name="name" value="${pjPermission.name }" readonly="readonly">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">菜单路径</label>
							<div class="controls">
								<input type="text" id="accessUrl" name="accessUrl" value="${pjPermission.accessUrl }">
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${pjPermission.id}"/>
								<button class="btn btn-warning" type="button"
									onclick="savePage();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//保存
	function savePage() {
		var $id = $("#id").val();
		var $requestData = formData2JSONObj("#custom_form");
		if ("" != $id) {
			$requestData._method = "put";
			url = "${pageContext.request.contextPath}/sys/custom/" + $id;
		}
		var loader = new loadLayer();
		loader.show();
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					if(parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							window.location.reload();
						}
					$.closeWindow();
				}else {
					$.error('操作失败');
				}
			}else{
				$.error("操作失败");
			}
			loader.close();
		});
	}
</script>
</html>