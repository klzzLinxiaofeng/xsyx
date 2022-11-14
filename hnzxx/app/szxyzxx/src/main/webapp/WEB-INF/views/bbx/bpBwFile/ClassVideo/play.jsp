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
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<title>播放</title>


<style>
	.uploadify{position:absolute;left:11px;top:4px;opacity:0}
	.close_1{
	color:#999;
	position:relative;
	font-size:18px;
	font-weight:bold;
	cursor:pointer;
	left:8px;
 	top: -5px; 
	margin-right: 10px;
}
.text_name{color:#333;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;max-width:400px;display: inline-block;}
.text_num{float:right;line-height:40px;}
</style>
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
			<jsp:param value="班级视频播放" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<a class="a4" href="javascript:history.go(-1)"><i class="fa fa-reply"></i> 返回 </a>
					<div class="content-widgets" style="margin-bottom: 0">
						<div class="video_div">
							<div class="play_area_1" id="res_player">
								<div id="res_player">
								<c:choose>
									<c:when test='${entity.extension == "mp4" || entity.extension == "flv" || entity.extension == "mp3"}'>
										 <script type="text/javascript">
										 	var httpUrl = "${eurlFn:convertUrl(entity.relativePath)}";
										 	console.log("1: "+ httpUrl);
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
										<a id="download_a" style="display: none;" onclick="downloadRes('${res.resEnt.uuid}')" href="javascript:void(0)" class="download"></a>
									</c:otherwise>
								</c:choose>
								<p id="error_message" style="display:none;text-align:center;color:#888;font-size:20px;position:absolute;width:100%;top:135px;font-weight:bold;">此文档不支持预览，请下载查看</p>
                                    <iframe allowFullScreen="true"  id="pdfPlayer" name="pdfPlayer" src="<c:url value="/res/plugin/pdf/ROOT/web/viewer.html" />?file=<c:url value="" />" width="786" style="min-height:599px;display:none" scrolling=no></iframe>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">


</script>
</html>