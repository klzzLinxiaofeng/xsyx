<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>电教管理</title>
<style>
	.row-fluid .span4 {
	width: 135px;
}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal"  id="user_form" action="${ctp}/schoolAffair/aptTask/exportSearchData" onsubmit="return onSubmit();" method="post">
						<div class="khcxdc">
							<div class="t_div">
								<p class="title">时间选择</p>
								<div class="w_div">
									<div class="s_div">
										<input type="radio" checked="checked" name="searchType" value="1"/>
										<label>全部</label>
									</div>
									<div class="s_div">
										<input type="radio" name="searchType" value="2"/>
										<label>周</label>  
										<select id="week" name="week" style="width: 250px;">
											<option value="">请选择</option>
											<c:forEach items="${weeks}" var="map">
												<option value='<fmt:formatDate value="${map.beginDate}" pattern="yyyy-MM-dd"/>,<fmt:formatDate value="${map.endDate}" pattern="yyyy-MM-dd"/>'>第${map.week}周（<fmt:formatDate value="${map.beginDate}" pattern="yyyy-MM-dd"/>~<fmt:formatDate value="${map.endDate}" pattern="yyyy-MM-dd"/>）</option>
											</c:forEach>
										</select>
									</div>
									<div class="s_div">
										<input type="radio" name="searchType" value="3"/>
										<label>时间段</label>
										<input type="text" id="startDate" name="startDate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDateValue\')}',maxDate:'#F{$dp.$D(\'finishDateValue\')}'});" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="开始时间" value="">
										——
										<input type="text" id="finishDate" name="finishDate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')||$dp.$D(\'startDateValue\')}',maxDate:'#F{$dp.$D(\'finishDateValue\')}'});" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="结束时间" value="">
										<input type="hidden" id="startDateValue" name="startDateValue" value="<fmt:formatDate value="${task.startDate}" pattern="yyyy-MM-dd"/>"/>
										<input type="hidden" id="finishDateValue" name="finishDateValue" value="<fmt:formatDate value="${task.finishDate}" pattern="yyyy-MM-dd"/>"/>
									</div>
								</div>
							</div>
							<c:if test="${type=='own'}">
								<input type="hidden" name="teacherId" value="${currentTeacherId}"/>
							</c:if>
							<div class="tea_select">
							<c:if test="${type=='all'}">
								<label>教师</label>
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
							</c:if>
							</div>
							<div class="sel_input">
								<div class="sle_title"><input type="checkbox">全选</div>
								<div class="sel_div">
									<c:forEach items="${voList}" var="vo" varStatus="sta">
										<div class="cxkh">
<%-- 										<c:choose> --%>
<%-- 											<c:when test="${sta.index == 0}"> --%>
<!-- 												<div class="d1"> -->
<%-- 													<input type="checkbox"> <label class="gb">${vo.category}123132</label> --%>
<!-- 												</div> -->
<%-- 											</c:when> --%>
<%-- 											<c:otherwise> --%>
												<div class="d1">
													<input type="checkbox"> <label class="zk">${vo.category}</label>
												</div>
<%-- 											</c:otherwise> --%>
<%-- 										</c:choose> --%>
											<div class="d2">
												<c:forEach items="${vo.taskItems}" var="taskItem" varStatus="itemSta">
													<input class="itemValue" type="checkbox" value="${taskItem.id}"> ${taskItem.name}
												</c:forEach>
											</div>
										</div>
									</c:forEach>
									<c:forEach items="${bonusList}" var="bonus">
										<div class="cxkh">
										<c:choose>
											<c:when test="${bonus.checkType == '02'}">
												<input class="itemValue" type="checkbox" value="${bonus.id}"> 加分
											</c:when>
											<c:when test="${bonus.checkType == '03'}">
												<input class="itemValue" type="checkbox" value="${bonus.id}"> 扣分
											</c:when>
										</c:choose>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<input type="hidden" id="taskItems" name="taskItems" value="">
						<input type="hidden" name="taskId" value="${task.id}">
						<div class="form-actions tan_bottom">
								<button  class="btn btn-warning" onclick="expToExcel();" type="botton">导出</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	$(function(){
		$(".cxkh .d1").on("click","label.zk",function(){
			$(this).parent().next().show();
			$(this).removeClass("zk").addClass("gb")
		});
		$(".cxkh .d1").on("click","label.gb",function(){
			$(this).parent().next().hide();
			$(this).removeClass("gb").addClass("zk")
		});
		$(".sel_input ").on("click",".sle_title input",function(){
			if($(this).is(':checked')){
				$(".sel_input  input").prop("checked", "checked");
			}else{
				$(".sel_input  input").removeAttr("checked");
			}
		});
		$(".sel_input ").on("click",".cxkh .d1 input",function(){
			if($(this).is(':checked')){
				$(this).parent().next().children("input").prop("checked", "checked");
			}else{
				$(this).parent().next().children("input").removeAttr("checked");
				$(".sel_input .sle_title input").removeAttr("checked");
			}
		});
		$(".sel_input ").on("click",".cxkh .d2 input",function(){
			if($(this).is(':checked')==false){
				$(this).parent().prev().children("input").removeAttr("checked");
				$(".sel_input .sle_title input").removeAttr("checked");
			}
		});
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
		$("#teacherId").chosen();
		$(".cxkh .d1 label").eq(0).click();
		
	});
	function onSubmit(){
		var $curSelector = $("input[type='radio']:checked").val();
		if(2 == $curSelector){
			var week = $("#week").val();
			if(week == ""){
				$.error("请选择周后再查询...");
				return false;
			}
			var startDate = week.split(",")[0];
			var finishDate = week.split(",")[1];
			$("#startDate").val(startDate);
			$("#finishDate").val(finishDate);
		}
		if(3 == $curSelector){
			var startDate = $("#startDate").val();
			var finishDate = $("#finishDate").val();
			if(startDate == "" && finishDate == ""){
				$.error("请选择时间段");
				return false;
			}
		}
		var taskItems = $(".itemValue:checked");
		var aptTaskItems = new Array();
		if(taskItems.length == 0){
			$.error("请至少选择一项考核项");
			return false;
		}else{
			$.each(taskItems,function(index,value){
				var taskItem = $(value).val();
				aptTaskItems.push(taskItem);
			});
			if(aptTaskItems != null && aptTaskItems != ""){
				$("#taskItems").val(aptTaskItems);
			}
		}
		return true;
    }
    function expToExcel() { 
    	if (onSubmit()) {
    		var loader = new loadLayer();
	        var form = $("#user_form");  
	        var options  = {    
	            url:'${ctp}/schoolAffair/aptTask/exportSearchData',    
	            type:'post',
	            async : true, 
	            enctype:"multipart/form-data",
	            success:function(data,status) {   
					loader.close();
	            	if("success" === status) {
// 	            		$.closeWindow();						            		
	            	}
	            }
	        };    
    		loader.show();
	        form.ajaxSubmit(options);  
    	}  
    }
</script>
</html>