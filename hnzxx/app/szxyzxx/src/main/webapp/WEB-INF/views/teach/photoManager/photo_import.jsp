<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改图片</title>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<style>
.form-horizontal .controls #zp .img_1 {
	float: left;
	margin: 10px;
	position: relative;
	top: 0;
	width: 120px;
	height: 130px;
}

.form-horizontal .controls #zp .img_1 img {
	width: 120px;
	height: 130px;
}

.form-horizontal .controls #zp .img_1 a {
	position: absolute;
	font-size: 16px;
	/* font-weight: bold; */
	color: #888;
	right: 0px;
	top: 0px;
	display: block;
	width: 14px;
	height: 14px;
	line-height: 14px;
	text-align: center;
	cursor: pointer;
	background-color: #eee;
	border: 1px solid #aaa;
}

.form-horizontal .controls {
	margin-left: 100px;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</head>
<body style="background-color: #F3F3F3 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div style="margin-bottom: 0" class="content-widgets">
				<div style="padding: 20px 0 0;" class="widget-container">
					<form class="form-horizontal" id="photoImport_form"
						action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label" style="width:80px;"><font style="color: red">*</font>
							姓名:
							</label>
							<div class="controls" style="width:330px;">
								<input type="text" id="name" name="name" class="span4"
									placeholder="" disabled="disabled" value="${user.name}">
							</div>
						</div>
						
						<div class="control-group"style="width:400px;margin: 0 40px 0 40px;">
							<div class="controls" style="margin-left: 0">
								<div id="zp" style="display: inline-block;">
									<div class="img_1" style="display: none"></div>
									<c:choose>
										<c:when test="${isExist }">
											<div class="img_1" style="width:100px;">
												<a data-id="${teamActivity.icon}"
													onclick="reMove(this);">x</a> <img
													src='<avatar:avatar userId='${user.userId }'></avatar:avatar>' onclick="Change(this);"
													style="width: 120px; height: 130px;">
											</div>
										</c:when>
										<c:otherwise>
											<img
												src="<avatar:avatar userId='${user.userId }'></avatar:avatar>"
												onclick="Change(this);"
												style="width: 120px; height: 130px; padding-bottom: 10px;">
										</c:otherwise>
									</c:choose>
								</div>
								<input type="hidden" id="uploader" name="uploader">
								 <input	type="hidden" id="icon" name="icon" value="${icon }" />
								<div class="clear"></div>
							</div>
						</div>
						<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center">
								<input type="hidden" id="userId" name="userId" value="${user.userId }" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
		uploadFile();
	});
	
	function initValidator() {
		return $("#photoImport_form").validate({
			errorClass : "myerror",
			rules : {
			},
			messages : {
			}
		});
	}
	
	function uploadFile(){
    	var obj = $("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'
							},
							fileObjName : 'file',
							fileTypeDesc : "文件上传",
// 							fileTypeExts : "*.*", //默认*.*
							fileTypeExts : '*.gif; *.jpg; *.png;*.jpeg;*.bmp',
							method : 'post',
							multi : false, // 是否能选择多个文件
							auto : true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
							removeTimeout : 1,
							queueSizeLimit : 1,
							fileSizeLimit : 4 * 1024,
							buttonText : "上传照片",
							requeueErrors : false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
							height : 20,
							width : 70,
							onUploadSuccess : function(file, data, response) {
								var $jsonObj = eval("(" + data + ")");
								var img = '<div class="img_1"><a data-id="'
										+ $jsonObj.uuid
										+ '" onclick="reMove(this);">x</a><img style="width:120px;height:130px;" class="ims" onclick="Change(this);" src="'
										+ $jsonObj.url
										+ '" data-id="'+$jsonObj.uuid+'"/>&nbsp;&nbsp;&nbsp;</div>';
								$.success("上传成功!", 9);
								$("#icon").val($jsonObj.uuid);	
								$("#zp").html(img);
							},
							onUploadStart : function(file) { //上传开始时触发（每个文件触发一次）
								$("#infoBox").prev("p").css("display", "none");
								$("#infoBox").css("display", "block");
							},
							onUploadError : function(file, errorCode, errorMsg,
									errorString) {
								$.alert('The file ' + file.name
										+ ' could not be uploaded: '
										+ errorString);
							}
						});

	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#photoImport_form");
			//alert(JSON.stringify($requestData));
			var url = "${pageContext.request.contextPath}/teach/photoManager/iconCreator";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						parent.core_iframe.search();
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	
	function Change(obj) {
		var imgSrc = $(obj).attr("src");
		window.open(imgSrc);
	}

	function reMove(obj) {
		$(obj).next("img").attr("src","${pageContext.request.contextPath}/res/images/no_picture.jpg");
		$(obj).remove();
		$("#icon").val("");
	}

	function closeWin(){
		$.confirm("确定离开此页面？", function() {
			$.closeWindow();
		});
	}
	
</script>
</html>