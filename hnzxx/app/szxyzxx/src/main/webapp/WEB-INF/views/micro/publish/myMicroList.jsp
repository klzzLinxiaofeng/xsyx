<%-- 
    Document   : myMicro
    Created on : 2015-5-13, 11:03:09
    Author     : Administrator
--%>
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
<c:forEach items="${reslist}" var="reslist">
    <dl>
        <dt>
            <a onclick="previewMicro('${reslist.resEnt.objectId}')"  href="javascript:void(0)" >
                <img src="${pageContext.request.contextPath}/res/images/video.png">
            </a>
        </dt>
        <dd>
            <div class="item-msg">
                <div class="top_1">
                    <div class="item-title">
                        <a onclick="previewMicro('${reslist.resEnt.objectId}')" href="javascript:void(0)" >${reslist.resEnt.title}</a>
                        <input id="microId_${reslist.resEnt.objectId}" type="hidden" value="${reslist.resEnt.objectId}"/>
                        <input id="resourceId_${reslist.resEnt.objectId}" type="hidden" value="${reslist.resEnt.id}"/> 
                       
                    </div>
<!--                     <div><span>教材目录：</span></div> -->
                    <div><span>上传时间：<t:showTime createTime="${reslist.resEnt.createDate}" /></span></div>
                </div>
                <div  class="item-comment">
                    <!--                    <span>349人浏览过</span>-->
                    <!--                        <span class="small-rating"><span style="width:80.0%"></span></span>
                                            <span class="fen">4</span>
                                            <span class="ren">（49人评价）</span>-->
                </div>
            </div>
            <div class="select"><input id="microCheck_${reslist.resEnt.objectId}" onclick="chooseMicro('${reslist.resEnt.objectId}','${reslist.resEnt.id}')" type="checkbox">选择</div>
        </dd>
    </dl>
</c:forEach>