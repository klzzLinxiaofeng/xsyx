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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
<title></title>
</head>
<body style="background-color: #ffffff;">
<div class="tis_edit tta_edit">
	<div>
		<span><i style="font-weight: normal;color:red">*</i>年级班级：</span>
		<select class="span2" disabled="disabled"><option>年级</option></select>
		<select class="span2" disabled="disabled"><option>班级</option></select>
		
		<span><i style="font-weight: normal;color:red">*</i>科目：</span>
		<select class="span2" disabled="disabled"><option>语文</option></select>
	</div>
	<div class="bjrkjs">
		<div>
			<span style=" width: auto;margin-left: 20px;">姓名：</span>
			<input type="text">
			<button class="btn btn-blue" style="margin-top: -11px;">搜索</button>
		</div>
		<div class="f_right">
			<span style="margin-right: 20px; width: auto;margin-bottom: 5px;">搜索结果（8个结果）</span>
		</div>
		<div class="bjrkjs_table">
		<table class="table">
			<thead>
				<tr><th>序号</th><th>姓名</th><th>别名</th><th>性别</th><th>联系方式</th><th>在职状态</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody>
				<tr><td>1</td><td>张三</td><td></td><td>男</td><td>16757837676</td><td>无</td><td class="caozuo"><button class="btn btn-green" >确定</button></td></tr>
				<tr><td>2</td><td>王五</td><td></td><td>女</td><td>16757837676</td><td>无</td><td class="caozuo"><button class="btn btn-green" >确定</button></td></tr>
				<tr><td>3</td><td>六六六</td><td></td><td>男</td><td>16757837676</td><td>无</td><td class="caozuo"><button class="btn btn-green" >确定</button></td></tr>
				<tr><td>4</td><td>李丽丽</td><td></td><td>女</td><td>16757837676</td><td>无</td><td class="caozuo"><button class="btn btn-green" >确定</button></td></tr>
				<tr><td>5</td><td>王依依</td><td></td><td>女</td><td>16757837676</td><td>无</td><td class="caozuo"><button class="btn btn-green" >确定</button></td></tr>
				<tr><td>6</td><td>刘云</td><td></td><td>女</td><td>16757837676</td><td>无</td><td class="caozuo"><button class="btn btn-green" >确定</button></td></tr>
				<tr><td>1</td><td>张三</td><td></td><td>男</td><td>16757837676</td><td>无</td><td class="caozuo"><button class="btn btn-green" >确定</button></td></tr>
				<tr><td>2</td><td>王五</td><td></td><td>女</td><td>16757837676</td><td>无</td><td class="caozuo"><button class="btn btn-green" >确定</button></td></tr>
				<tr><td>3</td><td>六六六</td><td></td><td>男</td><td>16757837676</td><td>无</td><td class="caozuo"><button class="btn btn-green" >确定</button></td></tr>
				<tr><td>4</td><td>李丽丽</td><td></td><td>女</td><td>16757837676</td><td>无</td><td class="caozuo"><button class="btn btn-green" >确定</button></td></tr>
				<tr><td>5</td><td>王依依</td><td></td><td>女</td><td>16757837676</td><td>无</td><td class="caozuo"><button class="btn btn-green" >确定</button></td></tr>
				<tr><td>6</td><td>刘云</td><td></td><td>女</td><td>16757837676</td><td>无</td><td class="caozuo"><button class="btn btn-green" >确定</button></td></tr>
			</tbody>
		</table>
		</div>
	</div>
	<div style="text-align: center; margin-top: 20px;"><button class="btn btn-blue gb"  onclick="$.closeWindow()">关闭</button></div>
	
</div>
<script>
$(".bjrkjs_table").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
</script>
</body>
</html>