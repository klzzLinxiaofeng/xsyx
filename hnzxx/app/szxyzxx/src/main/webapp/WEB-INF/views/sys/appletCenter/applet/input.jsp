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
					<form class="form-horizontal tan_form" id="applet_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									主键id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${applet.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									小应用所在的主应用UUID：
								</label>
								<div class="controls">
								<input type="text" id="appKey" name="appKey" class="span13" placeholder="" value="${applet.appKey}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									小应用UUID：
								</label>
								<div class="controls">
								<input type="text" id="appletKey" name="appletKey" class="span13" placeholder="" value="${applet.appletKey}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${applet.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									版本：
								</label>
								<div class="controls">
								<input type="text" id="version" name="version" class="span13" placeholder="" value="${applet.version}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									图标(地址)：
								</label>
								<div class="controls">
								<input type="text" id="icon" name="icon" class="span13" placeholder="" value="${applet.icon}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									应用访问入口地址：
								</label>
								<div class="controls">
								<input type="text" id="url" name="url" class="span13" placeholder="" value="${applet.url}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									适用范围 0=所有平台环境(默认) 1=Web端 2=移动端：
								</label>
								<div class="controls">
								<input type="text" id="actType" name="actType" class="span13" placeholder="" value="${applet.actType}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									来源类型 1=官方(默认) 2=第三方：
								</label>
								<div class="controls">
								<input type="text" id="sourceType" name="sourceType" class="span13" placeholder="" value="${applet.sourceType}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									上线情况 0=下线(默认) 1=上线(全平台) 2=部分上线：
								</label>
								<div class="controls">
								<input type="text" id="lineType" name="lineType" class="span13" placeholder="" value="${applet.lineType}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									说明：
								</label>
								<div class="controls">
								<input type="text" id="description" name="description" class="span13" placeholder="" value="${applet.description}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									创建人：
								</label>
								<div class="controls">
								<input type="text" id="createUserId" name="createUserId" class="span13" placeholder="" value="${applet.createUserId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									创建日期：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${applet.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${applet.modifyDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									删除标记：
								</label>
								<div class="controls">
								<input type="text" id="isDeleted" name="isDeleted" class="span13" placeholder="" value="${applet.isDeleted}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${applet.id}" />
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
		return $("#applet_form").validate({
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
			var $requestData = formData2JSONObj("#applet_form");
			var url = "${ctp}/user/applet/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/user/applet/" + $id;
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