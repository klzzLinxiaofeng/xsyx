<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/modules/exporting.js"></script>
<title>单科成绩统计</title>
<style type="text/css">

</style>
</head>
<body>
	<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-calculator" name="icon"/>
			<jsp:param value="班级单科成绩统计" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12"  style="height: 43px;">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							<span>2015学年上学期六年一班期末考试成绩统计</span>
							<p style="float:right;margin-bottom:0" class="btn_link">
							<a class="a3" href="javascript:void(0)"><i class="fa fa-plus"></i>选择</a>
							<a class="a1" href="javascript:void(0)"><i class="fa fa-print"></i>打印</a>
								<a class="a4" href="javascript:void(0)"><i class="fa  fa-undo"></i>重新统计</a>
							</p>
						</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="white cj_tj">
			<div class="nav">
				<ul>
					<li class="on"><a href="javascript:void(0)">统计</a></li>
					<li><a href="javascript:void(0)">排名</a></li>
					<li><a href="javascript:void(0)">对比</a></li>
				</ul>
			</div>
			<div class="tj_main">
				<div class="dkcj" >
					<div class="t_div">
						<p class="title">考试信息</p>
						<ul>
							<li><p class="p1">班级</p><p class="p2">六年级一班</p></li>
							<li><p class="p1">科目</p><p class="p2">语文</p></li>
							<li><p class="p1">考试日期</p><p class="p2">2015-12-1</p></li>
							<li><p class="p1">任课教师</p><p class="p2">张三</p></li>
							<li><p class="p1">满分分数</p><p class="p2">100</p></li>
							<li><p class="p1">优秀分数</p><p class="p2">90</p></li>
							<li><p class="p1">良好分数</p><p class="p2">75</p></li>
							<li><p class="p1">及格分数</p><p class="p2">60</p></li>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="t_div">
						<p class="title">基本统计</p>
						<ul>
							<li><p class="p1">平均分</p><p class="p2">六年级一班</p></li>
							<li><p class="p1">最高分</p><p class="p2">语文</p></li>
							<li><p class="p1">最低分</p><p class="p2">2015-12-1</p></li>
							<li><p class="p1">标准差</p><p class="p2">张三</p></li>
							<li><p class="p1">考试人数</p><p class="p2">100</p></li>
							<li><p class="p1">极差</p><p class="p2">90</p></li>
							<li><p class="p1">补考人数</p><p class="p2">75</p></li>
							<li><p class="p1">高差</p><p class="p2">60</p></li>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="t_div">
						<p class="title">三率统计</p>
						 <div id="container" style="width:99%;height:300px"></div>
					</div>
					<div class="t_div">
						<p class="title">分段统计</p>
						<div id="container_bing" style="width:99%;height:300px"></div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="paiming" style="display:none">
					<div class="xz">
						<div class="left">
							<span>排序项：</span>
							<input type="radio" name="score" value="学号" /> 学号 &nbsp; &nbsp;
  							<input type="radio" name="score" value="姓名" /> 姓名 &nbsp; &nbsp;
  							<input type="radio" name="score" value="名次" /> 名次 &nbsp; &nbsp;
						</div>
						<div class="right">
							<span>输出项：</span>
							<input type="checkbox" name="shuchu" value="班内学号" />班内学号 &nbsp; &nbsp;
							<input type="checkbox" name="shuchu" value="成绩" />成绩 &nbsp; &nbsp;
							<input type="checkbox" name="shuchu" value="等级" />等级 &nbsp; &nbsp;
							<input type="checkbox" name="shuchu" value="班级名称" />班级名称 &nbsp; &nbsp;
							<input type="checkbox" name="shuchu" value="年级名称" />年级名称 &nbsp; &nbsp;
							<button class="btn btn-primary">查询</button>
						</div>
						<div class="clear"></div>
					</div>
					<table class="responsive table table-striped">
						<thead><tr><th>班内学号</th><th>姓名</th><th>成绩</th><th>等级</th><th>班级名次</th><th>年级名次</th></tr></thead>
						<tbody>
							<tr><td>1</td><td>张三</td><td>80</td><td>80</td><td>4</td><td>54</td></tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function () {
		$(".cj_tj .nav ul li a").click(function(){
			$(".cj_tj .nav ul li ").removeClass("on");
			var i=$(this).parent().index();
			$(this).parent().addClass("on");
			$(".tj_main").children().hide();
			$(".tj_main").children().eq(i).show();
		})
	    $('#container').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: null
	        },
	        subtitle: {
	            text: null
	        },
	        credits: {
	            enabled: false
	       },
	       exporting:{ 
               enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
          },
	        xAxis: {
	            categories: [
	                '百分比'
	            ],
	        },
	        yAxis: {
	            min: 0,
	            allowDecimals:true,
	            title: {
	                text: null
	            },
	            stacking: 'percent',
	         	labels:{
	                    formatter: function(){
	                         return this.value+'%';
	                     },
	                 },
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0,
	                dataLabels: {
		                enabled: true
		            }
	            },
	            bar: {
	               	dataLabels: {
	                  enabled: true, // 在节点显示数据
	                  //color: '#000000',  // 设置节点显示数据字体的颜色
	                  formatter: function() {
	                          return this.point.y+'%';  // 重新设置节点显示数据
	                       	},
	              },
	            }
	        },
	        
	        series: [{
	            name: '优秀人数（9人）',
	            data: [35]

	        }, {
	            name: '良好人数（26人）',
	            data: [70]

	        }, {
	            name: '及格人数（38人）',
	            data: [90]

	        }, {
	            name: '不及格人数（4人）',
	            data: [7]

	        }]
	    });
	    $('#container_bing').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	            text: null
	        },
	        credits: {
	            enabled: false
	       },
	       exporting:{ 
               enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
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
	            name: 'Browser share',
	            data: [
	                ['90-100分段（9人）',   15.0],
	                ['75-90分段（15人）',       27],
	                {
	                    name: '75-60分段（23人）',
	                    y: 50,
	                    sliced: true,
	                    selected: true
	                },
	                ['60分以下（4人）',    8]
	            ]
	        }]
	    });
	});
	</script>
</body>
</html>
