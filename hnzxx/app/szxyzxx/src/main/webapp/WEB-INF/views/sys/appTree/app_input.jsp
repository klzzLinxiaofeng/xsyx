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
#uploader{
	position: absolute;
    display: block;
    margin-bottom: 80px;
    top:5px;
    left:0;
}
#SWFUpload_0{
    left: 4px;
    top: 1px;
}
.update_img{position:relative}
.uploadify-queue{
	margin-bottom:4em;
	position:relative;
	top:40px;
}
.update_img p{position:relative;top:40px;}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="app_form" action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>产品名称： </label>
							<div class="controls">
								<input type="text" id="name" name="name" class="span13"
									placeholder="" value="${app.name}">
							</div>

						</div>
						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>AppKey： </label>
							<div class="controls">
								<input type="text" id="appKey" name="appKey"
									class="span13" placeholder="" value="${app.appKey}">
							</div>

						</div>
						<div class="control-group">
							<label class="control-label"> 产品类型： </label>
							<div class="controls">
								<input type="text" id="type" name="type" class="span13"
									placeholder="" value="${app.type}">
							</div>

						</div>
						<div class="control-group">
							<label class="control-label"> 开发商： </label>
							<div class="controls">
								<input type="text" id="manufacturer" name="manufacturer"
									class="span13" placeholder="" value="${app.manufacturer}">
							</div>

						</div>
						
						<div class="control-group">
							<label class="control-label"> 运行环境： </label>
							<div class="controls">
								<input type="text" id="runtime" name="runtime"
									class="span13" placeholder="" value="${app.runtime}">
							</div>

						</div>
						
						<div class="control-group">
							<label class="control-label"> 功能介绍： </label>
							<div class="controls">
								<textarea id="description" class="span13" name="description">${app.description}</textarea>
							</div>

						</div>
						<div class="control-group">
							<label class="control-label" style="margin-right: 20px;"> 适用操作系统： </label>
							<select id="osForm" name="osForm">
									<option value="01">Windows</option>
									<option value="02">Linux/Unix</option>
									<option value="03">Mac OS/IOS</option>
									<option value="04">Android</option>
								</select>
						</div>
						<div class="control-group">
						<label class="control-label" style="margin-right: 20px;"> 商标文件： </label>
						<%-- <c:choose>
									<c:when test="${not empty app.trademark }">
									
									<p style="margin-top: 6px;"><a target="_blank" style="display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;width:300px;" id="a" href='${app.trademark}'>${tentity.fileName}</a></p>
									<c:choose>
											<c:when test="${isCK != null && isCK != '' }"></c:when>
											<c:otherwise><button id="uploader">上传</button>
										<input type="hidden" id="entityId" name="trademark" value="${app.trademark}"/>
										</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
									
										<p>
										
										<a taget="_blank" id="a"></a>
										<c:choose>
											<c:when test="${isCK != null && isCK != '' }"></c:when>
											<c:otherwise><button id="uploader" >上传</button>
										<input type="hidden" id="entityId" name="trademark" value="${app.trademark}"/>
										</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose> --%>
								<div class="controls update_img" >
								<input type="hidden" id="entityId" name="trademark" value="${app.trademark }"/>
								<c:choose>
									<c:when test="${not empty app.trademark }">
										<p style='display:inline-block;margin-bottom:0;width:350px;overflow:hidden;'><a target="_blank" id="a" href='<entity:getHttpUrl uuid="${app.trademark}"/>'>${entity.fileName}</a><button id="b" onclick="deleteFile();" class="btn btn-red" ${isCK != null && isCK != '' ? "style='display:none'" : ""}>删除</button></p>
									</c:when>
									<c:otherwise>
										<p style='display:inline-block;margin-bottom:0;width:350px;overflow:hidden;'><a taget="_blank" id="a"></a></p>
										<c:choose>
											<c:when test="${isCK != null && isCK != '' }"></c:when>
											<c:otherwise><input type="hidden" id="uploader" name="uploader"/></c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								
								</div>
						</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${app.id}" />
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
		uploadFile();
		var isCk = "${isCK}";
		if("true" === isCk) {
			$("#app_form").disable();
		}
		checker = initValidator();
	});

	function initValidator() {
		return $("#app_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
					maxlength:100,
					stringCheck:true,
					remote:{
						async : false,
						url:"${ctp}/sys/appTree/app/check",
						type:"post",
						dataType:"json",
						data:{
							'dxlx' : 'name',
							"name":function(){return $("#name").val();},
							'id' : function() {return $("#id").val();}
						}
					}
				},
				"appKey" : {
					required : true,
					maxlength:100,
					remote:{
						async : false,
						url:"${ctp}/sys/appTree/app/check",
						type:"post",
						dataType:"json",
						data:{
							'dxlx' : 'appKey',
							"appKey":function(){return $("#appKey").val();},
							'id' : function() {return $("#id").val();}
						}
					}
				}
			},
			messages : {
				"name" : {
					stringCheck:"只能输入中文字符",
					remote:"名称已存在"
				},
				"appKey" : {
					remote:"appKey不能重复"
				}
			}
		});
	}

	
	function deleteFile(){
		$.confirm("确定执行此次操作？", function() {
			executeDel();
		});
	}
	
	function executeDel(){
		$("#a").text("");
	  	$("#a").attr('href', ""); 
		$("#entityId").val("");
		$("#b").remove();
		$(".update_img").children().append("<input type='file' id='uploader' name='uploader'/>")
		uploadFile();
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#app_form");
			var url = "${ctp}/sys/appTree/app_creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/sys/appTree/app/" + $id;
			}
			/* alert(JSON.stringify($requestData));
			return; */
			
			var loader = new loadLayer();
			loader.show();
			$.post(url, $requestData, function(data, status) {
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
	
	function uploadFile(){
    	var obj = setTimeout(function() {$("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
            fileObjName: 'file',
            fileTypeDesc: "文件上传",
            fileTypeExts: "*.gif; *.jpg; *.png; *.jpeg; *.bmp; ", //默认*.*
            method: 'post',
            multi: false, // 是否能选择多个文件
            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout: 1,
            queueSizeLimit: 1,
            fileSizeLimit: 4 * 1024,
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
</script>
</html>