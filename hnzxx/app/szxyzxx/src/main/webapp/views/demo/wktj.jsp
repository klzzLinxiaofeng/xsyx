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
select{
	margin-bottom:0;
}
input[type="radio"]{
	margin:0 5px;
	position:relative;
	top:-1px;
}
</style>
<script type="text/javascript">
$(function () {
	$('#container_1').highcharts({
        title: {
            text: '',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            title: {
                text: '微课 (个)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '个'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '新增微课',
            data: [3,3,0,1,1,0,1,3,0,2,3,1]
        }]
    });
    $('#container_2').highcharts({
        title: {
            text: '',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        },
        xAxis: {
            categories: ['2015-05-10', '2015-05-11', '2015-05-12', '2015-05-13', '2015-05-14', '2015-05-15',
                '2015-05-16', '2015-05-17', '2015-05-18', '2015-05-19', '2015-05-20', '2015-05-21']
        },
        yAxis: {
            title: {
                text: '微课 (个)'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '个'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '新增微课访问数',
            data: [3,3,0,1,1,0,1,3,0,2,3,1]
        }]
    });
        $('#container_3').highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: '广东省广州市各市、区教师人数统计图'
            },
             subtitle: {
                text: '副标题'
            }, 
            xAxis: {
                categories: ['语文', '数学', '英语', '历史', '政治'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '微课 (个)',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' 个'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -40,
                y: 100,
                floating: true,
                borderWidth: 1,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor || '#FFFFFF'),
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: '微课个数',
                data: [45, 87,123, 175, 201]
            }]
        });
        $('#container_4').highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: '广东省广州市各市、区教师人数统计图'
            },
             subtitle: {
                text: '副标题'
            }, 
            xAxis: {
                categories: ['罗志明', '潘维良', '陈冠洪', '周津', '谭杨'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '微课 (个)',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' 个'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: -40,
                y: 100,
                floating: true,
                borderWidth: 1,
                backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor || '#FFFFFF'),
                shadow: true
            },
            credits: {
                enabled: false
            },
            series: [{
                name: '微课个数',
                data: [245, 187,123, 75, 40]
            }]
        });
    });
    

		</script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="统计分析" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white" style="margin-bottom:0">
					<div class="widget-head">
						<h3>
							微课分析
						</h3>
					</div>
				</div>
				<div class="tj_div">
					<div class="tj_main">
						<div class="tj_1">
							<p class="title">总体统计</p>
							<div class="tj_tu">
								<ul class="wenzi">
									<li style="background-color:#0093A8;">
										<p class="p1">新增微课数</p>
										<p class="p2">0</p>
										<p class="p3">日<i class="down_1"></i>100%</p>
										<p class="p3">周<i class="up_1"></i>4.3%</p>
										<p class="p3">月<i class="up_1"></i>14.7%</p>
									</li>
									<li style="background-color:#009600;">
										<p class="p1">微课总数</p>
										<p class="p2">1024</p>
										<p class="p3">日<i class="up_1"></i>0.3%</p>
										<p class="p3">周<i class="up_1"></i>4.3%</p>
										<p class="p3">月<i class="up_1"></i>14.7%</p>
									</li>
									<li style="background-color:#E88A05;">
										<p class="p1">新增微课访问数</p>
										<p class="p2">775</p>
										<p class="p3">日<i class="up_1"></i>0.3%</p>
										<p class="p3">周<i class="up_1"></i>4.3%</p>
										<p class="p3">月<i class="up_1"></i>14.7%</p>
									</li>
									<li style="background-color:#3498DB;">
										<p class="p1">微课访问总数</p>
										<p class="p2">10058</p>
										<p class="p3">日<i class="up_1"></i>0.3%</p>
										<p class="p3">周<i class="up_1"></i>4.3%</p>
										<p class="p3">月<i class="up_1"></i>14.7%</p>
									</li>
								</ul>
								<div class="clear"></div>
							</div>
						</div>
						<div class="tj_1">
							<p class="title">趋势图</p>
							<div class="tj_tu">
								<div class="xianxing">
									<p class="xx_title">新增微课趋势图</p>
									<div id="container_1" style="width: 1000px; height:350px; margin: 0 auto"></div>
									<p class="xx_title">新增微课访问数趋势图</p>
									<div id="container_2" style="width: 1000px; height:350px; margin: 0 auto"></div>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<div class="tj_1">
							<p class="title">详细统计</p>
							<div class="tj_tu">
								<div class="zhuxing">
									<p class="zx_title">微课资源各学科统计</p>
									<div id="container_3" style="width: 1000px; height:350px; margin: 0 auto"></div>
									<p class="zx_title">活跃教师统计</p>
									<div id="container_4" style="width: 1000px; height:350px; margin: 0 auto"></div>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<p class="banquan">版权所有 广州迅云教育科技有限公司   热线电话：400-608-8260</p>
		</div>
	</div>
</body>
</html>
