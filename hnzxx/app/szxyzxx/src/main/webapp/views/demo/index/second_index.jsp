<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/index.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>       
<title>二级页面</title>
</head>
<body>
	<div class="second_main">
		<ul>
			<!-- <li class="KE_REN_JIAO_SHI_GUAN_LI">
				<a href="javascript:void(0)">
					<span class="intro">
						<span class="span1">人事管理</span>
						<span class="span2">简单方便地布置微课任务，学生在线学习，轻松有效。</span>
						<b></b>
					</span>
				</a>
			</li> -->
		</ul>
		<div class="clear"></div>
	</div>
</body>
<script>
	$(function(){
		$ul=$(".navigation[data-parentid='XI_TONG_GUAN_LI']", window.top.document);
		var i=$ul.children("li").length;
		for(var k=0;k<i;k++){
			var code=$ul.children("li").eq(k).attr("data-menu-id");
			var name=$ul.children("li").eq(k).children().children("span").text();
			var desc=$ul.children("li").eq(k).attr("data-desc");
			$(".second_main ul").append("<li class="+code+"><a href='javascript:void(0)'><span class='intro'><span class='span1'>"+name+"</span><span class='span2'>"+desc+"</span></span></a></li>")
		}
	});
</script>
</html>
