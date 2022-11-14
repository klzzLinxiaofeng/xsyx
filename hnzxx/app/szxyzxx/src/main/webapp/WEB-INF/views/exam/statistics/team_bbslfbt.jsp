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
<h3>本班三率次数分布图</h3>
<div id="distribution"></div>
</body>
<script type="text/javascript">
    $(function(){
        getData2();
    });

    function createUI2(data){
        // 本班三率次数分布图
        var myChart = echarts.init(document.getElementById('distribution'));
        option = {
            title : {
                text: '',
                subtext: ''
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} %",
            },
            legend: {
                bottom:'10px',
                data:['优秀人数','良好人数','及格人数','不及格人数']
            },
            calculable : true,
            grid: {
                left: '2%',
                right: '4%',
                bottom: '12%',
                top: '5%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : ['百分比']
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
            series : data
        };
        myChart.setOption(option);
    }

    function getData2(){
        $.post("${ctp}/statistic/teamScoreSectionConta",{"examId":${exam.id}}, function(returnData, status) {
            if ("success" === status) {
                var json = "[{\"name\":\"优秀人数\",\"type\":\"bar\",\"data\":["+ Number(returnData["hightCount"]).toFixed(2) +"]}," +
                        "{\"name\":\"良好人数\",\"type\":\"bar\",\"data\":["+ Number(returnData["lowCount"]).toFixed(2) +"]}," +
                        "{\"name\":\"及格人数\",\"type\":\"bar\",\"data\":["+ Number(returnData["passCount"]).toFixed(2) +"]}," +
                        "{\"name\":\"不及格人数\",\"type\":\"bar\",\"data\":["+ Number(returnData["noPassCount"]).toFixed(2) +"]}]"
                var data = JSON.parse(json);
                createUI2(data);
            }
        },'json');
    }
</script>
</html>
