<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sca:getDefaultSchoolName()}</title>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/modules/exporting.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/extra/sx_statistics.css">
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script> --%>
<style>
	.highcharts-container{margin:0 auto;width:100%}
</style>
</head>
<body>
<script type="text/javascript">
/* $(document).ready(function() {  
	$("#outnumber,#number").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
});  */
var colorArr = ["#e9573f","#f6bb42","#37bc9b","#3bafda"];
// var commonGroupArray = null;
$(function(){
	//初始化数据
	getStudentNumberByAge();
	getStudentNumberOfSexData();
// 	getStudentNumberOfRaceData();
// 	getStudentNumberOfPoliticsData();
	getStudentNumberOfIsBoardedData();
	getStudentNumberOfGradeData();
	
    $(".statistics_page").hide();//隐藏wenben
    $(".statistics_page:eq(0)").show();//显示第一个wenben
    $("#fenye_statistics a").click(function(){
        $(".statistics_xuanx").removeClass("statistics_xuanx");//移除样式
        $(this).addClass("statistics_xuanx");//添加样式
        var i=$(this).index();//获得下标
        $(".statistics_page").hide();//隐藏文本
        $(".statistics_page:eq("+i+")").show();//显示第i个文本
    });

// 	//少先队员
//     $('#container_1').highcharts({
//         chart: {
//             type: 'column'
//         },
//         title: {
//             text: '年龄统计'
//         },
//         credits:{
//         enabled:false // 禁用版权信息
//         },
//         legend: {
//             enabled: false// 关闭图例
//         },
//         exporting: {
//             enabled:false
// 		},
//         xAxis: {
//             categories: [
//                 '12岁以下',
//                 '13-14',
//                 '14-15',
//                 '15岁以上',
//             ],
//             tickWidth:false,
//             lineColor:"#323232",
//             lineWidth:2,
//             labels:{
//                 style:{
//                     color:"#323232",
//                     fontSize:"12px",
//                 },
//             },
//         },
//         yAxis: {
//             min: 0,
//             title: {
//                 text: ''
//             },
//             labels:{
//                 style:{
//                     color:"#fff",
//                 },
//             },
//             gridLineColor: false,
//         },
//         tooltip: {
//             headerFormat: '<span style="font-size:10px">{point.key}</span><br><table style="border:none;">',
//             pointFormat: '<tr>' +
//                 '<td style="padding:0;border:none;"><b>{point.y:f} 人</b></td></tr>',
//             footerFormat: '</table>',
//             shared: true,
//             //useHTML: true
//         },
//         plotOptions: {
//             column: {
//                 pointPadding: 0.2,
//                 borderWidth: 0
//             }
//         },
//         series: [{
//             name: '',
//                 data: [{
//                 y:19,
//                 color:"#e9573f"},
//             {
//                 y:29,
//                 color:"#f6bb42"},
//             {
//                 y:39,
//                 color:"#37bc9b"},
//             {
//                 y:49,
//                 color:"#3bafda"},]
//         }]
//     });

// 	//团员
//     $('#container_2').highcharts({
//         chart: {
//             type: 'column'
//         },
//         title: {
//             text: '年龄统计'
//         },
//         credits:{
//         enabled:false // 禁用版权信息
//         },
//         legend: {
//             enabled: false// 关闭图例
//         },
//         exporting: {
//             enabled:false
// 		},
//         xAxis: {
//             categories: [
//                 '12岁以下',
//                 '13-14',
//                 '14-15',
//                 '15岁以上',
//             ],
//             tickWidth:false,
//             lineColor:"#323232",
//             lineWidth:2,
//             labels:{
//                 style:{
//                     color:"#323232",
//                     fontSize:"12px",
//                 },
//             },
//         },
//         yAxis: {
//             min: 0,
//             title: {
//                 text: ''
//             },
//             labels:{
//                 style:{
//                     color:"#fff",
//                 },
//             },
//             gridLineColor: false,
//         },
//         tooltip: {
//             headerFormat: '<span style="font-size:10px">{point.key}</span><br><table style="border:none;">',
//             pointFormat: '<tr>' +
//                 '<td style="padding:0;border:none;"><b>{point.y:f} 人</b></td></tr>',
//             footerFormat: '</table>',
//             shared: true,
//             //useHTML: true
//         },
//         plotOptions: {
//             column: {
//                 pointPadding: 0.2,
//                 borderWidth: 0
//             }
//         },
//         series: [{
//             name: '',
//                 data: [{
//                 y:19,
//                 color:"#e9573f"},
//             {
//                 y:29,
//                 color:"#f6bb42"},
//             {
//                 y:39,
//                 color:"#37bc9b"},
//             {
//                 y:49,
//                 color:"#3bafda"},]
//         }]
//     });

// 	//华侨
//     $('#container_4').highcharts({
//         chart: {
//             type: 'column'
//         },
//         title: {
//             text: '年龄统计'
//         },
//         credits:{
//         enabled:false // 禁用版权信息
//         },
//         legend: {
//             enabled: false// 关闭图例
//         },
//         exporting: {
//             enabled:false
// 		},
//         xAxis: {
//             categories: [
//                 '12岁以下',
//                 '13-14',
//                 '14-15',
//                 '15岁以上',
//             ],
//             tickWidth:false,
//             lineColor:"#323232",
//             lineWidth:2,
//             labels:{
//                 style:{
//                     color:"#323232",
//                     fontSize:"12px",
//                 },
//             },
//         },
//         yAxis: {
//             min: 0,
//             title: {
//                 text: ''
//             },
//             labels:{
//                 style:{
//                     color:"#fff",
//                 },
//             },
//             gridLineColor: false,
//         },
//         tooltip: {
//             headerFormat: '<span style="font-size:10px">{point.key}</span><br><table style="border:none;">',
//             pointFormat: '<tr>' +
//                 '<td style="padding:0;border:none;"><b>{point.y:f} 人</b></td></tr>',
//             footerFormat: '</table>',
//             shared: true,
//             //useHTML: true
//         },
//         plotOptions: {
//             column: {
//                 pointPadding: 0.2,
//                 borderWidth: 0
//             }
//         },
//         series: [{
//             name: '',
//                 data: [{
//                 y:19,
//                 color:"#e9573f"},
//             {
//                 y:29,
//                 color:"#f6bb42"},
//             {
//                 y:39,
//                 color:"#37bc9b"},
//             {
//                 y:49,
//                 color:"#3bafda"},]
//         }]
//     });

// 	//港澳台
//     $('#container_5').highcharts({
//         chart: {
//             type: 'column'
//         },
//         title: {
//             text: '年龄统计'
//         },
//         credits:{
//         enabled:false // 禁用版权信息
//         },
//         legend: {
//             enabled: false// 关闭图例
//         },
//         exporting: {
//             enabled:false
// 		},
//         xAxis: {
//             categories: [
//                 '12岁以下',
//                 '13-14',
//                 '14-15',
//                 '15岁以上',
//             ],
//             tickWidth:false,
//             lineColor:"#323232",
//             lineWidth:2,
//             labels:{
//                 style:{
//                     color:"#323232",
//                     fontSize:"12px",
//                 },
//             },
//         },
//         yAxis: {
//             min: 0,
//             title: {
//                 text: ''
//             },
//             labels:{
//                 style:{
//                     color:"#fff",
//                 },
//             },
//             gridLineColor: false,
//         },
//         tooltip: {
//             headerFormat: '<span style="font-size:10px">{point.key}</span><br><table style="border:none;">',
//             pointFormat: '<tr>' +
//                 '<td style="padding:0;border:none;"><b>{point.y:f} 人</b></td></tr>',
//             footerFormat: '</table>',
//             shared: true,
//             //useHTML: true
//         },
//         plotOptions: {
//             column: {
//                 pointPadding: 0.2,
//                 borderWidth: 0
//             }
//         },
//         series: [{
//             name: '',
//                 data: [{
//                 y:19,
//                 color:"#e9573f"},
//             {
//                 y:29,
//                 color:"#f6bb42"},
//             {
//                 y:39,
//                 color:"#37bc9b"},
//             {
//                 y:49,
//                 color:"#3bafda"},]
//         }]
//     });
});
	
	function getStudentNumberByAge(){
		var loader = new loadLayer();
		loader.show();
		$.post("${ctp}/schoolaffair/schoolDataStatistics/getStudentNumberByAge", function(returnData, status) {
			if ("success" === status) {
				studentNumberByAge(returnData);
			}
			loader.close();
		},'json');
	}
	//性别学生数
	function getStudentNumberOfSexData() {
// 		var loader = new loadLayer();
// 		loader.show();
		$.post("${ctp}/schoolaffair/schoolDataStatistics/getStudentNumberOfSexData", function(returnData, status) {
			if ("success" === status) {
				studentSexNumber(returnData);
			}
// 			loader.close();
		},'json');
	}
