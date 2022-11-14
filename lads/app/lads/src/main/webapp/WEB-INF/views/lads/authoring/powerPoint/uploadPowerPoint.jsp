<%-- 
    Document   : uploadPowerPoint
    Created on : 2013-8-24, 13:45:09
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<div class="ppt_add">
    <div class="ppt_chance">
        <!--        <div class="title_chance"><span>PPT选择</span></div>-->
        <div class="main">
            <div class="images">
                <img src="/css/common/lads/images/PPT.jpg" />
            </div>
            <div class="button" style="padding-left: 300px;">
                <input class="btn btn-success" type="file" name="ppt" id="powerPointFile_<s:property value="#request.id"/>" />
                <div id="fileQueue_<s:property value="#request.id"/>"></div>
                <input type="hidden" id="powerPointFileId_<s:property value="#request.id"/>" value="<s:property value="#request.powerPointFileId"/>"/>
            </div>
            <%-- <span>请用以上方式上传你的ppt课件</span> --%>
        </div>
    </div>
</div>
<script type="text/javascript">
    function initPowerPointUpload(){
        $('#powerPointFile_<s:property value="#request.id"/>').uploadify ({
            //以下参数均是可选
            'uploader' : '/js/common/upload/uploadify.swf', //指定上传控件的主体文件，默认‘uploader.swf’
            'fileDataName'   : 'ppt', //和以下input的name属性一致
            'queueID' : 'fileQueue_<s:property value="#request.id"/>',  //上传队列的div
            'script' : '/common/lads/ladsPowerPointAction_uploadPpt.action', //指定服务器端上传处理文件，默认‘upload.php’
            'cancelImg' : '/js/common/upload/cancel.png', //指定取消上传的图片，默认‘cancel.png’
            'buttonImg' : '/image/teacher/upload_button.gif', //指定上传按钮的图片
            'auto' : true, //选定文件后是否自动上传，默认false
            //'buttonText':"选择文件",
            //'folder' : '/uploads', //要上传到的服务器路径，默认‘/’
            'muti' : false, //是否允许同时上传多文件，默认false
            'fileDesc' : '请上传ppt或pptx文件', //出现在上传对话框中的文件类型描述
            'fileExt' : '*.ppt;*.pptx', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
            'sizeLimit': 524288000, //控制上传文件的大小，单位byte
            'simUploadLimit' :5, //多文件上传时，同时上传文件数目限制
            'scriptData' : {"toolId":"<s:property value="#request.id"/>"},
            onError: function(event, queueID, fileObj) {
                alert("文件:" + fileObj.name + "上传失败");
            },
            onComplete:function (event, queueID, fileObj, response, data) {
                if(response!=null&&response!=""){
                    $("#powerPointFileId_<s:property value="#request.id"/>").val(response);
                    loadPowerPointContentIndex(response,fileObj.name,"<s:property value="#request.id"/>");
                }else{
                    alert("文件:" + fileObj.name + "上传失败");
                    $("#powerPointFileId_<s:property value="#request.id"/>").removeAttr("value");
                }
            }
        });
    }
                
    $(function(){
        //初始上传组件
        initPowerPointUpload();
    })
</script>
