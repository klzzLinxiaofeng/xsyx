<%-- 
    Document   : studyMicroList
    Created on : 2015-6-17, 13:18:19
    Author     : Administrator
--%>

<%@page import="platform.education.resource.contants.StudyFinishedFlag"%>
<%@page import="platform.szxyzxx.web.common.contants.SysContants"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<!DOCTYPE html>
<c:forEach var="ms" items="${microList}">
    <li>
        <a href="${pageContext.request.contextPath}/learningDesignpublish/play?study=study&objId=${ms.resEnt.learningDesign.uuid}&microPublishedId=${microPublishId}&publisherId=${param.publisherId}">
            <img src="${thumbFn:getConvertedUrl(ms.thumbnailUrl, ms.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
        </a>
        <p class="de_2">${ms.resEnt.learningDesign.title}</p>
        <!--            <p class="de_3">数学  &gt; 初一</p>-->
    </li>
</c:forEach>
<script type="text/javascript">
    $(function () {
        $("#finishSpan_${microPublishId}").text("${finishedCount}")
    })
</script>