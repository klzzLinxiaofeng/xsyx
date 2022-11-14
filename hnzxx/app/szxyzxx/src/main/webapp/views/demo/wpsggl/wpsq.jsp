<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>物品申购管理</title>
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
			<jsp:param value="物品申请" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							物品申请列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<a onclick="loadCreatePage();" class="a4" href="javascript:void(0)"><i class="fa fa-plus"></i>添加物品申购</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								
								<div class="select_div">
									<span>申购人：</span> <input type="text" >
								</div>
								<div class="select_div">
									<span>申购时间范围：</span> <input type="text" onclick="WdatePicker();" /> - <input type="text" onclick="WdatePicker();" />
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
										<th>申购人</th>
										<th>申购时间</th>
										<th>物品名称</th>
										<th>物品用途</th>
										<th>物品单价</th>
										<th>物品个数</th>
										<th>使用人/单位</th>
										<th class="caozuo">操作</th>
									</tr>
								</thead>
								<tbody id="studentAid_list_content">
									<tr>
										<td>周津</td>
										<td>2015-05-17</td>
										<td>台式电脑</td>
										<td>办公需要</td>
										<td>3500</td>
										<td>1</td>
										<td>任课教师</td>
										<td class="caozuo"><button class="btn btn-green">批准</button><button class="btn btn-red">拒绝</button></td>
									</tr>
									<tr>
										<td>潘伟良</td>
										<td>2015-06-17</td>
										<td>平板</td>
										<td>教学需要</td>
										<td>1500</td>
										<td>5</td>
										<td>任课教师</td>
										<td class="caozuo"><button class="btn btn-green">批准</button><button class="btn btn-red">拒绝</button></td>
									</tr>
									<tr>
										<td>罗静淼</td>
										<td>2015-04-17</td>
										<td>钢笔</td>
										<td>办公需要</td>
										<td>25</td>
										<td>10</td>
										<td>三好学生</td>
										<td class="caozuo"><button class="btn btn-green">批准</button><button class="btn btn-red">拒绝</button></td>
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