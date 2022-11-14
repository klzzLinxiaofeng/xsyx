<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/kwgl.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts-more.js"></script>
<title>查看详情</title>
</head>
<body >
<div class="kwgl">
	<div class="return_kw">
		<p>查看详情[班级统考]高一期末考试</p>
		<div class="njtk">
			<a href="javascript:void(0)" class="on"  id="2033">班级成绩单</a>
			<a href="javascript:void(0)" id="220">班级综合分析</a>
			<a href="javascript:void(0)" id="221">班级单科分析</a>
			<a href="javascript:void(0)" id="222">班级趋势分析</a>
		</div>
		<a href="javascript:void(0)" class="btn btn-grey">返回</a>
		<a href="javascript:void(0)" class="btn btn-blue">导出分析报告</a>
		
	</div>
	<iframe src="2034.jsp" id="bjfx_iframe" width="100%" height="auto" frameborder="0"></iframe>
</div>
</body>
<script>
$(function(){
	$('.njtk a').click(function(){
		$(this).addClass('on');
		$(this).siblings().removeClass('on');
		var id = $(this).attr('id');
		$('#bjfx_iframe').attr('src',id+'.jsp');
	});
})
$('#bjfx_iframe').load(function() {  
    var iframeHeight=$(this).contents().height();  
    $(this).height(iframeHeight+'px');   
});  

</script>
</html>