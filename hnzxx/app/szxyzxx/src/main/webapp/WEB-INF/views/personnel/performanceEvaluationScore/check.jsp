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
				<div class="widget-container" style="padding: 20px 0 0;">
				<div style="padding:0 20px 0;">
					<table border="1" class="table table-bordered nr_table">
						<thead>
							<tr>
								<th>人员</th>
								<th>部门</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${voList }" var="vo">
								<tr>
									<td><dota:fieldVal type="teacher" code="${vo.personnelId }"></dota:fieldVal></td>
									<td>${vo.department }</td>
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
	
</script>
</html>