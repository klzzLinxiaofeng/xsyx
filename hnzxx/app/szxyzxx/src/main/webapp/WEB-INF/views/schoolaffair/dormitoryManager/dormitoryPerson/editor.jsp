<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css"
	rel="stylesheet">
<title></title>
</head>
<body>
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 20px;">
					<form class="form-horizontal tan_form" id="dormitoryPerson_form"
						action="javascript:void(0);">
						<table class=" responsive table table-striped table-bordered"
							style="text-align: right;" id="data-table">
							<thead>
								<tr role="row">
									<th>床位号</th>
									<th>年级</th>
									<th>班级</th>
									<th>姓名</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="module_list_content">
								<c:forEach items="${dormitoryPersonvo}" var="dps" varStatus="i">
									<tr id="${dps.id}_tr">
										<td>${i.index+1}<input id="xn" type="hidden"
											value="${dps.schoolYearId }" /></td>
										<td>${dps.gradeName}</td>
										<td>${dps.teamName}</td>
										<td>${dps.studentName}</td>
										<td><button class="btn btn-red" type="button"
												onclick="deleteObj(this, '${dps.id}');">删除</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- <p class="btn_link" style="float: left;">
								<a href="javascript:void(0)" class="a3"
									onclick="add();"><i class="fa  fa-plus"></i>&nbsp;&nbsp;新增</a>
						</p>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="" />
							<button class="btn btn-warning" type="button"
								onclick="saveOrUpdate();">保存</button>
						</div> -->
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	function add() {
		var s = 0;
		for (var i = 2; i < 6; i++) {
			s = i;
		}
		var tr = "<tr><td>"
				+ s
				+ "</td><td>2222</td><td>3333</td><td>4444</td><td><button class='btn btn-red' type='button'>删除 </button></td></tr>";
		var t = document.getElementById("data-table");
		t.innerHTML += tr;
	}

	//删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}
	// 	执行删除
	function executeDel(obj, id) {
		$.post(
				"${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/"
						+ id, {
					"_method" : "delete"
				}, function(data, status) {
					if ("success" === status) {
						if ("success" === data) {
							$.success("删除成功");
							$("#" + id + "_tr").remove();
						} else if ("fail" === data) {
							$.error("删除失败，系统异常", 1);
						}
					}
					parent.window.search();
				});
	}

	//导出对话框
	function educe() {
		var id = $("#id").val();
		window.location.href = "${pageContext.request.contextPath}/teach/studentCheckAttendance/checkOutOne?id="
				+ id;
	}
</script>