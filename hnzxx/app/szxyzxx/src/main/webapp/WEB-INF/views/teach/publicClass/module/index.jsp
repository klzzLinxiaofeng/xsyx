<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title></title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="select_b">
                        <button type="button" class="btn btn-primary" onclick="delMore()">批量删除</button>
                        <button type="button" class="btn btn-primary" onclick="loadCreatePage()">添加</button>
                    </div>
                    <div class="widget-container">
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th><input type="checkbox" id="checkAll"></th>
                                <th>序号</th>
                                <th>老师</th>
                                <th>图片</th>
                                <th>简介</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="teacher_list_content">
                            <jsp:include page="./teacherList.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="teacher_list_content"/>
                            <jsp:param name="url" value="/teach/publicClass/teacher/index?sub=list&dm=${param.dm}"/>
                            <jsp:param name="pageSize" value="${page.pageSize}"/>
                        </jsp:include>

                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        initCheckBtn("jc_subject");
    })

    //创建弹出框的筛选教师控件的回调函数
    function selectedHandler(data) {
        $(parent.window.frames[data.targetWindowName].document).find("#teacherId_select").val(data.names[0]);
        $(parent.window.frames[data.targetWindowName].document).find("#teacherId").val(data.ids[0]);
        $(parent.window.frames[data.targetWindowName].document).find("#teacherId").next("label").remove();
        $.success("设置成功");
        $.closeWindowByNameOnParent(data.windowName);
    }

    // 批量删除
    function delMore() {
        var checked = $("#teacher_list_content input:checkbox[name='ids']:checked");
        if (checked != null && checked.length > 0) {
            var ids = new Array();
            var requestData = {};
            $.each(checked, function (index, value) {
                ids.push($(value).val());
            });
            requestData.ids = ids;
            var idss = ids.join(",");
            $.post("${ctp}/teach/publicClass/teacher/delete", {ids: idss}, function (data, status) {
                if ("success" === status) {
                    if ("success" === data) {
                        $.success("删除成功");
                        window.location.reload();
                    } else if ("fail" === data) {
                        $.error("删除失败，系统异常", 1);
                        window.location.reload();
                    }
                }
            });

        } else {
            $.error("您未选中任何用户");
        }
    }

    function initCheckBtn() {
        $("#checkAll").on("click", function () {
            $("#teacher_list_content input:checkbox[name='ids']").prop(
                "checked", this.checked);
        });
    }

    // 	执行删除
    function executeDel(obj, id) {
        $.post("${ctp}/teach/publicClass/teacher/delete", {ids: id}, function (data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("删除成功");
                    $("#" + id + "_tr").remove();
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            }
        });
    }

    // 	删除对话框
    function deleteComment(obj, id) {
        $.confirm("确定执行此次操作？", function () {
            executeDel(obj, id);
        });
    }


    //  加载编辑对话框
    function loadEditPage(id) {
        $.initWinOnTopFromLeft('编辑', '${ctp}/teach/publicClass/teacher/editor?id=' + id, '800', '450');
    }


    // 	加载创建对话框
    function loadCreatePage() {
        $.initWinOnTopFromLeft('添加', '${ctp}/teach/publicClass/teacher/creator', '800', '450');
    }
</script>
</html>