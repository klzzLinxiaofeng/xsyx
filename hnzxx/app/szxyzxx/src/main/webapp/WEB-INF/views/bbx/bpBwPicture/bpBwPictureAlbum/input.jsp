<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/bbx/bbx.css" rel="stylesheet">
<link rel="stylesheet" href="${ctp}/res/css/extra/add.css" >
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<style>
/* .row-fluid .span13 {
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
.uploadify .uploadify-button {display:none !important;}
#SWFUpload_0{ display:none !important;}
.uploader-button{display:none !important;} */
.myerror {
	color: red !important;
	width: 22%;
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
.remove{
	margin-top: -35px;
}
.uploadify{
	position:absolute;
	opacity:0;
	left:0;
	top:0;
}
.uploadify-queue{width:80px;position: absolute;
    top: -12px;
    left: -95px;}
.fileName{
	width: 50px;
	height: 15px;
	overflow: hidden;
	display: inline-block;
}
.edit ul{
	padding:0;
}
 #a p{
	padding:0 0 10px 0;min-width:240px;
}
#a p a{
	font-size: 16px;
font-weight: bold;
}
.edit ul li{ /* width:100%; */height:auto;}
</style>
</head>
<body style="background-color: #f3f3f3 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 0;">
					<form class="form-horizontal tan_form" id="bwpicturealbum_form" action="javascript:void(0);">
						<div class="trend">
    					<div class="edit">
    						<div style="padding:20px 0px 0px 20px; display:none;"><!-- type="hidden" -->  
    							<select id="teamId" class="span4 chzn-select" style="width: 120px;"></select>			   
							</div>
        					<div style="padding:0 20px;">
        						<textarea id="name" placeholder="请输入名称"  name="content"
        							class="span12 zy_text span12 left_red {required : true,maxlength:1000}" >${bwPictureAlbum.name}</textarea>	   
							</div>
        					
							<input style="display: none;" type="text" id="id" name="id" class="span13" placeholder="" value="${bwPictureAlbum.id}">
        					
							<%-- <div class="control-group">	
								<div class="controls update_img" style="margin:15px 0 0 20px;">
									显示图片 start
									<c:choose>
										<c:when test="${not empty bwPictureAlbum.imageUrlList}">
											<ul class="zpzs-box">
											<c:forEach items="${bwPictureAlbum.imageUrlList}" var="imageUrl" varStatus="i">
													<li><div class="img"><img src="${imageUrl }" /></div><a href="#" class="tp"  onclick="delEditImageDiv(this.id)" style="display:none;" id="imgEdit${i.index }"><img src="${ctp }/res/css/bbx/images/x.png" class="ww"></a></li>
											</c:forEach>												
											</ul>
											<div style="position:relative;float:left">
												<div><a href="#" class="tianjia"><img src="${ctp}/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div>
												<input type="hidden" id="uploader" name="uploader"/>
											</div>
											<div class="clear"></div>
										</c:when>
										<c:otherwise>
											<ul class="zpzs-box">
											</ul>
											<div style="position:relative;float:left">
												<div><a href="#" class="tianjia" id="imgId"><img src="${ctp }/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div>
												<input type="hidden" id="uploader" name="uploader"/> 
											</div>
											<div class="clear"></div>
										</c:otherwise>
									</c:choose>
									显示图片 end
									<div style="display:none" id="fileUuid">
										 <c:if test="${not empty bwPictureAlbum.imageUuidlList }">
											<c:forEach items="${bwPictureAlbum.imageUuidlList}" var="imageUuid" varStatus="i">
											<input id="editImage${i.index }" value="${imageUuid }"/>
											</c:forEach>
										</c:if>
									</div> 
								</div>
							</div> --%>
							
							<div class="control-group">	
								<div class="controls update_img" style="margin:15px 0 0 20px;">
									<c:choose>
										<c:when test="${not empty bwPictureAlbum.imageDetailList}">
											<ul class="zpzs-box">
											<c:forEach items="${bwPictureAlbum.imageDetailList}" var="entity" varStatus="i">
