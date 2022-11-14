<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${homeWoke}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td style="text-align:center;">${i.index+1}</td>
        <td style="text-align:center;">${item.studentName}</td>
        <td style="text-align:center;">
            <c:if test="${item.zhuantai==0}">
                待提交
            </c:if>
            <c:if test="${item.zhuantai==1}">
                已提交
            </c:if>
            <c:if test="${item.zhuantai==2}">
                缺交
            </c:if>
            <c:if test="${item.zhuantai==3}">
                补交
            </c:if>
        </td>
    </tr>
</c:forEach>
