<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${list}" var="item" varStatus="status">
    <tr id="${item.id}_tr">
        <td>${item.itemName}</td>
        <td class="amount">${item.amount}</td>
        <td>${item.percent}</td>
    </tr>
</c:forEach>
<tr>
    <td colspan="3" id="tj">总计：</td>
</tr>

<script>
    $(function () {
        var amount = 0;
        $(".amount").each(function () {
            amount += parseFloat($(this).text());
        });
        $("#tj").text("总计： " + amount);
    });

</script>
