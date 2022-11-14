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
                   <%-- <div class="select_b">
                        <button type="button" class="btn btn-primary" onclick="delMore()">批量删除</button>
                        <button type="button" class="btn btn-primary" onclick="loadCreatePage()">添加</button>
                    </div>--%>
                    <div class="widget-container">
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th><input type="checkbox" id="checkAlls"></th>
                                <th>序号</th>
                                <th>年级</th>
                                <th>是否开启选课</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="time_list_content">
                            <jsp:include page="./list.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="time_list_content"/>
                            <jsp:param name="url" value="/teach/publicClass/indexKeXuan?sub=list"/>
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
    function search() {
        var val = {};
        var id = "time_list_content";
        var url = "/teach/publicClass/indexKeXuan?sub=list";
        myPagination(id, val, url);
    }
  $(function () {
        search();
  })
    // 	修改对话框
    function updateComment(obj, id) {
        $.confirm("确定执行此次操作？", function () {
            executeDel(obj, id);
        });
    }
    function executeDel(id,zhangtai) {
        $.get("${ctp}/teach/publicClass/updateKeXuanKe?gradeId="+id+"&zhuangtai="+zhangtai, function (data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                if ("success" === data.info) {
                    $.success("修改成功");
                    search();
                } else if ("fail" === data) {
                    $.error("修改失败，系统异常", 1);
                }
            }
        });
    }


</script>
</html>