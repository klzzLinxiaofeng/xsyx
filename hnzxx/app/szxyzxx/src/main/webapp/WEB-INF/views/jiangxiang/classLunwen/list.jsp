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
    <tr id="${item.teacherIds}_tr">
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.theme}</td>
        <td>${item.type}</td>
        <td>${item.shuXing}</td>
        <td>${item.winningTime}</td>
        <td>${item.teacherNames}</td>
        <td>${item.studentNames}</td>
        <td>${item.nameWoke}</td>
        <td>${item.winningLevelName}</td>
        <td>${item.dengciName}</td>
        <td>${item.allocated}</td>
        <td>${item.score}</td>
        <td>${item.jiXiaoDeFen}</td>
        <td class="caozuo">
            <button class="btn btn-green" type="button" onclick="xiangqing('${item.id}','${item.teacherIds}');">详情</button>
        </td>
    </tr>
</c:forEach>
<c:if test="${list.size()<=0}">暂无数据</c:if>