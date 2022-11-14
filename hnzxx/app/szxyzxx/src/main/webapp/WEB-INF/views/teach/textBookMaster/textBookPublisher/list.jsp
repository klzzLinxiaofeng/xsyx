<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.currentPage}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.id}_tr">
				<td>${item.id}</td>
				<td>${item.name}</td>
				<td>${item.shortName}</td>
				
	    		<td><fmt:formatDate value="${item.createTime}" pattern="yyyy/MM/dd" /></td>
				<td>${item.description}</td>
				<td><input type="text" value = '${item.sortOrder}' style="width:25px"></input></td>
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			</c:if>
			<button class="btn btn-blue" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
			
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			</c:if>
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
			</c:if>
				<button class="btn btn-blue" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
			
		</td>
	</tr>
</c:forEach>
