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
<c:forEach items="${list}" var="order" varStatus="i">
	<tr id="${order.id}_tr">
		<td>${i.index+1+(page.currentPage - 1) * 10}</td>
		<td>${order.emp_name}</td>
		<td>${order.emp_card}</td>
		<c:choose>
			<c:when test="${order.is_payed==1}">
				<td style="color: green">成功</td>
			</c:when>
			<c:when test="${order.is_payed==2}">
				<td style="color: red">失败</td>
			</c:when>
			<c:when test="${order.is_payed==0}">
				<td style="color: #a25ea2">未支付</td>
			</c:when>
			<c:otherwise>
				<td>未知</td>
			</c:otherwise>
		</c:choose>

		<td>${order.pay_amount}</td>

		<c:choose>
			<c:when test="${order.is_send_order==1}">
				<td style="color: green">是</td>
			</c:when>
			<c:otherwise>
				<td style="color: red">否</td>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${order.pay_type==1}">
				<td>微信支付</td>
			</c:when>
			<c:otherwise>
				<td>未知</td>
			</c:otherwise>
		</c:choose>


		<td>${order.pay_no}</td>
		<td><fmt:formatDate value='${order.create_time}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
		<td><fmt:formatDate value='${order.pay_time}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
	</tr>
</c:forEach>
