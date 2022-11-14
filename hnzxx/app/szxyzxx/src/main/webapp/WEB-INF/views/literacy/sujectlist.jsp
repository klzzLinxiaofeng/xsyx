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
    <tr id="${item.studentId}_tr">
        <td><input class="user_checkbox" type="checkbox" name="ids" value="${item.studentId}" ></td>
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.teamName}</td>
        <td>${item.stuName}</td>
        <td>${item.subName}</td>
        <td class="caozuo">
                <button class="btn btn-green" type="button" onclick="pinjia(${item.studentId},${item.subjectId});">评价</button>
        </td>
    </tr>
</c:forEach>
