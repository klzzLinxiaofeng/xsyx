<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	(function($){
		//不提供调用，请勿调用（绿色title）
		$.initWindowBase = function(onWhere, title, url, width, height, topVal, shift) {
			if("top" === onWhere) {
				window.top.layer.open({
					skin: 'layui-layer-lvse', //样式类名
				    type: 2,
				    title: title,
				    //closeBtn: false, //显示关闭按钮
				    shadeClose: true,
				    shade: 0.8,
				    area:[ width + 'px', height + 'px' ],
				   /*  offset:[ topVal + 'px', '' ], */
				    maxmin: false, //开启最大化最小化按钮
				    shift: shift,
				    content: url //iframe的url，no代表不显示滚动条
				   // time: 2000, //2秒后自动关闭
				});
			} else if ('cur' === onWhere){
				layer.open({
					/* extend: ['skin/myskin/style.css'], //加载您的扩展样式
					skin: 'layer-ext-yourskin', //一旦设定，所有弹层风格都采用此主题。 */
					skin: 'layui-layer-lvse', //样式类名
				    type: 2,
				    title: title,
				    //closeBtn: false, //显示关闭按钮
				    shadeClose: true,
				    shade: 0.8,
				    area:[ width + 'px', height + 'px' ],
				   /*  offset:[ top + 'px', '' ], */
				    maxmin: false, //开启最大化最小化按钮
				    shift: shift,
				    content: url //iframe的url，no代表不显示滚动条
				});
			}
		}
		
		//班班星用 不提供调用，请勿调用（蓝色title）
		$.initWindowBase_bbx = function(onWhere, title, url, width, height, topVal, shift) {
			if("top" === onWhere) {
				window.top.layer.open({
					skin: 'layui-layer-bbx', //样式类名
					//btn: ['重要','奇葩','你好'], //按钮
				    type: 2,
				    title: title,
				    //closeBtn: false, //显示关闭按钮
				    shadeClose: true,
				    shade: 0.8,
				    area:[ width + 'px', height + 'px' ],
				   /*  offset:[ topVal + 'px', '' ], */
				    maxmin: false, //开启最大化最小化按钮
				    shift: shift,
				    content: url //iframe的url，no代表不显示滚动条
				   // time: 2000, //2秒后自动关闭
				});
			} else if ('cur' === onWhere){
				layer.open({
					skin: 'layui-layer-lvse', //样式类名
					//btn: ['重要','奇葩','你好'], //按钮
				    type: 2,
				    title: title,
				    //closeBtn: false, //显示关闭按钮
				    shadeClose: true,
				    shade: 0.8,
				    area:[ width + 'px', height + 'px' ],
				   /*  offset:[ top + 'px', '' ], */
				    maxmin: false, //开启最大化最小化按钮
				    shift: shift,
				    content: url //iframe的url，no代表不显示滚动条
				});
			}
		}
		
		//奇云教学用 不提供调用，请勿调用（白色title）
		$.initWindowBase_qyjx = function(onWhere, title, url, width, height, topVal, shift) {
			if("top" === onWhere) {
				window.top.layer.open({
					skin: 'layui-layer-qyjx', //样式类名
					//btn: ['重要','奇葩','你好'], //按钮
				    type: 2,
				    title: title,
				    //closeBtn: false, //显示关闭按钮
				    shadeClose: true,
				    shade: 0.8,
				    area:[ width + 'px', height + 'px' ],
				   /*  offset:[ topVal + 'px', '' ], */
				    maxmin: false, //开启最大化最小化按钮
				    shift: shift,
				    content: url //iframe的url，no代表不显示滚动条
				   // time: 2000, //2秒后自动关闭
				});
			} else if ('cur' === onWhere){
				layer.open({
					skin: 'layui-layer-lvse', //样式类名
					//btn: ['重要','奇葩','你好'], //按钮
				    type: 2,
				    title: title,
				    //closeBtn: false, //显示关闭按钮
				    shadeClose: true,
				    shade: 0.8,
				    area:[ width + 'px', height + 'px' ],
				   /*  offset:[ top + 'px', '' ], */
				    maxmin: false, //开启最大化最小化按钮
				    shift: shift,
				    content: url //iframe的url，no代表不显示滚动条
				});
			}
		}
		
		//不提供调用，请勿调用
		$.initDialogBase = function(msg, yes, close, type) {
			if(yes ==  undefined) {
				yes = function() {};
			}
			if(close == undefined) {
				close = function() {};
			}
			if(top != null && top != undefined) {
				/*  top.$.layer({
				    shade: [0.4,"#000"],
				    area: ['auto','auto'],
				    dialog: {
				        msg: msg,
				        btns: 1,                    
				        type : type,
				        btn: ['确定'],
				        yes: function(index) {
				        	yes();
				        	top.layer.close(index);
				        }
				    },
				    closeBtn : [0, true],
			        close : close
				});  */
				
				 top.layer.confirm(msg, {
				    btn: ['确定'] //按钮
				}, function(index){
				    /* layer.msg('的确很重要', {icon: 1}); */
				    top.layer.close(index);
				}); 
				
			} else {
				/* $.layer({
				    shade: [0.4,"#000"],
				    area: ['auto','auto'],
				    dialog: {
				        msg: msg,
				        btns: 1,                    
				        type : 1,
				        btn: ['确定'],
				        yes: function(index) {
				        	yes();
				        	layer.close(index);
				        }
				    },
				    closeBtn : [0, true],
			        close : close
				}); */
				layer.confirm(msg, {
				    btn: [确定] //按钮
				}, function(index){
				    /* layer.msg('的确很重要', {icon: 1}); */
				    layer.close(index);
				}); 
			}
			
		}
		
		//在最顶层窗口的左侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
		$.initWinOnTopFromLeft = function(title, url, width, height, top) {
			if (width == undefined) {
				width = $(parent.window).width() - 50;
			}
			if (height == undefined) {
				height = $(parent.window).height() - 50;
			}
			if (top == undefined) {
				/* top = '20'; */
			}
			$.initWindowBase('top', title, url, width, height, top, 'left');
		}
		
		//在最顶层的窗口顶侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
		$.initWinOnTopFromTop = function(title, url, width, height, top) {
			if (width == undefined) {
				width = $(parent.window).width() - 50;
			}
			if (height == undefined) {
				height = $(parent.window).height() - 50;
			}
			if (top == undefined) {
				/* top = 20; */
			}
			$.initWindowBase('top', title, url, width, height, top, 'top');
		}
		
		//在当前的窗口左侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
		$.initWinOnCurFromLeft = function(title, url, width, height, top) {
			if (width == undefined) {
				width = $(parent.window).width() - 50;
			}
			if (height == undefined) {
				height = $(parent.window).height() - 50;
			}
			if (top == undefined) {
				/* top = '20'; */
			}
			$.initWindowBase('cur', title, url, width, height, top, 'left');
		}
		
		//在当前的窗口顶侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
		$.initWinOnCurFromTop = function(title, url, width, height, top) {
			if (width == undefined) {
				width = $(parent.window).width() - 50;
			}
			if (height == undefined) {
				height = $(parent.window).height() - 50;
			}
			if (top == undefined) {
				/* top = 20; */
			}
			$.initWindowBase('cur', title, url, width, height, top, 'top');
		}
		
		//在最顶层窗口的左侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
		$.initWinOnTopFromLeft_bbx = function(title, url, width, height, top) {
			if (width == undefined) {
				width = $(parent.window).width() - 50;
			}
			if (height == undefined) {
				height = $(parent.window).height() - 50;
			}
			if (top == undefined) {
				/* top = '20'; */
			}
			$.initWindowBase_bbx('top', title, url, width, height, top, 'left');
		}
		
		//在最顶层的窗口顶侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
		$.initWinOnTopFromTop_bbx = function(title, url, width, height, top) {
			if (width == undefined) {
				width = $(parent.window).width() - 50;
			}
			if (height == undefined) {
				height = $(parent.window).height() - 50;
			}
			if (top == undefined) {
				/* top = 20; */
			}
			$.initWindowBase_bbx('top', title, url, width, height, top, 'top');
		}
		
		//在当前的窗口左侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
		$.initWinOnCurFromLeft_bbx = function(title, url, width, height, top) {
			if (width == undefined) {
				width = $(parent.window).width() - 50;
			}
			if (height == undefined) {
				height = $(parent.window).height() - 50;
			}
			if (top == undefined) {
				/* top = '20'; */
			}
			$.initWindowBase_bbx('cur', title, url, width, height, top, 'left');
		}
		
		//在当前的窗口顶侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
		$.initWinOnCurFromTop_bbx = function(title, url, width, height, top) {
			if (width == undefined) {
				width = $(parent.window).width() - 50;
			}
			if (height == undefined) {
				height = $(parent.window).height() - 50;
			}
			if (top == undefined) {
				/* top = 20; */
			}
			$.initWindowBase_bbx('cur', title, url, width, height, top, 'top');
		}
		
		//在最顶层窗口的左侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
		$.initWinOnTopFromLeft_qyjx = function(title, url, width, height, top) {
			if (width == undefined) {
				width = $(parent.window).width() - 50;
			}
			if (height == undefined) {
				height = $(parent.window).height() - 50;
			}
			if (top == undefined) {
				/* top = '20'; */
			}
			$.initWindowBase_qyjx('top', title, url, width, height, top, 'left');
		}
		
		//在最顶层的窗口顶侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
		$.initWinOnTopFromTop_qyjx = function(title, url, width, height, top) {
			if (width == undefined) {
				width = $(parent.window).width() - 50;
			}
			if (height == undefined) {
				height = $(parent.window).height() - 50;
			}
			if (top == undefined) {
				/* top = 20; */
			}
			$.initWindowBase_qyjx('top', title, url, width, height, top, 'top');
		}
		
		//在当前的窗口左侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
		$.initWinOnCurFromLeft_qyjx = function(title, url, width, height, top) {
			if (width == undefined) {
				width = $(parent.window).width() - 50;
			}
			if (height == undefined) {
				height = $(parent.window).height() - 50;
			}
			if (top == undefined) {
				/* top = '20'; */
			}
			$.initWindowBase_qyjx('cur', title, url, width, height, top, 'left');
		}
		
		//在当前的窗口顶侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
		$.initWinOnCurFromTop_qyjx = function(title, url, width, height, top) {
			if (width == undefined) {
				width = $(parent.window).width() - 50;
			}
			if (height == undefined) {
				height = $(parent.window).height() - 50;
			}
			if (top == undefined) {
				/* top = 20; */
			}
			$.initWindowBase_qyjx('cur', title, url, width, height, top, 'top');
		}
		
		//操作错误提示标签
		$.error = function(msg) {
			var topLayer = top != null ? top.layer : layer;
			topLayer.msg(msg, {icon: 2});
		}
// 		example:
// 		$.error("抱歉，操作失败!");	
		
		//操作成功提示标签
		$.success =  function(msg) {
			var topLayer = top != null ? top.layer : layer;
			topLayer.msg(msg, {icon: 1}, 1);
		}
// 		example:
// 		$.success("恭喜您，操作成功!");		
	
		//确认对话框
		$.confirm = function(msg, yes, no) {
			var topLayer = top != null ? top.layer : layer;
			yes == undefined ? yes = function(){} : yes;
			no == undefined ? no = function(){} : no;
			topLayer.confirm(msg, function(index) {
				yes();
				topLayer.close(index);
			}, no);
		}
// 		example:
// 		$.confirm("确认执行此操作？", function () {
// 			alert("您点击了确定");
// 		}, function() {
// 			alert("您点击了关闭");
// 		});
		
		//错误提示对话框
		$.errorDialog = function(msg, yes, close) {
			$.initDialogBase(msg, yes, close, 3);
		}
// 		example：
// 		$.errorDialog("您长时间未操作平台，为确保您安全使用，请重新登录", function() {
// 			top.location.href=location.href;
// 		}, function(index) {
// 			top.location.href=location.href;
// 		})
		
		//成功提示对话框
		$.sucDialog = function(msg, yes, close) {
			$.initDialogBase(msg, yes, close, 1);
		}
// 		example：
// 		$.errorDialog("恭喜您操作成功！", function() {
// 			top.location.href=location.href;
// 		}, function(index) {
// 			top.location.href=location.href;
// 		})
	
		//简易提示对话框
		$.alert = function(msg) {
			var topLayer = top != null? top.layer : layer;
			topLayer.alert(msg, 9);
		}
		
		//奇云教学简易提示对话框
		$.qyalert = function(msg,title) {
			var topLayer = top != null? top.layer : layer;
			topLayer.alert(msg,{title:title});
		}
		
		$.closeWindow = function() {
			var layer = parent.layer;
			var frameIndex = layer.getFrameIndex(window.name);
			layer.close(frameIndex);
		}
		
		$.closeWindowByName = function(windowName) {
			var frameIndex = layer.getFrameIndex(windowName);
			layer.close(frameIndex);
		}
		
		$.closeWindowByNameOnParent = function(windowName) {
			var layer = parent.layer;
			var frameIndex = layer.getFrameIndex(windowName);
			layer.close(frameIndex);
		}
		$.closeWindowOnParent = function(){
			var layer = parent.parent.layer;
			var frameIndex = layer.getFrameIndex(window.parent.name);
			layer.close(frameIndex);
		}
		$.prompt = function(title, yes, no) {
			var topLayer = top != null? top.layer : layer;
			return topLayer.prompt({
						title : title,
						type : 3
					}, yes, no);
		}
		
		
		$.refreshWin = function() {
			if(parent != null && parent.loader != null) {
				parent.loader.show();
			}
			window.location.reload();
		}
		
		$.initWindow = function(options) {
			var defOption = {
				"on" : "top",
				"maxmin" : true ,
				"shadeClose" : true,
				"title" : "",
				"border" : [0],
				"top" : "20",
				"shade" : [ 0.1, '#fff' ],
				"width" : $(parent.window).width(),
				"height" :$(parent.window).height() - 50,
				"shift" : "top",
				"url" : "",
				"close" : function(index) {},
				"closeBtn" : 1
			};
			options = $.extend({}, defOption, options || {});
			
			if("top" === options.top) {
				window.top.layer.open({
					type : 2,
					maxmin : options.maxmin,
					shadeClose : options.shadeClose,
					title : options.title,
					border : options.border,
					shade : options.shade,
					offset : [ options.top + 'px', '' ],
					area : [ options.width + 'px', options.height + 'px' ],
					shift : options.shift,
					 content:options.url,
					close : options.close,
					closeBtn : options.closeBtn
				});
				
			} else {
				layer.open({
					type : 2,
					maxmin : options.maxmin,
					shadeClose : options.shadeClose,
					title : options.title,
					shade : options.shade,
					border : options.border,
					offset : [ options.top + 'px', '' ],
					area : [ options.width + 'px', options.height + 'px' ],
					shift : options.shift,
					content:options.url,
					close : options.close,
					closeBtn : options.closeBtn
				});
			}
			
		}
		
	})(jQuery);
	
