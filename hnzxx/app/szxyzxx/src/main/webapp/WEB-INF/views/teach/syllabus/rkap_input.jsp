<%@ page language="java" import="platform.education.generalTeachingAffair.ext.syllabus.rule.filter.constants.RuleResponseInfo" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>任课安排</title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.myerror {
	color: red !important;
	width:34%;
	display:inline-block;
	padding-left:10px;
}
</style>
</head>
<body style="background-color: #F3F3F3 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div style="margin-bottom: 0" class="content-widgets">
				<div style="padding: 20px 0 0;" class="widget-container">
					<form class="form-horizontal" id="kb_form">
						<div class="control-group">
							<label class="control-label">课程</label>
							<div class="controls">
								<select class="span4" id="subjectCode" name="subjectCode" style="width:300px;">
									<option value="">请选择</option>
									<c:forEach items="${items}" var="item">
<%-- 										<option value="${item.subjectCode}" data-teacher-id="${item.teacherId}">${item.subjectName} --- ${item.name}</option> --%>
										<c:choose>
											<c:when test="${empty item.teacherId}">
												<option value="${item.subjectCode}" data-teacher-id="${item.teacherId}">${item.subjectName}</option>
											</c:when>
											<c:otherwise>
												<option value="${item.subjectCode}" data-teacher-id="${item.teacherId}">${item.subjectName} --- ${item.teacherName}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="dayOfWeek" name="dayOfWeek" value="${syllabusVo.dayOfWeek}" />
							<input type="hidden" id="lesson" name="lesson" value="${syllabusVo.lesson}" />
							<input type="hidden" id="schoolYear" name="schoolYear" value="${syllabusVo.schoolYear}" />
							<input type="hidden" id="teamId" name="teamId" value="${syllabusVo.teamId}" />
							<input type="hidden" id="termCode" name="termCode" value="${syllabusVo.termCode}" />
							<input type="hidden" id="syllabusId" name="syllabusId" value="${syllabusVo.id}">
							<input type="hidden" id="lessonId" name="lessonId" value="${lesson.id}"/>
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
		var subjectCode = "${lesson.subjectCode}";
	});
	
	function initValidator() {
		return $("#kb_form").validate({
			errorClass : "myerror",
			rules : {
				"subjectCode" : {
					required : true
				},
			},
			messages : {
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var $lessonId = $("#lessonId").val();
			var $requestData = formData2JSONObj("#kb_form");
			$requestData.teacherId = $("#subjectCode").find("option:selected").attr("data-teacher-id");
			
			var url = "${pageContext.request.contextPath}/teach/syllabus/rkap/creator";
			if ("" != $lessonId) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/teach/syllabus/rkap/" + $lessonId;
			}
			
			$.ajax({
				async : false,
			    url: "${ctp}/teach/syllabus/rkap/rules/validator",
			    type: "GET",
			    data: $requestData,
			    timeout: 5000,
			    success : function(responseData) {
			    	responseData = eval("(" + responseData + ")");
			    	if("success" === responseData.info){
						excuteSaveOrUpdate(url, $requestData);
					} else if("<%= RuleResponseInfo.RULE_ONE %>" === responseData.info) {
						var msg = "当前老师已在班级（{bjMc}）安排了的课程";
						msg = msg.replace("{bjMc}", responseData.responseData.teamName);
						$.alert(msg);
					} else if("<%= RuleResponseInfo.RULE_TWO %>" === responseData.info) {
						var msg = "当前课节已经安排了课程";
						$.alert(msg);
					}
			    }
			});
		}
	}
	
	function excuteSaveOrUpdate(url, $requestData) {
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				if("success" === data.info) {
					if(parent.core_iframe != null) {
							parent.core_iframe.showSyllabusArrangementPane($("#syllabusId").val());
					} else {
						parent.showSyllabusArrangementPane($("#syllabusId").val());
					}	
					$.closeWindow();
				} else {
					
				}
			}
		});
	}
	
</script>
</html>