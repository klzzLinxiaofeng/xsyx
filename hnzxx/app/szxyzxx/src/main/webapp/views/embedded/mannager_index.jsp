<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/slider.js"></script>
</head>
<body onload="showLeftTime()">
	
    <div class="container-fluid">
       <jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-home" name="icon"/>
			<jsp:param value="欢迎页" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>欢迎页</h3>
					</div>
					<div class="widget-container" style="padding:10px 15px;">
						<div class="bn_div">
							<div id="banner" class="pic1">
								<ul id="pic" class="pic">
									<li><img src="${pageContext.request.contextPath}/res/images/banner_1.jpg"></li>
									<li><img src="${pageContext.request.contextPath}/res/images/banner_2.gif"></li>
									<li><img src="${pageContext.request.contextPath}/res/images/banner_3.jpg"></li>
								</ul>
							</div>
							<ul class="points" id="banner_btn">
								<li class="current" num="0">1</li>
								<li class="" num="1">2</li>
								<li class="" num="2">3</li>
							</ul>
						</div>
					</div>
					<div class="welcome">
						<p>欢迎使用中小学数字校园管理平台！
<!-- 							<button onclick="menu();">点我，点我！</button> -->
						</p>
						<p>当前时间是：<span id="show">显示时间的位置</span></p>
					</div>
				</div>
			</div>
		</div>
	</div>
<script language="javascript" type="text/javascript">

// 	function menu() {
// 		var dataMenuId = "XI_TONG_GUAN_LI"; 
// 		var lefMenuId = "YONG_HU_GUAN_LI";
// 		$("#main_head_menus ul li[data-menu-id='" + dataMenuId + "']", window.top.document).click();
// 		var leftHead = $("#left_head_" + dataMenuId, window.top.document);
// 		leftHead.find("ul[data-parentid='" + dataMenuId + "']>li:eq(1) a:first>i:first").click()
// 		alert(leftHead.find("ul li:eq(1) a:first").attr("ui-sref"));
// 	}

<!--
//获得当前时间,刻度为一千分一秒
var initializationTime=(new Date()).getTime();
function showLeftTime()
{
var now=new Date();
var year=now.getFullYear();
var month=now.getMonth()+1;
var day=now.getDate();
var hours=now.getHours();
var minutes=now.getMinutes();
var seconds=now.getSeconds();
document.all.show.innerHTML=""+year+"年"+month+"月"+day+"日 "+hours+":"+minutes+":"+seconds+"";
//一秒刷新一次显示时间
var timeID=setTimeout(showLeftTime,1000);
}
//-->
	
	

</script>
    <script>
    var objs = document.getElementById("banner_btn").getElementsByTagName("li");
    var tv = new TransformView("banner", "pic", 900, objs.length, {
    onStart: function () {
    Each(objs, function (o, i) {
    o.className = tv.Index === i ? "current" : "";
    });
    }, //按钮样式
    Up: false
    });
    tv.Start();
    Each(objs, function (o, i) {
    o.onmouseover = function () {
    o.className = "current";
    tv.Auto = false;
    tv.Index = i;
    tv.Start();
    };
    o.onmouseout = function () {
    o.className = "";
    tv.Auto = true;
    tv.Start();
    };
    }); 
    </script>
</body>
</html>