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
<body style="background-color: #e3e3e3;" onload="IFrameResize()">
	<div class="sjcsh_xx">
		<a href="javascript:void(0)" class="on" id="student_parents_import">学生与家长导入</a>
		<a href="javascript:void(0)" id="student_parents_manage">学生与家长管理</a>
	</div>
	<iframe src="3_0_0_index.jsp" width="100%" frameborder="0" id="iframe_sencond"  scrolling="no"></iframe>
<script>
$('.sjcsh_xx a').click(function(){
	$(this).siblings().removeClass('on');
	$(this).addClass('on');
	var id = $(this).attr('id');
	$('#iframe_sencond').attr('src',id+'.jsp');
	var t=setTimeout("IFrameResize()",1000);
	
});
$('#iframe_sencond').load(function(){   
     var iframeHeight=$(this).contents().height();
     $(this).height(iframeHeight+'px');
});
function IFrameResize(){  
	  
	 var obj = parent.document.getElementById("iframe_first");  //取得父页面IFrame对象  
	 obj.height = this.document.body.scrollHeight;  //调整父页面中IFrame的高度为此页面的高度  
}  

</script>
</body>
</html>