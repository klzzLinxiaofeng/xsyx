<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="zyjszl2" style="width: 80%; height: 500px;  margin: 0 auto"></div>
<script>

    function getTitle() {
        var title = [
            '1月',
            '2月',
            '3月',
            '4月',
            '5月',
            '6月',
            '7月',
            '8月',
            '9月',
            '10月',
            '11月',
            '12月'
        ]
        if(${countType eq 'week'}){
            title = ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'];
        }else if(${countType eq 'month'}){
            if(${updateResCountSize eq 5}){
                title = ['第一周','第二周','第三周','第四周','第五周'];
            }else {
                title = ['第一周','第二周','第三周','第四周'];
            }
        }
        return title;
    }
    //资源更新量
    $('#zyjszl2').highcharts({
        chart: {
            type: 'line',
            backgroundColor: 'rgba(0,0,0,0)'
        },
        title: {
            text: null
        },
        subtitle: {
            text: ''
        },
        credits:{
            enabled:false // 禁用版权信息
        },
        xAxis: {
            categories: getTitle(),
            labels: {
                style: {
                    color: '#666',//颜色
                }
            },
            crosshair: true
        },
        yAxis: {
            minRange: 1,
            title: {
                text: ''
            }

        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{point.y} </td>' +
            '</tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        legend:{
            enabled :false
        },
        plotOptions: {
            series: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        series: [{
            //colorByPoint:true,  或者直接写在这里
            data: ${updateResCountList}
        }]
    });
</script>
