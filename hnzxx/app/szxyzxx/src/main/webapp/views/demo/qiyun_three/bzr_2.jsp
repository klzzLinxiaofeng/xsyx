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
<title>班主任</title>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">首页  >  班主任评价  > 评价记录  ><span>查看评语</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>查看评语</p></div>
			<div class="f_right"><button class="btn btn-lightGray">返回</button></div>
		</div>
		<table class="table">
			<thead>
				<tr><th>序号</th><th>真实姓名</th><th>发表时间</th><th>管理能力</th><th>班级记录</th><th>责任心</th><th>评语内容</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>振兴</td>
					<td>2017-12-23 16:00</td>
					<td><div class="pf_yuan"><p style="width:20%;" class="black_face"></p></div></td>
					<td><div class="pf_yuan"><p style="width:60%;"></p></div></td>
					<td><div class="pf_yuan"><p style="width:80%;"></p></div></td>
					<td><div class="py_length">老师辛苦了，老师真辛苦老师辛苦了，老师真辛苦1</div></td>
					<td class="caozuo"><button class="btn btn-green">查看评语</button></td>
				</tr>
				<tr>
					<td>2</td>
					<td>振兴</td>
					<td>2017-12-23 16:00</td>
					<td><div class="pf_yuan"><p style="width:40%;"></p></div></td>
					<td><div class="pf_yuan"><p style="width:40%;"></p></div></td>
					<td><div class="pf_yuan"><p style="width:40%;"></p></div></td>
					<td><div class="py_length">老师辛苦了，老师真辛苦老师辛苦了，老师真辛苦2</div></td>
					<td class="caozuo"><button class="btn btn-green">查看评语</button></td>
				</tr>
				<tr>
					<td>3</td>
					<td>振兴</td>
					<td>2017-12-23 16:00</td>
					<td><div class="pf_yuan"><p style="width:40%;"></p></div></td>
					<td><div class="pf_yuan"><p style="width:40%;"></p></div></td>
					<td><div class="pf_yuan"><p style="width:40%;"></p></div></td>
					<td><div class="py_length">老师辛苦了，老师真辛苦老师辛苦了，老师真辛苦3</div></td>
					<td class="caozuo"><button class="btn btn-green">查看评语</button></td>
				</tr>
				<tr>
					<td>4</td>
					<td>振兴</td>
					<td>2017-12-23 16:00</td>
					<td><div class="pf_yuan"><p style="width:40%;"></p></div></td>
					<td><div class="pf_yuan"><p style="width:40%;"></p></div></td>
					<td><div class="pf_yuan"><p style="width:40%;"></p></div></td>
					<td><div class="py_length">老师辛苦了，老师真辛苦老师辛苦了，老师真师辛苦了，老师真辛苦老师辛苦了，老师真辛苦4</div></td>
					<td class="caozuo"><button class="btn btn-green">查看评语</button></td>
				</tr>
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
	$(function(){
		$(".caozuo .btn-green").click(function(){
			var py_text=$(this).parent().prev().children().text();
			layer.open({
				  title: '查看评语内容'
				  ,content: py_text
				});
		})
	})
</script>
</body>
</html>