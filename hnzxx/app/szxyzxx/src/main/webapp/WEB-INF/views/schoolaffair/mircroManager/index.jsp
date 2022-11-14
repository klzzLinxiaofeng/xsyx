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
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="微课管理" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        微课管理
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" class="a3"
                               onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
                                <a href="javascript:void(0)" onclick="loadCreatePage()" class="a4"><i
                                        class="fa fa-plus"></i>新增微课</a>
                            </c:if>
                        </p>
                    </h3>
                </div>
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="select_b">
                            <div class="select_div">
                                <span>标题: &nbsp;&nbsp;&nbsp;&nbsp; </span>
                                <input type="text" id="title" name="title"
                                       style="margin-bottom: 0; padding: 4px; width: 150px;" placeholder="请输入标题">
                            </div>

                            <div class="select_div">
                                <span>类别:  &nbsp;&nbsp;&nbsp;&nbsp;</span>
                                    <select name="typeId" id="typeId">
                                        <option value="">请选择</option> <!-- 这个option也可以写在ajax中-->
                                    </select>
                            </div>

                            <div class="select_div">
                                <span>年级:  &nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <select name="gradeId" id="gradeId">
                                    <option value="">请选择</option> <!-- 这个option也可以写在ajax中-->
                                </select>
                            </div>
                            <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                            <div class="clear"></div>
                        </div>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th>序号</th>
                                <th>标题</th>
                                <th>封面</th>

                                <th>类别</th>
                                <th>年级</th>
                                <th>上传时间</th>
                                <th>收藏数</th>
                                <th>点赞数</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="publicClass_list_content">
                            <jsp:include page="./list.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="publicClass_list_content"/>
                            <jsp:param name="url" value="/micro/management/index?sub=list&dm=${param.dm}"/>
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
        var title = $("#title").val();
        if (title != null && title != "") {
            val.title = title;
        }
        var typeId = $("#typeId option:selected").val();
        var gradeId = $("#gradeId option:selected").val();
        val.typeId = typeId;
        val.gradeId = gradeId;
        var id = "publicClass_list_content";
        var url = "/micro/management/index?sub=list&dm=${param.dm}";
        myPagination(id, val, url);
    }

    $(function () {
        // 获取类别
        $.ajax({
            async: false,
            type: "get",
            url: "${ctp}/micro/management/type",
            dataType: "json",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#typeId").append("<option value='"+data[i].id+"'>" + data[i].name + "</option>");
                }
            }
        });
        // 获取年级
        $.ajax({
            async: false,
            type: "get",
            url: "${ctp}/teach/grade/ztree/gradeList",
            dataType: "json",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#gradeId").append("<option value='"+data[i].id+"'>" + data[i].name + "</option>");
                }
            }
        });

        $("#typeId").on("change", function(){
            var va = $("#typeId option:selected").val();
            // 学校风采，对应所有课程
            if (va == "1"){
                $("#gradeId").attr("disabled","disabled");
                return;
            } else {
                $("#gradeId").removeAttr("disabled");
            }


        })
    })


    // 	加载创建对话框
    function loadCreatePage() {
        $.initWinOnTopFromLeft('新增微课', '${ctp}/micro/management/creator', '700', '650');
    }

    //  加载评论管理对话框
    function loadCommentsPage(id) {
        $.initWinOnTopFromLeft('评论管理', '${ctp}/micro/management/comment/index?dm=${param.dm}&id=' + id, '700', '650');
    }

    //  加载编辑对话框
    function loadEditPage(id) {
        $.initWinOnTopFromLeft('编辑', '${ctp}/micro/management/editor?id=' + id, '700', '650');
    }

    // 	删除对话框
    function deleteObj(obj, id) {
        $.confirm("确定执行此次操作？", function () {
            executeDel(obj, id);
        });
    }

    // 	执行删除
    function executeDel(obj, id) {
        $.post("${ctp}/micro/management/delete/" + id, {"_method": "delete"}, function (data, status) {
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

    //创建弹出框的筛选教师控件的回调函数
    function selectedHandler(data) {
        $(parent.window.frames[data.targetWindowName].document).find("#teacherId_select").val(data.names[0]);
        $(parent.window.frames[data.targetWindowName].document).find("#teacherId").val(data.ids[0]);
        $(parent.window.frames[data.targetWindowName].document).find("#teacherId").next("label").remove();
        $.success("设置成功");
        $.closeWindowByNameOnParent(data.windowName);
    }
</script>
</html>