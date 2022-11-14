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
	width: 15px;
	margin-right: 3px;
}  
.myerror {
	color: red !important;
	width: 35%;
	display: inline-block;
	padding-left: 10px;
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
	float:left;width:50%;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="result_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									教师：
								</label>
								<div class="controls">
								<input type="text" id="teachId" name="teachId" class="span4" data-id="${result.teachId }" placeholder="" value="${result.teachName}" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} > 
								</div>
							</div>	
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>成果名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${result.name}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>成果类型：
								</label>
								<div class="controls">
									<select id="type" name="type" class="span4" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}></select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>成果级别：
								</label>
								<div class="controls">
									<select id="level" name="level" class="span4" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}></select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>排名次序：
								</label>
								<div class="controls">
								<input type="text" id="rank" name="rank" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${result.rank}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>刊物分类：
								</label>
								<div class="controls">
								<input type="text" id="publication" name="publication" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${result.publication}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>日期：
								</label>
								<div class="controls"> 
								<input type="text" id="applyDate" name="applyDate" class="span4" placeholder="" value="<fmt:formatDate value='${result.applyDate }' />" onclick="WdatePicker();" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}>
								</div>
							</div> 
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>部署人数（人）：
								</label>
								<div class="controls">
								<input type="text" id="personNum" name="personNum" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${result.personNum}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>申请学分：
								</label>
								<div class="controls">
								<input type="text" id="applyScore" name="applyScore" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${result.applyScore}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>核定学分：
								</label>
								<div class="controls">
								<input type="text" id="checkScore" name="checkScore" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${result.checkScore}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>是否独立完成：
								</label>
								<div class="controls" style="width:222px;">
								 <c:choose>
								   <c:when test="${not empty result.independent }">
								      <input type="radio" id="independent" name="independent" class="span5"  value="true"  ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} <c:if test="${result.independent==true }">checked</c:if>/>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  <input type="radio" id="independent" name="independent" class="span5"  value="false" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} <c:if test="${result.independent==false }">checked</c:if>/>否
								   </c:when>
								   <c:otherwise>
								      <input style="margin:0 3px 0 0;" type="radio" id="independent" name="independent" class="span5"  value="true" checked="checked" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}/>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  <input style="margin:0 3px 0 0;" type="radio" id="independent" name="independent" class="span5"  value="false" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}/>否
								   </c:otherwise>
								 </c:choose>
								</div>
							</div>
							
							<div class="control-group">
							<label class="control-label" style="margin-right:20px;">附件</label>
							<div class="controls update_img" style="margin:0;float:left;width:225px;">
								<c:choose>
									<c:when test="${not empty result.fileUuid }">
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
								<input type="hidden" id="entityId" name="fileUuid" value="${result.fileUuid }"/>
							</div>
							</div>
						<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center">
							<c:if test="${isCK == null || isCK == ''}">
								<input type="hidden" id="id" name="id" value="${result.id}" />
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
		$.jcGcSelector("#type", {"tc" : "XY-JY-CGLX"}, "${result.type}", function(selector) {
			selector.chosen();
		});
		$.jcGcSelector("#level", {"tc" : "XY-JY-CGJB"}, "${result.level}", function(selector) {
			selector.chosen();
		});
		$.createMemberSelector({
			"inputIdSelector" : "#teachId",
			"enableBatch" : false,
			"isOnTopWindow" : true
		});
		checker = initValidator();
		uploadFile();
	});
	
	function initValidator() {
		return $("#result_form").validate({
			errorClass : "myerror",
			rules : {
				"teachId" : {
					required : true
				},
				"name" : {
					required : true,
					minlength : 1,
					maxlength : 20,
					stringCheck:true,
					remote : {
						async : false,
						type : "POST",
						url : "${ctp}/teach/result/checker",
						data : {
							'dxlx' : 'name',
							'name' : function() {
								return $("#name").val();
							},
							'id' : function() {
								return $("#id").val();
							}
						}
					}
				},
				"type" : {
					selectNone : true
				},
				"level" : {
					selectNone : true
				},
				"rank" : {
					required : true,
					minlength : 1,
					maxlength : 20,
				},
				"publication" : {
					required : true,
					minlength : 1,
					maxlength : 20,
				},
				"applyDate" : {
					required : true
				},
				"personNum" : {
					digits : true,
					required : true,
					max:1000
				},
				"applyScore" : {
					required : true,
					isFloatGZero:true,
					max:100
				},
				"checkScore" : {
					required : true,
					isFloatGZero:true,
					max:100
				}
			},
			messages : {
				"name" : {
					stringCheck:"只能输入中文字符",
					remote:"成果名称不能重复"
				},
				"applyScore" : {
					isFloatGZero:"学分输入不合法"
				},
				"checkScore" : {
					isFloatGZero:"学分输入不合法"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#result_form");
			//alert(JSON.stringify($requestData));
			var url = "${ctp}/teach/result/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/result/" + $id;
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