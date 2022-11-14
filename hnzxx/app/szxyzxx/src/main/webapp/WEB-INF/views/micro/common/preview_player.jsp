<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>canvas</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/login/jquery-1.7.2.min.js"></script>
<style type="text/css">
canvas {
  /* border: 1px solid grey; */
  width:786px;
  /* background-image:url("${eurlFn:convertUrl(microList[0].bgpicturePath)}"); */
   background-size:100% 100%; 
   position:absolute;
   top:45px;
   left:0
}

audio {
  /* margin-left:10px; */
  position:relative;
  width:766px;
   margin-left: 10px;
    top: -7px;
}
.canvas_play{
	width:786px;
	height:auto;
	padding:45px 0;
	background-color:#000;
	left:0;
	top:0;
	position:relative
}

.audio_play{
	position:absolute;
	bottom:0;
	background-color:#000;
	border-radius:5px;
}
.qp,.sx{
	background:url("${pageContext.request.contextPath}/res/css/extra/images/fullscrean.png") no-repeat center center;
	display:block;
	width:30px;
	height:30px;
	float:right;
}
#canvas{z-index:10;}
#canvas_images{z-index:9}
</style>
</head>
<body>
<div class="canvas_play">
  <canvas id="canvas" onclick="is_palying()">您的浏览器不支持 canvas 标签。</canvas>
  <canvas id="canvas_images">您的浏览器不支持 canvas 标签。</canvas>
  <div class="audio_play">
  <audio  id="audio" controls="controls"  onplay="mediaPlay()"  onpause="mediaPause()" onseeking="mediaSeeking()" onseeked="mediaSeeked()"  onended="mediaEnded()">
    <source type="audio/mpeg" src="${eurlFn:convertUrl(microList[0].mediaPath)}"/>
    您的浏览器不支持 audio 标签。
  </audio>
  
  </div>
  <%-- <img src="${eurlFn:convertUrl(microList[0].bgpicturePath)}" hidden id="img">  --%>
  <input id="jsonPath" type="hidden"
			value="${eurlFn:convert2InternalUrl(microList[flag].jsonPath)}" /> <input
			id="propertyPath" type="hidden"
			value="${eurlFn:convert2InternalUrl(microList[flag].propertyPath)}" />
		<input id="currentMicroId" type="hidden"
			value="${microList[flag].uuid}" />
		<c:forEach items="${microList[flag].mlbs}" var="mlb">
			<input type="hidden" value="${eurlFn:convertUrl(mlb.bgPath)}"
				name="${mlb.entityName}" id="${mlb.entityName}" />
		</c:forEach>
 </div>
  <script type="text/javascript">
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
    var w1=0;
    var h1=0;
    var color;
    var currentTimelines=0;
    var pages = null;
    var timelines = null;
    var newPressure=0; 
    var eventType=1;
    var images_id=0;
    var source=null;
    var a=new Array();
    var e_width=0;
    var angle=0;
    var timer;
    audio = document.getElementById("audio");
    function mediaPlay() {
   	  if(audio.currentTime==0){
   	    	  page=0;
   	  }
      mediaState = true;
      timer=setInterval(mediaSeeked,50);
    }

    function mediaPause() {
      mediaState = false;
      clearInterval(timer);
    }
	
    function is_palying(){
    	if(audio.paused){
    		audio.play();
    	}else{
    		audio.pause();
    	}
    }
    function mediaSeeking() {
      audio.pause();
      mediaState = false;
    }

    function mediaSeeked() {
        eachBreak = false;
       currentTime = audio.currentTime * 1000;
        for(var currentTimeLine = 0 ; currentTimeLine < timelines.length; currentTimeLine++ ){
      	  if(timelines[currentTimeLine].start < currentTime&&currentTime < timelines[currentTimeLine].end){
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
        /* ctx.fillStyle=pattern;
        ctx.fillRect(0,0,canvasWidth,canvasHeight) */
        var bg = pages[page].background;
		$("#canvas_images").css("backgroundImage","url(" + $("input[name='" + bg + "']").val() + ")");
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
	              newPressure = (obj.newPressure+obj.oldPressure)*obj.penSize/800;
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
        	}else if(commands[x].event=="Eraser"){
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
				if (page > 0){
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
		audio.play();
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
				//Images(commands,innerIndex,outerIndex,curEndTime);	
			}
		}
	}
 
    function sx(obj){
     	var h = document.documentElement.clientHeight;
        var w = document.documentElement.clientWidth;
        canvas = document.getElementById("canvas");
        canvas_images = document.getElementById("canvas_images");
        precent=786/width;
        canvasWidth=786;
        canvasHeight=height*precent;
       canvas_images.width =786;
        canvas_images.height= height*precent;
        canvas.width =786;
        canvas.height= height*precent;
        w1=0;
        w2=0;
        $(".canvas_play").css({"width":canvasWidth,"height":canvasHeight,"position":"relative","padding-left":w1,"padding-right":w1})
        $("canvas").css({"width":canvasWidth,"height":canvasHeight});
        $(".audio_play").css({"margin-left":w2});
        ctx = canvas.getContext("2d");
        ctx_images = canvas_images.getContext("2d");
        if(audio.currentTime!=0){
           mediaSeeked();
        }
        $this=$(obj);
		$this.hide();
		$this.prev().show();
    }
  
    $(function() {
    	$.ajaxSetup({
			async : false
		});
		//timer=setInterval(mediaSeeked,50);
		var requestData ={};
		var loader = new loadLayer();
		var jsonPath = "${eurlFn:convert2InternalUrl(microList[0].jsonPath)}";
		if(jsonPath != "" && jsonPath != null) {
			loader.show();
			requestData.jsonPath = jsonPath;
	     	 $.getJSON("mainJson", requestData,function(data) {
		    	loader.close();
		    	 width = data.pages[0].width;
		         height = data.pages[0].Height;
		         $(audio).css("width", 756);
		         pages=data.pages;
		         timelines=data.timelines;
		         sx();
		         audio.play();
	     	});
		}
    })
  </script>
</body>
</html>
