<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<html>
<head>
    <title>Title</title>
    <style>
        .bubble circle{
            stroke: black;
            stroke-width: 2px;
        }

        .bubble text{
            fill: black;
            font-size: 14px;
            font-family: arial;
            text-anchor: middle;
        }

    </style>
</head>
<body>
<h3>三率位置图</h3>
<div id="position" style="width: 500px;height: 415px;margin: 0 auto;"></div>
</body>
<script>
    $(function(){
        getDateForHighChar();
    })

    function getDateForHighChar(){
        $.post("${ctp}/statistic/getTeamStudentSLWZT", {"examId":${exam.id},"studentId":$("#stu").val()}, function (returnData, status) {
            if ("success" === status) {
                returnData = JSON.parse(returnData);
                var json = "[{\"name\":\"满分\",\"type\":\"bar\",\"data\":["+ Number(returnData["full"]).toFixed(2) + "]},"+
                            "{\"name\":\"优秀\",\"type\":\"bar\",\"data\":["+ Number(returnData["high"]).toFixed(2) + "]}," +
                            "{\"name\":\"良好\",\"type\":\"bar\",\"data\":["+ Number(returnData["low"]).toFixed(2) + "]}," +
                            "{\"name\":\"及格\",\"type\":\"bar\",\"data\":["+ Number(returnData["pass"]).toFixed(2) +"]}," +
                            "{\"name\":\"班级平均\",\"type\":\"bar\",\"data\":["+ Number(returnData["teamAvg"]).toFixed(2) +"]}," +
                            "{\"name\":\"我\",\"type\":\"bar\",\"data\":["+ Number(returnData["mine"]).toFixed(2) +"]}]";
                var data = JSON.parse(json);
                initHighChar(data);
                }
        });
    }

    function initHighChar(data){
        //三率位置图
        var myChart = echarts.init(document.getElementById('position'));
        option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['我', '班级平均'],
                top:'3%',
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis:  {
                type: 'value'
            },
            yAxis: {
                type: 'category',
                data: ['']
            },
            label: {
                normal: {
                    show: true,
                    position: 'insideRight'
                }
            },
            series: data
        };
        myChart.setOption(option);
    }

</script>

</html>
