<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="proportion"></div>

<script type="text/javascript">
//男女比例统计
// getSexData("440113");
var myChart_7 = echarts.init(document.getElementById('proportion'));

function getSexData(areaCode){
	var url = "";
	if(type == "teacher"){
		if(page == "school"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getSexData";
		}else if(page == "area"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getSexData?sub=all";
		} 
	}else if(type == "student"){
		if(page == "school"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/student/getSexData";
		}else if(page == "area"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/student/getSexData?sub=all";
		} 
	}
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			sexData(returnData);
		}
	}, 'json');
}

function sexData(returnData){
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
	
	

// 	var myChart = echarts.init(document.getElementById('proportion'));
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
	        data: ['男','女']
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
	    color: ['#104E8B','#FF6EB4']
	};
	myChart_7.setOption(option);

	
}
</script>