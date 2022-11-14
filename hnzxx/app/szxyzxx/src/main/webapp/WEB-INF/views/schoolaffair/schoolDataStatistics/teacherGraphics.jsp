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
<style type="text/css">
body{
	font-family:'宋体';
}
.highcharts-container{
	margin:0 auto;
}
.clear{ clear:both}
.tb{
	height:60px;background-color:#f2f2f2;border-bottom:1px solid #bfbfbf;border-top:1px solid #bfbfbf;
}
.tb a{
	width:110px;border-right:1px solid #bfbfbf;height:60px;float:left;text-align: center;line-height: 60px;font-size: 12px;color: #999;font-weight: bold;
}
.tb a:hover{
	background-color: #fff;height: 61px;color: #436b8e;	font-weight: bold;
}
a.bj{
	background-color: #fff;height: 61px;color: #436b8e;	font-weight: bold;
}
.bt{
    width:47.5%;border:1px solid #bfbfbf;height:455px;margin:20px 0 0 20px;float:left;
}
.zzt{
    width:47.5%;border:1px solid #bfbfbf;height:455px;margin:20px 20px 0 0;float:right;
}
.bt2{
    height:605px;border:1px solid #bfbfbf;margin:20px;
}
.tj{
    margin: 20px 20px 25px 20px;
}
.tj h4{
    font-size: 14px;background-color: #4993b3;line-height: 30px;text-align: center;  height: 30px;margin: 0;color: #fff;border:1px solid #bfbfbf;
}
.tj h4 span{
    color: #dfdfdf;font-size: 12px;
}
.bt h5, .bt2 h5, .zzt h5{
    font-size: 14px;background-color: #4993b3;line-height: 30px;text-align: center;  height: 30px;color: #fff;width: 300px;margin:10px 0 60px 0;display:block; overflow:hidden; text-overflow:ellipsis;white-space:nowrap;
}
table{
    width: 100%;border-collapse:collapse;
}
table tr td{
    padding-left: 10px;border:1px solid #bfbfbf;height: 30px;font-size: 12px;color: #444;
}
</style>
<script type="text/javascript">
$(function () {
	getTeacherSexData();
	getTeacherRaceData();
	getTeacherPoliticsData();
	getTeacherAgeData();
	getTeacherSubjectData();
	getTeacherDepartmentData();
	getTeacherTeamData();
});

//发送请求获取教师性别信息
function getTeacherSexData(){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getTeacherSexData", function(returnData, status) {
		if ("success" === status) {
			teacherSex(returnData);
		}
	}, 'json');
}

//发送请求获取教师民族信息
function getTeacherRaceData(){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getTeacherRaceData", function(returnData, status) {
		if ("success" === status) {
			teacherRace(returnData);
		}
	}, 'json');
}

//发送请求获取教师政治面貌信息
function getTeacherPoliticsData(){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getTeacherPoliticsData", function(returnData, status) {
		if ("success" === status) {
			teacherPolitics(returnData);
		}
	}, 'json');
}

//发送请求获取教师年龄段信息
function getTeacherAgeData(){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getTeacherAgeData", function(returnData, status) {
		if ("success" === status) {
			teacherAge(returnData);
		}
	}, 'json');
}

//发送请求获取教师任课信息
function getTeacherSubjectData(){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getTeacherSubjectData", function(returnData, status) {
		if ("success" === status) {
			teacherSubject(returnData);
		}
	}, 'json');
}

//发送请求获取部门人数信息
function getTeacherDepartmentData(){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getTeacherDepartmentData", function(returnData, status) {
		if ("success" === status) {
			teacherDepartment(returnData);
		}
	}, 'json');
}

//发送请求获取任课教师人数信息
function getTeacherTeamData(){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getteamTeacherNumber", function(returnData, status) {
		if ("success" === status) {
			getTeamData(returnData);
		}
	}, 'json');
}

function teacherRace(returnData){
	var k1 = returnData["nationalMinority"][0];
	var k2 = returnData["unNationalMinority"][0];
	var k3 = returnData["notNational"][0];
	
	var kv1 = returnData["nationalMinority"][1];
	var kv2 = returnData["unNationalMinority"][1];
	var kv3 = returnData["notNational"][1];
	$('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        credits: {
            enabled: false
        },
        exporting: {
            enabled:false
},
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            data: [
 				[k1,parseInt(kv1)],[k2,parseInt(kv2)],[k3,parseInt(kv3)]
            ]
        }]
    });
}

function teacherSex(returnData){
	
	var k1 = returnData["man"][0];
	var k2 = returnData["wom"][0];
	var k3 = returnData["noS"][0];
	
	var kv1 = returnData["man"][1];
	var kv2 = returnData["wom"][1];
	var kv3 = returnData["noS"][1];
	
    $('#container1').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        credits: {
            enabled: false
        },
        exporting: {
            enabled:false
},
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            data: [
 				[k1,parseInt(kv1)],[k2,parseInt(kv2)],[k3,parseInt(kv3)]
            ]
        }]
    });
}

function teacherPolitics(returnData){
	var k1 = returnData["Patry"][0];
	var k2 = returnData["Member"][0];
	var k3 = returnData["Other"][0];
	
	var kv1 = returnData["Patry"][1];
	var kv2 = returnData["Member"][1];
	var kv3 = returnData["Other"][1];
	$('#container2').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        credits: {
            enabled: false
        },
        exporting: {
            enabled:false
},
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            data: [
 				[k1,parseInt(kv1)],[k2,parseInt(kv2)],[k3,parseInt(kv3)]
            ]
        }]
    });
}

