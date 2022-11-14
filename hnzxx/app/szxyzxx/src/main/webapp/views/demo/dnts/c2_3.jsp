<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>客户投诉</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="客户投诉" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
						 <li><a  href="javascript:void(0);" class="on" >待处理</a></li>
						 <li><a  href="javascript:void(0);" >已处理</a></li>
						 <li><a  href="javascript:void(0);" >处理统计</a></li>
				        </ul>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b sq_list">
								<div class="select_div">
									<span>开放时间：</span> <input type="text"  onclick="WdatePicker();" />
								</div>
								<div class="select_div">
									<span>结束时间：</span> <input type="text"  onclick="WdatePicker();" />
								</div>
								<div class="select_div">
									<span>部门：</span> <select ><option>教务处</option><option>校长室</option></select>
								</div>
								  <div class="search_1" style="float:left;margin:5px 0 0 15px;">
									<input type="text" placeholder="问题描述关键字/问题类别">
									<a class="sea_s"><i class="fa fa-search"></i></a>
								</div>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>序号</th>
										<th>投诉人</th>
										<th>部门</th>
										<th>角色</th>
										<th>投诉次数</th>
										<th>处理评价</th>
									</tr>
								</thead>
								<tbody >
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>教务处</td>
										<td>教师</td>
										<td>1</td>
										<td><div class="p7"><div class="pf"><p style="width:40%"></p></div></div></td>
									</tr>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>教务处</td>
										<td>教师</td>
										<td>1</td>
										<td><div class="p7"><div class="pf"><p style="width:0"></p></div></div></td>
									</tr>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>教务处</td>
										<td>教师</td>
										<td>1</td>
										<td><div class="p7"><div class="pf"><p style="width:0"></p></div></div></td>
									</tr>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>教务处</td>
										<td>教师</td>
										<td>1</td>
										<td><div class="p7"><div class="pf"><p style="width:0"></p></div></div></td>
									</tr>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>教务处</td>
										<td>教师</td>
										<td>1</td>
										<td><div class="p7"><div class="pf"><p style="width:0"></p></div></div></td>
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