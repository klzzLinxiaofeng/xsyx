<%@page import="platform.education.resource.contants.ResourceType"%>
<%@page import="platform.education.commonResource.web.common.contants.SysContants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/bannercommon.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/extra/microcourse_style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/extra/microcourse_index.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/extra/zyck.css" rel="stylesheet">
<title>我的资源</title>
<style>
.course-box {
	float: left;
}
</style>
</head>
<body style="padding:0 15px">
	<input id="self" value="${sessionScope[sca:currentUserKey()].userId}" type="hidden">
	<div style="text-align:right;padding: 10px 5px 5px;">
		<c:if test="${!fav && (!empty userId && userId!=0)}">
			<button class="btn btn-primary" onclick="fav(${userId}, 1)"><i class="fa fa-plus"></i>关注他</button>
		</c:if>
		<c:if test="${fav && (!empty userId && userId!=0)}">
			<button class="btn btn-primary" onclick="fav(${userId}, 0)"><i class="fa fa-minus"></i>取消关注</button>
		</c:if>
		<c:if test="${!empty userId && userId!=0 }">
			<button class="btn btn-warning" onclick="sendMessage(${userId});">留言</button>
		</c:if>
	</div>
	<jsp:include page="./common/owner_head.jsp">
		<jsp:param value="${userId}" name="userId" />
	</jsp:include>
	<div style="padding:20px 5px" id="owner_list" class="zy_list">
		<jsp:include page="./owner_list.jsp" />
	</div>
	<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
		<jsp:param name="id" value="owner_list" />
		<jsp:param name="url" value="/resource/owner/list?index=list&ownerId=${userId}" />
		<jsp:param name="pageSize" value="${page.pageSize }" />
	</jsp:include>
	<div class="clear"></div>
</body>
<script type="text/javascript">
function fav(userId, fav) {
	$.ajax({
        url: "${pageContext.request.contextPath}/resource/user/fav",
        type: "POST",
        data: {"favUserId":userId, "fav":fav},
        async: false,
        success: function(result) {
        	if(fav==1) {
        		if(result==1) {
            		$.success("关注成功");
            	} else {
            		$.alert("不能重复关注");
            	}
        	} else {
        		if(result==3) {
            		$.success("取消关注成功");
            	} else {
            		$.alert("取消关注失败");
            	}
        	}
        	window.location.reload(true);
        }
    });
}

function sendMessage(userId) {
	var self = $("#self").val();
	if(self==userId) {
		$.alert("不能给自己留言");
		return;
	}
	var specialName = "${douFn:getFieldVal('teacher', userId)}";
	console.log(specialName);
	//弹窗 发送消息
	$.initWinOnTopFromLeft('发送消息','/message/center/send?type=1&userId='+userId, '620','590');
}
</script>
</html>