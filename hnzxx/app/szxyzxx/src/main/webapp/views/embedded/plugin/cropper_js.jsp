<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
//@author 陈业强
//@date 2015-12-3
(function($){
		$.createCropperAvatar = function(options){
			var defOption = {
					selector : ".avatar-view",
					isOnTopWindow : false
				};
			var initWindow = function(options) {
				$.layer(options);
			}
			
			var initWindowOnTop = function(options) {
				top.$.layer(options);
			}
			var layerOpDef = {
				type : 2,
				maxmin : true,
				shadeClose : true,
				title : "图片编辑",
				shade : [ 0.1, '#fff' ],
				offset : [ '15px', '' ],
				area : [ "1000px", "650px" ],
				shift : 'left'
			};
			var layerSettings = $.extend({}, layerOpDef, options.layerOp || {});
			var settings = $.extend({}, defOption, options || {});
			var $cropSelector = $(settings.selector);
			var url = "${pageContext.request.contextPath}/common/cropperAvatar"
			layerSettings['iframe'] = {src : url};
			$cropSelector.bind("click",function(event){
// 					initWindow(layerSettings);
				if(settings.isOnTopWindow) {
					initWindowOnTop(layerSettings)
				} else {
					initWindow(layerSettings);
				}
				event.stopPropagation();
			});
		}		
	})(jQuery); 
</script>