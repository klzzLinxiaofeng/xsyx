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
<h3>本班-年级 三率对比图</h3>
<div id="contrast"></div>
</body>
<script type="text/javascript">
    $(function(){
        getData4();
    });

    function createUI4(data){
        //本班-年级 三率对比图
        var myChart = echarts.init(document.getElementById('contrast'));
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
                orient: 'vertical',
                x: 'right',
                top:'5%',
                data: ['年级最高', '本班', '年级最低']
            },
            grid: {
                left: '12%',
                right: '18%',
                bottom: '10%',
                top:'5%',
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                data: ['不及格率','及格率','良好率','优秀率',]
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

    function getData4(){
        $.post("${ctp}/statistic/teamGradeSection",{"examId":${exam.id}}, function(returnData, status) {
            if ("success" === status) {
                var gradeHightOfHight = 0;
                var gradeHightOfLow = 0;
                var gradeHightOfPass = 0;
                var gradeHightOfNoPass = 0;

                var gradeLowOfHight = 0;
                var gradeLowOfLow = 0;
                var gradeLowOfPass = 0;
                var gradeLowOfNoPass = 0;

                var teamHight = 0;
                var teamLow = 0;
                var teamPass = 0;
                var teamNoPass = 0;

                $.each(returnData,function(key,values){
                    $(values).each(function(){
                        //年级最高数据
                        if(typeof(this["gradeHightOfHight"]) == "number"){
                            gradeHightOfHight = (Number(this["gradeHightOfHight"]*100)).toFixed(2);
                        }
                        if(typeof(this["gradeLowOfHight"]) == "number"){
                            gradeHightOfLow = (Number(this["gradeLowOfHight"]*100)).toFixed(2);
                        }
                        if(typeof(this["gradePassOfHight"]) == "number"){
                            gradeHightOfPass = (Number(this["gradePassOfHight"]*100)).toFixed(2);
                        }
                        if(typeof(this["gradeNoPassOfHight"]) == "number"){
                            gradeHightOfNoPass = (Number(this["gradeNoPassOfHight"]*100)).toFixed(2);
                        }

                        //年级最低数据
                        if(typeof(this["gradeHightOfLow"]) == "number"){
                            gradeLowOfHight = (Number(this["gradeHightOfLow"]*100)).toFixed(2);
                        }
                        if(typeof(this["gradeLowOfLow"]) == "number"){
                            gradeLowOfLow = (Number(this["gradeLowOfLow"]*100)).toFixed(2);
                        }
                        if(typeof(this["gradePassOfLow"]) == "number"){
                            gradeLowOfPass = (Number(this["gradePassOfLow"]*100)).toFixed(2);
                        }
                        if(typeof(this["gradeNoPassOfLow"]) == "number"){
                            gradeLowOfNoPass = (Number(this["gradeNoPassOfLow"]*100)).toFixed(2);
                        }

                        //班级数据
                        if(typeof(this["teamHight"]) == "number"){
                            teamHight = (Number(this["teamHight"]*100)).toFixed(2);
                        }
                        if(typeof(this["teamLow"]) == "number"){
                            teamLow = (Number(this["teamLow"]*100)).toFixed(2);
                        }
                        if(typeof(this["teamPass"]) == "number"){
                            teamPass = (Number(this["teamPass"]*100)).toFixed(2);
                        }
                        if(typeof(this["teamNoPass"]) == "number"){
                            teamNoPass = (Number(this["teamNoPass"]*100)).toFixed(2);
                        }

                    });
                });

                var json = "[{\"name\":\"年级最高\",\"type\":\"bar\",\"data\":["+ gradeHightOfNoPass + "," + gradeHightOfPass + "," + gradeHightOfLow + "," + gradeHightOfHight + "]}," +
                        "{\"name\":\"本班\",\"type\":\"bar\",\"data\":["+ teamNoPass + "," + teamPass + "," + teamLow + "," + teamHight +"]}," +
                        "{\"name\":\"年级最低\",\"type\":\"bar\",\"data\":["+ gradeLowOfNoPass + "," + gradeLowOfPass + "," + gradeLowOfLow + "," + gradeLowOfHight +"]}]"

                var data = JSON.parse(json);
                createUI4(data);
            }
        },'json');
    }
</script>
</html>
