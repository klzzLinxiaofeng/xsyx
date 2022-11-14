<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>书香校园管理</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="读书档案" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							读书档案列表
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
									<span>读书人：</span> <input type="text" >
								</div>
								<div class="select_div">
									<span>读书类型：</span> <select><option>读书档案</option><option>读书体会</option><option>佳作</option><option>留言</option><option>点评</option><option>读书小计</option></select>
								</div>
								<div class="select_div">
									<span>读书标题：</span> <input type="text" />
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>年级/班级</th>
										<th>原有总人数</th>
										<th>现有人数</th>
										<th>课堂情况</th>
										<th>教师在岗情况</th>
										<th>卫生情况</th>
									</tr>
								</thead>
								<tbody id="studentAid_list_content">
									<tr>
										<td>五年级 四班</td>
										<td>35</td>
										<td>34</td>
										<td>安静</td>
										<td>教师在岗</td>
										<td>优</td>
									</tr>
									<tr>
										<td>五年级 三班</td>
										<td>34</td>
										<td>34</td>
										<td>安静</td>
										<td>教师在岗</td>
										<td>良</td>
									</tr>
									<tr>
										<td>二年级 四班</td>
										<td>36</td>
										<td>32</td>
										<td>吵杂</td>
										<td>教师不在岗</td>
										<td>良</td>
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