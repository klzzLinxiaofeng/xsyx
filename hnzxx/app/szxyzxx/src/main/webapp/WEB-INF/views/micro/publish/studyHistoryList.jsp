<%-- 
    Document   : studyHistoryList
    Created on : 2015-8-4, 18:39:45
    Author     : Administrator
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/taglib.jsp"%>
<%@ taglib prefix="studystatus" uri="http://www.jiaoxueyun.com/studystatus"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<!DOCTYPE html>
<c:if test="${recordList== null || fn:length(recordList) == 0}">
    <tr>
        <td colspan="5">没有记录</td>
    </tr>
</c:if> 
<c:forEach items="${recordList}" var="record">
    <tr>
        <td><fmt:formatDate value="${record.mlrv.startDate}" pattern="yyyy年MM月dd日 "/>~<fmt:formatDate value="${record.mlrv.finishedDate}" pattern="yyyy年MM月dd日 HH"/> 点</td>
        <td>${record.mlrv.publisherName}</td>
        <td>${record.mlrv.relateName}</td>
        <td><p class="<studystatus:getFinishedFlag finishedFlag="${record.mpr.finishedFlag}"/>"></p></td>
        <td class="caozuo">
            <a href="${pageContext.request.contextPath}/micropublish/play?microPublishedId=${record.mlrv.publishMicroLessonId}">查看微课</a>
            <c:if test='${not empty record.mpr.reviews}'>
                <a onclick="watchReviews('${record.mlrv.publishMicroLessonId}','${record.mpr.userId}')" href="javascript:void(0)">查看评语</a>
            </c:if>
        </td>
    </tr>
</c:forEach>

