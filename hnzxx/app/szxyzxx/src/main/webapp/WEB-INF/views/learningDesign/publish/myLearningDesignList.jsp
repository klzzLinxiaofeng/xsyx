<%-- 
    Document   : myMicro
    Created on : 2015-5-13, 11:03:09
    Author     : Administrator
--%>
<%@page import="platform.szxyzxx.web.common.contants.SysContants"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <c:if test="${(reslist== null || fn:length(reslist) == 0)&&(personType eq 'res_school'||personType eq 'res_person')}">
        <div class="empty">
            <p>您暂未上传过此类资源</p>
        </div>
    </c:if>
        <c:if test="${(reslist== null || fn:length(reslist) == 0)&&personType eq 'res_share'}">
        <div class="empty">
            <p>您暂未共享过此类资源</p>
        </div>
    </c:if>
        <c:if test="${(reslist== null || fn:length(reslist) == 0)&&personType eq 'fav_resource'}">
        <div class="empty">
            <p>您暂未收藏过此类资源</p>
        </div>
    </c:if>
<c:forEach items="${reslist}" var="micro">
    <dl>
        <dt>
            <c:choose>
                <c:when test="${ladsType eq micro.learnDesignType}">
                    <a target="_blank"  href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/lads/learning/info?ldId=${micro.entityId}" >
                        <img src="${thumbFn:getConvertedUrl(micro.thumbnailUrl, micro.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
                    </a>
                </c:when>
                <c:otherwise>
                    <a onclick="previewMicro('${micro.resEnt.objectId}')"  href="javascript:void(0)" >
                        <img src="${thumbFn:getConvertedUrl(micro.thumbnailUrl, micro.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
                    </a>
                </c:otherwise>
            </c:choose>

        </dt>
        <dd>
            <div class="item-msg">
                <div class="top_1">
                    <div class="item-title">
                        <c:choose>
                            <c:when test="${ladsType eq micro.learnDesignType}">
                                <a target="_blank"  href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/lads/learning/info?ldId=${micro.entityId}" >${micro.resEnt.title}</a>
                            </c:when>
                            <c:otherwise>
                                <a onclick="previewMicro('${micro.resEnt.objectId}')" href="javascript:void(0)" >${micro.resEnt.title}</a>
                            </c:otherwise>
                        </c:choose>
                        <input id="microId_${micro.resEnt.objectId}" type="hidden" value="${micro.resEnt.objectId}"/>
                    </div>
<!--                     <div><span>教材目录：</span></div> -->
                    <div><span>上传时间：<t:showTime createTime="${micro.resEnt.createDate}" /></span></div>
                </div>
                <div  class="item-comment">
                    <!--                    <span>349人浏览过</span>-->
                    <!--                        <span class="small-rating"><span style="width:80.0%"></span></span>
                                            <span class="fen">4</span>
                                            <span class="ren">（49人评价）</span>-->
                </div>
            </div>
<%--             <a class="edit_btn" target="_blank" href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?lessonId=${micro.resEnt.objectId}">编辑</a> --%>
            <div class="select"><input id="microCheck_${micro.resEnt.objectId}" onclick="chooseMicro('${micro.resEnt.objectId}')" type="checkbox">选择</div>
        </dd>
    </dl>
</c:forEach>