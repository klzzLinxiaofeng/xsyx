<%-- 
    Document   : myMicro
    Created on : 2015-5-13, 11:03:09
    Author     : Administrator
--%>
<%@page import="platform.education.resource.contants.ResourceType"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="zy_list">
    <c:if test="${microLessonList== null || fn:length(microLessonList) == 0}">
        <div class="empty">
            <p>您暂未收藏过任何微课</p>
        </div>
    </c:if>
    <div class="xkzy_list" >
        <c:forEach items="${microLessonList}" var="micro">
            <dl>
                <dt>
                    <a href="${pageContext.request.contextPath}/micro/play?microId=${micro.resEnt.uuid}">
                        <img src="${thumbFn:getConvertedUrl(micro.thumbnailUrl,micro.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
                    </a>
                </dt>
                <dd>
                    <div class="item-msg">
                        <div class="item-title">
                            <!--                            <span class="res-swf icon-file res-iconb"></span>-->
                            <span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
                                <a href="${pageContext.request.contextPath}/micro/play?microId=${micro.resEnt.uuid}" title="${micro.resEnt.title}">${micro.resEnt.title}</a>
                            </span>
                        </div>
                        <span class="i1" style="display:block;height:48px;"> 
                            简介：${micro.resEnt.description}
                        </span> 
<!--                        <div class="i1">
                            教材目录：2 分数乘法 分数乘法
                        </div>-->
                        <div class="i1">
                            上传时间：<fmt:formatDate value="${micro.resEnt.createDate}" pattern="yyyy-MM-dd"/>
                        </div>
                        <div class="i2" style="margin-top:5px;">
                            <span class="left">
                                <img src="<avatar:avatar userId='${micro.resEnt.userId}'></avatar:avatar>"/>
                                ${douFn:getFieldVal('user', micro.resEnt.userId)}
                            </span>
                            <span class="right">
                                <!--                                <b class="zan"></b>-->
                            </span>
                        </div>
                        <div class="cz_btn">
                            <a class="shoucan" target="_blank" href="${pageContext.request.contextPath}/resource/download?objId=${micro.resEnt.uuid}&resType=<%=ResourceType.MICRO%>" >下载</a>
                            <a class="qingchu" onclick="removeFav('${micro.resEnt.uuid}');">取消收藏</a>
                        </div>
                        
                         <%-- <div>
                            <c:choose>
							  	 <c:when test="${res.verify==0}">
							  	 <a onclick="verifyDelete('${res.resEnt.uuid}');" ><font color="green">取消共享</font></a>
							  		
								 </c:when>
								 <c:when test="${res.verify==8}">
							  	 <a onclick="verifyAdd('${res.resEnt.uuid}');" ><font color="green">共享</font></a>
							  		
								 </c:when>
								  <c:otherwise>
								  
								  <a href="javaScript:;"><font color="red">审核中</font></a>
								 </c:otherwise>
							 </c:choose>
                            </div> --%>
                    </div>
                </dd>
            </dl>
        </c:forEach>
    </div>
</div>