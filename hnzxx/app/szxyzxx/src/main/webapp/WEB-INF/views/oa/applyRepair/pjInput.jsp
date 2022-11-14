<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%--<%@ include file="/views/embedded/plugin/uploadify.jsp"%>--%>
	<link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
	<script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
<title>报修</title>
</head>
<style type="text/css">
.form-horizontal .controls #zp .img_1 {
	float: left;
	margin: 10px;
	position: relative;
	top: 0;
	width: 233px;
	max-height: 130px;
}

.form-horizontal  #zp .img_1 img {
	width: 233px;
	height: 130px;
}

.form-horizontal #zp .img_1 a {
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
<body>
	<div class="container-fluid">
		<form class="pj_form form-horizontal" id="pj_form">
			<p class="clearfix">
				<span class="left"><i class="fa fa-thumbs-up"></i>评价</span>
				<input type="hidden" id="start" value="${applyRepair.appraise}">
				 <input type="hidden" id="appraise" value="1" /> <em
					class="x-star" id="star"> <i class="fa fa-star active"
					onclick="getStart('1')"></i> <i class="fa fa-star"
					onclick="getStart('2')"></i> <i class="fa fa-star"
					onclick="getStart('3')"></i> <i class="fa fa-star"
					onclick="getStart('4')"></i> <i class="fa fa-star"
					onclick="getStart('5')"></i>
				</em>
			</p>
			<div class="clearfix">
				<p>
					<span class="left"><i class="fa fa-comment-o"></i>备注</span>
				</p>
				<div class="controls left" style="margin:0">
					<textarea rows="3" class="span6 {maxlength:100}" name="remark" <c:if test="${applyRepair.appraise!='' && applyRepair.appraise!=null}">disabled="disabled"</c:if> >${applyRepair.remark}</textarea>
					<div class="fileupload fileupload-new clearfix"
						data-provides="fileupload">
					</div>
				</div>
			</div>
		</form>
		<c:if test="${applyRepair.appraise=='' || applyRepair.appraise==null}">
			<button class="btn btn-primary btn-main" type="button" onclick="saveOrUpdate()">提交评价</button>
		</c:if>
		<input type="hidden" id="id" value="${applyRepair.id}"/>
		<p class="clearfix date"><fmt:formatDate pattern="yyyy年MM月dd日" value="${applyRepair.appointmentDate}"></fmt:formatDate></p>
	</div>
</body>
<script type="text/javascript">
function getStart(num){
	$("#appraise").val(num);
}

//这里的作用是当已经评价过了，就不能再点亮星星
$(".x-star i").click(function(){
	if("${applyRepair.appraise=='' || applyRepair.appraise==null}" == "true"){
		$(".x-star i").removeClass("active");
		$(this).addClass("active").prevAll().addClass("active");
	}
});

var checker;
$(function(){
	uploadFile();
	checker = initValidator();
	addStart();
})

function addStart(){
	var num = $("#start").val();
	for(var i = 0;i < num;i++){
		$("#star i").eq(i).addClass("active");
	}
}

function initValidator() {
		return $("#pj_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}

function uploadFile(){
	$('#uploader').uploadifive({
		'auto': true,
		'fileObjName' : 'file',
		//'queueID': 'queue',
		'buttonText': '上传图片',
		'removeCompleted':true,
		'height' : 20,
		'width' : 70,
		'formData': {
			'jsessionId': '<%=request.getSession().getId()%>'
		},
		'uploadScript': '/uploader/common',
		'onUploadComplete': function (file,data) {
			var $jsonObj = eval("(" + data + ")");
			var img = '<div class="img_1"><a data-id="'
					+ $jsonObj.uuid
					+ '" onclick="reMove(this);">x</a><img style="width:233px;height:130px;" class="ims" onclick="Change(this);" src="'
					+ $jsonObj.url
					+ '" data-id="' + $jsonObj.uuid + '"/>&nbsp;&nbsp;&nbsp;</div>';
			$.success("上传成功!", 9);
			$("#zp").html(img);
		},
		onUpload:function (file) { //上传开始时触发（每个文件触发一次）
			$("#infoBox").prev("p").css("display", "none");
			$("#infoBox").css("display", "block");
		},
		onFallback : function() {
			alert("该浏览器无法使用!");
		},
	});


/*


	var obj = $("#uploader").uploadify({
        swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
        uploader: '${pageContext.request.contextPath}/uploader/common',
        formData: {'jsessionId': '<%=request.getSession().getId()%>'
							},
							fileObjName : 'file',
							fileTypeDesc : "文件上传",
							//							fileTypeExts : "*.*", //默认*.*
							fileTypeExts : '*.gif; *.jpg; *.png;*.jpeg;*.bmp',
							method : 'post',
							multi : true, // 是否能选择多个文件
							auto : true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
							removeTimeout : 1,
							queueSizeLimit : 1,
							fileSizeLimit : 4 * 1024,
							buttonText : "上传图片",
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
*/

	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#pj_form");
			$requestData.wholePicture = getFileUrls();
			$requestData._method = "put";
			if ($requestData.wholePicture == "undefined") {
				$requestData.wholePicture = "";
			}
			var appraise = $("#appraise").val();
			var prefix = "${pageContext.request.contextPath}";
			var url = prefix + "/oa/applyrepair/pjRepair/" + $id + "?appraise="+appraise;
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						window.location.reload();
// 						pjRepari($id);
					} else {
						$.error("操作失败");
					}
				} else {
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