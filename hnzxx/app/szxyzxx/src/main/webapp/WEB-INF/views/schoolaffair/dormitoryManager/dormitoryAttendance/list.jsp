<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
<c:forEach items="${dAttendanceVo}" var="dvs" varStatus="i">
	<tr id="${dvs.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td>
				<td><fmt:formatDate value="${dvs.createDate}" pattern="yyyy-MM-dd" /></td>
				<td><jcgc:cache tableCode="XY-YH-KQLX" value="${dvs.attendanceType}"></jcgc:cache></td>
				<td>${dvs.floorName}</td>
				<td>${dvs.dormitoryCode}</td>
				<td>${dvs.teamName}</td>
				<td>${dvs.studentName}</td>
				<td>${dvs.studentNumber}</td>
		  
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${dvs.id}');">查看</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${dvs.id}');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${dvs.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
