<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<tr>
	<td style="padding: 0; border: 0 none;"><input type="hidden"
		id="currentPage" name="currentPage" value="${page.currentPage}" /> <input
		type="hidden" id="totalPages" name="totalPages"
		value="${page.totalPages}" /></td>
</tr>

<c:forEach items="${studentAwardVoList}" var="item" varStatus="i">
	<tr id="${item.id}_tr">
		<td>${i.index+1}</td>
		<td>${item.schoolYearName}</td>
		<td><jcgc:cache tableCode="JY-JLFS" value="${item.awardContent}"></jcgc:cache></td>
		<td><jcgc:cache tableCode="JY-XSHJLB" value="${item.awardLevel}"></jcgc:cache></td>
		<td>${item.awardRanking}</td>
		<td><jcgc:cache tableCode="JY-HJLX" value="${item.awardType}"></jcgc:cache></td>
		<td><fmt:formatDate value="${item.awardDay}" pattern="yyyy/MM/dd" /></td>
		<td>${item.awardUnit}</td>
	</tr>
</c:forEach>
