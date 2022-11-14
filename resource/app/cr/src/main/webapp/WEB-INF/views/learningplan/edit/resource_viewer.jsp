<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/jwplayer.jsp"%>
<title>资源预览</title>
<style>
	.pagination{
		float:none;
	}
	#res_player_wrapper{
		margin:0 auto;
	}
	#download_img{display:block;}
	.video_div .play_area_1 .download{top:206px;}
	.video_div .play_area_1{width:669px;margin:0 auto;}
</style>

<script type="text/javascript">
	function submitJave(entityFileUUID,httpUrl){		
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
				callNormalJwplay(httpUrl, 669, 359);
				$.submitJaveSingleFile({"entityFileUUID":entityFileUUID},function(){
					
				});	
			}else{
				callJwplay(httpUrl, 669, 359, sourceJson);
			}
		});	
	};

	//调用JWPlayer
	function callJwplay(httpUrl, width, height,json) {
//  		var json = "[{'file' : httpUrl, 'label' : '原画'}, {'file' : httpUrl, 'label' : '高清版'}, {'file' : httpUrl, 'label' : '移动版'}]";
//   		var json = submitJave();
		//alert(JSON.stringify(json));
		currentPlayer = jwplayer("res_player").setup({
			width : width,
			height : height,
			primary : "flash",
			autostart : true,
			//image: "/image/teacher/music2.jpg",
			file : httpUrl,
			playlist: [{
			    /* label属性不能为中文，如果为中文必须转码，因为label的属性值会作为cookie的一个属性名，记录播放进度 */
				sources: json
			}],
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
	
	function callNormalJwplay(httpUrl, width, height) {
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
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets white" style="border:0;margin-bottom:0;padding-bottom:20px;">
				<div class="content-widgets" style="margin-bottom: 0">
					<div style="padding-left:20px;font-size:12px;line-height:30px;">
						<c:if test="${canReturn }">
							<a href="javascript:void(0)" onclick="back(this)">< 返回列表</a>
						</c:if>
					</div> 
					<div class="video_div">
						<div class="play_area_1" id="res_player" style="min-height:350px;">
							<div id="res_player" style="position:relative">
							<c:choose>
								<c:when test='${entity.extension == "mp4" || entity.extension == "flv" || entity.extension == "mp3"}'>
									 <script type="text/javascript">
										 	var httpUrl = "${eurlFn:convertUrl(entity.relativePath)}";
										 	var uuid = "${entity.uuid}";
										 	submitJave(uuid, httpUrl);
// 										 	callJwplay(httpUrl, 786, 619);
										 </script>
								</c:when>
								<c:when test='${entity.extension == "swf"}'>
									<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/swfobject/swfobject.js"></script>
									<script type="text/javascript">
											var $fileUrl = '${eurlFn:convertUrl(entity.relativePath)}';
// 											var $fileUrl = "http://${pageContext.request.contextPath}:8082/develop/hnzhxy_1/2015-06/1038992-461.swf";
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
								    <noscript>
								        <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%" id="swfplayer">
								            <param name="movie" value="${eurlFn:convertUrl(entity.relativePath)}" />
								            <param name="quality" value="high" />
								            <param name="bgcolor" value="#ffffff" />
								            <param name="allowScriptAccess" value="always" />
								            <param name="allowFullScreen" value="true" />
								            <object type="application/x-shockwave-flash" data='${eurlFn:convertUrl(entity.relativePath)}' width="100%" height="100%">
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
									<img src="${eurlFn:convertUrl(entity.relativePath)}" style="margin:0 auto;max-width:786px;max-height:619px;display:block">
								</c:when>
								<c:otherwise>
								<iframe allowFullScreen="true"  id="pdfPlayer" name="pdfPlayer" src="<c:url value="/res/plugin/pdf/ROOT/web/viewer.html" />?file=<c:url value="" />" width="669" style="min-height:619px;display:none" scrolling=no></iframe>
								<img id="download_img" src="${pageContext.request.contextPath}/res/images/not_play.jpg" style="height:380px;margin:0 auto;display:none">
								<p id="error_message" style="display:none;text-align:center;color:#888;font-size:20px;position:absolute;width:100%;top:135px;font-weight:bold;">此文档不支持预览，请下载查看</p>
								<a id="download_a" target="_blank" href="${pageContext.request.contextPath}/resource/download?Id=${res.id}" style="display:none" class="download"></a>
								</c:otherwise>
							</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var objId = "${res.objectId}";
	var resType = "${res.resType}";
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
	$(function(){
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
	
	function comment(){
		var content = $("#content1").val() + "";
		if(content === "" || content === "undefined"){
			$.alert("请输入评论再发表！");
			return;
		}
		var requestData = {"objectId" : objId,"resType" : resType, resourceId:"${res.id}","content" : content};
		$.post("${pageContext.request.contextPath}/message/comment/creator", requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				if("success" === data.info) {
					$.success("发表成功");
				} else {
					$.error("发表失败");
				}
			} else {
				$.error("服务器响应异常!");
			}
		});
	}
	
	function back(obj) {
		var sign = $("#sign").val();
		var personSign = $("#personSign").val();
		if(sign=="school") {
			$("#schoolMicro").click();
		} else {
			$("#myMicro").click(function() {});
			if(personSign=="myresource") {
				$("#myresource").click();
			} else if(personSign=="favresource") {
				$("#favresource").click();
			} else if(personSign=="myshare") {
				$("#myshare").click();
			}
		}
	}
	
	function like(id, obj) {
// 		alert(resId + "_" + resType + "_" + title);
		var requestData = {"Id" : id, "isLike" : true};
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
	
	/* function SetWinHeight(obj) 
	{ 
	var pdfPlayer=obj; 
	if (document.getElementById) 
	{ 
	if (pdfPlayer && !window.opera) 
	{ 
	if (pdfPlayer.contentDocument && pdfPlayer.contentDocument.body.offsetHeight) 
	pdfPlayer.height = pdfPlayer.contentDocument.body.offsetHeight; 
	else if(pdfPlayer.Document && pdfPlayer.Document.body.scrollHeight) 
	pdfPlayer.height = pdfPlayer.Document.body.scrollHeight; 
	} 
	} 
	}  */
// 	function SetWinHeight(obj){	
// 		$("#pdfPlayer").load(function(){
// 		var mainheight = $(this).contents().find("#viewerContainer #viewer").innerHeight();
// 		alert(mainheight)
// 		$(this).height(mainheight);
// 		});
// 	}
	
</script>
</html>
