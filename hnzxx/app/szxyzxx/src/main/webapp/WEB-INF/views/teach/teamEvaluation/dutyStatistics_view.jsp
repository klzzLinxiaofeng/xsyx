<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.chzn-drop .chzn-search input{
	padding-right:235px;
}
.widget-container thead th,.widget-container tbody td{
    text-align: center;
}
.select_b{
	margin-bottom: 15px;
}
</style>
</head>
<body style="background: #fff;">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
			
				<div class="content-widgets">
				<div class="widget-container">
				<div class="check-rated">
				
				<div class="widget-container" style="padding:0;">
				
				
					<div class="select_b">
						<div class="select_div" style="display: none"><span>年级：</span> <select id="nj" style="width:120px;"></select></div>
						<div class="select_div" style="display: none"><span>班级：</span> <select id="bj" style="width:120px;"></select></div>
						
						
						<div class="select_div" style="width: 350px;"><span>学年：</span> <select id="xn" style="width:250px;" ></select></div>
						<div class="select_div" style="width: 380px;">
							<span>选择：</span>
							<select id="select_time" style="width:250px;" onchange="show();">
								<option value="1">按周次查询</option>
								<option value="2">按月份查询</option>
								<option value="3">按学期查询</option>
							</select>
							<button type="button" class="btn btn-primary" onclick="search()" style="float: right;margin: 0;">查询</button>
						</div>
						<div class="select_div" style="width: 350px;"><span>学期：</span> <select id="xq" style="width:250px;" onchange="getTime();"></select></div>
						
						<div id="select_div_month" class="select_div" style="display: none;width: 325px;">
							<span>月份：</span>
							<input type="text" class="Wdate" id="select_month" 
							onFocus="WdatePicker({dateFmt:'yyyy年M月',minDate:begin,maxDate:end,onpicked:sad});" style="width:250px;height: 32px;margin: 0;border: 1px #aaa solid;"/>
						</div>
						<div id="select_div_week" class="select_div" style="display: none;width: 325px;">
							<span>周次：</span>
							<select id="select_week" style="width:250px;" ></select>
						</div>
						
						
						<div class="clear"></div>
					</div>
					
					<table class="responsive table table-striped" id="data-table" style="">
						<thead>
							<tr role="row">
								<th style="width:75px;">序号</th>
								<th>姓名</th>
								<th>安排值日日期</th>
								<th style="width: 333px;">值日时间</th>
							</tr>
						</thead>
						<tbody id="module_list_content">
							<jsp:include page="./dutyStatistics_viewlist.jsp" />
						</tbody>
					</table>
					
				</div>
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
	var flag = 1;
	$(function(){
		
		if(flag == 1){
			flag = 2;
			var modifyYear = "${year}";
			var modifyTermCode = "${termCode}";
			var modifyWeek = "${week}";
			var modifyMonth = "${month}";
			
			$.initCascadeSelector({
	 			"type" : "team",			
	 			"selectOne":true,
	 			"isEchoSelected" : true,
	 			"yearSelectedVal" : modifyYear,
	 			"yearChangeCallback" : function(year) {
	 				if(year != "") {
	 					$.SchoolTermSelector({
	 						"selector" : "#xq",
	 						"condition" : {"schoolYear" : year},
	 						"selectedVal": modifyTermCode,
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
			
		}else{
			
			$.initCascadeSelector({
				"type" : "team",			
				"selectOne":true, 
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
			
		}
		
		
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
			
			var modifyYear = "${year}";
			var modifyTermCode = "${termCode}";
			var modifyWeek = "${week}";
			var modifyMonth = "${month}";
			var type = "${type}";
			if(flag == 2 || flag == 3){
				flag++;
				//console.log(modifyYear +"  "+ modifyTermCode +"  "+ modifyWeek +"  "+ modifyMonth +"  "+ type);
				$("#select_time").val(type);
				
				if ("" != modifyWeek && "undefined" != modifyWeek) {
					$("#select_week").parent().children().remove('div'); 
					$("#select_week").removeClass();
					$("#select_week").find("option[text='"+modifyWeek+"']").attr("selected",true);
					$("#select_week option").each(function(){
						if($(this).text() == modifyWeek){
							$(this).attr("selected", "selected");
					    	$("#select_week").addClass("chzn-select"); 
							$("#select_week").chosen(); 
						}
					});
				}
				if ("" != modifyMonth && "undefined" != modifyMonth) {
 					document.getElementById("select_month").value=modifyMonth;
				}
			}
			
		});
	}
	
	function sad(){
		$("#select_month").blur();
	}
	
	function search() {
		var loader = new loadLayer();
		var year = $("#xn").val();
		var termCode = $("#xq").val();
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
		
		var gradeId = "${gradeId}";
		var teacherId = "${teacherId}";
		
// 		alert(year +"  "+ termCode +"  "+ month +"  "+ week +"  "+ gradeId +"  "+ teacherId);
		
		var val = {
				"year" : year,	
				"termCode" : termCode,	
				"gradeId" : gradeId,	
				"month" : month,	
				"week" : week,	
				"teacherId" : teacherId
		};
		var id = "module_list_content";
		var url = "/teach/teamEvaluation/duty/statistics/viewlist";
		myPagination(id, val, url);
	}
	
</script>
</html>