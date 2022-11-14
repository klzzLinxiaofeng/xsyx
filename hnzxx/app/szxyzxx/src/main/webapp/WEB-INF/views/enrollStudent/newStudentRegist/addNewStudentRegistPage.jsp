<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.min.js"></script>
<title>添加报名学生</title>
<style>
	.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}
.row-fluid .span2 {
	width: 200px;
}
.images .uploadify {
    background:url("${pageContext.request.contextPath}/res/css/extra/images/up_img.jpg") no-repeat;
    display: block;
    height: 30px;
    width: 98px;
}
.form-horizontal{
	position:relative;
}
.images img{
	position:absolute;
	width:156px;
	height:187px;
	left:420px;
	top:0px;
}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets"  style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="newStudentRegist_form" name="newStudentRegist_form" action="javascript:void(0);">
						<input type="hidden" id="id" name="id" value="${newStudent.id }"/>
						
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>姓名</label>
							<div class="controls">
								<input type="text" name="name" id="name" value="${newStudent.name }" readonly="readonly" class="span2"/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">学生类别:</label>
							<div class="controls">
								<select id="studentType" name="studentType" class="span2"></select>
							</div>
						</div>

						<div class="control-group">
							<label class="control-label">学籍号:</label>
							<div class="controls">
								<input type="text" name="studentNum" id="studentNum" class="span2"/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">入学时间:</label>
							<div class="controls">
								<input type="text" id="entrollSchoolDate" name="entrollSchoolDate" value="" class="span2" placeholder="入学时间" onclick="WdatePicker();"/>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">在读状态:</label>
							<div class="controls">
								<select name="readingState" id="readingState" class="span2" disabled="disabled"></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>角色:</label>
							<div class="controls">
								<select id="role" name="role"></select>
							</div>
						</div>
						<div class="control-group images">
							<label class="control-label">个人照片:</label>
							<div class="controls">
								<input type="file" id="uploader" name="uploader" />
								<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" id="imgId">
								<input type="hidden" id="entityId" name="entityId" value=""/>
							</div>
						</div>
						
						<div class="clear"></div>
						<div class="form-actions tan_bottom">
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var checker="";
$(function(){
	//学生类别
    var $requestData = {tc : "JY-XSLB","level":"1"};
	$.jcGcSelector("#studentType", $requestData, "", function() {
		
	});
	//在读状态
	$.jcGcSelector("#readingState", {tc : "JY-XSDQZT"}, "", function() {
		//2016-03-15 修改：默认在读状态是在读  并不可修改状态
		$("#readingState").val("01");
	});
	//获取角色 JSON 数据  1为教师 2为管理员 4为学生的下拉列表
	$.RoleSelector({
		"condition" : {
			"userType" : "4"
		}
	});
	checker =  initValidator();
	uploadImageFile();
});

function initValidator() {
	return $("#newStudentRegist_form").validate({
			errorClass : "myerror",
			rules : {
				"role":{
					required : true
				}
			},
			messages : {
				"role":{
					required:"角色必选"
				}
			}
		});
	}
	
	function uploadImageFile(){
		var obj = $("#uploader").uploadify({
	        swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
	        uploader: '${pageContext.request.contextPath}/uploader/common',
	        formData: {'jsessionId': '<%=request.getSession().getId()%>'},
	        fileObjName: 'file',
	        fileTypeDesc: "图片上传",
	        fileTypeExts: "*.jpg",
	        method: 'post',
	        multi: false, // 是否能选择多个文件
	        auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
	        removeTimeout: 1,
	        queueSizeLimit: 1,
	        fileSizeLimit: 4 * 1024,
	        buttonText: "",
	        requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
	        height: 30,
	        width: 98,
	        onUploadSuccess: function(file, data, response) {
	       	 var $jsonObj = eval("(" + data + ")");
	       	 $("#entityId").val($jsonObj.uuid);
	       	 $("#imgId").attr('src',$jsonObj.url);
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

function saveOrUpdate(){
	if (checker.form()) {
		var loader = new loadLayer();
		var $requestData = formData2JSONObj("#newStudentRegist_form");
		var url = "${pageContext.request.contextPath}/entrollStudent/newStudentRegist/addNewStudentRegist";
		loader.show();
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				if("success" === data.info) {
					$.success('保存成功');
					if(parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
					$.closeWindow();
				} else {
					$.error(data.info);
				}
			}else{
				$.error("服务器异常");
			}
			loader.close();
		});
	}
}
</script>
</html>