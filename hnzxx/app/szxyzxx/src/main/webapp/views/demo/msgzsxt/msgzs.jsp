<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>名师工作室系统</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="名师工作室" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							名师工作室列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<a href="javascript:void(0)" class="a4" onclick="$.refreshWin();"><i class="fa  fa-plus"></i>新增场馆</a>
							<a href="javascript:void(0)" class="a4" onclick="$.refreshWin();"><i class="fa  fa-plus"></i>场馆申请</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
						<div class="select_b">
								<div class="select_div">
									<span>教师名称：</span> <select><option>罗志明</option><option>潘伟良</option><option>周津</option></select>
								</div>
								<div class="select_div">
									<span>工作室：</span> <select><option>教师宿舍101</option><option>教师宿舍102</option><option>三年级</option></select>
								</div>
								<div class="select_div">
									<span>学科：</span> <select><option>语文</option><option>数学</option><option>英语</option></select>
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>教师名称</th>
										<th>教师工作室</th>
										<th>教师学科</th>
										<th>教师年龄</th>
										<th>教师简介</th>
									</tr>
								</thead>
								<tbody >
									<tr>
										<td>罗志明</td>
										<td>教师宿舍101</td>
										<td>数学</td>
										<td>35</td>
										<td>有着丰富的教学经验，讲课风趣</td>
									</tr>
									<tr>
										<td>周津</td>
										<td>教师宿舍102</td>
										<td>英语</td>
										<td>37</td>
										<td>有着丰富的教学经验，讲课风趣</td>
									</tr>
									<tr>
										<td>赵峰</td>
										<td>教师宿舍103</td>
										<td>数学</td>
										<td>45</td>
										<td>有着丰富的教学经验，讲课生动</td>
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