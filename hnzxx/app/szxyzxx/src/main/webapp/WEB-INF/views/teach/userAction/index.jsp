<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="apple-touch-fullscreen" content="YES">
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta http-equiv="Expires" content="-1">
    <meta http-equiv="pragram" content="no-cache">
    <%@ include file="/views/embedded/common.jsp"%>
    <title>首页</title>
    <meta name="viewport" content="width=1080, user-scalable=no, target-densitydpi=device-dpi">
    <link rel="stylesheet" href="${ctp}/res/css/statistics/parentlogin.css">
    <link rel="stylesheet" href="${ctp}/res/css/statistics/h5_select/iosSelect.css" />
    <script type="text/javascript" src="${ctp}/res/css/statistics/h5_select/iosSelect.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts-more.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/js/common/swiper/swiper-4.3.3.min.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/swiper/swiper-4.3.3.min.js"></script>
    <style type="text/css">
        body{background-color: #f1f1f1}
        .close{
            background-image: none !important;
            width: auto;
            opacity: 1;
        }
        #select_week_chzn{
            display: none;
        }
        .chart_div .bjncts{
             border: 3px solid rgb(255, 224, 178);
             border-radius: 6px;
             background-color: rgb(255, 252, 248);
             box-shadow: 0px 1px 6px 0px rgba(0, 0, 0, 0.1);
             padding: 10px 32px;
             color: #f28949;
             font-size: 17.78px;
            width: auto;
            margin:0;
         }
        .bjncts span{
            color: #f28949;
            font-size: 17.78px;
        }
    </style>
</head>
<body>
<div class="p_content">
    <div class="p_head">
        <img src="${ctp}/res/images/noPhoto.jpg" alt="头像">
        <div class="main">
            <p class="name">${studentName }</p>
            <p class="p1">学籍号：<span>${studentNumber }</span></p>
            <p class="p1">班级：<span>${teamName }</span></p>
        </div>
        <p class="qhzh"><a href="${pageContext.request.contextPath}/parent/login/index">切换账号</a></p>
    </div>
    <div class="day_select">
        <div class="day_1">
            <a href="javascript:void(0)" class="on">日</a>
            <a href="javascript:void(0)">周</a>
            <a href="javascript:void(0)">月</a>
            <a href="javascript:void(0)">本学期</a>
        </div>
        <div class="day_2">
            <div class="day_div">
                <input type="hidden" name="day_id" id="dayId" value="">
                <span id="showday">2018年6月22号</span>
            </div>
            <div class="day_div" style="display:none;">
                <input type="hidden" name="week_id" id="weekId" value="">
                <span id="showweek">第一周 208年2月14-2018年2月18日</span>
                <select id="select_week" style="display: none"></select>
            </div>
            <div class="day_div" style="display:none;">
                <input type="hidden" name="month_id" id="monthId" value="">
                <span id="showmonth">2018年5月</span>
            </div>
            <div class="day_div" style="display:none;">
                <input type="hidden" name="bank_id" id="bankId" value="">
                <span id="showBank">6月10号</span>
            </div>
        </div>
    </div>
    <div class="main_chart">
        <%--<div class="xk_select">--%>
            <%--<a href="javascript:void(0)">所有科目</a>--%>
        <%--</div>--%>
        <div class="swiper-container sc1">
            <div class="swiper-wrapper timu_type">
                <div class="swiper-slide all"><span class="ty on">所有科目</span></div>
                <div class="swiper-slide only_one"><span class="ty">语文</span></div>
            </div>
        </div>
        <div class="chart_div">
            <p>课堂表现波动<a href="javascript:void(0)" class="ts_btn" data-ts="用于了解孩子的课堂综合表现">?</a></p>
            <div id="container1" style="max-width:90%;height:640px;margin:0 auto"></div>
            <div class="no_message no_message1"></div>
        </div>
            <div class="chart_div">
                <p>课堂参与<a href="javascript:void(0)" class="ts_btn" data-ts="用于了解孩子的发言积极、上课专注度">?</a></p>
                <div id="container2" style="max-width:90%;height:640px;margin:0 auto"></div>
                <div class="no_message no_message2"></div>
            </div>
            <div class="chart_div">
                <p>随堂小测<a href="javascript:void(0)" class="ts_btn" data-ts="用于了解孩子的每堂课的知识掌握情况">?</a></p>
                <div id="container3" style="max-width:90%;height:640px;margin:0 auto"></div>
                <div class="no_message no_message3"></div>
            </div>
            <div class="chart_div">
                <p>随堂评价<a href="javascript:void(0)" class="ts_btn" data-ts="用于了解在教师、同学评价中的孩子">?</a></p>
                <div id="container4" style="max-width:90%;height:640px;margin:0 auto"></div>
                <div class="no_message no_message4"></div>
            </div>
    </div>
</div>
<div class="ts">
    <p class="p1">说明</p>
    <p class="p2"></p>
