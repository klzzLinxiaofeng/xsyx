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
									正文内容：
								</label>
								<div class="controls">
								<textarea id="content" name="content" class="span1">${schedule.content}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									开始时间：
								</label>
								<div class="controls">
								<input type="text" id="planStartTime" name="planStartTime" class="span1" placeholder="" value="${schedule.planStartTime}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									结束时间：
								</label>
								<div class="controls">
								<input type="text" id="planFinishTime" name="planFinishTime" class="span1" placeholder="" value="${schedule.planFinishTime}">
								</div>
							</div>
						<div style="text-align: center">
									<button class="btn btn-success" onclick="submitData()">提交</button>
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