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
		<td>${item.id}</td>
		<td>${item.name}</td>
		<td>${item.schoolName}</td>
		<td>${item.roomTypeName}</td>
		<td>${item.roomName}</td>
		<td>
			<c:choose>
				<c:when test="${item.usingState == 1}">是</c:when>
				<c:when test="${item.usingState == 0}">否</c:when>
			</c:choose>
		</td>
		<td>${item.num }</td>
			<%-- <td><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd" /></td>
            <td><fmt:formatDate value="${item.modifyDate}" pattern="yyyy/MM/dd" /></td>	 --%>
		<td class="caozuo">
				<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
                            <button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
                            </c:if>
                            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
                            <button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
                            </c:if> --%>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
