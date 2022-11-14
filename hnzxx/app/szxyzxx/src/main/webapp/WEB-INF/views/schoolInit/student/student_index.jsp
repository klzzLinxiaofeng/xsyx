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
		<a href="javascript:void(0)" class="on" id="student_import">学生与家长导入</a>
		<a href="javascript:void(0)" id="student_parents_manage">学生与家长管理</a>
		<a href="javascript:void(0)" id="student_parents_export">学生信息导出</a>
	</div>
	<iframe src="${pageContext.request.contextPath}/student/init/import" width="100%" frameborder="0" id="iframe_sencond" name="iframe_sencond" style="height:820px;"></iframe>
</body>
<script>
window.onload = function () {
	setIframeHeight(document.getElementById("iframe_sencond"));
    var iframeHeight=820;
    $("#iframe_first",window.parent.parent.document).height((iframeHeight+81)+'px');
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
//	var obj = parent.document.getElementById("iframe_first");  //取得父页面IFrame对象
//	 var _height=this.document.body.scrollHeight;
//	 obj.height = _height;  //调整父页面中IFrame的高度为此页面的高度
//	 $("#iframe_first",window.parent.document).height(_height+171)
//}

$(".sjcsh_xx a").click(function() {
	$(".sjcsh_xx a").removeClass("on");
	$(this).addClass("on");
	var t=setTimeout("setIframeHeight(document.getElementById('iframe_sencond'))",1000);
//	IFrameResize();
	var index = $(".sjcsh_xx a").index(this);
	
	var url = "";
	if(index==0) {
		url = "${pageContext.request.contextPath}/student/init/import";
	} else if(index==1) {
		url = "${pageContext.request.contextPath}/student/init/list";
	} else if(index==2){
		url = "${pageContext.request.contextPath}/student/init/export";
	}
	$("#iframe_sencond").attr("src", url);
})
</script>
</html>