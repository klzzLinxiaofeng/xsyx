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
<h3>全年级三率综合堆积对比图</h3>
<div id="comprehensive" style="width: 500px;height: 415px;margin: 0 auto;"></div>
<div class="clear"></div>
</body>
<script type="text/javascript">
    $(function(){
        getGradeDBT1();
    });

    function createUIGradeDBT1(data1,data2){
        //全年级三率综合堆积对比图
        var myChart = echarts.init(document.getElementById('comprehensive'));
        option = {
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['优秀率','良好率','及格率', '不及格率'],
                bottom: '3%',
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '10%',
                top:'5%',
                containLabel: true
            },
            xAxis:  {
                type: 'value'
            },
            yAxis: {
                type: 'category',
                data: data1
            },
            label: {
                normal: {
                    show: true,
                    position: 'insideLeft'
                }
            },
            series: data2
        };
        myChart.setOption(option);
    }

    function getGradeDBT1(){
        $.post("${ctp}/statistic/getAllGradeTreeRia",{"examId":${exam.id}}, function(returnData, status) {
            if ("success" === status) {
                var hight = new Array(returnData.length);
                var low = new Array(returnData.length);
                var pass = new Array(returnData.length);
                var noPass = new Array(returnData.length);
                var tit = new Array(returnData.length);

                var jsonOfHigh = "";
                var jsonOfLow = "";
                var jsonOfPass = "";

                $.each(returnData,function(n,value) {
                    var h = 0;
                    var l = 0;
                    var p = 0;
                    if(typeof(value.hight) == "number"){
                        hight[n] = (Number(value.hight * 100)).toFixed(2);
                        h = value.hight;
                        if(jsonOfHigh == ""){
                            if(value.hight > 0){
                                jsonOfHigh = "{\"name\":\""+value.teamName+"\",\"value\":\""+Number(value.hight *100).toFixed(2)+"\"}";
                            }
                        }else{
                            if(value.hight > 0){
                                jsonOfHigh += ",{\"name\":\""+value.teamName+"\",\"value\":\""+Number(value.hight * 100).toFixed(2)+"\"}";
                            }
                        }
                    }else{
                        hight[n] = 0;
                    }

                    if(typeof(value.low) == "number"){
                        low[n] = (Number(value.low  * 100)).toFixed(2);
                        l = value.low;
                        if(jsonOfLow == ""){
                            if((h+l) > 0){
                                jsonOfLow = "{\"name\":\""+value.teamName+"\",\"value\":\""+Number((h+l) * 100).toFixed(2)+"\"}";
                            }
                        }else{
                            if((h+l) > 0){
                                jsonOfLow += ",{\"name\":\""+value.teamName+"\",\"value\":\""+Number((h+l) * 100).toFixed(2)+"\"}";
                            }
                        }
                    }else{
                        low[n] = 0;
                    }

                    if(typeof(value.pass) == "number"){
                        pass[n] = (Number(value.pass  * 100)).toFixed(2);
                        p = value.pass;
                        if(jsonOfPass == ""){
                            if((h+l+p)>0){
                                jsonOfPass = "{\"name\":\""+value.teamName+"\",\"value\":\""+Number((h+l+p) * 100).toFixed(2)+"\"}";
                            }
                        }else{
                            if((h+l+p) > 0){
                                jsonOfPass += ",{\"name\":\""+value.teamName+"\",\"value\":\""+Number((h+l+p) * 100).toFixed(2)+"\"}";
                            }
                        }
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
                        "{\"name\":\"优秀率\",\"type\":\"bar\",\"stack\":\"总量\"," +
                        "\"data\":[" + hight + "]}," +
                        "{\"name\":\"良好率\",\"type\":\"bar\",\"stack\":\"总量\"," +
                        "\"data\":[" + low + "]}," +
                        "{\"name\":\"及格率\",\"type\":\"bar\",\"stack\":\"总量\"," +
                        "\"data\":[" + pass + "]}," +
                        "{\"name\":\"不及格率\",\"type\":\"bar\",\"stack\":\"总量\"," +
                        "\"data\":[" + noPass + "]}" +
                        "]";

                var json = JSON.parse(data2);
                createUIGradeDBT1(tit,json);

                //初始年级优秀率
                if(jsonOfHigh == ""){
                    $("#ThreeRatiosOfHigh").hide();
                }
                if(jsonOfLow == ""){
                    $("#ThreeRatiosOfLow").hide();
                }
                if(jsonOfPass == ""){
                    $("#ThreeRatiosOfPass").hide();
                }

                initThreeRatiosOfHigh(JSON.parse("["+jsonOfHigh+"]"));

                //初始年级良好率
                initThreeRatiosOfLow(JSON.parse("["+jsonOfLow+"]"));

                //初始年级及格率
                initThreeRatiosOfPass(JSON.parse("["+jsonOfPass+"]"));
            }
        },'json');
    }
</script>
</html>
