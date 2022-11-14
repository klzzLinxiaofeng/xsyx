<%-- 
    Document   : uploadFile
    Created on : 2012-3-10, 17:02:26
    Author     : Administrator
--%>
<%@page import="com.gzxtjy.resources.util.DocPathUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="resource" uri="/WEB-INF/resource"%>
<!DOCTYPE html>
<%
            String type = request.getParameter("type");
            String ext = "";
            String desc = "";
            if (type == null || "".equals(type)) {
                type = (String) request.getAttribute("type");
            }
            if (type.equals("image")) {
                desc = "请上传图片(png,gif,jpg,jpeg)文件";
                ext = "*.png;*.gif;*.jpg;*.jpeg";
            } else if (type.equals("video")) {
                desc = "请上传flv文件";
                ext = "*.flv";
            } else if (type.equals("sound")) {
                desc = "请上传mp3文件";
                ext = "*.mp3";
            }
%>
<link rel="stylesheet" href="/js/common/upload/uploadify.css" type="text/css" />
<script type="text/javascript" src="/js/common/upload/jquery.uploadify.v2.1.4.js"></script>
<div class="addjoon-open1 totaltable tablediv1" style="background-color: white;width: 468px;padding: 10px;border: 5px solid #999;">
    <table id="contentTable">
        <tbody>
            <tr>
                <td align="right" valign="top" class="tdbold">文件上传</td>
                <td align="left" valign="middle" id="uploadTd">
                    <input type="file" id="upload" class="input-all inwd3"/>
                    <div id="fileQueue"></div>
                </td>
            </tr>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    $(function(){
        //加载教材目录
        $('#upload').uploadify ({
            //以下参数均是可选
            'uploader' : '/js/common/upload/uploadify.swf', //指定上传控件的主体文件，默认‘uploader.swf’
            'fileDataName'   : 'doc', //和以下input的name属性一致
            'queueID' : 'fileQueue',  //上传队列的div
            'script' : '/common/resources/uploadAction_quizFileUpload.action', //指定服务器端上传处理文件，默认‘upload.php’
            'cancelImg' : '/js/common/upload/cancel.png', //指定取消上传的图片，默认‘cancel.png’
            'buttonImg' : '/image/teacher/upload_button.gif', //指定上传按钮的图片
            'auto' : true, //选定文件后是否自动上传，默认false
            //'buttonText':"选择文件",
            //'folder' : '/uploads', //要上传到的服务器路径，默认‘/’
            'muti' : false, //是否允许同时上传多文件，默认false
            'fileDesc' : '<%=desc%>', //出现在上传对话框中的文件类型描述
            'fileExt' : '<%=ext%>', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
            'sizeLimit': 524288000, //控制上传文件的大小，单位byte
            'simUploadLimit' :5, //多文件上传时，同时上传文件数目限制
            'scriptData' : {'userId':chooseQuiz,'userName':chooseQuiz,
                'uploadPath':$("#uploadPath_"+chooseQuiz).val()},
            onError: function(event, queueID, fileObj) {
                alert("文件:" + fileObj.name + "上传失败");
            },
            onComplete:function (event, queueID, fileObj, response, data) {
                if(response!=null&&response!=""){
                    var swf = getChooseQuizSwf();
                    var json = eval("("+response+")");
                    $("#absPath_"+chooseQuiz).val(json.absPath);
                    swf.FileSelected(json.urlPath);
                }else{
                    alert("文件:" + fileObj.name + "上传失败");
                }
            }
        });
    });
</script>
