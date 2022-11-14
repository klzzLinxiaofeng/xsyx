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
<h3>知识点 答题情况统计</h3>
<div id="answer1" style="width: 500px;height: 415px;margin: 0 auto;"></div>
<div class="zw_ts">暂无数据</div>
</body>
<script>
    $(function(){
        getDateForDTQKTJ();
    })

    function getDateForDTQKTJ(){
        $.post("${ctp}/statistic/getTeamStudentZSDDTQK", {"examId":${exam.id},"studentId":$("#stu").val()}, function (returnData, status) {
            if ("success" === status) {
                returnData = JSON.parse(returnData);

                var dataTeam = returnData["team"];
                var dataMine = returnData["mine"];

                //创建标题数组
                var titleData = "";

                //创建班级知识点得分数组
                var teamData = new Array(dataTeam.length);

                //创建个人知识点得分数组
                var mineData = new Array(dataTeam.length);

                if(returnData != ""){
                    //循环获取班级知识点得分数据
                    $.each(dataTeam,function(n,value) {
                        if(typeof(value.percent) == "number"){
                            var p = Number((value.percent)).toFixed(2);
                            if(p > 0){
                                teamData[n] = p;
                            }else{
                                teamData[n] = 0;
                            }
                        }else{
                            teamData[n] = 0;
                        }

                        //获取知识点标题
                        if(titleData == ""){
                            titleData = "{\"name\":\"" + value.knowledgeName + "\",\"max\":\"100\"}";
                        }else{
                            titleData += ",{\"name\":\"" + value.knowledgeName + "\",\"max\":\"100\"}";
                        }

                        if(dataMine == ""){
                            mineData[n] = 0;
                        }

                    });

                    //循环获取个人知识点得分数据
                    $.each(dataMine,function(n,value) {
                        if(typeof(value.scoreCount) == "number"){
                            var p = Number((value.scoreCount)).toFixed(2);
                            if(p > 0){
                                mineData[n] = p;
                            }else{
                                mineData[n] = 0;
                            }
                        }else{
                            mineData[n] = 0;
                        }
                    });

                    if(titleData == ""){
                        $("#answer1").hide();
                    }

                    var json = "[{\"name\":\"我\",\"value\":["+mineData+"]},{\"name\":\"班级\",\"value\":["+teamData+"]}]"

                    initDTQKTJ(JSON.parse(json),JSON.parse("[" + titleData + "]"));
                }
            }
        });
    }

    function initDTQKTJ(data,titleData){
        //知识点 答题情况统计
        var myChart = echarts.init(document.getElementById('answer1'));
        option = {
            title: {
                text: ''
            },
            tooltip: {},
            legend: {
                data: ['我', '班级'],
                right:'20px',
                top:'10px'
            },
            radar: {
                indicator: titleData
            },
            series: [{
                name: '',
                type: 'radar',
                tooltip: {
                    trigger: 'item'
                },
                itemStyle: {normal: {
                    areaStyle: {type: 'default'},
                    label : {show: true}
                }
                },
                data : data
            }]
        };
        myChart.setOption(option);
    }

</script>

</html>
