<%-- 
    Document   : upload
    Created on : 2015-3-26, 16:48:55
    Author     : Administrator
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/views/embedded/common.jsp"%>
<link type="text/css" href="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.css" rel="stylesheet"/>
<!--[if IE 7]>
                  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/extra/micro/font-awesome-ie7.min.css">
                <![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/xyuploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/md5_md5blob.js"></script>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<style type="text/css">
.uploadify{position:absolute;opacity:0;left:0;top:0;height: 70px; width: 80px;}
.uploadify-queue{width:80px;}
.myerror {color: red !important;width: 22%;display: inline-block;padding-left: 10px;}
.date .myerror{display:block;}
</style>
<!-- <div class="a2"> -->
    <div class="blue"></div>
    <div class="upload_wk">
        <div class="u_wk">
            <div class="upload_file" id="uploader0">
                <div id="picker"></div>
               <p style="color:red;text-align: center;line-height: 40px;font-size: 14px;">请上传数据速率为7954kbps、总比特率为8051kbps、帧速率为24帧/秒、格式为MP4的视频文件</p>
            </div>
        </div>
    </div>
    <div class="ul_wk" style="display: none">
        <div class="u_message">
            <div class="jindu" id="progress" style="width:635px;display:block;float:left">
            </div>
            <a onclick="removeUploadMicro()" class="no" href="javascript:void(0)">取消上传</a>
            <div class="clear"></div>
            <div class="sudu">
            </div>
           <!--  <input type="hidden" name="fileId" id="fileId" value="" > -->
        </div>
    </div>
<form id= "uploadForm" action="${pageContext.request.contextPath}/bbx/bpBwFile/ClassVideo/creator" method="post" enctype="multipart/form-data" onsubmit="return check(); parent.layer.close(parent.layer.getFrameIndex(window.name));" > <!-- onsubmit="window.layer.close();" -->  
     <input type="hidden" name="fileId" id="fileId" value="" >
     <div style="margin-top: 150px;">
	     <label style="width:80px; display: inline; height: 35px;">
	         <font style="color:red; ">*</font>标      题：
	     </label>
	     <input type="text" style="margin-left:80px; height: 35px;" name="uploadResTitle" id="uploadResTitle" value="" placeholder="视频标题" >
	     <br>
	     <label style="width:80px; display: inline; height: 35px;">
	               选择班级：
	      </label>
	      <select id="teamId" style="margin-left:80px;" name="teamId" style="height: 35px;">
	      </select>
     </div>
     
     <span>视频封面图片</span>
      <p >上传文件： <input type="file" name="file"/></ p>  
	  <!-- <input type="button" value="上传" onclick="saveMicro()" />  --> 
             
     <div style="display: none;" class="control-group">
		<label class="control-label">
			使用此文件的栏目类型：1=班级相册 2=班级视频 3=班级动态：
		</label>
		<div class="controls">
		<input type="text" id="objectType" name="objectType" class="span13" placeholder="${bpBwFile.objectType}" value="${bpBwFile.objectType}">
	</div>
</form>
<script type="text/javascript">
			$(function(){
				 $.ajax({
			        url: "${pageContext.request.contextPath}/bbx/bpBwFile/ClassVideo/getTeamList",
			        type: "GET",
			        data: {
			        },
			        async: false,
			        success: function(data) {
			       	 var list = JSON.parse(data);
			      		 $("#teamId").empty();
			       	 if ( list.length > 0 ) {
			       		 for(var item in list) {
			       			 $("#teamId").append("<option value='"+list[item].id+"'>"+list[item].name+"</option>");
			       		 }
			       	 } else {
			       		 $("#teamId").append("<option>该用户没有从属的班级</option>");
			       	 }
			        }
			    });
			});
			
			function check() {
				var teamId = $("#teamId").val();
				var uploadResTitle = $("#uploadResTitle").val();
				var fileId = $("#fileId").val();
				var objectType = $("#objectType").val();
				if ( teamId == null || teamId.length == 0 ) {
					alert("没有选中班级")
					return false;
				}
				if ( uploadResTitle == null || uploadResTitle.length == 0 ) {
					alert("标题没有名称")
					return false;
				}
				if ( fileId == null || fileId.length == 0 ) {
					alert("文件id不能为空")
					return false;
				}
				if ( objectType == null || objectType.length == 0 ) {
					alert("文件类型不能为空")
					return false;
				}
				return true;
			}

			var catalogEnd = 0;
            // 断点上传
            function initMicroUpload() {
                if (typeof (Worker) !== "undefined") {
                    $("#uploader0").fadeIn("slow");
                    $("#uploader0").xyUploader({
                        basePath: "${pageContext.request.contextPath}",
                        formData: {'jsessionId': '<%=request.getSession().getId()%>'},
                        picker: {id: "#picker", innerHTML: "上传视频", multiple: false},
                        progress: "progress",
                        accept: {title: 'MEDIA', extensions: "*.*", allowExts: "mp4"},
                        removeTimeout: 20,
                        uploadSuccess: function(file, response) {
                           
                            return uploadSucCallBack(response);
                        },
                        uploadError: function(code) {
                            alert(code);
                        }
                    });
                    
                } else {
                    // 初始化非HTML5上传组件
                  <%--   $("#uploader0").parent("div").css({"padding": "5px 23px", "height": "74px"});
                    $("#uploader0").uploadify({
                        swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
                        uploader: '${pageContext.request.contextPath}/uploader/common',
                        formData: {'jsessionId': '<%=request.getSession().getId()%>'},
                        fileObjName: 'file',
                        fileTypeDesc: "视频文件",
                        fileTypeExts: "*.mp4",
                        method: 'post',
                        multi: false, // 是否能选择多个文件
                        auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
                        removeTimeout: 1,
                        queueSizeLimit: 1,
                        fileSizeLimit: 40 * 1024 * 1024 * 1024,
                        buttonText: "添加视频",
                        requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
                        height: 20,
                        width: 66,
                        onUploadSuccess: function(file, data, response) {
                            if (response === true) {
                            	
                                var $jsonObj = eval("(" + data + ")");
                                return uploadSucCallBack($jsonObj);
                            }
                        },
                        onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
                            $("#infoBox").prev("p").css("display", "none");
                            $("#infoBox").css("display", "block");
                        },
                        onUploadError: function(file, errorCode, errorMsg, errorString) {
                            alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
                        }
                    }); --%>
                }
                
                
                // 上传回调
                var uploadSucCallBack = function(json) {
                    if (json.finishedFlag === 1) {
                        $("#fileId").val(json.uuid);
                       // $("#uploadResTitle").val(json.realFileName.substring(0,json.realFileName.lastIndexOf(".")));
                        //调用视频转换服务
             			/* $.submitJaveSingleFile({"entityFileUUID":json.uuid},function(){
                        }); */
                    }
                    
                };
            }
            
            function saveMicro() {
                var ca = {};
                var teamId = $("#teamId").val();
                var objectType = $("#objectType").val();
                var formData = new FormData($( "#uploadForm" )[0]);
                $.ajax({
                    url: "${pageContext.request.contextPath}/bbx/bpBwFile/ClassVideo/creator?teamId="+teamId+"&name="+$("#uploadResTitle").val()+"&fileId="+$("#entityId").val()+"&objectType="+objectType,
                    type: "POST",
                    data: formData
                    	/* {
                      "teamId": teamId, 
                      "name": $("#uploadResTitle").val(), 
                      "fileId": $("#entityId").val(),
                      "objectType": objectType
                       
                    } */,
                    async: false,  
                    cache: false,  
                    contentType: false,  
                    processData: false,
                    success: function(mid) {
                    	console.log(mid);
                        if ("publish" == "${publish}") {
                            //布置时的操作
                            showChosenMicroDiv(true);
                            addMicro(mid, $("#uploadResTitle").val());
                            setSize();
                            removeUploadMicro();
                        } else {
                            //上传时的操作
                            $.alert("上传成功");
                            if(parent.core_iframe != null) {
     							parent.core_iframe.window.location.reload();
     						} else {
     							parent.window.location.reload();
     						}
    						$.closeWindow();
                            // location.href = "${pageContext.request.contextPath}/bbx/bpBwFile/ClassVideo/index?";
                        }
                    }
                });
        }
            
            $(function() {
                //使用setIimeout方法延迟1秒加载是为了解决webupload第一次点不了的bug
                setTimeout("initMicroUpload()", 500);
                
            });

</script>
</html>
