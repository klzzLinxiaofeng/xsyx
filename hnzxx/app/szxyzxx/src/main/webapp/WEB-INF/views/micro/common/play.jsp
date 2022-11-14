<%@page import="platform.education.resource.contants.StudyFinishedFlag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>${micro.title}</title>
        <%@ include file="/views/embedded/plugin/jwplayer.jsp"%>
    </head>
    <body>
        <div class="container-fluid">
            <input id="microId" type="hidden" value="${micro.uuid}">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-asterisk" name="icon"/>
                <jsp:param value="微课播放" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include>
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-widgets white">
                        <div class="widget-head">
                            <h3>
                                微课播放
                                <p style="float:right;" class="btn_link">
                                    <a class="a4" onclick="history.back();" href="javascript:void(0)"><i class="fa  fa-undo"></i>返回</a>
                                </p>
                            </h3>
                        </div>
                        <div class="play_area">
                            <div class="p_detail">
                                <c:forEach varStatus="status" items="${microList}" var="micro" >
                                    <c:if test="${status.index==flag}">
                                        <div id="playTitle" class="title">${micro.title}</div>
                                    </c:if>
                                    <input id="httpUrl_${micro.uuid}" type="hidden" value="<entity:getHttpUrl uuid="${micro.entityId}"/>" />    
                                </c:forEach>
                                <div class="play_1" >
                                    <div class="p_left">
                                        <div id="detailContent">
                                        	<div id="html5_player" style="display: none">
                                        		<jsp:include page="html5_Player.jsp"/>
                                        	</div>
                                        </div>
                                    </div>
                                    <c:if test="${fn:length(microList)>1}">
                                        <div class="p_right">
                                            <ul>
                                                <c:forEach varStatus="status" items="${microList}" var="micro">
                                                    <li id="microDataLi_${micro.uuid}">
                                                        <a onclick="selectPlay('${micro.uuid}','${micro.type}','${status.index}')" href="javascript:void(0)">
                                                            <img src="<entity:getThumbnailUrl uuid="${micro.entityId}"></entity:getThumbnailUrl>">
                                                        </a>
                                                        <p class="de_2"><a onclick="selectPlay('${micro.uuid}','${micro.type}''${status.index}')" href="javascript:void(0)">${micro.title}</a></p>
                                                        <!--                                                    <p class="de_3">数学  &gt; 初一</p>-->
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </c:if>
                                    <div class="clear"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
                                        var initFlag = true;
        <c:if test="${fn:length(microList)<=0}">
                                        $.alert("该微课已被删除");
                                        initFlag = false;
        </c:if>

        <c:choose>
            <c:when test="${fn:length(microList)>1}">
           	 var width = 634;
            </c:when>
            <c:otherwise>
                var width = 900;
            </c:otherwise>
        </c:choose>

            var height = 500;
            var currentHttpUrl;
            var currentMid;
            var currentPlayer;
            var lastPlayTime;
            var microType;
            
            $(function() {
                if (initFlag) {
                    currentHttpUrl = $("input[id^='httpUrl_']").eq(0).val();
                    if (currentHttpUrl == null || currentHttpUrl == "") {
                        $.alert("该微课已被删除");
                    }
                    currentMid = $("input[id^='httpUrl_']").eq(0).attr("id").split("_")[1];
                    callJwplay(currentHttpUrl,"${microList[flag].type}", width, height);
                    //轮询记录当前播放进度,15秒记录一次
                    if("${type}" != "${microList[flag].type}" && "" != "${microList[flag].type}"){
	                    unfinishInterval = window.setInterval('saveRecord()', 15000);
                    }
                }
            });

            function selectPlay(mid,type,indexFlag) {
                var httpUrl = $("#httpUrl_" + mid).val();
                currentMid = mid;
                currentHttpUrl = httpUrl;
                if("${type}" === type){
	                reloadPlayer(indexFlag);
                }
//                 $("#playTitle").text($.trim($("#microDataLi_" + mid + " .de_2").text()));
                microType = type;
                callJwplay(currentHttpUrl,type, width, height);
            }
			function reloadPlayer(indexFlag){
					location.href = "${ctp}/micropublish/play?flag=" + indexFlag + "&microPublishedId=${microPublishedId}";
			}
            //调用JWPlayer
            function callJwplay(httpUrl,type, width, height) {
            	if("${type}" === type || "null" === type){
//             		var url = "html5_Player.jsp";
//             		WINDOW.LOCATION.HREF= URL;
            		$("#html5_player").show();
            	}else{
	                currentPlayer = jwplayer("detailContent").setup({
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
	                            getRecord();
	                        },
	                        onComplete: function(event) {
	                            saveRecord("<%=StudyFinishedFlag.FINISHED%>");
	                            $.alert("学习完毕");
	                        },
	                        onSeek: function(event) {
	                        }
	                    }
	                });
            	}
            }

            function getRecord() {
                var data = {};
                data.microId = currentMid;
                var microPublishId = "${microPublishedId}";
                if (microPublishId != "" && microPublishId != "null") {
                    data.publishLessonId = microPublishId;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/micropublish/getRecord",
                    type: "POST",
                    data: data,
                    async: false,
                    success: function(result) {
                        if (result != "fail") {
                            //跳到记录的播放进度
                            lastPlayTime = result;
                            //$.alert("上次播放到"+lastPlayTime+"秒");
                            currentPlayer.seek(lastPlayTime); //从指定位置开始播放(单位：秒)
                        }
                    }
                });
            }

            function saveRecord(finishedFlag) {
                var data = {};
                data.microId = currentMid;
                data.finishedFlag = finishedFlag;
                data.lastPlayTime = currentPlayer.getPosition();
                var publisherId = "${param.publisherId}";
                var microPublishId = "${microPublishedId}";
                if (microPublishId != "" && microPublishId != "null") {
                    data.publishLessonId = microPublishId;
                }
                if (publisherId != "" && publisherId != "null") {
                    data.publisherId = publisherId;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/micropublish/saveRecord",
                    type: "POST",
                    data: data,
                    async: false,
                    success: function(result) {

                    }
                });
            }

    </script>
</html>