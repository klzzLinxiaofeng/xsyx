<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/kwgl.css" rel="stylesheet">
    <title>考务管理</title>
</head>
<body >
<div class="kwgl">
    <div class="pd20">
        <div class="kw_select">
            <select id="schoolYear" name="schoolYear" onchange="getTerm();"></select>
            <select id="schoolTerm" name="schoolTerm"></select>
        </div>
        <div class="kwgl_main">
            <div class="creat_ts">
                <p class="p1">请根据以下<span>考试类型</span>选择按钮进入创建/修改考试信息页面</p>
                <div class="warn_ts">
                    <span class="ts1"><i class="fa fa-exclamation"></i></span>
                    <p class="f_red">注：管理员需先创建年级统考考试信息，科任教师/班主任才能进行该考试成绩的导入</p>
                </div>
                <div class="clear"></div>
            </div>
            <div class="kslx">
                <a href="javascript:void(0)" class="a1" onclick="toIndex('02');">期末考试</a>
                <a href="javascript:void(0)" class="a2" onclick="toIndex('01');">期中考试</a>
                <a href="javascript:void(0)" class="a3" onclick="toIndex('12');">月考</a>
                <a href="javascript:void(0)" class="a4" onclick="toIndex('03');">模拟考试</a>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function(){
        getYear();
        getTerm();
        $('.ts1').click(function () {
            $('.f_red').toggle();
        })
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
        });
    }

    function toIndex(type) {
        var year = $("#schoolYear").val();
        var termCode = $("#schoolTerm").val();
//        var yearName = $("#schoolYear option:selected").text();
//        var termName = $("#schoolTerm option:selected").text();
        window.location.href="${pageContext.request.contextPath}/teach/examWorks/index?type="+ type
            + "&year=" + year + "&termCode=" + termCode;
    }
</script>
</html>