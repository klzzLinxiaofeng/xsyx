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
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            学期周数：
                        </label>
                        <div class="controls">
                            <input type="text" id="name" name="name" class="span4" placeholder=""
                                   value="" style="width: 60%">
                            <br/>
                            <span style="margin-left: 113px;"> 周数名称相同并且周数名称下对应的没有学生分数，</span>
                            <br/>
                            <span style="margin-left: 113px;">再次上传会添加到这个周数名称下！，不会创建新的周之星</span>
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
                        <label class="control-label"><font style="color: red">*</font>
                            下载模板：
                        </label>
                        <div class="layui-btn-container">
                            <a href="/res/excel/周学生评分表.xls">
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
        var UPLOAD_FILES;
        $("#upload").hide();
        $('#commit').on('click', function () {
            var name = $("#name").val();
            if (name != '') {
                $('#upload').click();
            } else {
                layer.alert("请完善信息", {
                    icon: 2, title: '提示'
                });
            }
        })
        layui.use(['upload', 'element', 'layer'], function () {
            var $ = layui.jquery
                , upload = layui.upload
                , layer = layui.layer;
            //指定允许上传的文件类型
            upload.render({
                elem: '#test3'
                , url: '${ctp}/student/star/student/read' //改成您自己的上传接口
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
                    var name = $("#name").val();
                    this.data = {//传给后台的参数
                        'name': name
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

</script>
