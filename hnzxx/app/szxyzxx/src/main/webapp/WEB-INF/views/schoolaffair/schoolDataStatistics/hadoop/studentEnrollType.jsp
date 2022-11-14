<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="entrance"></div>

<script type="text/javascript">
var myChart_4 = echarts.init(document.getElementById('entrance'));
//入学方式统计
function getEnrollTypeData(areaCode){
	var url = "";
	if(page == "school"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/student/getEnrollTypeData";
	}else if(page == "area"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/student/getEnrollTypeData?sub=all";
	} 
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			enrollTypeData(returnData);
		}
	}, 'json');
	
}

function enrollTypeData(returnData){
	
// 	var data = "[";
// 	var xArr = [];
// 	var yArr = [];
// 	var ynumber = 0;	
// 	$.each(returnData, function(i,v){
// 		xArr.push(v.name);
// 		yArr.push(v.number);
// 		ynumber += v.number;
// 		data += '{"name":"'+v.name+'", "value":"'+v.number+'"},'
// 	});
// 	if(data != "["){
// 		data = data.substring(0, data.length-1) + "]";
// 		data = JSON.parse(data);
// 	}else{
// 		data = "";
// 	}	
	var xArr = ["高中阶段其他", "义务教育\n阶段其他", "文艺特招", "体育特招", "统一招生考试\n/普通入学", "就近入学"];
	var kv1 = 0;
	var kv2 = 0;
	var kv3 = 0;
	var kv4 = 0;
	var kv5 = 0;
	var kv6 = 0;
	$.each(returnData, function(i,v){
		switch(v.name){
		case "高中阶段其他":
			kv1 = v.number;
			break;
		case "义务教育阶段其他":
			kv2 = v.number;
			break;
		case "文艺特招":
			kv3 = v.number;
			break;
		case "体育特招":
			kv4 = v.number;
			break;
		case "统一招生考试/普通入学":
			kv5 = v.number;
			break;
		case "就近入学":
			kv6 = v.number;
			break;
		}
	});
	var yArr = [kv1, kv2, kv3, kv4, kv5, kv6];
	
// 	var myChart = echarts.init(document.getElementById('entrance'));
	option = {
		    title: {
		    },
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'shadow'
		        }
		    },
		    legend: {
		        data: []
		    },
		    grid: {
		        left: '3%',
		        right: '6%',
		        bottom: '3%',
		        top:'8%',
		        containLabel: true
		    },
		    xAxis: {
		        type: 'value',
		        boundaryGap: [0, 0]
		    },
		    yAxis: {
		        type: 'category',
		        data: xArr
		    },
		    series: [
		        {
		            name: '人数',
		            type: 'bar',
		            barWidth:'null',
		            data: yArr
		        }
		    ]
		};

	myChart_4.setOption(option);
}

</script>