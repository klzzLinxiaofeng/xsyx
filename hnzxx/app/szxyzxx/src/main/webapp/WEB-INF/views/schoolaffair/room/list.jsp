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
				<td>${item.code}</td>
				<td>${item.name}</td>
				<td>${item.roomName}</td>
				<td>
					<c:choose>
						<c:when test="${room.roomUse == 1 }">教室</c:when>
						<c:when test="${room.roomUse == 2 }">实验室</c:when>
						<c:when test="${room.roomUse == 3 }">物理实验室</c:when>
						<c:when test="${room.roomUse == 4 }">生物实验室</c:when>
						<c:otherwise>
							其他实验室
						</c:otherwise>
					</c:choose>
				</td>
				<td>${item.roomFloor}</td>
				<td>${item.roomArea}</td>
				<td>${item.roomUseArea}</td>
				<td>${item.remark}</td>
		
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
