<%@ page language="java" 
    pageEncoding="utf-8"%>
<%-- <%@include file="/views/embedded/taglib.jsp" %> --%>
<%@ include file="/views/embedded/plugin/jwplayer.jsp"%>
<%-- <%@include file="/views/embedded/plugin/layer.jsp" %> --%>
<%@ include file="/views/embedded/common.jsp"%>
<%-- <%@include file="/views/embedded/plugin/szxy_window_js.jsp" %> --%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="description" content="迅云微课星" />
<!-- uc强制竖屏 -->
<meta name="screen-orientation" content="portrait">
<!-- QQ强制竖屏 -->
<meta name="x5-orientation" content="portrait">
<title>${lessonUser.name}录制的${microLessonVo.title}</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/extra/wkx/index.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/login/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/js/audio/mediaelementplayer.min.css" />
<script src="${pageContext.request.contextPath}/res/js/audio/mediaelement-and-player.min.js"></script>
<style type="text/css">
.-mob-share-ui-button{
	
}
.audio_div{
	position:relative;
/* 	width:100%; */
/* 	background:url(background.png);background:rgba(0,0,0,.7);background:-webkit-gradient(linear,0 0,0 100%,from(rgba(50,50,50,.7)),to(rgba(0,0,0,.7)));background:-webkit-linear-gradient(top,rgba(50,50,50,.7),rgba(0,0,0,.7));background:-moz-linear-gradient(top,rgba(50,50,50,.7),rgba(0,0,0,.7));background:-o-linear-gradient(top,rgba(50,50,50,.7),rgba(0,0,0,.7));background:-ms-linear-gradient(top,rgba(50,50,50,.7),rgba(0,0,0,.7));background:linear-gradient(rgba(50,50,50,.7),rgba(0,0,0,.7)); */
}
.current_time{
	position:absolute;
	top:0px;
	display:block;
	background:#000;
	color:#fff;
	width:37px;
	height:30px;
	left:323px;
	text-align:center;
	line-height:30px;
	font-size:12px;
}
canvas{
/* 	background-image:url("${eurlFn:convertUrl(microLessonVo.bgpicturePath)}"); */
   background-size:100% 100%; 
}
#canvas{z-index:10;position:relative;}
#canvas_images{z-index:9;position:absolute;top:30px;left:0}
</style>
</head>
<script type="text/javascript">
	var play_w= document.documentElement.clientWidth;
	var play_height = 0.56*play_w;
	<c:if test="${empty microLessonVo}">
	    $.alert("该微课已被删除");
	    initFlag = false;
	</c:if>
	
	/* <c:choose>
	    <c:when test="${not empty microLessonVo}">
	   	 var width = 634;
	    </c:when>
	    <c:otherwise>
	        var width = 900;
	    </c:otherwise>
	</c:choose> */
	$(function(){
		var currentHttpUrl = "${microLessonVo.uuid}";
		callJwplay(currentHttpUrl,"${microLessonVo.type}", play_w, play_height);
	});	
