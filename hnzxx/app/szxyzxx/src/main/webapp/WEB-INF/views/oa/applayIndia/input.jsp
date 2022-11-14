<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%--<%@ include file="/views/embedded/plugin/uploadify.jsp"%>--%>
	<link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
	<script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
<%@ include file="/views/embedded/plugin/dept_selector_js.jsp" %>
<script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
<script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>文印</title>
<style type="text/css">
.row-fluid .span4 {
	width: 220px;
}
/* input[type="radio"]{
		margin:0 5px;
		position:relative;
		top:-1px;
	} */
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}

.back-right {
	color: #7C7C7C;
	font: 500 18px/35px "Microsoft YaHei";
	height: 35px;
	padding-right: 20px;
	float: right;
	line-height: 56px;
}

.back-right i {
	color: #9D9D9D;
	padding-right: 10px;
}
</style>
<script type="text/javascript">
	$(function() {
		$(".toggle").click(function() {
			$(this).html($(".content").is(":hidden") ? "收起<i class='fa fa-angle-up'></i>" : "展开<i class='fa fa-angle-down'></i>");
			$(".content").slideToggle();
		});
	});
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="文印" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
					<a href="javascript:void(0);" class="back-right" onclick="back();"><i class="fa fa-arrow-circle-left"></i>返回</a>
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" class="on">申请文印</a></li>
				        </ul>
					</div>
					<div class="yc_sq">
						<form class="form-horizontal" action="javascript:void(0);" id="applayIndia_form">
<%--							<div class="control-group" >--%>
<%--								<label class="control-label">标题<span style="color:red">*</span>：</label>--%>
<%--								<div class="controls">--%>
<%--									<input type="text" class="span8 left_red {required : true,maxlength:40}" placeholder="请输入标题，少于40个中文字符" id="title" name="title" value="${applayIndia.title }"/>--%>
<%--								</div>--%>
<%--							</div>--%>
							  <div class="control-group content">
								<label class="control-label">用章时间※：</label>
								<div class="controls">
									<input type="text" class="span4" onclick="WdatePicker({minDate:'%y-%M-{%d}',maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd HH:mm'});" id="startDate" name="publishDate"
										   value="<fmt:formatDate pattern="yyyy-MM-dd" value="${applayIndia.publishDate }" ></fmt:formatDate>"/> 至
									<input type="text" class="span4" onclick="WdatePicker({minDate:'#F{$dp.$D(\'startDate\',{m:+5})}',dateFmt:'yyyy-MM-dd HH:mm'});" id="endDate" name="publishDate"/>

								</div>
							</div>
							<!-- <div class="control-group content">
								<label class="control-label">摘要：</label>
								<div class="controls">
								<textarea class="span8"></textarea>
								</div>
							</div>
							<div class="control-group content">
								<label class="control-label">正文：</label>
								<div class="controls">
									<textarea style="" class="span8"></textarea>
								</div>
							</div> -->
							<c:if test="${departmentList.size()!=0 }">
							   <div class="control-group content">
								<label class="control-label">部门<span style="color:red">*</span>：</label>
								<div class="controls">
									<select class="span3" style="width: 150px;" name="departmentId" id="departmentId" onclick="getDepartment();">
									 <option value="">请选择</option>
								  <c:forEach items="${departmentList }" var="department">
								    <option value="${ department.departmentId}" <c:if test="${department.departmentId==d.id}">selected</c:if>>${ department.departmentName}</option>
								  </c:forEach>
								</select>
								</div>
							</div>
							</c:if>
<%--							<div class="control-group ">--%>
<%--								<label class="control-label">联系电话<span style="color:red">*</span>：</label>--%>
<%--								<div class="controls">--%>
<%--									<input type="text" name="mobile" value="${applayIndia.mobile}"--%>
<%--										class="span8 left_red {required:true,isTel:true,maxlength:11} " placeholder="请输入正确的联系电话" />--%>
<%--								</div>--%>
<%--							</div>--%>
							
							<div class="control-group content" >
								<label class="control-label">申请说明：</label>
								<div class="controls">
									<textarea style="width:800px;height:200px" class="span8 " name="remark" id="remark">${applayIndia.remark }</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">附件：</label>
								<div class="controls update_img">
								<c:choose>
									<c:when test="${not empty applayIndia.uploadId  }">
										<p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'><a target="_blank" id="a" href='<entity:getHttpUrl uuid="${india.uploadFile}"/>'>${entity.fileName}</a><button id="b" onclick="deleteFile();" class="btn btn-red" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>删除</button></p>
									</c:when>
									<c:otherwise>
										<p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'><a taget="_blank" id="a"></a></p>
										<c:choose>
											<c:when test="${isCK != null && isCk != '' }"></c:when>
											<c:otherwise><input type="hidden" id="uploader" name="uploader"/></c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
									<input type="hidden" id="entityId" name="uploadId" value="${applayIndia.uploadId }"/>
									<!-- <button class="btn"><i class="fa fa-upload"></i>上传附件</button> -->
								</div>
							</div>
							<div class="control-group content">
								<input class="controls" type="button" id="member_selector" value="选择审批人">
								<input type="hidden" id="teaId" value="${tIds}"/> <span id="ts"></span>
								<sapn id="teachName">${tNames}</sapn>
							</div>
							<div class="tag">
						        <a href="javascript:void(0);" class="toggle"> 收起 <i class="fa fa-angle-up"></i></a>
						    </div>
							<div class="caozuo" style="text-align:center;">
								<input type="hidden" id="id" name="id" value="${applayIndia.id}" />
								<button class="btn btn-success" onclick="saveOrupdate();">发布</button> <!-- <button class="btn">预览</button> -->
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var checker;
var editor;

