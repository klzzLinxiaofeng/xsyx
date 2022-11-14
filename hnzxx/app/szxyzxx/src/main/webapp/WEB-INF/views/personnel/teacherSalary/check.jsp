<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span4 {
	width: 75%;
}

.row-fluid .span4 {
	width: 227px;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.table th{
	font-weight:normal
}
.table th ,.table td{
	padding-left:8px;
	font-size:12px;
}
</style>
</head>
<body style="background-color: #fff !important">
	<div class="row-fluid">
		<div class="span12">
		
			<div class="content-widgets" style="margin-bottom: 0">
			<div class="widget-head" style="margin:5px 20px 0;"><h3>教师薪资<p class="btn_link" style="float: right;">
			<c:choose>
				<c:when test="${isCK != null && isCk != '' }"></c:when>
				<c:otherwise><a href="javascript:void(0)" class="a4" onclick="loadCreatePage('${teacherId}');"><i class="fa fa-plus"></i>新增薪资信息</a></c:otherwise>
			</c:choose>
			</p></h3></div>
				<div class="widget-container" style="padding: 20px 0 0;">
				<div style="padding:0 20px 0;">
					<table border="1" class="table table-bordered nr_table">
						<thead>
							<tr>
								<th>薪资金额</th>
								<th>有效开始时间</th>
								<th>记录人员</th>
								<th>录入时间</th>
								<c:choose>
									<c:when test="${isCK != null && isCk != '' }"></c:when>
									<c:otherwise><th class="caozuo" style="max-width: 250px;">操作</th></c:otherwise>
								</c:choose>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${teacherSalaryList }" var="salary">
								<tr id="${salary.id }_tr">
									<td>${salary.salary }</td>
									<td><fmt:formatDate value="${salary.validDate}" pattern="yyyy/MM/dd" /></td>
									<td>${salary.recorder }</td>
									<td><fmt:formatDate value="${salary.createDate}" pattern="yyyy/MM/dd" /></td>
									
									<c:choose>
									<c:when test="${isCK != null && isCk != '' }"></c:when>
									<c:otherwise>
									<td class="caozuo">
										<button class="btn btn-blue" type="button" onclick="loadEditPage('${salary.id}');">编辑</button>
										<button class="btn btn-red" type="button" onclick="deleteObj(this, '${salary.id}');">删除</button>
									</td>
									</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
//加载创建对话框
function loadCreatePage(teacherId) {
	$.initWinOnTopFromLeft('新增薪资信息', '${ctp}/personnel/teacherSalary/add?teacherId=' + teacherId, '600', '330');
}

//加载编辑对话框
function loadEditPage(id) {
	$.initWinOnCurFromLeft('编辑', '${ctp}/personnel/teacherSalary/editor?id=' + id, '600', '300');
}

//	删除对话框
function deleteObj(obj, id) {
	$.confirm("确定执行此次操作？", function() {
		executeDel(obj, id);
	});
}

// 	执行删除
function executeDel(obj, id) {
	$.post("${ctp}/personnel/teacherSalary/" + id, {"_method" : "delete"}, function(data, status) {
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