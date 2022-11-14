<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title>班主任</title>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">首页  >  班主任评价  > <span>评价记录</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>评价记录</p></div>
			<div class="f_right"><button class="btn btn-green"><i class="fa fa-external-link"></i>导出当前Excel表</button></div>
		</div>
		<div class="input_select">
			<div class="select_div"><select class="span2"><option>学年</option></select></div>
			<div class="select_div"><select class="span2"><option>学期</option></select></div>
			<div class="select_div"><select class="span2"><option>年级</option></select></div>
			<div class="select_div"><select class="span2"><option>班级</option></select></div>
			<div class="select_div">
				<select id="select_week" style="width: 240px;" >
					<option value="">请选学期</option>
				</select>
			</div>
			<div class="select_div">
				<span>关键字：</span>
				<input type="text">
			</div>
			<div class="btn_link" style="float: right;margin:5px 5px 0 0">
				<button class="btn btn-blue">搜索</button>
			</div>
		</div>
		<table class="table">
			<thead>
				<tr><th>年级</th><th>班级</th><th>时间</th><th>班主任</th><th>评价人数</th><th>综合评分↑</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody>
				<tr><td>高一</td><td>（1）班</td><td>10月23日-10月29日</td><td>郑新</td><td>45/50</td><td>9.2</td><td class="caozuo"><button class="btn btn-green">查看评语</button></td></tr>
				<tr><td>高一</td><td>（1）班</td><td>10月23日-10月29日</td><td>郑新</td><td>45/50</td><td>9.2</td><td class="caozuo"><button class="btn btn-green">查看评语</button></td></tr>
				<tr><td>高一</td><td>（1）班</td><td>10月23日-10月29日</td><td>郑新</td><td>45/50</td><td>9.3</td><td class="caozuo"><button class="btn btn-green">查看评语</button></td></tr>
				<tr><td>高一</td><td>（1）班</td><td>10月23日-10月29日</td><td>郑新</td><td>45/50</td><td>9.1</td><td class="caozuo"><button class="btn btn-green">查看评语</button></td></tr>
				<tr><td>高一</td><td>（1）班</td><td>10月23日-10月29日</td><td>郑新</td><td>45/50</td><td>9.5</td><td class="caozuo"><button class="btn btn-green">查看评语</button></td></tr>
				<tr><td>高一</td><td>（1）班</td><td>10月23日-10月29日</td><td>郑新</td><td>45/50</td><td>9.4</td><td class="caozuo"><button class="btn btn-green">查看评语</button></td></tr>
				<tr><td>高一</td><td>（1）班</td><td>10月23日-10月29日</td><td>郑新</td><td>34/50</td><td>9.2</td><td class="caozuo"><button class="btn btn-green">查看评语</button></td></tr>
				
			</tbody>
		</table>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="studentAid_list_content" />
				<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
</div>
<script>
$.getWeek({
	"selector":"#select_week",
	"begin" : "2018-02-03",
	"end" : "2018-07-01",
	"today" : "",
 	"isMonday":true,
	"isClear" : false,
	"isSelectCurrentWeek" : true,
	"clearedOptionTitle" : "请选择学期",
});
</script>
</body>
</html>