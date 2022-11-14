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
								<a href="javascript:void(0)" onclick="$.refreshWin();" class="a3"><i class="fa  fa-undo"></i>刷新列表</a>
								<!-- <a href="javascript:void(0)" onclick="uploadStudentInfo();" class="a4"><i class="fa  fa-arrow-up"></i>导入学生信息</a> -->
								<!-- <a href="javascript:void(0)" onclick="tjstudent();" class="a2"><i class="fa  fa-bar-chart"></i>统计学生信息</a> -->
								<a href="javascript:void(0)" onclick="exportStudentInfoPage();" class="a3"><i class="fa  fa-arrow-down"></i>导出学生信息</a>
								<%--<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
									<a href="javascript:void(0)" class="a4" onclick="loadCreateStudentPage();"><i class="fa fa-plus"></i>新建学生档案</a>
								</c:if>--%>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;" onchange="getClass()"></select> </div>
<!-- 								<div class="select_div"> -->
<!-- 									<span>年级：</span> -->
<!-- 									<select id="gradeId" name="gradeId" class="chzn-select" style="width:120px;" onchange="changClass()"> -->
<!-- <!-- 										<option value="">请选择</option> -->
<%-- 										<c:forEach items="${gradeList}" var="grade"> --%>
<%-- 											<option value="${grade.gradeId}">${grade.gradeName}</option> --%>
<%-- 										</c:forEach> --%>
<!-- 									</select> -->
<!-- 									</div> -->
								<div class="select_div"><span>班级：</span><select id="teamId" name="teamId" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>学生姓名：</span><input type="text" id="name" name="name" class="chzn-select" style="width:120px;"></div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>

							<div class="select_b" style="height:20px;display:none" id="d_switch">
								<div class="student_left">
									<span id="totalNum" style="color:#808080"><i class="fa fa-user"></i><span style="margin-left:0"></span></span>
									<span id="manNum" style="color:#07A7FB"><i class="fa fa-user"></i><span style="margin-left:0"></span></span>
									<span id="womanNum"style="color:#E50D8A"><i class="fa fa-user"></i><span style="margin-left:0"></span></span>
								</div>
								<div class="student_right">
									<span>家长端APP可以录入学生档案</span>
 									<a class="auto_open" id="btn_switch" onclick="changeInterrupteur()"></a>
								</div>
							</div>
							<table class="responsive table table-striped" id="data-table">
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
								<jsp:include page="./listForTeacher.jsp" />
							</tbody>
						</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="module_list_content" />
							<jsp:param name="url"  value="/teach/student/studentListForTeacher?sub=list" />
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
$(function() {
	$.ajaxSetup({
		async : false
	});
	
	$.initCascadeSelector({
		"type" : "team", 
		"yearCallback" : function($this){
			
			}
	});
	
	getClass();
	
	setTimeout(function () { 
		search();
    },0);
});
	
	function changeInterrupteur(){
		var teamId = $("#teamId").val();
		var val = {
				"schoolYear" : $("#xn").val(),
				"gradeId" : $("#gradeId").val(),
				"teamId" : $("#teamId").val(),
				"name" : $("#name").val(),
			};
		if(teamId == "" || teamId == "undefind"){
			return;
		}
		
		var url = "${pageContext.request.contextPath}/teach/student/modifyInterrupteur";
		$.post(url, {"teamId":teamId}, function(data) {
            if(data =="true"){
 				 $(".student_right a").removeClass("auto_close").addClass("auto_open");
 				$(".student_right span").html("家长端APP可以录入学生档案");
			} else{
 				$(".student_right a").removeClass("auto_open").addClass("auto_close");
 				$(".student_right span").html("家长端APP可以录入学生档案");
			}
		});
		
		//重新载入表格
		var id = "module_list_content";
		var url = "/teach/student/studentListForTeacher?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}


	function search() {
		var val = {
				"schoolYear" : $("#xn").val(),
				"teamId" : $("#teamId").val(),
				"name" : $("#name").val(),
			};
		if($("#teamId").val() == ''){
			$.alert("请选择班级");
			return;
		}
		var id = "module_list_content";
		var url = "/teach/student/studentListForTeacher?sub=list&dm=${param.dm}";
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
		var gradeId = $("#gradeId").val();
		var teamId =$("#teamId").val();
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
		var url="${pageContext.request.contextPath}/teach/student/modifyStudent?id="+id+"&year="+$("#xn").val();
        	$.initWinOnTopFromTop("修改学生信息", url, '900', '600');
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
	
	function tjstudent(){
		location.href="${pageContext.request.contextPath}/teach/student/studentTJ?type=bzr";
	}
	
	function getClass(){
		var schoolYear = $("#xn").val();
		var url = "${pageContext.request.contextPath}/teach/team/getclass";
		$.post(url, {"schoolYear":schoolYear}, function(data,status) {
			var obj = eval("(" + data + ")");
			if(status == "success"){
				$("#teamId").html("");
				if(obj.length > 0){
					$("#teamId").append("<option value=''>请选择</option>");
					for(var i=0; i<obj.length;i++){
						if(i == 0){
							var opt = "<option selected='selected' value='"+obj[i].teamId+"'>"+obj[i].teamName+"</option>";
						}else{
							var opt = "<option value='"+obj[i].teamId+"'>"+obj[i].teamName+"</option>";
						}
						$("#teamId").append(opt);
					}
				}else{
					$("#teamId").append("<option selected='selected' value=''>请选择</option>");
				}
			}
		});
	}
	
	function changeOfPage(id, name, sex, mobile){
		if(sex == 1){
			sex = "男";
		}else if(sex == 2){
			sex = "女";
		}
		$tr = $("#tr_"+id);
		$tr.find("td")[2].innerText = name;
		$tr.find("td")[3].innerText = sex;
		$tr.find("td")[4].innerText = mobile;
	}
	
</script>
</html>