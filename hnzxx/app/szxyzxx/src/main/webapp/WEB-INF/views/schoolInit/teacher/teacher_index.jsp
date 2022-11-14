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
<title></title>
</head>
<body style="background-color: #e3e3e3;" >
	<div class="sjcsh_xx">
		<a href="javascript:void(0)" class="on" id="teacher_import">教师信息导入</a>
		<a href="javascript:void(0)" id="teacher_info_manage">教师信息管理</a>
	</div>
	<iframe src="${pageContext.request.contextPath}/teacher/init/import" width="100%" frameborder="0" id="iframe_sencond" name="iframe_sencond" style="height:780px;"></iframe>
</body>
<script type="text/javascript">

window.onload = function () {
	setIframeHeight(document.getElementById("iframe_sencond"));
    var iframeHeight=780;
    $("#iframe_first",window.parent.parent.document).height((iframeHeight+71)+'px');
};
function setIframeHeight(iframe) {
	if (iframe) {
		var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
		if (iframeWin.document.body) {
			iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
		}
	}
};
//function IFrameResize(){
//
//	 var obj = parent.document.getElementById("iframe_first");  //取得父页面IFrame对象
//	 var _height=this.document.body.scrollHeight;
//	 obj.height = _height;  //调整父页面中IFrame的高度为此页面的高度
//	 $("#iframe_first",window.parent.document).height(_height+121)
//}

$(".sjcsh_xx a").click(function() {
	$(".sjcsh_xx a").removeClass("on");
	$(this).addClass("on");
	var t=setTimeout("setIframeHeight(document.getElementById('iframe_sencond'))",1000);
//	IFrameResize();
	//var tt=setTimeout("IFrameResize()",1000);
	var index = $(".sjcsh_xx a").index(this);
	
	var url = "";
	if(index==0) {
		url = "${pageContext.request.contextPath}/teacher/init/import";
	} else if(index==1) {
		url = "${pageContext.request.contextPath}/teacher/init/list";
	}
	$("#iframe_sencond").attr("src", url);
})
</script>
</html>