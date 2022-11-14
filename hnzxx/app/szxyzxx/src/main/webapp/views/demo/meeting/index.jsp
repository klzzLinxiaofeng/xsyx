<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
<title>网络会议</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param name="title" value="网络会议" />
			<jsp:param name="icon" value="icon-glass" />
			<jsp:param name="menuId" value="${param.dm}"  />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							会议列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();" class="a3"><i class="fa  fa-undo"></i>刷新列表</a> 
								<a href="javascript:void(0)"  class="a4" onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建会议</a>
<%-- 									<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
<!-- 										<a href="javascript:void(0)" class="a2" onclick="resetPwdBatch();"><i class="fa fa-plus"></i>批量重置密码</a> -->
<%-- 										<shiro:hasRole name="${sca:getSuperAdminCode()}"> --%>
<!-- 											<a href="javascript:void(0)" class="a2" onclick="regPush();"><i class="fa fa-plus"></i>批量开通推送服务</a> -->
<%-- 										</shiro:hasRole>  --%>
<%-- 									</c:if> --%>
<%-- 									<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}"> --%>
<!-- 										<a href="javascript:void(0)" -->
<!-- 										class="a4" onclick="loadCreatePage();"><i -->
<!-- 										class="fa fa-plus"></i>创建用户</a> -->
<%-- 									</c:if> --%>
								<!-- <a href="javascript:void(0)" class="a3"><i class="fa fa-plus"></i>批量导入数据</a>
								<a href="javascript:void(0)" class="a4"><i class="fa fa-plus"></i>添加教师</a> -->
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<!-- <table class="responsive table table-striped nav_1" id="">
								<tbody>
									<tr>
										<td style="text-align: right; width: 8%; vertical-align: middle;">账号：</td>
										<td style="width: 15%">
											<input type="text" id="userName" name="userName" data-id-container="userName" style="margin-bottom: 0; padding: 6^px; width: 120px; margin-right: 3px;"
											placeholder="" data-id="" value="">
										</td>
										<td rowspan="">
											<button type="button" class="btn btn-primary"
												onclick="search()">查询</button>
										</td>
									</tr>
								</tbody>
							</table> -->
							<div class="select_b">
								<div class="select_div">
									<span>名称：</span><input type="text" id="userName" name="userName" data-id-container="userName" style="margin-bottom: 0; padding: 6^px; width: 120px; margin-right: 3px;"
											placeholder=""value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th>名称</th>
										<!-- 									<th>用户昵称</th> -->
										<!-- 									<th>所属学校</th> -->
										<!-- 									<th>用户类型</th> -->
										<th>开始时间</th>
										<!-- 									<th>失效时间</th> -->
										<th>结束时间</th>
										<th class="caozuo">操作</th>
									</tr>
								</thead>
								<tbody id="user_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="user_list_content" />
								<jsp:param name="url" value="/sys/user/index?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value=" ${page.pageSize }" />
							</jsp:include>
							
<!-- 							<a href="javascript:void(0);" id="ry_xz" data-id-container="ry_xz" >选择</a> -->
								
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
		initCheckBtn("jc_subject");
	});
	
	function selectedHandler(data) {
		return false;
	}
	
	function search() {
		var val = {};
		var userName = $("#userName").val();
		if (userName != null && userName != "") {
			val.userName = userName;
		}

		var id = "user_list_content";
		var url = "/sys/user/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建菜单对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建会议',
				'${pageContext.request.contextPath}/views/demo/meeting/creator.jsp', '600', '340');
	}
	//  加载编辑菜单对话框  isCK 参数是 是否是参看
	function loadEditPage($this) {
		$("#user_list_content").find("tr").attr("data-on", "false");
		$($this).parent().parent().attr("data-on", "true");
		$.initWinOnTopFromLeft("编辑会议", '${pageContext.request.contextPath}/views/demo/meeting/editor.jsp', '600', '340');
	}
	
	// 	删除菜单对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${pageContext.request.contextPath}/sys/user/" + id, {"_method" : "delete"
		}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
					// 					$.refreshCache("zhxx", id, function() {});
				} else if ("fail" === data) {
					$.error("删除失败，系统异常");
				}
			}
		});
	}

	function initCheckBtn() {
		$("#checkAll").on("click", function() {
			$("#user_list_content input:checkbox[name='ids']").prop(
					"checked", this.checked);
		});
	}
	
	function saveOrUpdateTd(action, name, startDate, endDate) {
		if ("create" === action) {
			$("#user_list_content").append("<tr><td>" + name + "</td><td>" + startDate + "</td><td>" + endDate + "</td><td class='caozuo'><button class='btn btn-info update_permission' type='button' onclick='openWin()'>进入</button><button class='btn btn-blue update_permission' type='button' onclick='loadEditPage();'>编辑</button><button class='btn btn-warning update_permission' type='button' onclick='deleteObj(this, 1)'>删除</button></td>")
		} else if ("update" === action) {
			var tr = $("#user_list_content").find("tr[data-on=true]");
			tr.find("td:eq(0)").text(name);
			tr.find("td:eq(1)").text(startDate);
			tr.find("td:eq(2)").text(endDate);
		}
	}
	
</script>
</html>