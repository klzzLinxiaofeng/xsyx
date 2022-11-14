<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<style>
	.minutes-rated a{width:80px;text-align:center}
</style>
<title>评价报表</title>
</head>
<body>
    <c:if test="${manager eq 'yes'}">
		 <input type="hidden" id="isvip" value="yes">
	</c:if>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="评价报表" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							评价报表
							<p class="btn_link" style="float: right;">
							<!-- <a href="javascript:void(0)" class="a4" onclick="">导出</a> -->
							<a href="javascript:void(0)"  class="a3" onclick="history.go(-1)"><i class="fa  fa-reply"></i>返回</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b" id="sel_div">
								
								<div class="select_div">
									<span>学年：</span> <select id="xn" style="width:120px;" ></select>
								</div>
								<div class="select_div">
									<span>学期：</span> <select id="xq" style="width:150px;" onchange="getClass()"></select>
								</div>
							<c:choose>
								<c:when test="${manager eq 'yes'}">
									<div class="select_div"><span>年级：</span> <select id="nj" style="width:120px;"></select></div>
									<div class="select_div"><span>班级：</span> <select id="bj" style="width:120px;"></select></div>
								</c:when>
								<c:otherwise>
									<div class="select_div"><span>班级：</span> <select id="teamId" style="width:120px;"></select></div>
								</c:otherwise>
							</c:choose>
<!-- 								<div class="select_div"><span>年级：</span> <select id="nj" style="width:120px;"></select></div> -->
<!-- 								<div class="select_div"><span>班级：</span> <select id="bj" style="width:120px;"></select></div> -->
								<div class="select_div">
									<span>选择：</span>
									<select id="select" style="width:120px;" onchange="show();">
										<option value="2">按周次查询</option>
										<option value="1">按月份查询</option>
									</select>
								</div>
								
									<div id="select_div_month" class="select_div" style="display: none;">
										<span>月份：</span>
										<input type="text" class="Wdate" id="d4" onFocus="WdatePicker({dateFmt:'yyyy年M月',minDate:begin,maxDate:end,onpicked:sad})" style="width:172px;"/>
									</div>
									<div id="select_div_week" class="select_div" style="display: none;">
										<span>周次：</span>
										<select id="select_week" style="width:240px;"></select>
									</div>
								<c:choose>
									<c:when test="${manager eq 'yes'}">
										<p> <button class="btn btn-primary" type="button" onClick="sure()">查询</button></p>
									</c:when>
									<c:otherwise>
										<p> <button class="btn btn-primary" type="button" onClick="select()">查询</button></p>
									</c:otherwise>
								</c:choose>
<!-- 								<p> <button class="btn btn-primary" type="button" onClick="select()">查询</button></p> -->
								<div class="clear"></div>
								<c:choose>
									<c:when test="${manager eq 'yes'}">
                            			
									</c:when>
									<c:otherwise>
										<div class="minutes-rated">
										<a href="javascript:void(0);" class="see-rated" onClick="sure()">全部</a>
										<a href="javascript:void(0);" onclick="myself()">学生个人</a>
		                        		</div>
									</c:otherwise>
								</c:choose>
								<div id="kb_tb">
                  			</div>
							</div>
                        </div>
						</div>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	 
