<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
    </td>
</tr>
<c:forEach items="${list}" var="item" varStatus="i">
    <tr id="${item['teamName']}_tr">
        <td>${item['teamName']}</td>
       <c:forEach items="${item['fenshu']}" var="avg" varStatus="a">
           <td>${avg}</td>
       </c:forEach>
        <c:forEach items="${item['lisanduList']}" var="lisandu" varStatus="a">
            <td>${lisandu}</td>
        </c:forEach>
    </tr>
</c:forEach>
