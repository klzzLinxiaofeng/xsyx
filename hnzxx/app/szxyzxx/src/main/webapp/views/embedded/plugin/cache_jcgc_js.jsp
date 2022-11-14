<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysCacheHttpUrlAccessor" %>
<script type="text/javascript">

//@author 潘维良
//@date 2015-03-25
(function($){
	//selector jquery选择器
	//$requestData 请求参数 
	//tc, 基础代码表唯一标识 即 tableCode 为必填参数 无默认值 	
	//level, 获取某个级别的 基础代码  不为必填参数，当不填写时后台默认获取第一级基础代码
	//usePage, 是否使用分页 值为1/0 1:代表使用分页 如值为1时 可以传pageSize或currentPage来控制分页行为;如果为0即不使用分页
	//asc, 是否升序 值为true/false
	//selectedVal 要选中的选项 做下拉列表回显时使用
	//handler 回调函数 用于指定下拉列表选项的value以及title
	//afterHandler 下拉列表生成后调用的回调函数
	$.baseJcGcSelector = function (selector, $requestData, selectedVal, handler, afterHandler) {
		$.get("${ctp}<%= SysCacheHttpUrlAccessor.CACHE_JCGC_ITEMS_URL%>", $requestData, function(data, status) {
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
				//afterHandler(currentSelect);
			}
		});
	};
	
	//selector jquery选择器
	//$requestData 请求参数 
	//tc, 基础代码表唯一标识 即 tableCode 为必填参数 无默认值 	
	//level, 获取某个级别的 基础代码  不为必填参数，当不填写时后台默认获取第一级基础代码
	//usePage, 是否使用分页 值为1/0 1:代表使用分页 如值为1时 可以传pageSize或currentPage来控制分页行为;如果为0即不使用分页
	//asc, 是否升序 值为true/false
	//selectedVal 要选中的选项 做下拉列表回显时使用，通常在编辑页面中需要用到，只需传要回显示的基础代码的code
	//afterHandler 下拉列表生成后调用的回调函数
	$.jcGcSelector = function (selector, $requestData, selectedVal, afterHandler) {
		$.baseJcGcSelector(selector, $requestData, selectedVal, function(data) {
			return {"val" : data.value, "title" : data.name};
		}, afterHandler);
	};
	
	
	$.getDataFromJcGcCache = function ($requestData, handller) {
		$.get("${ctp}<%= SysCacheHttpUrlAccessor.CACHE_JCGC_CONDITION_URL%>", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				handller(data);
			}
		});
	}
	
	//刷新缓存
	//tn 要刷新的数据库名称
	//id 要刷新的对象唯一标识主键ID
	//callback 回调函数
	$.refreshJcGcCache = function (tc, val, callback) {
		var $requestData = {};
		$requestData.tc = tc;
		$requestData.value = val;
		$.get("${ctp}<%= SysCacheHttpUrlAccessor.CACHE_JC_REFRESH_URL%>", $requestData, function(data, status) {
			if("success" === status) {
				
			}
			if(callback != null) {
				callback();
			}
		});
	};
})(jQuery);
</script>