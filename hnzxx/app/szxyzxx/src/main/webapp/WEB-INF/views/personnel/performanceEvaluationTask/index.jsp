<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<%
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String timeStr = sdf.format(new Date());
%>

<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="考核任务" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							考核任务列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加考核任务</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>考核任务名称：</span>
									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>考核任务名称</th>
											<th>考核人</th>
											<th>被考核人</th>
											<th>考核阶段开始日期</th>
											<th>考核阶段结束日期</th>
											<th>状态</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="performanceEvaluationTask_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="performanceEvaluationTask_list_content" />
								<jsp:param name="url" value="/personnel/performanceEvaluationTask/index?sub=list&dm=${param.dm}" />
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
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "performanceEvaluationTask_list_content";
		var url = "/personnel/performanceEvaluationTask/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/personnel/performanceEvaluationTask/creator', '800', '600');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/personnel/performanceEvaluationTask/editor?id=' + id, '800', '600');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/personnel/performanceEvaluationTask/viewer?id=' + id, '800', '600');
	}
	
	//  加载查询考核人与被考核人列表
	function checkName (id, type) {
		$.initWinOnTopFromLeft('查看', '${ctp}/personnel/performanceEvaluationTask/checkName?id=' + id + '&type=' + type, '600', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("删除成功后考核人评分一同删除，确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/personnel/performanceEvaluationTask/" + id, {"_method" : "delete"}, function(data, status) {
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
	
	//发布考核任务
	function issueTask(obj, id) {
		$.confirm("发布成功后不可编辑，确定执行此次操作？", function() {
			executeIssue(obj, id);
		});
	}
	
	//执行发布
	function executeIssue(obj, id) {
		$.post("${ctp}/personnel/performanceEvaluationTask/issue/" + id, {"_method" : "post"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("发布成功");
					parent.core_iframe.window.location.reload(); 
				} else if ("fail" === data) {
					$.error("发布失败，系统异常", 1);
				}
			}
		});
	}
	$.ajaxSetup ({
     	async: false
    });
	//创建弹出框的筛选教师控件的回调函数
	function selectedHandler(data) {
		var evaluate = $(parent.window.frames[data.targetWindowName].document).find("#" + data.btnId + "_select");
		var evaluateId = $(parent.window.frames[data.targetWindowName].document).find("#" + data.btnId + "Id");
		var eva = $(parent.window.frames[data.targetWindowName].document).find("#" + data.btnId);
		var name = evaluate.val();
		var nameArr = [];
		var fla;
		$.each(data.names, function(index, value) {
			var ids = evaluateId.val();
			var idsArr = [];
			idsArr = ids.split(";");
			var val = data.ids[index];
			fla = $.inArray(val, idsArr);
			if(fla == -1) {
				name = name + ";" + value;
			}
// 			name = name + ";" + value;
// 			arr = name.split(";");
// 			var flag = $.inArray(value, arr);
// 			if(flag == -1) {
// 				name = name + ";" + value;
// 			}
		});
		var first = name.substring(0,1);
		if(first == ";") {
			name = name.substring(1);
		}
		
		var id = evaluateId.val();
		var arr = [];
		$.each(data.ids, function(index, value) {
			arr = id.split(";");
			var flag = $.inArray(value, arr);
			if(flag == -1){
				id = id + ";" + value;
			}
		});
		evaluate.val(name);
		evaluateId.val(id);
		eva.val(id);
		$(parent.window.frames[data.targetWindowName].document).find("label[for='" + data.btnId + "']").remove();
		$.success("设置成功");
		$.closeWindowByNameOnParent(data.windowName);
	}
</script>
</html>