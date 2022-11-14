<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${taskItems}" var="item" varStatus="sta">
	<tr id="${item.id}_tr">
<%-- 		<td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></td> --%>
		<td>${sta.index + 1 + (page.currentPage-1)*10}</td>
		<td>${item.judgeName}</td>
		<td>${item.name}</td>
		<td>${item.score}</td>
   		<td>${item.description}</td>
		<td class="caozuo">
<%-- 			<c:if test="${edit}"> --%>
			<button class="btn btn-warning" type="button" onclick="bonusEdit('${item.id}');">考核</button>
<%-- 			</c:if> --%>
			<button class="btn btn-green" type="button" onclick="checkView('${item.id}');">详情</button>
		</td>
	</tr>
</c:forEach>
