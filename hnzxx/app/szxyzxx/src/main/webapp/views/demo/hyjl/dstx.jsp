<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>会议记录</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="到时提醒" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							会议记录列表
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
									<span>会议名称： </span><input type="text">
								</div>
								<div class="select_div">
									<span>会议室： </span><input type="text">
								</div>
								<div class="select_div">
									<span>时间： </span><input type="text" onclick="WdatePicker();">
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>会议主题</th>
										<th>会议室</th>
										<th>会议开始时间</th>
										<th>会议时长</th>
										<th>发起人</th>
										<th>发起时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>项目总结</td>
										<td>会议室2</td>
										<td>2015-04-15 04:00</td>
										<td>2小时</td>
										<td>罗志宝</td>
										<td>2015-04-11</td>
										<td><button class="btn btn-green">到时提醒</button></td>
									</tr>
									<tr>
										<td>项目开始</td>
										<td>会议室1</td>
										<td>2015-07-15 01:00</td>
										<td>3小时</td>
										<td>罗志宝</td>
										<td>2015-07-11</td>
										<td><button class="btn btn-green">到时提醒</button></td>
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