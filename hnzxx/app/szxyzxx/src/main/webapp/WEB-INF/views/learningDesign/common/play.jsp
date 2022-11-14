<%@page import="platform.education.resource.contants.StudyFinishedFlag"%>
<%@page import="platform.education.resource.contants.ResourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/jwplayer.jsp"%>
<title>课件播放</title>


<script type="text/javascript">
	//调用JWPlayer
	function callJwplay(httpUrl, width, height) {
		currentPlayer = jwplayer("res_player").setup({
			width : width,
			height : height,
			primary : "flash",
			autostart : true,
			//image: "/image/teacher/music2.jpg",
			file : httpUrl,
			events : {
				onPlay : function(event) {
				},
				onBeforePlay : function(event) {
				},
				onPause : function(event) {
				},
				onReady : function(event) {
				},
				onComplete : function(event) {
				},
				onSeek : function(event) {
				}
			}
		});
	}
</script>


</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="课件查看" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							课件查看
							<p style="float: right;" class="btn_link">
								<a class="a4" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回 </a>
							</p>
						</h3>
					</div>
					<div class="content-widgets" style="margin-bottom: 0">
						<div class="video_div">
							<div class="title_1">
								<p>正在查看: &nbsp;</p>
								<div class="item-title">
									<span
										class="${iconFn:getClassName(res.iconType)} icon-file res-iconb"
										style="margin-top: 10px;"></span> <span
										style="overflow: hidden; width: 694px; float: left; display: block; white-space: nowrap; text-overflow: ellipsis; word-wrap: normal;">
										<a href="javascript:void(0);" target="_blank">
											${res.resEnt.title}.${entity.extension}
										</a>
									</span>
								</div>
							</div>
							<div class="play_area_1" id="res_player">
								<div id="res_player">
								<c:choose>
									<c:when test='${entity.extension == "mp4" || entity.extension == "flv" || entity.extension == "mp3"}'>
										 <script type="text/javascript">
										 	var httpUrl = "${eurlFn:convertUrl(entity.relativePath)}";
										 	callJwplay(httpUrl, 786, 619);
										 </script>
									</c:when>
									<c:when test='${entity.extension == "swf"}'>
										<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/swfobject/swfobject.js"></script>
										<script type="text/javascript">
											var $fileUrl = '${eurlFn:convertUrl(entity.relativePath)}';
// 											var $fileUrl = "http://127.0.0.1:8082/develop/hnzhxy_1/2015-06/1038992-461.swf";
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
										    swfobject.embedSWF($fileUrl, "res_player","786px", "619px", swfVersionStr, xiSwfUrlStr, flashvars, params, attributes);
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
										<img id="download_img" src="${eurlFn:convertUrl(entity.relativePath)}" style="margin:0 auto;max-width:786px;max-height:619px;display:none;">
<!-- 										<img src="http://127.0.0.1:8082/develop/hnzhxy_1/2015-06/c5af5ea430880e954a37677b2e414d03.jpg" style="margin:0 auto;max-width:786px;max-height:619px;display:block"> -->
									</c:when>
									<c:otherwise>
										<img id="download_img" src="${pageContext.request.contextPath}/res/images/not_play.jpg" style="width:786px;height:619px;display: none;">
										<a id="download_a" style="display: none;" onclick="downloadRes('${res.resEnt.id}')" href="javascript:void(0)" class="download"></a>
									</c:otherwise>
								</c:choose>
								 	<p id="error_message" style="display:none;text-align:center;color:#888;font-size:20px;position:absolute;width:100%;top:135px;font-weight:bold;">此文档不支持预览，请下载查看</p>
                                    <iframe allowFullScreen="true"  id="pdfPlayer" name="pdfPlayer" src="<c:url value="/res/plugin/pdf/ROOT/web/viewer.html" />?file=<c:url value="" />" width="786" style="min-height:599px;display:none" scrolling=no></iframe>
								</div>
							</div>
							<div class="v_div">
								<div class="fx_left">
									<div class="fx_1">
										<a href="javascript:void(0)" class="a1" onclick="fav('${res.resEnt.id}')">收藏资源</a> 
										<a href="javascript:void(0)" class="a3" onclick="like('${res.resEnt.id}', this)"></a>
                                                                                <a onclick="downloadRes('${res.resEnt.id}')" href="javascript:void(0)" class="a2">下载</a>
									</div>
									<div class="fx_2">
										<p class="title">资源介绍</p>
										<div class="intro">
											<p>${res.resEnt.description == null || res.resEnt.description == "" ? "此资源暂无相关介绍" : res.resEnt.description}</p>
											<!-- 											  <p>  这里的5节课从文学常识、文言虚词、文言实词、文言句式、翻译、文意理解等方面入手，系统阐 -->
											<!-- 											释其中的规律，介绍行之有效的学习方法，可以在很大程度上减轻困难，提高效率。可以说是：把 -->
											<!-- 											握规律方向明，方法对头效率高。</p> -->
										</div>
									</div>
								</div>
								<div class="fx_right">
									<div class="g1">
                                                                                <img src="<avatar:avatar userId='${res.resEnt.userId}'></avatar:avatar>"/>
										<p class="p1">资源所属 :${douFn:getFieldVal('teacher', res.resEnt.userId)}</p>
<!-- 										<p class="p2">广东省佛山市惠景中学</p> -->
									</div>
								</div>
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
	
	function like(id, obj) {
// 		alert(resId + "_" + resType + "_" + title);
		var requestData = {"Id":id, "isLike" : true};
		$.post("${pageContext.request.contextPath}/resource/like", requestData, function(data, status) {
			if("success" === status) {
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

      function downloadRes(id){
           var url = "<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/download?Id="+id;
           var study = "${study}";
           if(study!=""&&study!="null"){
               //正在学习
               saveRecord("<%=StudyFinishedFlag.FINISHED%>",url)   
           }else{
               window.open(url,"_blank");
           }
       }
       
       function saveRecord(finishedFlag,url) {
                var data = {};
                data.microId = "${res.resEnt.objectId}";
                data.finishedFlag = finishedFlag;
                var microPublishId = "${microPublishedId}";
                var publisherId = "${param.publisherId}";
                if(microPublishId!=""&&microPublishId!="null"){
                    data.publishLessonId = microPublishId;
                }
                if (publisherId != "" && publisherId != "null") {
                    data.publisherId = publisherId;
                }
                $.ajax({
                    url: "${pageContext.request.contextPath}/learningDesignpublish/saveRecord",
                    type: "POST",
                    data: data,
                    async: false,
                    success: function(result) {
                         window.open(url,"_blank");
                    }
                });
            }
            
      $(function(){
          <c:if test="${res==null}">
              $.alert("该资源已被删除");
          </c:if>
        var hisUrl = document.referrer;
  		$(".btn_link .a4").attr("href",hisUrl);
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
</html>