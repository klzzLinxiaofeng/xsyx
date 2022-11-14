<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<title></title>
</head>
<%-- <body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="widget-head">
						<div class="select_top">
								<div class="s1 s1_bg">
									<select  id="teamId" class="span4 chzn-select" style="width: 120px;" onchange="search()"></select>	
									<div class="click">
										<button class="btn btn-warning" type="button" onclick="loadCreatePage();">发布</button>
									</div>
								</div>
							</div>
					</div>
					<div>
							<div id="bwAttendances_list_content">
								<jsp:include page="./list.jsp" />
							</div>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="bwAttendances_list_content" />
							<jsp:param name="url" value="/bbx/bpBwAttendances/index?sub=list&dm=${param.dm}" />
							<jsp:param name="pageSize" value="${page.pageSize}" />
						</jsp:include>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body> --%>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="select_top">
						<div class="s1 s1_bg" id="classMasterSearch">
							<select id="bj" class="span4 chzn-select" style="width: 120px;"></select>
							<input style="width:200px;margin-top: -15px;margin-left: 10px;"
									class="sj_time" id="searchDay" name="searchDay"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});"
									placeholder="考勤日期" value="" type="text">
							<button class="btn btn-success" style="margin-top: -23px;" id="sosuo" onclick="search()">搜索</button>	
							<div class="content">
								<div class="click">
									<button class="btn btn-warning" type="button"
										onclick="loadCreatePage();">发布</button>
								</div>
							</div>
						</div>
						<div class="s1 s1_bg" id="schoolManagerSearch">
							<div hidden>
								<select id="xn"></select>
							</div>
							<select id="nj" name="gradeId" style="width: 160px;"></select> <select
								id="bj" name="teamId" style="width: 160px;"></select>
							<input style="width:200px;margin-top: -15px;margin-left: 10px;"
									class="sj_time" id="searchDay" name="searchDay"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});"
									placeholder="考勤日期" value="" type="text">
							<button class="btn btn-success" style="margin-top: -23px;" id="sosuo" onclick="search()">搜索</button>
							<div class="content">
								<div class="click">
									<button class="btn btn-warning" type="button"
										onclick="loadCreatePage();">发布</button>
								</div>
							</div>
						</div>
					</div>
					<div class="content-widgets" style="margin-top: 10px;">
						<div>
							<div id="attendance_list_content"></div>
								
							<div class="clear"></div>
						</div>
						<button id="lastWeek" class="btn btn-warning" style="display:none;">下一页</button>
						<input  class="sj_time" id="lastDay" name="lastDay" style="display:none;"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});"
									placeholder="考勤日期" value="" type="text">
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var loader = new loadLayer();
var i = 1;

$(function(){		
	// alert('${sessionScope[sca:currentUserKey()].currentRoleCode}');		
	var currentRoleCode = '${sessionScope[sca:currentUserKey()].currentRoleCode}';
	if(currentRoleCode == "SCHOOL_LEADER"){
		$("#classMasterSearch").html("");
		$("#classMasterSearch").hide();
		$.initCascadeSelector({
			"type" : "team",
			"teamCallback" : function($this) {
			}
		});		
	}else{
		$("#schoolManagerSearch").html("");		
		$("#schoolManagerSearch").hide();		
		var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
		$.BbxRoleTeamAccountSelector({
			   "selector" : "#bj",
			   "condition" : $requestData,
			   "selectedVal" : "",
			   "afterHandler" : function() {
				   search();
				}	
		   });
	}	
});


	function search() {
		var teamId = $("#bj").val();
		if(teamId == "" || teamId == null){
			$.error("请选择班级");
			return;
		}
		var searchDay = $("#searchDay").val();
		if(teamId != ""){
			loader.show();
			$("#attendance_list_content").load(
				"${ctp}/bbx/bpBwAttendances/getAttendanceList", 
				{"teamId":teamId, "searchDay":searchDay,"type":1},
				function(){
					loader.close();
					$("#lastWeek").css("display", "inline");
					$("#lastWeek").unbind("click");
					$("#lastWeek").click(function() {
						loader.show();
						var id = "div" + i;
						var lastDay = $("#lastDay").val();
						var attendanceDay = getDateStr1(lastDay);
						var currentDiv = "<div id='"+ id + "' style='margin-top:10px;'></div>";
						//$(this).parent().append(currentDiv);
						$("#attendance_list_content").append(currentDiv);
						var value = {
							teamId :teamId,
							attendanceDay : attendanceDay,
							type:2
						};
						$("#div" + i).load("${ctp}/bbx/bpBwAttendances/getAttendanceList", value, function(){
							loader.close();
						});
						i++;
						
					});
				}
			);
		}
		
		
		
		/* var val = {};
		var name = $("#name").val();
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "bwAttendances_list_content";
		var url = "/bbx/bpBwAttendances/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url); */
	}
	
	
	function getDateStr1(lastDay) {
		//alert(lastDay);
		var dd = new Date(lastDay.replace(/-/g, "/"));
		//var dd = new Date();
		dd.setDate(dd.getDate() - 1); 
		var y = dd.getFullYear();
		var m = dd.getMonth() + 1;//获取当前月份的日期
		var d = dd.getDate();
		//alert(y + "-" + m + "-" + d);
		return y + "-" + m + "-" + d;
	}
	
	
	
	
	
	
	
	
	
	
	

	// 	加载创建对话框
	function loadCreatePage() {
		var teamId = $("#bj").val();
		if(teamId == "" || teamId == null){
			$.error("请选择班级");
			return;
		}
		$.initWinOnTopFromLeft_bbx('创建', '${ctp}/bbx/bpBwAttendances/creator?teamId='+teamId, '860', '600');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/bpBwAttendances/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/bpBwAttendances/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/bpBwAttendances/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>