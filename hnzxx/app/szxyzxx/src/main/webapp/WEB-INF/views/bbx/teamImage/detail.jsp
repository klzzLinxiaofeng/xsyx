<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<style type="text/css">
.uploadify{position:absolute;opacity:0;left:0;top:0;height: 70px; width: 80px;}
.uploadify-queue{width:80px;}
.myerror {color: red !important;width: 22%;display: inline-block;padding-left: 10px;}
.date .myerror{display:block;}
.img_div{position:relative	}
.block{ background:rgba(165, 165, 165, 0.55); width:100%; height:100%;}
.pic_img{    position: absolute; top: 0;left: 0;z-index:99;top:10px;width: 257px;height: 184px; left: 10px;}
.pic_img p{  position: absolute; top: 0;left: 0;}
.pic_img p img{ width:50px; height:50px;}
.pic_img p.img_ok{ left:50%; top:50%; margin-top:-25px; margin-left:-25px;}
</style>

<div class="widget-container" style="padding: 20px 0 0;">
	<form class="form-horizontal padding15" id="welcomeText_form">								
		<div class="control-group">
			<textarea  id="signature" name="signature" class="span12" style="margin-left:10px;   resize: none;width: 1200px; 
				height: 100px;max-width: 1150px;max-height: 100px;" placeholder="请输入班级口号（最多可输入14个字）"></textarea>
			<input type="hidden" id="backgroundTemplate" name="backgroundTemplate" value="${backgroundTemplate}"/>
			<input type="hidden" id="backgroundFile" name="backgroundFile" value="${backgroundFile}"/>
			<input type="hidden" id="backgroundFileUuid" name="backgroundFileUuid" value="${backgroundFile}"/>
			<input type="hidden" id="teamId" name="teamId" value="${teamId}"/>
		</div>	

		<span style="margin-left:10px;">选择背景模版：</span>				
		<div class="bjxc_list" id="layer-photos-demo">
			<div class="img_div" onclick="selectTemplate_pic(this,0)">
				<div class="pic_img" id="picImg0">
					<p class="block"></p>
					<p class="img_ok"><img src="${ctp}/res/images/bbx/bp/ok.png"></p>
				</div>	
				<a href="javascript:void(0)"><img src="${ctp}/res/images/bbx/bp/bpImage1.png"></a>
			</div>
			<div class="img_div" onclick="selectTemplate_pic(this,1)">
				<div class="pic_img" id="picImg1">
					<p class="block"></p>
					<p class="img_ok"><img src="${ctp}/res/images/bbx/bp/ok.png"></p>
				</div>
				<a href="javascript:void(0)"><img src="${ctp}/res/images/bbx/bp/bpImage2.png"></a>
			</div>
			<div class="img_div" onclick="selectTemplate_pic(this,2)">
				<div class="pic_img" id="picImg2">
					<p class="block"></p>
					<p class="img_ok"><img src="${ctp}/res/images/bbx/bp/ok.png"></p>
				</div>
				<a href="javascript:void(0)"><img src="${ctp}/res/images/bbx/bp/bpImage3.png"></a>
			</div>
			<div class="img_div" onclick="selectTemplate_pic(this,3)">
				<div class="pic_img" id="picImg3">
					<p class="block"></p>
					<p class="img_ok"><img src="${ctp}/res/images/bbx/bp/ok.png"></p>
				</div>
				<a href="javascript:void(0)"><img src="${ctp}/res/images/bbx/bp/bpImage4.png"></a>
			</div>
			<div class="img_div" onclick="selectTemplate_pic(this,4)">
				<div class="pic_img" id="picImg4">
					<p class="block"></p>
					<p class="img_ok"><img src="${ctp}/res/images/bbx/bp/ok.png"></p>
				</div>
				<a href="javascript:void(0)"><img src="${ctp}/res/images/bbx/bp/bpImage5.png"></a>
			</div>
			<div class="clear"></div>							
		</div>
		<span style="margin-left:10px;">自定义添加背景：</span>
		<div class="bjxc_list" id="definedImageDiv" >
			<div class="img_div" onclick="selectFile(this)">
				<div class="pic_img">
					<p class="block"></p>
					<p class="img_ok"><img src="${ctp}/res/images/bbx/bp/ok.png"></p>
				</div>
				<a href="javascript:void(0)"><img id="definedImage" src=""></a>
			</div>	
		</div> 
		
		<div class="control-group" >
			<div class="controls update_img" style="margin:15px 0 0 20px;">
				<div style="position:relative;float:left">
					<div><a href="#" class="tianjia"><img src="${ctp}/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div>
					<input type="hidden" id="uploader" name="uploader"/>
				</div>
			</div>	
		</div>	
		<div class="control-group" style="margin-left:5px;">
