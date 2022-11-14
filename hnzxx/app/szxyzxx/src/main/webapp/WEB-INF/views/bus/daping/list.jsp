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
        <td>${item.name}</td>
        <td>${item.gradeNames}</td>
        <td class="caozuo">
                <button class="btn btn-blue" type="button" onclick="updateComment('${item.id}');">修改</button>
       </td>
    </tr>
</c:forEach>
