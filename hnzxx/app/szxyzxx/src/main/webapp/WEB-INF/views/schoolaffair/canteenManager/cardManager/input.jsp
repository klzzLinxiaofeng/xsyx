<%@ page language="java"
         import="platform.education.user.contants.UserContants"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>添加食堂</title>
    <%@ include file="/views/embedded/common.jsp" %>
    <style>
        .row-fluid .span13 {
            width: 75%;
        }

        .myerror {
            color: red !important;
            width: 34%;
            display: inline-block;
            padding-left: 10px;
        }

        .row-fluid .span4 {
            width: 227px;
        }

        .row-fluid .span2 {
            display: inline-block;
            width: 100px;
        }
    </style>
</head>
<body style="background-color: #F3F3F3 !important">
<div class="row-fluid ">
    <div class="span12">
        <div style="margin-bottom: 0" class="content-widgets">
            <div style="padding: 20px 0 0;" class="widget-container">
                <form class="form-horizontal" id="canteen_form"
                      action="javascript:void(0);">
                    <input type="hidden" name="userType" value="${canteen.userType}">
                    <input type="hidden" name="userId" value="${canteen.userId}">
                    <input type="hidden" name="oldIcNumber" value="${canteen.oldIcNumber}">
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>姓名</label>
                        <div class="controls">
                            <input type="text" id="name" name="name" class="span4"
                                   value="${canteen.name}" disabled>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>班级/部门</label>
                        <div class="controls">
                            <input type="text" id="teamName" name="teamName" class="span4" value="${canteen.teamName}"
                                   disabled>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>补卡理由</label>
                        <div class="controls">
                            <input type="text" id="mesg" name="mesg" class="span4" value="${canteen.mesg}" disabled>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">最新卡号</label>
                        <div class="controls">
                            <input type="text" id="newIcNumber" name="newIcNumber" class="span4"
                                   placeholder="请输入最新卡号" value="">
                        </div>
                    </div>

                    <div class="form-actions tan_bottom"
                         style="padding-left: 0; background-color: #eee; text-align: center">
                        <c:if test="${isCK == null || isCk == ''}">
                            <input type="hidden" id="id" name="id" value="${canteen.id}"/>
                            <button class="btn btn-warning" type="button"
                                    onclick="saveOrUpdate();">确定
                            </button>
                        </c:if>
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
    })

    function initValidator() {
        return $("#canteen_form").validate({
            errorClass: "myerror",
            rules: {
                "newIcNumber": {
                    required: true,
                    stringCheck: false,
                    isContainsSpecialChar: false
                }
            },
            messages: {}
        });
    }

    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            var $id = $("#id").val();
            var $requestData = formData2JSONObj("#canteen_form");
            var url = "${pageContext.request.contextPath}/canteen/management/updateCar";
            var loader = new loadLayer();
            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    if ("success" === data) {
                        if (parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else {
                        $.error(data);
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