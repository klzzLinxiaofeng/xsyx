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
					<form class="form-horizontal tan_form" id="bwsubjectteacher_form" action="javascript:void(0);">
							
							<div class="control-group">
								<label class="control-label">
									老师姓名：
								</label>
								<div class="controls">
								<input type="text" id="teacherId" name="teacherId" class="span13" placeholder="" value="${teacherName}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									轮数：
								</label>
								<div class="controls">
								<input type="text" id="round" name="round" class="span13" placeholder="" value="${bwSubjectTeacher.round}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${bwSubjectTeacher.id}" />
							<input type="hidden" id="subjectId" name="subjectId" value="${bwSubjectTeacher.subjectId}" />
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
		return $("#bwsubjectteacher_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}
	
	//更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = {};
			$requestData.id = $("#id").val();;
			var url = "${ctp}/bbx/courseTeacher/updateCourseTeacher";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						var courseId = $("#courseId").val();
						parent.core_iframe.window.ajaxSubjectFunction(courseId);
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