<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<title>A2-0我接收的</title>
<style>
.nr_right table .caozuo .btn{z-index:1}
</style>
</head>
<body>
<jsp:include page="./header.jsp"></jsp:include>
<div class="content_main">
	<div class="ku_select">
		<div class="xdkm_div">
			<jsp:include page="/views/embedded/plugin/gradeWidget.jsp"></jsp:include>
			<div class="search_div">
				<label>搜索：</label>
				<div class="ss">
					<input type="text" placeholder="试卷标题" id="title"/>
					<a href="javascript:void(0)" class="btn-blue" onclick="searchByTitle();">搜索</a>
				</div>
			</div>
		</div>
	</div>
	<div class="neirong_zs">
		<div class="nr_right" style="margin-left:0;">
			<div class="dxa_list" style="margin-bottom:20px;" id="taskList">
				<jsp:include page="./lp_task_list.jsp"></jsp:include>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
</body>
<script>
var param = null;

$(function() {
	$(".qyzj_header .qyzj_right ul li a").eq(2).addClass("btn-blue");
	
	$.gradeWidget(0, function(data) {
		param = data;
		listMyTask(data);
	})
})


function listMyTask(data) {
	myPagination("taskList", data, "/learningPlan/task/list");
}

function searchByTitle() {
	var title = $("#title").val();
	if(title!=null || ""!=title) {
		param["title"] = title;
	}
	listMyTask(param);
}

//导学案预览
function preview(id) {
	var url = "${pageContext.request.contextPath}/learningPlan/edit?type=view&id="+id;
	window.open(url, "预览-${title}");
}

//导学案任务删除
function deleteTask(id) {
	$.confirm("确认删除该任务吗?", function() {
		var loader = new loadDialog();
        loader.show();
		$.ajax({
            url: "${pageContext.request.contextPath}/learningPlan/task/delete?id="+id,
            type: "DELETE",
            data: {},
            async: true,
            success: function(data) {
           		loader.close();
           		$.success("删除成功");
           		listMyTask(param);
            }
        });
	});
}

function activityDetail(id) {
	location.href="${pageContext.request.contextPath}/learningPlan/task/activity/index?taskId="+id;
}
</script>
</html>