<%-- 													<li><div class="img"><img src="${entity.imageSrc }" /></div><a href="#" class="tp"  onclick="delEditImageDiv(this.id)" style="display:none;" id="imgEdit${i.index }"><img src="${ctp }/res/css/bbx/images/x.png" class="ww"></a></li> --%>
													<li id="${entity.uuid }"><div class="img"><img src="${entity.url }"/></div><a href="#" class="tp" onclick="delDiv(this)" style="display:none;"><img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww"></a></li>
											</c:forEach>												
											</ul>
											<div style="position:relative;float:left">
												<div><a href="#" class="tianjia"><img src="${ctp}/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div>
												<input type="hidden" id="uploader" name="uploader"/>
											</div>
											<div class="clear"></div>
										</c:when>
										<c:otherwise>
											<ul class="zpzs-box">
											</ul>
											<div style="position:relative;float:left">
												<div><a href="#" class="tianjia"><img src="${ctp }/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div>
												<input type="hidden" id="uploader" name="uploader"/>
											</div>
											<div class="clear"></div>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
						
    					<div class="clear"></div>
    					<div class="form-actions tan_bottom_1">
							<input type="hidden" id="id" name="id" value="" />
							<c:choose>
								<c:when test="${not empty bwPictureAlbum}">
									<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">保存</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">布置</a>
								</c:otherwise>
							</c:choose>
							<a href="javascript:void(0)" onclick="closeWin();">取消</a>
               		 	</div>
					</div>
							<%-- <div class="control-group">
								<label class="control-label">
									id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${bwpicturealbum.id}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									班级id：
								</label>
								<div class="controls">
								<input type="text" id="teamId" name="teamId" class="span13" placeholder="" value="${bwpicturealbum.teamId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									专辑名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${bwpicturealbum.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									专辑封面文件uuid：
								</label>
								<div class="controls">
								<input type="text" id="coverFileUuid" name="coverFileUuid" class="span13" placeholder="" value="${bwpicturealbum.coverFileUuid}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									专辑描述：
								</label>
								<div class="controls">
								<input type="text" id="description" name="description" class="span13" placeholder="" value="${bwpicturealbum.description}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									专辑图片总数量：
								</label>
								<div class="controls">
								<input type="text" id="totalRows" name="totalRows" class="span13" placeholder="" value="${bwpicturealbum.totalRows}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									发送者用户ID：
								</label>
								<div class="controls">
								<input type="text" id="postUserId" name="postUserId" class="span13" placeholder="" value="${bwpicturealbum.postUserId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录创建时间(发表时间）：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${bwpicturealbum.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${bwpicturealbum.modifyDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录是否删除：
								</label>
								<div class="controls">
								<input type="text" id="isDeleted" name="isDeleted" class="span13" placeholder="" value="${bwpicturealbum.isDeleted}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${bwpicturealbum.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div> --%>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
<%-- $(function(){
    $(".zpzs-box li").hover(function(){
        $(this).find(".tp").show();
    },function(){
        $(".zpzs-box li .tp").hide();
    })
   uploadImageFile();
});

 $.createAvartarEditor({
	"avatarLabel" : "班级图片预览，请注意清晰度",
	"title" :  "班级图片编辑",
	"width"          :     "750",
	"height"         :     "530",
	"btn" : "#imgId",
	/* "btn" : "#imgId,.up_img", */
	"avatarSize" : "480,270",
	"avatarSizeLabel" : "1280x720"
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
       //height: 20,
       //width: 66,
       onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
           $("#infoBox").prev("p").css("display", "none");
           $("#infoBox").css("display", "block");
       },
       onUploadError: function(file, errorCode, errorMsg, errorString) {
       	$.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
       }
   });
}

