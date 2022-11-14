<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${teamStudentVos}" var="teamStudent" varStatus="i">
	<tr id="${teamStudent.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td>
				<td>${teamStudent.userName }</td>
				<td>${teamStudent.studentNumber }</td>
				<td>${teamStudent.name}</td>
				<td><jcgc:cache tableCode="GB-XB" value="${teamStudent.sex }" /></td>
				<td><c:if test="${empty teamStudent.mobile }">- - -</c:if>${teamStudent.mobile }</td>
				<td><c:if test="${empty teamStudent.position }">- - -</c:if>${teamStudent.position}</td>
				<td><c:if test="${empty teamStudent.parentList[0].name }">- - -</c:if>${teamStudent.parentList[0].name }</td>
				<td><c:if test="${empty teamStudent.parentList[0].mobile }">- - -</c:if>${teamStudent.parentList[0].mobile }</td>
		
<!-- 		<td class="caozuo"> -->
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}"> --%>
<%-- 			<button class="btn btn-green" type="button" onclick="loadViewerPage('${teamStudent.id}');">详情</button> --%>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
<%-- 			<button class="btn btn-blue" type="button" onclick="loadEditPage('${teamStudent.id}');">编辑</button> --%>
<%-- 			</c:if> --%>
<%-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}"> --%>
<%-- 				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${teamStudent.id}');">删除</button> --%>
<%-- 			</c:if> --%>
<!-- 		</td> -->
	</tr>
</c:forEach>
