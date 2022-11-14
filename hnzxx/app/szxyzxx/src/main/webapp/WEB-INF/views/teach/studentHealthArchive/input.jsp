<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
	<%--已弃用--%>
<%--<%@ include file="/views/embedded/plugin/uploadify.jsp"%>--%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">

<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 227px;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
/* .uploadify{
	cursor:pointer;
	padding: 5px 12px;
	border: rgba(0, 0, 0, .1) 1px solid;
	border-radius: 3px;
	color: #ffffff;
	background-color: #EFAD4D;
} */
 .form-horizontal .control-group{
    	width:45%;
    	display:inline-block;
    }
    select[disabled],textarea[disabled]{
      cursor: default;
      }
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="studentHealthArchive_form" action="javascript:void(0);">
							
							<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>学年</label>
							<div class="controls">
								<select id="xn" name="xn" disabled="disabled"></select>
							</div>
							</div>
							
							<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>年级</label>
							<div class="controls">
								<select id="nj" name="nj" disabled="disabled"></select>
							</div>
							</div>
							
							<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>班级</label>
							<div class="controls">
								<select id="bj" name="bj" disabled="disabled"></select>
							</div>
							</div>
							
							<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>姓名</label>
							<div class="controls">
								<input type="text" id="stuName" name="stuName" class="span4" placeholder=""
									   value="${studentHealthArchiveVo.stuName}" style="width: 70%" disabled>
							</div>
							</div>
							
							<div class="control-group" style="float:left">
							<label class="control-label"><font style="color: red">*</font>健康类型</label>
							<div class="controls">
								<input type="text" id="types" name="types" class="span4" placeholder=""
									   value="${studentHealthArchiveVo.types}" style="width: 70%" disabled>
<%--
								<textarea id="healthType" style="width:220px;" name="types" rows="3" cols="1" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></textarea>
--%>
<%--
								<select id="healthType" name="type" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
${studentHealthArchiveVo.types}
--%>

							</div>
							</div>
							
							<%--<div class="control-group">
							<label class="control-label">附件</label>
							<div class="controls update_img">
								<c:choose>
									<c:when test="${not empty studentHealthArchiveVo.accessory }">
										<p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'><a target="_blank" id="a" href='<entity:getHttpUrl uuid="${studentHealthArchiveVo.accessory}"/>'>${entity.fileName}</a><button id="b" onclick="deleteFile();" class="btn btn-red" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>删除</button></p>
									</c:when>
									<c:otherwise>
										<p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'><a taget="_blank" id="a"></a></p>
										<c:choose>
											<c:when test="${isCK != null && isCk != '' }"></c:when>
											<c:otherwise><input type="hidden" id="uploader" name="uploader"/></c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								<input type="hidden" id="entityId" name="accessory" value="${studentHealthArchiveVo.accessory }"/>
							</div>
							</div>--%>

						<div class="control-group">
							<label class="control-label">备注</label>
							<div class="controls">
								<textarea id="remark" style="width:220px;" name="remark" rows="3" cols="1" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>${studentHealthArchiveVo.remark}</textarea>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">图片说明</label>
							<c:forEach items="${imgUrls}" var="img">
								<img src="${img}" onclick="Change(this);"
									 style="width: 260px; height: 130px;">
							</c:forEach>

						</div>


							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="teamStudentId" name="teamStudentId" value="${studentHealthArchiveVo.id}" />
							<c:if test="${isCK == null || isCk == ''}">
							<input type="hidden" id="id" name="id" value="${studentHealthArchiveVo.id}" />
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
		// uploadFile();
		checker = initValidator();
		$.initCascadeSelector({
			"type" : "stu",
			"isEchoSelected" : true,
			"yearSelectedVal" : "${studentHealthArchiveVo.schoolYear}",
			"gradeSelectedVal" : "${studentHealthArchiveVo.gradeId}",
			"teamSelectedVal" : "${studentHealthArchiveVo.teamId}",
			"stuSelectedVal" : "${studentHealthArchiveVo.studentId}" });

	});
	
	function initValidator() {
		return $("#studentHealthArchive_form").validate({
			errorClass : "myerror",
			rules : {
				"xn" : {
					selectNone : true
				},
				"nj" : {
					selectNone : true
				},
				"bj" : {
					selectNone : true
				},
				"stu" : {
					selectNone : true
				}
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
			var $requestData = formData2JSONObj("#studentHealthArchive_form");
			var url = "${ctp}/teach/studentHealthArchive/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/studentHealthArchive/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('保存成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						
					}
				}else{
					$.error("保存失败");
				}
				loader.close();
			});
		}
	}
	
<%--/*	function uploadFile(){--%>
<%--		--%>
<%--    	var obj = setTimeout(function() {$("#uploader").uploadify({--%>
<%--            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',--%>
<%--            uploader: '${pageContext.request.contextPath}/uploader/common',--%>
<%--            formData: {'jsessionId': '<%=request.getSession().getId()%>'},--%>
<%--            fileObjName: 'file',--%>
<%--            fileTypeDesc: "文件上传",--%>
<%--            fileTypeExts: "*.gif; *.jpg; *.png; *.jpeg; *.bmp; *.doc; *.docx; *.xls; *.xlsx", //默认*.*--%>
<%--            method: 'post',--%>
<%--            multi: false, // 是否能选择多个文件--%>
<%--            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.--%>
<%--            removeTimeout: 1,--%>
<%--            queueSizeLimit: 1,--%>
<%--            fileSizeLimit: 4 * 1024,--%>
<%--            buttonText: "上传文件",--%>
<%--            requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.--%>
<%--            height: 20,--%>
<%--            width: 70,--%>
<%--            onUploadSuccess: function(file, data, response) {--%>
<%--           	 var $jsonObj = eval("(" + data + ")");--%>
<%--           	 $("#entityId").val($jsonObj.uuid);--%>
<%--           	 $("#a").text($jsonObj.realFileName);--%>
<%--           	 $("#a").attr('href',$jsonObj.url); --%>
<%--           	 $("#a").attr('target','_blank');--%>
<%--           	--%>
<%--            },--%>
<%--            onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）--%>
<%--                $("#infoBox").prev("p").css("display", "none");--%>
<%--                $("#infoBox").css("display", "block");--%>
<%--            },--%>
<%--            onUploadError: function(file, errorCode, errorMsg, errorString) {--%>
<%--                $.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);--%>
<%--            }--%>
<%--        })},10);--%>
<%--    }*/--%>
	
	/*function deleteFile(){
		$.confirm("确定执行此次操作？", function() {
			executeDel();
		});
	}*/
	
	/*function executeDel(){
		$("#a").text("");
      	$("#a").attr('href', ""); 
		$("#entityId").val("");
		$("#b").remove();
		$(".update_img").children().append("<input type='file' id='uploader' name='uploader'/>")
		uploadFile();
	}*/


	function Change(obj) {
		var imgSrc = $(obj).attr("src");
		window.open(imgSrc);
	}
	
	</script>
</html>