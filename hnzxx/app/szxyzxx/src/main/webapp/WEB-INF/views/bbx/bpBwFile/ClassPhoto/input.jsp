<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/bbx/bbx.css" rel="stylesheet">
<style type="text/css">
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

. form-horizontal{position:relative;}
 /*    .form-horizontal .control-group{
    	width:45%;
    	display:inline-block;
    } */
    .table thead th{height:34px;line-height:34px;}
    legend + .control-group{margin-top:0;}
    
    table td .up_img {
    background: url("${pageContext.request.contextPath}/res/css/extra/images/up_img.jpg") no-repeat;
    display: block;
    height: 30px;
    width: 98px;
    position:absolute;
    top:230px;
    left:30px;
}
td input[type="text"],td select,td textarea{margin-bottom:0;width:100%;}
td input[type="text"],td textarea{border:0 none;width:95%;}
table{background-color:#fff;}
table td img{width:155px;height:175px;margin-left:5px;cursor:pointer;}
.red {
    color: red;
    padding: 0 5px;
}
 .myerror {
		color: red !important;
		width: 34%;
		display: inline-block;
		padding-left: 10px;
	}
.btn-extend {
    color: #ffffff;
    background-color: #e88a05;
    position:fixed;
    bottom:12px;
    z-index:1;
}
.button-back{
	left:340px;
}
.button-next,#stepy1-buttons-6 .button-back{
	left:412px;
}
.btn-extend:hover{
	background-color: #BF7204;
}
.chzn-container-single .chzn-search input{box-sizing: border-box;}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="bwfile_form" action="javascript:void(0);">
					
							<div class="control-group">
								<label class="control-label">
									?????????????????????
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${bwfile.name}">
								</div>
							</div>
							
							
					
					<div class="trend">
				    <div class="edit">
				        <%-- <textarea id="content" name="content" placeholder="????????????????????????">${circleMessage.content}</textarea> --%>
				        <ul class="zpzs-box" style="padding-top: 0px; padding-left: 20px;">
				        <c:forEach items="${thumbs}" var="thumb">
					        <li id="${thumb.uuid}" thumbId="${thumb.thumbUuid}">
					        	<div class="img"><img src="${thumb.url}"/>
					        	</div>
					        	<a href="#" class="tp" onclick="delDiv(this)" style="display:none;">
					        		<img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww">
					        	</a>
					        </li>
				       	</c:forEach>
						</ul>
				    </div>
				  
    				<div class="clear"></div>
					</div>
					
					<a class="control-label">
						<input type="button" value="????????????" id="imgId"> 
						<%-- <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" id="imgId"> --%>
						<a href="javascript:void(0)" class="up_img"></a> 
						<!-- <input type="file" id="uploader" name="uploader"/> -->
						<input type="hidden" id="photoUuid" name="photoUuid" value=""/>
					</a>
					
					<div style="padding-left: 25px;" class="edit">
						<div id="uploads"></div>
					</div>

							<div style="display: none;" class="control-group">
								<label class="control-label">
									id???
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${bwfile.id}">
								</div>
							</div>
							<div style="display: none;" class="control-group">
								<label class="control-label">
									?????????
								</label>
								<div class="controls">
								<input type="text" id="teamId" name="teamId" class="span13" placeholder="" value="${bwfile.teamId}">
								</div>
							</div>
							<div style="display: none;" class="control-group">
								<label class="control-label">
									?????????
								</label>
								<div class="controls">
								<input type="text" id="fileId" name="fileId" class="span13" placeholder="" value="${bwfile.fileId}">
								</div>
							</div>
							<div style="display: none;" class="control-group">
								<label class="control-label">
									??????ID???
								</label>
								<div class="controls">
								<input type="text" id="postUserId" name="postUserId" class="span13" placeholder="" value="${bwfile.postUserId}">
								</div>
							</div>
							<div style="display: none;" class="control-group">
								<label class="control-label">
									?????????????????????????????????1=???????????? 2=???????????? 3=???????????????
								</label>
								<div class="controls">
								<input type="text" id="objectType" name="objectType" class="span13" placeholder="" value="${bwfile.objectType}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${bwfile.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">??????</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
    $(".zpzs-box li").hover(function(){
        $(this).find(".tp").show();
    },function(){
        $(".zpzs-box li .tp").hide();
    })
    uploadImageFile();
});

$.createAvartarEditor({
	"avatarLabel" : "???????????????????????????????????????",
	"title" :  "??????????????????",
	"width"          :     "750",
	"height"         :     "530",
	"btn" : "#imgId",
	/* "btn" : "#imgId,.up_img", */
	"avatarSize" : "240,135",
	"avatarSizeLabel" : "640x360"
});

function uploadImageFile(){
	 var obj = $("#uploader").uploadify({
       swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
       uploader: '${pageContext.request.contextPath}/uploader/common',
       formData: {'jsessionId': '<%=request.getSession().getId()%>'},
       fileObjName: 'file',
       fileTypeDesc: "????????????",
       fileTypeExts: "*.jpg",
       method: 'post',
       multi: false, // ???????????????????????????
       auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
       removeTimeout: 1,
       queueSizeLimit: 1,
       fileSizeLimit: 10 * 1024,
       buttonText: "????????????",
       requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
       height: 20,
       width: 66,
       /* onUploadSuccess: function(file, data, response) {
      	 var $jsonObj = eval("(" + data + ")");
      	 $("#photoUuid").val($jsonObj.uuid);
      	 
      	 $("#imgId").attr('src',$jsonObj.url); 
      	
       }, */
       onUploadStart: function(file) { //???????????????????????????????????????????????????
           $("#infoBox").prev("p").css("display", "none");
           $("#infoBox").css("display", "block");
       },
       onUploadError: function(file, errorCode, errorMsg, errorString) {
       	$.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
       }
   });
}

