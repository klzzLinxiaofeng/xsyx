<%-- 
    Document   : upload
    Created on : 2015-3-26, 16:48:55
    Author     : Administrator
--%>
<%@page import="platform.education.resource.contants.ResourceType"%>
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
            <div class="title"></div>
            <div class="control-group">
                <label class="control-label" style="width:80px;">
                    <font style="color:red">*</font>标题：
                </label>
                <div class="controls" style="margin-left:100px;">
                    <input type="text" name="uploadResTitle" id="uploadResTitle" value="" placeholder="资源标题" class="span5" style="width:550px;">
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" style="width:80px;">
                    <font style="color:red">*</font>类型：
                </label>
                <div class="controls" style="margin-left:100px;">
                    <c:choose>
                        <c:when test="${resType!=null}">
                            <input name="resType" <c:if test="${resType == 2}">checked</c:if> type="radio" value="<%=ResourceType.LEARNING_DESIGN%>" /> 课件
                            <input name="resType" <c:if test="${resType == 5}">checked</c:if> type="radio" value="<%=ResourceType.TEACHING_PLAN%>" /> 教案
                            <input name="resType" <c:if test="${resType == 3}">checked</c:if> type="radio" value="<%=ResourceType.HOMEWORK%>" /> 作业
                            <input name="resType" <c:if test="${resType == 4}">checked</c:if> type="radio" value="<%=ResourceType.EXAM%>" /> 试卷
                            <input name="resType" <c:if test="${resType == 6}">checked</c:if> type="radio" value="<%=ResourceType.MATERIAL%>" /> 素材
                        </c:when>
                        <c:otherwise>
                            <input name="resType" checked type="radio" value="<%=ResourceType.LEARNING_DESIGN%>" /> 课件
                            <input name="resType" type="radio" value="<%=ResourceType.TEACHING_PLAN%>" /> 教案
                            <input name="resType" type="radio" value="<%=ResourceType.HOMEWORK%>" /> 作业
                            <input name="resType" type="radio" value="<%=ResourceType.EXAM%>" /> 试卷
                            <input name="resType" type="radio" value="<%=ResourceType.MATERIAL%>" /> 素材
                        </c:otherwise>
                    </c:choose>
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
                <%--   <label class="control-label" style="width:80px;">
                      教材目录：
                  </label>
                  <div id="catalogDiv" class="controls" style="margin-left:100px;">
                      <jsp:include page="/views/embedded/textBookCatalog.jsp"></jsp:include>
                      </div>--%>
            </div>
            <div class="control-group">
                <!--                <label class="control-label" style="width:80px;">
                                    关键字：
                                </label>
                                <div class="controls" style="margin-left:100px;">
                                    <input type="text" value="" placeholder="微课" class="span5" style="width:550px;">
                                </div>-->
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
                // 断点上传
                function initMicroUpload() {
//                     if (typeof (Worker) !== "undefined") {
                        $("#uploader").fadeIn("slow");
                        $("#uploader").xyUploader({
                            basePath: "${pageContext.request.contextPath}",
                            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
                            picker: {id: "#picker", innerHTML: "上传资源", multiple: false},
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
//                     } else {
//                         // 初始化非HTML5上传组件
//                         $("#uploader").parent("div").css({"padding": "5px 23px", "height": "74px"});
//                         $("#uploader").uploadify({
//                             swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
//                             uploader: '${pageContext.request.contextPath}/uploader/common',
<%--                             formData: {'jsessionId': '<%=request.getSession().getId()%>'}, --%>
//                             fileObjName: 'file',
//                             fileTypeDesc: "资源文件",
//                             fileTypeExts: "*.doc",
//                             method: 'post',
//                             multi: false, // 是否能选择多个文件
//                             auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
//                             removeTimeout: 1,
//                             queueSizeLimit: 1,
//                             fileSizeLimit: 4 * 1024 * 1024 * 1024,
//                             buttonText: "添加资源",
//                             requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
//                             height: 20,
//                             width: 66,
//                             onUploadSuccess: function(file, data, response) {
//                                 if (response === true) {
//                                     var $jsonObj = eval("(" + data + ")");
//                                     return uploadSucCallBack($jsonObj);
//                                 }
//                             },
//                             onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
//                                 $("#infoBox").prev("p").css("display", "none");
//                                 $("#infoBox").css("display", "block");
//                             },
//                             onUploadError: function(file, errorCode, errorMsg, errorString) {
//                                 alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
//                             }
//                         });
//                     }
                    // 上传回调
                    var uploadSucCallBack = function(json) {
                        if (json.finishedFlag === 1) {
                            $("#entityId").val(json.uuid);
                        }
                    };
                }

                function onSaveMicroSubmit() {
                    var title = $("#uploadResTitle").val();
                    var entityId = $("#entityId").val();
                    if (title == null || title == "") {
                        $.alert("请输入资源标题");
                        return false;
                    }
                    if (entityId == null || entityId == "") {
                        $.alert("请上传资源文件");
                        return false;
                    }
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
                        var ca = getCatalog();
                        //,"stage":ca.stage,"subject":ca.subject,"grade":ca.grade,"publish":ca.publish,"volumn":ca.volumn,"catalog":ca.catalog
                        $.ajax({
                            url: "${pageContext.request.contextPath}/resource/saveOrUpdate",
                            type: "POST",
                            data: {"title": $("#uploadResTitle").val(), "resType": $("[name='resType']:checked").val(), "description": $("#uploadMicroDescription").val(), "entityId": $("#entityId").val()},
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
                                    location.href = "${pageContext.request.contextPath}/resource/myResource?index=index&resType=" + $("[name='resType']:checked").val();
                                }
                            }
                        });
                    }
                }

                $(function() {
                    initMicroUpload();
                });

</script>
</html>
