<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>学年列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="学年管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							学年列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();"
									class="a3"><i class="fa  fa-undo"></i>刷新列表</a> 
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>新增学年</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>学年名称：</span>
									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="学年名称" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th>序号</th>
										<th>名称</th>
										<th>开始时间</th>
										<th>结束时间</th>
										<th class="caozuo">操作</th>
									</tr>
								</thead>
								<tbody id="schoolYear_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="schoolYear_list_content" />
								<jsp:param name="url" value="/teach/schoolYear/list?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize }" />
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

		var id = "schoolYear_list_content";
		var url = "/teach/schoolYear/list?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	//	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('新增学年',
				'${pageContext.request.contextPath}/teach/schoolYear/creator', '600',
				'300');
	}

	//  加载编辑菜单对话框  isCK 参数是 是否是参看
	function loadEditPage(id, isCK) {
		var mes = "编辑学年";
		if (isCK == 'disable') {
			mes = "学年详情";
		}
		$.initWinOnTopFromLeft(mes,
				'${pageContext.request.contextPath}/teach/schoolYear/editor?id=' + id
						+ '&isCK=' + isCK, '600', '300');
	}

	// 	删除菜单对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

// 	执行删除
	function executeDel(obj, id) {
		$.post("${pageContext.request.contextPath}/teach/schoolYear/" + id, {
			"_method" : "delete"
		}, function(data, status) {
			if ("success" === status) {
				if ("0" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("-1" === data) {
					$.error("删除失败,该学年下包含有学期");
				} else{
					$.error("删除失败,系统异常");
				}
			}
		});
	}

	// 查询学年下的所属学期
	function schoolTerm(year) {
		window.location.href = "${pageContext.request.contextPath}/teach/schoolTerm/list?year="
				+ year;
	}
	/* function schoolTerm(year) {
		$.initWinOnTopFromLeft('学期列表',
				'${pageContext.request.contextPath}/teach/schoolTerm/list?year='
						+ year, '800', '800');
	} */
	
</script>
</html>