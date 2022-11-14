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
    <title>修改考务信息</title>
</head>
<body >
<div class="kwgl">
    <div class="return_kw">
        <p>添加${name}考务信息</p>
        <a href="javascript:void(0)" class="btn btn-green" onclick="toIndex();">返回</a>
    </div>
    <div class="pd20">
        <div class="kw_select">
            <p class="xnxq">${title}</p>
        </div>
        <div class="kwgl_main">
            <div class="ksmc_select">
                <div class="xiazai_div">
                    <span>考试名称：</span><input type="text" placeholder="" id="name" value="${examWorks.name}" maxlength="10">
                </div>
                <div class="xiazai_div">
                    <span>考试时间段：</span>
                    <input type="text" placeholder="" id="begin" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}'})"
                           value='<fmt:formatDate pattern="yyyy-MM-dd" value="${examWorks.examDateBegin}"></fmt:formatDate>'>
                    至
                    <input type="text" placeholder="" id="end" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'begin\')}'})"
                           value='<fmt:formatDate pattern="yyyy-MM-dd" value="${examWorks.examDateEnd}"></fmt:formatDate>'>
                </div>
                <div class="clear"></div>
            </div>
            <div class="ks_list">
                <p class="ks_title">考试安排</p>
                <p class="cjbgmczx"><a href="javascript:void(0)" <c:if test="${examWorks.showRanking}">class="on"</c:if> ><input type="checkbox"></a>成绩报告名次展示</p>
                <table class="table table-striped" style="border:1px solid #e4e8eb;">
                    <thead><tr><th>是否参加考试</th><th>考试年级</th><th>考试科目及三率设置</th></tr></thead>
                    <tbody>
                    <c:forEach items="${gradeList}" var="item">
                        <tr data-id="${item.id}">
                            <td><a href="javascript:void(0);" class="join"><input type="checkbox"></a></td>
                            <td>${item.name}</td>
                            <td><button class="btn btn-orange" onclick="toSubjectPage('${item.id}','${item.uniGradeCode}', '${item.name}');">修改</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="qd-btn">
                    <button class="btn btn-lightGray">确认修改</button>
                    <button class="btn btn-green" onclick="save();" style="display: none">确认修改</button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function(){
        $(".ks_list table tbody tr a").click(function(){
            if (!$(this).hasClass("read")) {
                $(this).toggleClass("on");
            }
        });
        $(".ks_list .cjbgmczx a").click(function(){
            $(this).toggleClass("on");
        });
        var joinGrades = ${examGrades};
        //已有考试发布的班级，该年级考务不可取消
        $(".join").each(function () {
            var gradeId = $(this).parent().parent().data("id");
            for (var i=0; i<joinGrades.length; i++) {
                if (joinGrades[i].gradeId == gradeId) {
                    $(this).addClass("on");
                    if (joinGrades[i].lastPublishTime != null) {
                        $(this).children("input").attr("readonly", "readonly");
                        $(this).addClass("read");
                    }
                }
            }
        });

        //页面有变动时可修改
        $("#name").bind('input propertychange',function(){
            $(".btn-lightGray").hide();
            $(".btn-green").show();
        });
        var beginTime = $("#begin").val();
        var endTime = $("#end").val();
        $("#begin").focus(function(){
            if ($(this).val() != beginTime) {
                $(".btn-lightGray").hide();
                $(".btn-green").show();
            }
        });
        $("#end").focus(function(){
            if ($(this).val() != endTime) {
                $(".btn-lightGray").hide();
                $(".btn-green").show();
            }
        });
        $("a").click(function () {
            $(".btn-lightGray").hide();
            $(".btn-green").show();
        });
    });

    function toIndex() {
        var type = "${type}";
        if (type == "03" || type == "12") {
            window.location.href="${pageContext.request.contextPath}/teach/examWorks/index?type=${type}&year=${year}&termCode=${termCode}";
        } else {
            history.go(-1);
        }
        <%--window.location.href="${pageContext.request.contextPath}/teach/examWorks/index";--%>
    }

    function toSubjectPage(gradeId, gradeCode, gradeName) {
        var examWorksId = "${examWorksId}";
        $.initWinOnTopFromLeft_qyjx(gradeName +"考试科目设置", "${pageContext.request.contextPath}/teach/examWorks/subjectPage?gradeId="
            + gradeId + "&gradeCode=" + gradeCode + "&examWorksId=" + examWorksId , '700', '500');
    }

    function save(){
        var loader = new loadLayer("", 60000);
        var type = "${type}";
        var year = "${year}";
        var termCode = "${termCode}";
        var name = $("#name").val();
        var begin = $("#begin").val();
        var end = $("#end").val();
        var isShow = $(".cjbgmczx a").hasClass("on");
        var addGrades = "";     //新增的年级
        var delGrades = "";     //删除的年级
        var newGrades = "";
        $(".join").each(function (index) {
            if ($(this).hasClass("on")) {
                var gradeId = $(this).parent().parent().data("id");
                newGrades += "," + gradeId;
            }
        });
        if (newGrades == "") {
            $.error("请选择考试的年级");
            return;
        }

        var oldGrades = "${joinGrade}".substr(0, "${joinGrade}".length -1).split(",");
        newGrades = newGrades.substr(1, newGrades.length).split(",");

        for (var i=0; i<newGrades.length; i++) {
            var exist = false;
            for (var j=0; j<oldGrades.length; j++) {
                if (newGrades[i] == oldGrades[j]) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                addGrades += "," + newGrades[i];
            }
        }

        for (var i=0; i<oldGrades.length; i++) {
            var exist = false;
            for (var j=0; j<newGrades.length; j++) {
                if (oldGrades[i] == newGrades[j]) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                delGrades += "," + oldGrades[i];
            }
        }
        addGrades = addGrades.substr(1, addGrades.length);
        delGrades = delGrades.substr(1, delGrades.length);

        if (name == "" || name.trim() == "") {
            $.error("请输入考试名称");
            return;
        }
        if (begin == "" || end == "") {
            $.error("请选择考试时间段");
            return;
        }

        var val = {
            "id" : "${examWorksId}",
            "type" : type,
            "year" : year,
            "termCode" : termCode,
            "name" : name,
            "begin" : begin,
            "end" : end,
            "isShow" : isShow,
            "addGrades" : addGrades,
            "delGrades" : delGrades
        };
        loader.show();
        var url = "${pageContext.request.contextPath}/teach/examWorks/modifyExamWorks";
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                if("success" === data.info) {
                    $.success("保存成功");
                    window.location.reload();
                } else {
                    $.error("保存失败");
                }
            } else {
                $.error("保存失败");
            }
            loader.close();
        });
    }



</script>
</html>