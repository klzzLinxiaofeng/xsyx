<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>编辑</title>
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
                            <form id="microForm" class="form-horizontal">
                                <div class="control-group">
                                    <label class="control-label" style="width: 80px;"><font style="color:red">*</font>标题： </label>
                                        <div class="controls" style="margin-left: 100px;">
                                        <input type="text" name="title" id="title" value="${micro.title}" class="span5" style="width: 550px;">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" style="width: 80px;"> 简介： </label>
                                    <div class="controls" style="margin-left: 100px;">
                                        <textarea name="description" id="description" class="span5" style="width: 550px; height: 120px;">${micro.description}</textarea>
                                    </div>
                                </div>
                                <!--                            <div class="control-group">
                                                                <label class="control-label" style="width: 80px;"> 知识点： </label>
                                                                <div class="controls" style="margin-left: 100px;">
                                                                    <select style="width: 134px;"><option>五年级</option>
                                                                        <option>六年级</option></select> <select style="width: 134px;"><option>语文</option>
                                                                        <option>数学</option></select> <select style="width: 134px;"><option>上册</option>
                                                                        <option>下册</option></select> <select style="width: 134px;"><option>人教版</option>
                                                                        <option>北师大</option></select>
                                                                </div>
                                                            </div>-->
                                <!--						<div class="control-group">
                                                                                        <label class="control-label" style="width: 80px;"> 关键字： </label>
                                                                                        <div class="controls" style="margin-left: 100px;">
                                                                                                <input type="text" value="" placeholder="微课" class="span5"
                                                                                                        style="width: 550px;">
                                                                                        </div>
                                                                                </div>-->
                            </form>
                            <div class="form-actions tan_bottom">
                                <button class="btn btn-warning" onclick="save();" type="button">确认</button>
                                <button class="btn" onclick="$.closeWindow();" type="button">取消</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </body>
        <script type="text/javascript">
                                    $(function() {
                                        initValidator();
                                    });

                                    function initValidator() {
                                        var val = $("#microForm").validate({
                                            errorClass: "myerror",
                                            rules: {
                                                "title": {
                                                    required: true
                                                }
                                            },
                                            messages: {
                                                "title": {
                                                    required: "必须输入标题"
                                                }
                                            }
                                        });
                                        return val;
                                    }

                                    //保存或更新修改
                                    function save() {
                                        if ($('#microForm').valid()) {
                                            var loader = new loadLayer();
                                            var $requestData = {};
                                            $requestData.microId = "${micro.uuid}";
                                            $requestData.title = $.trim($("#title").val());
                                            $requestData.description = $.trim($("#description").val());
                                            var url = "${pageContext.request.contextPath}/micro/saveOrUpdate";
                                            loader.show();
                                            $.post(url, $requestData, function(data, status) {
                                                if ("success" === status) {
                                                    $.alert("修改微课成功");
                                                    var reurl = "${pageContext.request.contextPath}/micro/myMicro?index=index";
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