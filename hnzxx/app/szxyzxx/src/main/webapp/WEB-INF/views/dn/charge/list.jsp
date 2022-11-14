<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${chargeVoList}" var="item" varStatus="status">
    <tr id="${item.id}_tr">
        <td>${status.count}</td>
        <td>${item.gradeName}</td>
        <td>${item.teamName}</td>
        <td>${item.studentName}</td>
        <td>${item.itemName}</td>
        <td>
            <c:if test="${item.isPay}">
                ${item.amount}
            </c:if>
            <c:if test="${!item.isPay}">
                <span style="color: #ff0000;">未缴费</span>
            </c:if>
        </td>
        <td class="caozuo">
            <c:if test="${item.isPay}">
                <button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
            </c:if>
            <c:if test="${!item.isPay}">
                <button class="btn btn-green" type="button" onclick="loadEditPage('${item.id}');">缴费</button>
            </c:if>
        </td>
    </tr>
</c:forEach>
