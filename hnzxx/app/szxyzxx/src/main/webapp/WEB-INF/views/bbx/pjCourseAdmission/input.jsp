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
					<form class="form-horizontal tan_form" id="pjcourseadmission_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${pjCourseAdmission.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									学生id：
								</label>
								<div class="controls">
								<input type="text" id="studentId" name="studentId" class="span13" placeholder="" value="${pjCourseAdmission.studentId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									用户id：
								</label>
								<div class="controls">
								<input type="text" id="userId" name="userId" class="span13" placeholder="" value="${pjCourseAdmission.userId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									课程id：
								</label>
								<div class="controls">
								<input type="text" id="courseId" name="courseId" class="span13" placeholder="" value="${pjCourseAdmission.courseId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									学校id：
								</label>
								<div class="controls">
								<input type="text" id="schoolId" name="schoolId" class="span13" placeholder="" value="${pjCourseAdmission.schoolId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									年级id：
								</label>
								<div class="controls">
								<input type="text" id="gradeId" name="gradeId" class="span13" placeholder="" value="${pjCourseAdmission.gradeId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									学期编码：
								</label>
								<div class="controls">
								<input type="text" id="termCode" name="termCode" class="span13" placeholder="" value="${pjCourseAdmission.termCode}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${pjCourseAdmission.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${pjCourseAdmission.modifyDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否删除：
								</label>
								<div class="controls">
								<input type="text" id="isDeleted" name="isDeleted" class="span13" placeholder="" value="${pjCourseAdmission.isDeleted}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${pjCourseAdmission.id}" />
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
		return $("#pjcourseadmission_form").validate({
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
			var $requestData = formData2JSONObj("#pjcourseadmission_form");
			var url = "${ctp}/bbx/pjCourseAdmission/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/pjCourseAdmission/" + $id;
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