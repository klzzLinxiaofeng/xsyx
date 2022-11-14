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
                            <select id="schoolYear" name="schoolYear" class="span4"
                                    style="width:200px;" value="${pingYuType.schoolYear}">
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            学期：
                        </label>
                        <div class="controls">
                            <select id="schoolTrem" name="schoolTrem" class="span4"
                                    style="width:200px;" value="${pingYuType.schoolTrem}">
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            年级：
                        </label>
                        <div class="controls">
                            <select id="gradeId" name="gradeId" class="span4"
                                    style="width:200px;" value="${pingYuType.gradeId}">
                                <option>请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            科目：
                        </label>
                        <div class="controls">
                            <select id="subjectId" name="subjectId" class="span4"
                                    style="width:200px;" value="${pingYuType.subjectId}">
                                <option>请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            指标名称：
                        </label>
                        <div class="controls">
                            <select id="subjctZhiBiaoId" name="subjctZhiBiaoId" class="span4"
                                    style="width:200px;" value="${pingYuType.subjctZhiBiaoId}">
                                <option>请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            模板类型：
                        </label>
                        <div class="controls">
                            <select id="pingyuId" name="pingyuId" class="span4"
                                    style="width:200px;" value="${pingYuType.pingYuId}">
                                <option>请选择</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            分数段：
                        </label>
                        <div class="controls">
                            <input  type="text" id="minScore" name="minScore" class="span4" autocomplete="off" value="${pingYuType.minScore}"/>-
                            <input  type="text" id="maxScore" name="maxScore" class="span4" autocomplete="off" value="${pingYuType.maxScore}"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            评语：
                        </label>
                        <div class="controls">
                        <textarea  type="text" id="text" name="text" class="span4" autocomplete="off"> ${pingYuType.text}</textarea>
                        </div>
                    </div>
                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${pingYuType.id}"/>
                        <input type="hidden" id="id2" name="id" value="${pingYuType.gradeId}"/>
                        <input type="hidden" id="id3" name="id" value="${pingYuType.pingYuId}"/>
                        <input type="hidden" id="id4" name="id" value="${pingYuType.subjectId}"/>
                        <input type="hidden" id="id5" name="id" value="${pingYuType.subjctZhiBiaoId}"/>
                        <input type="hidden" id="id6" name="id" value="${pingYuType.schoolYear}"/>
                        <input type="hidden" id="id7" name="id" value="${pingYuType.schoolTrem}"/>
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
        initSelect();
    })
    function initSelect() {
       var gradeId=$("#id2").val();
       var pingyuId=$("#id3").val();
       var subjectId=$("#id4").val();
       var subjctZhiBiaoId=$("#id5").val();
        var schoolYear=$("#id6").val();
        var schooTrem=$("#id7").val();
        if(schoolYear!=null && schoolYear!=""){
            //初始填充学年、学期、年级
            addOption2('/teach/schoolYear/list/json', "schoolYear", "year", "name",schoolYear);
            addOption2('/teach/schoolTerm/list/json?schoolYear='+schoolYear, "schoolTrem", "code", "name",schooTrem);
            //因查询年级不需学期，所以不需在学期填充后的回调中执行
            addOption2('/teach/grade/list/json?schoolYear=' +schoolYear, "gradeId", "id", "name",gradeId);
            //添加指标
            addOption2("/pingyumoban/findByZhiBiao?gradeId="+gradeId+"&subjectId="+subjectId+"&schoolYear="+schoolYear+"&schooolTrem="+schooTrem, "subjctZhiBiaoId", "id", "literacy_name",subjctZhiBiaoId);
        }else{
            addOption('/teach/schoolYear/list/json', "schoolYear", "year", "name", function (d) {
                if (d.length > 0) {
                    addOptionxq('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "schoolTrem", "code", "name");
                    //因查询年级不需学期，所以不需在学期填充后的回调中执行
                    addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "gradeId", "id", "name")
                }
            })
        }
    //   addOption2('/pingyumoban/findByGrade', "gradeId", "id", "name",gradeId);

        addOption2('/pingyumoban/findBypingyu', "pingyuId", "id", "name",pingyuId);
        addOption2("/literacy/findExamSubject?nj="+gradeId, "subjectId", "subjectId", "subjectName",subjectId);

    }
    //绑定下拉框改变事件
    $("#schoolYear").change(function(){
        $("#subjctZhiBiaoId").html('<option value="">请选择</option>');
        $("#gradeId").html('<option value="">请选择</option>');
        $("#schoolTrem").html('');
        addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "schoolTrem", "code", "name");
        //添加年级
        addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "gradeId", "id", "name")
    })
    $("#schoolTrem").change(function(){
        $("#subjctZhiBiaoId").html('<option value="">请选择</option>');
    })

    $("#gradeId").change(function(){
        $("#subjectId").html('<option value="">请选择</option>');
        $("#subjctZhiBiaoId").html('<option value="">请选择</option>');
        //添加科目
        addOption("/literacy/findExamSubject?nj="+$("#gradeId").val(), "subjectId", "subjectId", "subjectName");
    })
    $("#subjectId").change(function () {
        $("#subjctZhiBiaoId").html('<option value="">请选择</option>');
        //添加指标
        addOption("/pingyumoban/findByZhiBiao?gradeId="+$("#gradeId").val()+"&subjectId="+$("#subjectId").val()+"&schoolYear="+$("#schoolYear").val()+"&schooolTrem="+$("#schoolTrem").val(), "subjctZhiBiaoId", "id", "literacy_name");
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
    function addOption2(url, id, valProperty, namePropety,asd, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                if(obj[valProperty]==asd){
                    $("#" + id).append("<option selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }else{
                    $("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }
            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }

    function addOptionxq(url, id, valProperty, namePropety, callback) {
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

    //保存或更新修改
    function saveOrUpdate() {
        var loader = new loadLayer();
        var text = $("#text").val();
        var gradeId=$("#gradeId").val();
        var pingYuId=$("#pingyuId").val();
        var pingYuName=$("#pingyuId").find("option:selected").text();
        var minScore=$("#minScore").val();
        var maxScore=$("#maxScore").val();
        var subjectId=$("#subjectId").val();
        var subjctZhiBiaoId=$("#subjctZhiBiaoId").val();
        var schoolYear=$("#schoolYear").val();
        var schoolTrem=$("#schoolTrem").val();

        var $id = $("#id").val();
        var val={};
        val.gradeId=gradeId;
        val.pingYuId=pingYuId;
        val.pingYuTypeName=pingYuName;
        val.minScore=minScore;
        val.maxScore=maxScore;
        val.text=text;
        val.subjectId=subjectId;
        val.subjctZhiBiaoId=subjctZhiBiaoId;
        val.schoolYear=schoolYear;
        val.schoolTrem=schoolTrem;
        var falg=pnduan(val);
        if(falg==null){
            var  url ="";
            loader.show();
            if($id!=null && $id!=""){
                val.id=$id;
                url="/pingyumoban/update";
            }else{
                url="/pingyumoban/create";
            }
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
            $.error(falg);
        }

    }
    function pnduan(val) {
        if(val.text==null || val.text==""){
            return "模板内容不能为空";
        }
        if(val.gradeId==null || val.gradeId==""){
            return "年级不能为空";
        }
        if(val.pingYuId==null || val.pingYuId==""){
            return "评语类型不能为空";
        }
        if(val.minScore==null || val.minScore==""){
            return "最小分值不能为空";
        }
        if(val.maxScore==null || val.maxScore==""){
            return "最大分值不能为空";
        }
        if(val.subjectId==null || val.subjectId==""){
            return "科目不能为空";
        }
        if(val.subjctZhiBiaoId==null || val.subjctZhiBiaoId==""){
            return "指标不能为空";
        }
        return null;
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
