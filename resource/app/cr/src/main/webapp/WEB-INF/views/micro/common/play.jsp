<%@page import="platform.education.resource.contants.ResourceType"%>
<%@page import="platform.education.resource.contants.StudyFinishedFlag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>${res.resEnt.title}</title>
        <%@ include file="/views/embedded/plugin/jwplayer.jsp"%>
    </head>
<style>
	.pagination{
		float:none;
	}
</style>
    <body>
        <div class="container-fluid">
            <input id="microId" type="hidden" value="${res.resEnt.objectId}">
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
                        <div class="content-widgets" style="margin-bottom: 0">
                            <div class="video_div">
                                <div class="title_1">
                                    <p>正在查看: &nbsp;</p>
                                    <div class="item-title">
                                        <span style="overflow: hidden; width: 694px; float: left; display: block; white-space: nowrap; text-overflow: ellipsis; word-wrap: normal;">
                                            <a href="javascript:void(0);">
                                                ${res.resEnt.title}
                                                <input id="httpUrl_${res.resEnt.objectId}" type="hidden" value="<entity:getHttpUrl uuid="${entity.uuid}"/>" />    
                                            </a>
                                        </span>
                                    </div>
                                </div>
                                <div class="play_area">
                                    <div class="p_detail" >
                                        <div class="play_1">
                                            <div class="p_left" style="height:auto;">
                                                <div id="detailContent">
                                                	<div id="html5_player" style="display: none">
		                                        		<jsp:include page="html5_Player.jsp"/>
		                                        	</div>
                                                </div>
                                            </div>
                                            <div class="clear"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="v_div">
                                    <div class="fx_left">
                                        <div class="fx_1">
                                            <a href="javascript:void(0)" class="a1" onclick="fav('${res.resEnt.id}');">收藏资源</a>
                                            <c:choose>
												<c:when test="${resFn:getResIsScoring(res.resEnt.id, sessionScope[sca:currentUserKey()].userId) }">
													<div class="pf_flower_view">
														<c:if test="${res.resEnt.scoringCount==0 }">
															<p style="width:0%"></p>
														</c:if>
														<c:if test="${res.resEnt.scoringCount!=0 }">
															<p style="width:${100*res.resEnt.score/(res.resEnt.scoringCount*5) }%"></p>
														</c:if>
													</div> 
												</c:when>
												<c:otherwise>
													<div class="pf_flower" >
														<ul>
														  <li><a href="javascript:void(0);" onclick="updateScoring(${res.resEnt.id}, 1);"></a></li>
														  <li><a href="javascript:void(0);" onclick="updateScoring(${res.resEnt.id}, 2);"></a></li>
														  <li><a href="javascript:void(0);" onclick="updateScoring(${res.resEnt.id}, 3);"></a></li>
														  <li><a href="javascript:void(0);" onclick="updateScoring(${res.resEnt.id}, 4);"></a></li>
														  <li><a href="javascript:void(0);" onclick="updateScoring(${res.resEnt.id}, 5);"></a></li>
														 </ul>
													</div>
												</c:otherwise>
											</c:choose>
                                            <a href="javascript:void(0)" class="a3" onclick="like(this)" style="width:0;"></a>
                                            <a href="javascript:void(0)" onclick="downloadRes(${res.resEnt.integral}, ${res.resEnt.id}, '${res.resEnt.title}')" class="a2" style="width:100px;">下载
                                            (<c:if test="${res.resEnt.integral==null ||  res.resEnt.integral==0}"><span style="color:green">免费</span></c:if>
										  <c:if test="${res.resEnt.integral!=null &&  res.resEnt.integral!=0}"><span style="color:#ff0000">${res.resEnt.integral}积分</span></c:if>)
											</a>
                                        </div>
                                        <div class="fx_2">
                                            <p class="title">资源介绍</p>
                                            <div class="intro">
                                                <p>${res.resEnt.description == null || res.resEnt.description == "" ? "此微课暂无相关介绍" : res.resEnt.description}</p>
                                                <!-- 											  <p>  这里的5节课从文学常识、文言虚词、文言实词、文言句式、翻译、文意理解等方面入手，系统阐 -->
                                                <!-- 											释其中的规律，介绍行之有效的学习方法，可以在很大程度上减轻困难，提高效率。可以说是：把 -->
                                                <!-- 											握规律方向明，方法对头效率高。</p> -->
                                            </div>
                                        </div>
                                        <div id="commentList">
											<jsp:include page="/WEB-INF/views/resource/comment.jsp" />
										</div>
										<div style="text-align:center">
											<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
												<jsp:param name="id" value="commentList" />
												<jsp:param name="url" value="/message/comment/index" />
												<jsp:param name="pageSize" value="${page.pageSize}" />
											</jsp:include>
										</div>
                                    </div>
                                    <div class="fx_right">
                                        <div class="g1">
                                        	<a href="${ctp}/resource/owner/list?ownerId=${res.resEnt.userId}" style="display:block;height:100%">
                                            	<img src="<avatar:avatar userId='${res.resEnt.userId}'></avatar:avatar>"/>
                                            	<p class="p1">资源所属 :${douFn:getFieldVal('user', res.resEnt.userId)}</p>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="clear"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </body>
    <script type="text/javascript">
    function downloadRes(integral, resId, title) {
    	if(integral==null || integral==0) {
    		window.location.href="${pageContext.request.contextPath}/resource/download?Id=" + resId;
    		return;
    	}
        $.ajax({
            url: "${pageContext.request.contextPath}/resource/getFileSize",
            type: "POST",
            data: {"resId": resId},
            async: true,
            success: function(data) {
            	if(data==0) {
            		$.alert("资源已经被删除");
            	} else {
            		var url = "${pageContext.request.contextPath}/resource/integral?resId="+resId+"&integral="+integral;
            		$.initWinOnTopFromLeft_bbx(title, url, "380", "230")
            	}
            }
       });
    }
    
		var initFlag = true;
        <c:if test="${res==null}">
