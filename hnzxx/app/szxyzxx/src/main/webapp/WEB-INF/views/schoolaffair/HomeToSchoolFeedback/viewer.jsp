<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>

    <title></title>

</head>
<body style="background-color: cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 20px">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="feedback_form" action="javascript:void(0);">
                    <input type="hidden" id="id" name="id" value="${entity.id}">
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            时间：
                        </label>
                        <div class="controls">
                            <input type="text" id="createDate" name="createDate" class="span4" placeholder=""
                                   value='<fmt:formatDate pattern="yyyy-MM-dd"  value="${entity.createDate}"></fmt:formatDate>'
                                   style="width: 70%" disabled>


                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            班级：
                        </label>
                        <div class="controls">
                            <input type="text" id="className" name="className" class="span4" placeholder=""
                                   value="${entity.className}" style="width: 70%" disabled>
                        </div>
                    </div>

                   <%-- <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            姓名：
                        </label>
                        <div class="controls">
                            <input type="text" id="name" name="name" class="span4" placeholder=""
                                   value="${entity.name}" style="width: 70%" disabled>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            家长姓名：
                        </label>
                        <div class="controls">
                            <input type="text" id="parentName" name="parentName" class="span4" placeholder=""
                                   value="${entity.parentName}" style="width: 70%" disabled>
                        </div>
                    </div>--%>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            家长电话：
                        </label>
                        <div class="controls">
                            <input type="text" id="phone" name="phone" class="span4" placeholder=""
                                   value="${entity.phone}" style="width: 70%" disabled>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            反馈内容：
                        </label>
                        <div class="controls">
                            <input type="text" id="content" name="content" class="span4" placeholder=""
                                   value="${entity.content}" style="width: 70%" disabled>
                        </div>

                    </div>


                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            图片说明：
                        </label>
                        <div class="controls">
                            <c:if test="${entity.picUrl != null&& entity.picUrl != ''}">
                                <img src="${entity.picUrl}" onclick="Change(this);"
                                     style="width: 260px; height: 130px;">
                            </c:if>
                            <c:if test="${entity.picUrl2 != null&& entity.picUrl2 != ''}">
                                <img src="${entity.picUrl2}" onclick="Change(this);"
                                     style="width: 260px; height: 130px;">
                            </c:if>
                            <c:if test="${entity.picUrl3 != null&& entity.picUrl3 != ''}">
                                <img src="${entity.picUrl3}" onclick="Change(this);"
                                     style="width: 260px; height: 130px;">
                            </c:if>

                        </div>
                    </div>


                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            回复：
                        </label>
                        <div class="controls">
                            <%--
                            1 已回复
                            0 未回复
                            --%>
                            <input type="text" id="content" name="content" class="span4" placeholder=""
                                   value="${entity.remark}" style="width: 70%">
                        </div>
                    </div>
                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${content.id}"/>
                        <c:if test="${isCK == null || isCK == ''}">
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

    });

    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }


    function reMove(obj) {
        $(obj).parent().remove();
        $("#uuid").val("");
        $("#zp").append('<div class="img_1"><img src="${ctp}/res/images/no_picture.jpg" onclick="Change(this);" style="width: 233px; height: 130px;"></div>');
    }

    /*//获取文件的缩略图路径
    function getFileUrls() {
        var imgs = $(".img_1");
        var urls = "";
        $.each(imgs, function (index, value) {
            urls += ($(value).find("img").attr("src") + ",");
        });
        if (urls != "") {
            urls = urls.substring(0, urls.length - 1);
        }
        return urls;
    }*/


    function initValidator() {
        return $("#feedback_form").validate({
            errorClass: "myerror",
            rules: {

                "content": {
                    required: true
                }
            },
            messages: {}
        });
    }


    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            var loader = new loadLayer();
            var $id = $("#id").val();
            //var $type = $("#type").val();
            var $requestData = formData2JSONObj("#feedback_form");
            $requestData.id = $("#id").val();

            var edit = '${edit}';
            var url = "${ctp}/schoolaffair/htsFeedback/create";
            if (edit != null && edit != undefined && edit != "") {
                $requestData._method = "put";
                url = "${ctp}/schoolaffair/htsFeedback/edit";
            }


            loader.show();
            console.info($requestData)
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    $.success('操作成功');
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        if (parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else {
                        $.error("操作失败");
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
