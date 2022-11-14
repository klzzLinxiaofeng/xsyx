<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<html>
<head>
    <title>Title</title>
    <%@ include file="/views/embedded/common.jsp"%>
</head>
<body>
<h3>全年级三率综合折线对比图</h3>
<div id="folding" style="width: 500px;height: 415px;margin: 0 auto;"></div>
</body>
<script type="text/javascript">

    $(function(){
        getGradeDBT2();
    });

    function createUIGradeDBT2(data1,data2){
        //全年级三率综合折线对比图
        var myChart = echarts.init(document.getElementById('folding'));
        option = {
            title: {
                text: ''
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['优秀率','良好率','及格率','不及格率'],
                bottom:'1%',
            },
            grid: {
                left: '0%',
                right: '10%',
                bottom: '8%',
                top:'5%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: data1,
                axisLabel:{
                    interval:0,//横轴信息全部显示
                    rotate:-20,//-30度角倾斜显示
                }
            },
            yAxis: {
                type: 'value'
            },
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            series: data2
        };
        myChart.setOption(option);
    }

    function getGradeDBT2(){
        $.post("${ctp}/statistic/getAllGradeTreeRia",{"examId":${exam.id}}, function(returnData, status) {
            if ("success" === status) {
                var hight = new Array(returnData.length);
                var low = new Array(returnData.length);
                var pass = new Array(returnData.length);
                var noPass = new Array(returnData.length);
                var tit = new Array(returnData.length);
                $.each(returnData,function(n,value) {
                    if(typeof(value.hight) == "number"){
                        hight[n] = (Number(value.hight * 100)).toFixed(2);
                    }else{
                        hight[n] = 0;
                    }
                    if(typeof(value.low) == "number"){
                        low[n] = (Number((value.low+value.hight)  * 100)).toFixed(2);
                    }else{
                        low[n] = 0;
                    }
                    if(typeof(value.pass) == "number"){
                        pass[n] = (Number((value.low+value.hight+value.pass)  * 100)).toFixed(2);
                    }else{
                        pass[n] = 0;
                    }
                    if(typeof(value.noPass) == "number"){
                        noPass[n] = (Number(value.noPass  * 100)).toFixed(2);
                    }else{
                        noPass[n] = 0;
                    }
                    tit[n] = value.teamName;
                });


                var data2 = "[" +
                        "{\"name\":\"优秀率\",\"type\":\"line\",\"itemStyle\" : \"{normal:{label:{show: true}}\"," +
                        "\"data\":[" + hight + "]}," +
                        "{\"name\":\"良好率\",\"type\":\"line\",\"itemStyle\" : \"{normal:{label:{show: true}}\"," +
                        "\"data\":[" + low + "]}," +
                        "{\"name\":\"及格率\",\"type\":\"line\",\"itemStyle\" : \"{normal:{label:{show: true}}\"," +
                        "\"data\":[" + pass + "]}," +
                        "{\"name\":\"不及格率\",\"type\":\"line\",\"itemStyle\" : \"{normal:{label:{show: true}}\"," +
                        "\"data\":[" + noPass + "]}" +
                        "]";
                createUIGradeDBT2(tit,JSON.parse(data2));
            }
        },'json');
    }
</script>
</html>
