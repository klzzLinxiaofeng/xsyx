<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>

<tr style="display:none">
	<td><input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>

<tr style="display:none">
	<td><input type="hidden" id="ydxCount"  value="${ydxCount}" />
		<input type="hidden" id="wdxCount"  value="${wdxCount}" />
		<input type="hidden" id="ycxCount"  value="${ycxCount}" />
		<input type="hidden" id="wcxCount"  value="${wcxCount}" />
	</td>
</tr>

<c:forEach items="${list}" var="stu" varStatus="i">
	<tr id="${stu.stuId}_tr">
		<td>${i.index+1+(page.currentPage - 1) * 10}</td>
		<td>${date}</td>
		<td>${stu.name}</td>
		<td>${stu.teamName}</td>

		<td>${stu.inStatus}</td>
		<td>${stu.goToType}</td>
		<td>${stu.outStatus}</td>
		<td>${stu.outType}</td>

	</tr>
</c:forEach>
