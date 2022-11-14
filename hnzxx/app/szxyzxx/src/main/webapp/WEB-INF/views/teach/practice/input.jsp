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
	width: 227px;
}

.row-fluid .span5 {
	width: 30px;
	margin-right: 3px;
}  
.myerror {
	color: red !important;
	width: 25%;
	display: inline-block;
	padding-left: 10px;
}
.control-group{
	float:left;
	width:50%;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="practice_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>学年：
								</label>
								<div class="controls">
									<select id="schoolYear" name="schoolYear" class="span4 chzn-select" onchange="onChangeSchoolYear();" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}>
									</select>
								</div>
							</div>		
							
							<div class="control-group" style="margin-bottom: 32px;">
								<label class="control-label">
									<span class="red">*</span>学期：
								</label>
								<div class="controls">
									<select id="termCode" name="termCode" class="span4 chzn-select" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}>
									</select>
								</div>
							</div>	
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>实践标题：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${practice.name}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>实践日期
								</label>
								<div class="controls"> 
								<input type="text" id="practiceDate" name="practiceDate" class="span4" placeholder="请输入时间" value="<fmt:formatDate value='${practice.practiceDate }' />" onclick="WdatePicker();" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}>
								</div>
							</div> 
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>实践地点：
								</label>
								<div class="controls">
								<input type="text" id="position" name="position" class="span4" placeholder=""  ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${practice.position}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									组织人员：
								</label>
								<div class="controls">
								<textarea rows="3" cols="20" id="master" name="master" class="span4 add_renyuan" data-id="${practice.masterId }" placeholder="批量添加组织者"  ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} >${practice.masterName}</textarea>
<%-- 								<input type="text" id="master" name="master" class="span4" data-id="${practice.masterId }" placeholder="批量添加组织者" value="${practice.masterName}"  ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} > --%>
								<input type="hidden" id="masterId" name="masterId" value="${practice.masterId }">
								<button class="btn btn-danger" onclick="emptyMaster();" ${isCK != null && isCK != '' ? "style='display:none'" : ""}>清空</button>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									参与教职工：
								</label>
								<div class="controls">
								<textarea rows="3" cols="20" id="teach" name="teach" class="span4 add_renyuan" data-id="${practice.teachId }" placeholder="批量添加参与教职工"  ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} >${practice.teachName}</textarea>
								<%-- <input type="text" id="teach" name="teach" class="span4" data-id="${practice.teachId }" placeholder="批量添加参与教职工" value="${practice.teachName}" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} > --%>
								<input type="hidden" id="teachId" name="teachId" value="${practice.teachId }">
								<button class="btn btn-danger" onclick="emptyTeach();" ${isCK != null && isCK != '' ? "style='display:none'" : ""}>清空</button>
								</div>
							</div>	
							
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									参与学生：
								</label>
								<div class="controls">
								<textarea rows="3" cols="20" id="student" name="student" class="span4 add_renyuan" data-id="${practice.studentId }" placeholder="批量添加参与学生"  ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} >${practice.studentName}</textarea>
								<%-- <input type="text" id="student" name="student" class="span4" data-id="${practice.studentId }" placeholder="批量添加参与学生" value="${practice.studentName}" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} > --%>
								<input type="hidden" id="studentId" name="studentId" value="${practice.studentId }">
								<button class="btn btn-danger" onclick="emptyStudent();" ${isCK != null && isCK != '' ? "style='display:none'" : ""}>清空</button>
								</div>
							</div>
							
							<div class="control-group">
							<label class="control-label">附件</label>
							<div class="controls update_img">
								<c:choose>
									<c:when test="${not empty practice.fileUuid }">
										<p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'><a target="_blank" id="a" href='<entity:getHttpUrl uuid="${practice.fileUuid}"/>'>${entity.fileName}</a><button id="b" onclick="deleteFile();" class="btn btn-red" ${isCK != null && isCK != '' ? "style='display:none'" : ""}>删除</button></p>
									</c:when>
									<c:otherwise>
										<p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'><a taget="_blank" id="a"></a></p>
										<c:choose>
											<c:when test="${isCK != null && isCK != '' }"></c:when>
											<c:otherwise><input type="hidden" id="uploader" name="uploader"/></c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								<input type="hidden" id="entityId" name="fileUuid" value="${practice.fileUuid }"/>
							</div>
							</div>
							
						<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center;">
							<c:if test="${isCK == null || isCK == ''}">
								<input type="hidden" id="id" name="id" value="${practice.id}" />
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
		$.SchoolYearSelector({
			"selector" : "#schoolYear",
			"selectedVal" : "${practice.schoolYear}",
			"afterHandler" : function() {
				
			}
		});
		onChangeSchoolYear();
		checker = initValidator();
		$.createMemberSelector({
			"inputIdSelector" : "#master",
			"isOnTopWindow" : true
		});
		$.createMemberSelector({
			"inputIdSelector" : "#teach",
			"isOnTopWindow" : true
		});
	   $.createMemberSelector({
			"inputIdSelector" : "#student",
			"ry_type":"stu",
			"isOnTopWindow" : true
		}); 
		uploadFile();
		
	});

	 function onChangeSchoolYear(){
		  var schoolYear =  $("#schoolYear").val();
		  $.getSchoolTerm({"schoolYear" : schoolYear}, function(data, status) {
				var $term = $("#termCode");
				$term.html("");
				$.each(data, function(index, value) {
					$term.append("<option value='" + value.code + "'>" + value.name + "</option>");
				});
			});
	  }
	 
	function initValidator() {
		return $("#practice_form").validate({
			errorClass : "myerror",
			rules : {
				"schoolYear" : {
					selectNone : true
				},
				"termCode" : {
					selectNone : true
				},
				"name" : {
					required : true,
					minlength : 1,
					maxlength : 20,
					stringCheck:true
				},
				"practiceDate" : {
					required : true
				},
				"position" : {
					required : true,
					maxlength : 30
				},
				"master" : {
					required : true,
					maxlength : 200
				},
				"teach" : {
					required : true,
					maxlength : 200
				},
				"student" : {
					required : true,
					maxlength : 200
				}
			},
			messages : {
				"name" : {
					stringCheck:"只能输入中文字符"
				},
				"master" : {
					maxlength : "添加人数过多"
				},
				"teach" : {
					maxlength : "添加人数过多"
				},
				"student" : {
					maxlength : "添加人数过多"
				},
			}
		});
	}
	//清除组织者文本框
	function emptyMaster() {
		$.confirm("确定执行此次操作？", function() {
			$("#master_select").val("");
			$("#masterId").val("");
			$("#master").val("");
		});
	}
	
	//清除参与教师
	function emptyTeach() {
		$.confirm("确定执行此次操作？", function() {
			$("#teach_select").val("");
			$("#teachId").val("");
			$("#teach").val("");
		});
	}
	
	//清除参与学生
	function emptyStudent() {
		$.confirm("确定执行此次操作？", function() {
			$("#student_select").val("");
			$("#studentId").val("");
			$("#student").val("");
		});
	}
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#practice_form");
			//alert(JSON.stringify($requestData));
			var url = "${pageContext.request.contextPath}/teach/practice/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/practice/" + $id;
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