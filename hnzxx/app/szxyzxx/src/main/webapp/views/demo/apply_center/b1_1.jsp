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
<title>平台权限设置</title>
<style>
.table thead tr th:first-child{
	padding-left: 33px;
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">应用中心  >  权限管理  > <span>学校权限管理</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>学校权限管理</p></div>
		</div>
		<div class="input_select" style="padding-bottom: 0;">
			<div class="select_div">
				<span>主应用：</span>
				<select class="span2"><option>定邦教育云</option></select>
			</div>
		</div>
		<div class="input_select" style="padding-top: 0;">
			<div class="select_div" style="float:left">
				<span>学校：</span>
				<input type="text">
			</div>
			<div class="btn_link" style="margin:5px 5px 0 0;">
				<button class="btn btn-blue" style="margin-top: 8px;">搜索</button>
			</div>
		</div>
		<div class="input_select" style="border-top: solid 1px #e4e8eb;border-bottom: solid 1px #e4e8eb;line-height: 27px;">
			<span style="color:#666666;">应用列表：</span>
			<button class="btn btn-peaGreen fr" onclick="batchOffTheShelf()">查看角色权限</button>
		</div>
		<table class="table">
			<thead>
				<tr><th>图标</th><th>名称</th><th>状态</th><th>适用角色</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody>
				<tr><td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span></td><td>排课1管理</td><td class="sj">上架</td><td>校管</td><td class="caozuo"><button class="btn btn-green qxsz">权限设置</button></td></tr>
				<tr><td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span></td><td>排课2管理</td><td class="sj">上架</td><td>班主任</td><td class="caozuo"><button class="btn btn-green qxsz">权限设置</button></td></tr>
				<tr><td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span></td><td>排课3管理</td><td class="xj">下架</td><td>校管</td><td class="caozuo"><button class="btn btn-green qxsz">权限设置</button></td></tr>
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

<script>
//权限设置
$('.qxsz').click(function(){
	var title = $(this).parent().siblings("td:nth-child(2)").text();
	 $.initWinOnTopFromLeft_qyjx("权限设置 - "+ title, '${pageContext.request.contextPath}/views/demo/apply_center/b5_0.jsp', '390', '390');
});
</script>
</body>
</html>