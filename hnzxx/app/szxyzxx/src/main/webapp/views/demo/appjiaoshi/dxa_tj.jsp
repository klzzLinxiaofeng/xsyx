<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导学案统计</title>
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="pragram" content="no-cache">
<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">
<link href="${pageContext.request.contextPath}/res/css/statistics/questions_app.css" rel="stylesheet">
<style>
.title-content{
        margin: 0 10px;
    border: 1px #89c6ec solid;
}
</style>
</head>
<body>
<div class="title-count">
	<div class="topic_native">
		<span>
	        <a href="javascript:void(0);" class="statistics">答题统计</a>
	        <a href="javascript:void(0);">单题分析</a>
        </span>
    </div>
    <div class="title-content" style="display: none;">
    	<div class="head">[预习自测] <span class="dfl">得分率</span>对比图</div>
        <div class="tj"></div>
        <div class="head">[课后检验] <span class="dfl">得分率</span>对比图</div>
        <div class="tj"></div>
        <div class="rank">
        	<div class="head">[预习自测] 题目应答情况统计</div>
        	<table>
        		<thead>
        			<tr><th>名次</th><th>班级</th><th>作答人数</th><th>正答数</th><th class="dfl">得分率</th><th>完成率</th></tr>
        		</thead>
        		<tbody>
        			<tr>
        				<td>1</td><td>选择题</td><td>40</td><td>30</td><td>75%</td><td>100%</td>
        			</tr>
        			<tr>
        				<td>1</td><td>选择题</td><td>40</td><td>30</td><td>75%</td><td>100%</td>
        			</tr>
        			<tr>
        				<td>1</td><td>选择题</td><td>40</td><td>30</td><td>75%</td><td>100%</td>
        			</tr>
        			<tr>
        				<td>1</td><td>选择题</td><td>40</td><td>30</td><td>75%</td><td>100%</td>
        			</tr>
        			<tr>
        				<td>1</td><td>选择题</td><td>40</td><td>30</td><td>75%</td><td>100%</td>
        			</tr>
        		</tbody>
        	</table>
        	<div class="head">[课后检验] 题目应答情况统计</div>
        	<table>
        		<thead>
        			<tr><th>名次</th><th>班级</th><th>作答人数</th><th>正答数</th><th class="dfl">得分率</th><th>完成率</th></tr>
        		</thead>
        		<tbody>
        			<tr>
        				<td>1</td><td>选择题</td><td>40</td><td>30</td><td>75%</td><td>100%</td>
        			</tr>
        			<tr>
        				<td>1</td><td>选择题</td><td>40</td><td>30</td><td>75%</td><td>100%</td>
        			</tr>
        			<tr>
        				<td>1</td><td>选择题</td><td>40</td><td>30</td><td>75%</td><td>100%</td>
        			</tr>
        			<tr>
        				<td>1</td><td>选择题</td><td>40</td><td>30</td><td>75%</td><td>100%</td>
        			</tr>
        			<tr>
        				<td>1</td><td>选择题</td><td>40</td><td>30</td><td>75%</td><td>100%</td>
        			</tr>
        		</tbody>
        	</table>
        </div>
    </div>
  <!--  <div class="title-content" style="display:none">
   		<div class="tj"></div>
   </div> -->
   <div class="title-content" style="display:none">
   		<div class="dxa_th">
   		<div class="title">
   			—— [预习自测] —— 
   		</div>
   			<ul>
   				<li><a href="javascript:void(0)">1</a></li>
   				<li><a href="javascript:void(0)">2</a></li>
   				<li class="on"><a href="javascript:void(0)">3</a></li>
   				<li><a href="javascript:void(0)">4</a></li>
   				<li><a href="javascript:void(0)">5</a></li>
   				<li><a href="javascript:void(0)">6</a></li>
   				<li><a href="javascript:void(0)">7</a></li>
   				<li><a href="javascript:void(0)">8</a></li>
   				<li><a href="javascript:void(0)">9</a></li>
   				<li><a href="javascript:void(0)">10</a></li>
   				<li><a href="javascript:void(0)">11</a></li>
   				<li><a href="javascript:void(0)">12</a></li>
   				<li><a href="javascript:void(0)">13</a></li>
   				<li><a href="javascript:void(0)">14</a></li>
   				<li><a href="javascript:void(0)">15</a></li>
   				<li><a href="javascript:void(0)">16</a></li>
   			</ul>
   			<div class="clear"></div>
   		</div>
   		<div class="dxa_th">
   		<div class="title">
   			—— [课后检验] —— 
   		</div>
   			<ul>
   				<li><a href="javascript:void(0)">1</a></li>
   				<li><a href="javascript:void(0)">2</a></li>
   				<li><a href="javascript:void(0)">3</a></li>
   				<li><a href="javascript:void(0)">4</a></li>
   				<li><a href="javascript:void(0)">5</a></li>
   				<li><a href="javascript:void(0)">6</a></li>
   				<li><a href="javascript:void(0)">7</a></li>
   				<li><a href="javascript:void(0)">8</a></li>
   				<li><a href="javascript:void(0)">9</a></li>
   				<li><a href="javascript:void(0)">10</a></li>
   				<li><a href="javascript:void(0)">11</a></li>
   				<li><a href="javascript:void(0)">12</a></li>
   				<li><a href="javascript:void(0)">13</a></li>
   				<li><a href="javascript:void(0)">14</a></li>
   				<li><a href="javascript:void(0)">15</a></li>
   				<li><a href="javascript:void(0)">16</a></li>
   			</ul>
   			<div class="clear"></div>
   		</div>
   			<div class="ti_xq" >
   				<div class="dxa_th">
   					<div class="title">[预习自测]  第7题</div>
   				</div>
		        <div id="container2" ></div>
		        <div id="container1"></div>
		        <div class="subject_1">
		        	<p>题目内容</p>
		        	<div class="nr">李白在《蜀道难》一诗中，写出了剑阁地势险要，易守难攻的特点的句子是()</div>
		        </div>
		        <div class="subject_2">
		        	<ul>
		        		<li><div>A.选项A</div></li>
		        		<li><div>B.选项B</div></li>
		        		<li class="on"><div>C.选项C</div></li>
		        		<li><div>D.选项DD.选项DD.选项DD.选项DD.选项DD.选项DD.选项DD.选项DD.选项DD.选项DD.选项DD.选项D</div></li>
		        	</ul>
		        </div>
		        <div class="subject_3">
		        	<p>详细解析</p>
		        	<div class="nr">如《蜀道难》开篇就是凭空发出的连声叹，“噫吁嚱，危乎高哉！”《将进酒》开篇便是“君不见黄河之水天上来，奔流到海不复回。
		        	君不见高堂明镜悲白发，朝如青丝暮成雪”。两组长句，以反问语气，凭空而起，上下对仗，仿佛诗人从天外而来，把那黄河之水写得来有激昂，去有</div>
		        </div>
        	</div>
   </div>
