<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通知中心</title>
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/extra/message.css">
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"> --%>
<!-- </script> -->
<style type="text/css">
	.pagination{
		float:none;
	}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/plugin/layer/layer.js"></script>
<script type="text/javascript">
		$(function() {
			var newOld = "old";
			if('${voList[0].newPageNum == 1}'){
				$("#url_sel").attr("value","/message/center/index?sub=list&dm=${param.dm}&newPage=true");
			}
			
			$(".grxx .grsz .gr_left .xgtx").hover(function() {
				$(".grxx .grsz .gr_left .xgtx .xg").show();
			}, function() {
				$(".grxx .grsz .gr_left .xgtx .xg").hide();
			});
			$(".grxx .grsz .gr_left ul li").on("click", function() {
				var $this = $(this);			
				
				$(".grxx .grsz .gr_left ul li").removeClass("active");
				$this.addClass("active");
			});
			
			//查看消息，更新为已读
			$(".x-list .text p a").click(function(){
				var newOrOldPage = $(this).data("new");
				if(newOrOldPage == "new"){
					$("#url_sel").attr("value","/message/center/index?sub=list&dm=${param.dm}&newPage=true");
				}
				var _num = $(this).index();
				var id =  $(this).parent().parent().parent().find("#id").val();
				var code = $(this).parent().find("#code").val();
				var l=$(".navbar .nav li", window.top.document).length;
				var leftMenuId = null;
				var dataMenuId = null;
				var parentMenuId = null;
				var length = 0;
				for(var k=0;k<l-1;k++){
					var li_html=$(".panel-body", window.top.document).eq(k).children().children();
					var l1=li_html.length;
					for(var m=0;m<l1;m++){
						var ul_html = li_html.eq(m).html();
						var menu_id=li_html.eq(m).attr("data-menu-id");
						if(ul_html.indexOf(code) > 0){
							dataMenuId = $('.navbar .nav li', window.top.document).eq(k+1).attr("data-menu-id");
							length = ul_html.split("ul").length-1;
							leftMenuId = li_html.eq(m).attr("data-menu-id");
							parentMenuId = li_html.eq(m).find("li[data-menu-id='" + code + "']").parent().attr("data-parentid");
						}
						if(menu_id === code){
							dataMenuId = $('.navbar .nav li', window.top.document).eq(k+1).attr("data-menu-id");
							length = ul_html.split("ul").length-1;
							leftMenuId = li_html.eq(m).attr("data-menu-id");
							parentMenuId = li_html.eq(m).find("li[data-menu-id='" + code + "']").parent().attr("data-parentid");
						}
					}
				}
				var url = "${pageContext.request.contextPath}/message/center/updateToReaded";
				$.post(url, {"messageId" : id}, function(data,status){
					if(status === "success"){
						$("#message", window.top.document).css("top","0px");
						if(length == 2){
							$("#main_head_menus ul li[data-menu-id='" + dataMenuId + "']", window.top.document).click(); //顶部菜单的单击
							var leftHead = $("#left_head_" + dataMenuId, window.top.document);  //第一层(左侧菜单div)
							var data_url = leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "']").attr("data-url");
							leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "'] a:first>i:first").click();
							if(data_url == null || data_url == "") {
								var leftFirst = leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "']");
								leftFirst.find("ul li[data-menu-id='" + code + "'] a:first>i:first").click();
							}
							$('.left_mu', window.top.document).css("width","215px");
							$('.left_mu', window.top.document).show();
							$(".leftbar", window.top.document).show();
							if(newOrOldPage == "old"){
								$(".man_right", window.top.document).css("margin-left","200px");
							}else {
								$(".man_right", window.top.document).css("margin-left","0px");
							}
							$("#left_close", window.top.document).removeClass("left_open");
							$(".top-nav", window.top.document).css({"position":"relative","top":"0px"});
						}else if(length == 3){
							$("#main_head_menus ul li[data-menu-id='" + dataMenuId + "']", window.top.document).click(); //顶部菜单的单击
							var leftHead = $("#left_head_" + dataMenuId, window.top.document);  //第一层(左侧菜单div)
							var data_url = leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "']").attr("data-url");
							leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "'] a:first>i:first").click();
							if(data_url == null || data_url == "") {
								var leftFirst = leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "']");
								leftFirst.find("ul li[data-menu-id='" + parentMenuId + "'] a:first>i:first").click();
								data_url = leftFirst.find("ul li[data-menu-id='" + parentMenuId + "']").attr("data-url");
								if(data_url == null || data_url == "") {
									var leftSecond = leftFirst.find("ul li:eq(0)");
									leftSecond.find("ul li[data-menu-id='" + code + "'] a:first>i:first").click();
									data_url = leftSecond.find("ul li:eq(0)").attr("data-url");
								}		
							}
							$('.left_mu', window.top.document).css("width","215px");
							$('.left_mu', window.top.document).show();
							$(".leftbar", window.top.document).show();
							if(newOrOldPage == "old"){
								$(".man_right", window.top.document).css("margin-left","200px");
							}else {
								$(".man_right", window.top.document).css("margin-left","0px");
							}
							$("#left_close", window.top.document).removeClass("left_open");
							$(".top-nav", window.top.document).css({"position":"relative","top":"0px"});
						}else{
							$("#main_head_menus ul li[data-menu-id='" + dataMenuId + "']", window.top.document).click(); //顶部菜单的单击
							var leftHead = $("#left_head_" + dataMenuId, window.top.document);  //第一层(左侧菜单div)
							var data_url = leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "']").attr("data-url");
							leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "'] a:first>i:first").click();
							if(data_url == null || data_url == "") {
								var leftFirst = leftHead.find("ul[data-parentid='" + dataMenuId + "']>li[data-menu-id='" + leftMenuId + "']");
								leftFirst.find("ul li[data-menu-id='" + parentMenuId + "'] a:first>i:first").click();
							}
							$('.left_mu', window.top.document).css("width","215px");
							$('.left_mu', window.top.document).show();
							$(".leftbar", window.top.document).show();
							if(newOrOldPage == "old"){
								$(".man_right", window.top.document).css("margin-left","200px");
							}else {
								$(".man_right", window.top.document).css("margin-left","0px");
							}
							$("#left_close", window.top.document).removeClass("left_open");
							$(".top-nav", window.top.document).css({"position":"relative","top":"0px"});
						}
						var url = "${pageContext.request.contextPath}/message/center/getCount"
						$.post(url,function(data,status){
							if(status == "success"){
								if(data != 0){
									$("#count",window.top.document).html("+" + data);
								}else{
									$("#count",window.top.document).html("");
								}
								$.closeWindowOnParent();
							}
						});
					}
				});
			});				
			//弹窗 发送消息
			$('.send-btn').on('click', function () {
				$.initWinOnTopFromLeft('发送消息','${pageContext.request.contextPath}/message/center/send', '620','590');
			});
		});
		//标记为已读
		function setReaded(id){
			var url = "${pageContext.request.contextPath}/message/center/setReaded"
			$.post(url,{"id":id},function(data,status){
				if("success" === data){
// 					$.success("设置成功");
					$("." + id).hide();
					getCount();
				}else{
					$.error("系统繁忙，请稍后再发送");
				}
			});
		}	
		function getCount(){
			var url = "${pageContext.request.contextPath}/message/center/getCount"
			$.post(url,function(data,status){
				if(status == "success"){
					if(data != 0){
						$("#count",window.top.document).html("+" + data);
					}else{
						$("#count",window.top.document).html("");
					}
				}
			});
		}
		//弹窗 老师消息回复
		function reply(id){
			setReaded(id);
			$.initWinOnTopFromLeft('回复消息','${pageContext.request.contextPath}/message/center/to?id=' + id, '520','390');
		}
		</script>
