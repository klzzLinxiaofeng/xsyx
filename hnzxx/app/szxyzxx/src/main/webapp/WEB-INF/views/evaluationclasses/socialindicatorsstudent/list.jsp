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
        <td></td>
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.studentName}</td>
        <td>${item.teamName}</td>
        <td>${item.classesIndicatorsName}</td>
        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>${item.teacherName}</td>
        <td>${item.fenshu}</td>
        <td>${item.pingyu}</td>
     </tr>
</c:forEach>
