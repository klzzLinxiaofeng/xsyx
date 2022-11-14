<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看统计</title>
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="pragram" content="no-cache">
<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">
<link href="${pageContext.request.contextPath}/res/css/statistics/questions_app.css" rel="stylesheet">
<style>
	.subject_1 input[type="text"]{
		border:0 none;
		border-bottom:1px solid #ccc;
		background-color:#fff;
	}
</style>
</head>
<body>
<div class="title-count" style="margin:0">
	<div class="topic-option">
		<span>
	        <a href="javascript:void(0);" class="statistics">整卷统计</a>
	        <a href="javascript:void(0);">答题统计</a>
	        <a href="javascript:void(0);">单题分析</a>
        </span>
	        <a href="javascript:void(0);" style="float: right" onclick="judge('${status}');" class="sctj" >生成统计</a>
    </div>
    <div class="fgf"></div>
    <div class="title-content" style="display: none;">
    	<div class="head">班级排名成绩表</div>
        <div id="container3" style="height:500px;"></div>
        <div class="wxts"><p></p>温馨提示：点击下方色块按钮即可显示或者隐藏相应柱状图</div>
        <div class="ranking">
        	<div class="publish">
                <p>年级平均分</p>
                <span>${gradeAvg}</span>
            </div>
            <div class="publish">
                <p>本班平均分</p>
                <span>${teamAvg}</span>
            </div>
            <div class="publish">
                <p>年级排名</p>
                <span>${gradeRank}</span>
            </div>
            <div class="clear"></div>
            <div class="wxts"><p></p>温馨提示：平均分为总分除以作答人数</div>
            <div class="clear"></div>
            <div class="head">班级排名成绩表</div>
            <div class="bjpm_div">
            	<ul>
            	<c:forEach items="${list}" var="it">
            		<li>
            			<p class="p1" <c:if test="${teamId eq it.teamId }">style="background-color: #ff0000"</c:if> >
	            			<c:if test="${!empty it.averageScore}">${it.rank}</c:if>
	            			<c:if test="${empty it.averageScore}">-</c:if>
            			</p>
            			<p class="p2 nj">
            				${it.teamName}
            			</p>
            			<p class="p2">${it.studentCount}<c:if test="${it.studentCount != 0}">人</c:if></p>
            			<p class="p3">${it.averageScore}<c:if test="${empty it.averageScore}">-</c:if></p>
            		</li>
            	</c:forEach>
            	</ul>
            	<div class="clear"></div>
            </div>
        </div>
    </div>
   <div class="title-content" style="display:none">
   		<div class="tj"></div>
   </div>
   <div class="title-content" style="display:none">
   		<div class="th_select">
   			<ul>
   				<c:forEach items="${qList}" var="item" varStatus="index">
   					<li <c:if test="${index.index == 0}">class="on"</c:if>>
   						<a href="javascript:void(0)" onclick="toptj(this,'${item.questionUuid}','${item.pos}')">${item.pos}</a>
   					</li>
   				</c:forEach>
   			</ul>
   			<div class="clear"></div>
   		</div>
   		<div class="fgf"></div>
   			<div class="ti_xq" id="top">
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
var w;
$(function(){
    $(".title-content").hide();//隐藏wenben
    $(".title-content:eq(0)").show();//显示第一个wenben
    $(".topic-option span a").click(function(){
    	/* if(!$('.title-content').eq(4).is(":hidden")){
   	 	  alert("正在统计请稍后");
   	 	  return  false;
    	  } */
        $(".topic-option a").removeClass("statistics");//移除样式
        $(this).addClass("statistics");//添加样式
        var i=$(this).index();//获得下标
        $(".title-content").hide();//隐藏wenben
        $(".title-content:eq("+i+")").show();//显示第i个wenbencontainer1
    });
    //进入点击第一题
    $(".th_select ul li.on a").eq(0).click();
    w=$("body").width();
    $("#container3").width(0.95*w);
    $("#container1, #container2").width(0.95*w);
    $(window).resize(function(){
    	w=$("body").width();
    	$("#container3").width(0.95*w);
    	$("#container1, #container2").width(0.95*w);
    });
});

//点击“统计”时判断是否需弹出“重新统计”的提示框
function judge(flag){
	if(flag){
		layer.open({
			type: 1,
			title: false,
			area: ['220px', '330px'],
			closeBtn: 0,
			shadeClose: true,
			skin: 'yourclass',
			content: $(".re_statistics")
		});
	}else{
		generate(${param.examId},${param.paperId},${param.taskId},flag);
	}
}

//统计中--统计成功弹窗
function generate(examId,paperId,ownerId,flag){
    /*进度条*/
    var i=0,w=0;
    var speed=1500;
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
	timer = window.setInterval(go,speed); //设置定时器
	timer1 = setInterval("isStatisticsSuccess("+ownerId+", true)",5000,ownerId);
	//弹出层
	layer.closeAll();
	layer.open({
		type: 1,
		title: false,
		area: ['220px', ''],
		closeBtn: 0,
		shadeClose: false,
		skin: 'yourclass',
		content: $(".statisticsing")
	});
	
	$.ajax({
	    type: "post",
	    url: "${pageContext.request.contextPath}/examStatistics/generate/h5",
	    data: {"examId": examId,"paperId":paperId, "ownerId": ownerId},
	    success: function (data) {
	    	if(data == 1){
	    		layer.closeAll();
	    		layer.open({
	    			type: 1,
	    			title: false,
	    			area: ['220px', ''],
	    			closeBtn: 0,
	    			shadeClose: false,
	    			skin: 'yourclass',
	    			content: $(".success_statistics")
	    		});
	    		if (timer1 != null) {
	    		    clearInterval(timer1);
				}
	    	}
	    }
	});
}

