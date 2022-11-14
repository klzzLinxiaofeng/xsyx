<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>班级常规管理</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="月报表、学期报表" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							月报表、学期报表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<a href="javascript:void(0)" class="a2" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>导出</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>年级/班级：</span> <select><option>一年级一班</option><option>一年级二班</option><option>二年级一班</option><option>二年级二班</option></select>
								</div>
								<div class="select_div">
									<span>项目：</span> <select><option>常规检查</option><option>早锻炼</option><option>早自习</option><option>宿舍卫生</option><option>早操</option><option>眼保健操</option><option>晚自习</option></select>
								</div>
								<div class="select_div">
									<span>时间：</span> <select><option>按月</option><option>按学期</option></select>
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>年级/班级</th>
										<th>项目</th>
										<th>时间</th>
										<th>总人数</th>
										<th>总次数</th>
										<th>优（次数）</th>
										<th>优（次数）</th>
										<th>优（次数）</th>
										<th>优（次数）</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>五年级 四班</td>
										<td>常规检查</td>
										<td>3月</td>
										<td>50</td>
										<td>10</td>
										<td>6</td>
										<td>2</td>
										<td>1</td>
										<td>1</td>
									</tr>
									<tr>
										<td>五年级 四班</td>
										<td>常规检查</td>
										<td>4月</td>
										<td>50</td>
										<td>10</td>
										<td>5</td>
										<td>3</td>
										<td>1</td>
										<td>1</td>
									</tr>
									<tr>
										<td>五年级 四班</td>
										<td>常规检查</td>
										<td>5月</td>
										<td>51</td>
										<td>10</td>
										<td>7</td>
										<td>2</td>
										<td>1</td>
										<td>0</td>
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