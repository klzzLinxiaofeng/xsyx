/**
 * author clouds 2014/02/28
 * 自定义分页组件样式
 * 依赖jquery.pagination.js
 */

function initPagination(container, totalPage, currentPage, pageChangeMethod) {
	if(totalPage != "0") {
	    $("#" + container).paginate({
			count 		: totalPage,
			start 		: currentPage,
			display     : 5,
			border					: true,
			border_color			: '#BEF8B8',
			text_color  			: '#ffffff',
			background_color    	: '#01B5FE',	
			border_hover_color		: '#68BA64',
			text_hover_color  		: '#ffffff',
			background_hover_color	: '#18CEFO', 
			images					: false,
			mouse					: 'press',
			firstBtnText            : '首页',
			lastBtnText             : '末页',
			onChange     			: pageChangeMethod
		});
	}
}

function initPaginationByDisplay(container, totalPage, currentPage, pageChangeMethod, displayCount) {
	if(totalPage != "0") {
	    $("#" + container).paginate({
			count 		: totalPage,
			start 		: currentPage,
			display     : displayCount,
			border					: true,
			border_color			: '#BEF8B8',
			text_color  			: '#ffffff',
			background_color    	: '#01B5FE',	
			border_hover_color		: '#68BA64',
			text_hover_color  		: '#ffffff',
			background_hover_color	: '#18CEFO', 
			images					: false,
			mouse					: 'press',
			firstBtnText            : '首页',
			lastBtnText             : '末页',
			onChange     			: pageChangeMethod
		});
	}
}