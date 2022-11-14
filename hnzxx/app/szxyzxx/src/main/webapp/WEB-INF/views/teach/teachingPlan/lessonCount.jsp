<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import="platform.szxyzxx.web.common.contants.SysContants"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/dept_selector_js.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sca:getDefaultSchoolName()}</title>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/modules/exporting.js"></script>
<style type="text/css">
.highcharts-container{
	margin:0 auto;
	width:100%;
}
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
    font-size: 14px;background-color: #4993b3;line-height: 30px;text-align: center;  height: 30px;color: #fff;width: 300px;margin:10px 0 60px 0;
}
.highchart_div{
	padding-top:50px;
	position:relative;
}
.highchart_div span{
	position:absolute;
	top:50px;
	right:110px;
	  color: #000;
  	z-index: 9999;
}
.figure{
	position:absolute;
	top:82px;
	background-color:#fff;
	width:100%;
}
</style>
<script type="text/javascript">
$(function () {
	$(window).resize(function() {
		  $('#containerOfSubject,#containerOfGrade,.highcharts-container').css("width","90%");
		});
	dateChangeInit("schoolYear","termCode");
	dateChangeInit("schoolYear1","termCode1");
	dateChangeInit("schoolYear2","termCode2");
	
	$.SchoolYearSelector({
		"selector" : "#schoolYear",
		"condition" : {},
		"afterHandler" : function(selector) {
			var schoolYear = $("#schoolYear").val();
			getTermBySchoolYear("termCode",schoolYear);  //获取学年下拉选
			seacher("teacher");
		}
	});
	
	$.SchoolYearSelector({
		"selector" : "#schoolYear1",
		"condition" : {},
		"afterHandler" : function(selector) {
			var schoolYear = $("#schoolYear1").val();
			getTermBySchoolYear("termCode1",schoolYear);  //获取学年下拉选
			seacher("subject");
		}
	});
	
	$.SchoolYearSelector({
		"selector" : "#schoolYear2",
		"condition" : {},
		"afterHandler" : function(selector) {
			var schoolYear = $("#schoolYear2").val();
			getTermBySchoolYear("termCode2",schoolYear);  //获取学年下拉选
			seacher("grade");
		}
	});
	
	$("#teacher").chosen();
});

//学年下拉选

//触发下拉框事件
function dateChangeInit(schoolYearId,termCodeId){
	$("#"+schoolYearId).change("on",function(){
		var currentValue = $(this).val();
		if(currentValue == ""){
			$("#"+termCodeId).html("").append("<option value=''>请选择</option>");
			$("#"+termCodeId).trigger("liszt:updated"); 
			$("#"+termCodeId).chosen();
		}else{
			getTermBySchoolYear(termCodeId,currentValue);
		}
	});
}

//根据学年获取学期
function getTermBySchoolYear(termCodeId,schoolYear){
	$("#"+termCodeId).html("");
	var htmlSelect = "<option value=''>请选择</option>";
	var url = "${ctp}/generalTeachingAffair/lessonplan/getTermBySchoolYear?schoolYear="+schoolYear;
	if(schoolYear == "" || schoolYear == "undefined" || schoolYear == null){
		$("#"+termCodeId).trigger("liszt:updated"); 
		$("#"+termCodeId).chosen();
		return;
	}
	$.post(url,null,function(data){
		for(var flag in data){
			htmlSelect += "<option value='" + data[flag].code + "'>"+ data[flag].name +"</option>"
		}
		$("#"+termCodeId).append(htmlSelect);
		$("#"+termCodeId).trigger("liszt:updated"); 
		$("#"+termCodeId).chosen();
	},'json')
}

//=====================以下是统计表、图=======================

//存放查询参数
var $parameters = {};

