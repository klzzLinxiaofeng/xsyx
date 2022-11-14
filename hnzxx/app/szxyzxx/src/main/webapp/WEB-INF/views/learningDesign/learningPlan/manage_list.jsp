<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<table border="0" cellspacing="0" cellpadding="0">
    <thead>
        <tr>
            <th>布置时间</th>
            <th>导学案名称</th>
            <th>班级</th>
            <th>科目</th>
            <th>完成率</th>
            <th>完成人数</th>
            <th>操作</th>
        </tr>
    </thead>
    <tbody>
    	<c:forEach items="${list }" var="task">
	        <tr>
	            <td><fmt:formatDate value="${task.createDate }" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
	            <td>${task.title }</td>
	            <td>${task.teamName }</td>
	            <td>${task.subjectName }</td>
	            <td><fmt:formatNumber type="number" value="${task.percent }" maxFractionDigits="2"/>%</td>
	            <td>${task.finishCount }</td>
	            <td>
	            	<button class="btn see" onclick="preview(${task.lpId});">查看</button>
	            	<button class="btn state" onclick="taskDetail(${task.id});">完成情况</button>
	            	<button class="btn state" onclick="tj(${task.id});">查看统计</button>
	            	<button class="btn state" onclick="activityDetail(${task.id});" style="margin-left:0px;">活动情况</button>
	            	
	         <%--    	<c:if test="${task.hasActivity}">
	            		<button class="state" onclick="activityDetail(${task.id});" style="margin-left:0px;">活动情况</button>
	            	</c:if>
	            	<c:if test="${!task.hasActivity}">
	            		<button class="btn grey_color" style="z-index: -1; margin-left:0px; width:80px; margin-right: 20px;" title="该导学案下无小结单元">活动情况</button>
	            	</c:if> --%>
	            	<button class="btn delete" onclick="deleteTask(${task.id});">删除</button>
	            </td>
	        </tr>
        </c:forEach>
    </tbody>
</table>