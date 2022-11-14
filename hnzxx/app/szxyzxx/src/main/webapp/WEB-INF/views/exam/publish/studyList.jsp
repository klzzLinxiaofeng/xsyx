<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="subjectIcon" uri="http://www.jiaoxueyun.com/subjectIcon"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>试卷学习</title>
        <script type="text/javascript">
            $(function() {
                $(".xs_wk .xs_left .tea").hover(function() {
                    $(this).children(".a1").hide();
                    $(this).children(".a1").next().show();
                }, function() {
                    $(this).children(".a1").show();
                    $(this).children(".a1").next().hide();
                });
            });
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-asterisk" name="icon"/>
                <jsp:param value="试卷学习" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include>
            <div class="row-fluid ">
                <c:if test='${errorFlag eq "no_class"}'>
                    该用户没有分配班级
                </c:if>
                <c:if test='${errorFlag eq "not_a_student"}'>
                    该用户不是一个学生
                </c:if>
                <c:if test='${microList!=null&&fn:length(microList)<=0}'>
                    该用户没有任何可学习的试卷
                </c:if>
                <c:forEach var="micro" items="${microList}">
                    <div class="xs_wk white">
                        <div class="xs_left">
                            <p class="km <subjectIcon:subjectIcon subjectName='${fn:substring(micro.relateName, fn:indexOf(micro.relateName,"[")+1, fn:indexOf(micro.relateName,"]"))}'/>">
                                ${fn:substring(micro.relateName, fn:indexOf(micro.relateName,"[")+1, fn:indexOf(micro.relateName,"]"))}</p>
                            <div class="tea">
                                <div class="a1">
                                    <p class="p1"><span id="finishSpan_${micro.publishMicroLessonId}">0</span>/${fn:length(micro.realMicroArray)}</p>
                                    <p class="p2">结束时间：<span><fmt:formatDate value="${micro.finishedDate}" pattern="yyyy-MM-dd HH时" /></span></p>
                                </div>
                                <div class="a2" style="display:none">
                                    <p class="p1">
                                       <img src="<avatar:avatar userId='${micro.publisherId}'></avatar:avatar>">
                                    </p>
                                    <p class="p2"><span>${micro.publisherName}</span>老师 布置的</p> 
                                </div>
                            </div>
                            <a href="${pageContext.request.contextPath}/exampublish/play?study=study&microPublishedId=${micro.publishMicroLessonId}&publisherId=${micro.publisherId}" class="start_study">开始学习</a>
                        </div>
                        <div class="xs_right">
                            <ul>
                                <input type="hidden" value="${micro.publisherId}"/>
                                <div id="micrlList_${micro.publishMicroLessonId}"></div>
                            </ul>
                            <div class="clear"></div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        $(function(){
           var url="${pageContext.request.contextPath}/exampublish/ajaxGetStudyJson";
           $("div[id^='micrlList_']").each(function(){
               var publisherId = $(this).parent().find("input[type='hidden']").val();
               $(this).html("<li><img src='${pageContext.request.contextPath}/res/plugin/layer/skin/default/xubox_loading2.gif' style='margin-top:65px;width:32px;height:32px;' /></li>");
               $(this).load(url+"?publisherId="+publisherId,{"microPublishId": $(this).attr("id").split("_")[1]})
           });
        })
    </script>
</html>