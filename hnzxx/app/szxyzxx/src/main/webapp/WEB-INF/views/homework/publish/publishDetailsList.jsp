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
        <td>
        	<c:if test="${record.finishedFlag == 1 || record.finishedFlag == 3}">
        		<c:forEach items="${record.publishedMicroName}" var="name">
        			<c:set scope="page" var="publishedMicro" value="${fn:split(name,'	')}"/>
        			${publishedMicro[0]}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        			<c:if test="${not empty record.finishedDate}"></c:if>
        			<a href="javascript:loadDetail('${publishedMicro[0]}','${publishedMicro[1]}','${record.publishedMicroId}','${record.userId}')">查看详情</a><br>
        		</c:forEach>
        	</c:if>
        </td>
<td class="caozuo">

<c:choose>
    <c:when test='${not empty record.reviews}'>
        <a onclick="editReviews('${record.userId}')" href="javascript:void(0)">修改解答</a>
        <a onclick="watchReviews('${record.userId}')" href="javascript:void(0)">查看解答</a>
    </c:when>
    <c:otherwise>
        <a onclick="editReviews('${record.userId}')" href="javascript:void(0)">解答</a>
    </c:otherwise>
</c:choose>
</td>
</tr>
</c:forEach>
