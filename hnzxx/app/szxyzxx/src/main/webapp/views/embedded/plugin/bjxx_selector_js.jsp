<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
//@author 潘维良
//@date 2014-08-11
(function($){
	$.createBjxxSelector = function (options) {
		var initWindow = function(options) {
			$.layer(options);
		}
		var defaults = {
			"inputIdSelector" : "#bjxx"
		};
		
		var layerOpDef = {
			type : 2,
			maxmin : true,
			shadeClose : true,
			title : "班级选择",
			shade : [ 0.1, '#000' ],
			offset : [ '15px', '' ],
			area : [ "400px", "370px" ],
			shift : 'left'
		};
		
		var layerSettings = $.extend({}, layerOpDef, options.layerOp || {});
		var settings = $.extend({}, defaults, options || {});
		
		var execute = function() {
			var inputSelector = $(settings.inputIdSelector);
			if(inputSelector.length <= 0) {
				return;
			}
			var clone = inputSelector.clone(true);
			inputSelector.after(clone);
			var selectorId = inputSelector.attr("id") + "_select";
			inputSelector.attr("id", selectorId).attr("name", "");
			clone.attr("class", "").val(clone.attr("data-id"));
			clone.css("display", "none");
			layerSettings['iframe'] = {src : "${pageContext.request.contextPath}/common/bjxx/selector?titleTo=" + selectorId + "&idTo=" + inputSelector.attr("data-id-container")};
			inputSelector.bind("click", function(event) {
				initWindow(layerSettings);
				event.stopPropagation();
			});
			
			var closeBtn = $('<abbr class="search-choice-close"></abbr>');
			var divParent = $("<span style='position:relative;display:inline-block;' title='清除' class='ryxx_selector'></span>");	
			clone.after(divParent);
			inputSelector.appendTo(divParent);
			clone.appendTo(divParent);
			closeBtn.appendTo(divParent);
			divParent.bind("click", function(event) {
				inputSelector.val("");
				clone.val("");
				event.stopPropagation();
			});
			
			inputSelector.bind("keydown", function(event) {
				$(this).val("");
				event.stopPropagation();
			});
			
			inputSelector.bind("keyup", function(event) {
				$(this).val("");
				event.stopPropagation();
			});
		}
		execute();
	};
	
	$.createBjxxSelectorV2 = function(options) {
		var initWindow = function(options) {
			$.layer(options);
		}
		var defaults = {
			"inputClassSelector" : ".bjxx"
		};
		var layerOpDef = {
			type : 2,
			maxmin : true,
			shadeClose : true,
			title : "班级选择",
			shade : [ 0.1, '#000' ],
			offset : [ '15px', '' ],
			area : [ "500px", "365px" ],
			shift : 'left'
		};
		var layerSettings = $.extend({}, layerOpDef, options.layerOp || {});
		var settings = $.extend({}, defaults, options || {});
		var execute = function() {
			var inputSelectors = $(settings.inputClassSelector);
			if(inputSelectors.length <= 0) {
				return;
			}
			
			$.each(inputSelectors, function(index, value) {
				var inputSelector = $(value);
				var clone = inputSelector.clone(true);
				inputSelector.after(clone);
				var selectorId = inputSelector.attr("id") + "_select";
				inputSelector.attr("id", selectorId).attr("name", "");
				clone.attr("class", "").val(clone.attr("data-id"));
				clone.css("display", "none");
				inputSelector.bind("click", function(event) {
					layerSettings['iframe'] = {src : "${pageContext.request.contextPath}/common/bjxx/selector?titleTo=" + $(this).attr("id") + "&idTo=" + $(this).attr("data-id-container")};
					initWindow(layerSettings);
					event.stopPropagation();
				});
				var closeBtn = $('<abbr class="search-choice-close"></abbr>');
				var divParent = $("<span style='position:relative;display:inline-block;' title='清除' class='ryxx_selector'></span>");	
				clone.after(divParent);
				inputSelector.appendTo(divParent);
				clone.appendTo(divParent);
				closeBtn.appendTo(divParent);
				divParent.bind("click", function(event) {
					inputSelector.val("");
					clone.val("");
					event.stopPropagation();
				});
				
				inputSelector.bind("keydown", function(event) {
					$(this).val("");
					event.stopPropagation();
				});
				
				inputSelector.bind("keyup", function(event) {
					$(this).val("");
					event.stopPropagation();
				});
			});
		}
		execute();
	}
})(jQuery);
</script>