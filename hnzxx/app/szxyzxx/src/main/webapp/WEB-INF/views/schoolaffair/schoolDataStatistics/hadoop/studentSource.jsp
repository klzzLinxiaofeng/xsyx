<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="job"></div>

<script type="text/javascript">
var myChart_6 = echarts.init(document.getElementById('job'));
//学生来源统计
function getStudentSourceData(areaCode){
	var url = "";
	if(page == "school"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/student/getStudentSourceData";
	}else if(page == "area"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/student/getStudentSourceData?sub=all";
	} 
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			studentSourceData(returnData);
		}
	}, 'json');
	
}

function studentSourceData(returnData){
	
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
	
	if(xArr.length == 0){
		xArr = ["正常入学","借读","其他"];
		data = '[{"name":"正常入学", "value":"0"},{"name":"借读", "value":"0"},{"name":"其他", "value":"0"}]';
		data = JSON.parse(data);
	}
	
// 	var myChart = echarts.init(document.getElementById('job'));
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
	    color: ['#91c7ae','#c23531','#2f4554']
	};
	myChart_6.setOption(option);
	
}

</script>