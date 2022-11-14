<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>学生选课系统</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="教师打分" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							开课内容设置列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<a href="javascript:void(0)" class="a4" onclick="$.refreshWin();"><i class="fa  fa-plus"></i>添加开课</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								
								<div class="select_div">
									<span>科目：</span> <select><option>语文</option><option>数学</option><option>英语</option></select>
								</div>
								<div class="select_div">
									<span>年级：</span> <select><option>一年级</option><option>二年级</option><option>三年级</option></select>
								</div>
								<div class="select_div">
									<span>教师名称：</span> <select><option>罗志明</option><option>潘为良</option></select>
								</div>
								<div class="select_div">
									<span>类型：</span> <select><option>艺术类</option><option>文学类</option></select>
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>年级</th>
										<th>科目</th>
										<th>类型</th>
										<th>开课时间</th>
										<th>选课时间</th>
										<th>课程类型</th>
										<th>评价指标</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody id="studentAid_list_content">
									<tr>
										<td>二年级</td>
										<td>音乐</td>
										<td>艺术类</td>
										<td>2015-05-15</td>
										<td>2015-04-15</td>
										<th>必修课</th>
										<td>平时成绩+考试</td>
										<td>未开课</td>
										<td><button class="btn btn-danger">删除</button></td>
									</tr>
									<tr>
										<td>一年级</td>
										<td>语文</td>
										<td>文学类</td>
										<td>2015-06-15</td>
										<td>2015-04-15</td>
										<th>必修课</th>
										<td>平时成绩+考试</td>
										<td>未开课</td>
										<td><button class="btn btn-danger">删除</button></td>
									</tr>
									<tr>
										<td>三年级</td>
										<td>语文</td>
										<td>文学类</td>
										<td>2015-06-15</td>
										<td>2015-04-15</td>
										<th>必修课</th>
										<td>平时成绩+考试</td>
										<td>未开课</td>
										<td><button class="btn btn-danger">删除</button></td>
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