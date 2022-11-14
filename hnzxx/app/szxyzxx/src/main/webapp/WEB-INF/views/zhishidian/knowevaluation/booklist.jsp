<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
    </td>
</tr>
<c:forEach items="${list}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.name}</td>
        <c:if test="${item.subjectId==1}">
            <td>语文</td>
        </c:if>
        <c:if test="${item.subjectId==2}">
            <td>数学</td>
        </c:if>
        <c:if test="${item.subjectId==3}">
            <td>英语</td>
        </c:if>
        <td class="caozuo">
            <button class="btn btn-green" type="button" onclick="pingjia('${item.id}');">评价</button>
        </td>
    </tr>
</c:forEach>
<c:if test="${list.size()<=0}">暂无数据</c:if>