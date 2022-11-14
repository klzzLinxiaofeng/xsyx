<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
.fenban_left .bottom .bj_rs ul li a{min-height:20px;height:auto;}
.coloe-red{font-size:18px;font-weight:bold;font-family:"微软雅黑";color:red}
.score{font-family:"微软雅黑";color:red}
.fenban_left .bottom .bj_rs ul li a{width:405px;}
.fenban_right .xs_div .xs_list .xuesheng{height:auto;}
</style>
</head>
<body>
	<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-calculator" name="icon"/>
			<jsp:param value="考评人查询" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12"  style="height: 43px;">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							<span>考评人查询--考核成绩列表</span>
							<p style="float:right;" class="btn_link">
								<a href="javascript:void(0)" class="a2" onclick="history.back();"><i class="fa  fa-undo"></i>返回</a>
								<a href="javascript:void(0)" class="a1" onclick="loadExportPage();"><i class="fa  fa-arrow-down"></i>导出信息</a>
								<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							</p>
						</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid white">
			<div class="select_b">
				<div class="select_div">
					<span><input type="radio" checked="checked" name="searchType" value="1"/> &nbsp;&nbsp;全部</span>
<!-- 					<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value=""> -->
				</div>
				<div class="select_div">
					<span><input type="radio" name="searchType" value="2"/>&nbsp;&nbsp;周：</span>
<!-- 					<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value=""> -->
					<select id="week" name="week" style="width: 250px;">
						<option value="">请选择</option>
						<c:forEach items="${weeks}" var="map">
							<option value='<fmt:formatDate value="${map.beginDate}" pattern="yyyy-MM-dd"/>,<fmt:formatDate value="${map.endDate}" pattern="yyyy-MM-dd"/>'>第${map.week}周（<fmt:formatDate value="${map.beginDate}" pattern="yyyy-MM-dd"/>~<fmt:formatDate value="${map.endDate}" pattern="yyyy-MM-dd"/>）</option>
						</c:forEach>
					</select>
				</div>
				<div class="select_div">
					<span><input type="radio" name="searchType" value="3"/>&nbsp;&nbsp;时间段：</span>
					<input type="text" id="startDate" name="startDate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDateValue\')}',maxDate:'#F{$dp.$D(\'finishDateValue\')}'});" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="开始时间" value="">
					&nbsp;&nbsp;-&nbsp;&nbsp;
					<input type="text" id="finishDate" name="finishDate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')||$dp.$D(\'startDateValue\')}',maxDate:'#F{$dp.$D(\'finishDateValue\')}'});" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="结束时间" value="">
					<input type="hidden" id="startDateValue" name="startDateValue" value="<fmt:formatDate value="${task.startDate}" pattern="yyyy-MM-dd"/>"/>
					<input type="hidden" id="finishDateValue" name="finishDateValue" value="<fmt:formatDate value="${task.finishDate}" pattern="yyyy-MM-dd"/>"/>
				</div>
				<button type="button" class="btn btn-primary" onclick="search()">查询</button>
				
				<div class="select_div" style="float:right;margin-right:15px;">
					<span>教师：</span>
