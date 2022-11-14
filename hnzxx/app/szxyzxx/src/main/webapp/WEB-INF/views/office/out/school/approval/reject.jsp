<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>驳回</title>
    <%@ include file="/views/embedded/common.jsp" %>
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

        .chzn-container .chzn-results {
            max-height: 80px;
        }
    </style>
</head>
<body style="background-color:#cdd4d7 !important">
<div class="row-fluid">
    <div class="span4">
        <div class="content-widgets" style="margin-bottom:0">
            <div class="widget-container" style="padding:20px 0 0">
                <form class="form-horizontal tan_form" id="data_form" action="javascript:">
                    <div class="control-group">
                        <label class="control-label" style="width:80px"><font style="color:red">*</font>驳回理由</label>
                        <div class="controls" style="margin-left:100px">
                            <textarea type="text" id="feedback" name="feedback" rows="7" placeholder="请输入驳回理由" style="width:400px;height:150px"></textarea>
                        </div>
                    </div>
                    <div class="form-actions tan_bottom">
                        <button class="btn btn-warning" type="button" onclick="saveOrUpdate()">确定</button>
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
        return $("#data_form").validate({
            errorClass: "myerror",
            rules: {
                "feedback": {
                    required: true,
                    maxlength: 1000
                }
            }
        });
    }

    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            var $requestData = formData2JSONObj("#data_form");
            var loader = new loadLayer();
            loader.show();
            $.post('${pageContext.request.contextPath}/office/out/school/approval/reject/${activityId}', $requestData, function (data, status) {
                if ("success" === status) {
                    $.success('操作成功');
                    if (data==0) {
                        if (parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else {
                        $.error('操作失败');
                    }
                } else {
                    $.error("操作失败");
                }
                loader.close();
            });
        }
    }
</script>
</html>