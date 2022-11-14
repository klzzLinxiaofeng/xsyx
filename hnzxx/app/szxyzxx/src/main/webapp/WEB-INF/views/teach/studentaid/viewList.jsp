<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<tr>
	<td style="padding: 0; border: 0 none;"><input type="hidden"
												   id="currentPage" name="currentPage" value="${page.currentPage}" /> <input
			type="hidden" id="totalPages" name="totalPages"
			value="${page.totalPages}" /></td>
</tr>

<c:forEach items="${studentAidVoList}" var="item" varStatus="i">
	<tr id="${item.id}_tr">
		<td>${i.index+1}</td>
		<td>${item.schoolYearName}</td>
		<td><jcgc:cache tableCode="JY-KNCD" value="${item.povertyCategory}"></jcgc:cache></td>
		<td><jcgc:cache tableCode="JY-KNYY" value="${item.povertyCauses}"></jcgc:cache></td>
		<td>${item.aidForm}</td>
		<td>${item.oneIncome}</td>
		<td>${item.aidAmount}</td>
		<td><fmt:formatDate value="${item.aidDay}" pattern="yyyy/MM/dd" /></td>
	</tr>
</c:forEach>
