<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/editor.jsp"%>
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

.form-horizontal .controls #zp {
	width: 423px;
}

.form-horizontal .controls #zp .img_1 {
	float: left;
	margin: 10px;
	position: relative;
	top: 0;
	width: 120px;
	height: 120px;
}

.form-horizontal .controls #zp .img_1 img {
	height: 120px;
	width: 120px;
}

.form-horizontal .controls #zp .img_1 a {
	position: absolute;
	font-size: 22px;
	font-weight: bold;
	color: #000;
	right: 0px;
	top: 0px;
	display: block;
	width: 16px;
	height: 16px;
	line-height: 16px;
	text-align: center;
	cursor: pointer;
}
</style>

<script type="text/javascript">
	$(function() {
		$.createMemberSelector({
			"inputIdSelector" : "#member_selector",
// 			"isOnTopWindow" : true,
			"layerOp" : {
				"shift" : "top"
			}
		});
	});
</script>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="oanotice_form"
						action="javascript:void(0);">
 

						<div class="control-group">
							<label class="control-label"> 主题<font style="color:red">*</font>： </label>
							<div class="controls">
								<input class="input-medium required" type="text" name="title"
									placeholder="公文主题" value="${paper.title }" style="height:30px;line-height:30px;width:150px;">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"> 发文单位<font style="color:red">*</font>： </label>
							<div class="controls">
								<input class="input-medium required" type="text" name="author"
									placeholder="发文单位" value="${paper.author }" style="height:30px;line-height:30px;width:150px;">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"> 公文紧急等级<font style="color:red">*</font>： </label>
							<div class="controls">
								<select class="span3" style="width: 150px;" name="emergencyLevel">
									<option value="平件" <c:if test="${paper.emergencyLevel=='平件' }"> selected="selected"</c:if>  >平件</option>
									<option value="急件" <c:if test="${paper.emergencyLevel=='急件' }"> selected="selected"</c:if>>急件</option> 
									<option value="特急" <c:if test="${paper.emergencyLevel=='特急' }"> selected="selected"</c:if>>特急</option> 
								</select>
							</div>
						</div>
						
					<div class="control-group">
							<label class="control-label">附件：</label>
							<div class="controls">
								 
									<input type="file" id="uploader" >
								   <input type="hidden" id="attachmentUuid" name="attachmentUuid">
								<span id="tp_queue"></span>
								 <a taget="_blank" id="uploader_a"></a>
								<div class="clear"></div>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label"> 正文<font style="color:red">*</font>： </label>
							<div class="controls">
								<div class="textarea">
									<textarea class="span8 required" id="content" name="content" rows="15" cols="200"
										  style="width:670px;">${paper.content}</textarea>

								</div>
							</div>
						</div>


						<div class="control-group">
							<label class="control-label"> 选择老师<font style="color:red">*</font>： </label>
							<div class="controls">
								<div class="textarea">
									<input type="button" id="member_selector" value="选择可以查看公文的老师">
                                  <span id="teachName">
                                  <c:if test="${!empty  paperUser}">已选择的老师</c:if>
                                  <c:forEach items="${paperUser }" var="entry" varStatus="statu">
                                   ${entry.receiverName },
                                    </c:forEach> 
                                     </span>
                                     
                                      <input type="hidden" id="ids" name="ids"  class="input-medium required">
                                       <input type="hidden" id="receivers" name="receivers">
								</div>
							</div>
						</div>

						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${paper.id}" />
							<button class="btn btn-warning" type="button"
								onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="content"]', {
		resizeType : 1,
		allowPreviewEmoticons : false,
		allowImageUpload : false,
		items : [
			'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	});
});
 
</script>
<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
		uploadFile();
	});

	function initValidator() {
		return $("#oanotice_form").validate({
		 
			errorClass : "myerror",
			rules : {
				 
			},
			messages : {
				 
				 
			}
		});
	}
	var ids="";
	//保存或更新修改
	function saveOrUpdate() {
		$("#content").val(editor.html());
		if (checker.form()) {
			
			var loader = new loadLayer();
			var $id = $("#id").val();

			var $requestData = formData2JSONObj("#oanotice_form");

			$requestData.content = editor.html();
			var url = "${ctp}/office/paper/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/office/paper/edit/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				} else {
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	
	
	
	
	
	var teachName= "已选择的老师：";
	var receivers="";
	function selectedHandler(data) {
		$.each(data.ids, function(index, value) {
			if(ids.indexOf(value) == -1) {
				ids=ids + value + ",";
				teachName = teachName + data.names[index] + ",";
				receivers=receivers+data.names[index] + ",";
			}
		});
		 $("#teachName").html(teachName);
		 $("#ids").val(ids);
		 $("#receivers").val(receivers);
		$.success("老师选择成功"); 
	}
	
	
	
	function uploadFile(){
    	var obj = $("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'
							},
							fileObjName : 'file',
							fileTypeDesc : "文件上传",
							fileTypeExts : "*.*", //默认*.*
							method : 'post',
							multi : true, // 是否能选择多个文件
							auto : true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
							removeTimeout : 1,
							queueSizeLimit : 1,
							fileSizeLimit : 4 * 1024,
							buttonText : "上传文件",
							requeueErrors : false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
							height : 20,
							width : 70,
							onUploadSuccess : function(file, data, response) {
								
								var $jsonObj = eval("(" + data + ")");
								 
								 
								
							 
								$.success("上传成功!", 9);
								$("#attachmentUuid").val($jsonObj.uuid);
								 
					           	 $("#uploader_a").text($jsonObj.realFileName);
					           	 $("#uploader_a").attr('href',$jsonObj.url); 
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

	function Change(obj) {
		var imgSrc = $(obj).attr("src");
		$.layer({
			type : 1,
			shade : [ 0.2, '#000' ],
			shadeClose : true,
			area : [ 'auto', 'auto' ],
			offset : [ '20px', '' ],
			title : false,
			border : [ 0 ],
			page : {
				html : '<img src="' + imgSrc + '">'
			},
			shift : 'left'
		});
	}

	function reMove(obj) {
		$(obj).parent().remove();
	}

	//获取文件的uuid
	function getFileUuids() {
		var imgs = $(".img_1");
		var fids = "";
		$.each(imgs, function(index, value) {
			fids += ($(value).find("a").attr("data-id") + ",");
		});
		if (fids != "") {
			fids = fids.substring(0, fids.length - 1);
		}
		return fids;
	}

	//获取文件的缩略图路径
	function getFileUrls() {
		var imgs = $(".img_1");
		var urls = "";
		$.each(imgs, function(index, value) {
			urls += ($(value).find("img").attr("src") + ",");
		});
		if (urls != "") {
			urls = urls.substring(0, urls.length - 1);
		}
		return urls;
	}
</script>



</html>