function selectedImgHandler(data) {
	var imageStr = '';
    // 显示已上传的图片
	if(data != null){
		imageStr += '<li id="'+data.uuid+'"><div class="img"><img src="'+data.imgUrl+'"/></div><a href="#" class="tp" onclick="delDiv(this)" style="display:none;"><img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww"></a></li>';
		// 给新建的li绑定事件.
		$(".zpzs-box").append(imageStr);
		$("#fileUuid").append("<input value='"+data.uuid+"'/> ");
		$(".zpzs-box li").hover(function(){
	        $(this).find(".tp").show();
	    },function(){
	        $(".zpzs-box li .tp").hide();
	    })
	}
} --%>

	var checker;
	$(function() {
		checker = initValidator();		
		var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
		var teamId = "${teamId}";
		if ( teamId == null || '' == teamId ) {
			$.BbxRoleTeamAccountSelector({
			  "selector" : "#teamId",
			  "condition" : $requestData,
			  "selectedVal" : "",
			  "afterHandler" : function() {
			   }	
			});	
		} else {
			// $("#teamId").html("");
			$("#teamId").append("<option value='"+teamId+"'></option>");
		}
		uploadFile();
		$(".zpzs-box li").each(function(){
			//alert(1);
			$(this).hover(function(){
	        	$(this).find(".tp").show();
	    	},function(){
	       		$(".zpzs-box li .tp").hide();
	    	}) ;
		});
	});
	
    var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#bwpicturealbum_form").validate({
			errorClass : "myerror",
			rules : {
				"content" : {
					required : true,
					maxlength: 250
				}
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
			var $requestData = {};//formData2JSONObj("#bwinfomore_form");
			//var $requestData = formData2JSONObj("#bwpicturealbum_form");
			$requestData.name = $("#name").val();
			$requestData.teamId = $("#teamId").val();
			var imageUuidlList = new Array();
			/* $("#fileUuid input").each(function(){
				imageUuidlList.push(this.value);
	    	}); */
	    	$('.zpzs-box li').each(function(){
	    		imageUuidlList.push(this.id);
			});
			if(imageUuidlList.length == 0){
				$.error("请上传图片");
				return;
			}
			$requestData.imageUuidlList = imageUuidlList;
			
			var url = "${ctp}/bbx/bpBwPictureAlbum/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/bpBwPictureAlbum/" + $id;
			}
			loader.show();
			if ( "" != $id ) {
				$.ajax({
	                url: url,
	                type: "PUT",
	                contentType : 'application/json;charset=utf-8', 
	                dataType:"json",
	                data: JSON.stringify($requestData),
	                success: function(data, status){
	                	if("success" === status) {
	    					$.success('操作成功');
	    					//data = eval("(" + data + ")");
	    					if("success" === data.info) {
	    						parent.core_iframe.window.search();
	    						$.closeWindow();
	    					} else {
	    						$.error("操作失败");
	    					}
	    				}else{
	    					$.error("操作失败");
	    				}
	    				loader.close();
	                },
	                error: function(res){
	                	$.error("操作失败");
	                }
	            });
			} else {
				$.ajax({
	                url: url,
	                type: "POST",
	                contentType : 'application/json;charset=utf-8', 
	                dataType:"json",
	                data: JSON.stringify($requestData),
	                success: function(data, status){
	                	if("success" === status) {
	    					$.success('操作成功');
	    					//data = eval("(" + data + ")");
	    					if("success" === data.info) {
	    						parent.core_iframe.search();
	    						$.closeWindow();
	    					} else {
	    						$.error("操作失败");
	    					}
	    				}else{
	    					$.error("操作失败");
	    				}
	    				loader.close();
	                },
	                error: function(res){
	                	$.error("操作失败");
	                }
	            });
			}
			/* $.post(url, $requestData, function(data, status) {
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
			});  */
		}
	}
	
	var step = 0;
	function uploadFile(){
    	var obj = $("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
            fileObjName: 'file',
            fileTypeDesc: "文件上传",
            fileTypeExts: "*.gif; *.jpg; *.png; *.jpeg; *.bmp;", //默认*.*
            method: 'post',
            multi: true, // 是否能选择多个文件
            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout: 1,
            queueSizeLimit: 100,
            fileSizeLimit: 10 * 1024,
            buttonText: "上传文件",
            requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
            height: 67,
            width: 80,
            onUploadSuccess: function(file, data, response) {
	           	var $jsonObj = eval("(" + data + ")");
	           	var uuid = "uuid"+step;
	           	var img = "img"+step;
	           	var suffix=$jsonObj.suffix;
	           	var url=$jsonObj.url;
	            //alert($jsonObj.url + "~~" + $jsonObj.uuid +"~~~");
	           /*  if(suffix=="gif"||suffix=="jpg"||suffix=="png"||suffix=="jpeg"||suffix=="bmp"){
	            	$(".zpzs-box").append("<li><div class='img'><img src='"+url
	            			+"' /></div><a href='#' class='tp'  onclick='delDiv(this.id)' style='display:none;' id='img'><img src='${ctp}/res/css/bbx/images/x.png' class='ww'></a></li>")
	            }
	            $("#fileUuid").append("<input id='"+uuid+"' value='"+$jsonObj.uuid+"'/> "); */
	            var imageStr = '<li id="'+$jsonObj.uuid+'"><div class="img"><img src="'+$jsonObj.url+'"/></div><a href="#" class="tp" onclick="delDiv(this)" style="display:none;"><img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww"></a></li>';
	            var uuidList = new Array();
	            $('.zpzs-box li').each(function(){
	            	uuidList.push(this.id);
				});
				if ( $.inArray($jsonObj.uuid, uuidList) < 0 ) {
		            $(".zpzs-box").append(imageStr);
		            // 给新建的li绑定事件.		
					$(".zpzs-box li").hover(function(){
			        	$(this).find(".tp").show();
			    	},function(){
			       		$(".zpzs-box li .tp").hide();
			    	})      
		            step++;
				}  else {
					$.error("本相册已存在该图片!");
				}
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
	
	function delDiv(a){
		$(a).closest('li').remove();
	}
	
</script>
</html>