</div>
<div class="re_statistics" style="display:none">
	<p class="title">重新统计需要等待较长时间</p>
	<p class="stati_tis">统计后如果有数据改动（如学生提交答卷、重新打分等）才进行重新统计。是否继续？</p>
	<div class="cz_btn" style="padding-bottom:0">
		<button class="btn btn-blue" onclick="generate('${param.examId}','${param.paperId}','${param.taskId}','${status}');">是的，我要重新统计</button>
		<button class="btn btn-green" onclick="layer.closeAll();">不了，返回统计页面</button>
	</div>
</div>
<div class="success_statistics" style="display:none">
	<p class="title">生成统计成功</p>
	<div class="cz_btn">
		<button class="btn btn-blue" onclick="window.location.reload();">确定</button>
	</div>
</div>
<div class="ts_statistics" style="display:none">
	<p class="title">提示</p>
	<p class="stati_tis">统计在五分钟之内只能执行一次，请稍后再试。</p>
	<div class="cz_btn">
		<button class="btn btn-blue">确定</button>
	</div>
</div>
<div class="statisticsing" style="display:none;">
	<p class="title">生成统计中</p>
	<p class="stati_tis">请稍等...</p>
	<div class="jdt_tu"><p style="width:0%"></p><span>0%</span></div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/statistics/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/statistics/highcharts.js"></script>
