/*
此插件基于Jquery
插件名：jquery.Sonline(在线客服插件)
作者 似懂非懂
版本 2.0
Blog：www.haw86.com
*/
(function($){
	$.fn.Sonline = function(options){
        var opts = $.extend({}, $.fn.Sonline.defualts, options); 
		$.fn.setList(opts); //调用列表设置
		$.fn.Sonline.styleType(opts);
		if(opts.DefaultsOpen == false){
			$.fn.Sonline.closes(opts.Position,0);
		}
		if($().jquery === "1.4.2") {
			//展开
			$("#SonlineBox > .openTrigger").live("click",function(){$.fn.Sonline.opens(opts);});
			//关闭
			$("#SonlineBox > .contentBox > .closeTrigger").live("click",function(){$.fn.Sonline.closes(opts.Position,"fast");});
		} else {
			//展开
			$("body").on("click", "#SonlineBox > .openTrigger",function(){$.fn.Sonline.opens(opts);});
			//关闭
			$("body").on("click","#SonlineBox > .contentBox > .closeTrigger",function(){$.fn.Sonline.closes(opts.Position,"fast");});
		}
		
		//Ie6兼容或滚动方式显示
//		if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style||opts.Effect==true) {$.fn.Sonline.scrollType();}
//		else if(opts.Effect==false){
			$("#SonlineBox").css({position:"fixed"});
//		}
	}
	//plugin defaults
	$.fn.Sonline.defualts ={
		Position:"left",//left或right
		Bottom:100,//顶部距离，默认200px
		Effect:true, //滚动或者固定两种方式，布尔值：true或
		Width:170,//顶部距离，默认200px
		DefaultsOpen:true, //默认展开：true,默认收缩：false
		Style:1,//图标的显示风格，默认显示:1
		Time:"",//服务热线
		Qqlist:"" //多个QQ用','隔开，QQ和客服名用'|'隔开
	}
	
	//展开
	$.fn.Sonline.opens = function(opts){
		var positionType = opts.Position;
		$("#SonlineBox").css({width:opts.Width+4});
		if(positionType=="left"){$("#SonlineBox > .contentBox").animate({left: 0},"fast");}
		else if(positionType=="right"){$("#SonlineBox > .contentBox").animate({right: 0},"fast");}
		$("#SonlineBox > .openTrigger").hide();
	}

	//关闭
	$.fn.Sonline.closes = function(positionType,speed){
		$("#SonlineBox > .openTrigger").show();
		var widthValue =$("#SonlineBox > .openTrigger").width();
		var allWidth =(-($("#SonlineBox > .contentBox").width())-6);
		if(positionType=="left"){$("#SonlineBox > .contentBox").animate({left: allWidth},speed);}
		else if(positionType=="right"){$("#SonlineBox > .contentBox").animate({right: allWidth},speed);}
		$("#SonlineBox").animate({width:widthValue},speed);
		
	}
	
	//风格选择
	$.fn.Sonline.styleType = function(opts){
		var typeNum = 1;
		switch(opts.Style) 
		{ case 1:
				typeNum = 41;
	 　　    break
	 		 case 2:
				typeNum = 42;
	　　     break
	 		 case 3:
				typeNum = 44;
	　　     break
	 		 case 4:
				typeNum = 45;
	　　     break
	 		 case 5:
				typeNum = 46;
	　　     break
	 		 case 6:
				typeNum = 47;
	　　     break
	　　     default:
				typeNum = 41;
	　　   }
		return typeNum;
	}

	//子插件：设置列表参数
	$.fn.setList = function(opts){
		$("body").append("<div class='SonlineBox' id='SonlineBox' style='position:absolute;'><div class='openTrigger' style='display:none' title='展开'></div><div class='contentBox'><div class='closeTrigger' title='关闭'></div><div class='titleBox'><span>客服中心</span></div><div class='listBox'></div><div class='tels'><font>服务时间   (<span class='areaName'>北京</span>)：</font><span>"+opts.Time+"</span></div></div></div>");
		$("#SonlineBox > .contentBox").width(opts.Width)
		if(opts.Qqlist==""){$("#SonlineBox > .contentBox > .listBox").append("<p style='padding:15px'>暂无在线客服。</p>")}
		else{var qqListHtml = $.fn.Sonline.splitStr(opts);$("#SonlineBox > .contentBox > .listBox").append(qqListHtml);	}
		if(opts.Position=="left"){$("#SonlineBox").css({left:0});}
		else if(opts.Position=="right"){$("#SonlineBox").css({right:0})}
		$("#SonlineBox").css({bottom:opts.Bottom,width:opts.Width+4});
		var allHeights=0;
		if($("#SonlineBox > .contentBox").height() < $("#SonlineBox > .openTrigger").height()){
			allHeights = $("#SonlineBox > .openTrigger").height()+4;
		} else{allHeights = $("#SonlineBox > .contentBox").height()+10;}
		$("#SonlineBox").height(allHeights);
		if(opts.Position=="left"){$("#SonlineBox > .openTrigger").css({left:0});}
		else if(opts.Position=="right"){$("#SonlineBox > .openTrigger").css({right:0});}
	}
	
	//滑动式效果
	$.fn.Sonline.scrollType = function(){
		$("#SonlineBox").css({position:"absolute"});
		var topNum = parseInt($("#SonlineBox").css("top")+"");
		$(window).scroll(function(){
			var scrollTopNum = $(window).scrollTop();//获取网页被卷去的高
			$("#SonlineBox").stop(true,false).delay(200).animate({top:scrollTopNum+topNum},"slow");
		});
	}
	
	//分割QQ
	$.fn.Sonline.splitStr = function(opts){
		var strs= new Array(); //定义一数组
		var QqlistText = opts.Qqlist;
		strs=QqlistText.split(","); //字符分割
		var QqHtml=""
		for (var i=0;i<strs.length;i++){	
			var subStrs= new Array(); //定义一数组
			var subQqlist = strs[i];
			subStrs = subQqlist.split("|"); //字符分割
			QqHtml = QqHtml+"<div class='QQList'><span>"+subStrs[1]+"：</span><div class='ico'><a target='_blank' href='https://wpa.qq.com/msgrd?v=3&uin="+subStrs[0]+"&site=qq&menu=yes'><img border='0' src='https://wpa.qq.com/pa?p=2:"+subStrs[0]+":"+$.fn.Sonline.styleType(opts)+" &amp;r=0.22914223582483828' alt='点击这里'></a></div><a target='_blank' href='https://wpa.qq.com/msgrd?v=3&uin="+subStrs[0]+"&site=qq&menu=yes'><span id='use_name'>" + subStrs[2] + "</span></a><div style='clear:both;'></div></div>";
//			QqHtml = QqHtml+"<div class='QQList'><span>"+subStrs[1]+"：</span><div class='ico'><a target='_blank' href='https://wpa.qq.com/msgrd?v=3&uin="+subStrs[0]+"&site=qq&menu=yes'><img border='0' src='https://wpa.qq.com/pa?p=2:"+subStrs[0]+":"+$.fn.Sonline.styleType(opts)+" &amp;r=0.22914223582483828' alt='点击这里'></a></div><span id='use_name'>" + subStrs[2] + "</span><div style='clear:both;'></div></div>";
		}
		
		//qq群设置
		var groups = new Array();
		var QqGroupListText = opts.QqGroupList;
		
		if(QqGroupListText != "false") {
			groups = QqGroupListText.split(",");
			for(var i = 0; i < groups.length; i++) {
				var subStrs = new Array();
				var subGroupList = groups[i];
				subStrs = subGroupList.split("|");
				//idkey = e4606c2d6c6e053ced9855590dd6891efeef530ea093b1cde1d760871da2fed1
				QqHtml = QqHtml+"<div class='QQList'><span>"+subStrs[1]+"：</span><div class='ico'><a target='_blank' href='https://shang.qq.com/wpa/qunwpa?idkey="+subStrs[0]+"'><img border='0' src='https://pub.idqqimg.com/wpa/images/group.png' alt='"+subStrs[1]+"' title='"+subStrs[1]+"'></a><div style='clear:both;'></div></div>";
			}
		}
		
		if(opts.FeedbackOpen == true) {
			QqHtml = QqHtml+"<div class='QQList'><a target='_blank' href='https://www.sojump.com/jq/2991183.aspx'><span class='feedback' style='margin-left:35%'></span></a></div>";
		}
		return QqHtml;
	}
})(jQuery);    


 