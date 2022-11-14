<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
    <td style="padding:0;border:0 none;">
        <input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}"/>
        <input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}"/>
        <input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
    </td>
</tr>
<c:forEach items="${recordsList}" var="item" varStatus="i">
    <tr id="${item.id}_tr">
        <td></td>
        <td>${i.index+1+(page.currentPage - 1) * 10}</td>
        <td>${item.evName}</td>
        <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm"/></td>
        <td>${item.teaName}</td>
        <td>${item.score}</td>
        <td>${item.message}</td>
        <td><img src="${item.pictureUrl}" style="width: 180px;height: 200px"></td>
        <td><audio loop src="${item.voice}" controls id="audio_${item.id}" onclick="bofan('${item.id}')" preload="auto" style="width: 150px;height: 40px"></audio></td>
    </tr>
</c:forEach>
