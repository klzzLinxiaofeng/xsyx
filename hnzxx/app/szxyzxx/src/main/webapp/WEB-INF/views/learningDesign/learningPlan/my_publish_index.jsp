<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理导学案任务</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/dxa/arrange.css">
     <%@ include file="/views/embedded/common.jsp"%>
</head>
<body>
<div class="bzdxa">
    <div class="position">* 管理导学案任务</div>
    <div class="frame">
        <div class="pull-down">标题：
        		<input id="title" type="text">
        </div>
        <div class="pull-down"><button onclick="selectByTitle()">查询</button></div>
        <div class="clear"></div>
        <div class="ranking" style="margin:0;" id="kv">
        	<jsp:include page="./my_publish_list.jsp" />
        </div>
        <div class="zy_list" style="margin-top:20px;height:50px;">
	      	<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
	          	<jsp:param name="id" value="kv" />
	          	<jsp:param name="url" value="/learningPlan/index?index=list" />
	          	<jsp:param name="pageSize" value="" />
	        </jsp:include>
     		<div class="clear"></div>
		</div>
    </div>
</div>
</body>
<script type="text/javascript">
	$(function() {
		selectByTitle();
	})

	function deleteTask(id) {
		var url = "${pageContext.request.contextPath}/learningPlan/task/delete/view?taskId="+id;
		 $.initWinOnTopFromLeft("注意！", url, '600', '350');
	}
	
	function preview(id) {
		var url = "${pageContext.request.contextPath}/cr/learningDesign/learningPlan/edit?type=view&id="+id;
		window.open(url, "预览");
	}
	
	function taskDetail(id) {
		location.href="${pageContext.request.contextPath}/learningPlan/task/detail?id="+id;
	}
	
	function selectByTitle() {
    	var title = $("#title").val();
        var url = "${pageContext.request.contextPath}/learningPlan/task/mypublish";
        var data = {"index":"list","title":title};
        var loader = new loadDialog();
        loader.show();
        myPagination("kv", data, url,function() {
        	loader.close();
        });
    }
	
	function taskModify(taskId, title) {
		 $.initWinOnTopFromLeft("操作设置-"+title, '${pageContext.request.contextPath}/learningPlan/task/modify/view?taskId='+taskId, '600', '350');
	}
</script>
</html>