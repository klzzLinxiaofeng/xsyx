<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/statistics/questions.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/statistics/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/statistics/highcharts.js"></script>
<title>Insert title here</title>
</head>
<style>
.title-count{
    background-color: #fff;
}
@media screen and (min-width: 700px) and (max-width: 1800px){
  .publish p
  {
    font-size:20px;
    padding: 0;
  }
  .publish span
  {
    font-size:30px;
  }
}
</style>
<script type="text/javascript">
$(function(){
    $(".title-content").hide();//隐藏wenben
    $(".title-content:eq(0)").show();//显示第一个wenben
    $(".topic-option a").click(function(){
        $(".topic-option a").removeClass("statistics");//移除样式
        $(this).addClass("statistics");//添加样式
        var i=$(this).index();//获得下标
        $(".title-content").hide();//隐藏wenben
        $(".title-content:eq("+i+")").show();//显示第i个wenben
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
                    fontSize:"12px",
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
                pointPadding: 0.2,
                borderWidth: 0,
                groupPadding: 0.001,
                dataLabels:{         //显示顶部数值
                            enabled:true, // dataLabels设为true
                            style:{
                                color:'#333',
                                fontSize:'14px'
                            }
                        }
            }
        },
        series: [{
            name: '全年级',
            data: [${gradeAvg}],
            visible:true
        }
        <c:forEach items="${list}" var="it">
        ,
        {
            name: '${it.teamName}${it.averageScore == null ? " (缺考)" : ""}',
            data: [${it.averageScore}],
            <c:if test="${it.teamId eq teamId}">
            visible:true
            </c:if>
        <c:if test="${it.teamId ne teamId}">
        visible:false
        </c:if>
        }
        </c:forEach>
        ]
    });
});
</script>
<body>
<div class="title-count">
    <div class="topic-option">
        <span><a href="javascript:void(0);" class="statistics">排名表</a>
        <a href="javascript:void(0);">对比图</a>
        <a href="javascript:void(0);">成绩表</a></span>
        <p style="float: right;" class="btn_link">
		<a class="a4" href="${pageContext.request.contextPath}/exampublish/publishManagerIndex?dm=SHI_JUAN_GUAN_LI&relateId=${param.relateId}&gradeId=${param.gradeId}"><i class="fa fa-reply"></i> 返回 </a>
		</p>
    </div>

    <div class="title-content">
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
            <div class="wxts"><p></p>温馨提示：平均分为总分除以作答人数</div>
            <div class="clear"></div>
        <div class="head">班级排名成绩表</div>
         <table border="0" cellspacing="0" cellpadding="0" style="width: 100%;">
            <thead>
                <tr>
                    <th>名次</th>
                    <th>班级</th>
                    <th>平均分</th>
                    <th>已作答人数</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${list}" var="it">
                <tr>
                    <td>
                    <c:if test="${!empty it.averageScore}">
                    
                    ${it.rank}
                    </c:if>
                   <c:if test="${empty it.averageScore}">
                    
                    -
                    </c:if>
                    </td>
                    <td>${it.teamName}
                    <c:if test="${empty it.averageScore}">
                    
                                                          <font color="red">(缺考)</font>
                    </c:if>
                    
                    </td>
                    <td>${it.averageScore}
                    <c:if test="${empty it.averageScore}">
                    
                    -
                    </c:if>
                    
                    </td>
                    <td>${it.studentCount}</td>
                </tr>
             </c:forEach>
            </tbody>
        </table>
        </div>
    </div>

    <div class="title-content">
        <div class="head">班级排名成绩表</div>
        <div id="container3" style="height:500px;"></div>
        <div class="wxts"><p></p>温馨提示：点击下方色块按钮即可显示或者隐藏相应柱状图</div>
    </div>
    <div class="title-content">
        <div class="ranking">
            <div class="publish">
                <p>本班平均分</p>
                <span>${teamAvg}</span>
            </div>

            <div class="clear"></div>
        <div class="head">班级成绩表</div>
        <table border="0" cellspacing="0" cellpadding="0" style="width: 100%;">
            <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>成绩</th>
                </tr>
            </thead>
            <tbody>
			 <c:forEach items="${svos}" var="vo">
                <tr>
                    <td>${vo.number}</td>
                    <td>${vo.name}</td>
                    <td>
                    <c:choose>
                    	<c:when test="${vo.score >= 0}">
                    		 ${vo.score}
                    	</c:when>
                    	<c:otherwise>
                    		<font color="red">缺考</font>
                    	</c:otherwise>
                    </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
</div>
</body>
</html>