<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="widget-head">
						<!-- <div class="select_top">
							<div class="s1 s1_bg">
								<select id="team" class="span4 chzn-select" onchange="search()">
								</select>
							</div>
						</div> -->
						<div class="select_top">
							<div class="s1 s1_bg" id="classMasterSearch">
								<select id="bj" class="span4 chzn-select" style="width: 120px;"
									onchange="search()"></select>
							</div>
							<div class="s1 s1_bg" id="schoolManagerSearch">
								<div hidden>
									<select id="xn"></select>
								</div>
								<select id="nj" name="gradeId" style="width: 160px;"></select> <select
									id="bj" name="teamId" style="width: 160px;"></select>
								<button class="btn btn-success" style="margin-top: -23px;"
									id="sosuo" onclick="search()">搜索</button>
							</div>
						</div>
					</div>
					<div id="dutyCount">
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
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

	var date = new Date();
	var i = 1;
	var teamId;
	/* $(function() {
		$.BbxRoleTeamAccountSelector({
			"selector" : "#team",
			"condition" : {
				roleType : "CLASS_MASTER"
			},
			"selectedVal" : "",
			"afterHandler" : function() {
				teamId = $("#team").find("option:selected").val()
				$("#dutyCount").load("${ctp}/clazz/teamDutyUser/getCount", {"teamId":teamId},function(){
					var val = {
							teamId : teamId,
							beginDate : date
						};
						$("#teamDutyUser_list_content").load("${ctp}/clazz/teamDutyUser/getDuty", val);
						$("#lastWeek").click(function() {
							var id = "div" + i;
							var beginDate = getDateStr1(4 * i);
							var currentDiv = "<div id='"+ id +"' begin-date='"+ beginDate +"' style='margin-top:10px;'></div>";
							$(this).parent().after(currentDiv);
							var value = {
								teamId : $("#team").find("option:selected")
										.val(),
								beginDate : beginDate
							};
							$("#div" + i).load(
									"${ctp}/clazz/teamDutyUser/getDuty", value);
							i++;
						});
				});
			}
		});

		
	}); */

	function getDateStr1(AddDayCount) {
		var dd = new Date();
		dd.setDate(dd.getDate() - AddDayCount); //获取AddDayCount天前的日期
		var y = dd.getFullYear();
		var m = dd.getMonth() + 1;//获取当前月份的日期
		var d = dd.getDate();
		return y + "-" + m + "-" + d;
	}

	function search() {
		var teamId = $("#bj").val();
		if(teamId == ""){
			$.error("请选择班级");
			return;
		}
		$("#dutyCount").load("${ctp}/clazz/teamDutyUser/getCount", {"teamId":teamId},function(){
			var otherDate = new Date();
			var val = {
				teamId : teamId,
				beginDate : otherDate
			};
			$("#teamDutyUser_list_content").load(
					"${ctp}/clazz/teamDutyUser/getDuty", val);
			$("#lastWeek").click(function() {
				var id = "div" + i;
				var beginDate = getDateStr1(4 * i);
				var currentDiv = "<div id='"+ id +"' begin-date='"+ beginDate +"' style='height:700px;'></div>";
				$(this).after(currentDiv);
				var value = {
					teamId : $("#bj").find("option:selected")
							.val(),
					beginDate : beginDate
				};
				$("#div" + i).load(
						"${ctp}/clazz/teamDutyUser/getDuty", value);
				i++;
			});
		});
		
	}

	// 	加载创建对话框
	function loadCreatePage(obj) {
		var teamId = $("#bj").find("option:selected").val();
		var dutyDate = $(obj).attr("date_time");
		if(teamId == undefined || teamId=='' || teamId=='-1'){
			$.error("请选择一个班级!");
			return;
		}
		$.initWinOnTopFromLeft_bbx('添加值日生',
				'${ctp}/clazz/teamDutyUser/creator?teamId=' + teamId
						+ "&dutyDate=" + dutyDate, '860', '400');
	}
	//  加载编辑对话框
	function loadEditPage(obj) {
		var teamId = $("#bj").find("option:selected").val();
		var dutyDate = $(obj).attr("date_time");
		$.initWinOnTopFromLeft_bbx('编辑值日生', '${ctp}/clazz/teamDutyUser/editor?teamId='
				+ teamId + "&dutyDate=" + dutyDate, '860', '400');
	}
</script>
</html>