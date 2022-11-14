<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>

<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/dygl.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/extra/sx_statistics.css">
<script type="text/javascript">
$(function(){
	/*打钩样式*/
    $(".all-class-students ul li").click(function(){
    	$(".all-class-students ul li").removeClass("on")
		$(this).toggleClass("on");
		var studentName = $(this).find("p").text();
		var studentId = $(this).attr("id");
		$("#name").html(studentName);
		searchmyself(studentName,studentId);
	});
});
</script>
<style>
.all-class-students{
	width:30%;
}
</style>
				
<div style="width: 88%;margin: 0 auto;">
<span style="text-align: center;width: 100%;float: left;line-height: 50px;font-size: 18px;font-weight: bold;margin-bottom: -50px;margin-top:10px;">
<span id="name"></span> 激励评价状况数据分析</span>
		<div class="all-class-students" style="margin-top: 88px;">
		<h3 style="line-height: 40px;font-size: 18px;font-weight: bold;padding: 0;">全部班级学生</h3>
<!-- 			<p>全部班级学生</p> -->
                <ul>
                    <c:forEach items="${studentList }" var="student">
                    	<li class="studentId" id="${student.studentId }"><!-- 通过这个值查询 -->
                    		
							<img src="<avatar:avatar userId='${student.userId }'></avatar:avatar>"/>
							<p>${student.name}</p>
<%-- 							<input type="hidden" name="studentId" value="${student.id }"> --%>
							<a href="javascript:void(0);" class="students-inside">
								<img src="${pageContext.request.contextPath}/res/css/dygl/images/evaluate_selected.png">
							</a>
                    	</li>
                    </c:forEach>
                    
                </ul>
        </div>
        <div class="data" style="float:left;width:69%;margin-top:50px;">
        	<!-- 列表内容 -->
        </div>
        </div>
        <div class="clear"></div>
<script type="text/javascript">
$(function(){
	search();
});
function search(){
	var loader = new loadLayer();
	var studentId = $(".studentId").val();
	var year = $("#xn").val();
	var termCode = $("#xq").val();
	var manager = $("#isvip").val();
	var month = $("#d4").val();
	var week = $("#select_week").val();
	var teamId = null;
	var nj = null;
	var checkDate=$("#startDate").val();
	var monthNone = $("#select_div_month").attr("style");
	var weekNone = $("#select_div_week").attr("style");
	var $requestData = {};
	if($("#sel_div").find("#nj").length>0){
		nj = $("#nj").val();
		teamId = $("#bj").val();
	}else{
		teamId = $("#teamId").val();
		$("#teamId").chosen();
		if(teamId==""){
			$.error("暂无班级数据信息，请联系管理员");
			return false;
		}
	}
	if ("" === year || "undefined" === year) {
		$.error("请选择学年");
		return false;
	}
	if ("" === termCode || "undefined" === termCode) {
		$.error("请选择学期");
		return false;
	}
	if(monthNone == "display: none;"){
		if(("" === week || "undefined" === week) && ("" === month || "undefind" === month)){
			$.error("请选择周次");
			return false
		}
	}
	if(weekNone == "display: none;"){
		if(("" === month || "undefind" === month) && ("" === week || "undefined" === week)){
			$.error("请选择月份");
			return false;
		}
	}
	$requestData.teamId = teamId;
	$requestData.manager = manager;
	$requestData.termCode = termCode;
	$requestData.year = year;
	$requestData.gradeId = nj;
	$requestData.month = month;
	$requestData.week = week;
	$requestData.studentId = studentId;
	loader.show();
	if(teamId != null && "" != teamId){
		$.post("${pageContext.request.contextPath}/teach/incentiveEvaluation/inmyself", $requestData, function(data, status) {
			if("success" === status) {
				$(".data").html(data);
			}
			loader.close();
		});
	}
}
function searchmyself(studentName,studentId){
	var url = "";
	var loader = new loadLayer();
	var year = $("#xn").val();
	var termCode = $("#xq").val();
	var manager = $("#isvip").val();
	var month = $("#d4").val();
	var week = $("#select_week").val();
	var teamId = null;
	var nj = null;
	var checkDate=$("#startDate").val();
	var monthNone = $("#select_div_month").attr("style");
	var weekNone = $("#select_div_week").attr("style");
	var $requestData = {};
	if($("#sel_div").find("#nj").length>0){
		nj = $("#nj").val();
		teamId = $("#bj").val();
		
	}else{
		teamId = $("#teamId").val();
		$("#teamId").chosen();
		if(teamId==""){
			$.error("暂无班级数据信息，请联系管理员");
			return false;
		}
	}
	
	if ("" === year || "undefined" === year) {
		$.error("请选择学年");
		return false;
	}
	if ("" === termCode || "undefined" === termCode) {
		$.error("请选择学期");
		return false;
	}
	if(monthNone == "display: none;"){
		if(("" === week || "undefined" === week) && ("" === month || "undefind" === month)){
			$.error("请选择周次");
			return false
		}
	}
	if(weekNone == "display: none;"){
		if(("" === month || "undefind" === month) && ("" === week || "undefined" === week)){
			$.error("请选择月份");
			return false;
		}
	}
	$requestData.teamId = teamId;
	$requestData.manager = manager;
	$requestData.termCode = termCode;
	$requestData.year = year;
	$requestData.gradeId = nj;
	$requestData.month = month;
	$requestData.week = week;
	$requestData.studentId = studentId;
	$requestData.studentName = studentName;
	loader.show();
	if(teamId != null && "" != teamId){
		$.post("${pageContext.request.contextPath}/teach/incentiveEvaluation/myselfList", $requestData, function(data, status) {
			if("success" === status) {
				$(".data").html(data);
			}
			loader.close();
		});
	}
}
</script>
