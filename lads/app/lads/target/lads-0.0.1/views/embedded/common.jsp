<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
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

<!-- 获取代码表中数据生成下拉列表函数 -->
<%--<%@include file="/views/embedded/plugin/cache_js.jsp" %>--%>
<!-- 获取代码表中数据生成下拉列表函数 -->
<%--<%@include file="/views/embedded/plugin/cache_jcgc_js.jsp" %>--%>
<!-- 获取当前平台的 上下文路径 函数 -->
<%-- <script type="text/javascript" src="${ctp}/res/js/common/contextPath.js"></script> --%>
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
			if($('.pagination').jqPagination('option','current_page')!=1){
				$('.pagination').jqPagination('option',{'current_page':1});
			}
	});
}

</script>

