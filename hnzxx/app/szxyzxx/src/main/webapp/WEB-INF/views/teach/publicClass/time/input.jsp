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
                            标题：
                        </label>
                        <div class="controls">
                            <input value="${publicTimeVo.classTime}" type="text" id="classTime" name="classTime" class="span4" autocomplete="off">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            星期：
                        </label>
                        <div class="controls">
                            <select id="weekDate" name="weekDate">
                                <option value="1" ${publicTimeVo.weekDate==1 ?'selected':''}>星期一</option>
                                <option value="2" ${publicTimeVo.weekDate==2 ?'selected':''}>星期二</option>
                                <option value="3" ${publicTimeVo.weekDate==3 ?'selected':''}>星期三</option>
                                <option value="4" ${publicTimeVo.weekDate==4 ?'selected':''}>星期四</option>
                                <option value="5" ${publicTimeVo.weekDate==5 ?'selected':''}>星期五</option>
                                <option value="6" ${publicTimeVo.weekDate==6 ?'selected':''}>星期六</option>
                                <option value="7" ${publicTimeVo.weekDate==7 ?'selected':''}>星期日</option>
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            上课时间：
                        </label>
                        <div class="controls">
                            <input type="text" id="classTimeStart" name="classTimeStart" class="span4" autocomplete="off"
                                   onFocus="WdatePicker({dateFmt:'HH:mm:ss'})"
                                   placeholder="xx:xx:xx"
                                   value='<fmt:formatDate pattern="HH:mm:ss" value="${publicTimeVo.classTimeStart}"></fmt:formatDate>'>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            下课时间：
                        </label>
                        <div class="controls">
                            <input type="text" id="classTimeEnd" name="classTimeEnd" class="span4" autocomplete="off"
                                   onFocus="WdatePicker({dateFmt:'HH:mm:ss'})"
                                   placeholder="xx:xx:xx"
                                   value='<fmt:formatDate pattern="HH:mm:ss" value="${publicTimeVo.classTimeEnd}"></fmt:formatDate>'>
                        </div>
                    </div>

                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${publicTimeVo.id}"/>
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
            var url = "${ctp}/teach/publicClass/class/creator";
            if ("" != $id) {
                url = "${ctp}/teach/publicClass/class/update";
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