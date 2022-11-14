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
			<jsp:param value="科研审评" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							科研审评
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>姓名：</span>
									<input type="text" id="teachName" name="teachName" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="关键字" value="">
								</div>
								<!-- <div class="select_div">
									<span>关键字：</span>
									<input type="text" id="searchWord" name="searchWord" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="教师姓名/工号" value="">
								</div> -->
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>姓名</th>
											<th>教师工号</th>
											<th>成果名称</th>
											<th>成果类型</th>
											<th>成果级别</th>
											<th>审核情况</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="result_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="result_list_content" />
								<jsp:param name="url" value="/teach/resultCheck/index?sub=list&dm=${param.dm}" />
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
		/* var val = {
				"searchWord" : $("#searchWord").val()
		}; */
		var val = {
				"teachName" : $("#teachName").val()
		};
		var name = $("#name").val();
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "result_list_content";
		var url = "/teach/resultCheck/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('审评', '${ctp}/teach/resultCheck/editor?id=' + id + "&isCK=disable", '600', '650');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/generalTeachingAffair/result/" + id, {"_method" : "delete"}, function(data, status) {
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