<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <%@ include file="/views/embedded/plugin/uploadify.jsp" %>
    <%@ include file="/views/embedded/plugin/avatar_js.jsp" %>

    <link type="text/css" href="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.css"
          rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/extra/micro/font-awesome-ie7.min.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/webuploader.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/xyuploader.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/js/common/plugin/webuploader/md5_md5blob.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
    <style>
        .row-fluid .span13 {
            width: 75%;
        }

        .row-fluid .span4 {
            width: 227px;
        }

        .myerror {
            color: red !important;
            width: 30%;
            display: inline-block;
            padding-left: 10px;
        }

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

        .up_img {
            background: url("${pageContext.request.contextPath}/res/css/extra/images/up_img.jpg") no-repeat;
            display: block;
            height: 30px;
            width: 98px;
            position: absolute;
            bottom: 10px;
            left: 30px;
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


        .form-horizontal .conss {
            width: 65%;
            display: inline-flex;
        }

        .form-horizontal .conss3 {
            width: 65%;
            display: inline-flex;
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
                            名称：
                        </label>
                        <div class="controls">
                            <input type="text" id="name" name="name" class="span4" placeholder=""
                                   value="${publicClass.name}" style="width: 70%">
                        </div>
                    </div>

                    <div class="control-group conss">
                        <label class="control-label"><font style="color: red">*</font>
                            展示文件：
                        </label>

                        <div class="controls update_img">
                            <c:choose>
                                <c:when test="${not empty publicClass.uuid }">
                                    <p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'>

                                        <a target="_blank" id="a"
                                           href='<entity:getHttpUrl uuid="${publicClass.uuid}"/>'>${entity.fileName}</a>
                                        <button id="b" onclick="deleteFile();"
                                                class="btn btn-red" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                                            删除
                                        </button>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'>
                                        <a taget="_blank" id="a"></a>
                                    </p>
                                    <c:choose>
                                        <c:when test="${isCK != null && isCk != '' }"></c:when>
                                        <c:otherwise><input type="hidden" id="uploader" name="uploader"/></c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" id="uuid" name="uuid" value="${publicClass.uuid }"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" style="width: 300px"><font style="color: red">*</font>
                            支持图片、GIF格式文件、视频控制在4S 尺寸比例：
                        </label>

                    </div>


                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            状态：
                        </label>
                        <div class="controls">
                            <input type="radio"
                                   <c:if test="${publicClass.status== 0}">checked="checked"</c:if> name="status"
                                   id="male" value="0" checked/>
                            <label for="male" style="margin-left: 5px">启用</label>

                            <input type="radio"
                                   <c:if test="${publicClass.status== 1}">checked="checked"</c:if> name="status"
                                   id="male2" value="1"/>
                            <label for="male2" style="margin-left: 5px">不启用</label>
                        </div>
                    </div>

                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${publicClass.id}"/>
                        <input type="hidden" id="resourceType" name="resourceType" value="${publicClass.resourceType}"/>
                        <button class="btn btn-warning" type="button"
                                onclick="saveOrUpdate();">确定
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    var checker;

    $(function () {
        uploadFile();
        checker = initValidator();
        $.createMemberSelector({
            "inputIdSelector": "#teacherId",
            "isOnTopWindow": true,
            "enableBatch": false
        });
    });

    function deleteFile() {
        $.confirm("确定执行此次操作？", function () {
            executeDel();
        });
    }

    function executeDel() {
        $("#a").text("");
        $("#a").attr('href', "");
        $("#uuid").val("");
        $("#b").remove();
        $(".update_img").children().append("<input type='file' id='uploader' name='uploader'/>")
        uploadFile();
    }

    function uploadFile() {

        var obj = setTimeout(function () {
            $("#uploader").uploadify({
                swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
                uploader: '${pageContext.request.contextPath}/uploader/common',
                formData: {'jsessionId': '<%=request.getSession().getId()%>'},
                fileObjName: 'file',
                fileTypeDesc: "文件上传",
                fileTypeExts: "*.mp4; *.gif; *.jpg; *.png;*.jpeg;*.bmp", //默认*.*
                method: 'post',
                multi: false, // 是否能选择多个文件
                auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
                removeTimeout: 1,
                queueSizeLimit: 1,
                fileSizeLimit: 4 * 1024,
                buttonText: "上传",
                requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
                height: 20,
                width: 70,
                onUploadSuccess: function (file, data, response) {
                    var $jsonObj = eval("(" + data + ")");
                    var suffix = $jsonObj.suffix;
                    $("#resourceType").val(suffix);
                    $("#uuid").val($jsonObj.uuid);
                    $("#a").text($jsonObj.realFileName);
                    $("#a").attr('href', $jsonObj.url);
                    $("#a").attr('target', '_blank');

                },
                onUploadStart: function (file) { //上传开始时触发（每个文件触发一次）
                    $("#infoBox").prev("p").css("display", "none");
                    $("#infoBox").css("display", "block");
                },
                onUploadError: function (file, errorCode, errorMsg, errorString) {
                    $.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
                }
            })
        }, 10);
    }
    function initValidator() {
        return $("#publicClass_form").validate({
            errorClass: "myerror",
            rules: {
                "name": {
                    required: true,
                    maxlength: 50,
                    isContainsSpecialChar: true
                },
            },
            messages: {}
        });
    }

    //保存或更新修改
    function saveOrUpdate() {
        var status = $('input[name="status"]:checked').val();
        console.log(status);
        if (status != null && status == 0){
            $.confirm("确定执行此次操作？将会修改其他已启用状态！", function () {
                save();
            });
        } else {
            save();
        }

    }

    function save(){
        if (checker.form()) {
            var loader = new loadLayer();
            var $id = $("#id").val();
            var $requestData = formData2JSONObj("#publicClass_form");
            var url = "${ctp}/start/page/creator";
            if ("" != $id) {
                url = "${ctp}/start/page/update";
            }
            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    $.success('操作成功');
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        if (parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else {
                        $.error("操作失败");
                    }
                } else {
                    $.error("操作失败");
                }
                loader.close();
            });
        }
    }

</script>
</html>