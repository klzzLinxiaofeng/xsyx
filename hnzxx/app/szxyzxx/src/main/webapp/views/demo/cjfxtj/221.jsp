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
		<div class="nj_basis_info">
			<p class="title">班级单科基础信息</p>
			<div class="ks_bj">
				<div class="ks_bj_list">
					<p>应试班级:</p>
					<div class="list">
						<span class="dr_ok">高一（1）班</span>
						<span class="dr_bfok">高一（2）班</span>
						<span class="dr_no">高一（3）班</span>
						<span class="dr_ok">高一（1）班</span>
						<span class="dr_bfok">高一（2）班</span>
						<span class="dr_no">高一（3）班</span>
						<span class="dr_ok">高一（1）班</span>
						<span class="dr_bfok">高一（2）班</span>
						<span class="dr_no">高一（3）班</span>
						<span class="dr_ok">高一（1）班</span>
						<span class="dr_bfok">高一（2）班</span>
						<span class="dr_no">高一（3）班</span>
						<span class="dr_ok">高一（1）班</span>
						<span class="dr_bfok">高一（2）班</span>
						<span class="dr_no">高一（3）班</span>
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
					<p class="ck_info">班级单科均分</p>
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
			<p class="title">班级三率分布</p>
			<div id="container5" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
			<div class="container5_legend">
				<p style="color:#2af598">优秀：<span>90-100</span>分</p>
				<p style="color:#03a9f5">良好：<span>70-89</span>分</p>
				<p style="color:#f7af07">及格：<span>60-69</span>分</p>
				<p style="color:#ff6a88">不及格：<span>0-59</span>分</p>
			</div>
			<div class="clear"></div>
		</div>
		<div class="kwgl_main">
			<p class="title">年级三率综合堆积对比图</p>
			<div id="container6" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
		<div class="kwgl_main">
			<p class="title">年级各班级单科平均分排名</p>
			<div id="container7" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
		<div class="kwgl_main">
			<p class="title">班级单科名次分布图</p>
			<div id="container8" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
		<div class="kwgl_main">
			<p class="title">班级单科分数段图</p>
			<div id="container9" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
		</div>
	</div>
