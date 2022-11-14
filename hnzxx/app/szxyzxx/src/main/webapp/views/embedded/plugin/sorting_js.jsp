<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
//@author 陈业强
//@date 2015-09-16
(function($){
	$.initSortingProperty = function(options){
		//tableSelector 选择器  / sortIndex 要自定义排序的th的下标  / sortColumn 要自定义排序的th的data-tag,即列名
		//defaultIndex 默认显示的排序字段  / ascending 默认排序字段是否升序，true为升序 / tagWidth 升降序图标距离文字的距离
		//afterHandler 回调函数，无入参
		var defOption = { 
			"tableSelector" : "table",
			"sortIndex" : [],
			"sortColumn":[],
			"defaultIndex" : "",
			"ascending" : true,
			"tagWidth" : 10,
			"afterHandler" : function() {},
		};
		if(options.sortIndex.length != options.sortColumn.length){
			$.alert("下标的数量与列名的数量不符合，请重新设置！");
			return;
		}
		options = $.extend({}, defOption, options || {});
		var tableSelector = $(options.tableSelector + " th");
		if(options.defaultIndex === ""){
			for(var i =0 ; i < options.sortIndex.length; i++){
				var curIndex = options.sortIndex[i];
				var selector = tableSelector.eq(curIndex);
				var colName = selector.html();
				var curHtml = "<span class='sjx'>" + colName + "</span>";
				selector.addClass("sorting");
				selector.attr("data-tag",options.sortColumn[i]);
				selector.html(curHtml);
			}
		}else{
			for(var i =0 ; i < options.sortIndex.length; i++){
				var curIndex = options.sortIndex[i];
				var selector = tableSelector.eq(curIndex);
				var colName = selector.html();
				var curHtml = "<span class='sjx'>" + colName + "</span>";
				if(options.defaultIndex == options.sortIndex[i]){
					if(options.ascending){
						selector.addClass("sorting_asc").addClass("on");
					}else{
						selector.addClass("sorting_desc").addClass("on");
					}
				}else{
					selector.addClass("sorting");
				}
				selector.attr("data-tag",options.sortColumn[i]);
				selector.html(curHtml);
			}
		}
		var th_width=$("th.on .sjx").width() + options.tagWidth;
	 	$(".sorting_asc").css("background-position-x",th_width);
	 	$(".sorting_desc").css("background-position-x",th_width);
	 	var sorter = $(".sorting"); 
	 	for(var i = 0; i < sorter.length ; i++){
	 		var span_width = sorter.eq(i).find(".sjx").width() + options.tagWidth;
	 		sorter.eq(i).css("background-position-x",span_width);
	 	}
	 	$(options.tableSelector).on('click'," .sorting_asc",function(){
	 		$(this).removeClass("sorting_asc").addClass("sorting_desc");
	 		options.afterHandler();
	 	})
	 	$(options.tableSelector).on('click','.sorting_desc',function(){
	 		$(this).removeClass("sorting_desc").addClass("sorting_asc");
	 		options.afterHandler();
	 	});
	 	$(options.tableSelector).on('click',".sorting",function(){
	 		$(".sorting_asc").removeClass("sorting_asc").addClass("sorting");
	 		$(".sorting_desc").removeClass("sorting_desc").addClass("sorting");
	 		$(".on").removeClass("on");
	 		$(this).removeClass("sorting").addClass("sorting_asc").addClass("on");
	 		options.afterHandler();
	 	});
		options.afterHandler();
	}
	$.setProperty = function(val){   //tag 排序字段，ascending 是否升序  true升序，false降序
		var tag = $("th.on").attr("data-tag") + "";
		var ascending = $("th.on").attr("class") + "";
		if(tag != null && tag != "" && tag !== "undefined"){
			val.property = tag;
		}
		if(ascending.indexOf("sorting_desc") != -1){
			val.ascending = false;
		}else if(ascending.indexOf("sorting_asc") != -1){
			val.ascending = true;
		}
		return val;
	}
})(jQuery);
</script>