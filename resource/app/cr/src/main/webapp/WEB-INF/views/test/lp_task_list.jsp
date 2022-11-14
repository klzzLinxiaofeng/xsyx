<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />

<table class="table">
	<thead>
		<tr>
			<th>布置时间</th>
			<th>标题</th>
			<th>科目</th>
			<th>班级</th>
			<th>完成人数/班级总人数</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="task">
			<tr>
				<td><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${task.createDate}" /></td>
				<td>${task.title }</td>
				<td>${task.subjectName }</td>
				<td>${task.teamName }</td>
				<td>${task.finishCount }/${task.studentCount }</td>
				<td class="caozuo">
					<button class="btn btn-green" onclick="preview(${task.lpId})">查看</button>
					<button class="btn btn-blue" onclick="activityDetail(${task.id})">活动详情</button>
					<button class="btn btn-orange">统计</button>
					<button class="btn btn-lightGray" onclick="deleteTask(${task.id})">删除</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
	<jsp:param name="id" value="taskList" />
	<jsp:param name="url" value="/learningPlan/task/list?dm=${param.dm}" />
	<jsp:param name="pageSize" value="${page.pageSize}" />
</jsp:include>
<div class="clear"></div>