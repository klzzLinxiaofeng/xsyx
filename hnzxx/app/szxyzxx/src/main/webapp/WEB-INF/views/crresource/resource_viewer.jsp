<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/jwplayer.jsp"%>
<title>学校列表</title>
<style>
	.pagination{
		float:none;
	}
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
	currentPlayer = jwplayer("res_player").setup({
		width : width,
		height : height,
		primary : "flash",
		autostart : true,
		file : httpUrl,
		playlist: [{
		    /* label属性不能为中文，如果为中文必须转码，因为label的属性值会作为cookie的一个属性名，记录播放进度 */
			sources: json
		}],
		events : {
			onPlay : function(event) {},
			onBeforePlay : function(event) {},
			onPause : function(event) {},
			onReady : function(event) {},
			onComplete : function(event) {},
			onSeek : function(event) {}
		}
	});
}

function callNormalJwplay(httpUrl, width, height) {
	currentPlayer = jwplayer("res_player").setup({
		width : width,
		height : height,
		primary : "flash",
		autostart : true,
		file : httpUrl,
		events : {
			onPlay : function(event) {},
			onBeforePlay : function(event) {},
			onPause : function(event) {},
			onReady : function(event) {},
			onComplete : function(event) {},
			onSeek : function(event) {}
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
						<h3>资源查看
							<p style="float: right;margin-bottom:0;" class="btn_link">
								<a class="a4" href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=${condition.stageCode}&subjectCode=${condition.subjectCode}&gradeCode=${condition.gradeCode}&versionCode=${condition.versionCode}&resType=${condition.resType}"><i class="fa fa-reply"></i> 返回 </a>
							</p>
						</h3>
					</div>
					<div class="content-widgets" style="margin-bottom: 0">
						<div class="video_div">
							<div class="title_1">
								<p>正在查看: &nbsp;</p>
								<div class="item-title">
									<span class="${iconFn:getClassName(res.iconType)} icon-file res-iconb" style="margin-top: 10px;"></span> 
									<span style="overflow: hidden; width: 670px; float: left; display: block; white-space: nowrap; text-overflow: ellipsis; word-wrap: normal;">
										<a href="javascript:void(0);" target="_blank">
											${res.title}.${entity.extension}
										</a>
									</span>
								</div>
							</div>
							<div class="play_area_1" id="res_player">
								<div id="res_player" style="position:relative">
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
										    var swfVersionStr = "10.0.0";
										    var xiSwfUrlStr = "playerProductInstall.swf";
										    var flashvars = {};
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
									                <p>
								                		Either scripts and active content are not permitted to run or Adobe Flash Player version
								                		10.0.0 or greater is not installed.
									                </p>
									                <a href="http://www.adobe.com/go/getflashplayer">
									                    <img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash Player" />
									                </a>
									            </object>
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
									<iframe allowFullScreen="true"  id="pdfPlayer" name="pdfPlayer" src="<c:url value="/res/plugin/pdf/ROOT/web/viewer.html" />?file=<c:url value="" />" width="786" style="min-height:619px;display:none;    border: 1px solid #cdcdcd;" scrolling=no></iframe>
									<img id="download_img" src="${pageContext.request.contextPath}/res/images/not_play.jpg" style="width:786px;height:619px;display:none">
									<p id="error_message" style="display:none;text-align:center;color:#888;font-size:20px;position:absolute;width:100%;top:135px;font-weight:bold;">此文档不支持预览，请下载查看</p>
									<a id="download_a" onclick="downloadRes(${res.integral}, ${res.id}, '${res.title }');" href="javascript:void(0)" style="display:none" class="download"></a>
									</c:otherwise>
								</c:choose>
								</div>
							</div>
							<div class="v_div">
								<div class="fx_left">
									<div class="fx_1">
										<a href="javascript:void(0)" class="a1" onclick="fav('${res.id}');">收藏资源</a>
										<c:choose>
											<c:when test="${resFn:getResIsScoring(res.id, sessionScope[sca:currentUserKey()].userId) }">
												<div class="pf_flower_view">
													<c:if test="${res.scoringCount==0 }">
														<p style="width:0%"></p>
													</c:if>
													<c:if test="${res.scoringCount!=0 }">
														<p style="width:${100*res.score/(res.scoringCount*5) }%"></p>
													</c:if>
												</div> 
											</c:when>
											<c:otherwise>
												<div class="pf_flower" >
													<ul>
													  <li><a href="javascript:void(0);" onclick="updateScoring(${res.id}, 1);"></a></li>
													  <li><a href="javascript:void(0);" onclick="updateScoring(${res.id}, 2);"></a></li>
													  <li><a href="javascript:void(0);" onclick="updateScoring(${res.id}, 3);"></a></li>
													  <li><a href="javascript:void(0);" onclick="updateScoring(${res.id}, 4);"></a></li>
													  <li><a href="javascript:void(0);" onclick="updateScoring(${res.id}, 5);"></a></li>
													 </ul>
												</div>
											</c:otherwise>
										</c:choose>
										<a href="javascript:void(0)" class="a3" onclick="like('${res.id}', this)" >${res.likeCount == null ? 0 : res.likeCount}</a>
                                        <a onclick="downloadRes(${res.integral}, ${res.id}, '${res.title }');" href="javascript:void(0)" class="a2" style="width:100px;">下载
                                        (<c:if test="${res.integral==null ||  res.integral==0}"><span style="color:green">免费</span></c:if>
										 <c:if test="${res.integral!=null &&  res.integral!=0}"><span style="color:#ff0000">${res.integral}积分</span></c:if>)
										 </a>
									</div>
									<div class="fx_2">
										<p class="title">资源介绍</p>
										<div class="intro">
											<p>${res.description == null || res.description == "" ? "此资源暂无相关介绍" : res.description}</p>
										</div>
									</div>
									<div id="commentList">
										<jsp:include page="./comment.jsp" />
									</div>
									<div style="text-align:right">
											<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
												<jsp:param name="id" value="commentList" />
												<jsp:param name="url" value="/message/comment/index" />
												<jsp:param name="pageSize" value="${page.pageSize}" />
											</jsp:include>
										</div>
								</div>
								<div class="fx_right">
									<div class="g1">
										<a href="${ctp}/resource/owner/list?ownerId=${res.userId}" style="display:block;height:100%">
										<img src="<avatar:avatar userId='${res.userId}'></avatar:avatar>">
										<p class="p1">分享者：${douFn:getFieldVal('teacher', res.userId)}</p>
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
	
	initComment();
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
			initComment();
		} else {
			$.error("服务器响应异常!");
		}
	});
}

function initComment(){
	var val = {};
	val.resType = resType;
	val.objectId = objId;
	var id = "commentList";
	var url = "/message/comment/index";
	myPagination(id, val, url);
}

function like(id, obj) {
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

function previewLp(lpId) {
	var url = "${pageContext.request.contextPath}/learningPlan/edit?type=view&editable=false&id="+lpId;
	window.open(url, "预览");
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
