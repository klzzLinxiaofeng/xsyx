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
	<tr id="${item.gradeId}_tr">
				<td>${item.gradeName}</td>
				<td>
					<fmt:formatDate value="${item.lateTime}" pattern="HH:mm:ss" />
				</td>
	    		<td><fmt:formatDate value="${item.outEarlyTime}" pattern="HH:mm:ss" /></td>
		
		<td class="caozuo">
			<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.gradeId}');">编辑</button>
			<%-- </c:if> --%>
			<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.gradeId}');">删除</button>
			
		</td>
	</tr>
</c:forEach>