<!-- 窗体控件 -->
<%@include file="/views/embedded/plugin/layer.jsp" %>
<script type="text/javascript">
$(function(){
    $(".title-content").hide();//隐藏wenben
    $(".title-content:eq(0)").show();//显示第一个wenben
    $(".topic_native span a").click(function(){
    	/* if(!$('.title-content').eq(4).is(":hidden")){
   	 	  alert("正在统计请稍后");
   	 	  return  false;
    	  } */
        $(".topic_native a").removeClass("statistics");//移除样式
        $(this).addClass("statistics");//添加样式
        var i=$(this).index();//获得下标
        $(".title-content").hide();//隐藏wenben
        $(".title-content:eq("+i+")").show();//显示第i个wenben
    });
    /*进度条*/
    var i=0,w=0;
    var timer;
    var speed=100;
    function go(){
    w=(i+1)+'%';
    $(".jdt_tu p").css("width",w); 
    $(".jdt_tu span").text(w); 
    if(w == "99%"){ 
        speed=9999999;
        window.clearInterval(timer); //进度为100时清除定时器
    } 
    if(w == "100%"){ 
        window.clearInterval(timer); //进度为100时清除定时器
    } 
    i=i+1;
	} 
	function zhixing(){
	timer = window.setInterval(go,speed); //设置定时器
	}
	window.onload = function(){ 
	    zhixing(); 
	} 
	//弹出层
	layer.open({
		  type: 1,
		  title: false,
		  area: ['220px', ''],
		  closeBtn: 0,
		  shadeClose: true,
		  skin: 'yourclass',
		  content: $(".re_statistics")
		});
});

