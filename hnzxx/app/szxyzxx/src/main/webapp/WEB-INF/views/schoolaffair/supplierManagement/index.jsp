<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%-- 本地注释，线上需要 --%>
<%--    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">--%>

    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/lay/layui.css" rel="stylesheet">
    <script src="${ctp}/res/lay/layui.js" charset="utf-8"></script>
</head>

<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="供货商管理" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="layui-form-item">
        <label class="" style="font-size: 20px">食堂视频：（宽高比16:9）</label>
        <br/>
        <button type="button" class="layui-btn cover" id="control_video_button">上传视频</button>
        <button type="button" class="layui-btn layui-btn-danger cover" id="control_video_del">删除视频</button>
        <input type="hidden" id="temp_url_id" value="${video.id}" name="temp_url_id"/>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label"></label>
        <div class="layui-input-block">
            <video src="" controls="controls" id="control_video_demo" style="width: 30%; height: 400px">
                您的浏览器不支持视屏
            </video>
            <p id="demoText"></p>
        </div>
    </div>

    <div class="layui-upload">
        <span style="font-size: 20px"> 宣传图片: （宽高比16:9） </span>
        <br/>
        <button type="button" class="layui-btn" id="test3" style="margin: 10px">+ 添加宣传图片</button>

        <blockquote class="layui-elem-quote layui-quote-nm" style="margin: 10px; background-color: #efefef">
            <div class="layui-upload-list" id="demo3" style="margin: 10px">
                <c:forEach items="${images}" var="item">
                    <div class="file-iteme" style="display: inline-block;margin-left: 10px">
                        <div class="removeIcon handle" style="margin:auto;">
                            <input hidden value="${item.id}" id="btn${item.id}">
                            <i style="color: red ;margin-right: 40%; font-size: 17px"><span class="layui-btn"
                                                                                            style="background-color: red">删除</span></i>
                        </div>
                        <img style="color: blue;width: 250px" src='${item.url}'>
                    </div>
                </c:forEach>
            </div>
        </blockquote>
    </div>

    <div class="layui-upload" style="margin-bottom: 150px">
        <button type="button" class="layui-btn" id="test2" style="margin: 10px" onclick="loadCreatePage()">+ 添加供应商资质
        </button>

        <blockquote class="layui-elem-quote layui-quote-nm" style="margin: 10px; background-color: #efefef">
            <div class="layui-upload-list" id="demo2" style="margin: 10px">
                <c:forEach items="${supplier}" var="item">
                    <div class="file-iteme" style="display: inline-block; margin-left: 10px">
                            <div class="removeIcon handle click" style="margin:auto; display: inline-block">
                                <input hidden value="${item.id}" id="btn${item.id}">
                                <i style="color: red ;margin-right: 40%; font-size: 17px">
                                    <span class="layui-btn" style="background-color: red">删除</span></i>
                            </div>
                            <span class="removeIcon delete" style="margin:auto;display: inline-block;" onclick="loadCreatePage(${item.id})">
                                <input hidden value="${item.id}">
                                <i style="color: red ;margin-right: 40%; font-size: 17px">
                                    <span class="layui-btn">修改</span></i>
                            </span>
                        <div style="font-size: 17px; margin: 5px;">${item.name}</div>
                        <img style="color: blue;width: 250px;height: 250px;" src='${item.url}'>
                    </div>
                </c:forEach>
            </div>
        </blockquote>
    </div>
</div>

