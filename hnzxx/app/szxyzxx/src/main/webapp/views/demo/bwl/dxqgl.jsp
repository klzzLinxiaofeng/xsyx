<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>数校云下载</title>
<script>
	$(function(){
		$(".download_btn").click(function(){
			$_url=$(this).attr("href");
			if($_url==""){
				$.alert("没有可下载的数校云助手，请联系管理员")
			}
		})
	})
</script>
</head>
<body style="background-color:#f3f3f3 !important">
	<div class="sxy_download">
		<div class="d_left">
			<div class="title"><p>数校云助手</p></div>
			<p class="demail font-14">导出学生档案请下载数校云助手，安装登录软件进入学生档案模块即可导出。</p>
			<p class="demail">支持系统：WinXP/Win2003/Vista/Win7/Win8</p>
			<a class="download_btn" href="javascript:void(0)"></a>
		</div>
	</div>
</body>
</html>