function teacherAge(returnData){
	var k1 = "其他 （0人）";
	var k2 = "20 ~ 30 （0人）";
	var k3 = "31 ~ 50 （0人）";
	var k4 = "大于50 （0人）";
	var kv1 = 0;
	var kv2 = 0;
	var kv3 = 0;
	var kv4 = 0;
	if(returnData["other"] != undefined){
		k1 = returnData["other"][0];
		kv1 = returnData["other"][1];
	}
	if(returnData["firstStage"] != undefined){
		k2 = returnData["firstStage"][0];
		kv2 = returnData["firstStage"][1];
	}
	if(returnData["secondStage"] != undefined){
		k3 = returnData["secondStage"][0];
		kv3 = returnData["secondStage"][1];
	}
	if(returnData["theThirdStage"] != undefined){
		k4 = returnData["theThirdStage"][0];
		kv4 = returnData["theThirdStage"][1];
	}
	$('#container3').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        credits: {
            enabled: false
        },
        exporting: {
            enabled:false
},
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            data: [
 				[k1,parseInt(kv1)],[k2,parseInt(kv2)],[k3,parseInt(kv3)],[k4,parseInt(kv4)]
            ]
        }]
    });
}

function teacherSubject(returnData){
	var data = [];
	for(var key in returnData){
		data.push([key,returnData[key]]);
	}
	$('#container4').highcharts({
        chart: {
        	height: 500,
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        credits: {
            enabled: false
        },
        exporting: {
            enabled:false
},
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            data: data
        }]
    });
}

function teacherDepartment(returnData){
	var data = [];
	for(var key in returnData){
		data.push([key,returnData[key]]);
	}
	$('#container5').highcharts({
        chart: {
        	height: 500,
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: ''
        },
        credits: {
            enabled: false
        },
        exporting: {
            enabled:false
},
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            data: data
        }]
    });
}

function getTeamData(returnData){
	var tBodyHtml = "";
	$("#teamData").text("");
	for(var i = 0; i < returnData.length; i++){
		tBodyHtml = "<tr>" +
					"<td>" + returnData[i].name + "</td>" +
					"<td>" + returnData[i].classNumber + "</td>" +
					"<td>" + returnData[i].teacherNumber + "</td>" +
					"<td>" + returnData[i].headTeacherNumber + "</td>";
		for(var j = 0; j < returnData[i].subjectList.length;j++){
			tBodyHtml += "<td>" + returnData[i].subjectList[j].teacherNumber + "</td>";
		}
		tBodyHtml += "</tr>";
	$("#teamData").append(tBodyHtml);
	}
	
}

</script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="教师统计" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							教师统计
						</h3>
					</div>
					<div class="content-widgets">
					<div>
					
					<div class="tb" id="tb">
						<a href="javascript:void(0)" class="bj">人数统计</a>
<!-- 						<a href="javascript:void(0)">岗位统计</a> -->
<!-- 						<a href="javascript:void(0)">学位统计</a> -->
						<a href="javascript:void(0)">科任教师统计</a>
						<a href="javascript:void(0)">年级任课教师统计</a>
						<a href="javascript:void(0)">部门统计</a>
						
					</div>
	<!-- 性别	 -->
	<div class="figure">
    <div class="bt">
	    <h5 title="教师人数统计-男女统计饼图">${schoolName}教师人数统计-男女统计饼图</h5>
	    <div id="container1" style="height:350px;"></div>
	</div>
	
	<!--民族 -->
    <div class="zzt">
          <h5 title="教师人数统计-民族统计饼图">${schoolName}教师人数统计-民族统计饼图</h5>
          <div id="container" style="height:350px"></div>
    </div>
    <div class="clear"></div>
    
	<!-- 政治面貌 -->
    <div class="bt" style="float:right;margin:20px 20px 0 0;">
        <h5 title="教师人数统计-政治面貌统计饼图">${schoolName}教师人数统计-政治面貌统计饼图</h5>
        <div id="container2" style="height:350px;"></div>
    </div>
    
	<!--年龄段 -->
    <div class="bt">
        <h5 title="教师人数统计-年龄统计饼图">${schoolName}教师人数统计-年龄统计饼图</h5>
        <div id="container3" style="height:350px;"></div>
    </div> 
    <div class="clear"></div>
 </div>
<!--  <div class="figure"> -->
   
<!-- </div> -->
<!-- <div class="figure"> -->
  
<!-- </div> -->
<div class="figure">
  <div class="bt2">
        <h5 title="教师人数统计-任课教师统计饼图">${schoolName}教师人数统计-任课教师统计饼图</h5>
        <div id="container4" style="height:350px;"></div>
    </div>
</div>
<div class="figure">
   <div class="tj">
        <h4>${schoolName}教师人数统计-科任任课教师统计表<span>(单位：人)</span></h4>
        <table>
        <thead>
            <tr>
                <td>年级</td>
                <td>班级数</td>
                <td>任课教师数</td>
                <td>班主任</td>
                <c:forEach items="${subjectList}" var="item">
	                <td>${item.name}</td>
                </c:forEach>
            </tr>
           </thead>
           <tbody id="teamData"> 
            </tbody>
    </table>
 </div>
</div>
<div class="figure">
    <div class="bt2" style="float:none;">
        <h5>${schoolName}教师人数统计-部门人员统计饼图</h5>
        <div id="container5" style="height:350px;"></div>
    </div>
</div>
</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<script type="text/javascript">
$(function(){
    $(".figure").hide();//隐藏wenben
    $(".figure:eq(0)").show();//显示第一个wenben
    $("#tb a").click(function(){
        $(".bj").removeClass("bj");//移除样式
        $(this).addClass("bj");//添加样式
        var i=$(this).index();//获得下标
        $(".figure").hide();//隐藏wenben
        $(".figure:eq("+i+")").show();//显示第i个wenben
    });
})
</script>
</body>
</html>
