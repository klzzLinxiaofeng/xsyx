<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<html>
<head>
<title></title>
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
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="aptTaskScore_form" action="javascript:void(0);">
					<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>日期：
								</label>
								<div class="controls">
									<input type="text" id="checkTime" name="checkTime" placeholder="考核日期" 
										value="<fmt:formatDate value="${empty taskScore ? now : taskScore.checkTime}" pattern="yyyy-MM-dd"/>" 
										class="span13" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDateValue\')}',maxDate:'#F{$dp.$D(\'finishDateValue\')}'});">
									<input type="hidden" id="startDateValue" name="startDateValue" value="<fmt:formatDate value="${task.startDate}" pattern="yyyy-MM-dd"/>"/>
									<input type="hidden" id="finishDateValue" name="finishDateValue" value="<fmt:formatDate value="${task.finishDate}" pattern="yyyy-MM-dd"/>"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>分值：
								</label>
								<div class="controls">
								<input type="text" id="score" name="score" class="span13" placeholder="分值" value="${taskScore.score}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>被考核人：
								</label>
								<div class="controls">
<%-- 									<input type="text" id="teacherName" class="span13" placeholder="被考核人" value="${teacherName}"> --%>
<%-- 									<input type="hidden" id="teacherId" name="teacherId" value="${taskScore.teacherId}"> --%>
<!-- 									<input type="button" id="cleanBtn" value="清除"/> -->
									<select id="teacherId" name="teacherId" class="span13">
										<option value="">请选择</option>
										<c:forEach items="${teachers}" var="teacher">
											<c:choose>
												<c:when test="${task.scopeType == 1}">
													<option value="${teacher.id}" <c:if test="${taskScore.teacherId == teacher.id}">selected="selected"</c:if>>${teacher.name}</option>													
												</c:when>
												<c:otherwise>
													<option value="${teacher.teacherId}" <c:if test="${taskScore.teacherId == teacher.teacherId}">selected="selected"</c:if>>${teacher.teacherName}</option>
												</c:otherwise>
											</c:choose>
										</c:forEach>									
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									附件：
								</label>
								<div class="controls">
									<input type="hidden" name="attachmentFileUuid" id="attachmentFileUuid" value="${taskScore.attachmentFileUuid}"/>
									<input type="hidden" id="uploaderFile"/>
									<div id="fj" style="display:inline-block;">
										<a target="_blank" href="<entity:getHttpUrl uuid="${taskScore.attachmentFileUuid}"/>">
											${entity.fileName}
										</a>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
									<textarea rows="5" cols="5" id="description" name="description" class="span13" placeholder="备注">${taskScore.description}</textarea>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${taskScore.id}" />
							<input type="hidden" id="taskItemId" name="aptTaskItemId" value="${empty taskScore ? taskItemId : taskScore.aptTaskItemId}" />
							
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
		
		//初始化教师选择弹出框
// 		var option = {
// 				"inputIdSelector" : "#teacherName",
// 				"ry_type" : "teach",
// 				"enableBatch" : false,
// 				"layerOp" : {
// 					"type" : 2,
// 					"shift" : 'top'
// 				}
// 			};
// 		$.createMemberSelector(option);
// 		$("#teacherName_select").val("${teacherName}");
		//清除
// 		$("#cleanBtn").click(function(){
// 			$("#teacherName_select").val("");
// 			$("#teacherId").val("");
// 		});
		$("#teacherId").chosen();
		uploadFile();
		checker = initValidator();
	});
	
	function selectedHandler(data){
		if(data != null && data != "undefined"){
			$("#teacherName_select").val(data.names);
			$("#teacherId").val(data.ids);
			$.closeWindowByName(data.windowName);
		}
	}
	
	function initValidator() {
		return $("#aptTaskScore_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
					accCheck : false,
					maxlength : 30,
// 					remote : {
// 						async : false,
// 						type : "GET",
// 						url : "${pageContext.request.contextPath}/schoolAffair/aptRuleItem/itemChecker",
// 						data : {
// 							'name' : function() {
// 								return encodeURI($("#name").val());
// 							}
// 						}
// 					}
				},
				"checkTime" : {
					required : true
				},
				"score" : {
					required : true,
					number : true
				},
				"teacherId" : {
					required : true
				}
			},
			messages : {
// 				"name" : {
// 					remote : "名称已存在"
// 				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#aptTaskScore_form");
			var url = "${ctp}/schoolAffair/aptTaskScore/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolAffair/aptTaskScore/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					if("success" === data.info) {
						$.success('操作成功');
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						$.error("操作失败," + data.pk);
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	function uploadFile(){
    	var obj = setTimeout(function(){$("#uploaderFile").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
							fileObjName : 'file',
							fileTypeDesc : "文件上传",
// 							fileTypeExts : "*.*", //默认*.*
							fileTypeExts : '*.doc; *.txt; *.docx;*.gif; *.jpg; *.png;*.jpeg;*.bmp',
							method : 'post',
							multi : true, // 是否能选择多个文件
							auto : true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
							removeTimeout : 1,
							queueSizeLimit : 1,
							fileSizeLimit : 4 * 1024,
							buttonText : "上传附件",
							requeueErrors : false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
							height : 20,
							width : 70,
							onUploadSuccess : function(file, data, response) {
								var $jsonObj = eval("(" + data + ")");
								var file ='<a target="_blank" href="' + $jsonObj.url +'">' + $jsonObj.realFileName + '</a>'
										+ '&nbsp;&nbsp;&nbsp;';
								$.success("上传成功!", 9);
								$("#fj").html(file);
								$("#attachmentFileUuid").val($jsonObj.uuid);
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
    	})},10);
	}
</script>
</html>