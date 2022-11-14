<%@page import="platform.education.resource.contants.ResourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <%@ include file="/views/embedded/plugin/jwplayer.jsp"%>
        <title>预览</title>
        <style>
            .video_show{
                width:509px;
                height:402px;
                margin:10px auto;
                background-color:#fff;
            }
            .play_area_1{
            	position:relative;
            }
             .play_area_1 .download {
                	background: url("${pageContext.request.contextPath}/res/css/extra/images/down_load.png") no-repeat;
                	display: block;
                	width: 32px;
                	height: 32px;
                	position: absolute;
                	left: 574px;
                	top: 203px;
                	}
                	.play_area_1 .download:hover{
                		background-position: -61px 0;
                	}
        </style>
        <script type="text/javascript">
            <c:if test="${entity==null}">
            $.alert("该课件已被删除");
            $(function() {
                $.closeWindow();
            })
            </c:if>

                //调用JWPlayer
                function callJwplay(httpUrl, width, height) {
                    currentPlayer = jwplayer("res_player").setup({
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
              $(function(){
            	  $.submitSingleFile({"entityFileUUID":"${entity.uuid}"},function(){
          			$.getConversionResult({"entityFileUUID":"${entity.uuid}"},function(data){
          				var result = data.result;
          				var defUrl = "${ctp}/res/plugin/pdf/ROOT/web/viewer.html?file=";
          				if(result != null){
          					if(result == 4 || result == 8){
          						var pdfUrl = data.pdfUrl;
          						if(pdfUrl != null && pdfUrl != ""){
          							$("#pdfPlayer").attr("src",defUrl + pdfUrl);
          							$("#pdfPlayer").show();

          						}
          					}else if(result == 3){
          						$("#error_message").show();
          						$("#download_img").show();
          						$("#download_a").show();
          					}
          				}else {
          					$("#download_img").show();
          					$("#download_a").show();
          				}
          			});
          		});
              });
        </script>
    </head>
    <body style="background-color: #fff !important">
        <div class="row-fluid ">
            <div class="span12">
                <div style="margin-bottom: 0" class="content-widgets">
                    <div style="padding: 20px 0 0;" class="widget-container">
                        <div class="play_area_1" id="res_player">
                            <div id="res_player">
                                <c:choose>
                                    <c:when test='${entity.extension == "mp4" || entity.extension == "flv" || entity.extension == "mp3"}'>
                                        <script type="text/javascript">
                                            var httpUrl = "${eurlFn:convertUrl(entity.relativePath)}";
                                            callJwplay(httpUrl, 700, 390);
                                        </script>
                                    </c:when>
                                    <c:when test='${entity.extension == "swf"}'>
                                        <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/swfobject/swfobject.js"></script>
                                        <script type="text/javascript">
                                            var $fileUrl = '${eurlFn:convertUrl(entity.relativePath)}';
                                            var swfVersionStr = "10.0.0";
                                            var xiSwfUrlStr = "playerProductInstall.swf";
                                            var flashvars = {
                                            };
                                            var params = {};
                                            params.quality = "high";
                                            params.bgcolor = "#ffffff";
                                            params.allowscriptaccess = "always";
                                            params.allowfullscreen = "true";
                                            var attributes = {};
                                            attributes.id = "swfplayer";
                                            attributes.name = "swfplayer";
                                            attributes.align = "middle";
                                            swfobject.embedSWF($fileUrl, "res_player", "786px", "619px", swfVersionStr, xiSwfUrlStr, flashvars, params, attributes);
                                            swfobject.createCSS("#res_player", "display:block;text-align:left;");
                                        </script>
                                        <!-- 									    <div id="video"> -->
                                        <!-- 									        <p> -->
                                        <!-- 										        To view this page ensure that Adobe Flash Player version -->
                                        <!-- 													10.0.0 or greater is installed. -->
                                        <!-- 									        </p> -->
                                        <!-- 									    </div> -->
                                        <noscript>
                                            <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%" id="swfplayer">
                                                <param name="movie" value="${eurlFn:convertUrl(entity.relativePath)}" />
                                                <!-- 									            <param name="movie" value="http://127.0.0.1:8082/develop/hnzhxy_1/2015-06/1038992-461.swf" /> -->
                                                <param name="quality" value="high" />
                                                <param name="bgcolor" value="#ffffff" />
                                                <param name="allowScriptAccess" value="always" />
                                                <param name="allowFullScreen" value="true" />
                                                <!--[if !IE]>-->
                                                <object type="application/x-shockwave-flash" data='${eurlFn:convertUrl(entity.relativePath)}' width="100%" height="100%">
                                                    <!-- 									             <object type="application/x-shockwave-flash" data='http://127.0.0.1:8082/develop/hnzhxy_1/2015-06/1038992-461.swf' width="100%" height="100%"> -->
                                                    <param name="quality" value="high" />
                                                    <param name="bgcolor" value="#ffffff" />
                                                    <param name="allowScriptAccess" value="always" />
                                                    <param name="allowFullScreen" value="true" />
                                                    <!--<![endif]-->
                                                    <!--[if gte IE 6]>-->
                                                    <p>
                                                        Either scripts and active content are not permitted to run or Adobe Flash Player version
                                                        10.0.0 or greater is not installed.
                                                    </p>
                                                    <!--<![endif]-->
                                                    <a href="http://www.adobe.com/go/getflashplayer">
                                                        <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
                                                    </a>
                                                    <!--[if !IE]>-->
                                                </object>
                                                <!--<![endif]-->
                                            </object>
                                        </noscript>
                                    </c:when>

                                    <c:when test='${entity.extension ==  "mp3"}'>
                                        <embed src="${eurlFn:convertUrl(entity.relativePath)}" hidden="true" autostart="true" loop="true">
                                        </c:when>


                                        <c:when test='${entity.extension == "jpeg" || entity.extension == "jpg" || entity.extension == "gif" || entity.extension == "png"}'>
                                            <img id="download_img" src="${eurlFn:convertUrl(entity.relativePath)}" style="margin:0 auto;max-width:786px;max-height:420px;display:none;">
                                            <!-- 										<img src="http://127.0.0.1:8082/develop/hnzhxy_1/2015-06/c5af5ea430880e954a37677b2e414d03.jpg" style="margin:0 auto;max-width:786px;max-height:619px;display:block"> -->
                                        </c:when>
                                        <c:otherwise>
                                            <img id="download_img" src="${pageContext.request.contextPath}/res/images/not_play.jpg" style="width:786px;height:420px;display: none;">
                                            <a id="download_a" style="display: none;" target="_blank" href="/resource/downloadByObject?objectId=${em.uuid}&resType=<%=ResourceType.LEARNING_DESIGN%>" class="download"></a>
                                        </c:otherwise>
                                    </c:choose>
                                    <p id="error_message" style="display:none;text-align:center;color:#888;font-size:20px;position:absolute;width:100%;top:135px;font-weight:bold;">此文档不支持预览，请下载查看</p>
                                    <iframe allowFullScreen="true"  id="pdfPlayer" name="pdfPlayer" src="<c:url value="/res/plugin/pdf/ROOT/web/viewer.html" />?file=<c:url value="" />" width="690" style="min-height:380px;display:none" scrolling=no></iframe>
                            </div>
                        </div>
                        <div class="v_div">
                            <div class="fx_left">
                                <div class="fx_1">
                                    <%-- <a href="${eurlFn:convertUrl(entity.relativePath)}" class="a2">下载</a> --%>
                                </div>
                            </div>
                        </div>
                        <div class="form-actions tan_bottom">
                            <button class="btn" onclick="$.closeWindow();" type="button">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>