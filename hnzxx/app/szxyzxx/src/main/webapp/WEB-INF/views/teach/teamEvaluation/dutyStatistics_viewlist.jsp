<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none; display: none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${list}" var="teacher" varStatus="index">
	<tr>
		<td>${index.count }</td>
		<td>${name}</td>
		<td>${teacher.week}·${teacher.dayOfWeek}</td>
		<c:choose>
			<c:when test="${teacher.isFinished eq true }">
				<td>${teacher.week}·${teacher.dayOfWeek} <fmt:formatDate value="${teacher.modifyDate}" type="both" /></td>
			</c:when>
			<c:otherwise>
				<td></td>
			</c:otherwise>
		</c:choose>
	</tr>
</c:forEach>
