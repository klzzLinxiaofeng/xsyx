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
    <title>班级测试</title>
</head>
<body >
<div class="kwgl">
    <div class="return_kw">
        <p>班级测试</p>
    </div>
    <div class="pd20">
        <div class="kw_select">
            <select id="schoolYear" onchange="getTerm();"></select>
            <select id="schoolTerm" onchange="search();"></select>
        </div>
        <div class="kwgl_main">
            <div class="ks_list" style="padding:10px 0;">
                <p class="ks_title"><button class="btn btn-blue fr" style="margin-bottom:10px" onclick="toAddPage();">添加</button></p>
                <table class="table table-striped" style="border:1px solid #e4e8eb;">
                    <thead><tr><th>考试名称</th><th>考试班级</th><th>科目</th><th>考试时间</th><th>操作</th></tr></thead>
                    <tbody id="content">
                        <jsp:include page="./classExamList.jsp"/>
                    </tbody>
                </table>
                <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                    <jsp:param name="id" value="content" />
                    <jsp:param name="url" value="/teach/examWorks/class/list" />
                    <jsp:param name="pageSize" value="${page.pageSize }" />
                </jsp:include>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function(){
        $(".ks_list table tbody tr a").click(function(){
            $(this).toggleClass("on");
        });
        getYear();
    });

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
        });
        getTerm();
    }

    function getTerm() {
        var year = $("#schoolYear").val();
        if(year == null || year == ""){
            year = "${sessionScope[sca:currentUserKey()].schoolYear}";
        }
        var termCurrent = "${sessionScope[sca:currentUserKey()].schoolTermCode}";
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

    function search(){
        var year = $("#schoolYear").val();
        var termCode = $("#schoolTerm").val();
        if (year == null) {
            year = "${sessionScope[sca:currentUserKey()].schoolYear}";
        }
        if (termCode == null) {
            termCode = "${sessionScope[sca:currentUserKey()].schoolTermCode}";
        }
        var val = {
            "year" : year,
            "termCode" : termCode
        }
        var id = "content";
        var url = "${pageContext.request.contextPath}/teach/examWorks/class/list";
//        $.get(url, val, function(data, status) {
//            if ("success" === status) {
//                $("#content").html("").html(data);
//            }
//        });
        myPagination(id, val, url);
    }

    function toAddPage(){
        var year = $("#schoolYear").val();
        var termCode = $("#schoolTerm").val();
        $.initWinOnTopFromLeft_qyjx("添加考务信息", "${pageContext.request.contextPath}/teach/examWorks/class/addOrUpdate?year="
            + year + "&termCode=" + termCode, '800', '400');
    }

    function modifyPage(id){
        var year = $("#schoolYear").val();
        var termCode = $("#schoolTerm").val();
        $.initWinOnTopFromLeft_qyjx("修改考务信息", "${pageContext.request.contextPath}/teach/examWorks/class/addOrUpdate?id=" + id
            + "&year=" + year + "&termCode=" + termCode, '800', '400');
    }

    function deleteObj(obj, id){
        $.confirm("确定执行此次操作？", function() {
            $.post("${pageContext.request.contextPath}/teach/examWorks/class/delete?id=" + id, {"_method" : "delete"}, function(data, status) {
                if ("success" === status) {
                    if ("success" === data) {
                        $.success("删除成功");
                        $("#" + id + "_tr").remove();
                    } else if ("fail" === data) {
                        $.error("删除失败，系统异常", 1);
                    }
                }
            });
        });
    }


</script>
</html>