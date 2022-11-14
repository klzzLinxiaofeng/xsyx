<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
<c:if test="${taskSize >0 }">
    <div class="lp_list">
    <table class="table">
        <thead>
        <tr>
            <th>序号</th>
            <th>标题</th>
            <th>完成人数/总人数</th>
            <th>完成率</th>
            <th>发布教师</th>
            <th>科目</th>
            <th>发布时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="task" varStatus="taskStatus">
            <tr>
                <td>${taskStatus.index+1}</td>
                <td>${task.title}</td>
                <td><span style="color:#ff6204">${task.finishCount}</span>/${task.studentCount}</td>
                <td>${task.percent}%</td>
                <td>${task.userName }</td>
                <td>${task.subjectName }</td>
                <td><fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${task.createDate}"/></td>
            </tr>
            <tr>
                <td colspan='7'>
                    <ul style="padding: 20px 0 0 20px;">
                        <c:forEach items="${task.unitVoList}" var="unit" varStatus="unitStatus">
                            <li class="unit">
                                <p class="title" style="padding: 15px 0 14px 0">${unit.title}</p>
                                <img src="${pageContext.request.contextPath}/res/images/finishCount.png" style="width: 20px;"/>
                                <span style="margin-right: 10px;color:#30A2F2">${unit.finishCount}</span>
                                <img src="${pageContext.request.contextPath}/res/images/percent.png" style="width: 20px;"/>
                                <span style="color:#FE8C12">${unit.percent}%</span>
                            </li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
        <jsp:param name="id" value="taskList"/>
        <jsp:param name="url"
                   value="/learningPlan/list?dm=${param.dm}&gradeId=${gradeId}&teamId=${teamId}&subjectCode=${subjectCode}&title=${title}"/>
        <jsp:param name="pageSize" value="${page.pageSize}"/>
    </jsp:include>
    <div class="clear"></div>
    </div>

</c:if>
<c:if test="${taskSize ==0 }">
    <div class="no_task"></div>
    <div style="display: none">
        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
            <jsp:param name="id" value="taskList"/>
            <jsp:param name="url" value="/learningPlan/list?dm=${param.dm}"/>
            <jsp:param name="pageSize" value="${page.pageSize}"/>
        </jsp:include>
        <div class="clear"></div>
    </div>
</c:if>