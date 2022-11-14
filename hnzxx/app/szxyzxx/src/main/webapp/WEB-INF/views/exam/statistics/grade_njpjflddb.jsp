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
<h3>全年级平均分 雷达对比图</h3>
<div id="radar2" style="width: 500px;height: 415px;margin: 0 auto;"></div>
<div class="clear"></div>
</body>
<script type="text/javascript">
    $(function(){
        getDataLd();
    });

    function createUILd(data1,data2){
        //全年级平均分 雷达对比图
        var myChart = echarts.init(document.getElementById('radar2'));
        option = {
            title: {
                text: ''
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                x: 'center',
                data:['年级标准差','班级标准差'],
                bottom: '3%',
            },
            radar: [
                {
                    indicator: data1,
                    center: ['50%','45%'],
                    radius: 140,
                },
            ],
            series: [
                {
                    type: 'radar',
                    tooltip: {
                        trigger: 'item'
                    },
                    itemStyle: {normal: {
                        areaStyle: {type: 'default'},
                        label : {show: true}
                    }
                    },
                    data: data2
                },
            ]
        };
        myChart.setOption(option);
    }

    function getDataLd(){
        $.post("${ctp}/statistic/gradeAvgLd",{"examId":${exam.id}}, function(returnData, status) {
            if ("success" === status) {
                var data1 = "";
                var teamAvg = new Array(returnData.length);
                var gradeAvg = 0;
                $.each(returnData,function(n,value) {
                    if(data1 == ""){
                        data1 = "{\"text\":\""+value.teamName+"\",\"max\":\""+value.fullScore+"\"}";
                    }else{
                        data1 += ",{\"text\":\""+value.teamName+"\",\"max\":\""+value.fullScore+"\"}";
                    }
                    if(typeof(value.averageScore) == "number"){
                        teamAvg[n] = value.averageScore;
                        gradeAvg += value.averageScore;
                    }else{
                        teamAvg[n] = 0;
                    }
                });

                json = "["+data1+"]";

                var data2 = "[{\"name\":\"年级平均分\",\"value\":[" + getArr2Grade(returnData.length,gradeAvg/returnData.length) + "]},{\"name\":\"班级平均分\",\"value\":["+teamAvg+"]}]";

                createUILd(JSON.parse(json),JSON.parse(data2));
            }
        },'json');
    }

    function getArr2Grade(len,num){
        var arr = new Array(len);
        for(var i = 0; i < len; i++){
            arr[i] = Number(num).toFixed(2);
        }
        return arr;
    }

    function countBZC(data){
        if(data == null || data.length == 0){
            return 0;
        }
        var length = data.length;
        var temp = new Array(length);

        var tempAvgl = 0;
        for (var i = 0; i < length; i++) {
            tempAvgl += data[i];
        }
        tempAvgl = tempAvgl/data.length;

        for (var i = 0; i < length; i++) {
            var dev = parseFloat(data[i]) - parseFloat(tempAvgl);
            temp[i] = Math.pow(dev, 2);
        }
        var powSum = 0;

        for (var j = 0; j < temp.length; j++) {
            if (temp[j].toString() != "" || temp[j].toString() != null) {
                powSum = parseFloat(powSum) + parseFloat(temp[j].toString());
            }
        }

        return Math.sqrt(parseFloat(powSum) / parseFloat(length)).toFixed(2);
    }
</script>
</html>
