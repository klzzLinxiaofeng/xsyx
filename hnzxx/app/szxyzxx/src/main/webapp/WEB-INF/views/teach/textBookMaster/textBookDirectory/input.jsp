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
					<form class="form-horizontal tan_form" id="jctextbookdirectory_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									主键：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13"
									placeholder="" value="${jctextbookdirectory.id}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									自关联父节点 根为0：
								</label>
								<div class="controls">
								<input type="text" id="parentId" name="parentId" class="span13"
									placeholder="" value="${jctextbookdirectory.parentId}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13"
									placeholder="" value="${jctextbookdirectory.name}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									树节点层次深度：
								</label>
								<div class="controls">
								<input type="text" id="level" name="level" class="span13"
									placeholder="" value="${jctextbookdirectory.level}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									树节点类型（教材分类目录节点类型） XY-JY-JCFLJDLX  00==主分类 01==出版社 02==专业 03==学科 04==学段 05==年级 06==册次 99==自定义：
								</label>
								<div class="controls">
								<input type="text" id="nodeType" name="nodeType" class="span13"
									placeholder="" value="${jctextbookdirectory.nodeType}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									名称对应的实体表名：
								</label>
								<div class="controls">
								<input type="text" id="ownerTable" name="ownerTable" class="span13"
									placeholder="" value="${jctextbookdirectory.ownerTable}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									名称对应的实体编码或者id：
								</label>
								<div class="controls">
								<input type="text" id="ownerId" name="ownerId" class="span13"
									placeholder="" value="${jctextbookdirectory.ownerId}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									是否删除，0==不删除，1===删除：
								</label>
								<div class="controls">
								<input type="text" id="isDelete" name="isDelete" class="span13"
									placeholder="" value="${jctextbookdirectory.isDelete}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createTime" name="createTime" class="span13"
									placeholder="" value="${jctextbookdirectory.createTime}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyTime" name="modifyTime" class="span13"
									placeholder="" value="${jctextbookdirectory.modifyTime}">
								</div>
								
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${jctextbookdirectory.id}" />
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
		return $("#jctextbookdirectory_form").validate({
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
			var $requestData = formData2JSONObj("#jctextbookdirectory_form");
			var url = "${ctp}/generalTeachingAffair/jctextbookdirectory/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/generalTeachingAffair/jctextbookdirectory/" + $id;
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