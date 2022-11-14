<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${voList}" var="vo" varStatus="i">
	<tr id="${vo.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td>       <!--序号  -->
				<td>${vo.name}</td>
				<td>${vo.hasPhotoTeacher}</td>
				<td>${vo.noPhotoTeacher}</td>
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button"
				onclick="loadPage('${vo.id}');">详情</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
