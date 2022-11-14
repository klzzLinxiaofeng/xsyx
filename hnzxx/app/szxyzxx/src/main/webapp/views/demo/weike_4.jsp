<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>微课学习</title>
<script>
	$(function(){
		$(".xs_wk .xs_left .tea").hover(function(){
			$(this).children(".a1").hide();
			$(this).children(".a1").next().show();
		},function(){
			$(this).children(".a1").show();
			$(this).children(".a1").next().hide();
		});
	});
</script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="微课学习" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="xs_wk white">
				<div class="xs_left">
					<p class="km sx">数学</p>
					<div class="tea">
						<div class="a1">
							<p class="p1"><span>0</span>/1</p>
							<p class="p2">结束时间：<span>02-04 23:00</span></p>
						</div>
						<div class="a2" style="display:none">
							<p class="p1"><img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"></p>
							<p class="p2"><span>罗志明</span>老师</p>
						</div>
					</div>
					<a href="javascript:void(0)" class="start_study">开始学习</a>
				</div>
				<div class="xs_right">
					<ul>
						<li>
							<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
							<p class="de_1">学习完毕</p>
							<p class="de_2">关于分数乘法的学习微课</p>
							<p class="de_3">数学  &gt; 初一</p>
						</li>
						<li>
							<img src="${pageContext.request.contextPath}/res/images/400.jpg">
							<p class="de_1">学习进度: 03:33</p>
							<p class="de_2">关于分数乘法的学习微课</p>
							<p class="de_3">数学  &gt; 初一</p>
						</li>
						<li>
							<img src="${pageContext.request.contextPath}/res/images/500.jpg">
							<p class="de_1"></p>
							<p class="de_2">关于分数乘法的学习微课</p>
							<p class="de_3">数学  &gt; 初一</p>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
			</div>
			<div class="xs_wk white">
				<div class="xs_left">
					<p class="km yw">语文</p>
					<div class="tea">
						<div class="a1">
							<p class="p1"><span>0</span>/1</p>
							<p class="p2">结束时间：<span>02-04 23:00</span></p>
						</div>
						<div class="a2" style="display:none">
							<p class="p1"><img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"></p>
							<p class="p2"><span>罗志明</span>老师</p>
						</div>
					</div>
					<a href="javascript:void(0)" class="start_study">开始学习</a>
				</div>
				<div class="xs_right">
					<ul>
						<li>
							<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
							<p class="de_1">学习完毕</p>
							<p class="de_2">关于分数乘法的学习微课</p>
							<p class="de_3">数学  &gt; 初一</p>
						</li>
						<li>
							<img src="${pageContext.request.contextPath}/res/images/400.jpg">
							<p class="de_1">学习进度: 03:33</p>
							<p class="de_2">关于分数乘法的学习微课</p>
							<p class="de_3">数学  &gt; 初一</p>
						</li>
						<li>
							<img src="${pageContext.request.contextPath}/res/images/500.jpg">
							<p class="de_1"></p>
							<p class="de_2">关于分数乘法的学习微课</p>
							<p class="de_3">数学  &gt; 初一</p>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
</script>
</html>