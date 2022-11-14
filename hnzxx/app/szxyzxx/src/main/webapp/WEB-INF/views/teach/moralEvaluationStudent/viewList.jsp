<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<tr>
	<td style="padding: 0; border: 0 none;" colspan="6"><input type="hidden"
		id="currentPage" name="currentPage" value="${page.currentPage}" /> <input
		type="hidden" id="totalPages" name="totalPages"
		value="${page.currentPage}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" /></td>
</tr>
<c:forEach items="${studentVoList}" var="vo" varStatus="i">
	<tr id="${vo.id}_tr">
		<td>${i.index+1}</td>
		<td>${vo.schoolYearName}</td>
		<td>
			<c:choose>
				<c:when test="${vo.totalEvaluation == 1}">优秀</c:when>
				<c:when test="${vo.totalEvaluation == 2}">良好</c:when>
				<c:when test="${vo.totalEvaluation == 3}">合格</c:when>
				<c:when test="${vo.totalEvaluation == 4}">不合格</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</td>
		<td title="${vo.remark }">
			<c:choose>
				<c:when test="${fn:length(vo.remark)>10 }"><c:out value="${fn:substring(vo.remark, 0, 15) }..."></c:out></c:when>
				<c:otherwise>${vo.remark }</c:otherwise>
			</c:choose>
		</td>
	</tr>
</c:forEach>
