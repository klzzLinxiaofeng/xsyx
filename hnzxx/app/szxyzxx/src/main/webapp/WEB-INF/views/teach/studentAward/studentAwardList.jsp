<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="学生奖励" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学生奖励列表
							<p class="btn_link" style="float: right;">
							<!-- <a href="javascript:void(0)" onclick="uploadStudentAwardInfo();" class="a3"><i class="fa  fa-undo"></i>导入学生奖励信息</a> -->
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<a id="downLoadExcel"  class="a2" href="" onclick="downLoadExcel();" class="a2" ><i class="fa fa-plus"></i>导出学生奖励信息</a>
							<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4" onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加学生奖励记录</a>
							</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>学年：</span> <select id="xn" name="schoolYear"
										class="chzn-select" style="width: 160px;"></select>
								</div>
								<div class="select_div">
									<span>年级： </span><select id="nj" name="gradeId"
										class="chzn-select" style="width: 120px;"></select>
								</div>
								<div class="select_div">
									<span>班级： </span><select id="bj" name="teamId"
										class="chzn-select" style="width: 160px;"></select>
								</div>
								<div class="select_div">
									<span>学生： </span><select id="stu" name="studentId"
										class="chzn-select" style="width: 160px;"></select>
								</div>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								</c:if>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>学年</th>
											<th>年级</th>
											<th>班级</th>
											<th>班内编号</th>
											<th>姓名</th>
											<th>奖励内容</th>
											<th>奖励级别</th>
											<th>获奖名次</th>
											<th>奖励类型</th>
											<th>奖励日期</th>
											<th>奖励单位</th>
											<th>备注</th>
						
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="studentAward_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="studentAward_list_content" />
								<jsp:param name="url" value="/teach/studentAward/studentAwardList?sub=list&dm=${param.dm}" />
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
		/* $.initCascadeSelector({
			"type":"stu"

	  }); */
	 
	  var year = '${sessionScope[sca:currentUserKey()].schoolYear}';
		$.initCascadeSelector({
			"type" : "stu",
			"yearCallback" : function($this){
				
				search();
			}
		});
	});

	function search() {
		var val = {};
		var studentId = $("#stu").val();
		var teamId = $("#bj").val();
		var schoolYear = $("#xn").val();
		var gradeId = $("#nj").val();
		
		if (studentId != null && studentId != "") {
			val.studentId = studentId;
		}
		if (gradeId != null && gradeId != "") {
			val.gradeId = gradeId;
		}
		if (teamId != null && teamId != "") {
			val.teamId = teamId;
		}
		if (schoolYear != null && schoolYear != "") {
			val.schoolYear = schoolYear;
		}
		var id = "studentAward_list_content";
		var url = "/teach/studentAward/studentAwardList?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	//导入对话框
	var h =$(window).height()*0.5-250;
	
	function uploadStudentAwardInfo(){
		$.initWinOnTopFromLeft("学生奖励信息导入", "${pageContext.request.contextPath}/teach/studentAward/upLoadStudentAwardInfoPage", '600', '400');
	}
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromTop('创建', '${ctp}/teach/studentAward/addStudentAwardPage','900','580',h);
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromTop('编辑', '${ctp}/teach/studentAward/modifyStudentAwardPage?id=' + id,'900','500',h);
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromTop('详情', '${ctp}/teach/studentAward/detailStudentAwardPage?id=' + id,'900','530',h);
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	//导出对话框
	function downLoadExcel(){
		
		var studentId = $("#stu").val();
		var teamId = $("#bj").val();
		var schoolYear = $("#xn").val();
		var gradeId = $("#nj").val();
		var param = "studentId="+studentId+"&"+"teamId="+teamId+"&"+"schoolYear="+schoolYear+"&"+"gradeId="+gradeId;
		var url = "${pageContext.request.contextPath}/teach/studentAward/downLoadExcel?";
		  url = url+param;
		  $("#downLoadExcel").attr("href", url);
		  
	}
	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/teach/studentAward/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>