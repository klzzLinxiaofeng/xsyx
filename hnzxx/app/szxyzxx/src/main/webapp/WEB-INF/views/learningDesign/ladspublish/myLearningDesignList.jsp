<%-- 
    Document   : myMicro
    Created on : 2015-5-13, 11:03:09
    Author     : Administrator
--%>
<%@page import="platform.education.resource.contants.ResourceType"%>
<%@page import="platform.szxyzxx.web.common.contants.SysContants"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<style>
    .edit{
        margin-right: 75px;
    }
</style>
<c:if test="${microLessonList== null || fn:length(microLessonList) == 0}">
    <p style="margin:0;line-height:30px;">  没有课件,请备课</p>
</c:if>
<c:forEach items="${microLessonList}" var="micro">
    <dl id="lddl_${micro.resEnt.uuid}">
        <dt>
            <a title="预览" target="_blank"  href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/lads/learning/info?ldId=${micro.resEnt.entityId}" >
                <img title="预览" alt="预览" src="${thumbFn:getConvertedUrl(micro.thumbnailUrl, micro.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
            </a>
        </dt>
        <dd>
            <div class="item-msg">
                <div class="top_1">
                    <div class="item-title">
                        <a title="预览" target="_blank"  href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/lads/learning/info?ldId=${micro.resEnt.entityId}" >${micro.resEnt.title}</a>
                        <input id="microId_${micro.resEnt.uuid}" type="hidden" value="${micro.resEnt.uuid}"/>
                        <input id="resourceId_${micro.resEnt.uuid}" type="hidden" value="${micro.resEnt.id}"/>
                    </div>
<!--                    <div><span>教材目录：</span></div>-->
                    <div><span>创建时间：<t:showTime createTime="${micro.resEnt.createDate}" /></span></div>
                </div>
                <div  class="item-comment">
                    <!--                    <span>349人浏览过</span>-->
                    <!--                        <span class="small-rating"><span style="width:80.0%"></span></span>
                                            <span class="fen">4</span>
                                            <span class="ren">（49人评价）</span>-->
                </div>
            </div>
            <a class="edit_btn edit" target="_blank" href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?lessonId=${micro.resEnt.uuid}">编辑</a>
            <a class="edit_btn" onclick="deleteMyLads('${micro.resEnt.uuid}')" href="javascript:void(0)">删除</a>
            <!--            <div class="select">
                            <input id="microCheck_${micro.resEnt.uuid}" onclick="chooseMicro('${micro.resEnt.uuid}')" type="checkbox">选择
                        </div>-->
            <div class="select" style="border: 0px">
                <div onclick="publishLesson('${micro.resEnt.uuid}','${micro.resEnt.entityId}');" class="btn btn-danger">布置</div>
            </div>
        </dd>
    </dl>
</c:forEach>