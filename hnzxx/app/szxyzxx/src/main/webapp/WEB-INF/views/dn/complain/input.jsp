<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <%@ include file="/views/embedded/plugin/uploadify.jsp"%>
    <%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <title>请假</title>
    <style type="text/css">
        input[type="radio"]{
            margin:0 5px;
            position:relative;
            top:-1px;
        }
        .img_1 a{
            font-size: 15px;
        }
    </style>
    <script>
        $(function(){
            $(".up_img ul li").hover(function(){
                $(this).children("a").show();
            },function(){
                $(this).children("a").hide();
            });
            $("body").on("click",".up_img ul li a",function(){
                $(this).parent().remove();
            })
        })
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets">
                <div class="yc_sq">
                    <form class="form-horizontal" action="javascript:void(0);" style="padding-bottom: 0;" id="form">
                        <div class="control-group">
                            <label class="control-label">所在部门：</label>
                            <div class="controls">
                                <select  id="dept" style="width:350px;"></select>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">问题类别：</label>
                            <div class="controls" style="padding-top:5px;">
                                <input type="radio" name="wtlb" value="技术咨询">技术咨询
                                <input type="radio" name="wtlb" value="使用操作">使用操作
                                <input type="radio" name="wtlb" value="费用咨询">费用咨询
                                <input type="radio" name="wtlb" value="应用更新">应用更新
                                <input type="radio" name="wtlb" value="其他">其他
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">问题描述：</label>
                            <div class="controls">
                                <textarea  style="height:75px;padding:5px;width:350px;" id="description" name="description">${complainVo.description}</textarea>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">上传照片：</label>
                            <div class="controls">
                                <div class="up_img">
                                    <%--<a href="javascript:void(0)" class="select">选择图片</a>--%>
                                    <input type="hidden" id="uploader" name="uploader">
                                    <div id="zp">
                                        <c:forEach items="${complainVo.fileUUIDList}" var="uuid" varStatus="vs">
                                        <div class="img_1">
                                            <a data-id="${uuid}" onclick="reMove(this);">x</a>
                                            <img src='<entity:getHttpUrl uuid="${uuid}"/>' data-id="${uuid}" onclick="Change(this);" style="width:120px;height:120px;">
                                        </div>
                                        </c:forEach>
                                    </div>
                                    <ul>
                                        <%--<c:forEach items="${complainVo.fileUUIDList}" var="uuid" >--%>
                                            <%--<li><img src="<entity:getHttpUrl uuid='${uuid}'/>"><a href="javascript:void(0)"></a></li>--%>
                                        <%--</c:forEach>--%>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="caozuo" style="text-align:center;">
                            <input type="hidden" id="id" name="id" value="${complainVo.id}" />
                            <button class="btn btn-primary" onclick="save();">确定</button> <button class="btn" onclick="$.closeWindow();">取消</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var checker;
    $(function () {
        checker = initValidator();
        getDept();
        uploadFile();
        var type = "${complainVo.type}";
        if("" != type){
            $("input[name='wtlb'][value='" + type + "']").attr('checked','true');
        }
    });

    function getDept() {
        $.post("${pageContext.request.contextPath}/dn/complain/dept/get", function(data, status){
            if("success" == status){
                data = eval("(" + data + ")");
                $("#dept").html("");
                for(var i=0; i<data.length; i++){
                    $("#dept").append("<option value='" + data[i].departmentId +"'>" + data[i].departmentName + "</option>");
                }
                //回显部门
                var deptId = "${complainVo.departId}";
                if("" != deptId){
                    $("#dept").val(deptId);
                }
            }
        });
    }

    function initValidator() {
        return $("#form").validate({
            rules : {
                "description" : {
                    required: true,
                    maxlength: 500
                },
                "wtlb" : {
                    required: true
                }
            },
            messages : {
                "description" : {
                    maxlength: "最长不能超过500个字符"
                }
            }
        });
    }

    function save() {
        if (checker.form()) {
            var loader = new loadLayer();
            var val = {};
            var $id = $("#id").val();
            var departId = $("#dept").val();
            var type = $("input[name='wtlb']:checked").val();
            var description = $("#description").val();
            val.departId = departId;
            val.type = type;
            val.description = description;
            var url = "${ctp}/dn/complain/creator";
            if ("" != $id) {
                val._method = "put";
                url = "${ctp}/dn/complain/" + $id;
            }
            var uuid = getFileUrls();
            val.fileUUIDs = uuid;
            val.isChange = true;
            loader.show();
            $.post(url, val, function(data, status) {
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
            });
        }
    }

    function getFileUrls() {
        var imgs = $(".img_1");
        var urls = "";
        $.each(imgs, function(index, value) {
            urls += ($(value).find("img").attr("data-id") + ",");
        });
        if (urls != "" && urls != "undefined") {
            urls = urls.substring(0, urls.length - 1);
        }
        return urls;
    }

    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
    }

    function uploadFile(){
        var obj = $("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'
            },
            fileObjName : 'file',
            fileTypeDesc : "文件上传",
// 							fileTypeExts : "*.*", //默认*.*
            fileTypeExts : '*.gif; *.jpg; *.png;*.jpeg;*.bmp',
            method : 'post',
            multi : true, // 是否能选择多个文件
            auto : true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout : 1,
            queueSizeLimit : 1,
            fileSizeLimit : 4 * 1024,
            buttonText : "选择图片",
            requeueErrors : false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
            height : 20,
            width : 70,
            onUploadSuccess : function(file, data, response) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"><a data-id="'
                    + $jsonObj.uuid
                    + '" onclick="reMove(this);">x</a><img style="width:120px;height:120px;" class="ims" onclick="Change(this);" src="'
                    + $jsonObj.url
                    + '" data-id="'+$jsonObj.uuid+'"/>&nbsp;&nbsp;&nbsp;</div>';
                $.success("上传成功!", 9);
                $("#zp").html(img);
            },
            onUploadStart : function(file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onUploadError : function(file, errorCode, errorMsg,
                                     errorString) {
                $.alert('The file ' + file.name
                    + ' could not be uploaded: '
                    + errorString);
            }
        });

    }

</script>
</body>
</html>