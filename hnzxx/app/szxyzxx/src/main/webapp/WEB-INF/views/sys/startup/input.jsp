<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.form-horizontal .controls #zp .img_1 {
	float: left;
	margin: 10px;
	position: relative;
	top: 0;
	width: 233px;
	height: 130px;
}
.form-horizontal .controls #zp .img_1 img {
	width: 233px;
	height: 130px;
}
.form-horizontal .controls #zp .img_1 a {
	position: absolute;
	font-size: 22px;
	font-weight: bold;
	color: #000;
	right: 0px;
	top: 0px;
	display: block;
	width: 16px;
	height: 16px;
	line-height: 16px;
	text-align: center;
	cursor: pointer;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="Startup_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>标题：
								</label>
								<div class="controls">
								<input type="text" id="title" name="title" style="width: 250px;" class="span13" placeholder="标题" value="${Startup.title}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									URL：
								</label>
								<div class="controls">
									<input type="text" id="url" name="url" style="width: 250px;" placeholder="请填写完整的url，如:http://www.baidu.com" class="span13" value="${Startup.url}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>缩略图：
								</label>
								<div class="controls">
								<div>
									<input type="hidden" id="entityId" name="entityId" value="${Startup.entityId}">
								</div>
								<div id="zp" style="display:inline-block;">
											<div class="img_1">
												<c:if test="${(isCK==''||isCK==null) && (Startup.thumUrl !=null && Startup.thumUrl != '') }">
														<a data-id="${Startup.thumUrl}" onclick="reMove(this);">x</a>
												</c:if>
												<c:if test="${Startup.thumUrl !=null && Startup.thumUrl != ''}">
													<img src="${Startup.thumUrl}" onclick="Change(this);" style="width: 260px; height: 130px;">
												</c:if>
												<c:if test="${Startup.thumUrl ==null || Startup.thumUrl == ''}">
													<img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);" style="width: 233px; height: 130px;">
												</c:if>
											</div>
									</div>
									<div><span>支持jpg、gif、png、bmp格式，宽高2*1</span></div>
									<c:if test="${isCK==''||isCK==null}">
											<input type="hidden" id="uploader" name="uploader">
									</c:if>
									<span id="tp_queue"></span>
									
									<div class="clear"></div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否启用：
								</label>
								<div class="controls">
<%-- 								<input type="text" id="pushState" name="pushState" class="span13" placeholder="" value="${Startup.pushState}"> --%>
								是<input type="radio" id="pushState" name="pushState" <c:if test="${Startup.pushState=='0'}">checked='checked'</c:if> placeholder="" value="0">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									否<input type="radio" id="pushState2" name="pushState"
										 <c:if test="${Startup.pushState == '1' || empty Startup.pushState}">checked='checked'</c:if> 
									 placeholder="" value="1">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${Startup.id}" />
							<c:if test="${isCK==''||isCK==null}">
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
							</c:if>
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
		uploadFile();
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#Startup_form").validate({
			errorClass : "myerror",
			rules : {
				"title" : {
					required : true,
					maxlength: 200
				},
				"entityId" : {
					required : true,
					maxlength: 36
				},
			},
			messages : {
				"entityId":{
					remote: "请上传缩略图"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#Startup_form");
			var url = "${ctp}/sys/startup/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/sys/startup/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
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
							buttonText : "上传封面",
							requeueErrors : false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
							height : 20,
							width : 70,
							onUploadSuccess : function(file, data, response) {
								var $jsonObj = eval("(" + data + ")");
								var img = '<div class="img_1"><a data-id="'
										+ $jsonObj.uuid
										+ '" onclick="reMove(this);">x</a><img style="width:233px;height:130px;" class="ims" onclick="Change(this);" src="'
										+ $jsonObj.url
										+ '"/>&nbsp;&nbsp;&nbsp;</div>';
								$("#entityId").val($jsonObj.uuid);
								$.success("上传成功!", 9);
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

	function Change(obj) {
		var imgSrc = $(obj).attr("src");
		window.open(imgSrc);
	}

	function reMove(obj) {
		$(obj).parent().remove();
		$("#entityId").val("");
		$("#zp").append('<div class="img_1"><img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);" style="width: 233px; height: 130px;"></div>');
	}

	//获取文件的缩略图路径
	function getFileUrls() {
		var imgs = $(".img_1");
		var urls = "";
		$.each(imgs, function(index, value) {
			urls += ($(value).find("img").attr("src") + ",");
		});
		if (urls != "") {
			urls = urls.substring(0, urls.length - 1);
		}
		return urls;
	}
</script>
</html>