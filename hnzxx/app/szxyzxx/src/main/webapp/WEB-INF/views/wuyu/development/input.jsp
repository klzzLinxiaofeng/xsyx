<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>品德发展指标添加或修改</title>
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
                            指标名称：
                        </label>
                        <div class="controls">
                            <input value="${developmentIndicators.name}" type="text" id="name" name="name" class="span4" autocomplete="off">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            评价角色：
                        </label>
                        <div class="controls">
                            <label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
                                <input type="checkbox" name="classType" value="SHENGHUOJIAOSHI"/>
                                生活教师 </label>
                            <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                <input type="checkbox" name="classType"
                                       value="CLASS_MASTER" />
                                班主任 </label>
                            <label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
                                <input type="checkbox" name="classType"
                                       value="PARENT"/>
                                家长</label>
                        </div>
                    </div>
                   <%-- <div class="control-group">
                        <label class="control-label">
                            初始分数：
                        </label>
                        <div class="controls">
                            <input value="${developmentIndicators.score}" type="text" id="score" name="score" class="span4" autocomplete="off">
                        </div>
                    </div>--%>


                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${developmentIndicators.id}"/>
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
            var code="${developmentIndicators.code}";
            if(code!=null && code!="") {
                var arr = new Array();
                arr = code.split(",");
                for (var i = 0; i < arr.length; i++) {
                    $("input[name='classType'][value='" + arr[i] + "']").attr("checked", true);
                }
            }

        checker = initValidator();
    });

    function initValidator() {
        return $("#publicClass_time_form").validate({
            errorClass: "myerror",
            rules: {
                "name": {
                    required: true,
                    maxlength: 15,
// 					stringCheck : true
                    isContainsSpecialChar: true
                },
            },
            messages: {}
        });
    }


    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
          /*  var type = '';
                $("input[name='classType']:checkbox").each(function () {
                    if ($(this).attr("checked")) {
                        type += $(this).attr('value') + ',';
                    }
                });
*/
            var loader = new loadLayer();
            var $requestData = formData2JSONObj("#publicClass_time_form");
            $requestData.code=$requestData.classType;
            var url = "${ctp}/developmentIndicators/createOrUpdate";
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
    }
</script>
</html>