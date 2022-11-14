<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
<c:forEach items="${items}" var="item" varStatus="sta">
	<tr id="${item.id}_tr">
				<td>${sta.index + 1 + (page.currentPage-1)*10}</td>
				<td>${item.ruleName}</td>
				<td>
					<c:choose>
						<c:when test="${item.checkType == '01'}">日常</c:when>
						<c:when test="${item.checkType == '02'}">加分</c:when>
						<c:when test="${item.checkType == '03'}">减分</c:when>
						<c:otherwise>其他</c:otherwise>					
					</c:choose>				
				</td>
				<td>${item.category}</td>
				<td>${item.name}</td>
				<td>${item.score}</td>
				<td><div title="${item.description}"  class="description">${item.description}</div></td>
<!-- 		<td class="caozuo"> -->
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}"> --%>
<%-- 			<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button> --%>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
<%-- 			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button> --%>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}"> --%>
<%-- 				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button> --%>
<%-- 			</c:if> --%>
<!-- 		</td> -->
	</tr>
</c:forEach>
