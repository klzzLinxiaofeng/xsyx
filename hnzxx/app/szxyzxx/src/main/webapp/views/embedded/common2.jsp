<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<%@include file="/views/embedded/taglib.jsp" %>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/jquery.form.js"></script>


<!-- 窗体控件 -->
<%@include file="/views/embedded/plugin/layer.jsp" %>
<!-- 打印组件 -->
<%@include file="/views/embedded/plugin/lodop.jsp" %>


<!-- 分页组件 -->
<%@include file="/views/embedded/plugin/pagination.jsp" %>

<%@include file="/views/embedded/plugin/szxy_window_js.jsp" %>

<%@include file="/views/embedded/plugin/data_getter_js.jsp" %>

<%@include file="/views/embedded/plugin/file_conversion_js.jsp" %>
<!-- 按照指定字段排序 -->
<%@include file="/views/embedded/plugin/sorting_js.jsp" %>
<!-- 获取代码表中数据生成下拉列表函数 -->
<%@include file="/views/embedded/plugin/cache_js.jsp" %>
<!-- 获取代码表中数据生成下拉列表函数 -->
<%@include file="/views/embedded/plugin/cache_jcgc_js.jsp" %>
<!-- 获取人员信息的筛选控件 -->
<%@include file="/views/embedded/plugin/ryxx_selector_js.jsp" %>
<!-- 获取班级信息的筛选控件 -->
<%@include file="/views/embedded/plugin/bjxx_selector_js.jsp" %>
<!-- 获取当前平台的 上下文路径 函数 -->
<%-- <script type="text/javascript" src="${ctp}/res/js/common/contextPath.js"></script> --%>
<!-- 时间控件 -->
<%--<script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>--%>
<!-- 将form表单的表单数据转换成json数据函数 -->
<script type="text/javascript" src="${ctp}/res/js/common/jsonTools.js"></script>
<!-- 从缓存中获取行政区划代码 并实现 级联的函数 -->
<script type="text/javascript" src="${ctp}/res/js/common/regionSelect.js"></script>
<!-- input限制字数 -->
<script type="text/javascript" src="${ctp}/res/js/common/maxlength.js"></script>

<script type="text/javascript" src="${ctp}/res/js/common/checkall.js"></script>
<script type="text/javascript" src="${ctp}/res/js/common/disabled.js"></script>
<!-- 对javascript API的拓展文件 -->
<script type="text/javascript" src="${ctp}/res/js/num0/javascriptAPIext.js"></script>
<script type="text/javascript" src="${ctp}/res/js/common/plugin/swfobject/swfobject.js"></script>

<!-- 系统样式框架 相关样式以及js -->

<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/bootstrap-responsive.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/falgun/css/font-awesome.css">
<link rel="stylesheet" href="/res/plugin/falgun/css/font-awesome-ie7.min.css">
<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/chosen.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/styles.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/accordion.nav.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/ios-orientationchange-fix.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/ZeroClipboard.js"></script>
<%--<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/TableTools.js"></script>--%>




<script type="text/javascript">
function ctp() {
    return "${pageContext.request.contextPath}";
}


$(function() {
//ajax 不使用缓存
	$.ajaxSetup({
		cache : false
	});

// 鼠标移动，点击显示操作按钮
	  $('body').delegate(".table tbody tr", "mouseover mouseout", function(e) {
	        if (e.type == 'mouseover')
	        {
	        	 $(this).addClass("blue_1");
	    		$(this).children(".caozuo").children("button").css("z-index","1");
	        }
	        else if (e.type == 'mouseout')
	        {
	        	if(!$(this).hasClass("on")){
					$(this).removeClass("blue_1");
		    		$(this).children(".caozuo").children("button").css("z-index","-1");
	        	}
	        }
	    });
	 $('body').on("click",".table tbody tr", function() {
		 $('.table tbody tr').removeClass("blue_1 on");
		 $('.table tbody tr .caozuo button').css("z-index","-1");
       	$(this).addClass("blue_1 on");
   		$(this).children(".caozuo").children("button").css("z-index","1");
	    });
});
//搜索或查询分页处理
function myPagination(id,val,url,callback){
	var paginationLoader = new loadDialog();
	url='${ctp}'+url;
	paginationLoader.show();
	$("#"+id).load(url, $.extend({}, {'currentPage' : '1','dm':$("#current_opera_zyDm", window.top.document).val()},val),function(){
		//首页加载完成，需要重新设置分页参数
		 	var totalPages= $("#totalPages").val();
		 	var currentPage= $("#currentPage").val();
            var totalRows=$("#totalRows").val();
		 	paginationLoader.close();
			//解决现实问题
			//$('.pagination input[type=text]').unbind();
			//$('.pagination input[type=text]').val();
			//$('.pagination').jqPagination('option',{'current_page':1});
			$('.pagination').jqPagination('destroy');
			$('.pagination').jqPagination({
				current_page:1, //设置当前页 默认为1'
				max_page :totalPages, //一共有多少页
			    page_string : '当前第{current_page}页,共{max_page}页,共'+totalRows+'条',
				paged: function(page) {
						paginationLoader.show();
					 	$("#"+id).load(url, $.extend({}, {'currentPage' : page,'dm':$("#current_opera_zyDm", window.top.document).val()},val),function(){
					 		if(callback!=undefined&&callback!=''){
					 			callback();
					 		}
					 		paginationLoader.close();
					 	});
					}
				});
			if(callback!=undefined&&callback!=''){
	 			callback();
	 		}
			if($('.pagination').jqPagination('option','current_page')!=1){
				$('.pagination').jqPagination('option',{'current_page':1});
			}
	});
}

//用于弹出页面刷新父窗口时调用
function listRefresh(){
	if(parent.core_iframe != null) {
		parent.core_iframe.window.location.reload();
	 } else {
		parent.window.location.reload();
	}
}
//情况表单数据，参数可以ID
function resetForm(form){
	$(':input',form)
	 .not(':button, :submit, :reset, :hidden')
	 .val('')
	 .removeAttr('checked')
	 .removeAttr('selected');
	  $(".chzn-select").chosen();
      $(".chzn-select-deselect").chosen({
          allow_single_deselect: true
      });
}

function SYS_LOGOUT() {
	$.confirm("确定退出系统？", function() {
<%-- 		var ennable = "<%= SysContants.COMMON_RESOURCE_ENABLE%>"; --%>
// 		if("true" === ennable) {
// 			var $requestData = {};
<%-- 			var url = "<%= SysContants.COMMON_RESOURCE_BASE_PATH%>" + "<%= SysContants.COMMON_RESOURCE_LOGOUT_URL%>"; --%>
// 			$.ajax({
// 				async : false,
// 			    url: url,
// 			    type: "GET",
// 			    dataType: 'jsonp',
// 			    jsonp: 'callback',
// 			    jsonpCallback:"jsonpcallback",
// 			    data: $requestData,
// 			    timeout: 5000,
// 			    complete : function(json) {
// 			    	window.location = "${pageContext.request.contextPath}/logout";
// 			    }
// 			});
// 		} else {
// 			window.location = "${pageContext.request.contextPath}/logout";
// 		}
		if (top != null) {
			top.window.location.href = "${pageContext.request.contextPath}/logout";
		} else {
			window.location.href = "${pageContext.request.contextPath}/logout";
		}
	}, function() {});
}
function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++) {
		var c = ca[i].trim();
		if (c.indexOf(name)==0) return c.substring(name.length,c.length);
	}
	return "";
}
</script>

