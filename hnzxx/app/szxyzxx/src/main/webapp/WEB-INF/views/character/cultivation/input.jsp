<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
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
                            <input value="${evaluation.name}" type="text" id="name" name="name" class="span4" autocomplete="off">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            初始分数：
                        </label>
                        <div class="controls">
                            <input value="${evaluation.initScore}" type="text" id="initScore" name="initScore" class="span4" autocomplete="off">
                        </div>
                    </div>


                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${evaluation.id}"/>
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
            var loader = new loadLayer();
            var $id = $("#id").val();
            var $requestData = formData2JSONObj("#publicClass_time_form");
            var url = "${ctp}/character/cultivation/creator";
            if ("" != $id) {
                url = "${ctp}/character/cultivation/update";
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
    }
</script>
</html>