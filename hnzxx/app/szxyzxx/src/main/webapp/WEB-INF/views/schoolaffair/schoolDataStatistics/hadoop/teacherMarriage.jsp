<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="marriage"></div>
 
 
<script>
//婚姻状态统计
// getMarriageData("440113");
var myChart_5 = echarts.init(document.getElementById('marriage'));

function getMarriageData(areaCode){
	var url = "";
	if(page == "school"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getMarriageData";
	}else if(page == "area"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getMarriageData?sub=all";
	} 
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			marriageData(returnData);
		}
	}, 'json');
}

function marriageData(returnData){
	
	var data = "[";
	var xArr = [];
	var yArr = [];
	var ynumber = 0;	//总人数
	$.each(returnData, function(i,v){
		xArr.push(v.name);
		yArr.push(v.number);
		ynumber += v.number;
	});
	
	$.each(returnData, function(i,v){
		data += '{"name":"'+v.name+'", "value":"'+Math.round(v.number/ynumber*100)+'"},'
	});
	
	
	if(data != "["){
		data = data.substring(0, data.length-1) + "]";
		data = JSON.parse(data);
	}else{
		data = "";
	}
	
// 	if(xArr.length == 0){
// 		xArr = ["未婚"];
// 		data = '[{"name":"未婚", "value":"100"}]';
// 		data = JSON.parse(data);
// 	}
	

	myChart_5 = echarts.init(document.getElementById('marriage'));
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
	        itemWidth: 15,
	        itemHeight: 5,
	        left:'10px',
	        top:'10px',
	        data: xArr
	    },
	    calculable: true,
	    series: [
	        {
	            name:'',
	            type:'funnel',
// 	            left: '10%',
	            top: 50,
	            bottom: 20,
	            sort: 'descending',
// 	            width: '80%',
// 	            min: 0,
// 	            max: 50,
	            minSize: '0%',
	            maxSize: '80%',
// 	            gap: 2,
	            label: {
	                normal: {
	                    show: true,
	                    position: 'right'
	                },
	                emphasis: {
	                    textStyle: {
	                        fontSize: 20
	                    }
	                }
	            },
	            labelLine: {
	                normal: {
	                    length: 10,
	                    lineStyle: {
	                        width: 1,
	                        type: 'solid'
	                    }
	                }
	            },
	            itemStyle: {
	                normal: {
	                    borderColor: '#fff',
	                    borderWidth: 1
	                }
	            },
	            data: data
	        }
	    ]
	};
	myChart_5.setOption(option);

	if(xArr.length == 0){
		$("#marriage").empty();
		$("#marriage").append("<p style='text-align:center;line-height:9;font-size:35px;font-family:微软雅黑;color:#999;'>暂无数据</p>");
	}
}

</script>