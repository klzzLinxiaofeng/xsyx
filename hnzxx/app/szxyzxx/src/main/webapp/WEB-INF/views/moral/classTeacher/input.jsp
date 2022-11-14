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
<body style="background-color: cdd4d7 !important">
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
                        <label class="control-label"><font style="color: red">*</font>
                            上传表格：
                        </label>
                        <div class="layui-btn-container">
                            <button type="button" class="layui-btn" id="test3"><i class="layui-icon"></i>上传文件</button>
                        </div>
                    </div>
                    <span id="fileName"
                          style="margin-left: 124px;    margin-top: -20px;    margin-bottom: 9px;display: block;">未选择</span>
                    <div class="control-group">
                        <label class="control-label">
                            下载模板：
                        </label>
                        <div class="layui-btn-container">
                            <a href="" id="ahref">
                                <button class="btn btn-warning" type="button">下载模板
                                </button>
                            </a>
                        </div>
                    </div>
                    <div class="form-actions tan_bottom">
                        <button class="btn btn-warning" type="button" id="commit">确定
                        </button>
                        <button data-type="reload" id="upload" lay-filter="import">
                            import
                        </button>
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
        initXnXq("xn","xq");
        var teacherId = "${teacherId}";
        var type = "${type}";
        if (type == 0) {
            $("#ahref").attr("href", "/res/excel/香市第一小学绩效评分方案（语数英）.xlsx");
        } else {
            $("#ahref").attr("href", "/res/excel/香市第一小学绩效评分方案（综合科）.xlsx");
        }

        $.initCascadeSelector({
            "type": "team",
            "yearSelectId": "xn"
        });

        var UPLOAD_FILES;
        $("#upload").hide();
        $('#commit').on('click', function () {
            var name = $("#xn").val();
            var xq = $("#xq").val();
            var fileName = $("#fileName").text();
            if(fileName==""){
                layer.alert("请选择上传的文件", {
                    icon: 2, title: '提示'
                });
                return;
            }
            if (name != '' && xq!='') {
                $('#upload').click();
            } else {
                layer.alert("请选择学年学期", {
                    icon: 2, title: '提示'
                });
            }
        });

        layui.use(['upload', 'element', 'layer'], function () {
            var $ = layui.jquery
                , upload = layui.upload
                , layer = layui.layer;
            //指定允许上传的文件类型
            upload.render({
                elem: '#test3'
                , url: '${ctp}/student/star/student/uploadTeacherExcel'
                , auto: false //选择文件后不自动上传
                , bindAction: '#upload' //指向一个按钮触发上传
                , accept: "file"
                , multiple: false
                , exts: 'xls|excel|xlsx'
                , number: 1
                , before: function (obj) {
                    loadingIndex = layer.msg('文件上传中...', {
                        icon: 16,
                        shade: 0.01,
                        time: 0
                    })
                    var xn = $("#xn").val();
                    var xq=$("#xq").val();
                    this.data = {//传给后台的参数
                        'year': xn
                        , 'teacherId': teacherId,
                        'xq':xq,
                        'type':type
                    }
                }
                , choose: function (obj) {

                    UPLOAD_FILES = obj.pushFile();
                    clearFile();    //将所有文件先删除再说
                    obj.preview(function (index, file, result) {
                        $("#fileName").text("文件：" + file.name);
                        obj.pushFile(); // 再把当前文件重新加入队列
                    });
                }
                , done: function (res, index) {
                    if (res.code == 200) { 	//上传成功
                        layer.msg("上传成功");
                        clearFile();    //将所有文件先删除再说
                        $("#fileName").text("文件：");
                        if (parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else {
                        layer.alert(res.msg, {
                            title: '提示'
                        });
                    }

                }
            });
        });

        //清空文件队列
        function clearFile() {
            for (var x in UPLOAD_FILES) {
                delete UPLOAD_FILES[x];
            }
        }
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
