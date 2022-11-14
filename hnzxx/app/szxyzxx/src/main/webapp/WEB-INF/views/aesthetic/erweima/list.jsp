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
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.teamName}</td>
        <td>${item.studnetName}</td>
        <td><img src="${item.url}" style="width: 180px;height: 200px"></td>
        <td class="caozuo">
                <button class="btn btn-green" type="button" onclick="createBtn('${item.studentId}')">生成二维码</button>
                <button class="btn btn-green" type="button" onclick="downloadImg('${item.id}')">下载</button>
        </td>
    </tr>
</c:forEach>