</body>
<script type="text/javascript">
$(function(){
 show();
 $("#select").chosen();
});
function select(){
	if($(".minutes-rated").children().eq(0).attr("class") != ""
			&& $(".minutes-rated").children().eq(0).attr("class") != "undefined" 
			&& $(".minutes-rated").children().eq(0).attr("class") == "see-rated"){
		sure();
	}else if($(".minutes-rated").children().eq(1).attr("class") != ""
			&& $(".minutes-rated").children().eq(1).attr("class") != "undefined"
			&& $(".minutes-rated").children().eq(1).attr("class") == "see-rated"){
		myself();
	}
}
function sad(){
	$("#d4").blur();
}
function sure(){
	$(".minutes-rated").children().eq(0).addClass("see-rated");
	$(".minutes-rated").children().eq(1).removeClass("see-rated");
	var loader = new loadLayer();
	var year = $("#xn").val();
	var termCode = $("#xq").val();
	var manager = $("#isvip").val();
	var month = $("#d4").val();
	var week = $("#select_week").val();
	var teamId = null;
	var nj = null;
	var $requestData = {};
	var checkDate=$("#startDate").val();
	var monthNone = $("#select_div_month").attr("style");
	var weekNone = $("#select_div_week").attr("style");
	if($("#sel_div").find("#nj").length>0){
		nj = $("#nj").val();
		teamId = $("#bj").val();
	}else{
		teamId = $("#teamId").val();
		$("#teamId").chosen();
		if(teamId==""){
			$.error("暂无班级数据信息，请联系管理员");
			return false;
		}
	}
	if ("" === year || "undefined" === year) {
		$.error("请选择学年");
		return false;
	}
	if ("" === termCode || "undefined" === termCode) {
		$.error("请选择学期");
		return false;
	}
	if(monthNone == "display: none;"){
		if(("" === week || "undefined" === week) && ("" === month || "undefind" === month)){
			$.error("请选择周次");
			return false
		}
	}
	if(weekNone == "display: none;"){
		if(("" === month || "undefind" === month) && ("" === week || "undefined" === week)){
			$.error("请选择月份");
			return false;
		}
	}
	$requestData.teamId = teamId;
	$requestData.manager = manager;
	$requestData.termCode = termCode;
	$requestData.year = year;
	$requestData.gradeId = nj;
	$requestData.checkDate = checkDate;
	$requestData.month = month;
	$requestData.week = week;
	loader.show();
	$.post("${pageContext.request.contextPath}/teach/classOptimizing/bbxx", $requestData, function(data, status) {
		if("success" === status) {
			$("#kb_tb").html(data);
		}
		loader.close();
	});
}
</script>
<script type="text/javascript">
var begin;
var end;
$("#d4").on('focus',function(){
	WdatePicker({dateFmt:'yyyy年M月',minDate:begin,maxDate:end});
});

