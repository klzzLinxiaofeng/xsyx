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
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="学生健康" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学生健康列表
							<p class="btn_link" style="float: right;">
								<a style="margin-right: 0px;" href="javascript:void(0)" class="a3" onclick="$.refreshWin();">
									<i class="fa  fa-undo"></i>刷新列表
								</a>
								<%--<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
									<a href="javascript:void(0)" class="a2" id="downloadExcel" onclick="downloadExcel();" >
										<i class="fa fa-plus"></i>导出学生健康信息
									</a>
									<a href="javascript:void(0)" class="a4" onclick="loadCreatePage();">
										<i class="fa fa-plus"></i>添加学生健康信息
									</a>
								</c:if>--%>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
						<form action="${ctp}/teach/studentHealthArchive/download" method="post" style="position:relative;">
						<!-- <button type="submit" class="btn btn-info  a2 fa fa-plus" style="position:absolute;right:120px;top:-56px;border-radius:0; background-color:#EFAD4D; height:30px;">&nbsp;&nbsp;导出健康档案信息 </button> -->
							<div class="select_b">
								<div class="select_div">
									<span>学年：</span><select id="xn" name="schoolYear" class="chzn-select"style="width:120px;"></select> </div>
									<div class="select_div"><span>年级：</span><select id="nj" name="gradeId" class="chzn-select"style="width:120px;"></select> </div>
									<div class="select_div"><span>班级：</span><select id="bj" name="teamId" class="chzn-select"style="width:120px;"></select> </div>
									<div class="select_div"><span>学生：</span><select id="stu" name="studentId" class="chzn-select"style="width:120px;"></select></div>
									<div class="select_div"><span>健康类型：</span><select id="healthType" name="healthType" class="chzn-select"style="width:120px;">
										<option value="">请选择</option>
									</select>
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
						</form>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th>序号</th>
										<th>录入时间</th>
										<%--<th>年级</th>--%>
										<th>班级</th>
										<th>姓名</th>
										<th>健康类型</th>
										<th>健康详情</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="studentHealthArchive_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="studentHealthArchive_list_content" />
								<jsp:param name="url"
									value="/teach/studentHealthArchive/index?sub=list&dm=${param.dm}" />
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
// 		$.initCascadeSelector({"type" : "stu"});
		$.initCascadeSelector({"type" : "stu", "yearCallback" : function($this){search()}});
		$.jcGcSelector("#healthType", {"tc" : "GB-JKZK3"});
	});

	function search() {
		var typeId = $("#healthType").val();
		
		if(healthType == ''){
			
			healthType = null;
		}
		var val = {
			"schoolYear" : $("#xn").val(),
			"gradeId" : $("#nj").val(),
			"teamId" : $("#bj").val(),
			"studentId" : $("#stu").val(),
			"typeId" : typeId
		};
		var id = "studentHealthArchive_list_content";
		var url = "/teach/studentHealthArchive/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('添加信息', '${ctp}/teach/studentHealthArchive/creator', '900', '450');
	}

	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑','${ctp}/teach/studentHealthArchive/editor?id='+ id, '900', '400');
	}

	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情',
				'${ctp}/teach/studentHealthArchive/viewer?id='
						+ id, '900', '400');
	}
	
	function downloadExcel() {
		var schoolYear = $("#xn").val();
		var gradeId = $("#nj").val();
		var teamId = $("#bj").val();
		var studentId = $("#stu").val();
		var healthType = $("#healthType").val();
		var url = "${ctp}/teach/studentHealthArchive/download?";
		var param = "schoolYear=" + schoolYear + "&gradeId=" + gradeId + "&teamId=" + teamId + "&studentId=" + studentId + "&healthType=" + healthType;
		url = url + param;
		$("#downloadExcel").attr("href", url);
	}

	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/teach/studentHealthArchive/" + id, {
			"_method" : "delete"
		}, function(data, status) {
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