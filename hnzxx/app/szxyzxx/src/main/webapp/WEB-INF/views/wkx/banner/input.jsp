<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%-- 本地注释，线上需要 --%>
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">

    <%@ include file="/views/embedded/common2.jsp" %>

    <script src="${ctp}/res/summernote/js/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link href="${ctp}/res/summernote/dist/summernote.css" rel="stylesheet"/>
    <script src="${ctp}/res/summernote/js/bootstrap.min.js"></script>
    <script src="${ctp}/res/summernote/dist/summernote.js"></script>
    <script src="${ctp}/res/summernote/dist/lang/summernote-zh-CN.js"></script>
    <!-- 页面验证功能 -->
    <script type="text/javascript" src="${ctp}/res/plugin/jquery-validate/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctp}/res/plugin/jquery-validate/additional-methods.min.js"></script>
    <script type="text/javascript" src="${ctp}/res/plugin/jquery-validate/jquery.validate.messages_cn.js"></script>
    <script type="text/javascript" src="${ctp}/res/plugin/jquery-validate/jquery.metadata.js"></script>
    <%@include file="/views/embedded/plugin/szxy_window_js.jsp" %>
   <%-- <%@ include file="/views/embedded/plugin/uploadify.jsp" %>--%>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>

    <link type="text/css" href="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.css"
          rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/extra/micro/font-awesome-ie7.min.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/xyuploader.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/md5_md5blob.js"></script>
    <style type="text/css">
        .myerror{
            color: red;
        }
    </style>
