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
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_time_form" action="javascript:void(0);">
                    <div class="control-group">
                        <label class="control-label">
                            学年：
                        </label>
                        <div class="controls">
                            <select id="xn" name="gradeId" class="span4"
                                    style="width:200px;" value="${homeWoke.gradeId}">
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            学期：
                        </label>
                        <div class="controls">
                            <select id="xq" name="gradeId" class="span4"
                                    style="width:200px;">
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            年级：
                        </label>
                        <div class="controls">
                            <select id="nj" name="gradeId" class="span4"
                                    style="width:200px;">
                                <option>请选择</option>
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            班级：
                        </label>
                        <div class="controls">
                            <select id="bj" name="teamId" class="span4"
                                    style="width:200px;">
                                <option>请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            科目：
                        </label>
                        <div class="controls">
                            <select id="km" name="subjectId" class="span4"
                                    style="width:200px;">
                                <option>请选择</option>
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            是否家庭作业：
                        </label>
                        <div class="controls">
                            <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                <input type="radio" name="isHome"
                                       value="1" Checked >
                                是 </label>
                            <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                <input type="radio" name="isHome" value="0"
                                         >
                                否 </label>

                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            作业内容：
                        </label>
                        <div class="controls">
                            <%--<div id="text" name="text" class="span4" contenteditable="true" style="background-color:#fff;">${homeWoke.text}</div>
                        --%>    <textarea  type="text" id="text" name="text" class="span4" autocomplete="off">${homeWoke.text}</textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            作业图片：
                        </label>
                        <div class="controls">
                            <div>
                                <input type="hidden" id="pictureUuid" name="pictureUuid" value="${homeWoke.pictureUuid}">
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


                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${homeWoke.id}"/>
                        <button class="btn btn-warning" type="button"
                                onclick="saveOrUpdate();">确定
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        uploadFile();
        initSelect();
    });
    function initSelect() {
        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                addOption2('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name")
            }
        })
    }
    //绑定下拉框改变事件
    $("#xn").change(function(){
        $("#xq").html('');
        $("#nj").html('<option value="">请选择</option>');
        $("#bj").html('<option value="">请选择</option>');
        addOption2('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name");
        //添加年级
        addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
    })
    $("#nj").change(function(){
        $("#bj").html('<option value="">请选择</option>');
        $("#km").html('<option value="">请选择</option>');
        //添加班级
        if($(this).val()!="") {
            //添加班级
            addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name")
        }
    })
    $("#bj").change(function(){
        $("#km").html('<option value="">请选择</option>');
        //添加kemu
            //添加科目
            addOption("/literacy/findExamSubject?nj="+$("#nj").val(), "km", "subjectId", "subjectName")
    })


    function addOption(url, id, valProperty, namePropety, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                $("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }

    function addOption2(url, id, valProperty, namePropety, callback) {
        var defaultTerm = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                if(defaultTerm==obj[valProperty]){
                    $("#" + id).append("<option selected=selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }else{
                    $("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }
            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }


    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
        var id=obj.getAttribute("id");
        $("#pictureUuid").val("");
    }

    function uploadFile() {
        $('#uploader').uploadifive({
            'auto': true,
            'fileObjName' : 'file',
            //'queueID': 'queue',
            'buttonText': '上传作业图片',
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
                    $("#pictureUuid").val($jsonObj.uuid);

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

    //保存或更新修改
    function saveOrUpdate() {
        var loader = new loadLayer();
        var $id = $("#id").val();
        var tex=$("#text").val();
        var teamId=$("#bj").val();
        var teamName=$("#bj").find("option:selected").text();
        var subjectId=$("#km").val();
        var subjectName=$("#km").find("option:selected").text();
        var pictureUuid=$("#pictureUuid").val();
        var gradeId=$("#nj").val();
        var schoolYear=$("#xn").val();
        var schoolTrem=$("#xq").val();
        var val={};
        val.id=$id;
        val.gradeId=gradeId;
        val.schoolYear=schoolYear;
        val.schoolTrem=schoolTrem;
        val.teamName=teamName;
        val.teamId=teamId;
        val.subjectId=subjectId;
        val.subjectName=subjectName;
        val.pictureUuid=pictureUuid;
        val.text=tex;
        var obj = document.getElementsByName("isHome");
        for(var i = 0; i < obj.length; i ++) {
            if (obj[i].checked) {
                val.isHome= obj[i].value;
            }
        }
       var  url = "${ctp}/home/woke/addHomeWoke";
        loader.show();
        if(schoolYear!=null && schoolYear!=""){
            if(schoolTrem!=null && schoolTrem!=""){
                if(gradeId!=null && gradeId!=""){
                    if(teamId!=null && teamId!=""){
                        if(subjectId!=null && subjectId!=""){
                            $.post(url, val, function (data, status) {
                                if (status=="success") {
                                    if ("success" === data) {
                                        $.success('操作成功');
                                        getBack();
                                        parent.layer.closeAll();
                                    } else {
                                        $.error("操作失败");
                                    }
                                }
                                loader.close();
                            });
                        }else{
                            $.error("请选择科目")
                        }
                    }else{
                        $.error("请选择班级")
                    }
                }else{
                    $.error("请选择年级")
                }
            }else{
                $.error("请选择学期")
            }
        }else{
            $.error("请选择学年")
        }

    }
    function getBack() {
        if(parent.core_iframe != null) {
            parent.core_iframe.window.location.reload();
        } else {
            parent.window.location.reload();
        }
        $.closeWindow();
    }
</script>
</body>
</html>
