<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="funnel"></div>
 
 
 <script type="text/javascript">
//学位统计
// getQualificationData("440113");
var myChart_1 = echarts.init(document.getElementById('funnel'));

function getQualificationData(areaCode){
	var url = "";
	if(page == "school"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getQualificationData";
	}else if(page == "area"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getQualificationData?sub=all";
	} 
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			qualificationData(returnData);
		}
	}, 'json');
	
}
function qualificationData(returnData){
	var dataArr = [];
	var xArr = [];
	var yArr = [];
	var ynumber = 0;	//总人数
	$.each(returnData, function(i,v){
		dataArr.push([v.name, v.number]);
		xArr.push(v.name);
		yArr.push(v.number);
		ynumber += v.number;
	});
	
	var data = "[";
	$.each(returnData,function(i, v){
		data += '{"name":"'+v.name+'", "value":"'+Math.round(v.number/ynumber*100)+'"},'
	});
	if(data != "["){
		data = data.substring(0, data.length-1) + "]";
		data = JSON.parse(data);
	}else{
		data = "";
	}
	
// 	if(xArr.length == 0){
// 		xArr = ["大学本科毕业"];
// 		data = '[{"name":"大学本科毕业", "value":"100"}]';
// 		data = JSON.parse(data);
// 	}
	
	myChart_1 = echarts.init(document.getElementById('funnel'));
	option = {
	    title: {
	    },
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a}{b} : {c}%"
	    },
	    toolbox: {
	    },
	    legend: {
	        orient: 'vertical',
	        left:'10px',
		    top:'10px',
	        itemWidth: 15,
	        itemHeight: 5,
	        data: xArr
	    },
	    calculable: true,
	    series: [
	        {
	            name: '',
	            type:'funnel',
	            sort: 'ascending',
	            maxSize: '80%',  //数值最大值时所占宽度
// 	            minSize: '10%',  //数值最小值宽度，设置后最小值的图形并不是尖端三角
	            top: 50,
	            bottom: 20,
	            label: {
	                normal: {
	                    position: 'right'
	                }
	            },
	            data:data
	        }
	    ]
	};
	myChart_1.setOption(option);
	
	if(xArr.length == 0){
		$("#funnel").empty();
		$("#funnel").append("<p style='text-align:center;line-height:9;font-size:35px;font-family:微软雅黑;color:#999;'>暂无数据</p>");
	}
}


</script>