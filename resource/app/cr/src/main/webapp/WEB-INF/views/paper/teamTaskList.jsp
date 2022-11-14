<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
<c:if test="${empty voList}">
    <div class="no_resource"></div>
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
            <th>布置时间</th>
            <th>标题</th>
            <th>科目</th>
            <th>考试时间</th>
            <th>状态</th>
            <th>发布人</th>
            <th style="text-align:center">操作</th>
        </tr>
        </thead>
        <c:forEach items="${voList}" var="item">
            <tr id="${item.id}_tr">
                <td><fmt:formatDate value="${item.createDate}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                <td>${item.title}</td>
                <td>${item.subjectName}</td>
                <td><fmt:formatDate value="${item.startTime}" pattern="yyyy年MM月dd日 HH:mm"/>-<fmt:formatDate
                        value="${item.finishTime}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                <td><c:if test="${item.state eq 0}"><span style="color:#76c641;">未开始</span></c:if>
                    <c:if test="${item.state eq 1}"><span style="color:#fda32f;">进行中</span></c:if>
                    <c:if test="${item.state eq 2}"><span>已结束</span></c:if>
                </td>
                <td>${item.publisherName}</td>
                <td class="caozuo">
                    <button class="btn btn-green" type="button" onclick="look(${item.paperId})">查看</button>
                    <button class="btn btn-orange" type="button" onclick="index(${item.examId})">统计</button>
                        <%-- 			<button class="btn btn-green" type="button" onclick="tj(${item.examId});">生成统计</button> --%>
                    <button class="btn btn-peaGreen" type="button" onclick="exports(${item.id})">导出</button>
                    <button class="btn btn-red" type="button" onclick="del(${item.id})">删除</button>
                        <%-- 			<button class="btn btn-green"  type="button" onclick="json(${item.examId});">1</button> --%>
                        <%-- 			<button class="btn btn-green"  type="button" onclick="json1 (${item.examId});">2</button> --%>
                        <%-- 			<button class="btn btn-green"  type="button" onclick="json2 (${item.examId});">3</button> --%>
                        <%-- 			<button class="btn btn-green"  type="button" onclick="json3 (${item.examId});">4</button> --%>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
