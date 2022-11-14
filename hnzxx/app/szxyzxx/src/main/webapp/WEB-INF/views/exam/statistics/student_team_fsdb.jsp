<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>分数对比</h3>
<div id="fraction" style="width: 500px;height: 415px;margin: 0 auto;"></div>
</body>
<script type="text/javascript">
    $(function(){
        getDataForMine();
    })

    function createUI_teamStudent_fsdb(data){
        //对比分数
        var myChart = echarts.init(document.getElementById('fraction'));
        option = {
            title: {
                text: '',
                subtext: ''
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} 分",
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                bottom:'10px',
                data: ['最高分', '年级平均分', '本班平均分', '最低分','我']
            },
            grid: {
                left: '-10%',
                right: '4%',
                bottom: '12%',
                top: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                data: ['分数项对比图'],
                show : false
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

    function getDataForMine(){
        $.post("${ctp}/statistic/teamScoreCompareMe",{"examId":${exam.id},"studentId":$("#stu").val()}, function(returnData, status) {
            if ("success" === status) {
                var high = 0;
                var gradeAvg = 0;
                var teamAvg = 0;
                var lowScore = 0;
                var mimeScore = 0;
                if(typeof(returnData["hightScore"]) == "number"){
                    high = Number(returnData["hightScore"]).toFixed(2);
                }
                if(typeof(returnData["gradeAvg"]) == "number"){
                    gradeAvg = Number(returnData["gradeAvg"]).toFixed(2);
                }
                if(typeof(returnData["teamAvg"]) == "number"){
                    teamAvg = Number(returnData["teamAvg"]).toFixed(2);
                }
                if(typeof(returnData["lowScore"]) == "number"){
                    lowScore = Number(returnData["lowScore"]).toFixed(2);
                }
                if(typeof(returnData["mineScore"]) == "number"){
                    mimeScore = Number(returnData["mineScore"]).toFixed(2);
                }
                var json = "[{\"name\":\"最高分\",\"type\":\"bar\",\"data\":["+ high +"]}," +
                        "{\"name\":\"年级平均分\",\"type\":\"bar\",\"data\":["+ gradeAvg +"]}," +
                        "{\"name\":\"本班平均分\",\"type\":\"bar\",\"data\":["+ teamAvg +"]}," +
                        "{\"name\":\"最低分\",\"type\":\"bar\",\"data\":["+ lowScore +"]}," +
                        "{\"name\":\"我\",\"type\":\"bar\",\"data\":["+ mimeScore +"]}" +
                        "]"
                var data = JSON.parse(json);
                createUI_teamStudent_fsdb(data);
            }
        },'json');
    }

</script>
</html>
