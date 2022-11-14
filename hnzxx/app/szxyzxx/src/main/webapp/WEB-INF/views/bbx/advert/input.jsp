<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/stepy.jquery.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.min.js"></script> --%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
<%-- <link href="${pageContext.request.contextPath}/res/css/bbx/bbx.css" rel="stylesheet">
<link rel="stylesheet" href="${ctp}/res/css/extra/add.css" >
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css"> --%>

<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
. form-horizontal{position:relative;}
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

/*==========================*/
/* .myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
} */
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
.edit ul li{ float: left;
    margin-bottom: 15px;
    height: 63px;
    position: relative;
}
.edit ul li img{height: 67px;
    width: 80px;
margin-right: 15px;}
.ww{background: black;
    filter: alpha(opacity:30);
    opacity: 0.3;
    margin: -67px 0 0 0;
        margin-top: -67px;
        margin-right: 0px;
    margin-top: -144px;

}.edit ul li .tp{
position: absolute;
left: 0;
top: 144px;
}
</style>
</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid ">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>信息发布</li>
                    </ul>
                </div>
        </div>
		<div class="row-fluid ">
			<div class="span12">
				<!-- <div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							广告机信息发布
						</h3>
					</div>
				</div> -->
				<div class="stepy-widget">
					<div class="widget-head clearfix bondi-blue">
						<div id="stepy_tabby1">
							<ul id="stepy_form-titles" class="stepy-titles">
							</ul>
						</div>
						<button href="javascript:void(0)" onclick="saveOrUpdate();" class="btn btn-warning finish" style="position:absolute;right:25px;top:11px;">保存</button>
					</div>
					<div class="widget-container gray ">
						<div class="form-container">
							<form id="stepy1" class="form-horizontal left-align form-well" novalidate="novalidate" style="padding-bottom:0;margin-bottom:0">
								<fieldset title="基本信息">
									<legend style="display: none;">基本信息</legend>

									<div class="control-group" style="display: none;">
										<label class="control-label"> id： </label>
										<div class="controls">
											<input type="text" id="id" name="id" class="span13"
												placeholder="" value="${advert.id}">
										</div>
									</div>
									<div class="control-group" style="display: none;">
										<label class="control-label"> 学校id： </label>
										<div class="controls">
											<input type="text" id="schoolId" name="schoolId"
												class="span13" placeholder="" value="${advert.schoolId}">
										</div>
									</div>
									<div class="control-group" style="display: none;">
										<label class="control-label"> 房间id： </label>
										<div class="controls">
											<input type="text" id="roomId" name="roomId" class="span13"
												placeholder="" value="${advert.roomId}">
										</div>
									</div>

									<table class="table table-bordered t_table">
										<tbody>
											<tr>
												<td style="width: 20%"><span class="red">*</span>发布内容类型:</td>
												<td>
													<select id="contentType" name="contentType" class="chzn-select" style="width:200px">
														<option value="0">请选择</option>
														<option value="1">文本</option>
														<option value="2">图片</option>
														<option value="4">视频</option>
														<option value="3">文本、图片</option>
														<option value="5">文本、视频</option>
														<option value="6">图片、视频</option>
														<option value="7">文本、图片、视频</option>
													</select>
												</td>
										</tbody>
									</table>
								</fieldset>
								<fieldset title="发布信息">
									<legend style="display: none;">内容发布</legend>
									
									<div style="padding: 0;padding-bottom:20px;" id="textContext" hidden="hidden">
										<span style="font-size:16px;">文本内容：</span>
										<textarea id="content" placeholder="请输入内容" name="content"
											class="span12 zy_text span12 left_red {required : true,maxlength:1000}">${advert.content}</textarea>
									</div>
									<%-- <div id="textContext" hidden="hidden" class="control-group">
										<!-- <label class="control-label"> 文本内容： </label> -->
										<div class="controls">
											<input type="text" id="content" name="content" class="span13"
												placeholder="" value="${advert.content}">
										</div>
									</div> --%>

									
									<div class="control-group" id="pictureContext" hidden="hidden">
										<span style="font-size:16px;">图片:</span>
										<div class="controls update_img edit" style="margin: 15px 0 0 0;">
											<c:choose>
												<c:when test="${not empty advert.imageDetailList}">
													<ul class="zpzs-box">
														<c:forEach items="${advert.imageDetailList}" var="entity" varStatus="i">
															<li id="${entity.uuid }"><div class="img">
																	<img src="${entity.url }" />
																</div>
																<a href="#" class="tp" onclick="delDiv(this)" style="display: none;">
																	<img src="${pageContext.request.contextPath}/res/css/bbx/images/x.png" class="ww">
																</a>
															</li>
														</c:forEach>
													</ul>
													<div style="position: relative; float: left">
														<div>
															<a href="#" class="tianjia">
																<img src="${ctp}/res/css/bbx/images/add.jpg" class="tp_tianjia"> 
															</a>
														</div>
														<input type="hidden" id="uploader" name="uploader" />
													</div>
													<div class="clear"></div>
												</c:when>
												<c:otherwise>
													<ul class="zpzs-box">
													</ul>
													<div style="position: relative; float: left">
														<div>
															<a href="#" class="tianjia">
																<img src="${ctp }/res/css/bbx/images/add.jpg" class="tp_tianjia"> 
															</a>
														</div>
														<input type="hidden" id="uploader" name="uploader" />
													</div>
													<div class="clear"></div>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</fieldset>
								<button href="javascript:void(0)" onclick="saveUserInfo();" class="btn btn-warning finish" style="display:none">保存</button>
							</form>
							<input type="hidden" id="roomTypeCode" name="roomTypeCode" value="${roomTypeCode}" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="form-actions tan_bottom" >
		<button class="btn" type="button" onclick="cancel();" style="position:fixed;left:482px;">取消</button>
	</div>
	
	
	
	<script>
	$("#contentType").change(function() {
		var contentType = $("#contentType").val();
		selectContentType(contentType);
	});
	
	function selectContentType(contentType) {
		if ( "1" ==  contentType ) {
			$("#textContext").show();
			$("#pictureContext").hide();
			$('.zpzs-box li').remove();
		} else if ( "2" == contentType ) {
			$("#textContext").hide();
			$("#content").val("");
			$("#pictureContext").show();
		} else if ( "3" == contentType ) {
			$("#textContext").show();
			$("#pictureContext").show();
		} else if ( "4" == contentType ) {
			$("#textContext").hide();
			$("#content").val("");
			$("#pictureContext").hide();
			$('.zpzs-box li').remove();
		} else if ( "5" == contentType ) {
			$("#textContext").show();
			$("#pictureContext").hide();
			$('.zpzs-box li').remove();
		} else if ( "6" == contentType ) {
			$("#textContext").hide();
			$("#content").val("");
			$("#pictureContext").show();
		} else if ( "7" == contentType ) {
			$("#textContext").show();
			$("#pictureContext").show();
		} else if("" ==  contentType){
			$("#textContext").hide();
			$("#content").val("");
			$("#pictureContext").hide();
			$('.zpzs-box li').remove();
		}
	}
	
	$(function(){
        $('#stepy1').stepy({
            backLabel: '上一步',
            nextLabel: '下一步',
            block: true,
            description: true,
            legend: false,
            titleClick: true,
            titleTarget: '#stepy_tabby1'
            });
		
        uploadFile();
		$(".zpzs-box li").each(function(){
			//alert(1);
			$(this).hover(function(){
	        	$(this).find(".tp").show();
	    	},function(){
	       		$(".zpzs-box li .tp").hide();
	    	}) ;
		});
		var contentType =  "${advert.contentType}";
		$("#contentType option").each(function() {    
		    // var op = $(this).text(); 
		    var val = $(this).val();
		    if ( contentType == val ) {
				$("#contentType option[value='"+val+"']").attr("selected", true); 
		    	selectContentType(contentType);
		    	
		    }
		});
		
		// uploadImageFile();
		// checker =  initValidator();
    });
	
	var checker="";
	function cancel(){
		$.closeWindow();
	}
	
	function selectedImgHandler(data) {
// 		alert(JSON.stringify(data));
		$("#imgId").attr("src", data.imgUrl);
		$("#photoUuid").val(data.uuid)
	}
	
    $(function(){
		$(".t_table tr").each(function(){ 
			$(this).children("td:eq(0),td:eq(2)").css({"background-color":"#F4F4F4","text-align":"right","padding-right":"10px"});
		}); 
	});
    $(function(){
    	$(".btn_link .add_family").click(function(){
    		var i=$("table tbody > tr").length+1;
    		$("#stepy1-step-3").children("table").children("tbody").append("<tr><td>" +i+ "</td><td><input type='text' /></td><td><input type='text' /></td><td><input type='text' /></td><td><input type='text' /></td><td><button type='button' class='btn btn-blue'>编辑</button><button type='button' class='btn btn-warning'>删除</button></td></tr>")
    	});
    });
    /* $(function() {
		$('textarea.tinymce-simple').tinymce({
			script_url : '${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js',
			theme : "simple"
			});
		}); */
    
    <%-- function uploadImageFile(){
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
           	 $("#photoUuid").val($jsonObj.uuid);
           	 
           	 $("#imgId").attr('src',$jsonObj.url); 
           	
            },
            onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onUploadError: function(file, errorCode, errorMsg, errorString) {
            	$.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
            }
        });
	} --%>
    
  //保存或更新修改
	function saveOrUpdate() {
		//if (checker.form()) {
		if ( initValidator() ) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#stepy1");
			var pictureUuid = "";
			/* $("#fileUuid input").each(function(){
				imageUuidlList.push(this.value);
	    	}); */
	    	$('.zpzs-box li').each(function(){
	    		pictureUuid = pictureUuid + this.id +",";
	    		//pictureUuid.push(this.id);
			});
	    	$requestData.pictureUuid = pictureUuid;
			var url = "${ctp}/bbx/advert/creator";
	    	var roomTypeCode = $("#roomTypeCode").val();
			if ( roomTypeCode != null && roomTypeCode != "" ) {
				url = url + "?roomTypeCode=" + roomTypeCode;
			}
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/advert/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
							parent.core_iframe.window.search();
    						$.closeWindow();							
							// parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.search();
 							// parent.window.location.reload();
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
    
    function initValidator() {
    	return $("#stepy1").validate({
				errorClass : "myerror",
				rules : {
					"schoolId" : {
						required : true
					},
					"roomId" : {
						required : true
					},
				
					"content" : {
						// required : true,
						// minlength : 0,
						maxlength : 500,
						// "chinese" : true
					},
					"pictureUuid" : {
						// required : true,
						maxlength : 500,
						// isEnglish : true
					}
				},
				messages : {
					"content":{
						// required:"名字必填",
						maxlength:"最长不能超500个字",
						// chinese : "请输入中文"
					}
				}
			});
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
            queueSizeLimit: 1,
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
	            // alert($jsonObj.url + "~~" + $jsonObj.uuid +"~~~" + suffix);
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
				
				// 
				var pictureUuid = "";
		    	$('.zpzs-box li').each(function(){
		    		pictureUuid = pictureUuid + this.value +",";
				});
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
</body>
</html>
