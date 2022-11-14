<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>校内信息</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="校内信息栏目及权限分配" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							栏目权限列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<a href="javascript:void(0)" class="a4" onclick="$.refreshWin();"><i class="fa  fa-plus"></i>添加任务流程</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
							<div class="select_div">
									<span>栏目： </span><input type="text">
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>是否开启</th>
										<th>栏目名称</th>
										<th>栏目负责人</th>
										<th>栏目创建时间</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><input type="checkbox"></td>
										<td>教师园地</td>
										<td>周津</td>
										<td>2013-08-30</td>
									</tr>
									<tr>
										<td><input type="checkbox"></td>
										<td>学生园地</td>
										<td>罗静淼</td>
										<td>2014-08-30</td>
									</tr>
									<tr>
										<td><input type="checkbox"></td>
										<td>教育动态</td>
										<td>罗志明</td>
										<td>2013-08-30</td>
									</tr>
									<tr>
										<td><input type="checkbox"></td>
										<td>校园资讯</td>
										<td>冠洪</td>
										<td>2014-08-30</td>
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