<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/avatar/avatar.js"></script>
<script type="text/javascript">
	(function($){
		$.createAvartarEditor = function (options) {
			var defaults = {
				"btn"            :     "#zp_modify_btn",
				"title"          :     "头像编辑",
				"width"          :     "750",
				"height"         :     "510",
				"top"            :     "20",
				"on"			 :     "top",
				"maxmin"         :     false,
				"shade"          :     [ 0.1, '#000' ],
				"shadeClose"     :     true,
				"shift"          :     "top",
				"border"         :     [5, 0.3, '#000'],
				"avatarSize"     :     "180,180|90,90|35,35",
				"avatarSizeLabel":     "180x180|90x90|35x35"
				
			};
			var settings = $.extend({}, defaults, options || {});
			var execute = function() {
				var btn = $(settings.btn);
				if(btn.length <= 0) {
					return;
				}
				$.each(btn, function(index, value) {
					$(value).bind("click", function() {
						$.initWindow({
							"on" : settings.on,
							"title" : settings.title, 
							"maxmin" : settings.maxmin,
							"shadeClose" : settings.shadeClose,
							"border" : settings.border,
							"top" : settings.top,
							"shade" : settings.shade,
							"width" : settings.width,
							"height" :settings.height,
							"shift" : settings.shift,
							"url" : "${pageContext.request.contextPath}/common/avartar/editor?avatarSize=" + settings.avatarSize + "&avatarSizeLabel=" + settings.avatarSizeLabel 	
						});
						event.stopPropagation();
					});
				});
			}
			execute();
		}
		
	})(jQuery); 
</script>