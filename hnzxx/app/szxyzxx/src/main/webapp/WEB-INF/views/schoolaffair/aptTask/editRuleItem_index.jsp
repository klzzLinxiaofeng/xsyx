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
			<jsp:param value="考核任务--考评细项" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							考核任务--考评细项列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="returnTaskList();"><i class="fa  fa-undo"></i>返回</a>
								<a href="javascript:void(0)" class="a4"
									onclick="loadCreatePage('${task.id}',false);"><i class="fa fa-plus"></i>新增加减分考核项目</a>
								<a href="javascript:void(0)" class="a2"
									onclick="loadCreatePage('${task.id}',true);"><i class="fa fa-plus"></i>新增日常考核项目</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b"  align="center">
								<h3><b>${task.name}</b></h3>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>考核类别</th>
											<th>内容分类</th>
											<th>名称</th>
											<th>分值</th>
											<th>负责人</th>
											<th>备注</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="editRuleItem_list_content">
									<jsp:include page="./editRuleItem_list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="editRuleItem_list_content" />
								<jsp:param name="url" value="/schoolAffair/aptTask/loadRuleItem?sub=list&dm=${param.dm}&aptTaskId=${task.id}" />
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
	$(function() {
		$("#ruleId").chosen();
	});
	//返回任务列表
	function returnTaskList(){
		location.href = '${ctp}/schoolAffair/aptTask/index?sub=index';
	}
	//  加载创建对话框
	function loadCreatePage(taskId,isTypeDaily) {
		$.initWinOnTopFromLeft('新增', '${ctp}/schoolAffair/aptTask/taskItemCreator?id=' + taskId + "&isTypeDaily=" + isTypeDaily,  '900', '700');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/schoolAffair/aptTask/editRuleItem/' + id,  '900', '700');
	}
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/schoolAffair/aptTask/deleteRuleItem/" + id, {"_method" : "delete"}, function(data, status) {
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