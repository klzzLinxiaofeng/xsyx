<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${ctp}/res/js/common/plugin/lodop/LodopFuncs.js"></script>
<!-- Author:潘维良 -->
<!-- Date 2014-10-17 -->
<script type="text/javascript">
	(function($){
		//打印HTML的默认配置项
		var html_defaults = {
			"taskName" : "defalut_task",  //打印任务名称
			"fontSize" : 18, 
			"bold" : 1,
			"htmlBody" : "<div style='color : red'>This is printer demo</div>", //需要打印的整体内容，包括内容元素、内嵌css、外联css、cs访问地址
			"y" : 100, //顶部距离
			"x" : 0,   //左侧距离
			"width" : 760, //内容宽度
			"height" : 900 //内容高度
		};
		
		//打印图片的默认配置项
		var img_defaults = {
			"taskName" : "defalut_task", //打印任务名称
			"y" : 50,  //顶部距离
			"x" : 150, //左侧距离
			"width" : 400,  //内容宽度
			"height" : 400, //内容高度
			"imgUrls" : ['http://b.hiphotos.baidu.com/image/w%3D2048/sign=9fb9d071bb389b5038ffe752b10de4dd/9d82d158ccbf6c812962e687be3eb13533fa400c.jpg'], //图片url列表
			"deformable" : false //进行缩放时，是否允许变形 true可变形的， false等比例缩放
			
		}
		
		var obj = "<object id='LODOP_OB' classid='clsid:2105C259-1E0C-4534-8141-A753534CB4CA' width=0 height=0>" + 
			"<embed id='LODOP_EM' type='application/x-print-lodop' width=0 height=0></embed>" +
		"</object>";
		
		function baseInitHtml_LODOP(LODOP, ops) {
			LODOP.PRINT_INIT(ops.taskName);
 			LODOP.SET_PRINT_STYLE("FontSize", ops.fontSize);
 			LODOP.SET_PRINT_STYLE("Bold", ops.bold);
 			LODOP.ADD_PRINT_HTM(ops.y, ops.x, ops.width, ops.height, ops.htmlBody);
 			LODOP.SET_PRINT_STYLEA(0,"Horient",2);
 			LODOP.SET_PRINT_STYLEA(0,"Vorient",2);
		}
		
		function baseInitImg_LODOP(LODOP, ops) {
			LODOP.PRINT_INIT(ops.taskName);
			$.each(ops.imgUrls, function(index, value) {
				var imgTag = "<img border='0' src='" + value + "' />";
				LODOP.ADD_PRINT_IMAGE(ops.y + index * 10, ops.x + index * 10, ops.width, ops.height, imgTag);
				if(!ops.deformable) {
					LODOP.SET_PRINT_STYLEA(0, "Stretch", 2); //原比例，可缩放
				} else {
					LODOP.SET_PRINT_STYLEA(0, "Stretch", 1); //可变形，可缩放
				}
			});
		}
		
		//根据传入的html样式，进行打印 只提供预览打印功能
		$.fn.printHtml_Preview = function(options) {
			var $this = this;
			var LODOP = null;
			var ops = $.extend({}, html_defaults, options || {});
			var initLodop = function() {
				$("#LODOP_OB").remove();
				$this.after(obj);
			}
			var initClick = function() {
				$this.unbind("click");
				$this.on("click", function() {
					LODOP = getLodop(document.getElementById("LODOP_OB"), document.getElementById("LODOP_EM"));
					baseInitHtml_LODOP(LODOP, ops);
					LODOP.PREVIEW();
				});
			}
			initLodop();
			initClick();
		}
		
		//根据传入的html样式，进行打印，提供维护功能
		$.fn.printHtml_Setup = function(options) {
			var LODOP = null;
			var $this = this;
			var ops = $.extend({}, html_defaults, options || {});
			var initLodop = function() {
				$this.after(obj);
			}
			var initClick = function() {
				$this.on("click", function() {
					LODOP = getLodop(document.getElementById("LODOP_OB"), document.getElementById("LODOP_EM"));	
					baseInitHtml_LODOP(LODOP, ops);
					LODOP.PRINT_SETUP();
				});
			}
			initLodop();
			initClick();
		}
		
		//打印图片
		$.fn.printImg_Setup = function(options) {
			var LODOP = null;
			var $this = this;
			var ops = $.extend({}, img_defaults, options || {});
			var initLodop = function() {
				$this.after(obj);
			}
			var initClick = function() {
				$this.on("click", function() {
					LODOP = getLodop(document.getElementById("LODOP_OB"), document.getElementById("LODOP_EM"));	
					baseInitImg_LODOP(LODOP, ops);
					LODOP.PRINT_SETUP();
				});
			}
			initLodop();
			initClick();
		}
		
	})(jQuery);
		
</script>