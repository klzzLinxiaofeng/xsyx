<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
        <input type="hidden" id="studentId" name="totalPages" value="${id}"/>
        <input type="hidden" id="teamId" name="totalPages" value="${teamId}"/>
    </td>
</tr>
<c:forEach items="${list}" var="item" varStatus="i">
    <tr  id="${item.id}_tr">
         <td style="text-align:center;">${i.index+1+(page.currentPage - 1) * 10}</td>
        <td style="text-align:center;">${item.gradeName}</td>
        <td style="text-align:center;">${item.pingYuTypeName}</td>
        <td style="text-align:center;">${item.text}</td>
        <td class="caozuo">
            <button class="btn btn-green" type="button" onclick="add('${item.text}');">确认添加</button>
        </td>
    </tr>
</c:forEach>