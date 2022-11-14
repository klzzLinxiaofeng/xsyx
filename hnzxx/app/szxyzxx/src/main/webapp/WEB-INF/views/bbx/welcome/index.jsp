<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/bbx/bbx.css">
<link rel="Stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/plugin/jPicker/css/jPicker-1.1.6.min.css" />
<link rel="Stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/plugin/jPicker/jPicker.css" />
<script
	src="${pageContext.request.contextPath}/res/plugin/jPicker/jpicker-1.1.6.js"
	type="text/javascript"></script>
<title>欢迎词</title>
<style>
#Binded {
	text-indent: -9999px;
}
 .xzmb {
    position: absolute;
    top: 283px;
    z-index:1;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="widget-head">
						<div class="select_top">
							<div class="s1 s1_bg">
								<select id="teamId" onchange="search();"></select>
							</div>
						</div>
					</div>
					<div class="xzmb" style="">
						<button class="btn btn-blue" onclick="loadCreatePage();">选择模板</button>
					</div>
					<div class="content-widgets white" id="welcome_list_content">
<%-- 						 <jsp:include page="./list.jsp" />  --%>
					    
					</div>
					
				</div>
			</div>
		</div>
	</div>


</body>
<script>
	$.BbxRoleTeamAccountSelector({
		"selector" : "#teamId",
		"condition" : {roleType : "CLASS_MASTER"},
		"selectedVal" : "",
		"afterHandler" : function() {
			search();
		}
	});
	
	
	function search() {
		
		var val = {};

		var teamId = $("#teamId").val();
		

		if (teamId != null && teamId != "") {
			val.teamId = teamId;
		}

		var id = "welcome_list_content";
		var url = "/bbx/welcome/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
$(function(){
	/* 选择模板的位置 */
	var w = $("body").width() / 2 + 200;
	$(".xzmb").css("left", w);
	$(window).resize(function() {
		var w = $("body").width() / 2 + 200;
		$(".xzmb").css("left", w);
	});
})
	
</script>
</html>