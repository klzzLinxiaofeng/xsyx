<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

<tr style="display:none">
    <td>
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${list}" var="item">
    <tr id="${item.id}_tr">
        <td>${item.name}</td>
        <td>${item.teamName}</td>
        <td>${item.subjectName}</td>
        <td>
            <fmt:formatDate value="${item.examDateBegin}" pattern="yyyy/MM/dd HH:mm"/>
        </td>
        <td>
            <c:if test="${empty item.publisherId}">
                <button class="btn btn-blue" onclick="modifyPage('${item.id}')">修改</button>
            </c:if>
            <c:if test="${empty item.posterId}">
                <button class="btn btn-red" onclick="deleteObj(this, '${item.id}')">删除</button>
            </c:if>
        </td>
    </tr>
</c:forEach>


