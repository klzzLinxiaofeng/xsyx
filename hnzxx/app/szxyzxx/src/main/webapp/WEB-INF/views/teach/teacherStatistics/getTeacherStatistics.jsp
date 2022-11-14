<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
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
		var areaName = ${arerTemp_};
		var manNumTemp_ = ${manNumTemp_};
		var womenNumTemp_ = ${womenNumTemp_};
		var sumNumTemp_ = ${sumNumTemp_};
		var weiZhi = ${weiZhi};
		
		getHightChar(areaName,manNumTemp_,womenNumTemp_,sumNumTemp_,weiZhi);
        
        $.initRegionSelector({
    		sjSelector : "province",
			shijSelector : "city",
			qxjSelector : "district"
    	});

    });
    
    function teacherStatisticsTemp(){
    	document.getElementById("teacherStatisticsForm").submit();
    }
	
	 function getHightChar(areaName,manNumTemp_,womenNumTemp_,sumNumTemp_,weiZhi){
		 
		 $('#container').highcharts({
	            chart: {
	                type: 'bar'
	            },
	            title: {
	                text: '${cityName }教师人数统计图'
	            },
	             subtitle: {
	                text: ''
	            }, 
	            xAxis: {
	                categories: areaName,
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
	            exporting: {
	                 enabled:false
	     		},
	            series: [{
	                name: '总数',
	                data: sumNumTemp_
	            }, {
	                name: '男',
	                data: manNumTemp_
	            }, {
	                name: '女',
	                data: womenNumTemp_
	            }, {
	                name: '未知',
	                data: weiZhi
	            }]
	        });
    }
  </script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="教师统计分析" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							教师统计分析
						</h3>
					</div>
					<div class="content-widgets">
					<div class="widget-container">
					<form action="${pageContext.request.contextPath}/teach/teacherStatistics/getTeacherStatistics" id="teacherStatisticsForm">
					<table class="responsive table table-striped table-bordered">
						<tbody>
							<tr><td>统计项</td><td><input type="radio" checked="checked">人数</td></tr>
							<tr><td>统计图</td><td><input type="radio" checked="checked">柱形图</td></tr>
							<tr><td>统计区域</td>
							<td>
							${cityName }
<!-- 							<select id="province" name="province"></select> -->
<!-- 							<select id="city" name="city"></select> -->
<!-- 							<select id="district" name="district"></select> -->
<!-- 							<button onclick="teacherStatisticsTemp();" type="button" class="btn btn-warning">确定</button> -->
<!-- 							<button onclick="javascript:void(0)" type="button" class="btn btn-success">导出Excel</button> -->
							</td>
							</tr>
						</tbody>
					</table>
					</form>
					<div id="container" style="min-width: 310px; max-width: 800px;
					 <c:if test="${level=='3'}">
						    height: 400px;
						</c:if>
					 	<c:if test="${level=='2' || level=='1'}">
						    height: 900px;
						</c:if>
					 margin: 0 auto"></div>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
