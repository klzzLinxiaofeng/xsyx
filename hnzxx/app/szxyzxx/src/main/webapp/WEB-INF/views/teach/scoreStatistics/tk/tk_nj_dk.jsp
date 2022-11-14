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
    <title>年级单科分析</title>
</head>
<body >
<div class="kwgl">
    <div class="njfx_km pd20">
        <c:forEach items="${subjectList}" var="item" >
            <a href="javascript:void(0)" <c:if test="${subjectCode eq item.subjectCode}">class="choose"</c:if> data-code="${item.subjectCode}">${item.subjectName}</a>
        </c:forEach>
    </div>

    <div class="pd20">
        <div class="kw_select">
            <p class="xnxq fl" style="margin:0">${title}</p>
            <p class="fr" style="color:#86939d">
                考试时间：
                <fmt:formatDate value="${examWorks.examDateBegin}" pattern="yyyy/MM/dd"/>
                -
                <fmt:formatDate value="${examWorks.examDateEnd}" pattern="MM/dd"/>
            </p>
        </div>
        <div class="nj_basis_info">
            <p class="title">年级基础信息</p>
            <div class="ks_bj">
                <div class="ks_bj_list">
                    <p>应试班级:</p>
                    <div class="list">
                        <c:forEach items="${teamList}" var="item" >
                            <c:choose>
                                <c:when test="${item.result eq 0}">
                                    <span class="dr_no">${item.teamName}</span>
                                </c:when>
                                <c:when test="${item.result eq 1}">
                                    <span class="dr_bfok">${item.teamName}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="dr_ok">${item.teamName}</span>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>
                <p class="ks_bj_info">
                    <span><i class="dr_ok"></i>成绩导入完成</span>
                    <span><i class="dr_bfok"></i>成绩部分导入</span>
                    <span><i class="dr_no"></i>成绩未导入</span>
                </p>
            </div>
            <div class="color_kuai">
                <div class="ck1">
                    <p class="ck_num"><span>${scoreMap.subjectStudentCount}</span>/<span>${scoreMap.gradeStudentCount}</span></p>
                    <p class="ck_info">应试人数</p>
                </div>
                <div class="ck2">
                    <p class="ck_num"><span>${scoreMap.avgScore}</span>/<span>${examWorksSubject.fullScore}</span></p>
                    <p class="ck_info">年级总分平均分</p>
                </div>
                <div class="ck3">
                    <p class="ck_num"><span>${scoreMap.highestScore}</span>/<span>${examWorksSubject.fullScore}</span></p>
                    <p class="ck_info">年级总分最高分</p>
                </div>
                <div class="ck4">
                    <p class="ck_num"><span>${scoreMap.lowestScore}</span>/<span>${examWorksSubject.fullScore}</span></p>
                    <p class="ck_info">年级总分最低分</p>
                </div>
            </div>
        </div>

        <div class="kwgl_main">
            <p class="title">年级三率分布</p>
            <div id="container5" style="height: 500px; width: 920px; margin: 0 auto"></div>
            <div class="container5_legend">
                <p style="color:#2af598">优秀：<span><fmt:formatNumber type="number" value="${examWorksSubject.highScore}" maxFractionDigits="0"/>-<fmt:formatNumber type="number" value="${examWorksSubject.fullScore}" maxFractionDigits="0"/></span>分</p>
                <p style="color:#03a9f5">良好：<span><fmt:formatNumber type="number" value="${examWorksSubject.lowScore}" maxFractionDigits="0"/>-<fmt:formatNumber type="number" value="${examWorksSubject.highScore}" maxFractionDigits="0"/></span>分</p>
                <p style="color:#f7af07">及格：<span><fmt:formatNumber type="number" value="${examWorksSubject.passScore}" maxFractionDigits="0"/>-<fmt:formatNumber type="number" value="${examWorksSubject.lowScore}" maxFractionDigits="0"/></span>分</p>
                <p style="color:#ff6a88">不及格：<span>0-<fmt:formatNumber type="number" value="${examWorksSubject.passScore}" maxFractionDigits="0"/></span>分</p>
            </div>
            <div class="clear"></div>
        </div>
        <div class="kwgl_main">
            <p class="title">年级三率综合堆积对比图</p>
            <div id="container6" style="min-height: 500px; width: 920px; margin: 0 auto"></div>
        </div>
        <div class="kwgl_main">
            <p class="title">年级各班级单科平均分排名</p>
            <div id="container7" style="height: 500px; width: 920px; margin: 0 auto"></div>
        </div>
        <div class="kwgl_main">
            <p class="title">年级单科名次分布图</p>
            <div id="container8" style="height: 500px; width: 920px; margin: 0 auto"></div>
        </div>
        <div class="kwgl_main">
            <p class="title">年级单科分数段图</p>
            <div id="container9" style="height: 500px; width: 920px; margin: 0 auto"></div>
        </div>
    </div>
