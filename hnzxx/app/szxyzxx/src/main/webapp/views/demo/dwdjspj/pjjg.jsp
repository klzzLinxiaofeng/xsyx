<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>多维度教师评价系统</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="评价结果" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							评价结果列表
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
									<span>教师：</span> <select><option>罗志明</option><option>罗金淼</option><option>潘伟良</option></select>
								</div>
								<div class="select_div">
									<span>评价时间段：</span> <input type="text"  onclick="WdatePicker();" /> - <input type="text"  onclick="WdatePicker();" />
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>被评价人</th>
										<th>评价时段</th>
										<th>得分</th>
										<th>综合评分</th>
										<th>综合等级</th>
										<th>学生评价人数</th>
										<th>教师评价人数</th>
									</tr>
								</thead>
								<tbody id="studentAid_list_content">
									<tr>
										<td>罗志明</td>
										<td>2015-05-01-5015-05-30</td>
										<td>87</td>
										<td>85</td>
										<td>优</td>
										<td>45</td>
										<td>15</td>
									</tr>
									<tr>
										<td>罗晶淼</td>
										<td>2015-04-01-5015-04-25</td>
										<td>90</td>
										<td>87</td>
										<td>优</td>
										<td>94</td>
										<td>45</td>
									</tr>
									<tr>
										<td>潘伟良</td>
										<td>2015-05-01-5015-06-22</td>
										<td>74</td>
										<td>87</td>
										<td>良</td>
										<td>41</td>
										<td>16</td>
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