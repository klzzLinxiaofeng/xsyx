<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>用户创建</title>
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

.row-fluid .span4 {
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
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="user_form">
						<div class="control-group">
							<label class="control-label">会议名称：</label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span4" placeholder="请输入会议名称 不能为空" value="">
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">摘要：</label>
							<div class="controls">
								<textarea class="span4"></textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">开始时间：</label>
							<div class="controls">
								<input type="text" id="start_Date" name="start_Date" class="span4"
									placeholder="请输入开始时间"
									value=''
									onclick="WdatePicker({dateFmt : 'yyyy-MM-dd HH:mm'});">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">结束时间：</label>
							<div class="controls">
								<input type="text" id="end_Date" name="end_Date" class="span4"
									placeholder="请输入结束时间"
									value=''
									onclick="WdatePicker({dateFmt : 'yyyy-MM-dd HH:mm'});">
							</div>
						</div>
						<div class="form-actions tan_bottom">
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
		return $("#user_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
				},
			},
			messages : {
				
			}
		});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var name = $("#name").val();
			var start_date = $("#start_Date").val();
			var end_date = $("#end_Date").val();
			var $ifm = $("#core_iframe", window.parent.document);
			if($ifm.length > 0) {
				var ifm = $ifm.get(0);
				ifm.contentWindow.saveOrUpdateTd("create", name, start_date, end_date);
			}
		}
	}
	
</script>
</html>