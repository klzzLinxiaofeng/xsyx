<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<c:if test="${empty volist}"><div class="no_resource"></div></c:if>
			<c:if test="${!empty volist}">
<table class="table">
					<thead>
						<tr><th>布置时间</th><th>标题</th><th>开始时间</th><th>结束时间</th><th></th></tr>
					</thead>
<c:forEach items="${volist}" var="item">
	<tr id="${item.id}_tr">
	    		<td><fmt:formatDate value="${item.createDate}" pattern="yyyy年MM月dd日 HH:mm" /></td>
				<td>${item.title}</td>
	    		<td><fmt:formatDate value="${item.startTime}" pattern="yyyy年MM月dd日 HH:mm" /></td>
	    		<td><fmt:formatDate value="${item.finishTime}" pattern="yyyy年MM月dd日 HH:mm" /></td>
		<td class="caozuo">
			<button class="btn btn-blue" type="button" onclick="loadEditPage(${item.id})">编辑</button>
			<button class="btn btn-red" type="button" onclick="del(${item.id});">删除</button>
		</td>
	</tr>
</c:forEach>
</table>
</c:if>
