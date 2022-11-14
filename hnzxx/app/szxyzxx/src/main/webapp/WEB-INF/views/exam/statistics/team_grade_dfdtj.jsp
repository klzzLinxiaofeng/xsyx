<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<html>
<head>
    <title>Title</title>
    <%--<%@ include file="/views/embedded/common.jsp"%>--%>
</head>
<body>
<h3>本班-年级得分段堆积对比图</h3>
<div id="accumulate"></div>
</body>
<script type="text/javascript">
    $(function(){
        getData5();
    });

    function createUI5(data){
        // 本班-年级得分段堆积对比图
        var myChart = echarts.init(document.getElementById('accumulate'));
        option = {
            title: {
                text: '',
                subtext: ''
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} %",
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                bottom:'5%',
                data: ['优秀率', '良好率','及格率','不及格率']
            },
            grid: {
                left: '8%',
                right: '5%',
                bottom: '20%',
                top:'8%',
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                data: ['本班','年级']
            },
            label: {
                normal: {
                    show: true,
                    position: 'insideLeft'
                }
            },
            series: data
        };
        myChart.setOption(option);
    }

    function getData5(){
        $.post("${ctp}/statistic/teamGradeScoreSection",{"examId":${exam.id}}, function(returnData, status) {
            if ("success" === status) {
                var json = "[{\"name\":\"优秀率\",\"type\":\"bar\",\"stack\":\"总量\",\"itemStyle\" : \"{ normal: {label : {show: true, position: 'insideRight'}}}\",\"data\":["+ Number(returnData["teamHight"]*100).toFixed(2) + "," + Number(returnData["gradeHight"]*100).toFixed(2) +"]}," +
                        "{\"name\":\"良好率\",\"type\":\"bar\",\"stack\":\"总量\",\"itemStyle\" : \"{ normal: {label : {show: true, position: 'insideRight'}}}\",\"data\":["+ Number(returnData["teamLow"]*100).toFixed(2) + "," + Number(returnData["gradeLow"]*100).toFixed(2) +"]}," +
                        "{\"name\":\"及格率\",\"type\":\"bar\",\"stack\":\"总量\",\"itemStyle\" : \"{ normal: {label : {show: true, position: 'insideRight'}}}\",\"data\":["+ Number(returnData["teamPass"]*100).toFixed(2) + "," + Number(returnData["gradePass"]*100).toFixed(2) +"]}," +
                        "{\"name\":\"不及格率\",\"type\":\"bar\",\"stack\":\"总量\",\"itemStyle\" : \"{ normal: {label : {show: true, position: 'insideRight'}}}\",\"data\":["+ Number(returnData["teamNoPass"]*100).toFixed(2) + "," + Number(returnData["gradeNoPass"]*100).toFixed(2) +"]}]";
                var data = JSON.parse(json);
                createUI5(data);
            }
        },'json');
    }
</script>
</html>
