/**
 * author clouds 2014/03/02
 * 通用js
 */
//确认对话框
function confirm(yesCallback) {
	$( "#dialog-confirm" ).html("确认要执行此操作?");
	$( "#dialog-confirm" ).dialog({
		resizable   :   true,
		height      :   154,
		modal       :   true,
		title       :   "提示",
		position    :   "top",
		buttons     : {"确定": function() {
					      yesCallback();
						  $( this ).dialog( "close" );
					  },
		               "取消": function() {
		     			  $( this ).dialog( "close" );
		    		  }
		}
	});
}

function confirmByMsg(msg, yesCallback) {
	$( "#dialog-confirm" ).html(msg);
	$( "#dialog-confirm" ).dialog({
		resizable   :   true,
		height      :   154,
		modal       :   true,
		title       :   "提示",
		position    :   "top",
		buttons     : {"确定": function() {
					      yesCallback();
						  $( this ).dialog( "close" );
					  },
		               "取消": function() {
		     			  $( this ).dialog( "close" );
		    		  }
		}
	});
}

function error(info) {
	$( "#dialog-confirm" ).html(info);
	$( "#dialog-confirm" ).dialog({
		resizable   :   true,
		height      :   154,
		modal       :   true,
		title       :   "提示",
		position    :   "top",
		buttons     : {"确定": function() {
						  $( this ).dialog( "close" );
					  }
		}
	});
}

(function($){  
    $.alert = function(msg, yesCallBack) {
    	$( "#dialog-confirm" ).html(msg);
    	$( "#dialog-confirm" ).dialog({
    		resizable   :   true,
    		height      :   154,
    		modal       :   true,
    		title       :   "提示",
    		position    :   "top",
    		buttons     : {"确定": function() {
    						  $( this ).dialog( "close" );
    						  yesCallBack();
    					  }
    		}
    	});
    };
    
    $.confirm = function(msg, yesCallBack) {
    	$( "#dialog-confirm" ).html(msg);
    	$( "#dialog-confirm" ).dialog({
    		resizable   :   true,
    		height      :   "auto",
    		modal       :   true,
    		title       :   "提示",
    		position    :   "top",
    		buttons     : {"确定": function() {
    						  $( this ).dialog( "close" );
    						  yesCallBack();
    					  },
    		               "取消": function() {
    		     			  $( this ).dialog( "close" );
    		    		  }
    		}
    	});
    };
})(jQuery); 

function initOrganizationNav(container,requestBasePath, handleFun, deptLv, specLv) {
	$(function() {

		$("#" + container).append('<li id="all_spec_li" data-specailtyId="" class=""><a href="javascript:void(0);" >全部</a></li>');
		
		//获取系别
		$.get(requestBasePath + "/ground/specialty", {
			usePage : false,
			level : deptLv
		}, function(data, status) {
			var $data = eval("(" + data + ")");
			$.each($data, function(index, value) {
				if(index == 0) {
					var $firstLi = $("<li data-isOpen='false' data-specailtyId='" + value.id + "'><a href='javascript:void(0);'>" + value.name + "</a><div style='display: none'></div></li>");
					$("#" + container).append($firstLi);
				} else {
					$("#" + container).append("<li data-isOpen='false' data-specailtyId='" + value.id + "'><a href='javascript:void(0);'>" + value.name + "</a><div style='display: none'></div></li>");
				}
			});
		});

		$("#" + container).on("click", "li", function(event) {
			var $this = $(this);
			var $isOpen = $this.attr("data-isOpen");
			var $currentSpecialtyId = $this.attr("data-specailtyId");
			var $currentSpecDiv = $this.find("div");
			if("false" === $isOpen) {
				var $allSpecLi = $("#" + container + " li");
				var $data = {};
				if ("" != $currentSpecialtyId) {
					$data.parentId = $currentSpecialtyId;
					$data.level = specLv;
				}
				$.get(requestBasePath + "/ground/specialty", $data, function(data, status) {
					var $data = eval("(" + data + ")");
					$currentSpecDiv.html("");
					$.each($data, function(index, value) {
						$currentSpecDiv.append("<p data-specailtyId='" + value.id + "' >+" + value.name + "</p>");
					});
					var $firstP = $currentSpecDiv.find("p:first").get(0);
					if($firstP != null) {
						$firstP.click();
					}
					$("#all_spec_li").removeClass("all");
					$("#" + container + " li div").animate({height : 'hide'});
					$currentSpecDiv.animate({height : 'show'});
					$allSpecLi.attr("data-isOpen", "false");
					$this.attr("data-isOpen", "true");
					$allSpecLi.removeClass("on");
					$this.addClass("on");
				});
			} else {
				$this.attr("data-isOpen", "false");
				$this.removeClass("on");
				$currentSpecDiv.animate({height : 'hide'});
			}
			event.stopPropagation();
		});
		
		$("#" + container).on("click", "li div p", function(event) {
			var $this = $(this);
			var $specialtyId = $this.attr("data-specailtyId");
			$("#" + container + " li div p").removeClass("current");
			$this.addClass("current");
			var $data = {specialtyId : $specialtyId};
			handleFun($data, function() {}, function() {});
			event.stopPropagation();
		});
		
		$("#all_spec_li").on("click", function() {
			var $allSpecLi = $("#" + container + " li");
			var $this = $(this);
			$("#" + container + " li").removeClass("on");
			$this.addClass("all");
			$allSpecLi.attr("data-isOpen", "false");
			$allSpecLi.removeClass("on");
			$("#" + container + " li div").animate({height : 'hide'});
			handleFun({}, function() {}, function() {});
		});
		
		$("#all_spec_li").click();
	});
	
}

function canInputChar(selector, canInputCount, showInfoSelector) {
	$(showInfoSelector).html(canInputCount - parseInt($(selector).val().length));
	$(selector).on("keyup", function() {
		var $currentChar = $(this).val();
		var $currentCharCount = $currentChar.length;
		var $canInputCharCount = canInputCount - parseInt($currentCharCount);
		if($canInputCharCount < 0) {
			$(this).val($currentChar.substring(0,canInputCount));
			return;
		} else {
			$(showInfoSelector).html($canInputCharCount);
		}
	});
}

function inputAutoClear(selector) {
	var input_value, input_old_value = "";
	$(selector).focus(function() {
		input_value = $(this).val();
		if (input_old_value === input_value) {
			$(this).val(input_value);
			return;
		}
		$(this).val("");
	});

	$(selector).change(function() {
		input_old_value = $(this).val();
	});
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
function ltrim(str){ //删除左边的空格
    return str.replace(/(^\s*)/g,"");
}
function rtrim(str){ //删除右边的空格
    return str.replace(/(\s*$)/g,"");
}
