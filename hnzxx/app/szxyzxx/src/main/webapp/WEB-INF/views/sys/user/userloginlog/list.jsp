<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${items}" var="item" varStatus="status">
	<tr id="${item.id}_tr">
				<td>${status.index + 1}</td>
				<td>${item.name}</td>
	    		<td><fmt:formatDate value="${item.lastLoginTime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
				<td>${item.lastIp}</td>
	    		<td><fmt:formatDate value="${item.curLoginTime}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
				<td>${item.curIp}</td>
		
		
	</tr>
</c:forEach>
