<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<title>A2-0我布置的</title>
</head>
<body>
<div class="qyzj_header">
	<div class="logo"></div>
	<div class="qyzj_right">
		<ul>
			<li><a href="javascript:void(0)" class="a1">创建导学案</a></li>
			<li><a href="javascript:void(0)" class="a2 btn-blue">我布置的</a></li>
			<li><a href="javascript:void(0)" class="a3">我接收的</a></li>
		</ul>
	</div>
</div>
<div class="content_main">
	<div class="ku_select">
		<div class="xdkm_div">
			<div class="xd_km">
				<div class="xueduan">
					<label>学段：</label>
					<div class="xd">
						<a href="javascript:void(0)">全部</a>
						<a href="javascript:void(0)" class="btn-blue">小学</a>
						<a href="javascript:void(0)">初中</a>
						<a href="javascript:void(0)">高中</a>
					</div>
				</div>
				<div class="xueduan">
					<label>科目：</label>
					<div class="xd">
						<a href="javascript:void(0)">全部</a>
						<a href="javascript:void(0)">语文</a>
						<a href="javascript:void(0)"  class="btn-blue">数学</a>
						<a href="javascript:void(0)">英语</a>
						<a href="javascript:void(0)">物理</a>
						<a href="javascript:void(0)">化学</a>
						<a href="javascript:void(0)">生物</a>
						<a href="javascript:void(0)">地理</a>
						<a href="javascript:void(0)">历史</a>
						<a href="javascript:void(0)">政治</a>
					</div>
				</div>
			</div>
			<div class="search_div">
				<label>搜索：</label>
				<div class="ss">
					<input type="text" placeholder="试卷标题" />
					<a href="javascript:void(0)" class="btn-blue">搜索</a>
				</div>
			</div>
		</div>
	</div>
	<div class="neirong_zs">
		<div class="nr_right" style="margin-left:0;">
			<div class="dxa_list" style="margin-bottom:20px;">
				<table class="table">
					<thead>
						<tr><th>布置时间</th><th>标题</th><th></th></tr>
					</thead>
					<tbody>
						<tr>
							<td>2017/05/20  17:35</td>
							<td>罗定邦中学高三语文期中考试试卷</td>
							<td class="caozuo"><btn class="btn btn-blue">编辑</btn><btn class="btn btn-red">删除</btn></td>
						</tr>
						<tr>
							<td>2017/05/20  17:35</td>
							<td>罗定邦中学高三语文期中考试试卷</td>
							<td class="caozuo"><btn class="btn btn-blue">编辑</btn><btn class="btn btn-red">删除</btn></td>
						</tr>
						<tr>
							<td>2017/05/20  17:35</td>
							<td>罗定邦中学高三语文期中考试试卷</td>
							<td class="caozuo"><btn class="btn btn-blue">编辑</btn><btn class="btn btn-red">删除</btn></td>
						</tr>
						<tr>
							<td>2017/05/20  17:35</td>
							<td>罗定邦中学高三语文期中考试试卷</td>
							<td class="caozuo"><btn class="btn btn-blue">编辑</btn><btn class="btn btn-red">删除</btn></td>
						</tr>
						<tr>
							<td>2017/05/20  17:35</td>
							<td>罗定邦中学高三语文期中考试试卷</td>
							<td class="caozuo"><btn class="btn btn-blue">编辑</btn><btn class="btn btn-red">删除</btn></td>
						</tr>
						<tr>
							<td>2017/05/20  17:35</td>
							<td>罗定邦中学高三语文期中考试试卷</td>
							<td class="caozuo"><btn class="btn btn-blue">编辑</btn><btn class="btn btn-red">删除</btn></td>
						</tr>
						
					</tbody>
				</table>
			</div>
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="studentAid_list_content" />
				<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
</div>
</body>
<script>
</script>
</html>