<script>
    $(function () {
        // 0：视频1: 供应商资质2:宣传图片
        $("div").find('.handle').on('click', function () {
            $(this).parent().remove();
            // 根据图片uuid 进行删除
            dels(2, $(this).find(':input:eq(0)').val());
        });
        $("div").find('.click').on('click', function () {
            $(this).parent().remove();
            // 根据图片uuid 进行删除
            dels(1, $(this).find(':input:eq(0)').val());
        });

        mouseChange();
        $(".file-iteme").children(".info").hide();
        $(".file-iteme").children(".handle").hide();

        var video = '${video.uuid}';
        if (video != null && video != "" && video != undefined) {
            $('#control_video_button').hide();
            $('#control_video_demo').attr('src', '${video.url}'); //图片链接（base64）
        } else {
            $('#control_video_button').show();
            $('#control_video_del').hide();
            $('#control_video_demo').hide();
        }
    });

    // 	加载创建对话框
    function loadCreatePage(id) {
        if (id == null || id == '' || id == undefined) {
            $.initWinOnTopFromLeft('供应商资质', '${ctp}/schoolaffair/recipes/supplier/readModifySupplier', '600', '450');
        } else {
            $.initWinOnTopFromLeft('供应商资质', '${ctp}/schoolaffair/recipes/supplier/readModifySupplier?id='+id, '600', '450');
        }
    }

    //给图片添加删除
    function mouseChange() {
        $(document).on("mouseenter mouseleave", ".file-iteme", function (event) {
            if (event.type === "mouseenter") {
                //鼠标悬浮
                $(this).children(".info").fadeIn("fast");
                $(this).children(".handle").fadeIn("fast");
            } else if (event.type === "mouseleave") {
                //鼠标离开
                $(this).children(".info").hide();
                $(this).children(".handle").hide();
            }
        });
    }

    var upload = layui.upload;

    // 宣传图片
    layui.use('upload', function () {
        var $ = layui.jquery, upload = layui.upload;
        upload.render({
            elem: '#test3',
            url: '${pageContext.request.contextPath}/uploader/common',
            data: {
                jsessionId: function () {
                    return '<%=request.getSession().getId()%>';
                }
            },
            accept: 'images',
            acceptMime: 'image/jpg,image/png,image/jpeg',
            exts: 'jpg|png|jpeg',
            size: 1024,
            multiple: true,
            auto: true,
            choose: function (obj) {
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列

                var length = $("#demo2").children().length;
                var size = length == null || length == undefined ? 0 : length;
                //读取本地文件
                obj.preview(function (index, file, result) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        var image = new Image();
                        image.onload = function () {
                            var realWidth = image.width;
                            var realHeight = image.height;
                            var tr = $(['<div id="upload-' + index + '" class="file-iteme" style="display: inline-block;margin-left: 10px">' +
                            '<div class="removeIcon handle" style="margin:auto;"><input hidden value="" id="btn' + index + '"> ' +
                            '<i class="layui-icon layui-icon-delete" style="color: red ;margin-right: 40%; font-size: 17px"><span class="layui-btn" style="background-color: red">删除</span></i></div>' +
                            '<img style="color: blue;width: 250px" "showBig(this)" src=' + result + ' id="img-' + index + '">' +
                            '</div>'].join(''));
                            tr.find('.handle').on('click', function () {
                                $(this).parent().remove();
                                delete files[index];
                                // 根据图片uuid 进行删除
                                dels(2, $(this).find(':input:eq(0)').val());
                            });
                            $('#demo3').append(tr);
                        };
                        image.src = result;
                    };
                    //正式读取文件
                    reader.readAsDataURL(file);
                });
            },
            before: function (obj) {
                layer.load(); //上传loading
            },
            done: function (res, index, upload) {
                var btn = "btn" + index;
                delete this.files[index]; //删除文件队列已经上传成功的文件
                // type1 供应商资质"1609583112220-0"
                $.ajax({
                    type: "POST",
                    url: "${ctp}/schoolaffair/recipes/supplier/createSupplier?type=2&uuid=" + res.uuid,
                    async: false,
                    success: function (data) {
                        data = eval("(" + data + ")");
                        if ("success" === data.info) {
                            $("#" + btn).val(data.responseData.id);
                            layer.msg("上传成功！");
                            $(".file-iteme").children(".info").hide();
                            $(".file-iteme").children(".handle").hide();
                        } else {
                            layer.msg("上传失败，请稍后再试！");
                            $("#upload-" + index).remove();
                        }
                    },
                    //请求失败，包含具体的错误信息
                    error: function (e) {
                        layer.msg("上传失败，请稍后再试！");
                        $("#upload-" + index).remove();
                    }
                });
                layer.closeAll('loading'); //关闭loading
                return;
            }, error: function (res, index, upload) {
                layer.msg("上传失败，请稍后再试！");
                delete this.files[index]; //删除文件队列已经上传成功的文件
                // 移除图片
                $("#upload-" + res).remove();
                layer.closeAll('loading'); //关闭loading
                return;
            }
        });
    });

    layui.use(['upload', 'form'], function () {
        var form = layui.form
            , $ = layui.jquery
            , upload = layui.upload;

        var uploadInst = upload.render({
            elem: '#control_video_button', //绑定元素
            url: '${pageContext.request.contextPath}/uploader/common',
            data: {
                jsessionId: function () {
                    return '<%=request.getSession().getId()%>';
                }
            },
            accept: 'video',
            acceptMime: 'video/mp4'
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#control_video_demo').attr('src', result); //图片链接（base64）
                    $('#control_video_demo').css('display', 'block');
                    $('#control_video_del').css('display', 'inline');
                });
            },
            done: function (res) {
                $.ajax({
                    type: "POST",
                    url: "${ctp}/schoolaffair/recipes/supplier/createSupplier?type=0&uuid=" + res.uuid,
                    async: false,
                    success: function (data) {
                        data = eval("(" + data + ")");
                        if ("success" === data.info) {
                            $("#temp_url_id").val(data.responseData.id);
                            $('#control_video_button').hide();
                            $('#control_video_del').show();
                            $('#control_video_demo').show();
                            layer.msg("上传成功！");
                        } else {
                            layer.msg("上传失败，请稍后再试！");
                        }
                    },
                    //请求失败，包含具体的错误信息
                    error: function (e) {
                        layer.msg("上传失败，请稍后再试！");
                        $("#upload-" + index).remove();
                    }
                });

            }, error: function (res) {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });
        $('#control_video_del').click(function () {
            $('#control_video_demo').css('display', 'none');
            $('#control_video_del').css('display', 'none');
            $('#control_video').val('');
            $('#control_video_button').show();
            var id = $("#temp_url_id").val();
            dels(0, id);
        })
    });


    function dels(type, id) {
        $.ajax({
            type: "POST",
            url: "${ctp}/schoolaffair/recipes/supplier/deleteSupplier?type=" + type + "&id=" + id,
            async: false,
            success: function (data) {
                data = eval("(" + data + ")");
                if ("success" === data.info) {
                    layer.msg("删除成功！");
                } else if ("fail" === data.info) {
                    layer.msg("删除失败，请稍后再试！");
                }
            },
            //请求失败，包含具体的错误信息
            error: function (e) {
                layer.msg("上传失败，请稍后再试！");
            }
        });
    }

</script>

</body>
</html>