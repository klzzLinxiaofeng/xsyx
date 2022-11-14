<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@include file="/views/embedded/plugin/jquery.jsp" %>
<!-- 系统样式框架 相关样式以及js -->
<%@include file="/views/embedded/plugin/falgun.jsp" %>
<!-- 窗体控件 -->
<%@include file="/views/embedded/plugin/layer.jsp" %>
<!-- 打印组件 -->
<%@include file="/views/embedded/plugin/lodop.jsp" %>
<!-- 页面验证功能 -->
<%@include file="/views/embedded/plugin/validation.jsp" %>
<!-- 分页组件 -->
<%@include file="/views/embedded/plugin/pagination.jsp" %>

<%@include file="/views/embedded/plugin/szxy_window_js.jsp" %>
<!-- 视频和文档转换 -->
<%@include file="/views/embedded/plugin/file_conversion_js.jsp" %>
<!-- 获取代码表中数据生成下拉列表函数 -->
<%@include file="/views/embedded/plugin/cache_js.jsp" %>
<!-- 获取代码表中数据生成下拉列表函数 -->
<%@include file="/views/embedded/plugin/cache_jcgc_js.jsp" %>
<!-- 获取当前平台的 上下文路径 函数 -->
<script type="text/javascript" src="${ctp}/res/js/common/contextPath.js"></script> 
<!-- 时间控件 -->
<%--<script type="text/javascript" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>--%>
<!-- 将form表单的表单数据转换成json数据函数 -->
<script type="text/javascript" src="${ctp}/res/js/common/jsonTools.js"></script>
<script type="text/javascript" src="${ctp}/res/js/common/checkall.js"></script>
<script type="text/javascript" src="${ctp}/res/js/common/disabled.js"></script>

<!-- 对javascript API的拓展文件 -->
<script type="text/javascript" src="${ctp}/res/js/num0/javascriptAPIext.js"></script>

<script type="text/javascript" src="${ctp}/res/js/common/plugin/swfobject/swfobject.js"></script>
<script type="text/javascript">

function ctp() {
    return "${pageContext.request.contextPath}";
}


//ajax 不使用缓存
$(function() {
	$.ajaxSetup({
		cache : false
	});
	//鼠标移动，点击显示操作按钮
	$('body').delegate(".table tbody tr", "mouseover mouseout", function(e) {
	      if (e.type == 'mouseover'){
	      	 $(this).addClass("blue_1"); 
	  		 $(this).children(".caozuo").children("button").css("z-index","1");
	      } 
	      else if (e.type == 'mouseout'){
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
function myPagination(id,val,url,callback,isStayCurrentPage){
	var paginationLoader = new loadDialog();
	url='${ctp}'+url;
	paginationLoader.show();
	$("#"+id).load(url, $.extend({}, {'currentPage' : '1','dm':$("#current_opera_zyDm", window.top.document).val()},val),function(){
		//首页加载完成，需要重新设置分页参数
		 	var totalPages= $("#totalPages").val();
		 	var currentPage= $("#currentPage").val();
		 	paginationLoader.close();
			//解决现实问题
			//$('.pagination input[type=text]').unbind();
			//$('.pagination input[type=text]').val();
			//$('.pagination').jqPagination('option',{'current_page':1});
			$('.pagination').jqPagination('destroy');
			$('.pagination').jqPagination({
				current_page:1, //设置当前页 默认为1'
				max_page :totalPages, //一共有多少页
			    page_string : '当前第{current_page}页,共{max_page}页',
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
			
			if("undefined"==typeof(isStayCurrentPage)){
				if($('.pagination').jqPagination('option','current_page')!=1){
					$('.pagination').jqPagination('option',{'current_page':1});
				}
			}
	});
}

</script>

