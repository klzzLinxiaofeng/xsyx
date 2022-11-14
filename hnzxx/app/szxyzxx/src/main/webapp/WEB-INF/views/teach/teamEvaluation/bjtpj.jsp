<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>


<%@ include file="/views/embedded/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>班集体评价</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">


</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="班集体评价" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							班集体评价
							<p class="btn_link" style="float: right;">
                                <a href="javascript:void(0)" onclick="toDuty();" class="a3" style="padding: 0 20px;">
	                                <c:choose>
										<c:when test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
											值日管理
										</c:when>
										<c:otherwise>
											值日查看
										</c:otherwise>
									</c:choose>
								</a>
                            </p>
						</h3>
						
					</div>  
	
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="check-rated">
							<div class="minutes-rated">
                            <a href="javascript:void(0);" class="see-rated">班集体评价查看</a>
<%-- 						    <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}"> --%>
<!-- 								 <a href="javascript:void(0);" onclick="addEva()">添加班集体评价</a> -->
<!-- 								 <input type="hidden" id="isvip" value="yes"> -->
<%-- 							</c:if> --%>
							<c:choose>
								<c:when test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
									 <a href="javascript:void(0);" onclick="addEva()">添加班集体评价</a>
									 <input type="hidden" id="isvip" value="yes">
								</c:when>
								<c:when test="${isOnDuty}">
									 <a href="javascript:void(0);" onclick="toDutyEva()">添加班集体评价</a>
								</c:when>
							</c:choose>
                         
                        </div>
                        <div class="card_detail">
                        <div class="project-rated">
                        <div class="content-widgets" style="margin:0">
                        <div class="widget-container" style="padding:20px 0 0 0">
                            <div class="select_b" id="sel_div">
								<div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;" ></select> </div>
								<c:choose>
									<c:when test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
										<div class="select_div"><span style="padding-left:30px;">学期： </span> <select id="xq" name="xq" class="chzn-select" style="width:160px;" onchange="getDay()"></select></div>
										<div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select"  style="width:120px;"></select> </div>
										<div class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select" onchange="search()" style="width:120px;"></select> </div>
										<input type="hidden" id="Administrator" value="all">
									</c:when>
									<c:otherwise>
										<div class="select_div"><span style="padding-left:30px;">学期： </span> <select id="xq" name="xq" class="chzn-select" style="width:160px;" onchange="getClass()"></select></div>
										<div class="select_div" style="display: none"><span>年级：</span><select id="nj" name="nj" class="chzn-select"  style="width:120px;"></select> </div>
										<div class="select_div" style="display: none"><span>班级：</span><select id="bj" name="bj" class="chzn-select" style="width:120px;"></select> </div>
										<div class="select_div"><span>班级：</span><select id="teamId" name="teamId" class="chzn-select" onchange="search()" style="width:120px;"></select> </div>
									</c:otherwise>
								</c:choose>
<!-- 								<div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select" style="width:120px;"></select> </div> -->
<!-- 								<div class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select" onchange="search()" style="width:120px;"></select> </div> -->
								<div class="select_div"><span>日期：</span><input type="text" class="Wdate" id="startDate" name="startDate" onFocus="WdatePicker({lang:'zh-cn',minDate:start,maxDate:end,onpicked:search})" style="width:120px;height:31px;margin:0" placeholder="" value=""/>  </div>
								  <p class="btn_link" style="float: right;line-height:47px;margin:5px 10px 0 0;">
<!-- 									<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-plus"></i>导出</a> -->
									<a href="javascript:void(0)" class="a2" onclick="pjbb();"><i class="fa fa-bar-chart"></i>评价报表</a>
									</p>
								<div class="clear"></div>
							</div>
                        </div>
                    </div>
                    <div class="clear"></div>
                            
                            


                  <div id="kb_tb">
                 
                  </div>
                  
