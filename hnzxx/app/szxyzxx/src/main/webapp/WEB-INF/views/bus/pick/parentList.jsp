<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<tr style="display:none">
	<td><input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${list}" var="s" varStatus="i">
	<tr id="${order.id}_tr">
		<td>${i.index+1+(page.currentPage - 1) * 10}</td>
		<td>${date}</td>
		<td>${s.team_name}</td>
		<td>${s.name}</td>
		<td>${s.pname}</td>
		<td>${s.mobile}</td>
		<td>${s.status}</td>
		<td>${s.place}</td>

		<td>${s.gateInfo.doorName}</td>
		<td>${s.gateInfo.eventTime}</td>
		<td>
			<c:if test="${s.begin_date!=null}">
				<fmt:formatDate value="${s.begin_date}" pattern='yyyy-MM-dd HH:mm'/>&nbsp;至&nbsp;<fmt:formatDate value="${s.end_date}" pattern='yyyy-MM-dd HH:mm'/>
			</c:if>
		</td>
	</tr>
</c:forEach>
