<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/cropper_js.jsp"%>
<title>Insert title here</title>
</head>
<body>
    <div class="avatar-view2" title="编辑图片">
      <img src="${pageContext.request.contextPath}/res/images/no_picture.jpg" alt="Avatar">
    </div>
</body>
<script>
$(function(){
	var requestData = {};
	requestData.selector = ".avatar-view2";
	$.createCropperAvatar(requestData);
});
function $avatarCropCallback(data){
	$(".avatar-view2 img").attr("src",data.url);
}
</script>
</html>