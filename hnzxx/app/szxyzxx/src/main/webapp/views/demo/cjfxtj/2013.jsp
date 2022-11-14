<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/kwgl.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<title>成绩查看</title>
<style>
.f_red,.f_green{margin:0 10px;}
</style>
</head>
<body >
<div class="kwgl">
	<div class="return_kw">
		<p>刘小诚-期末考试成绩查看</p>
		<div class="njtk">
			<a href="javascript:void(0)" class="on">综合统计</a>
			<a href="javascript:void(0)">单科分析</a>
			<a href="javascript:void(0)">趋势分析</a>
		</div>
		<a href="javascript:void(0)" class="btn btn-lightGray">返回</a>
		<a href="javascript:void(0)" class="btn btn-blue">导出成绩报告</a>
	</div>
	<div class="pd20">
		<div class="kw_select">
			<p class="xnxq fl" style="margin:0">2016-2017学年 春季学期（下学期）</p>
		</div>
		<div class="kwgl_main">
			<div class="ks_list" style="padding:20px;">
				<table class="table table-striped" style="border:1px solid #e4e8eb;">
					<thead><tr><th>科目</th><th>得分</th><th>班内排名</th><th>进退步</th><th>年级排名</th><th>进退步</th></tr></thead>
					<tbody>
						<tr><td>全学科</td><td>599分/900</td><td>第11名/50</td><td>20<span class="f_green">↑</span></td><td>第11名/50</td><td>20<span class="f_green">↑</span></td></tr>
						<tr><td>全学科</td><td>599分/900</td><td>第11名/50</td><td>20<span class="f_red">↓</span></td><td>第11名/50</td><td>20<span class="f_red">↓</span></td></tr>
						<tr><td>全学科</td><td>599分/900</td><td>第11名/50</td><td>20<span class="f_green">↑</span></td><td>第11名/50</td><td>20<span class="f_green">↑</span></td></tr>
						<tr><td>全学科</td><td>599分/900</td><td>第11名/50</td><td>20<span class="f_red">↓</span></td><td>第11名/50</td><td>20<span class="f_red">↓</span></td></tr>
						<tr><td>全学科</td><td>599分/900</td><td>第11名/50</td><td>20<span class="f_green">↑</span></td><td>第11名/50</td><td>20<span class="f_green">↑</span></td></tr>
						<tr><td>全学科</td><td>599分/900</td><td>第11名/50</td><td>20<span class="f_red">↓</span></td><td>第11名/50</td><td>20<span class="f_red">↓</span></td></tr>
						<tr><td>全学科</td><td>599分/900</td><td>第11名/50</td><td>20<span class="f_green">↑</span></td><td>第11名/50</td><td>20<span class="f_green">↑</span></td></tr>
						<tr><td>全学科</td><td>599分/900</td><td>第11名/50</td><td>20<span class="f_green">↑</span></td><td>第11名/50</td><td>20<span class="f_green">↑</span></td></tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
<script>
</script>
</html>