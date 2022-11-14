<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css"  media="all">
    <script src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.js" charset="utf-8"></script>
</head>
<body>


<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>上传资源</legend>
</fieldset>
<form class="layui-form" action="">



    <div class="layui-form-item">
        <label class="layui-form-label"><font style="color:red">*</font>资源文件</label>
        <div class="layui-input-block">
            <span id="resName" style="font-size: 16px"></span>
            <button type="button" class="layui-btn layui-btn-normal" id="test3"><i class="layui-icon"></i>上传</button>
        </div>
    </div>
    <c:choose>
<%--        共享资源不可选择是否共享，默认共享--%>
        <c:when test="${resourceType=='res_school'}">
            <input type="hidden" name="isPersonal" value="false" />
        </c:when>
        <c:otherwise>
            <div class="layui-form-item">
                <label class="layui-form-label">共享</label>
                <div class="layui-input-block">
                    <input type="checkbox" name="isPersonal" value="false" lay-filter="switchTest" lay-skin="switch" lay-text="校本资源|不共享">
                </div>
            </div>
        </c:otherwise>
    </c:choose>

    <div id="resTypeDiv" class="layui-form-item" style="display: ${resourceType=='res_school' ? 'block':'none'}">
        <label class="layui-form-label">文件夹</label>
        <div class="layui-input-block" style="width: 400px;">
            <select name="resType" >
                <option value="2">课件</option>
                <option value="4">试卷</option>
                <option value="3">作业</option>
                <option value="8">教案</option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">立即提交</button>
<%--            <button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
        </div>
    </div>

</form>

<script>
    var $=null;
    var fileInfo=null;
    layui.use(['form','upload'], function(){
        var form = layui.form;
        var upload=layui.upload;
        $=layui.jquery;


        //监听指定开关
        form.on('switch(switchTest)', function(data){
            if(this.checked){
                $("#resTypeDiv").show();
            }else{
                $("#resTypeDiv").hide();
            }
        });

        //监听提交
        form.on('submit(demo1)', function(data){

            if(fileInfo==null){
                layer.alert("请先上传文件");
                return false;
            }
            var field=data.field;
            field.fileUuid=fileInfo.uuid;
            field.fileUrl=fileInfo.url;
            field.fileName=fileInfo.realFileName;
            field.fileSize=getFileSizeStr(fileInfo.size);
            var loadIndex=layer.load(1);
            $.ajax({
                url: "${pageContext.request.contextPath}/resource/saveOrUpdate",
                type: "POST",
                data:field,
                async: false,
                success: function (data) {
                    if(data=="0"){
                    layer.close(loadIndex);
                    layer.msg("上传资源成功",{icon: 1},function(){
                        location.href = "${pageContext.request.contextPath}/resource/myResource?index=index&resType=${resType}&personType=${resourceType}";
                    });
                    }else{
                        layer.msg("上传资源失败",{icon: 2});
                    }
                },error:function(){
                    layer.close(loadIndex);
                    layer.msg("上传资源失败",{icon: 2});
                }
            });


            return false;
        });

        //指定允许上传的文件类型
        upload.render({
            elem: '#test3'
            ,url: '/cr/uploader/common'
            ,accept:'file'
            ,exts:'mp4|doc|docx|ppt|pptx|jpg|jpeg|png|gif|swf|xls|xlsx|pdf|rar|mp3|xep'
            ,done: function(res){
                layer.closeAll('loading'); //关闭loading
                $("#resName").text(res.realFileName);
                fileInfo=res;
                layer.msg("文件上传成功");
            }
            ,before: function(obj){
                layer.load(1);
            }
            ,error: function(index, upload){
                layer.closeAll('loading'); //关闭loading
            }
        });

    });

    function getFileSizeStr(byteSize){
        var mSize=1024*1024;
        if(byteSize<mSize){
            return "<1M";
        }
        return parseInt(byteSize/mSize)+"M"
    }

</script>

</body>
</html>