//                                         $.alert("该微课已被删除");
                                        initFlag = false;
        </c:if>

       
            var width = 900;
            var height = 500;
            var currentHttpUrl;
            var currentMid;
            var currentPlayer;
            var lastPlayTime;
            $(function() {
            	
                if (initFlag) {
                    currentHttpUrl = $("input[id^='httpUrl_']").eq(0).val();
                    if (currentHttpUrl == null || currentHttpUrl == "") {
                        $.alert("该微课已被删除");
                    }
                    currentMid = $("input[id^='httpUrl_']").eq(0).attr("id").split("_")[1];
                    getRecord();
                    
//                     callJwplay(currentHttpUrl,"${micro.type}", width, height);
					submitJave('${entity.uuid}',currentHttpUrl,'common_micro');
                    //轮询记录当前播放进度,15秒记录一次
//                     unfinishInterval = window.setInterval('saveRecord()', 15000);
                }
                initComment();
            });
        	function comment(){
        		var content = $("#content1").val() + "";
        		if(content === "" || content === "undefined"){
        			$.alert("请输入评论再发表！");
        			return;
        		}
        		 var requestData = {"objectId": "${res.resEnt.objectId}", "resType": "${res.resEnt.resType}", "content": content,"resourceId":"${res.resEnt.id}"};
        		$.post("${pageContext.request.contextPath}/message/comment/creator", requestData, function(data, status) {
        			if("success" === status) {
        				data = eval("(" + data + ")");
        				if("success" === data.info) {
        					$.success("发表成功");
        				} else {
        					$.error("发表失败");
        				}
        				initComment();
        			} else {
        				$.error("服务器响应异常!");
        			}
        		});
        	}
        	function initComment(){
        		var val = {};
        		val.resourceId = "${res.resEnt.id}";
        		 val.resType = "${res.resEnt.resType}";
        		var id = "commentList";
        		var url = "/message/comment/index?dm=${param.dm}";
        		myPagination(id, val, url);
        	}
            function selectPlay(mid) {
                var httpUrl = $("#httpUrl_" + mid).val();
                currentMid = mid;
                currentHttpUrl = httpUrl;
                $("#playTitle").text($.trim($("#microDataLi_" + mid + " .de_2").text()));
                getRecord();
//                 callJwplay(currentHttpUrl,"${micro.type}", width, height);
                //调用转换任务
                submitJave('${entity.uuid}',currentHttpUrl,'common_micro');
            }

            function getRecord() {
                var data = {};
                data.microId = currentMid;
                $.ajax({
                    url: "${pageContext.request.contextPath}/micro/getRecord",
                    type: "POST",
                    data: data,
                    async: false,
                    success: function(result) {
                        if (result != "fail") {
                            //跳到记录的播放进度
                            lastPlayTime = result;
                            //$.alert("上次播放到"+lastPlayTime+"秒")
                            //currentPlayer.seek(lastPlayTime); //从指定位置开始播放(单位：秒)
                        }
                    }
                });
            }

            function saveRecord(finishedFlag) {
                var data = {};
                data.microId = currentMid;
                data.finishedFlag = finishedFlag;
                data.lastPlayTime = currentPlayer.getPosition();
                $.ajax({
                    url: "${pageContext.request.contextPath}/micro/saveRecord",
                    type: "POST",
                    data: data,
                    async: false,
                    success: function(result) {

                    }
                });
            }
            function fav(id) {
        		var requestData = {"id":id, "isFav" : true};
        		$.post("${pageContext.request.contextPath}/resource/fav", requestData, function(data, status) {
        			if("success" === status) {
        				if ("faved" === data) {
        					$.success("已收藏过");
        				} else if ("success" === data) {
        					$.success("收藏成功");
        				} else {
        					$.error("收藏失败");
        				}		
        			} else {
        				$.error("服务器响应异常!");
        			}
        		});
        	}
            function like(obj) {
                var requestData = {"Id":"${res.resEnt.id}", "isLike": true};
                $.post("${pageContext.request.contextPath}/resource/like", requestData, function(data, status) {
                    if ("success" === status) {
                        if ("faved" === data) {
                            $.success("已赞过");
                        } else if ("success" === data) {
                            var likeCount = $(obj).html();
                            $(obj).html(parseInt(likeCount) + 1);
                            $.success("点赞成功");
                        } else {
                            $.error("点赞失败");
                        }
                    } else {
                        $.error("服务器响应异常!");
                    }
                });
            }
			
            
            function submitJave(entityFileUUID,httpUrl,type){
        		$.getJaveConversionResult({"entityFileUUID":entityFileUUID},function(data){
        			var sourceJson = [];
        			var length  = Object.keys(data).length;
        			if(length == 3){
        				for(var key in data){				
        					var curJson = {};
        					if(key == "MOBILE"){
        						curJson.default = true;
        					}
        					curJson.file = data[key];
        					curJson.label = key;
        				    sourceJson.push(curJson);
        				}
        			}
        			if(length == 2){
        				for(var key in data){				
        					var curJson = {};
        					if(key == "SD"){
        						curJson.default = true;
        					}
        					curJson.file = data[key];
        					curJson.label = key;
        				    sourceJson.push(curJson);
        				}
        			}
        			if(length == 1){
        				for(var key in data){				
        					var curJson = {};
        					if(key == "HD"){
        						curJson.default = true;
        					}
        					curJson.file = data[key];
        					curJson.label = key;
        				    sourceJson.push(curJson);
        				}
        			}
        			
        			if(sourceJson.length == 0){
        				callNormalJwplay(httpUrl,type,786, 619);
        				$.submitJaveSingleFile({"entityFileUUID":entityFileUUID},function(){
        					
        				});	
        			}else{
        				
        				callJwplay(httpUrl,type,786, 619, sourceJson);
        			}
        		});	
        	};
        	
        	//调用JWPlayer
            function callNormalJwplay(httpUrl,type, width, height) {
            	if("${type}" === type || "null" === type){
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
	                        "src/main/webapp/WEB-INF/views/micro/common/play.jsp"},
	                        onBeforePlay: function(event) {
	                        },
	                        onPause: function(event) {
	                        },
	                        onReady: function(event) {
	                        },
	                        onComplete: function(event) {
	                            saveRecord("<%=StudyFinishedFlag.FINISHED%>");
	                        },
	                        onSeek: function(event) {
	                        }
	                    }
	                });
            	}
            }
        	
          //调用JWPlayer
            function callJwplay(httpUrl,type, width, height,json) {
            	if("${type}" === type || "null" === type){
            		$("#html5_player").show();
            		
            	}else{
	                currentPlayer = jwplayer("detailContent").setup({
	                    width: width,
	                    height: height,
	                    primary: "flash",
	                    autostart: true,
	                    //image: "/image/teacher/music2.jpg",
	                    file: httpUrl,
	                    playlist: [{
	        			    /* label属性不能为中文，如果为中文必须转码，因为label的属性值会作为cookie的一个属性名，记录播放进度 */
	        				sources: json
	        			}],
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
	                            saveRecord("<%=StudyFinishedFlag.FINISHED%>");
	                        },
	                        onSeek: function(event) {
	                        }
	                    }
	                });
            	}
            }
          
            var tab = 0;

            function updateScoring(resId, score) {
            	if(tab==0) {
            		$.ajax({
            	        url: "${pageContext.request.contextPath}/resource/updateScore",
            	        type: "POST",
            	        data: {"resId": resId, "score":score},
            	        async: false,
            	        success: function(data) {
            	        	tab = 1;
            	        }
            	   });
            	}
            }

            var num=finalnum = tempnum= 0;
            var lis = $(".pf_flower li");

            function fnShow(num) {
            	if(tab==0) {
            		finalnum= num || tempnum;//如果传入的num为0，则finalnum取tempnum的值
            		for (var i = 0; i < lis.length; i++) {
            			lis[i].className = i < finalnum? "light" : "";//点亮星星就是加class为light的样式
            		}
            	}
            }

            for (var i = 1; i <= lis.length; i++) {
            	lis[i - 1].index = i;
            	lis[i - 1].onmouseover = function() { //鼠标经过点亮星星。
            		fnShow(this.index);//传入的值为正，就是finalnum
            	}
            	lis[i - 1].onmouseout = function() { //鼠标离开时星星变暗
            		fnShow(0);//传入值为0，finalnum为tempnum,初始为0
            	}
            	lis[i - 1].onclick = function() { //鼠标点击,同时会调用onmouseout,改变tempnum值点亮星星
            		tempnum= this.index;
            	}
            }
    </script>
</html>