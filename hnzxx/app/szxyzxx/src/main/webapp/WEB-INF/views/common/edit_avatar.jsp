<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>头像编辑</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="数字校园">
<meta http-equiv="description" content="This is my page">
<jsp:include page="/views/embedded/common.jsp"></jsp:include>
<jsp:include page="/views/embedded/plugin/avatar.jsp"></jsp:include>

</head>
<body>
	<div style="width:700px;height:450px; padding:10px;">
		<div id="altContent">
			<h1>AvatarUpload</h1>
			<p><a href="http://www.adobe.com/go/getflashplayer">Get Adobe Flash player</a></p>
		</div>
	</div>
</body>

<script type="text/javascript">
		var flashvars = {
				js_handler:"jsfun",
				swfID:"avatarEdit",
				picSize:"5242880",
				sourceAvatar:"${ctp}/res/images/no_pic.jpg",
				avatarLabel:"头像预览，请注意清晰度",
// 				sourceLabel:"保存你的原图吧",
// 				sourcePicAPI:"http://asv5.sinaapp.com/widget/upload.php",
				avatarAPI:"${pageContext.request.contextPath}/common/portrait/uploader",
// 				avatarSize:"180,180|90,90|35,35",
// 				avatarSizeLabel:"180x180|90x90|35x35"

// 				avatarSize:"148,208|74,104|37,52",
//  				avatarSizeLabel:"148x208|74x104|37x52"
				avatarSize : "${param.avatarSize}",
				avatarSizeLabel : "${param.avatarSizeLabel}"
			};
			var params = {
				menu: "false",
				scale: "noScale",
				allowFullscreen: "true",
				allowScriptAccess: "always",
				bgcolor: "",
				wmode: "transparent" // can cause issues with FP settings & webcam
			};
			var attributes = {
				id:"AvatarUpload"
			};
			swfobject.embedSWF(
				"${ctp}/res/js/common/plugin/avatar/avatarUpload.swf", 
				"altContent", "100%", "100%", "10.0.0", 
				"${pageContex.request.contextPath}/res/js/common/plugin/avatar/expressInstall.swf", 
				flashvars, params, attributes);
				
			function jsfun(obj)
			{
// 				if(obj.type == "sourcePicSuccess") alert("原图上传成功");
// 				if(obj.type == "sourcePicError") alert("原图上传失败");
				if(obj.type == "init") {
					return;
				}
				if(obj.type == "avatarSuccess") {
// 					$.success("头像上传成功");
					var data = {"uuid" : obj.data.uuid, "imgUrl" : obj.data.convertedUrl, "windowName" : window.name};
					var isSuc = window.parent.selectedImgHandler(data);
					$.closeWindow();
				}
				if(obj.type == "avatarError") {
					$.error("头像上传失败");
				}
// 				if(obj.type == "cancel") alert("取消编辑头像");
				if(obj.type == "cancel"){
					$.confirm("确定关闭吗？", function() {
						$.closeWindow();	
					}, function() {});
				}
// 				if(obj.type == "FileSelectCancel") alert("取消选取本机图片");	
			}
</script>
</html>