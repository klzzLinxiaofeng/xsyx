<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
<c:forEach items="${checks}" var="check" varStatus="i">
	<tr id="${check.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd "
					value="${check.createDate}"></fmt:formatDate></td>
				<td> <jcgc:cache tableCode="XY-YH-RCJCLX" value="${check.checkType}"></jcgc:cache>
				</td>
				<td>${check.floorName}</td>
				<td>${check.dormitoryCode}</td>
				<td>${check.checkResult}</td>
				<td>${check.remark}</td>  
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${check.id}');">查看</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${check.id}');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${check.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
