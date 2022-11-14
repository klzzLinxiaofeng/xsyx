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
	width: 227px;
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
					<form class="form-horizontal tan_form" id="healthStudentCheck_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									日期：
								</label>
								<div class="controls">
								<input type="text" id="checkDate" name="checkDate" class="span4" placeholder="" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${healthStudentCheck.checkDate}"></fmt:formatDate>' onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									年级：
								</label>
								<div class="controls">
								<select id="gradeId" name="gradeId" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									班级：
								</label>
								<div class="controls">
								<select id="teamId" name="teamId" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									姓名：
								</label>
								<div class="controls">
								<select id="studentId" name="studentId" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									主要症状：
								</label>
								<div class="controls">
								<input type="text" id="symptom" name="symptom" class="span4" placeholder="" value="${healthStudentCheck.symptom}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${healthStudentCheck.id}" />
								<c:if test="${isCK == null || isCk == ''}">
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
							</c:if>
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
		
		$.initNBSCascadeSelector({
			"type" : "stu",
			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
			"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
			"stuSelectId" : "studentId",  //学生select标签ID 默认为stu
 			"isEchoSelected" : true,
			"gradeSelectedVal" : "${healthStudentCheck.gradeId}", //要回显的年级唯一标识
			"teamSelectedVal" : "${healthStudentCheck.teamId}", //要回显的班级唯一标识
			"stuSelectedVal" : "${healthStudentCheck.studentId}" //要回显的学生唯一标识 
		});
		
		//性别
		$.jcGcSelector("#sex", {tc : "GB-XB"}, "${healthStudentCheck.sex}", function() {});
		
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#healthStudentCheck_form").validate({
			errorClass : "myerror",
			rules : {
				"checkDate" : {
					required : true
				},
				"gradeId" : {
					selectNone : true
				},
				"teamId" : {
					selectNone : true
				},
				"studentId" : {
					selectNone : true
				},
				"symptom" : {
					required : true,
					maxlength : 50
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
			var $requestData = formData2JSONObj("#healthStudentCheck_form");
			var url = "${ctp}/schoolaffair/healthStudentCheck/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolaffair/healthStudentCheck/" + $id;
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