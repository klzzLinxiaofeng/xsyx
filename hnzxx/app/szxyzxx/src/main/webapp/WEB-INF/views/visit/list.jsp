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
<c:forEach items="${list}" var="item" varStatus="i">
	<tr id="${item.id}_tr">
		<td>${i.index+1+(page.currentPage - 1) * 10}</td>
		<td>${item.applicant_name}</td>
		<td><fmt:formatDate value='${item.create_date}' pattern='yyyy-MM-dd HH:mm:ss'/></td>


		<td>${item.visit_user_name}</td>

		<td><fmt:formatDate value='${item.visit_date}' pattern='yyyy-MM-dd'/></td>

		<td>${item.purpose}</td>

		<c:choose>
			<c:when test="${item.visit_status==0}">
				<td>待被访人审批</td>
			</c:when>
			<c:when test="${item.visit_status==1}">
				<td>待管理员审批</td>
			</c:when>
			<c:when test="${item.visit_status==2}">
				<td style="color: red">被访人拒绝</td>
			</c:when>
			<c:when test="${item.visit_status==3}">
				<td style="color: #a25ea2">待入校</td>
			</c:when>
			<c:when test="${item.visit_status==4}">
				<td style="color: red">管理员拒绝</td>
			</c:when>
			<c:when test="${item.visit_status==5}">
				<td style="color: green">已入校</td>
			</c:when>
			<c:when test="${item.visit_status==6}">
				<td style="color: red">已失效</td>
			</c:when>
			<c:otherwise>
				<td>未知</td>
			</c:otherwise>
		</c:choose>
	</tr>
</c:forEach>
