<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
<c:forEach items="${items}" var="item">
	<tr id="${item.id}_tr">
	    		<td><fmt:formatDate value="${item.createDate}" pattern="yyyy年MM月dd日 HH:mm" /></td>
				<td>${item.title}</td>
	    		<td><fmt:formatDate value="${item.startDate}" pattern="yyyy年MM月dd日 HH:mm" /></td>
	    		<td><fmt:formatDate value="${item.finishedDate}" pattern="yyyy年MM月dd日 HH:mm" /></td>
		<td >
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.uuid}');">编辑</button>
			<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.uuid}');">删除</button>
		</td>
	</tr>
</c:forEach>
