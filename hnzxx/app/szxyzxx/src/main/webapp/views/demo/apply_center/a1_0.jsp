<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title>平台应用管理</title>
<style>
.table thead tr th:first-child{
	padding-left: 33px;
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">应用中心  >  应用管理  > <span>平台应用管理</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>平台应用管理</p></div>
			<div class="f_right"><button class="btn btn-blue"><i class="fa fa-plus"></i>应用注册</button></div>
		</div>
		<div class="input_select">
			<div class="select_div">
				<span>主应用：</span>
				<select class="span2"><option>定邦教育云</option></select>
			</div>
			<div class="btn_link" style="margin:5px 5px 0 0">
				<button class="btn btn-orange">桌面配置</button>
			</div>
		</div>
		<table class="table">
			<thead>
				<tr><th>图标</th><th>名称</th><th>KEY</th><th>状态</th><th>注册时间</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody>
				<tr><td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span></td><td>排课管理</td><td>ASFGASFS</td><td class="sj">上架</td><td>2018-11-11</td><td class="caozuo"><button class="btn btn-blue">编辑</button><button class="btn btn-green">权限设置</button><button class="btn btn-peaGreen">管理</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span></td><td>排课管理</td><td>ASFGASFS</td><td class="sj">上架</td><td>2018-11-11</td><td class="caozuo"><button class="btn btn-blue">编辑</button><button class="btn btn-green">权限设置</button><button class="btn btn-peaGreen">管理</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span></td><td>排课管理</td><td>ASFGASFS</td><td class="xj">下架</td><td>2018-11-11</td><td class="caozuo"><button class="btn btn-blue">编辑</button><button class="btn btn-green">权限设置</button><button class="btn btn-peaGreen">管理</button><button class="btn btn-red">删除</button></td></tr>
			</tbody>
		</table>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="studentAid_list_content" />
				<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
</div>
<div class="scts" style="display:none;text-align: center;padding-top:20px;">
	<p>您确定删除“<span style="color:#2299ee">排课管理</span>”小应用吗？</p>
	<p>一旦确定删除全平台以及学校的应用列表中无该小应用</p>
	<p>并且无法正常使用</p>
	<p style="color:#ff5252">请谨慎操作。</p>
</div>

<script>
$(".btn-red").click(function(){
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['390px', '230px'],
		  title: '提示', //不显示标题
		  content: $('.scts'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定删除','取消'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
})
</script>
</body>
</html>