//==========统计页面的所有条件查询============//
function seacher(type){
	if(type === "teacher"){
		var schoolYear = $("#schoolYear").val();
		var termCode = $("#termCode").val();
		var teacherId = $("#teacher").val();
		if(schoolYear != "" && schoolYear != "undefined" && schoolYear != null){
			$parameters.schoolYear = schoolYear;
		}else{
			$parameters.schoolYear = null;
		}
		if(termCode != "" && termCode != "undefined" && termCode != null){
			$parameters.termCode = termCode;
		}else{
			$parameters.termCode = null;
		}
		if(teacherId != "" && teacherId != "undefined" && teacherId != null){
			$parameters.teacherId = teacherId;
		}else{
			$parameters.teacherId = null;
		}
		getCountDataByTeacher($parameters);
	}else if(type === "subject"){
		var schoolYear = $("#schoolYear1").val();
		var termCode = $("#termCode1").val();
		if(schoolYear != "" && schoolYear != "undefined" && schoolYear != null){
			$parameters.schoolYear = schoolYear;
		}else{
			$parameters.schoolYear = null;
		}
		if(termCode != "" && termCode != "undefined" && termCode != null){
			$parameters.termCode = termCode;
		}else{
			$parameters.termCode = null;
		}
		getCountDataBySubject($parameters);
	}else if(type === "grade"){
		var schoolYear = $("#schoolYear2").val();
		var termCode = $("#termCode2").val();
		if(schoolYear != "" && schoolYear != "undefined" && schoolYear != null){
			$parameters.schoolYear = schoolYear;
		}else{
			$parameters.schoolYear = null;
		}
		if(termCode != "" && termCode != "undefined" && termCode != null){
			$parameters.termCode = termCode;
		}else{
			$parameters.termCode = null;
		}
		getCountDataByGrade($parameters);
	}else{
		alert("没有对应的查询");
		return;   //没有实际意义,正常情况下永远不会执行到
	}
}



//根据教师统计教案上传数量
function getCountDataByTeacher($parameters){
	$.post("${ctp}/generalTeachingAffair/pjteachingplan/countByTeacher", $parameters,function(returnData, status) {
		if ("success" === status) {
			var tableHtml = "";
			var allNumber = 0;
			var index = 0;
			for(var flag in returnData){
				index ++;
				tableHtml += "<tr>";
				tableHtml += "<td>" + index +"</td>";
				tableHtml += "<td>" + returnData[flag].teacherName +"</td>";
				tableHtml += "<td>" + returnData[flag].countNumber +"</td>";
				tableHtml += "</tr>";
				allNumber += returnData[flag].countNumber;
			}
			$("#tBody").html("").append(tableHtml);
			$("#allNumber").text("总计："+parseInt(allNumber));
		}
	}, 'json');
}

//根据学科统计教案上传数量
function getCountDataBySubject($parameters){
	$.post("${ctp}/generalTeachingAffair/pjteachingplan/getTeacherSubjectData", $parameters,function(returnData, status) {
		if ("success" === status) {
			countBySubject(returnData);
		}
	}, 'json');
}

//根据年级统计教案上传数量
function getCountDataByGrade($parameters){
	$.post("${ctp}/generalTeachingAffair/pjteachingplan/findCountNumberByGrade", $parameters,function(returnData, status) {
		if ("success" === status) {
			countByGrade(returnData);
		}
	}, 'json');
}

