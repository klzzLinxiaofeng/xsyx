<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@ include file="/views/embedded/common.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>新建校历</title>
        <style>
            .myerror {
                color: red !important;
                display: inline-block;
                padding-left: 10px;

            </style>
        </head>
        <body style="background-color:cdd4d7 !important">
                <div class="row-fluid" >
                    <div class="span12">
                        <div class="content-widgets" style="margin-bottom:0">
                            <div class="widget-container" style="padding:20px 0 0;">
                            <form id="calendarForm"  class="form-horizontal tan_form">
                                <div class="control-group">
                                    <label class="control-label"><font style="color:red">*</font>校历标题</label>
                                        <div class="controls">
                                            <input id="name" name="name" type="text" >
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label"><font style="color:red">*</font>开始时间</label>
                                        <div class="controls">
                                            <input type="text" class="Wdate" id="beginDate" name="beginDate" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd',isShowWeek:true,maxDate:'#F{$dp.$D(\'finishDate\')}'})" >
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label"><font style="color:red">*</font>结束时间</label>
                                        <div class="controls">
                                            <input type="text" class="Wdate" id="finishDate" name="finishDate" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd',isShowWeek:true,minDate:'#F{$dp.$D(\'beginDate\')}',maxDate:''})" >
                                        </div>
                                    </div>

                                    <div class="form-actions tan_bottom">
                                        <button class="btn btn-warning" onclick="save()" type="button">确定</button>
                                        <button class="btn" onclick="$.closeWindow();" type="reset">取消</button>
                                    </div>
                                </form>
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
                                                    var val = $("#calendarForm").validate({
                                                        errorClass: "myerror",
                                                        rules: {
                                                            "name": {
                                                                required: true,
                                                                maxlength: 50
                                                            },
                                                            "beginDate": {
                                                                required: true
                                                            },
                                                            "finishDate": {
                                                                required: true
                                                            }
                                                        },
                                                        messages: {
                                                            "name": {
                                                                required: "必须输入校历标题",
                                                                maxlength: "最多字符不能超过xxx"
                                                            },
                                                            "beginDate": {
                                                                required: "必须输入开始时间"
                                                            },
                                                            "finishDate": {
                                                                required: "必须输入结束时间"
                                                            }
                                                        }
                                                    });
                                                    return val;
                                                }

                                                //保存或更新修改
                                                function save() {
                                                    if ($('#calendarForm').valid()) {
                                                    	var loader = new loadLayer();
                                                        var $requestData = {};
                                                        $requestData.name = $.trim($("#name").val());
                                                        $requestData.beginDate = $.trim($("#beginDate").val());
                                                        $requestData.finishDate = $.trim($("#finishDate").val());
                                                        var url = "${pageContext.request.contextPath}/teach/calendar/saveOrUpdate";
                                                        loader.show();
                                                        $.post(url, $requestData, function(data, status) {
                                                            if ("success" === status) {
                                                            	if("success" === data){
                                                            		if (parent.core_iframe != null) {
                                                                        parent.core_iframe.window.location.href="${pageContext.request.contextPath}/teach/calendar/index";
                                                                    } else {
                                                                        parent.window.location.href="${pageContext.request.contextPath}/teach/calendar/index";
                                                                    }
                                                            	}else{
                                                            		$.error("校历名重复，不能创建！");
                                                            	}
                                                                
                                                                $.closeWindow();
                                                            }
                                                            loader.close();
                                                        });
                                                    }
                                                }
            </script>
        </html>
