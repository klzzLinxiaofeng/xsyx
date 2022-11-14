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
					<div class="dfgk mokuai mgb12">
						<p class="title">得分概况</p>
						<table class="dfqk3">
						<thead>
							<tr>
								<td>${subjectName }</td><td>本班</td><td>年级</td>
							</tr>
						</thead>
						<tbody>
						<tr>
							<td>你的分数</td><td>${userExamWork.score}</td><td>${userExamWork.score}</td>
						</tr>
						<tr>
							<td>你的名次</td><td>${userExamWork.team_rank}</td><td>${userExamWork.grade_rank }</td>
						</tr>
						<tr>
							<td>均分</td><td>${userExamWork.average_score}</td><td><fmt:formatNumber type="number" value="${gradeAvg }" pattern="0.00" maxFractionDigits="2"/></td>
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
					<!-- <div class="tfjy mokuai mgb12">
						<p class="title">提分建议</p>
						<a href="/res/css/scoreAnalysis/html/now_dy.html"></a>
					</div> -->
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
					var subjectCode = $(this).attr("code");
					$(this).addClass('on');
					$(this).siblings().removeClass('on');
				});
		
		
				//班级名次分布线
				   var info = "<p class='bjncts'><span>${userExamWork.studentName}:</span> | <span> 得分：${userExamWork.score}</span> | <span>排名:${userExamWork.grade_rank}</span></p>"
				    $('#container6').highcharts({
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
				            data: ${gradeRanks}
				        }]
				    });
				
				
				
				
			//分数段对比图
			var all_score=${subjectScoreMap.fullScore};
			var current_score=${userExamWork.score};
			var class_score=${subjectScoreMap.gradeScoreList};
			var fsd=[],fsd_number=[];
			var l=class_score.length;
			var you_score=Math.floor(current_score/10);
			if(all_score==current_score){
				you_score--
			}
			for(var i=0;i<all_score/10;i++){
				if(i==all_score/10-1){
					var inputValue = i*10+'-'+(i*10+10)+'段';
				}else{
					var inputValue = i*10+'-'+(i*10+9)+'段';
				}
				fsd.push(inputValue);
			}
			for(var i=0;i<all_score/10;i++){

				var num=0;
				if(i==all_score/10-1){
					$.each(class_score,function(index,value){
					if(value>=i*10&&value<=i*10+10){
						num++;
					}
				});
				}else{
					$.each(class_score,function(index,value){
					if(value>=i*10&&value<=i*10+9){
						num++;
					}
				});
				}
				if(i==you_score){
					num={y:num,color : "#36b5f6"}
				}
				fsd_number.push(num);
			}
			
			$('#container7').highcharts({
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
