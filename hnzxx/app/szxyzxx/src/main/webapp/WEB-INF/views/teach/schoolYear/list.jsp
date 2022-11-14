<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<tr style="display:none">
	<td><input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${schoolYearList}" var="schoolYear" varStatus="i">
	<tr id="${schoolYear.id}_tr">
		<td>${i.index+1+(page.currentPage - 1) * 10}</td>
		<td>${schoolYear.name}</td>
<%-- 		<td><a href="javascript:void(0);" onclick="schoolTerm('${schoolYear.year}');">${schoolYear.name}</a></td> --%>
		<td><fmt:formatDate value='${schoolYear.beginDate}' pattern='yyyy-MM-dd'/></td>
		<td><fmt:formatDate value='${schoolYear.finishDate}' pattern='yyyy-MM-dd'/></td>
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
				<button class="btn btn-green" type="button" onclick="loadEditPage('${schoolYear.id}','disable');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<button class="btn btn-blue" type="button" onclick="loadEditPage('${schoolYear.id}','');">编辑</button>
			</c:if>
<!-- 			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}"> -->
<!-- 				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${schoolYear.id}');">删除</button> -->
<!-- 			</c:if> -->
		</td>
	</tr>
</c:forEach>
