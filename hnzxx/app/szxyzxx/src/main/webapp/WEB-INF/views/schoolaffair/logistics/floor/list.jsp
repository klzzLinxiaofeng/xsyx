<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<input type="hidden" id="currentPage" name="currentPage"
	value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages"
	value="${page.totalPages}" />
<c:forEach items="${floorList}" var="floor" varStatus="i">
	<tr id="${floor.id}_tr">
		<td>${i.index+1+(page.currentPage - 1) * 10}</td>
		<td>${floor.name}</td>
		<td>${floor.code}</td>
		<td><jcgc:cache tableCode="JY-JZWYT" value="${floor.type}" /></td>
		<td>${floor.layerNumber}</td>
		<!-- <td>${floor.schoolId}</td> -->
		<td><jcgc:cache tableCode="JY-FJSYZT" value="${floor.state}" /></td>
		<td><fmt:formatDate value='${floor.createDate}' pattern='yyyy-MM-dd'/></td>
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
				<button class="btn btn-green" type="button"
					onclick="loadEditPage('${floor.id}','disable');">详情</button>
			</c:if> 
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<button class="btn btn-blue" type="button"
					onclick="loadEditPage('${floor.id}','');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button"
					onclick="deleteObj(this, '${floor.id}');">删除</button>
			</c:if></td>
	</tr>
</c:forEach>
