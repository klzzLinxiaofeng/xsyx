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
    	.form-horizontal .control-label{width:120px;}
    	.form-horizontal .controls{margin-left:0px;}
    	.delete_ts{text-align:center;line-height:30px;font-size:14px;font-family:"微软雅黑";margin:25px 73px 10px;}
    	.delete_ts span{color:#ff0000;}
    	button{
	    	width:100px;
	    	height:40px;
	    	text-align:center;
	    	line-height:40px;
    	}
    	.form-horizontal{
    		padding: 0;
    	}
    </style>
</head>
<body>
	<p class="delete_ts">删除<span>${title }</span>会同时删除以下对象的任务，是否执行操作?</p>
	<div class="form-horizontal">
		<div class="control-group"><div class="controls"><div  style="margin: 0 auto;border-radius:4px;border:1px solid #ccc;padding:10px;width: 364px;height: 114px;box-sizing:border-box;overflow:auto">${teamNames }</div></div></div>	
			<div style="text-align:center">
				<button onclick="deleteTask(${taskId})" class="btn btn-primary btn-blue" style="margin-right: 14px;">确定</button>
				<button onclick="$.closeWindow()" class="btn btn-lightGray">取消</button>
			</div>
	</div>
</body>
<script type="text/javascript">
function deleteTask(taskId) {
	var loader = new loadDialog();
    loader.show();
	$.ajax({
        url: "${pageContext.request.contextPath}/learningPlan/task/deleteAll",
        type: "POST",
        data: {"taskId":taskId},
        async: true,
        success: function(data) {
        	loader.close();
       		$.success("删除成功");
       		window.parent.listMyPublish(window.parent.param);
       		$.closeWindow();
        }
    });
}
</script>
</html>