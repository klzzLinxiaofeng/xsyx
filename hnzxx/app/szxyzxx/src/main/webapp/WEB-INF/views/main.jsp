<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="framework.generic.im.Constants.EasemobConstants"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
    <%-- 本地注释，线上需要 --%>
<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sca:getDefaultSchoolName()}</title>
<style>
	.top-nav{position:absolute;top:-56px;width:100%}
</style>

</head>
<body id="main_body" style="overflow: hidden">
	<div class="layout">
		<jsp:include page="/views/embedded/manager_head.jsp" />
		<jsp:include page="/views/embedded/manager_left.jsp" />
		<div style="margin-left: 0px;z-index:0;" class="man_right">
			<!-- 		<iframe src="" id="core_iframe" name="core_iframe" frameBorder=0 scrolling=no width="100%" onLoad="iFrameHeight()" ></iframe> -->
			<!-- 			<iframe runat="server" src="http://www.baidu.com" width="100%" height=100% -->
			<!-- 				id="core_iframe" name="core_iframe" onLoad="iFrameHeight()" -->
			<!-- 				frameborder="no" border="0" marginwidth="0" marginheight="0" -->
			<!-- 				scrolling="no" allowtransparency="yes"> </iframe> -->
			<%-- 			<iframe  src="${ctp}/views/embedded/mannager_index.jsp" marginheight="0" marginwidth="0" frameborder="0" scrolling="yes" width="100%" style="" id="core_iframe" name="core_iframe" onLoad=""></iframe> --%>
			<iframe allowFullScreen src="${ctp}/index/mainPage" marginheight="0" marginwidth="0"
				frameborder="0" scrolling="yes" width="100%" style=""
				id="core_iframe" name="core_iframe" onLoad=""></iframe>
		</div>
		<%-- <%@ include file="/views/embedded/manager_footer.jsp"%> --%>
	</div>

	<script type="text/javascript" language="javascript">
		var h = $(parent.window).height();

		$(function() {
			$(window).resize(function() {
				h=$(parent.window).height();
				var h1 = document.documentElement.clientHeight;
				var i=$(".navbar .nav li.hover").index();
				if(i==0){
				$("#core_iframe,.leftbar").css("height", h);
				}else{
					$("#core_iframe,.leftbar").css("height", h1-56);
					var window_w=$(".top-nav").css("width");
// 					var nav_w=parseInt(window_w)-183-parseInt(right_w)-192;
// 					$(".nav-collapse .ul_div").css("width",nav_w);
				}
			});

			$("#core_iframe,.leftbar").css("height", h);

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
					$(".top-nav", window.top.document).css({"position":"absolute","top":"-56px"});
				}
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

	<script type="text/javascript" language="javascript">
// 		var h = $(parent.window).height() - 56;
// 		$(function() {
// 			$("#core_iframe,.leftbar").css("height", h);

// 			$(".navbar .nav > li > a").click(function() {
// 				var i = $(this).parent().index();
// 				if (i != 0) {
// 					$('.left_mu').css("width", "15px");
// 					$('.left_mu').show(50);
// 					$(".leftbar").hide();
// 					$(".man_right").css("margin-left", "0px");
// 					$("#left_close").addClass("left_open");
// 				} else {
// 					$(".left_mu").hide();
// 					$(".leftbar").hide();
// 					$(".man_right").css("margin-left", "0");
// 					$("#left_close").addClass("left_open")
// 				}
// 			})

// 			$(".zg_btn").on("click", ".left_close", function() {
// 				$(".leftbar").fadeOut(100);
// 				$(".left_mu").animate({
// 					width : '15px'
// 				}, 100);
// 				$(".man_right").animate({
// 					marginLeft : '0px'
// 				}, 100);
// 				$(this).addClass("left_open");
// 			});
// 			$(".zg_btn").on("click", ".left_close.left_open", function() {
// 				$(".left_mu").animate({
// 					width : '215px'
// 				}, 100);
// 				$(".man_right").animate({
// 					marginLeft : '200px'
// 				}, 100);
// 				$(".leftbar").fadeIn(100);
// 				$(this).removeClass("left_open");
// 			});
// 		});
	</script>

	<script type="text/javascript">
		if (window != top) {
			$("#main_body").hide();
			$
					.errorDialog(
							'您长时间未操作平台，为确保您安全使用，请重新登录',
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/plugin/onlineCustomer/js/jquery.Sonline.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/plugin/onlineCustomer/js/mySonline.js"></script>
<%@include file="/views/embedded/plugin/easemob_js.jsp"%>
	<script type="text/javascript">
	var conn = null;
	conn = new Easemob.im.Connection();
	$(function(){
         conn.init({
 			onOpened : function(){
//  				var curUserId = conn.context.userId;
				getCount();
 				conn.setPresence();
 			},
 			onTextMessage : function(message){
 				setMessageData(message);
			},
			 https : true
 		});
         conn.open({
                 user : "${sessionScope[sca:currentUserKey()].imAccount}",
                 pwd : "<%=EasemobConstants.DEFAULT_PASSWORD%>",
                 appKey : '<%=EasemobConstants.APP_KEY%>'
         });
	});

	function setMessageData(message){
		var content = message.data;
		if(content > 0){
			$("#count",window.top.document).html("+" + content);
		}else if(content == 0){
			$("#count",window.top.document).html("");
		}
	}
</script>
</html>

