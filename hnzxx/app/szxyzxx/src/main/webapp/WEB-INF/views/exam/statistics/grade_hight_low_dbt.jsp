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
<h3>全年级最高分和最低分对比图</h3>
<div class=""></div>
<div id="height_contrast" style="width: 500px;height: 415px;margin: 0 auto;"></div>
<div class="clear"></div>
</body>
<script type="text/javascript">
    $(function(){
        getGradeDBT();
    });

    function createUIGradeDBT(data1,data2){
        //全年级最高分和最低分对比图
        var myChart = echarts.init(document.getElementById('height_contrast'));
        option = {
            title: {
                text: ''
            },
            tooltip : {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            legend: {
                data:['最低分','最高分'],
                bottom:'1%',
            },
            grid: {
                left: '0%',
                right: '10%',
                bottom: '10%',
                top:'5%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : data1,
                    axisLabel:{  
                        interval:0,//横轴信息全部显示  
                        rotate:-20,//-30度角倾斜显示  
                   }  
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            series : data2
        };
        myChart.setOption(option);
    }

    function getGradeDBT(){
        $.post("${ctp}/statistic/gradeAvgLd",{"examId":${exam.id}}, function(returnData, status) {
            if ("success" === status) {
                var hight = new Array(returnData.length);
                var low = new Array(returnData.length);
                var tit = new Array(returnData.length);
                $.each(returnData,function(n,value) {
                    if(typeof(value.highestScore) == "number"){
                        if(value.highestScore > 0){
                            hight[n] = (Number(value.highestScore)).toFixed(2);
                        }else{
                            hight[n] = 0;
                        }
                    }else{
                        hight[n] = 0;
                    }
                    if(typeof(value.lowestScore) == "number"){
                        if(value.lowestScore > 0){
                            low[n] = (Number(value.lowestScore)).toFixed(2);
                        }else{
                            low[n] = 0;
                        }
                    }else{
                        low[n] = 0;
                    }
                    tit[n] = value.teamName;
                });

               var data2 = "[{\"name\":\"最高分\",\"type\":\"line\",\"data\":[" + hight + "]}," +
                       "{\"name\":\"最低分\",\"type\":\"line\",\"data\":[" + low + "]}]";

                createUIGradeDBT(tit,JSON.parse(data2));
            }
        },'json');
    }
</script>
</html>
