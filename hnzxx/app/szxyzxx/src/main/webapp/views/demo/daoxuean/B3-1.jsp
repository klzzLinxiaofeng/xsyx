<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dxa/dxa.css" rel="stylesheet">
<title>备忘录</title>
<style>
	
</style>
</head>
<body>
<div class="container-fluid dxa">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets white">
				<div class="widget-head">
					<h3>
						<i class="fa fa-asterisk"></i>我创建的导学案
					</h3>
				</div>
				<div class="content-widgets cr_grey">
					<div class="my_creat">
						<div class="cr_top">
							<a href="javascript:void(0)" class="on">校本资源库</a>
							<a href="javascript:void(0)" >个人资源库</a>
						</div>
						<div class="table_sel">
							<table class="table table-border  table-striped">
								<thead><tr><th>导学案名称</th><th>科目</th><th>创建时间</th><th>操作</th></tr></thead>
								<tbody>
									<tr><td>蜀道难</td><td>语文</td><td></td><td class="caozuo"><button class="btn btn-warning" type="button"  style="z-index: -1;">布置</button><button class="btn btn-primary" type="button"  style="z-index: -1;">编辑</button><button class="btn btn-danger" type="button"  style="z-index: -1;">布置</button></td></tr>
									<tr><td>蜀道难</td><td>语文</td><td></td><td class="caozuo"><button class="btn btn-warning" type="button"  style="z-index: -1;">布置</button><button class="btn btn-primary" type="button"  style="z-index: -1;">编辑</button><button class="btn btn-danger" type="button"  style="z-index: -1;">布置</button></td></tr>
									<tr><td>蜀道难</td><td>语文</td><td></td><td class="caozuo"><button class="btn btn-warning" type="button"  style="z-index: -1;">布置</button><button class="btn btn-primary" type="button"  style="z-index: -1;">编辑</button><button class="btn btn-danger" type="button"  style="z-index: -1;">布置</button></td></tr>
								</tbody>
							</table>
							<table class="table table-border  table-striped" style="display:none">
								<thead><tr><th>导学案名称</th><th>科目</th><th>创建时间</th><th>操作</th></tr></thead>
								<tbody>
									<tr><td>蜀道难12</td><td>语文</td><td></td><td class="caozuo"><button class="btn btn-warning" type="button"  style="z-index: -1;">布置</button><button class="btn btn-primary" type="button"  style="z-index: -1;">编辑</button><button class="btn btn-danger" type="button"  style="z-index: -1;">布置</button></td></tr>
									<tr><td>蜀道难1</td><td>语文</td><td></td><td class="caozuo"><button class="btn btn-warning" type="button"  style="z-index: -1;">布置</button><button class="btn btn-primary" type="button"  style="z-index: -1;">编辑</button><button class="btn btn-danger" type="button"  style="z-index: -1;">布置</button></td></tr>
									<tr><td>蜀道难</td><td>语文</td><td></td><td class="caozuo"><button class="btn btn-warning" type="button"  style="z-index: -1;">布置</button><button class="btn btn-primary" type="button"  style="z-index: -1;">编辑</button><button class="btn btn-danger" type="button"  style="z-index: -1;">布置</button></td></tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
$(function(){
	$(".my_creat .cr_top a").click(function(){
		var i=$(this).index();
		$(".my_creat .cr_top a").removeClass("on");
		$(this).addClass("on")
		$(".table_sel table").hide();
		$(".table_sel table").eq(i).show();
	});
});
</script>
</body>
</html>