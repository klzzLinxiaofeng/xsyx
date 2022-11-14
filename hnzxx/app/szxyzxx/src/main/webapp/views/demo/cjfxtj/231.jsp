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
<style>
.f_red,.f_green{margin:0 10px;}
.table th, .table td{text-align: center;}
.table-striped tbody > tr:nth-child(even) > td, .table-striped tbody > tr:nth-child(even) > th {
    background-color: #fffcf8;
}
.table-striped tbody > tr:nth-child(odd) > td, .table-striped tbody > tr:nth-child(odd) > th {
    background-color: #ffffff;
}
.table tbody tr.blue_1 td {
    border-top: 1px solid #f9d89f;
    border-bottom: 1px solid #f9d89f;
    background-color: #fdf4e5;
}
thead tr{
	background-color: #f8f9fb;
} 
.njtk a:nth-child(1).on, .njtk a:nth-child(2).on {
    background: #f0f0f0;
    border-bottom: 1px solid #f0f0f0;
}
</style>
</head>
<body >
<div class="kwgl">

	<div class="return_kw">
		<p>查看详情[年级统考]高一期末考试</p>
		<div class="njtk">
			<a href="javascript:void(0)" >综合分析</a>
			<a href="javascript:void(0)" class="on">单科分析</a>
			<a href="javascript:void(0)" >趋势分析</a>
		</div>
		<a href="javascript:void(0)" class="btn btn-grey">返回</a>
	</div>
	
	<div class="njfx_km pd20">
		<a href="javascript:void(0)" class="choose">语文</a>
		<a href="javascript:void(0)">语文</a>
		<a href="javascript:void(0)">思想政治</a>
		<a href="javascript:void(0)">毛泽东思想与邓小平理论</a>
	</div>
	
	<div class="pd20">
		<div class="kw_select">
			<p class="xnxq fl" style="margin:0">学年：2017-2018学年 学期：春季学期（下学期）</p>
			<p class="fr" style="color:#86939d">考试时间段：2018/07/18  9:00</p>
		</div>
		
		<div class="person_info" style="padding-bottom: 50px;">
			<p class="p1">姓名：<span class="color_orange">罗志明</span> 学号：<span class="color_orange">20151111</span> <span class="fr">发布时间：<span>2018/3/10-3/20</span></span></p>
			<p class="p2">得分概况</p>
				<table class="table table-striped" style="border:1px solid #e4e8eb;">
					<thead><tr><th>语文</th><th>本班</th><th>年级</th></tr></thead>
					<tbody>
						<tr><td>你的分数</td><td>900</td><td>50</td></tr>
						<tr><td>你的名次</td><td>900</td><td>50</td></tr>
						<tr><td>均分</td><td>900</td><td>50</td></tr>
						<tr><td>均分名次</td><td>900</td><td>50</td></tr>
					</tbody>
				</table>
		</div>
		
		<div class="kwgl_main">
			<p class="title">年级名次分布图</p>
			<div id="container1" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
		<div class="kwgl_main">
			<p class="title">分数段图</p>
			<div id="container2" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
	</div>
</div>
</body>
<script>
$('.njtk a').click(function(){
	$(this).addClass('on');
	$(this).siblings().removeClass('on');
});
$('.njfx_km a').click(function(){
	$(this).addClass('choose');
	$(this).siblings().removeClass('choose');
});


 /* container1 */   
 var info = "<p class='bjncts'><span>网五五五</span> | <span>总分:1000</span> | <span>排名:4000</span></p>"
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
            value:40,
            label: {
                align: 'left',
                style: {
                    fontStyle: 'normal',
                    color:'#fd9494',
                    fontSize:20
                },
                text: '平均分名次线：40',
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
        data: [54,50,48,47,43,41,39,30,28,25,24,20,16,12,10,9,5,3,2,{
            marker: {
                radius: 8, //变大
                states:{
                    hover:{
                       fillColor:'#f18441',
                        lineColor:'#f18441'
                        }
                    }
            },
            y:1,
            color:'#f18441'
        }]
    }]
});

 
 /*container2  */
 var all_score=400;
 var current_score=378;
 var class_score=[15,25,26,27,28,29,15,25,26,27,28,29,35,38,59,62,78,90,324,377,378];
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
         var inputValue = i*d_number+'-'+(i*d_number+d_number-1)+'段';
     }
     fsd.push(inputValue);
 }
 for(var i=0;i<15;i++){
     var num=0;
     if(i==14){
         $.each(class_score,function(index,value){
             if(value>=14*d_number&&value<=all_score){
                 num++;
             }
         });
     }else{
         $.each(class_score,function(index,value){
             if(value>=i*d_number&&value<=i*d_number+d_number-1){
                 num++;
             }
         });
     }
      if(i==you_score){
         num={y:num,color : "#fe8890"}
     } 
     fsd_number.push(num);
 }
 $('#container2').highcharts({
     chart: {
         type: 'bar',
         marginTop:40
     },
     title: {
         text: null
     },
     xAxis: {
         categories: fsd,
         title: {
             text: '分数段',
             align: 'high',
             rotation:0,
             x:100,
             y:-10,
             style:{
                 fontSize:16,
                 color:'#999999'
             }
         },
         labels: {
             y:5,
             style: {
                 fontSize:16,
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
             text: '你处在红色分数段',
             style:{
                 fontSize:18,
                 color:'#999999'
             }
         },
         labels: {
             style: {
                 fontSize:16,
             }
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
         headerFormat: '<small style="font-size:20px;">{point.key}</small><br/>',
         pointFormat: '<span style="font-size:20px;" >{point.y}人<br/>',
         shared: true,
         useHTML: true,
     },
     plotOptions: {
         bar: {
             dataLabels: {
                 enabled: true,
                 allowOverlap: true,
                 color:'gray',
                 style: {
                     fontWeight: 'bold',
                     fontSize: '14px',
                 },
                 formatter: function() {
                     if (this.y > 0)
                         return this.y ; //这里进行判断
                 },
             },
         },
         series:{
             borderRadius:7,
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
         color:'#73c3f7',
         data: fsd_number
     }]
 });
</script>
</html>