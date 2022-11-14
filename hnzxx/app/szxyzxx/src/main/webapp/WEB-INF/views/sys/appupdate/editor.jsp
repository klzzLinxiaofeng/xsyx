<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
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
.update-center{
    width: 700px;
}
.update-record{
    font-family: '微软雅黑';
    font-size: 13px;
    color: #222;
    margin-top: 10px;
}
.update-record p{
    padding:0;
    margin: 0 10px 0 0;
    float: left;
    font-family: '微软雅黑';
    font-size: 13px;
    height: 30px;
    line-height: 30px;
    width: 150px;
    text-align: right;
}
.update-record input{
    height: 30px;
    width: 150px;
}
.update-record img{
    height: 100px;
    width: 100px;
    margin-bottom: 50px;
}
.update-file{
    float: left;
}
.update-file span{
    font-weight: normal;
}
.update-version{
    float: right;
    margin:10px 0 0 0;
}
.update-version p{
    line-height: 33px;
    min-width:100px;
}
#uploader{
	position: absolute;
    right: 250px;
}
#uploader1{
	position: absolute;
    right: 250px;
}
#a,#b{
	text-align: left;
	display:block;
	white-space:nowrap;
	overflow:hidden;
	text-overflow:ellipsis;
	width:200px;
	float:left;
	
}
#update_btzd label.myerror{
	float: right;
	width: 80px;
    margin: 5px 0 0 0;
}
#update_bt label.myerror{
	width: 80px;
	position: absolute;
    left: 310px;
    top: 45px;
}
#uploader-queue{
	float:right;
	width:250px;
	margin-top:30px;
}
#uploader1-queue{
	margin-top:30px;
	float:right;
	width:250px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0;">
				<div class="widget-container" style="padding:0;">
					<form class="form-horizontal tan_form" id="app_form" action="javascript:void(0);">
					<input type="hidden" value="${appRelease.id}" name="id">
						<div class="update-center">
						<div class="update-record">
							<p><span style="color: red">*</span>名称：</p><input type="text" placeholder="" value="${appEdition.name}" disabled="disabled">
							<input type="hidden" name="appKey" value="${appRelease.appKey}">
							<input type="hidden" name="appId" value="${appRelease.appId}">
						</div>
						</div>
						<div class="update-record"id="update_btzd">
							<p><span style="color: red">*</span>操作系统最低版本要求：</p><input name="osVersion" type="text" placeholder="" style="width:464px;height:30px;" value="${appRelease.osVersion }">
						</div>
						<div class="update-record">
							<p>版权说明：</p><input type="text" name="copyright" placeholder="" style="width:464px;height:30px;" value="${appRelease.copyright }">
						</div>
						<div class="update-record">
							<p>更新说明：</p><textarea type="text" name="description" placeholder="" style="width:464px;resize:none;height:100px;">${appRelease.description }</textarea>
						</div>
						<div class="update-record" style="margin-top: 20px;float: left;width: 100%;margin-bottom: 10px;">
							<div class="update-file">
							<p>安装文件：</p>
							<c:choose>
									<c:when test="${not empty appRelease.setupFile }">
									<p style="text-align: left;display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;width:200px;"><a target="_blank" id="a" st6y href='<entity:getHttpUrl uuid="${appRelease.setupFile}"/>'>${entitySetupFile.fileName}</a></p>
										<button id="uploader">重新上传</button>
									</c:when>
									<c:otherwise>
										<a taget="_blank" id="a"style="margin-top:5px;"></a>
										<button id="uploader">上传</button>
									</c:otherwise>
								</c:choose>
								<input type="hidden" id="entityId" name="setupFile" value="${appRelease.setupFile}"/>
							</div>
							</div>
							<div class="update-record" style="margin-top: 20px;width: 100%;">
							<div  class="update-file" >
							<p>打包文件：</p>
							<c:choose>
									<c:when test="${not empty appRelease.packageFile }">
										<p style="text-align: left;display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;width:200px;"><a target="_blank" id="b" href='<entity:getHttpUrl uuid="${appRelease.packageFile}"/>'>${entityPackageFile.fileName}</a></p>
										<button id="uploader1">重新上传</button>
									</c:when>
									<c:otherwise>
										<a taget="_blank" id="b"style="margin-top:5px;"></a>
										<button id="uploader1">上传</button>
									</c:otherwise>
								</c:choose>
								<input type="hidden" id="entityId1" name="packageFile" value="${appRelease.packageFile}"/>
						</div>
						</div>
						<div class="clear"></div>
						<div class="update-record" >
							<p>二维码：</p>
							<img src='<entity:getHttpUrl uuid="${appRelease.qrCodeFile}"/>'>
						</div>
						<div class="form-actions tan_bottom">
							<button class="btn btn-warning" type="button" id="saveButton" onclick="saveOrUpdate();">保存</button>
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
		uploadFile1();
		checker = initValidator();
	});

	function initValidator() {
		return $("#app_form").validate({
			errorClass : "myerror",
			rules : {
				"version" : {
					required : true,
					maxlength:20,
					isFloat : true
				},
				"osVersion" : {
					required : true,
					maxlength:20
				},
				"copyright" : {
					maxlength:200
				},
				"description" : {
					maxlength:1500
				}
			},
			messages : {
				"version" : {
					maxlength:"超过指定字符"
				},
				"osVersion" : {
					maxlength:"超过指定字符"
				},
				"copyright" : {
					maxlength:"超过指定字符"
				},
				"description" : {
					maxlength:"超过指定字符"
				}
			}
		});
	}

	//保存或更新修改
	function saveOrUpdate() {
		//alert("222")
		if (checker.form()) {
			//var $requestData = formData2JSONObj("#app_form");
			var $requestData = $("#app_form").serializeArray();
			for(var item in $requestData){//遍历json对象的每个key/value对,p为key
				if(item == 'description'){   
		            alert($requestData[item]);
		        }  
			}
			var url = "${ctp}/sys/appupdate/editor";
			var loader = new loadLayer();
			loader.show();
			/* alert(JSON.stringify($requestData)); */
			if($requestData.setupFile != ""){
			$.post(url, $requestData, function(data, status) {
				$("#saveButton").attr("disabled","disabled");
				if ("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					} else if("exist" === data.info){
						$.error("已有版本名称");
					}else {
						$.error("操作失败");
					}
				} else {
					$.error("操作失败");
				}
				loader.close();
			});
			}else{
				$.error("请上传文件");
				loader.close();
			}
		}
	}
	
	function showUploadButtonText(valueId){
		var data = $("#"+valueId).val();
		var text = "上传文件";
		if(data != null && data != "" && data != "unddefind"){
			text = "重新上传";
		}
		return text;
	}
	
	function uploadFile(){
		var buttonText = showUploadButtonText("entityId");
	    	var obj = setTimeout(function() {$("#uploader").uploadify({
	            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
	            uploader: '${pageContext.request.contextPath}/uploader/common',
	            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
	            fileObjName: 'file',
	            fileTypeDesc: "上传文件",
	            fileTypeExts: "*.apk;*.exe;*.zip;*.rar", //默认*.*
	            method: 'post',
	            multi: false, // 是否能选择多个文件
	            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
	            removeTimeout: 1,
	            queueSizeLimit: 1,
	            fileSizeLimit: 2 * 1024 * 1024,
	            buttonText: buttonText,
	            requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
	            height: 20,
	            width: 70,
	            onUploadSuccess: function(file, data, response) {
	           	 var $jsonObj = eval("(" + data + ")");
	           	 $("#entityId").val($jsonObj.uuid);
	           	 $("#a").text($jsonObj.realFileName);
	           	 $("#a").attr('href',$jsonObj.url); 
	           	 $("#a").attr('target','_blank');
	           	
	            },
	            onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
	                $("#infoBox").prev("p").css("display", "none");
	                $("#infoBox").css("display", "block");
	            },
	            onUploadError: function(file, errorCode, errorMsg, errorString) {
	                $.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
	            }
	        })},10);
	    }
	    
	function uploadFile1(){
		var buttonText = showUploadButtonText("entityId1");
		var obj = setTimeout(function() {$("#uploader1").uploadify({
	        swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
	        uploader: '${pageContext.request.contextPath}/uploader/common',
	        formData: {'jsessionId': '<%=request.getSession().getId()%>'},
	        fileObjName: 'file',
	        fileTypeDesc: "上传文件",
	        fileTypeExts: "*.zip;*.rar", //默认*.*
	        method: 'post',
	        multi: false, // 是否能选择多个文件
	        auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
	        removeTimeout: 1,
	        queueSizeLimit: 1,
	        fileSizeLimit: 2 * 1024 * 1024,
	        buttonText: buttonText,
	        requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
	        height: 20,
	        width: 70,
	        onUploadSuccess: function(file, data, response) {
	       	 var $jsonObj = eval("(" + data + ")");
	       	 $("#entityId1").val($jsonObj.uuid);
	       	 $("#b").text($jsonObj.realFileName);
	       	 $("#b").attr('href',$jsonObj.url); 
	       	 $("#b").attr('target','_blank');
	       	
	        },
	        onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
	            $("#infoBox").prev("p").css("display", "none");
	            $("#infoBox").css("display", "block");
	        },
	        onUploadError: function(file, errorCode, errorMsg, errorString) {
	            $.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
	        }
	    })},10);
	}
</script>
</html>