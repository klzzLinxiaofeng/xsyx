<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.userId}_tr">
		<td>${item.name}</td>
		<td>${item.lateNum}</td>
		<td>${item.outEarlyNum}</td>
		<td>${item.absentNum}</td>
		<td>${item.leaveNum}</td>
		<td>${item.total}</td>
		<td class="caozuo">
			<button class="btn btn-blue" type="button" onclick="loadViewPage('${item.userId}', 1);">迟到情况</button>
			<button class="btn btn-blue" type="button" onclick="loadViewPage('${item.userId}', 2);">早退情况</button>
			<button class="btn btn-blue" type="button" onclick="loadViewPage('${item.userId}', 3);">缺勤情况</button>
		</td>		
	</tr>
</c:forEach>