</div>
</body>
<script>
    $(function(){
        $('.njfx_km a').click(function(){
            $(this).addClass('choose');
            $(this).siblings().removeClass('choose');
            var code = $(this).data("code");
            window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/analysis/grade?examWorksId=${examWorksId}&gradeId=${gradeId}&sub=3&subjectCode=" + code;
        });


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
                    ['不及格率', ${scoreMap.noPassCount}],
                    ['及格率', ${scoreMap.passCount}],
                    ['良好率', ${scoreMap.lowCount}],
                    ['优秀率', ${scoreMap.highCount}]
                ]
            }]
        });


        /*container6  */
        var class_num=${scoreMap.noPassCountList}.length;
        $('#container6').height(class_num*60);
        var color1= ['#ff6b89', '#f7ae00', '#07aaf5', '#1dc544'] ;
        var color2= ['#fca299', '#fcce00', '#88c9f8', '#4fd051'] ;
        Highcharts.getOptions().colors = Highcharts.map(color1, function (color) {
            var v=color;
            var j=0;
            var color_end='#fff';
            for(var i=0;i<color1.length;i++){
                if(color1[i]==v){
                    j=i;
                    color_end=color2[j]
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
        $('#container6').highcharts({
            chart: {
                type: 'bar',
                spacing : [50, 0 , 0, 0],
                marginRight:10
            },
            title: {
                text: null
            },
            xAxis: {
                categories: ${teamNameList},
                gridLineDashStyle:'Dash',
                labels:{
                    style:{
                        fontSize:16,
                        color:'#666666'
                    }
                },
            },
            yAxis: {
                min: 0,
                gridLineColor: '#999999',
                gridLineWidth: 1,
                gridLineDashStyle:'Dash',
                title: {
                    text: null
                },
                labels:{
                    style:{
                        fontSize:16,
                        color:'#666666'
                    }
                },
                max:100,
            },
            exporting:{
                enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
            },
            credits: {
                enabled:false
            },
            tooltip: {
                headerFormat: '<small style="font-size:20px">{point.key}</small><br>',
                pointFormat: '<span style="font-size:20px;color:{series.color}">{series.name}: <b style="font-size:20px">{point.y}%</b><br/>',
                useHTML: true,
            },
            legend: {
                reversed: true,
                verticalAlign: 'top',
                align:'right',
                symbolHeight: 16,
                symbolWidth: 16,
                symbolRadius: 0,
                itemStyle : {
                    'fontSize' : '18px',
                    'color':'#666666'
                }
            },
            plotOptions: {
                series: {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: true,          // 开启数据标签
                        //color:'gray',
                        align: 'left',
                        y:3,
                        style:{
                            fontSize:'16',
                            color:'#ffffff',
                            useHTML: true
                        },
                        formatter: function() {
                            if (this.y > 0)
                                return this.y ; //这里进行判断
                        }
                    },
                }
            },
            series: [   {
                name: '不及格率',
                data: ${scoreMap.noPassCountList}
            },{
                name: '及格率',
                data: ${scoreMap.passCountList}
            },{
                name: '良好率',
                data: ${scoreMap.lowCountList}
            },{
                name: '优秀率',
                data: ${scoreMap.highCountList}
            }]
        });


        /* container7 */
        var colors = ['#06aaf5'];
        Highcharts.getOptions().colors = Highcharts.map(colors, function (color) {
            return {
                radialGradient: { cx:0, cy: 2.2,r:5.3 },
                stops: [[0, color], [2, Highcharts.Color(color).brighten(14).get('rgb')] // darken
                ]
            };
        });
        $('#container7').highcharts({
            chart: {
                type: 'bar',
                marginRight:40,
            },
            title: {
                text: null
            },
            xAxis: {
                categories: ${teamNameList_r},
                title: {
                    text: null,
                },
                labels: {
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
                title: {
                    text: null,
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
                pointFormat: '<span style="font-size:20px;color:{series.color}" >{point.y}分<br/>',
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
                        }
                    },
                },
                series:{
                    borderRadius:7,
                    pointPadding:0.3
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
                data: ${teamAvgScoreList}
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
                categories: ${studentNameList},
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
                    value:${gradeAvgRank},
                    label: {
                        align: 'left',
                        style: {
                            fontStyle: 'normal',
                            color:'#fd9494',
                            fontSize:20
                        },
                        text: '平均分名次线：${gradeAvgRank}',
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
                    },
                    turboThreshold : 20000
                },
            },
            series: [{
                color: 'rgba(3, 169, 245,0.8)',
//                data: [{fs:80,y:20},{fs:100,y:11},{fs:220,y:4},{fs:260,y:3},{fs:380,y:1}]
                data: ${gradeRankList}
            }]
        });



        /*container9  */
        var length = 10;
        var all_score = ${examWorksSubject.fullScore};
        var current_score = 0;
        var class_score = ${gradeScoreList};
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
                    text: null,
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
    })
</script>
</html>