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
					<form class="form-horizontal tan_form" id="bwpicture_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${bwpicture.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									班级id：
								</label>
								<div class="controls">
								<input type="text" id="teamId" name="teamId" class="span13" placeholder="" value="${bwpicture.teamId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									专辑id：
								</label>
								<div class="controls">
								<input type="text" id="albumId" name="albumId" class="span13" placeholder="" value="${bwpicture.albumId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									文件uuid：
								</label>
								<div class="controls">
								<input type="text" id="fileUuid" name="fileUuid" class="span13" placeholder="" value="${bwpicture.fileUuid}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									图片名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${bwpicture.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									图片描述：
								</label>
								<div class="controls">
								<input type="text" id="description" name="description" class="span13" placeholder="" value="${bwpicture.description}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									权重,默认0：
								</label>
								<div class="controls">
								<input type="text" id="weight" name="weight" class="span13" placeholder="" value="${bwpicture.weight}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									用途,默认0：
								</label>
								<div class="controls">
								<input type="text" id="use" name="use" class="span13" placeholder="" value="${bwpicture.use}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									发送者用户id：
								</label>
								<div class="controls">
								<input type="text" id="postUserId" name="postUserId" class="span13" placeholder="" value="${bwpicture.postUserId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录创建时间(发表时间）：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${bwpicture.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${bwpicture.modifyDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录是否删除：
								</label>
								<div class="controls">
								<input type="text" id="isDeleted" name="isDeleted" class="span13" placeholder="" value="${bwpicture.isDeleted}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${bwpicture.id}" />
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
		return $("#bwpicture_form").validate({
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
			var $requestData = formData2JSONObj("#bwpicture_form");
			var url = "${ctp}/bbx/bpBwPicture/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/bpBwPicture/" + $id;
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