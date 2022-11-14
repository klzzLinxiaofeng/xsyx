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
    height:455px;border:1px solid #bfbfbf;margin:20px 0 0 20px;width:47.5%;float:left;
}
.bt3{
	height:455px;border:1px solid #bfbfbf;margin:20px 20px 0 0;width:47.5%;float:right;
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
.bt h5, .bt2 h5, .zzt h5, .bt3 h5{
    display:block; overflow:hidden;white-space:nowrap; text-overflow:ellipsis;font-size: 14px;background-color: #4993b3;line-height: 30px;text-align: center;  height: 30px;color: #fff;width: 300px;margin:10px 0 60px 0;
    
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
	getStudentNumberOfSexData();
	getStudentNumberOfRaceData();
	getStudentNumberOfPoliticsData();
	getStudentNumberOfIsBoardedData();
	getStudentNumberOfGradeData();
});

function studentRaceNumber(returnData) {
	var k1 = returnData["unMinority"][0];
	var k2 = returnData["minority"][0];
	var k3 = returnData["other"][0];
	
	var kv1 = returnData["unMinority"][1];
	var kv2 = returnData["minority"][1];
	var kv3 = returnData["other"][1];
	
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
	                [k1, parseInt(kv1)],
	                [k3, parseInt(kv3)],
	                [k2, parseInt(kv2)],
	            ]
	        }]
	    });
}

function studentSexNumber(returnData) {
	var k1 = returnData["man"][0];
	var k2 = returnData["woman"][0];
	var k3 = returnData["untold"][0];
	
	var kv1 = returnData["man"][1];
	var kv2 = returnData["woman"][1];
	var kv3 = returnData["untold"][1];
	
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
	                [k1, parseInt(kv1)],
	                [k3, parseInt(kv3)],
	                [k2, parseInt(kv2)]
	            ]
	        }]
	    });
}

function studentPoliticsDataNumber (returnData) {
	var k1 = returnData["member"][0];
	var k2 = returnData["other"][0];
	
	var kv1 = returnData["member"][1];
	var kv2 = returnData["other"][1];
	
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
            name: '',
            data: [
				[k1, parseInt(kv1)],
				[k2, parseInt(kv2)]
            ]
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
            name: '',
            data: [
				[k1, parseInt(kv1)],
				[k3, parseInt(kv3)],
				[k2, parseInt(kv2)]
            ]
        }]
    });
}

function studentGradeNumber(returnData) {
	$.each(returnData, function(index, value) {
		var gradeId = value.id;
		var gradeName = value.name;
		var teamNumber = value.teamNumber;
		var studentNumber = value.studentNumber;
		var manNumber = value.manNumber;
		var womanNumber = value.womanNumber;
		var untoldNumber = value.untoldNumber;
		$("#gradeTable").children("tbody").append("<tr><td>" + gradeName + "</td><td>" + teamNumber + "</td><td>" + studentNumber + "</td><td>" + manNumber + "</td><td>" + womanNumber + "</td><td>" + untoldNumber + "</td></tr>");
// 		var sgCell = $("#gradeTable tbody tr").find("td[data-id='" + gradeId + "']").parent();
// 		sgCell.find("td[]").append("<p>" + teamNumber + "</p>");
// 		sgCell.find("td[data-num='1'").append("<p>" + teamNumber + "</p>");
// 		sgCell.find("td[2]").append("<p>" + StudentNumber + "</p>");
// 		sgCell.find("td[3]").append("<p>" + manNumber + "</p>");
// 		sgCell.find("td[4]").append("<p>" + womanNumber + "</p>");
// 		sgCell.find("td[5]").append("<p>" + untoldNumber + "</p>");
// 		sgCell.find("input").attr("data-id", subjectGradeId);
	});
}

function getStudentNumberOfSexData() {
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getStudentNumberOfSexData", function(returnData, status) {
		if ("success" === status) {
			studentSexNumber(returnData);
		}
	},'json');
}

function getStudentNumberOfRaceData() {
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getStudentNumberOfRaceData", function(returnData, status) {
		if ("success" === status) {
			studentRaceNumber(returnData);
		}
	},'json');
}

function getStudentNumberOfPoliticsData() {
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getStudentNumberOfPoliticsData", function(returnData, status) {
		if ("success" === status) {
			studentPoliticsDataNumber(returnData);
		}
	},'json');
}

function getStudentNumberOfIsBoardedData() {
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getStudentNumberOfIsBoardedData", function(returnData, status) {
		if ("success" === status) {
			studentIsBoardedNumber(returnData);
		}
	},'json');
}

function getStudentNumberOfGradeData() {
	$.post("${ctp}/schoolaffair/schoolDataStatistics/getStudentNumberOfGradeData", function(returnData, status) {
		if ("success" === status) {
			returnData = eval("(" + returnData + ")");
			studentGradeNumber(returnData);
		}
	});
}

</script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="学生统计" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学生统计
						</h3>
					</div>
					<div class="content-widgets">
					
					<div>
                    <div class="tb" id="tb">
                        <a href="javascript:void(0)" class="bj">人数统计</a>
                        <a href="javascript:void(0)">年级统计</a>
<!--                         <a href="javascript:void(0)">岗位统计</a> -->
<!--                         <a href="javascript:void(0)">学位统计</a> -->
<!--                         <a href="javascript:void(0)">科任教师统计</a> -->
<!--                         <a href="javascript:void(0)">年级任课教师统计</a> -->
<!--                         <a href="javascript:void(0)">部门统计</a> -->

                    </div>
                    <div class="figure">
    <div class="bt">
    	<h5 title="学生人数统计-男女统计饼图">${schoolName}学生人数统计-男女统计饼图</h5>
    	<div id="container1" style="height:350px;"></div>
    </div>
    <div class="zzt">
    	<h5 title="学生人数统计-民族统计饼图">${schoolName}学生人数统计-民族统计饼图</h5>
     	<div id="container" style="height:350px"></div>
    </div>
    <div class="clear"></div>
    <div class="bt2">
        <h5 title="学生人数统计-政治面貌统计饼图">${schoolName}学生人数统计-政治面貌统计饼图</h5>
        <div id="container2" style="height:350px;"></div>
    </div>
    <div class="bt3">
        <h5 title="学生人数统计-走读住读统计饼图">${schoolName}学生人数统计-走读住读统计饼图</h5>
        <div id="container3" style="height:350px;"></div>
    </div>
    <div class="clear"></div>
            
</div>

<div class="figure">
  <div class="tj">
                <h4>${schoolName}学生年级统计表<span></span></h4>
                <table id="gradeTable">
                <thead>
                    <tr>
                    	<td>年级</td>
                        <td>班级数</td>
                        <td>总人数</td>
                        <td>男生人数</td>
                        <td>女生人数</td>
                        <td>未标明人数</td>
                    </tr>
                 </thead>
                 <tbody>
<%--                     <c:forEach items="${gradeList }" var="grade"> --%>
<!--                     <tr> -->
<%--                      	<td data-id="${grade.id }">${grade.name }</td> --%>
<!--                      	<td data-num="1"></td> -->
<!--                         <td data-num="2"></td> -->
<!--                         <td data-num="3"></td> -->
<!--                         <td data-num="4"></td> -->
<!--                         <td data-num="5"></td> -->
<!--                     </tr> -->
<%--                     </c:forEach> --%>
                  </tbody>
            </table>
      </div>
      <div class="clear"></div>
</div>
<div class="figure">
   2
</div>
<div class="figure">
   3
</div>
<div class="figure">
   4
</div>
<div class="figure">
   5
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
