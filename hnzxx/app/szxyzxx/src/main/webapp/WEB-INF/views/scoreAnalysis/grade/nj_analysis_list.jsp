<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

					<div class="fsqs mokuai mgb12">
						<p class="title">分数趋势</p>
						<div id="container8" style="max-width:600px;height:600px"></div>
					</div>
					<div class="bnmcqs mokuai mgb12">
						<p class="title">班内名次趋势</p>
						<div id="container9" style="max-width:600px;height:600px"></div>
					</div>
					<div class="njmcqs mokuai mgb12">
						<p class="title">年级名次趋势</p>
						<div id="container10" style="max-width:600px;height:600px"></div>
					</div>
				
		<script>
			$(function(){
				$('.xzfw span').click(function(){
					$(this).addClass('on');
					$(this).siblings().removeClass('on');
					var i = $(this).index();
					$('.big_detail').find('.detail').hide();
					$('.big_detail').find('.detail').eq(i).show();
				});
				$('.all_km ul li').click(function(){
					$(this).addClass('on');
					$(this).siblings().removeClass('on');
				});
		
		//分数趋势
		$('#container8').highcharts({
			chart: {
			type: 'line',
			zoomType: 'x',
			panning: true,
			panKey: 'shift',
		},
		title: {
		    text: null
		},
		xAxis: {
		    gridLineColor: '#d6d6d6',
			gridLineWidth: 1,
			gridLineDashStyle: 'Dot',
			categories: ${titleList},
			labels: {
			     align: 'left',
			    style:{
			        color:'#999999',
					fontSize:'14',
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
		    min:0
		    
		},
		tooltip: {
		    crosshairs: true,
		    shared: true,
		    useHTML: true,
            style: {
                padding: 10,
                fontSize:16
            }
		},
		plotOptions: {
		    line: {
		        //enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
		    }
		},
		exporting:{
		    enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
		},
		credits: {
		    enabled:false
		},
		series: [{
		    name: '个人',
		color:'#07aaf5',
		data: ${userScoreList},
		dataLabels: {
		    enabled: true,
		    align: 'left',
		style: {
		    color:'#07aaf5',
		    fontSize:18,
		},
		x: 3,
		verticalAlign: 'middle',
		        overflow: true,
		        crop: false
		    },
		}, {
		    name: '班级平均分',
		color:'#4cb9c3',
		    data: ${teamAvgScoreList}
		},{
		    name: '年级平均分',
		color:'#ff6b88',
		    data: ${gradeAvgScoreList}
		}]
			})
			
		//班内名次趋势
		$('#container9').highcharts({
			chart: {
			type: 'line',
			zoomType: 'x',
	        panning: true,
	        panKey: 'shift',
		},
		title: {
		    text: null
		},
		xAxis: {
		    gridLineColor: '#d6d6d6',
			gridLineWidth: 1,
			gridLineDashStyle: 'Dot',
			categories: ${titleList},
		labels: {
		     align: 'left',
			    style:{
			        color:'#999999',
					fontSize:'14',
					writingMode : 'tb-rl'//文字竖排样式,
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
		    reversed: true,
		},
		legend: {
		    align: 'center', //水平方向位置
			verticalAlign: 'bottom', //垂直方向位置
		},
		tooltip: {
		    crosshairs: true,
		    shared: true
		},
		plotOptions: {
		    line: {
		        dataLabels: {
		            enabled: true,          // 开启数据标签
		        style:{
		            fontSize:'18',
		            color:'#666666'
		        }
		    },
		    enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
		    }
		},
		exporting:{
		    enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
		},
		credits: {
		    enabled:false
		},
		series: [{
		    name: '个人',
		color:'#07aaf5',
		data: ${userTeamRankList}
		}]
			})
		
		//年级名次趋势
		$('#container10').highcharts({
			chart: {
			type: 'line',
			zoomType: 'x',
	        panning: true,
	        panKey: 'shift',
		},
		title: {
		    text: null
		},
		xAxis: {
		    gridLineColor: '#d6d6d6',
			gridLineWidth: 1,
			gridLineDashStyle: 'Dot',
			categories: ${titleList},
			labels: {
			     align: 'left',
			    style:{
			        color:'#999999',
					fontSize:'14',
					writingMode : 'tb-rl'//文字竖排样式,
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
		    reversed: true,
		},
		legend: {
		    align: 'center', //水平方向位置
			verticalAlign: 'bottom', //垂直方向位置
		},
		tooltip: {
		    crosshairs: true,
		    shared: true
		},
		plotOptions: {
		    line: {
		        dataLabels: {
		            enabled: true,          // 开启数据标签
		        style:{
		            fontSize:'18',
		            color:'#666666'
		        }
		    },
		    enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
		    }
		},
		exporting:{
		    enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
		},
		credits: {
		    enabled:false
		},
		series: [{
		    name: '个人',
		color:'#07aaf5',
		data: ${userGradeRankList}
		}]
			})
				
			})
		</script>
	</body>
</html>
