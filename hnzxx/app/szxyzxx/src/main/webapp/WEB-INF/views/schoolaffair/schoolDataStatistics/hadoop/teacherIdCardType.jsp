<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <div id="papers"></div>
 
 
<script>
//证件类型统计
// getIdCardTypeData("440113");
var myChart_2 = echarts.init(document.getElementById('papers'));

function getIdCardTypeData(areaCode){
	var url = "";
	if(page == "school"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getIdCardTypeData";
	}else if(page == "area"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getIdCardTypeData?sub=all";
	} 
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			idCardTypeData(returnData);
		}
	}, 'json');
}

function idCardTypeData(returnData){
	
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
// 		xArr = ["居民身份证"];
// 		data = '[{"name":"居民身份证", "value":"0"}]';
// 		data = JSON.parse(data);
// 	}
	
	myChart_2 = echarts.init(document.getElementById('papers'));
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
	            startAngle:0, //旋转
	            radius : ['30%', '60%'],
	            center : ['50%', '55%'],
// 	            roseType : 'area',
	            data: data
	        }
	    ]
	};
	myChart_2.setOption(option);
	
	if(xArr.length == 0){
		$("#papers").empty();
		$("#papers").append("<p style='text-align:center;line-height:9;font-size:35px;font-family:微软雅黑;color:#999;'>暂无数据</p>");
	}
}

</script>