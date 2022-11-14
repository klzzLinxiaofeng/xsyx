<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sca:getDefaultSchoolName()}</title>
<style>
/* 	.top-nav{position:absolute;top:-56px;} */
</style>
</head>
<body id="main_body" style="overflow: hidden">
	<div class="layout">
		<jsp:include page="/views/embedded/manager_head.jsp">
			<jsp:param name="module" value="bbx"></jsp:param>
		</jsp:include>
		<jsp:include page="/views/embedded/bbx_manager_left.jsp" />
		<div style="margin-left: 200px;z-index:0;" class="man_right">
			<!-- 		<iframe src="" id="core_iframe" name="core_iframe" frameBorder=0 scrolling=no width="100%" onLoad="iFrameHeight()" ></iframe> -->
			<!-- 			<iframe runat="server" src="http://www.baidu.com" width="100%" height=100% -->
			<!-- 				id="core_iframe" name="core_iframe" onLoad="iFrameHeight()" -->
			<!-- 				frameborder="no" border="0" marginwidth="0" marginheight="0" -->
			<!-- 				scrolling="no" allowtransparency="yes"> </iframe> -->
			<%-- 			<iframe  src="${ctp}/views/embedded/mannager_index.jsp" marginheight="0" marginwidth="0" frameborder="0" scrolling="yes" width="100%" style="" id="core_iframe" name="core_iframe" onLoad=""></iframe> --%>
			<iframe src="" marginheight="0" marginwidth="0"
				frameborder="0" scrolling="yes" width="100%" style=""
				id="core_iframe" name="core_iframe" onLoad=""></iframe>
		</div>
		<%-- <%@ include file="/views/embedded/manager_footer.jsp"%> --%>
	</div>
	
	<script type="text/javascript" language="javascript">
		var h = $(parent.window).height();
		$(function() {
			$("#navigation > li").eq(0).children("a").click(); 
			var d_li=$(".top-nav .nav > li").length; 
			$(".top-nav .nav > li").removeClass("hover")
			for(var i=0;i<d_li;i++){
				var id=$(".top-nav .nav > li").eq(i).attr("data-menu-id");
				if(id=='BAN_BAN_XING'){
					$(".top-nav .nav > li").eq(i).addClass("hover")
				}
			}
			$(window).resize(function() {
				h=$(parent.window).height();
				var h1 = document.documentElement.clientHeight;
				var i=$(".navbar .nav li.hover").index();
				if(i==0){
					$("#core_iframe,.leftbar").css("height", h1-56);
				}else{
					$("#core_iframe,.leftbar").css("height", h1-56);
					var window_w=$(".top-nav").css("width");
					var nav_w=parseInt(window_w)-183-parseInt(right_w)-192;
					$(".nav-collapse .ul_div").css("width",nav_w);
				}
			});
			$("#core_iframe,.leftbar").css("height", h-56);
			$(".navbar .nav > li > a").click(function() {
				var i = $(this).parent().index();
				if (i != 0) {
					$("#core_iframe,.leftbar").css("height", h-56);
					/* $('.left_mu').hide();
					$(".leftbar").show();
					$('.left_mu').show(1000);
					$(".man_right").css("margin-left","200px");
					$("#left_close").removeClass("left_open"); */
					// 				$('.left_mu').css("width","15px");
					$(".left_mu").show().animate({
						width : '215px'
					}, 200);
					$(".leftbar").show();
					$(".man_right").css("margin-left", "200px");
					$("#left_close").removeClass("left_open");
				} else {
					$("#core_iframe,.leftbar").css("height", h);
					$(".left_mu").hide();
					$(".leftbar").hide();
					// 				$('.left_mu').show(2000);
					$(".man_right").css("margin-left", "0");
					$("#left_close").addClass("left_open");
					$(".top-nav").css({"position":"absolute","top":"-56px"});
					$(".top-nav", window.top.document).css({"position":"absolute","top":"-56px"});
				}
				var parentId=$(this).parent().data('menu-id');
 				$("#left_head_" + parentId).children("#navigation").children("li").eq(0).children("a").click();

			})

			$(".zg_btn").on("click", ".left_close", function() {
				/* $(".left_mu").animate({
				    width: '-=200'
				  }, 1000); */
				$(".leftbar").fadeOut(200);
				/* $(".left_mu").css("width","15px"); */
				$(".left_mu").animate({
					width : '15px'
				}, 200);
				$(".man_right").animate({
					marginLeft : '0px'
				}, 200);
				/* $(".man_right").css("margin-left","0"); */
				$(this).addClass("left_open");
			});

			$(".zg_btn").on("click", ".left_close.left_open", function() {
				/* $(".left_mu").css("width","215px");
				$(".leftbar").css("width","200px");
				$(".man_right").css("margin-left","200px");
				$(this).removeClass("left_open"); */

				$(".left_mu").animate({
					width : '215px'
				}, 200);
				/* $(".man_right").css("margin-left","200px"); */
				$(".man_right").animate({
					marginLeft : '200px'
				}, 200);
				$(".leftbar").fadeIn(200);
				$(this).removeClass("left_open");
			});
		});
	</script>
	

	<script type="text/javascript">
	    $(parent.window).close();
		if (window != top) {
			$("#main_body").hide();
			$.errorDialog('您长时间未操作平台，为确保您安全使用，请重新登录',
			function() {
				top.location.href = "${pageContext.request.contextPath}/logout";
			},
			function(index) {
				top.location.href = "${pageContext.request.contextPath}/logout";
			});
		}
	</script>
</body>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/onlineCustomer/style/default_blue.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/onlineCustomer/js/jquery.Sonline.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/onlineCustomer/js/mySonline.js"></script>
</html>

