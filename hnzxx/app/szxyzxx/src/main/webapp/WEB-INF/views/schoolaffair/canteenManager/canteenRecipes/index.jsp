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
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="食谱管理" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        食谱列表
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" class="a3"
                               onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
                                <a href="javascript:void(0)" class="a4"
                                   onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加食谱</a>
                            </c:if>
                        </p>
                    </h3>
                </div>
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="select_b">
                            <div class="select_div">

                                <span>日期：</span>
                                <input type="text" id="date" name="date"
                                       onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'date\')}'})"
                                       style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="2020-02-02"
                                       value="">

                            </div>
                            <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                            <button type="button" class="btn btn-primary" onclick="makeRecipes()">菜系预设</button>

                            <div class="clear"></div>
                        </div>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th>序号</th>
                                <th>日期</th>
                                <th>食谱</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="canteenRecipes_list_content">
                            <jsp:include page="./list.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="canteenRecipes_list_content"/>
                            <jsp:param name="url" value="/schoolaffair/recipes/index?sub=list&dm=${param.dm}"/>
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

    var count = 0;
    function time() {
        // $("#date").empty()
        if (count == 1) {
            $.get("${ctp}/schoolaffair/recipes/search", "", function (data) {
                var t = $.parseJSON(data)
                var s = "";
                for (var i = 0; i < t.canteenRecipes.length; i++) {
                    s += ' <option  value="' + t.canteenRecipes[i] + '">' + t.canteenRecipes[i] + '</option>'
                }
                $("#date").append(s)
            })
        }
        count++
    }

    function search() {
        var val = {};
        var date = $("#date").val();
        if (date != null && date != "") {
            val.date = date;
        }
        var id = "canteenRecipes_list_content";
        var url = "/schoolaffair/recipes/index?sub=list&dm=${param.dm}";

        myPagination(id, val, url);
    }

    // 菜系预设
    function makeRecipes() {
        $.initWinOnTopFromLeft('菜系预设', '/schoolaffair/recipes/goods/index?dm=${param.dm}', '1000', '550');
    }

    // 	加载创建对话框
    function loadCreatePage() {
        $.initWinOnTopFromLeft('创建', '${ctp}/schoolaffair/recipes/creator', '600', '400');
    }

    //  加载编辑菜单对话框  isCK 参数是 是否是参看
    function loadEditPage(id, isCK) {
        var mes = "编辑";
        if (isCK == 'disable') {
            mes = "详情";
        }
        $.initWinOnTopFromTop(mes, '${pageContext.request.contextPath}/schoolaffair/recipes/editor?id=' + id + '&isCK=' + isCK, '600', '400');
    }

    // 	删除对话框
    function deleteObj(obj, id) {
        $.confirm("确定执行此次操作？", function () {
            executeDel(obj, id);
        });
    }

    // 	执行删除
    function executeDel(obj, id) {
        $.post("${ctp}/schoolaffair/recipes/" + id, {"_method": "DELETE"}, function (data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("删除成功");
                    $("#" + id + "_tr").remove();
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            }
            window.location.reload();
        });
    }
</script>
</html>