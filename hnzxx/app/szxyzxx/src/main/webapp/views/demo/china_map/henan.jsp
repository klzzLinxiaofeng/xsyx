<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<title>河南省</title>
<style>
	.bingtu{
		height:100%;
		width:30%;
		float:left;
		margin-right:3%;
	}
</style>
</head>
<body style="height: 100%; margin: 0;background-color:#fff;">
<!-- <div id="container" class="bingtu"></div> -->
<script>
$(function () {
	var i=0;
	var data_count={ "wuzhi": [
	                           { "school_name": "武陟县实验中学", "student":"3793", "teacher": "197" },
	                           { "school_name": "武陟县兴华中学", "student":"1772", "teacher": "120" },
	                           { "school_name": "武陟县育才学校", "student":"2867", "teacher": "157" },
	                           { "school_name": "武陟县育英实验学校（小学）", "student":"1800", "teacher": "0" },
	                           { "school_name": "武陟县覃怀初中", "student":"954", "teacher": "59" },
	                           { "school_name": "武陟县木栾街道办事处第一初级中学", "student":"561", "teacher": "40" },
	                           { "school_name": "武陟县龙泉街道办事处第一初级中学", "student":"680", "teacher": "55" },
	                           { "school_name": "武陟县龙泉街道办事处第二初级中学", "student":"256", "teacher": "29" },
	                           { "school_name": "武陟县木城街道办事处第一初级中学", "student":"2008", "teacher": "129" },
	                           { "school_name": "武陟县龙源街道办事处第一初级中学", "student":"695", "teacher": "48" },
	                           { "school_name": "武陟县三阳乡第一初级中学", "student":"725", "teacher": "51" },
	                           { "school_name": "武陟县小董乡第一初级中学", "student":"1426", "teacher": "8" },
	                           { "school_name": "武陟县大封镇镇中", "student":"776", "teacher": "7" },
	                           { "school_name": "武陟县大封镇第一初级中学", "student":"746", "teacher": "53" },
	                           { "school_name": "武陟县西陶镇第一初级中学", "student":"917", "teacher": "51" },
	                           { "school_name": "武陟县西陶镇第三初级中学", "student":"470", "teacher": "36" },
	                           { "school_name": "武陟县大虹桥乡第一初级中学", "student":"302", "teacher": "41" },
	                           { "school_name": "武陟县北郭乡第一初级中学", "student":"438", "teacher": "43" },
	                           { "school_name": "武陟县大虹桥乡阳城第一初级中学", "student":"680", "teacher": "40" },
	                           { "school_name": "武陟县詹店镇第一初级中学", "student":"694", "teacher": "46" },
	                           { "school_name": "武陟县嘉应观乡第一初级中学", "student":"900", "teacher": "56" },
	                           { "school_name": "武陟县乔庙乡第一初级中学", "student":"1568", "teacher": "10" },
	                           { "school_name": "武陟县圪垱店乡第一初级中学", "student":"483", "teacher": "32" },
	                           { "school_name": "武陟县谢旗营镇第一初级中学", "student":"927", "teacher": "76" },
	                           { "school_name": "武陟县木城街道办事处第一小学", "student":"2042", "teacher": "137" },
	                           { "school_name": "武陟县木城街道办事处第二小学", "student":"1125", "teacher": "72" },
	                           { "school_name": "武陟县龙源街道办事处龙源小学", "student":"556", "teacher": "36" },
	                           { "school_name": "武陟县三阳乡三阳小学", "student":"553", "teacher": "28" },
	                           { "school_name": "武陟县小董乡小董小学", "student":"570", "teacher": "26" },
	                           { "school_name": "武陟县西陶镇西陶小学", "student":"570", "teacher": "20" },
	                          ]}
	for(var i=0;i<30;i++){
		$("<div id='container"+i+"' class='bingtu'></div>").appendTo("body");
		creat_chart("container"+i,data_count.wuzhi[i].school_name,data_count.wuzhi[i].teacher,data_count.wuzhi[i].student);
	}
	function creat_chart(id,school_name,teacher_count,student_count){
		var tea=parseInt(teacher_count);
		var stu=parseInt(student_count);
		$('#'+id).highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        colors:[
                    'red',//第一个颜色
                   '#1aadce', //。。。。
                       '#492970'
                  ],
	        title: {
	            text: school_name
	        },
	        tooltip: {
	            headerFormat: '{series.name}<br>',
	            pointFormat: '{point.name}: <b>{point.y}人</b>'
//	             pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    format: '<b>{point.name}</b>: {point.y}',
	                    style: {
	                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                    }
	                }
	            }
	        },
	        credits: {
	            enabled: false
	       },
	        series: [{
	            type: 'pie',
	            name: '用户人数对比',
	            data:[
	                  ['教师',   tea],
	                  ['学生', stu]
	              ]
	        }]
	    });
	}
    
});
</script>
</body>
</html>