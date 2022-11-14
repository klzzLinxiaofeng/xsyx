<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>用户角色管理</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid " style="margin-top: 10px;">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							角色类型列表
							<p style="float: right;" class="btn_link">
								<a class="a3" onclick="window.location.reload();"
									href="javascript:void(0)"><i class="fa  fa-undo"></i>刷新列表</a> <a
									onclick="loadCreatePage('${param.userId}');" class="a2"
									href="javascript:void(0)"><i class="fa fa-plus"></i>添加角色类型</a>
								<!-- <a href="javascript:void(0)" class="a3"><i class="fa fa-plus"></i>批量导入数据</a>
									<a href="javascript:void(0)" class="a4"><i class="fa fa-plus"></i>添加教师</a> -->
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<table class="responsive table table-striped table-bordered"
								id="data-table">
								<thead>
									<tr role="row">
										<!-- <th><input type="checkbox"></th> -->
										<th>角色名称</th>
										<th>角色优先级</th>
										<th>授权时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="yhji_list_content">
									<c:forEach items="${list}" var="ur">
										<tr id="${ur.id}_tr">
											<td>${ur.role.name}</td>
											<td>${ur.priority}</td>
											<td><t:showTime createTime="${ur.createDate}"></t:showTime></td>
											<td>
												<%-- 											<a href="javascript:loadEditPage('${ur.id}');">【编辑关联】</a> --%>
												<button onclick="deleteObj(this, '${ur.id}');" type="button"
													class="btn btn-blue">取消关联</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	// 	加载创建菜单对话框
	function loadCreatePage(userId) {
		$.initWinOnCurFromLeft('添加关联',
				'${pageContext.request.contextPath}/sys/ur/creator?userId='
						+ userId, '600', '400');
	} 
	//  加载编辑菜单对话框
	function loadEditPage(id) {
		$.initWinOnCurFromLeft('编辑关联',
				'${pageContext.request.contextPath}/sys/ur/editor?id=' + id,
				'600', '400');
	}

	// 	删除菜单对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${pageContext.request.contextPath}/sys/ur/" + id, {
			"_method" : "delete"
		}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常");
				}
			}
		});
	}

	function updateOrDelete(url, $requestData) {
		$.post(url, $requestData, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("操作成功");
				} else if ("fail" === data) {
					$.error("操作失败，系统异常");
				}
			}
		});
	}
</script>
</html>