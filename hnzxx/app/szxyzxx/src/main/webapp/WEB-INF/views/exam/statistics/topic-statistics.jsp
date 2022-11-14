<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/views/embedded/taglib.jsp" %>
</head>
<script type="text/javascript">


$(function () {
	 //单题统计题目input不能输入
    $(".nierong input[type='text']").prop("disabled","disabled");
	var w=$("body").width()-450;
	$('#container1').css("width",w);
	$(window).resize(function(){
		var w=$("body").width()-450;
		$('#container1').css("width",w);	
	})
    $('#container1').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: ''
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
<c:forEach items="${gradeAnwer}" var="q">
'${q.teamName}班',
</c:forEach>
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
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0,
                dataLabels:{         //显示顶部数值
                    enabled:true, // dataLabels设为true
                    crop:false,
                    formatter : function() {
                        return this.y + "%";  //返回百分比
                    },
                    style:{
                        color:'#333',
                        fontSize:'12px'
                    }
                }
            },
            series:{
                dataLabels:{
                    overflow:'none',
                }
            }
        },
        series: [{
            name: '',
            data: [
                   {
                       y:${avg},
                       color:"#0d7bd5"
                   },
                   <c:forEach items="${gradeAnwer}" var="q">
                   {
                   	
                   	<c:if test="${q.examId eq param.examIdString}">
                   	y:${q.rightRate},
                   	 color:"red"
                   </c:if>
                   	
                   	<c:if test="${q.examId ne param.examIdString}">
   	                	y:${q.rightRate},
   	       			 	color:"#0d7bd5"
                       </c:if>
                   	
                   	
   				    
                   },
                   </c:forEach>
                   ]
        }]
    });
});

$(function () {
	 $('#container2').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	        },
	        title: {
	            text: '',
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
				 <c:forEach items="${toList}" var="q">
	                   
                	   ['${q.questionOption}', ${q.questionOptionCount}],
   				    
                   </c:forEach>

	            ]
	        }]
	    });
	});
</script>
<body>
         <c:forEach items="${qList}" var="q">
        <div class="subject-content">
        <div class="head">第
        ${param.num}
        题年级正答率/得分率对比图</div>
        <div id="container1"></div>
<!--         <div class="head" style="border-bottom:1px solid #aaa;padding-bottom:50px">正答率/得分率年级对比图</div> -->
        <div class="head" id="headOption">答题项应答次数分布图</div>
        <div id="container2"></div>
            <div class="subject" style="border-top: none;">题目内容</div>
            <div class="nierong">
	             <c:if test="${!q.isComplex}">${q.content}</c:if>
	             <c:if test="${q.isComplex}">
		             ${q.complexTitle}
		             </br>
		             ${q.content}
	             </c:if>
	             <br>
				<c:choose>
					<c:when test="${q.questionType eq 'checkbox' || q.questionType eq 'radio'}">
						<c:forEach items="${q.webQuestionAnswer }" var="name" varStatus="status">
							<span class="xuanxiang" data-a="${status.index}"></span>：${name }</br>
						</c:forEach>
					</c:when>
					<c:when test="${q.questionType eq 'trueOrFalse' }">
						×</br>√
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
			</div>
            <div class="subject">答案</div>
            <div class="nierong" style="line-height: 1.5;">
            	<c:choose>
            	<c:when test="${q.questionType eq 'checkbox' || q.questionType eq 'radio' || q.questionType eq 'trueOrFalse'}">
	            	<c:forEach items="${q.webCorrectAnswer }" var="name" varStatus="status">
	            		${name } <c:if test="${!status.last }">,&nbsp;</c:if>
	            	</c:forEach>
            	</c:when>
            	<c:otherwise>
            		<c:forEach items="${q.webCorrectAnswer }" var="name" varStatus="status">
	            		${name } <br/>
	            	</c:forEach>
            	</c:otherwise>
            	</c:choose>
			</div>
            <div class="subject">解析</div>
            <div class="nierong">
            	${q.explanation}
            </div>
        </div>
        </c:forEach>
</body>
<script type="text/javascript">
	$(function(){
		var zm = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
		$(".xuanxiang").each(function(){
			var index = $(this).data("a");
			$(this).text(zm[index]);
		});
		
		var type = "${teamVos[0].questionType}";
		if (type.indexOf("选") < 0 && type.indexOf("判") < 0){
			$("#container2").hide();
			$("#headOption").hide();
		} else {
			$("#container2").show();
			$("#headOption").show();
		}
	});
</script>
</html>