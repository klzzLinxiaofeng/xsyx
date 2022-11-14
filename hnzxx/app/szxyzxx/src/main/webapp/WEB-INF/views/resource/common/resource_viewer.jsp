<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/jwplayer.jsp"%>
<title>学校列表</title>


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
				callNormalJwplay(httpUrl, 786, 619);
				$.submitJaveSingleFile({"entityFileUUID":entityFileUUID},function(){
					
				});	
			}else{
				
				callJwplay(httpUrl, 786, 619, sourceJson);
			}
		});	
	};
	
	//调用JWPlayer
	function callJwplay(httpUrl, width, height,json) {
	//		var json = "[{'file' : httpUrl, 'label' : '原画'}, {'file' : httpUrl, 'label' : '高清版'}, {'file' : httpUrl, 'label' : '移动版'}]";
	//		var json = submitJave();
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
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="学科资源" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							资源查看
							<p style="float: right;" class="btn_link">
								<a class="a4" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回 </a>
							</p>
						</h3>
					</div>
					<div class="content-widgets" style="margin-bottom: 0; padding-bottom :30px">
						<div class="video_div">
							<div class="title_1">
								<p>正在查看: &nbsp;</p>
								<div class="item-title">
									<span
										class="${iconFn:getClassName(res.iconType)} icon-file res-iconb"
										style="margin-top: 10px;"></span> <span
										style="overflow: hidden; width: 694px; float: left; display: block; white-space: nowrap; text-overflow: ellipsis; word-wrap: normal;">
										<a href="javascript:void(0);" target="_blank">
											${res.title}.${entity.extension}
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
										 	var uuid = "${entity.uuid}";
										 	submitJave(uuid, httpUrl);
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
										<img src="${eurlFn:convertUrl(entity.relativePath)}" style="margin:0 auto;max-width:786px;max-height:619px;display:block">
<!-- 										<img src="http://127.0.0.1:8082/develop/hnzhxy_1/2015-06/c5af5ea430880e954a37677b2e414d03.jpg" style="margin:0 auto;max-width:786px;max-height:619px;display:block"> -->
									</c:when>
									<c:otherwise>
										<iframe allowFullScreen="true"  id="pdfPlayer" name="pdfPlayer" src="<c:url value="/res/plugin/pdf/ROOT/web/viewer.html" />?file=<c:url value="" />" width="786" style="min-height:619px;display:none;    border: 1px solid #cdcdcd;" scrolling=no></iframe>
										<img id="download_img" src="${pageContext.request.contextPath}/res/images/not_play.jpg" style="width:786px;height:619px;display:none">
										<p id="error_message" style="display:none;text-align:center;color:#888;font-size:20px;position:absolute;width:100%;top:135px;font-weight:bold;">此文档不支持预览，请下载查看</p>
										<a target="_blank" href="${pageContext.request.contextPath}/resource/download?Id=${res.id}" class="download"></a>
									</c:otherwise>
								</c:choose>
								</div>
							</div>
							<div class="v_div">
								<div class="fx_left">
									<div class="fx_1">
										<a href="javascript:void(0)" class="a1" onclick="fav('${res.id}');">收藏资源</a> 
										<a href="javascript:void(0)" class="a3" onclick="like('${res.id}',  this)">${res.likeCount == null ? 0 : res.likeCount}</a>
										<a id="download_a" target="_blank" href="${pageContext.request.contextPath}/resource/download?Id=${res.id}" style="display:none" class="download"></a>
									</div>
									<div class="fx_2">
										<p class="title">资源介绍</p>
										<div class="intro">
											<p>${res.description == null || res.description == "" ? "此资源暂无相关介绍" : res.description}</p>
											<!-- 											  <p>  这里的5节课从文学常识、文言虚词、文言实词、文言句式、翻译、文意理解等方面入手，系统阐 -->
											<!-- 											释其中的规律，介绍行之有效的学习方法，可以在很大程度上减轻困难，提高效率。可以说是：把 -->
											<!-- 											握规律方向明，方法对头效率高。</p> -->
										</div>
									</div>
								</div>
								<div class="fx_right">
									<div class="g1">
										<img
											src="${pageContext.request.contextPath}/res/images/ckk.png">
										<p class="p1">分享者：${douFn:getFieldVal('teacher', res.userId)}</p>
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
		var requestData = {"Id":id, "isFav" : true};
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

	$(function() {
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

	function like(id, obj) {
// 		alert(resId + "_" + resType + "_" + title);
		var requestData = {"Id":id,  "isLike" : true};
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
	
</script>
</html>