</div>
<script type="text/javascript">
    // swiper
    var mySwiper = new Swiper('.sc1',{
        slidesPerView : 'auto',
    })
    //时间戳转换成日期格式
    function timestampToTime(timestamp) {
        var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
        D = date.getDate() + ' ';
        return Y+M+D;
    }
    var start = "",end = "",info,km_id=[],time_prev,time_next,num,s6;
    var data1=[],data2=[],data3=[],month_list=[],date_all=[],fulldate_all=[];
    var chart_1,chart_2,chart_3,chart_4;
    var loader = new loadDialog(); //实例化对象
    var userId = "${userId}";
    var today=new Date().getFullYear()+'-'+(new Date().getMonth()+1)+'-'+ new Date().getDate();
    // 获得2个日期间所有日期
    function getDate(datestr){
        var temp = datestr.split("-");
        var date = new Date(temp[0],temp[1]-1,temp[2]);
        return date;
    }
    $(function(){
        // 点击出现提示
        $('.ts_btn').click(function(){
            $('.ts .p2').text($(this).attr('data-ts'));
            layer.open({
                type: 1,
                shadeClose:true,
                closeBtn:0,
                area: ['923px', '314px'],
                title: false, //不显示标题
                content: $('.ts')
            });
        })
        $.getJSON("/course/action/teamCurrent?&userId="+userId, function(result){
            start=timestampToTime(result[0].beginDate.time/1000);
            end=timestampToTime(result[0].finishDate.time/1000);

            var startTime = getDate(start);
            var endTime = getDate(end);
            var i=0;
            while((endTime.getTime()-startTime.getTime())>=0){
                var year = startTime.getFullYear();
                // var month = (startTime.getMonth()+1).toString().length==1?"0"+(startTime.getMonth()+1).toString():(startTime.getMonth()+1).toString();
                // var day = startTime.getDate().toString().length==1?"0"+startTime.getDate():startTime.getDate();
                var month = (startTime.getMonth()+1).toString();
                var day = startTime.getDate();
                fulldate_all[i]=year+"-"+month+"-"+day
                date_all[i]=month+"-"+day;
                data1[i]={'value':year+"-"+month+"-"+day};
                startTime.setDate(startTime.getDate()+1);
                i=i+1;
            }
            $('#showday').text(today)
            // 获得2个日期间所有周次
            $.getWeek({
                "selector":"#select_week",
                "begin" : start,
                "end" : end,
                "today" : "",
                "isMonday":true,//是否周一开始算一周开始，否则周日算第一天
                "isClear" : false,
                "isSelectCurrentWeek" : true,
                "clearedOptionTitle" : "请选择周次",
            });
            for(var i=1;i<$('#select_week option').length;i++){
                data2[i-1]={'value':$('#select_week option').eq(i).text()}
            }
            $('#showweek').text($('#select_week option:selected').text());
            // 获得2个日期之间所有月份
            function getMonthBetween(start,end){
                var result = [];
                var s = start.split("-");
                var e = end.split("-");
                var min = new Date();
                var max = new Date();
                min.setFullYear(s[0],s[1]);
                max.setFullYear(e[0],e[1]);

                var curr = min;
                while(curr <= max){
                    var month = curr.getMonth();
                    if(month==0){
                        month_list.push(curr.getFullYear()+"-"+12);
                        data3.push({'value':curr.getFullYear()+"-"+12});
                    }else{
                        month_list.push(curr.getFullYear()+"-"+(month));
                        data3.push({'value':curr.getFullYear()+"-"+(month)});
                    }
                    curr.setMonth(month+1);
                }
                return result;
            }
            getMonthBetween(start,end);
            $('#showmonth').text(new Date().getFullYear()+'-'+(new Date().getMonth()+1))
            $('#showBank').text(start+'到'+end);
            // 六天前的日期
            var day6 =  new Date(today.replace(/-/g,'/'));
            day6.setTime(day6.getTime()-24*60*60*1000*6);
            s6=day6.getFullYear()+"-" + (day6.getMonth()+1) + "-" + day6.getDate();
             time_prev = day6.getFullYear()+"-" + (day6.getMonth()+1) + "-" + day6.getDate();
             time_next=today;
            // 拿科目
            $.getJSON("/course/action/subject?kind=1&userId="+userId+"&begin="+time_prev+"&end="+time_next+"",function(result){
                $('.timu_type .only_one').remove();
                for (var key in result[0]){
                    km_id.push(result[0][key])
                    $('<div class="swiper-slide only_one"><span class="ty" data-id='+result[0][key]+'>'+key+'</span></div>').appendTo($('.timu_type'))
                }
                // 获取课堂表现波动
                $.getJSON("/course/action/performance?kind=1&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode="+km_id+"",function(result){
                    if(result[0].error=='参数或数据异常'){
                        $('.no_message1').show();
                        $('#container1').hide();
                    }else{
                        var series=chart_1.series;
                        while(series.length > 0) {
                            series[0].remove(false);
                        }
                        var ts_list=[];
                        var startTime = getDate(time_prev);
                        var endTime = getDate(today);
                        var i=0;
                        while((endTime.getTime()-startTime.getTime())>=0){
                            var year = startTime.getFullYear();
                            var month = (startTime.getMonth()+1).toString().length==1?"0"+(startTime.getMonth()+1).toString():(startTime.getMonth()+1).toString();
                            var day = startTime.getDate().toString().length==1?"0"+startTime.getDate():startTime.getDate();
                            // date_all[i]=year+"-"+month+"-"+day;
                            // ts_list.push(year+"-"+month+"-"+day);
                            ts_list.push(month+"-"+day);
                            startTime.setDate(startTime.getDate()+1);
                            i=i+1;
                        }
                        for (var x in fulldate_all) {
                            if(fulldate_all[x]==time_next){
                                num=parseInt(x)+1;
                                if(num>7){
                                    ts_list=date_all.slice(parseInt(x)-6, parseInt(x)+1);
                                }else{
                                    ts_list=date_all.slice(0, parseInt(x)+1);
                                }
                            }
                        }
                        for (var key in result[0]){
                            if(num<7){
                                chart_1.addSeries({name:key,data:result[0][key].slice(7-num)}, false);
                            }else{
                                chart_1.addSeries({name:key,data:result[0][key]}, false);
                            }

                        }
                        chart_1.xAxis[0].setCategories(ts_list);
                        chart_1.redraw();
                    }
                })
            });

            // 获取课堂参与
            $.getJSON("/course/action/participation?kind=1&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode=",function(result){
                var series=chart_2.series;
                while(series.length > 0) {
                    series[0].remove(false);
                }
                for (var key in result[0]){
                    chart_2.addSeries({name:key,data:[result[0][key]]}, false);
                }
                chart_2.redraw();
            })
            // 获取随堂小测
            $.getJSON("/course/action/courseTest?kind=1&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode=",function(result){
                chart3=[];
                for (var key in result[0]){
                    chart3.push(result[0][key])
                }
                if(chart3.length>3) {
                    info = "<p class='bjncts'><span>" + chart3[0] + "</span> | <span> 正确率：" + chart3[1] * 100 + "</span> | <span>排名:" + chart3[2] + "</span></p>"
                    var series = chart_3.series;
                    var title = {
                        text: info,
                        margin: 50,
                        useHTML: true
                    };
                    chart_3.setTitle(title);
                    chart_3.yAxis[0].removePlotLine('plot-line-1');
                    // var plotLines=[];
                    while (series.length > 0) {
                        series[0].remove(false);
                    }
                    var test = [];
                    $.each(chart3[3], function (index, value) {
                        value = value * 100;
                        if (index == chart3[2] - 1) {
                            test.push({y: value, color: "#ffe0b2", marker: {radius: 8}});
                        } else {
                            test.push({y: value});
                        }
                    });
                    chart_3.addSeries({data: test, pointInterval: 1}, false);
                    chart_3.yAxis[0].addPlotLine({
                        color: '#ff6a88',
                        dashStyle: 'Solid',
                        width: 3,
                        value: chart3[4],
                        label: {
                            align: 'right',
                            style: {
                                fontStyle: 'normal',
                                color: '#fd9494',
                                fontSize: 20
                            },
                            text: '班级正确率:' + chart3[4] + '%',
                            x: -10,
                            y: 25
                        },
                        zIndex: 3,
                        id: 'plot-line-1'
                    });
                    chart_3.redraw();
                }else{
                    $('.no_message3').show();
                    $('#container3').hide();
                }
            });
            // 获取随堂评价
            $.getJSON("/course/action/appraise?kind=1&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode=",function(result){
                var series=chart_4.series;
                while(series.length > 0) {
                    series[0].remove(false);
                }
                for (var key in result[0]){
                    chart_4.addSeries({name:key,data:result[0][key]}, false);
                }
                chart_4.redraw();
            })
        });
        $('.day_select .day_1 a').click(function(){
            $('.day_select .day_1 a').removeClass('on');
            $(this).addClass('on');
            var i=$(this).index();
            $('.day_select .day_2 .day_div').hide();
            $('.day_select .day_2 .day_div').eq(i).show();
            var kind_j=i+1;
            var km_id='';
            if(i==0){
                $('#showday').text(today);
                time_prev=s6;
                time_next=today;
            }else if(i==1){
                $('#showweek').text($('#select_week option:selected').text());
                time_prev=$('#select_week option:selected').attr('data-prev');
                time_next=$('#select_week option:selected').attr('data-next');
            }else if(i==2){
                $('#showmonth').text(new Date().getFullYear()+'-'+(new Date().getMonth()+1))
                time_prev='';
                time_next=new Date().getFullYear()+'-'+(new Date().getMonth()+1);
            }else if(i==3){
                kind_j=3;
                time_prev='';
                time_next=new Date().getFullYear()+'-'+(new Date().getMonth()+1);
            }


            $.getJSON("/course/action/subject?kind="+kind_j+"&userId="+userId+"&begin="+time_prev+"&end="+time_next+"",function(result){
                km_id1=[];
                $('.timu_type .only_one').remove();
                for (var key in result[0]){
                    km_id1.push(result[0][key])
                    $('<div class="swiper-slide only_one"><span class="ty" data-id='+result[0][key]+'>'+key+'</span></div>').appendTo($('.timu_type'))
                }
                // 获取课堂表现波动
                $.getJSON("/course/action/performance?kind="+kind_j+"&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode="+km_id1+"",function(result){
                    if(result[0].error=='参数或数据异常'){
                        $('.no_message1').show();
                        $('#container1').hide();
                    }else{
                        $('.no_message1').hide();
                        $('#container1').show();
                        var series=chart_1.series;
                        while(series.length > 0) {
                            series[0].remove(false);
                        }

                        if(kind_j==1){
                            var ts_list=[];
                            var startTime = getDate(time_prev);
                            var endTime = getDate(time_next);
                            var i=0;
                            while((endTime.getTime()-startTime.getTime())>=0){
                                var year = startTime.getFullYear();
                                var month = (startTime.getMonth()+1).toString().length==1?"0"+(startTime.getMonth()+1).toString():(startTime.getMonth()+1).toString();
                                var day = startTime.getDate().toString().length==1?"0"+startTime.getDate():startTime.getDate();
                                // date_all[i]=year+"-"+month+"-"+day;
                                // ts_list.push(year+"-"+month+"-"+day);
                                ts_list.push(month+"-"+day);
                                startTime.setDate(startTime.getDate()+1);
                                i=i+1;
                            }
                            for (var x in fulldate_all) {
                                if(fulldate_all[x]==time_next){
                                    num=parseInt(x)+1;
                                    console.log(num)
                                    if(num>7){
                                        ts_list=date_all.slice(parseInt(x)-6, parseInt(x)+1);
                                    }else{
                                        ts_list=date_all.slice(0, parseInt(x)+1);
                                    }
                                }
                            }
                        }else if(kind_j==2){
                            num=$('#select_week option:selected').attr('data-num')
                            var ts_list=[]
                            for(var i=6;i>=0;i--){
                                var k=num-i;
                                if(k>0){
                                    ts_list.push("第"+k+"周");
                                }
                            }
                        }else if(kind_j==3){
                            var ts_list=[];
                            for (var x in month_list) {
                                if(month_list[x]==time_next){
                                    num=parseInt(x)+1;
                                    if(num>7){
                                        ts_list=month_list.slice(parseInt(x)-6, parseInt(x)+1);
                                    }else{
                                        ts_list=month_list.slice(0, parseInt(x)+1);
                                    }
                                }
                            }
                        }
                        for (var key in result[0]){
                            if(num<7){
                                chart_1.addSeries({name:key,data:result[0][key].slice(7-num)}, false);
                            }else{
                                chart_1.addSeries({name:key,data:result[0][key]}, false);
                            }

                        }
                        chart_1.xAxis[0].setCategories(ts_list);
                        chart_1.redraw();
                    }
                });
            });
            // 获取课堂参与
            $.getJSON("/course/action/participation?kind="+kind_j+"&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode="+km_id+"",function(result){
                var series=chart_2.series;
                while(series.length > 0) {
                    series[0].remove(false);
                }
                for (var key in result[0]){
                    chart_2.addSeries({name:key,data:[result[0][key]]}, false);
                }
                chart_2.redraw();
            })
            // 获取随堂小测
            $.getJSON("/course/action/courseTest?kind="+kind_j+"&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode="+km_id+"",function(result){
                chart3=[];
                for (var key in result[0]){
                    chart3.push(result[0][key])
                }
                if(chart3.length>3){
                    $('.no_message3').hide();
                    $('#container3').show();
                    info="<p class='bjncts'><span>"+chart3[0]+"</span> | <span> 正确率："+chart3[1]*100+"%</span> | <span>排名:"+chart3[2]+"</span></p>"
                    var series=chart_3.series;
                    var title={
                        text:info,
                        margin: 50,
                        useHTML:true
                    };
                    chart_3.setTitle(title);
                    chart_3.yAxis[0].removePlotLine('plot-line-1');
                    chart_3.yAxis[0].addPlotLine({
                        color: '#ff6a88',
                        dashStyle: 'Solid',
                        width: 3,
                        value:chart3[4],
                        label: {
                            align: 'right',
                            style: {
                                fontStyle: 'normal',
                                color:'#fd9494',
                                fontSize:20
                            },
                            text: '班级正确率:'+chart3[4]+'%',
                            x: -10,
                            y:25
                        },
                        zIndex: 3,
                        id: 'plot-line-1'
                    });
                    while(series.length > 0) {
                        series[0].remove(false);
                    }
                    var test=[];
                    $.each(chart3[3],function(index,value){
                        value=value*100;
                        if(index==chart3[2]-1){
                            test.push({y:value,color : "#ffe0b2",marker:{radius:8}});
                        }else{
                            test.push({y:value});
                        }
                    });
                    chart_3.addSeries({data:test,pointInterval: 1}, false);
                    chart_3.redraw();
                }else{
                    $('.no_message3').show();
                    $('#container3').hide();
                }
            });
            // 获取随堂评价
            $.getJSON("/course/action/appraise?kind="+kind_j+"&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode="+km_id+"",function(result){
                var series=chart_4.series;
                while(series.length > 0) {
                    series[0].remove(false);
                }
                for (var key in result[0]){
                    chart_4.addSeries({name:key,data:result[0][key]}, false);
                }
                chart_4.redraw();
            })
        })
    });
    var showdayDom = document.querySelector('#showday');
    var dayIdDom = document.querySelector('#dayId');
    showdayDom.addEventListener('click', function () {
        var dayId = showdayDom.dataset['id'];
        var dayName = showdayDom.dataset['value'];

        var daySelect = new IosSelect(1,
            [data1],
            {
                container: '.container',
                title: '',
                itemHeight: 90,
                itemShowCount: 4,
                oneLevelId: dayId,
                callback: function (selectOneObj) {
                    dayIdDom.value = selectOneObj.id;
                    showdayDom.innerHTML = selectOneObj.value;
                    showdayDom.dataset['id'] = selectOneObj.id;
                    showdayDom.dataset['value'] = selectOneObj.value;
                    var day1 =  new Date(selectOneObj.value.replace(/-/g,'/'));
                    day1.setTime(day1.getTime()-24*60*60*1000*6);
                    time_prev = day1.getFullYear()+"-" + (day1.getMonth()+1) + "-" + day1.getDate();
                    time_next=selectOneObj.value;
                    // 拿科目
                    $.getJSON("/course/action/subject?kind=1&userId="+userId+"&begin="+time_prev+"&end="+time_next+"",function(result){
                        km_id=[];
                        $('.timu_type .only_one').remove();
                        for (var key in result[0]){
                            km_id.push(result[0][key])
                            $('<div class="swiper-slide only_one"><span class="ty" data-id='+result[0][key]+'>'+key+'</span></div>').appendTo($('.timu_type'))
                        }
                        // 获取课堂表现波动
                        $.getJSON("/course/action/performance?kind=1&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode="+km_id+"",function(result){
                            if(result[0].error=='参数或数据异常'){
                                $('.no_message1').show();
                                $('#container1').hide();
                            }else{
                                $('.no_message1').hide();
                                $('#container1').show();
                                // var series=chart_1.series;
                                // while(series.length > 0) {
                                //     series[0].remove(false);
                                // }
                                // for (var key in result[0]){
                                //     chart_1.addSeries({name:key,data:result[0][key]}, false);
                                // }
                                // var ts_list=[]
                                // var startTime = getDate(s1);
                                // var endTime = getDate(selectOneObj.value);
                                // var i=0;
                                // while((endTime.getTime()-startTime.getTime())>=0){
                                //     var year = startTime.getFullYear();
                                //     var month = (startTime.getMonth()+1).toString().length==1?"0"+(startTime.getMonth()+1).toString():(startTime.getMonth()+1).toString();
                                //     var day = startTime.getDate().toString().length==1?"0"+startTime.getDate():startTime.getDate();
                                //     // date_all[i]=year+"-"+month+"-"+day;
                                //     ts_list.push(year+"-"+month+"-"+day);
                                //     startTime.setDate(startTime.getDate()+1);
                                //     i=i+1;
                                // }
                                // chart_1.xAxis[0].setCategories(ts_list);
                                // chart_1.redraw();
                                var series=chart_1.series;
                                while(series.length > 0) {
                                    series[0].remove(false);
                                }
                                var ts_list=[];
                                var startTime = getDate(time_prev);
                                var endTime = getDate(today);
                                var i=0;
                                while((endTime.getTime()-startTime.getTime())>=0){
                                    var year = startTime.getFullYear();
                                    var month = (startTime.getMonth()+1).toString().length==1?"0"+(startTime.getMonth()+1).toString():(startTime.getMonth()+1).toString();
                                    var day = startTime.getDate().toString().length==1?"0"+startTime.getDate():startTime.getDate();
                                    // date_all[i]=year+"-"+month+"-"+day;
                                    // ts_list.push(year+"-"+month+"-"+day);
                                    ts_list.push(month+"-"+day);
                                    startTime.setDate(startTime.getDate()+1);
                                    i=i+1;
                                }
                                for (var x in fulldate_all) {
                                    if(fulldate_all[x]==time_next){
                                        num=parseInt(x)+1;
                                        console.log(num)
                                        if(num>7){
                                            ts_list=date_all.slice(parseInt(x)-6, parseInt(x)+1);
                                        }else{
                                            ts_list=date_all.slice(0, parseInt(x)+1);
                                        }
                                    }
                                }
                                for (var key in result[0]){
                                    if(num<7){
                                        chart_1.addSeries({name:key,data:result[0][key].slice(7-num)}, false);
                                    }else{
                                        chart_1.addSeries({name:key,data:result[0][key]}, false);
                                    }

                                }
                                chart_1.xAxis[0].setCategories(ts_list);
                                 chart_1.redraw();
                            }
                        })
                    })

                    // 获取课堂参与
                    $.getJSON("/course/action/participation?kind=1&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode=",function(result){
                        var series=chart_2.series;
                        while(series.length > 0) {
                            series[0].remove(false);
                        }
                        for (var key in result[0]){
                            chart_2.addSeries({name:key,data:[result[0][key]]}, false);
                        }
                        chart_2.redraw();
                    })
                    // 获取随堂小测
                    $.getJSON("/course/action/courseTest?kind=1&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode=",function(result){
                        chart3=[];
                        for (var key in result[0]){
                            chart3.push(result[0][key])
                        }
                        if(chart3.length>3){
                            $('.no_message3').hide();
                            $('#container3').show();
                        info="<p class='bjncts'><span>"+chart3[0]+"</span> | <span> 正确率："+chart3[1]*100+"%</span> | <span>排名:"+chart3[2]+"</span></p>"
                        var series=chart_3.series;
                        var title={
                                text:info,
                                margin: 50,
                                useHTML:true
                        };
                        chart_3.setTitle(title);
                        chart_3.yAxis[0].removePlotLine('plot-line-1');
                            chart_3.yAxis[0].addPlotLine({
                                color: '#ff6a88',
                                dashStyle: 'Solid',
                                width: 3,
                                value:chart3[4],
                                label: {
                                    align: 'right',
                                    style: {
                                        fontStyle: 'normal',
                                        color:'#fd9494',
                                        fontSize:20
                                    },
                                    text: '班级正确率:'+chart3[4]+'%',
                                    x: -10,
                                    y:25
                                },
                                zIndex: 3,
                                id: 'plot-line-1'
                            });
                        while(series.length > 0) {
                            series[0].remove(false);
                        }
                        var test=[];
                        $.each(chart3[3],function(index,value){
                            value=value*100;
                            if(index==chart3[2]-1){
                                test.push({y:value,color : "#ffe0b2",marker:{radius:8}});
                            }else{
                                test.push({y:value});
                            }
                        });
                        chart_3.addSeries({data:test,pointInterval: 1}, false);
                        chart_3.redraw();
                        }else{
                            $('.no_message3').show();
                            $('#container3').hide();
                        }
                    });
                    // 获取随堂评价
                    $.getJSON("/course/action/appraise?kind=1&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode=",function(result){
                        var series=chart_4.series;
                        while(series.length > 0) {
                            series[0].remove(false);
                        }
                        for (var key in result[0]){
                            chart_4.addSeries({name:key,data:result[0][key]}, false);
                        }
                        chart_4.redraw();
                    })
                }
            });
    });
    /*周次控件*/
    var showweekDom = document.querySelector('#showweek');
    var weekIdDom = document.querySelector('#weekId');
    showweekDom.addEventListener('click', function () {
        var weekId = showweekDom.dataset['id'];
        var weekName = showweekDom.dataset['value'];
        var weekSelect = new IosSelect(1,
            [data2],
            {
                container: '.container',
                title: '',
                itemHeight: 90,
                itemShowCount: 4,
                oneLevelId: weekId,
                callback: function (selectOneObj) {
                    weekIdDom.value = selectOneObj.id;
                    showweekDom.innerHTML = selectOneObj.value;
                    showweekDom.dataset['id'] = selectOneObj.id;
                    showweekDom.dataset['value'] = selectOneObj.value;
                    num=selectOneObj.atindex;
                    time_next=$('#select_week option').eq(num).attr('data-next')
                    time_prev=$('#select_week option').eq(num).attr('data-prev');
                    // 拿科目
                    $.getJSON("/course/action/subject?kind=2&userId="+userId+"&begin="+time_prev+"&end="+time_next+"",function(result){
                        km_id=[]
                        $('.timu_type .only_one').remove();
                        for (var key in result[0]){
                            km_id.push(result[0][key])
                            $('<div class="swiper-slide only_one"><span class="ty" data-id='+result[0][key]+'>'+key+'</span></div>').appendTo($('.timu_type'))
                        }
                        // 获取课堂表现波动
                        $.getJSON("/course/action/performance?kind=2&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode="+km_id+"",function(result){
                            if(result[0].error=='参数或数据异常'){
                                $('.no_message1').show();
                                $('#container1').hide();
                            }else{
                                $('.no_message1').hide();
                                $('#container1').show();
                                var series=chart_1.series;
                                while(series.length > 0) {
                                    series[0].remove(false);
                                }
                                for (var key in result[0]){
                                    if(num<7){
                                        chart_1.addSeries({name:key,data:result[0][key].slice(7-num)}, false);
                                    }else{
                                        chart_1.addSeries({name:key,data:result[0][key]}, false);
                                    }

                                }
                                var ts_list=[];
                                // for (var x in month_list) {
                                //     if(month_list[x]==time_next){
                                //         num=parseInt(x)+1;
                                //         if(num>7){
                                //             ts_list=month_list.slice(parseInt(x)-6, parseInt(x)+1);
                                //         }else{
                                //             ts_list=month_list.slice(0, parseInt(x)+1);
                                //         }
                                //     }
                                // }
                                for(var i=6;i>=0;i--){
                                    var k=num-i;
                                    if(k>0){
                                        ts_list.push("第"+k+"周");
                                    }
                                }
                                chart_1.xAxis[0].setCategories(ts_list);
                                chart_1.redraw();
                            }
                        })
                    })

                    // 获取课堂参与
                    $.getJSON("/course/action/participation?kind=2&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode=",function(result){
                        var series=chart_2.series;
                        while(series.length > 0) {
                            series[0].remove(false);
                        }
                        for (var key in result[0]){
                            chart_2.addSeries({name:key,data:[result[0][key]]}, false);
                        }
                        chart_2.redraw();
                    })
                    // 获取随堂小测
                    $.getJSON("/course/action/courseTest?kind=2&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode=",function(result){
                        chart3=[];
                        for (var key in result[0]){
                            chart3.push(result[0][key])
                        }
                        if(chart3.length>3){
                            $('.no_message3').hide();
                            $('#container3').show();
                            info="<p class='bjncts'><span>"+chart3[0]+"</span> | <span> 正确率："+chart3[1]*100+"%</span> | <span>排名:"+chart3[2]+"</span></p>"
                            var series=chart_3.series;
                            var title={
                                text:info,
                                margin: 50,
                                useHTML:true
                            };
                            chart_3.setTitle(title);
                            chart_3.yAxis[0].removePlotLine('plot-line-1');
                            chart_3.yAxis[0].addPlotLine({
                                color: '#ff6a88',
                                dashStyle: 'Solid',
                                width: 3,
                                value:chart3[4],
                                label: {
                                    align: 'right',
                                    style: {
                                        fontStyle: 'normal',
                                        color:'#fd9494',
                                        fontSize:20
                                    },
                                    text: '班级正确率:'+chart3[4]+'%',
                                    x: -10,
                                    y:25
                                },
                                zIndex: 3,
                                id: 'plot-line-1'
                            });
                            while(series.length > 0) {
                                series[0].remove(false);
                            }
                            var test=[];
                            $.each(chart3[3],function(index,value){
                                value=value*100;
                                if(index==chart3[2]-1){
                                    test.push({y:value,color : "#ffe0b2",marker:{radius:8}});
                                }else{
                                    test.push({y:value});
                                }
                            });
                            chart_3.addSeries({data:test,pointInterval: 1}, false);
                            chart_3.redraw();
                        }else{
                            $('.no_message3').show();
                            $('#container3').hide();
                        }
                    });
                    // 获取随堂评价
                    $.getJSON("/course/action/appraise?kind=2&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode=",function(result){
                        var series=chart_4.series;
                        while(series.length > 0) {
                            series[0].remove(false);
                        }
                        for (var key in result[0]){
                            chart_4.addSeries({name:key,data:result[0][key]}, false);
                        }
                        chart_4.redraw();
                    })
                }
            });
    });
    /*月份控件*/
    var showmonthDom = document.querySelector('#showmonth');
    var monthIdDom = document.querySelector('#monthId');
    showmonthDom.addEventListener('click', function () {
        var monthId = showmonthDom.dataset['id'];
        var monthName = showmonthDom.dataset['value'];

        var monthSelect = new IosSelect(1,
            [data3],
            {
                container: '.container',
                title: '',
                itemHeight: 90,
                itemShowCount: 4,
                oneLevelId: monthId,
                callback: function (selectOneObj) {
                    monthIdDom.value = selectOneObj.id;
                    showmonthDom.innerHTML = selectOneObj.value;
                    showmonthDom.dataset['id'] = selectOneObj.id;
                    showmonthDom.dataset['value'] = selectOneObj.value;
                    num=selectOneObj.atindex;
                    // selectOneObj.value=$('#select_week option').eq(num).attr('data-next')
                     time_prev='';
                     time_next=selectOneObj.value;
                    // 拿科目
                    $.getJSON("/course/action/subject?kind=3&userId="+userId+"&begin="+time_prev+"&end="+time_next+"",function(result){
                        km_id=[]
                        $('.timu_type .only_one').remove();
                        for (var key in result[0]){
                            km_id.push(result[0][key])
                            $('<div class="swiper-slide only_one"><span class="ty" data-id='+result[0][key]+'>'+key+'</span></div>').appendTo($('.timu_type'))
                        }
                        // 获取课堂表现波动
                        $.getJSON("/course/action/performance?kind=3&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode="+km_id+"",function(result){
                            if(result[0].error=='参数或数据异常'){
                                $('.no_message1').show();
                                $('#container1').hide();
                            }else{
                                $('.no_message1').hide();
                                $('#container1').show();
                                var ts_list=[];
                                for (var x in month_list) {
                                    if(month_list[x]==time_next){
                                        num=parseInt(x)+1;
                                        if(num>7){
                                            ts_list=month_list.slice(parseInt(x)-6, parseInt(x)+1);
                                        }else{
                                            ts_list=month_list.slice(0, parseInt(x)+1);
                                        }
                                    }
                                }
                                var series=chart_1.series;
                                while(series.length > 0) {
                                    series[0].remove(false);
                                }
                                for (var key in result[0]){
                                    if(num<7){
                                        chart_1.addSeries({name:key,data:result[0][key].slice(7-num)}, false);
                                    }else{
                                        chart_1.addSeries({name:key,data:result[0][key]}, false);
                                    }

                                }
                                chart_1.xAxis[0].setCategories(ts_list);
                                chart_1.redraw();
                            }
                        })
                    })

                    // 获取课堂参与
                    $.getJSON("/course/action/participation?kind=3&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode=",function(result){
                        var series=chart_2.series;
                        while(series.length > 0) {
                            series[0].remove(false);
                        }
                        for (var key in result[0]){
                            chart_2.addSeries({name:key,data:[result[0][key]]}, false);
                        }
                        chart_2.redraw();
                    })
                    // 获取随堂小测
                    $.getJSON("/course/action/courseTest?kind=3&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode=",function(result){
                        chart3=[];
                        for (var key in result[0]){
                            chart3.push(result[0][key])
                        }
                        if(chart3.length>3){
                            $('.no_message3').hide();
                            $('#container3').show();
                            info="<p class='bjncts'><span>"+chart3[0]+"</span> | <span> 正确率："+chart3[1]*100+"%</span> | <span>排名:"+chart3[2]+"</span></p>"
                            var series=chart_3.series;
                            var title={
                                text:info,
                                margin: 50,
                                useHTML:true
                            };
                            chart_3.setTitle(title);
                            chart_3.yAxis[0].removePlotLine('plot-line-1');
                            chart_3.yAxis[0].addPlotLine({
                                color: '#ff6a88',
                                dashStyle: 'Solid',
                                width: 3,
                                value:chart3[4],
                                label: {
                                    align: 'right',
                                    style: {
                                        fontStyle: 'normal',
                                        color:'#fd9494',
                                        fontSize:20
                                    },
                                    text: '班级正确率:'+chart3[4]+'%',
                                    x: -10,
                                    y:25
                                },
                                zIndex: 3,
                                id: 'plot-line-1'
                            });
                            while(series.length > 0) {
                                series[0].remove(false);
                            }
                            var test=[];
                            $.each(chart3[3],function(index,value){
                                value=value*100;
                                if(index==chart3[2]-1){
                                    test.push({y:value,color : "#ffe0b2",marker:{radius:8}});
                                }else{
                                    test.push({y:value});
                                }
                            });
                            chart_3.addSeries({data:test,pointInterval: 1}, false);
                            chart_3.redraw();
                        }else{
                            $('.no_message3').show();
                            $('#container3').hide();
                        }
                    });
                    // 获取随堂评价
                    $.getJSON("/course/action/appraise?kind=3&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode=",function(result){
                        var series=chart_4.series;
                        while(series.length > 0) {
                            series[0].remove(false);
                        }
                        for (var key in result[0]){
                            chart_4.addSeries({name:key,data:result[0][key]}, false);
                        }
                        chart_4.redraw();
                    })
                }
            });
    });
    $(function(){
        // 第一个统计图
        chart_1 = Highcharts.chart('container1', {
            chart: {
                type: 'line',
                zoomType: 'x',
                panning: true,
                panKey: 'shift',
            },
            title: {
                text: null
            },
            xAxis: {
                gridLineColor: '#d6d6d6',
                gridLineWidth: 1,
                gridLineDashStyle: 'Dot',
                categories: ['6-11', '6-12','6-13','6-14', '6-15', '6-16','6-17'],
                labels: {
                    y:50,
                    style:{
                        color:'#999999',
                        fontSize:'25',
                        // writingMode : 'tb-rl'//文字竖排样式,
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
                    y:10,
                    style:{
                        fontSize:'25px',
                    }
                },
                min:0,
            },
            legend: {
                verticalAlign: 'bottom',
                align:'center',
                symbolHeight: 18,
                symbolWidth: 18,
                symbolRadius: 12,
                itemStyle : {
                    'fontSize' : '24px',
                    'color':'#666666'
                }
            },
            tooltip: {
                crosshairs: true,
                shared: true,
                useHTML: true,
                style: {
                    padding: 10,
                    fontSize:24
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: false,          // 开启数据标签
                        style:{
                            fontSize:'16',
                            color:'#666666'
                        },
                        allowOverlap: true,
                    },
                    //enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                }
            },
            exporting:{
                enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
            },
            credits: {
                enabled:false
            },
            colors: ['#90caf8', '#fca599', '#3cd3ad', '#f7a35c', '#8085e9',
                '#f15c80', '#e4d354', '#2b908f', '#f45b5b', '#91e8e1'],
            series: [{
                name: '语文',
                data: [70, 69, 95, 85, 73, 66, 84],
            },
                {
                name: '数学',
                data: [39, 42, 57, 85, 19, 52, 70]
            },{
                name: '英语',
                data: [8.8, 12.3, 14.2, 13.4, 10.3, 9.8, 10.5]
            }]
        })
        //第二个统计图
        chart_2 = Highcharts.chart('container2', {
            chart: {
                type: 'bar',
                zoomType: 'y',
                panning: true,
                panKey: 'shift',
                marginRight: 45
            },
            title: {
                text: null
            },
            xAxis: {
                title: {
                    text: '',
                    rotation:0,
                    x:-10,
                    style:{
                        fontSize:25,
                        color:'#999999'
                    }
                },
                labels: {
                    enabled: false
                },
                tickWidth:0,
                gridLineDashStyle:'Dash',
                lineWidth: 0
            },
            yAxis: {
                title: {
                    text: null
                },
                min:0,
                max:100,
                gridLineDashStyle:'Dash',
                gridLineColor: '#d6d6d6',
                labels: {
                    style:{
                        fontSize:'25',
                    }
                }
            },
            legend: {
                verticalAlign: 'bottom',
                align:'center',
                symbolHeight: 18,
                symbolWidth: 18,
                symbolRadius: 12,
                itemStyle : {
                    'fontSize' : '24px',
                    'color':'#666666'
                }
            },
            tooltip: {
                enabled:false
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true,          // 开启数据标签
                        style:{
                            fontSize:'20',
                            color:'#666666'
                        },
                        allowOverlap: true,
                        formatter:function(){
                            return this.y + "%";  //返回百分比和个数
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
            colors: ['#90caf8', '#fca599', '#3cd3ad'],
            series: [{
                name: '完成练习1',
                data: [2]
            }, {
                name: '成果展示',
                data: [50]
            },{
                name: '抢答',
                data: [88]
            }]
        });
        // var chart_2=$('#container2').highcharts({
        //
        // });
        //第三个统计图
        info = "<p class='bjncts'><span>张三</span> | <span> 正确率：98%</span> | <span>排名:2</span></p>"
        chart_3 = Highcharts.chart('container3', {
            chart: {
                type: 'scatter',
                marginLeft:85,
                marginRight:55,
            },
            title: {
                text:info,
                margin: 50,
                useHTML:true
            },
            legend: {
                enabled: false
            },
            plotOptions: {
                scatter: {
                    pointStart: 1,
                }
            },
            xAxis: {
                // min:0,
                allowDecimals: false,
                title: {
                    "text":"排名",
                    align: 'high',
                    x:50,
                    y:-20,
                    rotation:0,
                    style:{
                        fontSize:25,
                        color: '#999999',
                    }
                },
                labels: {
                    y:5,
                    style: {
                        color: '#999999',
                        fontSize:25
                    }
                },
                startOnTick: true,
                endOnTick: true,
                showLastLabel: true
            },
            yAxis: {
                min:0,
                max:100,
                showFirstLabel: false,
                title: {
                    "text": "正确率(%)",
                    align: 'high',
                    rotation:0,
                    x:130,
                    y:-30,
                    style:{
                        fontSize:25,
                        color: '#999999',
                    }
                },
                labels: {
                    y:5,
                    style: {
                        color: '#999999',
                        fontSize:25
                    }
                },
                reversed: false,
                plotLines: [{
                    color: '#ff6a88',
                    dashStyle: 'Solid',
                    width: 3,
                    value:25,
                    label: {
                        align: 'right',
                        style: {
                            fontStyle: 'normal',
                            color:'#fd9494',
                            fontSize:25
                        },
                        text: '班级正确率:25%',
                        x: -10,
                        y:25
                    },
                    zIndex: 3,
                    id: 'plot-line-1'
                }]
            },
            exporting:{
                enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
            },
            credits: {
                enabled:false
            },
            tooltip: {
                enabled:false,
            },
            color: 'rgba(3, 169, 245,0.8)',
            series: [{
                data: [{y:100,color : "#ffe0b2",marker:{radius:8}},
                    {y:80},
                    {y:60},
                    {y:30},
                    {y:10},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0},
                    {y:0}

                ],
            }]
        })
        //第四个统计图
        chart_4 = Highcharts.chart('container4', {
            chart: {
                type: 'column',
                zoomType: 'y',
                panning: true,
                panKey: 'shift'
            },
            title: {
                text: null
            },
            xAxis: {
                categories: [
                    '同学互评',
                    '老师互评'
                ],
                labels:{
                    style:{
                        fontSize:25,
                        color:'#999999'
                    },
                    y:30
                },
                crosshair: true
            },
            yAxis: {
                labels:{
                    style:{
                        fontSize:25,
                        color:'#999999'
                    },
                    y:8
                },
                min: 0,
                title: {
                    enabled:false
                },
            },
            exporting:{
                enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
            },
            credits: {
                enabled:false
            },
            tooltip: {
                crosshairs: true,
                shared: true,
                useHTML: true,
                style: {
                    padding: 10,
                    fontSize:16
                }
            },
            legend: {
                verticalAlign: 'bottom',
                align:'center',
                symbolHeight: 18,
                symbolWidth: 18,
                symbolRadius: 12,
                itemStyle : {
                    'fontSize' : '24px',
                    'color':'#666666'
                }
            },
            plotOptions: {
                series:{
                    borderRadius:5,
                    pointPadding:0.3
                }
            },
            colors: ['#08aaf5', 'rgb(255,106,136)', 'rgb(60,211,173)'],
            series: [{
                name: '个人得分',
                data: [49.9, 71.5]
            }, {
                name: '班级最高分',
                data: [83.6, 78.8]
            }, {
                name: '班级最低分',
                data: [48.9, 38.8]
            }]
        })
    });

    $(function(){
        $('body').on('click','.timu_type span.ty',function(){
            var km_id1=[];
            if($(this).text()=='所有科目'){
                var km_id='';
                $(this).parent().siblings().each(function(){
                    km_id1.push($(this).children().attr('data-id'));
                })
            }else{
                var km_id=$(this).attr('data-id');
                 km_id1.push($(this).attr('data-id'));
            }
            var kind_j=$('.day_select .day_1 a.on').index()+1;
            if(kind_j==4){
                kind_j=3;
            }
            $('.timu_type span.ty').removeClass('on')
            $(this).addClass('on');
            // 获取课堂表现波动
            $.getJSON("/course/action/performance?kind="+kind_j+"&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode="+km_id1+"",function(result){
                if(result[0].error=='参数或数据异常'){
                    $('.no_message1').show();
                    $('#container1').hide();
                }else{
                    $('.no_message1').hide();
                    $('#container1').show();
                    var series=chart_1.series;
                    while(series.length > 0) {
                        series[0].remove(false);
                    }
                    if(kind_j==1){
                        var ts_list=[];
                        var startTime = getDate(time_prev);
                        var endTime = getDate(today);
                        var i=0;
                        while((endTime.getTime()-startTime.getTime())>=0){
                            var year = startTime.getFullYear();
                            var month = (startTime.getMonth()+1).toString().length==1?"0"+(startTime.getMonth()+1).toString():(startTime.getMonth()+1).toString();
                            var day = startTime.getDate().toString().length==1?"0"+startTime.getDate():startTime.getDate();
                            // date_all[i]=year+"-"+month+"-"+day;
                            // ts_list.push(year+"-"+month+"-"+day);
                            ts_list.push(month+"-"+day);
                            startTime.setDate(startTime.getDate()+1);
                            i=i+1;
                        }
                        for (var x in fulldate_all) {
                            if(fulldate_all[x]==time_next){
                                num=parseInt(x)+1;
                                if(num>7){
                                    ts_list=date_all.slice(parseInt(x)-6, parseInt(x)+1);
                                }else{
                                    ts_list=date_all.slice(0, parseInt(x)+1);
                                }
                            }
                        }
                    }else if(kind_j==2){
                        var ts_list=[]
                        for(var i=6;i>=0;i--){
                            var k=num-i;
                            if(k>0){
                                ts_list.push("第"+k+"周");
                            }
                        }
                    }else if(kind_j==3){
                        var ts_list=[];
                        for (var x in month_list) {
                            if(month_list[x]==time_next){
                                num=parseInt(x)+1;
                                if(num>7){
                                    ts_list=month_list.slice(parseInt(x)-6, parseInt(x)+1);
                                }else{
                                    ts_list=month_list.slice(0, parseInt(x)+1);
                                }
                            }
                        }
                    }
                    for (var key in result[0]){
                        if(num<7){
                            chart_1.addSeries({name:key,data:result[0][key].slice(7-num)}, false);
                        }else{
                            chart_1.addSeries({name:key,data:result[0][key]}, false);
                        }

                    }
                    chart_1.xAxis[0].setCategories(ts_list);
                    chart_1.redraw();
                }
            });
            // 获取课堂参与
            $.getJSON("/course/action/participation?kind="+kind_j+"&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode="+km_id+"",function(result){
                var series=chart_2.series;
                while(series.length > 0) {
                    series[0].remove(false);
                }
                for (var key in result[0]){
                    chart_2.addSeries({name:key,data:[result[0][key]]}, false);
                }
                chart_2.redraw();
            })
            // 获取随堂小测
            $.getJSON("/course/action/courseTest?kind="+kind_j+"&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode="+km_id+"",function(result){
                chart3=[];
                for (var key in result[0]){
                    chart3.push(result[0][key])
                }
                if(chart3.length>3){
                    $('.no_message3').hide();
                    $('#container3').show();
                    info="<p class='bjncts'><span>"+chart3[0]+"</span> | <span> 正确率："+chart3[1]*100+"%</span> | <span>排名:"+chart3[2]+"</span></p>"
                    var series=chart_3.series;
                    var title={
                        text:info,
                        margin: 50,
                        useHTML:true
                    };
                    chart_3.setTitle(title);
                    chart_3.yAxis[0].removePlotLine('plot-line-1');
                    chart_3.yAxis[0].addPlotLine({
                        color: '#ff6a88',
                        dashStyle: 'Solid',
                        width: 3,
                        value:chart3[4],
                        label: {
                            align: 'right',
                            style: {
                                fontStyle: 'normal',
                                color:'#fd9494',
                                fontSize:20
                            },
                            text: '班级正确率:'+chart3[4]+'%',
                            x: -10,
                            y:25
                        },
                        zIndex: 3,
                        id: 'plot-line-1'
                    });
                    while(series.length > 0) {
                        series[0].remove(false);
                    }
                    var test=[];
                    $.each(chart3[3],function(index,value){
                        value=value*100;
                        if(index==chart3[2]-1){
                            test.push({y:value,color : "#ffe0b2",marker:{radius:8}});
                        }else{
                            test.push({y:value});
                        }
                    });
                    chart_3.addSeries({data:test,pointInterval: 1}, false);
                    chart_3.redraw();
                }else{
                    $('.no_message3').show();
                    $('#container3').hide();
                }
            });
            // 获取随堂评价
            $.getJSON("/course/action/appraise?kind="+kind_j+"&userId="+userId+"&begin="+time_prev+"&end="+time_next+"&subjectCode="+km_id+"",function(result){
                var series=chart_4.series;
                while(series.length > 0) {
                    series[0].remove(false);
                }
                for (var key in result[0]){
                    chart_4.addSeries({name:key,data:result[0][key]}, false);
                }
                chart_4.redraw();
            })
        })
    })
</script>
</body>
</html>