// 	//民族学生数
// 	function getStudentNumberOfRaceData() {
// 		var loader = new loadLayer();
// 		loader.show();
// 		$.post("${ctp}/schoolaffair/schoolDataStatistics/getStudentNumberOfRaceData", function(returnData, status) {
// 			if ("success" === status) {
// 				studentRaceNumber(returnData);
// 			}
// 			loader.close();
// 		},'json');
// 	}
// 	//党派学生数
// 	function getStudentNumberOfPoliticsData() {
// 		var loader = new loadLayer();
// 		loader.show();
// 		$.post("${ctp}/schoolaffair/schoolDataStatistics/getStudentNumberOfPoliticsData", function(returnData, status) {
// 			if ("success" === status) {
// 				studentPoliticsDataNumber(returnData);
// 			}
// 			loader.close();
// 		},'json');
// 	}
	//走就读学生数
	function getStudentNumberOfIsBoardedData() {
// 		var loader = new loadLayer();
// 		loader.show();
		$.post("${ctp}/schoolaffair/schoolDataStatistics/getStudentNumberOfIsBoardedData", function(returnData, status) {
			if ("success" === status) {
				studentIsBoardedNumber(returnData);
			}
// 			loader.close();
		},'json');
	}
	//年级学生数
	function getStudentNumberOfGradeData() {
// 		var loader = new loadLayer();
// 		loader.show();
		$.post("${ctp}/schoolaffair/schoolDataStatistics/getStudentNumberOfGradeData", function(returnData, status) {
			if ("success" === status) {
				returnData = eval("(" + returnData + ")");
				studentGradeNumber(returnData);
			}
// 			loader.close();
		});
	}
	//年龄段人数柱状图实现
	function studentNumberByAge(data){
		var currentFlag = 0;
		var minVal = data.minVal + "" == "undefined" ? "0" : data.minVal;
		var minCount = data.min;
		var groupNum = data.groupNum;
		var groupArray = new Array();
		var groupValArray = new Array();
		groupArray.push(minVal + "岁以下");
		groupValArray.push({y:minCount,color:colorArr[0]})
		for(var i = minVal ; i < minVal + groupNum ; i++){
			if(minVal != i){
				if(i == minVal + groupNum -1){
					groupArray.push(minVal + i + "岁以上");
				}else{
					groupArray.push(minVal + i + "岁");
				}
				groupValArray.push({y:data[i],color:colorArr[currentFlag]});
			}
			currentFlag++;
		}
// 		commonGroupArray = groupArray;
		//年龄统计
	    $('#container_6').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: ''
	        },
	        credits:{
	        enabled:false // 禁用版权信息
	        },
	        legend: {
	            enabled: false// 关闭图例
	        },
	        exporting: {
	            enabled:false
			},
	        xAxis: {
	            categories: groupArray,
	            tickWidth:false,
	            lineColor:"#323232",
	            lineWidth:3,
	            labels:{
	                style:{
	                    color:"#323232",
	                    fontSize:"24px",
	                },
	            },
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: ''
	            },
	            labels:{
	                style:{
	                    color:"#fff",
	                },
	            },
	            gridLineColor: false,
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table style="border:none;">',
	            pointFormat: '<tr>' +
	                '<td style="padding:0;border:none;"><b>{point.y:f} 人</b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series: [{
	            name: '',
	                data: groupValArray
	        }]
	    });
	}
	
	function studentIsBoardedNumber (returnData) {
		var k1 = returnData["boarded"][0];
		var k2 = returnData["unBoarded"][0];
		var k3 = returnData["other"][0];
		
		var kv1 = returnData["boarded"][1];
		var kv2 = returnData["unBoarded"][1];
		var kv3 = returnData["other"][1];
		// 就读方式  初始化饼图
	    $('#container').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	        },
	        title: {
	            text: '',
	        },
	        exporting: {
	            enabled:false
			},
	        credits:{
	    		enabled:false // 禁用版权信息
	        },
	        tooltip: {
	            pointFormat: '<b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    color: '#323232',
	                    connectorColor: '#000000',
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                    style: {fontSize:"18px",}
	                }
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '学生所占比例',
	            data: [
	                [k1, parseInt(kv1)],
					[k3, parseInt(kv3)],
					[k2, parseInt(kv2)]
	            ]
	        }]
	    });
	}
	//民主党派柱状图实现
