<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="学生处分" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学生处分列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
							<a id="downLoadExcel"  class="a2" href="" onclick="downLoadExcel();" class="a2" ><i class="fa fa-plus"></i>导出学生处分记录</a>
								<a href="javascript:void(0)" class="a4" onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建学生处分记录</a>
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
											<th style="min-width: 25px;">序号</th>
											<th style="min-width: 25px;">学年</th>
											<th style="min-width: 25px;">年级</th>
											<th style="min-width: 25px;">班级</th>
											<th style="min-width: 25px;">姓名</th>
											<th style="min-width:49px;">处分类型</th>
											<th style="min-width: 49px;">处分原因</th>
											<th>处分日期</th>
											<th style="min-width: 49px;">到期日期</th>
											<th>撤销日期</th>
											<th style="min-width: 73px;">是否撤销处分</th>
								      		<th style="min-width: 26px;">备注</th>
										<th class="caozuo">操作</th>
									</tr>
								</thead>
								<tbody id="module_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="module_list_content" />
								<jsp:param name="url" value="/teach/studentPunish/studentPunish?sub=list&dm=${param.dm}" />
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
		$.initCascadeSelector({
			"type":"stu",
				"yearCallback" : function($this){
					
					search();
				}
		});
		
		//违纪类别
		$.jcGcSelector("#punishType", {tc : "JY-WJLB"}, "", function() {
			$("#punishType").chosen();
		});
		
    });
	
	function search() {
		var val = {};
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
		var id = "module_list_content";
		var url = "/teach/studentPunish/studentPunish?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${pageContext.request.contextPath}/teach/studentPunish/addStudentPunishPage', '800', '490');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${pageContext.request.contextPath}/teach/studentPunish/modifyStudentPunishPage?id=' + id, '800', '490');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${pageContext.request.contextPath}/teach/studentPunish/detailStudentPunish?id=' + id, '800', '490');
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
		var url = "${pageContext.request.contextPath}/teach/studentPunish/downLoadExcel?";
		  url = url+param;
		  $("#downLoadExcel").attr("href", url);
		  
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${pageContext.request.contextPath}/teach/studentPunish/deleteStudentPunish?id=" + id, {"_method" : "delete"}, function(data, status) {
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