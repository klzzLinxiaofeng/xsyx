<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>完成统计情况</title>
     <%@ include file="/views/embedded/common.jsp"%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/dxa/arrange.css">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/waterbubble.js"></script>
    <style>
    	.form-horizontal .control-label{width:120px;color: #999999;font-size:14px;}
    	.form-horizontal .controls{margin-left:140px;}
    	button{
    	width:100px;
    	height:40px;
    	text-align:center;
    	line-height:40px;
    	margin-top: 36px;
    	}
    </style>
</head>
<body style="overflow: hidden;">
	<div class="form-horizontal" style="margin-top:27px;">
	<div class="control-group"><label class="control-label"><span class="red">*</span>导学案标题：</label>
		<div class="controls"><input type="text" id="title" value="${title }" style="width: 82%;maxlength:50;border-radius:4px";></div>
	</div>
	<div class="control-group"><label class="control-label">发布对象：</label><div class="controls"><div style="border:1px solid #ccc;padding:10px;width: 85%;height: 120px;box-sizing:border-box;overflow:auto;border-radius:4px;">${teamNames }</div></div></div>	
		<div style="text-align:center; margin-top: 29px;">
			<button onclick="modify(${taskId})" class="btn btn-primary btn-blue" style="margin-right: 14px;">确定</button>
			<button onclick="$.closeWindow()" class="btn btn-lightGray">取消</button>
		</div>
	</div>
</body>

<script type="text/javascript">
function modify(taskId) {
	var title = $("#title").val();
	if(title==null || ""==title) {
		$.alert("请输入导学案标题");
		return false;
	}
	$.ajax({
        url: "${pageContext.request.contextPath}/learningPlan/task/modify",
        type: "POST",
        data: {"taskId":taskId, "title":title},
        async: true,
        success: function(data) {
        	$.success("修改成功");
        	window.parent.listMyPublish(window.parent.param);
       		$.closeWindow();
        }
    });
}
</script>
</html>