// 	function studentRaceNumber(returnData) {
// 		var k1 = returnData["unMinority"][0];
// 		var k2 = returnData["minority"][0];
// 		var k3 = returnData["other"][0];
		
// 		var kv1 = returnData["unMinority"][1];
// 		var kv2 = returnData["minority"][1];
// 		var kv3 = returnData["other"][1];
// 		//民主党派
// 	    $('#container_3').highcharts({
// 	        chart: {
// 	            type: 'column'
// 	        },
// 	        title: {
// 	            text: '年龄统计'
// 	        },
// 	        credits:{
// 	        enabled:false // 禁用版权信息
// 	        },
// 	        legend: {
// 	            enabled: false// 关闭图例
// 	        },
// 	        exporting: {
// 	            enabled:false
// 			},
// 	        xAxis: {
// 	            categories: commonGroupArray,
// 	            tickWidth:false,
// 	            lineColor:"#323232",
// 	            lineWidth:2,
// 	            labels:{
// 	                style:{
// 	                    color:"#323232",
// 	                    fontSize:"12px",
// 	                },
// 	            },
// 	        },
// 	        yAxis: {
// 	            min: 0,
// 	            title: {
// 	                text: ''
// 	            },
// 	            labels:{
// 	                style:{
// 	                    color:"#fff",
// 	                },
// 	            },
// 	            gridLineColor: false,
// 	        },
// 	        tooltip: {
// 	            headerFormat: '<span style="font-size:10px">{point.key}</span><br><table style="border:none;">',
// 	            pointFormat: '<tr>' +
// 	                '<td style="padding:0;border:none;"><b>{point.y:f} 人</b></td></tr>',
// 	            footerFormat: '</table>',
// 	            shared: true,
// // 	            useHTML: true
// 	        },
// 	        plotOptions: {
// 	            column: {
// 	                pointPadding: 0.2,
// 	                borderWidth: 0
// 	            }
// 	        },
// 	        series: [{
// 	            name: '',
// 	                data: [{
// 	                y:19,
// 	                color:"#e9573f"},
// 	            {
// 	                y:29,
// 	                color:"#f6bb42"},
// 	            {
// 	                y:39,
// 	                color:"#37bc9b"},
// 	            {
// 	                y:49,
// 	                color:"#3bafda"},]
// 	        }]
// 	    });
// 	}
	function studentSexNumber(returnData) {
// 		var k1 = returnData["man"][0];
// 		var k2 = returnData["woman"][0];
// 		var k3 = returnData["untold"][0];

		var kv1 = parseFloat(returnData["man"][1]);
		var kv2 = parseFloat(returnData["woman"][1]);
		var totalNum = kv1 + kv2;
// 		var kv3 = returnData["untold"][1];
		var man_specific = "0%";
		var woman_specific = "0%";
		if(totalNum != 0){
			man_specific = Math.round(kv1 / totalNum * 10000) / 100.00 + "%";
			woman_specific = Math.round(kv2 / totalNum * 10000) / 100.00 + "%";
		}
		$("#total_specific_man b").text(man_specific);
		$("#total_specific_woman b").text(woman_specific);
		$(".ratio_statistics .man .man_i").css("width",man_specific);
		$(".ratio_statistics .female .female_i").css("width",woman_specific);
		$("#total_man").html("男" + kv1 + "人");
		$("#total_woman").html("女" + kv2 + "人");
	}
	function studentGradeNumber(data){
		var teamNumArr = new Array();
		var studentNumArr = new Array();
		var title = "";
		if(data != null){
			$.each(data,function(index,value){
				title = title == "" ? value.name : title + "、" + value.name;
				teamNumArr.push([value.name + "(" + value.teamNumber + "个班)", value.teamNumber]);
				studentNumArr.push([value.name + "(" + value.studentNumber + "人)" , value.studentNumber]);
				var manNumber = value.manNumber;
				var womanNumber = value.womanNumber;
				var totalNumber = manNumber + womanNumber;
				if(totalNumber != 0){
					var man_ratio = Math.round(manNumber / totalNumber * 10000) / 100.00;
					var woman_ratio = 100 - man_ratio;
				} else{
					var man_ratio = 0.00;
					var woman_ratio = 0.00;
				}
				var $htmlStr = null;
				if(index % 2 == 0){
					$htmlStr = 
						'<div class="left_statistics" style="height:455px;">'
				}else{
					$htmlStr = '<div class="right_statistics" style="height:455px;">';
				}
				$htmlStr += 
					'<h4>' + value.name + '男女比例</h4>'
		            + '<div class="man"><p class="man_i" style="width:' + man_ratio + '%;"></p></div><div class="man_specific"><b>' + man_ratio + '%</b><span>男' + manNumber + '人</span></div>'
		            + '<div class="female"><p class="female_i" style="width:' + woman_ratio + '%;"></p></div><div class="female_specific"><b>' +  woman_ratio + '%</b><span>女' + womanNumber + '人</span></div>'
		            + '</div>';
				$("#MF_ratio").append($htmlStr);
			});
		}
		//设置title
		$("#teamTitle").html(title + "班级数量");
		$("#studentTitle").html(title + "总人数");
		// 初一、初二、初三班级数量
	    $('#number').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	        },
	        title: {
	            text: '',
	        },
	        exporting: {
	            enabled:false
			},
	        credits:{
	     enabled:false // 禁用版权信息
	        },
	        tooltip: {
	            pointFormat: '<b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    color: '#323232',
	                    connectorColor: '#000000',
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                    style: {fontSize:"18px",}
	                }
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '',
	            data: teamNumArr
	        }]
	    });

		//初一、初二、初三总人数
	    $('#outnumber').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false,
	        },
	        title: {
	            text: '',
	        },
	        exporting: {
	            enabled:false
			},
	        credits:{
	     enabled:false // 禁用版权信息
	        },
	        tooltip: {
	            pointFormat: '<b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    color: '#323232',
	                    connectorColor: '#000000',
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                    style: {fontSize:"18px",}
	                }
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '',
	            data: studentNumArr
	        }]
	    });
	}
