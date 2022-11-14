<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="age"></div>

<script type="text/javascript">
//年龄统计
// getAgeData();
var myChart_8 = echarts.init(document.getElementById('age'));

function getAgeData(areaCode){
	var url = "";
	if(type == "teacher"){
		if(page == "school"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getAgeData";
		}else if(page == "area"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getAgeData?sub=all";
		} 
	}else if(type == "student"){
		if(page == "school"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/student/getAgeData";
		}else if(page == "area"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/student/getAgeData?sub=all";
		} 
	}
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			ageData(returnData);
		}
	}, 'json');
}

function ageData(returnData){
// 	var data = "[";
// 	var xArr = [];
// 	var yArr = [];
// 	var ynumber = 0; 
// 	$.each(returnData, function(i,v){
// 		if(v.name != "0"){
// 			xArr.push(v.name);
// 			yArr.push(v.number);
// 			ynumber += v.number;
// 			data += '{"name":"'+v.name+'", "value":"'+v.number+'"},'
// 		}
// 	});
	
// 	if(data != "["){
// 		data = data.substring(0, data.length-1) + "]";
// 		data = JSON.parse(data);
// 	}else{
// 		data = "";
// 	}
	var xArr = [];
	if(type == "teacher"){
		xArr = ["20以下", "20~30", "30~40", "40~50", "50~60", "60以上"];
	}
	if(type == "student"){
		xArr = ["7以下", "7~10", "10~15", "15~20", "20~25", "25以上"];
	}
	var kv1 = 0;
	var kv2 = 0;
	var kv3 = 0;
	var kv4 = 0;
	var kv5 = 0;
	var kv6 = 0;
	$.each(returnData, function(i,v){
		if(type == "teacher"){
			switch(v.name){
				case "20以下":
					kv1 = v.number;
					break;
				case "20~30":
					kv2 = v.number;
					break;
				case "30~40":
					kv3 = v.number;
					break;
				case "40~50":
					kv4 = v.number;
					break;
				case "50~60":
					kv5 = v.number;
					break;
				case "60以上":
					kv6 = v.number;
					break;
			}
		}
		if(type == "student"){
			switch(v.name){
			case "7以下":
				kv1 = v.number;
				break;
			case "7~10":
				kv2 = v.number;
				break;
			case "10~15":
				kv3 = v.number;
				break;
			case "15~20":
				kv4 = v.number;
				break;
			case "20~25":
				kv5 = v.number;
				break;
			case "25以上":
				kv6 = v.number;
				break;
			}
		}
	});
	var yArr = [kv1, kv2, kv3, kv4, kv5, kv6];
	
	
// 	var myChart = echarts.init(document.getElementById('age'));

	option = {
	    color: ['#3398DB'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '2%',
	        right: '2%',
	        bottom: '10%',
	        top:'10%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : xArr,
	            axisTick: {
	                alignWithLabel: true
	            }
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	        }
	    ],
	    series : [
	        {
	            name:'人数',
	            type:'bar',
	            itemStyle: {
	                    normal: {
	                        color: function(params) {
	                            var colorList = [
	                              '#e92f3c','#2d247d','#f6b000','#069cb4', "#00EE00", "#BCD2EE",
	                            ];
	                            return colorList[params.dataIndex]
	                        },
	                        label: {
	                            show: true,
	                            position: 'top',
	                            formatter: '{c}人'
	                        }
	                    }
	                },
	            barWidth: '50%',
	            data: yArr
	        }
	    ],
	};
	myChart_8.setOption(option);
	
}
</script>