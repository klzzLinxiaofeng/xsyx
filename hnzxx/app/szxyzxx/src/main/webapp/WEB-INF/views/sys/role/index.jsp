<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>角色管理</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param name="title" value="角色管理" />
			<jsp:param name="icon" value="icon-glass" />
			<jsp:param name="menuId" value="${param.dm}"  />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							角色列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建角色</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<shiro:hasRole name="${sca:getSuperAdminCode()}">
									<div class="select_div">
										<span>所属组：</span>
										<select id="groupId" name="groupId"></select>
									</div>
									
									<script type="text/javascript">
										$(function() {
											$.GroupSelector({
												"selector" : "#groupId"
											});
										});
									</script>
									
								</shiro:hasRole>
								<div class="select_div">
									<span>角色名称：</span>
										<input type="text" id="name" name="name" style="width: 120px;">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<!-- 									<th><input type="checkbox"></th> -->
										<th>角色编号</th>
										<th>角色名称</th>
										<th>所属组</th>
										<th>系统默认</th>
										<!-- 									<th>所属用户类型</th> -->
										<!-- 									<th>所属学校</th> -->
										<!-- 									<th>是否启用</th> -->
										<th>创建时间</th>
										<th class="caozuo" style="max-width:250px;">操作</th>
									</tr>
								</thead>
								<tbody id="role_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="role_list_content" />
								<jsp:param name="url"
									value="/sys/role/index?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value=" ${page.pageSize}" />
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
		var groupId = $("#groupId").val();
		
		if (name != null && name != "") {
			val.name = name;
		}
		
		if (groupId != null && groupId != "") {
			val.groupId = groupId;
		}
		var id = "role_list_content";
		var url = "/sys/role/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建角色对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建角色',
				'${pageContext.request.contextPath}/sys/role/creator', '560',
				'300');
	}
	//  加载编辑角色对话框
	function loadEditPage(id, isCK) {
		var mes = "编辑角色";
		if (isCK == "disable") {
			mes = "角色详情"
		}
		$.initWinOnTopFromLeft(mes,
				'${pageContext.request.contextPath}/sys/role/editor?id=' + id
						+ '&isCK=' + isCK, '560', '300');
	}
	// 	授权菜单
	function accredit(id, groupId) {
		$.initWinOnTopFromLeft('菜单授权',
				'${pageContext.request.contextPath}/sys/role/group/grantor?id=' + id + "&groupId=" + groupId,
				'750', '450');
	}
	
// 	授权菜单
	function super_accredit(id, groupId) {
		$.initWinOnTopFromLeft('菜单授权',
				'${pageContext.request.contextPath}/sys/role/grantor?id=' + id + "&groupId=" + groupId,
				'750', '450');
	}

	// 	删除角色对话框
	function deleteObj(obj, id, isAllowDel) {
		if("true" === isAllowDel) {
			$.confirm("确定执行此次操作？", function() {
				executeDel(obj, id);
			});
		} else {
			$.alert("此角色为系统内置角色，不可删除");
		}
		
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${pageContext.request.contextPath}/sys/role/" + id, {
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