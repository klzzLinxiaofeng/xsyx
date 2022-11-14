<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${items}" var="item" varStatus="i">
    <tr id="${item.gradeId}_tr">
        <td><input class="user_checkbox" type="checkbox" name="ids" value="${item.gradeId}" ></td>
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.gradeName}</td>
        <td>
            <c:if test="${item.zhuanTai == 0}">
                已开启
            </c:if>
            <c:if test="${item.zhuanTai == 1}">
                已关闭
            </c:if>
        </td>
        <td class="caozuo">
                <button class="btn btn-blue" type="button" onclick="updateComment('${item.gradeId}',0);">开启选课</button>
                <button class="btn btn-red" type="button" onclick="updateComment('${item.gradeId}',1);">关闭选课</button>
        </td>
    </tr>
</c:forEach>
