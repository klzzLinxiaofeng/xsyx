(function($){
	/**
	 * @param selector		下一级selectID选择器
	 * @param val			上级行政区划代码，通过上级行政区划代码查找下级行政区划代码
	 * @param lv			等级1,2,3
	 * @param isSelected 	是否选中true|false字符串
	 * @param xzqhDm	 	行政区划代码值
	 */
	function createSelect(selector, val, lv,isSelected,id, callback) {
		if(val == null || "" === val) {
			selector.html("");
			selector.append("<option value=''>请选择</option>");
			selector.trigger("change");
			callback(selector);
		} else {
			var $requestData = {};
			$requestData.tn = "jc_region";
			$requestData.pn = "parent";
			$requestData.value = val;
			$.get(ctp()+"/cache/findByParam",$requestData, function(data, status) {
				if ("success" === status) {
					data = eval("(" + data + ")");
					selector.html("");
					selector.append("<option value=''>请选择</option>");
					$.each(data, function(index, value) {
						selector.append("<option value='" + value.id + "'>" + value.name + "</option>");
					});
					if (isSelected) {
						if (lv == 1) {
							selector.val(id);
						} else if (lv == 2) {
							selector.val(id);
						} else if (lv == 3){
							selector.val(id);
						} else {
							selector.val(id);
						}
						
					}
					selector.trigger("change");
					callback(selector);
				}
			});
		}
	}
	
	$.initRegionSelector = function(options) {
		var defOption = {
			lv : "3",   //3 / 4
			isUseChosen : false,
			sjSelector : "sj",
			shijSelector : "shij",
			qxjSelector : "qx",
			jdSelector : "jd",
			
			isEchoSelected : false,
			sjSelectedVal : "",
			shijSelectedVal : "",
			qxjSelectedVal : "",
			jdSelectedVal : "",
			
			sjCallback : function($this) {
				
			},
			
			shijCallback : function($this) {
				
			},
			
			qxCallback : function($this) {
				
			},
			
			jdCallback : function($this) {
				
			}
		};
		
		options = $.extend({}, defOption, options || {});
		
		var sjSelector = $("#" + options.sjSelector);
		var shijSelector = $("#" + options.shijSelector);
		
		
		sjSelector.append("<option value=''>请选择</option>");
		shijSelector.append("<option value=''>请选择</option>");
		
		var qxjSelector;
		if("3" === options.lv || "4" === options.lv) {
			qxjSelector = $("#" + options.qxjSelector);
			qxjSelector.append("<option value=''>请选择</option>");
		}
		
		var jdSelector;
		if("4" === options.lv) {
			jdSelector = $("#" + options.jdSelector);
			jdSelector.append("<option value=''>请选择</option>");
		}
		
		if (!options.isUseChosen) {
			
			sjSelector.on("change", function(event) {
				var $this = $(this);
				var currentId = $this.val();
				createSelect(shijSelector, currentId, 2, options.isEchoSelected, options.shijSelectedVal, options.shijCallback);
			});
			
			if("3" === options.lv || "4" === options.lv) {
				shijSelector.on("change", function(event) {
					var $this = $(this);
					var currentId = $this.val();
					createSelect(qxjSelector, currentId, 3, options.isEchoSelected, options.qxjSelectedVal, options.qxCallback);
				});
			}
			
			if("4" === options.lv) {
				qxjSelector.on("change", function(event) {
					var $this = $(this);
					var currentId = $this.val();
					createSelect(jdSelector, currentId, 4, options.isEchoSelected, options.jdSelectedVal, options.jdCallback);
				});
			}
			
			createSelect(sjSelector, "0", 1, options.isEchoSelected, options.sjSelectedVal, options.sjCallback);
		} else {
			$("body").on("click", "#" + options.sjSelector + "_chzn .chzn-drop .chzn-results li", function(event) {
				var $this = $(this);
				var sjIndex = $this.attr("id").replace(options.sjSelector + "_chzn_o_", "");
				var $sjSelector = sjSelector;
				var sj = $sjSelector.find("option").eq(parseInt(sjIndex)).val();
				createSelect(shijSelector, sj, 2, options.isEchoSelected, options.shijSelectedVal, function(selector) {
					options.shijCallback(selector);
					$("#" + options.shijSelector + "_chzn").remove();
					shijSelector.show().removeClass("chzn-done").chosen();
					if(options.isEchoSelected || "" === sj || sj == null) {
						var index = shijSelector.get(0).selectedIndex;
						$("#" + options.shijSelector + "_chzn .chzn-drop .chzn-results li").eq(index).click();
					} 
				});
			});
			if("3" === options.lv || "4" === options.lv) {
				$("body").on("click", "#" + options.shijSelector + "_chzn .chzn-drop .chzn-results li", function(event) {
					var $this = $(this);
					var shijIndex = $this.attr("id").replace(options.shijSelector + "_chzn_o_", "");
					var $shijSelector = shijSelector;
					var shij = $shijSelector.find("option").eq(parseInt(shijIndex)).val();
					createSelect(qxjSelector, shij, 3, options.isEchoSelected, options.qxjSelectedVal, function(selector) {
						options.qxCallback(selector);
						$("#" + options.qxjSelector + "_chzn").remove();
						qxjSelector.show().removeClass("chzn-done").chosen();
						if(options.isEchoSelected || "" === shij || shij === null) {
							var index = qxjSelector.get(0).selectedIndex;
							$("#" + options.qxjSelector + "_chzn .chzn-drop .chzn-results li").eq(index).click();
						}
					});
				})
			}
			if("4" === options.lv) {
				$("body").on("click", "#" + options.qxjSelector + "_chzn .chzn-drop .chzn-results li", function(event) {
					var $this = $(this);
					var qxjIndex = $this.attr("id").replace(options.qxjSelector + "_chzn_o_", "");
					var $qxjSelector = qxjSelector;
					var qxj = $qxjSelector.find("option").eq(parseInt(qxjIndex)).val();
					createSelect(jdSelector, qxj, 4, options.isEchoSelected, options.jdSelectedVal, function(selector) {
						options.jdCallback(selector);
						$("#" + options.jdSelector + "_chzn").remove();
						jdSelector.show().removeClass("chzn-done").chosen();
						if(options.isEchoSelected || qxj == null || "" === qxj) {
							var index = jdSelector.get(0).selectedIndex;
							$("#" + options.jdSelector + "_chzn .chzn-drop .chzn-results li").eq(index).click();
						}
					});
				})
			}
			
			createSelect(sjSelector, "0", 1, options.isEchoSelected, options.sjSelectedVal, function(selector) {
				options.sjCallback(selector)
				sjSelector.chosen();
				shijSelector.chosen();
				qxjSelector.chosen();
				if("4" === options.lv) {
					jdSelector.chosen();
				}
			});
		}
	}
	
})(jQuery);

