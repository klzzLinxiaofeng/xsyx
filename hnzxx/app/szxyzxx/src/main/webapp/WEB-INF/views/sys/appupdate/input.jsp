<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">

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
    width: 160px;
    text-align: right;
}
.update-record input{
    height: 16px;
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
    width:350px;
}
.update-version p{
    line-height: 33px;
    width:100px;
}
#uploader{
	position: absolute;
    left: 170px;
}
#a{
	width: 150px;
    display: block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    margin-top: 30px;
    margin-left: 170px;
}
#b{
	width: 103px;
    display: block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    margin-right: 25px;
    float: right;
}
#uploader1{
	position: absolute;
    right: 95px;
}
.update-version #version{
	margin-top: 0px;
}
.update-version .myerror{
	float:right;
	margin-top: -25px;
}
#update_btzd label.myerror{
	float: right;
	width: 80px;
    margin-top: 5px;
}
#uploader-queue{
	margin-top:30px;
}
#uploader1-queue{
	margin-top:30px;
}
</style>
</head>
<body style="background-color: #cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="app_form" action="javascript:void(0);">
						<div class="update-center">
							<div class="update-record">
								<p style="margin-top:10px;"><span style="color: red">*</span>产品：</p>
								<select class="chzn-select" style="width: 150px;margin-left: 0;margin-top:10px;" id="appKey" name="appKey">
									<c:forEach items="${items }" var="item"> 
											<option value="${item.appKey}">${item.name }</option>
										</c:forEach>
								</select>
								<div class="update-version">
								<p style=""><span style="color: red">*</span>版本：</p><input id="version" name="version" type="text" placeholder="长度不可超过20个字符" style="margin-right:90px;height:30px;"></div>
							</div>
							<div class="update-record" id="update_btzd">
								<p><span style="color: red">*</span>操作系统最低版本要求：</p><input id="osVersion" name="osVersion" type="text" placeholder="" style="width:440px;height:30px;">
							</div>
							<div class="update-record">
								<p>版权说明：</p><input name="copyright" type="text" placeholder="" style="width:440px;height:30px;">
							</div>
							<div class="update-record">
								<p>更新说明：</p><textarea name="description" type="text" placeholder="" style="width:440px;height:30px;resize:none;height:100px;"></textarea>
							</div>
							<div class="update-record" style="margin-top: 20px;">
								<div class="update-file">
								<p><span style="color: red">*</span>安装文件：</p>
								<button id="uploader">上传</button>
								<a taget="_blank" id="a"></a>
								<input type="hidden" id="entityId" name="setupFile" value=""/>
								</div>
								<div  class="update-file" style="float:right;margin-right:63px;width: 215px;">
								<p style="width:100px;">打包文件(zip,rar文件)：</p>
								<button id="uploader1">上传</button>
								<a taget="_blank" id="b"></a>
								<input type="hidden" id="entityId1" name="packageFile" value=""/>
								</div>
							</div>
							<div class="clear"></div>
							<div class="form-actions tan_bottom">
									<button class="btn btn-warning" type="button" id="saveButton" onclick="saveOrUpdate();">保存</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

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
				},
				"osVersion" : {
					required : true,
					maxlength:20
				},
				"copyright" : {
					maxlength:200
				},
				"description" : {
					maxlength:200
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
		if (checker.form()) {
			var $requestData = formData2JSONObj("#app_form");
			var url = "${ctp}/sys/appupdate/creator";
			var loader = new loadLayer();
			loader.show();
			if($requestData.setupFile != ""){
			$.post(url, $requestData, function(data, status) {
				$("#saveButton").attr("disabled","disabled");
				if ("success" === status) {
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.success("操作成功");
						$.closeWindow();
					} else if("exist" === data.info){
						$.error("已有版本名称");
					}else{
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
	
function uploadFile(){
    	var obj = setTimeout(function() {$("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
            fileObjName: 'file',
            fileTypeDesc: "文件上传",
            fileTypeExts: "*.apk;*.zip;*.rar;*.exe", //默认*.*
            method: 'post',
            multi: false, // 是否能选择多个文件
            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout: 1,
            queueSizeLimit: 1,
            fileSizeLimit: 2 * 1024 * 1024,
            successTimeout: 180,
            buttonText: "上传文件",
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
	var obj = setTimeout(function() {$("#uploader1").uploadify({
        swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
        uploader: '${pageContext.request.contextPath}/uploader/common',
        formData: {'jsessionId': '<%=request.getSession().getId()%>'},
        fileObjName: 'file',
        fileTypeDesc: "文件上传",
        fileTypeExts: "*.zip; *.rar", //默认*.*
        method: 'post',
        multi: false, // 是否能选择多个文件
        auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
        removeTimeout: 1,
        queueSizeLimit: 1,
        fileSizeLimit: 2 * 1024 * 1024,
        successTimeout: 180,
        buttonText: "上传文件",
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