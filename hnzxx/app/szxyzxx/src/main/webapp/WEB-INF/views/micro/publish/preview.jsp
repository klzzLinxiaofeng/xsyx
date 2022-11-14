<%@page import="platform.education.resource.contants.ResourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/jwplayer6.8/jwplayer.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/jwplayer6.8/jwplayer.html5.js"></script>
        <title>预览</title>
        <style>
            .video_show{
                width:788px;
                height:402px;
                margin:10px auto;
                background-color:#fff;
            }
            #detailContent_wrapper{
            	margin:0 auto;
            }
        </style>
    </head>
    <body style="background-color: #F3F3F3 !important">
        <div class="row-fluid ">
            <div class="span12">
                <div style="margin-bottom: 0" class="content-widgets">
                    <div style="padding: 20px 0 0;" class="widget-container">
                        <%--                        <div class="widget-head">
                                                    <h3>
                        <c:forEach varStatus="status" items="${microList}" var="micro" >
                            <c:if test="${status.index==0}">
                                ${micro.title}
                            </c:if>
                            <input id="httpUrl_${micro.uuid}" type="hidden" value="<entity:getHttpUrl uuid="${micro.entityId}"/>" />    
                        </c:forEach>
                    </h3>
                </div>--%>
                        <c:forEach varStatus="status" items="${microList}" var="micro" >
                            <input id="httpUrl_${micro.uuid}" type="hidden" value="<entity:getHttpUrl uuid="${micro.entityId}"/>" />    
                        </c:forEach>
                        <div id="detailContent" class="video_show" style="">
							<div id="html5_player" style="display: none">
                             	<jsp:include page="../common/preview_player.jsp"/>
                             </div>
                        </div>
<!--                         <div class="form-actions tan_bottom"> -->
<!--                            <button class="btn" onclick="$.closeWindow();" type="button">取消</button> -->
<!--                         </div> -->
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        <c:if test="${fn:length(microList)<=0}">
            $.alert("该微课已被删除");
        </c:if>

            var width = 788;
            var height = 520;
            $(function() {
                var httpUrl = $("input[id^='httpUrl_']").eq(0).val();
                callJwplay(httpUrl,"${microList[0].type}", width, height);
            });

            //调用JWPlayer
            function callJwplay(httpUrl,type, width, height) {
            	if("${type}" === type || "null" === type){
            		$("#html5_player").show();
            	}else{
	                jwplayer("detailContent").setup({
	                    width: width,
	                    height: height,
	                    primary: "flash",
	                    autostart: true,
	                    //image: "/image/teacher/music2.jpg",
	                    file: httpUrl,
	                    events: {
	                        onPlay: function(event) {
	                        },
	                        onBeforePlay: function(event) {
	                        },
	                        onPause: function(event) {
	                        },
	                        onReady: function(event) {
	                        },
	                        onComplete: function(event) {
	                        },
	                        onSeek: function(event) {
	                        }
	                    }
	                });
            	}
            }
    </script>
</html>