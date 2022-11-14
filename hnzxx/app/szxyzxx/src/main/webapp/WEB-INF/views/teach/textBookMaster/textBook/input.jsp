<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/jcml.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/stepy.jquery.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.min.js"></script>
<html>
<head>
<title>

</title>

</head>
<body style="background-color: cdd4d7 !important">

					<form class="form-horizontal tan_form" id="jctextbook_form" action="javascript:void(0);">
							
							
						
							<div class="control-group">
								<label class="control-label">
									书籍封面：
								</label>
								<div class="controls">
								<div class="up_img">

										<c:if test="${empty  jcTextbook.image}">
											<img src="${pageContext.request.contextPath}/res/images/cover_default_new.png" id="imgId"/>
										</c:if>
										<c:if test="${not empty jcTextbook.image}">
										  
											<img src="${jcTextbook.image}" id="imgId" />
										</c:if>

										<%-- <img src="${pageContext.request.contextPath}/res/images/cover_default_new.png" id="imgId"/> --%>
									   <p>建议分辨率:宽120x高170</p>
										<input type="file" id="uploader" name="uploader"/>
										<input type="hidden" id="entityId" name="image" value=""/>
										<input type="hidden" id="entityId" name="entityId" value=""/>
								</div>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									书名：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13"
									placeholder="" value="${jcTextbook.name}" style="width:220px;">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									副标题：
								</label>
								<div class="controls">
								<input type="text" id="subtitle" name="subtitle" class="span13"
									placeholder="" value="${jcTextbook.subtitle}" style="width:220px;">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									原作名：
								</label>
								<div class="controls">
								<input type="text" id="oldName" name="oldName" class="span13"
									placeholder="" value="${jcTextbook.oldName}" style="width:220px;">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									ISBN：
								</label>
								<div class="controls">
								<input type="text" id="isbn" name="isbn" class="span13"
									placeholder="" value="${jcTextbook.isbn}" style="width:220px;">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									版本年级：
								</label>
								<div class="controls">
								<input type="text" id="stageCode" name="stageCode" class="span13"
								placeholder="" value="${jcTextbook.stageCode}" style="width:110px;">
								
								<input type="text" id="subjectCode" name="subjectCode" class="span13"
								placeholder="" value="${jcTextbook.subjectCode}" style="width:110px;">
								
								<input type="text" id="gradeCode" name="gradeCode" class="span13"
								placeholder="" value="${jcTextbook.gradeCode}" style="width:110px;">
								
								<input type="text" id="volumn" name="volumn" class="span13"
								placeholder="" value="${jcTextbook.volumn}" style="width:110px;">
								
								<input type="text" id="version" name="version" class="span13"
								placeholder="" value="${jcTextbook.version}" style="width:110px;">
								
								<input type="text" id="type" name="type" class="span13"
								placeholder="" value="${jcTextbook.type}" style="width:110px;">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									作者：
								</label>
								<div class="controls">
								
								<%-- <input type="text" id="type" name="type" class="span13"
								placeholder="" value="${fn:length(${jcTextbook.writerMain})}" style="width:110px;"> --%>
								
								<c:forEach items="${jcTextbook.type}" var="writermain" varStatus="status">
								<input type="text" id="type" name="type" class="span13" value="${status.index}" style="width:110px;">
								
								<%-- <input type="text" id="id" name="writerMain['${status.index}'].id" class="span13"
									placeholder="" value="${writermain.id }" style="width:50px;">
									<input type="text" id="name" name="writerMain['${status.index}'].name" class="span13"
									placeholder="" value="${ writermain.name}" style="width:160px;">
								 --%></c:forEach>
								+
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									译者：
								</label>
								<div class="controls">
								
								<%-- <input type="text" id="writerId" name="name" class="span13"
									placeholder="" value="${fn:length(${jcTextbook.writerMain})}" style="width:50px;"> --%>
								 <%-- <c:forEach items="${jcTextbook.writerMain}" var="writerMain" varStatus="status">
									<input type="text" id="writerId" name="writerMain['${status.index}'].id" class="span13"
									placeholder="" value="${ writerMain.id}" style="width:50px;">
									<input type="text" id="writerId" name="writerMain['${status.index}'].name" class="span13"
									placeholder="" value="${ writerMain.name}" style="width:160px;">
								</c:forEach> --%>
								+
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									定价：
								</label>
								<div class="controls">
								<input type="text" id="price" name="price" class="span13"
									placeholder="" value="${jcTextbook.price}" style="width:220px;">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									出版社：
								</label>
								<div class="controls">
								<input type="text" id="pressName" name="pressName" class="span13"
									placeholder="" value="${jcTextbook.pressName}" style="width:220px;">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									出版日期：
								</label>
								<div class="controls">
								<%-- value="<fmt:formatDate pattern='yyyy-MM-dd' value='${jcTextbook.publishDate}'></fmt:formatDate>" --%>
								<input type="text" id="publishDate" name="publishDate" class="span13"
								placeholder="出版日期" value="${jcTextbook.publishDate}" onclick="WdatePicker();" style="width:220px;">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									装帧：
								</label>
								<div class="controls">
								
								    <input type="radio" id="radio1" name='binding' value="1">平装
           							<input type="radio" id="radio2" name='binding' value="2">精装
           							<input type="radio"  id="radio3" name='binding' value="3">Pagerback
           							<input type="radio"  id="radio4" name='binding' value="4" >Hardcover
           							<input type="radio"  id="radio5" name='binding' >其他：
           							<%-- <input type="text" id="binding" name="binding" class="span13"
									placeholder="" value="${jcTextbook.binding}" style="width:80px;"> --%>
								</div>
								
							</div>
							
							
							<div class="control-group">
								<label class="control-label">
									页数：
								</label>
								<div class="controls">
								<input type="text" id="pages" name="pages" class="span13"
									placeholder="" value="${jcTextbook.pages}" style="width:100px;">&nbsp;页
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									字数：
								</label>
								<div class="controls">
								<input type="text" id="wordCount" name="wordCount" class="span13"
									placeholder="" value="${jcTextbook.wordCount}" style="width:100px;">&nbsp;字
								</div>
								
							</div>
							
							
							<div class="control-group">
								<label class="control-label">
									内容介绍：
								</label>
								<div class="controls">
								<textarea  rows="3"  id="description" name="description" class="span13"
									placeholder=""style="width:260px;">${jcTextbook.description} </textarea>
									
									
									
								</div>
								
							</div>
							
							<%-- <div class="control-group">
								<label class="control-label">
									作者介绍：
								</label>
								<div class="controls">
								
								<textarea  rows="3"  id="description" name="" class="span13"
									placeholder=""style="width:260px;">${jcTextbook.description} </textarea>
								</div>
								
							</div> --%>
							
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${jcTextbook.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
	
</body>
<script type="text/javascript">
	var checker;
	$(function() {
		
		if('${isCK}' == 'disable'){
			
			$("form input").prop("readonly", true);
			
			
		}
		
		
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#jctextbook_form").validate({
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
			var $requestData = formData2JSONObj("#jctextbook_form");
			var url = "${ctp}/teach/textBookMaster/textBook/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/textBookMaster/textBook/" + $id;
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
	
	function loadViewerCatalogPage(id) {
		var url = "${ctp}/teach/textBookMaster/textBookCatalog/catalogList?textBookId=";
		 url = url+id;
		 $("#loadViewerCatalogPage").attr("href", url);
	}
	//教材基本信息显示
	function loadViewerPage(id) {
		
		var url = "${ctp}/teach/textBookMaster/textBook/viewer?id=";
		 url = url+id;
		 $("#loadViewerPage").attr("href", url);
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
          buttonText: "图片上传",
          requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
          height: 20,
          width: 66,
          onUploadSuccess: function(file, data, response) {
         	 var $jsonObj = eval("(" + data + ")");
         	 $("#entityId").val($jsonObj.uuid);
         	 $("#imgId").attr('src',$jsonObj.url); 
         	 $("#entityId").val($jsonObj.url);
         	
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
</script>
</html>