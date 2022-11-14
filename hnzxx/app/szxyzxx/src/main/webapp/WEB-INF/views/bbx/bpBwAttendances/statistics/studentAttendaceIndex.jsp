<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
</head>


<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="content-widgets">
							<div class="select_top">
								<div class="s1 s1_bg" id="classMasterSearch">
									<select id="bj" class="span4 chzn-select" style="width: 120px;"
										onchange="search()"></select>
									<input style="width:200px;margin-left: 10px;margin-top: -12px;"
											class="sj_time" id="startDay" name="startDay"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});"
											placeholder="开始日期" value="" type="text">
									<input style="width:200px;margin-left: 10px;margin-top: -12px;"
											class="sj_time" id="endDay" name="endDay"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDay\',{m:+5})}', maxDate:'%y-%M-%d'});"
											placeholder="结束日期" value="" type="text">
									<button class="btn btn-success" style="margin-top: -23px;" id="sosuo"
										onclick="search()">搜索</button>
								</div>
								<div class="s1 s1_bg" id="schoolManagerSearch">
									<div hidden>
										<select id="xn"></select>
									</div>
									<select id="nj" name="gradeId" style="width: 160px;"></select> <select
										id="bj" name="teamId" style="width: 160px;"></select>
									<input style="width:200px;margin-left: 10px;margin-top: -12px;"
											class="sj_time" id="startDay" name="startDay"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});"
											placeholder="开始日期" value="" type="text">
									<input style="width:200px;margin-left: 10px;margin-top: -12px;"
											class="sj_time" id="endDay" name="endDay"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDay\',{m:+5})}', maxDate:'%y-%M-%d'});"
											placeholder="结束日期" value="" type="text">
									<button class="btn btn-success" style="margin-top: -23px;" id="sosuo"
										onclick="search()">搜索</button>
								</div>
							</div>
				</div>
			</div>
			<div class="content-widgets white">
					<div class="content-widgets">
						<div class="widget-container">
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th>姓名</th>
										<th>迟到次数</th>
										<th>早退次数</th>
										<th>缺勤次数</th>
										<th>请假次数</th>
										<th>总数</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="attendance_sta_list_content">
									<jsp:include page="./studentAttendaceList.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="attendance_sta_list_content" />
								<jsp:param name="url" value="/bbx/bpBwAttendances/statisticsStudentAttendance?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
						</div>
					</div>
				</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	var loader = new loadLayer();
	
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
					   //search();
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
		var startDay = $("#startDay").val();
		if(startDay == "" || startDay == null){
			$.error("请选择开始日期");
			return;
		}
		var endDay = $("#endDay").val();
		if(endDay == "" || endDay == null){
			$.error("请选择结束日期");
			return;
		}
		var val = {};
		val.teamId = teamId;
		val.startDay = startDay;
		val.endDay = endDay;
		var id = "attendance_sta_list_content";
		var url = "/bbx/bpBwAttendances/staStudentAttendance?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}	
		
	
//  加载编辑对话框
 	function loadViewPage(userId, type) {
 		var teamId = $("#bj").val();
		if(teamId == "" || teamId == null){
			$.error("请选择班级");
			return;
		}
		var startDay = $("#startDay").val();
		if(startDay == "" || startDay == null){
			$.error("请选择开始日期");
			return;
		}
		var endDay = $("#endDay").val();
		if(endDay == "" || endDay == null){
			$.error("请选择结束日期");
			return;
		}
		//alert(userId + "aa" + type);
		var title = "";
		if(1 == type){
			title = "迟到情况";
		}else if(2 == type){
			title = "早退情况";
		}else if(3 == type){
			title = "缺勤情况";
		}
		$.initWinOnTopFromLeft_bbx( title, 
			'${ctp}/bbx/bpBwAttendances/staStudentAttendanceDetail?userId=' + userId + "&startDay=" +startDay + "&endDay=" + endDay + "&type=" + type, 
			'700', '500');
	}
	
</script>
</html>