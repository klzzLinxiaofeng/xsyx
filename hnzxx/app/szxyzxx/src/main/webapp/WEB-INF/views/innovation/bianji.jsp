<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>新增实践创新</title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
    <style>
        div#zpa div, div#zp div{
            display: inline-block;
        }
        .controls table {
            width:500px;
            background-color: white;
            text-align: center;
        }
        .controls table tr {
            height: 30px;
        }

        #zpa {
            display: inline-block;
        }

        .control-group div span,
        div.uploadifive-button {
            margin-left: 200px;
        }

    </style>
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_time_form" action="javascript:void(0);">
                    <div class="control-group">
                        <label class="control-label">
                            班级节假日课程：
                        </label>
                        <div class="controls">
                            <input  type="text" id="teamJiaDay" name="teamJiaDay" class="span4" autocomplete="off">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            社团评分：
                        </label>
                        <div class="controls">
                            <input   type="text" id="score" name="score" class="span4" autocomplete="off">分
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            学习或比赛图片：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="pctreId" name="pctreId">
                            </div>
                            <div id="zpa" style="display:inline-block;">

                            </div>

                        </div>
                        <div><span>支持jpg、gif、png、bmp格式，宽高3:4</span></div>
                        <c:if test="${isCK==''||isCK==null}">
                            <input type="hidden" id="uploader" name="uploader">
                        </c:if>
                        <span id="tp_queue"></span>
                        <div class="clear"></div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            个人奖状：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="jiangzhuanId" name="jiangzhuanId" >
                            </div>
                            <div id="zp" style="display:inline-block;">
                            </div>
                            <div><span>支持jpg、gif、png、bmp格式，宽高3:4</span></div>
                            <c:if test="${isCK==''||isCK==null}">
                                <input type="hidden" id="uploader2" name="uploader2">
                            </c:if>
                            <span id="tp_queue"></span>

                            <div class="clear"></div>
                        </div>

                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            科创相关图书借阅：
                        </label>
                        <div class="controls">
                            <input disabled="disabled" type="number" id="bookNumer" name="bookNumer" class="span4" autocomplete="off">本
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            学习评语：
                        </label>
                        <div class="controls">
                            <input  type="text" id="pingyu" name="pingyu" class="span4" autocomplete="off">
                        </div>
                    </div>


                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="studentId" name="studentId" value="${studentId}"/>
                        <input type="hidden" id="studentName" name="studentName" value="${studentName}"/>
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
        uploadFile2();
    });

    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
        var id=obj.getAttribute("id");
        var uuid= $("#pctreId").val();
        var str=uuid.split(",");
        var newuuid=[];
        var a=0;
        for(var i=0;i<str.length;i++){
            if(id!=str[i]){
                newuuid[a]=str[i];
                a++;
            }
        }
        $("#pctreId").val(newuuid);
    }
    function reMove2(obj) {
        $(obj).parent().remove();
        var id=obj.getAttribute("id");
        var uuid= $("#jiangzhuanId").val();
        var str=uuid.split(",");
        var newuuid=[];
        var a=0;
        for(var i=0;i<str.length;i++){
            if(id!=str[i]){
                newuuid[a]=str[i];
                a++;
            }
        }
        $("#jiangzhuanId").val(newuuid);
    }


    function uploadFile() {
        $('#uploader').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传学习或比赛图片',
            removeCompleted:true,
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"  style="margin: 5px;"><a id="'
                    + $jsonObj.uuid
                    + '" onclick="reMove(this);">取消</a><img style="width:233px;height:130px;" class="ims" onclick="change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                var uuid= $("#pctreId").val();
                if(uuid==null || uuid=="" || uuid=="undefined"){
                    $("#pctreId").val($jsonObj.uuid);
                }else{
                    var tinajiaId=$jsonObj.uuid+","+uuid;
                    $("#pctreId").val(tinajiaId);
                }
                $("#zpa").append(img);
            },
            onUpload:function (file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onFallback : function() {
                alert("该浏览器无法使用!");
            },
        });
    }
    function uploadFile2() {
        $('#uploader2').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传奖状',
            /*'fileType':'*.gif, *.jpg, *.png,*.jpeg,*.bmp',*/
            'removeCompleted':true,
            'formData': {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"  style="margin: 5px;"><a id="'
                    + $jsonObj.uuid
                    + '" onclick="reMove2(this);">取消</a><img style="width:233px;height:130px;" class="ims" onclick="change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                var uuid= $("#jiangzhuanId").val();
                if(uuid==null || uuid=="" || uuid=="undefined"){
                    $("#jiangzhuanId").val($jsonObj.uuid);
                }else{
                    var tinajiaId=$jsonObj.uuid+","+uuid;
                    $("#jiangzhuanId").val(tinajiaId);
                }
                $("#zp").append(img);
            },
            onUpload:function (file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onFallback : function() {
                alert("该浏览器无法使用!");
            },
        });
    }

    //保存或更新修改
    function saveOrUpdate() {
        var loader = new loadLayer();
        var $requestData = formData2JSONObj("#publicClass_time_form");
        url = "${ctp}/practice/innovation/inputCreate";
        loader.show();
        $.post(url, $requestData, function (data, status) {
            if (status=="success") {
                if ("success" === data) {
                    $.success('操作成功');
                    parent.$("iframe").each(function () {
                        $(this).attr('src', $(this).attr('src'));//需要引用jquery
                    })
                    $.closeWindow();
                } else {
                    $.error("操作失败");
                }
            }
            loader.close();
        });
    }

</script>
</html>