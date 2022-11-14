<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link href="${ctp}/res/css/extra/jcml.css" rel="stylesheet">
<title></title>
<style type="text/css">
.catalog .dl a{
	float : right;
}
/* .table .sorting_asc,.table .sorting_desc{
	background-position:center left;
	padding-left:20px;
} */
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="会议记录" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3 class="jc_top">
							会议记录列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							</p>
						</h3>
					</div>
					<div class="widget-head">
						<div class="row-fluid">
						<div class="widget-container">
<!-- 							<ul id="treeZylb" class="ztree"></ul> -->
						</div>
					</div>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>会议ID：</span>
									<input type="text" id="meetId" name="meetId" style="margin-bottom: 0; padding: 6px; width: 180px;" placeholder="会议ID号">
								</div>
								<div class="select_div">
									<span>时间范围：</span>
									<input type="text" id="startTime" name="startTime" class="Wdate span13" placeholder="开始"
                           		 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',maxDate:'#F{$dp.$D(\'endTime\')}'})"  style="margin-bottom: 0; padding: 6px; width: 180px;" />
                           		 —
									<input type="text" id="endTime" name="endTime" class="Wdate span13" placeholder="结束"
                           		 onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'#F{$dp.$D(\'startTime\')}'})"  style="margin-bottom: 0; padding: 6px; width: 180px;" />
								</div>
<!-- 								<div class="select_div"> -->
<!-- 									<span>结束时间：</span> -->
<!-- 								</div> -->
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>会议号</th>
<!-- 											<th>开始时间</th> -->
											<th>主题</th>
											<th>参会者人数</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="uin_list_content">
									<jsp:include page="./record_list.jsp" />
								</tbody>
							</table>
							<div id="page_div">
								<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
									<jsp:param name="id" value="uin_list_content" />
									<jsp:param name="url" value="/uin/meeting/findConfRecord?sub=list&dm=${param.dm}" />
									<jsp:param name="pageSize" value="${page.pageSize}" />
								</jsp:include>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function search() {
		var loader = new loadLayer();
		var val = {};
		var meetId = $("#meetId").val();
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		if (meetId != null && meetId != "") {
			val.meetId = meetId;
		}
		if (startTime != null && startTime != "") {
			val.startTime = startTime;
		}
		if (endTime != null && endTime != "") {
			val.endTime = endTime;
		}
		var id = "uin_list_content";
		var url = "/uin/meeting/findConfRecord?sub=list&dm=${param.dm}";
		loader.show();
		myPagination(id, val, url,function(){
			loader.close();
		});
	}
	$(function(){
		
	});
	function loadEditPage(index){
		$.initWinOnTopFromTop('与会详情', '${ctp}/uin/meeting/detail?index=' + index, '850', '760');
	}
</script>
</html>