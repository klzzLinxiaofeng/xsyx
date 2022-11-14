<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.education.commonResource.web.common.contants.SysCacheHttpUrlAccessor" %>
<script type="text/javascript">

//@author 潘维良
//@date 2014-08-11
(function($){
	//selector jquery选择器
	//$requestData 请求参数 tn pn value
	//要选中的选项
	//回调函数 用于指定下拉列表选项的value以及title
	$.jcSelector = function (selector, $requestData, selectedVal, handler) {
		alert("sdsds");
		$.get("${ctp}<%= SysCacheHttpUrlAccessor.CACHE_JC_FINDBYPARAM_URL%>", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				var currentSelect = $(selector);
				currentSelect.html("<option value=''>请选择</option>");
				$.each(data, function(index, value) {
					var optionData = handler(value);
					currentSelect.append("<option value='" + optionData.val + "'>" + optionData.title + "</option>");
				});
				currentSelect.val(selectedVal);
				currentSelect.trigger("change");
			}
		});
	};
	
	//selector jquery选择器
	//$requestData 请求参数 tn pn value
	//selectedVal 要选中的选项
	//handler 回调函数 用于指定下拉列表选项的value以及title
	//afterHandler 下拉列表生成后调用的回调函数
	$.jcSelector = function (selector, $requestData, selectedVal, handler, afterHandler) {
		$.get("${ctp}<%= SysCacheHttpUrlAccessor.CACHE_JC_FINDBYPARAM_URL%>", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				var currentSelect = $(selector);
				currentSelect.html("<option value=''>请选择</option>");
				$.each(data, function(index, value) {
					var optionData = handler(value);
					currentSelect.append("<option value='" + optionData.val + "'>" + optionData.title + "</option>");
				});
				currentSelect.val(selectedVal);
				currentSelect.trigger("change");
				afterHandler();
			}
		});
	};
	
	$.getDataFromJcCache = function ($requestData, handller) {
		$.get("${ctp}<%= SysCacheHttpUrlAccessor.CACHE_JC_FINDBYPARAM_URL%>", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				handller(data);
			}
		});
	}
	
	// 多条件筛选缓存数据生成下拉列表
	// 	selector  jquery选择器
	// 	$requestData 筛选条件 
	// 	tn:表名；
	// 	expr筛选表达式param=value;param2=value2;
	// 	usePage:1表示使用，非1表示不使用
	// 	page.pageSzie : 每页要显示的条数:
	// 	page.currentPage ： 当前为第几页;
	// 	useOrder : 1表示使用，非1表示不使用
	// 	order : "[{"param1", true}, {"param2", true}]"
	$.jcSelectorByCondition = function (selector, $requestData, selectedVal, handler, afterHandler) {
		$.get("${ctp}<%= SysCacheHttpUrlAccessor.CACHE_JC_FINDBYCONDITION_URL%>", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				var currentSelect = $(selector);
				currentSelect.html("<option value=''>请选择</option>");
				$.each(data, function(index, value) {
					var optionData = handler(value);
					currentSelect.append("<option value='" + optionData.val + "'>" + optionData.title + "</option>");
				});
				currentSelect.val(selectedVal);
				currentSelect.trigger("change");
				afterHandler();
			}
		});
	};
	
	$.createSelectByClass = function (classSelector, $requestData, handler, afterHandler) {
		$.get("${ctp}<%= SysCacheHttpUrlAccessor.CACHE_JC_FINDBYCONDITION_URL%>", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				var currentSelects = $(classSelector);
				currentSelects.html("<option value=''>请选择</option>");
				$.each(data, function(index, value) {
					var optionData = handler(value);
					currentSelects.append("<option value='" + optionData.val + "'>" + optionData.title + "</option>");
				});
				
				$.each(currentSelects, function(index, value) {
					var currentSelect = $(value);
					var dm = currentSelect.attr("data-dm");
					currentSelect.val(dm);
					currentSelect.trigger("change");
				});
				afterHandler();
			}
		});
	};
	
	//刷新缓存
	//tn 要刷新的数据库名称
	//id 要刷新的对象唯一标识主键ID
	//callback 回调函数
	$.refreshCache = function (tn, id, callback) {
		var $requestData = {};
		$requestData.tn = tn;
		$requestData.id = id;
		$.get("${ctp}<%= SysCacheHttpUrlAccessor.CACHE_JC_REFRESH_URL%>", $requestData, function(data, status) {
			if("success" === status) {
				
			}
			if(callback != null) {
				callback();
			}
		});
	};
})(jQuery);



