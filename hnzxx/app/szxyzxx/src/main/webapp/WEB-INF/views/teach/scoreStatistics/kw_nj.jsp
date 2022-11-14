<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/kwgl.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <title>考务信息查询</title>
</head>
<body >
<div class="kwgl">
    <div class="return_kw">
        <div class="njtk">
            <a href="javascript:void(0)" class="on">年级统考</a>
            <a href="javascript:void(0)" onclick="goTeam();">班级测试</a>
        </div>
        <a href="javascript:void(0)" class="btn btn-lightGray" onclick="toIndex();">返回</a>
    </div>
    <div class="tk_div">
        <div class="pd20"></div>
        <div class="pd20">
            <div class="kw_select">
                <select id="schoolYear" onchange="getTerm();"></select>
                <select id="schoolTerm" onchange="search()"></select>
            </div>
            <div class="kwgl_main">
                <div class="ks_list" style="padding:20px;">
                    <table class="table table-striped" style="border:1px solid #e4e8eb;">
                        <thead><tr><th>序号</th><th>考试信息</th><th>考试时间段</th><th class="caozuo">操作</th></tr></thead>
                        <tbody id="content">
                        <jsp:include page="./kw_list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="content" />
                        <jsp:param name="url" value="/teach/scoreStatistics/exam/index?sub=list" />
                        <jsp:param name="pageSize" value="${page.pageSize }" />
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function(){
        getYear();
    })

    function search(){
        var id = "content";
        var val = {
            "year" : $("#schoolYear").val(),
            "termCode" : $("#schoolTerm").val(),
            "isJoint" : true
        }
        var url = "${pageContext.request.contextPath}/teach/scoreStatistics/exam/index?sub=list";
        myPagination(id, val, url);
    }

    function getYear() {
        var schoolId = "${sessionScope[sca:currentUserKey()].schoolId}";
        var year = "${sessionScope[sca:currentUserKey()].schoolYear}";
        $.getSchoolYear({"schoolId" : schoolId}, function (data, status) {
            var $year = $("#schoolYear");
            $year.html("");
            $.each(data, function(index, value) {
                if (year == value.year) {
                    $year.append("<option selected='selected' value='" + value.year + "'>" + value.name + "</option>");
                } else {
                    $year.append("<option value='" + value.year + "'>" + value.name + "</option>");
                }
            });
            getTerm();
        });
    }

    function getTerm() {
        var year = $("#schoolYear").val();
        if(year == null || year == ""){
            year = "${sessionScope[sca:currentUserKey()].schoolYear}";
        }
        var termCurrent = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
        $.getSchoolTerm({"schoolYear" : year}, function(data, status) {
            var $term = $("#schoolTerm");
            $term.html("");
            $.each(data, function(index, value) {
                if (termCurrent == value.code) {
                    $term.append("<option selected='selected' value='" + value.code + "'>" + value.name + "</option>");
                } else {
                    $term.append("<option value='" + value.code + "'>" + value.name + "</option>");
                }
            });
            search();
        });
    }

    function toIndex(){
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/index";
    }

    function goTeam() {
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/exam/index?isJoint=false";
    }

    function toGrade(id){
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/exam/grade?examWorksId=" + id;
    }

    function toTeam(id, teamId) {
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/exam/student?examWorksId=" + id + "&teamId=" + teamId;
    }

</script>
</html>