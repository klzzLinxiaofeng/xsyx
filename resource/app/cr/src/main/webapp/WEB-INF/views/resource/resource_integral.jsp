<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/zyck.css" rel="stylesheet">
<title>个人资源</title>
	<style>
	.layui-layer-title{
		background-color:#0d7bd5;
		color:#fff;
	}
	</style>
</head>
<body>
<div class="qrxz">
	<c:choose>
		<c:when test="${owner eq 1}">
			<p class="p1">该资源为您上传的，下载不需要消耗积分</p>
		</c:when>
		<c:when test="${downloaded eq 1 }">
			<p class="p1">您已经下载过本资源，下载不需要消耗积分</p>
		</c:when>
		<c:otherwise>
			<p class="p1">所需积分:<span>${integral}</span>积分</p>
		</c:otherwise>
	</c:choose>
	<p class="p2">您有:${resFn:getIntegralCount(userId) }积分</p>
	<button class="btn" onclick="downloadResource(${resId});">确定下载</button>
</div>
</body>
<script>
function downloadResource(resId) {
	$.ajax({
        url: "${pageContext.request.contextPath}/resource/integral/operation",
        type: "POST",
        data: {"resId": resId, "type":"download"},
        async: true,
        success: function(data) {
        	if(data=="1") {
        		//downloadSuccess(resId);
        		window.location.href="${pageContext.request.contextPath}/resource/download?Id="+resId;
        		setTimeout("$.closeWindow()", 1000)
        		//window.open("${pageContext.request.contextPath}/resource/download?Id="+resId, "");
            	
        	} else {
        		$.alert("您的积分不足");
        	}
        }
   });
}

function downloadSuccess(resId) {
	var url = "${pageContext.request.contextPath}/resource/download?Id="+resId;
	$.ajax({
        url: url,
        type: "get",
        data: {},
        async: true,
        success: function(data) {
        	$.closeWindow();
        }
   });
}
</script>
</html>