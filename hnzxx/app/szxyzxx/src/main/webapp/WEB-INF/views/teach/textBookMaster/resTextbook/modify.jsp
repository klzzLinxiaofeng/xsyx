<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/jcml.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/stepy.jquery.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.min.js"></script>
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
input[type="radio"]{
	position:relative;
	margin:0 5px;
	top:-1px;
}
</style>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="书籍查看" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="ts_top">
						书籍查看
						<p>
						<a href="${ctp}/teach/textBookMaster/resTextBook/index" >图书列表</a>
					    <a href="" id="loadViewerPage"  onclick="loadViewerPage('${resTextbook.id}');" class="on">基本信息</a>
						<a href=""  id="loadViewerCatalogPage"  onclick="loadViewerCatalogPage('${resTextbook.id}');">教材目录</a>
						<a href="" id="loadViewerModelPage"  onclick="loadViewerModelPage('${resTextbook.id}');">目录管理</a>
						</p>
					</div>
					<div class="class_detail">
						<form class="form-horizontal tan_form" id="jctextbook_form" action="javascript:void(0);">
							
						
						
							<div class="control-group">
								<label class="control-label">
									书籍封面：
								</label>
								<div class="controls">
								<div class="up_img">

										<c:if test="${empty  resTextbook.image}">
											<img src="${pageContext.request.contextPath}/res/images/cover_default_new.png" id="imgId"/>
										</c:if>
										<c:if test="${not empty resTextbook.image}">
										  
											<img src="${resTextbook.image}" id="imgId" />
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
									<span class="red">*</span>书名：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13"
									placeholder="" value="${resTextbook.name}" style="width:220px;">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									副标题：
								</label>
								<div class="controls">
								<input type="text" id="subtitle" name="subtitle" class="span13"
									placeholder="" value="${resTextbook.subtitle}" style="width:220px;">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									原作名：
								</label>
								<div class="controls">
								<input type="text" id="oldName" name="oldName" class="span13"
									placeholder="" value="${resTextbook.oldName}" style="width:220px;">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									ISBN：
								</label>
								<div class="controls">
								<input type="text" id="isbn" name="isbn" class="span13"
									placeholder="" value="${resTextbook.isbn}" style="width:220px;">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>版本年级：
								</label>
								<div class="controls">
								
								
								<select  id="stageCode" name="stageCode"  style="width:110px;"> </select>
								<select id="subjectCode" name="subjectCode"  style="width:110px;"></select>
								<select  id="gradeCode" name="gradeCode"  style="width:110px;"></select>
								
								<select  id="volumn" name="volumn"  style="width:110px;"></select>
								<select  id="version" name="version"  style="width:110px;"></select>
								<select  id="type" name="type"  style="width:110px;">
									<option value="" id ='type0'>请选择</option>
									<option value="1" id ='type1'>教课书</option>
									<option value="2" id ='type2'>教辅书</option>
									<option value="3" id ='type3'>其他</option>
								</select>
								
								<%-- <input type="text" id="volumn" name="volumn" class="span13"
								placeholder="" value="${resTextbook.volumn}" style="width:110px;">
								
								<input type="text" id="version" name="version" class="span13"
								placeholder="" value="${resTextbook.version}" style="width:110px;">
								
								<input type="text" id="type" name="type" class="span13"
								placeholder="" value="${resTextbook.type}" style="width:110px;"> --%>
								<!-- <select > <option>学段</option></select>
								<select > <option>学科</option></select>
								<select > <option>年级</option></select>
								<select > <option>册次</option></select>
								<select > <option>版本</option></select>
								<select > <option>类型</option></select> -->
								
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									作者：
								</label>
								<div class="controls" id = "writerMainAdd">
								
								
								
								
								<c:set var="writerMainCountTemp">${fn:length(resTextbook.writerMain)}</c:set>
								
								<c:forEach items="${resTextbook.writerMain}" var="writerMain" varStatus="status">
								 <c:if test="${ status.index >0 }"></br></c:if>
								  <input type="hidden" id="writerMain['${status.index}'].id" name="writerMain['${status.index}'].id" class="span13"
									placeholder="" value="${writerMain.id }" style="width:50px;">
									<input type="hidden" id="writerMain['${status.index}'].type" name="writerMain['${status.index}'].type" class="span13"
									placeholder="" value="${writerMain.type }" style="width:50px;">
									
									<input type="hidden" id="writerMain['${status.index}'].textBookId" name="writerMain['${status.index}'].textBookId" class="span13"
									placeholder="" value="${writerMain.textBookId }" style="width:50px;">
									<input type="hidden" id="writerMain['${status.index}'].listOrder" name="writerMain['${status.index}'].listOrder" class="span13"
									placeholder="" value="${writerMain.listOrder }" style="width:50px;">
									<c:set var="writerMainListOrderCountTemp" value="${writerMain.listOrder }"></c:set>
									
									<input type="text" id="writerMain['${status.index}'].name" name="writerMain['${status.index}'].name" class="span13"
									placeholder="" value="${ writerMain.name}" style="width:220px;">
								</c:forEach>
									
									
								    <a href="javascript:void(0)" onclick="writerAdd('1');">+</a>
									
								</div>
								
							</div>
							
							
							
								
							
							<div class="control-group">
								<label class="control-label">
									译者：
								</label>
								<div class="controls" id="writerTranslatorAdd">
								<c:set var='writerTranslatorCountTemp' >${fn:length(resTextbook.writerTranslator)}</c:set>
								<c:forEach items="${resTextbook.writerTranslator}" var="writerTranslator" varStatus="status">
								 <c:if test="${ status.index >0 }"></br></c:if>
								<input type="hidden" id="writerTranslator['${status.index}'].id" name="writerTranslator['${status.index}'].id" class="span13"
									placeholder="" value="${writerTranslator.id }" style="width:50px;">
									<input type="hidden" id="writerTranslator['${status.index}'].type" name="writerTranslator['${status.index}'].type" class="span13"
									placeholder="" value="${writerTranslator.type }" style="width:50px;">
									<input type="text" id="writerTranslator['${status.index}'].name" name="writerTranslator['${status.index}'].name" class="span13"
									placeholder="" value="${ writerTranslator.name}" style="width:220px;">
									<c:set var='writerTranslatorListOrderCountTemp' >${writerTranslator.listOrder }</c:set>
								</c:forEach>
								 <a href="javascript:void(0)" onclick="writerAdd('2');">+</a>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									定价：
								</label>
								<div class="controls">
								<input type="text" id="price" name="price" class="span13"
									placeholder="" value="${resTextbook.price}" style="width:220px;">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>出版社：
								</label>
								<div class="controls">
								<select  id="publisherId" name="publisherId"  style="width:220px;"></select>
								<%-- <input type="text" id="publisherId" name="publisherId" class="span13"
									placeholder="" value="${resTextbook.publisherId}" style="width:220px;"> --%>
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>出版日期：
								</label>
								<div class="controls">
								<%-- value="<fmt:formatDate pattern='yyyy-MM-dd' value='${resTextbook.publishDate}'></fmt:formatDate>" --%>
								<input type="text" id="publishDate" name="publishDate" class="span13"
								placeholder="出版日期" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${resTextbook.publishDate}"/>' onclick="WdatePicker()" style="width:220px;">
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									装帧：
								</label>
								<div class="controls">
								
								    <input type="radio" id="radio1" name='binding'  value="1" >平装
           							<input type="radio" id="radio2" name='binding' value="2" >精装
           							<input type="radio"  id="radio3" name='binding' value="3" >Pagerback
           							<input type="radio"  id="radio4" name='binding' value="4" >Hardcover
           							<input type="radio"  id="radio5" name='binding'  >其他：
           							<input type="text" id="binding" name="bindingTemp" class="span13"
									placeholder="" value="" style="width:80px;" /> 
									
								</div>
								
							</div>
							
							
							<div class="control-group">
								<label class="control-label">
									页数：
								</label>
								<div class="controls">
								<input type="text" id="pages" name="pages" class="span13"
									placeholder="" value="${resTextbook.pages}" style="width:100px;">&nbsp;页
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									字数：
								</label>
								<div class="controls">
								<input type="text" id="wordCount" name="wordCount" class="span13"
									placeholder="" value="${resTextbook.wordCount}" style="width:100px;">&nbsp;字
								</div>
								
							</div>
							
							
							<div class="control-group">
								<label class="control-label">
									内容介绍：
								</label>
								<div class="controls">
								<textarea  rows="3"  id="description" name="description" class="span13"
									placeholder=""style="width:450px;height:200px;">${resTextbook.description} </textarea>
									
									
									
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									作者介绍：
								</label>
								<div class="controls">
								
								<textarea  rows="3"  id="writerDescription" name="writerDescription" class="span13"
									placeholder=""style="width:450px;height:200px;">${resTextbook.writerDescription} </textarea>
								</div>
								
							</div>
							
						<input type="hidden"  id = "writerMainCountId" value="${writerMainCountTemp}"/>
						<input type="hidden"  id = "writerMainListOrderCountId" value="${writerMainListOrderCountTemp}"/>
						<input type="hidden"  id = "writerTranslatorCountId" value="${writerTranslatorCountTemp}"/>
						<input type="hidden"  id = "writerTranslatorListOrderCountId" value="${writerTranslatorListOrderCountTemp}"/>
							
						<%-- <div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${resTextbook.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div> --%>
						<div class="control-group">
								<label class="control-label">
								</label>
								<div class="controls">
								<input type="hidden" id="id" name="id" value="${resTextbook.id}" />
								<button class="btn btn-success" type="button"
									onclick="saveOrUpdate();"><i class="fa fa-check-square-o"></i> 确定</button>
							</div>
					</form>
						
					</div>
				</div>
			</div>
		</div>
</div>
	<%-- <div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
			<div>
				<a href="${ctp}/teach/textBookMaster/textBook/index" >图书列表</a>
			    <a href="" id="loadViewerPage"  onclick="loadViewerPage('${resTextbook.id}');">基本信息</a>
				<a href=""  id="loadViewerCatalogPage"  onclick="loadViewerCatalogPage('${resTextbook.id}');">教材目录</a>

				 
			</div>
				<div class="widget-container" style="padding: 20px 0 0;">
					
				</div>
			</div>
		</div>
	</div> --%>
</body>
<script type="text/javascript">
	var checker;
	$(function() {
		var aa = "4";
		if('${isCK}' == 'disable'){
			
			$("form input").prop("readonly", true);	
		}
		
		if('${resTextbook.binding}' == 1||
				'${resTextbook.binding}' == 2||
				'${resTextbook.binding}' == 3||
				'${resTextbook.binding}' == 4){
			
			var name = 'radio'+'${resTextbook.binding}';
			$("#"+name).attr("checked",true);
			
		}else{
			$("#radio5").attr("checked",true);
			$("#binding").val('${resTextbook.binding}');
		}
		
		$('#binding').focus(function() { 
			$("#radio5").attr("checked",true);
		}); 
		$('#binding').blur(function() { 
			$("#radio5").val($("#binding").val());
		});
		
		
		if('${resTextbook.type}' == 1||'${resTextbook.type}' == 2||'${resTextbook.type}' == 3){
			var name ='type'+'${resTextbook.type}';
			$("#"+name).attr("select","selected");
		}
		
		//学段
		$.jcSelector("#stageCode",{"tn" : "jc_stage"}, '${resTextbook.stageCode}', function(data) {
			return {"val" : data.code, "title" : data.name};
		}, function() {
			
		});
		//学科
		$.jcSelector("#subjectCode",{"tn" : "jc_subject", "pn" : "subjectClass", "value" : "1"}, '${resTextbook.subjectCode}', function(data) {
			return {"val" : data.code, "title" : data.name};
		}, function() {
			
		});
		//年级
		$.jcSelector("#gradeCode",{"tn" : "jc_grade"}, '${resTextbook.gradeCode}', function(data) {
			return {"val" : data.code, "title" : data.name};
		}, function() {
			
		});
		
		
		//册次
		$.jcSelector("#volumn",{"tn" : "jc_textbook_volumn"}, '${resTextbook.volumn}', function(data) {
			return {"val" : data.code, "title" : data.name};
		}, function() {
			
		});
		//版本'${resTextbook.version}'
		/* $.jcSelector("#version",{"tn" : "jc_textbook_version"}, "", function(data) {
			return {"val" : data.id, "title" : data.name};
		}, function() {
			
		}); */
		
		textBookVersion();
		
		//出版社
		/* $.jcSelector("#publisherId",{"tn" : "jc_textbook_publisher"}, '${resTextbook.publisherId}', function(data) {
			return {"val" : data.id, "title" : data.name};
		}, function() {
			
		}); */
		textBookPublish();
		
		checker = initValidator();
		uploadImageFile();
	});
	
	function initValidator() {
		return $("#jctextbook_form").validate({
			errorClass : "myerror",
			rules : {
				"name":{
					required : true
				},"stageCode":{
					required : true
				},"subjectCode":{
					required : true
				},"gradeCode":{
					required : true
				},"volumn":{
					required : true
				},"version":{
					required : true
				},"publisherId":{
					required : true
				},"publishDate":{
					required : true
				}
				
			},
			messages : {
				name:"书名必填",
				stageCode:"必填",
				subjectCode:"必填",
				volumn:"必填",
				gradeCode:"必填",
				version:"必填",
				publisherId:"必填",
				publishDate:"必填"
			}
		});
	}
	
	function loadViewerModelPage(id) {
		var url = "${ctp}/teach/textBookMaster/catalogmodel/structrueManager?textBookId="+id;
		$("#loadViewerModelPage").attr("href", url);
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
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#jctextbook_form");
			var url = "${ctp}/teach/textBookMaster/resTextBook/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/textBookMaster/resTextBook/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
							window.location.href="${ctp}/teach/textBookMaster/resTextBook/viewer?id=${resTextbook.id}";
 						} else {
 							window.location.href="${ctp}/teach/textBookMaster/resTextBook/viewer?id=${resTextbook.id}";
 						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
			});
		}
	}
	
	function loadViewerCatalogPage(id) {
		var url = "${ctp}/teach/textBookMaster/resTextBookCatalog/resCatalogList?resTextBookId=";
		 url = url+id;
		 $("#loadViewerCatalogPage").attr("href", url);
	}
	//教材基本信息显示
	function loadViewerPage(id) {
		
		var url = "${ctp}/teach/textBookMaster/resTextBook/viewer?id=";
		 url = url+id;
		 $("#loadViewerPage").attr("href", url);
	}
	
	function writerAdd(type){
			
		
			var firstName = "";
			var divId = ""
			var writerCount = "";
			var writerListOrderCount = "";
			if(type == 1){
				firstName = "writerMain";
				divId = "writerMainAdd";
				writerCount = $("#writerMainCountId").val();
				writerListOrderCount = $("#writerMainListOrderCountId").val();
			}if(type == 2){
				firstName = "writerTranslator";
				divId = "writerTranslatorAdd";
				writerCount = $("#writerTranslatorCountId").val();
				writerListOrderCount = $("#writerTranslatorListOrderCountId").val();
			}
			writerListOrderCount = Number(writerListOrderCount)+1;
			var inputWriter = "<br/><input type='hidden' id='"+firstName+"["+writerCount+"].id' name='"+firstName+"["+writerCount+"].id' class='span13'  value='' style='width:50px;'>";
			inputWriter = inputWriter+"<input type='hidden' id='"+firstName+"["+writerCount+"].type' name='"+firstName+"["+writerCount+"].type' class='span13'  value='"+type+"' style='width:50px;'>";
			inputWriter = inputWriter+"<input type='hidden' id='"+firstName+"["+writerCount+"].listOrder' name='"+firstName+"["+writerCount+"].listOrder' class='span13' value='"+writerListOrderCount+"' style='width:50px;'>";
			inputWriter = inputWriter+"<input type='text' id='"+firstName+"["+writerCount+"].name' name='"+firstName+"["+writerCount+"].name' class='span13' value='' style='width:160px;'>";
			writerCount =  Number(writerCount) +1;
			if(type == 1){
				$("#writerMainCountId").val(writerCount);
				$("#writerMainListOrderCountId").val(writerListOrderCount);
				
			}if(type == 2){
				
				$("#writerTranslatorCountId").val(writerCount);
				$("#writerTranslatorListOrderCountId").val(writerListOrderCount);
				
			}
			/* inputWriter = inputWriter+"<c:set var='"+firstName+"Count' >"+writerCount+"</c:set>";
			inputWriter = inputWriter+"<c:set var='"+firstName+"ListOrderCount' >"+writerListOrderCount+"</c:set>"; */
			
			
			var div = "#"+divId;
			$(div).prepend(inputWriter); 
			
	}
	
	function textBookVersion() {
		
		 $("#version").empty();
		 var versionId ='${resTextbook.version}';
		 var textBookVersionData={};
		 $.ajax({  
			type:"post",  
			url:"${ctp}/teach/textBookMaster/textBook/textBookVersion",  
			data:textBookVersionData,
			success:function(data) {  
			var map =  eval("("+data+")");
			
			$.each(map,function(key,values){
				 if(${resTextbook.version != ''}&&${resTextbook.version != undefined}&&(versionId == values)){
						
						$("<option value='"+values+"' selected='selected'>"+key+"</option>").appendTo("#version");
						
					}else{
						$("<option value="+values+" >"+key+"</option>").appendTo("#version");
					} 
			}  
			);   
			}  
		});  
		
	}
	
	function textBookPublish() {
		
		 $("#publisherId").empty();
		var publishId ='${resTextbook.publisherId}';
		 var textBookPublishData={};
		 $.ajax({  
			type:"post",  
			url:"${ctp}/teach/textBookMaster/textBook/textBookPublish",  
			data:textBookPublishData,
			success:function(data) {  
			var map =  eval("("+data+")");
			
			$.each(map,function(key,values){
			   if(${resTextbook.publisherId != ''}&&${resTextbook.publisherId != undefined}&&(publishId == values)){
					
					$("<option value='"+values+"' selected='selected'>"+key+"</option>").appendTo("#publisherId");
					
				}else{
					$("<option value="+values+" >"+key+"</option>").appendTo("#publisherId");
				} 
				
				
			}  
			);   
			}  
		});  
		
	}
	
</script>
</html>