<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="marriage"></div>

<script type="text/javascript">
var myChart_5 = echarts.init(document.getElementById('marriage'));
//就读方式统计
function getAttendSchoolTypeData(areaCode){
	var url = "";
	if(page == "school"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/student/getAttendSchoolTypeData";
	}else if(page == "area"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/student/getAttendSchoolTypeData?sub=all";
	} 
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			attendSchoolTypeData(returnData);
		}
	}, 'json');
	
}

function attendSchoolTypeData(returnData){
	
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
		xArr = ["走读","住校"];
		data = '[{"name":"走读", "value":"0"},{"name":"住校", "value":"0"}]';
		data = JSON.parse(data);
	}
	
	
// 	var myChart = echarts.init(document.getElementById('marriage'));
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
	    color: ['#91c7ae','#c23531']
	};
	myChart_5.setOption(option);
}

</script>