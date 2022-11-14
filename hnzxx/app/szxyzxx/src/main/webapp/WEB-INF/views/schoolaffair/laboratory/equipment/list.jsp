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
				<td style="display: none;">${item.id}</td>
				<td style="display: none;">${item.schoolId}</td>
				<td>${item.name}</td>
				<td>${item.code}</td>
				<td>${item.floorName}</td>
				<td>${item.roomName}</td>
				<td>${item.roomCode }</td>
				<td>${item.roomPosition}</td>
				<td>${item.brand}</td>
				<td>${item.type}</td>
				<td>${item.specifications}</td>
				<td>${item.remark}</td>
	    		<td><fmt:formatDate value="${item.boughtTime}" pattern="yyyy/MM/dd" /></td>
				<td style="display: none;">${item.isDelete}</td>
				<td>
					<c:if test="${item.isNotice == true}">是</c:if>
					<%-- <c:if test="${item.isNotice == false}">否</c:if> --%>
				</td>
	    		<td><fmt:formatDate value="${item.preRepariTime}" pattern="yyyy/MM/dd" /></td>
				<td>
					<c:if test="${item.status == true}">正常</c:if>
					<c:if test="${item.status == false}">故障</c:if>
				</td>
				<td>${item.cause}</td>
	    		<td style="display: none;"><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd" /></td>
	    		<td style="display: none;"><fmt:formatDate value="${item.modifyDate}" pattern="yyyy/MM/dd" /></td>
		
		<td class="caozuo">
			<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
			</c:if> --%>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
