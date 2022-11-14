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
</head>
<script type="text/javascript">
$(function(){
	//数据初始化
	getTeacherSexData();
	getTeacherAgeData();
	getTeacherDYData();
	getTeacherTYData();
	getTeacherPostsData(1);
	getTeacherPostsData(2);
	getTeacherPostsData(3);
	getTeacherPostsData(4);
	getTeacherNumberOfQualificationData();
	getTeacherSubjectData();
	getTeacherTeamData();
	getTeacherDepartmentData();
    $(".statistics_page").hide();//隐藏wenben
    $(".statistics_page:eq(0)").show();//显示第一个wenben
    $("#fenye_statistics a").click(function(){
        $(".statistics_xuanx").removeClass("statistics_xuanx");//移除样式
        $(this).addClass("statistics_xuanx");//添加样式
        var i=$(this).index();//获得下标
        $(".statistics_page").hide();//隐藏wenben
        $(".statistics_page:eq("+i+")").show();//显示第i个wenben
    });
});

//发送请求获取教师性别信息
function getTeacherSexData(){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getTeacherSexData", function(returnData, status) {
		if ("success" === status) {
			teacherSex(returnData);
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
//发送请求获取党员、性别人数信息
function getTeacherDYData(){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getTeacherDYData", function(returnData, status) {
		if ("success" === status) {
			teacherDY(returnData);
		}
	}, 'json');
}
//发送请求获取团员、性别人数信息
function getTeacherTYData(){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getTeacherTYData", function(returnData, status) {
		if ("success" === status) {
			teacherTY(returnData);
		}
	}, 'json');
}
//发送请求获取岗位、性别人数信息
function getTeacherPostsData(postStaffing){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getTeacherPostsData",{postStaffing : postStaffing}, function(returnData, status) {
		if ("success" === status) {
			teacherPosts(returnData,postStaffing);
		}
	}, 'json');
}
//发送请求获取学历饼图所需数据
function getTeacherNumberOfQualificationData(){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getTeacherXLData", function(returnData, status) {
		if ("success" === status) {
			returnData = eval("(" + returnData + ")");
			teacherXLData(returnData);
		}
	});
}
//发送请求获取教师任课信息
function getTeacherSubjectData(){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getTeacherSubjectDataNew", function(returnData, status) {
		if ("success" === status) {
			teacherSubject(returnData);
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
//发送请求获取部门人数信息
function getTeacherDepartmentData(){
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getTeacherDepartmentDataNew", function(returnData, status) {
		if ("success" === status) {
			teacherDepartment(returnData);
		}
	}, 'json');
}
function teacherSex(returnData) {
	var kv1 = parseFloat(returnData["man"][1]);
	var kv2 = parseFloat(returnData["wom"][1]);
	var totalNum = kv1 + kv2;
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
		kv1 = parseInt(returnData["other"][1]);
	}
	if(returnData["firstStage"] != undefined){
		k2 = returnData["firstStage"][0];
		kv2 = parseInt(returnData["firstStage"][1]);
	}
	if(returnData["secondStage"] != undefined){
		k3 = returnData["secondStage"][0];
		kv3 = parseInt(returnData["secondStage"][1]);
	}
	if(returnData["theThirdStage"] != undefined){
		k4 = returnData["theThirdStage"][0];
		kv4 = parseInt(returnData["theThirdStage"][1]);
	}
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
            enabled:false//关闭设置按钮
        },
        xAxis: {
            categories: [
                '20岁以下',
                '20-30',
                '30-40',
                '50岁以上',
            ],
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
                data: [{
                y:kv1,
                color:"#e9573f"},
            {
                y:kv2,
                color:"#f6bb42"},
            {
                y:kv3,
                color:"#37bc9b"},
            {
                y:kv4,
                color:"#3bafda"},]
        }]
    });
}
function teacherDY(returnData){
	var kv1 = parseFloat(returnData["man"][1]);
	var kv2 = parseFloat(returnData["wom"][1]);
	var totalNum = kv1 + kv2;
	var man_specific = "0%";
	var woman_specific = "0%";
	if(totalNum != 0){
		man_specific = Math.round(kv1 / totalNum * 10000) / 100.00 + "%";
		woman_specific = Math.round(kv2 / totalNum * 10000) / 100.00 + "%";
	}
	$("#member_specific span").text(totalNum + "人");
	$("#member_specific .man_specific b").text(man_specific);
	$("#member_specific .female_specific b").text(woman_specific);
	$("#member_specific .man .man_i").css("width",man_specific);
	$("#member_specific .female .female_i").css("width",woman_specific);
	$("#member_specific .man_specific span").html("男" + kv1 + "人");
	$("#member_specific .female_specific span").html("女" + kv2 + "人");
}
function teacherTY(returnData){
	var kv1 = parseFloat(returnData["man"][1]);
	var kv2 = parseFloat(returnData["wom"][1]);
	var totalNum = kv1 + kv2;
	var man_specific = "0%";
	var woman_specific = "0%";
	if(totalNum != 0){
		man_specific = Math.round(kv1 / totalNum * 10000) / 100.00 + "%";
		woman_specific = Math.round(kv2 / totalNum * 10000) / 100.00 + "%";
	}
	$("#leagueMember_specific span").text(totalNum + "人");
	$("#leagueMember_specific .man_specific b").text(man_specific);
	$("#leagueMember_specific .female_specific b").text(woman_specific);
	$("#leagueMember_specific .man .man_i").css("width",man_specific);
	$("#leagueMember_specific .female .female_i").css("width",woman_specific);
	$("#leagueMember_specific .man_specific span").html("男" + kv1 + "人");
	$("#leagueMember_specific .female_specific span").html("女" + kv2 + "人");
}
function teacherPosts(returnData,type){
	var kv1 = parseFloat(returnData["man"][1]);
	var kv2 = parseFloat(returnData["wom"][1]);
	var totalNum = kv1 + kv2;
	var man_specific = "0%";
	var woman_specific = "0%";
	if(totalNum != 0){
		man_specific = Math.round(kv1 / totalNum * 10000) / 100.00 + "%";
		woman_specific = Math.round(kv2 / totalNum * 10000) / 100.00 + "%";
	}
	$("#posts_specific_" + type + " span").text(totalNum + "人");
	$("#posts_specific_" + type + " .man_specific b").text(man_specific);
	$("#posts_specific_" + type + " .female_specific b").text(woman_specific);
	$("#posts_specific_" + type + " .man .man_i").css("width",man_specific);
	$("#posts_specific_" + type + " .female .female_i").css("width",woman_specific);
	$("#posts_specific_" + type + " .man_specific span").html("男" + kv1 + "人");
	$("#posts_specific_" + type + " .female_specific span").html("女" + kv2 + "人");
}
function teacherXLData(returnData){
	var dataArr = new Array();
	$.each(returnData,function(index,value){
		dataArr.push([value["GBXL"][0],parseFloat(value["GBXL"][1])]);
	});
	$('#ratio').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
        },
        title: {
            text: '',
        },
        credits:{
     enabled:false // 禁用版权信息
        },
        exporting: {
            enabled:false//关闭设置按钮
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
            data: dataArr
        }]
    });
}
function teacherSubject(returnData){
	var titleData = new Array();
	var valueData = new Array();
	for(var key in returnData){
		titleData.push(key);
		valueData.push(returnData[key]);
	}
// 	alert(titleData);
	//科任教师人数
	$('#teacher_num').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: ''
        },
        credits:{
        enabled:false // 禁用版权信息
        },

        exporting: {
            enabled:false//关闭设置按钮
        },
        xAxis: {
            categories: titleData,
            // tickWidth:false,
            lineColor:"#323232",
            // lineWidth:2,
            labels:{
                style:{
                    color:"#323232",
                    fontSize:"12px",
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
                    // color:"#fff",
                },
            },
            // gridLineColor: false,
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px;">{point.key}</span><br><table style="border:none;">',
            pointFormat: '<tr>' +
                '<td style="padding:0;border:none;"><b>{point.y:f} 人</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
             // useHTML: true,
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: '人数',
                data: valueData
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
function teacherDepartment(returnData){
	var $classSelector = "left_statistics";
	var $selector = $("#departmentPage");
	var flag = 0;
	for(var key in returnData){
		var manNum = parseFloat(returnData[key][0]); 
		var womanNum = parseFloat(returnData[key][1]); 
		var sumNum = manNum + womanNum; 
		var man_specific = "0%";
		var woman_specific = "0%";
		if(sumNum != 0){
			man_specific = Math.round(manNum / sumNum * 10000) / 100.00 + "%";
			woman_specific = Math.round(womanNum / sumNum * 10000) / 100.00 + "%";
		}
		if(flag % 2 == 0){
			$classSelector = "left_statistics";
		}else{
			$classSelector = "right_statistics";
		}
		$selector.append(
				'<div class="' + $classSelector + '" style="margin-left: 0;">'
	            + '<h4>' + key + '</h4>'
	            + '<span style="color:#4fc1e9;">' + sumNum + '人</span>'
	            + '<div class="man">'
	            + '<p class="man_i" style="width:' + man_specific + ';"></p>'
	            + '</div>'
	            + '<div class="man_specific">'
	            + '<b>'+ man_specific +'</b>'
	            + '<span>男' + manNum + '人</span>'
	            + '</div>'
	            + '<div class="female">'
	            + '<p class="female_i" style="width:' + woman_specific + ';"></p>'
	            + '</div>'
	            + '<div class="female_specific">'
	            + '<b>' + woman_specific + '</b>'
	            + '<span>女' + womanNum + '人</span>'
	            + '</div>'
	            );
		flag++;
	}
}
</script>
<body>
<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
		<jsp:param value="fa-asterisk" name="icon" />
		<jsp:param value="教师统计" name="title" />
		<jsp:param value="${param.dm}" name="menuId" />
	</jsp:include>
<div class="jxzy_statistics">
    <h3>教师统计<span>${teacherNum}人</span><p>教职工总数：</p></h3>
    <div class="fenye_statistics" id="fenye_statistics">
        <a href="javascript:void(0);" class="statistics_xuanx">人数统计</a>
        <a href="javascript:void(0);">岗位统计</a>
        <a href="javascript:void(0);">学位统计</a>
        <a href="javascript:void(0);">科任教师统计</a>
        <a href="javascript:void(0);">年级任课教师统计</a>
        <a href="javascript:void(0);">部门统计</a>
    </div>
    <div class="statistics_page">
        <div class="ratio_statistics" style="background: #fff;">
            <h4>男女比例</h4>
            <div class="man">
            	<p class="man_i"></p>
            </div>
        	<div id="total_specific_man" class="man_specific">
        		<b></b>
        		<span id="total_man"></span>
        	</div>
            <div class="female">
            	<p class="female_i"></p>
            </div>
            <div id="total_specific_woman" class="female_specific">
            	<b></b>
            	<span id="total_woman"></span>
            </div>
        </div>
        <div class="age_statistics" style="float:right;margin-left:0">
            <h4>年龄统计</h4>
            <div id="container_6" style="height:415px"></div>
        </div>
<!--         <div class="line" style="margin-top: 20px;"> -->
<!--         <div class="nation_statistics"> -->
<!--             <h4>民族/党派</h4> -->
<!--             <table cellspacing="0"> -->
<!--                 <tr style="background:#f2f2f2;"> -->
<!--                     <td>少数民族</td> -->
<!--                     <td>苗族</td> -->
<!--                     <td>土家族</td> -->
<!--                     <td style="border-right: none;">民主党派</td> -->
<!--                 </tr> -->
<!--                 <tr> -->
<!--                     <td><span>167人</span></td> -->
<!--                     <td><span>56人</span></td> -->
<!--                     <td><span>51人</span></td> -->
<!--                     <td style="border-right: none;"><span>60人</span></td> -->
<!--                 </tr> -->
<!--             </table> -->
<!--         </div> -->
        <div id="member_specific" class="left_statistics" style="margin-left: 0px;">
            <h4>党员：男女比例</h4>
            <span>184人</span>
            <div class="man">
            	<p class="man_i" style="width:67%;"></p>
            </div>
        	<div class="man_specific">
        		<b>67%</b>
        		<span>男238人</span>
        	</div>
            <div class="female">
            	<p class="female_i" style="width:33%;"></p>
            </div>
            <div class="female_specific">
            	<b>33%</b>
            	<span>女238人</span>
            </div>
        </div>
        <div id="leagueMember_specific" class="right_statistics">
            <h4>团员：男女比例</h4>
            <span>154人</span>
            <div class="man">
            	<p class="man_i" style="width:67%;"></p>
            </div>
        	<div class="man_specific">
        		<b>67%</b>
        		<span>男238人</span>
        	</div>
            <div class="female">
            	<p class="female_i" style="width:33%;"></p>
            </div>
            <div class="female_specific">
            	<b>33%</b>
            	<span>女238人</span>
            </div>
        </div>
<!--         <div class="left_statistics"> -->
<!--             <h4>民主党派</h4> -->
<!--             <span>125人</span> -->
<!--             <div class="scale_statistics"> -->
<!--                 <p>男女比例</p> -->
<!--                 <div class="man_1"><p class="man_x" style="width:67%;"></p></div><div class="man_percent"><b>67%</b><span>男238人</span></div> -->
<!--                 <div class="female_1"><p class="female_x" style="width:33%;"></p></div><div class="female_percent"><b>33%</b><span>女238人</span></div> -->
<!--             </div> -->
<!--             <div class="year_statistics"> -->
<!--                 <p>年龄统计</p> -->
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
<!--                 <p>年龄统计</p> -->
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
<!--                 <p>年龄统计</p> -->
<!--                 <div id="container_5" style="min-width:330px;height:220px;"></div> -->
<!--             </div> -->
<!--         </div> -->
<!--         </div> -->
        <div class="statistics_option">
            <div style="margin: 0 auto;width: 85px;">
<!--                 <a href="javascript:void(0);" style="margin-right: 20px;"><img src="images/statistics_export.png" height="90" width="84"></a> -->
                <!-- <a href="javascript:void(0);"><img src="images/statistics_share.png" height="89" width="83"></a> -->
            </div>
        </div>
    </div>

    <div class="statistics_page">
<!--         <div class="line"> -->
<!--             <h4>教师岗位</h4> -->
<!--             <div class="left_statistics"> -->
<!--             <h4>在编</h4> -->
<!--             <span style="color:#4fc1e9;">184人</span> -->
<!--             <div class="man"> -->
<!--             	<p class="man_i" style="width:67%;"></p> -->
<!--             </div> -->
<!--         	<div class="man_specific"> -->
<!--         		<b>67%</b> -->
<!--         		<span>男238人</span> -->
<!--         	</div> -->
<!--             <div class="female"> -->
<!--             	<p class="female_i" style="width:33%;"></p> -->
<!--             </div> -->
<!--             <div class="female_specific"> -->
<!--             	<b>33%</b> -->
<!--             	<span>女238人</span> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="left_statistics"> -->
<!--             <h4>外聘（临聘）</h4> -->
<!--             <span style="color:#4fc1e9;">154人</span> -->
<!--             <div class="man"> -->
<!--             	<p class="man_i" style="width:67%;"></p> -->
<!--             </div> -->
<!--         	<div class="man_specific"> -->
<!--         		<b>67%</b> -->
<!--         		<span>男238人</span> -->
<!--         	</div> -->
<!--             <div class="female"> -->
<!--             	<p class="female_i" style="width:33%;"></p> -->
<!--             </div> -->
<!--             <div class="female_specific"> -->
<!--             	<b>33%</b> -->
<!--             	<span>女238人</span> -->
<!--             </div> -->
<!--         </div> -->
<!--         </div> -->
        <div class="line">
            <h4>教职工岗位</h4>
            <div id="posts_specific_1" class="left_statistics" style="margin-left: 5px;">
            <h4>教学</h4>
            <span>184人</span>
            <div class="man">
            	<p class="man_i" style="width:67%;"></p>
            </div>
        	<div class="man_specific">
        		<b>67%</b>
        		<span>男238人</span>
        	</div>
            <div class="female">
            	<p class="female_i" style="width:33%;"></p>
            </div>
            <div class="female_specific">
            	<b>33%</b>
            	<span>女238人</span>
            </div>
        </div>
        <div id="posts_specific_2" class="right_statistics"style="margin-right: 5px;">
            <h4>行政</h4>
            <span>154人</span>
            <div class="man">
            	<p class="man_i" style="width:67%;"></p>
            </div>
        	<div class="man_specific">
        		<b>67%</b>
        		<span>男238人</span>
        	</div>
            <div class="female">
            	<p class="female_i" style="width:33%;"></p>
            </div>
            <div class="female_specific">
            	<b>33%</b>
            	<span>女238人</span>
            </div>
        </div>
        <div id="posts_specific_3" class="left_statistics"style="margin-left: 5px;">
            <h4>教辅</h4>
            <span>184人</span>
            <div class="man">
            	<p class="man_i" style="width:67%;"></p>
            </div>
        	<div class="man_specific">
        		<b>67%</b>
        		<span>男238人</span>
        	</div>
            <div class="female">
            	<p class="female_i" style="width:33%;"></p>
            </div>
            <div class="female_specific">
            	<b>33%</b>
            	<span>女238人</span>
            </div>
        </div>
        <div id="posts_specific_4" class="right_statistics"style="margin-right: 5px;">
            <h4>工勤</h4>
            <span>154人</span>
            <div class="man">
            	<p class="man_i" style="width:67%;"></p>
            </div>
        	<div class="man_specific">
        		<b>67%</b>
        		<span>男238人</span>
        	</div>
            <div class="female">
            	<p class="female_i" style="width:33%;"></p>
            </div>
            <div class="female_specific">
            	<b>33%</b>
            	<span>女238人</span>
            </div>
        </div>
        </div>
        <div class="statistics_option">
            <div style="margin: 0 auto;width: 85px;">
<!--                 <a href="javascript:void(0);" style="margin-right: 20px;"><img src="images/statistics_export.png" height="90" width="84"></a> -->
                <!-- <a href="javascript:void(0);"><img src="images/statistics_share.png" height="89" width="83"></a> -->
            </div>
        </div>
    </div>

    <div class="statistics_page">
    <div class="way_statistics" style="margin:0;width: 100%;">
        <h4>学位比例</h4>
        <div id="ratio" style="min-width:96%;height:415px;"></div>
    </div>
    <div class="statistics_option">
            <div style="margin: 0 auto;width: 85px;">
<!--                 <a href="javascript:void(0);" style="margin-right: 20px;"><img src="images/statistics_export.png" height="90" width="84"></a> -->
                <!-- <a href="javascript:void(0);"><img src="images/statistics_share.png" height="89" width="83"></a> -->
            </div>
        </div>
    </div>

    <div class="statistics_page">
    <div class="way_statistics" style="height: 505px;margin:0;width: 100%;">
        <h4>科任教师人数</h4>
        <div id="teacher_num" style="min-width:96%;height:415px;margin-top:50px;"></div>
    </div>
    <div class="statistics_option">
            <div style="margin: 0 auto;width: 85px;">
<!--                 <a href="javascript:void(0);" style="margin-right: 20px;"><img src="images/statistics_export.png" height="90" width="84"></a> -->
                <!-- <a href="javascript:void(0);"><img src="images/statistics_share.png" height="89" width="83"></a> -->
            </div>
        </div>
    </div>

    <div class="statistics_page" style="border-top: 1px #bfbfbf solid;border-left: 1px #bfbfbf solid;">
        <h4 style="margin-top: 0;">年级任课教师人数</h4>
        <table cellspacing="0">
            <tr style="font-weight: bold;background:#f2f2f2;line-height: 30px;">
                <td>年级</td>
                <td>班级数</td>
                <td>任课老师</td>
                <td>班主任</td>
                <c:forEach items="${subjectList}" var="item">
	                <td>${item.name}</td>
                </c:forEach>
            </tr>
         <tbody id="teamData"> 
         </tbody>
        </table>
        <div class="statistics_option">
            <div style="margin: 0 auto;width: 85px;">
<!--                 <a href="javascript:void(0);" style="margin-right: 20px;"><img src="images/statistics_export.png" height="90" width="84"></a> -->
                <!-- <a href="javascript:void(0);"><img src="images/statistics_share.png" height="89" width="83"></a> -->
            </div>
        </div>
    </div>

    <div id="departmentPage" class="statistics_page">
<!--         <div class="left_statistics" style="margin-left: 0;"> -->
<!--             <h4>办公室</h4> -->
<!--             <span style="color:#4fc1e9;">127人</span> -->
<!--             <div class="man"> -->
<!--             	<p class="man_i" style="width:67%;"></p> -->
<!--             </div> -->
<!--         	<div class="man_specific"> -->
<!--         		<b>67%</b> -->
<!--         		<span>男238人</span> -->
<!--         	</div> -->
<!--             <div class="female"> -->
<!--             	<p class="female_i" style="width:33%;"></p> -->
<!--             </div> -->
<!--             <div class="female_specific"> -->
<!--             	<b>33%</b> -->
<!--             	<span>女238人</span> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="right_statistics" style="margin-right: 0;"> -->
<!--             <h4>教务处</h4> -->
<!--             <span  style="color:#4fc1e9;">65人</span> -->
<!--             <div class="man"> -->
<!--             	<p class="man_i" style="width:67%;"></p> -->
<!--             </div> -->
<!--         	<div class="man_specific"> -->
<!--         		<b>67%</b> -->
<!--         		<span>男238人</span> -->
<!--         	</div> -->
<!--             <div class="female"> -->
<!--             	<p class="female_i" style="width:33%;"></p> -->
<!--             </div> -->
<!--             <div class="female_specific"> -->
<!--             	<b>33%</b> -->
<!--             	<span>女238人</span> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="left_statistics" style="margin-left: 0;"> -->
<!--             <h4>信息中心</h4> -->
<!--             <span  style="color:#4fc1e9;">44人</span> -->
<!--             <div class="man"> -->
<!--             	<p class="man_i" style="width:67%;"></p> -->
<!--             </div> -->
<!--         	<div class="man_specific"> -->
<!--         		<b>67%</b> -->
<!--         		<span>男238人</span> -->
<!--         	</div> -->
<!--             <div class="female"> -->
<!--             	<p class="female_i" style="width:33%;"></p> -->
<!--             </div> -->
<!--             <div class="female_specific"> -->
<!--             	<b>33%</b> -->
<!--             	<span>女238人</span> -->
<!--             </div> -->
<!--         </div> -->
        <div class="statistics_option">
            <div style="margin: 0 auto;width: 85px;">
<!--                 <a href="javascript:void(0);" style="margin-right: 20px;"><img src="images/statistics_export.png" height="90" width="84"></a> -->
<!--                 <a href="javascript:void(0);"><img src="images/statistics_share.png" height="89" width="83"></a> -->
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>