<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	width: 227px;
}

.myerror {
	color: red !important;
	width: 35%;
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
    .control-group{
    	float:left;
    	margin: 0;
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
								<select id="xn" name="xn"></select>
							</div>
							</div>
							
							<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>年级</label>
							<div class="controls">
								<select id="nj" name="nj"></select>
							</div>
							</div>
							
							<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>班级</label>
							<div class="controls">
								<select id="bj" name="teamId"></select>
							</div>
							</div>
							
							<div class="control-group">
							<label class="control-label"><font style="color: red">*</font>姓名</label>
							<div class="controls">
								<select id="stu" name="studentId"></select>
							</div>
							</div>
							
							<div class="control-group" style="float:left">
							<label class="control-label"><font style="color: red">*</font>健康类型</label>
							<div class="controls">
								<select id="healthType" name="type" style="width:222px;"></select>
							</div>
							</div>
							
							<div class="control-group">
							<label class="control-label">附件</label>
							<div class="controls">
								<p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'><a target="_blank" id="a"></a></p>
								<input type="hidden" id="uploader" name="uploader"/>
								<input type="hidden" id="entityId" name="accessory" value=""/>
							</div>
							</div>
							
							<div class="control-group" style="width:60%;">
							<label class="control-label">备注</label>
							<div class="controls">
								<textarea id="remark" name="remark" rows="3" cols="1" style="width:220px;"></textarea>
							</div>
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id"/>
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
		checker = initValidator();
		$.initCascadeSelector({"type" : "stu"});
		$.jcGcSelector("#healthType", {"tc" : "GB-JKZK3"});
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
				"teamId" : {
					selectNone : true
				},
				"studentId" : {
					selectNone : true
				},
				"type" : {
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
			var url = "${ctp}/teach/studentHealthArchive/add";
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
	
	function uploadFile(){
    	var obj = setTimeout(function() {$("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
            fileObjName: 'file',
            fileTypeDesc: "文件上传",
            fileTypeExts: "*.gif; *.jpg; *.png; *.jpeg; *.bmp; *.doc; *.docx; *.xls; *.xlsx", //默认*.*
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