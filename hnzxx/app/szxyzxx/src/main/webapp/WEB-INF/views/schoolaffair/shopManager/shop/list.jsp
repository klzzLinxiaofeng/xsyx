<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${shops}" var="shop" varStatus="i">
	<tr id="${shop.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}</td>        <!--序号  -->
				<td>${shop.code}</td>
				<td>${shop.name}</td>
				<td>${shop.leader}</td>
				<td>${shop.runTime}</td>
				<td>${shop.floorName}</td>		
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button"
				onclick="loadEditPage('${shop.id}','disable');">详情</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button"
				onclick="loadEditPage('${shop.id}','');">编辑</button>
			</c:if>
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
			<button class="btn btn-red" type="button"
				onclick="deleteObj(this, '${shop.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
