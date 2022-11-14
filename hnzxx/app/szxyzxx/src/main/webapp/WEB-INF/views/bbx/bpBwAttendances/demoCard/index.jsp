<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="select_top">
<!-- 						<div class="s1 s1_bg" id="classMasterSearch"> -->
<!-- 							<select id="bj" class="span4 chzn-select" style="width: 120px;"></select> -->
<!-- 							<input style="width:200px;margin-top: -15px;margin-left: 10px;" -->
<!-- 									class="sj_time" id="searchDay" name="searchDay" -->
<!-- 									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});" -->
<!-- 									placeholder="考勤日期" value="" type="text"> -->
<!-- 							<button class="btn btn-success" style="margin-top: -23px;" id="sosuo" onclick="search()">搜索</button>	 -->
							<!-- <div class="content">
								<div class="click">
									<button class="btn btn-warning" type="button"
										onclick="loadCreatePage();">发布</button>
								</div>
							</div> -->
<!-- 						</div> -->
<!-- 						<div class="s1 s1_bg" id="schoolManagerSearch"> -->
<!-- 							<div hidden> -->
<!-- 								<select id="xn"></select> -->
<!-- 							</div> -->
<!-- 							<select id="nj" name="gradeId" style="width: 160px;"></select> <select -->
<!-- 								id="bj" name="teamId" style="width: 160px;"></select> -->
<!-- 							<input style="width:200px;margin-top: -15px;margin-left: 10px;" -->
<!-- 									class="sj_time" id="searchDay" name="searchDay" -->
<!-- 									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});" -->
<!-- 									placeholder="考勤日期" value="" type="text"> -->
<!-- 							<button class="btn btn-success" style="margin-top: -23px;" id="sosuo" onclick="search()">搜索</button> -->
<!-- 							<div class="content">
<!-- 								<div class="click"> -->
<!-- 									<button class="btn btn-warning" type="button" -->
<!-- 										onclick="loadCreatePage();">发布</button> -->
<!-- 								</div> -->
<!-- 							</div> --> 
<!-- 						</div> -->
					</div>
				</div>
				
				<div class="content-widgets white">
					<div class="content-widgets">
						<div class="widget-container">
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th style="padding-left:32px;">学生姓名</th>
											<th style="padding-left:19px;">打卡时间</th>
<!-- 										<th class="caozuo" style="max-width: 250px;">操作</th> -->
									</tr>
								</thead>
								<tbody id="bwAttendanceCardTime_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="bwAttendanceTime_list_content" />
								<jsp:param name="url" value="/hm/bbx/bpBwAttendances/cardIndex?sub=list&dm=${param.dm}" />
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

var time;
	function search() {
		var val = {};
		var teamId = $("#bj").val();
		var searchDay = $("#searchDay").val();
		
		val.teamId = teamId;
		val.attendanceDay = searchDay;
		
		var id = "bwAttendanceCardTime_list_content";
		var url = "/hm/bbx/bpBwAttendances/cardIndex?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	function gose(){window.location.reload()}
	time = setInterval("gose()",30000);
	
</script>
</html>