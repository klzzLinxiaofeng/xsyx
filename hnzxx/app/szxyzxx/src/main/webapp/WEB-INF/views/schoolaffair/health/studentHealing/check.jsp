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
			<div class="widget-head" style="margin:5px 20px 0;"><h3>就诊登记<p class="btn_link" style="float: right;">
			<c:choose>
				<c:when test="${isCK != null && isCk != '' }"></c:when>
				<c:otherwise><a href="javascript:void(0)" class="a4" onclick="loadCreatePage('${studentId}', '${teamId }');"><i class="fa fa-plus"></i>添加记录</a></c:otherwise>
			</c:choose>
			</p></h3></div>
				<div class="widget-container" style="padding: 20px 0 0;">
				<div style="padding:0 20px 0;">
					<table border="1" class="table table-bordered nr_table">
						<thead>
							<tr>
								<th>日期</th>
								<th>主要症状</th>
								<th>处理方式</th>
								<c:choose>
									<c:when test="${isCK != null && isCk != '' }"></c:when>
									<c:otherwise><th class="caozuo" style="max-width: 250px;">操作</th></c:otherwise>
								</c:choose>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${healingList }" var="healing">
								<tr id="${healing.id }_tr">
									<td><fmt:formatDate value="${healing.healingDate}" pattern="yyyy/MM/dd" /></td>
									<td>${healing.symptom }</td>
									<td>${healing.handle }
									
									<c:choose>
									<c:when test="${isCK != null && isCk != '' }"></c:when>
									<c:otherwise>
									<td class="caozuo">
										<button class="btn btn-blue" type="button" onclick="loadEditPage('${healing.id}');">编辑</button>
										<button class="btn btn-red" type="button" onclick="deleteObj(this, '${healing.id}');">删除</button>
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
function loadCreatePage(studentId, teamId) {
	$.initWinOnTopFromTop('添加记录', '${ctp}/schoolaffair/healthStudentHealing/add?studentId=' + studentId + '&teamId=' + teamId, '600', '300');
}

//加载编辑对话框
function loadEditPage(id) {
	$.initWinOnCurFromLeft('编辑', '${ctp}/schoolaffair/healthStudentHealing/editor?id=' + id, '600', '300');
}

//	删除对话框
function deleteObj(obj, id) {
	$.confirm("确定执行此次操作？", function() {
		executeDel(obj, id);
	});
}

// 	执行删除
function executeDel(obj, id) {
	$.post("${ctp}/schoolaffair/healthStudentHealing/" + id, {"_method" : "delete"}, function(data, status) {
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