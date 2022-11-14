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
					<form class="form-horizontal tan_form" id="bwfile_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${bwfile.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									班级：
								</label>
								<div class="controls">
								<input type="text" id="teamId" name="teamId" class="span13" placeholder="" value="${bwfile.teamId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									标题，文件名等：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${bwfile.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									文件：
								</label>
								<div class="controls">
								<input type="text" id="fileId" name="fileId" class="span13" placeholder="" value="${bwfile.fileId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									用户ID：
								</label>
								<div class="controls">
								<input type="text" id="postUserId" name="postUserId" class="span13" placeholder="" value="${bwfile.postUserId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									使用此文件的栏目类型：1=班级相册 2=班级视频 3=班级动态：
								</label>
								<div class="controls">
								<input type="text" id="objectType" name="objectType" class="span13" placeholder="" value="${bwfile.objectType}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录创建时间(发表时间）：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${bwfile.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${bwfile.modifyDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录是否删除：
								</label>
								<div class="controls">
								<input type="text" id="isDeleted" name="isDeleted" class="span13" placeholder="" value="${bwfile.isDeleted}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${bwfile.id}" />
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
		return $("#bwfile_form").validate({
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
			var $requestData = formData2JSONObj("#bwfile_form");
			var url = "${ctp}/bbx/bpBwFile/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/bpBwFile/" + $id;
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