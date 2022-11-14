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
				<td>
				<c:if test="${!empty item.headUrl}">
				<img style="height:28px;" src="${item.headUrl}"/>
				</c:if>
				</td>
				<td>${item.name}</td>
				<%-- <td>${item.roleCode}</td> --%>
	    		<%-- <td><fmt:formatDate value="${item.joinDate}" pattern="yyyy/MM/dd" /></td>
	    		<td><fmt:formatDate value="${item.leaveDate}" pattern="yyyy/MM/dd" /></td> --%>
				<td>
				<c:choose>
				<c:when test="${item.status==0}">正常</c:when>
				<c:when test="${item.status==1}">删除</c:when>
				<c:otherwise>禁言</c:otherwise>
				</c:choose>
				</td>
				<td>
				<c:choose>
				<c:when test="${item.isAdmin==0}">否</c:when>
				<c:otherwise>是</c:otherwise>
				</c:choose>
				</td>
		
		<td class="caozuo">
			<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
			</c:if> --%>
			<c:if test="${item.status==0}"><button class="btn btn-gray" type="button" onclick="changeStatus('${item.id}', 2);">禁言</button></c:if>
			<c:if test="${item.status==2}"><button class="btn btn-green" type="button" onclick="changeStatus('${item.id}', 0);">解禁</button></c:if>
			<c:if test="${circleType==3}"><button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}', '${item.circleId}');">删除</button></c:if>
		</td>
	</tr>
</c:forEach>
