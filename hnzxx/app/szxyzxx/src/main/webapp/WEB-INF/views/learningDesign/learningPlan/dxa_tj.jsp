<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		    <a href="javascript:void(0);" class="statistics">完成率统计</a>
	        <a href="javascript:void(0);">本班完成情况</a>
	        <a href="javascript:void(0);">答题统计</a>
	        <a href="javascript:void(0);">单题分析</a>
        </span>
         <a href="javascript:void(0);" style="float: right" onclick="judge('${status}');" class="sctj" >生成统计</a>
    </div>
    <b>
        <div class="title-content" style="display: none;">
        <div class="ranking">
        	<div class="publish">
                <p>年级排名</p>
                <span>${teamOrder }</span>
            </div>
            <div class="publish">
                <p>年级完成率</p>
                <span><fmt:formatNumber type="number" value="${totalPercent }" maxFractionDigits="2"/>%</span>
            </div>
            <div class="publish">
                <p>本班完成率</p>
                <span><fmt:formatNumber type="number" value="${teamPercent }" maxFractionDigits="2"/>%</span>
            </div>
            <div class="publish" style="margin-right:0;">
                <p>完成人数</p>
                <span>${teamCount }</span>
            </div>
            <div class="clear"></div>
            <div class="head">班级完成率排名</div>
        <table border="0" cellspacing="0" cellpadding="0">
            <thead>
                <tr>
                    <th>名次</th>
                    <th>班级</th>
                    <th>完成率</th>
                    <th>完成人数</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${teamsOrder }" var="team">
	                <tr>
	                    <td>${team.order }</td>
	                    <td>${team.teamName }</td>
	                    <td><fmt:formatNumber type="number" value="${team.percent }" maxFractionDigits="2"/>%</td>
	                    <td>${team.finishCount }</td>
	                </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
   <div class="title-content" style="display:none">
   		<div class="ranking">
        <div class="population">
            <span>总体完成情况</span>
            <div class="kuang">
            <div class="already">
                <canvas id="finishCount" style="demo"></canvas>
                <p>已完成</p>
            </div>
            <div class="not">
                <canvas id="noFinishCount" style="demo"></canvas>
                <p>未完成</p>
            </div>
            </div>
        </div>
        <table border="0" cellspacing="0" cellpadding="0">
            <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>完成情况</th>
                    <th>完成时间</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${list }" var="detail">
	                <tr>
	                    <td>${detail.studentNum }</td>
	                    <td>${detail.studentName }</td>
	                    <td>${detail.hasFinish }</td>
	                    <td><fmt:formatDate value="${detail.finishTime }" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
	                </tr>
               </c:forEach>
            </tbody>
        </table>
        </div>
   </div>
    <div class="title-content" style="display: none;">
    <div class="dttj_div"></div>
        <div class="rank">
        	<c:forEach items="${teamQuestionlist}" var="item">
        	      <c:forEach items="${item}" var="map">
                  <div class="head">[<span>${map.key}</span>] 题目应答情况统计</div>
                                   <table>
        		<thead>
        			<tr><th>序号</th><th>题型</th><th>作答人数</th><th>正答数</th><th>正答率/得分率</th><th>完成率</th></tr>
        		</thead>
        		<tbody>
                  <c:forEach items="${map.value}" var="vo" varStatus="index">
                  <tr>
        		    <td>${index.count}</td>
                    <td>${vo.questionType}</td>
                    <td>${vo.answerCount}</td>
                    <td>${vo.rightAnswerCount}</td>
<%--                     <td><fmt:formatNumber type="number" value="${vo.teamScoringRate*100 }" maxFractionDigits="2"/>%</td> --%>
                    <td><fmt:formatNumber type="number" value="${vo.teamScoringRate}" maxFractionDigits="0"/>%</td>
                    <td>${vo.finishRate}%</td>
        			</tr>
                  </c:forEach>
                  </tbody>
                  </table>
                 </c:forEach>
        	</c:forEach>
        </div>
    </div>
  <!--  <div class="title-content" style="display:none">
   		<div class="tj"></div>
   </div> -->
   <div class="title-content" style="display:none">
   <c:forEach items="${unitlist}" var="unit">
   <c:forEach items="${unit}" var="unitMap">
   <c:forEach items="${unitMap.value}" var="qmap">
   <div class="dxa_th">
   		<div class="title">
   		—— [${qmap.key}] —— 
   		</div>
   		<ul>
   		<c:forEach items="${qmap.value}" var="q" varStatus="index">
   		<li><a href="javascript:void(0)" onclick="topic(this,'${unitMap.key}','${q.questionUuid}','${qmap.key}','${index.index+1}')">${index.index+1}</a></li>
   		</c:forEach>
   		</ul>
   		<div class="clear"></div>
   		</div>
   </c:forEach>
   </c:forEach>
   </c:forEach>
   	          <div class="dxa_th">
   					<div class="title"><span id="qtitle">[预习自测]  第7题</span></div>
   				</div>
   			<div class="ti_xq" >
		    </div>
        	</div>
   </div>
