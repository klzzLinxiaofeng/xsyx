<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title></title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="收费项目" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        收费项目列表
                        <p class="btn_link" style="float: right;">
                            <%--<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">--%>
                                <a href="javascript:void(0)" class="a4" onclick="loadCreatePage();"><i class="fa fa-plus"></i>创建项目</a>
                            <%--</c:if>--%>
                        </p>
                    </h3>
                </div>
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="select_b">
                            <div class="select_div">
                                <span>项目名称：</span>
                                <input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
                            </div>
                            <button type="button" class="btn btn-primary" onclick="search()">搜索</button>
                            <div class="clear"></div>
                        </div>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th>序号</th>
                                <th>项目名称</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="charge_list_content">
                            <jsp:include page="./item_list.jsp" />
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="charge_list_content" />
                            <jsp:param name="url" value="/dn/charge/item/index?sub=list&dm=${param.dm}" />
                            <jsp:param name="pageSize" value="${page.pageSize}" />
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
    function search() {
        var val = {};
        var name = $("#name").val();
        if (name != null && name != "") {
            val.name = name;
        }
        var id = "charge_list_content";
        var url = "${ctp}/dn/charge/item/index?sub=list&dm=${param.dm}";
        myPagination(id, val, url);
    }

    // 	加载创建对话框
    function loadCreatePage() {
        $.initWinOnTopFromLeft('添加项目', '${ctp}/dn/charge/item/creator', '500', '220');
    }
    //  加载编辑对话框
    function loadEditPage(id) {
        $.initWinOnTopFromLeft('编辑项目', '${ctp}/dn/charge/item/editor?id=' + id, '500', '220');
    }


    // 	删除对话框
    function deleteObj(obj, id) {
        $.confirm("确定执行此次操作？", function() {
            executeDel(obj, id);
        });
    }

    // 	执行删除
    function executeDel(obj, id) {
        $.post("${ctp}/dn/charge/item/" + id, {"_method" : "delete"}, function(data, status) {
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
</script>
</html>