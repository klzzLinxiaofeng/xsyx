<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title>教师信息导入</title>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">数据初始化 > <span class="s1">教师信息导入</span></p>
	<div class="content_main white">
		<div class="content_top" style="height: 68px;">
			<div class="f_left">
				<p class="p1" style="line-height: 42px;">罗定邦中学</p>
				<p class="p2"><span class="s3">2018年春季下学期 2018年3月1日 - 2018年7月30日</span><span class="s2 f_right" style="margin-top: -5px;">学年/学期管理</span></p>
			</div>
		</div>
		<div class="sjcsh_top">
			<ul>
				<li class="on" id="one"><i>1</i>教师信息</li>
				<li id="sp_import_manage"><i>2</i>学生与家长信息</li>
				<li id="unfinished"><i>3</i>教师任课安排</li>
				<li><i>4</i>课程表</li>
			</ul>
		</div>
		<iframe src="one.jsp" width="100%" frameborder="0" id="iframe_first"  scrolling="no" onload="setIframeHeight(this)"></iframe>
		
	</div>
</div>
<script>
$('.sjcsh_top ul li').click(function(){
	$(this).siblings().removeClass('on');
	$(this).addClass('on');
	var id = $(this).attr('id');
	$('#iframe_first').attr('src',id+'.jsp');
	//步骤完成 添加Class:pass
	//步骤未完成添加Class:nopass
});
 $('#iframe_first').load(function(){   
    var iframeHeight=$(this).contents().height();
    $(this).height(iframeHeight+'px');
}); 
 window.onload = function () {
	setIframeHeight(document.getElementById('iframe_first'));
};
	function setIframeHeight(iframe) {
		if (iframe) {
		var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
		if (iframeWin.document.body) {
		iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
		}
	}
};
</script>
</body>
</html>