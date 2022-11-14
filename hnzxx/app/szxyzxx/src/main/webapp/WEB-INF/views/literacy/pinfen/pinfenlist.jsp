<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="studentId" name="totalPages" value="${studentId}"/>
        <input type="hidden" id="subjectId" name="totalPages" value="${subjectId}"/>
        <input type="hidden" id="schoolYear" name="totalPages" value="${schoolYear}"/>
        <input type="hidden" id="schoolTrem" name="totalPages" value="${schoolTrem}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${list}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td><inp ut class="user_checkbox" type="checkbox" name="ids" value="${item.id}"></td>
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.teamName}</td>
        <td>${item.stuName}</td>
        <td>${item.subName}</td>
        <td>${item.literacyName}</td>
        <td>${item.score}</td>
        <td>${item.fenshu}<%--<input value="${item.fenshu}" onblur="xiugai(${item.id},${item.score},${item.fenshu})" type="text" name="fenshu" id="fenshu_${item.id}"/>--%></td>
        <td>${item.pingyu}<%-- <textarea onblur="xiugai2(${item.id})" type="text" name="fenshu" id="pingyu_${item.id}">${item.pingyu}</textarea>--%></td>
        <td class="caozuo">
            <button class="btn btn-green" type="button" onclick="pingyu(${item.id});">添加评语</button>
        </td>
    </tr>
</c:forEach>
