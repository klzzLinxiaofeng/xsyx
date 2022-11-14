<%@ page language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
    <title>${name}</title>
</head>
<body style="background-color:#e3e3e3">
<div class="container-fluid">
    <%--<p class="top_link">首页  > ${name}  > <span>评价统计</span></p>--%>
    <div class="content_main white">
        <div class="content_top">
            <div class="f_left"><p>评价统计</p></div>
        </div>
        <div class="input_select">
            <div class="select_div">
                <select id="xn" name="xn" class="chzn-select">
                    <option>学年</option>
                </select>
            </div>
            <div class="select_div">
                <select id="xq" name="xq" class="chzn-select">
                    <option>学期</option>
                </select>
            </div>
            <div class="select_div">
                <select id="nj" name="nj" class="chzn-select">
                    <option>年级</option>
                </select></div>
            <div class="select_div">
                <select id="bj" name="bj" class="chzn-select">
                    <option>班级</option>
                </select>
            </div>
            <div class="select_div">
                <span>关键字：</span>
                <input type="text" id="title">
            </div>

            <div class="select_div" <c:if test="${type == 1}">style="display: none"</c:if>>
                <span>科目：</span>
                <select id="subject"></select>
            </div>
            <div class="btn_link" style="float: right;margin:5px 5px 0 0">
                <button class="btn btn-blue" onclick="find()">搜索</button>
            </div>
        </div>
        <div class="qsdb">
            <a href="javascript:void(0)" class="on">综合评分<br/>对比统计</a>
            <a href="javascript:void(0)">综合评分<br/>趋势统计</a>
        </div>
        <div class="qsfx_chart">
            <div class="chart_div">
                <div style="padding:20px;">
                    <select id="select_week" style="width: 240px;" onchange="change_week()">
                        <option value="">请选学期</option>
                    </select>
                </div>
                <div class="chart_list" style="margin:0 70px;">
                    <!-- <div id="container" style="height:500px;"></div> -->
                </div>
            </div>
            <div class="chart_div" style="display:none;margin:35px 70px;">
                <div id="container_1" class='container container_2' style="height:500px;"></div>
            </div>
        </div>
    </div>
