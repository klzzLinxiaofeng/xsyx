<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改科任教师</title>

<style>
.row-fluid .span13 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}

.row-fluid .span4 {
	width: 227px;
}
.chzn-results{
	height: 70px;
}
</style>
<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
	});

	function initValidator() {
		$("#subjectCode").val("${subjectTeacher.subjectCode}").chosen();
		return $("#subjectTeacher_form").validate({
			errorClass : "myerror",
			rules : {
				
				"stageCode":{
					required:true
				},
				
				"teacherId" : {
					required : true
					
				},
				
				"subjectCode" : {
					required : true
					
				}
			},
			messages : {
				"subjectCode" : {
					required : "该科目已存在该教师"
				}	
				
			}

		});
	}

	function saveOrUpdate() {
		 if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#subjectTeacher_form");
			var url = "${pageContext.request.contextPath}/teach/subjectTeacher/updateSubjectTeacher";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success("修改成功");
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							var subjectCode1 = ${subjectTeacher.subjectCode}
 							parent.core_iframe.window.location.reload();
							parent.core_iframe.window.ajaxSubjectFunction(subjectCode1);
						} else {
 							parent.window.location.reload();
							parent.window.ajaxSubjectFunction($requestData.subjectCode);
						}
						
						$.closeWindow();
						
					} else{
						$.error("该科任教师在该科目下已存在");
						
					}
				}
				loader.close();
			});

		} 
	}
</script>


</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal" id="subjectTeacher_form" action="javascript:void(0);">
					
					   <div class="control-group">
							<label class="control-label"><font style="color: red"></font>科任教师</label>
							<div class="controls">
								<input type="hidden" id="stageCode" name="stageCode" value="${stageCode }"/>
							    <input type="text" id="name" name="name" class="span4" value="${subjectTeacher.name }" readonly>
								<input type="hidden" id="teacherId" name="teacherId" value="${subjectTeacher.teacherId}"/>
								
							</div>
						</div>
					
					
						<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>科目</label>
							<div class="controls">
								<select id="subjectCode" name="subjectCode"
									onchange="getSubjectCode();" class="span4">
									<c:forEach items="${subjectList}" var="subject">
										<option value="${subject.code}" <c:if test="${subject.code==subjectTeacher.subjectCode}">selected</c:if>>${subject.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${subjectTeacher.id }" />
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
</html>