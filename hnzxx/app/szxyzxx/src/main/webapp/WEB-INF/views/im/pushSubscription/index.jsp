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
			<jsp:param value="推送订阅配置" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							推送订阅配置
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建</a>
								</c:if>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>版本：</span>
									<select id="appKey" name="appKey" style="margin-bottom: 0; padding: 6px; width: 120px;" class="span4">
									</select>
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>推送内容分类代码</th>
											<th>推送内容名称</th>
											<th>推送版本分类</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="pushSubscription_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="pushSubscription_list_content" />
								<jsp:param name="url" value="/im/pushSubscription/index?sub=list&dm=${param.dm}" />
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
		var appKey = $("#appKey").val();
		if (appKey != null && appKey != "") {
			val.appKey = appKey;
		}
		var id = "pushSubscription_list_content";
		var url = "/im/pushSubscription/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	$(function() {
		$.AllAppEditionsSelector({
			"selector" : "#appKey"
		});
	});
	
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/im/pushSubscription/creator', '680', '350');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/im/pushSubscription/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/im/pushSubscription/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/im/pushSubscription/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
			window.location.reload();
		});
	}
</script>
</html>