/* 代码整理：懒人之家 www.lanrenzhijia.com */
$(function() {
	var mainOnNum = 1;
	var timer;
	var topIndex = 1;
	var width = $(window).width();

	function onTimer() {
		if (mainOnNum > 4) {
			mainOnNum = 1;
		} else {
			mainOnNum++;
		}

		$(".control li").eq(mainOnNum-1).trigger("click");
	}

	function changeSceneSelector($num) {
		mainOnNum = $num+1;

		changeScene(mainOnNum);
	}
/* 代码整理：懒人之家 www.lanrenzhijia.com */
	function changeScene($num) {
		clearInterval(timer);

		mainOnNum = $num;

		topIndex++;

		switch($num) {
			case 1:
				timer = setInterval(onTimer,7000);

				$("#scene1").css({"z-index":topIndex});

				motion($num);
			break;
			
			case 2:
				timer = setInterval(onTimer,7000);

				$("#scene2").css({"z-index":topIndex});

				motion($num);
			break;

			case 3:
				timer = setInterval(onTimer,7000);

				$("#scene3").css({"z-index":topIndex});

				motion($num);
			break;

			case 4:
				timer = setInterval(onTimer,7000);

				$("#scene4").css({"z-index":topIndex});

				motion($num);
			break;
		}
	}

	function clearFunctions(){
		$("li").clearQueue();
		$("li").stop();

		$("p").clearQueue();
		$("p").stop();

		$("h2").clearQueue();
		$("h2").stop();

		$("ul").clearQueue();
		$("ul").stop();
	}/* 代码整理：懒人之家 www.lanrenzhijia.com */

	function motion($mc) {
		clearFunctions();
/* 代码整理：懒人之家 www.lanrenzhijia.com */
		$("#scene1").stop().css("left",width);
		/*$("#scene1 h2").stop().css("top","-400px");
		$("#scene1 .link").stop().css("top","-400px");*/
		$("#scene1 .pointer1").stop().css("top","600px");
		$("#scene1 .pointer2").stop().css("top","-600px");
		$("#scene1 .pointer3").stop().css("top","600px");

		$("#scene2").stop().css("left",width);
		/*$("#scene2 h2").stop().css("top","-400px");
		$("#scene2 .link").stop().css("top","-400px");*/
		$("#scene2 .pointer1").stop().css("top","630px");
		$("#scene2 .pointer2").stop().css("top","-630px");
		$("#scene2 .pointer3").stop().css("top","600px");
		/*$("#scene2 .pointer4").stop().css("top","-200px");*/

		$("#scene3").stop().css("left",width);
		/*$("#scene3 h2").stop().css("top","-400px");
		$("#scene3 .link").stop().css("top","-400px");*/
		$("#scene3 .pointer1").stop().css("top","650px");
		$("#scene3 .pointer2").stop().css("top","-650px");
		$("#scene3 .pointer3").stop().css("top","650px");
		/*$("#scene3 .pointer4").stop().css("top","650px");*/

		$("#scene4").stop().css("left",width);
		$("#scene4 .pointer1").stop().css("top","650px");
		$("#scene4 .pointer2").stop().css("top","-650px");
		$("#scene4 .pointer3").stop().css("top","650px");

		if($mc == 1) {
			$("#scene1").animate({"left":"0"},{duration:700, easing:"easeOutExpo"});
			/*$("#scene1 h2").delay(300).animate({"top":"35px"},{duration:1000, easing:"easeInOutCirc"});
			$("#scene1 .link").delay(100).animate({"top":"175px"},{duration:1000, easing:"easeInOutCirc"});*/
			$("#scene1 .pointer1").delay(600).animate({"top":"63px"},{duration:300, easing:"easeOutBack"});
			$("#scene1 .pointer2").delay(800).animate({"top":"103px"},{duration:600, easing:"easeOutBack"});
			$("#scene1 .pointer3").delay(1000).animate({"top":"206px"},{duration:300, easing:"easeOutBack"});

		} else if($mc == 2) {
			$("#scene2").animate({"left":"0"},{duration:700, easing:"easeOutExpo"});
			/*$("#scene2 h2").delay(300).animate({"top":"35px"},{duration:1000, easing:"easeInOutCirc"});
			$("#scene2 .link").delay(100).animate({"top":"175px"},{duration:1000, easing:"easeInOutCirc"});*/
			$("#scene2 .pointer1").delay(600).animate({"top":"98px"},{duration:300, easing:"easeOutBack"});
			$("#scene2 .pointer2").delay(800).animate({"top":"105px"},{duration:300, easing:"easeOutBack"});
			$("#scene2 .pointer3").delay(1000).animate({"top":"193px"},{duration:300, easing:"easeOutBack"});
			$("#scene2 .pointer4").delay(1300).animate({"top":"36px"},{duration:300, easing:"easeOutBack"});
		} else if($mc == 3){
			$("#scene3").animate({"left":"0"},{duration:700, easing:"easeOutExpo"});
			/*$("#scene3 h2").delay(300).animate({"top":"35px"},{duration:1000, easing:"easeInOutCirc"});
			$("#scene3 .link").delay(100).animate({"top":"175px"},{duration:1000, easing:"easeInOutCirc"});*/
			$("#scene3 .pointer1").delay(600).animate({"top":"80px"},{duration:300, easing:"easeOutBack"});
			$("#scene3 .pointer2").delay(800).animate({"top":"105px"},{duration:300, easing:"easeOutBack"});
			$("#scene3 .pointer3").delay(1000).animate({"top":"173px"},{duration:300, easing:"easeOutBack"});
			$("#scene3 .pointer4").delay(2300).animate({"top":"113px"},{duration:300, easing:"easeOutBack"});
		}else{
			$("#scene4").animate({"left":"0"},{duration:700, easing:"easeOutExpo"});
			$("#scene4 .pointer1").delay(600).animate({"top":"109px"},{duration:300, easing:"easeOutBack"});
			$("#scene4 .pointer2").delay(800).animate({"top":"105px"},{duration:300, easing:"easeOutBack"});
			$("#scene4 .pointer3").delay(1000).animate({"top":"94px"},{duration:300, easing:"easeOutBack"});
		}
	}
/* 代码整理：懒人之家 www.lanrenzhijia.com */
	function init() { 
		var $btns = $(".control li"),
			$motionC = $(".control #btnPlay");

		$btns.bind("click", function() {
			clearInterval(timer);
			timer = setInterval(onTimer,7000);
			var $this = $(this),
				index = $btns.index($this);
			
//			if(window.console) {console.log("clicked : " + mainOnNum);}
			$btns.removeClass("on"); 
			$this.addClass("on");
			
			changeSceneSelector(index);
			return false;
		});
/* 代码整理：懒人之家 www.lanrenzhijia.com */
		$motionC.bind("click", function() {
			var $this = $(this);
			if($this.hasClass("on")) {
				$this.text("自动播放");
				$this.attr("title","自动播放");
				$this.removeClass("on");
				clearInterval(timer);
			} else {
				$this.text("自动播放 停止");
				$this.attr("title","自动播放 停止");
				$this.addClass("on");
				clearInterval(timer);
				timer = setInterval(onTimer,3000);
			}
			return false;
		});

		changeScene(1);
	}

	var goInit = setTimeout(function() {
		init();
	}, 10);

	$(".scene").css("display","block");
	$(".scene").css("left",width);

});
/* 代码整理：懒人之家 www.lanrenzhijia.com */