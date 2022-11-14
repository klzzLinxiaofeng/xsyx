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
					<form class="form-horizontal tan_form" id="examteamsubject_form" action="javascript:void(0);">
					
							<div class="control-group">
								<label class="control-label">
									考试名称
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13"
									placeholder="" value="${examTeamSubjectVo.name}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									学年
								</label>
								<div class="controls">
								<input type="text" id="schoolYear" name="schoolYear" class="span13"
									placeholder="" value="${examTeamSubjectVo.schoolYear}">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									考试班级
								</label>
								<div class="controls">
								<input type="text" id="teamId" name="teamId" class="span13"
									placeholder="" value="${examTeamSubjectVo.gradeId}">
								
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
								</label>
								<div class="controls">
								<input type="text" id="teamId" name="teamId" class="span13"
									placeholder="" value="${examTeamSubjectVo.teamIdCollection}">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									考试类型
								</label>
								<div class="controls">
								<input type="text" id="type" name="type" class="span13"
									placeholder="" value="${examTeamSubjectVo.type}">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									考试科目
								</label>
								<div class="controls">
								<input type="text" id="subjectCode" name="subjectCode" class="span13"
									placeholder="" value="${examTeamSubjectVo.subjectCode}">
								</div>
								
							</div>
							
							
							<%--<div class="control-group">
								<label class="control-label">
									是否在线
								</label>
								<div class="controls">
								 <select id="taskOnline"  name="taskOnline" class="chzn-select" style="width:160px;">
										<option value="1">是</option>
										<option value="0">否</option>
										<option value="2">其他</option>
								</select>
								</div>
								
							</div>--%>
							
							 
							
							<div class="control-group">
								<label class="control-label">
									考试时间
								</label>
								<div class="controls">
								<input type="text" id="preciseStartDate" name="preciseStartDate" class="span13"
									placeholder="" value="${examTeamSubjectVo.preciseStartDate}">
								</div>
								
							</div>
							<%--<div class="control-group">
								<label class="control-label">
									考试结束时间
								</label>
								<div class="controls">
								<input type="text" id="preciseEndDate" name="preciseEndDate" class="span13"
									placeholder="" value="${examTeamSubjectVo.preciseEndDate}">
								</div>
								
							</div>--%>
							
							<div class="control-group">
								<label class="control-label">
									考试人数
								</label>
								<div class="controls">
								<input type="text" id="TeamSum" name="TeamSum" class="span13"
									placeholder="" value="${examTeamSubjectVo.teamSum}">
								</div>
								
							</div>
							
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${examTeamSubjectVo.id}" />
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
		return $("#examteamsubject_form").validate({
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
			var $requestData = formData2JSONObj("#examteamsubject_form");
			var url = "${ctp}/teach/examTeamSubject/addExamTeamSubject";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/examTeamSubject/" + $id;
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