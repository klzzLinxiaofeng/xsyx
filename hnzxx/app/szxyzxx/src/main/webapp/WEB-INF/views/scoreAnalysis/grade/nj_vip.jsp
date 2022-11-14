<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>年级vip成绩查看</title>
		<meta name="apple-touch-fullscreen" content="YES">
	    <meta name="format-detection" content="telephone=no">
	    <meta name="apple-mobile-web-app-capable" content="yes">
	    <meta name="apple-mobile-web-app-status-bar-style" content="black">
	    <meta http-equiv="Expires" content="-1">
	    <meta http-equiv="pragram" content="no-cache">
	    <meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi"> 
	    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/scoreAnalysis/css/all.css "/>
		<!-- <link rel="stylesheet" type="text/css" href="css/all.css"/>
		<link rel="stylesheet" href="css/highcharts.css" /> -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/css/scoreAnalysis/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/css/scoreAnalysis/js/highcharts.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/css/scoreAnalysis/js/highcharts-more.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/layer/layer.js"></script>
		<style>
			.layui-layer-loading{margin-top:25px;}
		</style>
	</head>
	<body>
		<div>
			<p class="top_info">“注：有<b class="b1">${gradeExamStudentCount }</b>人参与本次统计，还有<b class="b1">${noImportStudentCount }</b>人数据未导入。”</p>
			<div class="sjxx mgb12">
				<p>
					<span class="type bgcolor_ffcd40">年级统考</span>
					<span class="title">${userExamWork.name }</span>
				</p>
				<p class="time">发布时间：<fmt:formatDate value="${userExamWork.publish_time}" pattern="yyyy年MM月dd日"/></p>
			</div>
			<div class="xsxx ">
				 <p>
				 	<span>姓名：<b>${userExamWork.studentName}</b></span><span>学号：<b>
				 	<c:choose>
				 		<c:when test="${empty userExamWork.number}">
				 			无
				 		</c:when>
				 		<c:otherwise>
				 			${userExamWork.number}
				 		</c:otherwise>
				 	</c:choose>
				 	</b></span>
				 </p>
				 <p class="ks_time">考试时间：<fmt:formatDate value="${userExamWork.exam_date}" pattern="yyyy年MM月dd日 HH:mm"/></p>
			</div>
				 <div class="xzfw" >
					<span class="on">综合分析</span>
					<span>单科分析</span>
					<span>趋势分析</span>
				</div>
			<div class="big_detail">
				
				
			</div>
		</div>
		<script>
		
			$(function(){
				var index = $('.xzfw span.on').index();
				var loader=layer.load();
				if(index == 0){
					$(".big_detail").load("/scoreAnalysis/WeChat/loadCompositeData/${examWorksId}/${studentId}/${teamId}",function(){
						layer.close(loader);
					});
				}
				$('.xzfw span').click(function(){
					$(this).addClass('on');
					$(this).siblings().removeClass('on');
					var i = $(this).index();
					$('.big_detail').find('.detail').hide();
					$('.big_detail').find('.detail').eq(i).show();
					//清掉之前的
					$(".big_detail").empty();
					if(i == 0){
					var loader=layer.load();
						//加载数据
						$(".big_detail").load("/scoreAnalysis/WeChat/loadCompositeData/${examWorksId}/${studentId}/${teamId}",function(){
							layer.close(loader);
						});
					}else if(i == 1){
						$(".big_detail").load("/scoreAnalysis/WeChat/loadSingleData/${examWorksId}/${schoolId}/${userId}/"+i,function(){
// 							layer.close(loader);
						});
					}else{
						$(".big_detail").load("/scoreAnalysis/WeChat/loadSingleData/${examWorksId}/${schoolId}/${userId}/"+i,function(){
// 							layer.close(loader);
});
						//$(".big_detail").load("/scoreAnalysis/WeChat/loadAnalysisData/${examWorksId}/${studentId}");
					}
				});
				$('.all_km ul li').click(function(){
					$(this).addClass('on');
					$(this).siblings().removeClass('on');
				});
			})
		</script>
	</body>
</html>
