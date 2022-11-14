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
					<form class="form-horizontal tan_form" id="bwinfo_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${bwinfo.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									班级id：
								</label>
								<div class="controls">
								<input type="text" id="teamId" name="teamId" class="span13" placeholder="" value="${bwinfo.teamId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									欢迎词：
								</label>
								<div class="controls">
								<input type="text" id="welcomeText" name="welcomeText" class="span13" placeholder="" value="${bwinfo.welcomeText}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									班级宣言：
								</label>
								<div class="controls">
								<input type="text" id="signature" name="signature" class="span13" placeholder="" value="${bwinfo.signature}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									背景文件：
								</label>
								<div class="controls">
								<input type="text" id="backgroundFile" name="backgroundFile" class="span13" placeholder="" value="${bwinfo.backgroundFile}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${bwinfo.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${bwinfo.modifyDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否删除：
								</label>
								<div class="controls">
								<input type="text" id="isDeleted" name="isDeleted" class="span13" placeholder="" value="${bwinfo.isDeleted}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${bwinfo.id}" />
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
		return $("#bwinfo_form").validate({
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
			var $requestData = formData2JSONObj("#bwinfo_form");
			var url = "${ctp}/bbx/bpBwInfo/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/bpBwInfo/" + $id;
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