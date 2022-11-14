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
			<jsp:param value="IM账号查询" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							IM账号查询
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<%-- <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建</a>
								</c:if> --%>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>角色名称：</span>
									<input type="text" id="searchWord" name="searchWord" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="请输入用户名/用户姓名/班级名称" value="">
								</div>
								<div class="select_div">
									<span>类型：</span>
									<select id="imType" name="imType" style="margin-bottom: 0; padding: 6px; width: 120px;" class="span4">
									</select>
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>用户名</th>
											<th>用户姓名/班级名称</th>
											<th>产品名称</th>
											<th>账号</th>
											<th>类型</th>
									</tr>
								</thead>
								<tbody id="imAccount_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="imAccount_list_content" />
								<jsp:param name="url" value="/im/imAccount/index?sub=list&dm=${param.dm}" />
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
		var searchWord = $("#searchWord").val();
		var imType = $("#imType").val();
		if (searchWord != null && searchWord != "") {
			val.searchWord = searchWord;
		}
		if (imType != null && imType != "") {
			val.imType = imType;
		}
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "imAccount_list_content";
		var url = "/im/imAccount/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	$(function(){
		$.IMProviderSelector({
			   "selector" : "#imType",
		});
	});
	
</script>
</html>