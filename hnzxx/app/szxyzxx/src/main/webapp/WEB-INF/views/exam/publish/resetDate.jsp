<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>修改时间</title>
        <style>
            .myerror {
                color: red !important;
                display: inline-block;
                padding-left: 10px;

            </style>
        </head>
        <body style="background-color: #F3F3F3 !important">
                <div class="row-fluid ">
                    <div class="span12">
                        <div style="margin-bottom: 0" class="content-widgets">
                            <div style="padding: 20px 0 0;" class="widget-container">
                            <div class="time_sz">
                                <form id="dateForm" class="form-horizontal">
                                    <div class="control-group">
                                        <label class="control-label">时间</label>
                                        <div class="controls">
                                            <input id="startDate" type="text" value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd HH:mm" />" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss', minDate: '%y-%M-%d %H:%m:%s', maxDate: '#F{$dp.$D(\'finishedDate\')}'})" placeholder="开始日期" class="span2">
                                            点 <span style="padding:0 8px 0 18px;">至</span>
                                        <input id="finishedDate" type="text" value="<fmt:formatDate value="${finishedDate}" pattern="yyyy-MM-dd HH:mm" />"onclick="WdatePicker({dateFmt: 'yyyy-MM-dd HH:mm:ss',minDate: '%y-%M-%d %H:%m:%s', maxDate: ''})" placeholder="结束日期" class="span2">
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="form-actions tan_bottom">
                                <button class="btn btn-warning" onclick="save();" type="button">保存</button>
                                <button class="btn" onclick="$.closeWindow();" type="button">取消</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </body>
        <script type="text/javascript">
            $(function () {
                initValidator();
            });

            function initValidator() {
                var val = $("#dateForm").validate({
                    errorClass: "myerror",
                    rules: {
                        "startDate": {
                            required: true
                        },
                        "finishedDate": {
                            required: true
                        }
                    },
                    messages: {
                        "startDate": {
                            required: "必须输入开始时间"
                        },
                        "finishedDate": {
                            required: "必须输入结束时间"
                        }
                    }
                });
                return val;
            }

            //保存或更新修改
            function save() {
                if ($('#dateForm').valid()) {
                    var loader = new loadLayer();
                    var $requestData = {};
                    $requestData.publishId = "${publishId}";
                    $requestData.relateId = "${relateId}";
                    $requestData.startDate = $.trim($("#startDate").val());
                    $requestData.finishedDate = $.trim($("#finishedDate").val());
                    $requestData.startClock = $.trim($("#startClock option:selected").text());
                    $requestData.finishedClock = $.trim($("#finishedClock option:selected").text());
                    if ($requestData.startDate == $requestData.finishedDate) {
                        if (parseInt($requestData.startClock) > parseInt($requestData.finishedClock)) {
                            $.alert("开始时间不能大于结束时间")
                            return;
                        }
                    }
                    var url = "${pageContext.request.contextPath}/exampublish/updatePublishedDate";
                    loader.show();
                    $.post(url, $requestData, function (data, status) {
                        if ("success" === status) {
                            $.alert("修改试卷布置时间成功");
                            var reurl = "${pageContext.request.contextPath}/exampublish/publishManagerIndex?relateId=" + $requestData.relateId+"&gradeId=${param.gradeId}&dm=${param.dm}";
                            if (parent.core_iframe != null) {
                                parent.core_iframe.window.location.href = reurl;
                            } else {
                                parent.window.location.href = reurl;
                            }
                            $.closeWindow();
                        }
                        loader.close();
                    });
                }
            }
        </script>
    </html>