</script>
<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
		<jsp:param value="fa-asterisk" name="icon" />
		<jsp:param value="学生统计" name="title" />
		<jsp:param value="${param.dm}" name="menuId" />
	</jsp:include>
<div class="jxzy_statistics">
    <h3>学生统计<span>${count}人</span><p>学生总数：</p></h3>
    <div class="fenye_statistics" id="fenye_statistics">
        <a href="javascript:void(0)" class="statistics_xuanx">人数统计</a>
        <a href="javascript:void(0)">年级统计</a>
    </div>
    <div class="statistics_page">
        <div class="ratio_statistics" style="background-color:#fff">
            <h4>男女比例</h4>
            <div class="man">
            	<p class="man_i" style="width:67%;"></p>
            </div>
            <div class="man_specific" id="total_specific_man">
            	<b></b>
            	<span id="total_man"></span>
            </div>
            <div class="female">
            	<p class="female_i" style="width:33%;"></p>
            </div>
            <div class="female_specific" id="total_specific_woman">
            	<b></b>
            	<span id="total_woman"></span>
            </div>
        </div>
        <div class="age_statistics" style="float:right;margin-left:0">
            <h4>年龄统计</h4>
            <div id="container_6" style="height:415px"></div>
        </div>
<!--         <div class="nation_statistics"> -->
<!--             <h4>民族/党派</h4> -->
<!--             <table cellspacing="0"> -->
<!--                 <tr style="background:#f2f2f2;"> -->
<!--                     <td>少数民族</td> -->
<!--                     <td>苗族</td> -->
<!--                     <td>土家族</td> -->
<!--                     <td>民主党派</td> -->
<!--                 </tr> -->
<!--                 <tr> -->
<!--                     <td><span>167人</span></td> -->
<!--                     <td><span>56人</span></td> -->
<!--                     <td><span>51人</span></td> -->
<!--                     <td><span>60人</span></td> -->
<!--                 </tr> -->
<!--             </table> -->
<!--         </div> -->
<!--         <div class="left_statistics"> -->
<!--             <h4>少先队员</h4> -->
<!--             <span>184人</span> -->
<!--             <div class="scale_statistics"> -->
<!--                 <p>男女比例</p> -->
<!--                 <div class="man_1"><p class="man_x" style="width:67%;"></p></div><div class="man_percent"><b>67%</b><span>男238人</span></div> -->
<!--                 <div class="female_1"><p class="female_x" style="width:33%;"></p></div><div class="female_percent"><b>33%</b><span>女238人</span></div> -->
<!--             </div> -->
<!--             <div class="year_statistics"> -->
<!--                 <div id="container_1" style="min-width:330px;height:220px;"></div> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="right_statistics"> -->
<!--             <h4>团员</h4> -->
<!--             <span>154人</span> -->
<!--             <div class="scale_statistics"> -->
<!--                 <p>男女比例</p> -->
<!--                 <div class="man_1"><p class="man_x" style="width:67%;"></p></div><div class="man_percent"><b>67%</b><span>男238人</span></div> -->
<!--                 <div class="female_1"><p class="female_x" style="width:33%;"></p></div><div class="female_percent"><b>33%</b><span>女238人</span></div> -->
<!--             </div> -->
<!--             <div class="year_statistics"> -->
<!--                 <div id="container_2" style="min-width:330px;height:220px;"></div> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="left_statistics"> -->
<!--             <h4>民主党派</h4> -->
<!--             <span id="statistics_race">125人</span> -->
<!--             <div class="scale_statistics"> -->
<!--                 <p>男女比例</p> -->
<!--                 <div class="man_1"><p class="man_x" style="width:67%;"></p></div><div class="man_percent"><b>67%</b><span id="statistics_race_man">男238人</span></div> -->
<!--                 <div class="female_1"><p class="female_x" style="width:33%;"></p></div><div class="female_percent"><b>33%</b id="statistics_race_woman"><span>女238人</span></div> -->
<!--             </div> -->
<!--             <div class="year_statistics"> -->
<!--                 <div id="container_3" style="min-width:330px;height:220px;"></div> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="right_statistics"> -->
<!--             <h4>华侨</h4> -->
<!--             <span>187人</span> -->
<!--             <div class="scale_statistics"> -->
<!--                 <p>男女比例</p> -->
<!--                 <div class="man_1"><p class="man_x" style="width:67%;"></p></div><div class="man_percent"><b>67%</b><span>男238人</span></div> -->
<!--                 <div class="female_1"><p class="female_x" style="width:33%;"></p></div><div class="female_percent"><b>33%</b><span>女238人</span></div> -->
<!--             </div> -->
<!--             <div class="year_statistics"> -->
<!--                 <div id="container_4" style="min-width:330px;height:220px;"></div> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="left_statistics"> -->
<!--             <h4>港澳台</h4> -->
<!--             <span>140人</span> -->
<!--             <div class="scale_statistics"> -->
<!--                 <p>男女比例</p> -->
<!--                 <div class="man_1"><p class="man_x" style="width:67%;"></p></div><div class="man_percent"><b>67%</b><span>男238人</span></div> -->
<!--                 <div class="female_1"><p class="female_x" style="width:33%;"></p></div><div class="female_percent"><b>33%</b><span>女238人</span></div> -->
<!--             </div> -->
<!--             <div class="year_statistics"> -->
<!--                 <div id="container_5" style="min-width:330px;height:220px;"></div> -->
<!--             </div> -->
<!--         </div> -->
        <div class="way_statistics">
            <h4>就读方式</h4>
            <div id="container" style="min-width:700px;height:415px;"></div>
        </div>
