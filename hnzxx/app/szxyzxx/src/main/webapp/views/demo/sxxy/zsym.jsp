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
			<jsp:param value="展示页面" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							展示列表
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
										<th>类别</th>
										<th>读书人</th>
										<th>性别</th>
										<th>年龄</th>
										<th>读书名称</th>
										<th>读书指数</th>
										<th>读书之星</th>
										<th>读书进度</th>
										<th>第几页</th>
										<th>百分之几</th>
									</tr>
								</thead>
								<tbody id="studentAid_list_content">
									<tr>
										<td>诗词</td>
										<td>罗志明</td>
										<td>男</td>
										<td>18</td>
										<td>苏轼诗词选</td>
										<td>高</td>
										<td>是</td>
										<td>一半</td>
										<td>78</td>
										<td>45%</td>
									</tr>
									<tr>
										<td>散文</td>
										<td>罗晶淼</td>
										<td>女</td>
										<td>19</td>
										<td>送你一匹马</td>
										<td>高</td>
										<td>是</td>
										<td>开始读</td>
										<td>20</td>
										<td>5%</td>
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