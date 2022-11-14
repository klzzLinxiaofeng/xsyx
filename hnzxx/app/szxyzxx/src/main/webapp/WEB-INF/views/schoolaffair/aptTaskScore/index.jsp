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
			<jsp:param value="进行考核" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							进行考核--考核任务列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
<!-- 								<a href="javascript:void(0)" class="a4" -->
<!-- 									onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加考核任务</a> -->
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>考核任务名称：</span>
									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<th>考核任务名称</th>
											<th>被考核人</th>
											<th>考核开始时间</th>
											<th>考核结束时间</th>
											<th>状态</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="aptTask_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="aptTask_list_content" />
								<jsp:param name="url" value="/schoolAffair/aptTask/index?sub=list&dm=${param.dm}" />
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
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "aptTask_list_content";
		var url = "/schoolAffair/aptTaskScore/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	//  加载日常审核对话框
	function checkEdit(id) {
		location.href = '${ctp}/schoolAffair/aptTaskScore/checkEdit?sub=index&edit=true&id=' + id;
	}
	
	//  加载加减分审核对话框
	function bonusEdit(id) {
		location.href = '${ctp}/schoolAffair/aptTaskScore/bonusCheck2?sub=index&edit=true&taskId=' + id;
	}
	
	function checkView(id) {
		location.href = '${ctp}/schoolAffair/aptTaskScore/checkEdit?sub=index&edit=false&id=' + id;
// 		$.initWinOnTopFromLeft('详情', '${ctp}/schoolAffair/aptTaskScore/checkView?id=' + id, '850', '700');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/schoolAffair/aptTask/" + id, {"_method" : "delete"}, function(data, status) {
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