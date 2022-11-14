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
</head>
<body >
<div class="kwgl">
	<div class="return_kw">
		<div class="njtk">
			<a href="javascript:void(0)" >全部</a>
			<a href="javascript:void(0)" class="on">年级统考</a>
			<a href="javascript:void(0)">班级统考</a>
		</div>
		<a href="javascript:void(0)" class="btn btn-lightGray">返回</a>
	</div>
	<div class="tk_div">
		<div class="pd20" style="display:none"></div>
		<div class="pd20">
			<div class="kw_select">
				<select><option>学年学期</option></select>
			</div>
			<div class="kwgl_main">
				<div class="ks_list" style="padding:20px;">
					<table class="table table-striped" style="border:1px solid #e4e8eb;">
						<thead><tr><th>序号</th><th>考试信息</th><th>考试时间段</th><th class="caozuo">操作</th></tr></thead>
						<tbody>
							<tr><td>1</td><td>期末考试</td><td>2017/1/11-1/15</td><td class="caozuo"><button class="btn btn-green">查看详情</button></td></tr>
							<tr><td>1</td><td>期末考试</td><td>2017/1/11-1/15</td><td class="caozuo"><button class="btn btn-green">查看详情</button></td></tr>
							<tr><td>1</td><td>期末考试</td><td>2017/1/11-1/15</td><td class="caozuo"><button class="btn btn-green">查看详情</button></td></tr>
							<tr><td>1</td><td>期末考试</td><td>2017/1/11-1/15</td><td class="caozuo"><button class="btn btn-green">查看详情</button></td></tr>
							<tr><td>1</td><td>期末考试</td><td>2017/1/11-1/15</td><td class="caozuo"><button class="btn btn-green">查看详情</button></td></tr>
							<tr><td>1</td><td>期末考试</td><td>2017/1/11-1/15</td><td class="caozuo"><button class="btn btn-green">查看详情</button></td></tr>
							<tr><td>1</td><td>期末考试</td><td>2017/1/11-1/15</td><td class="caozuo"><button class="btn btn-green">查看详情</button></td></tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="pd20" style="display:none">
			<div class="kw_select">
				<select><option>学年学期</option></select>
			</div>
			<div class="kwgl_main">
				<div class="ks_list" style="padding:20px;">
					<table class="table table-striped" style="border:1px solid #e4e8eb;">
						<thead><tr><th>序号</th><th>考试信息</th><th>考试时间段</th><th class="caozuo">操作</th></tr></thead>
						<tbody>
							<tr><td>1</td><td>随堂测试</td><td>2017/1/11-1/15</td><td class="caozuo"><button class="btn btn-green">查看详情</button><button class="btn btn-orange">班级成绩分析</button><button class="btn btn-blue">导出班级分析报告</button></td></tr>
							<tr><td>2</td><td>随堂测试</td><td>2017/1/11-1/15</td><td class="caozuo"><button class="btn btn-green">查看详情</button><button class="btn btn-orange">班级成绩分析</button><button class="btn btn-blue">导出班级分析报告</button></td></tr>
							<tr><td>3</td><td>随堂测试</td><td>2017/1/11-1/15</td><td class="caozuo"><button class="btn btn-green">查看详情</button><button class="btn btn-orange">班级成绩分析</button><button class="btn btn-blue">导出班级分析报告</button></td></tr>
							<tr><td>4</td><td>随堂测试</td><td>2017/1/11-1/15</td><td class="caozuo"><button class="btn btn-green">查看详情</button><button class="btn btn-orange">班级成绩分析</button><button class="btn btn-blue">导出班级分析报告</button></td></tr>
							<tr><td>5</td><td>随堂测试</td><td>2017/1/11-1/15</td><td class="caozuo"><button class="btn btn-green">查看详情</button><button class="btn btn-orange">班级成绩分析</button><button class="btn btn-blue">导出班级分析报告</button></td></tr>
							<tr><td>6</td><td>随堂测试</td><td>2017/1/11-1/15</td><td class="caozuo"><button class="btn btn-green">查看详情</button><button class="btn btn-orange">班级成绩分析</button><button class="btn btn-blue">导出班级分析报告</button></td></tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script>
$(function(){
	$(".njtk a").click(function(){
		var i=$(this).index();
		if(i!=0){
			$(".njtk a").removeClass("on");
			$(".njtk a").eq(i).addClass("on")
			$(".pd20").hide();
			$(".pd20").eq(i).show();
		}
	})
})
</script>
</html>