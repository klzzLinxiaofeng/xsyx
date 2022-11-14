<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<input type="hidden" id="currentPage" name="currentPage"
	value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages"
	value="${page.totalPages}" />
<c:forEach items="${classroomVoList}" var="classroom" varStatus="i">
	<tr id="${classroom.id}_tr">
		<td>${i.index+1+(page.currentPage - 1) * 10}</td>
		<td>${classroom.name}</td>
		<td>${classroom.code}</td>
		<td><jcgc:cache tableCode="JY-JSLX" value="${classroom.type}" /></td>
		<td><c:choose>
				<c:when test="${classroom.state == 0}">可用</c:when>
				<c:when test="${classroom.state == 1}">不可用</c:when>
				<c:otherwise></c:otherwise>
			</c:choose></td>
		<td>
			<c:choose>
				<c:when test="${classroom.floorName == '0'}"><span style="color:red">该大楼已废弃</span></c:when>
				<c:otherwise>${classroom.floorName }</c:otherwise>
			</c:choose>
		</td>
		<td>${classroom.storey }</td>
		<td><fmt:formatDate value='${classroom.createDate}' pattern='yyyy-MM-dd'/></td>
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button"
				onclick="loadEditPage('${classroom.id}','disable');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button"
				onclick="loadEditPage('${classroom.id}','');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
			<button class="btn btn-red" type="button"
				onclick="deleteObj(this, '${classroom.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