<!-- 					<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value=""> -->
						<select id="teacherId" name="teacherId" style="width: 150px;">
							<option value="">请选择</option>
							<c:choose>
								<c:when test="${task.scopeType == 2}">
									<c:forEach items="${teachers}" var="teacher">
										<option value="${teacher.teacherId}">${teacher.teacherName}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach items="${teachers}" var="teacher">
										<option value="${teacher.id}">${teacher.name}</option>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</select>
				</div>
				<div class="clear"></div>
			</div>
			<div class="fenban_left" style="width: 450px;">
				<div class="top">
					<span>${rule.name}</span>
				</div>
				<div class="bottom">
					<c:forEach items="${voList}" var="vo" varStatus="sta">
						<c:choose>
							<c:when test="${sta.index == 0}">
								<div class="open"><a href="javascript:void(0)" class="school">${vo.category}<span class="score">（${vo.totalScore}分）</span></a></div>
								<div class="bj_rs">
							</c:when>
							<c:otherwise>
								<div class="close_1"><a href="javascript:void(0)" class="school">${vo.category}<span class="score">（${vo.totalScore}分）</span></a></div>
								<div class="bj_rs" style="display: none;">
							</c:otherwise>
						</c:choose>
							<ul class="ban_ul">
								<c:forEach items="${vo.taskItems}" var="taskItem" varStatus="itemSta">
									<li>
										<c:choose>
										<c:when test="${itemSta.index == 0 && sta.index == 0}">
											<a class="on" href="javascript:void(0)" data-id="${taskItem.id}" data-name="${taskItem.name}">
										</c:when>
										<c:otherwise>
											<a href="javascript:void(0)" data-id="${taskItem.id}" data-name="${taskItem.name}"> 
										</c:otherwise>
										</c:choose>
										${taskItem.name}<span class="score">（${taskItem.itemTotalNum}分）</span></a>
									</li>
								</c:forEach>
							</ul>
						</div>
					</c:forEach>
					<div class="bottom">
					<c:forEach items="${bonusList}" var="bonus">
						<c:choose>
							<c:when test="${bonus.checkType == '02'}">
								<div class="close_1"><a href="javascript:void(0)" data-id="${bonus.id}" data-name="加分" class="bonus">
								加分
								<span class="score">（${bonusScore}分）</span></a></div>
							</c:when>
							<c:when test="${bonus.checkType == '03'}">
								<div class="close_1"><a href="javascript:void(0)" data-id="${bonus.id}" data-name="扣分" class="bonus">
								扣分
								<span class="score">（${deductScore}分） </span></a></div>
							</c:when>
						</c:choose>
					</c:forEach>
				</div>
				</div>
			</div>
			<div class="fenban_right" style="border-top:1px solid #ddd;border-left: 1px solid #D3D1D1;margin-left: 451px;">
				<div class="xs_div" style="width:100%">
					<div class="top">
						<p>当前项目：<span id="currentItem" class="coloe-red"></span></p>
						<p>当前记录：<span id="totalRow" class="coloe-red">${totalRow}</span>条    &nbsp; 
						总分：<span id="totalNum" class="coloe-red">${totalNum}</span>分</p>
					</div>
					<div class="xs_list">
						<div class="xuesheng" style="margin-bottom:15px;">
						<table class="table table-bordered responsive white " style="width:100%">
							<thead>
								<tr><th>序号</th><th>被考核人</th><th>负责人</th><th>分值</th><th>日期</th></tr>
							</thead>
							<tbody id="search_deltail_list">
								<jsp:include page="./judge_search_detail_list.jsp"/>
							</tbody>
						</table>
							<div class="clear"></div>
						</div>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="search_deltail_list" />
								<jsp:param name="url" value="/schoolAffair/aptTask/judgeSearchDetail?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
					</div>
				</div>
				<div class="clear"></div>
				<%
						session.setAttribute("weeks", request.getAttribute("weeks"));
						session.setAttribute("task", request.getAttribute("task"));
						session.setAttribute("teachers", request.getAttribute("teachers"));
						session.setAttribute("voList", request.getAttribute("voList"));
						session.setAttribute("bonusList", request.getAttribute("bonusList"));
					%>
			</div> 
		</div>
	</div>
	<script>
	function search(taskItemId){
		var val = {};
		if(taskItemId == null || taskItemId + "" == "undefined" || taskItemId == ""){
			var taskItemId = $("a.on").attr("data-id");
		}
		if(taskItemId != null && taskItemId != ""){
			val.aptTaskItemId = taskItemId;
		}
		val.id = "${task.id}";
		var teacherId = $("#teacherId").val();
		if(teacherId != null && teacherId != ""){
			val.teacherId = teacherId;
		}
		var $curSelector = $("input[type='radio']:checked").val();
		if(2 == $curSelector){
			var week = $("#week").val() + "";
			if(week == ""){
				$.error("请选择周后再查询...");
				return;
			}
			var startDate = week.split(",")[0];
			var finishDate = week.split(",")[1];
			val.startDate = startDate;
			val.finishDate = finishDate;
		}
		if(3 == $curSelector){
			var startDate = $("#startDate").val();
			var finishDate = $("#finishDate").val();
			val.startDate = startDate;
			val.finishDate = finishDate;
		}
		var id = "search_deltail_list";
		var url = "/schoolAffair/aptTask/judgeSearchDetail?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	function loadExportPage(){
		var taskId = "${task.id}";
		$.initWinOnTopFromLeft('导出', '${ctp}/schoolAffair/aptTask/loadExport?type=judge&taskId=' + taskId, '850', '700');
	}
	$(function(){
		$(".fenban_left .bottom .bj_rs .ban_ul li a").click(function(){
			$(".fenban_left .bottom .bj_rs .ban_ul li a").removeClass("on");
			$(this).addClass("on");
			var currentItem = $(this).attr("data-name");
			$("#currentItem").html(currentItem);
			search();
		});
		$(".fenban_left .bottom").on("click",".open .school",function(event){
			event.stopPropagation();
			$(this).parent().next().hide();
			$(this).parent().removeClass("open").addClass("close_1");
		});
		$(".fenban_left .bottom").on("click",".close_1 .school",function(event){
			event.stopPropagation();
			$(this).parent().next().show();
			$(this).parent().removeClass("close_1").addClass("open");
		});
		var wid=$(".fenban_right").css("width");
		var w=(parseInt(wid)-140)*0.5;
		/* $(".fenban_right .xs_div").css("width",w); */
		
		//初始化周数
// 		var weeks = "${weeks}";
// 		for(var i = 1; i <= weeks ; i++){
// 			$("#week").append('<option value="' + i + '">第' + i + '周</option>');
// 		}
		$("#week").chosen();
		$("#teacherId").chosen();
		$(".fenban_left .bottom .bj_rs .ban_ul li a.on").click();
		$("#startDate").click(function(){
			$("input:radio[value='3']").click();
			$("input:radio[value='3']").attr("checked",true);
		});
		$("#finishDate").click(function(){
			$("input:radio[value='3']").click();
			$("input:radio[value='3']").attr("checked",true);
		});
		$("#week").change(function(){
			$("input:radio[value='2']").click();
			$("input:radio[value='2']").attr("checked",true);
		});
		$("#teacherId").change(function(){
			search();
		});
		$(".fenban_left .bottom .bonus").click(function(){
			$(".on").removeClass("on");
			$(this).addClass("on");
			var currentItem = $(this).attr("data-name");
			var itemId = $(this).attr("data-id");
			$("#currentItem").html(currentItem);
			search(itemId);
		});
	});
	</script>
</body>
</html>
