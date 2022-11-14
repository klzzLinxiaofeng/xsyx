<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
<style>
	.description{
		overflow:hidden;display:inline-block;width:280px; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;
	}
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="考核项目" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							考核项目列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<a href="javascript:void(0)" class="a2"
									onclick="createRule();"><i class="fa fa-plus"></i>标准管理</a>
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加考核项目</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>考核标准项目：</span>
<!-- 									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value=""> -->
									<select name="ruleId" id="ruleId" style="margin-bottom: 0; padding: 6px; width: 180px;">
										<option value="">请选择</option>
										<c:forEach items="${arList}" var="list">
											<option value="${list.id}">${list.name}</option>
										</c:forEach>
									</select>
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>考核标准</th>
											<th> 考核类别</th>
											<th>内容分类</th>
											<th>名称</th>
											<th>分值</th>
											<th>标注说明</th>
<!-- 										<th class="caozuo" style="max-width: 250px;">操作</th> -->
									</tr>
								</thead>
								<tbody id="aptRuleItem_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="aptRuleItem_list_content" />
								<jsp:param name="url" value="/schoolAffair/aptRuleItem/index?sub=list&dm=${param.dm}" />
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
		var ruleId = $("#ruleId").val();
		if (ruleId != null && ruleId != "") {
			val.ruleId = ruleId;
		}
		var id = "aptRuleItem_list_content";
		var url = "/schoolAffair/aptRuleItem/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	$(function() {
		$("#ruleId").chosen();
	});
	// 	加载创建考核项目对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/schoolAffair/aptRuleItem/creator',  '700', '500');
	}
	// 	加载标准管理对话框
	function createRule() {
		$.initWinOnTopFromLeft('标准管理', '${ctp}/schoolAffair/aptRuleItem/ruleCreator',  '700', '400');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/schoolAffair/aptRuleItem/editor?id=' + id,  '900', '700');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/schoolAffair/aptRuleItem/viewer?id=' + id,  '900', '700');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/schoolAffair/aptRuleItem/" + id, {"_method" : "delete"}, function(data, status) {
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