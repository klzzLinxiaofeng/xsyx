<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<tr>
	<td style="padding: 0; border: 0 none;" colspan="6">
	<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" /> 
	<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" /></td>
</tr>
<c:forEach items="${moralEvaluationStudentVos}" var="mesvo" varStatus="i">
	<tr id="${mesvo.id}_tr">
		<td>${i.index+1+(page.currentPage - 1) * 10}</td>
		<td>${mesvo.schoolYearName}</td>
		<td>${mesvo.gradeName}</td>
		<td>${mesvo.teamName}</td>
		<td>${mesvo.studentName}</td>
		<td>
			<c:choose>
				<c:when test="${mesvo.totalEvaluation == 1}">优秀</c:when>
				<c:when test="${mesvo.totalEvaluation == 2}">良好</c:when>
				<c:when test="${mesvo.totalEvaluation == 3}">合格</c:when>
				<c:when test="${mesvo.totalEvaluation == 4}">不合格</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</td>
		<td title="${mesvo.remark }">
					<c:choose>
						<c:when test="${fn:length(mesvo.remark)>10 }"><c:out value="${fn:substring(mesvo.remark, 0, 15) }..."></c:out></c:when>
						<c:otherwise>${mesvo.remark }</c:otherwise>
					</c:choose>
		</td>

		<td class="caozuo"><c:if
				test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
				<button class="btn btn-green" type="button"
					onclick="loadViewerPage('${mesvo.id}');">详情</button>
			</c:if> <c:if
				test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
				<button class="btn btn-blue" type="button"
					onclick="loadEditPage('${mesvo.id}');">编辑</button>
			</c:if> <c:if
				test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<button class="btn btn-red" type="button"
					onclick="deleteObj(this, '${mesvo.id}');">删除</button>
			</c:if></td>
	</tr>
</c:forEach>
