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
<c:forEach items="${list}" var="stu" varStatus="i">
	<tr id="${stu.stuId}_tr">
		<td>${i.index+1+(page.currentPage - 1) * 10}</td>
		<td>${stu.name}</td>
		<td>${stu.teamName}</td>
		<td>${date}</td>
		<td>${direction ==0 ? '上学':'放学'}</td>
		<td>${stu.stuStatus}</td>
		<td>${stu.busInfo.schoolBusCard}</td>
		<td>${stu.busInfo.routeNum}</td>
		<td>${stu.busInfo.schoolBusNumber}</td>
		<td>${stu.busInfo.status}</td>
		<td>
			<c:if test="${stu.leaveStartDate!=null}">
				${stu.leaveStartDate} 至 ${stu.leaveEndDate}
			</c:if>
		</td>
		<td>${stu.busInfo.driverName}</td>
		<td>${stu.busInfo.driverPhone}</td>
		<td>${stu.busInfo.caretakerName}</td>
		<td>${stu.busInfo.caretakerPhone}</td>
		<td>${stu.upTime}</td>
		<td>${stu.upPlace}</td>
		<td>${stu.downTime}</td>
		<td>${stu.downPlace}</td>
	</tr>
</c:forEach>
