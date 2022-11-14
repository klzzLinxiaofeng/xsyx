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

        /*    top*/
        .reportrepair__body > .top > div {
            padding: 0;
            display: inline-block; /* 如需支持IE8以下版本，用浮动来做 */
            width: calc(98% / 2); /* 此处运用了一个css3的表达式，将div三等分，IE8及以上可以支持，当然也可以根据需要设置固定值 */
            width: -webkit-calc(98% / 2); /*3.09 排除margin的宽度*/
            width: -moz-calc(98% / 2);
            margin: 0 auto;
            height: 100%;
        }

        .reportrepair__body > .top > div:nth-child(n) {
            border: 0.1px rgb(192, 192, 192) solid;
            background: #fff;
        }

        .reportrepair__top_div1 {
            width: 100%;
            height: 150px;
        }

        .reportrepair__top_div2 {
            margin: 0;
            padding: 0 3%;
            width: 95%;
            height: 200px;
        }


        .reportrepair__top_div1 {
            margin: 0;
            padding: 0 3%;
            width: 95%;
            height: 150px;
        }

        .reportrepair__top_div2_title {
            width: 100%;
            height: 40px;
            border-bottom: 1px grey solid;

        }

        .reportrepair__top_div2_bottom {

            width: 100%;
            height: 140px;
        }

        .reportrepair__top_div2_title > h2 {
            text-align: left;
        }

        .reportrepair__top_div2_bottom > ul > li:nth-child(1) {
            margin-top: 3%;
            color: grey;
            font-size: 1.5rem;
        }

        .reportrepair__top_div2_bottom > ul > li:nth-child(2) {
            color: #000;
            margin-top: 4%;
            font-size: 3rem;
        }

        .reportrepair__top_div2_bottom > ul > li:nth-child(2) > span:nth-child(2) {
            margin-right: 20%;
            font-size: 1.5rem;
            float: right;
        }

        .reportrepair__top_div2_bottom > ul > li:nth-child(2) > span:nth-child(2) > span {
            color: orange;
        }

        .reportrepair__top_div2_bottom {

            width: 98%;
            height: 140px;
        }
        .reportrepair__top_div2_bottom2 > ul > li:nth-child(1) {
            margin-top: 3%;
            color: grey;
            font-size: 1.5rem;
        }
        .reportrepair__top_div2_bottom2 > ul > li:nth-child(1) >span:nth-child(2) {
        float:  right;
            padding-right: 13%;
        }

        .reportrepair__top_div2_bottom2 > ul > li:nth-child(2) {
            color: #000;
            margin-top: 4%;
            font-size: 3rem;
        }

        .reportrepair__top_div2_bottom2 > ul > li:nth-child(2) > span:nth-child(2) {
            float: right;
            padding-right: 13%;
        }

        .reportrepair__top_div2_bottom2>ul>li:nth-child(3){
            margin-top: 5%;
            color: grey;
            font-size: 1.5rem;
        }

        /*bottom*/


      .bottom > div:nth-child(n) {
            padding: 0;
            margin: 0 auto;
            height: 28rem;
            border: 0.1px rgb(192, 192, 192) solid;
            background: #fff;
            overflow: hidden;
        }

        .bottom {
            margin-top: 0.2rem;
            width: 98.5%;

        }

        .reportrepair__bottom_title {
            padding: 0 3% 0 1.5%;
            width: 95%;
            height: 40px;
            border-bottom: 1px grey solid;

        }

      .reportrepair__bottom_div2>ul>li:nth-child(n)>span{
          display: block;
          padding: 0;
          color: grey;
          display: inline-block; /* 如需支持IE8以下版本，用浮动来做 */
          width: calc(98% / 5); /* 此处运用了一个css3的表达式，将div三等分，IE8及以上可以支持，当然也可以根据需要设置固定值 */
          width: -webkit-calc(98% / 5); /*3.09 排除margin的宽度*/
          width: -moz-calc(98% / 5);
          margin: 0 auto;
          height: 100%;
          text-align: center;

      }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="报修统计" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="reportrepair__body">
        <div class="top">
            <%--报修统计--%>
            <div>
                <div class="reportrepair__top_div2">
                    <div class="reportrepair__top_div2_title"><h2>报销统计</h2></div>
                    <div class="reportrepair__top_div2_bottom">
                        <ul>
                            <li>本月报修数</li>
                            <li><span>15</span><span>环比 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span> +15.25%</span></span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div id="main" class="reportrepair__top_div1">

                </div>
            </div>
            <%--    保修分析--%>
            <div>
                <div class="reportrepair__top_div2">
                    <div class="reportrepair__top_div2_title"><h2>保修分析</h2></div>
                    <div class="reportrepair__top_div2_bottom2">
                        <ul>
                            <li><span>平均报修总天数</span><span> 平均维修消耗工时</span></li>
                            <li><span>3.25天</span>&nbsp;<span> 8.56小时</span></li>
                            <li>平均保修进程</li>
                        </ul>
                    </div>
                </div>
                <div id="main2" class="reportrepair__top_div1">

                </div>
            </div>
        </div>

        <div class="bottom">
            <%--    保修记录--%>
            <div class="">
                <div class="reportrepair__bottom_title">
                    <h2>保修记录</h2>
                </div>
                <div class="reportrepair__bottom_div2">
            <ul>
                <li><span>序号</span><span>申请教师</span><span>维修地点</span><span>申请时间</span><span>进度</span></li>
                <li><span>1</span><span>老陈</span><span>教学楼</span><span>2017-03-11</span><span>98%</span></li>
            </ul>
                </div>
            </div>
        </div>

    </div>
    <%--报销统计--%>
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
                formatter: '报销统计 : <br/>{b} : {c}个'
            },
            //整张表与div的关系 类似于padding
            grid: {
                left: '3%',
                top: '5%',
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
                    formatter: '{value}个 '

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
                data: ['2019-11-01', '2019-11-15', '2019-11-30', '2019-12-30']
            },
            //决定y轴呈现的数值
            series: [
                {
                    //与图例对应
                    name: '报销统计',
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
    <%--    保修分析--%>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main2'));
        // 指定图表的配置项和数据
        option = {
            tooltip: {
                //触发类型：axis在柱状图，折线图等会使用类目轴的图表中使用。，item 数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                },
                //显示数据 b是x轴 c是y轴
                // formatter: '校园消费 : <br/>{b} : {c}元'
            },
            legend: {
                data: ['报修申请', '开始维修', '维修完成', '完成评价'],
            },
            grid: {
                left: '3%',
                top: '-80%',
                //表中是否包含刻度
                containLabel: false
            },
            xAxis: [
                {
                    axisTick:{       //x轴刻度线
                        show:false
                    },
                    type: 'value',
                    axisLabel: {
                        //显示数据
                        formatter: '{value}'

                    },splitLine: {     //去掉网格线
                        show: false
                    },show:false
                }
            ],
            yAxis: [
                {

                    type: 'category',
                    data: ['报修申请', '开始维修', '维修完成', '完成评价'],
         axisTick:{       //y轴刻度线
            show:false
        },splitLine: {     //去掉网格线
                        show: false
                    },
                    show:false
                }
            ],
            series: [
                {
                    name: '报修申请',
                    type: 'bar',
                    stack:'时间',
                    data: [320]
                },
                {
                    name: '开始维修',
                    type: 'bar',
                    stack: '时间',
                    data: [120]
                },
                {
                    name: '维修完成',
                    type: 'bar',
                    stack: '时间',
                    data: [220]
                },
                {
                    name: '完成评价',
                    type: 'bar',
                    stack: '时间',
                    data: [150]
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