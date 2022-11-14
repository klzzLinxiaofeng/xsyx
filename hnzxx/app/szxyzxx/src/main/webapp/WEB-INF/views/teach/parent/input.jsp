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
	display: inline-block;
	padding-left: 10px;
}
   .form-horizontal .control-group{
    	width:45%;
    	display:inline-block;
    	float:left;
    }
</style>
</head>
<body style="background-color: cdd4d7 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="studentaward_form" class="form-horizontal left-align form-well" novalidate="novalidate">
							
							<div class="control-group">
									<label class="control-label">学年</label>
										<div class="controls">
										<select id="schoolYear" name="schoolYear" class="chzn-select" style="width:160px;"></select>
									
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">年级</label>
									<div class="controls">
										 <select id="gradeId" name="gradeId" class="chzn-select" style="width:120px;"></select>
										
									</div>
								</div>
									
								<div class="control-group">
									<label class="control-label">班级</label>
									<div class="controls">
										<select id="teamId" name="teamId" class="chzn-select" style="width:160px;"></select>
										
									</div>
							</div>
							<div class="control-group">
								<label class="control-label">姓名</label>
									<div class="controls">
										<select id="studentId" name="studentId" class="chzn-select" style="width:120px;"></select>
									
									</div>
							</div>
									
							<div class="control-group">
								<label class="control-label">
									班内编号
								</label>
								<div class="controls">
								<input type="text"  style="width:160px;"  id="numInTeam" name="numInTeam" class="span13"
									placeholder="班内编号" value="${studentaward.numInTeam}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									奖励内容
								</label>
								<div class="controls">
								<%-- <input type="text"  style="width:160px;"  id="awardContent" name="awardContent" class="span13"
									placeholder="奖励内容" value="${studentaward.awardContent}"> --%>
									
									<select id="awardContent" name="awardContent" class="chzn-select" style="width:160px;" ></select>
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									奖励级别
								</label>
								<div class="controls">
								<%-- <input type="text"  style="width:160px;"  id="awardLevel" name="awardLevel" class="span13"
									placeholder="奖励级别" value="${studentaward.awardLevel}"> --%>
									<select id="awardLevel" name="awardLevel" class="chzn-select" style="width:160px;" ></select>
									
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									获奖名次
								</label>
								<div class="controls">
								<input type="text"  style="width:160px;"  id="awardRanking" name="awardRanking" class="span13"
									placeholder="奖励名次" value="${studentaward.awardRanking}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									奖励类型
								</label>
								<div class="controls">
								<%-- <input type="text"  style="width:160px;"  id="awardType" name="awardType" class="span13"
									placeholder="奖励类型" value="${studentaward.awardType}"> --%>
									
									<select id="awardType" name="awardType" class="chzn-select" style="width:160px;" ></select>
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									奖励日期
								</label>
								<div class="controls">
								<input type="text"  style="width:160px;"  id="awardDay" name="awardDay" class="span13"
									placeholder="奖励日期" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentaward.awardDay}"/>' onclick="WdatePicker()">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									奖励单位
								</label>
								<div class="controls">
								<input type="text"  style="width:160px;"  id="awardUnit" name="awardUnit" class="span13"
									placeholder="奖励单位" value="${studentaward.awardUnit}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									备注
								</label>
								<div class="controls">
								<input type="text"  style="width:200px;"  id="remark" name="remark" class="span13"
									placeholder="备注" value="${studentaward.remark}">
								</div>
								
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${studentaward.id}" />
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
		
		$.initCascadeSelector({
			"type" : "stu",
			"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
			"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
			"stuSelectId" : "studentId",  //学生select标签ID 默认为stu
 			"isEchoSelected" : true,
			"yearSelectedVal" : "${studentaward.schoolYear}", //要回显的学年唯一标识
			"gradeSelectedVal" : "${studentaward.gradeId}", //要回显的年级唯一标识
			"teamSelectedVal" : "${studentaward.teamId}", //要回显的班级唯一标识
			 "stuSelectedVal" : "${studentaward.studentId}" //要回显的学生唯一标识 
		});
		
		//获奖类型 
 		$.jcGcSelector("#awardType", {tc : "JY-HJLX"}, "${studentaward.awardType}", function() {
 			$("#awardType").chosen();
 		});
 		//奖励方式
 		$.jcGcSelector("#awardContent", {tc : "JY-JLFS"}, "${studentaward.awardContent}", function() {
 			$("#awardContent").chosen();
 		});
 		//奖励级别
 		$.jcGcSelector("#awardLevel", {tc : "JY-XSHJLB"}, "${studentaward.awardLevel}", function() {
 			$("#awardLevel").chosen();
 		});
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#studentaward_form").validate({
			errorClass : "myerror",
			rules : {
				
				"schoolYear":{
					required : true
				},
				"gradeId":{
					required : true
				},
				"teamId":{
					required : true
				},
				"studentId":{
					required : true
				}
				
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
			var $requestData = formData2JSONObj("#studentaward_form");
			var url = "${ctp}/teach/studentAward/addStudentAward";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/studentAward/updateStudentAward";
			}
			loader.show();
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
				loader.close();
			});
		}
	}
	
</script>
</html>