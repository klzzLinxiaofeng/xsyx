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
.table th{
	font-weight:normal
}
.table th ,.table td{
	padding-left:8px;
	font-size:12px;
}
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</head>
<body style="background-color: #fff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
				<%-- <form action="${ctp}/personnel/performanceEvaluationSearch/downLoadTask" method="get">
				<button type="submit">导出考核信息</button>
				<input type="hidden" name="taskId" value="${taskId }"/>
				</form> --%>
				<div style="padding:0 20px 0;">
					<table border="1" class="table table-bordered nr_table">
						<thead>
							<tr>
								<th>被考核人</th>
								<th>部门</th>
								<c:forEach items="${taskItemList }" var="taskItem">
								<th>${taskItem.itemName }</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${personnelList }" var="vo">
								<tr>
									<td><dota:fieldVal type="teacher" code="${vo.personnelId }"></dota:fieldVal></td>
									<td>${vo.department }</td>
 									<c:forEach items="${vo.taskItemListSort }" var="sort">
 										<td>${sort.score }</td>
 									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
					<div class="form-actions tan_bottom">
						<form action="${ctp}/personnel/performanceEvaluationSearch/downLoadTask" method="get" style="margin:0;display:inline">
							<button type="submit" class="btn btn-warning">导出</button>
							<input type="hidden" name="taskId" value="${taskId }"/>
						</form>
<!-- 						<button type="button" onclick=";" class="btn">取消</button> -->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
// 	function downLoadTask(taskId) {
// 		var url = "${ctp}/personnel/performanceEvaluationSearch/downLoadTask";
		
// 		$.post(url, {"taskId" : taskId, "_method" : "GET"}, function(data, status) {
// 			if("success" === status) {
// 				data = eval("(" + data +　")");
// 				if("success" === data.info) {
// 					$.success('导出成功');
// 					if(parent.core_iframe != null) {
// 							parent.core_iframe.window.location.reload();
// 						} else {
// 							parent.window.location.reload();
// 						}
// 					$.closeWindow();
// 				}else if("fail" === data.info) {
// 					$.error('导出失败');
// 				}else {
					
// 				}
// 			}else {
// 				$.error('服务器异常');
// 			}
// 		});
// 	}
</script>
</html>