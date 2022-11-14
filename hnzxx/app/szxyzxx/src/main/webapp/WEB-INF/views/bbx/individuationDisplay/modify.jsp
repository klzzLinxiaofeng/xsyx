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
.edit ul li{height:auto;}
#content{margin:0px; width:97%;}
input[type=radio]{vertical-align: sub; margin-right:5px; margin-left:8px;}

.chzn-container{vertical-align:middle;margin-right:15px;}
.chzn-container .chzn-results li{line-height:25px;width:100%;}

.widget-container {
    padding: 20px 20px 1px 20px;
    background: #f3f3f3;
}
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
    					
									
						<div class="control-group" style="margin-left: 20px;padding-top: 20px; ">
							<input type="text" id="title" name="title" class="span12" style="width:680px;" 
								placeholder="请输入标题" value="${individuationDisplay.title}"/>
						</div>
						
						<div class="control-group" style="margin-left: 20px">
						<span style="margin-right:2px;">高考时间：</span>
							<input style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"
									class="sj_time" id="examDate" name="examDate"
									onclick="WdatePicker({minDate:'%y-%M-%d %H:%m:%s', dateFmt:'yyyy-MM-dd HH:mm'});"
									placeholder="高考时间" value="<fmt:formatDate value="${individuationDisplay.examDate}" pattern="yyyy-MM-dd HH:mm"/>" type="text">
						</div>
						
						<div class="control-group" style="margin-left: 20px">
						<span style="margin-right:2px;">是否每日循环：</span>
							<input type="radio" id="isCirculate" name="isCirculate" value="1"/>是
							<input type="radio" id="isCirculate" name="isCirculate" value="0"/>否
						</div>
						
						
						<div class="control-group" style="margin-left: 20px">
							<input type="radio" id="contentType1" name="contentType" value="1" onclick="clickRadio(1)"/>文字
							<input type="radio" id="contentType2" name="contentType" value="2" onclick="clickRadio(2)"/>图片
						</div>
						
						<div style="margin-left: 20px; margin-top:20px; margin-bottom:20px;">
							<input type="radio" id="check" name="check" onclick="clickRadioT(1)" value="1">立即发布
							<input type="radio" id="check" name="check" onclick="clickRadioT(2)" value="2">
							<input type="text" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"
									class="sj_time" id="startTime" name="startTime"
									onclick="WdatePicker({minDate:'%y-%M-%d %H:%m:%s', dateFmt:'yyyy-MM-dd HH:mm'});"
									placeholder="触发时间"  disabled="disabled"
									value="<fmt:formatDate value="${individuationDisplay.startTime}" pattern="yyyy-MM-dd HH:mm"/>">
							<input type="text" style="height: 34px; line-height: 34px; font-size: 12px;width:200px;"
									class="sj_time" id="finishTime" name="finishTime"
									onclick="WdatePicker({minDate:'#F{$dp.$D(\'startTime\',{m:+5})}', dateFmt:'yyyy-MM-dd HH:mm'});"
									placeholder="结束时间" value="<fmt:formatDate value="${individuationDisplay.finishTime }" pattern="yyyy-MM-dd HH:mm"/>">
						</div>
    						
    					<div style="margin-left: 20px" id="contentTextarea">
        					<textarea id="content" placeholder="请输入内容"  name="content" 
        						class="span12 zy_text span12" style="margin-bottom: 20px" id="contentTextarea">${individuationDisplay.content}</textarea>	   
						</div>				
        					
        				<div class="control-group" id="uploadPirture">	
							<div class="controls update_img" style="margin:15px 0 0 20px;">
								<c:choose>
									<c:when test="${not empty individuationDisplay.imageDetailList}">
										<ul class="zpzs-box">
										<c:forEach items="${individuationDisplay.imageDetailList}" var="entity" varStatus="i">
	<%-- 											<li><div class="img"><img src="${entity.imageSrc }" /></div><a href="#" class="tp"  onclick="delEditImageDiv(this.id)" style="display:none;" id="imgEdit${i.index }"><img src="${ctp }/res/css/bbx/images/x.png" class="ww"></a></li> --%>
												<li id="${entity.uuid}"><div class="img"><img src="${entity.url}"/></div><a href="#" class="tp" onclick="delDiv(this)" style="display:none;"><img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww"></a></li>
										</c:forEach>												
										</ul>
										<div style="position:relative;float:left" id="uploads">
											<div><a href="#" class="tianjia"><img src="${ctp}/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div>
											<input type="hidden" id="uploader" name="uploader"/>
										</div>
										<div class="clear"></div>
									</c:when>
									<c:otherwise>
										<ul class="zpzs-box">
										</ul>
										<div style="position:relative;float:left" id="uploads">
											<div><a href="#" class="tianjia"><img src="${ctp }/res/css/bbx/images/add.jpg" class="tp_tianjia"> </a></div>
											<input type="hidden" id="uploader" name="uploader"/>
										</div>
										<div class="clear"></div>
									</c:otherwise>
								</c:choose>
								<div style="display:none" id="fileUuid">
									 <c:if test="${not empty individuationDisplay.imageDetailList }">
										<c:forEach items="${individuationDisplay.imageDetailList}" var="entity" varStatus="i">
										<input id="editImage${i.index }" value="${entity.uuid }"/>
										</c:forEach>
									</c:if>
									
								</div> 
							</div>
						</div>	
						</div>
    					<div class="clear"></div>
    					<div class="form-actions tan_bottom_1">
							<input type="hidden" id="id" name="id" value="${individuationDisplay.id}" />
							<input type="hidden" id="teamId" name="teamId" value="${individuationDisplay.teamId}" />
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
function delDiv(a){
	$(a).closest('li').remove();
	var divLen = $('.zpzs-box').find('li').length;
    var sunLen = 9;
    if(divLen>=sunLen){
    	$('#uploads').hide();
    	$('#uploads-queue').html('')
    }else{
    	$('#uploads').show();
    }
}
	
	var checker;
	var currentRoleCode = '${sessionScope[sca:currentUserKey()].currentRoleCode}';
	
	$(function() {
		var contentType = "${individuationDisplay.contentType}";
		$(":radio[name='contentType'][value='" + contentType + "']").prop("checked", "checked");
		
		var isCirculate = "${individuationDisplay.isCirculate}";
		if(isCirculate === "true"){
			$(":radio[name='isCirculate'][value='" + 1 + "']").prop("checked", "checked");
		}else{
			$(":radio[name='isCirculate'][value='" + 0 + "']").prop("checked", "checked");
		}
		
		clickRadio(contentType);
		
		$(":radio[name='check'][value='" + 2 + "']").prop("checked", "checked");
		clickRadioT(2);
		
		
		uploadFile();
		checker = initValidator();
		/* $(".zpzs-box li").each(function(){
			//alert(1);
			$(this).hover(function(){
	        	$(this).find(".tp").show();
	    	},function(){
	       		$(".zpzs-box li .tp").hide();
	    	}) ;
		}); */	
	});
	
	function initValidator() {
		return $("#studyShare_form").validate({
			errorClass : "myerror",
			rules : {
				"title" : {
					required : true,
					maxlength: 100
				},
				"content" : {
					maxlength: 512
				},
				"check" : {
					required : true,
					checkT : true
				},
				"finishTime" : {
					required : true
				}
			},
			messages : {
				
			}
		});
	}
	
	$.validator.addMethod("checkT", function(value, element, param) {
		var result = true;
		var $this = $(element);
		var startTime = $("#startTime").val();
		var radioValue = $("#check:checked").val();
		if(radioValue == "2" && startTime == ""){
			result = false;	
		}
	   	return this.optional(element) || result;
	}, "触发时间不能为空");
	
	$.validator.addMethod("check", function(value, element, param) {
		var result = true;
		var $this = $(element);
		var content = $("#content").val();
		var radioValue = $("input[name='contentType']:checked").val();
		if(radioValue == "1" && startTime == ""){
			result = false;	
		}
	   	return this.optional(element) || result;
	}, "内容不能为空");
	
	
	function clickRadio(type){
		if(type == "1"){
			$("#contentTextarea").show();
			$("#uploadPirture").hide();
		}else if(type == "2"){
			$("#contentTextarea").hide();
			//$("#content").val("");
			$("#uploadPirture").show();
		}
	}
	
	function clickRadioT(type){
		if(type == 1){
			$("#startTime").val("");
			$("#startTime").attr("disabled", true); 
		}else if(type == 2){
			$("#startTime").removeAttr("disabled"); 
		}
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = {};
			$requestData.teamId = $("#teamId").val();
			$requestData.title = $("#title").val();
			$requestData.contentType = $("input[name='contentType']:checked").val();
			$requestData.isCirculate = $("input[name='isCirculate']:checked").val();
			$requestData.examDate = $("#examDate").val();
			$requestData.startTime = $("#startTime").val();
			if($requestData.startTime == ""){
				$requestData.startTime = (new Date()).format("yyyy-MM-dd hh:mm:ss");
			}
			$requestData.finishTime = $("#finishTime").val();	
			$requestData.content = $("#content").val();
			$requestData.id = $id;
			var pictureUuid = "";//= new Array();

		    $('.zpzs-box li').each(function(){
		    	//imageUuidlList.push(this.id);
		    	pictureUuid += this.id + ","
			});
		    if(pictureUuid != "" && pictureUuid != null){
		    	pictureUuid = pictureUuid.substring(0,pictureUuid.length-1);
		    }
		    $requestData.pictureUuid = pictureUuid;
		    
		    if(pictureUuid == "" && $requestData.contentType == "2"){
		    	 $.error("请上传图片");
				return;
		    }
		    
		    if($requestData.content == "" && $requestData.contentType == "1"){
		    	$.error("请输入内容");
				return;
		    }
    
			var url = "${ctp}/bbx/individuationDisplay/creator";
			if ("" != $id) {
				url = "${ctp}/bbx/individuationDisplay/modify";
			}
			loader.show();
			
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					parent.core_iframe.search();
					$.closeWindow();
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
			/* $.ajax({
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
            }); */
		}
	}
	
	Date.prototype.format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
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
            fileSizeLimit: 4 * 1024,
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
            
            
            var imageStr = '<li id="'+$jsonObj.uuid+'"><div class="img"><img src="'+$jsonObj.url
            	+'"/></div><a href="#" class="tp" onclick="delDiv(this)" style="display:none;"><img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww"></a></li>';
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
				$.error("已存在该图片!");
			}
			
			var divLen = $('.zpzs-box').find('li').length;
		    var sunLen = 9;
		    if(divLen>=sunLen){
		    	$('#uploads').hide();
		    	$('#uploads-queue').html('')
		    }else{
		    	$('#uploads').show();
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
	
		
</script>
</html>