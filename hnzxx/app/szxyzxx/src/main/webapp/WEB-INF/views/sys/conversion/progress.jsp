<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dxa/dxa.css" rel="stylesheet">
<title>转换进度条</title>
<style>
/* 视频转换进度条 */
.video_progress{
	text-align: center;
	margin:0 auto;
	width:440px;
}
.video_progress ul li{
	display: inline-block;
	width:128px;
	margin:18px 8px;
}
.video_progress ul li p{
	text-align:center;
	line-height:48px;
}
</style>
</head>
<body style="background-color:#fff !important">
<div class="video_progress">
		<ul>
			<li style="display: none" id="t1"><p class="p1">PDF格式</p><canvas id="canvas1" width="128" height="128" ></canvas></li>
			<li style="display: none" id="t2"><p class="p2">HTML格式</p><canvas id="canvas2" width="128" height="128"></canvas></li>
			<li style="display: none" id="t3"><p class="p1">高清</p><canvas id="canvas3" width="128" height="128" ></canvas></li>
			<li style="display: none" id="t4"><p class="p2">标清</p><canvas id="canvas4" width="128" height="128"></canvas></li>
			<li style="display: none" id="t5"><p class="p3">移动版</p><canvas id="canvas5" width="128" height="128"></canvas></li>
		</ul>
		<div class="clear"></div>
	</div>
	<input id="uuid" type="hidden" value="${uuid}">
<script>
	var i = 0;
	function creat_canvas(id,color,speed){
		var canvas = document.getElementById(id),  //获取canvas1元素
		context = canvas.getContext('2d'),  //获取画图环境，指明为2d
		centerX = canvas.width/2,   //Canvas中心点x轴坐标
		centerY = canvas.height/2,  //Canvas中心点y轴坐标
		rad = Math.PI*2/100; //将360度分成100份，那么每一份就是rad度
			//绘制红色外圈
			function blueCircle(n){
				context.save();
				context.strokeStyle =color; //设置描边样式
				context.lineWidth = 5; //设置线宽
				context.beginPath(); //路径开始
				context.arc(centerX, centerY, 58 , -Math.PI/2, -Math.PI/2 +n*rad, false); //用于绘制圆弧context.arc(x坐标，y坐标，半径，起始角度，终止角度，顺时针/逆时针)
				context.stroke(); //绘制
				context.closePath(); //路径结束
				context.restore();
			}
			//绘制灰色外圈
			function whiteCircle(){
				context.save();
				context.beginPath();
				context.strokeStyle = "#e5e9f2";
				context.lineWidth = 5; //设置线宽
				context.arc(centerX, centerY, 58 , 0, Math.PI*2, false);
				context.stroke();
				context.closePath();
				context.restore();
			}  
			//百分比文字绘制
			function text(n){
				context.save(); //save和restore可以保证样式属性只运用于该段canvas元素
				context.strokeStyle = "#000"; //设置描边样式
				context.font = "16px 宋体"; //设置字体大小和字体
				//绘制字体，并且指定位置
				context.strokeText(n.toFixed(0)+"%", centerX-10, centerY+5);
				context.stroke(); //执行绘制
				context.restore();
			} 
			
			function drawFrame(speed){
				context.clearRect(0, 0, canvas.width, canvas.height);
				whiteCircle();
				text(speed);
				blueCircle(speed);
			}
			drawFrame(speed);
	}

	var iv;
	$(function(){
		getByInterval();
		iv = setInterval(getByInterval,5000);
	});

	function getByInterval(){
		var url = "${ctp}/conversion/status/getDetailedByTime";
		$.post(url, {uuid:$("#uuid").val()}, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				var countDOC = 0;
				var countVID = 0;
				$.each(data,function(){
					var progress = this.progress;
					var type = this.type;
					init(type,progress);
					
					/* 添加判断，当转换完成后停止获取 */
					if(type == 1 || type == 2){
						countDOC += parseInt(progress);
					}
					if(type == 3 || type == 4 || type == 5){
						countVID += parseInt(progress);
					}
					if(countDOC == 200 || countVID == 300){
						clearInterval(iv);
					}
					/* 添加判断，当转换完成后停止获取 */
				});
				
			}
		});
	}

	function init(type,progress){
		if(type == 1){
			$("#t1").show();
			creat_canvas("canvas1","#ff4949",progress);
		}
		if(type == 2){
			$("#t2").show();
			creat_canvas("canvas2","#20a0ff",progress);
		}
		if(type == 3){
			$("#t3").show();
			creat_canvas("canvas3","#ff4949",progress);
		}
		if(type == 4){
			$("#t4").show();
			creat_canvas("canvas4","#20a0ff",progress);
		}
		if(type == 5){
			$("#t5").show();
			creat_canvas("canvas5","#13ce66",progress);
		}
	}
</script>
</body>
</html>