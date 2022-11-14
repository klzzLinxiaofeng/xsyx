<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<title>学生管理</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="学生管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学生管理
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="goback();" class="a4"><i class="fa   fa-reply"></i>返回</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
							<c:if test="${type == 'all'}">
								<div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>年级：</span><select id="gradeId" name="nj" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>班级：</span><select id="teamId" name="bj" class="chzn-select" style="width:120px;"></select> </div>
							</c:if>
							<c:if test="${type == 'bzr'}">
								<div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;" onchange="getClass()"></select> </div>
								<div class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select" style="width:120px;"></select> </div>
							</c:if>
								<div class="select_div"><span>学生姓名：</span><input type="text" id="name" name="name" class="chzn-select" style="width:120px;"></div>
								<button type="button" class="btn btn-primary" onclick="searchall()">查询</button>
								<div class="clear"></div>
							</div>
							<div class="s_rstj">
								<a href="javascript:void(0)" class="on" id="finishNum">完成人数（0）</a>
								<a href="javascript:void(0)" id="unfinishNum">未完成人数（0）</a>
							</div>
							<div class="s_table">
							<div class="unfinish">
							<table class="responsive table table-striped" id="data-table" style="border-bottom:0 none">
							<thead>
								<tr role="row">
		                           <th>用户名</th>
		                           <th>学籍号</th>
		                           <th>姓名</th>
		                           <th>性别</th>
		                           <th>手机号码</th>
		                           <th>所在班级</th>
		                           <th>在读状态</th>
		                           <th>学生类别</th>
<!-- 		                           <th>职务</th> -->
		                           <th class="caozuo" style="width:155px;">操作</th>
								</tr>
							</thead>
							<tbody id="module_list_content">
								<jsp:include page="./listTJ.jsp" />
							</tbody>
						</table>
<%-- 						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true"> --%>
<%-- 							<jsp:param name="id" value="module_list_content" /> --%>
<%-- 							<jsp:param name="url"  value="/teach/student/studentTJfinish?sub=list" /> --%>
<%-- 							<jsp:param name="pageSize" value="${page.pageSize}" /> --%>
<%-- 						</jsp:include> --%>
						</div>
						<div class="unfinish" style="display:none">
						<table class="responsive table table-striped" style="border-bottom:0 none" >
							<thead>
								<tr role="row">
		                           <th>用户名</th>
		                           <th>学籍号</th>
		                           <th>姓名</th>
		                           <th>性别</th>
		                           <th>手机号码</th>
		                           <th>所在班级</th>
		                           <th>在读状态</th>
		                           <th>学生类别</th>
<!-- 		                           <th>职务</th> -->
		                           <th class="caozuo">操作</th>
								</tr>
							</thead>
							<tbody id="module_list_content1">
								<jsp:include page="./listTJ1.jsp" />
							</tbody>
						</table>
<%-- 						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true"> --%>
<%-- 							<jsp:param name="id" value="module_list_content1" /> --%>
<%-- 							<jsp:param name="url"  value="/teach/student/studentTJUnfinish?sub=list" /> --%>
<%-- 							<jsp:param name="pageSize" value="${pageunfinish.pageSize}" /> --%>
<%-- 						</jsp:include> --%>
						</div>
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

function getClass(){
	var schoolYear = $("#xn").val();
	var url = "${pageContext.request.contextPath}/teach/team/getclass";
	$.post(url, {"schoolYear":schoolYear}, function(data,status) {
		var obj = eval("(" + data + ")");
		if(status == "success"){
			$("#bj").html("");
			for(var i=0; i<obj.length;i++){
				var opt = "<option value='"+obj[i].teamId+"'>"+obj[i].teamName+"</option>";
				$("#bj").append(opt);
			}
			$("#bj").append("<option value=''>请选择</option>");
		}
	});
}
	