var ts = "已选择的老师：";
var teachName = "";

function selectedHandler(data) {
	if(typeof(data.ids)=="object"){
		teachName = data.names[0];
		ids = data.ids[0];
	}else {
		teachName = data.names.split(",")[0];
		ids = data.ids.split(",")[0];
	}
	$("#ts").text(ts+teachName);
	//$("#teachName").text(teachName);
	$("#teaId").val(ids);
	$.success("设置成功");
	$.closeWindowByName(data.windowName);
}


$(function(){


	//教师筛选
	$.createMemberSelector({
		"inputIdSelector": "#member_selector",
		"ry_type": "teach",
		"layerOp": {
			"shift": "top",
			type: 2
		}
	});





// 	$("#departmentId").val("${department.departmentId}").chosen();
// 	KindEditor.ready(function(K) {
//
// 		editor = K.create('textarea[name="remark"]', {
// 				/* allowFileManager : true */
//
// 				resizeType : 1,
// 				allowPreviewEmoticons : false,
// 				allowImageUpload : false,
// 				items : [ 'fontname', 'fontsize', '|', 'forecolor',
// 						'hilitecolor', 'bold', 'italic', 'underline',
// 						'removeformat', '|', 'justifyleft', 'justifycenter',
// 						'justifyright', 'insertorderedlist',
// 						'insertunorderedlist', '|', 'emoticons', 'image',
// 						'link' ]
// 			});
// 			K('input[name=getHtml]').click(function(e) {
// 				alert(editor.html());
// 			});
// 			K('input[name=isEmpty]').click(function(e) {
// 				alert(editor.isEmpty());
// 			});
// 			K('input[name=getText]').click(function(e) {
// 				alert(editor.text());
// 			});
// 			K('input[name=selectedHtml]').click(function(e) {
// 				alert(editor.selectedHtml());
// 			});
// 			K('input[name=setHtml]').click(function(e) {
// 				editor.html('<h3>Hello KindEditor</h3>');
// 			});
// 			K('input[name=setText]').click(function(e) {
// 				editor.text('<h3>Hello KindEditor</h3>');
// 			});
// 			K('input[name=insertHtml]').click(function(e) {
// 				editor.insertHtml('<strong>插入HTML</strong>');
// 			});
// 			K('input[name=appendHtml]').click(function(e) {
// 				editor.appendHtml('<strong>添加HTML</strong>');
// 			});
// 			K('input[name=clear]').click(function(e) {
// 				editor.html('');
// 			});
// 		});
	uploadFile();
	checker = initValidator();
});


function uploadFile(){
	$('#uploader').uploadifive({
		'auto': true,
		'fileObjName' : 'file',
		//'queueID': 'queue',
		'buttonText': '上传文件',
		'removeCompleted':true,
		'height' : 20,
		'width' : 70,
		'formData': {
			'jsessionId': '<%=request.getSession().getId()%>'
		},
		'uploadScript': '/uploader/common',
		'onUploadComplete': function (file,data) {
			var $jsonObj = eval("(" + data + ")");
			$("#entityId").val($jsonObj.uuid);
			$("#a").text($jsonObj.realFileName);
			$("#a").attr('href',$jsonObj.url);
			$("#a").attr('target','_blank');
		},
		onUpload:function (file) { //上传开始时触发（每个文件触发一次）
			$("#infoBox").prev("p").css("display", "none");
			$("#infoBox").css("display", "block");
		},
		onFallback : function() {
			alert("该浏览器无法使用!");
		},
	});


	/*var obj = $("#uploader").uploadify({
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
	*/
}

function initValidator() {
	return $("#applayIndia_form").validate({
		errorClass : "myerror",
		rules : {
			
			"departmentId":{
				selectNone : true
			} 
		},
		messages : {
			
			"departmentId":{
				required : "部门必选"
			} 
		}
		
	});
}
function saveOrupdate(){
	if (checker.form()) {
		var loader = new loadLayer();
		var $id = $("#id").val();
		var $requestData = formData2JSONObj("#applayIndia_form");
		//$requestData.remark = editor.html();
		$requestData.remark = $("#remark").val();
		$requestData.startDate = $("#startDate").val();
		$requestData.endDate = $("#endDate").val();
	    $requestData.approverTeacherId = $("#teaId").val();

	    if($requestData.approverTeacherId == null ||
				$requestData.approverTeacherId == undefined ||
				$requestData.approverTeacherId.trim() == ""){
			$.error("审批人必须指定");
		}



		var url = "${pageContext.request.contextPath}/office/applayIndia/creator";
		if ("" != $id) {
			$requestData._method = "put";
			url = "${pageContext.request.contextPath}/office/applayIndia/" + $id;
		}
		loader.show();
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				$.success('发布成功');
				window.location.href="${pageContext.request.contextPath}/office/applayIndia/index?auditType=own";
			}else{
				$.error("发布失败");
			}
			loader.close();
		});
	}
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
	$(".update_img").children().append("<input type='file' id='uploader' name='uploader'/>");
	uploadFile();
} 

//返回按钮事件
function back(){
	window.location.href="${pageContext.request.contextPath}/office/applayIndia/index?auditType=own";
}
</script>
</html>