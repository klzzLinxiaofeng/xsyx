<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
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
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="india_form"
						action="javascript:void(0);">

						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>部门：
							</label>
							<div class="controls">
								<select class="span3" style="width: 150px;" name="departmentId" id="departmentId">
								  <c:forEach items="${departmentList }" var="department">
								    <option value="${ department.departmentId}" <c:if test="${department.departmentId==d.id}">selected</c:if>>${ department.departmentName}</option>
								  </c:forEach>
								</select>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>文印标题：
							</label>
							<div class="controls">
								<input class="input-medium required" type="text" name="title"  id="title"
									placeholder="文印标题" value="${india.title}"
									style="height: 30px; line-height: 30px; width: 270px;">
							</div>
						</div>

						<div class="control-group">
							<label class="control-label"><span class="red">*</span> 上传文件： </label>
							<div class="controls update_img">
							    <c:choose>
									<c:when test="${not empty india.uploadFile }">
										<p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'><a target="_blank" id="a" href='<entity:getHttpUrl uuid="${india.uploadFile}"/>'>${entity.realFileName}</a><button id="b" onclick="deleteFile();" class="btn btn-red" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>删除</button></p>
									</c:when>
									<c:otherwise>
										<p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'><a taget="_blank" id="a"></a></p>
										<c:choose>
											<c:when test="${isCK != null && isCk != '' }"></c:when>
											<c:otherwise><input type="file" id="uploader" name="uploader"/></c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							    <input type="hidden" id="entityId" name="uploadFile" value="${india.uploadFile}"/>
							
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">说明（附注）：
							</label>
							<div class="controls">
								<div class="textarea">
									<textarea id="remark" name="remark" rows="5" cols="5"
										class="span8  " style="width: 270px;">${india.remark}</textarea>

								</div>
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${india.id}" />
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
		return $("#india_form").validate({
			errorClass : "myerror",
			rules : {
				"departmentId":{
					selectNone : true
				},
				"uploadFile":{
					required:true
				}
			},
			messages : {
				"departmentId":{
					reqired : "部门必选"
				},
				"uploadFile":{
					required:"请上传文件"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#india_form");
			var url = "${pageContext.request.contextPath}/office/india/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/office/india/" + $id;
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
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
            fileObjName: 'file',
            fileTypeDesc: "文件上传",
            fileTypeExts: "*.*", //默认*.*
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
	
</script>
</html>