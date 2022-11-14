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
        <!--lads 块不同处理-->
        <a target="_blank"  href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/lads/learning/learn?ldId=${ms.resEnt.learningDesign.ldId}" >
            <img src="${thumbFn:getConvertedUrl(ms.thumbnailUrl, ms.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
        </a>
        <p id="ladsStatus_${microPublishId}" class="de_1"></p>
        <input type="hidden" id="microPublishId_${microPublishId}" value="${microPublishId}"  />
        <input type="hidden" id="ldId_${microPublishId}" value="${ms.resEnt.learningDesign.ldId}"  />
        <input type="hidden" id="microId_${microPublishId}" value="${ms.resEnt.learningDesign.id}"  />
        <p class="de_2">${ms.resEnt.learningDesign.title}</p>
        <!--            <p class="de_3">数学  &gt; 初一</p>-->
    </li>
</c:forEach>
<script type="text/javascript">
    $(function () {
        $("#finishSpan_${microPublishId}").text("${finishedCount}");
    });
</script>
