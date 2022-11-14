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
.uploadify-button {
background:url("${pageContext.request.contextPath}/res/css/extra/images/tpsc.png") no-repeat;
	width:120px;
	height:30px;
	display:block;
	left:172px;
	bottom:21px;
	}
 .uploadify-queue-item .cancel{
 display: none;

    }
    .form-horizontal .controls select{
    	margin-bottom:10px;
    }
</style>
</head>
<body>
<div class="container-fluid" style="padding:0">

		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">

					<div class="class_detail">
						<form class="form-horizontal tan_form" id="jctextbook_form" action="javascript:void(0);">


							<%-- <div class="control-group" style="width:100%;">
								<label class="control-label">缩略图：</label>
								<div class="controls">
									<div class="up_images">
										<c:if test="${not empty  jcTextbook.image}">
											<img src="${pageContext.request.contextPath}/res/images/tubiao.jpg"/>
										</c:if>
										<c:if test="${empty jcTextbook.image}">
											<img src="<entity:getHttpUrl uuid="${jcTextbook.image}"/>"  id="imgId"/>
										</c:if>
										<div class="right">
											<p>建议分辨率：宽120*高170</p>
											<input type="text" id="image" name="image" value="${jcTextbook.image}" style="width:150px;"/>
											<input type="file" id="uploader" name="uploader"/>

											<!-- <a href="javascript:void(0)" class="up_img"></a> -->
										</div>

									</div>
								</div>
							</div> --%>

							<%-- <div class="control-group">
								<label class="control-label">
									缩略图：
								</label>
								<div class="controls">
								<input type="text" id="image" name="image" value="${jcTextbook.image}" style="width:150px;"/>
								</div>

							</div> --%>
							<%-- <div class="control-group">
								<label class="control-label">
									书籍封面：
								</label>
								<div class="controls">
								<div class="up_img">
									<img src="${pageContext.request.contextPath}/res/images/cover_default_new.png" id="imgId_"/>
									   <p>建议分辨率:宽120x高170</p>
									   <input type="file" id="uploader_" name="uploader_" class="up_img"/>
									<a href="javascript:void(0)"></a>
									<input type="hidden" id="image" name="image" value=""/>
								</div>
								</div>
							</div> --%>


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
									   <p style="width: 207px;">建议分辨率:宽120x高171</p>
										<input type="file" id="uploader" name="uploader" class="up_img"/>
										<input type="hidden" id="entityId" name="image" value=""/>
										<input type="hidden" id="entityId" name="entityId" value=""/>
								</div>
								</div>
							</div>



							<%-- <div class="control-group" style="width:100%;">
									<div class="controls">
										<div class="up_images">
											<img src="${pageContext.request.contextPath}/res/images/500.jpg" id="imgId_"/>
											<div class="right">
												<p>支持jpg、gif、png、bmp格式，大小：400*260</p>
												<input type="file" id="uploader_" name="uploader_" class="up_img"/>
											</div>
											<input type="hidden" id="entityId_image" name="entityId_image" value=""/>
										</div>
									</div>
							</div> --%>



							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>书名：
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

								</div>

							</div>

							<div class="control-group">
								<label class="control-label">
									作者：
								</label>
								<div class="controls" id = "writerMainAdd">

								<c:set var="writerMainCountTemp">${fn:length(jcTextbook.writerMain)}</c:set>

								<c:forEach items="${jcTextbook.writerMain}" var="writerMain" varStatus="status">
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
								<c:set var='writerTranslatorCountTemp' >${fn:length(jcTextbook.writerTranslator)}</c:set>
								<c:forEach items="${jcTextbook.writerTranslator}" var="writerTranslator" varStatus="status">
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
									placeholder="" value="${jcTextbook.price}" style="width:220px;">
								</div>

							</div>

							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>出版社：
								</label>
								<div class="controls">
								<select  id="publisherId" name="publisherId"  style="width:220px;"></select>
								<%-- <input type="text" id="publisherId" name="publisherId" class="span13"
									placeholder="" value="${jcTextbook.publisherId}" style="width:220px;"> --%>
								</div>

							</div>

							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>出版日期：
								</label>
								<div class="controls">
								<%-- value="<fmt:formatDate pattern='yyyy-MM-dd' value='${jcTextbook.publishDate}'></fmt:formatDate>" --%>
								<input type="text" id="publishDate" name="publishDate" class="span13"
								placeholder="出版日期" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${jcTextbook.publishDate}'></fmt:formatDate>" onclick="WdatePicker();" style="width:220px;">
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
									placeholder=""style="width:450px;height:200px;">${jcTextbook.description} </textarea>
								</div>

							</div>

							<div class="control-group">
								<label class="control-label">
									作者介绍：
								</label>
								<div class="controls">
								
								<textarea  rows="3"  id="writerDescription" name="writerDescription" class="span13"
									placeholder=""style="width:450px;height:200px;"> </textarea>
								</div>

							</div>

						<input type="hidden"  id = "writerMainCountId" value="${writerMainCountTemp}"/>
						<input type="hidden"  id = "writerMainListOrderCountId" value="${writerMainListOrderCountTemp}"/>
						<input type="hidden"  id = "writerTranslatorCountId" value="${writerTranslatorCountTemp}"/>
						<input type="hidden"  id = "writerTranslatorListOrderCountId" value="${writerTranslatorListOrderCountTemp}"/>

						<%-- <div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${jcTextbook.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div> --%>
						<div class="control-group">
								<label class="control-label">
								</label>
								<div class="controls">
								<input type="hidden" id="id" name="id" value="${jcTextbook.id}" />
								<button class="btn btn-success" type="button"
									onclick="saveOrUpdate();"><i class="fa fa-check-square-o"></i> 确定</button>
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
	$(function() {

		if('${isCK}' == 'disable'){
			$("form input").prop("readonly", true);
		}

		if('${jcTextbook.binding}' == 1||
				'${jcTextbook.binding}' == 2||
				'${jcTextbook.binding}' == 3||
				'${jcTextbook.binding}' == 4){

			var name = 'radio'+'${jcTextbook.binding}';
			$("#"+name).attr("checked",true);

		}else{
			$("#radio5").attr("checked",true);
			$("#binding").val('${jcTextbook.binding}');
		}

		$('#binding').focus(function() {
			$("#radio5").attr("checked",true);
		});
		$('#binding').blur(function() {
			$("#radio5").val($("#binding").val());
		});


		if('${jcTextbook.type}' == 1||'${jcTextbook.type}' == 2||'${jcTextbook.type}' == 3){
			var name ='type'+'${jcTextbook.type}';
			$("#"+name).attr("select","selected");
		}

		//学段
		$.jcSelector("#stageCode",{"tn" : "jc_stage"}, '${jcTextbook.stageCode}', function(data) {
			return {"val" : data.code, "title" : data.name};
		}, function() {
			$('#stageCode option').sort(function(a,b){
    		    var aText = $(a).val();
    		    var bText = $(b).val();
    		    if(aText>bText) return 1;
    		    if(aText<bText) return -1;
    		    return 0;
    		}).appendTo('#stageCode') ;
			$('#stageCode').val("");
		});
		//学科
		$.jcSelector("#subjectCode",{"tn" : "jc_subject", "pn" : "subjectClass", "value" : "1"}, '${jcTextbook.subjectCode}', function(data) {
			return {"val" : data.code, "title" : data.name};
		}, function() {

		});

		/* $.jcSelector("#demo". {"tn" : "jc_subject", "pn" : "stageCode", "value" : "2"}, "", function(data) {
			return {"val" : data.code, "title" : data.name};
		}); */
		//年级
		$.jcSelector("#gradeCode",{"tn" : "jc_grade"}, '${jcTextbook.gradeCode}', function(data) {
			return {"val" : data.code, "title" : data.name};
		}, function() {
			$('#gradeCode option').sort(function(a,b){
    		    var aText = $(a).val();
    		    var bText = $(b).val();
    		    if(aText>bText) return 1;
    		    if(aText<bText) return -1;
    		    return 0;
    		}).appendTo('#gradeCode');
			$('#gradeCode').val("");
		});


		//册次
		$.jcSelector("#volumn",{"tn" : "jc_textbook_volumn"}, '${jcTextbook.volumn}', function(data) {
			return {"val" : data.code, "title" : data.name};
		}, function() {
			$('#volumn option').sort(function(a,b){
    		    var aText = $(a).val();
    		    var bText = $(b).val();
    		    if(aText>bText) return 1;
    		    if(aText<bText) return -1;
    		    return 0;
    		}).appendTo('#volumn') ;
			$('#volumn').val("");
		});
		//版本'${jcTextbook.version}'
		/* $.jcSelector("#version",{"tn" : "jc_textbook_version"}, "", function(data) {
			return {"val" : data.id, "title" : data.name};
		}, function() {

		}); */
		textBookVersion();


		//出版社
		/* $.jcSelector("#publisherId",{"tn" : "jc_textbook_publisher"}, '${jcTextbook.publisherId}', function(data) {
			return {"val" : data.id, "title" : data.name};
		}, function() {
			
		}); */
		textBookPublish();

		checker = initValidator();
		uploadImageFile();
	});

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
           buttonText: "",
           requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
           height: 30,
           width: 120,
           onUploadSuccess: function(file, data, response) {
          	 var $jsonObj = eval("(" + data + ")");
          	 $("#entityId").val($jsonObj.uuid);
          	 $("#imgId").attr('src',$jsonObj.url);
          	$("#imgId").css("color","red");
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
		 var textBookVersionData={};
		 $.ajax({
			type:"post",
			url:"${ctp}/teach/textBookMaster/textBook/textBookVersion",
			data:textBookVersionData,
			success:function(data) {
				var map =  eval("("+data+")");
				$("<option selected value=''>请选择</option>").appendTo("#version");
				$.each(map,function(key,values){
					$("<option value="+values+" >"+key+"</option>").appendTo("#version");
				});
			}
		});

	}

	function textBookPublish() {

		 $("#publisherId").empty();
		 var textBookPublishData={};
		 $.ajax({
			type:"post",
			url:"${ctp}/teach/textBookMaster/textBook/textBookPublish",
			data:textBookPublishData,
			success:function(data) {
			var map =  eval("("+data+")");

			$.each(map,function(key,values){

				$("<option value="+values+" >"+key+"</option>").appendTo("#publisherId");
			}
			);
			}
		});

	}

</script>
</html>