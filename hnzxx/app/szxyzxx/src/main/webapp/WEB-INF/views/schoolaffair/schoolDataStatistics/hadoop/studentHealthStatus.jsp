<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="papers"></div>

<script type="text/javascript">
var myChart_3 = echarts.init(document.getElementById('papers'));
//健康状况统计
function getHealthStatusData(areaCode){
	var url = "";
	if(page == "school"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/student/getHealthStatusData";
	}else if(page == "area"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/student/getHealthStatusData?sub=all";
	} 
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			healthStatusData(returnData);
		}
	}, 'json');
	
}

function healthStatusData(returnData){
	
	var data = "[";
	var xArr = [];
	var yArr = [];
	var ynumber = 0;	
	$.each(returnData, function(i,v){
		xArr.push(v.name);
		yArr.push(v.number);
		ynumber += v.number;
		data += '{"name":"'+v.name+'", "value":"'+v.number+'"},'
	});
	if(data != "["){
		data = data.substring(0, data.length-1) + "]";
		data = JSON.parse(data);
	}else{
		data = "";
	}	
	
	
// 	var myChart = echarts.init(document.getElementById('papers'));
	option = {
	    title : {
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a}{b} : {c} ({d}%)"
	    },
	    legend: {
	    	left:'10px',
		    top:'10px',
	        itemWidth: 15,
	        itemHeight: 5,
	        data: xArr
	    },
	    series : [
	        {
	            name: '',
	            type: 'pie',
	            startAngle:-30,
	            radius : '55%',
	            center: ['50%', '55%'],
	            data: data,
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ],
	};
	myChart_3.setOption(option);
}

</script>