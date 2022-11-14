<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2022/11/1
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>添加美术作品</title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>

    <style>
        div#zpi div, div#zp div,div#zpo div{
            display: inline-block;
        }
    </style>
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_time_form" action="javascript:void(0);">
                  <%--  <div class="control-group">
                        <label class="control-label">
                            学生姓名：
                        </label>
                        <div class="controls">
                            <input type="hidden" value="${aestheticPojo.studentId}" id="studentId" name="studentId"/>
                            <input value="${aestheticPojo.studentName}" type="text" id="studentName" name="studentName" class="span4" autocomplete="off">
                        </div>
                    </div>--%>
                    <div class="control-group">
                        <label class="control-label">
                            个人优秀美术作品：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="fineArtId" name="fineArtId" value="${aestheticPojo.fineArtId}">
                            </div>
                            <div id="zp" style="display:inline-block;">
                                <c:forEach items="${aestheticPojo.fineArtPicter}" var="item" varStatus="i">
                                    <div class="img_1" style="margin: 5px;">
                                        <a id='${item.key}'  onclick="reMove(this);">取消</a>
                                        <img style="width:233px;height:130px;" class="ims" onclick="change(this);" src='${item.value}'/>
                                    </div>

                                </c:forEach>
                            </div>
                            <div><span>支持jpg、gif、png、bmp格式，宽高3:4</span></div>
                            <c:if test="${isCK==''||isCK==null}">
                                <input type="hidden" id="uploader3" name="uploader3">
                            </c:if>
                            <span id="tp_queue"></span>

                            <div class="clear"></div>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            作品点评：
                        </label>
                        <div class="controls">
                            <input value="${aestheticPojo.review}" type="text" id="review" name="review" class="span4" autocomplete="off">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            作品评分：
                        </label>
                        <div class="controls">
                            <input value="${aestheticPojo.reviewSore}" type="text" id="reviewSore" name="reviewSore" class="span4" autocomplete="off">分
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            学习或比赛图片：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="gameWorksId" name="gameWorksId" value="${aestheticPojo.gameWorksId}">
                            </div>
                            <div id="zpi" style="display:inline-block;">
                                <c:forEach items="${aestheticPojo.gameWorksPicter}" var="item" varStatus="i">
                                    <div class="img_1" style="margin: 5px;">
                                        <a id='${item.key}'  onclick="reMove1(this);">取消</a>
                                        <img style="width:233px;height:130px;" class="ims" onclick="change(this);" src='${item.value}'/>
                                    </div>

                                </c:forEach>
                            </div>


                            <%--  <div id="zpi" style="display:inline-block;">
                                  <div class="img_1">
                                      <c:if test="${(isCK==''||isCK==null) && (aestheticPojo.gameWorksUrl !=null && aestheticPojo.gameWorksUrl != '') }">
                                          <a data-id="${aestheticPojo.gameWorksUrl}" onclick="reMove1(this);">取消</a>
                                      </c:if>
                                      <c:if test="${aestheticPojo.gameWorksUrl !=null && aestheticPojo.gameWorksUrl != ''}">
                                          <img src="${aestheticPojo.gameWorksUrl}" onclick="change(this);"
                                               style="width: 260px; height: 130px;">
                                      </c:if>
                                      <c:if test="${aestheticPojo.gameWorksUrl ==null || aestheticPojo.gameWorksUrl == ''}">
                                          <img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);"
                                               style="width: 233px; height: 130px;">
                                      </c:if>
                                  </div>
                              </div>--%>
                            <div><span>支持jpg、gif、png、bmp格式，宽高3:4</span></div>
                            <c:if test="${isCK==''||isCK==null}">
                                <input type="hidden" id="uploader" name="uploader">
                            </c:if>
                            <span id="tp_queue"></span>

                            <div class="clear"></div>
                        </div>

                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            个人奖状：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="jiangzhuanId" name="jiangzhuanId" value="${aestheticPojo.jiangzhuanId}">
                            </div>
                            <div id="zpo" style="display:inline-block;">
                                <c:forEach items="${aestheticPojo.jiangzhuanPicter}" var="item" varStatus="i">
                                    <div class="img_1" style="margin: 5px;">
                                        <a id='${item.key}'  onclick="reMove2(this);">取消</a>
                                        <img style="width:233px;height:130px;" class="ims" onclick="change(this);" src='${item.value}'/>
                                    </div>

                                </c:forEach>
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
                            艺术相关图书借阅：
                        </label>
                        <div class="controls">
                            <input value="${aestheticPojo.bookNumber}" disabled="disabled" type="text" id="bookNumber" name="bookNumber" class="span4" autocomplete="off">本
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            学习评语：
                        </label>
                        <div class="controls">
                            <input value="${aestheticPojo.comments}" type="text" id="comments" name="comments" class="span4" autocomplete="off">
                        </div>
                    </div>


                    <div class="form-actions tan_bottom">
                        <input type="hidden" value="${studentId}" id="studentId" name="studentId"/>
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
    //操作成功提示标签
    function a(msg) {
        var topLayer = top != null ? top.layer : layer;
        topLayer.msg(msg, {icon: 1}, 1);
    }
    var checker;
    $(function () {
        uploadFile();
        uploadFile2();
        uploadFile3();
    });

    function change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
        var id=obj.getAttribute("id");
        var uuid= $("#fineArtId").val();
        var str=uuid.split(",");
        var newuuid=[];
        var a=0;
        for(var i=0;i<str.length;i++){
            if(id!=str[i]){
                newuuid[a]=str[i];
                a++;
            }
        }
        $("#fineArtId").val(newuuid);
    }
    function reMove1(obj) {
        $(obj).parent().remove();
        var id=obj.getAttribute("id");
        var uuid= $("#gameWorksId").val();
        var str=uuid.split(",");
        var newuuid=[];
        var a=0;
        for(var i=0;i<str.length;i++){
            if(id!=str[i]){
                newuuid[a]=str[i];
                a++;
            }
        }
        $("#gameWorksId").val(newuuid);

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
            'buttonText': '上传比赛作品',
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
                var uuid= $("#gameWorksId").val();
                if(uuid==null || uuid=="" || uuid=="undefined"){
                    $("#gameWorksId").val($jsonObj.uuid);
                }else{
                    var tinajiaId=$jsonObj.uuid+","+uuid;
                    $("#gameWorksId").val(tinajiaId);
                }
                $("#zpi").append(img);
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
            removeCompleted:true,
            formData: {
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
                $("#zpo").append(img);
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
    function uploadFile3() {
        $('#uploader3').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传优秀作品',
            removeCompleted:true,
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"  style="margin: 5px;"><a id="'
                    + $jsonObj.uuid
                    + '" onclick="reMove1(this);">取消</a><img style="width:233px;height:130px;" class="ims" onclick="change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                var uuid= $("#fineArtId").val();
                if(uuid==null || uuid=="" || uuid=="undefined"){
                    $("#fineArtId").val($jsonObj.uuid);
                }else{
                    var tinajiaId=$jsonObj.uuid+","+uuid;
                    $("#fineArtId").val(tinajiaId);
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
        var $id = $("#id").val();
        var $requestData = formData2JSONObj("#publicClass_time_form");
        url = "${ctp}/aesthetic/inputCreate";
        loader.show();
        $.post(url, $requestData, function (data, status) {
            if (status=="success") {
                if ("success" === data) {
                    parent.$("iframe").each(function () {
                        $(this).attr('src', $(this).attr('src'));//需要引用jquery
                    })
                    $.success('操作成功');
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
