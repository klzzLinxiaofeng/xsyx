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
	<div class="pd20">
		<div class="kw_select">
			<p class="xnxq fl" style="margin:0">学年：2017-2018学年 学期：春季学期（下学期）</p>
			<p class="fr" style="color:#86939d">考试时间段：2018/07/18  9:00</p>
		</div>
		<div class="nj_basis_info">
			<p class="title">年级基础信息</p>
			<div class="ks_bj">
				<div class="ks_bj_list">
					<p>应试班级:</p>
					<div class="list">
						<span class="dr_ok">高一（1）班</span>
					</div>
				</div>
				<p class="ks_bj_info">
					<span><i class="dr_ok"></i>成绩导入完成</span>
					<span><i class="dr_bfok"></i>成绩部分导入</span>
					<span><i class="dr_no"></i>成绩未导入</span>
				</p>
			</div>
			<div class="color_kuai">
				<div class="ck1">
					<p class="ck_num"><span>500</span>/<span>50000</span></p>
					<p class="ck_info">应试人数</p>
				</div>
				<div class="ck2">
					<p class="ck_num"><span>50000</span>/<span>500</span></p>
					<p class="ck_info">班级各学科均分</p>
				</div>
				<div class="ck3">
					<p class="ck_num"><span>500</span>/<span>500</span></p>
					<p class="ck_info">年级总分最高分</p>
				</div>
				<div class="ck4">
					<p class="ck_num"><span>500</span>/<span>500</span></p>
					<p class="ck_info">年级总分最低分</p>
				</div>
			</div>
		</div>
		
		<div class="kwgl_main">
			<p class="title">班级各学科均分雷达图</p>
			<div id="container1" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
		<div class="kwgl_main">
			<p class="title">各班级总分平均分排名</p>
			<div id="container2" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
		<div class="kwgl_main">
			<p class="title">班级总分名次分布图</p>
			<div id="container3" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
		<div class="kwgl_main">
			<p class="title">班级总分分数段图</p>
			<div id="container4" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
	</div>
</div>
</body>
<script>
/*年级各学科均分雷达图  */
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
            name: '年级各学科均分',
            data: [43, 19, 60, 35, 17, 10,20],
            color:'#59bdf7'
        },{
            name: '班级各学科均分',
            data: [53, 39, 40, 25, 12, 30,10],
            color:'#fe778c'
        }]
    });
 /* container2 */   
 var colors = ['#06aaf5'];
 Highcharts.getOptions().colors = Highcharts.map(colors, function (color) {
     return {
         radialGradient: { cx:0, cy: 2.2,r:5.3 },
         stops: [[0, color], [2, Highcharts.Color(color).brighten(14).get('rgb')] // darken 
                ]
     };
 });
 $('#container2').highcharts({
     chart: {
         type: 'bar',
         marginRight:40
     },
     title: {
         text: null
     },
     xAxis: {
         categories: ['高一（1）班', '高一（2）班', '高一（3）班', '高一（4）班', '高一（5）班','高一（6）班','高一（7）班'],
         title: {
             text: null,
         },
         labels: {
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
         title: {
             text: null,
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
         pointFormat: '<span style="font-size:20px;color:{series.color}" >{point.y}人<br/>',
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
                 }
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
         data: [260,446,620,680,654,580,730]
     }]
 });
 /* container3 */
 $('#container3').highcharts({
    chart: {
        type: 'scatter',
        marginTop:55,
    },
    title: {
        text:null
    },
    legend: {
        enabled: false
    },
    xAxis: {
        min:0,
        allowDecimals: false,
        categories: ['名字一', '网五五五', '名字三', '名字四', '名字五'],
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
        allowDecimals: false,
        gridLineDashStyle:'Dash',
        gridLineColor: '#d6d6d6',
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
            value:15,
            label: {
                align: 'left',
                style: {
                    fontStyle: 'normal',
                    color:'#fd9494',
                    fontSize:20
                },
                text: '平均分名次线：15',
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
        shared: true,
        useHTML: true,
        formatter: function () {
            return '<small style="font-size:18px">' + this.x + '</small><br/>' +
                '<small style="font-size:18px">总分：'+this.point.fs+'</small><br/>'+
                '<small style="font-size:18px"> 排名：'+this.y+'</small>';
        },
    },
    plotOptions: {
        scatter: {
            marker: {
                radius: 8 //变大
            },
            series:{
                borderRadius:7,
            }
        }},
    series: [{
        color: 'rgba(3, 169, 245,0.8)',
        data: [{fs:80,y:20},
               {fs:100,y:11},
               {fs:220,y:4},
               {fs:260,y:3},
               {fs:380,y:1}
              ]
    }]
});


 
 /*container4  */
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
     /* if(i==you_score){
         num={y:num,color : "#fe8890"}
     } */
     fsd_number.push(num);
 }
 $('#container4').highcharts({
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
             text: '处于该分数段的人数',
             style: {
                 fontSize:16,
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