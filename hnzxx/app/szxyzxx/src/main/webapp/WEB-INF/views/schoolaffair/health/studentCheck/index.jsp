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
			<jsp:param value="学生晨检症状" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学生晨检症状列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加记录</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>年级：</span>
									<select id="gradeId" name="gradeId" class="chzn-select" style="width:120px;"></select>
								</div>
								<div class="select_div">
									<span>班级：</span>
									<select id="teamId" name="teamId" class="chzn-select" style="width:120px;"></select>
								</div>
								<div class="select_div">
									<span>学生：</span>
<!-- 									<select id="studentId" name="studentId"></select> -->
									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="学生姓名" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>年级</th>
											<th>班级</th>
											<th>姓名</th>
											<th>性别</th>
											
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="healthStudentCheck_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="healthStudentCheck_list_content" />
								<jsp:param name="url" value="/schoolaffair/healthStudentCheck/index?sub=list&dm=${param.dm}" />
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
	function search() {
		var val = {};
		var name = $("#name").val();
		var gradeId = $("#gradeId").val();
		var teamId = $("#teamId").val();
// 		var studentId = $("#student").val();
		var name = $("#name").val();
		if (name != null && name != "") {
			val.name = name;
		}
		if (gradeId != null && gradeId != "") {
			val.gradeId = gradeId;
		}
		if (teamId != null && teamId != "") {
			val.teamId = teamId;
		}
		var id = "healthStudentCheck_list_content";
		var url = "/schoolaffair/healthStudentCheck/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	
	$(function() {
		$.initNBSCascadeSelector({
			"type" : "team",
			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
			"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
// 			"stuSelectId" : "studentId",  //学生select标签ID 默认为stu
			"isEchoSelected" : true
		});
	});

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/schoolaffair/healthStudentCheck/creator', '600', '450');
	}
	
	function loadViewerPage(studentId, teamId) {
		$.initWinOnTopFromLeft('晨检症状', '${ctp}/schoolaffair/healthStudentCheck/viewer?studentId=' + studentId + '&teamId=' + teamId, '900', '600');
	}
	
	//查看晨检症状对话框
	function loadCheckPage(studentId, teamId) {
		$.initWinOnTopFromLeft('晨检症状', '${ctp}/schoolaffair/healthStudentCheck/check?studentId=' + studentId + '&teamId=' + teamId, '900', '600');
	}
	
</script>
</html>