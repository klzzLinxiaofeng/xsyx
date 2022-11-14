<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/dmgl.css" rel="stylesheet">
<title>基础代码管理系统</title>
<style>
</style>
</head>
<body style="background-color:#fff">
<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
		<jsp:param value="fa-asterisk" name="icon"/>
		<jsp:param value="代码管理" name="title" />
		<jsp:param value="${param.dm}" name="menuId" />
	</jsp:include>
	<div class="dmgl">
		<div class="dm_left">
			<div class="dm_course">
				<div class="title_top">
					<p class="p1">代码规范</p>
					<p class="btn-right">
						<a href="javasript:void(0)"><i class="fa fa-plus"></i></a>
						<a href="javasript:void(0)">管理</a>
					</p>
				</div>
				<div class="dm_all">
					<select><option>项目管理</option></select>
				</div>
			</div>
			<div class="dm_course">
				<div class="title_top">
					<p class="p1">代码表</p>
					<p class="btn-right">
						<a href="javasript:void(0)"><i class="fa fa-plus"></i></a>
						<a href="javasript:void(0)">管理</a>
					</p>
				</div>
				<div class="dm_all dmb">
					<a href="javascript:void(0)">A</a>
					<a href="javascript:void(0)">B</a>
					<a href="javascript:void(0)">C</a>
				</div>
			</div>
		</div>
		<div class="dm_right">
			<div class="dm_course">
				<div class="title_top">
					<p class="p1">代码表</p>
					<p class="btn-right">
						<a href="javasript:void(0)"><i class="fa fa-plus"></i></a>
						<a href="javasript:void(0)">编辑</a>
						<a href="javasript:void(0)" class="turn_up">上移</a>
						<a href="javasript:void(0)" class="turn_down">下移</a>
					</p>
				</div>
				<div class="dm_all dm_table white">
					<table class="table ">
						<thead>
							<tr><th>次序</th><th>名称</th><th>代码</th></tr>
						</thead>
						<tbody>
							<tr><td>1</td><td>1</td><td>1</td></tr>
							<tr><td>2</td><td>2</td><td>2</td></tr>
							<tr><td>3</td><td>3</td><td>3</td></tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
</body>
<script>
$(function() { 
    //上移 
    var $up = $(".turn_up");
    $up.click(function() { 
        var $tr = $(".blue_1"); 
        if ($tr.index() != 0) { 
            $tr.fadeOut().fadeIn(); 
            $tr.prev().before($tr); 
 
        } 
    }); 
    //下移 
    var $down = $(".turn_down"); 
    var len = $(".dm_all table tr").length; 
    $down.click(function() { 
        var $tr = $(".blue_1");
        if ($tr.index() != len - 1) { 
            $tr.fadeOut().fadeIn(); 
            $tr.next().after($tr); 
        } 
    }); 
});
</script>
</html>