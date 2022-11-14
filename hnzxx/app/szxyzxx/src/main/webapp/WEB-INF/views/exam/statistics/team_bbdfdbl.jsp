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
<h3>本班得分段比例分布图</h3>
<div id="cake"></div>
</body>
<script type="text/javascript">
    $(function(){
        getData1();
    });

    function createUI1(data){
        // 本班得分段比例分布图
        var myChart = echarts.init(document.getElementById('cake'));
        option = {
            title : {
                text: '',
                subtext: '',
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {d}%"
            },
            legend: {
                left: 'left',
            },
            series : [
                {
                    name: '',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '50%'],
                    data:data,
                    itemStyle:{
                        normal:{
                            label:{
                                show: true,
                                formatter: '{b}({d}%)'
                            },
                            labelLine :{show:true}
                        }
                    }
                }
            ]
        };
        myChart.setOption(option);
    }

    function getData1(){
        $.post("${ctp}/statistic/teamScoreSectionfsd",{"examId":${exam.id}}, function(returnData, status) {
            if ("success" === status) {
                var json = "[{\"name\":\"" + returnData["hightName"] + "分\",\"value\":["+ returnData["hightCount"] +"]}," +
                        "{\"name\":\"" + returnData["lowName"] + "分\",\"value\":["+ returnData["lowCount"] +"]}," +
                        "{\"name\":\"" + returnData["passName"] + "分\",\"value\":["+ returnData["passCount"] +"]}," +
                        "{\"name\":\"" + returnData["noPassName"] + "分\",\"value\":["+ returnData["noPassCount"] +"]}]"
                var data = JSON.parse(json);
                createUI1(data);
            }
        },'json');
    }
</script>
</html>