</div>
<div class="re_statistics" style="display:none">
	<p class="title">重新统计需要等待较长时间</p>
	<p class="stati_tis">统计后如果有数据改动（如学生提交答卷、重新打分等）才进行重新统计。是否继续？</p>
	<div class="cz_btn" style="padding-bottom:0">
		<button class="btn btn-blue" onclick="generate('${param.taskId}','${status}');">是的，我要重新统计</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/waterbubble.js"></script>
<script type="text/javascript">
$(function(){
    $(".title-content").hide();//隐藏wenben
    $(".title-content:eq(0)").show();//显示第一个wenben
    $(".topic_native span a").click(function(){
        $(".topic_native a").removeClass("statistics");//移除样式
        $(this).addClass("statistics");//添加样式
        var i=$(this).index();//获得下标
        $(".title-content").hide();//隐藏wenben
        $(".title-content:eq("+i+")").show();//显示第i个wenben
    });
    var uf_data="${unFinishPercent}";
    var f_data="${finishPercent}";
	if(uf_data==1){
		uf_data=1.01;
	}
	if(f_data==1){
		f_data=1.01;
	}
$('#finishCount').waterbubble({
    waterColor: '#0376d4',
    txt: '${finishCount}人',
    data: f_data,//数据多少
});
$('#noFinishCount').waterbubble({
    waterColor: '#f3be73',
    txt: '${unFinishCount}人',
    textColor: '#d27d06',
    data: uf_data,//数据多少
});
    
    /*进度条*/
//     var i=0,w=0;
//     var timer;
//     var speed=100;
//     function go(){
//     w=(i+1)+'%';
//     $(".jdt_tu p").css("width",w); 
//     $(".jdt_tu span").text(w); 
//     if(w == "99%"){ 
//         speed=9999999;
//         window.clearInterval(timer); //进度为100时清除定时器
//     } 
//     if(w == "100%"){ 
//         window.clearInterval(timer); //进度为100时清除定时器
//     } 
//     i=i+1;
// 	} 
// 	function zhixing(){
// 	timer = window.setInterval(go,speed); //设置定时器
// 	}
// 	window.onload = function(){ 
// 	    zhixing(); 
// 	} 
	/*弹出层
	layer.open({
		  type: 1,
		  title: false,
		  area: ['220px', ''],
		  closeBtn: 0,
		  shadeClose: true,
		  skin: 'yourclass',
		  content: $(".re_statistics")
		});*/
	 //进入点击第一题
    $(".dxa_th ul li a").eq(0).click();
});

