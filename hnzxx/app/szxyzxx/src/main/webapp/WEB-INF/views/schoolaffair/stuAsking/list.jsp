<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
    </td>
</tr>
<c:forEach items="${studentAsking}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm"/></td>
        <td>${item.teamName}</td>
        <td>${item.stuName}</td>
        <td>${item.timeConsuming} 小时</td>
        <td>${item.teacherName}</td>
        <td>
            <c:if test="${item.indiaStatus == 1}">审核中</c:if>
            <c:if test="${item.indiaStatus == 2}"><span style="color: green">已通过</span></c:if>
            <c:if test="${item.indiaStatus == 3}"><span style="color: red;">已驳回</span></c:if>
        </td>
        <td class="caozuo">
            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
                <button class="btn btn-green" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
            </c:if>
        </td>
    </tr>
</c:forEach>