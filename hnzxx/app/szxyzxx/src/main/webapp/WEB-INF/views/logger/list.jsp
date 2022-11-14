<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${loggers}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.name}</td>
        <td>${item.mokuaiName}</td>
        <td>
            <c:if test="${item.type==1}">
                添加
            </c:if>
            <c:if test="${item.type==2}">
                修改
            </c:if>
            <c:if test="${item.type==3}">
                删除
            </c:if>
        </td>
        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        <td>${item.message}</td>
       <%-- <td class="caozuo">
            <button class="btn btn-green" type="button" onclick="('${item.id}')">评价</button>
        </td>--%>
    </tr>
</c:forEach>