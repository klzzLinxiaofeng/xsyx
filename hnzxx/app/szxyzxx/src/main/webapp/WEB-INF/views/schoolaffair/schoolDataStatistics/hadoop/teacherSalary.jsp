<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="wages"></div>
 
 
<script>
//工资统计
// getSalaryData("440113");
var myChart_4 = echarts.init(document.getElementById('wages'));

function getSalaryData(areaCode){
	var url = "";
	if(page == "school"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getSalaryData";
	}else if(page == "area"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getSalaryData?sub=all";
	} 
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			salaryData(returnData);
		}
	}, 'json');
}

function salaryData(returnData){
	
// 	var data = "[";
// 	var xArr = [];
// 	var yArr = [];
// 	var ynumber = 0;	//总人数
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
// 	var xArr = ["2000\n~5000", "5000\n~8000", "8000\n~12000", "12000\n~15000", "15000\n~18000", "18000以上"];
	var xArr = ["2000\n~5000", "5000\n~8000", "8000\n~12000", "12000\n~15000", "15000以上"];
	var kv1 = 0;
	var kv2 = 0;
	var kv3 = 0;
	var kv4 = 0;
	var kv5 = 0;
	var kv6 = 0;
	$.each(returnData, function(i,v){
		switch(v.name){
		case "2000~5000":
			kv1 = v.number;
			break;
		case "5000~8000":
			kv2 = v.number;
			break;
		case "8000~12000":
			kv3 = v.number;
			break;
		case "12000~15000":
			kv4 = v.number;
			break;
		case "15000~18000":
			kv5 = v.number;
			break;
		case "18000以上":
			kv6 = v.number;
			break;
		}
	});
	var yArr = [kv1, kv2, kv3, kv4, kv5+kv6];
	
// 	var myChart = echarts.init(document.getElementById('wages'));
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
	        top:'15%',
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
	            name:'',
	        }
	    ],
	    series : [
	        {
	            name:'人数',
	            type:'bar',
	            itemStyle: {
	                    normal: {
	                        label: {
	                            show: true,
	                            position: 'top',
	                            formatter: '{c}人',
	                        }
	                    }
	                },
	            barWidth: '50%',
	            data: yArr
	        }
	    ]
	};
	myChart_4.setOption(option);

}

</script>