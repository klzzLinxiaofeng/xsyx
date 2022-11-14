<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp"%>
    <style>
        .row-fluid .span13 {
            width: 75%;
        }

        .row-fluid .span4 {
            width: 75%;
        }

        .myerror {
            color: red !important;
            width: 22%;
            display: inline-block;
            padding-left: 10px;
        }
    </style>
</head>
<body style="background-color: cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="chargeitem_form" action="javascript:void(0);">

                    <div class="control-group">
                        <label class="control-label">
                            项目名称：
                        </label>
                        <div class="controls">
                            <input type="text" id="name" name="name" class="span13" placeholder="" value="${chargeItem.name}">
                        </div>
                    </div>

                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${chargeItem.id}" />
                        <input type="hidden" id="schoolId" name="schoolId" value="${chargeItem.schoolId}" />
                        <button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
                        <button class="btn btn-warning" type="button" onclick="$.closeWindow();">取消</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var checker;
    $(function() {
        checker = initValidator();
    });

    function initValidator() {
        return $("#chargeitem_form").validate({
            errorClass : "myerror",
            rules : {
                "name" : {
                    required : true,
                    maxlength : 20
                }
            },
            messages : {
                "name" : {
                    required : "项目名称不能为空",
                    maxlength : "最长不能超出20个字符"
                }
            }
        });
    }

    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            var loader = new loadLayer();
            var $id = $("#id").val();
            var $requestData = formData2JSONObj("#chargeitem_form");
            var url = "${ctp}/dn/charge/item/creator";
            if ("" != $id) {
                $requestData._method = "put";
                url = "${ctp}/dn/charge/item/" + $id;
            }
            loader.show();
            $.post(url, $requestData, function(data, status) {
                if("success" === status) {
                    $.success('操作成功');
                    data = eval("(" + data + ")");
                    if("success" === data.info) {
                        if(parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else if ("dataRepeat" == data.info) {
                        $.error("与原有项目重名");
                    } else {
                        $.error("操作失败");
                    }
                }else{
                    $.error("操作失败");
                }
                loader.close();
            });
        }
    }

</script>
</html>