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
    <script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts.js"></script>
    <style type="text/css">
        .kwgl_main {
            position: relative;
            padding-bottom: 140px;
        }
        #tl {
            position: absolute;
            right: 40px;
            top: 400px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 20px;
            font-size: 15px;
            font-family: "微软雅黑";
            line-height: 22px;
        }
        #p1 {
            color: #03a9f5;
        }
        #p2 {
            color: #ff6a88;
        }
        #p3 {
            color: #58ebcf;
        }
        #p4 {
            color: #6e7cff;
        }

    </style>
    <title>查看报告</title>
</head>
<body >
<div class="kwgl">
    <div class="return_kw">
        <p>${teamName} ${map.name} 报告查看</p>
        <div class="njtk">
            <a href="javascript:void(0)" class="on" style="width: 90px;">班级单科分析</a>
            <%--<a href="javascript:void(0)">班级趋势分析</a>--%>
        </div>
        <a href="javascript:void(0)" class="btn btn-green" onclick="goBack();">返回</a>
        <%--<a href="javascript:void(0)" class="btn btn-blue">导出统计报告</a>--%>
    </div>
    <div class="pd20">
        <div class="kw_select">
            <p class="xnxq fl" style="margin:0">${title}</p>
            <p class="fr" style="color:#86939d">考试时间：<fmt:formatDate pattern="yyyy/MM/dd HH:mm" value="${map.examDate}"></fmt:formatDate></p>
        </div>
        <div class="kwgl_main" style="padding-bottom: 140px">
            <div id="container" style="min-width: 310px; height: 400px; max-width:90%; margin: 0 auto"></div>
            <div id="tl">
                <%--<p id="p1">优秀 ：${map.highScore}-${map.fullScore}分</p>--%>
                <%--<p id="p2">良好 ：${map.lowScore}-${map.highScore-1}分</p>--%>
                <%--<p id="p3">及格 ：${map.passScore}-${map.lowScore-1}分</p>--%>
                <%--<p id="p4">不及格 ：0-${map.passScore-1}分</p>--%>
                <p id="p1">优秀 ：<fmt:formatNumber type="number" value="${map.highScore}" maxFractionDigits="0"/>-<fmt:formatNumber type="number" value="${map.fullScore}" maxFractionDigits="0"/>分</p>
                <p id="p2">良好 ：<fmt:formatNumber type="number" value="${map.lowScore}" maxFractionDigits="0"/>-<fmt:formatNumber type="number" value="${map.highScore}" maxFractionDigits="0"/>分</p>
                <p id="p3">及格 ：<fmt:formatNumber type="number" value="${map.passScore}" maxFractionDigits="0"/>-<fmt:formatNumber type="number" value="${map.lowScore}" maxFractionDigits="0"/>分</p>
                <p id="p4">不及格 ：0-<fmt:formatNumber type="number" value="${map.passScore}" maxFractionDigits="0"/>分</p>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    var high = ${high};
    var low = ${low};
    var pass = ${pass};
    var noPass = ${noPass};

    $(document).ready(function() {
        var chart = {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        };
        var title = {
            text: ''
        };
        var tooltip = {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        };
        var credits={
            enabled:false // 禁用版权信息
        };
        var plotOptions = {
            pie: {
                allowPointSelect: true,
                connectorColor:'red',
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: ({point.y}人 <span>{point.percentage:.1f}%)</span>',
                    style: {
                        color: '#2a9ff7',
                        fontWeight: 'bold',
                        fontSize:'18px',
                        fontFamily:"微软雅黑",
                        textShadow: '0px 0px 0px #fff'
                    }
                }
            }
        };
        var series= [{
            type: 'pie',
            name: '作答',
            innerSize: '75%',
            data: [
                ['优秀率',   high],
                ['良好率',   low],
                ['及格率',    pass],
                ['不及格率',    noPass]
            ]
        }];
        var color1= ['#03a9f5', '#ff6a88', '#16b2eb', '#6e7cff', '#999999'] ;
        var color2= ['#90caf8', '#fca599', '#2af598', '#fd83ec', '#999999'] ;
        // Radialize the colors
        Highcharts.getOptions().colors = Highcharts.map(color1, function (color) {
            var v=color;
            var j=0;
            var color_end='#fff';
            for(var i=0;i<color1.length;i++){
                if(color1[i]==v){
                    j=i;
                    color_end=color2[j]
                }
            }
            return {
                linearGradient: { x1: 0, y1: 1, y2: 0, x2: 0 }, //横向渐变效果 如果将x2和y2值交换将会变成纵向渐变效果
                stops: [
                    [0, color],
                    [1, color_end] // darken
                ]
            };
        });

        var json = {};
        json.chart = chart;
        json.title = title;
        json.credits=credits;
        json.tooltip = tooltip;
        json.series = series;
        json.plotOptions = plotOptions;
        $('#container').highcharts(json);
    });

    function goBack() {
        var type = "${type}";
        if (type == "report") {
            window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/achievement?examWorksId=${map.examWorksId}&teamId=${map.teamId}&ewtsId=${map.ewtsId}&category=${category}&isMain=${isMain}";
        } else {
            var category = "${category}";
            var year = "${map.schoolYear}";
            var termCode = "${map.termCode}";
            var examWorksId = "${map.examWorksId}";
            var gradeId = "${map.gradeId}";
            var teamId = "${map.teamId}";
            if (category == 0) {
                window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/index?year=" + year
                    + "&termCode=" + termCode + "&examWorksId=" + examWorksId + "&gradeId=" + gradeId + "&teamId=" + teamId;
            } else if (category == 1) {
                window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/teacher/index?year=" + year
                    + "&termCode=" + termCode + "&examWorksId=" + examWorksId + "&teamId=" + teamId;
            } else if (category == 2) {
                window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/class/index?year=" + year
                    + "&termCode=" + termCode + "&teamId=" + teamId + "&isMain=true";
            } else if (category == 3) {
                window.location.href="${pageContext.request.contextPath}/teach/examWorks/manage/class/index?year=" + year
                    + "&termCode=" + termCode + "&teamId=" + teamId + "&isMain=false";
            } else if (category == 99) {
                var json = { "type": "backExam" };
                json = JSON.stringify(json);
                var msg = WPFObj.ShowImportScoreWindow(json);
            } else {
                var json = { "type": "backExam" };
                json = JSON.stringify(json);
                var msg = WPFObj.ShowImportScoreWindow(json);
            }
        }
    }
</script>
</html>