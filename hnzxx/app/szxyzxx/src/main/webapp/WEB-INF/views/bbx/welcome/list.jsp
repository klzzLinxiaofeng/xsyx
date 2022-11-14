<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>


<form id="welcome_form">
	<div class="preview">
		<p class="p_yl">预览</p>
		<div class="yl_main">
			<input type="hidden" id="fileUuid" name="fileUuid"
				value="${wv.fileUuid }" /> 
			<img src="${wv.fileURL }" alt="" id="src" />
			<div id="demo" class="hyc" style="overflow: hidden;">
				<!-- <table align="left" cellpadding="0" cellspace="0" border="0">
	  									<tbody>
	  									    <tr><td id="demo1" valign="top"><p style="min-width:352px">这是一条欢迎词</p></td>
	  									         <td id="demo2" valign="top"><p style="min-width:352px"></p></td>
	  									    </tr>
	  									</tbody>
	  								</table> -->
				<ul>
					<li id="demo1">
						<p id="content" name="content" >${wv.content }</p>
					</li>
					<li id="demo2"><p>
							<span></span>
						</p></li>
				</ul>
			</div>
		</div>

	</div>
	<div class="tx_div">
		<textarea rows="" cols="" class="textarea" name="content">${wv.content }</textarea>
	</div>
	<div class="cs_div">
		<div class="s_div">
			<p class="p1">
				速度: <span id="scrollBarTxt" style="text-align: center;"
					name="playSpeed"> ${wv.playSpeed } </span>
			</p>
			<div class="cz">
			 <p class="c_btn sd">
	        	<a href="javascript:void(0)" class="left"></a>
	        	<a href="javascript:void(0)" class="right"></a>
	        </p> 
				<div id="Demo">
					<div id="Main">
						<div id="scrollBar">
							<div id="scroll_Track"></div>
							<div id="scroll_Thumb"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="s_div">
			<p class="p2">
				字体大小:<span id="scrollBarTxt_1" style="text-align: center;"
					name="fontSize"> ${wv.fontSize } </span>
			</p>
			<div class="cz">
				<p class="c_btn dx">
		        	<a href="javascript:void(0)" class="left"></a>
		        	<a href="javascript:void(0)" class="right"></a>
		        </p> 
				<div id="Demo_1">
					<div id="Main_1">
						<div id="scrollBar_1">
							<div id="scroll_Track_1"></div>
							<div id="scroll_Thumb_1"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="s_div">
			<p class="p1">设置字体颜色</p>
			<div class="cz">
				<div class="yanse">
					<input id="Binded" class="span3" type="text"
						value="${wv.fontColor  }" name="fontColor"
						style="min-height: 24px; height: 10px; margin: 0" />
				</div>
			</div>
		</div>
		<div class="clear"></div>
		<div
			style="text-align: center; padding: 10px 0 50px; border: 1px solid #EAEAEA">
			<a href="javascript:void(0)" class="btn btn-warning"
				onclick="saveOrUpdate();">发送</a>
		</div>
</form>
<script type="text/javascript">

 	var checker;
	$(function() {
		checker = initValidator();
	});

	function initValidator() {
		return $("#welcome_form").validate({
			errorClass : "myerror",
			rules : {

			},
			messages : {

			}
		});
	}
 
	function saveOrUpdate() {
		if (checker.form()) {
			var teamId = $("#teamId").val();
			var fileUuid = $("#fileUuid").val();
			var playSpeeds = $("#scrollBarTxt").html().split("/");
			var fontSizes = $("#scrollBarTxt_1").html().split("/");

			var playSpeed = playSpeeds[0];
			var fontSize = fontSizes[0];

			var $requestData = formData2JSONObj("#welcome_form");
			if (teamId != null && teamId != "") {
				$requestData.teamId = teamId;
			}

			if (fileUuid != null && fileUuid != "") {
				$requestData.fileUuid = fileUuid;
			}

			if (playSpeed != null && playSpeed != "") {
				$requestData.playSpeed = playSpeed;
			}

			if (fontSize != null && fontSize != "") {
				$requestData.fontSize = fontSize;
			}

// 						alert(JSON.stringify($requestData));
			var url = "${ctp}/bbx/welcome/creator";
			if(teamId == undefined || teamId=='' || teamId=='-1'){
				$.error("请选择一个班级!");
			}else if(($requestData.content.length-1)>50){
				$.error("欢迎词不能超过50个字符，请重新填写!");
			}else{
				$.post(url, $requestData, function(data, status) {
					if ("success" === status) {
						$.success('保存成功!');
						data = eval("(" + data + ")");
						if ("success" === data.info) {
							$.success('保存成功!');
							search();
	
						} else {
							$.error("保存失败!");
						}
					} else {
						$.error("保存失败!");
					}
	
				});
				
			}
			
			
		}
	}
	
