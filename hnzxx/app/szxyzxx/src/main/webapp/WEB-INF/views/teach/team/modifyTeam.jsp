<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级修改</title>
<style>
.row-fluid .span4 {
	width: 227px;
}

input[class*="span"], select[class*="span"], textarea[class*="span"],
	.uneditable-input[class*="span"], .row-fluid input[class*="span"],
	.row-fluid select[class*="span"], .row-fluid textarea[class*="span"],
	.row-fluid .uneditable-input[class*="span"] {
	width: 227px;
}
</style>
</head>
<body>            
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container"  style="padding:20px 0 0;">
						<form class="form-horizontal" id="modifyTeam_form" action="javascript:void(0);">
							<input type="hidden" name="id" id="id" value="${team.id}"/>
							<div class="control-group">
								<label class="control-label"><font style="color:red">*</font>顺序编号</label>
								<div class="controls">
									<input type="text" id="teamNumber" name="teamNumber" value="${team.teamNumber}" disabled="disabled" class="span4"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color:red">*</font>班级名称</label>
								<div class="controls">
									 <input type="text" id="fullName" name="fullName" value="${team.fullName}" readonly="readonly" class="span4" >
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color:red">*</font>校内名称</label>
								<div class="controls">
									<input type="text" id="name" name="name" value="${team.name}" class="span4" >
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"><font style="color:red">*</font>校内编号</label>
								<div class="controls">
									<input type="text" id="code2" name="code2" value="${team.code2}" disabled="disabled" class="span4" >
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">班级类别</label>
								<div class="controls">
									<select id="teamType" name="teamType" class="span4" >
										
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">班级属性</label>
								<div class="controls">
									   <select id="teamProperty" name="teamProperty" class="span4">
									 	
									  </select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"></label>
								<div class="controls">
									 <input type="hidden" id="code" name="code" value="${team.code}" class="span4" >
								</div>
							</div>
<!-- 							<div class="control-group"> -->
<!-- 								<label class="control-label"><font style="color:red">*</font>启用时间</label> -->
<!-- 								<div class="controls"> -->
<!-- 									 <input type="text" id="beginDate" name="beginDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${team.beginDate}"></fmt:formatDate>' onfocus="WdatePicker();" class="span4"> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="control-group"> -->
<!-- 								<label class="control-label"><font style="color:red">*</font>结束时间</label> -->
<!-- 								<div class="controls"> -->
<!-- 									 <input type="text" id="finishDate" name="finishDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${team.finishDate}"></fmt:formatDate>'  onfocus="WdatePicker();" class="span4"> -->
<!-- 								</div> -->
<!-- 							</div> -->
							<div class="form-actions tan_bottom" >
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
	$(function () {
		//班级类别
		$.jcGcSelector("#teamType", {tc : "XY-JY-BJSX"}, "${team.teamType}", function() {
			
		});
		//班级属性
		$.jcGcSelector("#teamProperty", {tc : "JY-ZXXBJLX"}, "${team.teamProperty}", function() {
			
		});
		checker = initValidator();
	});
	function initValidator() {
		return $("#modifyTeam_form")
				.validate(
					{
						errorClass : "myerror",
						rules : {
							
							"fullName" : {
								required : true
							},
							"name":{
								required : true
							}
							
						},
						messages : {
							"fullName":{
								required:"班级名称不能为空"
							},
							"name":{
								required : "校内名称不能为空"
							}
						}
					});
	}
	
	function saveOrUpdate() {
		if(checker.form()){
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#modifyTeam_form");
			var url = "${pageContext.request.contextPath}/teach/team/updateTeam";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('更新成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
								parent.core_iframe.window.ajaxFunction(${team.gradeId}, null);
							} else {
								parent.window.ajaxFunction(${team.gradeId}, null);
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