<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css"
	rel="stylesheet">
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

.form-horizontal .controls #zp {
	width: 423px;
}

.form-horizontal .controls #zp .img_1 {
	float: left;
	margin: 10px;
	position: relative;
	top: 0;
	width: 120px;
	height: 120px;
}

.form-horizontal .controls #zp .img_1 img {
	height: 120px;
	width: 120px;
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
					<input type="hidden" id="isCK" value="${isCK}" />
					<form class="form-horizontal tan_form" id="repair_form"
						action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label"> <span style="color: red;">*</span>标题： </label>
							<div class="controls">
								<input type="text" id="title" name="title" class="span13 {required:true,maxlength:40}"
									placeholder="" value="${repair.title}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"> <span style="color: red;">*</span>保修部门： </label>
							<div class="controls">
								<select id="departmentId" name="departmentId" class="span13 {required:true}">
									<option value="">请选择</option>
									<c:forEach items="${departmentList}" var="list">
										<option value="${list.id}"
											<c:if test="${list.id==repair.departmentId}">selected="selected"</c:if>>${list.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">附件：</label>
							<div class="controls">
								<c:if test="${isCK==''||isCK==null}">
									<c:if test="${repair.dealStatus=='01' || repair.dealStatus==null || repair.dealStatus==''}">
										<input type="file" id="uploader" name="uploader">
									</c:if>
								</c:if>
								<span id="tp_queue"></span>
								<div id="zp">
									<c:forEach items="${fileList}" var="f">
										<div class="img_1">
											<c:if test="${isCK==''||isCK==null}">
												<c:if test="${repair.dealStatus=='01' || repair.dealStatus==null || repair.dealStatus==''}">
													<a data-id="${f.fileUuid}" onclick="reMove(this);">x</a>
												</c:if>
											</c:if>
											<img src="${f.thumbUrl}"
												class="ims" onclick="Change(this);" style="width: 120px; height: 120px;">
										</div>
									</c:forEach>
								</div>
								<div class="clear"></div>
							</div>
						</div>
						<c:if
							test="${repair.dealStatus=='08' || repair.dealStatus=='04' || repair.dealStatus=='03' || repair.dealStatus=='02'}">
							<div class="control-group">
								<label class="control-label"> 维修状态： </label>
								<div class="controls">
									<select id="dealStatus" name="dealStatus" class="span13" disabled="disabled">
										<option value="01"
											<c:if test="${repair.dealStatus=='01'}">selected="selected"</c:if>>申请</option>
										<option value="02"
											<c:if test="${repair.dealStatus=='02'}">selected="selected"</c:if>>受理（维修中）</option>
										<option value="03"
											<c:if test="${repair.dealStatus=='03'}">selected="selected"</c:if>>不受理</option>
										<option value="04"
											<c:if test="${repair.dealStatus=='04'}">selected="selected"</c:if>>维修完成</option>
										<option value="08"
											<c:if test="${repair.dealStatus=='08'}">selected="selected"</c:if>>评价完成</option>
									</select>
								</div>
							</div>
							<c:if test="${isCK!=null}">
								<div class="control-group">
									<label class="control-label"> 受理人： </label>
									<div class="controls">
										<input type="text" id="receiverId" name="receiverId"
											class="span13" placeholder=""
											value="${repair.receiverId}">
									</div>
								</div>
							</c:if>
							<c:if test="${repair.dealStatus!='03'}">
							<div class="control-group">
								<label class="control-label"> 负责人、维修人： </label>
								<div class="controls">
									<input type="text" id="handler" name="handler" class="span13"
										placeholder="" value="${repair.handler}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"> 负责人联系电话： </label>
								<div class="controls">
									<input type="text" id="handlerPhone" name="handlerPhone"
										class="span13" placeholder="" value="${repair.handlerPhone}">
								</div>
							</div>
							<c:if test="${isCK!=null}">
								<div class="control-group">
									<label class="control-label"> 受理时间： </label>
									<div class="controls">
										<input type="text" id="dealTime" name="dealTime"
											class="span13" placeholder=""
											value='<fmt:formatDate pattern="yyyy-MM-dd" value="${repair.dealTime}"></fmt:formatDate>'>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"> 维修完成时间： </label>
									<div class="controls">
										<input type="text" id="finishTime" name="finishTime"
											class="span13" placeholder=""
											value='<fmt:formatDate pattern="yyyy-MM-dd" value="${repair.finishTime}"></fmt:formatDate>'>
									</div>
								</div>
							</c:if>
							<div class="control-group">
								<label class="control-label"> 维修内容： </label>
								<div class="controls">
									<input type="text" id="content" name="content" class="span13"
										placeholder="" value="${repair.content}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"> 评价得分： </label>
								<div class="controls">
									<input type="text" id="score" name="score" class="span13"
										placeholder="" value="${repair.score}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label"> 评价内容： </label>
								<div class="controls">
									<input type="text" id="comment" name="comment" class="span13"
										placeholder="" value="${repair.comment}">
								</div>
							</div>
						</c:if>
						</c:if>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${repair.id}" />
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
	var isCK = $("#isCK").val();
	if(isCK!=""){
		$(".controls").disable();
		$(".form-actions").hide();
	}
	
	$(function() {
		uploadFile();
		checker = initValidator();
		});
	
	function initValidator() {
		return $("#repair_form").validate({
			errorClass : "myerror",
			rules : {
			},
			messages : {
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#repair_form");
				$requestData.fileUuid = getFileUuids();
				$requestData.thumbUrl = getFileUrls();
			var url = "${ctp}/oa/repair/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/oa/repair/" + $id;
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
							multi : true, // 是否能选择多个文件
							auto : true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
							removeTimeout : 1,
							queueSizeLimit : 1,
							fileSizeLimit : 4 * 1024,
							buttonText : "上传文件",
							requeueErrors : false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
							height : 20,
							width : 70,
							onUploadSuccess : function(file, data, response) {
								var $jsonObj = eval("(" + data + ")");
								var img = '<div class="img_1"><a data-id="'
										+ $jsonObj.uuid
										+ '" onclick="reMove(this);">x</a><img style="width:120px;height:120px;" class="ims" onclick="Change(this);" src="'
										+ $jsonObj.url
										+ '"/>&nbsp;&nbsp;&nbsp;</div>';
								$.success("上传成功!", 9);
								$("#zp").append(img);
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
// 		alert(imgSrc);
// 		$.layer({
// 			type : 1,
// 			shade : [ 0.2, '#000' ],
// 			shadeClose : true,
// 			area : [ 'auto', 'auto' ],
// 			offset : [ '20px', '' ],
// 			title : false,
// 			border : [ 0 ],
// 			page : {
// 				html : '<img src="' + imgSrc + '">'
// 			},
// 			shift : 'left'
// 		});
// 		window.location.href =imgSrc;
		window.open(imgSrc);
	}

	function reMove(obj) {
		$(obj).parent().remove();
	}

	//获取文件的uuid
	function getFileUuids() {
		var imgs = $(".img_1");
		var fids = "";
		$.each(imgs, function(index, value) {
			fids += ($(value).find("a").attr("data-id") + ",");
		});
		if (fids != "") {
			fids = fids.substring(0, fids.length - 1);
		}
		return fids;
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