<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<style type="text/css">



.row-fluid .span4{
		width:220px;
	}
input[type="radio"]{
		margin:0 5px 0 0px;
		position:relative;
		top:-1px;
	}
.form-horizontal .controls #zp .img_1 {
	float: left;
	margin: 10px;
	position: relative;
	top: 0;
	width: 233px;
	height: 130px;
}
.form-horizontal .controls #zp .img_1 img {
	width: 233px;
	height: 130px;
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
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.ke-container{display:inline-block}
.chzn-container{top:10px;}
#dept_seleted_id{display:inline-block;}
</style>

<style type="text/css">
.sq_list .clsq ul li .detail_1{
	margin-left:0;
}
</style>

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
					<form class="form-horizontal tan_form" id="research_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${research.name}">
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									作者：
								</label>
								<div class="controls">
								<input type="text" id="author" name="author" class="span13" placeholder="" value="${research.author}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									类别：
								</label>
								<div class="controls">
								
								<select name="type" disabled="disabled">类别：
									
									<c:choose>
										<c:when test="${type == 1}">
											<option value="1" selected="selected">科研成果</option>
											<option value="2">科技著作</option>
											<option value="3">科技论文</option>
											<option value="4">鉴定成果</option>
											<option value="5">专利成果</option>
											<option value="6">技术转让</option>
										</c:when>
										<c:when test="${type == 2}">
											<option value="1" >科研成果</option>
											<option value="2" selected="selected">科技著作</option>
											<option value="3">科技论文</option>
											<option value="4">鉴定成果</option>
											<option value="5">专利成果</option>
											<option value="6">技术转让</option>
										</c:when>
										<c:when test="${type == 3}">
											<option value="1" >科研成果</option>
											<option value="2" >科技著作</option>
											<option value="3" selected="selected">科技论文</option>
											<option value="4">鉴定成果</option>
											<option value="5">专利成果</option>
											<option value="6">技术转让</option>
										</c:when>
										<c:when test="${type == 4}">
											<option value="1" >科研成果</option>
											<option value="2" >科技著作</option>
											<option value="3" >科技论文</option>
											<option value="4" selected="selected">鉴定成果</option>
											<option value="5">专利成果</option>
											<option value="6">技术转让</option>
										</c:when>
										<c:when test="${type == 5}">
											<option value="1" >科研成果</option>
											<option value="2" >科技著作</option>
											<option value="3" >科技论文</option>
											<option value="4" >鉴定成果</option>
											<option value="5" selected="selected">专利成果</option>
											<option value="6">技术转让</option>
										</c:when>
										<c:when test="${type == 6}">
											<option value="1" >科研成果</option>
											<option value="2" >科技著作</option>
											<option value="3" >科技论文</option>
											<option value="4" >鉴定成果</option>
											<option value="5" >专利成果</option>
											<option value="6" selected="selected">技术转让</option>
										</c:when>
										
									</c:choose>
								
								
									
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									发布时间：
								</label>
								<div class="controls">
								<td><input type="text" id="publishTime" name="publishTime" class="span13" value="<fmt:formatDate value="${research.publishDate}" pattern="yyyy-MM-dd" />" placeholder="发布时间" onclick="WdatePicker();"/></td>
								<span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
							<div id="release_picture" style="margin-left: 180px;float: none;width: initial;">
							<ul>
							<c:forEach items="${fileList}" var="file">
								<li><span>${file.fileName }</span>
								<a href="javascript:void(0);" id="${file.uuid}"></a></li>
							</c:forEach>
							</ul>
							<div class="clear"></div>
							</div>
								<label class="control-label">附件：</label>
								<div class="controls">
								<div id="fj" style="display:inline-block;">

								</div>
								<input type="hidden" name="files" id="files" value="${research.files}"/>
								<input type="hidden" id="uploaderFile">
								<span id="tp_queue"></span>
								<div class="clear"></div>
								</div>
							</div>
							<div class="control-group">
							<label class="control-label">
									备注：
							</label>
								<div class="controls">
									<textarea cols="5" rows="5" name="remark" class="span13">${research.remark}</textarea>
								</div>
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${research.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">保存</button>
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
		$("#release_picture").on("click","ul li a",function(){
		    
	        $(this).parent().remove();
	        var id=$(this).attr('id')+",";
	  
	        str=str.replace(id,"");
	        i=i-1;
	        if(i<5){
	        	$("#uploaderFile").uploadify("disable",false);
	        }
	 

	    })
		
	});
	
	function initValidator() {
		return $("#research_form").validate({
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
			var name = $("#name").val();
			if(name == null || name == ''){
				alert("名称不能为空");
				return false ;
			}
			
			var publishTime = $("#publishTime").val();
			if(publishTime == null || publishTime == ''){
				alert("发布时间不能为空");
				return false ;
			}
			
			var index = $('#release_picture ul li').length;
			if(index > 5){
				alert('超出最大附件五张限制，请删减再试');
				return false;
			}
			var loader = new loadLayer();
			var $id = $("#id").val();
			var files = ''
				$('#release_picture ul li').each(function(index,item){
					files += $(item).find('a').attr('id')+",";
					//alert($(item).find('a').attr('id'));
				})
				if(files != ''){
					files=files.substring(0,files.length-1);
					$("#files").val(files);
				}
			
			var $requestData = formData2JSONObj("#research_form");
			//alert(JSON.stringify($requestData))
			var url = "${ctp}/schoolaffair/research/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolaffair/research/" + $id;
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
	
	
	//文档上传
	function uploadFile(){
		var str = "";
    	var obj = setTimeout(function(){$("#uploaderFile").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
							fileObjName : 'file',
							fileTypeDesc : "文件上传",
// 							fileTypeExts : "*.*", //默认*.*
							fileTypeExts : '*.doc;*.docx; *.xls;*.xlsx; *.ppt;*pptx;',
							method : 'post',
							multi : true, // 是否能选择多个文件
							auto : true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
							removeTimeout : 1,
							queueSizeLimit : 9,
							fileSizeLimit : 4 * 1024,
							buttonText : "上传附件",
							requeueErrors : false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
							height : 20,
							width : 70,
// 							uploadLimit:9,
							onUploadSuccess : function(file, data, response) {
								var $jsonObj = eval("(" + data + ")");
								var realFileName = $jsonObj.realFileName;
								var img1='<li><span>'+realFileName+'</span><a href="javascript:void(0);"id="'+$jsonObj.uuid+'"></a></li>';
								  $("#release_picture ul").append(img1);
// 									$.success("上传成功!", 9);
// 								$("#fj").append(img);
								var i = $('#release_picture ul li').length;
									 if(i==3){
										$("#uploaderFile").uploadify("disable",true);
										return;
									} 
		
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
    	})},30);
	}
	
	
</script>
</html>