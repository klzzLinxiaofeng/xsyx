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
					<form class="form-horizontal tan_form" id="studentmilitary_form" action="javascript:void(0);">
							<%-- <div class="control-group" >
								<label class="control-label" >
									主键：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${studentmilitary.id}">
								</div>
							</div> --%>
							
							<%-- <div class="control-group">
								<label class="control-label">
									学生ID，根据学生id可以找到学生信息。关联表pj_student.id：
								</label>
								<div class="controls">
								<input type="text" id="studentId" name="studentId" class="span13" placeholder="" value="${studentmilitary.studentId}">
								</div>
							</div> --%>
							<div class="control-group">
								<label class="control-label">
									全国统一学籍号：
								</label>
								<div class="controls">
								<input type="text" id="studentNumber" name="studentNumber" class="span13" placeholder="" value="${studentmilitary.studentNumber}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									学生姓名：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${studentmilitary.name}">
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">
									班级ID：
								</label>
								<div class="controls">
								<input type="text" id="teamId" name="teamId" class="span13" placeholder="" value="${studentmilitary.teamId}">
								</div>
							</div> --%>
							<div class="control-group">
								<label class="control-label">
									班级名称：
								</label>
								<div class="controls">
								<input type="text" id="teamName" name="teamName" class="span13" placeholder="" value="${studentmilitary.teamName}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									学号：
								</label>
								<div class="controls">
								<input type="text" id="number" name="number" class="span13" placeholder="" value="${studentmilitary.number}">
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">
									军训状态：
								</label>
								<div class="controls">
								<input type="text" id="status" name="status" class="span13" placeholder="" value="${studentmilitary.status}">
								</div>
							</div> --%>
							<div class="control-group">
								<label class="control-label">
									军训状态：
								</label>
								<div class="controls">
									<input type="radio" id="status" name="status" placeholder="" value="已军训" ${studentMilitary.status=='已军训'?'Checked':''}/>已军训
									<input type="radio" id="status" name="status" placeholder="" value="未军训" ${studentMilitary.status=='未军训'?'Checked':''}/>未军训
									<input type="radio" id="status" name="status" placeholder="" value="病假" ${studentMilitary.status=='病假'?'Checked':''}/>病假
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									请假天数：
								</label>
								<div class="controls">
								<input type="text" id="leaveDay" name="leaveDay" class="span13" placeholder="" value="${studentmilitary.leaveDay}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									军训成绩：
								</label>
								<div class="controls">
								<input type="text" id="score" name="score" class="span13" placeholder="" value="${studentmilitary.score}">
								</div>
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${studentmilitary.id}" />
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
		return $("#studentmilitary_form").validate({
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
			var $requestData = formData2JSONObj("#studentmilitary_form");
			var url = "${ctp}/teach/military/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/military/" + $id;
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