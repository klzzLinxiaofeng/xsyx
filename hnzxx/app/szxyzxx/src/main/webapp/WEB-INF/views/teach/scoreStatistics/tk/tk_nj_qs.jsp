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
    <script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts-more.js"></script>
    <title>年级趋势分析</title>
</head>
<body >
<div class="kwgl">

    <div class="njfx_km pd20">
        <c:forEach items="${subjectList}" var="item" >
            <a href="javascript:void(0)" <c:if test="${subjectCode eq item.subjectCode}">class="choose"</c:if> data-code="${item.subjectCode}">${item.subjectName}</a>
        </c:forEach>
    </div>

    <div class="pd20">
        <div class="kw_select">
            <p class="xnxq fl" style="margin:0">${title}</p>
            <p class="fr" style="color:#86939d">
                考试时间：
                <fmt:formatDate value="${examWorks.examDateBegin}" pattern="yyyy/MM/dd"/>
                -
                <fmt:formatDate value="${examWorks.examDateEnd}" pattern="MM/dd"/>
            </p>
        </div>
        <div class="nj_basis_info">
            <p class="title">年级基础信息</p>
            <div class="ks_bj">
                <div class="ks_bj_list">
                    <p>应试班级:</p>
                    <div class="list">
                        <c:forEach items="${teamList}" var="item" >
                            <c:choose>
                                <c:when test="${item.result eq 0}">
                                    <span class="dr_no">${item.teamName}</span>
                                </c:when>
                                <c:when test="${item.result eq 1}">
                                    <span class="dr_bfok">${item.teamName}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="dr_ok">${item.teamName}</span>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>
                <p class="ks_bj_info">
                    <span><i class="dr_ok"></i>成绩导入完成</span>
                    <span><i class="dr_bfok"></i>成绩部分导入</span>
                    <span><i class="dr_no"></i>成绩未导入</span>
                </p>
            </div>
            <div class="color_kuai">
                <div class="ck1">
                    <p class="ck_num"><span>${scoreMap.subjectStudentCount}</span>/<span>${scoreMap.gradeStudentCount}</span></p>
                    <p class="ck_info">应试人数</p>
                </div>
                <div class="ck2">
                    <p class="ck_num"><span>${scoreMap.avgScore}</span>/<span>${examWorksSubject.fullScore}</span></p>
                    <p class="ck_info">年级总分平均分</p>
                </div>
                <div class="ck3">
                    <p class="ck_num"><span>${scoreMap.highestScore}</span>/<span>${examWorksSubject.fullScore}</span></p>
                    <p class="ck_info">年级总分最高分</p>
                </div>
                <div class="ck4">
                    <p class="ck_num"><span>${scoreMap.lowestScore}</span>/<span>${examWorksSubject.fullScore}</span></p>
                    <p class="ck_info">年级总分最低分</p>
                </div>
            </div>
        </div>

        <div class="kwgl_main">
            <p class="title">年级各班级单科平均分排名</p>
            <div id="container10" style="height: 700px; width: 920px; margin: 0 auto"></div>
        </div>
        <div class="kwgl_main">
            <p class="title">班级单科平均分各班对比趋势</p>
            <div id="container11" style="height: 700px; width: 920px; margin: 0 auto"></div>
        </div>
    </div>
</div>
</body>
<script>
    $(function(){
        $('.njfx_km a').click(function(){
            $(this).addClass('choose');
            $(this).siblings().removeClass('choose');
            var code = $(this).data("code");
            window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/analysis/grade?examWorksId=${examWorksId}&gradeId=${gradeId}&sub=4&subjectCode=" + code;
        });

        $('#container10').highcharts({
            chart: {
                type: 'line',
            },
            title: {
                text: null
            },
            xAxis: {
                gridLineColor: '#d6d6d6',
                gridLineWidth: 1,
                gridLineDashStyle: 'Dot',
                categories: ${nameList},
                labels: {
                    y:30,
                    align: 'left',
                    style:{
                        color:'#999999',
                        fontSize:'16',
                        writingMode : 'tb-rl',//文字竖排样式,
                    }
                }
            },
            yAxis: {
                tickInterval: 10,
                gridLineColor: '#d6d6d6',
                gridLineWidth: 1,
                gridLineDashStyle: 'Dot',
                title: {
                    enabled:false
                },
                min:1,
                labels: {
                    style:{
                        color:'#999999',
                        fontSize:'16',
                    },
                    y:5
                },
            },
            legend: {
                verticalAlign: 'top',
                align:'right',
                itemStyle : {
                    'fontSize' : '18px',
                    'color':'#666666'
                }
            },
            tooltip: {
                crosshairs: true,
                shared: true,
                headerFormat: '<small style="font-size:20px">{point.key}</small><br>',
                pointFormat: '<span style="font-size:20px;color:{series.color}">{series.name}: <b style="font-size:20px">{point.y}</b><br/>',
                useHTML: true
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true,          // 开启数据标签
                        style:{
                            fontSize:'18',
                            color:'#666666'
                        }
                    }
                }
            },
            exporting:{
                enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
            },
            credits: {
                enabled:false
            },
            series: [{
                name: '年级单科平均分',
                color:'#07aaf5',
                data: ${gradeAvgScoreList}
            }]
        });


        var teamScoreList = ${teamScoreList};
        var data = [];
        for (var i=0; i<teamScoreList.length; i++) {
            data.push({name: teamScoreList[i].teamName, data: teamScoreList[i].avgScoreList});
        }
        Highcharts.setOptions({
            colors: ['#07aaf5','#4cb9c3', '#ff6b88', '#F7AF08', '#8EACBA', '#3C89D0', '#29F29C', '#FF5050', '#FFE467']
        });

        $('#container11').highcharts({
            chart: {
                type: 'line',
                marginRight:50
            },
            title: {
                text: null
            },
            xAxis: {
                gridLineColor: '#d6d6d6',
                gridLineWidth: 1,
                gridLineDashStyle: 'Dot',
                categories: ${nameList},
                labels: {
                    align: 'left',
                    style:{
                        color:'#999999',
                        fontSize:'16',
                        writingMode : 'tb-rl'//文字竖排样式,
                    }
                }
            },
            yAxis: {
                gridLineColor: '#d6d6d6',
                gridLineWidth: 1,
                gridLineDashStyle: 'Dot',
                title: {
                    enabled:false
                },
                labels: {
                    style:{
                        color:'#999999',
                        fontSize:'16',
                    },
                    y:5
                },
                min:0,
            },
            legend: {
                align: 'right',
                verticalAlign: 'top',
                symbolHeight: 18,
                symbolWidth: 18,
                symbolRadius: 12,
                itemStyle : {
                    'fontSize' : '18px',
                    'color':'#666666'
                }
            },
            tooltip: {
                crosshairs: true,
                shared: true,
                headerFormat: '<small style="font-size:20px">{point.key}</small><br>',
                pointFormat: '<span style="font-size:20px;color:{series.color}">{series.name}: <b style="font-size:20px">{point.y}</b><br/>',
                useHTML: true
            },
            plotOptions: {
                line: {
                    //enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                    dataLabels: {
                        enabled: true,
                        align: 'left',
                        formatter: function() {
                            return '<span style="font-size:18px;color:'+this.series.color +'">'+this.point.y  +'</span>';
                        },
                        style: {
                            fontSize:18,
                        },
                        allowOverlap:true,
                        x: 3,
                        verticalAlign: 'middle',
                        overflow: true,
                        crop: false,
                        useHTML: true
                    }
                }
            },
            exporting:{
                enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
            },
            credits: {
                enabled:false
            },
            series:data
        });
    })
</script>
</html>