<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />

<c:forEach items="${dormitories}" var="dormitory" varStatus="i">
	<tr id="${dormitory.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td>
				<td>${dormitory.name}</td>
				<td>${dormitory.dormitoryCode}</td>
				<td> <jcgc:cache tableCode="GB-XB" value="${dormitory.sex}"></jcgc:cache></td>
				<td>${dormitory.capacity}</td>
				  
		<td class="caozuo" >
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${dormitory.id}');">查看</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${dormitory.id}');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${dormitory.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
