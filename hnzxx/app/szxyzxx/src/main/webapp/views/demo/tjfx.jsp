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
        $('#container').highcharts({
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
                categories: ['番禺区', '天河区', '萝岗区', '海珠区', '越秀区'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: '人数 (个)',
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
                name: '总数',
                data: [1106, 1070, 5001, 1140, 40]
            }, {
                name: '男',
                data: [133, 156, 947, 408, 6]
            }, {
                name: '女',
                data: [973, 914, 4054, 732, 34]
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
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							统计分析
						</h3>
					</div>
					<div class="content-widgets">
					<div class="widget-container">
					<table class="responsive table table-striped table-bordered">
						<tbody>
							<tr><td>统计项</td><td><input type="radio">人数</td></tr>
							<tr><td>统计图</td><td><input type="radio">柱形图</td></tr>
							<tr><td>统计区域</td><td>
							<select><option>广东省</option><option>广西省</option></select>
							<select><option>广州市</option><option>惠州市</option></select>
							<select><option>全部</option><option>番禺区</option></select>
							<button onclick="javascript:void(0)" type="button" class="btn btn-warning">确定</button>
							<button onclick="javascript:void(0)" type="button" class="btn btn-success">导出Excel</button>
							</td></tr>
						</tbody>
					</table>
					<div id="container" style="min-width: 310px; max-width: 800px; height: 400px; margin: 0 auto"></div>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
