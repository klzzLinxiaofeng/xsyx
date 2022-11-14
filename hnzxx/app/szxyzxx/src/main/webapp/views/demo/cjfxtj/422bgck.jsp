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
<title>查看报告</title>
</head>
<body >
<div class="kwgl">
	<div class="return_kw">
		<p>一年级3班 语文随堂测 报告查看</p>
		<div class="njtk">
			<a href="javascript:void(0)" class="on">班级单科分析</a>
			<a href="javascript:void(0)">班级趋势分析</a>
		</div>
		<a href="javascript:void(0)" class="btn btn-green">返回</a>
		<a href="javascript:void(0)" class="btn btn-blue">到处统计报告</a>
	</div>
	<div class="pd20">
		<div class="kw_select">
			<p class="xnxq fl" style="margin:0">学年：2017-2018学年 学期：春季学期（下学期）试类型：班级测试</p>
			<p class="fr" style="color:#86939d">考试时间：2018/07/18  9:00</p>
		</div>
		<div class="kwgl_main">
			<div id="container" style="min-width: 310px; height: 500px; max-width: 600px; margin: 0 auto"></div>
		</div>
	</div>
</div>
</body>
<script>
$(document).ready(function() {  
	   var chart = {
	       plotBackgroundColor: null,
	       plotBorderWidth: null,
	       plotShadow: false
	   };
	   var title = {
	      text: ''   
	   };     
	   var tooltip = {
	      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	   };
	   var credits={
	            enabled:false // 禁用版权信息
	       };
	   var plotOptions = {
	      pie: {
	         allowPointSelect: true,
	         connectorColor:'red',
	         cursor: 'pointer',
	         dataLabels: {
	            enabled: true,
	            format: '<b>{point.name}<span>{point.y}</span></b>: {point.percentage:.1f} %',
	            style: {
	               color: '#2a9ff7',
	               fontWeight: 'bold',
	               fontSize:'18px',
	               fontFamily:"微软雅黑",
	               textShadow: '0px 0px 0px #fff'
	            }
	         }
	      }
	   };
	   var series= [{
	      type: 'pie',
	      name: '作答',
	       innerSize: '75%',
	      data: [
	         ['优秀率 ',   25],
	         ['良好率',   330],
	         ['及格率',    220],
	         ['不及格率',    15]
	      ]
	   }];    
	    var color1= ['#03a9f5', '#ff6a88', '#16b2eb', '#6e7cff', '#999999'] ;
	    var color2= ['#90caf8', '#fca599', '#2af598', '#fd83ec', '#999999'] ;
	   // Radialize the colors
	   Highcharts.getOptions().colors = Highcharts.map(color1, function (color) {
	        var v=color;
	        var j=0;
	        var color_end='#fff';
	        for(var i=0;i<color1.length;i++){
	        if(color1[i]==v){
	           j=i;
	            color_end=color2[j]
	        }
	        }
	      return {
	         linearGradient: { x1: 0, y1: 1, y2: 0, x2: 0 }, //横向渐变效果 如果将x2和y2值交换将会变成纵向渐变效果  
	         stops: [
	            [0, color],
	            [1, color_end] // darken
	         ]
	      };
	   });
	      
	   var json = {};   
	   json.chart = chart; 
	   json.title = title;   
	   json.credits=credits;  
	   json.tooltip = tooltip;  
	   json.series = series;
	   json.plotOptions = plotOptions;
	   $('#container').highcharts(json);  
	});
</script>
</html>