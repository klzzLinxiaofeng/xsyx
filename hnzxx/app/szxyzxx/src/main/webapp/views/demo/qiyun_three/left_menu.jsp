<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_left.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<title>左侧菜单</title>
<style>
.main_iframe{margin-left:246px;}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="left_mu">
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
							<a href="javascript:void(0)" ><span>评价记录</span></a>
						</li>
						<li>
							<a href="javascript:void(0)"><span>评价统计</span></a>
						</li>
						<li>
							<a href="javascript:void(0)"><span>评价设置</span></a>
						</li>
					</ul>
				</li>
				<li>
					<a href="javascript:void(0)"><span>教师评价</span></a>
					<ul style="display: none;">
						<li>
							<a href="javascript:void(0)"><span>评价记录</span></a>
						</li>
						<li>
							<a href="javascript:void(0)"><span>评价统计</span></a>
						</li>
						<li>
							<a href="javascript:void(0)"><span>评价设置</span></a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div>
<div class="main_iframe">
	<iframe allowFullScreen src="http://127.0.0.1/views/demo/qiyun_three/bzr_4.jsp" marginheight="0" marginwidth="0"
				frameborder="0" scrolling="yes" width="100%" style=""
				id="core_iframe" name="core_iframe" onLoad=""></iframe>
</div>
</body>
<script>
	$(function(){
		var h = $(parent.window).height();
		$("#core_iframe").height(h)
		$(window).resize(function() {
			h = $(parent.window).height();
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
</script>
</html>