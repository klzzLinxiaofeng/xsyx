<%-- 
    Document   : studyMicroList
    Created on : 2015-6-17, 13:18:19
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<!DOCTYPE html>
    <c:forEach var="ms" items="${microList}">
        <li>
            <a href="${pageContext.request.contextPath}/homeworkpublish/play?study=study&objId=${ms.resEnt.homework.id}&microPublishedId=${microPublishId}&publisherId=${param.publisherId}">
                <img src="${thumbFn:getConvertedUrl(ms.thumbnailUrl, ms.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
            </a>
            <c:choose>
                <c:when test="${ms.resEnt.record.finishedDate!=null}">
                    <p class="de_1">已完成</p>
                </c:when>
                <c:otherwise>
                    <p class="de_1">还没学习</p>
                </c:otherwise>
            </c:choose>
            <p class="de_2">${ms.resEnt.homework.title}</p>
            <!--            <p class="de_3">数学  &gt; 初一</p>-->
        </li>
    </c:forEach>
<script type="text/javascript">
    $(function() {
        $("#finishSpan_${microPublishId}").text("${finishedCount}")
    })
</script>