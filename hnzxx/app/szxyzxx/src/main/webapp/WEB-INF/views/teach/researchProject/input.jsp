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
	width: 35%;
	display: inline-block;
	padding-left: 10px;
	display: block;
}
.remove_fj{
	color:#666;
	margin-left:15px;
	font-size:16px;
	font-family:"微软雅黑";
	width:18px;
	height:18px;
	text-align:center;
	line-height:18px;
	display:inline-block;
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
					<form class="form-horizontal tan_form" id="researchproject_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>立项级别：
								</label>
								<div class="controls">
									<select id="level" name="level" class="span4" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}></select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>进展级别：
								</label>
								<div class="controls">
									<select id="progressLevel" name="progressLevel" class="span4" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}></select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>项目编号：
								</label>
								<div class="controls">
								<input type="text" id="code" name="code" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${researchProject.code}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>项目名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${researchProject.name}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									负责人：
								</label>
								<div class="controls">
								<textarea rows="3" cols="20" id="master" name="master" class="span4 add_renyuan" data-id="${researchProject.masterId }" placeholder="批量添加负责人"  ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} >${researchProject.masterName}</textarea>
								<input type="hidden" id="masterId" name="masterId" value="${researchProject.masterId }">
								<button class="btn btn-danger" onclick="emptyMaster();" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}>清空</button>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									参与人员：
								</label>
								<div class="controls">
								<textarea rows="3" cols="20" id="attendees" name="attendees" class="span4 add_renyuan" data-id="${researchProject.attendeesId }" placeholder="批量添加参与人员"  ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} >${researchProject.attendeesName}</textarea>
								<input type="hidden" id="attendeesId" name="attendeesId" value="${researchProject.attendeesId }">
								<button class="btn btn-danger" onclick="emptyAttendees();" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}>清空</button>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>项目开始日期：
								</label>
								<div class="controls"> 
								<input type="text" id="beginDate" name="beginDate" class="span4" placeholder="" value="<fmt:formatDate value='${researchProject.beginDate }' />" onclick="WdatePicker();" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}>
								</div>
							</div> 
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>项目结束时间：
								</label>
								<div class="controls"> 
								<input type="text" id="endDate" name="endDate" class="span4" placeholder="" value="<fmt:formatDate value='${researchProject.endDate }' />" onclick="WdatePicker();" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}>
								</div>
							</div> 
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>获得奖项：
								</label>
								<div class="controls">
								<input type="text" id="prize" name="prize" class="span4" placeholder=""  ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${researchProject.prize}" >
								</div>
							</div>
							
							<div class="control-group">
							<label class="control-label">附件</label>
							<div class="controls update_img" style="padding-top:5px;">
								<c:choose>
									<c:when test="${not empty researchProject.fileUuid }">
										<c:forEach items="${entityList}" var="entity" varStatus="i">
											<p style='display:inline-block;margin-bottom:0;width:300px;overflow:hidden;'><a target="_blank" id="${i.index+1}" href='<entity:getHttpUrl uuid="${entity.uuid}"/>'>${entity.fileName}</a><a id="b${i.index}" onclick="deleteFile(this);" href="javascript:void(0);" class="remove_fj" ${isCK != null && isCK != '' ? "style='display:none'" : ""}>x</a></p>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<p style='display:inline-block;margin-bottom:0;width:300px;overflow:hidden;'><a taget="_blank" id="a"></a></p>
										<c:choose>
											<c:when test="${isCK != null && isCK != '' }"></c:when>
											<c:otherwise><input type="hidden" id="uploader" name="uploader"/></c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								<input type="hidden" id="entityId" name="fileUuid" value="${researchProject.fileUuid }"/>
							</div>
							</div>
							
						<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center">
							<c:if test="${isCK == null || isCK == ''}">
								<input type="hidden" id="id" name="id" value="${researchProject.id}" />
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
		$.jcGcSelector("#level", {"tc" : "XY-JY-LXJB"}, "${researchProject.level}", function(selector) {
			selector.chosen();
		});
		
		$.jcGcSelector("#progressLevel", {"tc" : "XY-JY-JZJB"}, "${researchProject.progressLevel}", function(selector) {
			selector.chosen();
		});
		
		$.createMemberSelector({
			"inputIdSelector" : "#master",
			"isOnTopWindow" : true
		});
		$.createMemberSelector({
			"inputIdSelector" : "#attendees",
			"isOnTopWindow" : true
		});
		checker = initValidator();
		uploadFile();
	});
	
	function initValidator() {
		return $("#researchproject_form").validate({
			errorClass : "myerror",
			rules : {
				"level" : {
					selectNone : true
				},
				"progressLevel" : {
					selectNone : true
				},
				"code" : {
					required : true,
					maxlength : 30,
					stringCheck:true,
					remote : {
						async : false,
						type : "GET",
						url : "${ctp}/teach/researchProject/checker",
						data : {
							'dxlx' : 'code',
							'code' : function() {
								return $("#code").val();
							},
							'id' : function() {
								return $("#id").val();
							}
						}
					}
				},
				"name" : {
					required : true,
					minlength : 1,
					maxlength : 20,
					stringCheck:true
				},
				"master" : {
					required : true,
					maxlength : 3000
				},
				"attendees" : {
					required : true,
					maxlength : 3000
				},
				"beginDate" : {
					required : true
				},
				"endDate" : {
					required : true,
					greaterThanDate1:true
				},
				"prize" : {
					required : true,
					minlength : 1,
					maxlength : 20,
				}
			},
			messages : {
				"code":{
					remote:"该编号已存在"
				},
				"master" : {
					maxlength : "添加人数过多"
				},
				"attendees" : {
					maxlength : "添加人数过多"
				},
				"name" : {
					stringCheck:"只能输入中文字符"
				},
				"code" : {
					stringCheck:"请输入合法字符"
				}
			}
		});
	}
	
	$.validator.addMethod("greaterThanDate1", function(value, element, param) {
		var result = true;
		var $this = $(element);
		var begin = new Date($("#beginDate").val().replace(/-/g, "/"));
		var end = new Date(value.replace(/-/g, "/"));
	   if(end - begin< 0){
	   	result = false;
	   }
	   return this.optional(element) || result;
	}, "结束时间必须大于开始时间");
	
	function emptyMaster() {
		$.confirm("确定执行此次操作？", function() {
			$("#master_select").val("");
			$("#masterId").val("");
			$("#master").val("");
		});
	}
	
	//清除参与人员
	function emptyAttendees() {
		$.confirm("确定执行此次操作？", function() {
			$("#attendees_select").val("");
			$("#attendeesId").val("");
			$("#attendees").val("");
		});
	}
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#researchproject_form");
			//alert(JSON.stringify($requestData));
			var url = "${ctp}/teach/researchProject/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/researchProject/" + $id;
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
	
	var step = 0;
	function uploadFile(){
    	var obj = setTimeout(function() {$("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
            fileObjName: 'file',
            fileTypeDesc: "文件上传",
            fileTypeExts: "*.gif; *.jpg; *.png; *.jpeg; *.bmp; *.doc; *.docx; *.xls; *.xlsx", //默认*.*
            method: 'post',
            multi: true, // 是否能选择多个文件
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
           	$("#entityId").val($("#entityId").val()+";"+$jsonObj.uuid);
           	//$("#a").html($("#a").html()+"<br />"+$jsonObj.realFileName);
           	var a = "a"+step;
            //	alert(a);
           	$("#a").append("<p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'><a taget='_blank' id='"+ a +"'>'"+ $jsonObj.realFileName +"'</a></p>");
           	
           	//$("#a").attr('href',$jsonObj.url); 
           	//$("#a").attr('target','_blank');
            step++;
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
	
	function deleteFile(obj){
		$.confirm("确定执行此次操作？", function() {
			$(obj).parent().remove();
			var id=$(obj).prev().attr("id");
			var entity = $("#entityId").val();
			ch = new Array();
			ch = entity.split(";");
			ch.splice(id,id);
			//alert(ch[id]);
			var fileUuid = ch.join(";");
			$("#entityId").val(fileUuid);
		});
	}
	 
</script>
</html>