//去拿到学期起始时间或结束时间
//$('#xq').on('change',week);
function week(){
	var term=$('#xq').val();
	if("" === term || "undefind" === term){
		return false;
	}
	var $requestData = {};
	$requestData.code=$('#xq').val();
	$.get("${pageContext.request.contextPath}/teach/teamEvaluation/list/json", $requestData, function(data, status) {
		if("success" === status) {
			data = eval("(" + data + ")");
			begin=data.begin;
			end=data.end;
			if (begin != '') {
				var date;
				var myarray = begin.split("-");
				if (myarray[1].charAt(0) != '0') {
					date = myarray[0]+ "年"+ myarray[1]+ "月";
				} else {
					date = myarray[0]+ "年"+ myarray[1].charAt(1)+ "月";
				}
				$('#d4').val(date);
			}
			var today = new Date().Format("yyyy-MM-dd");
			$.getWeek({
				"selector" : "#select_week",
				"begin" : begin,
				"end" : end,
				"isClear" : false,
				"today" : today,
				"isSelectCurrentWeek" : true,
				"clearedOptionTitle" : "请选择学期"
			});
		}
	});
}
$(function(){
	$.initCascadeSelector({
		"type" : "team",
		"gradeFirstOptTitle":"全部 ",
		"teamFirstOptTitle":"全部 ",
		"yearChangeCallback" : function(year) {
			if(year != "") {
				$.SchoolTermSelector({
					"selector" : "#xq",
					"condition" : {"schoolYear" : year},
					"afterHandler" : function($this) {
						$this.change();
						$("#xq_chzn").remove();
						$this.show().removeClass("chzn-done").chosen();
					}
				});
			} else {
				$("#xq").val("");
				$("#xq_chzn").remove();
				$("#xq").show().removeClass("chzn-done").chosen();
			}
		}
	});
	sure();
	getClass();
});
function getClass(){
	var schoolYear = $("#xn").val();
	var url = "${pageContext.request.contextPath}/teach/teamEvaluation/getTeam";
	$.post(url, {"schoolYear":schoolYear}, function(data,status) {
		var obj = eval("(" + data + ")");
		if(status == "success"){
			$("#teamId").html("");
			for(var i=0; i<obj.length;i++){
				var opt = "<option value='"+obj[i].teamId+"'>"+obj[i].teamName+"</option>";
				$("#teamId").append(opt);
			}
			if(obj.length == 0){
 				$("#teamId").append("<option value=''>请选择</option>");
			}
		}
		$("#teamId").chosen();
	});
	$("#d4").val("");
	var term=$('#xq').val();
	if("" === term || "undefind" === term){
		return false;
	}
	var $requestData = {};
	$requestData.code=$('#xq').val();
	$.get("${pageContext.request.contextPath}/teach/teamEvaluation/list/json", $requestData, function(data, status) {
	if("success" === status) {
		data = eval("(" + data + ")");
			begin=data.begin;
			end=data.end;
			if (begin != '') {
				var date;
				var myarray = begin.split("-");
				if (myarray[1].charAt(0) != '0') {
					date = myarray[0]+ "年"+ myarray[1]+ "月";
				} else {
					date = myarray[0]+ "年"+ myarray[1].charAt(1)+ "月";
				}
				$('#d4').val(date);
			}
		}
// 		sure();
	});
		week();
}
function show(){
	var select = $("#select").val();
	if(select == 1){
		document.getElementById("select_div_month").style.display="block";
		document.getElementById("select_div_week").style.display="none";
		$('#select_week').val("");
	}else if(select == 2){
		document.getElementById("select_div_week").style.display="block";
		document.getElementById("select_div_month").style.display="none";
		$('#d4').val("");
		week();
	}
}
function myself(){
	$(".minutes-rated").children().eq(0).removeClass("see-rated");
	$(".minutes-rated").children().eq(1).addClass("see-rated");
	var loader = new loadLayer();
	var year = $("#xn").val();
	var termCode = $("#xq").val();
	var manager = $("#isvip").val();
	var month = $("#d4").val();
	var week = $("#select_week").val();
	var teamId = null;
	var nj = null;
	var checkDate=$("#startDate").val();
	var monthNone = $("#select_div_month").attr("style");
	var weekNone = $("#select_div_week").attr("style");
	var $requestData = {};
	if($("#sel_div").find("#nj").length>0){
		nj = $("#nj").val();
		teamId = $("#bj").val();
		
	}else{
		teamId = $("#teamId").val();
		$("#teamId").chosen();
		if(teamId==""){
			$.error("暂无班级数据信息，请联系管理员");
			return false;
		}
	}
	
	if ("" === year || "undefined" === year) {
		$.error("请选择学年");
		return false;
	}
	if ("" === termCode || "undefined" === termCode) {
		$.error("请选择学期");
		return false;
	}
	if(monthNone == "display: none;"){
		if(("" === week || "undefined" === week) && ("" === month || "undefind" === month)){
			$.error("请选择周次");
			return false
		}
	}
	if(weekNone == "display: none;"){
		if(("" === month || "undefind" === month) && ("" === week || "undefined" === week)){
			$.error("请选择月份");
			return false;
		}
	}
	$requestData.teamId = teamId;
	$requestData.manager = manager;
	$requestData.termCode = termCode;
	$requestData.year = year;
	$requestData.gradeId = nj;
	$requestData.month = month;
	$requestData.week = week;
	loader.show();
	if(teamId != null && "" != teamId){
		$.post("${pageContext.request.contextPath}/teach/classOptimizing/myself", $requestData, function(data, status) {
			if("success" === status) {
				$("#kb_tb").html(data);
			}
			loader.close();
		});
	}
}
</script>

</html>