<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>班级VIP成绩查看</title>
		<meta name="apple-touch-fullscreen" content="YES">
	    <meta name="format-detection" content="telephone=no">
	    <meta name="apple-mobile-web-app-capable" content="yes">
	    <meta name="apple-mobile-web-app-status-bar-style" content="black">
	    <meta http-equiv="Expires" content="-1">
	    <meta http-equiv="pragram" content="no-cache">
	    <meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi"> 
	    <style>
.bjncts{
			  border: 3px solid rgb(255, 224, 178);
			  border-radius: 6px;
			  background-color: rgb(255, 252, 248);
			  box-shadow: 0px 1px 6px 0px rgba(0, 0, 0, 0.1);
			 padding: 10px 32px;
			 color: #f28949;
			 font-size: 17.78px;
		}
		.bjncts span{
			color: #f28949;
			font-size: 17.78px;
		}

</style>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/scoreAnalysis/css/all.css "/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/css/scoreAnalysis/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/css/scoreAnalysis/js/highcharts.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/css/scoreAnalysis/js/annotations.js"></script>
	</head>
	<body>
		<div>
			<!-- <p class="top_info">班级：<b>一年级一班</b>  科目：<b>语文</b>   总人数：<b>45</b>人 </p> -->
			<div class="sjxx mgb12">
				<p>
					<span class="type bgcolor_ff8a65">班级测试</span>
					<span class="title">${userTeamExam.name }</span>
				</p>
				<p class="time">发布时间：<fmt:formatDate value="${userTeamExam.publishTime}" pattern="yyyy年MM月dd日"/></p>
			</div>
			<div class="xsxx mgb12">
				 <p>
				 	<span>姓名：<b>${userTeamExam.studentName}</b></span><span>学号：<b>
				 	
				 	<c:choose>
				 		<c:when test="${empty userTeamExam.number}">
				 			无
				 		</c:when>
				 		<c:otherwise>
				 			${userTeamExam.number}
				 		</c:otherwise>
				 	</c:choose>
				 	</b></span>
				 </p><%-- <fmt:formatDate value="${userExamWorks.exam_date_begin } " pattern="yyyy年MM月dd日 HH:mm"/> ~ <fmt:formatDate value="${userExamWorks.exam_date_end } " pattern="HH:mm"/> --%>
				 <p class="ks_time">考试时间：<fmt:formatDate value="${userTeamExam.exam_date}" pattern="yyyy年MM月dd日 HH:mm"/></p>
			</div>
			
			<div class="ztqk mgb12 mokuai">
				<p class="title">总体情况</p>
				<c:choose>
					<c:when test="${showRanking == true }">
					<p class="wz"><b class="b1">${userTeamExam.studentName}</b>同学，在本次<b class="b1">${userTeamExam.subjectName}</b>考试中，分数为<b class="b1">${userTeamExam.score}</b>分，属于<b class="b1">${subsection }</b>。本次班级<b class="b1">${studentCount}</b>人参与考试，在班中排名第<b class="b1">${userTeamExam.team_rank}</b>名。</p>
					</c:when>
					<c:otherwise>
					<p class="wz"><b class="b1">${userTeamExam.studentName}</b>同学，在本次<b class="b1">${userTeamExam.subjectName}</b>考试中，分数为<b class="b1">${userTeamExam.score}</b>分，属于<b class="b1">${subsection }</b>。本次班级<b class="b1">${studentCount}</b>人参与考试，在班中<b class="b1">${userTeamExam.team_rank}</b> 继续努力^_^。</p>
					</c:otherwise>
				</c:choose>
				<table class="ztqk">
					<thead>
						<tr>
							<td>${userTeamExam.subjectName}</td><td>本班</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>您的分数</td><td>${userTeamExam.score}</td>
						</tr>
						<tr>
							<td>您的名次</td><td>${userTeamExam.team_rank}</td>
						</tr>
						<tr>
							<td>班级平均分</td><td>${userTeamExam.average_score}</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="mcfbx mokuai">
				<p class="title">班级名次分布线</p>
				<div id="container1" style="max-width:600px;height:400px"></div>
			</div>
			
			<div class="mcfbx mokuai">
				<p class="title">班级分数对比图</p>
				<div id="container2" style="max-width:600px;height:400px"></div>
			</div>
			
			<div class="fsddbt mokuai">
				<p class="title">分数段对比图</p>
				<div id="container3" style="max-width:600px;height:400px"></div>
			</div>
			
			<div class="fsqs mokuai">
				<p class="title">分数趋势</p>
				<div id="container4" style="max-width:600px;height:400px"></div>
			</div>
			
			<div class="mcqs mokuai">
				<p class="title">班内名次趋势</p>
				<div id="container5" style="max-width:600px;height:400px"></div>
			</div>
		</div>
		<script>
		$(function(){
			
			//班级名次分布线
			   var info = "<p class='bjncts'><span>${userTeamExam.studentName}</span> | <span> 得分：${userTeamExam.score}</span> | <span>排名:${userTeamExam.team_rank}</span></p>"
			    $('#container1').highcharts({
			        chart: {
			            type: 'scatter',
			            marginLeft:55,
			        },
			        title: {
			            text:info,
			            margin: 50,
			            useHTML:true
			        },
			        legend: {
			            enabled: false
			        },
			        xAxis: {
			            min:1,
			            allowDecimals: false,
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
			            min:1,
			            showFirstLabel: false,
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
			                value:${teamAvgRank},
			                label: {
			                    align: 'left',
			                    style: {
			                        fontStyle: 'normal',
			                        color:'#fd9494',
			                        fontSize:20
			                    },
			                    text: '平均分名次线：${teamAvgRank}',
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
			            enabled:false,
			        },
			        series: [{
			            color: 'rgba(3, 169, 245,0.8)',
			            data: ${teamRankList}
			        }]
			    });
			   
			 //班级分数对比图
				$('#container2').highcharts({
				   chart: {
				        type: 'bar',
				        zoomType: 'y',
			            panning: true,
			            panKey: 'shift',
			            marginLeft: 45
				},
				title: {
				    text: null
				},
				xAxis: {
				    title: {
				       // text: '总分',
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
		            shared: true,
		            headerFormat: '',
		            pointFormat: '<span style="font-size:20px;color:{series.color}">{series.name}: <b style="font-size:20px">{point.y}</b><br/>',
		            useHTML: true
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
				    data: [${userTeamExam.score}]
				}, {
				    name: '班级平均分',
				    color:'rgb(255,106,136)',
				    data: [${userTeamExam.average_score}]
				}, {
				    name: '班级最高分',
				    color:'rgb(60,211,173)',
				        data: [${teamMaxScore}]
				    }]
				});		
			   
				//分数段对比图
				var all_score=${fullScore};
				var current_score=${userTeamExam.score};
				var class_score=${teamScoreList};
				var fsd=[],fsd_number=[];
				var l=class_score.length;
				var you_score=Math.floor(current_score/10);
				if(all_score==current_score){
					you_score--
				}
				for(var i=0;i<all_score/10;i++){
					if(i==all_score/10-1){
						var inputValue =i*10+'-'+(i*10+10)+'段';
					}else{
						var inputValue=i*10+'-'+(i*10+10)+'段';
					}
					fsd.push(inputValue);
				}
				for(var i=0;i<all_score/10;i++){

					var num=0;
					if(i==all_score/10-1){
						$.each(class_score,function(index,value){
							if(value>i*10&&value<=i*10+10){
								num++;
							}
						});
					}else if(i==0){
                        $.each(class_score,function(index,value){
                            if(value>=0&&value<=10){
                                num++;
                            }
                        });
                    }else{
						$.each(class_score,function(index,value){
							if(value>i*10&&value<=i*10+10){
								num++;
							}
						});
					}
					if(i==you_score){
						num={y:num,color : "#36b5f6"}
					}
					fsd_number.push(num);
				}

				$('#container3').highcharts({
		        chart: {
		            type: 'bar',
		           /*  zoomType: 'y',
		            panning: true,
		            panKey: 'shift', */
		            marginLeft: 95
		        },
		        title: {
		            text: null
		        },
		        xAxis: {
		            categories: fsd,
		            title: {
		                text: '分数段',
		                rotation:0,
		                x:0,
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
		        	allowDecimals: false,
		            title: {
		                text: '您的孩子处于蓝色分数段',
		                style:{
		                    color:'#999999',
		                    fontSize:'16'
		                },
		                y:10
		            },
		            min:0,
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
		            data: fsd_number
		        }]
		    });
				
				//分数趋势
				$('#container4').highcharts({
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
					categories:  ${titleList},
				labels: {
				    //y:50,
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
		            headerFormat: '<small style="font-size:20px">{point.key}</small><br>',
			        pointFormat: '<span style="font-size:20px;color:{series.color}">{series.name}: <b style="font-size:20px">{point.y}</b><br/>',
			        useHTML: true
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
				    data: ${avgScoreList}
				}]
					})	
				
					//班内名次趋势
		$('#container5').highcharts({
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
		    //y:50,
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
		data:${nteamRankList}
		}]
			})
				
		});

		</script>
	</body>
</html>
