<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<tr role="row">
    <th>序号</th>
    <th>姓名</th>
    <c:forEach items="list2" var="list" varStatus="s">
        <th>${list.name}</th>
    </c:forEach>
    <th class="caozuo" style="max-width: 250px;">操作</th>
</tr>
