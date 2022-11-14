<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>通讯录</title>
<script type="text/javascript">
	$(function(){
		$(".txl .select_peo ul li a").click(function(){
			$(".txl .select_peo ul li a").parent().removeClass("on");
			$(this).parent().addClass("on");
			var i=$(this).parent().index();
			$(".txl_list ul").hide();
			$(".txl_list ul").eq(i).show();
		})
	})
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="通讯录" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" class="on">通讯录</a></li>
				        </ul>
					</div>
					<div class="txl">
						<div class="select_peo">
							<ul>
								<li class="on">
									<a href="javascript:void(0)">全部同事</a>
								</li>
								<li>
									<a href="javascript:void(0)">校办方</a>
								</li>
								<li>
									<a href="javascript:void(0)">教务处</a>
								</li>
								<li>
									<a href="javascript:void(0)">学生处</a>
								</li>
								<li>
									<a href="javascript:void(0)">财务处</a>
								</li>
							</ul>
							<div class="search_ts">
								<input type="text" placeholder="搜索同事"/>
								<a href="javascript:void(0)"><i class="fa fa-search"></i></a>
							</div>
						</div>
						<div class="pailie">
							<p>共<span class="red">5</span>人</p>
							<div class="zm">
								<a href="javascript:void(0)">a</a>
								<a href="javascript:void(0)">b</a>
								<a href="javascript:void(0)">c</a>
								<a href="javascript:void(0)">d</a>
								<a href="javascript:void(0)">e</a>
								<a href="javascript:void(0)">f</a>
								<a href="javascript:void(0)">g</a>
								<a href="javascript:void(0)">h</a>
								<a href="javascript:void(0)">i</a>
								<a href="javascript:void(0)">j</a>
								<a href="javascript:void(0)">k</a>
								<a href="javascript:void(0)">l</a>
								<a href="javascript:void(0)">m</a>
								<a href="javascript:void(0)">n</a>
								<a href="javascript:void(0)">o</a>
								<a href="javascript:void(0)">p</a>
								<a href="javascript:void(0)">q</a>
								<a href="javascript:void(0)">r</a>
								<a href="javascript:void(0)">s</a>
								<a href="javascript:void(0)">t</a>
								<a href="javascript:void(0)">u</a>
								<a href="javascript:void(0)">v</a>
								<a href="javascript:void(0)">w</a>
								<a href="javascript:void(0)">x</a>
								<a href="javascript:void(0)">y</a>
								<a href="javascript:void(0)">z</a>
								<a href="javascript:void(0)" class="all">所有</a>
							</div>
						</div>
						<div class="txl_list">
							<ul>
								<li>
									<img alt="头像" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									<p class="name">于慧娟</p>
									<p class="bm">校长办/校长</p>
									<p class="p1">电子邮件：631570902@qq.com</p>
									<p class="p1">办公电话：未设置</p>
									<p class="p1">手机：13800138000</p>
									<p class="p1">QQ：未设置</p>
									<p class="p1">微信：未设置</p>
								</li>
								<li>
									<img alt="头像" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									<p class="name">于慧娟</p>
									<p class="bm">校长办/校长</p>
									<p class="p1">电子邮件：631570902@qq.com</p>
									<p class="p1">办公电话：未设置</p>
									<p class="p1">手机：13800138000</p>
									<p class="p1">QQ：未设置</p>
									<p class="p1">微信：未设置</p>
								</li>
								<li>
									<img alt="头像" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									<p class="name">于慧娟</p>
									<p class="bm">校长办/校长</p>
									<p class="p1">电子邮件：631570902@qq.com</p>
									<p class="p1">办公电话：未设置</p>
									<p class="p1">手机：13800138000</p>
									<p class="p1">QQ：未设置</p>
									<p class="p1">微信：未设置</p>
								</li>
								<li>
									<img alt="头像" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									<p class="name">于慧娟</p>
									<p class="bm">校长办/校长</p>
									<p class="p1">电子邮件：631570902@qq.com</p>
									<p class="p1">办公电话：未设置</p>
									<p class="p1">手机：13800138000</p>
									<p class="p1">QQ：未设置</p>
									<p class="p1">微信：未设置</p>
								</li>
								<li>
									<img alt="头像" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									<p class="name">于慧娟</p>
									<p class="bm">校长办/校长</p>
									<p class="p1">电子邮件：631570902@qq.com</p>
									<p class="p1">办公电话：未设置</p>
									<p class="p1">手机：13800138000</p>
									<p class="p1">QQ：未设置</p>
									<p class="p1">微信：未设置</p>
								</li>
							</ul>
							<ul style="display:none">
								<li>
									<img alt="头像" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									<p class="name">于慧娟1</p>
									<p class="bm">校长办/校长</p>
									<p class="p1">电子邮件：631570902@qq.com</p>
									<p class="p1">办公电话：未设置</p>
									<p class="p1">手机：13800138000</p>
									<p class="p1">QQ：未设置</p>
									<p class="p1">微信：未设置</p>
								</li>
							</ul>
							<ul style="display:none">
								<li>
									<img alt="头像" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									<p class="name">于慧娟2</p>
									<p class="bm">校长办/校长</p>
									<p class="p1">电子邮件：631570902@qq.com</p>
									<p class="p1">办公电话：未设置</p>
									<p class="p1">手机：13800138000</p>
									<p class="p1">QQ：未设置</p>
									<p class="p1">微信：未设置</p>
								</li>
							</ul>
							<ul style="display:none">
								<li>
									<img alt="头像" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									<p class="name">于慧娟3</p>
									<p class="bm">校长办/校长</p>
									<p class="p1">电子邮件：631570902@qq.com</p>
									<p class="p1">办公电话：未设置</p>
									<p class="p1">手机：13800138000</p>
									<p class="p1">QQ：未设置</p>
									<p class="p1">微信：未设置</p>
								</li>
							</ul>
							<ul style="display:none">
								<li>
									<img alt="头像" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									<p class="name">于慧娟4</p>
									<p class="bm">校长办/校长</p>
									<p class="p1">电子邮件：631570902@qq.com</p>
									<p class="p1">办公电话：未设置</p>
									<p class="p1">手机：13800138000</p>
									<p class="p1">QQ：未设置</p>
									<p class="p1">微信：未设置</p>
								</li>
							</ul>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>