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
    <title>添加考务信息</title>
</head>
<body >
<div class="kwgl">
    <div class="return_kw">
        <p>添加${name}考务信息</p>
        <a href="javascript:void(0)" class="btn btn-green" onclick="history.go(-1);">返回</a>
    </div>
    <div class="pd20">
        <div class="kw_select">
            <p class="xnxq">${title}</p>
        </div>
        <div class="kwgl_main">
            <div class="ksmc_select">
                <div class="xiazai_div">
                    <span>考试名称：</span><input type="text" placeholder="请输入考试名称" id="name" maxlength="10">
                </div>
                <c:if test="${type eq '12'}">
                    <div class="xiazai_div">
                        <span>考试月份：</span>
                        <select id="month">
                            <c:forEach items="${months}" var="item">
                                <option value="${item.num}">${item.month}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>
                <c:if test="${type eq '03'}">
                    <div class="xiazai_div">
                        <span>考试次数：</span>
                        <select id="count">
                        </select>
                    </div>
                </c:if>
                <div class="xiazai_div">
                    <span>考试时间段：</span>
                    <input type="text" placeholder="请选择开始时间" id="begin"  onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'end\')}'})">
                    至
                    <input type="text" placeholder="请选择结束时间" id="end" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'begin\')}'})">
                </div>
                <div class="clear"></div>
            </div>
            <div class="ks_list">
                <p class="ks_title">考试安排</p>
                <p class="cjbgmczx"><a href="javascript:void(0)" class="on"><input type="checkbox"></a>成绩报告名次展示</p>
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
                <div class="qd-btn"><button class="btn btn-green" onclick="save();">确认添加</button></div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function(){
        $(".ks_list table tbody tr a").click(function(){
            $(this).toggleClass("on");
        })
        $(".ks_list .cjbgmczx a").click(function(){
            $(this).toggleClass("on");
        });
        //筛选 未使用的考试次数
        if ("${type}" == "03") {
            var num = ["零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"];
            var rounds = "${rounds}".split(",");
            for (var i=1; i< 11; i++) {
                var flag = false;
                for (var j=0; j<rounds.length; j++){
                    if (i == rounds[j]){
                        flag = true;
                    }
                }
                if (!flag) {
                    $("#count").append("<option value='" + i +"'>第" + num[i] +"次</option>");
                }
            }
        }
        if ("${type}" == "12") {
            var rounds = "${rounds}".split(",");
            $('#month option').each(function(){
                var i = $(this).val();
                for (var j in rounds) {
                    if(rounds[j] == i) {
                        $(this).remove();
                    }
                }
            });
        }
    })

    function toIndex() {
        window.location.href="${pageContext.request.contextPath}/teach/examWorks/index";
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
        var gradeIds = "";
        var round;
        $(".join").each(function (index) {
            if ($(this).hasClass("on")) {
                var gradeId = $(this).parent().parent().data("id");
                gradeIds += "," + gradeId;
            }
        });

        if (type == "03") {
            round = $("#count").val();
        }
        if (type == "12") {
            round = $("#month").val();
        }
        if ((type == "03" || type == "12") && round == null) {
            $.error("已无考试轮次，请勿添加");
            return;
        }

        if (name == "" || name.trim() == "") {
            $.error("请输入考试名称");
            return;
        }
        if (begin == "" || end == "") {
            $.error("请选择考试时间段");
            return;
        }
        if (gradeIds != "") {
            gradeIds = gradeIds.substr(1, gradeIds.length);
            console.log(gradeIds);
        } else {
            $.error("请选择考试的年级");
            return;
        }

        var val = {
            "type" : type,
            "year" : year,
            "termCode" : termCode,
            "name" : name,
            "begin" : begin,
            "end" : end,
            "isShow" : isShow,
            "gradeIds" : gradeIds,
            "round" : round
        };
        loader.show();
        var url = "${pageContext.request.contextPath}/teach/examWorks/addExamWorks";
        $.post(url, val, function(data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                if("success" === data.info) {
                    $.success("保存成功");
                    setTimeout(function () {
                        window.location.href="${pageContext.request.contextPath}/teach/examWorks/index?type="+ type + "&year=" + year + "&termCode=" + termCode;
                    }, 300);
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