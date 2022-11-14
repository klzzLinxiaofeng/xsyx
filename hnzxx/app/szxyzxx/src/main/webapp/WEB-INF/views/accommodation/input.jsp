<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2022/1/7
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>

<head>
    <title>住房登记</title>
    <%@ include file="/views/embedded/common.jsp" %>
    <%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
    <%@ include file="/views/embedded/plugin/zTree.jsp"%>
    <%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <style type="text/css">
        .form-horizontal .controls #zpa .img_1 {
            float: left;
            margin: 10px;
            position: relative;
            top: 0;
            width: 233px;
            height: 130px;
        }
        .form-horizontal .controls #zpa .img_1 img {
            width: 233px;
            height: 130px;
        }
        .form-horizontal .controls #zpa .img_1 a {
            position: absolute;
            font-size: 22px;
            font-weight: bold;
            color: #000;
            right: 0px;
            top: 0px;
            display: block;
            width: 16px;
            height: 16px;
            line-height: 16px;
            text-align: center;
            cursor: pointer;
        }
        .form-horizontal .controls{
            margin-left: 100px;
        }
        .myerror {
            color: red !important;
            width: 22%;
            display: inline-block;
            padding-left: 10px;
        }
        .table tr th,.table tr td{text-align:center;}
    </style>
    <style type="text/css">
        #groupList,#groupLists{
            background: #f5f5f8;
            position: fixed;
            text-align:center;
            width: 60%;
            height: 50%;
            box-shadow: 0 3px 4px 0 #00000020, 0 4px 12px #00000020;
            border: 1px solid #d9d9d9;
        }
        .select_div{
            margin:30px;
            margin-bottom:130px;
        }
        .off{
            position:absolute;
            top:5px;
            right:10px;
            color: white;
        }
        /*屏幕中间*/
        #groupList,#groupLists{
            right: 20%;
            top: 20%;
        }
    </style>
    <style type="text/css">
        table {
            /*设置相邻单元格的边框间的距离*/
            border-spacing: 0;
            /*表格设置合并边框模型*/
            border-collapse: collapse;
            text-align: center;
        }
        /*关键设置 tbody出现滚动条*/
        table tbody {
            display: block;
            height: 220px;
            overflow-y: scroll;
        }

        table thead tr,
        tbody tr {
            display: table;
            width: 100%;
            table-layout: fixed;
        }
        /*关键设置：滚动条默认宽度是16px 将thead的宽度减16px*/
        table thead {
            width: calc( 100% - 1em)
        }
        table thead th {
            background: #ccc;
        }

    </style>
    <style type="text/css">
        button.btn.btn-primary {
            height: 30px;
            background: #01316c;
            padding: 0 20px;
            border-radius: 3px;
        }

        div#yixuanStudent {
            overflow: scroll;
        }
        div#yixuanTeacher {
            overflow: scroll;
        }
        div#yixuanStudent::-webkit-scrollbar {
            width: 0;
            height: 0;
        }
        div#yixuanTeacher::-webkit-scrollbar {
            width: 0;
            height: 0;
        }

        .chexiao {
            right: 5px;
            top: 1px;
            position: absolute;
            cursor: pointer;
        }

        table {
            border-spacing: 0;
            border-collapse: collapse;
            text-align: center;
            width: 98%;
            margin-left: 1%;
        }

        table tbody {
            display: block;
            height: 220px;
            overflow: auto;
        }

        table#studentBiao tbody::-webkit-scrollbar {
            width: 0;
            height: 0;
        }
        table#teacherBiao tbody::-webkit-scrollbar {
            width: 0;
            height: 0;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="住房登记" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid ">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3 class="x-head content-top"><a href="javascript:void(0);" class="on">住房登记</a><a href="javascript:history.go(-1);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
                </div>
                <div class="space20"></div>
                <div class="x-main" >
                    <div class="clearfix x-mright">
                        <div class="list">
                            <form class="form-horizontal" id="applyrepair_form">
                                <div class="control-group">
                                    <label class="control-label">
                                        上报人<span style="color:red">*</span>：
                                    </label>
                                    <div class="textarea">
                                        <input type="hidden" id="teacherUserId" name="teacherUserId" value="${userInfo.id}"/>
                                        <input readonly  type="button" id="teacherName"  name="teacherName" style="width:800px;height:100px;" value="${userInfo.realName}"/>
                                    </div>
                                </div>
                                <div class="x-up">
                                    <div class="control-group">
                                        <label class="control-label">
                                            上报时间<span style="color:red">*</span>
                                        </label>
                                        <input type="text" id="shangBaoTime" name="shangBaoTime" class="chzn-select" autocomplete="off"
                                               style="width:200px;padding-top: 4px;"
                                               onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                                               placeholder="xxxx-xx-xx xx:xx:xx">

                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            房室号<span style="color:red">*</span>
                                        </label>
                                        <div>
                                            <input type="text" id="fangshiHao"  name="fangshiHao"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            房屋面积
                                        </label>
                                        <div>
                                            <input type="text" id="area"  name="area"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            冷水度数
                                        </label>
                                        <div>
                                            <input type="text" id="coldWater"  name="coldWater"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            热水度数
                                        </label>
                                        <div>
                                            <input type="text" id="hotWater"  name="hotWater"/>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            用电度数
                                        </label>
                                        <div>
                                            <input type="text" id="electricity"  name="electricity"/>
                                        </div>
                                    </div>
                                    <%--详情    --%>
                                    <div class="control-group">
                                        <label class="control-label">
                                            详情：
                                        </label>
                                        <div class="left">
                                            <div class="textarea">
                                                <textarea style="width:200px;height:100px;padding-top: 4px;"  type="text" id="tontent" name="tontent" class="span4" autocomplete="off"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">
                                            冷水度数图片<span style="color:red">*</span>：
                                        </label>
                                        <div class="controls">
                                            <div id="coldPictureUUid" style="display:inline-block;">

                                            </div>
                                            <div><span>支持jpg、gif、png、bmp格式，宽高3:4</span></div>
                                            <c:if test="${isCK==''||isCK==null}">
                                                <input type="hidden" id="coldUploader" name="uploader3">
                                            </c:if>
                                           <div class="clear"></div>
                                        </div>
                                    </div>
                                    <%--热水表图片--%>
                                    <div class="control-group">
                                        <label class="control-label">
                                            热水度数图片<span style="color:red">*</span>：
                                        </label>
                                        <div class="controls">
                                            <div id="hotPictureUUid" style="display:inline-block;">

                                            </div>
                                            <div><span>支持jpg、gif、png、bmp格式，宽高3:4</span></div>
                                            <c:if test="${isCK==''||isCK==null}">
                                                <input type="hidden" id="hotUploader" name="uploader3">
                                            </c:if>
                                            <div class="clear"></div>
                                        </div>
                                    </div>
                                    <%--用电度数图片--%>
                                    <div class="control-group">
                                        <label class="control-label">
                                            用电度数图片<span style="color:red">*</span>：
                                        </label>
                                        <div class="controls">
                                            <div id="electricityUUid" style="display:inline-block;">
                                            </div>
                                            <div><span>支持jpg、gif、png、bmp格式</span></div>
                                            <c:if test="${isCK==''||isCK==null}">
                                                <input type="hidden" id="electricityUploader" name="uploader3">
                                            </c:if>

                                            <div class="clear"></div>
                                        </div>
                                    </div>
                                    <%--附件--%>
                                    <div class="control-group">
                                        <label class="control-label">
                                            附件：
                                        </label>
                                        <div class="controls">
                                            <div id="fujianUUID" style="display:inline-block;">

                                            </div>
                                            <input type="hidden" id="fujianUploader" name="uploader2">
                                            <span id="tp_queue"></span>

                                            <div class="clear"></div>
                                        </div>
                                    </div>

                                </div>
                            </form>
                        </div>
                    </div>
                    <p class="tc" style="padding-top:8px;border-top: #ccc 1px solid;"><button class="btn btn-success" type="button" style="margin:0 15px;" onclick="saveOrUpdate();">确定</button>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        hotUploader();
        coldUploader();
        electricityUploader();
        fujianUploader();
    })
    /*
      * 上传热水表
      */
    function hotUploader() {
        $('#hotUploader').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传热水表图片',
            removeCompleted:true,
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"  style="margin: 5px;"><a id="'+$jsonObj.uuid+ '" ' +
                    'onclick="reMove(this);">X</a><input type="hidden" name="reshui" value="'+$jsonObj.uuid+'"/><img style="width:233px;height:130px;" class="ims" onclick="change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                $("#hotPictureUUid").append(img);
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
    /*
   * 上传冷水表
   */
    function coldUploader() {
        $('#coldUploader').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传冷水表图片',
            removeCompleted:true,
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"  style="margin: 5px;"><a id="'+$jsonObj.uuid+ '" ' +
                    'onclick="reMove(this);">X</a><input type="hidden" name="lenshui" value="'+$jsonObj.uuid+'"/><img style="width:233px;height:130px;" class="ims" onclick="change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                $("#coldPictureUUid").append(img);
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
    /*
    * 上传电表图片
    */
    function electricityUploader() {
        $('#electricityUploader').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传电表图片',
            removeCompleted:true,
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div class="img_1"  style="margin: 5px;"><a id="'+$jsonObj.uuid+ '" ' +
                    'onclick="reMove(this);">X</a><input type="hidden" name="dianbiao" value="'+$jsonObj.uuid+'"/><img style="width:233px;height:130px;" class="ims" onclick="change(this);" src="'
                    + $jsonObj.url
                    + '"/>&nbsp;&nbsp;&nbsp;</div>';
                $("#electricityUUid").append(img);
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
    /*
    * 移除电表图片
    */
    function change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
    }
    /*
   *附件上传
   */
    function fujianUploader() {
        $('#fujianUploader').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传房屋住宿附件',
            removeCompleted:true,
            formData: {
                'jsessionId': '<%=request.getSession().getId()%>'
            },
            'uploadScript': '/uploader/common',
            'onUploadComplete': function (file,data) {
                var $jsonObj = eval("(" + data + ")");
                var img = '<div id="div_'+$jsonObj.uuid+'"><input type="text" style="display:none;" name="fujianUuid" class="fujianUuid" value="'+$jsonObj.uuid+'" /><a href="'+$jsonObj.url+'">'+$jsonObj.realFileName+'</a><button onclick="chehui(this)" type="button">撤销</button></div>';
                $("#fujianUUID").append(img);
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
    //附件撤回
    function chehui(id) {
        $(id).parent().remove();
    }
</script>
<script type="text/javascript">
    //保存或更新修改
    function saveOrUpdate() {
        var val={};
        var loader = new loadLayer();
        val.teacherUserId=$("#teacherUserId").val();
        val.teacherName=$("#teacherName").val();
        val.shangBaoTime=$("#shangBaoTime").val();
        val.fangshiHao=$("#fangshiHao").val();
        var area=$("#area").val();
        if(area!=null && area!=""){
            val.area=area;
        }
        val.coldWater=$("#coldWater").val();
        val.hotWater=$("#hotWater").val();
        val.electricity=$("#electricity").val();
        val.tontent=$("#tontent").val();
        var hotPictureUUid = "";
        /*热水图片uuid*/
        $("input[name='reshui']").each(
            function () {
                if (hotPictureUUid != null && hotPictureUUid != '') {
                    hotPictureUUid += "," + $(this).val();
                } else {
                    hotPictureUUid = $(this).val();
                }
            }
        )
        val.hotPictureUUid=hotPictureUUid;
        /*冷水图片uuid*/
        var coldPictureUUid = "";
        $("input[name='lenshui']").each(
            function () {
                if (coldPictureUUid != null && coldPictureUUid != '') {
                    coldPictureUUid += "," + $(this).val();
                } else {
                    coldPictureUUid = $(this).val();
                }
            }
        )
        val.coldPictureUUid=coldPictureUUid;
        /*电表图片uuid*/
        var electricityUUid = "";
        $("input[name='dianbiao']").each(
            function () {
                if (electricityUUid != null && electricityUUid != '') {
                    electricityUUid += "," + $(this).val();
                } else {
                    electricityUUid = $(this).val();
                }
            }
        )
        val.electricityUUid=electricityUUid;
        var pictureId="";
        $("input[name='fujianUuid']").each(
            function() {
                if (pictureId != null && pictureId != '') {
                    pictureId += "," + $(this).val();
                } else {
                    pictureId = $(this).val();
                };
            }
        )
        if(pictureId!=null && pictureId!=""){
            val.fujianUUID=pictureId;
        }
        var panduans=panduan(val);
        if(panduans=='true'){
            var url="/accomm/zhusu/addAccommdation";

            loader.show();
            $.post(url, val, function (data, status) {
                if (status=="success") {
                    if ("success" === data) {
                        $.success('操作成功');
                        window.location.href="${ctp}/accomm/zhusu/findByAll?sub=asd";
                    } else {
                        $.error("操作失败");
                    }
                }
                loader.close();
            });
        }else{
            $.error(panduans);
        }

    }
    function panduan(val) {
        if(val.teacherUserId==null || val.teacherUserId==""){
            return "上报人id不能为空";
        }
        if(val.teacherName==null || val.teacherName==""){
            return "上报人姓名不能为空";
        }
        if(val.shangBaoTime==null || val.shangBaoTime==""){
            return "上报时间不能为空";
        }
        if(val.fangshiHao==null || val.fangshiHao==""){
            return "房室号不能为空";
        }
        return "true";
    }
</script>
</body>
</html>