var timer1;
var timer;
$(function(){
	var i=isStatisticsSuccess(${param.taskId}, false);
	if(i!=1 && i!=''){
	 	var i=30,w=0,w1=0;
	    var speed=1500;
		var go=function(){
			w=(i+1)+'%';
		    w1=(i+1-5)+'%';
		    $(".jdt_tu p").css("width",w); 
		    $(".jdt_tu span").text(w); 
		    $(".jdt_tu span").css('left',w1)
		    if(w == "99%"){ 
		        speed=9999999;
		        window.clearInterval(timer); //进度为100时清除定时器
		    } 
		    if(w == "100%"){ 
		        window.clearInterval(timer); //进度为100时清除定时器
		    } 
		    i=i+1;
		}
		timer = window.setInterval(go,speed);
        timer1 = setInterval("isStatisticsSuccess(${param.taskId}, true)",5000);
		layer.closeAll();
		layer.open({
			type: 1,
			title: false,
			area: ['220px', ''],
			closeBtn: 0,
			shadeClose: false,
			skin: 'yourclass',
			content: $(".statisticsing")
		});

	}
});


//整卷统计————班级排名成绩表
$(function () {
	var series = [];
	var gradeAvg = ${gradeAvg};
	var teamList = "${teamList}";
	var teamId = "${teamId}";
	
	series.push({
		name: '全年级',
        data: [gradeAvg],
        visible:true
	});
	if(teamList != ""){
		var obj = eval("(" + teamList + ")");
		for(var i=0; i<obj.length; i++){
			//* 获得的变量是字符串，需转为数字
			//* data的参数是数组，需要加[ ]
			var averageScore = obj[i].averageScore;
			var teamName = obj[i].teamName;
			if(averageScore == "null" || averageScore == ""){
				teamName += "(缺考)";
				averageScore = [];
			}else{
				averageScore = [parseFloat(averageScore)];
			}
			var visible = false;
			if(teamId == obj[i].teamId){
				visible = true;
			}	
			series.push({
				name: teamName,
		        data: averageScore,
		        visible: visible
			});	
		}	
	}
	
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
        series: series
    });
});

//答题统计——图
$(function(){
	$.ajax({
	    url: "${pageContext.request.contextPath}/examStatistics/paperJson/h5",
	    data: {"examStringId": '${param.examId}',"paperId":'${param.paperId}', "relateId":'${param.relateId}'},
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
	    	        	$("<div class='head'>"+k1+"-"+j+"正答率/得分率对比图</div><div class='all_div'><div id='container_"+k+"' style='height:500px;margin:0 auto'></div></div>").appendTo($(".tj"));
	    	        }else{
	    	        	$("<div class='head'>"+k1+"-"+k2+"正答率/得分率对比图</div><div class='all_div'><div id='container_"+k+"' style='height:500px;margin:0 auto'></div></div>").appendTo($(".tj"));
	    	        }
	    	        $(".all_div div").width(0.8*w);
	    	        $(window).resize(function(){
	    	        	w=$("body").width();
	    	        	 $(".all_div div").width(0.8*w);
	    	        })
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
		    	        	title: {
		    	                text: '题号(年级排名)'
		    	            },
		    	            categories: [
		    	                '第'+k1+'题<br>('+data.rank[k1-1]+')',
		    	                '第'+(k1+1)+'题<br>('+data.rank[k1]+')',
		    	                '第'+(k1+2)+'题<br>('+data.rank[k1+1]+')',
		    	                '第'+(k1+3)+'题<br>('+data.rank[k1+2]+')',
		    	                '第'+(k1+4)+'题<br>('+data.rank[k1+3]+')',
		    	                '第'+(k1+5)+'题<br>('+data.rank[k1+4]+')',
		    	                '第'+(k1+6)+'题<br>('+data.rank[k1+5]+')',
		    	                '第'+(k1+7)+'题<br>('+data.rank[k1+6]+')',
		    	                '第'+(k1+8)+'题<br>('+data.rank[k1+7]+')',
		    	                '第'+(k1+9)+'题<br>('+data.rank[k1+8]+')',
		    	            ],
		    	            crosshair: true
		    	        },
		    	        yAxis: {
		    	            min: 0,
		    	            title: {
		    	                text: '正答率/得分率'
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
	});
});


//判断统计任务状态 每5秒检测一次状态。
function isStatisticsSuccess(ownerId, async){
	var flag ;
	//定时去检查状态。
	$.ajax({
		async:async,
	    type: "post",
	    url: "${pageContext.request.contextPath}/examStatistics/checkStatisticsTaskState/h5",
	    data: {"ownerId": ownerId},
	    success: function (data) {
	    	flag = data;
	    	if(data == 1){
		    	clearInterval(timer1);
                if (async) {
                    window.location.reload();
                }
	    	}
	    }
	});
	return flag;
}

//单题分析--点击生成/加载页面
function toptj(obj,qUuid,num){
	$(".th_select li").removeClass("on");
	$(obj).parent().addClass("on");
	var url="${pageContext.request.contextPath}/examStatistics/single/h5?examIdString=${param.examId}&relateId=${param.relateId}&taskId=${param.taskId}&questionUuId="+qUuid+"&paperId=${param.paperId}&num="+num;
	var $requestdata={};
	$.ajax({
	    url: url,
	    type: "POST",
	    data: {},
	    async: false,
	    success: function(data) {
	    	$('#top').html("").html(data);
	    	
	    }
	});
}


</script>
</html>