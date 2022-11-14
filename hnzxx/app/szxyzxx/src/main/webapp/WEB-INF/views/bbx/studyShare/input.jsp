<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link rel="stylesheet" href="${ctp}/res/css/extra/add.css" >
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<style>
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
.edit ul li{height:auto;overflow: hidden;}
</style>
</head>
<body style="background-color: #f3f3f3 !important">
<div class="row-fluid">
	<div class="span12">
 		<div class="content-widgets" style="margin-bottom:0">
			<div class="widget-container" style="padding: 0;">
				<form class="form-horizontal tan_form" id="studyShare_form" action="javascript:void(0);">
					<div class="trend">
    					<div class="edit">
    						<div style="padding:20px 0px 0px 20px; display: none;">
    							<select  id="teamId" class="span4 chzn-select" style="width: 120px;"></select>			   
							</div>	
<!--         					<div style="padding:0 20px;"> -->
<!--         						<textarea id="content" placeholder="请输入内容"  name="content" -->
<%--         							class="span12 zy_text span12 left_red {required : true,maxlength:1000}" >${studyShare.content}</textarea>	    --%>
<!-- 							</div> -->
        
							<div class="control-group">	
								<div class="controls update_img" style="margin:15px 0 0 20px;">
									<c:choose>
										<c:when test="${not empty studyShare.imageDetailList}">
											<ul class="zpzs-box">
											<c:forEach items="${studyShare.imageDetailList}" var="entity" varStatus="i">
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
									<div style="display:none" id="fileUuid">
										 <c:if test="${not empty studyShare.imageDetailList }">
											<c:forEach items="${studyShare.imageDetailList}" var="entity" varStatus="i">
											<input id="editImage${i.index }" value="${entity.uuid }"/>
											</c:forEach>
										</c:if>
										
									</div> 
								</div>
							</div>
						</div>
    					<div class="clear"></div>
    					<div class="form-actions tan_bottom_1">
							<input type="hidden" id="id" name="id" value="${studyShare.id }" />
							<c:choose>
								<c:when test="${not empty studyShare}">
									<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">保存</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate();">布置</a>
								</c:otherwise>
							</c:choose>
							<a href="javascript:void(0)" onclick="$.closeWindow();">取消</a>
               		 	</div>
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
		var teamId = "${teamId}";
		if ( teamId == null || '' == teamId ) {
		var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
		$.BbxRoleTeamAccountSelector({
		  "selector" : "#teamId",
		  "condition" : $requestData,
		  "selectedVal" : "",
		  "afterHandler" : function() {
			}	
		});
		} else {
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
	
	function initValidator() {
		return $("#studyShare_form").validate({
			errorClass : "myerror",
			rules : {
// 				"content" : {
// 					required : true,
// 					maxlength: 200
// 				}
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
			$requestData.content = $("#content").val();
			$requestData.teamId = $("#teamId").val();
			$requestData.id = $id;
			var imageUuidlList = new Array();
			/* $("#fileUuid input").each(function(){
				imageUuidlList.push(this.value);
		    }) */
		    $('.zpzs-box li').each(function(){
		    	imageUuidlList.push(this.id);
			});
			//alert(imageUuidlList.join("-"));
			//return;
		    if(imageUuidlList.length == 0){
				$.error("请上传图片");
				return;
			}
			$requestData.imageUuidlList = imageUuidlList;
			var url = "${ctp}/bbx/studyShare/creator";
			if ("" != $id) {
				url = "${ctp}/bbx/studyShare/modify";
			}
			loader.show();
			
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
						$.dow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			}); */
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
            queueSizeLimit: 9,
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
            /* if(suffix=="gif"||suffix=="jpg"||suffix=="png"||suffix=="jpeg"||suffix=="bmp"){
            	$(".zpzs-box").append("<li><div class='img'><img src='"+url
            			+"' /></div><a href='#' class='tp'  onclick='delDiv(this.id)' style='display:none;' id='img'><img src='${ctp}/res/css/bbx/images/x.png' class='ww'></a></li>");
            	// 给新建的li绑定事件.
				//$(".zpzs-box").append(imageStr);
				$(".zpzs-box li").hover(function(){
		        	$(this).find(".tp").show();
		    	},function(){
		       		$(".zpzs-box li .tp").hide();
		    	})
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
			
			var divLen = $('.zpzs-box').find('li').length;
		    var sunLen = 9;
		    if(divLen>=sunLen){
		    	$('#uploader').hide();
		    	$('#uploader-queue').html('');
		    }else{
		    	$('#uploader').show();
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
		var divLen = $('.zpzs-box').find('li').length;
		var sunLen = 9;
		if(divLen>=sunLen){
	    	$('#uploader').hide();
	    }else{
	    	$('#uploader').show();
	    }
	}
</script>
</html>