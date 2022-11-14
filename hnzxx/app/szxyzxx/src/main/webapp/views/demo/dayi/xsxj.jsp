<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dxa/dxa.css" rel="stylesheet">
<title>学生小结</title>
<style>
	.pagination{
		margin:20px 0 0 0;
	}
</style>
</head>
<body>
<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
		<jsp:param value="fa-asterisk" name="icon"/>
		<jsp:param value="｛导学案名称｝- 查看学生小结" name="title" />
		<jsp:param value="${param.dm}" name="menuId" />
	</jsp:include>
	<div class="row-fluid ">
		<div class="xsxj">
			<div class="nav">
				<a href="javascript:void(0)" class="on">课前总结</a>
				<a href="javascript:void(0)">自习总结</a>
				<a href="javascript:void(0)">课后总结</a>
			</div>
			<div class="xj_list">
				<div class="xj_div">
					<div class="jszj">
						<p class="title">教师总结</p>
						<p>请写下你的学习感悟和学习后存在的疑问</p>
					</div>
					<div class="ckxq">
						<a href="javascript:void(0)">查看详情</a>
						<p>已提交：<span class="c_green">16人</span>丨未提交：<span class="c_yellow">16人</span></p>
					</div>
					<div class="xszj_list">
						<ul>
							<li>
								<div class="l_left">
									<img src="${pageContext.request.contextPath}/res/images/no_pic.jpg">
									<p class="name">学生一</p>
								</div>
								<div class="l_right">
									<p class="nr">这是一条内容</p>
									<img src="${pageContext.request.contextPath}/res/images/no_pic.jpg">
									<p class="time">2017-05-15 16:40</p>
								</div>
							</li>
							<li>
								<div class="l_left">
									<img src="${pageContext.request.contextPath}/res/images/no_pic.jpg">
									<p class="name">学生二</p>
								</div>
								<div class="l_right">
									<img src="${pageContext.request.contextPath}/res/images/no_pic.jpg">
									<img src="${pageContext.request.contextPath}/res/images/no_pic.jpg">
									<img src="${pageContext.request.contextPath}/res/images/no_pic.jpg">
									<p class="time">2017-05-15 16:40</p>
								</div>
							</li>
							<li>
								<div class="l_left">
									<img src="${pageContext.request.contextPath}/res/images/no_pic.jpg">
									<p class="name">学生三</p>
								</div>
								<div class="l_right">
									<p>这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容</p>
									<p class="time">2017-05-15 16:40</p>
								</div>
							</li>
						</ul>
					</div>
					<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
						<jsp:param name="id" value="aptRuleItem_list_content" />
						<jsp:param name="url" value="/schoolAffair/aptRuleItem/index?sub=list&dm=${param.dm}" />
						<jsp:param name="pageSize" value="${page.pageSize}" />
					</jsp:include>
					<div class="clear"></div>
				</div>
				<div class="xj_div" style="display:none;">
					<div class="jszj">
						<p class="title">教师总结</p>
						<p>请写下你的学习感悟和学习后存在的疑问</p>
					</div>
					<div class="ckxq">
						<a href="javascript:void(0)">查看详情</a>
						<p>已提交：<span class="c_green">16人</span>丨未提交：<span class="c_yellow">16人</span></p>
					</div>
					<div class="xszj_list">
						<ul>
							<li>
								<div class="l_left">
									<img src="${pageContext.request.contextPath}/res/images/no_pic.jpg">
									<p class="name">学生一</p>
								</div>
								<div class="l_right">
									<p class="nr">这是一条内容</p>
									<img src="${pageContext.request.contextPath}/res/images/no_pic.jpg">
									<p class="time">2017-05-15 16:40</p>
								</div>
							</li>
						</ul>
					</div>
					<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
						<jsp:param name="id" value="aptRuleItem_list_content" />
						<jsp:param name="url" value="/schoolAffair/aptRuleItem/index?sub=list&dm=${param.dm}" />
						<jsp:param name="pageSize" value="${page.pageSize}" />
					</jsp:include>
					<div class="clear"></div>
				</div>
				<div class="xj_div" style="display:none;">
					<div class="jszj">
						<p class="title">教师总结</p>
						<p>请写下你的学习感悟和学习后存在的疑问</p>
					</div>
					<div class="ckxq">
						<a href="javascript:void(0)">查看详情</a>
						<p>已提交：<span class="c_green">16人</span>丨未提交：<span class="c_yellow">16人</span></p>
					</div>
					<div class="xszj_list">
						<ul>
							<li>
								<div class="l_left">
									<img src="${pageContext.request.contextPath}/res/images/no_pic.jpg">
									<p class="name">学生三</p>
								</div>
								<div class="l_right">
									<p>这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容这是一条内容</p>
									<p class="time">2017-05-15 16:40</p>
								</div>
							</li>
						</ul>
					</div>
					<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
						<jsp:param name="id" value="aptRuleItem_list_content" />
						<jsp:param name="url" value="/schoolAffair/aptRuleItem/index?sub=list&dm=${param.dm}" />
						<jsp:param name="pageSize" value="${page.pageSize}" />
					</jsp:include>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(function(){
		$(".xsxj .nav a").click(function(){
			$(".xsxj .nav a").removeClass("on");
			$(this).addClass("on");
			var i=$(this).index();
			$(".xj_list .xj_div").hide();
			$(".xj_list .xj_div").eq(i).show();
		})
	})
</script>
</body>
</html>