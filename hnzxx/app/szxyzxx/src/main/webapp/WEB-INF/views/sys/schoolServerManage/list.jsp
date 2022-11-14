<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.id}_tr">
			<td>${item.name}</td>
			<td>${item.eduServer}</td>
			<td>${item.eduPort}</td>
			<td>${item.apiServer}</td>
			<td>${item.apiPort}</td>
		<td class="caozuo">
<%-- 			<button class="btn btn-green permission_check" type="button" onclick="loadViewerPage('${item.id}');">详情</button> --%>
<%-- 			<button class="btn btn-blue permission_update" type="button" onclick="loadEditPage('${item.id}');">编辑</button> --%>
			<button class="btn btn-red permission_del" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
		</td>
	</tr>
</c:forEach>
