<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

        .form-horizontal .controls #zp .img_1 a {
            position: absolute;
            font-size: 22px;
            font-weight: bold;
            color: #000;
            right: 0;
            top: 0;
            display: block;
            width: 16px;
            height: 16px;
            line-height: 16px;
            text-align: center;
            cursor: pointer;
        }
    </style>
</head>
<body style="background-color: #fff !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <input type="hidden" id="id" name="id" value="${inSchoolRoom.id}"/>
                <form class="form-horizontal tan_form" id="form_data">
                    <div class="control-group">
                        <label class="control-label"><span style="color:red">*</span>&nbsp;活动室名称：</label>
                        <div class="controls">
                            <input type="text" name="name" class="span13 {required:true,maxlength:20}" placeholder="" value="${inSchoolRoom.name}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">是否禁用：</label>
                        <div class="controls">
                            <select name="isDeleted" style="width:60px;height:32px">
                                <option value="0" <c:if test='${inSchoolRoom.isDeleted != null && !inSchoolRoom.isDeleted}'>selected</c:if>>否</option>
                                <option value="1" <c:if test='${inSchoolRoom.isDeleted != null && inSchoolRoom.isDeleted}'>selected</c:if>>是</option>
                            </select>
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
    let checker;
    $(function () {
        checker = initValidator();
    });

    function initValidator() {
        return $("#form_data").validate({
            errorClass: "myerror",
            rules: {},
            messages: {}
        });
    }

    // 保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            let loader = new loadLayer();
            let $id = $("#id").val();
            let $requestData = formData2JSONObj("#form_data")
            let url = "${pageContext.request.contextPath}/office/in/school/room/" + $id;
            if ("" === $id) {
                url = "${pageContext.request.contextPath}/office/in/school/room/input";
            }
            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        if (parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else {
                        if (data.responseData && data.responseData.msg) {
                            $.error(data.responseData.msg);
                        } else {
                            $.error("操作失败");
                        }
                    }
                } else {
                    $.error("提交失败");
                }
                loader.close();
            });
        }
    }

    function Change(obj) {
        let imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }

    function reMove(obj) {
        $(obj).parent().remove();
    }
</script>
</html>