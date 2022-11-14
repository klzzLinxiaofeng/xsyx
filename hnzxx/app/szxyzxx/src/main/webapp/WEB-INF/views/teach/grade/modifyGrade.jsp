<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改年级</title>
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
	<form action="" id="modifyGradeForm" name="modifyGradeForm" class="form-horizontal" method="post">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container"  style="padding:20px 0 0;">
					<form class="form-horizontal" id="grade_form" action="javascript:void(0);">
						<input type="hidden" id="schoolId" name="schoolId" value="${schoolId}"/>
						<input type="hidden" id="id" name="id" value="${grade.id}"/>
						<div class="control-group">
							<label class="control-label">学年:</label>
							<div class="controls">
								 ${schoolYear.name }
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">学段:</label>
							<div class="controls">
								<select id="stageCode" id="stageCode" name="stageCode" disabled="disabled" class="span4">
		                           	<c:forEach items="${stageList}" var="stage">
		                           		<option value="${stage.code}" <c:if test="${stage.code==grade.stageCode}">selected</c:if> >${stage.name}</option>
		                           	</c:forEach>
	                           	</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">年级</label>
							<div class="controls">
								${grade.fullName}
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>校内名称:</label>
							<div class="controls">
								 <input type="text" id="name" name="name" value="${grade.name }" class="span4" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								 <input type="hidden" id="fullName" name="fullName" value="${grade.fullName }" class="span4" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								 <input type="hidden" id="code" name="code" value="${grade.code }" readonly="readonly" class="span4" />
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<c:if test="${isCK == null || isCk == ''}">
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
							</c:if>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
  </form>
</body>
<script type="text/javascript">
function saveOrUpdate() {
	var loader = new loadLayer();
	var $id = $("#id").val();
	var $requestData = formData2JSONObj("#modifyGradeForm");
	var url = "${pageContext.request.contextPath}/teach/grade/updateGrade";
	if ("" != $id) {
		$requestData._method = "put";
		url = "${pageContext.request.contextPath}/teach/grade/updateGrade";
	}
	loader.show();
	$.post(url, $requestData, function(data, status) {
		if("success" === status) {
			$.success('更新成功');
			if("success" === data) {
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
</script>
</html>