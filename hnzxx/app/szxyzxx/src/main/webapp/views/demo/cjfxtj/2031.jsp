<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/kwgl.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<title>成绩查看</title>
</head>
<body >
<div class="kwgl">
	<div class="return_kw">
		<p>查看详情 期末考试 考试信息</p>
		<a href="javascript:void(0)" class="btn btn-lightGray">返回</a>
	</div>
	<div class="pd20">
		<div class="kw_select">
			<p class="xnxq fl" style="margin:0">2016-2017学年 春季学期（下学期）</p>
		</div>
		<div class="kwgl_main">
			<div class="ks_list" style="padding:20px;">
				<table class="table table-striped" style="border:1px solid #e4e8eb;">
					<thead><tr><th>年级</th><th>人均总分</th><th class="caozuo">操作</th></tr></thead>
					<tbody>
						<tr><td>一年级</td><td>600</td><td class="caozuo"><button class="btn btn-green">年级统计</button><button class="btn btn-orange">班级统计</button></td></tr>
						<tr><td>二年级</td><td>600</td><td class="caozuo"><button class="btn btn-green">年级统计</button><button class="btn btn-orange">班级统计</button></td></tr>
						<tr><td>三年级</td><td>600</td><td class="caozuo"><button class="btn btn-green">年级统计</button><button class="btn btn-orange">班级统计</button></td></tr>
						<tr><td>四年级</td><td>600</td><td class="caozuo"><button class="btn btn-green">年级统计</button><button class="btn btn-orange">班级统计</button></td></tr>
						<tr><td>五年级</td><td>600</td><td class="caozuo"><button class="btn btn-green">年级统计</button><button class="btn btn-orange">班级统计</button></td></tr>
						<tr><td>六年级</td><td>600</td><td class="caozuo"><button class="btn btn-green">年级统计</button><button class="btn btn-orange">班级统计</button></td></tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="bjtj_tck" style="display:none;">
	<div class="bj_list">
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
		<a href=javascript:void(0)>一年级1班</a>
	</div>
</div>
</body>
<script>



$('.bj_list a').click(function(){
	$(this).addClass('on');
	$(this).siblings().removeClass('on');
});
$(".btn-orange").click(function(){
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['505px', '303px'],
		  title: '班级统计', //不显示标题
		  content: $('.bjtj_tck'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
})
$(function(){
	
	
})
</script>
</html>