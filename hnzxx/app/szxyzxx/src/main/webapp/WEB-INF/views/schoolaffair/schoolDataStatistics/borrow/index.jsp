<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/views/embedded/common.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${sca:getDefaultSchoolName()}</title>
    <script src="${pageContext.request.contextPath}/res/plugin/echarts/js/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/plugin/echarts/js/jquery-2.1.1.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/extra/sx_statistics.css">
    <style type="text/css">
        html, body {
            height: 100%;
        }

        body {
            margin: 0;
            padding: 0;
        }


    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="借阅统计" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="borrow__body">
        <div class="top">
            <%--借阅统计--%>
            <div>
                <div class="borrow__top_div2">
                    <div class="borrow__top_div2_title"><h2>借阅统计</h2></div>
                    <div class="borrow__top_div2_bottom">
                        <ul>
                            <li>本周借阅</li>
                            <li>
                                <span>153</span><span>环比 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span> +15.25%</span></span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div id="main" class="borrow__top_div1">

                </div>
            </div>
            <%--    周借阅分布--%>
            <div>

                <div class="borrow__top_div2_1_title"><h2>周借阅分布</h2></div>
                <div id="main2" class="borrow__top_div1_2_bottom">

                </div>
            </div>
        </div>

        <div class="bottom">
            <%--   图书热度榜--%>
            <div class="">
                <div class="borrow__bottom_title">
                    <h2>图书热度榜</h2>
                </div>
                <div class="borrow__bottom_div2">
                    <ul>
                        <li><span>1</span><span>小王子</span><span>12</span></li>
                        <li><span>2</span><span>小王子</span><span>10</span></li>
                    </ul>
                </div>
            </div>
            <%--                班级周榜--%>
            <div class="">
                <div class="borrow__bottom_title">
                    <h2>班级周榜</h2>
                </div>
                <div class="borrow__bottom_div2">
                    <ul>
                        <li><span>1</span><span>四(3)班</span><span>12</span></li>
                        <li><span>2</span><span>四(3)班</span><span>10</span></li>
                    </ul>
                </div>
            </div>
            <%--                学生周榜--%>
            <div class="">
                <div class="borrow__bottom_title">
                    <h2>学生周榜</h2>
                </div>
                <div class="borrow__bottom_div2">
                    <ul>
                        <li><span>1</span><span>冯思琪</span><span>12</span></li>
                        <li><span>2</span><span>冯思琪</span><span>10</span></li>
                    </ul>
                </div>
            </div>
            <%--                教师周榜--%>
            <div class="">
                <div class="borrow__bottom_title">
                    <h2>教师周榜</h2>
                </div>
                <div class="borrow__bottom_div2">
                    <ul>
                        <li><span>1</span><span>林海波</span><span>12</span></li>
                        <li><span>2</span><span>林海波</span><span>10</span></li>
                    </ul>
                </div>
            </div>
        </div>

    </div>
    <%--借阅统计--%>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        var option = {

            //提示框组件
            tooltip: {
                //触发类型：axis在柱状图，折线图等会使用类目轴的图表中使用。，item 数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
                trigger: 'axis',
                //显示数据 b是x轴 c是y轴
                formatter: '借阅统计 : <br/>{b}本 : {c}'
            },
            //整张表与div的关系 类似于padding
            grid: {
                left: '%',
                top: '4%',
                // rigth:"3%", 不要rigth x轴的最大值文字不会溢出
                bottom: '3%',
                //表中是否包含刻度
                containLabel: true

            },
            yAxis: {
                //value显示连续值【一般用于y轴】，time是显示时间，log对数轴，category离散值【一般用于x轴】
                type: 'value',
                splitLine: {show: false},   //去除网格线
                //轴布局
                axisLabel: {
                    //显示数据
                    formatter: '{value} 本'

                }
            },
            xAxis: {
                type: 'category',
                //坐标轴轴线相关设置
                axisLine: {
                    //在y轴0刻度是否生成一条线
                    onZero: false
                },
                //轴布局
                axisLabel: {
                    //显示数据
                    formatter: '{value} '
                },
                // 坐标轴两边留白策略
                boundaryGap: false,
                data: ['第五周', '第六周', '第七周', '第八周']
            },
            //决定y轴呈现的数值
            series: [
                {
                    //与图例对应
                    name: '借阅统计',
                    //line 将点连接的图
                    type: 'line',
                    //曲线还是折线，true为曲线
                    smooth: true,
                    //线的类型
                    lineStyle: {
                        width: 3,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 10,
                        shadowOffsetY: 10,

                    },
                    //y轴数据
                    data: [100000, 180000, 3880000, 1500000, 200000],


                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <%--    周借阅分布--%>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main2'));
        // 指定图表的配置项和数据
        option = {
            color: ['#3398DB'],
            tooltip: {
                trigger: 'axis',
                //显示数据 b是x轴 c是y轴
                formatter: '周借阅分布 : <br/>{b}:{c}本'
            },
            grid: {
                left: '5%',
                right: '%',
                bottom: '3%',
                top: "5%",
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['一年级', '二年级', '三年级 ', '四年级', '五年级', '六年级', '七年级'],
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    axisLabel: {
                        //显示数据
                        formatter: '{value} 本'
                    },
                    axisTick: {       //y轴刻度线
                        show: false
                    }, splitLine: {     //去掉网格线
                        show: false
                    },
                }
                ,
            ],
            series: [
                {
                    name: '周借阅分布',
                    type: 'bar',
                    label: {
                        show: true,
                        position: 'top'
                    },
                    data: [10, 52, 200, 334, 390, 330, 220]
                }
            ]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
    <%--    角色占比--%>
    <script type="text/javascript">

        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('pie'));
        // 指定图表的配置项和数据
        option = {
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c} ({d}%)'
            },
            series: [
                {
                    name: '角色占比',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: [
                        {value: 600, name: '学生消费'},
                        {value: 90000, name: '教师消费'},
                    ],
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</div>
</body>
</html>