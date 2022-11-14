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
        <td><input class="user_checkbox" type="checkbox" name="ids" value="${item.id}" ></td>
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.gradeName}</td>
        <td>${item.subjectName}</td>
        <td>${item.literacyName}</td>
        <td>${item.score}</td>
        <td class="caozuo">
                <button class="btn btn-green" type="button" onclick="chakan('${item.id}');">查看</button>
                <button class="btn btn-green" type="button" onclick="bianji('${item.id}');">编辑</button>
                <button class="btn btn-green" type="button" onclick="deleteComment('${item.id}');">移除</button>
        </td>
    </tr>
</c:forEach>
