<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/szxy/css/szxy_common.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/szxy/css/banji_info_manage.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <title>班级信息管理</title>
    <style>
        .table thead tr th:first-child{
            padding-left: 33px;
        }
    </style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
    <p class="top_link">智核 >  班级信息管理  > <span>设置班级管理</span></p>
    <div class="content_main white">
        <div class="content_top">
            <div class="f_left"><p>设置班级管理</p></div>
            <div class="f_right"><button class="btn btn-blue" onclick="addBjController()"><i class="jia "></i>新增班级管理者</button></div>
        </div>
        <div class="banji_info_top">
            <ul>
                <c:forEach items="${gradeList}" var="item" varStatus="status">
                    <li <c:if test="${status.index == 0}">class="on"</c:if> data-id="${item.id}"><a href="javascript:void(0)">${item.name}</a></li>
                </c:forEach>
            </ul>
        </div>
        <div class="banji_info_list" id="table_list">
            <table class="table">
                <thead>
                <tr><th>班级</th><th>教师</th></tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <%--<div class="page_div">--%>
            <%--<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">--%>
                <%--<jsp:param name="id" value="studentAid_list_content" />--%>
                <%--<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />--%>
                <%--<jsp:param name="pageSize" value="${page.pageSize}" />--%>
            <%--</jsp:include>--%>
            <%--<div class="clear"></div>--%>
        <%--</div>--%>
    </div>
</div>

<script>
    $('body').on('click','.banji_info_top ul li',function(){
        $(this).siblings().removeClass('on');
        $(this).addClass('on');
        search($(this).data("id"));
    });
    search($(".banji_info_top li.on").data("id"));

    function search(gradeId) {
        var url = "/teach/teamAdmin/index?sub=list";
        var val = {"gradeId" : gradeId};
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                $("#table_list").html("").html(data);
            }
        });
    }

    $('body').on('click','.banji_info_list tbody a ',function(){
        var id = $(this).data("id");
        $.initWinOnTopFromLeft_qyjx("班级管理", '${pageContext.request.contextPath}/teach/teamAdmin/editor?id=' + id, '600', '500');
    });

    function addBjController(){
        window.location.href = '${pageContext.request.contextPath}/teach/teamAdmin/creator';
    }

</script>
</body>
</html>