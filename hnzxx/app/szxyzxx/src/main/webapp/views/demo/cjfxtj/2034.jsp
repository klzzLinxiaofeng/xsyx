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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<title>班级学生成绩单</title>
</head>
<body >
<div class="kwgl">
	<div class="pd20">
		<div class="kw_select">
			<p class="xnxq fl" style="margin:0">2016-2017学年 春季学期（下学期）</p>
			<p class="fr" style="color:#86939d">考试时间：2017/1/11-1/15</p>
		</div>
		<div class="kwgl_main">
			<div class="ks_list" style="padding:0">
				<div class="ban_select"><a href="javascript:void(0)" class="on">一年级1班</a><a href="javascript:void(0)">一年级2班</a><a href="javascript:void(0)">一年级3班</a></div>
				<div class="all_table">
					<table class="table table-striped" style="border:1px solid #e4e8eb;">
						<thead><tr><th style="width:38px;">学号</th><th style="width:58px;">姓名</th><th style="width:68px;">班级</th><th style="width:48px;">总分</th><th style="width:48px;">年级排名</th><th class="caozuo">操作</th></tr></thead>
						<tbody>
							<tr><td>1</td><td>刘小城</td><td>高一1班</td><td>600</td><td>1</td><td class="caozuo"><button class="btn btn-green">个人成绩分析</button><button class="btn btn-blue">导出分析报告</button></td></tr>
							<tr><td>1</td><td>刘小城</td><td>高一1班</td><td>600</td><td>1</td><td class="caozuo"><button class="btn btn-green">个人成绩分析</button><button class="btn btn-blue">导出分析报告</button></td></tr>
							<tr><td>1</td><td>刘小城</td><td>高一1班</td><td>600</td><td>1</td><td class="caozuo"><button class="btn btn-green">个人成绩分析</button><button class="btn btn-blue">导出分析报告</button></td></tr>
							<tr><td>1</td><td>刘小城</td><td>高一1班</td><td>600</td><td>1</td><td class="caozuo"><button class="btn btn-green">个人成绩分析</button><button class="btn btn-blue">导出分析报告</button></td></tr>
							<tr><td>1</td><td>刘小城</td><td>高一1班</td><td>600</td><td>1</td><td class="caozuo"><button class="btn btn-green">个人成绩分析</button><button class="btn btn-blue">导出分析报告</button></td></tr>
						</tbody>
					</table>
					<div class="fd_table">
						<table class="table table-striped" style="">
							<thead><tr><th style="width:50px;">语文</th><th style="width:50px;">年级排名</th><th style="width:50px;">数学</th><th style="width:50px;">年级排名</th><th style="width:50px;">语文</th><th style="width:50px;">年级排名</th><th style="width:50px;">数学</th><th style="width:50px;">年级排名</th></tr></thead>
							<tbody>
								<tr><td>90</td><td>1</td><td>90</td><td>1</td><td>90</td><td>1</td><td>90</td><td>1</td></tr>
								<tr><td>90</td><td>1</td><td>90</td><td>1</td><td>90</td><td>1</td><td>90</td><td>1</td></tr>
								<tr><td>90</td><td>1</td><td>90</td><td>1</td><td>90</td><td>1</td><td>90</td><td>1</td></tr>
								<tr><td>90</td><td>1</td><td>90</td><td>1</td><td>90</td><td>1</td><td>90</td><td>1</td></tr>
								<tr><td>90</td><td>1</td><td>90</td><td>1</td><td>90</td><td>1</td><td>90</td><td>1</td></tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script>
$(function(){
	var w=$(".all_table").width();
	var w1=w-650;
	$(".fd_table").width(w1);
	var i=$(".fd_table table th").length;
	$(".fd_table table").width(73*i);
	$(".fd_table").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
	$(".fd_table table tr td").hover(function(){
		var j=$(this).parent().index();
		$(".table1 tr").removeClass("blue_1");
		$(".table1 tbody tr button").css("z-index","-1");
		$(".table1 tbody tr").eq(j).addClass("blue_1");
		$(".table1 tbody tr").eq(j).find("button").css("z-index","1");
	},function(){
	});
	$(".table1 tr td").hover(function(){
		var k=$(this).parent().index();
		$(".fd_table table tr").removeClass("blue_1");
		$(".fd_table table tbody tr button").css("z-index","-1");
		$(".fd_table table tbody tr").eq(k).addClass("blue_1");
		$(".fd_table table tbody tr").eq(k).find("button").css("z-index","1");
	},function(){
	});
	/* $(".fd_table table tr td").click(function(){
		var j=$(this).parent().index();
		$(".table1 tbody tr").eq(j).addClass("blue_1");
	}) */
});
$(window).resize(function(){
	var w=$(".all_table").width();
	var w1=w-650;
	$(".fd_table").width(w1)
	var i=$(".fd_table table th").length;
	$(".fd_table table").width(73*i);
	$(".fd_table").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
})
</script>
</html>