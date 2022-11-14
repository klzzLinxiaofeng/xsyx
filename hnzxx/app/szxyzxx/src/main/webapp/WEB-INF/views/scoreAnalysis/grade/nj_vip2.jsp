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
	     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/scoreAnalysis/css/highcharts.css "/>
		<!-- <link rel="stylesheet" type="text/css" href="css/all.css"/>
		<link rel="stylesheet" href="css/highcharts.css" /> -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/css/scoreAnalysis/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/css/scoreAnalysis/js/highcharts.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/css/scoreAnalysis/js/highcharts-more.js"></script>
	</head>
	<body>
		<div>
			<p class="top_info">“注：有<b class="b1">500</b>人参与本次统计，还有<b class="b1">96</b>人数据未导入。”</p>
			<div class="sjxx mgb12">
				<p>
					<span class="type bgcolor_ffcd40">年级统考</span>
					<span class="title">高一期末考试高一期末考试高一期末考试</span>
				</p>
				<p class="time">发布时间：2017年5月12日</p>
			</div>
			<div class="xsxx ">
				 <p>
				 	<span>姓名：<b>罗志明</b></span><span>学号：<b>20151100</b></span>
				 </p>
				 <p class="ks_time">考试时间段：2018年3月10日14:00~15:30</p>
				 <div class="xzfw" >
					<span class="on">综合分析</span>
					<span>单科分析</span>
					<span>趋势分析</span>
				</div>
			</div>
			<div class="big_detail">
				<div class="detail" >
					<div class='ztqk mokuai mgt12'>
						<p class="title">总体情况</p>
						<p class="wz"><b>罗志明</b>同学，在本次考试中，分数为<b class="b1">599.00</b>分，属于<b>高</b>分段。本次班级<b>500</b>人参与考试，在班中排名第<b class="b1">11</b>名，年级排名第<b class="b1">110</b>名。</p>
					</div>
					<div class="dfgk mokuai mgb12">
						<p class="title">得分概况</p>
						<table class="dfqk1">
						<thead>
							<tr>
								<td>科目</td><td>得分</td><td>班内排名</td><td>进退步</td><td>年级排名</td><td>进退步</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>全学科</td>
								<td><b class="b1">599</b>分/<b>900</b></td>
								<td>第<b class="b1">11</b>名/<b>50</b></td>
								<td><b>20</b><i class="up">↑</i></td>
								<td>第<b class="b1">11</b>名/<b>500</b></td>
								<td><b>20</b><i class="down">↓</i></td>
							</tr>
							<tr>
								<td>语文</td>
								<td><b class="b1">599</b>分/<b>900</b></td>
								<td>第<b class="b1">11</b>名/<b>50</b></td>
								<td><b>20</b><i class="up">↑</i></td>
								<td>第<b class="b1">11</b>名/<b>500</b></td>
								<td><b>20</b><i class="down" >↓</i></td>
							</tr>
							<tr>
								<td>语文</td>
								<td><b class="b1">599</b>分/<b>900</b></td>
								<td>第<b class="b1">11</b>名/<b>50</b></td>
								<td><b>20</b><i class="up">↑</i></td>
								<td>第<b class="b1">11</b>名/<b>500</b></td>
								<td><b>20</b><i class="down" >↓</i></td>
							</tr>
							<tr>
								<td>语文</td>
								<td><b class="b1">599</b>分/<b>900</b></td>
								<td>第<b class="b1">11</b>名/<b>50</b></td>
								<td><b>20</b><i class="up">↑</i></td>
								<td>第<b class="b1">11</b>名/<b>500</b></td>
								<td><b>20</b><i class="down" >↓</i></td>
							</tr>
							<tr>
								<td>语文</td>
								<td><b class="b1">599</b>分/<b>900</b></td>
								<td>第<b class="b1">11</b>名/<b>50</b></td>
								<td><b>20</b><i class="up">↑</i></td>
								<td>第<b class="b1">11</b>名/<b>500</b></td>
								<td><b>20</b><i class="down" >↓</i></td>
							</tr>
						</tbody>
					</table>
					<table class="dfqk2">
						<thead>
							<tr>
								<td>本科线</td><td>差异</td><td>重点线</td><td>差异</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>650</td><td style="color:#4caf50">-51</td><td>820</td><td style="color:#4caf50">-51</td>
							</tr>
						</tbody>
					</table>
					</div>
					<div class="pkfx mokuai mgb12">
						<p class="title">偏科分析</p>
						<p class="wz">罗志明同学，<b class="b1">英语</b>是较薄弱的科目，均衡发展是成功的垫脚石，要加强薄弱科目的学习，争取更大的进步。<b class="b1">英语</b>是较薄弱的科目，均衡发展是成功的垫脚石，要加强薄弱科目的学习，争取更大的进步。<b class="b1">英语</b>是较薄弱的科目，均衡发展是成功的垫脚石，要加强薄弱科目的学习，争取更大的进步。<b class="b1">英语</b>是较薄弱的科目，均衡发展是成功的垫脚石，要加强薄弱科目的学习，争取更大的进步。</p>
					</div>
					<div class="gxkdfldt mokuai mgb12">
						<p class="title">各学科得分雷达图</p>
						<div id="container1" style=" max-width: 600px; height: 600px; margin: 0 auto"></div>
					</div>
					<div class="njmcfbx mokuai mgb12">
						<p class="title">年级名次分布线</p>
						<div id="container2" style="max-width:600px;height:400px"></div>
					</div>
					<div class="zfdbt mokuai mgb12">
						<p class="title">总分对比图</p>
						<div id="container3" style="max-width:600px;height:400px"></div>
					</div>
					<div class="fsdbt mokuai mgb12">
						<p class="title">分数对比图</p>
						<div id="container4" style="max-width:600px;height:400px"></div>
					</div>
					<div class="zffsdt mokuai mgb12">
						<p class="title">总分分数段图</p>
						<div id="container5" style="max-width:600px;height:400px"></div>
					</div>
				</div>
				<div class="detail" style="display: none;">
					<div class="all_km mokuai mgb12">
						<ul>
							<li class="on">语文</li>
							<li>数学123456</li>
							<li>英语12345</li>
							<li>物理</li>
							<li>化学</li>
							<li>英语12345</li>
							<li>物理</li>
							<li>化学</li>
						</ul>
					</div>
					<div class="dfgk mokuai mgb12">
						<p class="title">得分概况</p>
						<table class="dfqk3">
						<thead>
							<tr>
								<td>语文</td><td>本班</td><td>年级</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>你的分数</td><td>90</td><td>90</td>
							</tr>
							<tr>
								<td>你的名次</td><td>90</td><td>90</td>
							</tr>
							<tr>
								<td>均分</td><td>90</td><td>90</td>
							</tr>
							<tr>
								<td>均分名次</td><td>90</td><td>90</td>
							</tr>
						</tbody>
					</table>
					</div>
					<div class="njmcfbx mokuai mgb12">
						<p class="title">年级名次分布线</p>
						<div id="container6" style="max-width:600px;height:400px"></div>
					</div>
					<div class="zffsd mokuai mgb12">
						<p class="title">总分分数段</p>
						<div id="container7" style="max-width:600px;height:400px"></div>
					</div>
					<div class="tfjy mokuai mgb12">
						<p class="title">提分建议</p>
						<a href="javascript:void(0)"></a>
					</div>
				</div>
				<div class="detail" style="display: none;">
					<div class="all_km mokuai mgb12">
						<ul>
							<li class="on">语文</li>
							<li>数学123456</li>
							<li>英语12345</li>
							<li>物理</li>
							<li>化学</li>
							<li>英语12345</li>
							<li>物理</li>
							<li>化学</li>
						</ul>
					</div>
					<div class="fsqs mokuai mgb12">
						<p class="title">分数趋势</p>
						<div id="container8" style="max-width:600px;height:400px"></div>
					</div>
					<div class="bnmcqs mokuai mgb12">
						<p class="title">班内名次趋势</p>
						<div id="container9" style="max-width:600px;height:400px"></div>
					</div>
					<div class="njmcqs mokuai mgb12">
						<p class="title">年级名次趋势</p>
						<div id="container10" style="max-width:600px;height:400px"></div>
					</div>
				</div>
			</div>
			
				
			
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
		
		//各学科得分雷达图
		$('#container1').highcharts({
        chart: {
            polar: true,
            type: 'line',
            zoomType: 'y',
            panning: true,
            panKey: 'shift'
        },
        title: {
            text: null
        },
        pane: {
            size: '80%'
        },
        xAxis: {
            categories: ['语文', '数学', '英语', '生物',
                         '化学', '政治','物理'],
            tickmarkPlacement: 'on',
            lineWidth: 0,
            //lineColor: '#FFFFFF',
            labels:{
                style:{
                    fontSize:16,
                    color:'#999999'
                },
                y:10
            },
        },
        yAxis: {
            labels:{
                enabled:false
            },
            gridLineInterpolation: 'polygon',
            lineWidth: 0,
            min: 0
        },
        tooltip: {
            shared: true,
            useHTML: true,
            style: {
                padding: 10,
                fontSize:16
            }
        },
        exporting:{
            enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
        },
        credits: {
            enabled:false
        },
        legend: {
            align: 'center',
            verticalAlign: 'bottom',
            /* y: 70,
             layout: 'vertical',*/
            symbolHeight: 18,
            symbolWidth: 18,
            symbolRadius: 12,
            itemStyle : {
                'fontSize' : '18px', 
                'color':'#666666'
            }
        },
        series: [{
            name: '个人得分',
            data: [43, 19, 60, 35, 17, 10,20],
            color:'#08aaf5',
            pointPlacement: 'on'
        }, {
            name: '班级评分',
            data: [50, 39, 42, 31, 26, 14,30],
            color:'rgb(255,106,136)',
            pointPlacement: 'on'
        }, {
            name: '年级均分',
            data: [40, 59, 36, 38, 36, 55,40],
            color:'rgb(60,211,173)',
            pointPlacement: 'on'
        }]
    });
		
		//年级名次分布线
		$('#container2').highcharts({
		chart: {
		    type: 'scatter',
			zoomType: 'x',
            panning: true,
            panKey: 'shift',
		    spacingTop:50,
		    marginLeft: 40
		},
		title: {
		    text: null
		},
		legend: {
		    enabled: false
		},
		xAxis: {
		    title: {
		        enabled: true,
		    },
		    labels: {
		        enabled: false
		    },
		    startOnTick: true,
		    endOnTick: true,
		    showLastLabel: true
		},
		yAxis: {
		    min: 0,
		    title: {
		        "text": "排名",
				align: 'high',
				rotation:0,
				x:50,
				y:-20,
		style:{
		    fontSize:16,
		    color: '#999999',
		    }
		},
		labels: {
		    y:5,
		    style: {
		        color: '#999999',
		        fontSize:16
		    }
		},
		reversed: true,
		plotLines: [{
		    color: '#ff6a88',
		dashStyle: 'Solid',
		width: 3,
		value: 13,
		label: {
		    align: 'left',
		style: {
		    fontStyle: 'normal',
		    color:'#fd9494',
		    fontSize:20
		},
		text: '平均分名次线：13',
		            x: -10,
		            y:25
		        },
		        zIndex: 3
		    }]
		},
		exporting:{
		    enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
		},
		credits: {
		    enabled:false
		},
		tooltip: {
		    useHTML: true,
			formatter: function () {
                return '<p style="font-size:18px">' + this.point.name + '</p>' +
                    '<p style="font-size:18px">总分：'+this.x+'</p>'+
                    '<p style="font-size:18px"> 排名：'+this.y+'</p>';
            }
	},
		//针对不同类型图表的配置
		plotOptions: {
		    scatter: {
		        marker: {
		            radius: 5,
		            states: {
		                hover: {
		                    enabled: true,
		                    lineColor: '#f38d4e',
		        color:'#f38d4e'
		                }
		            }
		        },
		        states: {
		            hover: {
		                marker: {
		                    enabled: false
		                }
		            }
		        }
		    }
		},
		series: [{
		    color: 'rgba(3, 169, 245,0.8)',
		data: [{x:380,y:2,name:'a'},
		   {x:279,y:3,name:'b'},
		   {x:176,y:4,name:'c'},
		   {x:166,y:11,name:'d'},
		   {x:120,y:20,name:'e'}
		              ]
		    }]
		});
		
		//总分对比图
		$('#container3').highcharts({
        chart: {
            type: 'bar',
            zoomType: 'y',
            panning: true,
            panKey: 'shift'
        },
        title: {
            text: null
        },
        xAxis: {
            title: {
                text: '总分',
                rotation:0,
                x:-10,
                style:{
                    fontSize:16,
                    color:'#999999'
                }
            },
            labels: {
                enabled: false
            },
            tickWidth:0,
            gridLineDashStyle:'Dash',
            lineWidth: 0
        },
        yAxis: {
            title: {
                text: null
            },
            min:0,
            max:800,
            gridLineDashStyle:'Dash',
            gridLineColor: '#d6d6d6'
        },
        legend: {
            verticalAlign: 'bottom',
            align:'center',
            symbolHeight: 18,
            symbolWidth: 18,
            symbolRadius: 12,
            itemStyle : {
                'fontSize' : '18px', 
                'color':'#666666'
            }
        },
        tooltip: {
            enabled:false
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true,          // 开启数据标签
                    style:{
                        fontSize:'16',
                        color:'#666666'
                    },
                    allowOverlap: true,
                },
            },
            series:{
                borderRadius:7,
                pointPadding:0.3
            }
        },
        exporting:{
            enabled:false
        },
        credits: {
            enabled: false
        },
        series: [{
            name: '个人得分',
            color:'#08aaf5',
            data: [599.5]
        }, {
            name: '班级评分',
            color:'rgb(255,106,136)',
            data: [540.2]
        }, {
            name: '班级最高分',
            color:'rgb(60,211,173)',
            data: [520]
        }, {
            name: '年级最高分',
            color:'rgb(139,170,185)',
            data: [675]
        }]
    });
		
		//分数对比图
		$('#container4').highcharts({
        chart: {
            type: 'column',
            zoomType: 'y',
            panning: true,
            panKey: 'shift'
        },
        title: {
            text: null
        },
        xAxis: {
            categories: [
                '语文',
                '数学',
                '英语',
                '物理',
                '化学',
                '生物'
            ],
            labels:{
                style:{
                    fontSize:18,
                    color:'#999999'
                },
                y:30
            },
            crosshair: true
        },
        yAxis: {
        	labels:{
                style:{
                    fontSize:18,
                    color:'#999999'
                },
                y:8
            },
            min: 0,
            title: {
                enabled:false
            },
        },
        exporting:{
            enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
        },
        credits: {
            enabled:false
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
        legend: {
            verticalAlign: 'bottom',
            align:'center',
            symbolHeight: 18,
            symbolWidth: 18,
            symbolRadius: 12,
            itemStyle : {
                'fontSize' : '18px',
                'color':'#666666'
            }
        },
        plotOptions: {
            series:{
                borderRadius:5,
                pointPadding:0.3
            }
        },
        series: [{
            name: '个人得分',
            color:'#08aaf5',
            data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0]
        }, {
            name: '班级评分',
            color:'rgb(255,106,136)',
            data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5]
        }, {
            name: '年级均分',
            color:'rgb(60,211,173)',
            data: [48.9, 38.8, 39.3, 41.4, 0, 0]
        }, {
            name: '年级最高分',
            color:'rgb(139,170,185)',
            data: [42.4, 33.2, 34.5, 39.7, 0,0]
        }]
    });
		
		//分数段对比图
		$('#container5').highcharts({
        chart: {
            type: 'bar',
            zoomType: 'y',
            panning: true,
            panKey: 'shift',
            marginLeft: 95
        },
        title: {
            text: null
        },
        xAxis: {
            categories: ['0-9段', '10-19段', '20-29段', '30-39段', '40-49段','50-59段','60-69段','70-79段','80-89段','90-99段','100-109段','110-119段','120-129段','130-139段','140-150段'],
            title: {
                text: '分数段',
                rotation:0,
                x:47,
                style:{
                    fontSize:16,
                    color:'#999999',
                    writingMode : 'tb-rl'//文字竖排样式,
                }
            },
            reversed: false,
            tickWidth:0,
            gridLineDashStyle:'Dash',
            lineWidth: 0
        },
        yAxis: {
            title: {
                text: '处于该分数段的人数',
                style:{
                    color:'#999999',
                    fontSize:'16'
                },
                y:10
            },
            min:0,
            max:70,
            gridLineDashStyle:'Dash',
            gridLineColor: '#d6d6d6',
        },
        legend: {
            enabled:false
        },
         tooltip: {
            crosshairs: true,
            pointFormat: '<span style="color:{series.color}" >{point.y}人<br/>',
            shared: true,
            useHTML: true,
            style: {
                padding: 10,
                fontSize:16
            }
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: false,
                    allowOverlap: true
                },
            },
            series:{
                borderRadius:7,
                pointPadding:0.3
            }
        },
        exporting:{
            enabled:false
        },
        credits: {
            enabled: false
        },
        series: [{
            name:'a',
            color:'#8baab9',
            data: [0,0,0,0,0,0,15,20,12,{
                y:10,
                color : "#36b5f6"
            },6,0,0,0,0]
        }]
    });
		
		//年级名次分布线
		 $('#container6').highcharts({
		chart: {
		    type: 'scatter',
			zoomType: 'x',
            panning: true,
            panKey: 'shift',
		    spacingTop:50,
		    marginLeft: 40
		},
		title: {
		    text: null
		},
		legend: {
		    enabled: false
		},
		xAxis: {
		    title: {
		        enabled: true,
		    },
		    labels: {
		        enabled: false
		    },
		    startOnTick: true,
		    endOnTick: true,
		    showLastLabel: true
		},
		yAxis: {
		    min: 0,
		    title: {
		        "text": "排名",
				align: 'high',
				rotation:0,
				x:50,
				y:-20,
		style:{
		    fontSize:16,
		    color: '#999999',
		    }
		},
		labels: {
		    y:5,
		    style: {
		        color: '#999999',
		        fontSize:16
		    }
		},
		reversed: true,
		plotLines: [{
		    color: '#ff6a88',
		dashStyle: 'Solid',
		width: 3,
		value: 13,
		label: {
		    align: 'left',
		style: {
		    fontStyle: 'normal',
		    color:'#fd9494',
		    fontSize:20
		},
		text: '平均分名次线：13',
		            x: -10,
		            y:25
		        },
		        zIndex: 3
		    }]
		},
		exporting:{
		    enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
		},
		credits: {
		    enabled:false
		},
		tooltip: {
		    useHTML: true,
			formatter: function () {
                return '<p style="font-size:18px">' + this.point.name + '</p>' +
                    '<p style="font-size:18px">总分：'+this.x+'</p>'+
                    '<p style="font-size:18px"> 排名：'+this.y+'</p>';
            }
	},
		//针对不同类型图表的配置
		plotOptions: {
		    scatter: {
		        marker: {
		            radius: 5,
		            states: {
		                hover: {
		                    enabled: true,
		                    lineColor: '#f38d4e',
		        color:'#f38d4e'
		                }
		            }
		        },
		        states: {
		            hover: {
		                marker: {
		                    enabled: false
		                }
		            }
		        }
		    }
		},
		series: [{
		    color: 'rgba(3, 169, 245,0.8)',
		data: [{x:380,y:2,name:'a'},
		   {x:279,y:3,name:'b'},
		   {x:176,y:4,name:'c'},
		   {x:166,y:11,name:'d'},
		   {x:120,y:20,name:'e'}
		              ]
		    }]
		});
		
		//总分分数段
		$('#container7').highcharts({
        chart: {
            type: 'bar',
            zoomType: 'y',
            panning: true,
            panKey: 'shift',
            marginLeft: 95
        },
        title: {
            text: null
        },
        xAxis: {
            categories: ['0-9段', '10-19段', '20-29段', '30-39段', '40-49段','50-59段','60-69段','70-79段','80-89段','90-99段','100-109段','110-119段','120-129段','130-139段','140-150段'],
            title: {
                text: '分数段',
                rotation:0,
                x:47,
                style:{
                    fontSize:16,
                    color:'#999999',
                    writingMode : 'tb-rl'//文字竖排样式,
                }
            },
            reversed: false,
            tickWidth:0,
            gridLineDashStyle:'Dash',
            lineWidth: 0
        },
        yAxis: {
            title: {
                text: '处于该分数段的人数',
                style:{
                    color:'#999999',
                    fontSize:'16'
                },
                y:10
            },
            min:0,
            max:70,
            gridLineDashStyle:'Dash',
            gridLineColor: '#d6d6d6',
        },
        legend: {
            enabled:false
        },
         tooltip: {
            crosshairs: true,
            pointFormat: '<span style="color:{series.color}" >{point.y}人<br/>',
            shared: true,
            useHTML: true,
            style: {
                padding: 10,
                fontSize:16
            }
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: false,
                    allowOverlap: true
                },
            },
            series:{
                borderRadius:7,
                pointPadding:0.3
            }
        },
        exporting:{
            enabled:false
        },
        credits: {
            enabled: false
        },
        series: [{
            name:'a',
            color:'#8baab9',
            data: [0,0,0,0,0,0,15,20,12,{
                y:10,
                color : "#36b5f6"
            },6,0,0,0,0]
        }]
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
			categories: ['模拟考', '模拟考', '模拟考', '模拟考', '模拟考', '模拟考',
			     '模拟考', '期中考试', '模拟考', '模拟考', '模拟考', '期末考试'],
		labels: {
		    y:50,
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
		    min:0,
		    max:100
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
		data: [70, 69, 95, 85, 73, 66, 84, 85, 93, 83, 76, {
		    dataLabels: {
		        style: {
		            fontWeight: 'bold',
				    color:'#ff5252',
				    fontSize:'18'
		    }
		},
		y:96,
		color:'#ff5252'
		}],
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
		    name: '班级平均',
		color:'#4cb9c3',
		    data: [39, 42, 57, 85, 19, 52, 70, 66, 42, 83, 66, 48]
		},{
		    name: '年级平均',
		color:'#ff6b88',
		    data: [{
		        y: 3.9,
		    }, 1.2, 4.3, 6.2, 10.6, 8.8, 12.3, 14.2, 13.4, 10.3, 9.8, 10.5]
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
			categories: ['模拟考', '模拟考', '模拟考', '模拟考', '模拟考', '模拟考',
			             '模拟考', '模拟考','期末考'],
		labels: {
		    y:50,
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
		    max:60,
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
		data: [20, 25, 17, 15, 16, 14, 12, 15,{
		    dataLabels: {
		        enabled: true,
		        align: 'left',
		        style: {
		            fontWeight: 'bold',
		            color:'#ff5252',
		            fontSize:'18'
		        },
		        verticalAlign: 'middle',
		            overflow: true,
		            crop: false
		        },
		        y: 11,
		    }]
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
			categories: ['模拟考', '模拟考', '模拟考', '模拟考', '模拟考', '模拟考',
			             '模拟考', '模拟考','期末考'],
		labels: {
		    y:50,
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
		    max:60,
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
		data: [20, 25, 17, 15, 16, 14, 12, 15,{
		    dataLabels: {
		        enabled: true,
		        align: 'left',
		        style: {
		            fontWeight: 'bold',
		            color:'#ff5252',
		            fontSize:'18'
		        },
		        verticalAlign: 'middle',
		            overflow: true,
		            crop: false
		        },
		        y: 11,
		    }]
		}]
			})
				
				
				
			})
		</script>
	</body>
</html>
