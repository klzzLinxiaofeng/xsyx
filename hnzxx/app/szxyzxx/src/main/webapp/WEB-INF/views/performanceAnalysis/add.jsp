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
    <script type="text/javascript" defer="defer" src="/res/plugin/My97DatePicker/WdatePicker.js"></script>
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
                <form class="form-horizontal tan_form" id="publicClass_form"  enctype="multipart/form-data" action="javascript:void(0);">

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
                            全镇成绩表<span style="color: red">*</span>
                        </label>
                        <div class="layui-btn-container">
                            <input type="file" name="file" id="file" class="layui-btn" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" name="上传表格">
                        </div>
                    </div>
                    <div class="form-actions tan_bottom">
                        <button class="btn btn-warning" onclick="sub()">确定</button>
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

        var month=new Date().getMonth()+1;
        $("#xqy").val(month);
        initXnXq("xn","xq");
    });


    function sub(){

        if($("#file").val()==""){
            layer.alert("请选择上传的表格");
            return false;
        }

        var formData = new FormData($('form')[0]);
        layer.load(2);
        $.ajax({
            url:"/performance/analysis/add",
            type: "POST",//方法类型
            cache : false,//
            processData: false,
            contentType: false,
            data: formData,
            success: function(d){
                layer.closeAll('loading');
                d=JSON.parse(d);
                if(d.code!=200){
                    layer.alert(d.msg);
                    return;
                }
                layer.msg('导入成功', {
                    icon: 1,
                    time: 2000
                }, function () {
                    parent.layer.closeAll();
                    parent.reloadTable();
                });
            },error:function(){
                layer.msg("操作失败")
                layer.closeAll('loading');
            }
        });


    }


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
