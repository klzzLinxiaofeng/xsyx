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
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.name}</td>
        <td>
            <c:if test="${item.sex==0}">
                男
            </c:if>
            <c:if test="${item.sex==1}">
                女
            </c:if>
        </td>
        <td>${item.mobile}</td>
        <td>
            <c:if test="${item.jobState=='11'}">
                在职
            </c:if>
            <c:if test="${item.jobState=='01'}">
                退休
            </c:if>
            <c:if test="${item.jobState=='14'}">
                长假
            </c:if>
            <c:if test="${item.jobState=='06'}">
                辞职
            </c:if>
            <c:if test="${item.jobState=='08'}">
                开除
            </c:if>
            <c:if test="${item.jobState=='16'}">
                停薪留职
            </c:if></td>
        <td class="caozuo">
            <c:if test="${item.jobState=='11'}">
                <button class="btn btn-green" type="button" onclick="queren('${item.userId}');">确认</button>
            </c:if>

        </td>
    </tr>
</c:forEach>