<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
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
                        <button type="button" class="btn btn-primary" onclick="selectedCx()">选择</button>
                    </div>
                    <div class="widget-container">
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th><input type="checkbox" id="checkAll"></th>
                                <th>序号</th>
                                <th>名称</th>
                                <th>图片</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="goods_list_content">
                            <jsp:include page="./goodsList.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="goods_list_content"/>
                            <jsp:param name="url" value="/schoolaffair/recipes/goods/index?sub=list&dm=${param.dm}"/>
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


    function selectedCx() {
        var checked = $("#goods_list_content input:checkbox[name='ids']:checked");
        if (checked != null && checked.length > 0) {
            var names = "";
            var ids = "";
            for (var i = 0; i < checked.length; i++) {
                names += $(checked[i]).parent().parent().find("td").eq(2).text();
                ids += $(checked[i]).val();
                if (i != checked.length - 1) {
                    names += ",";
                    ids += ",";
                }
            }

            console.log('${jump_id}')

            var jumpId = '${jump_id}';
            var path = "/schoolaffair/recipes/creator";
            if (jumpId != "" && jumpId != null && jumpId != undefined) {
                path = "/schoolaffair/recipes/editor";
            }
            /*console.log(path);*/
            findWindowByPathName(parent, path).cxSelected(names, ids);
        } else {
            layer.msg("请先选择");
        }

    }


    function findWindowByPathName(parent, pathName) {
        for (var i = 0; i < parent.length; i++) {
            if (parent[i].pathname == pathName) {
                return parent[i];
            }
        }
        return null;
    }


    //创建弹出框的筛选食谱控件的回调函数
    function selectedHandler(data) {
        $(parent.window.frames[data.targetWindowName].document).find("#goodsId_select").val(data.names[0]);
        $(parent.window.frames[data.targetWindowName].document).find("#goodsId").val(data.ids[0]);
        $(parent.window.frames[data.targetWindowName].document).find("#goodsId").next("label").remove();
        $.success("设置成功");
        $.closeWindowByNameOnParent(data.windowName);
    }

    // 批量删除
    function delMore() {
        var checked = $("#goods_list_content input:checkbox[name='ids']:checked");
        if (checked != null && checked.length > 0) {
            var ids = new Array();
            var requestData = {};
            $.each(checked, function (index, value) {
                ids.push($(value).val());
            });
            requestData.ids = ids;
            var idss = ids.join(",");
            $.post("${ctp}/schoolaffair/recipes/goods/delete", {ids: idss}, function (data, status) {
                if ("success" === status) {
                    if ("success" === data) {
                        $.success("删除成功");
                        window.top.reload();
                    } else if ("fail" === data) {
                        $.error("删除失败，系统异常", 1);
                    }
                }
            });

        } else {
            $.error("您未选中任何菜系");
        }
    }

    function initCheckBtn() {
        $("#checkAll").on("click", function () {
            $("#goods_list_content input:checkbox[name='ids']").prop(
                "checked", this.checked);
        });
    }

    // 	执行删除
    function executeDel(obj, id) {
        $.post("${ctp}/schoolaffair/recipes/goods/delete", {ids: id}, function (data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("删除成功");
                    $("#" + id + "_tr").remove();
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常", 0);
                }
            }
        });
    }

    // 	删除对话框
    function deleteObj(obj, id) {
        $.confirm("确定执行此次操作？", function () {
            executeDel(obj, id);
        });
    }


    //  加载编辑对话框
    function loadEditPage(id) {
        $.initWinOnTopFromLeft('编辑', '${ctp}/schoolaffair/recipes/goods/editor?id=' + id, '800', '450');
    }


    // 	加载创建对话框
    function loadCreatePage() {
        $.initWinOnTopFromLeft('编辑', '${ctp}/schoolaffair/recipes/goods/creator', '800', '450');
    }
</script>
</html>