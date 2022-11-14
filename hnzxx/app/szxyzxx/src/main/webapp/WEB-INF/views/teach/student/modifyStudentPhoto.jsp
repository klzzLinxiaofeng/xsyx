<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/stepy.jquery.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.min.js"></script>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
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
    top: 190px;
    left:300px;
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
.button-next,#modifyUserInfoForm-buttons-7 .button-back{
	left:412px;
}
.btn-extend:hover{
	background-color: #BF7204;
}
</style>
</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid ">
                <!-- <div class="span12">
                    <ul class="breadcrumb">
                        <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>修改学生</li>
                    </ul>
                </div> -->
        </div>
		<div class="row-fluid ">
			<div class="span12">
				<!-- <div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							学生头像信息
						</h3>
					</div>
				</div> -->
				<div class="stepy-widget">
					<!-- <div class="widget-head clearfix bondi-blue">
						<div id="stepy_tabby1">
							<ul id="stepy_form-titles" class="stepy-titles">
							</ul>
						</div>
						<button href="javascript:void(0)" onclick="updateUserInfo();" class="btn btn-warning finish" style="position:absolute;right:25px;top:11px;">保存</button>
					</div> -->
					<div class="widget-container gray ">
						<div class="form-container">
							<form id="modifyUserInfoForm"  class="form-horizontal left-align form-well" novalidate="novalidate" style="padding-bottom:0;margin-bottom:0">
								<input type="hidden" id="studentId" name="studentId" value="${studentId}"/> 
								<fieldset title="基本信息">
									<legend style="display: none;">基本账户信息</legend>
									<table class="table table-bordered t_table">
										<tbody>
											<tr>
												<td rowspan="8" style="width: 165px; position: relative;">
													<c:choose>
														<c:when test="${not empty studentData.basic.photoUuid}">
															<img src="<entity:getHttpUrl uuid='${studentData.basic.photoUuid}'/>" id="imgId"/>
														</c:when>
														<c:otherwise>
															<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" id="imgId">
														</c:otherwise>
													</c:choose>
<%-- 													<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" id="imgId"> --%>
													<a href="javascript:void(0)" class="up_img"></a>
<!-- 													<input type="file" id="uploader" name="uploader"/> -->
													<input type="hidden" id="photoUuid" name="photoUuid" value="${studentData.basic.photoUuid }"/>
												</td>
											</tr>
										</tbody>
									</table>
								</fieldset>
								<button href="javascript:void(0)" onclick="saveUserInfo();" style="display: none;" class="btn btn-warning finish" >保存</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="form-actions tan_bottom" >
		<button onclick="updateUserInfo();"  class="btn btn-warning finish" style="position: fixed; left: 320px;">保存</button>
		<button class="btn" type="button" onclick="cancel();" style="position:fixed; left:388px;">取消</button>
	</div>
	<script>
	var checker="";
	function cancel(){
		$.closeWindow();
	}
	$.createAvartarEditor({
		"btn" : "#imgId,.up_img",
		"avatarSize" : "208,208",
		"avatarSizeLabel" : "208x208"
	});
	
	function selectedImgHandler(data) {
		$("#imgId").attr("src", data.imgUrl);
		$("#photoUuid").val(data.uuid)
	}
	
    $(function(){
    	$(".t_table tr").each(function(){ 
			$(this).children("td:eq(0),td:eq(2)").css({"background-color":"#F4F4F4","text-align":"center","padding-right":"10px"});
		}); 
    	
		uploadImageFile();
		checker =  initValidator();
		
    })

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
	}
    
	function updateUserInfo(){
    	if (checker.form()) {
    		//2016-8-15新增学号与学籍辅助号的验证   没有选择班级的，班内学号不能填
        	if($("#teamId").val()==""){
        		if($("#number").val() != ""){
        			$.alert("当前您输入了学生班内学号，请为学生分班或者选择班级！");
        		}
        	}
    		
    		var $requestData = formData2JSONObj("#modifyUserInfoForm");
    		$requestData.teamId = "${studentData.archive.teamId}";
    		
        	var url = "${pageContext.request.contextPath}/teach/student/updateStudentPhoto";
        	
        	$.post(url, $requestData, function(data, status) {
        		if("success" === status) {
        			data = eval("(" + data + ")");
        			if("success" === data.info) {
        				$.success("保存成功");
        				if(parent.core_iframe != null) {
//         						parent.core_iframe.window.location.reload();
								var id = "${studentId}";
								var name = "${studentData.basic.name }";
								var sex = "${studentData.basic.sex }";
								var mobile = "${studentData.relation.mobile }";
        						parent.core_iframe.window.changeOfPage(id, name, sex, mobile);
        					} else {
        						parent.window.location.reload();
        					}
        				$.closeWindow();
        			} else {
        				$.error(data.info);
        			}
        		}else{
        			$.error("服务器异常");
        		}
        	});
    	}else{
    		$.alert("请完善页签上的所有必填信息！");
    		return;
    	}
    }
    
function initValidator() {
	return $("#modifyUserInfoForm").validate({
			errorClass : "myerror",
			rules : {
				"photoUuid" : {
					required : true
				}
			},
			messages : {
				"photoUuid":{
					required:"请上传图片",
				}
			}
		});
	}
  </script>
</body>
</html>
