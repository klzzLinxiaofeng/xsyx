<%-- 
    Document   : upload
    Created on : 2015-3-26, 16:48:55
    Author     : Administrator
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link type="text/css" href="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.css" rel="stylesheet"/>
<!--[if IE 7]>
                  <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/extra/micro/font-awesome-ie7.min.css">
                <![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/xyuploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/md5_md5blob.js"></script>


<div class="a2">
    <div class="blue"></div>
    <div class="upload_wk">
        <div class="u_wk">
            <div class="upload_file" id="uploader">
                <div id="picker"></div>
            </div>
        </div>
    </div>
    <div class="ul_wk" style="display: none">
        <div class="u_message">
            <div class="jindu" id="progress" style="width:635px;display:block;float:left">
                <!--                <div id="progress"></div>-->

            </div>
            <a onclick="removeUploadMicro()" class="no" href="javascript:void(0)">取消上传</a>
            <div class="clear"></div>
            <div class="sudu">
                <!--                <span class="s1">上传速度：100k/s</span>
                                <span class="s1">已上传：100M/100M</span>
                                <span class="s1">剩余时间：03:00</span>-->
            </div>
            <input type="hidden" name="entityId" id="entityId" value="" >
        </div>
        <div class="u_wk_message">
            <div class="title">试卷信息</div>
            <div class="control-group">
                <label class="control-label" style="width:80px;">
                    <font style="color:red">*</font>标题：
                </label>
                <div class="controls" style="margin-left:100px;">
                    <input type="text" name="uploadResTitle" id="uploadResTitle" value="" placeholder="试卷标题" class="span5" style="width:550px;">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" style="width:80px;">
                    简介：
                </label>
                <div class="controls" style="margin-left:100px;">
                    <textarea name="uploadMicroDescription" id="uploadMicroDescription" class="span5" style="width:550px;"></textarea>
                </div>
            </div>
            
            <div class="control-group">
                <label class="control-label" style="width:80px;">
                    	共享：
                </label>
                <div class="controls" style="margin-left:100px;">
                	<input type="checkbox" id="shareBox" name = "shareBox" onchange="uploadShare();"> </input>
                </div>
            </div>
            
            <div class="control-group" id="upload_textbook" hidden="true">
                <label class="control-label" style="width:80px;">
                    	<span class="red">*</span>教材：
                </label>
                <div>
                	<jsp:include page="/views/embedded/textBookCatalog.jsp">
                	<jsp:param value="0" name="type"/>
                	</jsp:include>	
                </div>
            </div>
            
            <div class="control-group" id="upload_textbookcatalog" hidden="true">
                <label class="control-label" style="width:80px;">
                    	<span class="red">*</span>目录：
                </label>
            <div id="dvTextBookCatalog" class="select_div">
				
			</div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
var catalogEnd = 0;
            // 断点上传
            function initMicroUpload() {
//                 if (typeof (Worker) !== "undefined") {
                    $("#uploader").fadeIn("slow");
                    $("#uploader").xyUploader({
                        basePath: "${pageContext.request.contextPath}",
                        formData: {'jsessionId': '<%=request.getSession().getId()%>'},
                        picker: {id: "#picker", innerHTML: "", multiple: false},
                        progress: "progress",
                        accept: {title: 'MEDIA', extensions: "*.*", allowExts: "mp4,doc,docx,ppt,pptx,jpg,jpeg,png,gif,swf,xls,xlsx,pdf,rar,mp3"},
                        removeTimeout: 20,
                        uploadSuccess: function(file, response) {
                            return uploadSucCallBack(response);
                        },
                        uploadError: function(code) {
                            alert(code);
                        }
                    });
//                 } else {
//                     // 初始化非HTML5上传组件
//                     $("#uploader").parent("div").css({"padding": "5px 23px", "height": "74px"});
//                     $("#uploader").uploadify({
//                         swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
//                         uploader: '${pageContext.request.contextPath}/uploader/common',
<%--                         formData: {'jsessionId': '<%=request.getSession().getId()%>'}, --%>
//                         fileObjName: 'file',
//                         fileTypeDesc: "视频文件",
//                         fileTypeExts: "*.mp4",
//                         method: 'post',
//                         multi: false, // 是否能选择多个文件
//                         auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
//                         removeTimeout: 1,
//                         queueSizeLimit: 1,
//                         fileSizeLimit: 4 * 1024 * 1024 * 1024,
//                         buttonText: "添加视频",
//                         requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
//                         height: 20,
//                         width: 66,
//                         onUploadSuccess: function(file, data, response) {
//                             if (response === true) {
//                                 var $jsonObj = eval("(" + data + ")");
//                                 return uploadSucCallBack($jsonObj);
//                             }
//                         },
//                         onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
//                             $("#infoBox").prev("p").css("display", "none");
//                             $("#infoBox").css("display", "block");
//                         },
//                         onUploadError: function(file, errorCode, errorMsg, errorString) {
//                             alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
//                         }
//                     });
//                 }
                // 上传回调
                var uploadSucCallBack = function(json) {
                    if (json.finishedFlag === 1) {
                        $("#entityId").val(json.uuid);
                    }
                };
            }

            function onSaveMicroSubmit() {
                var title = $.trim($("#uploadResTitle").val());
                var entityId = $("#entityId").val();
                var catalog1 = $("#catalog1").val();
                var uploadMicroDescription = $("#uploadMicroDescription").val();
                var textbookId = catalog1;
                var detailLength=uploadMicroDescription.trim().length;
                var titleLength=title.trim().length;
            	
                if (title == null || title == "") {
                    $.alert("请输入微课标题");
                    return false;
                }
                if (entityId == null || entityId == "") {
                    $.alert("请上传微课视频");
                    return false;
                }
                if (detailLength > 1000) {
                    $.alert("简介不能超过500字");
                    return false;
                }
                if (titleLength > 100) {
                    $.alert("标题不能超过50字");
                    return false;
                }
                 if($("#shareBox").attr("checked")){
                	 
               if(textbookId == null ||textbookId == ""){
           		 $.alert("请输入教材");
           		 return false;
           		}  
               if(catalog1 == null ||catalog1 == ""){
             		 $.alert("请选择目录");
             		 return false;
             	}
              }
           	 
           	var aa = $("#dvTextBookCatalog").children("select").length;
            	var catalogTemp;
            	for (var num = aa; num >= 1; num--) {
                    var catalogname = "catalog" + num

                    catalogTemp = $("#" + catalogname).val();
                    
                    if (Number(catalogTemp) > 0) {
                        catalogEnd = catalogTemp;
                        catalogEnd = $("#" + catalogname).find("option:selected").attr("data-code");
                       
                        break;
                    } else {
                        catalogEnd = 0;
                    }

                }
//                 if (aa == 1) {
//                     catalogEnd = 0;
//                 }
            	
                
                return true;
            }

            function getCatalog() {
                var ca = {};
//                    ca["stage"] = $("#stageCode").val();
//                    ca["subject"] = $("#subjectCode").val();
//                    ca["grade"] = $("#gradeCode").val();
//                    ca["publish"] = $("#publisherId").val();
//                    ca["volumn"] = $("#volumn").val();
//                    ca["catalog"] = textBookCatalogSelectId;
                return ca;
            }


            function saveMicro() {
                if (onSaveMicroSubmit()) {
                	var gradeCodeVolumnValue = $("#gradeCodeVolumn").val();
                	var array = gradeCodeVolumnValue.split("-");
                	
                	var grade;
                	var volumn;
                	
                	if(array.length == 2){
                		grade = array[0];
                    	volumn = array[1];
                	}
                    var ca = getCatalog();
                    //,"stage":ca.stage,"subject":ca.subject,"grade":ca.grade,"publish":ca.publish,"volumn":ca.volumn,"catalog":ca.catalog
                    $.ajax({
                        url: "${pageContext.request.contextPath}/learningDesignhouse/saveOrUpdate",
                        type: "POST",
                        data: {
                        	"title": $("#uploadResTitle").val(), 
                        	"description": $("#uploadMicroDescription").val(), 
                        	"entityId": $("#entityId").val(),
                        	"checked":$('#shareBox').is(':checked'),
                        	
                        	"stageCode":$("#stageCode").val(),
                        	"subjectCode":$("#subjectCode").val(),
                        	"gradeCode":grade,
                        	"version": $("#publisherId").val(),
                        	"volumn": volumn,
                        	
                        	"stageName":$("select[id=stageCode] option:selected").text(),
                        	"subjectName":$("select[id=subjectCode] option:selected").text(),
                        	 //"gradeName":$("select[id=gradeCode] option:selected").text(),
                        	"versionName":$("select[id=publisherId] option:selected").text(),
                        	//"volumnName": $("select[id=volumn] option:selected").text(),
                        	"textbookId":$("#catalog1").val(),
                        	"catalogEnd":catalogEnd
                        	
                        },
                        async: false,
                        success: function(mid) {
                            if ("publish" == "${publish}") {
                                //布置时的操作
                                showChosenMicroDiv(true);
                                addMicro(mid, $("#uploadResTitle").val());
                                setSize();
                                removeUploadMicro();
                            } else {
                                //上传时的操作
                                $.alert("上传成功");
                                location.href = "${pageContext.request.contextPath}/learningDesignhouse/myLearningDesign?index=index";
                            }
                        }
                    });
                }
            }
            
            function uploadShare() {
            	//资源共享则显示教材目录
            	if($("#shareBox").prop('checked')) {
            		$("#upload_textbook").show();
            		$("#upload_textbookcatalog").show();
            	} else {
            		//隐藏教材
            		$("#upload_textbook").hide();
            		$("#upload_textbookcatalog").hide();
            		//重置学段选中状态
            		$("#stageCode").val("");
            		//重置科目相应的下拉选项
            		$("#subjectCode").html("<option value=''>请选择</option>");
            		//重置版本相应的下拉选项
            		$("#publisherId").html("<option value=''>请选择</option>");
            		//重置年级册次相应的下拉选项
            		$("#gradeCodeVolumn").html("<option value=''>请选择</option>");
            		//清空目录信息
            		$("#dvTextBookCatalog").html("");
            	}
            }

            $(function() {
                //使用setIimeout方法延迟1秒加载是为了解决webupload第一次点不了的bug
                setTimeout("initMicroUpload()", 500);
            });

</script>
</html>
