<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="nation"></div>

<script type="text/javascript">
//民族统计
// getRaceData("440113");
var myChart_10 = echarts.init(document.getElementById('nation'));
	
function getRaceData(areaCode){
	var url = "";
	if(type == "teacher"){
		if(page == "school"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getRaceData";
		}else if(page == "area"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getRaceData?sub=all";
		} 
	}else if(type == "student"){
		if(page == "school"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/student/getRaceData";
		}else if(page == "area"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/student/getRaceData?sub=all";
		} 
	}
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			raceData(returnData);
		}
	}, 'json');
}

function raceData(returnData){
	var data = "[";
	var xArr = [];
	var yArr = [];
	var ynumber = 0;	//总人数
	$.each(returnData, function(i,v){
		if(i<10){
			xArr.push(v.name);
			yArr.push(v.number);
			ynumber += v.number;
			data += '{"name":"'+v.name+'", "value":"'+v.number+'"},'
		}
	});
	
	if(returnData.length > 10){
		$("#race_explain").show();
	}else{
		$("#race_explain").hide();
	}
	
	if(data != "["){
		data = data.substring(0, data.length-1) + "]";
		data = JSON.parse(data);
	}else{
		data = "";
	}
	
	//空数据处理
// 	if(xArr.length == 0){
// 		xArr = ["汉族"];
// 		data = '[{"name":"汉族", "value":"0"}]';
// 	}
	

	myChart_10 = echarts.init(document.getElementById('nation'));
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
	    toolbox: {
	        show : false,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {
	                show: true,
	                type: ['pie', 'funnel']
	            },
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    series : [
	        {
	            name:'',
	            type:'pie',
	            radius : ['30%', '60%'],
	            center : ['50%', '55%'],
	            roseType : 'radius',
	            startAngle:0,
	            data: data
	        }
	    ]

	};
	myChart_10.setOption(option);
	
	if(xArr.length == 0){
		$("#nation").empty();
		$("#nation").append("<p style='text-align:center;line-height:9;font-size:35px;font-family:微软雅黑;color:#999;'>暂无数据</p>");
	}
}
</script>