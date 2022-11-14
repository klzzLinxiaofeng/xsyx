<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none; display: none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${teacherList}" var="teacher" varStatus="index">
	<tr>
			<td style="width: 50px;">${index.count + (page.currentPage-1) * page.pageSize}</td>
			<td>${teacher.name}</td>
			<td>${teacher.userName}</td>
			<td>${teacher.gradeName}</td>
			<td><jcgc:cache tableCode="GB-XB" value="${teacher.sex}"></jcgc:cache></td>
			<td style="text-align: center;">${teacher.dutyDayCount}</td>
			<td style="color: red;text-align: center;">${teacher.finishedDayCount}</td>

		<td style="text-align: center;">
			<a href="javascript:void(0)" onclick="DetailPage('${teacher.teacherId}', '${teacher.gradeId}', '${week}', '${month}');">查看明细</a>
		</td>
	</tr>
</c:forEach>
