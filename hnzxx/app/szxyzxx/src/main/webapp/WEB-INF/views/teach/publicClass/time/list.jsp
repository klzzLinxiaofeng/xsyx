<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${items}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td><input class="user_checkbox" type="checkbox" name="ids" value="${item.id}" ></td>
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.classTime}</td>
        <td>
            <c:if test="${item.weekDate == 1}">
                星期一
            </c:if>
            <c:if test="${item.weekDate == 2}">
                星期二
            </c:if>
            <c:if test="${item.weekDate == 3}">
                星期三
            </c:if>
            <c:if test="${item.weekDate == 4}">
                星期四
            </c:if>
            <c:if test="${item.weekDate == 5}">
                星期五
            </c:if>
            <c:if test="${item.weekDate == 6}">
                星期六
            </c:if>
            <c:if test="${item.weekDate == 7}">
                星期日
            </c:if></td>
        <td>${item.classTimeStart}</td>
        <td>${item.classTimeEnd}</td>
        <td class="caozuo">
            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
                <button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
            </c:if>
            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
                <button class="btn btn-red" type="button" onclick="deleteComment(this, '${item.id}');">删除</button>
            </c:if>
        </td>
    </tr>
</c:forEach>
