<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <%@ include file="/views/embedded/plugin/uploadify.jsp" %>
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
                            班级：
                        </label>
                        <div class="controls">
                            <select id="bj" name="teamId" class="span4"
                                    style="width:200px;" >
                            </select>
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
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name");
            }
        })
    }

    //绑定下拉框改变事件
    $("#xn").change(function(){
        $("#nj").html('<option value="">请选择</option>');
        $("#xq").html('<option value="">请选择</option>');
        $("#bj").html('<option value="">请选择</option>');
        //添加学期
        addOption('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name")
        //添加年级
        addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
    })

    $("#nj").change(function(){
        //$("#bj").html('<option value="">请选择</option>');
        $("#bj").html('<option value="">请选择</option>');
        //添加科目
        //添加班级
            addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name")
    })

    function addOption(url, id, valProperty, namePropety, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                    $("#" + id).append("<option   value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }

    //保存或更新修改
    function saveOrUpdate() {

        var xn = $("#xn").val();
        var xq = $("#xq").val();
        var nj = $("#nj").val();
        var bg = $("#bj").val();
        var url ="/character/cultivation/deletePicter?xn="+xn+"&xq="+xq+"&nj="+nj+"&bg"+bg;
        $.get(url, function (data, status) {
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