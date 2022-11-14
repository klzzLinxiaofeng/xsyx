<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>后勤/总务维修</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="后勤/总务维修统计" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							后勤/总务维修列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
							<div class="select_div">
									<span>维修人： </span>
									<input type="text" class="span4" >
								</div>
								<div class="select_div">
									<span>时间范围： </span><input type="text" onclick="WdatePicker();"> - <input type="text" onclick="WdatePicker();">
								</div>
							<div class="select_div">
									<span>维修类型： </span><select><option>已经维修</option><option>未维修</option><option>正在维修</option></select>
								</div>
								
								
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>维修人</th>
										<th>已维修</th>
										<th>未维修</th>
										<th>正在维修</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>罗静淼</td>
										<td>120</td>
										<td>25</td>
										<td>20</td>
									</tr>
									<tr>
										<td>周津</td>
										<td>150</td>
										<td>250</td>
										<td>20</td>
									</tr>
									<tr>
										<td>国碇</td>
										<td>180</td>
										<td>205</td>
										<td>50</td>
									</tr>
									<tr>
										<td>之家</td>
										<td>1200</td>
										<td>250</td>
										<td>100</td>
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