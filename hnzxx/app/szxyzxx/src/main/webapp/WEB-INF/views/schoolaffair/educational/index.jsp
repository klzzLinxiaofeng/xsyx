<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title></title>
    <script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
    <script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/modules/exporting.js"></script>
    <style type="text/css">
        .ffs {
            font-weight: 600;
        }

        #teacher_num9 {
            display: inline-flex;
        }

        #teacher_num10 {
            text-align: center;
            line-height: 154px;
            border-radius: 91%;
            height: 154px;
            width: 150px;
            border: 1px solid #7c7c7c;
        }

        #teacher_num11 {
            text-align: center;
            line-height: 154px;
            border-radius: 91%;
            height: 154px;
            width: 150px;
            border: 1px solid #7c7c7c;
            margin-left: 50px;
        }

        .content-widgets {
            margin-bottom: 0px !important;
            position: relative;
            border: 1px solid #ccc9c9;
            height: 485px;
            width: 98%;
            margin-left: auto;
            margin-right: auto;
        }

        .widget-container {
            padding: 0px 0px 0px 0px !important;
        }

        .breadcrumb {
            background: #fff;
        }

    </style>
</head>
<body>
<div class="container-fluid" style="background: #e4e2e2;">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="教务统计" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div style="background-color: #e6e6e6;width: 100%;">
                <div style="width: 35%;float: left;">
                    <div class="content-widgets white">
                        <div class="way_statistics" style="height: 505px;margin:0;width: 100%;">
                            <div style="font-size: 29px;font-weight: 700;text-align: center;margin-top: 13px;">一年级成绩走势
                            </div>
                            <div id="teacher_num" style="min-width:95%;height:415px;margin-top:30px;"></div>
                        </div>
                    </div>

                    <div class="content-widgets white" style="margin-top: 10px">
                        <div class="way_statistics" style="height: 505px;margin:0;width: 100%;">
                            <div style="font-size: 29px;font-weight: 700;text-align: center;margin-top: 13px;">二年级成绩走势
                            </div>
                            <div id="teacher_num2" style="min-width:95%;height:415px;margin-top:30px;"></div>
                        </div>
                    </div>

                    <div class="content-widgets white" style="margin-top: 10px">
                        <div class="way_statistics" style="height: 505px;margin:0;width: 100%;">
                            <div style="font-size: 29px;font-weight: 700;text-align: center;margin-top: 13px;">三年级成绩走势
                            </div>
                            <div id="teacher_num3" style="min-width:95%;height:415px;margin-top:30px;"></div>
                        </div>
                    </div>
                    <div class="content-widgets white" style="margin-top: 10px">
                        <div style="font-size: 29px;font-weight: 700;text-align: center;margin-top: 13px;">四年级成绩走势</div>
                        <div class="way_statistics" style="height: 505px;margin:0;width: 100%;">
                            <div id="teacher_num4" style="min-width:95%;height:415px;margin-top:30px;"></div>
                        </div>
                    </div>
                    <div class="content-widgets white" style="margin-top: 10px">
                        <div style="font-size: 29px;font-weight: 700;text-align: center;margin-top: 13px;">五年级成绩走势</div>
                        <div class="way_statistics" style="height: 505px;margin:0;width: 100%;">
                            <div id="teacher_num5" style="min-width:95%;height:415px;margin-top:30px;"></div>
                        </div>
                    </div>
                    <div class="content-widgets white" style="margin-top: 10px">
                        <div style="font-size: 29px;font-weight: 700;text-align: center;margin-top: 13px;">六年级成绩走势</div>
                        <div class="way_statistics" style="height: 505px;margin:0;width: 100%;">
                            <div id="teacher_num6" style="min-width:95%;height:415px;margin-top:30px;"></div>
                        </div>
                    </div>
                </div>
                <div style="width: 65%;float:right;">
                    <div style="width: 100%">
                        <div style="width: 49%;float: left;margin-left: 10px">
                            <div class="content-widgets white">
                                <div style="font-size: 29px;font-weight: 700;text-align: center;margin-top: 13px;">
                                    选课压测
                                </div>
                                <div class="way_statistics" style="height: 505px;margin:0;width: 100%;">

                                    <div id="teacher_num7" style="min-width:95%;height:415px;margin-top:30px;"></div>
                                </div>
                            </div>
                        </div>
                        <div style="width: 50%;float:right;">
                            <div class="content-widgets white">
                                <div style="font-size: 29px;font-weight: 700;text-align: center;margin-top: 13px;">
                                    教育质量
                                </div>
                                <div class="way_statistics" style="height: 505px;margin:0;width: 100%;">
                                    <div id="teacher_num8"
                                         style="min-width:96%;height:415px;margin-top:50px;font-size: 20px; padding-left: 20px;">
                                        <div class="ffs">高级教师占比：35%</div>
                                        <br/>
                                        <div class="ffs">任课教师占比：75%</div>
                                        <br/>
                                        <div class="ffs">备课优秀率：85%</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="width: 100%;position: initial;display: inline-block;margin-top: 10px;">
                        <div class="content-widgets white" style="height: 1000px">
                            <div style="font-size: 29px;font-weight: 700;text-align: left;margin-top: 13px;">学生综合</div>
                            <div class="way_statistics" style="height: 505px;margin:0;width: 100%;">
                                <div id="teacher_num9" style="min-width:96%;height:415px;margin-top:50px;">
                                    <div style="-webkit-writing-mode: vertical-rl;writing-mode: vertical-rl;font-size: 29px;margin-left: 20px;margin-top: 70px;">
                                        身体素质
                                    </div>

                                    <div style="width: 100%">

                                        <div style="width: 100%;margin-left: 90px;margin-top: 100px">
                                            <table>
                                                <tr>
                                                    <td>
                                                        <div id="teacher_num10">体育课出勤率: 96.44%</div>
                                                    </td>
                                                    <td>
                                                        <div id="teacher_num11">年度体侧达标率: 85.34%</div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>

                                        <div style="width: 96%;margin-top: 200px;position: relative;">
                                            <div style="width: 10%">
                                                <div style="-webkit-writing-mode: vertical-rl;writing-mode: vertical-rl;font-size: 29px;margin-left: 35px;margin-top: 70px;position: absolute;margin-left: -3%;">
                                                    文化素养
                                                </div>
                                            </div>
                                            <div style="width: 90%;margin-left: 10%;">
                                                <div id="teacher_num14"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="width: 100%">

                                        <div style="width: 96%;">
                                            <div id="teacher_num12"></div>
                                        </div>
                                        <div style="width: 96%;margin-top: 50px">
                                            <div id="teacher_num15"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">


    $(function () {
        teacherSubject();
        teacherSubject2();
        teacherSubject3();
        teacherSubject4();
        teacherSubject5();
        teacherSubject6();
        teacherSubject7();
        teacherSubject12();
        teacherSubject14();
        teacherSubject15();
    });


    function teacherSubject() {
        var returnData = {"2020-09-15": 10, "2020-09-30": 50, "2020-10-15": 80}
        var titleData = new Array();
        var valueData = new Array();
        for (var key in returnData) {
            titleData.push(key);
            valueData.push(returnData[key]);
        }
        $("#teacher_num").css("height", ($("#charts").height() - 100) + "px");
        $("#teacher_num").css("width", $("#charts").width() + "px");

        //科任教师%数
        $('#teacher_num').highcharts({
            chart: {
                type: 'area'
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false // 禁用版权信息
            },

            exporting: {
                enabled: false//关闭设置按钮
            },
            xAxis: {
                categories: titleData,
                // tickWidth:false,
                lineColor: "#323232",
                // lineWidth:2,
                labels: {
                    style: {
                        color: "#323232",
                        fontSize: "12px",
                    },
                },
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                labels: {
                    style: {
                        // color:"#fff",
                    },
                },
                // gridLineColor: false,
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px;">{point.key}</span><br><table style="border:none;">',
                pointFormat: '<tr>' +
                    '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                // useHTML: true,
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '一年级成绩走势',
                data: valueData
            }]
        });
    }

    function teacherSubject2() {
        var returnData = {"2020-09-15": 30, "2020-09-30": 80, "2020-10-15": 100}
        var titleData = new Array();
        var valueData = new Array();
        for (var key in returnData) {
            titleData.push(key);
            valueData.push(returnData[key]);
        }
        //科任教师%数
        $('#teacher_num2').highcharts({
            chart: {
                type: 'area'
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false // 禁用版权信息
            },

            exporting: {
                enabled: false//关闭设置按钮
            },
            xAxis: {
                categories: titleData,
                // tickWidth:false,
                lineColor: "#323232",
                // lineWidth:2,
                labels: {
                    style: {
                        color: "#323232",
                        fontSize: "12px",
                    },
                },
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                labels: {
                    style: {
                        // color:"#fff",
                    },
                },
                // gridLineColor: false,
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px;">{point.key}</span><br><table style="border:none;">',
                pointFormat: '<tr>' +
                    '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                // useHTML: true,
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '二年级成绩走势',
                data: valueData
            }]
        });
    }

    function teacherSubject3() {
        var returnData = {"2020-09-15": 30, "2020-09-30": 50, "2020-10-15": 100}
        var titleData = new Array();
        var valueData = new Array();
        for (var key in returnData) {
            titleData.push(key);
            valueData.push(returnData[key]);
        }
        //科任教师%数
        $('#teacher_num3').highcharts({
            chart: {
                type: 'area'
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false // 禁用版权信息
            },

            exporting: {
                enabled: false//关闭设置按钮
            },
            xAxis: {
                categories: titleData,
                // tickWidth:false,
                lineColor: "#323232",
                // lineWidth:2,
                labels: {
                    style: {
                        color: "#323232",
                        fontSize: "12px",
                    },
                },
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                labels: {
                    style: {
                        // color:"#fff",
                    },
                },
                // gridLineColor: false,
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px;">{point.key}</span><br><table style="border:none;">',
                pointFormat: '<tr>' +
                    '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                // useHTML: true,
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '三年级成绩走势',
                data: valueData
            }]
        });
    }

    function teacherSubject4() {
        var returnData = {"2020-09-15": 30, "2020-09-30": 50, "2020-10-15": 60}
        var titleData = new Array();
        var valueData = new Array();
        for (var key in returnData) {
            titleData.push(key);
            valueData.push(returnData[key]);
        }
        //科任教师%数
        $('#teacher_num4').highcharts({
            chart: {
                type: 'area'
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false // 禁用版权信息
            },

            exporting: {
                enabled: false//关闭设置按钮
            },
            xAxis: {
                categories: titleData,
                // tickWidth:false,
                lineColor: "#323232",
                // lineWidth:2,
                labels: {
                    style: {
                        color: "#323232",
                        fontSize: "12px",
                    },
                },
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                labels: {
                    style: {
                        // color:"#fff",
                    },
                },
                // gridLineColor: false,
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px;">{point.key}</span><br><table style="border:none;">',
                pointFormat: '<tr>' +
                    '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                // useHTML: true,
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '四年级成绩走势',
                data: valueData
            }]
        });
    }

    function teacherSubject5() {
        var returnData = {"2020-09-15": 10, "2020-09-30": 50, "2020-10-15": 80}
        var titleData = new Array();
        var valueData = new Array();
        for (var key in returnData) {
            titleData.push(key);
            valueData.push(returnData[key]);
        }
        //科任教师%数
        $('#teacher_num5').highcharts({
            chart: {
                type: 'area'
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false // 禁用版权信息
            },

            exporting: {
                enabled: false//关闭设置按钮
            },
            xAxis: {
                categories: titleData,
                // tickWidth:false,
                lineColor: "#323232",
                // lineWidth:2,
                labels: {
                    style: {
                        color: "#323232",
                        fontSize: "12px",
                    },
                },
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                labels: {
                    style: {
                        // color:"#fff",
                    },
                },
                // gridLineColor: false,
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px;">{point.key}</span><br><table style="border:none;">',
                pointFormat: '<tr>' +
                    '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                // useHTML: true,
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '五年级成绩走势',
                data: valueData
            }]
        });
    }

    function teacherSubject6() {
        var returnData = {"2020-09-15": 20, "2020-09-30": 30, "2020-10-15": 60}
        var titleData = new Array();
        var valueData = new Array();
        for (var key in returnData) {
            titleData.push(key);
            valueData.push(returnData[key]);
        }
        //科任教师%数
        $('#teacher_num6').highcharts({
            chart: {
                type: 'area'
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false // 禁用版权信息
            },

            exporting: {
                enabled: false//关闭设置按钮
            },
            xAxis: {
                categories: titleData,
                // tickWidth:false,
                lineColor: "#323232",
                // lineWidth:2,
                labels: {
                    style: {
                        color: "#323232",
                        fontSize: "12px",
                    },
                },
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                labels: {
                    style: {
                        // color:"#fff",
                    },
                },
                // gridLineColor: false,
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px;">{point.key}</span><br><table style="border:none;">',
                pointFormat: '<tr>' +
                    '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                // useHTML: true,
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '六年级成绩走势',
                data: valueData
            }]
        });
    }

    function teacherSubject7() {
        var returnData = {"实时在线人数": 20, "承压数": 50}
        var titleData = new Array();
        var valueData = new Array();
        for (var key in returnData) {
            titleData.push(key);
            valueData.push(returnData[key]);
        }
        //科任教师%数
        $('#teacher_num7').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                //  text: '选课压测'
            },
            credits: {
                enabled: false // 禁用版权信息
            },

            exporting: {
                enabled: false//关闭设置按钮
            },
            xAxis: {
                categories: titleData,
                // tickWidth:false,
                lineColor: "#323232",
                // lineWidth:2,
                labels: {
                    style: {
                        color: "#323232",
                        fontSize: "12px",
                    },
                },
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                labels: {
                    style: {
                        // color:"#fff",
                    },
                },
                // gridLineColor: false,
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px;">{point.key}</span><br><table style="border:none;">',
                pointFormat: '<tr>' +
                    '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                // useHTML: true,
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '选课压测',
                data: [
                    {
                        name: '实时在线%数',
                        y: 61.41,
                        sliced: true,
                        selected: true
                    }, {
                        name: '承压数',
                        y: 11.84
                    }
                ]
            }]
        });
    }

    function teacherSubject12() {
        var returnData = {"2020-09-15": 20, "2020-09-30": 50, "2020-10-15": 60}
        var titleData = new Array();
        var valueData = new Array();
        for (var key in returnData) {
            titleData.push(key);
            valueData.push(returnData[key]);
        }
        //科任教师%数
        $('#teacher_num12').highcharts({
            chart: {
                type: 'area'
            },
            title: {
                text: '周体侧达标率走势'
            },
            credits: {
                enabled: false // 禁用版权信息
            },

            exporting: {
                enabled: false//关闭设置按钮
            },
            xAxis: {
                categories: titleData,
                // tickWidth:false,
                lineColor: "#323232",
                // lineWidth:2,
                labels: {
                    style: {
                        color: "#323232",
                        fontSize: "12px",
                    },
                },
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                labels: {
                    style: {
                        // color:"#fff",
                    },
                },
                // gridLineColor: false,
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px;">{point.key}</span><br><table style="border:none;">',
                pointFormat: '<tr>' +
                    '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                // useHTML: true,
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '周体侧达标率走势',
                data: valueData
            }]
        });
    }

    function teacherSubject14() {
        var returnData = {"2020-09-15": 20, "2020-09-30": 50, "2020-10-15": 60}
        var titleData = new Array();
        var valueData = new Array();
        for (var key in returnData) {
            titleData.push(key);
            valueData.push(returnData[key]);
        }
        //科任教师%数
        $('#teacher_num14').highcharts({
            chart: {
                type: 'area'
            },
            title: {
                text: '行为规范日均得分走势'
            },
            credits: {
                enabled: false // 禁用版权信息
            },

            exporting: {
                enabled: false//关闭设置按钮
            },
            xAxis: {
                categories: titleData,
                // tickWidth:false,
                lineColor: "#323232",
                // lineWidth:2,
                labels: {
                    style: {
                        color: "#323232",
                        fontSize: "12px",
                    },
                },
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                labels: {
                    style: {
                        // color:"#fff",
                    },
                },
                // gridLineColor: false,
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px;">{point.key}</span><br><table style="border:none;">',
                pointFormat: '<tr>' +
                    '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                // useHTML: true,
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '行为规范日均得分走势',
                data: valueData
            }]
        });
    }

    function teacherSubject15() {
        var returnData = {"早读": 20, "课间": 50, "课堂": 40, "两操": 30}
        var titleData = new Array();
        var valueData = new Array();
        for (var key in returnData) {
            titleData.push(key);
            valueData.push(returnData[key]);
        }
        //科任教师%数
        $('#teacher_num15').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: '行为规范细化日均'
            },
            credits: {
                enabled: false // 禁用版权信息
            },

            exporting: {
                enabled: false//关闭设置按钮
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px;">{point.key}</span><br><table style="border:none;">',
                pointFormat: '<tr>' +
                    '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
            },
            plotOptions: {
                pie: {
                    showInLegend: true
                }
            },
            series: [{
                name: '行为规范细化日均',
                data: [
                    {
                        name: '早读',
                        y: 61.41,
                    }, {
                        name: '课间',
                        y: 11.84
                    }, {
                        name: '课堂',
                        y: 11.84
                    }, {
                        name: '两操',
                        y: 11.84
                    }
                ]
            }]
        });
    }


</script>
</html>