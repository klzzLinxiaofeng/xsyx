<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity" %>
<html>
<head>
    <title></title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%-- 本地注释，线上需要 --%>
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">

    <%@ include file="/views/embedded/common.jsp" %>
    <%@ include file="/views/embedded/plugin/zTree.jsp" %>
    <%@ include file="/views/embedded/plugin/avatar_js.jsp" %>
    <%--<%@ include file="/views/embedded/plugin/uploadify.jsp" %>--%>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>

    <%@include file="/views/embedded/plugin/grade_selector_js.jsp" %>
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
                            类别：
                        </label>
                        <div class="controls">
                            <select name="type" id="type">
                                <option value=""> 请选择</option> <!-- 这个option也可以写在ajax中-->
                            </select>
                        </div>
                    </div>

                    <%--<div class="select_b">
                        <label class="control-label"><font style="color: red">*</font>
                            学年：
                        </label>
                        <div class="controls">
                            <select name="yearId" id="yearId">
                                <option value=""> 请选择</option> <!-- 这个option也可以写在ajax中-->
                            </select>
                        </div>--%>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            年级：
                        </label>
                        <div class="controls">
                            <%--<select name="grade" id="grade">
                                <option value=""> 请选择</option> <!-- 这个option也可以写在ajax中-->
                            </select>--%>
                            <div id="grade"></div>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            标题：
                        </label>
                        <div class="controls">
                            <input type="text" id="title" name="title" class="span4" placeholder=""
                                   value="${publicClass.title}" style="width: 70%">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            简介：
                        </label>
                        <div class="controls" style="margin-top: 5px">
                            <input type="text" id="directory" name="directory" class="span4" placeholder=""
                                   value="${publicClass.directory}" style="width: 70%">
                        </div>
                    </div>
                    <div class="control-group">
                        <label><font style="color: red;font-size: 14px;padding-left: 5%;">*
                            封面和视频都尽量保持宽高16:9的比例</font>
                        </label>
                    </div>
                    <div class="control-group conss">
                        <label class="control-label"><font style="color: red">*</font>
                            封面：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="coverUuid" name="coverUuid" value="${publicClass.coverUuid}">
                            </div>
                            <div id="zp" style="display:inline-block;">
                                <div class="img_1">
                                    <c:if test="${(isCK==''||isCK==null) && (publicClass.coverUrl !=null && publicClass.coverUrl != '') }">
                                        <a data-id="${publicClass.coverUrl}" onclick="reMove(this);">取消</a>
                                    </c:if>
                                    <c:if test="${publicClass.coverUrl !=null && publicClass.coverUrl != ''}">
                                        <img src="${publicClass.coverUrl}" onclick="Change(this);"
                                             style="width: 260px; height: 130px;">
                                    </c:if>
                                    <c:if test="${publicClass.coverUrl ==null || publicClass.coverUrl == ''}">
                                        <img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);"
                                             style="width: 233px; height: 130px;">
                                    </c:if>
                                </div>
                            </div>

                            <c:if test="${isCK==''||isCK==null}">
                                <input type="hidden" id="uploaderImage" name="uploaderImage">
                            </c:if>
                            <span id="tp_queue"></span>

                            <div class="clear"></div>
                        </div>


                    </div>


                    <div class="control-group conss">
                        <label class="control-label"><font style="color: red">*</font>
                            课件上传：
                        </label>

                        <div class="controls update_img">
                            <c:choose>
                                <c:when test="${not empty publicClass.classUuid }">
                                    <p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'>
                                        <a target="_blank" id="a"
                                           href='<entity:getHttpUrl uuid="${publicClass.classUuid}"/>'>${entity2.fileName}</a>
                                        <button id="b" onclick="deleteFile();"
                                                class="btn btn-red" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                                            删除
                                        </button>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'><a
                                            taget="_blank" id="a"></a></p>
                                    <c:choose>
                                        <c:when test="${isCK != null && isCk != '' }"></c:when>
                                        <c:otherwise><input type="hidden" id="uploader" name="uploader"/></c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" id="classUuid" name="classUuid" value="${publicClass.classUuid }"/>
                        </div>
                    </div>

                    <div class="control-group conss3">
                        <label class="control-label"><font style="color: red">*</font>
                            视频上传：
                        </label>

                        <div class="controls update_img3">
                            <c:choose>
                                <c:when test="${not empty publicClass.videoUuid }">
                                    <p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'>
                                        <a target="_blank" id="a3"
                                           href='<entity:getHttpUrl uuid="${publicClass.videoUuid}"/>'>${entity3.fileName}</a>
                                        <button id="b3" onclick="deleteFile2();"
                                                class="btn btn-red" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
                                            删除
                                        </button>
                                    </p>
                                </c:when>
                                <c:otherwise>
                                    <p style='display:inline-block;margin-bottom:0;width:240px;overflow:hidden;'><a
                                            taget="_blank" id="a3"></a></p>
                                    <c:choose>
                                        <c:when test="${isCK != null && isCk != '' }"></c:when>
                                        <c:otherwise><input type="hidden" id="uploader3"
                                                            name="uploader3"/></c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" id="videoUuid" name="videoUuid" value="${publicClass.videoUuid }"/>
                        </div>
                    </div>

                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${publicClass.id}"/>
                        <button class="btn btn-warning" type="button"
                                onclick="saveOrUpdate();">确定
                        </button>
                    </div>

            </div>
                </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    var checker;
    var catalogEnd = 0;
    $(function () {
        initSelect();
    });

    function deleteFile() {
        $.confirm("确定执行此次操作？", function () {
            executeDel();
        });
    }

    function deleteFile2() {
        $.confirm("确定执行此次操作？", function () {
            executeDel2();
        });
    }

    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
        $("#coverUuid").val("");
        $("#zp").append('<div class="img_1"><img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);" style="width: 233px; height: 130px;"></div>');
    }


    function executeDel2() {
        $("#a3").text("");
        $("#a3").attr('href', "");
        $("#videoUuid").val("");
        $("#b3").remove();
        $(".update_img3").children().append("<input type='file' id='uploader3' name='uploader3'/>")
        uploadVideoFile();
    }

    function executeDel() {
        $("#a").text("");
        $("#a").attr('href', "");
        $("#classUuid").val("");
        $("#b").remove();
        $(".update_img").children().append("<input type='file' id='uploader' name='uploader'/>")
        uploadFile();
    }

        //文件上传
    function uploadFile() {
        setTimeout(function () {
            $('#uploader').uploadifive({
                'auto': true,
                'fileObjName': 'file',
                //'queueID': 'queue',
                'buttonText': '上传课件',
                'removeCompleted': true,
                'height': 20,
                'width': 70,
                'formData': {
                    'jsessionId': '<%=request.getSession().getId()%>'
                },
                'uploadScript': '/uploader/common',
                'onUploadComplete': function (file, data) {
                    var $jsonObj = eval("(" + data + ")");
                    $("#classUuid").val($jsonObj.uuid);
                    $("#a").text($jsonObj.realFileName);
                    $("#a").attr('href', $jsonObj.url);
                    $("#a").attr('target', '_blank');
                    $.success("上传成功!", 9);
                },
                onUpload: function (file) { //上传开始时触发（每个文件触发一次）
                    $("#infoBox").prev("p").css("display", "none");
                    $("#infoBox").css("display", "block");
                },
                onFallback: function () {
                    alert("该浏览器无法使用!");
                },
            });
        },10)

    /*
        var obj = setTimeout(function () {
            $("#uploader").uploadify({
                swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
                uploader: '${pageContext.request.contextPath}/uploader/common',
                formData: {'jsessionId': '<%=request.getSession().getId()%>'},
                fileObjName: 'file',
                fileTypeDesc: "文件上传",
                fileTypeExts: "*.doc; *.docx; *.xls; *.xlsx; *.ppt; *.pptx", //默认*.*
                method: 'post',
                multi: false, // 是否能选择多个文件
                auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
                removeTimeout: 1,
                queueSizeLimit: 1,
                fileSizeLimit: 50 * 1024,
                buttonText: "上传课件",
                requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
                height: 20,
                width: 70,
                onUploadSuccess: function (file, data, response) {
                    var $jsonObj = eval("(" + data + ")");
                    $("#classUuid").val($jsonObj.uuid);
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
        }, 10);*/
    }

    function uploadVideoFile() {
        setTimeout(function () {
            $('#uploader3').uploadifive({
                'auto': true,
                'fileObjName': 'file',
                //'queueID': 'queue',
                'buttonText': '上传视频',
                'removeCompleted': true,
                'height': 20,
                'width': 70,
                'formData': {
                    'jsessionId': '<%=request.getSession().getId()%>'
                },
                'uploadScript': '/uploader/common',
                'onUploadComplete': function (file, data) {
                    var $jsonObj = eval("(" + data + ")");
                    $("#videoUuid").val($jsonObj.uuid);
                    $("#a3").text($jsonObj.realFileName);
                    $("#a3").attr('href', $jsonObj.url);
                    $("#a3").attr('target', '_blank');
                },
                onUpload: function (file) { //上传开始时触发（每个文件触发一次）
                    $("#infoBox").prev("p").css("display", "none");
                    $("#infoBox").css("display", "block");
                },
                onFallback: function () {
                    alert("该浏览器无法使用!");
                },
            });
        }, 10)



    /*    var obj = setTimeout(function () {
            $("#uploader3").uploadify({
                swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
                uploader: '${pageContext.request.contextPath}/uploader/common',
                formData: {'jsessionId': '<%=request.getSession().getId()%>'},
                fileObjName: 'file',
                fileTypeDesc: "文件上传",
                fileTypeExts: "*.mp4", //默认*.*
                method: 'post',
                multi: false, // 是否能选择多个文件
                auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
                removeTimeout: 1,
                queueSizeLimit: 1,
                fileSizeLimit: 500 * 1024,
                buttonText: "上传视频",
                requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
                height: 20,
                width: 70,
                onUploadSuccess: function (file, data, response) {
                    var $jsonObj = eval("(" + data + ")");
                    $("#videoUuid").val($jsonObj.uuid);
                    $("#a3").text($jsonObj.realFileName);
                    $("#a3").attr('href', $jsonObj.url);
                    $("#a3").attr('target', '_blank');

                },
                onUploadStart: function (file) { //上传开始时触发（每个文件触发一次）
                    $("#infoBox").prev("p").css("display", "none");
                    $("#infoBox").css("display", "block");
                },
                onUploadError: function (file, errorCode, errorMsg, errorString) {
                    $.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
                }
            })
        }, 10);*/
    }

    function uploadImageFile() {
        $('#uploaderImage').uploadifive({
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
                    + '" onclick="reMove(this);">取消</a><img style="width:233px;height:130px;" class="ims" onclick="Change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                $("#coverUuid").val($jsonObj.uuid);
                $.success("上传成功!", 9);
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




    /*    var obj = $("#uploaderImage").uploadify({
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
                    + '" onclick="reMove(this);">取消</a><img style="width:233px;height:130px;" class="ims" onclick="Change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                $("#coverUuid").val($jsonObj.uuid);
                $.success("上传成功!", 9);
                $("#zp").html(img);
            },
            onUploadStart: function (file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onUploadError: function (file, errorCode, errorMsg,
                                     errorString) {
                $.alert('The file ' + file.name
                    + ' could not be uploaded: '
                    + errorString);
            }
        });
*/
    }


    $(function () {
        uploadFile();
        uploadVideoFile();
        uploadImageFile();
        checker = initValidator();
        $.createMemberSelector({
            "inputIdSelector": "#teacherId",
            "isOnTopWindow": true,
            "enableBatch": false
        });
        // 年级选择器
        $.createGradeSelector({
            "deptContainer": "#grade",
            enableBatch: true,
        });


        // 获取类别
        $.ajax({
            async: false,
            type: "get",
            url: "${ctp}/micro/management/type",
            dataType: "json",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#type").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
                }
                var typeId = '${publicClass.typeId}';
                if (typeId != null && typeId != '' && typeId != undefined) {
                    $("#type option[value='" + typeId + "']").attr("selected", true);
                }
            }
        });
    });

    function initValidator() {
        return $("#publicClass_form").validate({
            errorClass: "myerror",
            rules: {
                "title": {
                    required: true,
                    maxlength: 100,
// 					stringCheck : true
//                    isContainsSpecialChar: true
                },
                "directory": {
                    required: true,
                    maxlength: 100,
// 					stringCheck : true
//                    isContainsSpecialChar: true
                },
                "coverUuid": {
                    required: true,
                },
                "classUuid": {
                    required: true,
                },
                "videoUuid": {
                    required: true,
                },
            },
            messages: {
                "coverUuid": "请上传封面",
                "classUuid": "请上传课件",
                "videoUuid": "请上传视频"
            }
        });
    }

    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            var typeId = $("#type option:selected").val();
            if (typeId == null || typeId == '' || typeId == undefined) {
                $.alert("请选择类别");
                return;
            }
            var gradeIds = $("#grade").attr("data-id");

            if (typeId != '1' && (gradeIds == null || gradeIds == '' || gradeIds == undefined || gradeIds == '0')) {
                $.alert("请选择年级");
                return;
            }
            var split = gradeIds.split(",");
            console.log(split.length);
            if (typeId != 1 && split != null && split.length > 1) {
                $.alert("当前课程类别暂不支持多选！");
                return;

            }


            var loader = new loadLayer();
            var $id = $("#id").val();
            var $requestData = formData2JSONObj("#publicClass_form");

            $requestData.gradeIds = gradeIds;
            $requestData.typeId = typeId;
            var url = "${ctp}/micro/management/creator";
            if ("" != $id) {
                url = "${ctp}/micro/management/update";
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


    function initSelect() {
        //初始填充
        addOption('/teach/schoolYear/list/json', "yearId", "id", "name")
         /*$("#grade").change(function() {
             alert("asd"+$("#grade").val())
         })*/
       /* addOption('/micro/management/getGradel',"grade","id","name")*/
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
</html>