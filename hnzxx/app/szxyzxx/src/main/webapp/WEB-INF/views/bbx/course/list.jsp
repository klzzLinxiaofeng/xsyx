<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.id}_tr">
				<td>${item.name}</td>
<%-- 				<td>${item.hours}</td> --%>
				<td>${item.classHourWeek}</td>
				<%-- <td>${item.planCount}</td> --%>
		
		<td class="caozuo">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			<c:if test="${flag == true }">
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
			</c:if>
		</td>
	</tr>
</c:forEach>
