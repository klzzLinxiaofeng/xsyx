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
<title>数据初始化</title>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link"><a href="${pageContext.request.contextPath}/school/init/index">数据初始化 ></a>
		<c:choose>
			<c:when test="${type==0 }">
				<span class="s1">教师信息导入</span>
			</c:when>
			<c:when test="${type==1 }">
				<span class="s1">学生与家长信息导入</span>
			</c:when>
			<c:when test="${type==2 }">
				<span class="s1">教师任课安排导入</span>
			</c:when>
			<c:when test="${type==3 }">
				<span class="s1">课程表导入</span>
			</c:when>
		</c:choose>
	</p>
	<div class="content_main white">
		<div class="content_top" style="height: 68px;">
			<div class="f_left">
				<p class="p1" style="line-height: 42px;">${schoolName }</p>
				<p class="p2">
					<span class="s3 f_left">${term.schoolYear }年${term.schoolTermName }
						<fmt:formatDate value="${term.beginDate }" pattern="yyyy年MM月dd日"/>
						- 
						<fmt:formatDate value="${term.finishDate }" pattern="yyyy年MM月dd日"/>
					</span>
					<a class="s2 f_right" style="margin-top: -5px;" href="${pageContext.request.contextPath}/school/schoolYear/index">学年/学期管理</a></p>
			</div>
		</div>
		<div class="sjcsh_top">
			<ul>
				<li class="${type==0?'on':'' }"><i>1</i>教师信息</li>
				<li class="${type==1?'on':'' }"><i>2</i>学生与家长信息</li>
				<li class="${type==2?'on':'' }"><i>3</i>教师任课安排</li>
				<!-- 
				<li class="${type==3?'on':'' }"><i>4</i>课程表</li>
				 -->
			</ul>
		</div>
		<iframe width="100%" frameborder="0" id="iframe_first" style=""></iframe>
	</div>
</div>
<script>
$(function() {
	var index = "${type}";
	var url = "";
	var loader = new loadDialog();
    loader.show();
    
	if(index=="0") {
		url = "${pageContext.request.contextPath}/teacher/init/index";
	} else if(index=="1") {
		url = "${pageContext.request.contextPath}/student/init/index";
	} else if(index=="2") {
        url = "${pageContext.request.contextPath}/team/teacher/init/index";
	} else if(index=="3") {
		
	}
	$("#iframe_first").attr("src", url);
	loader.close();
})
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

$('.sjcsh_top ul li').click(function(){
	$(this).siblings().removeClass('on');
	$(this).addClass('on');
	
	var index = $('.sjcsh_top ul li').index(this);
	var url = "";
	if(index==0) {
		url = "${pageContext.request.contextPath}/teacher/init/index";
	} else if(index==1) {
		url = "${pageContext.request.contextPath}/student/init/index";
	} else if(index==2) {
        url = "${pageContext.request.contextPath}/team/teacher/init/index";
	} else if(index==3) {
		
	}
	$("#iframe_first").attr("src", url);
});
$('#iframe_first').load(function(){   //方法2
    /* var iframeHeight=$(this).contents().height();
    $(this).height(iframeHeight); */
});
</script>
</body>
</html>