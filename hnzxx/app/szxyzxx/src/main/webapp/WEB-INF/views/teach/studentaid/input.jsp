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
					<form class="form-horizontal tan_form" id="studentaid_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									主键：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13"
									placeholder="" value="${studentaid.id}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									所在班级ID：
								</label>
								<div class="controls">
								<input type="text" id="teamId" name="teamId" class="span13"
									placeholder="" value="${studentaid.teamId}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									学生ID：
								</label>
								<div class="controls">
								<input type="text" id="studentId" name="studentId" class="span13"
									placeholder="" value="${studentaid.studentId}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									贫困类别：
								</label>
								<div class="controls">
								<input type="text" id="povertyCategory" name="povertyCategory" class="span13"
									placeholder="" value="${studentaid.povertyCategory}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									贫困原因：
								</label>
								<div class="controls">
								<input type="text" id="povertyCauses" name="povertyCauses" class="span13"
									placeholder="" value="${studentaid.povertyCauses}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									助困形式：
								</label>
								<div class="controls">
								<input type="text" id="aidForm" name="aidForm" class="span13"
									placeholder="" value="${studentaid.aidForm}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									家庭收入/人口：
								</label>
								<div class="controls">
								<input type="text" id="oneIncome" name="oneIncome" class="span13"
									placeholder="" value="${studentaid.oneIncome}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									资助金额：
								</label>
								<div class="controls">
								<input type="text" id="aidAmount" name="aidAmount" class="span13"
									placeholder="" value="${studentaid.aidAmount}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									资助日期：
								</label>
								<div class="controls">
								<input type="text" id="aidDay" name="aidDay" class="span13"
									placeholder="" value="${studentaid.aidDay}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<input type="text" id="remark" name="remark" class="span13"
									placeholder="" value="${studentaid.remark}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									学年：
								</label>
								<div class="controls">
								<input type="text" id="schoolYear" name="schoolYear" class="span13"
									placeholder="" value="${studentaid.schoolYear}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									createDate：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13"
									placeholder="" value="${studentaid.createDate}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									modifyDate：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13"
									placeholder="" value="${studentaid.modifyDate}">
								</div>
								
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${studentaid.id}" />
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
		return $("#studentaid_form").validate({
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
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#studentaid_form");
			var url = "${ctp}/teach/studentaid/addStudentAid";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/studentaid/updateStudentAid"
			}
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('保存成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						
					}
				}else{
					$.error("保存失败");
				}
			});
		}
	}
	
</script>
</html>