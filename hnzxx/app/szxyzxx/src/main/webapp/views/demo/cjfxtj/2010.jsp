<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/kwgl.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<title>姓名查询</title>
<style>
body{box-sizing:border-box;
	-moz-box-sizing:border-box; /* Firefox */
	-webkit-box-sizing:border-box; /* Safari */
	height:100%;
	padding:20px;
	}
</style>
</head>
<body>
	<div class="name_cx">
		<div class="cx_top">
			<div class="name-cx"><a href="javascript:void(0)" class="on">姓名查询</a><a href="javascript:void(0)">选项查询</a></div>
			<a href="javascript:void(0)" class="btn btn-lightGray">返回</a>
		</div>
		<div class="xx_s1">
			<div class="xs_select" >
				<div class="xsxm"><span>学生姓名：</span><input type="text"></div>
				<a href="javascript:void(0)" class="btn btn-green">查询</a>
			</div>
			<div class="xx_select" style="display:none">
				<div class="xxcx"><span>学年：</span><select><option>2016-2017学年</option></select></div>
				<div class="njbj">
					<span class="b1">年级/班级：</span>
					<div class="bj_all">
						<ul>
							<li>
								<a href="javascript:void(0)" class="a1 on">一年级</a>
								<div class="bj_div" style="display:block">
									<ul>
										<li><a href="javascript:void(0)" class="hover">一班</a></li>
										<li><a href="javascript:void(0)">二班</a></li>
										<li><a href="javascript:void(0)">三班</a></li>
										<li><a href="javascript:void(0)">四班</a></li>
										<li><a href="javascript:void(0)">五班</a></li>
									</ul>
								</div>
							</li>
							<li>
								<a href="javascript:void(0)" class="a1 ">二年级</a>
								<div class="bj_div">
									<ul>
										<li><a href="javascript:void(0)">一班</a></li>
										<li><a href="javascript:void(0)" >二班</a></li>
										<li><a href="javascript:void(0)">三班</a></li>
										<li><a href="javascript:void(0)">四班</a></li>
										<li><a href="javascript:void(0)">五班</a></li>
										<li><a href="javascript:void(0)">一班</a></li>
										<li><a href="javascript:void(0)" >二班</a></li>
										<li><a href="javascript:void(0)">三班</a></li>
										<li><a href="javascript:void(0)">四班</a></li>
										<li><a href="javascript:void(0)">五班</a></li>
										<li><a href="javascript:void(0)">一班</a></li>
										<li><a href="javascript:void(0)" >二班</a></li>
										<li><a href="javascript:void(0)">三班</a></li>
										<li><a href="javascript:void(0)">四班</a></li>
										<li><a href="javascript:void(0)">五班</a></li>
									</ul>
								</div>
							</li>
							<li>
								<a href="javascript:void(0)" class="a1">三年级</a>
								<div class="bj_div" >
									<ul>
										<li><a href="javascript:void(0)">一班</a></li>
										<li><a href="javascript:void(0)">二班</a></li>
										<li><a href="javascript:void(0)">三班</a></li>
										<li><a href="javascript:void(0)">四班</a></li>
										<li><a href="javascript:void(0)">五班</a></li>
									</ul>
								</div>
							</li>
							<li>
								<a href="javascript:void(0)" class="a1">四年级</a>
								<div class="bj_div" >
									<ul>
										<li><a href="javascript:void(0)">一班</a></li>
										<li><a href="javascript:void(0)">二班</a></li>
										<li><a href="javascript:void(0)">三班</a></li>
										<li><a href="javascript:void(0)">四班</a></li>
										<li><a href="javascript:void(0)">五班</a></li>
									</ul>
								</div>
							</li>
							<li>
								<a href="javascript:void(0)" class="a1">五年级</a>
								<div class="bj_div" >
									<ul>
										<li><a href="javascript:void(0)">一班</a></li>
										<li><a href="javascript:void(0)">二班</a></li>
										<li><a href="javascript:void(0)">三班</a></li>
										<li><a href="javascript:void(0)">四班</a></li>
										<li><a href="javascript:void(0)">五班</a></li>
									</ul>
								</div>
							</li>
							<li>
								<a href="javascript:void(0)" class="a1">六年级</a>
								<div class="bj_div" >
									<ul>
										<li><a href="javascript:void(0)">一班</a></li>
										<li><a href="javascript:void(0)">二班</a></li>
										<li><a href="javascript:void(0)">三班</a></li>
										<li><a href="javascript:void(0)">四班</a></li>
										<li><a href="javascript:void(0)">五班</a></li>
									</ul>
								</div>
							</li>
							<li>
								<a href="javascript:void(0)" class="a1">七年级</a>
								<div class="bj_div" >
									<ul>
										<li><a href="javascript:void(0)">一班</a></li>
										<li><a href="javascript:void(0)">二班</a></li>
										<li><a href="javascript:void(0)">三班</a></li>
										<li><a href="javascript:void(0)">四班</a></li>
										<li><a href="javascript:void(0)">五班</a></li>
									</ul>
								</div>
							</li>
							<li>
								<a href="javascript:void(0)" class="a1">八年级</a>
								<div class="bj_div" >
									<ul>
										<li><a href="javascript:void(0)">一班</a></li>
										<li><a href="javascript:void(0)">二班</a></li>
										<li><a href="javascript:void(0)">三班</a></li>
										<li><a href="javascript:void(0)">四班</a></li>
										<li><a href="javascript:void(0)">五班</a></li>
									</ul>
								</div>
							</li>
							<li>
								<a href="javascript:void(0)" class="a1">九年级</a>
								<div class="bj_div" >
									<ul>
										<li><a href="javascript:void(0)">一班</a></li>
										<li><a href="javascript:void(0)">二班</a></li>
										<li><a href="javascript:void(0)">三班</a></li>
										<li><a href="javascript:void(0)">四班</a></li>
										<li><a href="javascript:void(0)">五班</a></li>
									</ul>
								</div>
							</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<div style="text-align:center"><a href="javascript:void(0)" class="btn btn-green">查询</a></div>
			</div>
		</div>
	</div>
</body>
<script>
$(function(){
	$(".name_cx .cx_top .name-cx a").click(function(){
		$(".name_cx .cx_top .name-cx a").removeClass("on");
		$(this).addClass("on");
		var i=$(this).index();
		$(".xx_s1>div").hide();
		$(".xx_s1>div").eq(i).show();
	});
	/* $(".njbj .bj_all>ul>li .a1").hover(function(){
		$(".njbj .bj_all .bj_div").hide();
		$(this).next().show();
	}) */
	$("body").on("click",".njbj .bj_all>ul>li .a1",function(){
		$(".njbj .bj_all>ul>li .a1").removeClass("on");
		$(this).addClass("on");
		$(".njbj .bj_all .bj_div").hide();
		$(this).next().show();
	})
	$("body").on("click",".njbj .bj_all .bj_div ul li a",function(){
		$(".njbj .bj_all .bj_div ul li a").removeClass("hover");
		$(this).addClass("hover");
	})
})
</script>
</html>