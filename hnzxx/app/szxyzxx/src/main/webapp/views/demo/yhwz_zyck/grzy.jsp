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
							<div class="x1" ><p style="width:80%"></p></div>
							<div class="x2" style="display:none"><p style="width:80%"></p></div>
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
	<div style="padding:20px 5px" class="zy_list">
		<table class="table table-striped" style="border:1px solid #e4e8eb; ">
			<thead><tr><th style="width:52%">他的上传</th><th style="width:12%">浏览量</th><th style="width:12%">下载量</th><th style="width:12%">积分值</th><th style="width:12%">上传时间</th></tr></thead>
			<tbody>
				<tr><td><div class="wztt"><i class="lx_word"></i><p>《朗读者》第二期陪伴，是人性光辉迸发的奇是人性光辉迸发的奇《朗读者》第二期陪伴，是人性光辉迸发的奇是人性光辉迸发的奇《朗读者》第二期陪伴，是人性光辉迸发的奇是人性光辉迸发的奇</p></div></td><td>7418</td><td>76</td><td>2</td><td>2017-02-27</td></tr>
				<tr><td><div class="wztt"><i class="lx_rar"></i><p>《朗读者》第二期陪伴，是人性光辉迸发的奇是人性光辉迸发的奇</p></p></div></td><td>7418</td><td>76</td><td>2</td><td>2017-02-27</td></tr>
				<tr><td><div class="wztt"><i class="lx_excel"></i><p>《朗读者》第二期陪伴，是人性光辉迸发的奇是人性光辉迸发的奇</p></div></td><td>7418</td><td>76</td><td>2</td><td>2017-02-27</td></tr>
				<tr><td><div class="wztt"><i class="lx_ppt"></i><p>《朗读者》第二期陪伴，是人性光辉迸发的奇是人性光辉迸发的奇</p></div></td><td>7418</td><td>76</td><td>2</td><td>2017-02-27</td></tr>
				<tr><td><div class="wztt"><i class="lx_flash"></i><p>《朗读者》第二期陪伴，是人性光辉迸发的奇是人性光辉迸发的奇</p></div></td><td>7418</td><td>76</td><td>2</td><td>2017-02-27</td></tr>
				<tr><td><div class="wztt"><i class="lx_video"></i><p>《朗读者》第二期陪伴，是人性光辉迸发的奇是人性光辉迸发的奇</p></div></td><td>7418</td><td>76</td><td>2</td><td>2017-02-27</td></tr>
				<tr><td><div class="wztt"><i class="lx_img"></i><p>《朗读者》第二期陪伴，是人性光辉迸发的奇是人性光辉迸发的奇</p></div></td><td>7418</td><td>76</td><td>2</td><td>2017-02-27</td></tr>
			</tbody>
		</table>
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