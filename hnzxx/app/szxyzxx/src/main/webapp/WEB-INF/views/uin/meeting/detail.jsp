<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.service.uin.vo.ConfsRecord"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 227px;
}

.row-fluid .span5 {
	width: 30px;
	margin-right: 3px;
}  
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.table{
	font-size:12px;
}
</style>
</head>
<body style="background-color: #fff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets">
					<div class="widget-container">
						<table class="responsive table table-striped" id="data-table">
							<thead>
								<tr role="row">
									<th>名称</th>
									<th>是否出席会议</th>
									<th>出席身份</th>
									<th>入会时间</th>
									<th>退出会议时间</th>
									<th>退出会议方式</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="item">
									<tr>
										<td>${item.name}</td>
										<td>${item.isattended ? "出席" : "未出席"}</td>
										<td>
											<c:choose>
												<c:when test="${item.role == 0}">主持人</c:when>
												<c:when test="${item.role == 1}">普通参会者</c:when>
												<c:otherwise>未知</c:otherwise>
											</c:choose>
										</td>
										<td><fmt:formatDate value="${item.enrollmentDate}" pattern="yyyy-MM-dd HH:mm"/></td>
										<td><fmt:formatDate value="${item.leaveDate}" pattern="yyyy-MM-dd HH:mm"/></td>
										<td>
											<c:choose>
												<c:when test="${item.leaveway == 0}">主动挂断</c:when>
												<c:when test="${item.leaveway == 1}">主持人挂断</c:when>
												<c:when test="${item.leaveway == 2}">主持人踢出</c:when>
												<c:when test="${item.leaveway == 3}">未接通</c:when>
												<c:when test="${item.leaveway == 4}">会议结束</c:when>
												<c:otherwise>未知</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
								<c:if test="${empty list}">
									<tr>
										<td colspan="6">此会议无人参与</td>
									</tr>
								</c:if>
							</tbody>
						</table>
						<div class="clear"></div>
					</div>
				</div>
		</div>
	</div>
</body>
<script type="text/javascript">

	var checker;
	$(function() {
		
	});
	
</script>
</html>