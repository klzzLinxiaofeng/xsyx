<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">

    <title></title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="开课管理" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        开课管理列表
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" class="a3"
                               onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
                                <a href="javascript:void(0)" class="a4"
                                   onclick="loadCreatePage();"><i class="fa fa-plus"></i>开课</a>
                            </c:if>
                        </p>
                    </h3>
                </div>
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="select_b">
                            <div class="select_div">
                                <span>开始上课日期：</span>
                                <input type="text" id="startDate" name="startDate" autocomplete="off"
                                       onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})"
                                       style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="2015-09-01"
                                       value="">
                                &nbsp;-&nbsp;
                                <input type="text" id="endDate" name="endDate" autocomplete="off"
                                       onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})"
                                       style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="2015-09-05"
                                       value="">
                            </div>
                            <div class="select_div">
                                <span>关键字：</span>
                                <input type="text" id="keyword" name="keyword"
                                       style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="课程名称"
                                       value="">
                            </div>
                            <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                            <button type="button" class="btn btn-primary" onclick="teacher()">教师预设</button>
                            <button type="button" class="btn btn-primary" onclick="classTime()">上课时间预设</button>
                            <button type="button" class="btn btn-primary" onclick="openClass()">开放可选课的年级</button>
                            <button type="button" class="btn btn-primary" onclick="exports()">导出</button>


                            <div class="clear"></div>
                        </div>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th>序号</th>
                                <th>课程名称</th>
                                <th>课程封面</th>
                                <th>年级</th>
                                <th>上课教师</th>
                                <th>上课时间</th>
                                <th>选课开始日期</th>
                                <th>课程总节数</th>
                                <th>人数上限</th>
                                <th>已报名人数</th>
                                <th>选课截止日期</th>
                                <th>课程类型</th>
                                <th>课程费用</th>
                                <th>材料费用</th>
                                <th>课程类别</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="publicClass_list_content">
                            <jsp:include page="./list.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="publicClass_list_content"/>
                            <jsp:param name="url" value="/teach/publicClass/index?sub=list&dm=${param.dm}"/>
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
        var keyword = $("#keyword").val();
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        if (keyword != null && keyword != "") {
            val.keyword = keyword;
        }
        if (startDate != null && startDate != "") {
            val.startDate = startDate;
        }
        if (endDate != null && endDate != "") {
            val.endDate = endDate;
        }
        var id = "publicClass_list_content";
        var url = "/teach/publicClass/index?sub=list&dm=${param.dm}";
        myPagination(id, val, url);
    }

    // 新增老师详细信息
    function teacher() {
        $.initWinOnTopFromLeft('教师预设', '/teach/publicClass/teacher/index?dm=${param.dm}', '1000', '550');
    }

    // 新增开课日期标签
    function classTime() {
        $.initWinOnTopFromLeft('选课教师', '/teach/publicClass/class/index?dm=${param.dm}', '1000', '550');
    }
    // 可选可课设置
    function openClass() {
        $.initWinOnTopFromLeft('设置可选课年级', '/teach/publicClass/indexKeXuan?sub=index', '1000', '550');
    }


    // 导出功能
    function exports() {
        $.initWinOnTopFromLeft('导出', '/teach/publicClass/export?dm=${param.dm}', '500', '400');
    }

    // 	加载创建对话框
    function loadCreatePage() {
        $.initWinOnTopFromLeft('创建', '${ctp}/teach/publicClass/creator', '1000', '550');
    }

    //  加载编辑对话框
    function loadEditPage(id) {
        $.initWinOnTopFromLeft('编辑', '${ctp}/teach/publicClass/editor?id=' + id, '1000', '550');
    }

    function loadViewerPage(id) {
        $.initWinOnTopFromLeft('详情', '${ctp}/teach/publicClass/viewer?id=' + id, '1000', '550');
    }

    //查看已报名学生信息
    function checkEnrollStudent(id) {
        $.initWinOnTopFromLeft('已报名学生', '${ctp}/teach/publicClass/checkEnrollStu?id=' + id, '900', '600');
    }

    // 	删除对话框
    function deleteObj(obj, id) {
        $.confirm("确定执行此次操作？", function () {
            executeDel(obj, id);
        });
    }
    // 	执行删除
    function executeDel(obj, id) {
        $.post("${ctp}/teach/publicClass/" + id, {"_method": "delete"}, function (data, status) {
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
        $(parent.window.frames[data.targetWindowName].document).find("#teacherId_select").val(data.names);
        $(parent.window.frames[data.targetWindowName].document).find("#teacherId").val(data.ids);
        $(parent.window.frames[data.targetWindowName].document).find("#teacherId").next("label").remove();
        $.success("设置成功");
        $.closeWindowByNameOnParent(data.windowName);
    }

    function selectedTimeHandler(data) {
        $(parent.window.frames[data.targetWindowName].document).find("#timeId_select").val(data.names);
        $(parent.window.frames[data.targetWindowName].document).find("#timeId").val(data.ids);
        $(parent.window.frames[data.targetWindowName].document).find("#timeId").next("label").remove();
        $.success("设置成功");
        $.closeWindowByNameOnParent(data.windowName);
    }
</script>
</html>