<%-- 		<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}"> --%>
			<button class="btn btn-warning" type="button" id="sosuo" onclick="saveTeamImage()" >保存</button>
<%-- 			</c:if>	 --%>
		</div>													
	</form>
</div>

<script type="text/javascript">
var checker;
$(function() {	
	checker = initValidator();	
	$(".bjxc_list .pic_img").each(function(){
		$(this).hide();
	});
	
	
	$("#signature").val("${signature}");
	var backgroundFileUrl = "${backgroundFileUrl}";
	if(backgroundFileUrl != ""){
		$("#definedImage").attr("src", backgroundFileUrl);
		$("#definedImageDiv").find('.pic_img').show();
	}else{
		$("#definedImageDiv").hide();
		var backgroundTemplate = "${backgroundTemplate}";
		if(backgroundTemplate != ""){
			$("#picImg" + backgroundTemplate).show();
		}
	}	
	
	uploadFile();
});

function selectTemplate_pic(div, template){
	$(".bjxc_list .pic_img").each(function(){
		$(this).hide();
	});
	$(div).find('.pic_img').show();
	
	/* if($(div).find('.pic_img').is(":hidden")){
		$(div).find('.pic_img').show();
	}else{
		$(div).find('.pic_img').hide();
	} */
	
	$("#backgroundTemplate").val(template);
	$("#backgroundFile").val("");
}


function selectFile(div){
	$(".bjxc_list .pic_img").each(function(){
		$(this).hide();
	});
	$(div).find('.pic_img').show();
	
	$("#backgroundTemplate").val("");
	$("#backgroundFile").val($("#backgroundFileUuid").val());
}

function initValidator() {
	return $("#welcomeText_form").validate({
		errorClass : "myerror",
		rules : {	
			"signature" : {
				maxlength : 14
			}
		},
		messages : {
			
		}
	});
}



function saveTeamImage(){
	/* var backgroundFile = $("#backgroundFile").val();
	var backgroundTemplate = $("#backgroundTemplate").val();
	alert(backgroundFile + "~~~~~" + backgroundTemplate + "~~~~~"); */
	if (checker.form()) {
		var loader = new loadLayer();
		var url = "${pageContext.request.contextPath}/bbx/teamImage/creator";		
		var $requestData = {};
		$requestData.teamId = $("#teamId").val();
		$requestData.backgroundTemplate = $("#backgroundTemplate").val();
		$requestData.backgroundFile = $("#backgroundFile").val();	
		var signature=document.getElementById("signature").value; 
		signature=signature.replace('\n',' '); 
		document.getElementById("signature").value=signature;
		$requestData.signature = $("#signature").val();
		loader.show();
		$.post(url, $requestData, function(data, status) {				
			if("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					getTeamImage();
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


//图片上传
function uploadFile(){
	var obj = $("#uploader").uploadify({
	    swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
	    uploader: '${pageContext.request.contextPath}/uploader/common',
	    formData: {'jsessionId': '<%=request.getSession().getId()%>'},
	    fileObjName: 'file',
	    fileTypeDesc: "文件上传",
	    fileTypeExts: "*.gif; *.jpg; *.png;*.jpeg;*.bmp", //默认*.*
	    method: 'post',
	    multi: false, // 是否能选择多个文件
	    auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
	    removeTimeout: 1,
	    queueSizeLimit: 1,
	    fileSizeLimit: 4 * 1024,
	    buttonText: "上传文件",
	    requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
	    height: 67,
	    width: 80,
	    onUploadSuccess: function(file, data, response) {
	    	//alert(JSON.stringify(data));
	    	var $jsonObj = eval("(" + data + ")");
	    	$("#definedImageDiv").show();
	    	$("#definedImage").attr("src", $jsonObj.url);
	    	$("#backgroundFile").val($jsonObj.uuid);
	    	$("#backgroundFileUuid").val($jsonObj.uuid);
	    	$("#backgroundTemplate").val("");
	    	$(".bjxc_list .pic_img").each(function(){
	    		$(this).hide();
	    	});
	    	$("#definedImageDiv").find('.pic_img').show();
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