</head>
<body style="overflow:hidden" id="message_list_content">
	<div class="grxx" style="height:620px;background-color:#fff;">
		<div class="grsz">
			<div class="gr_right" style="min-height:520px;">
				<div class="x-tab clearfix">
					<a href="javascript:void(0);" class="on" id="sysMes">系统消息</a>
					<c:if test="${empty isStudent}">
						<a href="javascript:void(0);" id="teaMes" class="">教师消息</a>
					</c:if>
					<span class="fr send-new send-btn" style="display:none"><i></i>发送消息</span>
				</div>
				<table>
					<tbody id="message_list_data">
						<jsp:include page="list.jsp" />
					</tbody>
				</table>
				<c:choose>
					<c:when test='${voList[0].newPageNum == 1}'><div id="page_div" style="text-align:center">
					<jsp:include page="myJqpagination.jsp" flush="true">
						<jsp:param name="id" value="message_list_data" />
						<jsp:param name="url" value="/message/center/index?sub=list&dm=${param.dm}&newPage=true" />
						<jsp:param name="pageSize" value="${page.pageSize}" />
					</jsp:include>
				</div></c:when>
					<c:otherwise><div id="page_div" style="text-align:center">
					<jsp:include page="myJqpagination.jsp" flush="true">
						<jsp:param name="id" value="message_list_data" />
						<jsp:param name="url" value="/message/center/index?sub=list&dm=${param.dm}" />
						<jsp:param name="pageSize" value="${page.pageSize}" />
					</jsp:include>
				</div></c:otherwise>
				</c:choose>
				<%-- <div id="page_div" style="text-align:center">
					<jsp:include page="myJqpagination.jsp" flush="true">
						<jsp:param name="id" value="message_list_data" />
						<jsp:param name="url" value="/message/center/index?sub=list&dm=${param.dm}" />
						<jsp:param name="pageSize" value="${page.pageSize}" />
					</jsp:include>
				</div> --%>
			</div>
		</div>
	</div>
</body>
</html>