$(function () {
    $('#container3').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: ''
        },
        credits:{
        	enabled:false // 禁用版权信息
        },
       
        exporting: {
            enabled:false//关闭设置按钮
        },
        xAxis: {
            categories: [
                ''
            ],
            labels:{
                style:{
                    color:"#666",
                    fontFamily:"微软雅黑",
                    fontSize:"16px",
                },
            },
            crosshair: true
        }, 
        yAxis: {
            min: 0,
            title: {
                text: '班级平均分'
            },
            labels:{
                style:{
                    color:"#666",
                },
            },
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table class="table-ts">',
            pointFormat: '<tr><td style="color:{series.color};padding:0;">{series.name}: </td>' +
            '<td style="padding:0;"><b>{point.y:.1f} 分</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true,
            enabled:false
        },
        plotOptions: {
            column: {
                pointPadding: 0.05,
                borderWidth: 0,
                groupPadding: 0.001,  
                dataLabels:{         //显示顶部数值
                            enabled:true, // dataLabels设为true
                            y:-20,
                            style:{
                                color:'#333',
                                fontSize:'12px'
                            }
                        }
            }
        },
        series: [{
            name: '全年级',
            data: [77.11],
            visible:true
        },{
            name: '一班',
            data: [77.12],
            visible:true
        },{
            name: '二班',
            data: [71.12],
			visible:true
        },{
            name: '三班',
            data: [74.52],
            visible:false
        },{
            name: '四班',
            data: [67.11],
            visible:false
        },{
            name: '五班',
            data: [82.22],
            visible:false
        },{
            name: '六班',
            data: [69.54],
            visible:false
        },{
            name: '七班',
            data: [75.12],
            visible:false
        },{
            name: '一班',
            data: [77.12],
            visible:false
        },{
            name: '二班',
            data: [71.12],
			visible:false
        },{
            name: '三班',
            data: [74.11],
            visible:false
        },{
            name: '四班',
            data: [67.12],
            visible:false
        },{
            name: '五班',
            data: [82.12],
            visible:false
        },{
            name: '六班',
            data: [69.22],
            visible:false
        },{
            name: '七班',
            data: [75.12],
            visible:false
        },{
            name: '一班',
            data: [77.21],
            visible:false
        },{
            name: '二班',
            data: [71.21],
			visible:false
        },{
            name: '三班',
            data: [74.51],
            visible:false
        },{
            name: '四班',
            data: [67.21],
            visible:false
        },{
            name: '五班',
            data: [82.32],
            visible:false
        },{
            name: '六班',
            data: [69.21],
            visible:false
        },{
            name: '七班',
            data: [75.21],
            visible:false
        },{
            name: '一班',
            data: [77.21],
            visible:false
        },{
            name: '二班',
            data: [71.25],
			visible:false
        },{
            name: '三班',
            data: [74.51],
            visible:false
        },{
            name: '四班',
            data: [67.25],
            visible:false
        },{
            name: '五班',
            data: [82.21],
            visible:false
        },{
            name: '六班',
            data: [69.21],
            visible:false
        },{
            name: '七班',
            data: [75.21],
            visible:true
        }]
    });
});
$('#container1').highcharts({
    chart: {
        type: 'column'
    },
    title: {
        text: '答题项应答次数分布图',
        style:{
	        color:"#0d7bd5",
	        fontSize: '16px',
	        fontFamily:"MicrosoftYaHeiUI",
	        fontWeight:"bold"
	    }
    },
    credits:{
    enabled:false // 禁用版权信息
    },
    legend: {
        enabled: false// 关闭图例
    },
    exporting: {
        enabled:false//关闭设置按钮
    },
    xAxis: {
        categories: [
            '年级',
            '1班',
            '2班',
            '3班',
            '4班',
            '5班',
            '6班',
            '7班',
            '8班',
            '年级',
            '1班',
            '2班',
            '3班',
            '4班',
            '5班',
            '6班',
            '7班',
            '8班',
        ],
        tickWidth:false,
        lineColor:"#ccc",
        lineWidth:1,
        labels:{
            style:{
                color:"#666",
                fontFamily:"微软雅黑",
                fontSize:"16px",
            },
        },
    },
    yAxis: {
        gridLineColor: '#ccc',
        gridLineWidth: 1,
        min: 0,
        title: {
            text: ''
        },
        labels:{
             formatter: function () {  
                        return this.value + '%';//y轴加上%  
                    },
            style:{
                color:"#666",
            },
        },
        // gridLineColor: false,
    },
    tooltip: {
        headerFormat: '<span style="font-size:10px">{point.key}</span><table style="border:none;">',
        pointFormat: '<tr>' +
            '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
        footerFormat: '</table>',
        shared: true,
        useHTML: true,
    },
    plotOptions: {
        column: {
            pointPadding: 0.2,
            borderWidth: 0,
            dataLabels:{         //显示顶部数值
                        enabled:true, // dataLabels设为true
                        formatter : function() {
                            return this.y + "%";  //返回百分比
                        },
                        style:{
                            color:'#333',
                            fontSize:'12px'
                        }
                    }
        }
    },
    series: [{
        name: '',
            data: [
            {
                y:50,
                color:"#0d7bd5"
            },
            {
                y:31,
                color:"#0d7bd5"
            },
            {
                y:60,
                color:"#0d7bd5"
            },
            {
                y:41,
                color:"#0d7bd5"
            },
            {
                y:18,
                color:"#0d7bd5"
            },
            {
                y:75,
                color:"#0d7bd5"
            },
            {
                y:23,
                color:"#0d7bd5"
            },
            {
                y:19,
                color:"#0d7bd5"
            },
            {
                y:63,
                color:"#0d7bd5"
            },{
                y:50,
                color:"#0d7bd5"
            },
            {
                y:31,
                color:"#0d7bd5"
            },
            {
                y:60,
                color:"#0d7bd5"
            },
            {
                y:41,
                color:"#0d7bd5"
            },
            {
                y:18,
                color:"#0d7bd5"
            },
            {
                y:75,
                color:"#0d7bd5"
            },
            {
                y:23,
                color:"#0d7bd5"
            },
            {
                y:19,
                color:"#0d7bd5"
            },
            {
                y:63,
                color:"#0d7bd5"
            }
            ]
    }]
});

$(function () {
$('#container2').highcharts({
    chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
    },
    title: {
        text: '答题项应答次数分布图',
	    style:{
	        color:"#0d7bd5",
	        fontSize: '16px',
	        fontFamily:"MicrosoftYaHeiUI",
	        fontWeight:"bold"
	    }
    },
    credits:{
 enabled:false // 禁用版权信息
    },
    exporting: {
        enabled:false//关闭设置按钮
    },
    tooltip: {
        formatter: function() {
        return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage, 1) +'% ('+
                     Highcharts.numberFormat(this.y, 0, ',') +' 人)';
     }
        //pointFormat: '<b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: true,
                color: '#323232',
                connectorColor: '#000000',
                format: '<b>{point.name}</b>: {point.percentage:.1f} %({point.y}人)',
                style: {fontSize:"18px",}
            },
            showInLegend: true,
        }
    },
    series: [{
        type: 'pie',
        name: '',
        data: [
            ['大专',   5.0],
            ['本科',   5.0],
            ['硕士',   25.0],
            ['博士',   15.0],
            ['博士',   63.0],

        ]
    }]
});

