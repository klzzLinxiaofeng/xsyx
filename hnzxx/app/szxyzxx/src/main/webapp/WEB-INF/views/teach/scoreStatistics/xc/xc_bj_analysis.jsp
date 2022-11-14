<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/kwgl.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts.js"></script>
    <script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts-more.js"></script>
    <title>查看详情</title>
</head>
<body >
<div class="kwgl">
    <div class="return_kw">
        <p>[班级测试] ${teamName} ${examWorks.name} </p>
        <%--<div class="njtk">--%>
            <%--<a href="javascript:void(0)" class="on">班级单科分析</a>--%>
            <%--<a href="javascript:void(0)" >班级趋势分析</a>--%>
        <%--</div>--%>
        <a href="javascript:void(0)" class="btn btn-grey" onclick="toIndex();">返回</a>
        <%--<a href="javascript:void(0)" class="btn btn-blue">导出分析报告</a>--%>

    </div>
    <div class="xcym">
        <div class="kwgl">
            <div class="njfx_km pd20">
                <a href="javascript:void(0)" class="choose">${subjectName}</a>
            </div>
            <div class="pd20">
                <div class="kw_select">
                    <p class="xnxq fl" style="margin:0">${title}</p>
                    <p class="fr" style="color:#86939d">考试时间：<fmt:formatDate value="${examWorks.examDateBegin}" pattern="yyyy/MM/dd HH:mm"/></p>
                </div>

                <div class="nj_basis_info">
                    <p class="title">班级单科基础信息</p>
                    <div class="ks_bj">
                        <div class="ks_bj_list">
                            <p>应试班级:</p>
                            <div class="list">
                                <c:choose>
                                    <c:when test="${empty teamSubject.postTeacherId}">
                                        <span class="dr_no">${teamName}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="dr_ok">${teamName}</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <p class="ks_bj_info">
                            <span><i class="dr_ok"></i>成绩导入完成</span>
                            <span><i class="dr_no"></i>成绩未导入</span>
                        </p>
                    </div>
                    <div class="color_kuai">
                        <div class="ck1">
                            <p class="ck_num"><span>${examStudentCount}</span>/<span>${studentCount}</span></p>
                            <p class="ck_info">应试人数</p>
                        </div>
                        <div class="ck2">
                            <p class="ck_num"><span>${examStat.averageScore}</span>/<span>${examWorksSubject.fullScore}</span></p>
                            <p class="ck_info">年级总分平均分</p>
                        </div>
                        <div class="ck3">
                            <p class="ck_num"><span>${examStat.highestScore}</span>/<span>${examWorksSubject.fullScore}</span></p>
                            <p class="ck_info">年级总分最高分</p>
                        </div>
                        <div class="ck4">
                            <p class="ck_num"><span>${examStat.lowestScore}</span>/<span>${examWorksSubject.fullScore}</span></p>
                            <p class="ck_info">年级总分最低分</p>
                        </div>
                    </div>
                </div>

                <div class="kwgl_main">
                    <p class="title">班级三率分布</p>
                    <div id="container5" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
                    <div class="container5_legend">
                        <p style="color:#2af598">优秀：<span><fmt:formatNumber type="number" value="${examWorksSubject.highScore}" maxFractionDigits="0"/>-<fmt:formatNumber type="number" value="${examWorksSubject.fullScore}" maxFractionDigits="0"/></span>分</p>
                        <p style="color:#03a9f5">良好：<span><fmt:formatNumber type="number" value="${examWorksSubject.lowScore}" maxFractionDigits="0"/>-<fmt:formatNumber type="number" value="${examWorksSubject.highScore}" maxFractionDigits="0"/></span>分</p>
                        <p style="color:#f7af07">及格：<span><fmt:formatNumber type="number" value="${examWorksSubject.passScore}" maxFractionDigits="0"/>-<fmt:formatNumber type="number" value="${examWorksSubject.lowScore}" maxFractionDigits="0"/></span>分</p>
                        <p style="color:#ff6a88">不及格：<span>0-<fmt:formatNumber type="number" value="${examWorksSubject.passScore}" maxFractionDigits="0"/></span>分</p>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="kwgl_main">
                    <p class="title">班级单科名次分布图</p>
                    <div id="container8" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
                </div>
                <div class="kwgl_main">
                    <p class="title">班级单科分数段图</p>
                    <div id="container9" style="min-width: 400px; height: 500px; max-width: 920px; margin: 0 auto"></div>
                </div>
                <div class="kwgl_main">
                    <p class="title">班级单科平均分趋势</p>
                    <div id="container10" style="min-width: 400px; height: 700px; max-width: 920px; margin: 0 auto"></div>
                </div>
            </div>
        </div>
    </div>


    <%--<div class="xcym" style="display: none">--%>
        <%--<div class="kwgl">--%>
            <%--<div class="njfx_km pd20">--%>
                <%--<a href="javascript:void(0)" class="choose">${subjectName}</a>--%>
            <%--</div>--%>
            <%--<div class="pd20">--%>
                <%--<div class="kw_select">--%>
                    <%--<p class="xnxq fl" style="margin:0">${title}</p>--%>
                    <%--<p class="fr" style="color:#86939d">考试时间：<fmt:formatDate value="${examWorks.examDateBegin}" pattern="yyyy/MM/dd HH:mm"/></p>--%>
                <%--</div>--%>
                <%--<div class="nj_basis_info">--%>
                    <%--<p class="title">班级单科基础信息</p>--%>
                    <%--<div class="ks_bj">--%>
                        <%--<div class="ks_bj_list">--%>
                            <%--<p>应试班级:</p>--%>
                            <%--<div class="list">--%>
                                <%--<c:choose>--%>
                                    <%--<c:when test="${empty teamSubject.postTeacherId}">--%>
                                        <%--<span class="dr_no">${teamName}</span>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                        <%--<span class="dr_ok">${teamName}</span>--%>
                                    <%--</c:otherwise>--%>
                                <%--</c:choose>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<p class="ks_bj_info">--%>
                            <%--<span><i class="dr_ok"></i>成绩导入完成</span>--%>
                            <%--<span><i class="dr_no"></i>成绩未导入</span>--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="color_kuai">--%>
                        <%--<div class="ck1">--%>
                            <%--<p class="ck_num"><span>${examStat.studentCount}</span>/<span>${studentCount}</span></p>--%>
                            <%--<p class="ck_info">应试人数</p>--%>
                        <%--</div>--%>
                        <%--<div class="ck2">--%>
                            <%--<p class="ck_num"><span>${examStat.averageScore}</span>/<span>${examWorksSubject.fullScore}</span></p>--%>
                            <%--<p class="ck_info">年级总分平均分</p>--%>
                        <%--</div>--%>
                        <%--<div class="ck3">--%>
                            <%--<p class="ck_num"><span>${examStat.highestScore}</span>/<span>${examWorksSubject.fullScore}</span></p>--%>
                            <%--<p class="ck_info">年级总分最高分</p>--%>
                        <%--</div>--%>
                        <%--<div class="ck4">--%>
                            <%--<p class="ck_num"><span>${examStat.lowestScore}</span>/<span>${examWorksSubject.fullScore}</span></p>--%>
                            <%--<p class="ck_info">年级总分最低分</p>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>

                <%--<div class="kwgl_main">--%>
                    <%--<p class="title">班级单科平均分趋势</p>--%>
                    <%--<div id="container10" style="min-width: 400px; height: 700px; max-width: 920px; margin: 0 auto"></div>--%>
                <%--</div>--%>
                <%--<div class="kwgl_main">--%>
                    <%--<p class="title">班级单科平均分各班对比趋势</p>--%>
                    <%--<div id="container11" style="min-width: 400px; height: 700px; max-width: 920px; margin: 0 auto"></div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
