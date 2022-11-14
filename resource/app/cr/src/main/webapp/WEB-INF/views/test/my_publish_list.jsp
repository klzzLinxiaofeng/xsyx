<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />

<table class="table">
	<thead>
		<tr><th>布置时间</th><th>标题</th><th></th></tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="vo">
			<tr>
				<td><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${vo.createDate}" /></td>
				<td>${vo.title }</td>
				<td class="caozuo">
					<button class="btn btn-blue" onclick="taskModify(${vo.id},'${vo.title }')">编辑</button>
					<button class="btn btn-red" onclick="deleteTask(${vo.id});">删除</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
	<jsp:param name="id" value="lpList" />
	<jsp:param name="url" value="/learningPlan/task/mypublish?dm=${param.dm}" />
	<jsp:param name="pageSize" value="${page.pageSize}" />
</jsp:include>
<div class="clear"></div>