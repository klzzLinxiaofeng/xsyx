<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<tr style="display: none">
	<td><input type="hidden" id="currentPage" name="currentPage"
		value="${page.currentPage}" /> <input type="hidden" id="totalPages"
		name="totalPages" value="${page.totalPages}" /></td>
</tr>
<c:forEach items="${studentPunishVoList}" var="item" varStatus="i">
	<tr id="${item.id}_tr">
		<td>${i.index+1}</td>
		<td>${item.schoolYearName}</td>
		<td>${item.punishType}</td>
		<td><jcgc:cache tableCode="JY-WJLB" value="${item.punishCause}" /></td>
		<td><fmt:formatDate value="${item.punishDay}" pattern="yyyy/MM/dd" /></td>
		<td><fmt:formatDate value="${item.punishEndDay}" pattern="yyyy/MM/dd" /></td>
		<td><fmt:formatDate value="${item.repealDay}" pattern="yyyy/MM/dd" /></td>
		<c:choose>
			<c:when test="${item.isRepeal}">
				<td>是</td>
			</c:when>
			<c:otherwise>
				<td>否</td>
			</c:otherwise>
		</c:choose>
	</tr>
</c:forEach>