</div>
<script>
    var fyb = 0;
    $(function () {
        var val1 = {};
        $.ajax({
            url: "${pageContext.request.contextPath}/assessment/statistics/subject",
            type: "POST",
            data: val1,
            async: false,
            success: function (data) {
                data = eval("(" + data + ")");
                for (var z = 0; z < data.length; z++) {
                    $("#subject").append("<option value='" + data[z].code + "'>" + data[z].name + "</option>");
                }
            }
        });
        $.initCascadeSelector({
            "type": "team",
            "selectOne": true,
            "yearChangeCallback": function (year) {
                if (year != "") {
                    $.SchoolTermSelector({
                            "selector": "#xq",
                            "condition": {"schoolYear": year},
                            "afterHandler": function ($this) {
                                $this.change();
                                $("#xq_chzn").remove();
                                $this.show().removeClass("chzn-done").chosen();
                            }
                        }
                    );
                } else {
                    $("#xq").val("");
                    $("#xq_chzn").remove();
                    $("#xq").show().removeClass("chzn-done").chosen();
                }
            }, "teamCallback": function () {
                if (fyb == 0) {
                    find();
                    fyb++;
                }
            }
        });
    })

    function change_week() {
        var zc_text = $("#select_week_chzn .chzn-single span").text();
        zc_text = zc_text.substring(1, zc_text.indexOf("周")) - 1;
        console.log(zc_text)
        $(".chart_list .container").hide();
        $(".chart_list .container").eq(zc_text).show();
    }

    function init(data) {
        $(function () {
                /*切换*/
                var div_width = $("body").width() - 180;
                $(".qsdb a").click(function () {
                    $(".qsdb a").removeClass("on");
                    $(this).addClass("on");
                    $(".chart_div").hide();
                    var i = $(this).index();
                    $(".chart_div").eq(i).show();
                })
                var zc_text = $("#select_week_chzn .chzn-single span").text();
                zc_text = zc_text.substring(1, zc_text.indexOf("周")) - 1
                $(".chart_list").niceScroll({"styler": "fb", "cursorcolor": "#BDBDBD", "cursorwidth": "5"});
                var p_length = data.everyWeek.length;
                for (var i = 0; i < p_length; i++) {
                    $("<div id='container" + i + "' class='container container_1' style='height:500px;display:none'></div>").appendTo($(".chart_list"));
                    $('.container').css({"width": div_width, "min-width": div_width});
                    var km_num = data.everyWeek[i].length;
                    if (km_num > 10) {
                        $("#container" + i).width(100 * km_num)
                    }
                    var column_data = [];
                    for (var j = 0; j < km_num; j++) {
                        var new_data = {
                            name: data.everyWeek[i][j].teamName + '<br>' + data.everyWeek[i][j].teacherName,
                            data: [data.everyWeek[i][j].avgScore]
                        }
                        column_data.push(new_data);
                    }
                    if (km_num > 0) {
                        $('#container' + i).highcharts({
                            chart: {
                                type: 'column',
                                marginTop: 30,
                            },
                            title: {
                                text: null
                            },
                            colors: ['#07aaf5', '#4cb9c3', '#ff6b88', '#F7AF08', '#8EACBA', '#3C89D0', '#29F29C', '#FF5050', '#FFE467'],
                            xAxis: {
                                categories: [''],
                                visible: false,
                                labels: {
                                    style: {
                                        fontSize: 14,
                                        color: '#999999'
                                    },
                                    y: 30
                                },
                                crosshair: true,
                            },
                            yAxis: {
                                min: 0,
                                max: 10,
                                title: {
                                    enabled: false
                                },
                            },
                            exporting: {
                                enabled: false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
                            },
                            credits: {
                                enabled: false
                            },
                            tooltip: {
                                shared: false,
                                headerFormat: '<span style="font-size:20px;color:{series.color}">{series.name}: <b style="font-size:20px">{point.y}</b>',
                                pointFormat: '',
                                useHTML: true
                                // enabled:false
                            },
                            legend: {
                                verticalAlign: 'bottom',
                                align: 'center',
                                symbolHeight: 12,
                                symbolWidth: 12,
                                symbolRadius: 6,
                                itemStyle: {
                                    'fontSize': '14px',
                                    'color': '#666666'
                                }
                            },
                            plotOptions: {
                                series: {
                                    dataLabels: {
                                        enabled: true,
                                        crop: false,
                                        overflow: 'none',
                                        style: {
                                            "color": '#666',
                                            "fontSize": "14px",
                                        }

                                    },
                                    column: {},
                                    maxPointWidth: 30,
                                    borderRadius: 15,
                                    pointPadding: 0.4,
                                    groupPadding: 0
                                }
                            },
                            series: column_data
                        });
                    }
                }
                console.log(zc_text)
                $("#container" + zc_text).show();
                var zc = ['第一周 ', '第二周 ', '第三周', '第四周', '第五周', '第六周', '第七周', '第八周', '第九周', '第十周', '第十一周', '第十二周', '第十三周', '第十四周', '第十五周', '第十六周', '第十七周', '第十八周', '第十九周', '第二十周', '第二十一周', '第二十二周', '第二十三周', '第二十四周', '第二十五周', '第二十六周', '第二十七周', '第二十八周', '第二十九周', '第三十周', '第三十一周', '第三十二周', '第三十三周', '第三十四周', '第三十五周', '第三十六周', '第三十七周', '第三十八周', '第三十九周', '第四十周', '第四十一周', '第四十二周', '第四十三周', '第四十四周', '第四十五周', '第四十六周', '第四十七周', '第四十八周', '第四十九周', '第五十周', '第五十一周', '第五十二周']
                var line_data = [];
                for (var m = 0; m < data.week.length; m++) {
                    var new_data1 = {
                        name: data.week[m].teamName + '<br>' + data.week[m].teacherName,
                        data: data.week[m].list
                    }
                    line_data.push(new_data1);
                }
                console.log(line_data)
                $('.container').css({"width": div_width, "min-width": div_width});
                if (data.week.length > 0) {
                    var km_num1 = data.week[0].list.length;
                    var week = zc.slice(0, km_num1);
                    $('#container_1').highcharts({
                        chart: {
                            type: 'line',
                            marginRight: 50,
                            panning: true,
                            pinchType: 'x',
                        },
                        title: {
                            text: null
                        },
                        colors: ['#07aaf5', '#4cb9c3', '#ff6b88', '#F7AF08', '#8EACBA', '#3C89D0', '#29F29C', '#FF5050', '#FFE467'],
                        xAxis: {
                            gridLineColor: '#d6d6d6',
                            gridLineWidth: 1,
                            gridLineDashStyle: 'Dot',
                            categories: week,
                            labels: {
                                y: 30,
// 	        align: 'left',
                                style: {
                                    color: '#999999',
                                    fontSize: '16',
                                    //writingMode : 'tb-rl'//文字竖排样式,
                                }
                            }
                        },
                        yAxis: {
                            gridLineColor: '#d6d6d6',
                            gridLineWidth: 1,
                            gridLineDashStyle: 'Dot',
                            title: {
                                enabled: false
                            },
                            labels: {
                                style: {
                                    color: '#999999',
                                    fontSize: '16',
                                },
                                y: 5
                            },
                            min: 0,
                            max: 10,
                            tickAmount: 6
                        },
                        legend: {
                            align: 'center',
                            verticalAlign: 'bottom',
                            symbolHeight: 18,
                            symbolWidth: 18,
                            symbolRadius: 12,
                            itemStyle: {
                                'fontSize': '14px',
                                'color': '#666666'
                            }
                        },
                        tooltip: {
                            enabled: false,
                            crosshairs: true,
                            shared: true,
                            /*  split: true, */
                            headerFormat: '<small style="font-size:14px">{point.key}</small><br>',
                            pointFormat: '<span style="font-size:14px;color:{series.color}">{series.name}: <b style="font-size:14px">{point.y}</b><br/>',
                            useHTML: true
                        },
                        plotOptions: {
                            line: {
                                //enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                                dataLabels: {
                                    enabled: false,
                                    align: 'left',
                                    formatter: function () {
                                        return '<span style="color:' + this.series.color + '">' + this.point.y + '</span>';
                                    },
                                    style: {
                                        // color:{series.color},
                                        fontSize: 18,
                                    },
                                    shadow: false,
                                    allowOverlap: true,
                                    x: 3,
                                    verticalAlign: 'middle',
                                    overflow: true,
                                    crop: false,
                                    useHTML: true,  //去掉阴影
                                }
                            }
                        },
                        exporting: {
                            enabled: false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
                        },
                        credits: {
                            enabled: false
                        },
                        series: line_data
                    }, function (c) {
                        // 动态改变 x 轴范围即可实现拖动
                        if (km_num1 > 10) {
                            c.xAxis[0].minRange = 9.5;
                            c.xAxis[0].setExtremes(0, 9);
                        }
                    });
                }
            }
        );
    }

    function find() {
        var teamId = $('#bj').val();
        var gradeld = $('#nj').val();
        var termCode = $('#xq').val();
        var title = $('#title').val();
        var subjectCode = $('#subject').val();
        var type =${type};
        var val = {};
        val.gradeId = gradeld;
        val.teamId = teamId;
        val.termCode = termCode;
        val.title = title;
        val.subjectCode = subjectCode;
        val.type = type;
        if (termCode == '') {
            $.error("请选择学期");
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/assessment/statistics/aptsData",
            type: "POST",
            data: val,
            async: false,
            success: function (data) {
//	     	var data={"everyWeek":[[],[],[],[],[],[],[],[],[{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.5,"teacherName":"李谷二","teamName":"高一(1)班","teacherId":1898}],[],[],[{"avgScore":10,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897}],[{"avgScore":9.8,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897}],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[]],"week":[{"teamName":"高一(1)班","list":[0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,9.2,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0],"teacherName":"李谷一"}]}
                console.log(data);
                data = eval("(" + data + ")");
// 	    	data.startDate
                $.getWeek({
                    "selector": "#select_week",
                    "begin": data.startDate,
                    "end": data.finishDate,
                    "today": data.today,
                    "isClear": false,
                    "isSelectCurrentWeek": true,
                    "clearedOptionTitle": "请选择学期",
                    "isMonday": true,
                });
                $(".container_1").remove();
                $(".container_2").empty();
                init(data)
            }
        });
    }
</script>
</body>
</html>