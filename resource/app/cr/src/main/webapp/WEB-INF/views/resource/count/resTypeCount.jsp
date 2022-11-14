<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="zyzlfb" style="width: 90%; height: 500px;"></div>
<script>

    //资源类型分布
    $('#zyzlfb').highcharts({
        chart: {
            type: 'column',
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
            categories: [
                '微课',
                '课件',
                '试卷',
                '作业',
                '教案',
                '素材'
            ],
            labels: {
                style: {
                    color: '#666',//颜色
                }
            },
            crosshair: true
        },
        yAxis: {
            minRange: 1,
            min: 0,
            title: {
                text: ''
            },
            labels: {
                style: {
                    color: '#fff',//颜色
                }
            },
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
            column: {
                dataLabels:{
                    enabled:true, // dataLabels设为true
                    style:{
                        color:'#fff'
                    }
                },
                colorByPoint:true
            }
        },
        series: [{
            //colorByPoint:true,  或者直接写在这里
            data: ${resTypeCountList}
        }]
    });
</script>
