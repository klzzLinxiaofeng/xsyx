<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<thead><tr><th>班内学号</th><th>姓名</th><th>成绩</th><th>等级</th><th>班级名次</th><th>年级名次</th></tr></thead>
<tbody>
	<c:forEach items="${examStudentList }" var="examStudent" varStatus="i">
		<tr><td>${examStudent.number }</td><td>${examStudent.name }</td><td><c:choose><c:when test="${examStudent.score==-1 }">0</c:when><c:otherwise>${examStudent.score }</c:otherwise></c:choose></td><td>${examStudent.degree }</td><td>${examStudent.teamRank }</td><td>${examStudent.gradeRank }</td></tr>
	</c:forEach>
</tbody>
<script>
	$(function(){
		
		/* $("thead th").eq(2).remove();
		
		$("tbody tr").each(function(){
			$(this).children().eq(2).remove();
		}) */
	})
</script>