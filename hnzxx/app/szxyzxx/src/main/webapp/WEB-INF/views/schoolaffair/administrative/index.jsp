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

    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="行政统计" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div style="width: 100%;display: flex">
                <div style="width: 49%;">
                    <div class="content-widgets white">
                        <div class="widget-head">
                            <h3>
                                行政统计
                            </h3>
                        </div>
                        <div class="content-widgets">
                            <div id="teacher_num" style="width: 98%"></div>
                        </div>
                    </div>
                </div>
                <div style="width: 51%;margin-left: 20px">
                    <div class="content-widgets white">
                        <div class="widget-head">
                            <h3>
                                行政分布
                            </h3>
                        </div>
                        <div class="content-widgets">
                            <div id="teacher_num2" style="width: 98%"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        事项记录
                    </h3>
                </div>
                <div class="content-widgets">
                    <div id="teacher_num3"></div>
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
    });


    function teacherSubject() {
        var returnData = {"2020-09-15": 10, "2020-09-30": 50, "2020-10-15": 80}
        var titleData = new Array();
        var valueData = new Array();
        for (var key in returnData) {
            titleData.push(key);
            valueData.push(returnData[key]);
        }
        //科任教师人数
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
                enabled: true//关闭设置按钮
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
                name: '行政统计',
                data: valueData
            }]
        });
    }

    function teacherSubject2() {
        var returnData = {"早读": 20, "课间": 50, "课堂": 40, "两操": 30}
        var titleData = new Array();
        var valueData = new Array();
        for (var key in returnData) {
            titleData.push(key);
            valueData.push(returnData[key]);
        }
        //科任教师%数
        $('#teacher_num2').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: '行政分布'
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
                name: '行政分布',
                data: [

                    {
                        name: '请假',
                        y: 20.31,
                    }, {
                        name: '报销',
                        y: 15
                    }, {
                        name: '外勤',
                        y: 11.84
                    }, {
                        name: '休假',
                        y: 9
                    }, {
                        name: '采购',
                        y: 11.81
                    }, {
                        name: '用章',
                        y: 11
                    }, {
                        name: '公文',
                        y: 21
                    }
                ]
            }]
        });
    }

    function teacherSubject3() {
        var returnData = {"序号": 30, "类型": 50, "申请人": 100, "审批人": 100, "进度": 11}
        var titleData = new Array();
        var valueData = new Array();
        for (var key in returnData) {
            titleData.push(key);
            valueData.push(returnData[key]);
        }
        //科任教师人数
        $('#teacher_num3').highcharts({
            chart: {
                type: 'column'
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
                    '<td style="padding:0;border:none;"><b>{point.y:f} 人</b></td></tr>',
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
                name: '事项记录',
                data: valueData
            }]
        });
    }


</script>
</html>