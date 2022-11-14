<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单题分析</title>
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="pragram" content="no-cache">
<meta name="viewport" content="width=640, user-scalable=no">
<link href="${pageContext.request.contextPath}/res/css/statistics/questions_app.css" rel="stylesheet">
<script>
	$(function(){
		$(window).resize(function(){
	    	var w1=$("body").width();
	    	$(".highcharts-container").width(0.95*w1);
	    })
	})
</script>
</head>
<body>
	<c:forEach items="${qList}" var="q">
	<div class="biaoti">
		<p class="left">第${param.num}题</p>
		<p class="right">类型：<span>${teamVos[0].questionType}</span>  分值：<span>${teamVos[0].fullScore}</span>  作答人数：<span>${teamVos[0].answerCount}</span>人</p>
	</div>
	<div id="container1"></div>
	<div id="container2"></div>
	<div class="subject_1">
		<p>题目内容</p>
		<div class="nr">
			<c:if test="${!q.isComplex}">
             	${q.content}
             </c:if>
             <c:if test="${q.isComplex}">
             	${q.complexTitle}
             </br>
             	${q.content}
             </c:if>
		</div>
	</div>
	<div class="subject_2">
		<ul>
			<c:set value="${fn:substring(q.questionAnswer,fn:indexOf(q.questionAnswer,'[')+1,fn:indexOf(q.questionAnswer,']'))}" var="ss" />
            <c:set value="${ fn:split(ss, ',') }" var="names" />
			<c:choose>
				<c:when test="${q.questionType eq 'checkbox' || q.questionType eq 'radio'}">
					<c:forEach items="${q.webQuestionAnswer }" var="name" varStatus="status">
						<li data-a="${status.index}" ><div><span class="xuanxiang"></span>. ${name }</div></li>
					</c:forEach>
				</c:when>
				<c:when test="${q.questionType eq 'trueOrFalse' }">
					<li <c:if test="${fn:contains(q.correctAnswer, '×')}">class="on"</c:if>><div> ×</div></li>
					<li <c:if test="${fn:contains(q.correctAnswer, '√')}">class="on"</c:if>><div> √</div></li>
				</c:when>
				<c:otherwise>
					<p style="color:#cccccc">答案</p>
						<div style="line-height: 1.8;padding: 8px;">
							<c:forEach items="${q.webCorrectAnswer }" var="name" varStatus="status">
			            		${name } <br/>
			            	</c:forEach>
						</div>
					
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	<div class="subject_3">
		<p>详细解析</p>
		<div class="nr">
			${q.explanation}
		</div>
	</div>
	</c:forEach>

</body>
<script type="text/javascript">

$(function () {
	// 	input不能输入
	$("input").attr("disabled","disabled")
	//选择题的字母显示与选中
	var answer = '${qList[0].correctAnswer}';
	var zm = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
	$(".xuanxiang").each(function(){
		var index = $(this).parent().parent().data("a");
		$(this).text(zm[index]);
		if(answer.indexOf(zm[index]) != -1){
			$(this).parent().parent().addClass("on");
		}
	})
	
	var type = "${teamVos[0].questionType}";
	if (type.indexOf("选") < 0 && type.indexOf("判") < 0){
		$("#container2").hide();
	} else {
		$("#container2").show();
	}
	
	//正答率/得分率对比图——柱状图
// 	var teamNames = ['年级'];
// 	var rightRates = [{y: ${avg}, color: "#0d7bd5"}];
// 	var teams = eval("(${gradeAnwer})");
// 	for(var i=0; i<teams.length; i++){
// 		teamNames.push(teams[i].teamName+"班");
// 		var color = "#0d7bd5";
// 		if(teams[i].teamName == ${teamVos[0].teamName}){
// 			color = "red";
// 		}
// 		rightRates.push({
// 			y: parseFloat(teams[i].rightRate),
// 			color: color
// 		});
// 	}
	var data = [{name:'年级', data: [parseFloat('${avg}')], visible:true}]
	var teams = eval("(${gradeAnwer})");
	for(var i=0; i<teams.length; i++){
		var name = teams[i].teamName+"班";
		var rightRate = [parseFloat(teams[i].rightRate)];
		var visible = false;
		if(teams[i].teamName == '${teamVos[0].teamName}'){
			visible = true;
		}
		data.push({
			name: name,
			data: rightRate,
			visible: visible
		});
	}

	
	$('#container1').highcharts({
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: '第${param.num}题 年级正答率/得分率对比图'
	    },
	    credits:{
	    	enabled:false // 禁用版权信息
	    },
	    legend: {
	        enabled: true// 关闭图例
	    },
	    exporting: {
	        enabled:false//关闭设置按钮
	    },
	    xAxis: {
// 	        categories: teamNames,
	        categories: [''],
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
	    },
	    tooltip: {
	        headerFormat: '<span style="font-size:10px">{point.key}</span><table style="border:none;">',
	        pointFormat: '<tr>' +
	            '<td style="padding:0;border:none;"><b>{series.name}: {point.y:f} %</b></td></tr>',
	        footerFormat: '</table>',
// 	        shared: true,
// 	        useHTML: true,
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
// 	    series: [{
// 	        name: '',
//          data: rightRates
// 	    }]
	    series: data
	});
	
	//答题项分布图——饼图
	var data2 = [];
	var questions = eval("(${toList})");
	for(var i=0; i<questions.length; i++){
		data2.push([questions[i].questionOption, parseInt(questions[i].questionOptionCount)]);
	}
	
	$('#container2').highcharts({
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false,
	    },
	    title: {
	        text: '答题项应答次数分布图',
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
	        data: data2
	    }]
	});
});
</script>
</html>