function callJwplay(httpUrl,type, width, height) {
	if("micro_course" === type || "null" === type){
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
<body>
	<!--顶部-->
	<header>
		<div class="head">
                <img src="${pageContext.request.contextPath}/res/images/logo.png" alt="" class="top_logo_img">
                <a href="http://wk.studyo.cn" target="_blank" class="top_download">了解更多</a>
            </div>
	</header>
	
	<div class="main">
		<div id="detailContent" class="video_part" style="">
			<div id="html5_player" style="display: none">
				 <canvas id="canvas" onclick="is_palying()" style="float:left">您的浏览器不支持 canvas 标签。</canvas>
				 <canvas id="canvas_images">您的浏览器不支持 canvas 标签。</canvas>
				 <div style="height:30px;float:left;" class="audio_div">
				    <%-- <audio id="audio" controls="controls"  onplay="mediaPlay()"  onpause="mediaPause()" onseeking="mediaSeeking()" onseeked="mediaSeeked()"  onended="mediaEnded()">
				    <source src="${eurlFn:convertUrl(microLessonVo.mediaPath)}" type="audio/mpeg" />
				    您的浏览器不支持 audio 标签。
				 </audio> --%>
					 	<audio id="player2" src="${eurlFn:convertUrl(microLessonVo.mediaPath)}" type="audio/mp3" controls="controls"></audio>
				 	<span class="current_time">00:00</span>
				 </div>
				 <div class="c_play" onclick="IS_pause()"></div>
				 <div class="video_head">
			      <div class="left">
			        <img src="${eurlFn:convertUrl(microLessonVo.logoPath)}">
			        <div class="teacher_name"></div>
			      </div>
			      <div class="right">
			          <div class="r1"></div>
			          <div class="r2"></div>
			      </div>
			  </div>
<%-- 				 <img src="${eurlFn:convertUrl(microLessonVo.bgpicturePath)}" hidden id="img" style="display:none">  --%>
				  <input id="jsonPath" type="hidden"
			value="${eurlFn:convert2InternalUrl(microLessonVo.jsonPath)}" /> <input
			id="propertyPath" type="hidden"
			value="${eurlFn:convert2InternalUrl(microLessonVo.propertyPath)}" />
		<input id="currentMicroId" type="hidden"
			value="${microLessonVo.uuid}" />
		<c:forEach items="${microLessonVo.mlbs}" var="mlb">
			<input type="hidden" value="${eurlFn:convertUrl(mlb.bgPath)}" name="${mlb.entityName}" id="${mlb.entityName}" />
		</c:forEach>
			</div>
		</div>
			<!--用户信息部分-->
		<div class="user_info">
                <div class="info_top">
                    <div class="info_top_left">
                        <img src="${pageContext.request.contextPath}/res/images/no_pic.jpg" alt="" class="user_logo_img">
                    </div>
                    <div class="info_top_right">
                        <div class="username replacetext">${microLessonVo.title}</div>
                        <div class="play_time_num">
                            <span class="sp_time"><fmt:formatDate value="${microLessonVo.createDate}" pattern="yyyy-MM-dd HH:mm"/></span>
                            <span class="play_times"><span>播放</span><span class="num_times">${empty microLessonVo.playNumber ? 0 : microLessonVo.playNumber}</span>次</span>
                        </div>
						
						<div class="addr_part">
							<img src="${pageContext.request.contextPath}/res/images/xiaoimg1.jpg" alt="" class="img_addr">
							<span class="sp_addr">${schoolInfo.name}</span>
						</div>
						
                    </div>
                </div>
         </div>
		 <!--热门微课-->
		 <div class="videomore" style="margin-bottom:50px;">
            <div class="morevideotitle">
                <div class="liney"></div>
                <div class="title_top">热门微课</div>
            </div>
            <ul class="videomoreul">
				<c:forEach items="${hotLessons}" var="item">
	                <li class="videomoreulli">
	                <a href="javascript:void(0)" class="link_a" onclick="hotLesson('${item.uuid}','${schoolInfo.id}')">
<%-- 						<a href="${ctp}/termial/micro/lessonShare/${item.uuid}?schoolId=${schoolInfo.id}" class="link_a"> --%>
							<img src="${eurlFn:convertUrl(item.thumbUrl)}" alt="">
							<p>${item.title}</p>
							<div class="hot-name"><strong class="name">${item.userName}</strong><em class="name">${empty item.playNumber ? 0 : item.playNumber}</em></div>
						</a>
					</li>
				</c:forEach>
            </ul>
<!-- 			 <a href="http://wk.studyo.cn" target="_blank" class="more_btn">了解微课星</a> -->
        </div>
	<!--热门微课-->
	
	<!--MOB SHARE BEGIN-->

<div class="-mob-share-ui -mob-share-ui-theme -mob-share-ui-theme-slide-bottom" style="display: none">
    <ul class="-mob-share-list">
        <li class="-mob-share-weibo"><p>新浪微博</p></li>
        <li class="-mob-share-qzone"><p>QQ空间</p></li>
        <li class="-mob-share-qq"><p>QQ好友</p></li>
        <li class="-mob-share-weixin"><p>微信</p></li>
        <li class="-mob-share-weixin pyq"><p>朋友圈</p></li>

    </ul>
    <div class="-mob-share-close">取消</div>
</div>
<div class="-mob-share-ui-bg"></div>	
<script id="-mob-share" src="http://f1.webshare.mob.com/code/mob-share.js?appkey=c26c9f4022b0"></script>
<!--MOB SHARE END-->

            
	<div class="footpart">
        <div class="footinner">
            <%-- <div class="footleft">
               <a href="javascript:praise('${microLessonVo.id}')" > <img src="${pageContext.request.contextPath}/res/images/bottomstar.png" alt="" class="bottom_star"></a>
                <span class="likecount">234赞</span>
            </div> --%>
            <a class="footmid" href="#" style="margin-top:0.4rem;display:none;">
               <div class="-mob-share-ui-button -mob-share-open" style="padding:0;background:#fff;color:#3ea9f5;border-radius:5px;">分享</div>
            </a>
            
        </div>
    </div>
    <input id="jsonPath" type="hidden" value="${eurlFn:convertUrl(microLessonVo.jsonPath)}"/>
 	 <input id="propertyPath" type="hidden" value="${eurlFn:convertUrl(microLessonVo.propertyPath)}"/>
	</div>
	<script type="text/javascript">
	var enableSave = 0;
    var ctx = null;
    var ctx_images = null;
    var audio = null;
    var canvas = null;
    var canvas_images = null;
    var commands = null;
    var width = null;
    var height = null;
    var prevPointX = 0;// 记录上次X坐标
    var prevPointY = 0;// 记录上次Y坐标
    var prevPointX_1 = 0;// 记录上次X坐标
    var prevPointY_1 = 0;// 记录上次Y坐标
    var imagesWidth=0;//图片宽度
    var imagesHeight=0;//图片高度
    var prevTime = 0;// 记录上次时间
    var outerIndex = 0;
    var innerIndex = 0;
    var mediaState = false;
    var currentTime = 0;
    var eachBreak = false;
    var page=0;
    var precent=0;
    var prev_time=0;
    var pattern=null;
    var canvasWidth=0;
    var canvasHeight=0;
    var color;
    var currentTimelines=0;
    var newPressure=0; 
    var eventType=1;
    var images_id=0;
    var source=null;
    var a=new Array();
    var e_width=0;
    var angle=0;
   /*  $("button").click(function(){
    	$(".video_head").hide();
    	var a_style=$(this).attr("title")
    	if(a_style=="Play"){
        	mediaPlay();
        	$(".c_play").hide();
    	}else{
    		 mediaPause();
    		 $(".c_play").show();
    	}
     })
      $(".mejs-time-slider").click(function(){
        	mediaSeeked()
      }) */
    
    function praise(lesssonId){
//     	var WEIXIN_url = "https://open.weixin.qq.com/connect/qrconnect?appid=wx7f727633f796b164&redirect_uri=http%3a%2f%2f192.168.11.132%2fszxyzxx%2ftermial%2fmicro%2flessonShare%2f07564b27d8a04e3ca44497eff294c705&response_type=code&scope=snsapi_login&state=http%3a%2f%2f192.168.11.132%2fszxyzxx%2ftermial%2fmicro%2flessonShare%2f07564b27d8a04e3ca44497eff294c705#wechat_redirect";
//     	$.get(WEIXIN_url,{},function(data,status){
//     		alert(data);
//     	});
//     	var url = "${ctp}/termial/micro/getWXAccount";
//     	$.post(url,{},function(data,status){
//     		alert(JSON.stringify(data));
//     	});
    }
   function mediaPlay() {
    //$(".video_head").hide();
    currentTime = $(".mejs-currenttime").text()*1000;
    var time1=$(".mejs-time-slider").attr("aria-valuetext");
	var time2=$(".mejs-duration").text();
   	  if(currentTime==0||time1==time2){
   		currentTime=0;
   		prevPointX = 0;
        prevPointY = 0;
        prevTime = 0;
        outerIndex = 0;
        innerIndex = 0;
	    page=0;
	    ctx.clearRect(0, 0, width, height);
	     /*  ctx.fillStyle=pattern;
	        ctx.fillRect(0,0,canvasWidth,canvasHeight) */
   	  }
      mediaState = true;
      writer();
      /* if(enableSave == 0){
			 saveSeeNumber();
		 } */
		 enableSave++;
    }


	function is_palying(){
		/* var a_style=$(".mejs-controls .mejs-play button").attr("title")
		$(".mejs-controls .mejs-playpause-button button").click();
    	if(a_style=="Play"){
        	mediaPlay();
        	
    	}else{
    		 mediaPause();
    	} */
    	currentTime = $(".mejs-currenttime").text()*1000;
    	var time1=$(".mejs-time-slider").attr("aria-valuetext");
    	var time2=$(".mejs-duration").text();
    	if(currentTime==0||time1==time2){
    		$(".mejs-controls .mejs-playpause-button button").click();
    		mediaPlay();
    	}else{
			$(".mejs-controls .mejs-playpause-button button").click();
			mediaPause();
			//$(".c_play").show();
    	}
    }
	
	function IS_pause(){
		$(".mejs-controls .mejs-playpause-button button").click();
		is_pause = false;
		mediaPlay();
		$(".c_play").hide();
	}
    function mediaPause() {
      mediaState = false;
    }

    function mediaSeeking() {
      audio.pause();
      mediaState = false;
    }
    
    function mediaSeeked() {
    	var a_style=$(".mejs-controls .mejs-button button").attr("title");
    	if(a_style=="Play"){
    		 $(".c_play").show();
    	}else{
    	$(".video_head").hide();
        	$(".c_play").hide();
    	}
        eachBreak = false;
        currentTime = $(".mejs-currenttime").text()*1000;
        for(var currentTimeLine = 0 ; currentTimeLine < timelines.length; currentTimeLine++ ){
      	  if(timelines[currentTimeLine].start<currentTime&&currentTime<timelines[currentTimeLine].end){
      		page = timelines[currentTimeLine].index; 
      		$.each(pages,function(i0){
				if(pages[i0].index==page){
					page=i0;
					a=[];
					return false; //跳出循环
				}
			})
      		break;
      	  }
        }
        if(page!=pages.length && page > -1){
            commands = pages[page].commands;
          }
        // 清空画布
        ctx.clearRect(0,0,canvasWidth,canvasHeight);
        ctx_images.clearRect(0,0,canvasWidth,canvasHeight);
        var bg = pages[page].background;
		$("#canvas_images").css("backgroundImage","url(" + $("input[name='" + bg + "']").val() + ")");
        /* ctx.fillStyle=pattern;
        ctx.fillRect(0,0,canvasWidth,canvasHeight) */
        $.each(commands, function(x, command) {
        	if(commands[x].event=="Point"){
          $.each(command.eventArgs.points, function(y, obj) {
            if (y === 0) {
              prevPointX = obj.cX;
              prevPointY = obj.cY;
              prevTime = obj.time;
            } else {
            	if(prevTime <= currentTime){	
              ctx.beginPath();
              color=commands[x].eventArgs.points[y].penColorString + "";
              if(color == null || color=="" || color == "undefined"){
           	   color = commands[x].eventArgs.points[y].penColor + "";
           	   color = "#" + color.substring(3);
              }else{
           	   color = "#" + color.substring(2);
              }
              ctx.strokeStyle = color; 
              ctx.lineCap = "round";
	  			ctx.lineJoin = "round";
            newPressure = (obj.newPressure+obj.oldPressure)*obj.penSize/1000;
            ctx.lineWidth = newPressure;
              ctx.moveTo(prevPointX*precent, prevPointY*precent);
              ctx.lineTo(obj.cX*precent, obj.cY*precent);
              ctx.stroke();
              prevPointX = obj.cX;
              prevPointY = obj.cY;
              prevTime = obj.time;
              if (obj.time >= currentTime) {
                outerIndex = x;
                innerIndex = y;
                eachBreak = true;
                return false;
              }
            	}
            }
          });
          if (eachBreak) {
            return false;
          }
        	}else if(commands[x].event=="Image"){
        		$.each(command.eventArgs.image, function(z, Obj) {
        			if (z === 0) {
        				 prevPointX_1 = Obj.x*precent;
	       	              prevPointY_1 = Obj.y*precent;
	       	              imagesWidth=Obj.width*precent;
	       	              imagesHeight=Obj.height*precent;
        	              prevTime = Obj.time;
        	              source = Obj.source;
        	              images_id=Obj.id;
                          angle=Obj.angle
        	              var dx=command.eventArgs.image;
        	              var image_length=dx.length-1;
        	              var image_set={
        	            	 "Id":images_id,
        	            	 "source":source,
        	            	 "angle":angle,
        	            	 "prevPointX_1":dx[image_length].x * precent,
        	            	 "prevPointY_1":dx[image_length].y * precent,
        	            	 "imagesWidth":dx[image_length].width * precent,
        	            	 "imagesHeight":dx[image_length].height * precent,
        	              }
        	              for(var i=0;i<a.length;i++){
        	            	  if(a[i].Id==images_id){
        	            		  a.splice(i,1);
        	            	  }
        	              }
        	              a.push(image_set);
        	              if(prevTime <= currentTime){
        	            	if(Obj.eventType==6){
        	            		ctx_images.clearRect(0,0,canvasWidth,canvasHeight);
        	            		for(var i=0;i<a.length;i++){
    						  	  if(a[i].Id!=images_id){
    						  		var pic = new Image();
    	    			            source = a[i].source;
    	    			            angle=a[i].angle;
    	    			            pic.src = $("input[name='" + source + "']").val();
    	    			            ctx_images.save();
    	    			            ctx_images.translate((a[i].prevPointX_1+(a[i].imagesWidth/2)),(a[i].prevPointY_1+(a[i].imagesHeight/2)));
   	                             ctx_images.rotate(a[i].angle*Math.PI/180);
   	                        	ctx_images.translate(-(a[i].prevPointX_1+(a[i].imagesWidth/2)),-(a[i].prevPointY_1+(a[i].imagesHeight/2)));
   	                        	if(angle==90||angle==270){
   	                        		ctx_images.drawImage(pic,a[i].prevPointX_1+(a[i].imagesWidth/2)-(a[i].imagesHeight/2),a[i].prevPointY_1+(a[i].imagesHeight/2)-(a[i].imagesWidth/2),a[i].imagesHeight,a[i].imagesWidth);
   	                        	}else{
    						  		ctx_images.drawImage(pic,a[i].prevPointX_1,a[i].prevPointY_1,a[i].imagesWidth,a[i].imagesHeight);
   	                        	}
    	                        ctx_images.restore();
    						  	  }else{
    						  		a.splice(i,1);
    						  	  }
    						    }
        	            	}else if(Obj.eventType==1){
        	            	
        	            		ctx_images.clearRect(0,0,canvasWidth,canvasHeight);
    						    for(var i=0;i<a.length;i++){
    						  	  if(a[i].Id!=images_id){
    						  		var pic = new Image();
    						  		angle=a[i].angle;
    	    			            source = a[i].source;
    	    			            pic.src = $("input[name='" + source + "']").val(); 
    	    			            ctx_images.save();
    	                            ctx_images.translate((a[i].prevPointX_1+(a[i].imagesWidth/2)),(a[i].prevPointY_1+(a[i].imagesHeight/2)));
    	                             ctx_images.rotate(a[i].angle*Math.PI/180);
    	                        	ctx_images.translate(-(a[i].prevPointX_1+(a[i].imagesWidth/2)),-(a[i].prevPointY_1+(a[i].imagesHeight/2)));
    	                        	if(angle==90||angle==270){
    	                        		ctx_images.drawImage(pic,a[i].prevPointX_1+(a[i].imagesWidth/2)-(a[i].imagesHeight/2),a[i].prevPointY_1+(a[i].imagesHeight/2)-(a[i].imagesWidth/2),a[i].imagesHeight,a[i].imagesWidth);
       	                        	}else{
        						  		ctx_images.drawImage(pic,a[i].prevPointX_1,a[i].prevPointY_1,a[i].imagesWidth,a[i].imagesHeight);
       	                        	}
    	                        	ctx_images.restore();
    						  	  }
    						    }
            	            	var pic = new Image();
        			            source = Obj.source;
        			            pic.src = $("input[name='" + source + "']").val(); 
            					ctx_images.drawImage(pic,prevPointX_1,prevPointY_1,imagesWidth,imagesHeight);
        	            	}
                        else if(Obj.eventType==4){
                          ctx_images.clearRect(0,0,canvasWidth,canvasHeight);
                    for(var i=0;i<a.length;i++){
                      if(a[i].Id!=images_id){
                      var pic = new Image();
                      angle=a[i].angle;
                            source = a[i].source;
                            pic.src = $("input[name='" + source + "']").val(); 
                      ctx_images.drawImage(pic,a[i].prevPointX_1,a[i].prevPointY_1,a[i].imagesWidth,a[i].imagesHeight);
                      }
                    }
                            var pic = new Image();
                          source = Obj.source;
                          angle=Obj.angle;
                          pic.src = $("input[name='" + source + "']").val(); 
                          ctx_images.save();
                           ctx_images.translate(prevPointX_1+imagesWidth/2,prevPointY_1+imagesHeight/2);
                            ctx_images.rotate(angle*Math.PI/180);
                       ctx_images.translate(-(prevPointX_1+(imagesWidth/2)),-(prevPointY_1+(imagesHeight/2)));
                       if(angle==90||angle==270){
                    	   ctx_images.drawImage(pic,prevPointX_1+imagesWidth/2-imagesHeight/2,prevPointY_1+imagesHeight/2-imagesWidth/2,imagesHeight,imagesWidth);
                      	}else{
                      ctx_images.drawImage(pic,prevPointX_1,prevPointY_1,imagesWidth,imagesHeight);
                      	}
                       ctx_images.restore();
                        }
        	              }
        	            } else {
        			if(prevTime <= currentTime){
        					ctx_images.clearRect(0,0,canvasWidth,canvasHeight);
						    for(var i=0;i<a.length;i++){
						  	  if(a[i].Id!=images_id){
						  		var pic = new Image();
	    			            source = a[i].source;
	    			            angle=a[i].angle;
	    			            pic.src = $("input[name='" + source + "']").val(); 
	    			            ctx_images.save();
	    			            ctx_images.translate((a[i].prevPointX_1+(a[i].imagesWidth/2)),(a[i].prevPointY_1+(a[i].imagesHeight/2)));
	                             ctx_images.rotate(a[i].angle*Math.PI/180);
	                        	ctx_images.translate(-(a[i].prevPointX_1+(a[i].imagesWidth/2)),-(a[i].prevPointY_1+(a[i].imagesHeight/2)));
	                        	if(angle==90||angle==270){
	                        		ctx_images.drawImage(pic,a[i].prevPointX_1+(a[i].imagesWidth/2)-(a[i].imagesHeight/2),a[i].prevPointY_1+(a[i].imagesHeight/2)-(a[i].imagesWidth/2),a[i].imagesHeight,a[i].imagesWidth);
   	                        	}else{
    						  		ctx_images.drawImage(pic,a[i].prevPointX_1,a[i].prevPointY_1,a[i].imagesWidth,a[i].imagesHeight);
   	                        	}
	                        ctx_images.restore();
						  	  }
						    }
        	            	var pic = new Image();
        	            	angle=Obj.angle;
    			            source = Obj.source;
    			            pic.src = $("input[name='" + source + "']").val(); 
			                ctx_images.save();
						    ctx_images.translate(prevPointX_1+imagesWidth/2,prevPointY_1+imagesHeight/2);
						    ctx_images.rotate(angle*Math.PI/180);
			  				ctx_images.translate(-(prevPointX_1+(imagesWidth/2)),-(prevPointY_1+(imagesHeight/2)));
			                if(angle==90||angle==270){
         	   ctx_images.drawImage(pic,prevPointX_1+imagesWidth/2-imagesHeight/2,prevPointY_1+imagesHeight/2-imagesWidth/2,imagesHeight,imagesWidth);
          	}else{
              ctx_images.drawImage(pic,prevPointX_1,prevPointY_1,imagesWidth,imagesHeight);
          	}
							ctx_images.restore();
        	              prevPointX_1 = Obj.x*precent;
        	              prevPointY_1= Obj.y*precent;
        	              imagesWidth=Obj.width*precent;
        	              imagesHeight=Obj.height*precent;
        	              prevTime = Obj.time;
        			}
        	              if (Obj.time >= currentTime) {
        	                outerIndex = x;
        	                innerIndex = z;
        	                eachBreak = true;
        	                return false;
        	              }
        	            }
        		});
        		if (eachBreak) {
                    return false;
                  };
        	}
        	else if(commands[x].event=="Eraser"){
        		$.each(command.eventArgs.erasers, function(y, obj) {
    	            if (y === 0) {
    	              prevPointX = obj.cX;
    	              prevPointY = obj.cY;
    	              e_width=(obj.penSize*precent)/2;
    	              prevTime = obj.time;
        			if(prevTime <= currentTime){
    					
    					ctx.save()
    					ctx.beginPath()
    					ctx.arc(prevPointX*precent,prevPointY*precent,e_width,0,2*Math.PI);
    					ctx.clip()
    					ctx.clearRect(0,0,canvas.width,canvas.height);
    					ctx.restore();
        			}
    	            } else {
    	            	if(prevTime <= currentTime){
     	            var asin = e_width*Math.sin(Math.atan((obj.cY*precent-prevPointY*precent)/(obj.cX*precent-prevPointX*precent)));
					var acos = e_width*Math.cos(Math.atan((obj.cY*precent-prevPointY*precent)/(obj.cX*precent-prevPointX*precent)));
					var x3 = prevPointX*precent+asin;
					var y3 = prevPointY*precent-acos;
					var x4 = prevPointX*precent-asin;
					var y4 = prevPointY*precent+acos;
					var x5 = obj.cX*precent+asin;
					var y5 = obj.cY*precent-acos;
					var x6 = obj.cX*precent-asin;
					var y6 = obj.cY*precent+acos;
					
					ctx.save()
					ctx.beginPath()
					ctx.arc(obj.cX*precent,obj.cY*precent,e_width,0,2*Math.PI);
					ctx.clip()
					ctx.clearRect(0,0,canvas.width,canvas.height);
					ctx.restore();
					
					ctx.save()
					ctx.beginPath()
					ctx.moveTo(x3,y3);
					ctx.lineTo(x5,y5);
					ctx.lineTo(x6,y6);
					ctx.lineTo(x4,y4);
					ctx.closePath();
					ctx.clip()
					ctx.clearRect(0,0,canvas.width,canvas.height);
					ctx.restore();
    	              
      	              prevPointX = obj.cX;
    	              prevPointY = obj.cY;
    	              e_width=obj.penSize*precent;
    	              prevTime = obj.time;
    	              if (obj.time >= currentTime) {
    	                outerIndex = x;
    	                innerIndex = y;
    	                eachBreak = true;
    	                return false;
    	              }
    	            }
    	            }
    	          });
    	          if (eachBreak) {
    	            return false;
    	          }
        	}
        });
        
        writer();
      }

    function prevplay(prev_time){
      audio.currentTime=prev_time;
      mediaSeeked();
    }

    function mediaPlaying() {
      mediaState = true;
      writer();
    }

    function mediaEnded() {
      // 播放结束重新初始化
      prevPointX = 0;
      prevPointY = 0;
      prevTime = 0;
      outerIndex = 0;
      innerIndex = 0;
      page=0;
      audio.currentTime=0;
      // 清空画布
      //ctx.clearRect(0, 0, width, height);
    }
	
//     function Point(commands,innerIndex,outerIndex,curEndTime){
//     	if (commands !== null&& innerIndex < commands[outerIndex].eventArgs.points.length&& mediaState) {
// 			if (commands[outerIndex].eventArgs.points.length !== 0&& prevTime !== 0) {
// 				var bg = pages[page].background;
// 				$("#canvas").css("backgroundImage","url(" + $("input[name='" + bg + "']").val() + ")");
// 				////////////////画点开始，并且马上显示出来////////////////
// 				var length = commands[outerIndex].eventArgs.points.length - 1;
// 				var xyd = outerIndex + 1;
// 				var outerlist = commands.length;
// 				if (xyd < outerlist) {
// 					var next_time = commands[xyd].start;
// 					if (curEndTime != 0 && next_time > curEndTime&& innerIndex == length) {
// 						currentTimelines += 1;
// 						page = timelines[currentTimelines].page - 1;
// 						commands = pages[page].commands;
// 						if (curEndTime - currentTime > 0) {
// 							setTimeout(mediaSeeked, curEndTime - currentTime);
// 						}
// 					} else {
						
// 						ctx.beginPath();
// 						color = commands[outerIndex].eventArgs.points[innerIndex].penColorString + "";
// 						if (color == null || color == "" || color == "undefined") {
// 							color = commands[outerIndex].eventArgs.points[innerIndex].penColor + "";
// 							color = "#" + color.substring(3);
// 						} else {
// 							color = "#" + color.substring(2);
// 						}
// 						ctx.strokeStyle = color;

// 						ctx.moveTo(prevPointX * precent, prevPointY * precent);
// 						ctx.lineTo(commands[outerIndex].eventArgs.points[innerIndex].cX * precent, commands[outerIndex].eventArgs.points[innerIndex].cY * precent);
// 						ctx.stroke();
// 						//////////////////////画点结束/////////////////////
// 						if (innerIndex < commands[outerIndex].eventArgs.points.length - 1) {
// 							setTimeout(writer,commands[outerIndex].eventArgs.points[innerIndex].time - prevTime);
// 						}
							
// 						if ((outerIndex < commands.length - 1) && (innerIndex === commands[outerIndex].eventArgs.points.length - 1)) {
// 							outerIndex++;
// 							innerIndex = 0;
// 							// 初始化等待时间，可以设置时间
// 							if(commands[outerIndex].event=="Point"){
// 							setTimeout(writer,commands[outerIndex].eventArgs.points[innerIndex].time - prevTime);
// 							}else if(commands[outerIndex].event=="Image"){
// 								//setTimeout(writer,commands[outerIndex].eventArgs.image[innerIndex].time - prevTime);
// 							}
// 						}
// 						if ((outerIndex === commands.length - 1) && (innerIndex === commands[outerIndex].eventArgs.points.length - 1)) {
// 							page++;
// 							outerIndex = 0;
// 							innerIndex = 0;
// 							prevPointX = 0;
// 							prevPointY = 0;
// 							if (page != pages.length) {
// 								prevTime = timelines[page].start;
// 							}
// 							//if(page<pages.length){
// 							setTimeout(writer,commands[outerIndex].eventArgs.points[innerIndex].time - prevTime);
// 							//}
// 						}
							
// 					}
// 				} else {
// 					if(innerIndex == length ){
// 					currentTimelines += 1;
// 					page = timelines[currentTimelines].page - 1;
// 					if (page > -1) {
// 						commands = pages[page].commands;
// 					}

// 					if (curEndTime - currentTime > 0) {
// 						setTimeout(mediaSeeked, curEndTime - currentTime);
// 					}
// 					}else{
// 						ctx.beginPath();
// 						color = commands[outerIndex].eventArgs.points[innerIndex].penColorString + "";
// 						if (color == null || color == "" || color == "undefined") {
// 							color = commands[outerIndex].eventArgs.points[innerIndex].penColor + "";
// 							color = "#" + color.substring(3);
// 						} else {
// 							color = "#" + color.substring(2);
// 						}
// 						ctx.strokeStyle = color;

// 						ctx.moveTo(prevPointX * precent, prevPointY * precent);
// 						ctx.lineTo(commands[outerIndex].eventArgs.points[innerIndex].cX * precent, commands[outerIndex].eventArgs.points[innerIndex].cY * precent);
// 						ctx.stroke();
// 						//////////////////////画点结束/////////////////////
// 						if (innerIndex < commands[outerIndex].eventArgs.points.length - 1) {
// 							setTimeout(writer,commands[outerIndex].eventArgs.points[innerIndex].time - prevTime);
// 						}
// 						if ((outerIndex < commands.length - 1) && (innerIndex === commands[outerIndex].eventArgs.points.length - 1)) {
// 							outerIndex++;
// 							innerIndex = 0;
// 							// 初始化等待时间，可以设置时间
// 							if(commands[outerIndex].event=="Point"){
								
// 								setTimeout(writer,commands[outerIndex].eventArgs.points[innerIndex].time - prevTime);
// 								}else if(commands[outerIndex].event=="Image"){
// 									setTimeout(writer,commands[outerIndex].eventArgs.image[innerIndex].time - prevTime);
// 								}
// 						}
// 						if ((outerIndex === commands.length - 1) && (innerIndex === commands[outerIndex].eventArgs.points.length - 1)) {
// 							page++;
// 							outerIndex = 0;
// 							innerIndex = 0;
// 							prevPointX = 0;
// 							prevPointY = 0;
// 							if (page != pages.length) {
// 								prevTime = timelines[page].start;
// 							}
// 							//if(page<pages.length){
// 							setTimeout(writer,commands[outerIndex].eventArgs.points[innerIndex].time - prevTime);
// 							//}
// 						}
// 					}
// 				}
// 			}
// 			if (outerIndex < commands.length&&commands[outerIndex].event=="Point") {
// 				prevPointX = commands[outerIndex].eventArgs.points[innerIndex].cX;
// 				prevPointY = commands[outerIndex].eventArgs.points[innerIndex].cY;
// 				prevTime = commands[outerIndex].eventArgs.points[innerIndex].time;
// 				currentTime = $(".mejs-currenttime").text()*1000;
// 			}else{
// 			}

// 			if (outerIndex === 0 && innerIndex === 1) {
// 				if (page < pages.length) {
// 					ctx.clearRect(0, 0, canvasWidth, canvasHeight);
// 				}
// 			}
// 			// 只执行一次
// 			if (outerIndex === 0 && innerIndex === 0) {
// 				// 初始化等待时间，可以设置时间
// 				if (page != pages.length) {
// 					setTimeout(writer, prevTime - timelines[page].start);
// 				}
// 			}
// 			if (currentTime > prevTime) {
// 				innerIndex++;
// 			}

// 		}
//     }
    
//     function Image(){
//     	setTimeout(writer,commands[outerIndex].start-commands[outerIndex].end);
//     }
    
    function writer() {
		/* currentTime = audio.currentTime * 1000; */
		var curEndTime = 0;
		var time_list=timelines.length;
		/* 当前时间比当前时间线路的最后一个时间大时，过下一个时间段 */
		for (var currentTimeLine = 0; currentTimeLine < timelines.length; currentTimeLine++) {
			if (timelines[currentTimeLine].start < currentTime && currentTime < timelines[currentTimeLine].end) {
				currentTimelines = currentTimeLine;
				curEndTime = timelines[currentTimelines].end;
				page = timelines[currentTimelines].index;
				if (page > 0) {
					$.each(pages,function(i0){
						if(pages[i0].index==page){
							page=i0;
							return false; //跳出循环
						}
					})
					commands = pages[page].commands;
				}
				break;
			}
		}

		if (page<pages.length && page > - 1) {
			commands = pages[page].commands;
			//          if(currentTime>curEndTime){
			//     		 innerIndex = 0;
			//     		 outerIndex = 0;
			//prevplay(currentTime);
			//     	 }
			if (outerIndex < commands.length&&commands[outerIndex].event=="Point") {
				
				//Point(commands,innerIndex,outerIndex,curEndTime);
				
			}
			else if(outerIndex < commands.length&&commands[outerIndex].event=="Image"){
				//Image();	
			}
		}
	}

    
    function qp(){
        var h = document.documentElement.clientHeight;
        var w = document.documentElement.clientWidth;
        precent=w/width;
        canvasWidth=w;
        canvasHeight=height*precent;
        if(height*precent>h){
          canvasHeight=h;
          precent=h/height;
          canvasWidth=width*precent
        }
        canvas.width = canvasWidth;
        canvas.height= canvasHeight;
         ctx = canvas.getContext("2d");
        var imageElem=document.getElementById("img");
        pattern =ctx.createPattern(imageElem,"repeat")
         /* ctx.fillStyle=pattern;
        ctx.fillRect(0,0,canvasWidth,canvasHeight); */
       if(audio.currentTime!=0){
           mediaSeeked();
        }

    }
    function sx(afterHandler){
    	var w = document.documentElement.clientWidth;
    	$("#audio").css("width",w);
        precent=w/width;
        canvasWidth=w;
        canvasHeight=height*precent;
       canvas.width =w;
        canvas.height= height*precent;
        canvas_images.width =w;
        canvas_images.height= height*precent;
    	$("canvas").css("width", w);
        ctx = canvas.getContext("2d");
        ctx_images = canvas_images.getContext("2d");
         $(".video_head").css({"height":canvasHeight-45,"left":0,"width":w,"top":"30px"});
         $(".c_play").css("height",canvasHeight);
       /*  var imageElem=document.getElementById("img");
        pattern =ctx.createPattern(imageElem,"repeat")
         ctx.fillStyle=pattern; */
        /* ctx.fillRect(0,0,canvasWidth,canvasHeight); */
        currentTime =$(".mejs-currenttime").text()*1000;
        if(isNaN(currentTime)){
        	currentTime=0;
        }
        if(currentTime!=0){
           mediaSeeked();
        }
        afterHandler();
    }
    $(function() {
    	canvas = document.getElementById("canvas");
    	 canvas_images = document.getElementById("canvas_images");
        audio = document.getElementById("audio");
        $('audio').mediaelementplayer();
    	$.ajaxSetup({
			async : false
		});
		setInterval(mediaSeeked,10);
        var data = eval("("+'${mainJson}'+")");
      	if(data != null || data != "" || data != "undefined"){
	        width = data.pages[0].width;
	        height = data.pages[0].Height;
	        //canvas.width =  786;
	        
	        //canvas.height = canvasHeight;
	        $(audio).css("width", 766);
	
	        /*ctx = canvas.getContext("2d");
	        var imageElem=document.getElementById("img");
	         pattern =ctx.createPattern(imageElem,"repeat")
	        ctx.fillStyle=pattern;
	        ctx.fillRect(0,0,786,canvasHeight)*/
	        pages=data.pages;
	        timelines=data.timelines;
      	}
      	sx(function(){
    	    if(data.isHeadShow!=true){
    	    	$(".c_play").show();
    	       /* if(enableSave == 0){
    	         saveSeeNumber();
    	         } */
    	    }else{
    	           $(".video_head").show();
    	           var propertyData = eval("("+'${propertyJson}'+")");
    	           $(".video_head .left .teacher_name").text(propertyData.course.ownerName);
    	           $(".video_head .right .r1").text(propertyData.course.title);
    	           $(".video_head .right .r2").text(propertyData.course.learnGoal);
    	             //console.log(data.course.title +"="+  data.course.ownerName)
    	           $(".video_head").click(function(){
    	        	$(".mejs-controls .mejs-playpause-button button").click();
    	        	 $(".c_play").hide();
    	               	mediaPlay();
    	             /* if(enableSave == 0){
    	               saveSeeNumber();
    	             } */
    	             enableSave++;
    	           })
    	      }
          });
      
      
      /* var imageElem=document.getElementById("img");
      pattern =ctx.createPattern(imageElem,"repeat")
       ctx.fillStyle=pattern; */
      $(".mejs-time-rail").click(function(){
    	  $(".video_head").hide();
      });
      $(".video_head").click(function(){
    	  $(".video_head").hide();
      });
     
    var flag = 0;
      
    var tttt=setInterval(function(){
    	 var time3=$(".mejs-time-slider").attr("aria-valuetext");
    	 var time2=$(".mejs-duration").text();
    	 currentTime = $(".mejs-currenttime").text()*1000;
    	 if(currentTime != 0){
    		if(flag == 0){
    			flag = 1;
    			saveSeeNumber();
    		}
    	 }
    	 $(".current_time").text(time3);
    	 if(time3==time2||currentTime=="0"){
    		 var a_style=$(".mejs-controls .mejs-play button").attr("title");
    		 if(a_style=="Play"){
	    		 $(".c_play").show();
    		 }
    	 }
    },500);
    });
    
   	//播放完成
    function saveSeeNumber(){
    	var url = "${ctp}/termial/micro/saveSeeNumber"
    	$.post(url,{"uuid":"${microLessonVo.uuid}"},function(){
    		
    	});
    }
    
    mobShare.config( {
    	 
        debug: true, // 开启调试，将在浏览器的控制台输出调试信息
     
        appkey: 'c26c9f4022b0', // appkey
     
        params: {
            url: 'window.location.href', // 分享链接
            title: '${microLessonVo.title}', // 分享标题
            description: '迅云微课，让你轻松掌握知识，爱上学习', // 分享内容
            pic: '', // 分享图片，使用逗号,隔开
        }
     
    } );
    
    function hotLesson(uuid, schoolId){
    	var loader = new loadwkx();
    	loader.show();
    	window.location.href="${ctp}/termial/micro/lessonShare/"+ uuid +"?schoolId="+schoolId;
    }
    
  </script>
</body>
</html>