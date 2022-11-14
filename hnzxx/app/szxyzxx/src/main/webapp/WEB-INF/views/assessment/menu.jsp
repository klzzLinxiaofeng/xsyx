<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
	<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_left.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<title>教师评价</title>
<style>
/*.main_iframe{margin-left:246px;margin-top: 3px;}*/
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="qyzj_header">
	<div class="logo"></div>
	<div class="qyzj_right">
		<ul>
			<li><a href="javascript:void(0)" class="b1 btn-blue" >班主任评价</a></li>
			<li><a href="javascript:void(0" class="b2">学科教师评价</a></li>
		</ul>
	</div>
</div>
<%--<div class="left_mu">
	<div class="leftbar">
		<div class="panel-body" id="LEFT_HEAD_PING_SHI">
			<div class="main_modele">
				<p class="p_icon"></p>
				<p class="p1">评师</p>
			</div>
			<ul class="first_ul">
				<li class="active">
					<a href="javascript:void(0)"><span>班主任评价</span></a>
					<ul>
						<li>
							<a href="javascript:void(0)" onclick="change('/assessment/teacher/index?evType=1')"  class="blue_1"><span>评价记录</span></a>
						</li>
						<li>
							<a href="javascript:void(0)" onclick="change('/assessment/statistics/index')"><span>评价统计</span></a>
						</li>
						<li>
							<a href="javascript:void(0)" onclick="change('/assessment/statistics/editBorad')"><span>评价设置</span></a>
						</li>
						<li class="line"></li>
					</ul>
				</li>
				<li>
					<a href="javascript:void(0)"><span>学科教师评价</span></a>
					<ul style="display: none;">
						<li>
							<a href="javascript:void(0)" onclick="change('/assessment/teacher/index?evType=2')"><span>评价记录</span></a>
						</li>
						<li>
							<a href="javascript:void(0)" onclick="change('/assessment/statistics/index?type=2')"><span>评价统计</span></a>
						</li>
						<li>
							<a href="javascript:void(0)" onclick="change('/assessment/statistics/editBorad?type=2 ')"><span>评价设置</span></a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div>--%>
<div class="main_iframe" style="background-color: #e3e3e3;">
	<div class="ps_nav">
		<a href="javascript:void(0)" onclick="change('/assessment/teacher/index?evType=1')"  class="on">评价记录</a>
		<a href="javascript:void(0)" onclick="change('/assessment/statistics/index')"  >评价统计</a>
		<a href="javascript:void(0)" onclick="change('/assessment/statistics/editBorad')" >评价设置</a>
		<ul>
	</div>
	<div class="ps_nav" style="display: none">
		<a href="javascript:void(0)" onclick="change('/assessment/teacher/index?evType=2')"  class="on">评价记录</a>
		<a href="javascript:void(0)" onclick="change('/assessment/statistics/index?type=2')"  >评价统计</a>
		<a href="javascript:void(0)" onclick="change('/assessment/statistics/editBorad?type=2 ')" >评价设置</a>
		<ul>
	</div>
	<iframe allowFullScreen src="${ctp}/assessment/teacher/index?evType=1" marginheight="0" marginwidth="0"
				frameborder="0" scrolling="yes" width="100%" style=""
				id="core_iframe" name="core_iframe" onLoad=""></iframe>
</div>
</body>
<script>
	$(function(){
	    $('.ps_nav a').click(function () {
			$('.ps_nav a').removeClass('on');
			$(this).addClass('on');
        });
        $('.qyzj_header .qyzj_right ul li a').click(function () {
            $('.qyzj_header .qyzj_right ul li a').removeClass('btn-blue');
            $(this).addClass('btn-blue');
            var i=$(this).parent().index();
            console.log(i)
            $('.ps_nav').hide();
            $('.ps_nav').eq(i).show();
            $('.ps_nav').eq(i).children().eq(0).click();
        });
	    //iframe的高度
		var h = $(parent.window).height()-65-62;
		$("#core_iframe").height(h)
		$(window).resize(function() {
			h = $(parent.window).height()-65-62;
			$("#core_iframe").height(h)
		})
// 		点击一级目录
		$(".left_mu .first_ul > li>a").click(function(){
			$(".left_mu .first_ul > li").removeClass("active");
			$(".left_mu .first_ul > li >ul").hide();
			$(this).parent().addClass('active')
			$(this).parent().children('ul').show();
		})
// 		点击二级目录
		$(".left_mu .first_ul > li >ul >li>a").click(function(){
			$(".left_mu .first_ul > li >ul >li>a").removeClass("blue_1");
			$(this).addClass("blue_1");
			var i=$(this).parent().index();
			var b_height=14+36*i;
			if($(this).parent().parent().children('.line').length==0){
				$(this).parent().parent().append("<li class='line'></li>")
			}
			$(".left_mu .first_ul > li>ul>.line").height(b_height)
		})
	})

	function change(url){
        $("#core_iframe").attr("src","${ctp}"+url);
	}
</script>
</html>