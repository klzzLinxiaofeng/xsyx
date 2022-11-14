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
					<form class="form-horizontal tan_form" id="healthStudentHealing_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									日期：
								</label>
								<div class="controls">
								<input type="text" id="healingDate" name="healingDate" class="span4" placeholder="" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${healthStudentHealing.healingDate}"></fmt:formatDate>' onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									主要症状：
								</label>
								<div class="controls">
								<input type="text" id="symptom" name="symptom" class="span4" placeholder="" value="${healthStudentHealing.symptom}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									处理方式：
								</label>
								<div class="controls">
								<input type="text" id="handle" name="handle" class="span4" placeholder="" value="${healthStudentHealing.handle}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<input type="hidden" id="studentId" name="studentId" value="${studentId }"> 
							<input type="hidden" id="teamId" name="teamId" value="${teamId }"> 
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${healthStudentHealing.id}" />
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
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#healthStudentHealing_form").validate({
			errorClass : "myerror",
			rules : {
				"healingDate" : {
					required : true
				},
				"symptom" : {
					required : true,
					maxlength : 50
				},
				"handle" : {
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
			var $requestData = formData2JSONObj("#healthStudentHealing_form");
			var url = "${ctp}/schoolaffair/healthStudentHealing/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolaffair/healthStudentHealing/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						parent.window.location.reload();
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