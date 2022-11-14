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
            <p>您暂未上传过任何微课</p>
        </div>
    </c:if>
    <div class="xkzy_list" >
        <c:forEach items="${microLessonList}" var="micro">
            <dl>
                <dt>
                    <a href="${pageContext.request.contextPath}/micro/play?microId=${micro.uuid}">
                        <img src="${pageContext.request.contextPath}/res/images/video.png">
                    </a>
                </dt>
                <dd>
                    <div class="item-msg">
                        <div class="item-title">
                            <span class="res-mp4 icon-file res-iconb"></span>
                            <span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
                                <a href="${pageContext.request.contextPath}/micro/play?microId=${micro.uuid}" title="${micro.title}">${micro.title}</a>
                            </span>
                        </div>
                        <span class="i1" style="height:72px;overflow:hidden;display:block;"> 
                            简介：${micro.description}
                        </span> 
                        <!--                        <div class="i1">
                                                    教材目录：2 分数乘法 分数乘法
                                                </div>-->
                        <div class="i1">
                            上传时间：<fmt:formatDate value="${micro.createDate}" pattern="yyyy-MM-dd"/>
                        </div>
                        <div class="i2">
                            <span class="left">
                                <%--                               <img src="<avatar:avatar userId='${micro.userId}'></avatar:avatar>"/>
                                                               ${douFn:getFieldVal('user', micro.userId)}   --%>
                            </span>
                            <span class="right">
                                <!--                                <b class="zan"></b>-->
                            </span>
                        </div>
                        <div class="cz_btn">
                            <div style="display: inline-block;">
                            <c:if test="${res.verify==8}">
 	                        <a onclick="shareIndex('${res.resEnt.uuid}','${res.resId}','${res.title}');" ><font color="green">共享</font></a>
 	                        </c:if>
 	                        <c:if test="${res.verify==7||res.verify==4}">
 	                         <a onclick="verifyDelete('${res.resEnt.uuid}','${res.resId}');" ><font color="green">取消共享</font></a>
 	                         </c:if>
                            </div>
                            <a class="shoucan" target="_blank" href="${pageContext.request.contextPath}/resource/download?objId=${micro.uuid}&resType=<%=ResourceType.MICRO%>" >下载</a>
                            <a class="shoucan" onclick="editMicro('${micro.uuid}');" >编辑</a>
                            <a class="qingchu" onclick="deleteMicro('${micro.uuid}','${micro.verify}');">删除</a>
                        </div>

                    </div>
                </dd>
            </dl>
        </c:forEach>
    </div>
</div>
<script type="text/javascript">
   $(function(){
       $("#yourLike .rs_platformContainer_1 p span").text("${page.totalRows}");
   })
</script>