</div>
</body>
<script>
    $(function(){
        $('.njtk a').click(function(){
            $(this).addClass('on');
            $(this).siblings().removeClass('on');
            var i=$(this).index();
            $(".xcym").hide();
            $(".xcym").eq(i).show();
        });
    })

    $(function(){
        /*container5  */
        var chart = null;
        var color3= ['#ff6a88','#f7af07','#03a9f5','#16b2eb'] ;
        var color4= ['#fca599','#ffe366' ,'#90caf8','#2af598'] ;
        // Radialize the colors
        Highcharts.getOptions().colors = Highcharts.map(color3, function (color) {
            var v=color;
            var j=0;
            var color_end='#fff';
            for(var i=0;i<color3.length;i++){
                if(color3[i]==v){
                    j=i;
                    color_end=color4[j]
                }
            }
            return {
                linearGradient: { x1: 0, y1: 1, y2: 0, x2: 0 }, //横向渐变效果 如果将x2和y2值交换将会变成纵向渐变效果
                stops: [
                    [0, color],
                    [1, color_end] // darken
                ]
            };
        });
        $('#container5').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                spacing : [100, 0 , 40, 0]
            },
            title: {
                floating:true,
                text: null
            },
            tooltip: {
                headerFormat: '<small style="font-size:20px;">{point.key}</small><br/>',
                pointFormat: '<small style="font-size:20px;">{point.y}人</small><b  style="font-size:20px;">{point.percentage:.1f}%</b>',
                useHTML: true,
            },
            exporting:{
                enabled:false
            },
            credits: {
                enabled: false
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    connectorColor:'red',
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        formatter: function() {
                            if(this.point.name == '不及格率'){
                                return '<b style="color:#ff6a88">'+ this.point.name +' <br/><span style="color:#ff6a88">('+this.point.y+'人'+ Highcharts.numberFormat(this.percentage, 2)+'%)</span></b>';
                            }else if(this.point.name == '及格率'){
                                return '<b style="color:#f7af07">'+ this.point.name +' <br/><span style="color:#f7af07">('+this.point.y+'人'+ Highcharts.numberFormat(this.percentage, 2)+'%)</span></b>';
                            }else if(this.point.name == '良好率'){
                                return '<b style="color:#03a9f5">'+ this.point.name +' <br/><span style="color:#03a9f5">('+this.point.y+'人'+ Highcharts.numberFormat(this.percentage, 2)+'%)</span></b>';
                            }else if(this.point.name == '优秀率'){
                                return '<b style="color:#2af598">'+ this.point.name +' <br/><span style="color:#2af598">('+this.point.y+'人'+ Highcharts.numberFormat(this.percentage, 2)+'%)</span></b>';
                            }
                        },
                        style: {
                            fontWeight: 'bold',
                            fontSize:'18px',
                            fontFamily:"微软雅黑",
                            textShadow: '0px 0px 0px #fff'
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                innerSize: '65%',
                data: [
                    ['不及格率', ${examStudentCount != null ? examStudentCount : 0} - ${examStat.passCount != null ? examStat.passCount : 0}],
                    ['及格率', ${examStat.passCount != null ? examStat.passCount : 0} - ${examStat.lowCount != null ? examStat.lowCount : 0}],
                    ['良好率', ${examStat.lowCount != null ? examStat.lowCount : 0} - ${examStat.highCount != null ? examStat.highCount : 0}],
                    ['优秀率', ${examStat.highCount != null ? examStat.highCount : 0}]
                ]
            }]
        });


        /* container8 */
        $('#container8').highcharts({
            chart: {
                type: 'scatter',
                marginTop:55,
            },
            title: {
                text:null
            },
            legend: {
                enabled: false
            },
            xAxis: {
                min:0,
                allowDecimals: false,
                categories: ${nameList},
                title: {
                    enabled: true,
                },
                labels: {
                    enabled: false
                },
                startOnTick: true,
                endOnTick: true,
                showLastLabel: true
            },
            yAxis: {
                min:1,
                allowDecimals: false,
                gridLineDashStyle:'Dash',
                gridLineColor: '#d6d6d6',
                title: {
                    "text": "排名",
                    align: 'high',
                    rotation:0,
                    x:50,
                    y:-20,
                    style:{
                        fontSize:16,
                        color: '#999999',
                    }
                },
                labels: {
                    y:5,
                    style: {
                        color: '#999999',
                        fontSize:16
                    }
                },
                reversed: true,
                plotLines: [{
                    color: '#ff6a88',
                    dashStyle: 'Solid',
                    width: 3,
                    value: ${avgRank},
                    label: {
                        align: 'left',
                        style: {
                            fontStyle: 'normal',
                            color:'#fd9494',
                            fontSize:20
                        },
                        text: '平均分名次线：${avgRank}',
                        x: -10,
                        y:25
                    },
                    zIndex: 3
                }]
            },
            exporting:{
                enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
            },
            credits: {
                enabled:false
            },
            tooltip: {
                shared: true,
                useHTML: true,
                formatter: function () {
                    return '<small style="font-size:18px">' + this.x + '</small><br/>' +
                        '<small style="font-size:18px">总分：'+this.point.fs+'</small><br/>'+
                        '<small style="font-size:18px"> 排名：'+this.y+'</small>';
                },
            },
            plotOptions: {
                scatter: {
                    marker: {
                        radius: 8 //变大
                    },
                    series:{
                        borderRadius:7,
                    }
                }},
            series: [{
                color: 'rgba(3, 169, 245,0.8)',
                data: ${teamRankList}
            }]
        });

        /*container9  */
        var length = 10;
        var all_score=${examWorksSubject.fullScore};
        var current_score=378;
        var class_score=${teamScoreList};
        var d_number= Math.ceil(all_score/length);
        var fsd=[],fsd_number=[];
        var l=class_score.length;
        var you_score=Math.floor(current_score/d_number);
        if(all_score==current_score){
            you_score--
        }
        for(var i=0;i<length;i++){
            if(i==length-1){
                var inputValue = i*d_number+'-'+(all_score)+'段';
            }else{
                var inputValue = i*d_number+'-'+(i*d_number+d_number)+'段';
            }
            fsd.push(inputValue);
        }
        for(var i=0;i<length;i++){
            var num=0;
            if(i==length-1){
                $.each(class_score,function(index,value){
                    if(value>(length-1)*d_number&&value<=all_score){
                        num++;
                    }
                });
            }else if(i==0){
                $.each(class_score,function(index,value){
                    if(value>=0&&value<=d_number){
                        num++;
                    }
                });
            }else{
                $.each(class_score,function(index,value){
                    if(value>i*d_number&&value<=i*d_number+d_number){
                        num++;
                    }
                });
            }
            /* if(i==you_score){
             num={y:num,color : "#fe8890"}
             } */
            fsd_number.push(num);
        }
        $('#container9').highcharts({
            chart: {
                type: 'bar',
                marginTop:40,
            },
            title: {
                text: null
            },
            xAxis: {
                categories: fsd,
                title: {
                    text: '分数段',
                    align: 'high',
                    rotation:0,
                    x:100,
                    y:-10,
                    style:{
                        fontSize:16,
                        color:'#999999'
                    }
                },
                labels: {
                    y:5,
                    style: {
                        fontSize:16,
                    }
                },
                reversed: false,
                tickWidth:0,
                gridLineDashStyle:'Dash',
                lineWidth: 0
            },
            yAxis: {
                allowDecimals: false,
                title: {
                    text: '处于该分数段的人数',
                    style: {
                        fontSize:16,
                    }
                },
                labels: {
                    style: {
                        fontSize:16,
                    }
                },
                min:0,
                gridLineDashStyle:'Dash',
                gridLineColor: '#d6d6d6',
            },
            legend: {
                enabled:false
            },
            tooltip: {
                crosshairs: true,
                headerFormat: '<small style="font-size:20px;">{point.key}</small><br/>',
                pointFormat: '<span style="font-size:20px;" >{point.y}人<br/>',
                shared: true,
                useHTML: true,
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true,
                        allowOverlap: true,
                        color:'gray',
                        style: {
                            fontWeight: 'bold',
                            fontSize: '14px',
                        },
                        formatter: function() {
                            if (this.y > 0)
                                return this.y ; //这里进行判断
                        },
                    },
                },
                series:{
                    borderRadius:7,
                }
            },
            exporting:{
                enabled:false
            },
            credits: {
                enabled: false
            },
            series: [{
                name:'a',
                color:'#73c3f7',
                data: fsd_number
            }]
        });

        $('#container10').highcharts({
            chart: {
                type: 'line',
            },
            title: {
                text: null
            },
            xAxis: {
                gridLineColor: '#d6d6d6',
                gridLineWidth: 1,
                gridLineDashStyle: 'Dot',
                categories: ${examNameList},
                labels: {
                    y:30,
                    align: 'left',
                    style:{
                        color:'#999999',
                        fontSize:'16',
                        writingMode : 'tb-rl',//文字竖排样式,
                    }
                }
            },
            yAxis: {
                tickInterval: 10,
                gridLineColor: '#d6d6d6',
                gridLineWidth: 1,
                gridLineDashStyle: 'Dot',
                title: {
                    enabled:false
                },
                min:1,
                labels: {
                    style:{
                        color:'#999999',
                        fontSize:'16',
                    },
                    y:5
                },
            },
            legend: {
                verticalAlign: 'top',
                align:'right',
                itemStyle : {
                    'fontSize' : '18px',
                    'color':'#666666'
                }
            },
            tooltip: {
                crosshairs: true,
                shared: true,
                headerFormat: '<small style="font-size:20px">{point.key}</small><br>',
                pointFormat: '<span style="font-size:20px;color:{series.color}">{series.name}: <b style="font-size:20px">{point.y}</b><br/>',
                useHTML: true
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true,          // 开启数据标签
                        style:{
                            fontSize:'18',
                            color:'#666666'
                        }
                    }
                }
            },
            exporting:{
                enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
            },
            credits: {
                enabled:false
            },
            series: [{
                name: '班级单科平均分',
                color:'#07aaf5',
                data: ${teamAvgScoreList}
            }]
        });


        $('#container11').highcharts({
            chart: {
                type: 'line',
                marginRight:50
            },
            title: {
                text: null
            },
            xAxis: {
                gridLineColor: '#d6d6d6',
                gridLineWidth: 1,
                gridLineDashStyle: 'Dot',
                categories: ['模拟考1234561', '模拟考模拟考模拟考模', '模拟考', '模拟考', '模拟考', '模拟考',
                    '模拟考', '期中考试', '模拟考模拟考模拟考模', '模拟考', '模拟考', '期末考试'],
                labels: {
                    align: 'left',
                    style:{
                        color:'#999999',
                        fontSize:'16',
                        writingMode : 'tb-rl'//文字竖排样式,
                    }
                }
            },
            yAxis: {
                gridLineColor: '#d6d6d6',
                gridLineWidth: 1,
                gridLineDashStyle: 'Dot',
                title: {
                    enabled:false
                },
                labels: {
                    style:{
                        color:'#999999',
                        fontSize:'16',
                    },
                    y:5
                },
                min:0,
            },
            legend: {
                align: 'right',
                verticalAlign: 'top',
                symbolHeight: 18,
                symbolWidth: 18,
                symbolRadius: 12,
                itemStyle : {
                    'fontSize' : '18px',
                    'color':'#666666'
                }
            },
            tooltip: {
                crosshairs: true,
                shared: true,
                headerFormat: '<small style="font-size:20px">{point.key}</small><br>',
                pointFormat: '<span style="font-size:20px;color:{series.color}">{series.name}: <b style="font-size:20px">{point.y}</b><br/>',
                useHTML: true
            },
            plotOptions: {
                line: {
                    //enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                    dataLabels: {
                        enabled: true,
                        align: 'left',
                        style: {
                            fontSize:18,
                        },
                        allowOverlap:true,
                        x: 3,
                        verticalAlign: 'middle',
                        overflow: true,
                        crop: false,
                        useHTML: true
                    }
                }
            },
            exporting:{
                enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
            },
            credits: {
                enabled:false
            },
            series: [{
                name: '高一1班',
                color:'#07aaf5',
                data: [70, 69, 95, 85, 73, 66, 84, 85, 93, 83, 76,96 ],
                dataLabels: {
                    shadow:false,
                    style: {
                        color:'#07aaf5',
                    },
                },
            }, {
                name: '高一2班',
                color:'#4cb9c3',
                data: [39, 42, 57, 85, 25, 52, 70, 66, 42, 83, 66, 48],
                dataLabels: {
                    style: {
                        color:'#4cb9c3',
                    },
                },
            },{
                name: '高一3班',
                color:'#ff6b88',
                data: [{
                    y: 3.9,
                }, 1.2, 4.3, 6.2, 10.6, 8.8, 12.3, 14.2, 13.4, 10.3, 9.8, 10.5],
                dataLabels: {
                    style: {
                        color:'#ff6b88',
                    },
                },
            }]
        });
    });

    function toIndex(){
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/exam/index?isJoint=false";
    }

</script>
</html>