<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title></title>
</head>
<body style="background-color: #e3e3e3;"">
	<div class="sjcsh_xx_detail njkmgl">
	<div class=" content_main">
		<div class="kmlb">
	 		<div class='f_left'>
	 			<p style="color: #666666;font-size:18px;font-weight:bold">年级科目管理</p>
	 		</div>
	 		<div class="f_right">
	 			<button class="btn btn-green" >刷新列表</button>
	 			<button class="btn btn-blue" >批量添加年级科目</button>
	 		</div>
	 		<div class="clear"></div>
	 	</div>
	 	<div style="padding:15px 20px">
	 		<div class='f_left'>
	 			<ul class="grade">
	 				<li href="javascript:void(0)">一年级</li>
	 				<li href="javascript:void(0)">二年级</li>
	 				<li href="javascript:void(0)">三年级</li>
	 				<li href="javascript:void(0)">四年级</li>
	 			</ul>
	 		</div>
	 		<div class="clear"></div>
	 	</div>
	 	<div style="margin:0 20px;">
		<table class="table"  id="sort" style="border: 1px solid #dddddd;">
			<thead>
				<tr><th class="index">序号</th><th>科目名称</th><th>课程范围</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody>
				<tr><td class="index">1</td><td>语文</td><td>公共课程</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td class="index">2</td><td>数学</td><td>课程0</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td class="index">3</td><td>英语</td><td>课程1</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td class="index">4</td><td>物理</td><td>课程2</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td class="index">5</td><td>毛泽东思想</td><td>课程3</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td class="index">6</td><td>邓小平理论</td><td>公共课程</td><td class="caozuo"><button class="btn btn-green" onclick="editTeacher()">编辑</button><button class="btn btn-red">删除</button></td></tr>
			</tbody>
		</table>
		</div>
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
$('.grade li').click(function(){
	$(this).siblings().removeClass('on');
	$(this).addClass('on');
});
</script>
</body>
</html>