<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="funnel"></div>

<script type="text/javascript">
var myChart_2 = echarts.init(document.getElementById('funnel'));
//籍贯统计
function getNativePlaceData(areaCode){
	var url = "";
	if(page == "school"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/student/getNativePlaceData";
	}else if(page == "area"){
		url = "${ctp}/schoolaffair/schoolDataStatistics/student/getNativePlaceData?sub=all";
	} 
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			nativePlaceData(returnData);
		}
	}, 'json');
	
}

function nativePlaceData(returnData){
	
	var data = "[";
	var xArr = [];
	var yArr = [];
	var ynumber = 0;	
	var num = 0;
	$.each(returnData, function(i,v){
		if(v.name != null && v.name != "" && num<10){
			num ++;
			xArr.push(v.name);
			yArr.push(v.number);
			ynumber += v.number;
			data += '{"name":"'+v.name+'", "value":"'+v.number+'"},'
		}
	});
	
	if(returnData.length > 10){
		$("#native_explain").show();
	}else{
		$("#native_explain").hide();
	}
	
	if(data != "["){
		data = data.substring(0, data.length-1) + "]";
		data = JSON.parse(data);
	}else{
		data = "";
	}	
	
	myChart_2 = echarts.init(document.getElementById('funnel'));
	option = {
		    title: {
		    },
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a}{b} : {c} ({d}%)"
		    },
		    toolbox: {
		    },
		    legend: {
// 		        orient: 'vertical',
		        itemWidth: 15,
		        itemHeight: 5,
		        left:'10px',
		        top:'10px',
		        data: xArr
		    },
		    calculable: true,
		    series: [
// 		        {
// 		            name: '',
// 		            type:'funnel',
// 		            width: '40%',
// 		            height: '45%',
// 		            left: '40%',
// 		            top: '5%',
// 		            label: {
// 		                normal: {
// 		                    position: 'left'
// 		                }
// 		            },
// 		            data: data
// 		        },
// 		        {
// 		            name: '',
// 		            type:'funnel',
// 		            width: '40%',
// 		            height: '45%',
// 		            left: '40%',
// 		            top: '50%',
// 		            sort: 'ascending',
// 		            label: {
// 		                normal: {
// 		                    position: 'left'
// 		                }
// 		            },
// 		            data: data
// 		        }
				{
				    name:'',
				    type:'pie',
				    startAngle:0,
				    radius : ['30%', '60%'],
		            center : ['50%', '55%'],
				    roseType : 'radius',
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
				                show : true
				            },
				            labelLine : {
				                show : true
				            }
				        }
				    },
				    data: data
				}
		    ]
		};
	myChart_2.setOption(option);
	
	if(xArr.length == 0){
		$("#funnel").empty();
		$("#funnel").append("<p style='text-align:center;line-height:9;font-size:35px;font-family:微软雅黑;color:#999;'>暂无数据</p>");
	}
}

</script>