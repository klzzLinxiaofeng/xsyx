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
					<form class="form-horizontal tan_form" id="schedule_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									主键：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${schedule.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									uuid：
								</label>
								<div class="controls">
								<input type="text" id="uuid" name="uuid" class="span13" placeholder="" value="${schedule.uuid}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									app.id：
								</label>
								<div class="controls">
								<input type="text" id="appId" name="appId" class="span13" placeholder="" value="${schedule.appId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									所属的单位，学校：
								</label>
								<div class="controls">
								<input type="text" id="ownerId" name="ownerId" class="span13" placeholder="" value="${schedule.ownerId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									组的类型，1：学校：
								</label>
								<div class="controls">
								<input type="text" id="ownerType" name="ownerType" class="span13" placeholder="" value="${schedule.ownerType}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									发布者ID：
								</label>
								<div class="controls">
								<input type="text" id="posterId" name="posterId" class="span13" placeholder="" value="${schedule.posterId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									发布者姓名：
								</label>
								<div class="controls">
								<input type="text" id="posterName" name="posterName" class="span13" placeholder="" value="${schedule.posterName}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									日程安排开始时间：
								</label>
								<div class="controls">
								<input type="text" id="planStartTime" name="planStartTime" class="span13" placeholder="" value="${schedule.planStartTime}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									日程安排结束时间：
								</label>
								<div class="controls">
								<input type="text" id="planFinishTime" name="planFinishTime" class="span13" placeholder="" value="${schedule.planFinishTime}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									共享对象范围：
								</label>
								<div class="controls">
								<input type="text" id="shareTo" name="shareTo" class="span13" placeholder="" value="${schedule.shareTo}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									优先等级,0=普通，1=重要：
								</label>
								<div class="controls">
								<input type="text" id="rank" name="rank" class="span13" placeholder="" value="${schedule.rank}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									正文内容：
								</label>
								<div class="controls">
								<input type="text" id="content" name="content" class="span13" placeholder="" value="${schedule.content}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${schedule.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${schedule.modifyDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否删除，0：还没删除，1：已经删除：
								</label>
								<div class="controls">
								<input type="text" id="isDeleted" name="isDeleted" class="span13" placeholder="" value="${schedule.isDeleted}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${schedule.id}" />
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
		return $("#schedule_form").validate({
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
			var $requestData = formData2JSONObj("#schedule_form");
			var url = "${ctp}/oa/schedule/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/oa/schedule/" + $id;
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