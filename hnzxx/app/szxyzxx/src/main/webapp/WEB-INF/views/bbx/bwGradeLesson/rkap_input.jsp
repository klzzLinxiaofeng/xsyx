<%@ page language="java" import="platform.education.generalTeachingAffair.ext.syllabus.rule.filter.constants.RuleResponseInfo" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>年级课表基础设置</title>
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
							<label class="control-label">课程类型:</label>
							<div class="controls">
								<select class="span4" id="type" name="type" style="width:300px;">
									<option value="1">行政课</option>
									<option value="2">走班课</option>
									<option value="0">不上课</option>
									<%-- <c:forEach items="${items}" var="item">
										<c:choose>
											<c:when test="${empty item.teacherId}">
												<option value="${item.subjectCode}" data-teacher-id="${item.teacherId}">${item.subjectName}</option>
											</c:when>
											<c:otherwise>
												<option value="${item.subjectCode}" data-teacher-id="${item.teacherId}">${item.subjectName} --- ${item.teacherName}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach> --%>
								</select>
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${bwGradeLesson.id}"/>
							<input type="hidden" id="gradeSyllabusId" name="gradeSyllabusId" value="${bwGradeLesson.gradeSyllabusId}">
							<input type="hidden" id="lesson" name="lesson" value="${bwGradeLesson.lesson}" />
							<input type="hidden" id="dayOfWeek" name="dayOfWeek" value="${bwGradeLesson.dayOfWeek}" />
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
		// checker = initValidator();
		// var subjectCode = "${lesson.subjectCode}";
		var type = "${bwGradeLesson.type}";
		if ( type == 0 ) {
			$("#type").find("option[value='0']").attr("selected",true);
		} else if (type == 1) {
			$("#type").find("option[value='1']").attr("selected",true);
		} else if (type == 2) {
			$("#type").find("option[value='2']").attr("selected",true);
		}
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
		var $id = $("#id").val();
		var $requestData = formData2JSONObj("#kb_form");
		// $requestData.teacherId = $("#subjectCode").find("option:selected").attr("data-teacher-id");
		var type = $("#type").val();
		var orginalType = "${bwGradeLesson.type}";
		/* if ( type != orginalType ) {
			alert("aa");
			$.confirm({
			    title: 'Confirm!',
			    content: 'Simple confirm!',
			    confirm: function(){
			        $.alert('Confirmed!');
			    },
			    cancel: function(){
			        $.alert('Canceled!')
			    }
			});
		
		} */
		var url = "${pageContext.request.contextPath}/bbx/bwGradeLesson/creator";
		if ("" != $id) {
			$requestData._method = "put";
			url = "${pageContext.request.contextPath}/bbx/bwGradeLesson/" + $id;
		}
		excuteSaveOrUpdate(url, $requestData);
		<%-- $.ajax({
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
		}); --%>
	}
	
	function excuteSaveOrUpdate(url, $requestData) {
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				if("success" === data.info) {
					if(parent.core_iframe != null) {
							parent.core_iframe.showSyllabusArrangementPane($("#gradeSyllabusId").val());
					} else {
						parent.showSyllabusArrangementPane($("#gradeSyllabusId").val());
					}	
					$.closeWindow();
				} 
			}
		});
	}
	
</script>
</html>