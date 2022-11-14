<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css"
	rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<title>运营人员管理</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param name="title" value="运营人员管理" />
			<jsp:param name="icon" value="icon-glass" />
			<jsp:param name="menuId" value="${param.dm}" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							用户列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();" class="a3"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if
									test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
									<a href="javascript:void(0)" class="a2"
										onclick="resetPwdBatch();"><i class="fa fa-plus"></i>批量重置密码</a>
								</c:if>
								<c:if
									test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
									<a href="javascript:void(0)" class="a4"
										onclick="loadCreatePage();"><i class="fa fa-plus"></i>初始运营人员</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>学校：</span> 
									<select id="school" name="school" onchange="search();">
										
									</select>
									<div class="clear"></div>
								</div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th><input type="checkbox" id="checkAll"></th>
										<th>账号</th>
										<!-- 									<th>用户昵称</th> -->
										<!-- 									<th>所属学校</th> -->
										<!-- 									<th>用户类型</th> -->
										<th>状态</th>
										<!-- 									<th>失效时间</th> -->
										<th>创建时间</th>
										<th class="caozuo">操作</th>
									</tr>
								</thead>
								<tbody id="user_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="user_list_content" />
								<jsp:param name="url" value="/sys/gadmin/index?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value=" ${page.pageSize }" />
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
		initCheckBtn();
		$.GroupSelector({
			"selector" : "#school"
		});
	});

	function selectedHandler(data) {
		return false;
	}

	function search() {
		var val = {};
		var groupId = $("#school").val();
		if (groupId != null && groupId != "") {
			val.groupId = groupId;
			var id = "user_list_content";
			var url = "/sys/gadmin/index?sub=list&dm=${param.dm}";
			myPagination(id, val, url);
		} else {
			$.alert("请选择学校");
		}
	}

	// 	加载创建菜单对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('初始化运营人员',
				'${pageContext.request.contextPath}/sys/gadmin/creator', '600',
				'400');
	}
	//  加载编辑菜单对话框  isCK 参数是 是否是参看
	function loadEditPage(id, isCK) {
		var mes = "编辑账号";
		if (isCK == 'disable') {
			mes = "账号详情";
		}
		$.initWinOnTopFromLeft(mes,
				'${pageContext.request.contextPath}/sys/user/editor?id=' + id
						+ '&isCK=' + isCK, '600', '320');
	}
	//加载角色分配页面
	function loadAssigningRolePage(id) {
		$.initWinOnTopFromTop('角色分配',
				'${pageContext.request.contextPath}/sys/ur/index?userId=' + id,
				'1000', ($(parent.window).height() - 50) + '');
	}

	function resetPwd(id) {
		$.confirm("确定重置当前账号密码？", function() {
			var ids = new Array();
			ids.push(id);
			var requestData = {};
			requestData.ids = ids;
			executeResetPwd(requestData);
		});
	}

	function resetPwdBatch() {
		$
				.confirm(
						"确定重置选中的账号密码？",
						function() {
							var checked = $("#user_list_content input:checkbox[name='ids']:checked");
							var ids = new Array();
							var requestData = {};
							$.each(checked, function(index, value) {
								ids.push($(value).val());
							});
							requestData.ids = ids;
							executeResetPwd(requestData);
						});
	}

	function executeResetPwd($requestData) {
		$.get("${pageContext.request.contextPath}/sys/user/editor/pwd",
				$requestData, function(data, status) {
					if ("success" === status) {
						if ("success" === data) {
							$.success("重置成功");
						} else {
							$.error("重置失败，系统异常");
						}
					}
				});
	}

	// 	删除菜单对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${pageContext.request.contextPath}/sys/user/" + id, {
			"_method" : "delete"
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
		$("#checkAll").on(
				"click",
				function() {
					$("#user_list_content input:checkbox[name='ids']").prop(
							"checked", this.checked);
				});
	}
</script>
</html>