function selectedImgHandler(data) {
//		alert(JSON.stringify(data));
	/* $("#imgId").attr("src", data.imgUrl);
	$("#photoUuid").val(data.uuid) */
	
	var imageStr = '';
    // ????????????????????????
	if(data != null){
		var fileIds = $("#fileId").val();
		fileIds += data.uuid+",";
		$("#fileId").val(fileIds);
		imageStr += '<li id="'+data.uuid+'"><div class="img"><img src="'+data.imgUrl+'"/></div><a href="#" class="tp" onclick="delDiv(this)" style="display:none;"><img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww"></a></li>';
		// ????????????li????????????.
			$(".zpzs-box").append(imageStr);
		$(".zpzs-box li").hover(function(){
	        $(this).find(".tp").show();
	    },function(){
	        $(".zpzs-box li .tp").hide();
	    })
	}
}
	var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#bwfile_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}

	//?????????????????????
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#bwfile_form");
			var url = "${ctp}/bbx/bpBwFile/ClassPhoto/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/bpBwFile/ClassPhoto/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('????????????');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						$.error("????????????");
					}
				}else{
					$.error("????????????");
				}
				loader.close();
			});
		}
	}
	
	
	
	
	/**	
	$(function(){
		  var uploadify_onSelectError = function(file, errorCode, errorMsg) {  
		        var msgText = "????????????\n";  
		        switch (errorCode) {  
		            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:  
		                //this.queueData.errorMsg = "?????????????????? " + this.settings.queueSizeLimit + "?????????";  
		                msgText += "?????????????????? " + this.settings.queueSizeLimit + "?????????";  
		                break;  
		            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:  
		                msgText += "????????????????????????( " + this.settings.fileSizeLimit + " )";  
		                break;  
		            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:  
		                msgText += "???????????????0";  
		                break;  
		            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:  
		                msgText += "?????????????????????????????? " + this.settings.fileTypeExts;  
		                break;  
		            default:  
		                msgText += "???????????????" + errorCode + "\n" + errorMsg;  
		        }  
		       // alert(msgText);  
		        $("#imgUpload").append("<p><font color='green'>???????????????"+msgText+"</font></p>")
		    };  
		    
		var uploadify_onSelect = function(){  
		   //$('#confirmUpload').show();
		};  
		
 		//????????????????????????   
		var uploadify_onUploadSuccess = function(file, data, response) {  
		    var imageStr = '';
		    data = eval("("+data+")");
		    // ????????????????????????
			if(data != null){
				var fileIds = $("#fileId").val();
				fileIds += data.uuid+",";
				$("#fileId").val(fileIds);
				imageStr += '<li id="'+data.uuid+'"><div class="img"><img src="'+data.url+'"/></div><a href="#" class="tp" onclick="delDiv(this)" style="display:none;"><img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww"></a></li>';
				// ????????????li????????????.
					$(".zpzs-box").append(imageStr);
				$(".zpzs-box li").hover(function(){
			        $(this).find(".tp").show();
			    },function(){
			        $(".zpzs-box li .tp").hide();
			    })
				
			}
		};  
		
		var uploadify_config = {  
			'auto'  : true, 
			'multi' : true,  
	        'swf'   : '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
	        'uploader' : '${pageContext.request.contextPath}/uploader/common',
	        //'script' : '/admin/familyschoolOfficeDocument/upload',
	        'hideButton':true,
	        'wmode' : 'transparent',
	        'width' : '80' ,
	        'height' : '32',
	        'buttonCursor':'hand',
	        //'buttonClass':'some-class',
		    'removeTimeout' : 0,  
		    'buttonText' : '????????????',  
		    //'buttonImage' : '${pageContext.request.contextPath}/res/css/bbx/images/add.jpg',
		    'hideButton' : 'true',
		    'fileTypeExts':'*.png;*.jpg;*.jpeg;*.gif;*.bmp',
		    //'buttonImage':'${ctxStatic}/images/btn.png',
		    'fileSizeLimit' : '10MB',  
		    'formData' : {jsessionId:"${pageContext.session.id}"},  //??????FLASH???BUG?????????FF????????????????????????SESSION???????????????formData?????????
		    'overrideEvents' : [ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],  
		    'onSelect' : uploadify_onSelect,  
		    'onSelectError' : uploadify_onSelectError,  
		    //'onUploadError' : uploadify_onUploadError,  
		    'onUploadSuccess' : uploadify_onUploadSuccess,
		    'onDialogClose'  : function(queueData) {
	        },
	        onCancel:function(file){
	        },
	        onClearQueue:function(){
	        },
	        onQueueComplete:function(queueData){
	        }
		};  
		
		//????????????????????????   
		$("#uploads").uploadify(uploadify_config);
		//---------------------- ?????????uploadify??????????????????----------------------
	  }) */
	
</script>
</html>