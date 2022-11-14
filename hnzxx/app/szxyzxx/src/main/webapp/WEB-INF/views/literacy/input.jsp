<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>学科素养指标编辑</title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <style>

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
                            学年：
                        </label>
                        <div class="controls">
                            <select id="xn" name="xn" class="span4"
                                    style="width:200px;" value="${literacyVo.xn}">

                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            学期：
                        </label>
                        <div class="controls">
                            <select id="xq" name="xq" class="span4"
                                    style="width:200px;" value="${literacyVo.xq}">

                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            年级：
                        </label>
                        <div class="controls">
                            <select id="nj" name="gradeId" class="span4"
                                    style="width:200px;" value="${literacyVo.gradeId}">
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            科目：
                        </label>
                        <div class="controls">
                            <select id="km" name="subjectId" class="span4"
                                    style="width:200px;" value="${literacyVo.subjectId}">
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            学科素养指标：
                        </label>
                        <div class="controls">
                            <input value="${literacyVo.literacyName}" type="text" id="literacyName" name="literacyName" class="span4" autocomplete="off">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            最大分值：
                        </label>
                        <div class="controls">
                            <input value="${literacyVo.score}" type="text" id="score" name="score" class="span4" autocomplete="off">
                        </div>
                    </div>


                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${literacyVo.id}"/>
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
        initSelect();
    });

        function initSelect() {

            //初始填充学年、学期、年级
            addOption('/teach/schoolYear/list/json', "xn", "year", "name",${literacyVo.xn}, function (d) {
                if (d.length > 0) {
                    addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name",${literacyVo.xq});
                    //因查询年级不需学期，所以不需在学期填充后的回调中执行
                    addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name",${literacyVo.gradeId});
                    //添加科目
                    addOption("/literacy/findExamSubject?nj="+${literacyVo.gradeId}, "km", "subjectId", "subjectName",${literacyVo.subjectId})
                }
            })
        }


        //绑定下拉框改变事件
        $("#xn").change(function(){
            $("#nj").html('<option value="">请选择</option>');
            $("#xq").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
            //添加学期
            addOption('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name")
            //添加年级
            addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
        })
        $("#xq").change(function(){
            $("#km").html('<option value="">请选择</option>');
            $("#nj").html('<option value="">请选择</option>');
            //$("#ks").html('<option value="">请选择</option>');
            //添加年级
            addOption('/teach/grade/list/json?schoolYear='+$("#xn").val(), "nj", "id", "name")
        })

        $("#nj").change(function(){
            //$("#bj").html('<option value="">请选择</option>');
            $("#km").html('<option value="">请选择</option>');
                //添加科目
                addOption("/literacy/findExamSubject?nj="+$("#nj").val(), "km", "subjectId", "subjectName")

        })

    function addOption(url, id, valProperty, namePropety,addd, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                var str=obj[valProperty];
                if(id=="xq"){
                    var num="";
                    var arr=str.split("-");
                    for(var a=0;a<arr.length;a++){
                        if(num==""){
                            num=arr[a];
                        }else{
                            num-=arr[a];
                        }
                    }
                    if(num===addd){
                        $("#" + id).append("<option selected  value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                    }else{
                        $("#" + id).append("<option   value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                    }
                }else{
                    if(addd==obj[valProperty]){
                        $("#" + id).append("<option selected  value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                    }else{
                        $("#" + id).append("<option   value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");

                    }
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
            var $id = $("#id").val();
            var $requestData = formData2JSONObj("#publicClass_time_form");
            var url = "${ctp}/literacy/input/create";
            if ("" != $id) {
                url = "${ctp}/literacy/update";
            }
            loader.show();
            $.post(url, $requestData, function (data, status) {
                if (status=="success") {
                    $.success('操作成功');
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
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