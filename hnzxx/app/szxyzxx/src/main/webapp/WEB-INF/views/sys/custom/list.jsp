<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${pjPermissions}" var="pjPermission">
	<tr>
		<td>${pjPermission.code }</td>
		<td>${pjPermission.name }</td>
		<td>${pjPermission.accessUrl }</td>
		<td class="caozuo">
				<button class="btn btn-blue" type="button" onclick="loadEditPage('${pjPermission.id}');">修改路径</button>
		</td>
	</tr>
</c:forEach>
