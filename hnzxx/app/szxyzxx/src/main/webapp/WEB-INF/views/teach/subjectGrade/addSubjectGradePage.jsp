<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加科目</title>
<script src="js/chosen.jquery.js"></script>
<link href="css/chosen.css" rel="stylesheet">
<style>
	.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}
.row-fluid .span4 {
	width: 227px;
}

input[class*="span"], select[class*="span"], textarea[class*="span"],
	.uneditable-input[class*="span"], .row-fluid input[class*="span"],
	.row-fluid select[class*="span"], .row-fluid textarea[class*="span"],
	.row-fluid .uneditable-input[class*="span"] {
	width: 227px;
}
.chzn-container-multi .chzn-choices .search-field input{height:30px;}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets"  style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="addSubjecForm" action="javascript:void(0);">
						<input type="hidden" id="stageCode"  name="stageCode" value="${stageCode }"/>
						<input type="hidden" id="gradeId"  name="gradeId" value="${gradeId }"/>
						<div class="control-group">
							<label class="control-label">科目名称</label>
							<div class="controls">
<!-- 								<select id="subjectId" name="subjectId" class="span4"> -->
<%-- 									<c:forEach items="${subjectList }" var="subject"> --%>
<%-- 		                           		<option value="${subject.id}">${subject.name }</option> --%>
<%-- 				                    </c:forEach> --%>
<!-- 								</select> -->
								
								<!--批量处理添加 -->
								<select id="someSubjectIds"
								 style="height: 200px;" data-placeholder="请选择科目"
								 class="chzn-select span4" multiple tabindex="4">
									<c:forEach items="${subjectList }" var="subject">
		                           		<option value="${subject.id}">${subject.name }</option>
				                    </c:forEach>
								</select>
							</div>
						</div>
						
						<div class="form-actions tan_bottom">
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function () {
    $(".chzn-select").chosen();
    $(".chzn-select-deselect").chosen({
        allow_single_deselect: true
    });
});
	function saveSubjectGrade() {
		var $requestData = formData2JSONObj("#addSubjecForm");
		var gradeId = $("#gradeId").val();
// 		var subjectId = $("#subjectId").val();
		var subjectIds = $("#someSubjectIds").val();
		var stageCode = $("#stageCode").val();
		var url = "${pageContext.request.contextPath}/teach/subjectGrade/addSubjectGradeOfBatch?gradeId="+gradeId+"&subjectIds="+subjectIds+"&stageCode="+stageCode;
		$.post(url, {}, function(data, status) {
			if("success" === status) {
				if("success" === data) {
					$.success("保存成功");
					if(parent.core_iframe != null) {
							parent.core_iframe.window.ajaxFunction(gradeId, null);
						} else {
							parent.window.ajaxFunction(gradeId, null);
						}
					$.closeWindow();
				} else {
					$.success("保存失败");
				}
			}else{
				$.error("服务器异 常");
			}
		});
	}
	
	
	
	function saveOrUpdate(){
		var loader = new loadLayer();
		var gradeId = $("#gradeId").val();
// 		var subjectId = $("#subjectId").val();
		var subjectIds = $("#someSubjectIds").val();
		if(subjectIds == null || subjectIds == "" || subjectIds == "undefined"){
			$.alert("请选择科目");
			return;
		}
		var stageCode = $("#stageCode").val();
		loader.show();
		var url = "${pageContext.request.contextPath}/teach/subjectGrade/checkSubjectGradeOfBatch?gradeId="+gradeId+"&subjectIds="+subjectIds+"&stageCode="+stageCode;
		var aj = $.ajax({
			url : url,
			data : '',
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if(data=="1"){
					saveSubjectGrade();
				}else{
					$.alert("该科目已经存在该班级里！");
				}
				
			},
			error : function() {
				$.alert("异常！");
			}
		});
		loader.close();
	}
	
</script>
</html>