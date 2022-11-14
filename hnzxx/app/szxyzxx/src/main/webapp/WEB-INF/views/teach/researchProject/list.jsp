<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${researchProjectList}" var="researchProject" varStatus="i">
	<tr id="${researchProject.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td> 
				<td>${researchProject.code}</td>
				<td>${researchProject.name}</td>
				<td><p class="people_name" >${researchProject.masterName}</p></td>
				<td><p class="people_name" >${researchProject.attendeesName}</p></td>
	    		<td><fmt:formatDate value="${researchProject.beginDate}" pattern="yyyy-MM-dd" /></td>
	    		<td><fmt:formatDate value="${researchProject.endDate}" pattern="yyyy-MM-dd" /></td>
				<td>${researchProject.prize}</td>
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${researchProject.id}');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${researchProject.id}');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${researchProject.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
