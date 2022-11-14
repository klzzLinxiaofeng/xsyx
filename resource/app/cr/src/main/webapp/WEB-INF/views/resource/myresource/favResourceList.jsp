<%-- 
    Document   : myMicro
    Created on : 2015-5-13, 11:03:09
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="zy_list">
    <c:if test="${resList== null || fn:length(resList) == 0}">
        <div class="empty">
            <p>您暂未收藏过此类资源</p>
        </div>
    </c:if>
    <div class="xkzy_list" >
        <c:forEach items="${resList}" var="res">
            <dl>
                <dt>
                    <a href="${pageContext.request.contextPath}${playUrl}?objId=${res.resEnt.uuid}">
                        <img src="${thumbFn:getConvertedUrl(res.thumbnailUrl,res.iconType, pageContext.request.contextPath, pageContext.request.serverName)}">
                    </a>
                </dt>
                <dd>
                    <div class="item-msg">
                        <div class="item-title">
                            <span class="${iconFn:getClassName(res.iconType)} icon-file res-iconb"></span>
                            <span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
                                <a href="${pageContext.request.contextPath}${playUrl}?objId=${res.resEnt.uuid}" title="${res.resEnt.title}">${res.resEnt.title}</a>
                            </span>
                        </div>
                        <span class="i1"> 
                            简介：${res.resEnt.description}
                        </span> 
                        <!--                        <div class="i1">
                                                    教材目录：2 分数乘法 分数乘法
                                                </div>-->
                        <div class="i1">
                            上传时间：<fmt:formatDate value="${res.resEnt.createDate}" pattern="yyyy-MM-dd"/>
                        </div>
                        <div class="i2">
                            <span class="left">
                                <img src="<avatar:avatar userId='${res.resEnt.userId}'></avatar:avatar>"/>
                                ${douFn:getFieldVal('user', res.resEnt.userId)}
                            </span>
                            <span class="right">
                                <!--                                <b class="zan"></b>-->
                            </span>
                        </div>
                        <div class="cz_btn">
                        	<c:if test="${resType!=7 }">
                            	<a class="shoucan" target="_blank" href="${pageContext.request.contextPath}/resource/download?objId=${res.resEnt.uuid}&resType=${resType}" >下载</a>
                            </c:if>
                            <a class="qingchu" onclick="removeFav('${res.resEnt.uuid}');">取消收藏</a>
                        </div>
                    </div>
                </dd>
            </dl>
        </c:forEach>
    </div>
</div>