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
	<input type="hidden" id="code" value="${dm }"/>
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
		var parentId = $("#code").val();
		$ul=$(".navigation[data-parentid=" + parentId + "]", window.top.document);
		var i=$ul.children("li").length;
		for(var k=0;k<i;k++){
			var code=$ul.children("li").eq(k).attr("data-menu-id");
			var name=$ul.children("li").eq(k).children().children("span").text();
			var desc=$ul.children("li").eq(k).attr("data-desc");
			$(".second_main ul").append("<li id="+k+" class="+code+"><a href='javascript:void(0)' onclick=\"menu('" + parentId + "','" + code +"');\"><span class='intro'><span class='span1'>"+name+"</span><span class='span2'>"+desc+"</span></span></a></li>")
			$("#"+k).hide();
			$("#"+k).fadeIn(1000);
		}
	});
	
	function menu(dataMenuId, leftMenuId) {
		$("#main_head_menus ul li[data-menu-id='" + dataMenuId + "']", window.top.document).click(); //顶部菜单的单击
		var leftHead = $("#left_head_" + dataMenuId, window.top.document);  //第一层(左侧菜单div)
		var data_url = leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "']").attr("data-url");
		leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "'] a:first>i:first").click();
		if(data_url == null || data_url == "") {
			var leftFirst = leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "']");
			leftFirst.find("ul li:eq(0) a:first>i:first").click();
			data_url = leftFirst.find("ul li:eq(0)").attr("data-url");
			if(data_url == null || data_url == "") {
				var leftSecond = leftFirst.find("ul li:eq(0)");
				leftSecond.find("ul li:eq(0) a:first>i:first").click();
				data_url = leftSecond.find("ul li:eq(0)").attr("data-url");
			}		
		}
		$('.left_mu', window.top.document).css("width","215px");
		$('.left_mu', window.top.document).show();
		$(".leftbar", window.top.document).show();
		$(".man_right", window.top.document).css("margin-left","200px");
		$("#left_close", window.top.document).removeClass("left_open");
	}
</script>
</html>
