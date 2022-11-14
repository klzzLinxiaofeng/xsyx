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
<title>新建校首页</title>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link"><span class="s1">数据初始化</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p class="p1">罗定邦中学<span class="s1">学校数据暂未导入，请根据指引完成数据导入</span></p></div>
		</div>
		<div class="begin"> 
			<a href="javascript:void(0)">
				<p class="p1"><span></span></p>
				<p class="p2">开始</p>
			</a>
		</div>
		<div class="czlc">
			<div class="czlc_content">
			<span class="f_left">操作流程：</span>
			<ul>
				<li>
					<!-- 未建立学年学期  -->
					<span class="wjl_ts">
						<span class="s1">学期学年未建立</span>
						<button class="btn btn-orange"><i>1</i>学期学年</button>
					</span>
					<!-- 已建立学年学期 -->
					<!--  <button class="btn btn-green"><i>1</i>学期学年</button> -->
					<i class="i1"></i>
				</li>
				<li>
					<button class="btn btn-lightGray"><i>2</i>教师信息导入</button>
					<a href="javascript:void(0)" class="a2">教师导入模板.xls</a> 
					<i class="i1"></i>
				</li>
				<li>
					<button class="btn btn-lightGray"><i>3</i>学生与家长信息</button>
					<a href="javascript:void(0)" class="a2">学生与家长导入模板.xls</a> 
					<i class="i1"></i>
				</li>
				<li>
					<button class="btn btn-lightGray"><i>4</i>教师任课安排</button>
					<a href="javascript:void(0)" class="a2">任课安排导入模板.xls</a> 
					<i class="i1"></i>
				</li>
				<li>
					<button class="btn btn-lightGray"><i>5</i>课程表</button>
					<a href="javascript:void(0)" class="a2">课程表导入模板.xls</a> 
				</li>
			</ul>
			<div class="clear"></div>
			</div>
			
		</div>
	</div>
</div>

<script>
</script>
</body>
</html>