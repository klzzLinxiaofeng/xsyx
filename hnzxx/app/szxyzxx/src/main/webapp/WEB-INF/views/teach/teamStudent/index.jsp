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
			<jsp:param value="班级学生" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							班级学生列表
							<p class="btn_link" style="float: right;">
								<a style="margin-right: 147px;position: relative;top: 3px;" href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
<!-- 								<a id="downLoadExcel"  class="a2" href="" onclick="downLoadExcel();" class="a2" ><i class="fa fa-plus"></i>导出班级学生信息</a> -->
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
						<form action="${ctp}/teach/teamStudent/downLoadTeamStudent" method="post" style="position:relative;">
<%--						<button type="submit" class="btn btn-info  a2 fa fa-plus" style="position:absolute;right:-17px;top:-56px;border-radius:0; background-color:#EFAD4D; height:30px;">&nbsp;&nbsp;导出班级学生信息</button>--%>
							<div class="select_b">
								<div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;" disabled="disabled"></select> </div>
								<div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>学生姓名：</span><input type="text" id="name" name="name" class="chzn-select" style="width:120px;"></div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
						</form>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>用户名</th>
											<th>学籍号</th>
											<th>姓名</th>
											<th>性别</th>
											<th>手机号码</th>
											<th>职务</th>
											<th>家长姓名</th>
											<th>家长手机号码</th>
<!-- 										<th class="caozuo" style="max-width: 250px;">操作</th> -->
									</tr>
								</thead>
								<tbody id="teamStudent_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="teamStudent_list_content" />
								<jsp:param name="url" value="/generalTeachingAffair/teamStudent/index?sub=list&dm=${param.dm}" />
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
		$.initCascadeSelector({"type" : "team", "yearCallback" : function($this){search()}});
// 		$.initNBSCascadeSelector({"type" : "team", "gradeCallback" : function($this){search()}});
	});

	function search() {
		var val = {
			"schoolYear" : $("#xn").val(),
			"gradeId" : $("#nj").val(),
			"teamId" : $("#bj").val(),
			"name" : $("#name").val(),
			"pattern" : true
		};
		
		var id = "teamStudent_list_content";
		var url = "/teach/teamStudent/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/generalTeachingAffair/teamStudent/creator', '700', '290');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/generalTeachingAffair/teamStudent/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/generalTeachingAffair/teamStudent/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/generalTeachingAffair/teamStudent/" + id, {"_method" : "delete"}, function(data, status) {
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
	
	//导出班级学生信息
	function downLoadExcel() {
// 		var $requestData = {};
		var schoolYear = $("#xn").val();
		var gradeId = $("#nj").val();
		var teamId = $("#bj").val();
		var name = $("#name").val();
// 		if (schoolYear != null && schoolYear != "") {
// 			$requestData.schoolYear = schoolYear;
// 		}
// 		if (gradeId != null && gradeId != "") {
// 			$requestData.gradeId = gradeId;
// 		}
// 		if (teamId != null && teamId != "") {
// 			$requestData.teamId = teamId;
// 		}
// 		if (name != null && name != "") {
// 			$requestData.name = name;
// 		}
// 		$requestData._method = "post"
		var url = "${ctp}/teach/teamStudent/downLoadTeamStudent?";
		var param = "schoolYear=" + schoolYear + "&gradeId=" + gradeId + "&teamId=" + teamId + "&name=" + name;
		url = url + param;
		url = encodeURI(url);
		$("#downLoadExcel").attr("href", url);
// 		$.post(url, $requestData, function(data, status) {});
	}
</script>
</html>