<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="post"></div>
 
 
 <script type="text/javascript">
//岗位编制统计
// getPostStaffingData("440113");
var myChart_3 = echarts.init(document.getElementById('post'));

function getPostStaffingData(areaCode){
	var url = "";
	if(page == "school"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getPostStaffingData";
	}else if(page == "area"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getPostStaffingData?sub=all";
	} 
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			postStaffingData(returnData);
		}
	}, 'json');
	
}
function postStaffingData(returnData){
	var dataArr = [];
	var xArr = [];
	var yArr = [];
	var ynumber = 0;	
	var data = "[";
	$.each(returnData, function(i,v){
		dataArr.push([v.name, v.number]);
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
	
// 	if(xArr.length == 0){
// 		xArr = ["教学类", "行政类", "教辅类", "工勤类"];
// 		data = '[{"name":"教学类", "value":"0"},{"name":"行政类", "value":"0"},{"name":"教辅类", "value":"0"},{"name":"工勤类", "value":"0"}]';
// 		data = JSON.parse(data);
// 	}
	
	myChart_3 = echarts.init(document.getElementById('post'));
	option = {
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	    	left:'10px',
		    top:'10px',
	        itemWidth: 15,
	        itemHeight: 5,
	        data: xArr
	    },
	    toolbox: {
	    },
	    calculable : true,
	    series : [
	        {
	            name:'岗位编制',
	            type:'pie',
	            radius : ['30%', '60%'],
	            center : ['50%', '55%'],
	            itemStyle : {
	                normal : {
	                    label : {
	                        show : false
	                    },
	                    labelLine : {
	                        show : false
	                    }
	                },
	                emphasis : {
	                    label : {
	                        show : true,
	                        position : 'center',
	                        textStyle : {
	                            fontSize : '20',
	                            fontWeight : 'bold'
	                        }
	                    }
	                }
	            },
	            data: data
	        }
	    ]
	};
	myChart_3.setOption(option);
	
	if(xArr.length == 0){
		$("#post").empty();
		$("#post").append("<p style='text-align:center;line-height:9;font-size:35px;font-family:微软雅黑;color:#999;'>暂无数据</p>");
	}
}


</script>