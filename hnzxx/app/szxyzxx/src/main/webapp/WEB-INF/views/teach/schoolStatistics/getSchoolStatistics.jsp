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
select {
	margin-bottom: 0;
}

input[type="radio"] {
	margin: 0 5px;
	position: relative;
	top: -1px;
}

input[type="checkbox"] {
	margin: 0 5px;
	position: relative;
	top: -1px;
}

p {
	color: #333333;
	font-size: 12px;
	font-weight: bold;
	cursor: pointer;
	fill: #333333;
	line-height: 0px;
}
</style>
<script type="text/javascript">
$(function () {
		var areaName = ${areaTemp_};
		var xiaoxueTemp_ = ${xiaoxueTemp_};
		var chouzhongTemp_ = ${chouzhongTemp_};
		var gaozhongTemp_ = ${gaozhongTemp_};
		var zongsuTemp_ = ${zongsuTemp_};

		getHightChar(areaName,xiaoxueTemp_,chouzhongTemp_,gaozhongTemp_,zongsuTemp_);
        
        $.initRegionSelector({
    		sjSelector : "province",
			shijSelector : "city",
			qxjSelector : "district",
			
			isEchoSelected : true,
			sjSelectedVal : "${privinceTemp}",
			shijSelectedVal : "${cityTemp}",
			qxjSelectedVal : "${districtTemp}"
    	});

    });
    
    function getHightChar(areaName,xiaoxueTemp_,chouzhongTemp_,gaozhongTemp_,zongsuTemp_){
    	 $('#container').highcharts({
             chart: {
                 type: 'bar'
             },
             title: {
                 text: "${cityName }学校统计柱状图"
             },
              subtitle: {
                 text: ""
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
             exporting: {
                 enabled:false
     		},
             legend: {
                 layout: 'vertical',
                 align: 'right',
                 verticalAlign: 'top',
                 x: 0,
                 y: -10,
                 floating: true,
                 borderWidth: 1,
                 backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor || '#FFFFFF'),
                 shadow: true
             },
             credits: {
                 enabled: false
             },
             series: [{
	              		name: '小学',
	                	data: xiaoxueTemp_
            		},{
	            	 	name: '初中',
	                 	data: chouzhongTemp_
            		},{
		             	 name: '高中',
		                 data: gaozhongTemp_
	             	},{
	             		name: '总数',
	             		data: zongsuTemp_
             		}]
         });
    }
    
    function checkAll(){
    	if ($("#checkboxAll").prop("checked")){
            $('input[name="stageScope"]').prop("checked",true);
        }else{
            $('input[name="stageScope"]').prop("checked",false);
        }
    }
    
    function schoolStatisticsTemp(){
    	document.getElementById("schoolStatisticsForm").submit();
    }
   
</script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="学校统计分析" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>学校统计分析</h3>
					</div>
					<div class="content-widgets">
					<div class="widget-container">
					<form  id="schoolStatisticsForm" action="${pageContext.request.contextPath}/teach/schoolStatistics/getSchoolStatistics" method="post">
					<table class="responsive table table-striped table-bordered">
						<tbody>
<!-- 							<tr> -->
<!-- 								<td>统计项</td> -->
<!-- 								<td colspan="5"> -->
<!-- 									<input type="checkbox" id="checkboxAll" onclick="checkAll();">全部 &nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 									<input type="checkbox"  name="stageScope" value="2">小学 &nbsp;&nbsp; -->
<!-- 									<input type="checkbox"  name="stageScope" value="3">初中&nbsp;&nbsp; -->
<!-- 									<input type="checkbox"  name="stageScope" value="4">高中 -->
<!-- 								</td> -->
<!-- 							</tr> -->
							<tr><td>统计项</td><td><input type="radio" checked="checked">个数</td></tr>
							<tr>
								<td>统计图</td>
								<td colspan="5">
									<input type="radio" checked="checked">柱形图
								</td>
							</tr>
							<tr>
								<td>统计区域</td>
								<td colspan="5">
										${cityName }
<!-- 									<select id="province" name="province"></select> -->
<!-- 									<select id="city" name="city"></select> -->
<!-- 									<select id="district" name="district"></select> -->
<!-- 									<button onclick="schoolStatisticsTemp();" type="button" class="btn btn-warning">确定</button> -->
	<!-- 							<button onclick="javascript:void(0)" type="button" class="btn btn-success">导出Excel</button> -->
								</td>
						   </tr>
						</tbody>
					</table>
					</form>
					<div id="container1"  style="min-width: 150px; max-width: 200px; height: 100px; margin: 0 75%;border:1px solid ;border-color:#909090; box-shadow: 0 0 3px #909090; strokeWidth:1; stroke:#909090; stroke-width:1; fill:#FFFFFF;visibility:visible">
					  <p style="padding-top: 18px; padding-bottom: 5px;" ><span style="background-color:#4994B4;color:#FFFFFF;font-size: 14px;padding:3px 9px ">备注</span></p>
					  <p>小学+初中 ：<span style="color:red">${xiaoxue_chuzhong }</span> 所</p>
					  <p>初中+高中 ：<span style="color:red">${chuzhong_gaozhong }</span> 所</p>
					  <p>小学+初中+高中 ：<span style="color:red">${xiaoxue_chuzhong_gaozhong }</span> 所</p>
                    </div>
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
