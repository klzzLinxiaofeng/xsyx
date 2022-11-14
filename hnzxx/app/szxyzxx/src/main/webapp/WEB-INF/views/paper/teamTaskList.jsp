<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
<c:if test="${empty voList}">
    <div class="no_resource"></div>
    <div style="display: none">
        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
            <jsp:param name="id" value="emExamPublish_list_content"/>
            <jsp:param name="url" value="/paper/list?"/>
            <jsp:param name="pageSize" value="${page.pageSize}"/>
        </jsp:include>
    </div>
</c:if>
<c:if test="${!empty voList}">
    <style>
        .table td span {
            font-weight: bold;
        }
    </style>
    <table class="table">
        <thead>
        <tr>
            <th>序号</th>
            <th>标题</th>
            <th>发布教师</th>
            <th>发布时间</th>
            <th>科目</th>
            <th>作答人数/总人数</th>
            <th>完成率</th>
        </tr>
        </thead>
        <c:forEach items="${voList}" var="item" varStatus="itemStatus">
            <tr id="${item.id}_tr">
                <td>${itemStatus.index+1}</td>
                <td>${item.title}</td>
                <td>${item.publisherName}</td>
                <td><fmt:formatDate value="${item.createDate}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                <td>${item.subjectName}</td>
                <td><span style="color:#ff6204">${item.finishedCount}</span>/${item.userCount}</td>
                <td>${item.percent}</td>
            </tr>
        </c:forEach>
    </table>
    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
        <jsp:param name="id" value="emExamPublish_list_content"/>
        <jsp:param name="url" value="/paper/list?"/>
        <jsp:param name="pageSize" value="${page.pageSize}"/>
    </jsp:include>
</c:if>