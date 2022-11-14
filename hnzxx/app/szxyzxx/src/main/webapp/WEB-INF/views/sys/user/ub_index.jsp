<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>绑定的账号</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid " style="margin-top: 10px;">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							绑定的账号
							<p style="float: right;" class="btn_link">
								<a class="a3" onclick="window.location.reload();"
									href="javascript:void(0)"><i class="fa  fa-undo"></i>刷新列表</a> 
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
										<th>账号名称</th>
<!-- 										<th>账号类型</th> -->
										<th>创建时间</th>
									</tr>
								</thead>
								<tbody id="bn_list_content">
									<c:forEach items="${items}" var="ub">
										<tr id="${ub.id}_tr">
											<td>${ub.bindingName}</td>
<%-- 											<td>${ub.bindingType} --%>
<%-- 												<c:choose> --%>
<%-- 													<c:when test="${ub.bindingType == 0}"></c:when> --%>
<%-- 													<c:when test="${ub.bindingType == 0}"></c:when> --%>
<%-- 													<c:when test="${ub.bindingType == 0}"></c:when> --%>
<%-- 													<c:when test="${ub.bindingType == 0}"></c:when> --%>
<%-- 												</c:choose> --%>
<!-- 											</td> -->
											<td><t:showTime createTime="${ub.createDate}"></t:showTime></td>
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
</html>