<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>A1-0检索</title>
<style>
	.search_div{
		position:relative;
		    top: -16px;
	}
</style>
</head>
<body>
<jsp:include page="./header.jsp"></jsp:include>
<div class="content_main">
	<jsp:include page="/views/embedded/plugin/stageWidget.jsp"></jsp:include>
	<div class="search_div">
		<label>搜索：</label>
		<div class="ss">
			<input type="text" placeholder="导学案标题" id="dxa_title"/>
			<a href="javascript:void(0)" class="btn-blue" onclick="searchTitle()">搜索</a>
		</div>
	</div>
	<div class="nr_right" style="margin-left:0px">
		<div class="dxa_list" style="margin-bottom:20px;" id="lpList">
			<jsp:include page="./my_publish_list.jsp"></jsp:include>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
var param = null;

$(function() {
	$(".qyzj_header .qyzj_right ul li a").eq(1).addClass("btn-blue");
	invokeStageWidget(0);
})

//调用学段科目组件
function invokeStageWidget(type) {
	$.stageWidget(type, true, function(data) {
 		//记录科目和学段
		param = data;
		listMyPublish(data); 
	})
}

//根据相应科目去获取导学案列表
function listMyPublish(data) {
	myPagination("lpList", data, "/learningPlan/task/my/list");
}

//排序
function orderByParam(orderParam, obj) {
	//切换筛选标志
	$(obj).parent(".left").children("a").removeClass("on");
	$(obj).addClass("on");
	
	var b = $(obj).children("b");
	var ascending = null;
	if(b.hasClass("fa-long-arrow-down")){
		ascending = true;
		b.removeClass("fa-long-arrow-down").addClass("fa-long-arrow-up");
	} else {
		ascending = false;
		b.removeClass("fa-long-arrow-up").addClass("fa-long-arrow-down");
	};
	//添加排序的参数
	param["property"] = orderParam;
	param["ascending"] = ascending;
}

function taskModify(taskId, title) {
	 $.initWinOnTopFromLeft("操作设置-"+title, '${pageContext.request.contextPath}/learningPlan/task/modify/view?taskId='+taskId, '600', '350');
}

function deleteTask(id) {
	var url = "${pageContext.request.contextPath}/learningPlan/task/delete/view?taskId="+id;
	 $.initWinOnTopFromLeft("注意！", url, '600', '350');
}

function searchTitle() {
	var title = $("#dxa_title").val();
	if(title!=null || ""!=title) {
		param["title"] = title;
	}
	listMyPublish(param);
}
</script>
</html>