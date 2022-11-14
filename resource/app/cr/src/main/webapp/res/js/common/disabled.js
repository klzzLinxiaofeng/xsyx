(function($) {
	$.fn.disable = function() {
		//  屏蔽所有元素
		return $(this).find("*").each(function() {
			$(this).attr("disabled", "disabled");
		});
	}
	$.fn.enable = function() {
		// 使得所有元素都有效
		return $(this).find("*").each(function() {
			$(this).removeAttr("disabled");
		});
	}
})(jQuery);