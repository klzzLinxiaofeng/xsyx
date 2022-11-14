<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/kaoshi.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/modules/exporting.js"></script>
<title>考试管理</title>
<style>
	.table thead th,.table tbody td{
		text-align:center;
	}
	.table-left thead th,.table-left tbody td{
		text-align:left;
	}
	.table .b1{
		display:none;
	}
</style>
<script type="text/javascript">
	$(function () {
	    $('#container').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: '成绩分段统计图'
	        },
	        xAxis: {
	            categories: [
'100-95',
'94-90',
'89-80',
'79-60',
'59-0'
	            ]
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: '人数 (个)'
	            }
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span>',
	            pointFormat: '' +
	                '',
	            footerFormat: '<table><tbody><tr><td style="color:{series.color};padding:0">{series.name}: </td><td style="padding:0"><b>{point.y:.1f} 人</b></td></tr></tbody></table>',
	            shared: true,
	            useHTML: true
	        },
	        exporting:{
                enabled:false
            },
            credits: {
                enabled: false
            },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series: [{
	            name: '成绩',
	            data:  [26,4, 2, 9, 2]

	        }]
	    });
	});
	$(function () {
	    $('#container1').highcharts({
	        title: {
	            text: '近五次考试平均分趋势',
	            x: -20 //center
	        },
	      
	        xAxis: {
	            categories: ['1', '2', '3', '4', '5']
	        },
	        yAxis: {
	            title: {
	                text: '成绩 (分)'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        exporting:{
                enabled:false
            },
            credits: {
                enabled: false
            },
	        tooltip: {
	            valueSuffix: '°C'
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: [{
	            name: '当前成绩',
	            data: [68, 77, 80, 81, 85]
	        }]
	    });
	});
	 $(function () {
         $('.tbl-simple').dataTable({
             "sDom": "t"
         });
         /* 成绩切换 */
         $(".m_top .tongji_1 a").click(function(){
        	 var i=$(this).index();
        	 $(".m_top .tongji_1 a").removeClass("on");
        	 $(this).addClass("on");
        	 $(".m_bottom").children().hide();
        	 $(".m_bottom").children().eq(i).show();
         });
         $(".top").click(function(){
        	 if( $(".top").children("i").hasClass("fa-angle-down")){
	        	 $(".table .a1").hide();
	        	 $(this).children("i").removeClass("fa-angle-down").addClass("fa-angle-up")
        	 }else{
        		 $(".table .a1").show();
	        	 $(this).children("i").removeClass("fa-angle-up").addClass("fa-angle-down")
        	 }
         })
         $(".down_1").click(function(){
        	 if( $(".down_1").children("i").hasClass("fa-angle-up")){
        		 $(".table .b1").show();
	        	 $(this).children("i").removeClass("fa-angle-up").addClass("fa-angle-down")
        	 }else{
        		 $(".table .b1").hide();
	        	 $(this).children("i").removeClass("fa-angle-down").addClass("fa-angle-up")
        	 }
         })
     });

		</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="考试管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							2015年下学期语文模拟考试
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a4" onclick="$.refreshWin();"><i class="fa  fa-reply"></i>返回列表</a>
							</p>
						</h3>
					</div>
					<div class="light_grey">
						<p><b>班级：</b>六年级(3)班 <b>科目：</b>语文 <b>考试时间：</b>2015年2月4日 ~ 2015年2月4日 23:00</p>
					</div>
					<div class="content-widgets">
						<div class="m_top">
							<div class="tongji_1">
								<a href="javascript:void(0)" class="on">总体统计</a>
								<a href="javascript:void(0)">成绩清单</a>
								<a href="javascript:void(0)">知识点统计</a>
							</div>
							<a class="dc_cj">导出成绩信息</a>
						</div>
						<div class="m_bottom">
							<div class="a1" style="display:block">
								<div class="cjtj">
									<ul>
										<li>
											<p class="p1">人数</p>
											<p class="p2">40</p>
										</li>
										<li>
											<p class="p1">平均分</p>
											<p class="p2">85.3</p>
										</li>
										<li>
											<p class="p1">最高分</p>
											<p class="p2">100</p>
										</li>
										<li>
											<p class="p1">最低分</p>
											<p class="p2">40</p>
										</li>
										<li>
											<p class="p1">高分率</p>
											<p class="p2">68%</p>
										</li>
										<li>
											<p class="p1">低分率</p>
											<p class="p2">25%</p>
										</li>
										<li>
											<p class="p1">及格率</p>
											<p class="p2">92%</p>
										</li>
									</ul>
								</div>
								<div class="tjt">
								<div id="container" style="width:50%; height: 420px;float:left "></div>
								<div id="container1" style="width:50%; height: 420px;float:left "></div>
								<div class="clear"></div>
								</div>
							</div>
							<div class="a2" style="display:none">
								<div class="row-fluid">
								<div class="span12">
									<div class="content-widgets">
										<div>
											<div style="padding-top:10px;">
												<table class="responsive table table-striped tbl-simple table-bordered">
												<thead>
												<tr>
													<th>
														名次
													</th>
													<th>
														学号
													</th>
													<th>
														姓名
													</th>
													<th>
														完成时间
													</th>
													<th>
														完成用时
													</th>
													<th>
														得分
													</th>
												</tr>
												</thead>
												<tbody>
												<tr>
													<td>
														1
													</td>
													<td>
														201102501
													</td>
													<td>
														赵欣
													</td>
													<td>
														2015-02-04 19:00
													</td>
													<td>
														28分37秒
													</td>
													<td>
														100
													</td>
												</tr>
												<tr>
													<td>
														1
													</td>
													<td>
														201102502
													</td>
													<td>
														赵本山
													</td>
													<td>
														2015-02-04 19:00
													</td>
													<td>
														25分59秒
													</td>
													<td>
														100
													</td>
												</tr>
												<tr>
													<td>
														2
													</td>
													<td>
														201102503
													</td>
													<td>
														赵磊
													</td>
													<td>
														2015-02-04 19:00
													</td>
													<td>
														28分37秒
													</td>
													<td>
														95
													</td>
												</tr>
												<tr>
													<td>
														3
													</td>
													<td>
														201102504
													</td>
													<td>
														赵四
													</td>
													<td>
														2015-02-04 19:00
													</td>
													<td>
														28分37秒
													</td>
													<td>
														93
													</td>
												</tr>
												<tr>
													<td>
														4
													</td>
													<td>
														201102505
													</td>
													<td>
														李明
													</td>
													<td>
														2015-02-04 19:00
													</td>
													<td>
														28分37秒
													</td>
													<td>
														92
													</td>
												</tr>
												<tr>
													<td>
														5
													</td>
													<td>
														201102506
													</td>
													<td>
														孙斌
													</td>
													<td>
														2015-02-04 19:00
													</td>
													<td>
														28分37秒
													</td>
													<td>
														92
													</td>
												</tr>
												<tr>
													<td>
														6
													</td>
													<td>
														201102507
													</td>
													<td>
														贾亮
													</td>
													<td>
														2015-02-04 19:00
													</td>
													<td>
														28分37秒
													</td>
													<td>
														92
													</td>
												</tr>
												</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							</div>
							<div class="a3" style="display:none">
								<table class="table responsive table-left" >
									<thead>
										<tr>
											<th style="width:40px;"></th>
											<th>知识点</th>
											<th>题量</th>
											<th>正确率</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td style="text-align:center"><a class="top" href="javascript:void(0)" style="display:block;"><i class="fa fa-angle-down"></i></a></td>
											<td>基础知识及语言表达</td>
											<td>40</td>
											<td><span>97%</span></td>
										</tr>
										<tr class="a1">
											<td></td>
											<td>字音</td>
											<td>5</td>
											<td><span>80%</span></td>
										</tr>
										<tr class="a1">
											<td></td>
											<td>字型</td>
											<td>5</td>
											<td><span>100%</span></td>
										</tr>
										<tr class="a1">
											<td></td>
											<td>词语</td>
											<td>5</td>
											<td><span>100%</span></td>
										</tr>
										<tr class="a1">
											<td></td>
											<td>语法</td>
											<td>5</td>
											<td><span>100%</span></td>
										</tr>
										<tr class="a1">
											<td></td>
											<td>标点符号</td>
											<td>5</td>
											<td><span>100%</span></td>
										</tr>
										<tr class="a1">
											<td></td>
											<td>病句辨析</td>
											<td>5</td>
											<td><span>100%</span></td>
										</tr>
										<tr class="a1">
											<td></td>
											<td>书写规范</td>
											<td>5</td>
											<td><span>100%</span></td>
										</tr>
										<tr class="a1">
											<td></td>
											<td>名言警句</td>
											<td>5</td>
											<td><span>100%</span></td>
										</tr>
										<tr>
											<td style="text-align:center"><a href="javascript:void(0)" class="down_1" style="display:block;"><i class="fa fa-angle-up"></i></a></td>
											<td>基础知识及语言表达</td>
											<td>30</td>
											<td><span>93%</span></td>
										</tr>
										<tr class="b1">
											<td></td>
											<td>成语</td>
											<td>10</td>
											<td><span>90%</span></td>
										</tr>
										<tr class="b1">
											<td></td>
											<td>阅读理解</td>
											<td>5</td>
											<td><span>100%</span></td>
										</tr>
										<tr class="b1">
											<td></td>
											<td>造句</td>
											<td>5</td>
											<td><span>80%</span></td>
										</tr>
										<tr class="b1">
											<td></td>
											<td>找病句</td>
											<td>10</td>
											<td><span>100%</span></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>