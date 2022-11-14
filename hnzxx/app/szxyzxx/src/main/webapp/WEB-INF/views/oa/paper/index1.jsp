<%@ page language="java"
         import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css"
          rel="stylesheet">
    <title>公文</title>
    <script type="text/javascript">
        $(function () {
            $(".sq_list .clsq ul li .caozuo .delete").click(function () {
                $(this).parent().parent().remove()
            });
            $(".oa_top .top_ul li a").click(function () {
                $(".oa_top .top_ul li a").removeClass("on");
                $(this).addClass("on");
                var i = $(this).parent().index();
                $(".sq_list").hide();
                $(".sq_list").eq(i).show();
            })
            /* $(".entry ul li a").click(function() {
                $(".entry ul li ").parent().removeClass("on");
                $(this).parent().addClass("on");
                var j = $(this).parent().index();
            }); */

            addClass_On();
        })
    </script>
</head>
<body>
<input type="hidden" id="dep"/>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="公文" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="oa_top">
                    <ul class="top_ul">
                        <li><a href="javascript:void(0)" id="related_a"
                               onclick="searchByCondition('related')">与我相关</a></li>
<%--                        <c:if test="${noDepartment==null || noDepartment==''}">--%>
<%--                            <li><a href="javascript:void(0)" id="department_a"--%>
<%--                                   onclick="searchByCondition('department')">部门公文</a></li>--%>
<%--                        </c:if>--%>
                        <li><a href="javascript:void(0)" id="all_a"
                               onclick="searchByCondition('all')">全部公文</a></li>
                        <li><a href="javascript:void(0)" id="own_a"
                               onclick="searchByCondition('own')">我发布的</a></li>
                    </ul>
                </div>
                <div class="wy_all">
                    <div class="sq_list">
                        <div class="search_1">
                            <input type="text" placeholder="标题/发布人/摘要" id="searchWord">
                            <a class="sea_s" href="javascript:void(0)" onclick="ss();"><i
                                    class="fa fa-search"></i></a>
                        </div>

                        <div id="paper_list1">
                            <jsp:include page="./list1.jsp"/>
                        </div>
                        <!--分页 -->
                    </div>
                </div>
                <div style="padding-right: 14px;">
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="paper_list1"/>
                        <jsp:param name="url"
                                   value="/office/paper/index1?sub=list1&dm=${param.dm}&type=${type}&departmentId=${departmentId }"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function searchByCondition(type) {
        $("#searchWord").val("");
        var depart = $("#depart").val();
        window.location.href = "${ctp}/office/paper/index1?dm=${param.dm}&type="
            + type + "&departmentId=" + depart;
    }

    function ss() {
        $.ajaxSetup({
            async: false
        });
        var type = $("#type").val();
        var clickId = '';
        if (type == "all") {
            clickId = $("#departId").val();
            $("#dep").val(clickId);
        }
        var val = {
            "searchWord": $("#searchWord").val()
        };

        var id = "paper_list1";
        var url = "/office/paper/index1?sub=list1&dm=${param.dm}&type="
            + type + "&departmentId=" + clickId;
        myPagination(id, val, url);
        if ($("#dep").val() != "") {
            $(".li").removeClass("on");
        }
        if (clickId != '') {
            $(".li_" + clickId).addClass("on");
        }
    }

    function addClass_On() {
        var type = $("#type").val();
        if (type == "related") {
            $("#related_a").addClass("on");
        } else if (type == "department") {
            $("#department_a").addClass("on");
        } else if (type == "own") {
            $("#own_a").addClass("on");
        } else {
            $("#all_a").addClass("on");
        }
    }

    //加载公文发布对话框
    function loadCreatePage() {
        window.location.href = "${ctp}/office/paper/creator1";
    }

    //加载公文编辑对话框
    function loadEditPage(id) {
        window.location.href = "${ctp}/office/paper/editor1?id=" + id;
    }

    //加载查看公文对话框
    function loadViewerPage(id) {
        window.location.href = "${ctp}/office/paper/viewer1?id=" + id;
    }

    //加载 删除公文对话框
    function deleteObj(obj, id) {
        $.confirm("确定执行此次操作？", function () {
            executeDel(obj, id);
        });
    }

    //执行删除
    function executeDel(obj, id) {
        $.post("${pageContext.request.contextPath}/office/paper/" + id, {
            "_method": "delete"
        }, function (data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("删除成功");
                    $("#li_" + id).remove();
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            }
            window.location.reload();
        });
    }

    //需要查询全部公文下的部门的人，可以在路径中加上当前点击的部门的相关参数就可以了
    function departmentSearch(departmentId, obj) {
        if (departmentId == 'all') {
            departmentId = '';
        }
        var type = $("#type").val();
        var val = {
            "searchWord": $("#searchWord").val()
        };
        $("#dep").val(departmentId);
        var id = "paper_list1";
        var url = "/office/paper/index1?sub=list1&dm=${param.dm}&type=" + type + "&departmentId=" + departmentId;
        $.ajaxSetup({
            async: false
        });
        myPagination(id, val, url);
        $("#departId").val(departmentId);
        var dep = $("#departId").val();
        var i = $(obj).parent().index() / 3;
        $(".li").removeClass("on")
        $(".li").eq(i).addClass("on");
    }

</script>

</html>