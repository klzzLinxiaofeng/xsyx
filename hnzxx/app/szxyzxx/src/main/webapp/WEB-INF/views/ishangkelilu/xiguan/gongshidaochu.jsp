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
    <title>课堂行为公示导出</title>
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
                                    style="width:200px;" value="${homeWoke.gradeId}">
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            年级：
                        </label>
                        <div class="controls">
                            <select id="nj" name="gradeId" class="span4"
                                    style="width:200px;" value="${homeWoke.gradeId}">
                                <option value="">全年级</option>
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            班级：
                        </label>
                        <div class="controls">
                            <select id="bj" name="teamId" class="span4"
                                    style="width:200px;" value="${homeWoke.teamId}">
                                <option value="">所有班级</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            科目：
                        </label>
                        <div class="controls">
                            <select id="km" name="subjectId" class="span4"
                                    style="width:200px;" value="${homeWoke.subjectId}">
                                <option value="">所有科目</option>
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                        评价时间：
                        </label>
                        <%--<span>评价时间：</span>--%>
                        <div class="controls">
                            <input type="text" id="startDate" name="startDate" class="chzn-select" autocomplete="off"
                                   style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" placeholder="开始时间"
                                   value="">-
                            <input type="text" id="endDate" name="startDate" class="chzn-select" autocomplete="off"
                                   style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" placeholder="结束时间"
                                   value="">
                        </div>
                    </div>
                    <div class="form-actions tan_bottom">
                        <button class="btn btn-warning" type="button"
                                onclick="daochu();">导出                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        initSelect();
    });
    function initSelect() {
        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name")
            }
        })
    }
    //绑定下拉框改变事件
    $("#xn").change(function(){
        $("#nj").html('<option value="">全年级</option>');
        $("#bj").html('<option value="">所有班级</option>');
        addOption('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name");
        //添加年级
        addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
    })
    $("#nj").change(function(){
        $("#bj").html('<option value="">所有班级</option>');
        $("#km").html('<option value="">所有科目</option>');
        //添加班级
        if($(this).val()!="") {
            //添加班级
            addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name")
        }
    })
    $("#bj").change(function(){
        $("#km").html('<option value="">所有科目</option>');
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

    //保存或更新修改
    function daochu() {
        var loader = new loadLayer();
        var teamId=$("#bj").val();
        var subjectId=$("#km").val();
        var gradeId=$("#nj").val();
        var schoolYear=$("#xn").val();
        var schoolTrem=$("#xq").val();
        var startDate=$("#startDate").val();
        var endDate=$("#endDate").val();
        var  url = "${ctp}/KeTangGongShi/daochuWoke";
        if (schoolYear != null && schoolYear != "") {
            url+="?schoolYear="+schoolYear;
        }
        if (schoolTrem != null && schoolTrem != "") {
            url+="&schoolTrem="+schoolTrem;
        }
        if (gradeId != null && gradeId != "") {
            url+="&gradeId="+gradeId;
        }
        if (teamId != null && teamId != "") {
            url+="&teamId="+teamId;
        }
        if (subjectId != null && subjectId != "") {
            url+="&subjectId="+subjectId;
        }
        if (startDate != null && startDate != "") {
            url+="&startDate="+startDate;
        }
        if (endDate != null && endDate != "") {
            var data=new Date(endDate);
            var year= data.getFullYear();
            var mounth=data.getMonth()+1;
            var datas=new Date(year,mounth, 0).getDate();
            if(data<=datas){
                var day=data.getDate()+1;
                url+="&endDate="+ year+"-"+mounth+"-"+day;;
            }else{
                url+="&endDate="+ year+"-"+Number(mounth+1)+"-01";
            }

        }
        window.open(url);

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
