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
					<form class="form-horizontal tan_form" id="publicClass_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									课程名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span4" placeholder="" value="${publicClass.name}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									上课教师：
								</label>
								<div class="controls">
								<input type="text" id="teacherId" name="teacherId" class="span4" placeholder="" value="${publicClass.teacherName}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									开始上课日期：
								</label>
								<div class="controls">
								<input type="text" id="beginDate" name="beginDate" class="span4" placeholder="" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${publicClass.beginDate}"></fmt:formatDate>' ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									课程总节数：
								</label>
								<div class="controls">
								<input type="text" id="classNumber" name="classNumber" class="span4" placeholder="" value="${publicClass.classNumber}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									人数上限：
								</label>
								<div class="controls">
								<input type="text" id="maxMember" name="maxMember" class="span4" placeholder="" value="${publicClass.maxMember}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									已报名人数：
								</label>
								<div class="controls">
								<input type="text" id="enrollNumber" name="enrollNumber" class="span4" placeholder="" value="${publicClass.enrollNumber}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									报名截止日期：
								</label>
								<div class="controls">
								<input type="text" id="expiryDate" name="expiryDate" class="span4" placeholder="" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${publicClass.expiryDate}"></fmt:formatDate>' ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									报名详情：
								</label>
								<div class="controls">
								<textarea id="enrollDesc" name="enrollDesc" class="span4" placeholder="" rows="3" cols="1" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${publicClass.enrollDesc}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									课程详情：
								</label>
								<div class="controls">
								<textarea id="classDesc" name="classDesc" class="span4" placeholder="" rows="3" cols="1" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${publicClass.classDesc}</textarea>
								</div>
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${publicClass.id}" />
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
		return $("#publicClass_form").validate({
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
			var $requestData = formData2JSONObj("#publicClass_form");
			var url = "${ctp}/teach/publicClass/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/publicClass/" + $id;
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