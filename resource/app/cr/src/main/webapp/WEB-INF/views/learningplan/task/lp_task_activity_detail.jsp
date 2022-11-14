<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/qyjx/css/all.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<title>提交情况 </title>
<style>
html{background-color:#fff;}
</style>
</head>
<body>
	<div class="tjqk">
		<div class="tj_select">
			<a class="btn-green" href="javascript:void(0)">已提交（<span>${finishedCount}</span>）</a>
			<a class="btn-forbidGray" href="javascript:void(0)">未提交（<span>${unFinishedCount }</span>）</a>
		</div>
		<div class="tj_detail">
			<div class="tj_div">
				<ul>
					<c:forEach items="${finishedStudents }" var="student">	
						<li><a href="javascript:void(0)" onclick="studentDetail(${student.userId},'${student.studentName }')"><img src="<avatar:avatar userId='${student.userId}'></avatar:avatar>"><p>${student.studentName }</p></a></li>
					</c:forEach>		
				</ul>
				<div class="clear"></div>
			</div>
			<div class="tj_div" style="display:none;">
				<ul>
					<c:forEach items="${unfinishStudents }" var="student">	
						<li><a href="javascript:void(0)"><img src="<avatar:avatar userId='${student.userId}'></avatar:avatar>"><p>${student.studentName }</p></a></li>
					</c:forEach>
				</ul>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
<script>
$(function(){
	$(".tj_div").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
	$(".tjqk .tj_select a").click(function(){
		$(".tjqk .tj_select a").removeClass("btn-green").addClass("btn-forbidGray");
		$(this).addClass("btn-green").removeClass("btn-forbidGray");
		var i=$(this).index();
		$(".tj_div").hide();
		$(".tj_div").eq(i).show();
	})
})

function studentDetail(userId, studentName) {
	var unitId = "${unitId}";
	var taskId = "${taskId}";
	window.parent.getUserActivityList(taskId, unitId, userId, studentName);
	$.closeWindow();
}
</script>
</html>