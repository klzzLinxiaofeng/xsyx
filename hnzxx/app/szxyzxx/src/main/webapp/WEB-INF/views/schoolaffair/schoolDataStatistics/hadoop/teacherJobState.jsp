<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="job"></div>
 
 
<script>
//在职状态统计
// getJobStateData("440113");
var myChart_6 = echarts.init(document.getElementById('job'));

function getJobStateData(areaCode){
	var url = "";
	if(page == "school"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getJobStateData";
	}else if(page == "area"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getJobStateData?sub=all";
	} 
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			jobStateData(returnData);
		}
	}, 'json');
}

function jobStateData(returnData){
	
	var data = "[";
	var xArr = [];
	var yArr = [];
	var ynumber = 0;	//总人数
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
	
// 	console.log(xArr);
// 	console.log(yArr);
// 	console.log(data);
// 	if(xArr.length == 0){
// 		xArr = ["在职"];
// 		data = '[{"name":"在职", "value":"0"}]';
// 		data = JSON.parse(data);
// 	}


	myChart_6 = echarts.init(document.getElementById('job'));
	option = {
	    title : {
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a}{b} : {c} ({d}%)"
	    },
	    legend: {
// 	    	orient: 'vertical',
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
	            radius : '55%',
	            center: ['50%', '55%'],
	            data: data,
	            startAngle:0,
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
	myChart_6.setOption(option);
	
	if(xArr.length == 0){
		$("#job").empty();
		$("#job").append("<p style='text-align:center;line-height:9;font-size:35px;font-family:微软雅黑;color:#999;'>暂无数据</p>");
	}
}

</script>