<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				<div class="detail" >
					<div class='ztqk mokuai mgt12'>
						<p class="title">总体情况</p>
						<c:choose>
							<c:when test="${showRanking == true}">
								<p class="wz"><b class="b1">${compositeMap.studentName}</b>同学在本次考试中，总分为 <b class="b1">${compositeMap.totalScore}</b>分，属于<b class="b1">${subsection }</b>。其中，全年级<b class="b1">${gradeExamStudentCount}</b>人参与考试，年级排名第<b class="b1"> ${compositeMap.gradeRank }</b>名; 全班<b class="b1">${teamStudentCount}</b>人参与考试，在班中排名第<b class="b1"> ${compositeMap.teamRank}</b>名。</p>
							</c:when>
							<c:otherwise>
								<p class="wz"><b class="b1">${compositeMap.studentName}</b>同学在本次考试中，总分为 <b class="b1">${compositeMap.totalScore}</b>分，属于<b class="b1">${subsection }</b>。其中，全年级<b class="b1">${gradeExamStudentCount}</b>人参与考试，年级<b class="b1"> ${compositeMap.gradeRank }</b>; 全班<b class="b1">${teamStudentCount}</b>人参与考试，在班中<b class="b1"> ${compositeMap.teamRank}</b>。</p>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="dfgk mokuai mgb12">
						<p class="title">得分概况</p>
						<table class="dfqk1">
						<thead>
							<tr>
								<td>科目</td><td>得分</td><td>班内排名<br>(人数：${teamStudentCount })</td><td>进退步</td><td>年级排名<br>(人数：${gradeExamStudentCount })</td><td>进退步</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>全学科</td>
								<td><b class="b1">${compositeMap.totalScore }</b>分/<b>${compositeMap.allSubjectTotalScore}</b></td>
								<td><b class="b1">${compositeMap.teamRank }</b></td>
								<c:choose>
									<c:when test="${compositeMap.teamRankChange > 0}">
										<td><b>${compositeMap.teamRankChange }</b><i class="up">↑</i></td>
									</c:when>
									<c:when test="${compositeMap.teamRankChange < 0}">
										<td><b>${compositeMap.teamRankChange < 0 ? -compositeMap.teamRankChange:compositeMap.teamRankChange}</b><i class="down">↓</i></td>
									</c:when>
									<c:otherwise>
										<td> - </td>
									</c:otherwise>     
								</c:choose>
								<td><b class="b1">${compositeMap.gradeRank }</b></td>
								<c:choose>
									<c:when test="${compositeMap.gradeRankChange > 0}">
										<td><b>${compositeMap.gradeRankChange }</b><i class="up">↑</i></td>
									</c:when>
									<c:when test="${compositeMap.gradeRankChange < 0}">
										<td><b>${-compositeMap.gradeRankChange}</b><i class="down">↓</i></td>
									</c:when>
									<c:otherwise>
										<td> - </td>
									</c:otherwise>
								</c:choose>
							</tr>
							<c:forEach items="${userSubjectList}" var="userSubject">
								<tr>
									<td>${userSubject.subjectName}</td>
									<td><b class="b1">${userSubject.score }</b>分/<b>${userSubject.full_score }</b></td>
									<td><b class="b1">${userSubject.team_rank}</b></td>
								<c:choose>
									<c:when test="${userSubject.team_rank_change > 0}">
										<td><b>${userSubject.team_rank_change }</b><i class="up">↑</i></td>
									</c:when>
									<c:when test="${userSubject.team_rank_change < 0}">
										<td><b>${-userSubject.team_rank_change}</b><i class="down">↓</i></td>
									</c:when>
									<c:otherwise>
										<td> - </td>
									</c:otherwise>	
									</c:choose>
									<td><b class="b1">${userSubject.grade_rank}</b></td>									
									<c:choose>
									<c:when test="${userSubject.grade_rank_change > 0}">
										<td><b>${userSubject.grade_rank_change }</b><i class="up">↑</i></td>
									</c:when>
									<c:when test="${userSubject.grade_rank_change < 0}">
										<td><b>${-userSubject.grade_rank_change}</b><i class="down">↓</i></td>
									</c:when>
									<c:otherwise>
										<td> - </td>
									</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					</div>
					<%-- <div class="pkfx mokuai mgb12">
						<p class="title">偏科分析</p>
						<p class="wz">${userExamWork.studentName}同学，<b class="b1">英语</b>是较薄弱的科目，均衡发展是成功的垫脚石，要加强薄弱科目的学习，争取更大的进步。<b class="b1">英语</b>是较薄弱的科目，均衡发展是成功的垫脚石，要加强薄弱科目的学习，争取更大的进步。<b class="b1">英语</b>是较薄弱的科目，均衡发展是成功的垫脚石，要加强薄弱科目的学习，争取更大的进步。<b class="b1">英语</b>是较薄弱的科目，均衡发展是成功的垫脚石，要加强薄弱科目的学习，争取更大的进步。</p>
					</div> --%>
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
						<p class="title">全学科分数对比图</p>
						<div id="container4" style="max-width:600px;height:400px"></div>
					</div>
					<div class="zffsdt mokuai mgb12">
						<p class="title">总分分数段图</p>
						<div id="container5" style="max-width:600px;height:400px"></div>
					</div>
				</div>
				<div class="detail" style="display: none;">
					<!-- <div class="all_km mokuai mgb12">
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
					</div> -->
					<!-- <div class="dfgk mokuai mgb12">
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
					</div> -->
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
            categories: ${subjectNameList},
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
             headerFormat: '<small style="font-size:20px">{point.key}</small><br>',
            pointFormat: '<span style="font-size:20px;color:{series.color}">{series.name}: <b style="font-size:20px">{point.y}</b><br/>',
            useHTML: true,
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
            data: ${userScoreList},
            color:'#08aaf5',
            pointPlacement: 'on'
        }, {
            name: '班级平均分',
            data: ${teamAvgScoreList},
            color:'rgb(255,106,136)',
            pointPlacement: 'on'
        }, {
            name: '年级平均分',
            data: ${gradeAvgScoreList},
            color:'rgb(60,211,173)',
            pointPlacement: 'on'
        }]
    });
		
		//班级名次分布线
		   var info = "<p class='bjncts'><span>${compositeMap.studentName}</span> | <span> 得分:${compositeMap.totalScore}</span> | <span>排名:${compositeMap.gradeRank}</span></p>"
		    $('#container2').highcharts({
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
		                value:${gradeAvgRank},
		                label: {
		                    align: 'left',
		                    style: {
		                        fontStyle: 'normal',
		                        color:'#fd9494',
		                        fontSize:20
		                    },
		                    text: '平均分名次线：${gradeAvgRank}',
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
		            data: ${gradeRankList}
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
	                //text: '总分',
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
//	            max:800,
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
	            data: [${compositeMap.totalScore}]
	        }, {
	            name: '班级平均分',
	            color:'rgb(255,106,136)',
	            data: [${totalComparisonMap.teamAvgScore}]
	        }, {
	            name: '年级平均分',
	            color:'rgb(60,211,173)',
	            data: [${totalComparisonMap.gradeAvgScore}]
	        }, {
	            name: '年级最高分',
	            color:'rgb(139,170,185)',
	            data: [${totalComparisonMap.gradeMaxScore}]
	        }]
	    });
		 
		 
		 
			//分数对比图
			
			var km_num = '${subjectSize}';
			$('#container4').highcharts({
				chart: {
		        	 panning: true,
		        	pinchType: 'x',
		            type: 'column',
		           resetZoomButton: {
		            position: {
		                y: -1000 
		            }
		        }
		            
		        },
	        title: {
	            text: null
	        },
	        xAxis: {
	            categories: ${subjectNameList},
	            labels:{
	                style:{
	                    fontSize:18,
	                    color:'#999'
	                },
	                y:30
	            },
	            crosshair: true
	        },
	        scrollbar : {
	            enabled:false
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
	            shared: true,
	            headerFormat: '<small style="font-size:20px;font-weight:bold">{point.key}</small><br>',
	            pointFormat: '<span style="font-size:20px;color:{series.color}">{series.name}: <b style="font-size:20px">{point.y}</b><br/>',
	            useHTML: true,
	            followTouchMove:false,

	        },
	        legend: {
	            verticalAlign: 'bottom',
	            align:'center',
	            symbolHeight: 18,
	            symbolWidth: 18,
	            symbolRadius: 12,
	            itemStyle : {
	                'fontSize' : '18px',
	                'color':'#666'
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
	            data: ${userScoreList}
	        }, {
	            name: '班级平均分',
	            color:'rgb(255,106,136)',
	            data: ${teamAvgScoreList}
	        }, {
	            name: '年级平均分',
	            color:'rgb(60,211,173)',
	            data: ${gradeAvgScoreList}
	        }, {
	            name: '年级最高分',
	            color:'rgb(139,170,185)',
	            data: ${gradeMaxScoreList}
	        }]
	    }, function(c) {
	        // 动态改变 x 轴范围即可实现拖动
	        
	    	 if(km_num>3){
	    	 		c.xAxis[0].minRange=2.5;
	    	    	 c.xAxis[0].setExtremes(0,2);
	    	  }
	    	   
	    	});
			//分数段对比图
			var all_score=${compositeMap.allSubjectTotalScore};
			var current_score=${compositeMap.totalScore};
			var class_score= ${teamTotalScoreList};
			var d_number= Math.ceil(all_score/15)
			var fsd=[],fsd_number=[];
			var l=class_score.length;
			var you_score=Math.floor(current_score/d_number);
			if(all_score==current_score){
				you_score--
			}
			for(var i=0;i<15;i++){
				if(i==14){
					var inputValue = i*d_number+'-'+(all_score)+'段';
				}else{
					var inputValue = i*d_number+'-'+(i*d_number+d_number)+'段';
				}
				fsd.push(inputValue);
			}
			for(var i=0;i<15;i++){

				var num=0;
				if(i==14){
					$.each(class_score,function(index,value){
					if(value>14*d_number&&value<=all_score){
						num++;
					}
				});
				}else if(i==0){
                    $.each(class_score,function(index,value){
                        if(value>=0&&value<=d_number){
                            num++;
                        }
                    });
                }else{
					$.each(class_score,function(index,value){
					if(value>i*d_number&&value<=i*d_number+d_number){
						num++;
					}
				});
				}
				if(i==you_score){
					num={y:num,color : "#36b5f6"}
				}
				fsd_number.push(num);
			}
			
			$('#container5').highcharts({
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
		 
			})
		</script>
	</body>
</html>
