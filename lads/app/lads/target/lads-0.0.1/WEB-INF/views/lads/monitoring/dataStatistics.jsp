<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<!DOCTYPE html>
<div class="module module_s">
    <div class="m_title">
        <h3 class="stare">学习人数统计</h3>

        <div class="clear"></div>
    </div>
    <div class="m_content">
        <div id="learnerChart" style="height: 300px">

        </div>
        <div class="statistics_item">
            总发布人数：<span class="st_red">${requestScope.learnerVo.total}</span>人
        </div>
        <div class="statistics_item">
            已参与学习人数：<span class="st_red">${requestScope.learnerVo.finished}</span>人
        </div>
        <div class="statistics_item">
            未参与学习人数：<span class="st_red">${requestScope.learnerVo.notFinished}</span>人
        </div>
    </div>
</div>
<div class="module module_s">
    <div class="m_title">
        <h3 class="stare">学员成绩统计</h3>

        <div class="clear"></div>
    </div>
    <div class="m_content">
        <div id="scoreChart" style="height: 300px">

        </div>
        <div class="statistics_item">
            <span class="color_lump cl_blue"></span>
            <span style="display:inline-block;height: 20px">90分以上</span>
            <span class="stt_result"><span class="st_red">${requestScope.scoreVo.graterThanOrEqual90}</span>人</span>
        </div>
        <div class="statistics_item">
            <span class="color_lump cl_green"></span>
            <span style="display:inline-block;height: 20px">80分--90分之间</span>
            <span class="stt_result"><span class="st_red">${requestScope.scoreVo.between80To90}</span>人</span>
        </div>
        <div class="statistics_item">
            <span class="color_lump cl_purple"></span>
            <span style="display:inline-block;height: 20px">60分--80分之间</span>
            <span class="stt_result"><span class="st_red">${requestScope.scoreVo.between60To80}</span>人</span>
        </div>
        <div class="statistics_item">
            <span class="color_lump cl_yellow"></span>
            <span style="display:inline-block;height: 20px">60分以下</span>
            <span class="stt_result"><span class="st_red">${requestScope.scoreVo.lessThanOrEqual60}</span>人</span>
        </div>

    </div>
</div>
<script type="text/javascript">
    function getLearnerChartData() {
        var finished = ${requestScope.learnerVo.finished};
        var notFinished = ${requestScope.learnerVo.notFinished};
        var fp = ${requestScope.learnerVo.finishedPercent};
        var nfp = ${requestScope.learnerVo.notFinishedPercent};
        var array = new Array();
        var finishedData = ['<span style="font-size:14px">已参与学习人数 ' + finished + '人</span>', fp];
        var notFinishedData = ['<span style="font-size:14px">未参与学习人数 ' + notFinished + '人</span>', nfp];
        array.push(finishedData);
        array.push(notFinishedData);
        return array;
    }
    function getScoreChartData() {
        var graterThanOrEqual90 =${requestScope.scoreVo.graterThanOrEqual90};
        var between80To90 =${requestScope.scoreVo.between80To90};
        var between60To80 =${requestScope.scoreVo.between60To80};
        var lessThanOrEqual60 = ${requestScope.scoreVo.lessThanOrEqual60};
        var graterThanOrEqual90Percent =${requestScope.scoreVo.graterThanOrEqual90Percent};
        var between80To90Percent =${requestScope.scoreVo.between80To90Percent};
        var between60To80Percent =${requestScope.scoreVo.between60To80Percent};
        var lessThanOrEqual60Percent = ${requestScope.scoreVo.lessThanOrEqual60Percent};
        var array = new Array();
        var graterThanOrEqual90Data = ['<span style="font-size:14px">90分以上 ' + graterThanOrEqual90 + '人</span>', graterThanOrEqual90Percent];
        var between80To90Data = ['<span style="font-size:14px">80分--90分之间 ' + between80To90 + '人</span>', between80To90Percent];
        var between60To80Data = ['<span style="font-size:14px">60分--80分之间 ' + between60To80 + '人</span>', between60To80Percent];
        var lessThanOrEqual60Data = ['<span style="font-size:14px">60分以下 ' + lessThanOrEqual60 + '人</span>', lessThanOrEqual60Percent];
        array.push(graterThanOrEqual90Data);
        array.push(between80To90Data);
        array.push(between60To80Data);
        array.push(lessThanOrEqual60Data);
        return array;
    }
    $(function () {
        var learnerChart = new Highcharts.Chart({
            chart: {
                renderTo: 'learnerChart',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: ''
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function () {
                            return '<b>' + this.point.name + '</b>: ' + Math.round(this.percentage) + ' %';
                        }
                    }
                }
            },
            series: [{
                    type: 'pie',
                    name: ' ',
                    data: getLearnerChartData()
                }],
            credits: {
                enabled: false
            }
        });

        var scoreChart = new Highcharts.Chart({
            chart: {
                renderTo: 'scoreChart',
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: ''
            },
            colors: ['#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655',
                '#FFF263', '#6AF9C4'],
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        color: '#000000',
                        connectorColor: '#000000',
                        formatter: function () {
                            return '<b>' + this.point.name + '</b>: ' + Math.round(this.percentage) + ' %';
                        }
                    }
                }
            },
            series: [{
                    type: 'pie',
                    name: ' ',
                    data: getScoreChartData()
                }],
            credits: {
                enabled: false
            }
        });
    });
</script>