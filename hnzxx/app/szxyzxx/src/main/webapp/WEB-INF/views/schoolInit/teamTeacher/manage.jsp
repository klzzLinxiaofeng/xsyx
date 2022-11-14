<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
    <title>教师任课管理</title>
</head>
<body style="background-color: #e3e3e3;">
<div class="row-fluid">
<div class="span12">
    <div class="content-widgets white">
        <div class="widget-head">
            <h3 style="height: 20px;"></h3>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="select_c" style="margin: 5px 20px;">
                    <c:forEach items="${gradeList}" var="grade" varStatus="status">
                        <a data-obj-id="${grade.id}" data-obj-name="${grade.name}" href="javascript:void(0)"
                           <c:if test='${status.index==0}'>class="on"</c:if> onclick="find('${grade.id}')">${grade.fullName}</a>
                    </c:forEach>
                </div>

                <div class="ks_list" style="padding:0;margin: 10px 20px;">
                    <div class="all_table" id="manage_list">
                        <jsp:include page="./manage_list.jsp"/>
                    </div>
                    <%--<div class="page_div">--%>
                        <%--<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">--%>
                            <%--<jsp:param name="id" value="manage_list" />--%>
                            <%--<jsp:param name="url" value="/team/teacher/init/manage/list" />--%>
                            <%--<jsp:param name="pageSize" value="${page.pageSize}" />--%>
                        <%--</jsp:include>--%>
                        <%--<div class="clear"></div>--%>
                    <%--</div>--%>
                </div>

            </div>
        </div>
    </div>
</div>
</div>
</body>
<script>
    $(".select_c a").click(function () {
        $(".select_c a").removeClass("on");
        $(this).addClass("on");
//        var gradeId = $(this).data("obj-id");
//        find(gradeId);
    });

    $(function () {
        find($(".select_c .on").data("obj-id"));
    });

    function find(gradeId) {
        var val = {
            "gradeId" : gradeId
        }
        var url = "/team/teacher/init/manage/list";
        var id = "manage_list";
//        myPagination(id, val, url);
        var loader = new loadDialog();
        loader.show();
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                $("#manage_list").html("").html(data);
                var iframeHeight=$('body').height();
                $("#iframe_sencond",window.parent.document).height(iframeHeight+'px');
                $("#iframe_first",window.parent.parent.document).height((iframeHeight+71)+'px');
                loader.close();
            }
        });
    }

    function addPage(obj) {
        var $tr = $(obj).parent().parent();

        var tr_index = $tr.index();
        var $tr_o = $(".table1 tbody tr").eq(tr_index);
        var gradeId = $tr_o.data("grade-id");
        var teamId = $tr_o.data("team-id");

        var td_index = $(obj).parent().index();
        var $th = $tr.parent().prev().find("th")[td_index];

        var subjectCode = $($th).data("code");
        var type = $($th).data("type");
        var subjectName = $($th).text();

        $.initWinOnTopFromLeft_qyjx("新增任课教师",  '${pageContext.request.contextPath}/team/teacher/init/manage/edit?gradeId='+ gradeId +
            '&teamId=' + teamId + '&subjectCode=' + subjectCode + '&subjectName=' + subjectName + '&type=' + type, '713', '619');
    }

    function editPage(id) {
        $.initWinOnTopFromLeft_qyjx("编辑任课教师",  '${pageContext.request.contextPath}/team/teacher/init/manage/edit?id='+id, '713', '619');
    }

    function deletePage(id) {
        $.post("${pageContext.request.contextPath}/tmp/team/teacher/delete", {"teamTeacherId":id}, function(data, status) {
            if ("success" === status) {
                if("success" === data) {
                    $.success("删除成功");
                    find($(".select_c a.on").data("obj-id"));
                } else {
                    $.error("删除失败");
                }
            } else {
                $.error("服务器异常");
            }
        });
    }
</script>
</html>
