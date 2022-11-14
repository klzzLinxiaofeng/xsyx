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
			<jsp:param value="物品统计" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							物品统计列表
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
									<span>物品类型：</span> <select><option>电器</option><option>教科书</option><option>车辆</option></select>
								</div>
								<div class="select_div">
									<span>物品名称：</span> <input type="text" /> 
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>物品类型</th>
										<th>物品名称</th>
										<th>物品价格</th>
										<th>物品库存数量</th>
										<th>物品出库数量</th>
										<th>物品库存上限</th>
										<th>物品库存下限</th>
										
									</tr>
								</thead>
								<tbody id="studentAid_list_content">
									<tr>
										<td>电器</td>
										<td>台式电脑</td>
										<td>2800</td>
										<td>85</td>
										<td>185</td>
										<td>500</td>
										<td>50</td>
									</tr>
									<tr>
										<td>电器</td>
										<td>笔记本电脑</td>
										<td>3500</td>
										<td>15</td>
										<td>285</td>
										<td>300</td>
										<td>100</td>
									</tr>
									<tr>
										<td>车</td>
										<td>奔驰</td>
										<td>250000</td>
										<td>6</td>
										<td>4</td>
										<td>10</td>
										<td>3</td>
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