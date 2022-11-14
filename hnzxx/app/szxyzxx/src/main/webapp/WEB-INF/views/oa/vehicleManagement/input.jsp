<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
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
<body style="background-color: #fff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<input type="hidden" id="isCK" value="${isCK}"/>
					<form class="form-horizontal tan_form" id="vehicleManagement_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									车名<span style="color:red">*</span>：
								</label>
								<div class="controls">
								<input type="text" id="cardName" name="cardName" class="span13 {required:true,maxlength:20}" placeholder="" value="${vehicleManagement.cardName}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									车牌号<span style="color:red">*</span>：
								</label>
								<div class="controls">
								<input type="text" id="plateNumber" name="plateNumber" class="span13 {required:true,maxlength:20}" placeholder="" value="${vehicleManagement.plateNumber}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									满载人数<span style="color:red">*</span>：
								</label>
								<div class="controls">
								<input type="text" id="fullLoad" name="fullLoad" class="span13 {required:true,digits:true,max:60}" placeholder="" value="${vehicleManagement.fullLoad}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									购置日期<span style="color:red">*</span>：
								</label>
								<div class="controls">
								<input type="text" id="purchaseData" name="purchaseData" class="span13 {required:true}" placeholder="" 
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${vehicleManagement.purchaseData}"></fmt:formatDate>' onclick="WdatePicker({maxDate:'%y-%M-{%d}'});">			
								</div>
							</div>
							
							<div class="control-group">
							<label class="control-label">封面：</label>
							<div class="controls">
							<div id="zp" style="display:inline-block;">
										<div class="img_1">
											<c:if test="${(isCK==''||isCK==null) && (vehicleManagement.cover !=null && vehicleManagement.cover != '') }">
													<a data-id="${vehicleManagement.cover}" onclick="reMove(this);">x</a>
											</c:if>
											<c:if test="${vehicleManagement.cover !=null && vehicleManagement.cover != ''}">
<%-- 												<img src="${vehicleManagement.cover}" onclick="Change(this);" style="width: 233px; height: 130px;"> --%>
													<img src="<entity:getHttpUrl uuid='${vehicleManagement.cover}'/>" data-id="${vehicleManagement.cover}" onclick="Change(this);" style="width: 233px; height: 130px;">
											</c:if>
											<c:if test="${vehicleManagement.cover ==null || vehicleManagement.cover == ''}">
												<img src="${ctp}/res/images/cheliang.jpg" onclick="Change(this);" style="width: 233px; height: 130px;">
											</c:if>
										</div>
								</div>
								<c:if test="${isCK==''||isCK==null}">
										<input type="hidden" id="uploader" name="uploader">
								</c:if>
								<span id="tp_queue"></span>
								
								<div class="clear"></div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<input type="text" id="remark" name="remark" class="span13" placeholder="" value="${vehicleManagement.remark}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${vehicleManagement.id}" />
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
		return $("#vehicleManagement_form").validate({
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
			var $requestData = formData2JSONObj("#vehicleManagement_form");
			$requestData.cover = getFileUrls();
			var url = "${ctp}/oa/vehicleManagement/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/oa/vehicleManagement/" + $id;
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
										+ '" data-id="' + $jsonObj.uuid + '"/>&nbsp;&nbsp;&nbsp;</div>';
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
	}

	//获取文件的缩略图路径
	function getFileUrls() {
		var imgs = $(".img_1");
		var urls = "";
		$.each(imgs, function(index, value) {
			urls += ($(value).find("img").attr("data-id") + ",");
		});
		if (urls != "" && urls != "undefined") {
			urls = urls.substring(0, urls.length - 1);
		}
		return urls;
	}
</script>
</html>