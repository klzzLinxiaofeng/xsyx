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
    <title>查看成绩</title>
</head>
<body >
<div class="kwgl">
    <div class="return_kw">
        <p>查看 ${map.name} 考试成绩</p>
        <a href="javascript:void(0)" class="btn btn-green" onclick="toIndex();">返回</a>
    </div>
    <div class="pd20">
        <div class="kw_select">
            <p class="xnxq fl" style="margin:0">${title}</p>
            <p class="fr" style="color:#86939d">考试时间：<fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${map.examDate}"></fmt:formatDate></p>
        </div>
        <div class="kwgl_main">
            <div class="sl_sz">
                <p class="fl"><span class="f_blue" style="font-weight:bold;font-size:14px;">${map.name}</span></p>
                <p class="fr">三率
                    <span class="f_green">满分：${map.fullScore}分</span>
                    <span class="f_blue">优秀：${map.highScore}（含）分</span>
                    <span class="f_orange">良好：${map.lowScore}(含)分</span>
                    <span class="f_red"> 及格：${map.passScore}(含)分</span>
                </p>
            </div>
            <div class="ks_list" style="padding:0 20px 20px;">
                <p class="ks_title">
                    ${teamName} - ${map.subjectName}
                    <c:choose>
                        <c:when test="${empty map.postTime}">
                            <span style="float:right;font-size:12px;" class="f_red">未导入</span>
                        </c:when>
                        <c:otherwise>
                            <span style="float:right;font-size:12px;">导入时间：<fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${map.postTime}"></fmt:formatDate></span>
                        </c:otherwise>
                    </c:choose>
                </p>
                <table class="table table-striped" style="border:1px solid #e4e8eb;">
                    <thead><tr><th>学号</th><th>姓名</th><th>科目</th><th>分数</th></tr></thead>
                    <tbody>
                    <c:forEach items="${studentList}" var="item">
                        <tr>
                            <td>${item.number}</td>
                            <td>${item.name}</td>
                            <td>${map.subjectName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.score eq -1}">
                                        --
                                    </c:when>
                                    <c:otherwise>
                                        ${item.score}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${!empty map.posterId}">
                        <tr><td colspan="4">考试成绩报告已生成，<a href="javascript:void(0)" class="djck" onclick="toReport();">点击查看</a></td></tr>
                    </c:if>
                    </tbody>
                </table>
                <div class="qd-btn">
                    <c:choose>
                        <c:when test="${empty map.posterId}">
                            <button class="btn btn-blue" onclick="lead();">导入</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-orange" onclick="reLead();">重新导入</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="qrfb" style="display:none;">
    <p id="fbts">该班级的成绩报告已经发布，您确定要重新导入成绩吗？<br>一旦确定重新导入后，原成绩文件将被替换。</p>
</div>
</body>
<script>
    $(function(){
        $(".ks_list table  tr a").click(function(){
            $(this).toggleClass("on");
        });
        //根据权限是否显示导入按钮
        var isShow = "${isShow}";
        if (isShow != "true") {
            $(".qd-btn").hide();
        }
    });

    function toReport() {
        window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/achievement?type=report" +
        "&examWorksId=${map.examWorksId}&teamId=${map.teamId}&ewtsId=${map.ewtsId}&category=${category}&isMain=${isMain}";
    }

    function lead(){
        var val = {
            "schoolYear":"${map.schoolYear}",
            "termCode":"${map.termCode}",
            "examWorksId":"${map.examWorksId}",
            "subjectCode":"${map.subjectCode}",
            "gradeId":"${map.gradeId}",
            "teamId":"${map.teamId}",
            "examType":"${map.examType}",
            "beginDate":new Date("${map.beginDate}").Format("yyyy-MM-dd"),
            "endDate":new Date("${map.endDate}").Format("yyyy-MM-dd"),
            "fullScore":"${map.fullScore}",
            "highScore":"${map.highScore}",
            "lowScore":"${map.lowScore}",
            "passScore":"${map.passScore}",
            "subjectName":"${map.subjectName}",
            "teamName":"${teamName}",
            "category": "${category}"
        };
        var json = JSON.stringify(val);
        var msg = WPFObj.ShowImportScoreWindow(json);
        if (msg == "success") {
            window.location.reload();
        }
    }

    function reLead(){
        lead();
        <%--var publisherId = "${map.publisherId}";--%>
        <%--if (publisherId == "" || publisherId == null){--%>
            <%--lead();--%>
        <%--} else {--%>
            <%--layer.open({--%>
                <%--type: 1,--%>
                <%--shade: false,--%>
                <%--area: ['380px', '230px'],--%>
                <%--title: '重新导入提示',--%>
                <%--content: $('.qrfb'),--%>
                <%--cancel: function(){--%>
                    <%--layer.close();--%>
                <%--},--%>
                <%--btn: ['确定导入','再考虑下'],//按钮--%>
                <%--btn1: function(index, layero){--%>
                    <%--lead();--%>
                <%--}--%>
            <%--});--%>
        <%--}--%>
    }

    function toIndex() {
        var category = "${category}";
        var year = "${map.schoolYear}";
        var termCode = "${map.termCode}";
        var examWorksId = "${map.examWorksId}";
        var gradeId = "${map.gradeId}";
        var teamId = "${map.teamId}";
        if (category == 0) {
            //统考--管理员
            window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/index?year=" + year
                + "&termCode=" + termCode + "&examWorksId=" + examWorksId + "&gradeId=" + gradeId + "&teamId=" + teamId;
        } else if (category == 1) {
            //统考--教师
            window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/teacher/index?year=" + year
                + "&termCode=" + termCode + "&examWorksId=" + examWorksId + "&teamId=" + teamId;
        } else if (category == 2 || category == 3) {
            //小测
            window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/class/index?year=" + year
                + "&termCode=" + termCode + "&teamId=" + teamId + "&isMain=${isMain}";
        } else {
            history.go(-1);
        }
    }
</script>
</html>