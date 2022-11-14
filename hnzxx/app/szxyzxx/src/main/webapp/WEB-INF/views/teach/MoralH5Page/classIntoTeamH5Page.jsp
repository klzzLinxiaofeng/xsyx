<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="pragram" content="no-cache">
<meta name="viewport" content="width=640,user-scalable=no, target-densitydpi=device-dpi">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/report_forms.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/moralWidget/classIntoTeamWidget.js"></script>
<title>红旗进入班集体评价报表</title>
<style>
th.project, td.project{
    width: 190px;
}
.chzn-container-single .chzn-single{height:77px;line-height:77px;font-size:28px;}
.chzn-container-single .chzn-single div b{background-position:0 60%}
.chzn-container .chzn-results .active-result{font-size:20px;line-height:30px;}
.choice-class,.choice-term,.choice-or,.choice-specific,.choice-type,.choice-month,.choice-week{
	color:#8f8f9e;
}
.details-table table tbody.details-table table tbody, tbody.details-table2, tbody.normaltablelist {
    font-size: 23px;
}
span.green {
    color: #3ac982;
    background: #fff;
}
td p.name{
	width:250px;
}
.details-table table tbody td div.schedule,.details-table table tbody td div.schedule{
	width:66%;
}
</style>
<script type="text/javascript">
var termCode = 0;
var schoolYear = 0;
var teamId = "";
var beginDate = 0;
var endDate = 0;
var schoolId = $(".controllerData").attr("data-schoolId");
var flag = "${flag == 1 ? true:false}";
var appGetTeamId = "${teamId}";
var appGetWeekTime = "${weekName}";
var appGetTermCode = "${schoolTermCode}";
var appGetSchoolYear = "${schoolYear}";
var appGetBeginDate = "${beginDate}";
var appGetEndDate = "${endDate}";
var urlSearchForGrade = "${sca:getESBServerUrl()}/school/moral/team/summaryByGrade/jsonp";
var urlForSchoolYearList = "${sca:getESBServerUrl()}/school/basic/term/listAll/jsonp";
var urlForClassList = "${sca:getESBServerUrl()}/school/teacher/team/list/jsonp";
var urlForTableList = "${sca:getESBServerUrl()}/school/moral/team/summaryByTeam/jsonp";
$(function(){
	getmonth();
	schoolYearList(urlForSchoolYearList,urlForClassList,urlForTableList,urlSearchForGrade);
// 	tableList(urlForTableList);
});
</script>
</head>
<body>
<div class="controllerData" style="display: none;" data-schoolId="${schoolId }" data-userId="${userId }" data-role="${role }" ></div>
<div class="menu-choice">
<div class="choice-term" style="width:24%;line-height: 30px;">
        <p><span class="xq_name"></span><span class="tog">﹀</span></p>
    </div>
     <div class="choice_prom">
<!--      	<div class="school-year"> -->
<!--                 <span></span> -->
<!--                 <ul class="schoolTerm"> -->
<!--                     <li>上学期</li> -->
<!--                     <li>下学期</li> -->
<!--                 </ul> -->
<!--         </div> -->
        </div>
    <i></i>
   <div class="choice-class" id="btnshow" style="width: 27%;">
        <p><span class="c_name" id="3">全部</span><span class="tog">﹀</span></p>
    </div>
    <div class="choice_show">
        <div class="choice_remove">
            <p class="selectClass">选择年级</p>
            <ul>
<!-- 				<li>全部</li> -->
            </ul>
        </div>
        <div class="minutes">
            <p class="selectTerm">选择班级</p>
<!--             <ul> -->
<!-- 				班级列表 -->
<!--             </ul> -->
        </div>
    </div>
    <i></i>
   <!--  <select id="select" style="width:100px;float: left;" onchange="show();">
        <option value="1">月份</option>
        <option value="2">周次</option>
    </select> -->
    <div class="choice-type" id="select" style="width:18.5%;float: left;">
        <p><span class="c_type">月份</span><span class="tog">﹀</span></p>
    </div>
    <div class="choice_t">
        <div class="choice_y">
            <p class="selectClass">选择</p>
            <ul>
               <li class="grade" data-id="1">月份</li> 
               <li data-id="2">周次</li> 
            </ul>
        </div>
    </div>
    <i></i>
	    <div id="select_div_month" class="select_div" style="">
<!-- 	    onFocus="WdatePicker({dateFmt:'yyyy年M月',minDate:bgDate,maxDate:edDate})" ,onpicked:search-->
<!-- 			<input type="text"       style="width:130px;margin:0;height:65px;line-height:65px;font-size:20px;"/> -->			
<!-- 				<select style="width:130px;margin:0;height:77px;line-height:77px;font-size:28px;" > -->
<!-- 				</select> -->
<!-- 		<select style="width:150px;margin:0;height:77px;line-height:77px;font-size:24px;" id="d4" onchange="search();"></select> -->
		 <div class="choice-month"  style="width:22%;float: left;">
        <p><span class="c_month">月份</span><span class="tog">﹀</span></p>
    </div>
    <div class="choice_mon">
        <div class="choice_mo">
            <p class="selectClass">选择</p>
            <ul id="select_month">
            </ul>
        </div>
    </div>
		</div>
		<div id="select_div_week" class="select_div" style="display: none;">
<!-- 			<select onclick="search();" id="select_week" style="width:159px;" onchange="search();"></select> -->
			 <div class="choice-week"  style="width:22%;float: left;">
        <p><span class="c_week">周次</span><span class="tog">﹀</span></p>
    </div>
    <div class="choice_wee">
        <div class="choice_we">
            <p class="selectClass">选择</p>
            <ul id="select_week">
            </ul>
        </div>
    </div>
		</div>
    
    <div id="choice_bg"></div>
</div>
<div class="overall-score">
    <h1>班集体评价数据分析</h1>
    <p>统计总分</p>
</div>
<div class="clear"></div>
<div class="details">
    <h2 class="details_awarded"><!-- 总分+25 --></h2>
    <div class="nape">
        <span class="nape_deduct"  style="color:#ff544b"></span><i></i>
        <span class="nape_awarded" style="color:#2299ee"></span>
    </div>
</div>
<div class="character">
    <p>统计详情</p>
</div>
<div class="details-table" style="font-size:23px;">
    <table>
        <thead>
            <tr>
                <th style="width: 180px;" class="project">项目</th>
                <th>分数</th>
            </tr>
        </thead>
        <tbody class="table-list">
            <!-- 项目各项列表 -->
        </tbody>
    </table>
</div>
</body>
<script type="text/javascript">
var c_name=0;
function select(urlForTableList){
	c_name = $(".c_name").attr("id");
	if(c_name == 2 && c_name !=null && c_name != "" && c_name != "undefined"){
		var urlSearchForTeam = "${sca:getESBServerUrl()}/school/moral/team/summaryByTeam/jsonp";
		teamH5searchForTeam(newyear,newmonth,urlSearchForTeam);
	}else if(c_name == 1 && c_name !=null && c_name != "" && c_name != "undefined"){
		var urlSearchForGrade = "${sca:getESBServerUrl()}/school/moral/team/summaryByGrade/jsonp";
		teamH5searchForGrade(newyear,newmonth,urlSearchForGrade);
	}else if(c_name == 3 && c_name !=null && c_name != "" && c_name != "undefined"){
		var urlSearchForSchool = "${sca:getESBServerUrl()}/school/moral/team/summaryBySchool/jsonp";
		teamH5searchForSchool(newyear,newmonth,urlSearchForSchool,urlForTableList);
	}
}
</script>
</html>