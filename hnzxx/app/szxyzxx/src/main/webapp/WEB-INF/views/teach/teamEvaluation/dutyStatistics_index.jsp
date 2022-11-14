<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<title>值日统计</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="值日统计" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							班集体评价>>值日管理>>值日统计
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)"  class="a3" onclick="history.go(-1)"><i class="fa  fa-reply"></i>返回</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div"><span>学年：</span> <select id="xn" style="width:120px;" ></select></div>
								<div class="select_div"><span>学期：</span> <select id="xq" style="width:150px;" onchange="getTime();"></select></div>
								<div class="select_div"><span>年级：</span> <select id="nj" style="width:120px;" ></select></div>
								<div class="select_div" style="display: none"><span>班级：</span> <select id="bj" style="width:120px;"></select></div>
								
								<div class="select_div">
									<span>选择：</span>
									<select id="select_time" style="width:120px;" onchange="show();">
										<option value="1">按周次查询</option>
										<option value="2">按月份查询</option>
										<option value="3">按学期查询</option>
									</select>
								</div>
								<div id="select_div_month" class="select_div" style="display: none;">
									<span>月份：</span>
									<input type="text" class="Wdate" id="select_month" 
									onFocus="WdatePicker({dateFmt:'yyyy年M月',minDate:begin,maxDate:end,onpicked:sad});" style="width:172px;"/>
								</div>
								<div id="select_div_week" class="select_div" style="display: none;">
									<span>周次：</span>
									<select id="select_week" style="width:260px;"></select>
								</div>
								
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th style="width: 50px;">序号</th>
										<th>姓名</th>
										<th>用户名</th>
										<th>值日年级</th>
										<th>性别</th>
										<th style="width: 200px;text-align: center;">安排值日数</th>
										<th style="width: 150px;text-align: center;padding-left: 45px;">值日总数
											<span id="up" onclick="search('asc')" style="position: relative;top: -5px;cursor:pointer;color:#999;">▲</span>
											<span id="down" onclick="search('desc')" style="margin-left: 0;position: relative;top: 5px;left: -15px;cursor:pointer;color:red;">▼</span>
										</th>
										<th style="width: 125px;text-align: center;">操作</th>
									</tr>
								</thead>
								<tbody id="module_list_content">
									<jsp:include page="./dutyStatistics_list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="module_list_content" />
								<jsp:param name="url" value="/teach/teamEvaluation/duty/statistics/list" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var begin;
	var end;
	$(function(){
		
		$.initCascadeSelector({
			"type" : "team",			
			//"selectOne":true, 
			"gradeFirstOptTitle":"全部 ",
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
					}
					);
				} else {
					$("#xq").val("");
					$("#xq_chzn").remove();
					$("#xq").show().removeClass("chzn-done").chosen();
				}
			}
		});
		
		$("#select_time").chosen();
		show();
		search();
		
	});

	//日期切换
	function show(){
		var select = $("#select_time").val();
		getTime();
		if(select == 1){
			document.getElementById("select_div_week").style.display="block";
			document.getElementById("select_div_month").style.display="none";
			$("#select_month").val("");
		}else if(select == 2){
			document.getElementById("select_div_month").style.display="block";
			document.getElementById("select_div_week").style.display="none";
			$("#select_week").val("");
		}else if(select == 3){
			document.getElementById("select_div_week").style.display="none";
			document.getElementById("select_div_month").style.display="none";
			$("#select_month").val("");
			$("#select_week").val("");
		}
		//search();
	}
	
	//周次 月份
	function getTime(){
		var term = $('#xq').val();
		if("" === term || "undefind" === term || term ==null){
			return false;
		}
		var $requestData = {};
		$requestData.code=$('#xq').val();
		$.get("${pageContext.request.contextPath}/teach/teamEvaluation/list/json", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				begin = data.begin;
				end = data.end;
				var today = new Date().Format("yyyy-MM-dd");
				
				if (begin != "") {
					var date;
					var myarray = begin.split("-");
					if (myarray[1].charAt(0) != '0') {
						date = myarray[0]+ "年"+ myarray[1]+ "月";
					} else {
						date = myarray[0]+ "年"+ myarray[1].charAt(1)+ "月";
					}
					$("#select_month").val(date);
				}
				
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
	
	function sad(){
		$("#select_month").blur();
	}

	function search(sortord) {
		var loader = new loadLayer();
		var year = $("#xn").val();
		var termCode = $("#xq").val();
		var gradeId = $("#nj").val();
		var month = $("#select_month").val();
		var week = $("#select_week").val();
		
		if ("" === year || "undefined" === year) {
			$.error("请选择学年");
			return false;
		}
		if ("" === termCode || "undefined" === termCode) {
			$.error("请选择学期");
			return false;
		}
		if($("#select_div_week").is(":visible") && $("#select_div_month").is(":hidden")){
			if ("" === week || "undefined" === week) {
				$.error("请选择周次");
				return false;
			}
		}
		if($("#select_div_month").is(":visible") && $("#select_div_week").is(":hidden")){
			if ("" === month || "undefined" === month) {
				$.error("请选择月份");
				return false;
			}
		}
//		alert(year+"  "+termCode+"  "+gradeId+"  "+month+"  "+week);
		
		if("" != sortord && "undefined" != sortord){
			if(sortord == "desc"){
				$("#down").css("color", "red");
				$("#up").css("color", "#999");
			}
			if(sortord == "asc"){
				$("#up").css("color", "red");
				$("#down").css("color", "#999");
			}
		}
		
		var val = {
				"year" : year,	
				"termCode" : termCode,	
				"gradeId" : gradeId,	
				"month" : month,	
				"week" : week,	
				"sortord" : sortord
		};
		var id = "module_list_content";
		var url = "/teach/teamEvaluation/duty/statistics/list?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	
	function DetailPage(teacherId, gradeId, week, month){
		var year = $("#xn").val();
		var termCode = $("#xq").val();
		if(week != "" && "undefined" != week){
			var n =  week.substring(week.indexOf("第")+1, week.indexOf("周"));
			week = week.substring(week.indexOf("(")+1, week.indexOf(")"));
// 			var wstr = week.split("~");
// 			beginDate = wstr[0];
// 			endDate = wstr[1];
			week = n + "(" + week + ")";
		}else if(month != "" && "undefined" != month){
			var y = month.substring(0, month.indexOf("年"));
			var m = month.substring(month.indexOf("年")+1, month.indexOf("月"));
// 			var day = new Date(y,m,0);
// 			beginDate = y + "-" + m + "-01";
// 			endDate = y + "-" + m + "-" +day.getDate();
			month = y + "-" + m ;
		}else {
			beginDate = begin;
			endDate = end;
		}
		//alert(year +"  "+ termCode +"  "+ teacherId +"  "+ gradeId +"  "+ week  +"  "+ month);
		$.initWinOnTopFromLeft('值日明细', '${pageContext.request.contextPath}/teach/teamEvaluation/duty/statistics/view?teacherId='
				+ teacherId + '&gradeId=' + gradeId + '&year=' + year + '&termCode=' + termCode + '&week=' + week
				+ '&month=' + month, '800', '500');
	}
	
</script>
</html>