<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />

<c:forEach items="${items}" var="item" varStatus="i">
	<tr id="${item.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td>
				<td>${item.teacherName}（${item.jobNumber != null && item.jobNumber !="" ? item.jobNumber : "无分配工号"}）</td>
				<td>${item.approver}</td>
				<td><jcgc:cache tableCode="JY-ZYJSZW" value="${item.titleType}"></jcgc:cache></td>
				<td><jcgc:cache tableCode="XY-JY-ZCQDFS" value="${item.acquireType}"></jcgc:cache></td>
	    		<td><fmt:formatDate value="${item.acquireDate}" pattern="yyyy/MM/dd" /></td>
		<td class="caozuo">
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}"> --%>
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}"> --%>
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
<%-- 			</c:if> --%>
		</td>
	</tr>
</c:forEach>
