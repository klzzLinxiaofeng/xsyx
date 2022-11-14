<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<title>教师任课管理</title>
</head>
<body >
<div class="kwgl">
	<div class="pd20">
		<div class="kwgl_main">
			<div class="ks_list" style="padding:0">
				<div class="all_table">
					<table class="table table-striped table1" style="border:1px solid #e4e8eb;">
						<thead><tr><th style="width:70px;">年级</th><th style="width:70px;">班级</th><th>班主任</th></tr></thead>
						<tbody>
							<tr><td>一年级</td><td>高一1班</td><td>刘先生<i class="edit"></i><i class="delete"></i></td></tr>
							<tr><td>一年级</td><td>高一1班</td><td>刘先生<i class="edit"></i><i class="delete"></i></td></tr>
							<tr><td>一年级</td><td>高一1班</td><td>刘先生<i class="edit"></i><i class="delete"></i></td></tr>
						</tbody>
					</table>
					<div class="fd_table">
						<table class="table table-striped" style="">
							<thead><tr><th >语文</th><th >数学</th><th >英语</th><th >物理</th><th >化学</th><th >生物</th><th >地理</th></tr></thead>
							<tbody>
								<tr><td>王先生生<i class="edit"></i><i class="delete"></i></td><td>李先生<i class="edit"></i> <i class="delete"></i></td><td><i class="add"></i></td><td><i class="add"></i></td><td><i class="add"></i></td><td><i class="add"></i></td><td><i class="add"></i></td></tr>
								<tr><td>王先生<i class="edit"></i><i class="delete"></i></td><td>李先生生生<i class="edit"></i> <i class="delete"></i></td><td><i class="add"></i></td><td><i class="add"></i></td><td><i class="add"></i></td><td><i class="add"></i></td><td><i class="add"></i></td></tr>
								<tr><td>王先生<i class="edit"></i><i class="delete"></i></td><td>李先生<i class="edit"></i> <i class="delete"></i></td><td><i class="add"></i></td><td><i class="add"></i></td><td><i class="add"></i></td><td><i class="add"></i></td><td><i class="add"></i></td></tr>
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
	var w1=w-300;
	$(".fd_table").width(w1);
	var i=$(".fd_table table th").length;
	if(w1>120*i){
		$(".fd_table table").width(w1);
	}else{
		$(".fd_table table").width(120*i);
	}
	$(".fd_table").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
	//子
	$(".fd_table table tr td").hover(function(){
		var j=$(this).parent().index();
		$(".table1 tr").removeClass("blue_1");
		$(".table1 tbody tr").eq(j).addClass("blue_1");
		$(this).parent().find(".edit").css("z-index","1");
		$(this).parent().find(".delete").css("z-index","1");
		$(".table1 tbody tr").eq(j).find(".edit").css("z-index","1");
		$(".table1 tbody tr").eq(j).find(".delete").css("z-index","1");
	},function(){
		var j=$(this).parent().index();
		$(".table1 tbody tr").eq(j).removeClass("blue_1");
		$(this).parent().find(".edit").css("z-index","-1");
		$(this).parent().find(".delete").css("z-index","-1");
		$(".table1 tbody tr").eq(j).find(".edit").css("z-index","-1");
		$(".table1 tbody tr").eq(j).find(".delete").css("z-index","-1");
	});
	
	//父
	$(".table1 tr td").hover(function(){
		var k=$(this).parent().index();
		$(".fd_table table tr").removeClass("blue_1");
		$(".fd_table table tbody tr").eq(k).addClass("blue_1");
		$(this).parent().find(".edit").css("z-index","1");
		$(this).parent().find(".delete").css("z-index","1");
		$(".fd_table table tbody tr").eq(k).find(".edit").css("z-index","1");
		$(".fd_table table tbody tr").eq(k).find(".delete").css("z-index","1");
	},function(){
		var k=$(this).parent().index();
		$(".fd_table table tbody tr").eq(k).removeClass("blue_1");
		$(this).parent().find(".edit").css("z-index","-1");
		$(this).parent().find(".delete").css("z-index","-1");
		$(".fd_table table tbody tr").eq(k).find(".edit").css("z-index","-1");
		$(".fd_table table tbody tr").eq(k).find(".delete").css("z-index","-1");
	});
});
$(window).resize(function(){
	var w=$(".all_table").width();
	var w1=w-300;
	$(".fd_table").width(w1);
	var i=$(".fd_table table th").length;
	if(w1>120*i){
		$(".fd_table table").width(w1);
	}else{
		$(".fd_table table").width(120*i);
	}
	$(".fd_table").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
})
</script>
</html>