</div>
</body>
<script>
$(function(){
	$('.njfx_km a').click(function(){
		$(this).addClass('choose');
		$(this).siblings().removeClass('choose');
	});
	
	
	/*container5  */
	var chart = null;
	var color3= ['#ff6a88','#f7af07','#03a9f5','#16b2eb'] ;
	var color4= ['#fca599','#ffe366' ,'#90caf8','#2af598'] ;
	// Radialize the colors
	Highcharts.getOptions().colors = Highcharts.map(color3, function (color) {
	    var v=color;
	    var j=0;
	    var color_end='#fff';
	    for(var i=0;i<color3.length;i++){
	        if(color3[i]==v){
	            j=i;
	            color_end=color4[j]
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
	$('#container5').highcharts({
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false,
	        spacing : [100, 0 , 40, 0]
	    },
	    title: {
	        floating:true,
	        text: null
	    },
	    tooltip: {
	        headerFormat: '<small style="font-size:20px;">{point.key}</small><br/>',
	        pointFormat: '<small style="font-size:20px;">{point.y}人</small><b  style="font-size:20px;">{point.percentage:.1f}%</b>',
	        useHTML: true,
	    },
	    exporting:{
	        enabled:false
	    },
	    credits: {
	        enabled: false
	    },
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            connectorColor:'red',
	            cursor: 'pointer',
	            dataLabels: {
	                enabled: true,
	                formatter: function() {
	                    if(this.point.name == '不及格率'){
	                        return '<b style="color:#ff6a88">'+ this.point.name +' <br/><span style="color:#ff6a88">('+this.point.y+'人'+ Highcharts.numberFormat(this.percentage, 2)+'%)</span></b>';
	                    }else if(this.point.name == '及格率'){
	                        return '<b style="color:#f7af07">'+ this.point.name +' <br/><span style="color:#f7af07">('+this.point.y+'人'+ Highcharts.numberFormat(this.percentage, 2)+'%)</span></b>';
	                    }else if(this.point.name == '良好率'){
	                        return '<b style="color:#03a9f5">'+ this.point.name +' <br/><span style="color:#03a9f5">('+this.point.y+'人'+ Highcharts.numberFormat(this.percentage, 2)+'%)</span></b>';
	                    }else if(this.point.name == '优秀率'){
	                        return '<b style="color:#2af598">'+ this.point.name +' <br/><span style="color:#2af598">('+this.point.y+'人'+ Highcharts.numberFormat(this.percentage, 2)+'%)</span></b>';
	                    } 
	                },
	                style: {
	                    fontWeight: 'bold',
	                    fontSize:'18px',
	                    fontFamily:"微软雅黑",
	                    textShadow: '0px 0px 0px #fff'
	                }
	            }
	        }
	    },
	    series: [{
	        type: 'pie',
	        innerSize: '65%',
	        data: [
	            ['不及格率', 4],
	            ['及格率', 15],
	            ['良好率',26],
	            ['优秀率',5]
	        ]
	    }]
	});

	
	/*container6  */
	  var color1= ['#ff6b89', '#f7ae00', '#07aaf5', '#1dc544'] ;
	    var color2= ['#fca299', '#fcce00', '#88c9f8', '#4fd051'] ;
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
	    $('#container6').highcharts({
	        chart: {
	            type: 'bar',
	            spacing : [50, 0 , 0, 0]
	        },
	        title: {
	            text: null
	        },
	        xAxis: {
	            categories: ['高一（1）班', '高一（2）班', '高一（3）班', '高一（4）班', '高一（5）班'],
	            gridLineDashStyle:'Dash',
	            labels:{
	                style:{
	                    fontSize:16,
	                    color:'#666666'
	                }
	            },
	        },
	        yAxis: {
	            min: 0,
	            gridLineColor: '#999999',
	            gridLineWidth: 1,
	            gridLineDashStyle:'Dash',
	            title: {
	                text: null
	            },
	            labels:{
	                style:{
	                    fontSize:16,
	                    color:'#666666'
	                }
	            },
	        },
	        exporting:{
	            enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
	        },
	        credits: {
	            enabled:false
	        },
	        tooltip: {
	            headerFormat: '<small style="font-size:20px">{point.key}</small><br>',
	            pointFormat: '<span style="font-size:20px;color:{series.color}">{series.name}: <b style="font-size:20px">{point.y}</b><br/>',
	            useHTML: true,
	        },
	        legend: {
	            reversed: true,
	            verticalAlign: 'top',
	            align:'right',
	            symbolHeight: 16,
	            symbolWidth: 16,
	            symbolRadius: 0,
	            itemStyle : {
	                'fontSize' : '18px',
	                'color':'#666666'
	            }
	        },
	        plotOptions: {
	            series: {
	                stacking: 'normal',
	                dataLabels: {
	                    enabled: true,          // 开启数据标签
	                    //color:'gray',
	                    align: 'left',
	                    y:3,
	                     style:{
	                        fontSize:'16',
	                        color:'#ffffff',
	                        useHTML: true
	                    }
	                },
	            }
	        },
	        series: [   {
	            name: '不及格率',
	            data: [35, 48, 44, 52, 53]
	        },{
	            name: '及格率',
	            data: [33, 40, 45, 32, 35]
	        },{
	            name: '良好率',
	            data: [20, 52, 43, 52, 41]
	        },{
	            name: '优秀率',
	            data: [55, 33, 44, 37, 52]
	        }]
	    });
	    
	    
	    /* container7 */   
	    var colors = ['#06aaf5'];
	    Highcharts.getOptions().colors = Highcharts.map(colors, function (color) {
	        return {
	            radialGradient: { cx:0, cy: 2.2,r:5.3 },
	            stops: [[0, color], [2, Highcharts.Color(color).brighten(14).get('rgb')] // darken 
	                   ]
	        };
	    });
	    $('#container7').highcharts({
	        chart: {
	            type: 'bar',
	            marginRight:40,
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
	    /* container8 */
	    $('#container8').highcharts({
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


	    
	    /*container9  */
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
	    $('#container9').highcharts({
	        chart: {
	            type: 'bar',
	            marginTop:40,
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
})
</script>
</html>