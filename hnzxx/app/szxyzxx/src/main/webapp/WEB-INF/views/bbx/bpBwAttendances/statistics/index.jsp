<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<title></title>
</head>

<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="select_top">
						<div class="s1 s1_bg" id="schoolManagerSearch">
							<!-- <div hidden>
								<select id="xn"></select>
							</div>
							<select id="nj" name="gradeId" style="width: 160px;"></select> <select
								id="bj" name="teamId" style="width: 160px;"></select> -->
							<input style="width:200px;margin-left: 10px;"
									class="sj_time" id="startDay" name="startDay"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});"
									placeholder="开始日期" value="" type="text">
							<input style="width:200px;margin-left: 10px;"
									class="sj_time" id="endDay" name="endDay"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startDay\',{m:+5})}', maxDate:'%y-%M-%d'});"
									placeholder="结束日期" value="" type="text">
							<button class="btn btn-success" style="margin-top: -8px;" id="sosuo" onclick="search()">搜索</button>
							<div class="content">
								<!-- <div class="click">
									<button class="btn btn-warning" type="button"
										onclick="loadCreatePage();">发布</button>
								</div> -->
							</div>
						</div>
					</div>
					<div class="content-widgets" style="margin-top: 10px;">
						<div>
							<div id="attendance_sta_list_content"></div>
								
							<div class="clear"></div>
						</div>					
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
		/* var currentRoleCode = '${sessionScope[sca:currentUserKey()].currentRoleCode}';
		if(currentRoleCode == "SCHOOL_LEADER"){
			$("#classMasterSearch").html("");
			$("#classMasterSearch").hide();
			$.initCascadeSelector({
				"type" : "team",
				"teamCallback" : function($this) {
				}
			});		
		} */
	});


	function search() {
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
		
		loader.show();
		$("#attendance_sta_list_content").load(
			"${ctp}/bbx/bpBwAttendances/statisticsAttendance", 
			{"startDay":startDay, "endDay":endDay},
			function(){
				loader.close();
			}
		);
	}	
		
		/* var searchDay = $("#searchDay").val();
		if(teamId != ""){
			
			
				function(){
					loader.close();
					$("#lastWeek").css("display", "inline");
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
		}	 */
	
</script>
</html>