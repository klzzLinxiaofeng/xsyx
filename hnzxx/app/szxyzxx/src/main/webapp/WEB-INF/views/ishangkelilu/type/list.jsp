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
    <tr  id="${item.id}_tr">
        <td style="text-align:center;">${i.index+1+(page.currentPage - 1) * 10}</td>
        <td style="text-align:center;">${item.name}</td>
        <c:if test="${item.type==0}">
            <td style="text-align:center;">加分项</td>
        </c:if>
        <c:if test="${item.type==1}">
            <td style="text-align:center;">减分项</td>
        </c:if>
        <td class="caozuo">
            <button class="btn btn-green" type="button" onclick="bianji('${item.id}');">编辑</button>
            <button class="btn btn-green" type="button" onclick="shanchu('${item.id}');">删除</button>
        </td>
    </tr>
</c:forEach>