</head>
<body>
<div class="container-fluid" style="margin-bottom: 50px">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="banner管理" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        banner管理
                        <p class="btn_link" style="float: right;">
                            <button type="button" class="btn btn-success" onclick="$.refreshWin();">
                                <i
                                        class="fa  fa-undo"></i>刷新
                            </button>
                            <button type="button" class="btn btn-success" onclick="backToLearnerRecord();">返回</button>
                        </p>
                    </h3>
                </div>

                <div class="light_grey"></div>


                <div class="content-widgets ">
                    <div class="widget-container">
                        <form class="form-horizontal tan_form" id="microbanner_form" action="javascript:void(0);">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-md-1">
                                        <label class="control-label">
                                            <span style="color: red;">*</span>标题：
                                        </label>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="controls">
                                            <input type="text" id="title" name="title" style="width: 250px;"
                                                   class="span13"
                                                   placeholder="标题" value="${microBanner.title}">
                                        </div>
                                    </div>
                                </div>
                                <br/>
                                <div class="row">
                                    <div class="col-md-1">
                                        <label class="">
                                            <span style="color: red;">*</span>宣传图：
                                        </label>
                                    </div>
                                    <div>

                                    </div>
                                    <div class="col-md-3">
                                        <div id="zp" style="display:inline-block;">
                                            <div class="img_1">
                                                <c:if test="${(isCK==''||isCK==null) && (microBanner.thumUrl !=null && microBanner.thumUrl != '') }">
                                                    <a data-id="${microBanner.thumUrl}" onclick="reMove(this);"> 取消 </a>
                                                </c:if>
                                                <c:if test="${microBanner.thumUrl !=null && microBanner.thumUrl != ''}">
                                                    <img src="${microBanner.thumUrl}" onclick="Change(this);"
                                                         style="width: 260px; height: 130px;">
                                                </c:if>
                                                <c:if test="${microBanner.thumUrl ==null || microBanner.thumUrl == ''}">
                                                    <img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);"
                                                         style="width: 233px; height: 130px;">
                                                </c:if>
                                            </div>
                                        </div>
                                        <div><span>支持jpg、gif、png、bmp格式，宽高2*1</span></div>

                                        <c:if test="${isCK==''||isCK==null}">
                                            <input type="hidden" id="uploader" name="uploader">
                                        </c:if>
                                        <input type="hidden" id="entityId" name="entityId"
                                               value="${microBanner.entityId}">
                                        <span id="tp_queue"></span>

                                        <div class="clear"></div>
                                    </div>
                                </div>
                                <br/>
                                <div class="row">
                                    <div class="col-md-1">
                                        <label class="control-label">
                                            是否启用：
                                        </label>
                                    </div>

                                    <div class="col-md-2">
                                        <div class="controls">
                                            <label for="pushState_1">是</label> <input type="radio" id="pushState_1"
                                                                                    name="pushState" class="pushState"
                                                                                    <c:if test="${microBanner.pushState=='0'}">checked='checked'</c:if>
                                                                                    placeholder=""
                                                                                    value="0">
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <label for="pushState_2">否</label>
                                            <input type="radio" id="pushState_2"
                                                                                     name="pushState" class="pushState"
                                                                                     <c:if test="${microBanner.pushState == '1' || empty microBanner.pushState}">checked='checked'</c:if>
                                                                                     placeholder="" value="1">
                                        </div>
                                    </div>
                                </div>
                                <br/>
                                <div class="control-group">
                                    <label class="control-label">
                                        <span style="color: red;">*</span>正文：
                                    </label>
                                    <div class="controls">
                                        <div id="summernote">${microBanner.content}</div>
                                    </div>
                                </div>
                                <input type="hidden" id="id" name="id" value="${microBanner.id}"/>
                                <input type="hidden" id="isCK" value="${isCK}"/>
                                <div class="control-group">

                                    <div class="controls">
                                        <button class="btn btn-warning" id="btn-hide" type="button"
                                                onclick="saveOrUpdate();">确定
                                        </button>
                                    </div>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var checker;

    $(function () {
        // getSelectData();
        var isCK = $("#isCK").val();
        if (isCK != "") {
            $("input").attr('readonly', true);
            $("textarea").attr('readonly', true);
            $(':radio').attr('disabled', true);
            $(':checkbox').attr('disabled', true);
            $('a').removeAttr('onclick');
            $('select').attr('disabled', true);
            $("#btn-hide").hide();
            $('#summernote').summernote('disable');
        }

        $.refreshWin = function () {
            if (parent != null && parent.loader != null) {
                parent.loader.show();
            }
            window.location.reload();
        }


        $('#summernote').summernote({
            //功能图标改为中文
            lang: 'zh-CN',
            //预设内容
            placeholder: '请在此输入内容...',
            height: 300,
            width: 900,
            toolbar: [
                ['style', ['style']],
                ['font', ['bold', 'italic', 'underline', 'clear']],
                ['fontname', ['fontname']],
                ['color', ['color']],
                // ['para', ['ul', 'ol', 'paragraph']],
                ['height', ['height']],
                ['table', ['table']],
                ['insert', ['link', 'picture', 'hr']],
                ['view', ['fullscreen', 'codeview']],
                ['help', ['help']]
            ],
            //回调函数
            callbacks: {
                //图片文件上传
                onImageUpload: function (files, editor, $editable) {
                    data = new FormData();
                    data.append("file", files[0]);
                    data.append("jsessionId", '<%=request.getSession().getId()%>');
                    $.ajax({
                        data: data,
                        type: "POST",
                        url: '${pageContext.request.contextPath}/uploader/common',
                        cache: false,
                        contentType: false,
                        processData: false,
                        dataType: "json",
                        success: function (data) {
                            $('#summernote').summernote('insertImage', data.url);
                        },
                        error: function () {
                            layer.alert('上传失败');
                        }
                    });
                }
            }

        });
        uploadFile();
        checker = initValidator();
    });

    function getSelectData() {
        var idVal = $("#userType").find("option:selected").val();
        $.get("${ctp}/res/path/user-type?userTypeId=" + idVal,
            function (res) {
                let data = eval("(" + res + ")");
                $("#resPath").empty();
                //添加select第一个option
                for (var i = 0; i < data.length; i++) {
                    //添加option元素
                    $("#resPath").append("<option value='" + data[i].value + "'>" + data[i].name + "</option>");
                }
            });
    }

    // $("#userType").change(function () {
    //     getSelectData();
    // })

    function initValidator() {
        return $("#microbanner_form").validate({
            errorClass: "myerror",
            rules: {
                "title": {
                    required: true,
                    maxlength: 200
                },
                "entityId": {
                    required: true,
                    maxlength: 36
                },
                "resPath": {
                    required: true
                },

            },
            messages: {
                "entityId": "请上传图片"
            }
        });
    }

    //保存或更新修改
    function saveOrUpdate() {

        if (checker.form()) {
            // if ($('input[name="pushState"]').data("previousValue")) {
            //     $('input[name="pushState"]').data("previousValue").old = null;
            // }
            var $requestData2 = formData2JSONObj("#microbanner_form");
            var flg;
            if ($requestData2.pushState == 0){
                $.ajax({
                    url: "${pageContext.request.contextPath}/wkx/microbanner/checker",
                    type: "post",
                    data: $requestData2,
                    async: false,
                    success: function(data) {
                        console.log(data)
                        flg = data;
                    }
                });
                if ("false" === flg) {
                    layer.alert('暂不支持开启多个banner');
                    return;
                }
            }
            if ($('#summernote').summernote('isEmpty')) {
                layer.alert('请完善页面内容');
                return;
            }
            var summernote = $('#summernote').summernote('code');
            var loader = new loadLayer();
            var $id = $("#id").val();
            var $requestData = formData2JSONObj("#microbanner_form");
            $requestData.content = summernote;
            var url = "${ctp}/wkx/microbanner/creator";
            if ("" != $id) {
                $requestData._method = "put";
                url = "${ctp}/wkx/microbanner/" + $id;
            }
            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    layer.msg('操作成功');
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        window.location.href = encodeURI("${ctp}/wkx/microbanner/index");
                    } else {
                        layer.alert('操作失败');
                    }
                } else {
                    layer.alert('操作失败');
                }
                loader.close();
            });
        }
    }

    function getType() {

    }

    function uploadFile() {
        $('#uploader').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传封面',
            'removeCompleted':true,
            'height' : 20,
            'width' : 70,
            'formData': {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"><a data-id="'
                    + $jsonObj.uuid
                    + '" onclick="reMove(this);">x</a><img style="width:233px;height:130px;" class="ims" onclick="Change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                $("#entityId").val($jsonObj.uuid);
                layer.msg('上传成功');
                $("#zp").html(img);
            },
            onUpload:function (file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onFallback : function() {
                alert("该浏览器无法使用!");
            },
        });




      /*  var obj = $("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            fileObjName: 'file',
            fileTypeDesc: "文件上传",
            fileTypeExts: '*.gif; *.jpg; *.png;*.jpeg;*.bmp',
            method: 'post',
            multi: false, // 是否能选择多个文件
            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout: 1,
            queueSizeLimit: 1,
            fileSizeLimit: 4 * 1024,
            buttonText: "上传封面",
            requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
            height: 20,
            width: 70,
            onUploadSuccess: function (file, data, response) {
                var $jsonObj = eval("(" + data + ")");

                var img = '<div class="img_1"><a data-id="'
                    + $jsonObj.uuid
                    + '" onclick="reMove(this);"> 取消 </a><img style="width:233px;height:130px;" class="ims" onclick="Change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                $("#entityId").val($jsonObj.uuid);

                layer.msg('上传成功');
                $("#zp").html(img);
            },
            onUploadStart: function (file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onUploadError: function (file, errorCode, errorMsg,
                                     errorString) {
                layer.msg('上传成功');
            }
        });
*/
    }

    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
        $("#entityId").val("");
        $("#zp").append('<div class="img_1"><img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);" style="width: 233px; height: 130px;"></div>');
    }

    //获取文件的缩略图路径
    function getFileUrls() {
        var imgs = $(".img_1");
        var urls = "";
        $.each(imgs, function (index, value) {
            urls += ($(value).find("img").attr("src") + ",");
        });
        if (urls != "") {
            urls = urls.substring(0, urls.length - 1);
        }
        return urls;
    }

    function backToLearnerRecord() {
        window.location.href = encodeURI("${ctp}/wkx/microbanner/index");
    }

</script>
</html>