<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>下载品德发展评语模板</title>
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
                            模板类型：
                        </label>
                        <div class="controls">
                            <select id="pingyuId" name="pingyuId" class="span4"
                                    style="width:200px;">
                                <option value="">请选择</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-actions tan_bottom">
                        <button class="btn btn-warning" type="button"
                                onclick="downLoadModel();">下载
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var checkerSelect;
    $(function () {
        initSelect();
    })

    function initValidatorSelect() {
        return $("#publicClass_time_form").validate({
            errorClass : "myerror",
            rules : {
                "schoolYear" : {
                    required : true
                },
                "schoolTrem" : {
                    required : true
                },
                "pingyuId" : {
                    required : true
                }
            },
            messages : {

            }
        });
    }


    function downLoadModel() {
        checkerSelect = initValidatorSelect();
        if (checkerSelect.form()) {
            var loader = new loadLayer();
            var schoolYear=$("#schoolYear").val();
            var schoolTrem=$("#schoolTrem").val();
            var pingyuId=$("#pingyuId").val();
            var text=$("#pingyuId").text();
            var url = "${ctp}/PinDeMoBan/downloadPingYuMoBan?schoolYear="+schoolYear+"&schoolTrem="+schoolTrem+
                "&mobanTypeId="+pingyuId+"&mobanTypeName="+text;
            loader.show();
            $.get(url, function(data, status) {
                if("success" === status) {
                    //乱码问题尚未解决  执行到451行报错  执行不下去了
                    $.success('下载成功');
                    loader.close();
                    window.open(url);
                }else{
                    $.error("服务器异常");
                }

            });

            loader.close();
        }
    }





    function initSelect() {
        addOption('/pingyumoban/findBypingyu', "pingyuId", "id", "name");
        addOption('/teach/schoolYear/list/json', "schoolYear", "year", "name", function (d) {
            if (d.length > 0) {
                addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "schoolTrem", "code", "name");
              }
        })
    }
    //绑定下拉框改变事件
    $("#schoolYear").change(function(){
        $("#schoolTrem").html('');
        addOption('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "schoolTrem", "code", "name");
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
</script>
</body>
</html>
