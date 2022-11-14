<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@include file="/WEB-INF/views/common/permission.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${dataItems}" var="appRelease" varStatus="status">
	<tr id="${appRelease.id}_tr">
		<tr>
			<td>${status.index+1}</td>
			<td>${appRelease.name }</td>
			<td>${appRelease.version }</td>
			<td><fmt:formatDate value="${appRelease.releaseDate }" pattern="yyyy-MM-dd" /></td>
			<c:choose>
				<c:when test="${appRelease.isCurrent eq true}">
					<td style="color:red;">是</td>
				</c:when>
				<c:otherwise>
					<td>否</td>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${appRelease.isForce eq true}">
					<td style="color:red;">是</td>
				</c:when>
				<c:otherwise>
					<td>否</td>
				</c:otherwise>
			</c:choose>
		<td class="caozuo">
			<button class="btn btn-green permission_add" type="button" onclick="loadEditPage('${appRelease.id}');">编辑</button>
			<button class="btn btn-blue permission_update" type="button" onclick="setverison('${appRelease.id}');">设置为当前版本</button>
			<c:choose>
				<c:when test="${appRelease.isForce eq true}">
					<button class="btn btn-red permission_del" type="button" onclick="removeForce('${appRelease.id}');">取消强制更新</button>
				</c:when>
				<c:otherwise>
					<button class="btn btn-red permission_del" type="button" onclick="setForce('${appRelease.id}');">设置强制更新</button>
				</c:otherwise>
			</c:choose>
			<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${appRelease.id}');">删除</button>
			</c:if> --%>
		</td>
	</tr>
</c:forEach>
