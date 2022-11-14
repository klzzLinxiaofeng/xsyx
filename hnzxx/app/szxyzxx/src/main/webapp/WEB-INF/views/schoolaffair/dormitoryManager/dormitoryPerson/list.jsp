<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
	<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
	<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
<c:forEach items="${dormitoryPersonvo}" var="dps" varStatus="i" >
	<tr id="${dps.dormitoryCode}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td>
				<td>${dps.floorName}</td>
				<td>${dps.dormitoryCode}</td>
				<td> <jcgc:cache tableCode="GB-XB" value="${dps.sex}"></jcgc:cache></td>
				<td>${dps.personCount}/${dps.capacity}</td>
				<td>${dps.studentNames }</td>
		
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${dps.dormitoryId}');">查看</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${dps.id}');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteAll(this, '${dps.schoolYearId}','${dps.dormitoryId }');">清除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
