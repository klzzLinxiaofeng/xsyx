<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
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
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="lessonplan_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span style="color: red">*</span> 学年：
								</label>
								<div class="controls">
									<select id="schoolYear" name="schoolYear" disabled="disabled" class="{required : true}">
										<c:if test="${pjTeachingPlan.id == null || lessonPlan.id == ''}">
											<option value="${termCurrent.schoolYear}">${termCurrent.schoolYearName}</option>
										</c:if>
										<c:if test="${pjTeachingPlan.id != null && lessonPlan.id != ''}">
											<option value="${schoolYear.year}">${schoolYear.name}</option>
										</c:if>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red">*</span> 学期：
								</label>
								<div class="controls">
									<select id="termCode" name="termCode" disabled="disabled" class="{required : true}">
										<c:if test="${pjTeachingPlan.id == null || lessonPlan.id == ''}">
											<option value="${termCurrent.schoolTermCode}">${termCurrent.schoolTermName}</option>
										</c:if>
										<c:if test="${pjTeachingPlan.id != null && lessonPlan.id != ''}">
											<option value="${schoolTerm.code}">${schoolTerm.name}</option>
										</c:if>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red">*</span> 年级：
								</label>
								<div class="controls">
								<select id="gradeCode" name="gradeCode" class="{required : true,maxlength:50}"></select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red">*</span> 科目：
								</label>
								<div class="controls">
									<select id="subjectCode" name="subjectCode" class="{required : true,maxlength:50}">
										<option value="">请选择</option>			
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red">*</span>上传教学计划：
								</label>
								<div class="controls">
									<c:if test="${isCK != 'disable'}">
										<input type="hidden" id="uploader" name="uploader">
									</c:if>
									<input type="hidden" name="fileId" id="fileId" value="${pjTeachingPlan.fileId}"/>
									<p id="fileName">
										${entity.entityFile.fileName}
									</p>
									<c:if test="${isCK == 'disable'}">
										<a target="_blank" class="btn btn-success cz_btn" href='<entity:getHttpUrl uuid="${pjTeachingPlan.fileId}"/>'>点击下载文件</a>
									</c:if>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${pjTeachingPlan.id}" />
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
		 
		 getGradeBySchoolYear();
		 
		 getSubjectByGradeCodeInit();
		 
		 getSubjectByGradeCode("${pjTeachingPlan.gradeCode}");
		 
		 //根据 是否是查看 设置表单不可用
		 allDisable();
	});
	
	//校验
	function initValidator() {
		return $("#lessonplan_form").validate({
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
			var $requestData = formData2JSONObj("#lessonplan_form");
			var url = "${ctp}/generalTeachingAffair/pjteachingplan/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/generalTeachingAffair/pjteachingplan/" + $id;
			}
			if($("#fileId").val()==""){
				$.alert("请上传教案！");
				return;
			}
			if($("#schoolYear").val()=="" || $("#schoolYear").val()=="undefined"){
				$.alert("没有设置学年，不能添加！");
				return;
			}
			if($("#termCode").val()=="" || $("#termCode").val()=="undefined"){
				$.alert("没有设置学期，不能添加！");
				return;
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
					} else if("error" === data.info){
						$.error("操作失败");
					}else{
						$.error("该用户不是教师，创建教案失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	
	//根据学年获取年级列表
	function getGradeBySchoolYear(){
		var schoolYear = $("#schoolYear").val();
		$("#gradeCode").html("");
		var htmlSelect = "<option value=''>请选择</option>";
		var url = "${ctp}/generalTeachingAffair/lessonplan/getGradeBySchoolYear?schoolYear="+schoolYear;
		$.post(url,null,function(data){
			for(var flag in data){
				htmlSelect += "<option value='" + data[flag].uniGradeCode + "'>"+ data[flag].name +"</option>"
			}
			$("#gradeCode").append(htmlSelect);
			$("#gradeCode").val("${pjTeachingPlan.gradeCode}");
			$("#gradeCode").chosen();
		},'json')
	}
	
	//根据年级获取科目
	function getSubjectByGradeCodeInit(){
		$("#gradeCode").change("on",function(){
			$("#subjectCode").html("").append("<option value=''>请选择</option>");
			var currentValue = $(this).val();
			if(currentValue == ""){
				$("#subjectCode").html("").append("<option value=''>请选择</option>");			
			}else{
				getSubjectByGradeCode(currentValue);
			}
		});
	}
	
	//根据年级获取科目
	function getSubjectByGradeCode(gradeCode){
		$("#subjectCode").html("");
		var htmlSelect = "<option value=''>请选择</option>";
		var url = "${ctp}/generalTeachingAffair/lessonplan/getSubjectByGradeCode?gradeCode="+gradeCode;
		$.post(url,null,function(data){
			for(var flag in data){
				htmlSelect += "<option value='" + data[flag].subjectCode + "'>"+ data[flag].subjectName +"</option>"
			}
			$("#subjectCode").append(htmlSelect);
			$("#subjectCode").val("${pjTeachingPlan.subjectCode}");
			$("#subjectCode").trigger("liszt:updated"); 
			$("#subjectCode").chosen();
		},'json')
	}
	
	//文件上传
	function uploadFile(){
    	var obj = $("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'
							},
							fileObjName : 'file',
							fileTypeDesc : "文件上传",
// 							fileTypeExts : "*.*", //默认*.*
							fileTypeExts : '*.doc; *.docx;',
							method : 'post',
							multi : true, // 是否能选择多个文件
							auto : true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
							removeTimeout : 1,
							queueSizeLimit : 1,
							fileSizeLimit : 50 * 1024,
							buttonText : "上传教学计划",
							requeueErrors : false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
							height : 20,
							width : 90,
							onUploadSuccess : function(file, data, response) {
								var $jsonObj = eval("(" + data + ")");
								var fileUuid = $jsonObj.uuid;
								$.success("上传成功!", 9);
								$("#fileId").val(fileUuid);
								$("#fileName").html($jsonObj.realFileName);
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
	
	
	//获取没有后缀名的文件名
	function getFileName(fileName){
		var strs= new Array();
		strs = fileName.split(".");
		var name = "";
		for(var i = 0; i < strs.length-1; i++){
			name += strs[i];
		}
		return name;
	}
	
	//如果是点击查看，则所有按钮文本框等的不可编辑
	function allDisable(){
		if("${isCK}" == "disable"){
			$("#lessonplan_form").disable();
			$(".cz_btn").attr("disabled", false);
		}
	}
</script>
</html>