<!--         <div class="statistics_option"> -->
<!--             <div style="margin: 0 auto;width: 188px;"> -->
<!--                 <a href="javascript:void(0)" style="margin-right: 20px;"><img src="images/statistics_export.png" height="90" width="84"></a> -->
<!--                 <a href="javascript:void(0)"><img src="images/statistics_share.png" height="89" width="83"></a> -->
<!--             </div> -->
<!--         </div> -->
    </div>
    <div id="MF_ratio" class="statistics_page">
        <div class="left_statistics" style="min-height:495px;">
            <h4 id="teamTitle"></h4>
            <div id="number" style="height:415px;overflow-x:auto;overflow-y:hidden"></div>
        </div>
        <div class="right_statistics" style="min-height:495px;">
            <h4 id="studentTitle"></h4>
            <div id="outnumber" style="height:415px;overflow-x:auto;overflow-y:hidden"></div>
        </div>
<!--         <div class="statistics_option"> -->
<!--             <div style="margin: 0 auto;width: 188px;"> -->
<!--                 <a href="javascript:void(0)" style="margin-right: 20px;"><img src="images/statistics_export.png" height="90" width="84"></a> -->
<!--                 <a href="javascript:void(0)"><img src="images/statistics_share.png" height="89" width="83"></a> -->
<!--             </div> -->
        </div>
    </div>
</div>
</div>
</body>
</html>