<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>学校列表</title>
<style>
</style>
</head>
<body>
	<div class="grxx">
		<div class="grsz">
			<div class="gr_left">
				<div class="xgtx"><a href="javascript:void(0)"><img alt="头像" src="${pageContext.request.contextPath}/res/images/no_pic.jpg"></a><a href="javascript:void(0)" class="xg">修改头像</a></div>
				<ul><li class="active"><a href="javascript:void(0)">个人中心</a></li><li><a href="javascript:void(0)">消息中心</a></li></ul>
			</div>
			<div class="gr_right">
			<iframe id="core_iframe" width="100%"  frameborder="0" onload="iFrameHeight();" name="core_iframe" style="height: 650px;" scrolling="yes" marginwidth="0" marginheight="0" src="grxx_1.jsp"></iframe>
			</div>
			<div class="clear"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$(".grxx .grsz .gr_left .xgtx").hover(function(){
			$(".grxx .grsz .gr_left .xgtx .xg").show();
		},function(){
			$(".grxx .grsz .gr_left .xgtx .xg").hide();
		})
	})
</script>
</html>