<%-- 
    Document   : publishDetailsList
    Created on : 2015-7-10, 17:03:13
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib prefix="studystatus" uri="http://www.jiaoxueyun.com/studystatus"%>
<c:if test="${recordList== null || fn:length(recordList) == 0}">
    <tr>
        <td colspan="5">该班没有学员</td>
    </tr>
</c:if>
<c:forEach items="${recordList}" var="record">
    <tr id="userTr_${record.userId}">
        <td>${record.studentNumber}</td> 
        <td>${record.userName}</td>
        <td>
            <p class="<studystatus:getFinishedFlag finishedFlag="${record.finishedFlag}"/>"></p>
        </td>
        <td><fmt:formatDate value="${record.finishedDate}" pattern="yyyy年MM月dd日 HH:mm"/></td>
<td class="caozuo">

<c:choose>
    <c:when test='${not empty record.reviews}'>
        <a onclick="editReviews('${record.userId}')" href="javascript:void(0)">修改评语</a>
        <a onclick="watchReviews('${record.userId}')" href="javascript:void(0)">查看评语</a>
    </c:when>
    <c:otherwise>
        <a onclick="editReviews('${record.userId}')" href="javascript:void(0)">写评语</a>
    </c:otherwise>
</c:choose>
</td>
</tr>
</c:forEach>
