<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${tongji}" var="item" varStatus="i">
    <tr id="${item.teacherIds}_tr">
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.teacherNames}</td>
        <td>${item.empCode}</td>
        <td>${item.score}</td>
        <td>${item.jiXiaoDeFen}</td>
        <td class="caozuo">
            <button class="btn btn-green" type="button" onclick="xiangqing('${item.teacherIds}');">获奖记录</button>
        </td>
    </tr>
</c:forEach>
<c:if test="${tongji.size()<=0}">暂无数据</c:if>