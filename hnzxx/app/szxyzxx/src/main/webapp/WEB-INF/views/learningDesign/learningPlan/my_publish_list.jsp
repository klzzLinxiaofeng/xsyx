<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<table border="0" cellspacing="0" cellpadding="0">
    <thead>
        <tr>
            <th>布置时间</th>
            <th>导学案名称</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
    	<c:forEach items="${list }" var="task">
	        <tr>
	            <td><fmt:formatDate value="${task.createDate }" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
	            <td id="title_${task.id }">${task.title }</td>
	            <td>
	            	<button class="btn see" onclick="taskModify(${task.id}, '${task.title }');">编辑</button>
	            	<button class="btn delete" onclick="deleteTask(${task.id});">删除</button>
	            </td>
	        </tr>
        </c:forEach>
    </tbody>
</table>