/* $.ajax({
    url: "${pageContext.request.contextPath}/statistic/paperJson",
    data: {"examStringId": '${param.examIdString}',"paperId":'${param.paperId}', "relateId":'${param.relateId}'},
    success: function (data) {
    	// $.success("正在进行统计，请稍后查看统计信息!");
    	data = eval("(" + data + ")");
    	$(function(){
    	    var i=0,j=0;
    	  j=data.grade.length;
    	    i=Math.ceil(j/10);
    	    for (var  l = 0; l < j; l++) {
                if (data.rank[l] === 0) {
                    data.rank[l] = '-';
                }
            }
    	    
    	    for(var k=0;k<i;k++){
    	        k1=1+10*k;
    	        k2=10*(k+1);
    	        k3=k2-1;
    	        if(i-k==1){
    	        $("<div class='head'>"+k1+"-"+j+"正确率对比图</div><div class='all_div'><div id='container_"+k+"' style='height:500px;width:80%;margin:0 auto'></div><div class='x_title'><p>题号</p><p>年级排名</p></div></div>").appendTo($(".tj"));
    	        }else{
    	        	$("<div class='head'>"+k1+"-"+k2+"正确率对比图</div><div class='all_div'><div id='container_"+k+"' style='height:500px;width:80%;margin:0 auto'></div><div class='x_title'><p>题号</p><p>年级排名</p></div></div>").appendTo($(".tj"));
    	        }
    	        $('#container_'+k).highcharts({
    	        chart: {
    	            type: 'column'
    	        },
    	        title: {
    	            text: ''
    	        },
    	        subtitle: {
    	            text: ''
    	        },
    	         credits:{
    	            enabled:false // 禁用版权信息
    	        },
    	       
    	        exporting: {
    	            enabled:false//关闭设置按钮
    	        },
    	        xAxis: {

    	            categories: [
    	                '第'+k1+'题<br>'+data.rank[k1-1]+'',
    	                '第'+(k1+1)+'题<br>'+data.rank[k1]+'',
    	                '第'+(k1+2)+'题<br>'+data.rank[k1+1]+'',
    	                '第'+(k1+3)+'题<br>'+data.rank[k1+2]+'',
    	                '第'+(k1+4)+'题<br>'+data.rank[k1+3]+'',
    	                '第'+(k1+5)+'题<br>'+data.rank[k1+4]+'',
    	                '第'+(k1+6)+'题<br>'+data.rank[k1+5]+'',
    	                '第'+(k1+7)+'题<br>'+data.rank[k1+6]+'',
    	                '第'+(k1+8)+'题<br>'+data.rank[k1+7]+'',
    	                '第'+(k1+9)+'题<br>'+data.rank[k1+8]+'',
    	            ],
    	            crosshair: true
    	        },
    	        yAxis: {
    	            min: 0,
    	            title: {
    	                text: '正确率'
    	            },
    	            labels:{
    	                 formatter: function () {  
    	                            return this.value + '%';//y轴加上%  
    	                        },
    	                style:{
    	                    color:"#666",
    	                },
    	            },
    	        },
    	        tooltip: {
    	            headerFormat: '<span style="font-size:10px">{point.key}</span><table style="border:none;">',
    	            pointFormat: '<tr>' +
    	                '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
    	            footerFormat: '</table>',
    	            shared: true,
    	            useHTML: true,
    	        },
    	        plotOptions: {
    	        	 column: {
    	        		 pointPadding: 0.1,
    	                 borderWidth: 0,
    	                 groupPadding: 0.08,
    	                 dataLabels:{         //显示顶部数值
    	                             enabled:true, // dataLabels设为true
    	                             formatter : function() {
    	                                 return this.y ;  //返回百分比
    	                             },
    	                             y:-20,
    	                             style:{
    	                                 color:'#333',
    	                                 fontSize:'12px'
    	                             }
    	                         }
    	             }

    	        },
    	        series: [{
    	            name: '年级',
    	            data: data.grade.slice(10*k,k2),
    	        }, {
    	            name: '本班',
    	            data: data.team.slice(10*k, k2),
    	        }]
    	    });
    	    }
    	}) 

    }
}); */
var json={
	    "nianji":[71.55, 64.22, 0, 0, 0, 76.0, 35.6,0, 16.4,194.1, 95.6, 54.4,49.9,71.5, 64.2, 29.2, 144.0,76.0, 35.6, 148.5, 16.4,194.1, 95.6, 54.4],
	    "banji":[71.55, 64.22, 29.2, 0, 76.0, 35.6, 148.5, 16.4,194.1, 95.6, 54.4,49.9, 71.5,64.2, 29.2, 144.0, 76.0,35.6, 148.5, 16.4,194.1, 95.6, 54.4,46.2],
	    "paiming":[1,0,0,4,0,0,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24]
	}

	$(function(){
	    var m=0,j=0;
	  $.each(json,function(idx,item){     
	   //输出
	  j=item.length;

	});
	    m=Math.ceil(j/15);
	    for(var k=0;k<m;k++){
	        k1=1+15*k;
	        k2=15*(k+1);
	        k3=k2-1;
	        $("<div class='head_title'>"+k1+"-"+k2+"正确率对比图</div><div class='all_div'><div id='container_"+k+"' style='height:500px;width:80%;margin:0 auto'></div><div class='x_title'><p>题号</p><p>年级排名</p></div></div>").appendTo($(".tj"));
	        /*for (var  i = 0; i < j; i++) {
	            if (data.rank[i] === 0) {
	                data.rank[i] = '-';
	            }
	        }*/
	        for (var  i = 0; i < j; i++) {
	            if (json.paiming[i] === 0) {
	                json.paiming[i] = '-';
	            }
	        }

	        $('#container_'+k).highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: ''
	        },
	        subtitle: {
	            text: ''
	        },
	         credits:{
	            enabled:false // 禁用版权信息
	        },
	       
	        exporting: {
	            enabled:false//关闭设置按钮
	        },
	        xAxis: {
	            /*title: {
	                text: '题号<br>年级排名',
	                align: 'high',
	                x:50,
	               offset: -20,
	                style:{ 
	                    color: "#0464c8"
	                 }
	            },*/
	            
	            categories: [
	                '第'+k1+'题<br>'+json.paiming[k1-1]+'',
	                '第'+(k1+1)+'题<br>'+json.paiming[k1]+'',
	                '第'+(k1+2)+'题<br>'+json.paiming[k1+1]+'',
	                '第'+(k1+3)+'题<br>'+json.paiming[k1+2]+'',
	                '第'+(k1+4)+'题<br>'+json.paiming[k1+3]+'',
	                '第'+(k1+5)+'题<br>'+json.paiming[k1+4]+'',
	                '第'+(k1+6)+'题<br>'+json.paiming[k1+5]+'',
	                '第'+(k1+7)+'题',
	                '第'+(k1+8)+'题',
	                '第'+(k1+9)+'题',
	                '第'+(k1+10)+'题',
	                '第'+(k1+11)+'题',
	                '第'+(k1+12)+'题',
	                '第'+(k1+13)+'题',
	                '第'+(k1+14)+'题'
	            ],
	            crosshair: true,
	            /* title: {
	                float:true,
	                offset: -20,
	                text: 'Rainfall (mm)',
	                x:-2000,
	            }*/
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: '正确率'
	            },
	            labels:{
	                 formatter: function () {  
	                            return this.value + '%';//y轴加上%  
	                        },
	                style:{
	                    color:"#666",
	                },
	            },
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table style="border:none;">',
	            pointFormat: '<tr>' +
	                '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true,
	        },
	        plotOptions: {
	           column: {
	                pointPadding: 0.1,
	                borderWidth: 0,
	                groupPadding: 0.001, 
	                dataLabels:{         //显示顶部数值
	                            enabled:true, // dataLabels设为true
	                            formatter : function() {
	                                return this.y ;  //返回百分比
	                            },
	                            y:-20,
	                            style:{

	                                color:'#333',
	                                fontSize:'12px'
	                            }
	                        }
	            }
	        },
	        series: [{
	            name: '年级',
	            data: json.nianji.slice(15*k,k2),
	        }, {
	            name: '本班',
	            data: json.banji.slice(15*k, k2),
	        }]
	    });
	    }
	}) 
});
</script>
</html>