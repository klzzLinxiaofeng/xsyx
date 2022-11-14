<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/plugin/falgun/css/add.css" rel="stylesheet">
<%@ include file="/views/embedded/plugin/zTree.jsp"%>
<style>
.cdlj {
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	word-wrap: normal;
	max-width: 520px;
	display: block;
}
.chzn-container{
	position:relative;
	top:7px;
}
</style>
</head>
<body>
	<!-- <div class="layout">
		<div class="main-wrapper"> -->
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="菜单管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3 class="page-header" style="height: 34px">
							<div class="select_div">
								<span>所属组：</span>
								<select style="padding: 6px; width: 220px;" id="school" name="school" onchange="initZtree(this)">
									<option value="0">系统默认角色组</option>
								</select>
							</div>
						</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="widget-container" id="initTree">
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$.GroupSelector({
			"selector" : "#school"
		});
	});
	function initZtree($this) {
		var $this = $($this);
		
		var groupId = $this.val();
		
		if(groupId != "") {
			$("#initTree").load("${ctp}/sys/gmodule/tree/sub", {"groupId" : groupId}, function() {
				
			});	
		} else {
			$.alert("请选择组");
		}
	}
</script>
</html>