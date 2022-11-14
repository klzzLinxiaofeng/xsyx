<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>教师签到</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="个人签到汇总" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							教师签到列表
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
									<span>上课班级： </span><select><option>罗志明</option><option>周瑾</option></select>
								</div>
								<div class="select_div">
									<span>时间： </span><input type="text" onclick="WdatePicker();">
								</div>
								<div class="select_div">
									<span>签到节次： </span>
									<select style="width: 165px; " class="chzn-done"><option value="3" selected="selected">第二节</option><option value="2">第一节</option><option value="10">晚自修</option><option value="1">早自修</option><option value="7">第六节</option><option value="6">第五节</option><option value="5">第四节</option><option value="4">第三节</option><option value="9">第八节</option><option value="8">第七节</option></select>
								</div>
								<div class="select_div">
									<span>签到人： </span><select><option>罗志明</option><option>周瑾</option></select>
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>姓名</th>
										<th>用户名</th>
										<th>职位</th>
										<th>签到次数</th>
										<th>未签次数</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>周瑾</td>
										<td>458412</td>
										<td>语文老师</td>
										<td>15</td>
										<td>1</td>
									</tr>
									<tr>
										<td>潘伟良</td>
										<td>458423</td>
										<td>数学老师</td>
										<td>18</td>
										<td>1</td>
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