function countBySubject(returnData){
	var data = [];
	var allNumber = 0
	for(var key in returnData){
		allNumber += returnData[key];
		data.push([key,returnData[key]]);
	}
	$("#containerOfSubjectAllNumber").text("总计："+allNumber);
$('#containerOfSubject').highcharts({
    chart: {
        credits: {
        	enabled: false
        	},
        type: 'column'
    },
    title: {
        text: '按学科统计教师上传教案数量'
    },
    subtitle: {
        text: ''
    },
    credits: {
        enabled: false
   },
   exporting: {
       enabled:false
},
    xAxis: {
        type: 'category',
        labels: {
            rotation: -45,
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    },
    yAxis: {
    	allowDecimals:false,
        min: 0,
        title: {
            text: '单位 (次)'
        }
    },
    legend: {
        enabled: false
    },
    tooltip: {
        pointFormat: '<b>{point.y:f} 次</b>'
    },
    plotOptions: {
        column: {
           colorByPoint:true
        }
    },
    series: [{
        name: 'Population',
		data: data,
        dataLabels: {
            enabled: true,
            rotation: -90,
            color: '#FFFFFF',
            align: 'right',
            format: '{point.y:f}', // one decimal
            y: 10, // 10 pixels down from the top
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    }]
});
}

function countByGrade(returnData){
	var data = [];
	var allNumber = 0
	for(var key in returnData){
		allNumber += returnData[key].countNumber;
		data.push([returnData[key].gradeName,returnData[key].countNumber]);
	}
	$("#containerOfGradeAllNumber").text("总计："+allNumber);
$('#containerOfGrade').highcharts({
    chart: {
        credits: {
        	enabled: false
        	},
        type: 'column'
    },
    title: {
        text: '按年级统计教师上传教案数量'
    },
    subtitle: {
        text: ''
    },
    credits: {
        enabled: false
   },
   exporting: {
       enabled:false
},
    xAxis: {
        type: 'category',
        labels: {
            rotation: -45,
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    },
    yAxis: {
    	allowDecimals:false,
        min: 0,
        title: {
            text: '单位 (次)'
        }
    },
    legend: {
        enabled: false
    },
    tooltip: {
        pointFormat: '<b>{point.y:f} 次</b>'
    },
    plotOptions: {
        column: {
           colorByPoint:true
        }
    },
    series: [{
        name: 'Population',
		data: data,
        dataLabels: {
            enabled: true,
            rotation: -90,
            color: '#FFFFFF',
            align: 'right',
            format: '{point.y:f}', // one decimal
            y: 10, // 10 pixels down from the top
            style: {
                fontSize: '13px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    }]
});
}
</script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="教学计划统计" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white" style="height:127px;">
					<div class="widget-head">
						<h3>
							教学计划统计列表
						</h3>
					</div>
					<div class="content-widgets">
					<div style="position:relative;">
					
					<div class="tb" id="tb">
						<a href="javascript:void(0)" class="bj">按教师统计</a>
						<a href="javascript:void(0)">按学科统计</a>
						<a href="javascript:void(0)">按年级统计</a>
					</div>
	<!-- 性别	 -->
	<div class="figure">
    <div class="tj">
    <div class="select_b">
    	<div class="select_div">
    		<span>学年：</span>
	    	<select id="schoolYear"></select>
    	</div>
    	<div class="select_div">
    		<span>学期：</span>
	    	 <select id="termCode"></select> 
    	</div>
    	<div class="select_div">
    		<span>  教师：</span>
	    	<select id="teacher">
	  		<option value="">请选择</option>
	  		<c:forEach items="${teacherList}" var="teacher">
	  			<option value="${teacher.id}">${teacher.name}</option>
	  		</c:forEach>
	  	</select> 
    	</div>
    	<button onclick="seacher('teacher')" class="btn btn-primary">查询</button>
    	<div class="clear"></div>
    </div>
	  	<div class="table_div" style="margin-top:50px;">
		  	<table class="responsive table table-striped">
		  		<thead>
		  		<tr>
		  			<td style="width:33%">序号</td>
		  			<td style="width:33%">教师姓名</td>
		  			<td style="width:33%">上传量</td>
		  		</tr>
		  		</thead>
		  		<tbody id="tBody">
		  		</tbody>
		  	</table>
	  	</div>
	  	<span style="font-size:14px;padding-left:66%" id="allNumber">总计:0</span>
	    <div id="container1" style="height:350px;"></div>
	</div>
    <div class="clear"></div>
 </div>
<div class="figure">
  <div class="tj">
  <div class="select_b">
    	<div class="select_div">
    		<span>学年：</span>
	    	<select id="schoolYear1"></select>
    	</div>
    	<div class="select_div">
    		<span>学期：</span>
	    	 <select id="termCode1"></select> 
    	</div>
    	<button onclick="seacher('subject')" class="btn btn-primary">查询</button>
    	<div class="clear"></div>
    </div>
    <div class="highchart_div">
    	<span id="containerOfSubjectAllNumber">总计：0</span>
        <div id="containerOfSubject" class="span12" style="height:400px;margin: 0 auto;"></div>
     </div>
    </div>
</div>
<div class="figure">
   <div class="tj">
   <div class="select_b">
    	<div class="select_div">
    		<span>学年：</span>
	    	<select id="schoolYear2"></select>
    	</div>
    	<div class="select_div">
    		<span>学期：</span>
	    	 <select id="termCode2"></select> 
    	</div>
    	<button onclick="seacher('grade')" class="btn btn-primary">查询</button>
    	<div class="clear"></div>
    </div>
    <div class="highchart_div">
    	<span id="containerOfGradeAllNumber">总计：0</span>
       <div id="containerOfGrade" class="span12" style="height:400px;margin: 0 auto;"></div>
     </div>
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
    $(".figure").css("top","-9999px");//隐藏wenben
    $(".figure:eq(0)").css("top","86px");//显示第一个wenben
    $("#tb a").click(function(){
        $(".bj").removeClass("bj");//移除样式
        $(this).addClass("bj");//添加样式
        var i=$(this).index();//获得下标
        $(".figure").css("top","-9999px");//隐藏wenben
        $(".figure:eq("+i+")").css("top","86px");//显示第i个wenben
    });
})
</script>
</body>
</html>