//		加载过程的提示标签
//		example:
// 		var loader = new loadDialog(); 实例化对象
// 		loader.show(); 显示
// 		loader.close(); 关闭
	function loadDialog() {
		var topLayer = top != null? top.layer : layer;
		var loadi;
		this.show = function() {
// 			loadi = topLayer.load("加载中...", 120);
			loadi = topLayer.load(2,{time: 120*1000});
			//loadi = topLayer.msg('加载中...', {icon: 16,shade: [0.5, '#f5f5f5']});
		};
		this.close = function() {
			topLayer.close(loadi);
		};
	}
	/* 微课星加载要控制高度 */
	function loadwkx() {
		var topLayer = top != null? top.layer : layer;
		var loadi;
		this.show = function() {
// 			loadi = topLayer.load("加载中...", 120);
			loadi = topLayer.load(2,{time: 120*1000,offset: '300px'},false);
			//loadi = topLayer.msg('加载中...', {icon: 16,shade: [0.5, '#f5f5f5']});
		};
		this.close = function() {
			topLayer.close(loadi);
		};
	}
	function loadLayer(msg,time) {
		msg = msg != "" && msg != "undefined" && msg != null ? msg : "请稍等..."
			time = time != "" && time != "undefined" && time != null ? time : "10000"		
		var topLayer = top != null? top.layer : layer;
		var loadi;
		this.show = function() {
// 			loadi = topLayer.load(msg, 10);
			loadi = topLayer.msg(msg, {icon: 16,time: time,shade: 0.01});
		};
		this.close = function() {
			topLayer.close(loadi);
		};
	}
	
</script>