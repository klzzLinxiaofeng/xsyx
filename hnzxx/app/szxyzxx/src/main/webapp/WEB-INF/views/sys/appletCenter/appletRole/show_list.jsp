<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/views/embedded/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<tr style="display:none">
    <td colspan="8">
        <input type="hidden" id="currentPage" name="currentPage"
               value="${page.currentPage}" />
        <input type="hidden" id="totalPages" name="totalPages"
               value="${page.totalPages}" />
        <input type="hidden" id="total" name="total"
               value="${page.totalRows}" />
    </td>
</tr>

<c:forEach items="${items}" var="item">
    <tr>
        <td><i class="ck" data-appletid="${item.appletId}"></i></td>
        <td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}${item.icon}"></span>
        </td>
        <td>${item.appletName}</td>
            <c:choose>
                <c:when test="${item.lineType==0}">
                    <td class="xj">下架</td>
                </c:when>
                <c:otherwise>
                    <td class="sj">上架</td>
                </c:otherwise>
            </c:choose>
        <td class="caozuo"><button class="btn btn-red" onclick="delSingle(${item.appletId})">移除</button></td>
    </tr>
</c:forEach>
