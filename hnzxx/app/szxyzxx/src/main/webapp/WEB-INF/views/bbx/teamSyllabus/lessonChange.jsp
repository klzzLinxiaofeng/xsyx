<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<style>
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</head>
<body style="background-color: #F3F3F3 !important;">
<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal padding15" id="syllabus_form">
					<input type="hidden" id="teamId" name="teamId" value="${teamId }" />
						<div class="control-group">
							<textarea type="text" id="content" name="content" class="span12" style="min-height:125px;" value=" "placeholder="请描述调课的时间以及具体安排">${messageLessonChange.content }</textarea>
						</div>
						<div class="control-group">
					    	<input id="changeDate"  name="changeDate" type="text" onclick="WdatePicker({minDate:'%y-%M-{%d}}',dateFmt:'yyyy-MM-dd'});" value="<fmt:formatDate value='${messageLessonChange.changeDate }' />" placeholder="调课截止时间" class="span12 sj_time">
						</div>
					</form>
						<div class="form-actions tan_bottom_1">
								<input type="hidden" id="id" name="id" value="${messageLessonChange.id}" />
							<c:choose>
								<c:when test="${not empty messageLessonChange}">
									<a href="javascript:void(0)" class="yellow"
										onclick="saveOrUpdate();">保存</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)" class="yellow"
										onclick="saveOrUpdate();">发布</a>
								</c:otherwise>
							</c:choose>
							<a href="javascript:void(0)" onclick="closeWin();">取消</a>
						</div>
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
	return $("#syllabus_form").validate({
		errorClass : "myerror",
		rules : {
			"content" : {
				required : true,
				minlength : 1,
				maxlength : 140,
			},
			"changeDate" : {
				required : true
			}
		},
		messages : {
			"name" : {
			}
		}
	});
}

//保存或更新修改
function saveOrUpdate() {
	if (checker.form()) {
		var loader = new loadLayer();
		var $id = $("#id").val();
		var content=document.getElementById("content").value; 
		content=content.replace('\n',' '); 
		document.getElementById("content").value=content;
		var $requestData = formData2JSONObj("#syllabus_form");
		//alert(JSON.stringify($requestData));
		var url = "${pageContext.request.contextPath}/clazz/teamSyllabus/creator";
		if ("" != $id) {
			$requestData._method = "put";
			url = "${pageContext.request.contextPath}/clazz/teamSyllabus/" + $id;
		}
		loader.show();
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					parent.core_iframe.search();
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

function closeWin(){
	$.confirm("确定离开此页面？", function() {
		$.closeWindow();
	});
}
</script>