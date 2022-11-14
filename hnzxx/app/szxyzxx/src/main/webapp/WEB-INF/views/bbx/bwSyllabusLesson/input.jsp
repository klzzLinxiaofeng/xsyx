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
					<form class="form-horizontal tan_form" id="bwsyllabuslesson_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									主键：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${bwsyllabuslesson.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									bw_syllabus.id：
								</label>
								<div class="controls">
								<input type="text" id="syllabusId" name="syllabusId" class="span13" placeholder="" value="${bwsyllabuslesson.syllabusId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									课程类型： 0不上课，1行政班，2走班：
								</label>
								<div class="controls">
								<input type="text" id="type" name="type" class="span13" placeholder="" value="${bwsyllabuslesson.type}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									节次，第几节：
								</label>
								<div class="controls">
								<input type="text" id="lesson" name="lesson" class="span13" placeholder="" value="${bwsyllabuslesson.lesson}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									星期几 XY-JY-XQ 0=星期日 1=星期一：
								</label>
								<div class="controls">
								<input type="text" id="dayOfWeek" name="dayOfWeek" class="span13" placeholder="" value="${bwsyllabuslesson.dayOfWeek}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									pj_teacher.id：
								</label>
								<div class="controls">
								<input type="text" id="teacherId" name="teacherId" class="span13" placeholder="" value="${bwsyllabuslesson.teacherId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									pj_subject：
								</label>
								<div class="controls">
								<input type="text" id="subjectCode" name="subjectCode" class="span13" placeholder="" value="${bwsyllabuslesson.subjectCode}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									subjectName：
								</label>
								<div class="controls">
								<input type="text" id="subjectName" name="subjectName" class="span13" placeholder="" value="${bwsyllabuslesson.subjectName}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									第几轮：
								</label>
								<div class="controls">
								<input type="text" id="round" name="round" class="span13" placeholder="" value="${bwsyllabuslesson.round}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									createDate：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${bwsyllabuslesson.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									modifyDate：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${bwsyllabuslesson.modifyDate}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${bwsyllabuslesson.id}" />
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
		return $("#bwsyllabuslesson_form").validate({
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
			var $requestData = formData2JSONObj("#bwsyllabuslesson_form");
			var url = "${ctp}/bbx/bwSyllabusLesson/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/bwSyllabusLesson/" + $id;
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