<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title>社会责任指标管理</title>
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
                                <th><input type="checkbox" id="checkAlls"></th>
                                <th>序号</th>
                                <th>指标名称</th>
                                <th>初始分值</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="time_list_content">
                            <jsp:include page="./list.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="time_list_content"/>
                            <jsp:param name="url" value="/socialIndicator/findByAll?sub=list"/>
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
    function selectedHandler(data) {
        $(parent.window.frames[data.targetWindowName].document).find("#teacherId_select").val(data.names[0]);
        $(parent.window.frames[data.targetWindowName].document).find("#teacherId").val(data.ids[0]);
        $(parent.window.frames[data.targetWindowName].document).find("#teacherId").next("label").remove();
        $.success("设置成功");
        $.closeWindowByNameOnParent(data.windowName);
    }

    // 批量删除
    function delMore() {
        var checked = $("#time_list_content input:checkbox[name='ids']:checked");
        if (checked != null && checked.length > 0) {
            var ids = new Array();
            var requestData = {};
            $.each(checked, function (index, value) {
                ids.push($(value).val());
            });
            requestData.ids = ids;
            var idss = ids.join(",");
            $.post("${ctp}/socialIndicator/delete", {ids: idss}, function (data, status) {
                if ("success" === status) {
                    if ("success" === data) {
                        $.success("删除成功");
                        for(var i=0;i<ids.length;i++){
                            $("#" + ids[i] + "_tr").remove();
                        }
                    } else if ("fail" === data) {
                        $.error("删除失败，系统异常", 1);
                    }
                }
            });
        } else {
            $.error("您未选中任何用户");
        }
    }

    function initCheckBtn() {
        $("#checkAlls").on("click", function () {
            $("#time_list_content input:checkbox[name='ids']").prop(
                "checked", this.checked);
        });
    }

    // 	执行删除
    function executeDel(obj, id) {
        $.post("${ctp}/socialIndicator/delete", {ids: id}, function (data, status) {
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
        $.initWinOnTopFromLeft('编辑', '${ctp}/socialIndicator/addOrupdateView?id=' + id, '800', '450');
    }


    // 	加载创建对话框
    function loadCreatePage() {
        $.initWinOnTopFromLeft('添加', '${ctp}/socialIndicator/addOrupdateView', '800', '450');
    }


</script>
</html>