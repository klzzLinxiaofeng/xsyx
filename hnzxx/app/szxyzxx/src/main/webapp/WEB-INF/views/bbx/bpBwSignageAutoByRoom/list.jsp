<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.roomId}_tr">
				<td>${item.roomName}</td>
				<td>${item.signageName}</td>
				<td>
					<c:if test="${item.pushSignageAuto == 'true'}">是</c:if>
					<c:if test="${item.pushSignageAuto == 'false'}">否</c:if>
				</td>
				<td><fmt:formatDate value="${item.mondayOpenTime}" pattern="HH:mm" />
				- <fmt:formatDate value="${item.mondayCloseTime}" pattern="HH:mm" /></td>
				<td><fmt:formatDate value="${item.tuesdayOpenTime}" pattern="HH:mm" />
				- <fmt:formatDate value="${item.tuesdayCloseTime}" pattern="HH:mm" /></td>
				<td><fmt:formatDate value="${item.wednesdayOpenTime}" pattern="HH:mm" />
				- <fmt:formatDate value="${item.wednesdayCloseTime}" pattern="HH:mm" /></td>
				<td><fmt:formatDate value="${item.thursdayOpenTime}" pattern="HH:mm" />
				- <fmt:formatDate value="${item.thursdayCloseTime}" pattern="HH:mm" /></td>
				<td><fmt:formatDate value="${item.fridayOpenTime}" pattern="HH:mm" />
				- <fmt:formatDate value="${item.fridayCloseTime}" pattern="HH:mm" /></td>
				<td><fmt:formatDate value="${item.saturdayOpenTime}" pattern="HH:mm" />
				- <fmt:formatDate value="${item.saturdayCloseTime}" pattern="HH:mm" /></td>
				<td><fmt:formatDate value="${item.sundayOpenTime}" pattern="HH:mm" />
				- <fmt:formatDate value="${item.sundayCloseTime}" pattern="HH:mm" /></td>

		<td class="caozuo">
			<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.roomId}');">编辑</button>
<%-- 			 </c:if> --%>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}"> --%>
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.roomId}');">删除</button>
<%-- 			</c:if>  --%>
		</td>
	</tr>
</c:forEach>
