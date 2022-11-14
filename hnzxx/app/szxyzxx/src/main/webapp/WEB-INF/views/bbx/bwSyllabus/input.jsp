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
					<form class="form-horizontal tan_form" id="bwsyllabus_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									主键：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${bwsyllabus.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									bw_grade_syllabus：
								</label>
								<div class="controls">
								<input type="text" id="gradeSyllabusId" name="gradeSyllabusId" class="span13" placeholder="" value="${bwsyllabus.gradeSyllabusId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									相关学校pj_school.id：
								</label>
								<div class="controls">
								<input type="text" id="schoolId" name="schoolId" class="span13" placeholder="" value="${bwsyllabus.schoolId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									教室id：
								</label>
								<div class="controls">
								<input type="text" id="roomId" name="roomId" class="span13" placeholder="" value="${bwsyllabus.roomId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									年级id：
								</label>
								<div class="controls">
								<input type="text" id="teamId" name="teamId" class="span13" placeholder="" value="${bwsyllabus.teamId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									pj_school_term.code：
								</label>
								<div class="controls">
								<input type="text" id="termCode" name="termCode" class="span13" placeholder="" value="${bwsyllabus.termCode}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									createDate：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${bwsyllabus.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									modifyDate：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${bwsyllabus.modifyDate}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${bwsyllabus.id}" />
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
		return $("#bwsyllabus_form").validate({
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
			var $requestData = formData2JSONObj("#bwsyllabus_form");
			var url = "${ctp}/clazz/bwsyllabus/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/clazz/bwsyllabus/" + $id;
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