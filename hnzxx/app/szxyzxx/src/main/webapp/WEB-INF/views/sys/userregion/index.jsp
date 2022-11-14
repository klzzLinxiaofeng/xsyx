<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
<title>区管管理</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param name="title" value="区管管理" />
			<jsp:param name="icon" value="icon-glass" />
			<jsp:param name="menuId" value="${param.dm}"  />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							用户列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();"
									class="a3"><i class="fa  fa-undo"></i>刷新列表</a> 
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>账号：</span><input type="text" id="userName" name="userName" data-id-container="userName" style="margin-bottom: 0; padding: 6px; width: 120px; margin-right: 3px;"
											placeholder=""value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th>用户账号</th>
										<th>用户状态</th>
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
								<jsp:param name="url" value="/sys/userregion/index?sub=list&dm=${param.dm}" />
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
	});
	
	function search() {
		var val = {};
		var userName = $("#userName").val();
		if (userName != null && userName != "") {
			val.userName = userName;
		}
		var id = "user_list_content";
		var url = "/sys/userregion/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	//加载角色分配页面
	function loadAssigningRegionPage(id) {
		$.initWinOnTopFromLeft('区域分配',
				'${pageContext.request.contextPath}/sys/userregion/creator?userId=' + id, '600', '220');
	}
</script>
</html>