$(function(){
	$.ajax({
	    url: "${pageContext.request.contextPath}/learningPlan/paperJson/h5",
	    data: {"taskId": '${param.taskId}'},
	    success: function (data) {
	    	// $.success("正在进行统计，请稍后查看统计信息!");
	    	 var data = eval("(" + data + ")");
            var  json=data;
	    	$(function(){
	    	    var m=0,j=0;
	    	  $.each(data,function(index,item){     
	    	   //输出
	    	  j=item.grade.length;


	    	    m=Math.ceil(j/15);
	    	   $("<div class='head'>["+item.title+"]<span class='dfl'>正答率/得分率</span>对比图</div>").appendTo($(".dttj_div"));
	    	   $(".rank .head").eq(index).children('span').text(item.title);
	    	    for(var k=0;k<m;k++){
	    	        k1=1+15*k;
	    	        k2=15*(k+1);
	    	        k3=k2-1;
	    	        if(m-k==1){
	    	        	$("<div class='head_title'>"+k1+"-"+j+"正答率/得分率对比图</div><div class='all_div'><div id='container_"+index+""+k+"' class='container' style='height:500px;width:500px'></div><div class='x_title'><p>题号</p><p>年级排名</p></div></div>").appendTo($(".dttj_div"));
	    	        }else{
	    	        	$("<div class='head_title'>"+k1+"-"+k2+"正答率/得分率对比图</div><div class='all_div'><div id='container_"+index+""+k+"' class='container' style='height:500px;width:500px'></div><div class='x_title'><p>题号</p><p>年级排名</p></div></div>").appendTo($(".dttj_div"));
	    	        }
	    	        browserRedirect();
	    	        $(window).resize(function(){
	    	        	w=$("body").width();
	    	        	 $(".all_div .container").width(0.8*w);
	    	        })
	    	        /*for (var  i = 0; i < j; i++) {
	    	            if (data.rank[i] === 0) {
	    	                data.rank[i] = '-';
	    	            }
	    	        }*/
	    	        for (var  i = 0; i < j; i++) {
	    	            if (item.rank[i] === 0) {
	    	               item.rank[i] = '-';
	    	            }
	    	        }

	    	        $('#container_'+index+k).highcharts({
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
	    	                '第'+k1+'题<br>'+item.rank[k1-1]+'',
	    	                '第'+(k1+1)+'题<br>'+item.rank[k1]+'',
	    	                '第'+(k1+2)+'题<br>'+item.rank[k1+1]+'',
	    	                '第'+(k1+3)+'题<br>'+item.rank[k1+2]+'',
	    	                '第'+(k1+4)+'题<br>'+item.rank[k1+3]+'',
	    	                '第'+(k1+5)+'题<br>'+item.rank[k1+4]+'',
	    	                '第'+(k1+6)+'题<br>'+item.rank[k1+5]+'',
	    	                '第'+(k1+7)+'题<br>'+item.rank[k1+6]+'',
	    	                '第'+(k1+8)+'题<br>'+item.rank[k1+7]+'',
	    	                '第'+(k1+9)+'题<br>'+item.rank[k1+8]+'',
	    	                '第'+(k1+10)+'题<br>'+item.rank[k1+9]+'',
	    	                '第'+(k1+11)+'题<br>'+item.rank[k1+10]+'',
	    	                '第'+(k1+12)+'题<br>'+item.rank[k1+11]+'',
	    	                '第'+(k1+13)+'题<br>'+item.rank[k1+12]+'',
	    	                '第'+(k1+14)+'题<br>'+item.rank[k1+13]+''
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
	    	            data: item.grade.slice(15*k,k2),
	    	        }, {
	    	            name: '本班',
	    	            data: item.team.slice(15*k, k2),
	    	        }]
	    	    });
	    	    }
	    	    });
	    	})  
	    	 
	    }
	});
});
//单题分析--点击生成/加载页面
function topic(obj,examId,questionUuid,title,num){
	$(".dxa_th li").removeClass("on");
	$(obj).parent().addClass("on");
	var url="${pageContext.request.contextPath}/learningPlan/single/h5?examId="+examId+"&num="+num+"&questionUuId="+questionUuid;
	var $requestdata={};
	$.ajax({
	    url: url,
	    type: "POST",
	    data: {},
	    async: false,
	    success: function(data) {
	    	$('.ti_xq').html("").html(data);
	    	var qtitle="["+title+"]  第"+num+"题";
	        $('#qtitle').text(qtitle);
	    }
	});
}
var timer1;
var timer;
function isStatisticsSuccess(ownerId, async){
	var flag ;
	//定时去检查状态。
	$.ajax({
		async:async,
	    type: "post",
	    url: "${pageContext.request.contextPath}/examStatistics/checkStatisticsTaskState/h5",
	    data: {"ownerId": ownerId,type:4},
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
//统计中--统计成功弹窗
function generate(ownerId,flag){
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
	    data: {"type":4,"ownerId": ownerId},
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
var w;
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
		generate(${param.taskId},flag);
	}
}

function browserRedirect() {
    var sUserAgent = navigator.userAgent.toLowerCase();
    var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
    var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
    var bIsMidp = sUserAgent.match(/midp/i) == "midp";
    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
    var bIsAndroid = sUserAgent.match(/android/i) == "android";
    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
    if (bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) {
      
    } else {
    	for (var i=0;i<$(".all_div").length;i++)
    	{
    		$(".all_div").eq(i).children("div").eq(0).width("80%");
    	}
    }
  }

 
</script>
</html>