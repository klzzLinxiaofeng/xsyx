<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dxa/dxa.css" rel="stylesheet">
<title>备忘录</title>
<style>
	.row-fluid .span4 {
	width: 227px;
}
</style>
</head>
<body style="background-color:f3f3f3 !important">
	<div class="xstjqk">
		<div class="ytj_list">
			<p class="title">已提交（${fn:length(finishedStudents) }）</p>
			<ul>
				<c:forEach items="${finishedStudents }" var="finishedStudent">
					<li>
						<img alt="头像" src="<avatar:avatar userId='${finishedStudent.userId}'></avatar:avatar>">
						<p>${finishedStudent.studentName}</p>
					</li>
				</c:forEach>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="wtj_list">
			<p class="title">未提交（${fn:length(unfinishStudents) }）</p>
			<ul>
				<c:forEach items="${unfinishStudents }" var="unfinishStudent">
					<li>
						<img alt="头像" src="<avatar:avatar userId='${unfinishStudent.userId}'></avatar:avatar>">
						<p>${unfinishStudent.studentName}</p>
					</li>
				</c:forEach>
			</ul>
			<div class="clear"></div>
		</div>
	</div>
</body>
</html>