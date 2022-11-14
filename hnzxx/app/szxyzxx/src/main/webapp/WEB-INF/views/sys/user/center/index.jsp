<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
<title>学校列表</title>
<style>
</style>
</head>
<body>
	<div class="grxx">
		<div class="grsz">
			<div class="gr_left">
				<div class="xgtx">
					<a href="javascript:void(0)"> <img alt="头像" id="img_tag" class="zp_modify_btn" src="<avatar:avatar userId='${sessionScope[sca:currentUserKey()].id}'></avatar:avatar>">
					</a> <a href="javascript:void(0)" id="zp_modify_btn" class="xg zp_modify_btn">修改头像</a>
					<input type="hidden" id="zp_uuid" value="">
				</div>
				<ul>
					<li class="active" data-url="${pageContext.request.contextPath}/user/center/profile/editor"><a href="javascript:void(0)">个人中心</a></li>
					<li id="password_editor" data-url="${pageContext.request.contextPath}/user/center/password/editor"><a href="javascript:void(0)">修改密码</a></li>
<%-- 					<li id="message" data-url="${pageContext.request.contextPath}/message/center/index"><a href="javascript:void(0)">消息中心</a></li> --%>
				</ul>
			</div>
			<div class="gr_right">
				<iframe id="user_center_iframe" width="100%" frameborder="0" onload="" name="user_center_iframe" style="height: 580px;" scrolling="yes" marginwidth="0" marginheight="0"
					src="${pageContext.request.contextPath}/user/center/profile/editor"></iframe>
					<div class="close_page"><a href="javascript:void(0)" onclick="closeWindow();"></a></div>
					<a id="closeBtn"></a>
			</div>
			<div class="clear"></div>
		</div>
		
	</div>
</body>
<script type="text/javascript">
	$(function() {
		var newPage = "${param.newPage}";
		if(newPage == "true"){
			$("#message").attr("data-url","${pageContext.request.contextPath}/message/center/index?newPage=true");
		}
		var teaMes = "${param.teaMes}";
		if(teaMes == "true"){
			$("#message").attr("data-url","${pageContext.request.contextPath}/message/center/index?newPage=true&teaMes=true");
		}
		
		$(".grxx .grsz .gr_left .xgtx").hover(function() {
			$(".grxx .grsz .gr_left .xgtx .xg").show();
		}, function() {
			$(".grxx .grsz .gr_left .xgtx .xg").hide();
		});

		$.createAvartarEditor({
			"btn" : ".zp_modify_btn"
		});
		
		$(".grxx .grsz .gr_left ul li").on("click", function() {
			var $this = $(this);			
			$("#user_center_iframe").attr("src", $this.attr("data-url"));
			$(".grxx .grsz .gr_left ul li").removeClass("active");
			$this.addClass("active");
		});
		
		var module = "${param.module}";
		
		if(module != null && module !==""){
			if ("message_center" === module) {
				$("#message").click();
			} else if ("password_editor" === module) {
				$("#password_editor").click();
			}
			
		}
		
	})

	function selectedImgHandler(data) {
		$("#img_tag").attr("src", data.imgUrl);
		var $requestData = {};
		$requestData.icon = data.uuid;
		$requestData.userId = "${sessionScope[sca:currentUserKey()].id}";
		$requestData.userName = "${sessionScope[sca:currentUserKey()].userName}";
		var url = "${pageContext.request.contextPath}/user/center/profile/editor";
		$.post(url, $requestData, function(data, status) {
			if ("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if ("success" === data.info) {
					$.success("操作成功");
					$.closeWindowByName(data.windowName);
				} else {
					$.error('操作失败');
				}
			} else {
				$.error('操作失败');
			}
		});
	}
	
	function closeWindow() {
		$.confirm("确定关闭吗？", function() {
			$.closeWindow();	
		}, function() {});
		
	}
</script>
</html>