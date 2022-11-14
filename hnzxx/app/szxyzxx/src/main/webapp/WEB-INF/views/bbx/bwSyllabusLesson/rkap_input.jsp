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
							<%-- <label class="control-label">课程类型:</label>
							<div class="controls">
								<select class="span4" id="type" name="type" style="width:300px;">
									<option value="1">行政课</option>
									<option value="2">走班课</option>
									<option value="0">不上课</option>
									<c:forEach items="${items}" var="item">
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
							</div> --%>
							
							
							<label class="control-label">课程：</label>
							<div class="controls">
								<select class="span4"  id="subjectCode" name="subjectCode" style="width:240px;">
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
								<select id="teacherName" name="teacherName" style="display: none;">								
								</select>			
							</div>	
						</div>
						
						<div class="control-group">
							<label class="control-label">
								轮数：
							</label>
							<div class="controls">
								<input type="text" id="round" name="round" class="span13" style="width: 240px" placeholder="" value="${bwSyllabusLesson.round}">
							</div>		
						</div>
						
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${bwSyllabusLesson.id}"/>
							<input type="hidden" id="syllabusId" name="syllabusId" value="${bwSyllabusLesson.syllabusId}">
							<input type="hidden" id="gradeSyllabusId" name="gradeSyllabusId" value="${gradeSyllabusId}">
							<input type="hidden" id="lesson" name="lesson" value="${bwSyllabusLesson.lesson}" />
							<input type="hidden" id="dayOfWeek" name="dayOfWeek" value="${bwSyllabusLesson.dayOfWeek}" />
							<input type="hidden" id="gardeId" name="gardeId" value="${gardeId}" />
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
		var type = "${bwSyllabusLesson.type}";
		if ( type == 0 ) {
			$("#type").find("option[value='0']").attr("selected",true);
		} else if (type == 1) {
			$("#type").find("option[value='1']").attr("selected",true);
		} else if (type == 2) {
			$("#type").find("option[value='2']").attr("selected",true);
		}
		var id = $("#id").val();
		var dayOfWeek = $("#dayOfWeek").val();
		var lesson = $("#lesson").val();
		// alert(id + " dayOfWeek:" + dayOfWeek +" lesson:" + lesson);
	});
	
	$("#subjectCode").change(function(){
		var type = "${bwSyllabusLesson.type}";
		if ( type == 2 ) {
			var teacherName = $("#teacherName");
			teacherName.show();
			teacherName.html("");
			var $requestData = {};
			var subjectCode = $("#subjectCode").val();
			var gradeId = $("#gardeId").val();
			$requestData.subjectCode=subjectCode;
			$requestData.gradeId=gradeId;
			var url = "${pageContext.request.contextPath}/bbx/bwSyllabusLesson/getTeacherNameBySubjectCode";
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					$.each(data, function (index, obj) {
						teacherName.append("<option value='" + obj.teacherId + "'>" + obj.teacherName +"</option>" );
			        });
				}
			});
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
		var type = "${bwSyllabusLesson.type}";
		if ( type == 1 ) { // 行政班科任老师
			$requestData.teacherId = $("#subjectCode").find("option:selected").attr("data-teacher-id");
		} else if ( type == 2 ) { // 走班科任老师
			$requestData.teacherId = $("#teacherName").val();
		}		
		$requestData.xq = $("#xq").val();
		$.ajax({
			async : false,
		    url: "${ctp}/bbx/bwSyllabusLesson/isAllowCreate",
		    type: "GET",
		    data: $requestData,
		    timeout: 5000,
		    success : function(respon) {
		    	data = eval("(" + respon + ")");
		    	//alert(data.info);
				if("success" === data.info) {
					excuteSaveOrUpdate();
				} else {
					$.error(data.responseData);
				}
		    	//alert(data);

		    	<%-- responseData = eval("(" + responseData + ")");
		    	if("success" === responseData.info){
					excuteSaveOrUpdate(url, $requestData);
				} else if("<%= RuleResponseInfo.RULE_ONE %>" === responseData.info) {
					var msg = "当前老师已在班级（{bjMc}）安排了的课程";
					msg = msg.replace("{bjMc}", responseData.responseData.teamName);
					$.alert(msg);
				} else if("<%= RuleResponseInfo.RULE_TWO %>" === responseData.info) {
					var msg = "当前课节已经安排了课程";
					$.alert(msg);
				} --%>
		    }
		});
		//
		
	}
	
	function validateTeacher(){
		
	}
	
	function excuteSaveOrUpdate() {	
		var $id = $("#id").val();
		var $requestData = formData2JSONObj("#kb_form");
		var type = "${bwSyllabusLesson.type}";
		if ( type == 1 ) { // 行政班科任老师
			$requestData.teacherId = $("#subjectCode").find("option:selected").attr("data-teacher-id");
		} else if ( type == 2 ) { // 走班科任老师
			$requestData.teacherId = $("#teacherName").val();
		}	
		var url = "${pageContext.request.contextPath}/bbx/bwSyllabusLesson/creator";
		if ("" != $id) {
			$requestData._method = "put";
			url = "${pageContext.request.contextPath}/bbx/bwSyllabusLesson/" + $id;
		}
		
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