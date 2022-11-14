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
			<jsp:param value="荐读跟工作" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							荐读跟工作列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<a href="javascript:void(0)" class="a4" onclick="$.refreshWin();"><i class="fa  fa-plus"></i>新建</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
							<div class="select_div">
									<span>创建者： </span><input type="text">
								</div>
								<div class="select_div">
									<span>接收者： </span><input type="text">
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>创建人</th>
										<th>创建者身份</th>
										<th>接受对象</th>
										<th>接收的内容</th>
										<th>创建时间</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>周津</td>
										<td>校长</td>
										<td>全体老师</td>
										<td><a href="javascript:void(0)">http://www.jiaoxueyun.com</a></td>
										<td>2014-08-30</td>
									</tr>
									<tr>
										<td>罗志明</td>
										<td>教师</td>
										<td>全体老师</td>
										<td>《拒绝拖延症》这本书挺好的</td>
										<td>2015-07-30</td>
									</tr>
									<tr>
										<td>罗静淼</td>
										<td>副校长</td>
										<td>全体老师</td>
										<td>校运会要负责好</td>
										<td>2015-07-25</td>
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