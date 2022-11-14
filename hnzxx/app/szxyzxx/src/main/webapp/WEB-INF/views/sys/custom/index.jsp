<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>菜单自定义</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param name="title" value="菜单自定义" />
			<jsp:param name="icon" value="icon-glass" />
			<jsp:param name="menuId" value="${param.dm}"  />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							菜单列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>菜单编号</th>
										<th>菜单名称</th>
										<th>访问地址</th>
										<th class="caozuo" style="max-width:250px;">操作</th>
									</tr>
								</thead>
								<tbody id="custom_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="custom_list_content" />
								<jsp:param name="url"
									value="/sys/custom/index?sub=list&dm=${param.dm}" />
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

	
	//  加载编辑角色对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft("修改菜单",'${pageContext.request.contextPath}/sys/custom/editor?id=' + id,'560', '300');
	}
</script>
</html>