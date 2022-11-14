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
.kwgl_main  table tbody tr.blue_1 td {
    border-top: 1px solid #f9d89f;
    border-bottom: 1px solid #f9d89f;
    background-color: #fdf4e5;
}
thead tr{
	background-color: #f8f9fb;
} 
</style>
</head>
<body >
<div class="kwgl">

	<div class="return_kw">
		<p>查看详情[年级统考]高一期末考试</p>
		<div class="njtk">
			<a href="javascript:void(0)" class="on"  id="2033">综合分析</a>
			<a href="javascript:void(0)" id="210njfx">单科分析</a>
			<a href="javascript:void(0)" id="212">年级趋势分析</a>
		</div>
		<a href="javascript:void(0)" class="btn btn-grey">返回</a>
	</div>
	
	<div class="pd20">
		<div class="kw_select">
			<p class="xnxq fl" style="margin:0">学年：2017-2018学年 学期：春季学期（下学期）</p>
			<p class="fr" style="color:#86939d">考试时间段：2018/07/18  9:00</p>
		</div>
		
		<div class="person_info">
			<p class="p1">姓名：<span class="color_orange">罗志明</span> 学号：<span class="color_orange">20151111</span> <span class="fr">发布时间：<span>2018/3/10-3/20</span></span></p>
			<p class="p2">总体情况</p>
			<p class="p3">罗志明同学，在本次考试中，总分为<span class="color_orange">599.00</span>分，属于高分段。本次全年级500人参与考试，在班中排名前<span class="color_orange">11</span>名，年级排名前<span  class="color_orange">110</span>名。</p>
		</div>
		
		<div class="kwgl_main">
			<p class="title" style="border-bottom: none;">得分概况</p>
				<table class="table table-striped" style="border:1px solid #e4e8eb;">
					<thead><tr><th>科目</th><th>得分</th><th>班内排名</th><th>进退步</th><th>年级排名</th><th>进退步</th></tr></thead>
					<tbody>
						<tr><td>全学科</td><td><span class="color_orange">599</span>分/900</td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_green">↑</span></td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_green">↑</span></td></tr>
						<tr><td>全学科</td><td><span class="color_orange">599</span>分/900</td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_red">↓</span></td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_red">↓</span></td></tr>
						<tr><td>全学科</td><td><span class="color_orange">599</span>分/900</td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_green">↑</span></td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_green">↑</span></td></tr>
						<tr><td>全学科</td><td><span class="color_orange">599</span>分/900</td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_red">↓</span></td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_red">↓</span></td></tr>
						<tr><td>全学科</td><td><span class="color_orange">599</span>分/900</td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_green">↑</span></td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_green">↑</span></td></tr>
						<tr><td>全学科</td><td><span class="color_orange">599</span>分/900</td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_red">↓</span></td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_red">↓</span></td></tr>
						<tr><td>全学科</td><td><span class="color_orange">599</span>分/900</td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_green">↑</span></td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_green">↑</span></td></tr>
						<tr><td>全学科</td><td><span class="color_orange">599</span>分/900</td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_green">↑</span></td><td>第<span class="color_orange">11</span>名/50</td><td>20<span class="f_green">↑</span></td></tr>
					</tbody>
				</table>
		</div>
		
		<div class="kwgl_main">
			<p class="title">各学科得分雷达图</p>
			<div id="container1" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
		<div class="kwgl_main">
			<p class="title">年级名次分布图</p>
			<div id="container2" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
		<div class="kwgl_main">
			<p class="title">总分对比图</p>
			<div id="container3" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
		<div class="kwgl_main">
			<p class="title">分数对比图</p>
			<div id="container4" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
		<div class="kwgl_main">
			<p class="title">总分分数段图</p>
			<div id="container5" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
	</div>
</div>
</body>
<script>
$('.njfx_km a').click(function(){
	$(this).addClass('choose');
	$(this).siblings().removeClass('choose');
});
/*各学科均分雷达图  */
 $('#container1').highcharts({
        chart: {
            polar: true,
            type: 'line'
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
            labels:{
                style:{
                    fontSize:20,
                    color:'#999999'
                },
                y:5
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
        plotOptions: {
            series: {
                lineWidth: 4,
                marker: {
                    enabled:  false //取消点
                }
            }
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
            align: 'right',
            verticalAlign: 'top',
            symbolWidth: 25,
            itemStyle : {
                'fontSize' : '20px',
                'color':'#666666'
            }
        },
        series: [{
            name: '个人得分',
            data: [43, 19, 60, 35, 17, 10,20],
            color:'#59bdf7'
        },{
            name: '班级评分',
            data: [53, 39, 40, 25, 12, 30,10],
            color:'#fe778c'
        },{
            name: '年级均分',
            data: [88, 55, 66, 44, 33, 30,30],
            color:'#59d254'
        }]
    });
 /* container2 */   
 var info = "<p class='bjncts'><span>网五五五</span> | <span>总分:1000</span> | <span>排名:4000</span></p>"
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

 /* container3 */
 var color1= ['#06aaf5', '#ff6b88', '#1dc544', '#f6ac00','#85a4b3'] ;
    var color2= ['#8ecaf8', '#fca499', '#7bd95c', '#fdcf00','#9cbac7'] ;
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
    $('#container3').highcharts({
        chart: {
            type: 'bar',
            marginRight:40
        },
        title: {
            text: null
        },
        xAxis: {
            title: {
                text: '总分',
                align: 'high',
                rotation:0,
                x:35,
                y:-10,
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
            labels:{
                style:{
                    fontSize:18,
                    color:'#999999'
                },
            },
            gridLineDashStyle:'Dash',
            gridLineColor: '#d6d6d6'
        },
        legend: {
            verticalAlign: 'top',
            align:'right',
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
            formatter: function () {
                var s = ' ';
                $.each(this.points, function (i) {
                    s += '' +'<span style="font-size:20px;color:'+Highcharts.getOptions().colors[i].stops[0][1]+'" >'+ this.series.name + ': ' + this.y + '分'+'</span><br/>';
                });
                return s;
            },
            useHTML: true
            // enabled:false
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
            data: [599.5]
        }, {
            name: '班级均分',
            data: [540.2]
        }, {
            name: '年级均分',
            data: [520]
        }, {
            name: '目标分',
            data: [675]
        }, {
            name: '年级最高分',
            data: [675]
        }]
    });

   /*container4  */
	var km_num = 12;
	$('#container4').highcharts({
    chart: {
    	 panning: true,
        type: 'column'
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
           '生物',
            '生物1',
            '生物2',
            '生物3',
            '生物4',
            '生物5',
            '生物6'
        ],
        labels:{
            style:{
                fontSize:18,
                color:'#999999'
            },
            y:30
        },
        max:2.5,
        crosshair: true,
       /* minRange: 2.5*/
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
        verticalAlign: 'top',
        align:'right',
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
        data: [49.9, 71.5, 106.4, 129.2, 144.0, 176.0,85,99,100,150,120]
    }, {
        name: '班级评分',
        color:'rgb(255,106,136)',
        data: [83.6, 78.8, 98.5, 93.4, 106.0, 84.5,99,120,133,120,55]
    }, {
        name: '年级均分',
        color:'rgb(60,211,173)',
        data: [48.9, 38.8, 39.3, 41.4, 0, 0,55,36,42,50,63]
    }, {
        name: '年级最高分',
        color:'rgb(139,170,185)',
        data: [42.4, 33.2, 34.5, 39.7, 0,0,25,36,78,96,52]
    }]
});
	
 
 /*container5  */
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
 $('#container5').highcharts({
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