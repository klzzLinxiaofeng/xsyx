<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${list}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.name}</td>
        <td>${item.sex}</td>
        <td>${item.mobile}</td>
        <td>${item.mobileparent}</td>
        <td>${item.teamName}</td>
        <td>
            <c:if test="${item.studyState=='01'}">
                在读
            </c:if>
                <c:if test="${item.studyState=='02'}">
                休学
                </c:if>
                <c:if test="${item.studyState=='03'}">
                退学
                </c:if>
                <c:if test="${item.studyState=='04'}">
                停学
                </c:if>
        </td>
        <td class="caozuo">
            <button class="btn btn-green" type="button" onclick="queren('${item.id}','${item.name}',${item.teamId});">确认</button>
        </td>
    </tr>
</c:forEach>
