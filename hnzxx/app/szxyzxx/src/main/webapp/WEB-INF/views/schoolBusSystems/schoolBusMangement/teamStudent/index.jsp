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
                <div class="widget-head">

                </div>
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <form action="${ctp}/teach/teamStudent/downLoadTeamStudent" method="post"
                              style="position:relative;">
                            <div class="select_b">
                                <div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select"
                                                                                style="width:120px;"
                                                                                disabled="disabled"></select></div>
                                <div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select"
                                                                                style="width:120px;"></select></div>
                                <div class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select"
                                                                                style="width:120px;"></select></div>
                                <div class="select_div"><span>学生姓名：</span><input type="text" id="name" name="name"
                                                                                 class="chzn-select"
                                                                                 style="width:120px;"></div>
                                <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                                <button type="button" class="btn btn-blue" onclick="batchAdd()">批量添加</button>
                                <div class="clear"></div>
                            </div>
                        </form>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th><input type="checkbox" id="checkAll"></th>
                                <th>序号</th>
                                <%-- 对应要发给校车的学生id --%>
                                <th>学生ID</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>家长姓名</th>
                                <th>家长手机号码</th>
                                <th>卡号</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="teamStudent_list_content">
                            <jsp:include page="./list.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="teamStudent_list_content"/>
                            <jsp:param name="url" value="/teach/teamStudent/indexStu?sub=list&dm=${param.dm}"/>
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
    var systemId = "${systemId}";
    $(function () {
        initCheckBtn("jc_subject");
        $.initCascadeSelector({
            "type": "team", "yearCallback": function ($this) {
                search()
            }
        });
    });


    function initCheckBtn() {
        $("#checkAll").on("click", function () {
            $("#teamStudent_list_content input:checkbox[name='ids']").prop(
                "checked", this.checked);
        });
    }

    function search() {

        var val = {
            "schoolYear": $("#xn").val(),
            "gradeId": $("#nj").val(),
            "teamId": $("#bj").val(),
            "name": $("#name").val(),
            "pattern": true
        };
        var id = "teamStudent_list_content";
        var url = "/teach/teamStudent/indexStu?sub=list&dm=${param.dm}&systemId=" + systemId;
        myPagination(id, val, url);
    }


    // 批量添加
    function batchAdd() {
        var checked = $("#teamStudent_list_content input:checkbox[name='ids']:checked");
        if (checked != null && checked.length > 0) {
            var ids = new Array();
            var requestData = {};
            $.each(checked, function (index, value) {
                ids.push($(value).val());
            });
            requestData.ids = ids;
            var idss = ids.join(",");
            $.post("${ctp}/schoolBus/management/batchAdd", {ids: idss}, function (data, status) {
                if ("success" === status) {
                    if ("success" === data) {
                        search();
                        $.success("添加成功");
                    } else if ("fail" === data) {
                        $.error("添加失败失败，系统异常", 1);
                    }
                }
            });

        } else {
            $.error("您未选中任何用户");
        }
    }


    // 	添加
    function addObj(id) {
        $.post("${ctp}/schoolBus/management/batchAdd", {ids: id}, function(data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("添加成功");
                    search();
                } else if ("fail" === data) {
                    $.error("添加失败，系统异常", 1);
                }
            }
        });
    }


</script>
</html>