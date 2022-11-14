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
				<%-- <td>
				<c:choose>
				<c:when test="${item.objectType==1}">
				班级群
				</c:when>
				<c:when test="${item.objectType==2}">
				部门群
				</c:when>
				<c:otherwise>
				自建群
				</c:otherwise>
				</c:choose>
				</td> --%>
				<td>${item.createUserName}</td>
				<%-- <td>${item.adminUserName}</td> --%>
				<td>
				<c:choose>
				<c:when test="${item.status==0}">已开通</c:when>
				<c:otherwise>未开通</c:otherwise>
				</c:choose>
				</td>
				<%-- <td>${item.memberCount}</td> --%>
		
		<td class="caozuo">
			<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
			</c:if> --%>
			<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			</c:if>
			<button class="btn btn-green" type="button" onclick="loadMemberPage('${item.id}', '${item.objectType}', '${item.objectId}');">群成员</button>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
			</c:if> --%>
			<c:choose>
				<c:when test="${item.status==0}"><button class="btn btn-red" type="button" onclick="changeStatus(0, '${item.objectId}');">关闭</button></c:when>
				<c:otherwise><button class="btn btn-green" type="button" onclick="changeStatus(1, '${item.objectId}');">开通</button></c:otherwise>
			</c:choose>
			
		</td>
	</tr>
</c:forEach>