$(function() {
	$(".s_rstj a").click(function(){
		$(".s_rstj a").removeClass("on");
		$(this).addClass("on");
		var i=$(this).index();
		$(".s_table .unfinish").hide();
		$(".s_table .unfinish").eq(i).show()
	})
	
	$.initCascadeSelector({
		"type" : "team", 
		"yearCallback" : function($this){
			
			},
		"gradeSelectId" : "gradeId", 
		"teamSelectId" : "teamId"
	});
	
	getClass();
	
	setTimeout(function () { 
		if("${type}" == 'bzr'){
			searchall();
		}
    },0);
	
});
	
	function searchall(){
		if($("#xn").val() == null || $("#xn").val() == ""){
			$.alert("请选择学年");
			return;
		}
		if("${type}" == 'all'){
			if($("#gradeId").val() == null || $("#gradeId").val() == ""){
				$.alert("请选择年级");
				return;
			}
			if($("#teamId").val() == null || $("#teamId").val() == ""){
				$.alert("请选择班级");
				return;
			}
		}
		if("${type}" == 'bzr'){
			if($("#bj").val() == null || $("#bj").val() == ""){
				$.alert("请选择班级");
				return;
			}
		}
		
		search();
		search1();
	}
	
	function search() {
		var val = {
// 			"schoolYear" : $("#xn").val(),
// 			"gradeId" : $("#nj").val(),
// 			"teamId" : $("#bj").val(),
// 			"name" : $("#name").val()
		};
		if("${type}" == 'all'){
			val.schoolYear = $("#xn").val();
			val.gradeId = $("#gradeId").val();
			val.teamId = $("#teamId").val();
			val.name = $("#name").val();
		}
		if("${type}" == 'bzr'){
			val.schoolYear = $("#xn").val();
			val.teamId = $("#bj").val();
			val.name = $("#name").val();
		}
		var id = "module_list_content";
		var url = "/teach/student/studentTJfinish?sub=list";
		myPagination(id, val, url);
	}
	
	function search1() {
		var val = {
// 				"schoolYear" : $("#xn").val(),
// 				"gradeId" : $("#nj").val(),
// 				"teamId" : $("#bj").val(),
// 				"name" : $("#name").val()
			};
		if("${type}" == 'all'){
			val.schoolYear = $("#xn").val();
			val.gradeId = $("#gradeId").val();
			val.teamId = $("#teamId").val();
			val.name = $("#name").val();
		}
		if("${type}" == 'bzr'){
			val.schoolYear = $("#xn").val();
			val.teamId = $("#bj").val();
			val.name = $("#name").val();
		}
		var id = "module_list_content1";
		var url = "/teach/student/studentTJUnfinish?sub=list";
		myPagination(id, val, url);
	}

		
	function getGrade(gradeId){
		$("#gradeId").val(gradeId);
	}
	
	function uploadStudentInfo(){
		$.initWinOnTopFromTop("学生信息导入", "${pageContext.request.contextPath}/teach/student/upLoadStudentInfoPage", '900');
	}
	
	function exportStudentInfoPage(){
		
		var url = "${pageContext.request.contextPath}/teach/student/exportStudentInfoPage";
		var schoolYear= $("#xn").val();
		var gradeId = $("#nj").val();
		var teamId =$("#bj").val();
		var name  = $("#name").val();
		var data = "?schoolYear="+schoolYear
		data = data+"&gradeId="+gradeId
		data = data+"&teamId="+teamId
		
		data = data+"&name="+name
		url = url+data;
		url =  encodeURI(url);
		$.initWinOnTopFromTop("学生基本信息导出", url, '742','500');
	}
	
	function loadCreateStudentPage() {
		$.initWinOnTopFromTop("新增学生信息", "${pageContext.request.contextPath}/teach/student/addStudentPage", '900', '600');
	}
	
	function loadModifyStudentPage(id){
		$.initWinOnTopFromTop("修改学生信息", "${pageContext.request.contextPath}/teach/student/modifyStudent?id="+id+"&schoolYear="+$("#xn").val(), '1000', '650');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft("查看档案", "${pageContext.request.contextPath}/teach/student/view?id="+id+"&schoolYear="+$("#xn").val(), '900', '600');
	}
	
	function deleteStudent(id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(id);
		});
	}
	
	function executeDel(id) {
		$.post("${pageContext.request.contextPath}/teach/student/deleteStudent?id=" + id, {"_method" : "delete"}, function(data, status) {
			if("success" === status) {
				if("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
					window.location.reload();
				} else if("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
	
	function goback(){
		window.location.href=document.referrer;
// 		location.href = "${pageContext.request.contextPath}/teach/student/studentList?dm=${param.dm}";
	}
</script>
</html>