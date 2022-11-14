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
    <title>公示导出</title>
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
                            <select id="xn" name="schoolYear" class="span4"
                                    style="width:200px;" >
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            学期：
                        </label>
                        <div class="controls">
                            <select id="xq" name="schoolTrem" class="span4"
                                    style="width:200px;" >
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <span>时间区间：</span>
                        <div class="controls">
                            <input type="text" id="startDate" name="startDate" class="chzn-select" autocomplete="off"
                                   style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" placeholder="开始时间"
                                   value="">-
                            <input type="text" id="endDate" name="startDate" class="chzn-select" autocomplete="off"
                                   style="width:200px;padding-top: 4px;"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" placeholder="结束时间"
                                   value="">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            模块名称：
                        </label>
                        <div class="controls">
                            <input  id="mokuaiName" name="mokuaiName" class="span4" autocomplete="off"></input>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            教师名称：
                        </label>
                        <div class="controls">
                            <input  id="name" name="name" class="span4" autocomplete="off"></input>
                        </div>
                    </div>


                    <div class="form-actions tan_bottom">
                        <button class="btn btn-warning" type="button"
                                onclick="daochu();">导出
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
    });
    function initSelect() {
        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                addOptionxq('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
             }
        })
    }
    //绑定下拉框改变事件
    $("#xn").change(function(){
        $("#xq").html('');
        addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name");
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
    function daochu() {
        var loader = new loadLayer();
        var schoolYear=$("#xn").val();
        var mokuaiName=$("#mokuaiName").val();
        var name=$("#name").val();
        var schoolTrem=$("#xq").val();
        var startDate=$("#startDate").val();
        var endDate=$("#endDate").val();
        var  url = "${ctp}/logger/daochu";
        if (schoolYear != null && schoolYear != "") {
            url+="?schoolYear="+schoolYear;
        }
        if (schoolTrem != null && schoolTrem != "") {
            url += "&schoolTrem=" + schoolTrem;
        }
        if (mokuaiName != null && mokuaiName != "") {
            url+="&mokuaiName="+mokuaiName;
        }
        if (name != null && name != "") {
            url+="&name="+name;
        }
        if (startDate != null && startDate != "") {
            url+="&startTime="+startDate+" 00:00:00";
        }
        if(endDate!=null && endDate !=""){
                url+="&endTime="+ endDate+" 23:59:59";
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
