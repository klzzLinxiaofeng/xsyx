<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>教师成长档案</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="教师详细" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							教师详细列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<a onclick="loadCreatePage();" class="a4" href="javascript:void(0)"><i class="fa fa-plus"></i>新建教师详细</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
							<div class="select_div">
									<span>教师名称： </span>
									<input type="text" class="span4" >
								</div>
							<div class="select_div">
									<span>文件类型： </span><select><option>教学情况</option><option>教科研成果展示</option><option>教案或者教学设计</option><option>教学反思</option><option>个人获得荣誉奖项</option><option>指导学生获奖情况</option></select>
								</div>
								<div class="select_div">
									<span>上传时间： </span><input type="text" onclick="WdatePicker();">
								</div>
								
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>教师名称</th>
										<th>所教科目</th>
										<th>上传日期</th>
										<th>文件类型</th>
										<th>文件</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>罗静淼</td>
										<td>语文老师</td>
										<td>2015-04-15</td>
										<td>教学情况</td>
										<td><a href="javascript:void(0)">五年4班语文第二学期的教学情况.doc</a></td>
									</tr>
									<tr>
										<td>周津</td>
										<td>数学老师</td>
										<td>2015-04-15</td>
										<td>科研成果展示</td>
										<td><a href="javascript:void(0)">勾股定理的广泛应用.doc</a></td>
									</tr>
									<tr>
										<td>罗志明</td>
										<td>英语老师</td>
										<td>2015-04-15</td>
										<td>教案或教学设计</td>
										<td><a href="javascript:void(0)">六年级英语上学期教案.doc</a></td>
									</tr>
									<tr>
										<td>王志佳</td>
										<td>语文老师</td>
										<td>2015-04-15</td>
										<td>指导学生获奖情况</td>
										<td><a href="javascript:void(0)">语文3年级全级作文大赛帮助学生获得第四名优异成绩.doc</a></td>
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