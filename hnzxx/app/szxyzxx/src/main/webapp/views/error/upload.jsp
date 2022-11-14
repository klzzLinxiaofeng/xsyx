<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>uploadify</title>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp" %>
</head>
<body>
	<div id="fileQueue"></div>
	<input type="file" name="uploadify" id="uploadify" />
	<div id="tp_queue"></div>
<!-- 	<p> -->
<!-- 		<a href="javascript:$('#uploadify').uploadify('upload', '*');">开始上传</a> -->
<!-- 		<a href="javascript:$('#uploadify').uploadify('stop', '*');">取消所有上传</a> -->
<!-- 	</p> -->
<%-- 	<form action="${ctp}/test/test/import" method="post" enctype="multipart/form-data"> --%>
<!-- 		<input type="file" name="file" > -->
<!-- 		<input type="submit" value="导入" /> -->
<!-- 	</form> -->
	
	
<!-- 	<input id="bjxx" data-id-container="bjxx" > -->
	
	
<!-- 	<input type="button" value="确定" onclick="getId();"> -->
</body>

<script type="text/javascript">
	
	$(function() {
// 		$.createBjxxSelector({});
		initUplaoder();
	});
	
	function getId() {
// 		alert($("#bjxx").val());
	}
	
	function initUplaoder() {
		$("#uploadify").uploadify({
	        swf              : '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
	        uploader         : '${pageContext.request.contextPath}/uploader/common',
	        formData         : {'jsessionId' : '<%=request.getSession().getId()%>', "c" : "1"},
							fileObjName : 'file',
							fileTypeDesc : 'Img(*.gif;*.jpg;*.jpeg;*.png;*.bmp)',
							fileTypeExts : '*.gif; *.jpg; *.png;*.jpeg;*.bmp',
							method : 'post',
							auto : true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
							queueID : 'tp_queue',
							removeTimeout : 1,
							multi : true,

							buttonText : '文件上传',
							requeueErrors : false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
							height : 20,
							width : 66,
							onUploadSuccess : function(file, data, response) {
								
								alert(JSON.stringify(data));
								
								
							},
							onUploadError : function(file, errorCode, errorMsg,
									errorString) {
								alert('The file ' + file.name
										+ ' could not be uploaded: '
										+ errorString);
							}
						});
	}
</script>
</html>

