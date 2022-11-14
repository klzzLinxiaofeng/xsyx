<%@ page language="java" import="platform.education.user.contants.UserContants" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

<tr style="display:none">
    <td>
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
    </td>
</tr>
<c:forEach items="${list}" var="item">
    <tr>
        <td>${item.name}</td>
        <td>${item.totalScore}</td>
        <td class="caozuo">
            <button class="btn btn-green" onclick="toAnalysis('${item.id}', '${item.teamId}');">查看详情</button>
            <%--<button class="btn btn-orange">导出成绩</button>--%>
        </td>
    </tr>
</c:forEach>