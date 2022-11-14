<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.currentPage}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item" varStatus="count">
	<tr id="${item.id}_tr">
				<td>${count.count}</td>
				<td>${item.cardNumber}</td>
				<td><dota:fieldVal type="teacher" code="${item.proposer}"></dota:fieldVal></td>
	    		<td><fmt:formatDate value="${item.returnDate}" pattern="yyyy/MM/dd" /></td>
				<td>${item.returnExplain}</td>
				<td>
					<c:if test="${item.returnStatus=='2'}"><span style="color: red;">归还中</span></c:if>
					<c:if test="${item.returnStatus=='3'}"><span style="color: green;">已归还</span></c:if>
				</td>
		<td class="caozuo">
				<button class="btn btn-blue" type="button"
				 <c:if test="${item.returnStatus=='3'}">
				 disabled="disabled"</c:if>
				  onclick="loadAuditPage('${item.id}','${item.returnStatus}');">审批</button>
		</td>
	</tr>
</c:forEach>
