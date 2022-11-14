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
		<p>查看详情[年级统考]高一期末考试</p>
		<div class="njtk">
			<a href="javascript:void(0)" class="on">班级单科分析</a>
			<a href="javascript:void(0)" >班级趋势分析</a>
		</div>
		<a href="javascript:void(0)" class="btn btn-grey">返回</a>
	</div>
	
	<div class="pd20">
		<div class="kw_select">
			<p class="xnxq fl" style="margin:0">学年：2017-2018学年 学期：春季学期（下学期）</p>
			<p class="fr" style="color:#86939d">考试时间段：2018/07/18  9:00</p>
		</div>
		<div class="nj_basis_info">
			<p class="title">班级单科基础信息</p>
			<div class="ks_bj">
				<div class="ks_bj_list">
					<p>应试班级:</p>
					<div class="list">
						<span class="dr_ok">高一（1）班</span>
					</div>
				</div>
			</div>
			<div class="color_kuai">
				<div class="ck1">
					<p class="ck_num"><span>500</span>/<span>500</span></p>
					<p class="ck_info">应试人数</p>
				</div>
				<div class="ck2">
					<p class="ck_num"><span>500</span>/<span>500</span></p>
					<p class="ck_info">班级单科平均分</p>
				</div>
				<div class="ck3">
					<p class="ck_num"><span>500</span>/<span>500</span></p>
					<p class="ck_info">班级单科最高分</p>
				</div>
				<div class="ck4">
					<p class="ck_num"><span>500</span>/<span>500</span></p>
					<p class="ck_info">班级单科最低分</p>
				</div>
			</div>
		</div>
		
		<div class="kwgl_main">
			<p class="title">班级单科平均分趋势</p>
			<div id="container10" style="min-width: 400px; height: 700px; max-width: 920px; margin: 0 auto"></div>
		</div>
		<div class="kwgl_main">
			<p class="title">班级单科平均分各班对比趋势</p>
			<div id="container11" style="min-width: 400px; height: 700px; max-width: 920px; margin: 0 auto"></div>
		</div>
	</div>
</div>
</body>
<script>
$(function(){
	$('.njtk a').click(function(){
		$(this).addClass('on');
		$(this).siblings().removeClass('on');
	});
	
	$('#container10').highcharts({
	    chart: {
	        type: 'line',
	    },
	    title: {
	        text: null
	    },
	    xAxis: {
	        gridLineColor: '#d6d6d6',
	        gridLineWidth: 1,
	        gridLineDashStyle: 'Dot',
	        categories: ['模拟考123456', '模拟考dddddddd', '模拟考模拟考模拟考数', '模拟考数学数学', '模拟考模拟考模拟考', '模拟考',
	                     '模拟考', '模拟考','期末考'],
	        labels: {
	            y:30,
	            align: 'left',
	            style:{
	                color:'#999999',
	                fontSize:'16',
	                writingMode : 'tb-rl',//文字竖排样式,
	            }
	        }
	    },
	    yAxis: {
	        tickInterval: 10,
	        gridLineColor: '#d6d6d6',
	        gridLineWidth: 1,
	        gridLineDashStyle: 'Dot',
	        title: {
	            enabled:false
	        },
	        min:1,
	        labels: {
	            style:{
	                color:'#999999',
	                fontSize:'16',
	            },
	            y:5
	        },
	    },
	    legend: {
	        verticalAlign: 'top',
	        align:'right',
	        itemStyle : {
	            'fontSize' : '18px', 
	            'color':'#666666'
	        }
	    },
	    tooltip: {
	        crosshairs: true,
	        shared: true,
	        headerFormat: '<small style="font-size:20px">{point.key}</small><br>',
	        pointFormat: '<span style="font-size:20px;color:{series.color}">{series.name}: <b style="font-size:20px">{point.y}</b><br/>',
	        useHTML: true
	    },
	    plotOptions: {
	        line: {
	            dataLabels: {
	                enabled: true,          // 开启数据标签
	                style:{
	                    fontSize:'18',
	                    color:'#666666'
	                }
	            }
	        }
	    },
	    exporting:{
	        enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
	    },
	    credits: {
	        enabled:false
	    },
	    series: [{
	        name: '班级单科平均分',
	        color:'#07aaf5',
	        data: [20, 25, 17, 15, 16, 14, 12, 15,11]
	    }]
	});
	
	
	$('#container11').highcharts({
	    chart: {
	        type: 'line',
	        marginRight:50
	    },
	    title: {
	        text: null
	    },
	    xAxis: {
	        gridLineColor: '#d6d6d6',
	        gridLineWidth: 1,
	        gridLineDashStyle: 'Dot',
	        categories: ['模拟考1234561', '模拟考模拟考模拟考模', '模拟考', '模拟考', '模拟考', '模拟考',
	                     '模拟考', '期中考试', '模拟考模拟考模拟考模', '模拟考', '模拟考', '期末考试'],
	        labels: {
	            align: 'left',
	            style:{
	                color:'#999999',
	                fontSize:'16',
	                writingMode : 'tb-rl'//文字竖排样式,
	            }
	        }
	    },
	    yAxis: {
	        gridLineColor: '#d6d6d6',
	        gridLineWidth: 1,
	        gridLineDashStyle: 'Dot',
	        title: {
	            enabled:false
	        },
	        labels: {
	            style:{
	                color:'#999999',
	                fontSize:'16',
	            },
	            y:5
	        },
	        min:0,
	    },
	    legend: {
	        align: 'right',
	        verticalAlign: 'top',
	        symbolHeight: 18,
	        symbolWidth: 18,
	        symbolRadius: 12,
	        itemStyle : {
	            'fontSize' : '18px', 
	            'color':'#666666'
	        }
	    },
	    tooltip: {
	        crosshairs: true,
	        shared: true,
	        headerFormat: '<small style="font-size:20px">{point.key}</small><br>',
	        pointFormat: '<span style="font-size:20px;color:{series.color}">{series.name}: <b style="font-size:20px">{point.y}</b><br/>',
	        useHTML: true
	    },
	    plotOptions: {
	        line: {
	            //enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
	            dataLabels: {
	                enabled: true,
	                align: 'left',
	                style: {
	                    fontSize:18,
	                },
	                allowOverlap:true,
	                x: 3,
	                verticalAlign: 'middle',
	                overflow: true,
	                crop: false,
	                useHTML: true
	            }
	        }
	    },
	    exporting:{
	        enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
	    },
	    credits: {
	        enabled:false
	    },
	    series: [{
	        name: '高一1班',
	        color:'#07aaf5',
	        data: [70, 69, 95, 85, 73, 66, 84, 85, 93, 83, 76,96 ],
	        dataLabels: {
	        	  shadow:false,
	            style: {
	                color:'#07aaf5',
	            },
	        },
	    }, {
	        name: '高一2班',
	        color:'#4cb9c3',
	        data: [39, 42, 57, 85, 25, 52, 70, 66, 42, 83, 66, 48],
	        dataLabels: {
	            style: {
	                color:'#4cb9c3',
	            },
	        },
	    },{
	        name: '高一3班',
	        color:'#ff6b88',
	        data: [{
	            y: 3.9,
	        }, 1.2, 4.3, 6.2, 10.6, 8.8, 12.3, 14.2, 13.4, 10.3, 9.8, 10.5],
	        dataLabels: {
	            style: {
	                color:'#ff6b88',
	            },
	        },
	    }]
	});


	
	
})
</script>
</html>