</body>
<script type="text/javascript">
    var teamId = null;
	var start;
	var end;
	//去拿到学期起始时间或结束时间
	//$('#xq').on('change',getDay);
	
	function getDay(){
		var term=$('#xq').val();
		$('#startDate').val("");
		if("" === term || "undefind" === term){
			return false;
		}
		var $requestData = {};
		$requestData.code=$('#xq').val();
		$.get("${pageContext.request.contextPath}/teach/teamEvaluation/list/json", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				start = data.begin;
				end = data.end;
				if(new Date().Format("yyyy-MM-dd")>end){
					$("#startDate").val(end);
				}else if(new Date().Format("yyyy-MM-dd")<start){
					$("#startDate").val(start);
				}else{
					$("#startDate").val(new Date().Format("yyyy-MM-dd"));
				}
				var bj=$('#bj').val();
				if(bj!=""){
					search();
				}
			}
		});
	}
	

	$(function(){
		$(".Wdate").keyup(function(){
        	this.value = this.value.replace(/[^\-\d.]/g, "");
        });
	});


	function addEva(){
		window.location.href="${pageContext.request.contextPath}/teach/teamEvaluation/addEvaluationIndex?dm=${param.dm}";
	}
	
	
	$(function() {
// 		var manager=$('#isvip').val();
// 		if(manager!='yes'){
		
// 			$.initCascadeSelector({
// 				"type" : "team",
// 				"selectOne":true, 
// 				"enableRole":true,
// 				"yearChangeCallback" : function(year) {
// 					if(year != "") {
// 						$.SchoolTermSelector({
// 							"selector" : "#xq",
// 							"condition" : {"schoolYear" : year},
// 							"afterHandler" : function($this) {
// 								$this.change();
// 								$("#xq_chzn").remove();
// 								$this.show().removeClass("chzn-done").chosen();
// 							}
// 						}
// 					);
//  					} else {
// 						$("#xq").val("");
// 						$("#xq_chzn").remove();
// 						$("#xq").show().removeClass("chzn-done").chosen();
// 					}
					
// 				}
// 			});
			
// 		}else{
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
				},"teamCallback" : function() {
					var s1=$('#bj').val();
                    if(teamId!=s1){
						search();
                    }
				}
			});
// 		}
	});
	
  
	function search() {
		var loader = new loadLayer();
		var year = $("#xn").val();
		var termCode = $("#xq").val();
		
		var nj = null;
		var $requestData = {};
		var checkDate=$("#startDate").val();
// 		if($("#sel_div").find("#nj").length>0){
		if($("#Administrator").val() != null && $("#Administrator").val() != undefined){
			nj = $("#nj").val();
	
			teamId = $("#bj").val();
		}else{
			teamId = $("#teamId").val();
			if(teamId==""){
				$.error("暂无班级数据信息，请联系管理员");
				return false;
			}
		}
		if ("" === year || "undefind" === year) {
			$.error("请选择学年");
			return false;
		}
		if ("" === termCode || "undefind" === termCode) {
			$.error("请选择学期");
			return false;
		}
		if ("" === nj || "undefind" === nj) {
			$.error("请选择年级");
			return false;
		}
		if(teamId == null || "" === teamId || "undefind" === teamId) {
			$.error("请选择班级");
			return false;
		}
		$requestData.teamId = teamId;
		
		$requestData.termCode = termCode;
		$requestData.year = year;
		$requestData.gradeId = nj;
		$requestData.checkDate = checkDate;
		loader.show();
	
		$.get("${pageContext.request.contextPath}/teach/teamEvaluation/list", $requestData, function(data, status) {
			if("success" === status) {
				$("#kb_tb").html("").html(data);
			}
			loader.close();
		});
	}
	
	function getClass(){		
		var schoolYear = $("#xn").val();
		var url = "${pageContext.request.contextPath}/teach/teamEvaluation/getTeam";
		$.post(url, {"schoolYear":schoolYear}, function(data,status) {
			var obj = eval("(" + data + ")");
			if(status == "success"){
				$("#teamId").html("");
				for(var i=0; i<obj.length;i++){
					var opt = "<option value='"+obj[i].teamId+"'>"+obj[i].teamName+"</option>";
					$("#teamId").append(opt);
				}
				if(obj.length == 0){
	 				$("#teamId").append("<option value=''>请选择</option>");
				}
				//$("#teamId").chosen();
				var selectObj = $("#teamId"); 
				selectObj.parent().children().remove('div'); 
				selectObj.removeClass(); 
				selectObj.addClass("chzn-select"); 
				selectObj.chosen(); 
			}
		});
		getDay();
	}
	
	function pjbb(){
		var s=$('#isvip').val();
		window.location.href="${pageContext.request.contextPath}/teach/teamEvaluation/pjbb?dm=${param.dm}&manager="+s;
	}
	
	function toDuty(){
		window.location.href="${pageContext.request.contextPath}/teach/teamEvaluation/duty/index?dm=${param.dm}&isOnDuty=${isOnDuty}";
	}
	function toDutyEva(){
		window.location.href="${pageContext.request.contextPath}/teach/teamEvaluation/duty/add?dm=${param.dm}";
	}
	</script>
</html>