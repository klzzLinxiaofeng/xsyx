<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>备忘录</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="备忘录" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							备忘录列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<a onclick="loadCreatePage();" class="a4" href="javascript:void(0)"><i class="fa fa-plus"></i>新建备忘录</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>到期时间： </span><input type="text" onclick="WdatePicker();">
								</div>
								
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>发起人</th>
										<th>备忘内容</th>
										<th>修改提醒时间</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>罗静淼</td>
										<td>周四下午2点准时到会议室1开会</td>
										<td><input type="text" onclick="WdatePicker();"></td>
										<td><button class="btn btn-danger">删除</button></td>
									</tr>
									<tr>
										<td>我</td>
										<td>周四下午参加学生毕业会</td>
										<td><input type="text" onclick="WdatePicker();"></td>
										<td><button class="btn btn-danger">删除</button></td>
									</tr>
									<tr>
										<td>周津</td>
										<td>周四下午5点羽毛球馆打球</td>
										<td><input type="text" onclick="WdatePicker();"></td>
										<td><button class="btn btn-danger">删除</button></td>
									</tr>
									<tr>
										<td>我</td>
										<td>周一下午第二节替林老师上课</td>
										<td><input type="text" onclick="WdatePicker();"></td>
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