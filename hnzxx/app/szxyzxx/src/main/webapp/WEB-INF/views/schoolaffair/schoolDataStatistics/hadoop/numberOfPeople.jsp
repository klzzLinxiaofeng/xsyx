<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/views/embedded/common.jsp"%>
<div id="main" class="numberof"></div>

<script type="text/javascript">
// getNumberOfPeopleData("440113");
var myChart_0 = echarts.init(document.getElementById('main'));
//人数统计
function getNumberOfPeopleData(areaCode){
	var url = "";
	if(type == "teacher"){
		if(page == "school"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getNumberOfPeopleData?sub=grade";
		}else if(page == "area"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/teacher/getNumberOfPeopleData?sub=school";
		} 
	}else if(type == "student"){
		if(page == "school"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/student/getNumberOfPeopleData?sub=grade";
		}else if(page == "area"){
			url = "${ctp}/schoolaffair/schoolDataStatistics/student/getNumberOfPeopleData?sub=school";
		} 
	}
	
	$.post(url, {"areaCode":areaCode}, function(returnData, status) {
		if ("success" === status) {
			numberOfPeopleData(returnData, areaCode);
		}
	}, 'json');
}

function numberOfPeopleData(returnData, areaCode){
	var dataArr = [];
	var xArr = [];
	var yArr = [];
	var ynumber = 0;	//总人数
	var yMax = 0;		//最大值，用于设定阴影
	var yMaxArr =[];
	$.each(returnData, function(i,v){
		dataArr.push([v.name, v.number]);
		xArr.push(v.name);
		yArr.push(v.number);
		ynumber += v.number;
		if(v.number >= yMax){
			yMax = v.number;
		}
	});
	
	for(var i=0; i<xArr.length; i++){
		yMaxArr.push(yMax);
	}
	
	if(type == "teacher"){
		if(page == "school"){
			$("#x_nop").text("年级总数：" + xArr.length);
		}else if(page == "area"){
			$("#x_nop").text("学校总数：" + xArr.length);
		}
		$("#y_nop").text("老师总数：" + ynumber);
	}else if(type == "student"){
		if(page == "school"){
			$("#x_nop").text("年级总数：" + xArr.length);
		}else if(page == "area"){
			$("#x_nop").text("学校总数：" + xArr.length);
		}
		$("#y_nop").text("学生总数：" + ynumber);
	}
	
	myChart_0 = echarts.init(document.getElementById('main'));

	option = {
	    title: {
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '2%',
	        right: '2%',
	        bottom: '10%',
	        top:'15%',
	        containLabel: true
	    },
	    xAxis: {
	        data: xArr,
	        axisLabel: {
	            inside: true,
	            textStyle: {
	                color: '#ccc'
	            }
	        },
	        axisTick: {
	            show: false
	        },
	        axisLine: {
	            show: false
	        },
	        z: 10
	    },
	    yAxis: {
	        axisLine: {
	            show: false
	        },
	        axisTick: {
	            show: false
	        },
	        axisLabel: {
	            textStyle: {
	                color: '#999'
	            }
	        }
	    },
	    dataZoom: [
	        {
	            type: 'inside'
	        }
	    ],
	    series: [
// 	        { // For shadow
// 	            type: 'bar',
// 	            itemStyle: {
// 	                normal: {color: 'rgba(0,0,0,0.05)'}
// 	            },
// 	            barGap:'-100%',
// 	            barCategoryGap:'40%',
// 	            data: yMaxArr,
// 	            animation: false
// 	        },
	        {	
	        	name: '人数',
	            type: 'bar',
	            itemStyle: {
	                normal: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: '#83bff6'},
	                            {offset: 0.5, color: '#188df0'},
	                            {offset: 1, color: '#188df0'}
	                        ]
	                    )
	                },
	                emphasis: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: '#2378f7'},
	                            {offset: 0.7, color: '#2378f7'},
	                            {offset: 1, color: '#83bff6'}
	                        ]
	                    )
	                }
	            },
	            data: yArr
	        }
	    ]
	};
	myChart_0.setOption(option);
	var zoomSize = 2;
	myChart_0.on('click', function (params) {
	    myChart_0.dispatchAction({
	        type: 'dataZoom',
	        startValue: xArr[Math.max(params.dataIndex - zoomSize / 2, 0)],
	        endValue: xArr[Math.min(params.dataIndex + zoomSize / 2, yArr.length - 1)]
	    });
	});
	/* myChart.on('click', function (params) {
		$.initWinOnTopFromTop("", "${pageContext.request.contextPath}/schoolaffair/schoolDataStatistics/enlargement?type="+
				type+"&page="+page+"&areaCode="+areaCode, '1800', '650');
	}); */
	
	//无数据时展示
	if(xArr.length == 0){
		$("#main").empty();
		$("#main").append("<p style='text-align:center;line-height:6;font-size:35px;font-family:微软雅黑;color:#999;'>暂无数据</p>");
	}
}
</script>