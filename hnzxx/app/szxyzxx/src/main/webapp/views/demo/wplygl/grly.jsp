<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>物品领用管理</title>
<style>
	input[type="text"]{
		margin-bottom:0;
	}
</style>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="个人领用" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							个人领用列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								
								<div class="select_div">
									<span>领用类型：</span> <select><option>电器</option><option>教科书</option><option>车辆</option></select>
								</div>
								<div class="select_div">
									<span>领用日期：</span> <input type="text" onclick="WdatePicker();" /> 
								</div>
								<div class="select_div">
									<span>申购名称：</span> <input type="text" />
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>领用类型</th>
										<th>领用时间</th>
										<th>领用物品</th>
										<th>领用用途</th>
										<th>领用数量</th>
									</tr>
								</thead>
								<tbody id="studentAid_list_content">
									<tr>
										<td>电器</td>
										<td>2015-05-17</td>
										<td>台式电脑</td>
										<td>办公需要</td>
										<td>1</td>
									</tr>
									<tr>
										<td>书</td>
										<td>2015-06-17</td>
										<td>语文教科书</td>
										<td>办公需要</td>
										<td>35</td>
									</tr>
									<tr>
										<td>车</td>
										<td>2015-07-11</td>
										<td>奥迪</td>
										<td>出差需要</td>
										<td>1</td>
									</tr>
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="studentAid_list_content" />
								<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
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
</html>