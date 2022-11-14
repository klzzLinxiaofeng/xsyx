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
	<tr id="${item.id}_tr">
				<td>${item.studentNumber}</td>
				<td>${item.name}</td>
				<td>${item.sex}</td>
				<td>${item.mobile}</td>
				<td>${item.company}</td>
				<td>${item.employAddress}</td> 
	    		<td>${item.enrollDate}</td>
	    		<td>${item.employDate}</td>
		
		<td class="caozuo">
			<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
			</c:if> --%>
			<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			<%-- </c:if> --%>
			<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}"> --%>
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
			<%-- </c:if> --%>
		</td>
	</tr>
</c:forEach>
