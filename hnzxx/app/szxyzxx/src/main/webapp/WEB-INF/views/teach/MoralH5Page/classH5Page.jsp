<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="pragram" content="no-cache">
<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/report_forms.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/moralWidget/widget.js"></script>
<title>学生课堂不良数据分析</title>
<script type="text/javascript">
var role = $(".controllerData").attr("data-role");
var urlSearchForTeam = "${sca:getESBServerUrl()}/school/moral/class/summaryByTeam/jsonp";
var urlSearchForGrade = "${sca:getESBServerUrl()}/school/moral/class/summaryByGrade/jsonp";
var urlSearchForSchool = "${sca:getESBServerUrl()}/school/moral/class/summaryBySchool/jsonp";
$(function(){
    $(".analysis").hide();//隐藏wenben
    $(".analysis:eq(0)").show();//显示第一个wenben
    $(".classify a").click(function(){
       	$(".check").removeClass("check");//移除样式
       	$(this).addClass("check");//添加样式
       	var i=$(this).index();//获得下标
      	 	$(".analysis").hide();//隐藏wenben
       	$(".analysis:eq("+i+")").show();//显示第i个wenben
       	select();
    });
    
    $(".personal").on("click","li div",function(){//单个学生选中时查询
//     	$(".personal li p").css("display","block");
//     	$(".personal li p").addClass("called");
        $(".personal li div").addClass("default").removeClass("pitch-on").next(".called").hide();
        $(this).addClass("pitch-on").removeClass("default").next(".called").show();
        studentId = $(this).children("p").attr("data-studentId");
        if(role != "PARENT"){
        	var urlForStudent = "${sca:getESBServerUrl()}/school/moral/class/summaryByTeam/jsonp";
	        oneStudent(newyear,newmonth,urlForStudent);
        }
	});
});
var termCode = 0;
var schoolYear = 0;
var teamId = 0;
var beginDate = 0;
var endDate = 0;
var schoolId = 0;
var urlForSchoolYearList;
var urlForClassList;
var urlForTableList;
var urlStudent;
$(function(){
	getmonth();
	urlStudent = "${sca:getESBServerUrl()}/school/parent/children/listWithSchool/jsonp";
	urlForSchoolYearList = "${sca:getESBServerUrl()}/school/basic/term/listAll/jsonp";
	urlForClassList = "${sca:getESBServerUrl()}/school/teacher/team/list/jsonp";
	urlForTableList = "${sca:getESBServerUrl()}/school/moral/progress/summaryByTeam/jsonp";
	schoolYearList(urlForSchoolYearList,urlForClassList,urlForTableList,urlStudent);
});
</script>
<style>
.choice-class,.choice-term,.choice-or,.choice-specific,.choice-type,.choice-month,.choice-week{
	color:#8f8f9e;
}
td p.name{
    width: 230px;
}
.default{
    background: #fff;
}
span.green{background:#fff;}
.personal{
	float:left;
}
</style>
</head>
<body>
<div class="tank"></div>
<div class="controllerData" style="display: none;" data-schoolId="${schoolId }" data-userId="${userId }" data-role="${role }"></div>
<div class="message" style="display: none;" data-message="class"></div>
<div class="menu-choice">
<c:choose>
  <c:when test="${role == 'PARENT' }"><!-- 家长角色进入显示 -->
    <div class="choice-term" style="width:24%;line-height: 30px;">
	        <p><span class="xq_name"></span><span class="tog" >﹀</span></p>
	</div>
    <div class="choice_prom"></div>
  </c:when>
  <c:otherwise><!-- 其他角色进入显示 -->
    <div class="choice-term" style="width:24%;line-height: 30px;">
	        <p><span class="xq_name"></span><span class="tog">﹀</span></p>
	</div>
    <div class="choice_prom"></div>
  </c:otherwise>
</c:choose>
    <i></i>
   <div class="choice-class" id="btnshow" style="width: 27%;">
		<c:choose>
			<c:when test="${role == 'CLASS_MASTER' }">
		        <p><span class="c_name" id="2">全部</span><span class="tog">﹀</span></p>
			</c:when>
			<c:otherwise>
		        <p><span class="c_name" id="3">全部</span><span class="tog">﹀</span></p>
			</c:otherwise>
		</c:choose>
    </div>
    <div class="choice_show">
        <c:choose>
        <c:when  test="${role == 'PARENT' }">
	        <div class="choice_remove">
	            <p class="selectClass">选择学生</p>
	            <ul>
	<!--               <li>一年级</li> -->
	            </ul>
	        </div>
        </c:when>
        <c:otherwise>
        	<div class="choice_remove">
	            <p class="selectClass">选择年级</p>
	            <ul>
	<!--               <li>一年级</li> -->
	            </ul>
	        </div>
	        <div class="minutes">
	            <p class="selectTerm">选择班级</p>
	        </div>
        </c:otherwise>
        </c:choose>
    </div>
    <i></i>
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
			 <div class="choice-week"  style="width:22%;float: left;" onchange="select()" >
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
    <h1>学生课堂不良数据分析</h1>
</div>
<c:if test="${role == 'CLASS_MASTER' }">
	<div class="classify" style="background: #f6f6f6;">
	    <a href="javascript:void(0);" onclick="select();" class="check" style="border-right: 1px solid #d3d3d3;" id="one">全部</a>
	    <a href="javascript:void(0);" onclick="myself(newmonth)" id="two">个人</a>
	</div>
</c:if>
<div class="clear"></div>
<c:if test="${role == 'SUBJECT_TEACHER' }">
	<div class="classify" style="background: #f6f6f6;">
	    <a href="javascript:void(0);" class="check" style="border-right: 1px solid #d3d3d3;" id="one">全部</a>
	    <a href="javascript:void(0);" onclick="myself(newmonth)" id="two">个人</a>
	</div>
</c:if>
<div class="clear"></div>
<div class="analysis" style="background:#fff;">
    <div style="height:250px;width:250px;margin:0 auto;" id="container"></div>
    <div class="tst"></div>
    <div class="specific">
        <h2><span class="count">总数量0</span></h2>
<!--         <p class="grade_class">全年级占比0%</p> -->
    </div>
    <div class="details-table" style="font-size:23px;">
    <table>
        <thead>
            <tr>
                <th style="width: 180px;" class="project">不良行为</th>
                <th>次数</th>
                <th style="width: 145px;" class="dataPercent">全校占比</th>
            </tr>
        </thead>
        <tbody class="normaltablelist">
<!--             <tr> -->
<!--                 <td class="project"><p class="name her">品德课堂优化评价卡1</p></td> -->
<!--                 <td> -->
<!--                     <span class="green">5</span> -->
<!--                     <div class="schedule"> -->
<!--                         <p class="green" style="width:100%";></p> -->
<!--                     </div> -->
<!--                 </td> -->
<!--                 <td class="range">100%</td> -->
<!--             </tr> -->
        </tbody>
    </table>
</div>
</div>
<div class="analysis">
<div class="personal" ><!-- 学生列表 --><!-- style="height: 920px;" -->
    <ul>
<!--         <li> -->
<!--             <div class="default"> -->
<%--                 <img src="{pageContext.request.contextPath}/res/images/noPhoto.jpg"> --%>
<!--                 <p>张三</p> -->
<!--             </div> -->
<!--             <p class="called"></p> -->
<!--         </li> -->
    </ul>
</div>

<div class="details-table" id="details-table2"style="font-size:23px;">
    <table>
        <thead>
            <tr>
                <th style="width: 180px;" class="project">不良行为</th>
                <th>人数</th>
                <th style="width: 145px;" id="datapercent">全班级占比</th>
            </tr>
        </thead>
<!--         <tbody class="details-table2"> -->
<!--             <tr> -->
<!--                 <td class="project"><p class="name her">品德课堂优化评价卡2</p></td> -->
<!--                 <td> -->
<!--                     <span class="green">5</span> -->
<!--                     <div class="schedule"> -->
<!--                         <p class="green" style="width:100%";></p> -->
<!--                     </div> -->
<!--                 </td> -->
<!--                 <td class="range">100%</td> -->
<!--             </tr> -->
<!--         </tbody> -->
    </table>
</div>
</div>
</body>
<script type="text/javascript">
getmonth();
function myself(newmonth){
	$(".personal").html("").append("<ul></ul>");
		$(".project").html("不良行为");
		$("#datapercent").html("班级占比");
// 	if(newmonth === "undefined" || newmonth == null || newmonth == ""){
// 		$(".personal").css("height","920px");
// 		$(".personal").html("<img src='${pageContext.request.contextPath}/res/images/noPhoto.jpg' style='width:799px;height:920px;position: absolute;top: 35%;left: 0;right: 0;'>");
// 		return false;
// 	}
	if(teamId!="" && teamId!=null){
		$.ajax({
			type : "GET",  
			url : "${sca:getESBServerUrl()}/school/basic/team/student/list", //{sca:getESBServerUrl()}/school/basic/team/student/list
			dataType : "jsonp",//数据类型为jsonp  
			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			data:{
				teamId : teamId
			},
			success : function(data){   //console.log("学生"+JSON.stringify(data));
				$(".personal").children("ul").html("");
				var data = data.data;
				if(data.length>0){
					for(var i=0;i<data.length;i++){
						if(data[i].userIcon == null || data[i].userIcon == ""){
							$(".personal").children("ul").append("<li><div class='default'>"+
									"<img src='${pageContext.request.contextPath}/res/images/noPhoto.jpg'>"+
									"<p data-studentId='"+data[i].studentId+"'>"+data[i].name+"</p></div>"+
									"<p class='called'></p></li>");
						}else{
							$(".personal").children("ul").append("<li><div class='default'>"+
																"<img src='"+data[i].userIcon+"'>"+
																"<p data-studentId='"+data[i].studentId+"'>"+data[i].name+"</p></div>"+
																"<p class='called'></p></li>");
						}
					}
				}else{
					$(".personal").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 375px;left: 0;right: 0;'><img src='/res/css/dygl/images/unhappy.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size: 30px;font-family: 微软雅黑;font-weight: bold;'>暂无学生对应数据~</p></div>");
					return false;
				}
			},
			error:function(){  
			    alert('fail：查询所有学生数据错误');  
			}
		});
	}else{
		$(".personal").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 375px;left: 0;right: 0;'><img src='/res/css/dygl/images/unhappy.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size: 30px;font-family: 微软雅黑;font-weight: bold;'>暂无学生对应数据~</p></div>");
		return false;
	}
}
function parentFindStudentList (newyear,newmonth,teamId,termCode,studentId){
	var countstudent = 0;
	var countteam = 0;
	var napepercent = 0;//本班占比（取值用的变量）
	var teamPercent= 0;//班级在年级的占比
	$(".normaltablelist").html("");
	$(".details-table").find(".dataPercent").html("班级占比");
	if($("#select_div_week").is(":hidden")){
		var month = newmonth;
		var year = newyear;
		beginDate = year+"-"+selectmonth+"-1";
		var lastdate  = new Date(year,selectmonth,0);   
	    endDate = year + '-' + selectmonth + '-' + lastdate.getDate();//alert("beginDate_month:"+beginDate);alert("endDate_month:"+endDate);
	    $.ajax({  
			type : "GET",  
			url : "${sca:getESBServerUrl()}/school/moral/class/summaryByTeam/jsonp",
			dataType : "jsonp",//数据类型为jsonp  
			jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
			data:{
				termCode  : termCode,
				teamId : teamId,
				studentId : studentId,
				beginDate : beginDate,
				endDate : endDate
			},
			success : function(data){   //console.log("单个学生的数据"+JSON.stringify(data));
			$(".normaltablelist").html("");
			$(".tst").hide();
			$('#container').hide();
			$(".specific").css("margin-top","10px")
				var data = data.data;
				if(data.length>0){
					$(".specific").show();
					for(var i=0;i<data.length;i++){
						napepercent = data[i].ratio;
						percent = parseFloat((napepercent * 100).toFixed(0)) + "%";
						countstudent += parseInt(data[i].count);
						$(".normaltablelist").append("<tr><td class='project'><p class='name her'>"+data[i].objectName+"</p></td><td>"+
														"<span class='green'>"+data[i].count+"</span>"+
														  	"<div class='schedule'>"+
														  		"<p class='green' style='width:"+percent+"';></p>"+
														  	"</div>"+
														"</td>"+"<td class='range'>"+percent+"</td></tr>");
					}
				}else{
					$(".tst").show();
					$(".specific").hide();
					$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 385px;left: 0;right: 0;'><img src='/res/css/dygl/images/happy.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们表现很好，课堂上没有不良行为记录~</p></div>");
					return false;
				}
				$.ajax({
					type : "GET",  
					url : "${sca:getESBServerUrl()}/school/moral/class/summaryByTeam/jsonp",
					dataType : "jsonp",//数据类型为jsonp  
					jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
					data:{
						termCode  : termCode,
						teamId : teamId,
						beginDate : beginDate,
						endDate : endDate
					},
					success : function(data){   //console.log("班级2"+JSON.stringify(data));
						var data = data.data;
						for(var i=0;i<data.length;i++){
							countteam += parseInt(data[i].count);//学生在班级的占比
						}
						$(".specific").find(".count").html("");
						$(".specific").find(".count").html("总数量"+countstudent);
						if(countteam!=0){
							var dper = parseInt((countstudent/countteam)*100) + "%";
							$(".grade_class").html("<p>班级占比 "+dper+"</p>");
						}else{
							$(".grade_class").html("<p>班级占比 0%</p>");
						}
					},
					error:function(){  
					    alert('fail：获取班级参数错误');  //参数用于计算百分比
					} 
				});
			},
			error:function(){  
			    alert('fail：获取学生数据错误');  
			}  
		});
	}else if($("#select_div_month").is(":hidden")){
			$(".normaltablelist").html("");
// 			beginDate=$(this).data("prev");//获得周次开始的时间
// 			endDate=$(this).data("next");//获得周次结束的时间   alert("beginDate_week:"+beginDate);alert("endDate_week:"+endDate);
			$.ajax({  
				type : "GET",  
				url : "${sca:getESBServerUrl()}/school/moral/class/summaryByTeam/jsonp",
				dataType : "jsonp",//数据类型为jsonp  
				jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
				data:{
					termCode  : termCode,
					teamId : teamId,
					studentId : studentId,
					beginDate : beginDate,
					endDate : endDate
				},
				success : function(data){   //console.log("单个学生的数据"+JSON.stringify(data));
				$(".normaltablelist").html("");
				$(".tst").hide();
				$('#container').hide();
				$(".specific").css("margin-top","10px")
					var data = data.data;
					if(data.length>0){
						$(".specific").show();
						for(var i=0;i<data.length;i++){
							napepercent = data[i].ratio;
							percent = parseFloat((napepercent * 100).toFixed(0)) + "%";
							countstudent += parseInt(data[i].count);
							$(".normaltablelist").append("<tr><td class='project'><p class='name her'>"+data[i].objectName+"</p></td><td>"+
															"<span class='green'>"+data[i].count+"</span>"+
															  	"<div class='schedule'>"+
															  		"<p class='green' style='width:"+percent+"';></p>"+
															  	"</div>"+
															"</td>"+"<td class='range'>"+percent+"</td></tr>");
						}
					}else{
						$(".tst").show();
						$(".specific").hide();
						$(".tst").html("<div style='background: #fff;width:100%;height:700px;position: absolute;top: 385px;left: 0;right: 0;'><img src='/res/css/dygl/images/happy.png' style='margin: 20px auto 50px;display: block;'><p style='text-align: center;font-size:24px;color:#000'>孩子们表现很好，课堂上没有不良行为记录~</p></div>");
						return false;
					}
					$.ajax({
						type : "GET",  
						url : "${sca:getESBServerUrl()}/school/moral/class/summaryByTeam/jsonp",
						dataType : "jsonp",//数据类型为jsonp  
						jsonp: "jsonpCallback",//服务端用于接收callback调用的function名的参数
						data:{
							termCode  : termCode,
							teamId : teamId,
							beginDate : beginDate,
							endDate : endDate
						},
						success : function(data){   //console.log("班级2"+JSON.stringify(data));
							var data = data.data;
							for(var i=0;i<data.length;i++){
								countteam += parseInt(data[i].count);//学生在班级的占比
							}
							$(".specific").find(".count").html("");
							$(".specific").find(".count").html("总数量"+countstudent);
							if(countteam!=0){
								var dper = parseInt((countstudent/countteam)*100) + "%";
								$(".grade_class").html("<p>班级占比 "+dper+"</p>");
							}else{
								alert(countteam);
								$(".grade_class").html("<p>班级占比 0%</p>");
							}
						},
						error:function(){  
							alert('fail：获取班级参数错误');  //参数用于计算百分比
						} 
					});
				},
				error:function(){  
					alert('fail：获取学生数据错误');  
				}  
			});
	}
}
function select(){
	$(".details-table table tbody").html("");
	var c_name = $(".c_name").attr("id");
	if(c_name == 2 && c_name !=null && c_name != "" && c_name != "undefined"){
// 		var urlSearchForTeam = "${sca:getESBServerUrl()}/school/moral/class/summaryByTeam/jsonp";
// 		var urlSearchForGrade = "${sca:getESBServerUrl()}/school/moral/class/summaryByGrade/jsonp";
		searchForTeam(newyear,newmonth,urlSearchForTeam,urlSearchForGrade);
	}else if(c_name == 1 && c_name !=null && c_name != "" && c_name != "undefined"){
// 		var urlSearchForGrade = "${sca:getESBServerUrl()}/school/moral/class/summaryByGrade/jsonp";
// 		var urlSearchForSchool = "${sca:getESBServerUrl()}/school/moral/class/summaryBySchool/jsonp";
		searchForGrade(newyear,newmonth,urlSearchForGrade,urlSearchForSchool);
	}else if(c_name == 3 && c_name !=null && c_name != "" && c_name != "undefined"){
// 		var urlSearchForSchool = "${sca:getESBServerUrl()}/school/moral/class/summaryBySchool/jsonp";
		searchForSchool(newyear,newmonth,urlSearchForSchool);
	}
}
function choicefunction(){
	var one = $(".classify #one").attr("class");
	var two = $(".classify #two").attr("class");
	if(one == "check" && two == null || two == "" || two == "undefined"){
		select();
	}else if(two == "check" && one == null || one == ""){
		
	}else{
		select();
	}
}
</script>
</html>