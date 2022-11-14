<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css"
          media="all">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.all.js"></script>
    <style>
        .control-group {
            width: 100%;
            margin-left: auto;
            margin-right: auto;
        }

        label {
            display: inline;
        }

        .form-horizontal .control-label {
            text-align: left;
            margin-left: 40px;
            width: 83px;
        }

        .form-horizontal .controls {
            margin-left: 10px !important;
        }

        /* 新增样式 */
        .box {
            float: left;
            position: relative;
        }

        .box button {
            position: absolute;
        }

        #commit {
            z-index: 99;
        }
    </style>
</head>
<body >
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_form" action="javascript:void(0);">

                    <div class="layui-form-item">
                        <label class="layui-form-label">学年<span style="color: red">*</span></label>
                        <div class="layui-input-block">
                            <select id="xn" name="xn"></select>
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <label class="layui-form-label">学期<span style="color: red">*</span></label>
                        <div class="layui-input-block">
                            <select id="xq" name="xq"></select>
                        </div>
                    </div>



                    <div class="control-group">
                        <label class="control-label">
                            汇报材料<span style="color: red">*</span>
                        </label>
                        <div class="layui-btn-container">
                            <button type="button" class="layui-btn" id="upBtn"><i class="layui-icon"></i>上传文档</button>
                        </div>
                    </div>

                    <span id="fileName" style="margin-left: 124px;    margin-top: -20px;    margin-bottom: 9px;display: block;">未选择</span>
                    <input type="hidden" name="workDataName" id="workDataName">
                    <input type="hidden" name="workDataPath" id="workDataPath">

                    <div class="form-actions tan_bottom">
                        <button class="btn btn-warning" lay-submit lay-filter="sub">确定</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>





<script type="text/javascript">
    $(function () {
        initXnXq("xn","xq",function(){
            layui.use(['upload', 'form'],function(){
                var upload = layui.upload;
                var form = layui.form;
                //执行实例
                var uploadInst = upload.render({
                    elem: '#upBtn' //绑定元素
                    ,url: '/dy/excellentTm/upload' //上传接口
                    ,accept:'file'
                    ,acceptMime:'application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document'
                    ,exts:'doc|docx'
                    ,before: function(obj){
                        layer.load(2);
                    }
                    ,done: function(res){
                        layer.closeAll('loading');
                        if(res.code!=200){
                            layer.msg("上传失败");
                            return;
                        }
                        var fileInfo=res.data.entityFile;
                        $("#fileName").text(fileInfo.fileName);
                        $("#workDataName").val(fileInfo.fileName);
                        $("#workDataPath").val(fileInfo.relativePath);
                    }
                    ,error: function(){
                        layer.closeAll('loading');
                        layer.msg("上传失败");
                    }
                });


                form.on('submit(sub)', function(data){
                   var param={
                       year:$("#xn").val(),
                       xq:$("#xq").val(),
                       workDataName:$("#workDataName").val(),
                       workDataPath:$("#workDataPath").val()
                   }
                    if(param.year=="" || param.xq==""){
                        layer.alert("学年学期必选");
                        return false;
                    }
                   if(param.workDataName==""){
                       layer.alert("请先上传工作资料");
                       return false;
                   }
                    layer.load(2);
                   $.post('/dy/excellentTm/add',param,function(d){
                       layer.closeAll('loading');
                       d=JSON.parse(d);
                       if(d.code!=200){
                           layer.msg(d.msg);
                           return;
                       }
                       layer.msg('申请成功', {
                           icon: 1,
                           time: 2000
                       }, function () {
                           parent.layer.closeAll();
                           parent.reloadTable();
                       });

                   })


                    return false;

                });

            });

        });
    });



    function initXnXq(xnId,xqId,callback){
        $("#"+xnId).change(function(){
            $("#"+xqId).html("");
            addOption('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), xqId, "code", "name")
        })
        addOption('/teach/schoolYear/list/json',xnId,"year","name",function(d){
            if(d.length>0) {
                addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, xqId, "code", "name", callback)
            }
        })
    }

    function addOption(url,id,valProperty,namePropety,callback){
        $.get(url,function(d){
            d=JSON.parse(d);
            for(var i=0;i<d.length;i++){
                var obj=d[i];
                $("#"+id).append("<option value="+obj[valProperty]+">"+obj[namePropety]+"</option>");
            }
            if(callback!=null && callback!=undefined) {
                callback(d);
            }
        })
    }

</script>