/*$(function() {
var sjXzqhDmSelectId = '${param.sjSelectId != null && param.sjSelectId != ""? param.sjSelectId : "sjXzqhDm"}';
var shijXzqhDmSelectId = '${param.shijSelectId != null && param.shijSelectId != ""? param.shijSelectId : "shijXzqhDm"}';
var qxjXzqhDmSelectId = '${param.qxjSelectId != null && param.qxjSelectId != ""? param.qxjSelectId : "qxjXzqhDm"}';
initXzqhSelect(sjXzqhDmSelectId,shijXzqhDmSelectId,qxjXzqhDmSelectId);
});*/
/**
* @param sjXzqhDmID		省级行政区划对应的selectID
* @param shijXzqhDmID		市级行政区划对应的selectID
* @param qxjXzqhDmID		区县级行政区划对应的selectID
*/
function initXzqhSelect2(sjXzqhDmID,shijXzqhDmID,qxjXzqhDmID,isSelected,sjXzqhDm,shijXzqhDm,qxjXzqhDm) {
	$("#" + sjXzqhDmID).on("change", function(event) {
		var $this = $(this);
		var currentXzqhDm = $this.val();
		createXzqhSelect2("#" + shijXzqhDmID, currentXzqhDm, 2,isSelected,shijXzqhDm);
	});
	$("#" + shijXzqhDmID).on("change", function(event) {
		var $this = $(this);
		var currentXzqhDm = $this.val();
		createXzqhSelect2("#" + qxjXzqhDmID, currentXzqhDm, 3,isSelected,qxjXzqhDm);
	});
	createXzqhSelect2("#" + sjXzqhDmID, "", 1,isSelected,sjXzqhDm);
}
/**
* @param selector		下一级selectID选择器
* @param val			上级行政区划代码，通过上级行政区划代码查找下级行政区划代码
* @param lv			等级1,2,3
* @param isSelected 	是否选中true|false字符串
* @param xzqhDm	 	行政区划代码值
*/
function createXzqhSelect2(selector, val, lv,isSelected,xzqhDm) {
	var $requestData = {};
	$requestData.tn = "t_dm_gy_xzqh";
	$requestData.pn = "sjxzqhDm";
	$requestData.value = val;
	$.get("/ehcache/findByParam",$requestData, function(data, status) {
		if ("success" === status) {
			data = eval("(" + data + ")");
			var currentSelect = $(selector);
			currentSelect.html("");
			$.each(data, function(index, value) {
				currentSelect.append("<option value='"
						+ value.xzqhDm + "'>" + value.mc
						+ "</option>");
			});
			if ("true" === isSelected) {
				if (lv == 1) {
					currentSelect.val(xzqhDm);
				} else if (lv == 2) {
					currentSelect.val(xzqhDm);
				} else {
					currentSelect.val(xzqhDm);
				}
				currentSelect.trigger("change");
			}
		}
	});
}
/**
* @param selector		选择器
* @param requestData	请求json数据
* @param isSelected	true|flase 字符串
* @param selectDm		选中的代码值
*/
function createSelect(selector,requestData,isSelected,selectDm){
$.get("/ehcache/findByParam",requestData, function(data, status) {
	if ("success" === status) {
		data = eval("(" + data + ")");
		var currentSelect = $(selector);
		currentSelect.html("");
		$.each(data, function(index, value) {
			currentSelect.append("<option value='"
					+ value.xzqhDm + "'>" + value.mc
					+ "</option>");
		});
		if ("true" === isSelected) {
			if (lv == 1) {
				currentSelect.val(selectDm);
			} else if (lv == 2) {
				currentSelect.val(selectDm);
			} else {
				currentSelect.val(selectDm);
			}
			currentSelect.trigger("change");
		}
	}
});
}



</script>