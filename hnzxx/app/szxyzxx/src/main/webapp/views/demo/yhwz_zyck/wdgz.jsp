<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/zyck.css" rel="stylesheet">
<title>个人资源</title>
<style>
.layui-layer-title{background-color:#0d7bd5;color:#fff;}
</style>
</head>
<body style="background-color:#fff">
	<div class="commond_top">
		<ul>
			<li><p class="p1">积分值</p><p class="p2">170</p></li>
			<li><p class="p1">资源数</p><p class="p2">85</p></li>
			<li class="main">
				<div style="width:254px;margin:0 auto;">
					<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
					<div class="nr_right">
						<p class="name">刘小城</p>
						<div class="jfpj">
							<p class="p3">积分评级</p>
							<div class="x1" style="display:none"><p style="width:80%"></p></div>
							<div class="x2" ><p style="width:80%"></p></div>
							<div class="x3" style="display:none"><p style="width:80%"></p></div>
							<div class="x4" style="display:none"><p style="width:80%"></p></div>
						</div>
					</div>
				</div>
			</li>
			<li><p class="p1">浏览量</p><p class="p2">651</p></li>
			<li><p class="p1">下载量</p><p class="p2">56</p></li>
		</ul>
	</div>
	<div style="padding:20px 5px">
		<div class="wdgz_1">
			<p><input type="text"><a href="javascript:void(0)" class="btn btn-primary">搜索</a><a href="javascript:void(0)" class="btn btn-warning">上传资源</a></p>
		</div>
		<div class="wdgz_2">
			<a href="javascript:void(0)">我收藏的资源</a>
			<a href="javascript:void(0)">我上传的资源</a>
			<a href="javascript:void(0)">我的共享</a>
			<a href="javascript:void(0)" class="on">我的关注</a>
		</div>
		<div class="wdgz_3">
			<ul>
				<li>
					<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
					<div class="nr_right">
						<p class="name">刘小城</p>
						<p class="p1"><span>学校：</span>罗定邦中学</p>
						<p class="p1"><span>资源数：</span>45</p>
						<div class="x1" ><p style="width:80%"></p></div>
						<div class="x2" style="display:none"><p style="width:80%"></p></div>
						<div class="x3" style="display:none"><p style="width:80%"></p></div>
						<div class="x4" style="display:none"><p style="width:80%"></p></div>
					</div>
				</li>
				<li>
					<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
					<div class="nr_right">
						<p class="name">刘小城</p>
						<p class="p1"><span>学校：</span>罗定邦中学</p>
						<p class="p1"><span>资源数：</span>45</p>
						<div class="x1" style="display:none"><p style="width:25%"></p></div>
						<div class="x2" ><p style="width:70%"></p></div>
						<div class="x3" style="display:none"><p style="width:80%"></p></div>
						<div class="x4" style="display:none"><p style="width:80%"></p></div>
					</div>
				</li>
				<li>
					<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
					<div class="nr_right">
						<p class="name">刘小城</p>
						<p class="p1"><span>学校：</span>罗定邦中学</p>
						<p class="p1"><span>资源数：</span>45</p>
						<div class="x1" style="display:none"><p style="width:50%"></p></div>
						<div class="x2" style="display:none"><p style="width:80%"></p></div>
						<div class="x3" ><p style="width:40%"></p></div>
						<div class="x4" style="display:none"><p style="width:80%"></p></div>
					</div>
				</li>
				<li>
					<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
					<div class="nr_right">
						<p class="name">刘小城</p>
						<p class="p1"><span>学校：</span>罗定邦中学</p>
						<p class="p1"><span>资源数：</span>45</p>
						<div class="x1" style="display:none"><p style="width:50%"></p></div>
						<div class="x2" style="display:none"><p style="width:80%"></p></div>
						<div class="x3" style="display:none"><p style="width:80%"></p></div>
						<div class="x4" ><p style="width:30%"></p></div>
					</div>
				</li>
			</ul>
			<div class="clear"></div>
		</div>
	</div>
	<div class="qrxz" style="display:none">
		<p class="p1">所需积分:<span>3</span>积分</p>
		<p class="p2">您有:150积分</p>
		<button class="btn">确定下载</button>
	</div>
</body>
<script>
$(function(){
//     点击下载 弹出层
	$(".xz").click(function(){
		layer.open({
			  type: 1,
			  shade: false,
			  area: ['380px', '230px'],
			  title: '巧妙区分“的地得”的正确用法的正确用法的正确用法的正确用法', //不显示标题
			  content: $('.qrxz'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
			  cancel: function(){
			    layer.close();
			  },
		});
	})
})
</script>
</html>