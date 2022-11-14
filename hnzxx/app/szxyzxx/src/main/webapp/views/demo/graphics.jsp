<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sca:getDefaultSchoolName()}</title>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/modules/exporting.js"></script>
<style type="text/css">
body{
	font-family:'宋体';
}
.clear{ clear:both}
.tb{
	height:60px;background-color:#f2f2f2;border-bottom:1px solid #bfbfbf;border-top:1px solid #bfbfbf;
}
.tb a{
	width:110px;border-right:1px solid #bfbfbf;height:60px;float:left;text-align: center;line-height: 60px;font-size: 12px;color: #999;font-weight: bold;
}
.tb a:hover{
	background-color: #fff;height: 61px;color: #436b8e;	font-weight: bold;
}
a.bj{
	background-color: #fff;height: 61px;color: #436b8e;	font-weight: bold;
}
.bt{
    width:47.5%;border:1px solid #bfbfbf;height:455px;margin:20px 0 0 20px;float:left;
}
.zzt{
    width:47.5%;border:1px solid #bfbfbf;height:455px;margin:20px 20px 0 0;float:right;
}
.bt2{
    height:455px;border:1px solid #bfbfbf;margin:20px;
}
.tj{
    height:100px;margin: 0 20px 25px 20px;
}
.tj h4{
    font-size: 14px;background-color: #4993b3;line-height: 30px;text-align: center;  height: 30px;margin: 0;color: #fff;border:1px solid #bfbfbf;
}
.tj h4 span{
    color: #dfdfdf;font-size: 12px;
}
.bt h5, .bt2 h5, .zzt h5{
    font-size: 14px;background-color: #4993b3;line-height: 30px;text-align: center;  height: 30px;color: #fff;width: 300px;margin:10px 0 60px 0;
}
table{
    width: 100%;border-collapse:collapse;
}
table tr td{
    padding-left: 10px;border:1px solid #bfbfbf;height: 30px;font-size: 12px;color: #444;
}
</style>
<script type="text/javascript">
$(function () {
    $('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            categories: [
                '男',
                '女',
            ]
        },
        credits: {
            enabled: false
        },
        exporting: {
            enabled:false
},
        yAxis: {
            min: 0,
            title: {
                text: ''
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span>',
            pointFormat:''+
                '<td style="padding:0"><b>{point.y:.1f}人</b',
            footerFormat: '',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '人数',
            data: [650, 360,]
        },]
    });
});

$(function () {
    $('#container1').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        credits: {
            enabled: false
        },
        exporting: {
            enabled:false
},
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',

            data: [
                ['男',   67.0],
                ['女',   33.0],
            ]
        }]
    });
});

$(function () {
    $('#container2').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        credits: {
            enabled: false
        },
        exporting: {
            enabled:false
},
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: '',
            data: [
                ['20岁一下：',   19.0],
                ['20-30',       27.5],
                {
                    name: '30-40：',
                    y: 45.0,
                    sliced: true,
                    selected: true
                },
                ['50以上：',    8.5],
            ]
        }]
    });
});


</script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="教师统计" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							教师统计
						</h3>
					</div>
					<div class="content-widgets">
					
					<div>
                    <div class="tb" id="tb">
                        <a href="javascript:void(0)" class="bj">人数统计</a>
                        <a href="javascript:void(0)">岗位统计</a>
                        <a href="javascript:void(0)">学位统计</a>
                        <a href="javascript:void(0)">科任教师统计</a>
                        <a href="javascript:void(0)">年级任课教师统计</a>
                        <a href="javascript:void(0)">部门统计</a>

                    </div>
                    <div class="figure">
    <div class="bt">
    <h5>XX学校教师人数统计-男女统计饼图</h5>
    <div id="container1" style="height:350px;"></div>
    </div>
    <div class="zzt">
          <h5>XX学校教师人数统计-男女统计柱状图</h5>
          <div id="container" style="height:350px"></div>
    </div>
        <div class="clear"></div>
        <div class="bt2">
            <h5>XX学校教师人数统计-年龄统计饼图</h5>
            <div id="container2" style="height:350px;"></div>
        </div>
            <div class="tj">
                <h4>XX学校教师人数统计-政治面貌统计表<span>(单位：人)</span></h4>
                <table>
                    <tr>
                        <td>少数民族</td>
                        <td>党员</td>
                        <td>团员</td>
                        <td>民主党派</td>
                        <td>华侨</td>
                        <td>港澳台</td>
                    </tr>
                    <tr>
                         <td>20</td>
                         <td>200</td>
                         <td>300</td>
                         <td>100</td>
                         <td>20</td>
                         <td>20</td>
                    </tr>
            </table>
      </div>
</div>

<div class="figure">
   1
</div>
<div class="figure">
   2
</div>
<div class="figure">
   3
</div>
<div class="figure">
   4
</div>
<div class="figure">
   5
</div>
</div>
				</div>
			</div>
		</div>
		</div>
		
	</div>
	<script type="text/javascript">
$(function(){
    $(".figure").hide();//隐藏wenben
    $(".figure:eq(0)").show();//显示第一个wenben
    $("#tb a").click(function(){
        $(".bj").removeClass("bj");//移除样式
        $(this).addClass("bj");//添加样式
        var i=$(this).index();//获得下标
        $(".figure").hide();//隐藏wenben
        $(".figure:eq("+i+")").show();//显示第i个wenben
    });
})
</script>
</body>
</html>