// 	加载选择模板对话框
	function loadCreatePage() {
		var teamId = $("#teamId").val();
		$.initWinOnTopFromLeft_bbx('选择模板', '${ctp}/bbx/welcome/creator?teamId='
				+ teamId, '700', '400');
	}
	
// 	$(document).ready(function(e) {
// 		//设置速度最大值
// 		ScrollBar.maxValue = 10;
// 		//速度初始化
// 		ScrollBar.Initialize();
// 		//设置大小最大值
// 		ScrollBar_1.maxValue = 100;
// 		//大小初始化
// 		ScrollBar_1.Initialize_1();

// 	});
	

	//颜色
// 	var FontColor=${wv.fontColor};
	var playSpeedForTeam = ${wv.playSpeed};
	/* 速度 */
	var ScrollBar = {
		value : playSpeedForTeam,
		maxValue : 10,
		step : 1,
		currentX : 0,
		Initialize : function() {
			if (this.value > this.maxValue) {
				alert("给定当前值大于了最大值");
				return;
			}
			this.GetValue();
			$("#scroll_Track").css("width", this.currentX + 2 + "px");
			$("#scroll_Thumb").css("margin-left", this.currentX + "px");
			this.Value();
			$("#scrollBarTxt").html(ScrollBar.value + "/" + ScrollBar.maxValue);
		},
		Value : function() {
			var valite = false;
			var currentValue;
			$("#scroll_Thumb").mousedown(function() {
				valite = true;
				$(document.body).mousemove(function(event) {
					if (valite == false) return;
					var main_width=$("#Demo").width()*0.14;
					var _offsetleft = $("#Demo").offset().left;
					currentValue = event.clientX - main_width-_offsetleft;
// 					var changeX = event.clientX - ScrollBar.currentX;
// 					currentValue = changeX - ScrollBar.currentX-$("#Demo").offset().left;
					$("#scroll_Thumb").css("margin-left", currentValue + "px");
					$("#scroll_Track").css("width", currentValue + 2 + "px");
					if ((currentValue + 25) >= $("#scrollBar").width()) {
						$("#scroll_Thumb").css("margin-left", $("#scrollBar").width() - 25 + "px");
						$("#scroll_Track").css("width", $("#scrollBar").width() + 2 + "px");
						ScrollBar.value = ScrollBar.maxValue;
					} else if (currentValue <= 0) {
						$("#scroll_Thumb").css("margin-left", "0px");
						$("#scroll_Track").css("width", "0px");
					} else {
						ScrollBar.value = Math.round(10 * (currentValue / $("#scrollBar").width()));
					}
					$("#scrollBarTxt").html(ScrollBar.value + "/" + ScrollBar.maxValue);
					var s=(10-ScrollBar.value)*10;
					clearInterval(MyMar)	
					if(ScrollBar.value >0 && ScrollBar.value <10){
					scroll(s)
					}else if(0==ScrollBar.value){
						scroll(99999999)
					}else{
						scroll(1);
					}
					
				});
							});
			$(document.body).mouseup(function() {
				valite = false;
				/* ScrollBar.value = Math.round(10 * (currentValue / $("#scrollBar").width()));
				if (ScrollBar.value >= ScrollBar.maxValue) ScrollBar.value = ScrollBar.maxValue;
				if (ScrollBar.value <= 0) ScrollBar.value = 0;
				$("#scrollBarTxt").html(ScrollBar.value + "/" + ScrollBar.maxValue); */
			});
		},
		GetValue : function() {
			this.currentX = $("#scrollBar").width()
					* (this.value / this.maxValue);
		}
	}
	
	var fontSize = ${wv.fontSize};
	/* 大小 */
	var ScrollBar_1 = {
		value_1 : fontSize,
		maxValue : 100,
		step : 1,
		currentX : 0,
		Initialize_1 : function() {
			if (this.value_1 > this.maxValue) {
				alert("给定当前值大于了最大值");
				return;
			}
			this.GetValue();
			$("#scroll_Track_1").css("width", this.currentX + 2 + "px");
			$("#scroll_Thumb_1").css("margin-left", this.currentX + "px");
			this.Value_1();
			$("#scrollBarTxt_1").html(
					ScrollBar_1.value_1 + "/" + ScrollBar_1.maxValue);
		},
		Value_1 : function() {
			var valite = false;
			var currentValue;
			$("#scroll_Thumb_1").mousedown(function() {
				valite = true;
				$(document.body).mousemove(function(event) {
					if (valite == false) return;
					var main_width=$("#Demo_1").width()*0.14;
					var _offsetleft = $("#Demo_1").offset().left;
					currentValue = event.clientX - main_width-_offsetleft;

// 					var changeX = event.clientX - ScrollBar_1.currentX;
// 					currentValue = changeX - ScrollBar_1.currentX-$("#Demo_1").offset().left;
					$("#scroll_Thumb_1").css("margin-left", currentValue + "px");
					$("#scroll_Track_1").css("width", currentValue + 2 + "px");
					if ((currentValue + 25) >= $("#scrollBar_1").width()) {
						$("#scroll_Thumb_1").css("margin-left", $("#scrollBar_1").width() - 25 + "px");
						$("#scroll_Track_1").css("width", $("#scrollBar_1").width() + 2 + "px");
						ScrollBar_1.value_1 = ScrollBar_1.maxValue;
					} else if (currentValue <= 0) {
						$("#scroll_Thumb_1").css("margin-left", "0px");
						$("#scroll_Track_1").css("width", "0px");
					} else {
						ScrollBar_1.value_1 = Math.round(100 * (currentValue / $("#scrollBar_1").width()));
					}
					$("#scrollBarTxt_1").html(ScrollBar_1.value_1 + "/" + ScrollBar_1.maxValue);
					var s=(ScrollBar_1.value_1)*1.2;
					$(".preview .yl_main .hyc p").css("font-size",s);
					var width=$(".preview .yl_main li").width()*2+1;
					$(".hyc ul").css("width",width)
				});
			});
			$(document.body).mouseup(function() {
				ScrollBar.value_1 = Math.round(100 * (currentValue / $("#scrollBar_1").width()));
				valite = false;
				if (ScrollBar_1.value_1 >= ScrollBar_1.maxValue) ScrollBar_1.value_1 = ScrollBar_1.maxValue;
				if (ScrollBar_1.value_1 <= 0) ScrollBar_1.value_1 = 0;
				$("#scrollBarTxt_1").html(ScrollBar_1.value_1 + "/" + ScrollBar_1.maxValue);
			});
		},
		GetValue : function() {
			this.currentX = $("#scrollBar_1").width()
					* (this.value_1 / this.maxValue);
		}
	}
	/* 欢迎词滚动 */
	function Marquee() {
		if (demo2.offsetWidth - demo.scrollLeft <= 0)
			demo.scrollLeft = demo.scrollLeft - demo1.offsetWidth - 3
		else {
			demo.scrollLeft = demo.scrollLeft + 3;
		}
	}
	var MyMar;
	function scroll(s) {
		var speed = s;
		demo2.innerHTML = demo1.innerHTML;
		MyMar = setInterval(Marquee, speed)
		demo.onmouseover = function() {
			clearInterval(MyMar);
		}
		demo.onmouseout = function() {
			MyMar = setInterval(Marquee, speed);
		}
	}
	

	$(function() {
		/* 颜色选择 */
		$('#Binded').jPicker({}, function(color, context) {
			//$(this).css("text-indent","-9999px");
			var all = color.val('all');
			$('.yl_main li p').css({
				color : all && '#' + all.hex || 'transparent'
			}); // prevent IE from throwing exception if hex is empty
		});
		/* 选择模板的位置 */
// 		var w = $("body").width() / 2 + 200;
// 		$(".xzmb").css("left", w);
// 		$(window).resize(function() {
// 			var w = $("body").width() / 2 + 200;
// 			$(".xzmb").css("left", w);
// 		});
		/* scroll(80); */
		/*  欢迎词的改变 */
		$(".textarea").change(function() {
			var text = $(this).val();
			$("#demo1 p,#demo2 p").text(text)
		});
		/* 速度左右键 */
		$(".s_div .cz .sd .left").click(function() {
			var currents = $("#scrollBarTxt").html().split("/");
			var current = currents[0];
			if (current >= 1) {
				var x_sd=parseInt(current) - 1;
				ScrollBar.value = x_sd;
				ScrollBar.Initialize();
				var s1=(10-x_sd)*10;
				clearInterval(MyMar)	
				if(ScrollBar.value >0 && ScrollBar.value <10){
				scroll(s1)
				}else if(0==ScrollBar.value){
					scroll(99999999)
				}else{
					scroll(1);
				}
			}
		})
		$(".s_div .cz .sd .right").click(function() {
			var currents = $("#scrollBarTxt").html().split("/");
			var current = currents[0];
			if (current < 10) {
				var x_sd=parseInt(current) + 1;
				ScrollBar.value = x_sd;
				ScrollBar.Initialize();
				var s1=(10-x_sd)*10;
				clearInterval(MyMar)	
				if(ScrollBar.value >0 && ScrollBar.value <10){
				scroll(s1)
				}else if(0==ScrollBar.value){
					scroll(99999999)
				}else{
					scroll(1);
				}
			}
		})
		/* 大小左右键 */
		$(".s_div .cz .dx .left").click(function() {
			var currents = $("#scrollBarTxt_1").html().split("/");
			var current = currents[0];
			if (current > 0) {
				var x_sd=parseInt(current) - 1;
				ScrollBar_1.value_1 = x_sd;
				ScrollBar_1.Initialize_1();
				var s2=(x_sd)*1.2;
				$(".preview .yl_main .hyc p").css("font-size",s2);
				var width=$(".preview .yl_main li").width()*2+1;
				$(".hyc ul").css("width",width);
			}
		})
		$(".s_div .cz .dx .right").click(function() {
			var currents = $("#scrollBarTxt_1").html().split("/");
			var current = currents[0];
			if (current < 100) {
				var x_sd=parseInt(current) + 1;
				ScrollBar_1.value_1 = x_sd;
				ScrollBar_1.Initialize_1();
				var s2=(x_sd)*1.2;
				$(".preview .yl_main .hyc p").css("font-size",s2);
				var width=$(".preview .yl_main li").width()*2+1;
				$(".hyc ul").css("width",width);
			}
		});
		/* 欢迎词初始状态 */
		/* 速度 */
		ScrollBar.value=playSpeedForTeam;
		ScrollBar.Initialize();
		//scroll(99999);
		var s1=(10-playSpeedForTeam)*10;
		clearInterval(MyMar)	
		if(ScrollBar.value >0 && ScrollBar.value <10){
		scroll(s1)
		}else if(0==ScrollBar.value){
			scroll(99999999)
		}else{
			scroll(1);
		}
		/* 大小 */
		ScrollBar_1.value_1 = fontSize;
		ScrollBar_1.Initialize_1();
		var s2=(ScrollBar_1.value_1)*1.2;
		$(".preview .yl_main .hyc p").css("font-size",s2);
		var width=$(".preview .yl_main li").width()*2+1;
		$(".hyc ul").css("width",width);
		/* 颜色 */
//  		var FontColor=${wv.fontColor};
 		var fontcolor=$("#Binded").val();
		$(".preview .yl_main .hyc p").css("color","#"+fontcolor);
	})
	
</script>
