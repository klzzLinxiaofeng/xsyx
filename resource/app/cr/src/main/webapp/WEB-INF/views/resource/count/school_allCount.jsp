<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="margin: 0 100px;">
    <div id="zyjszl" style="width:80%; height: 500px;">
    </div>
</div>
<script>
    //资源总量
    $('#zyjszl').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: ''
        },
        credits: {
            enabled: false
        },
        colors:[
            '#65ad57',//第一个颜色，欢迎加入Highcharts学习交流群294191384
            '#f5a948',//第二个颜色
            '#2298ef',//第三个颜色
            '#ff5534', //。。。。
            '#b035b8',
            '#27b0ce'
        ],
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.y} ',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            name: "占比",
            type: 'pie',
//            colorByPoint: true,
            data: [${schoolAllResJsonStr}]
        }]
    });

    $(function () {
        $("#schoolAllCountSize").text('(${schoolAllCountSize} 套)');
        if(${schoolAllResJsonStr eq ''}){
            $("#zyjszl").html("<div class='tip'>暂无数据</div>");
        }
    })
</script>
