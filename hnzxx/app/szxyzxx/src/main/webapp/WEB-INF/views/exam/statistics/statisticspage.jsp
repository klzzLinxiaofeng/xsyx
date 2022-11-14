<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${pageContext.request.contextPath}/res/css/statistics/questions.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/statistics/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/statistics/highcharts.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        $(".title-content").hide();//隐藏wenben
        $(".title-content:eq(0)").show();//显示第一个wenben
        $(".topic-option span a").click(function () {
            if (!$('.title-content').eq(4).is(":hidden")) {
                alert("正在统计请稍后");
                return false;
            }
            $(".topic-option a").removeClass("statistics");//移除样式
            $(this).addClass("statistics");//添加样式
            var i = $(this).index();//获得下标
            $(".title-content").hide();//隐藏wenben
            $(".title-content:eq(" + i + ")").show();//显示第i个wenben
        });
    });

    $(function () {
        $('#container3').highcharts({
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
                categories: [
                    ''
                ],
                labels: {
                    style: {
                        color: "#666",
                        fontFamily: "微软雅黑",
                        fontSize: "16px",
                    },
                },
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: '班级平均分'
                },
                labels: {
                    style: {
                        color: "#666",
                    },
                },
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table class="table-ts">',
                pointFormat: '<tr><td style="color:{series.color};padding:0;">{series.name}: </td>' +
                '<td style="padding:0;"><b>{point.y:.1f} 分</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true,
                enabled: false
            },
            plotOptions: {
                column: {
                    pointPadding: 0.05,
                    borderWidth: 0,
                    groupPadding: 0.001,
                    dataLabels: {         //显示顶部数值
                        enabled: true, // dataLabels设为true
                        y: -20,
                        style: {
                            color: '#333',
                            fontSize: '12px'
                        }
                    }
                }
            },
            series: [{
                name: '全年级',
                data: [${gradeAvg}],
                visible: true
            }
                <c:forEach items="${teamList}" var="it">
                ,
                {
                    name: '${it.teamName}${it.averageScore == null ? " (缺考)" : ""}',
                    data: [${it.averageScore}],
                    <c:if test="${it.teamId eq teamId}">
                    visible: true
                    </c:if>
                    <c:if test="${it.teamId ne teamId}">
                    visible: false
                    </c:if>
                }
                </c:forEach>
            ]
        });
    });
</script>
<body>
<div class="title-count white">
    <div class="topic-option">
    <span>
        <a href="javascript:void(0);">整卷统计</a>
        <a href="javascript:void(0);">答题统计</a>
        <a href="javascript:void(0);">单题分析</a>
    </span>

        <p style="float: right;" class="btn_link">
            <a class="a3" href="javascript:void(0)"
               onclick="mark('${param.examIdString}','${param.paperId}','${param.taskId}','${status}')">生成统计</a>
            <a class="a4"
               href="${pageContext.request.contextPath}/exampublish/publishManagerIndex?dm=SHI_JUAN_GUAN_LI&relateId=${param.relateId}&gradeId=${param.gradeId}"><i
                    class="fa fa-reply"></i> 返回 </a>
        </p>
    </div>
    <div class="title-content" style="display: none;">
        <div class="head">班级排名成绩表</div>
        <div id="container3" style="height:500px;"></div>
        <div class="wxts"><p></p>温馨提示：点击下方色块按钮即可显示或者隐藏相应柱状图</div>
        <div class="ranking">
            <div class="publish">
                <p>年级平均分</p>
                <span>${gradeAvg}</span>
            </div>
            <div class="publish">
                <p>本班平均分</p>
                <span>${teamAvg}</span>
            </div>
            <div class="publish">
                <p>年级排名</p>
                <span>${gradeRank}</span>
            </div>
            <div class="clear"></div>
            <div class="wxts"><p></p>温馨提示：平均分为总分除以作答人数</div>
            <div class="clear"></div>
            <div style="width:47%;float:left">
                <div class="head">班级排名成绩表</div>
                <table border="0" cellspacing="0" cellpadding="0" style="width: 100%;">
                    <thead>
                    <tr>
                        <th>名次</th>
                        <th>班级</th>
                        <th>平均分</th>
                        <th>应答人数</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="it">
                        <tr>
                            <td>
                                <c:if test="${!empty it.averageScore}">

                                    ${it.rank}
                                </c:if>
                                <c:if test="${empty it.averageScore}">

                                    -
                                </c:if>
                            </td>
                            <td>${it.teamName}
                                <c:if test="${empty it.averageScore}">

                                    <font color="red">(缺考)</font>
                                </c:if>

                            </td>
                            <td>${it.averageScore}
                                <c:if test="${empty it.averageScore}">

                                    -
                                </c:if>

                            </td>
                            <td>${it.studentCount}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div style="width:47%;float:left;margin-left:4%">
                <div class="head">班级成绩表</div>
                <table border="0" cellspacing="0" cellpadding="0" style="width: 100%;">
                    <thead>
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>成绩</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${svos}" var="vo">
                        <tr>

                            <td>${vo.number}</td>
                            <td>${vo.name}</td>

                            <td>
                                <c:if test="${vo.score ge 0}">
                                    ${vo.score}
                                </c:if>
                                <c:if test="${vo.score lt 0}">
                                    <font color="red">缺考</font>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <div class="title-content" style="display:none">
        <div class="tj"></div>
        <div class="head">题目应答情况统计</div>
        <table border="0" cellspacing="0" cellpadding="0">
            <thead>
            <tr>
                <th>序号</th>
                <th>题型</th>
                <th>作答人数</th>
                <th>正答数</th>
                <th>正答率/得分率</th>
                <th>完成率</th>
                <!--  <th>操作</th> -->
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty eqs}">
                <tr>
                    <td colspan="7">
                        暂无数据
                    </td>
                </tr>
            </c:if>
            <c:forEach items="${eqs}" var="vo" varStatus="index">
                <tr>
                    <td>${index.count}</td>
                    <td>${vo.questionType}</td>
                    <td>${vo.answerCount}</td>
                    <td>${vo.rightAnswerCount}</td>
                    <td><fmt:formatNumber type="number" value="${vo.rightRate}" maxFractionDigits="0"/>%</td>
                    <td><fmt:formatNumber type="number" value="${vo.finishRate}" maxFractionDigits="0"/>%</td>
                  
                        <%-- <td><a href="${pageContext.request.contextPath}/statistic/single?examIdString=${param.examIdString}&relateId=${param.relateId}&taskId=${param.taskId}&questionUuId=${vo.questionUuid}&paperId=${param.paperId}&num=${index.count}">单题统计</a></td> --%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="title-content" style="display: none;">
        <div class="subject-choice">
            <div class="subject" style="border-top: none;">题目</div>
            <div class="topic">
                <c:forEach items="${qList}" var="item" varStatus="index">
                    <a href="javascript:void(0);"
                       onclick="toptj(this,'${item.questionUuid}','${item.pos}')">${item.pos}</a>
                </c:forEach>
            </div>

        </div>
        <div id="top">
        </div>
    </div>

    <div class="title-content" style="display:none">
        <div class="ts_2"></div>
        <div class="ts_1"></div>
    </div>
    <div class="title-content" style="display:none">
        <div class="jdt">
            <p class="title">统计生成中……请稍后！</p>
            <div class="jdt_tu"><p style="width:0%"></p><span>0%</span></div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var timer1;
    var timer;
    function toptj(obj, qUuid, num) {
        $(".topic a").removeClass("choice");//移除样式
        $(obj).addClass("choice");//添加样式
        var url = "${pageContext.request.contextPath}/statistic/single?examIdString=${param.examIdString}&relateId=${param.relateId}&taskId=${param.taskId}&questionUuId=" + qUuid + "&paperId=${param.paperId}&num=" + num;
        var $requestdata = {}
        $.ajax({
            url: url,
            type: "POST",
            data: {},
            async: false,
            success: function (data) {
                $('#top').html("").html(data);

            }
        });
    }
    $(function () {
        var i = isStatisticsSuccess(${param.taskId}, false);
        if (i != 1 && i != '') {
            $('.title-content').hide();
            $('.title-content').eq(4).show();
            var i = 30, w = 0, w1 = 0;
            var timer;
            var speed = 1500;
            var go = function () {
                w = (i + 1) + '%';
                w1 = (i + 1 - 5) + '%';
                $(".jdt_tu p").css("width", w);
                $(".jdt_tu span").text(w);
                $(".jdt_tu span").css('left', w1)
                if (w == "99%") {
                    speed = 9999999;
                    window.clearInterval(timer); //进度为100时清除定时器
                }
                if (w == "100%") {
                    window.clearInterval(timer); //进度为100时清除定时器
                }
                i = i + 1;
            }
            timer = window.setInterval(go, speed);
            timer1 = setInterval("isStatisticsSuccess(${param.taskId}, true)", 5000);
        } else {
            $('.topic a').eq(0).click();
            $(".topic-option span a").eq(0).addClass("statistics");
            $.ajax({
                url: "${pageContext.request.contextPath}/statistic/paperJson",
                data: {
                    "examStringId": '${param.examIdString}',
                    "paperId": '${param.paperId}',
                    "relateId": '${param.relateId}'
                },
                success: function (data) {
                    // $.success("正在进行统计，请稍后查看统计信息!");
                    data = eval("(" + data + ")");
                    $(function () {
                        var i = 0, j = 0;
                        j = data.grade.length;
                        i = Math.ceil(j / 10);
                        for (var l = 0; l < j; l++) {
                            if (data.rank[l] === 0 || typeof(data.rank[l]) == "undefined") {
                                data.rank[l] = '-';
                            }
                        }

                        for (var k = 0; k < i; k++) {
                            k1 = 1 + 10 * k;
                            k2 = 10 * (k + 1);
                            k3 = k2 - 1;
                            if (i - k == 1) {
                                $("<div class='head'>" + k1 + "-" + j + "正答率/得分率对比图</div><div id='container_" + k + "' class='container' style='height:500px;width:80%;margin:0 auto'></div>").appendTo($(".tj"));
                            } else {
                                $("<div class='head'>" + k1 + "-" + k2 + "正答率/得分率对比图</div><div id='container_" + k + "' class='container' style='height:500px;width:80%;margin:0 auto'></div>").appendTo($(".tj"));
                            }
                            $(window).resize(function(){
        	    	        	w=$("body").width();
        	    	        	 $(".tj .container").width(0.8*w);
        	    	        })
                            $('#container_' + k).highcharts({
                                chart: {
                                    type: 'column'
                                },
                                title: {
                                    text: ''
                                },
                                subtitle: {
                                    text: ''
                                },
                                credits: {
                                    enabled: false // 禁用版权信息
                                },

                                exporting: {
                                    enabled: false//关闭设置按钮
                                },
                                xAxis: {
                                    /* title: {
                                     text: '题号<br>年级排名',
                                     align: 'high',
                                     x:100,
                                     offset: -10,
                                     style:{
                                     color: "#0464c8"
                                     }
                                     }, */
                                    categories: [
                                        '第' + k1 + '题<br>' + data.rank[k1 - 1] + '',
                                        '第' + (k1 + 1) + '题<br>' + data.rank[k1] + '',
                                        '第' + (k1 + 2) + '题<br>' + data.rank[k1 + 1] + '',
                                        '第' + (k1 + 3) + '题<br>' + data.rank[k1 + 2] + '',
                                        '第' + (k1 + 4) + '题<br>' + data.rank[k1 + 3] + '',
                                        '第' + (k1 + 5) + '题<br>' + data.rank[k1 + 4] + '',
                                        '第' + (k1 + 6) + '题<br>' + data.rank[k1 + 5] + '',
                                        '第' + (k1 + 7) + '题<br>' + data.rank[k1 + 6] + '',
                                        '第' + (k1 + 8) + '题<br>' + data.rank[k1 + 7] + '',
                                        '第' + (k1 + 9) + '题<br>' + data.rank[k1 + 8] + '',
                                    ],
                                    crosshair: true
                                },
                                yAxis: {
                                    min: 0,
                                    title: {
                                        text: '正答率/得分率'
                                    },
                                    labels: {
                                        formatter: function () {
                                            return this.value + '%';//y轴加上%
                                        },
                                        style: {
                                            color: "#666",
                                        },
                                    },
                                },
                                tooltip: {
                                    headerFormat: '<span style="font-size:10px">{point.key}</span><table style="border:none;">',
                                    pointFormat: '<tr>' +
                                    '<td style="padding:0;border:none;"><b>{point.y:f} %</b></td></tr>',
                                    footerFormat: '</table>',
                                    shared: true,
                                    useHTML: true,
                                },
                                plotOptions: {
                                    column: {
                                        pointPadding: 0.1,
                                        borderWidth: 0,
                                        groupPadding: 0.08,
                                        dataLabels: {         //显示顶部数值
                                            enabled: true, // dataLabels设为true
                                            formatter: function () {
                                                return this.y;  //返回百分比
                                            },
                                            y: -20,
                                            style: {
                                                color: '#333',
                                                fontSize: '12px'
                                            }
                                        }
                                    }

                                },
                                series: [{
                                    name: '年级',
                                    data: data.grade.slice(10 * k, k2),
                                }, {
                                    name: '本班',
                                    data: data.team.slice(10 * k, k2),
                                }]
                            });
                        }
                    })

                }
            });
        }


    });

    //判断统计任务状态 每5秒检测一次状态。
    function isStatisticsSuccess(ownerId, async) {
        var flag;
        //定时去检查状态。
        $.ajax({
            async: async,
            type: "post",
            url: "${pageContext.request.contextPath}/statistic/checkStatisticsTaskState",
            data: {"ownerId": ownerId},
            success: function (data) {
                flag = data;
                if (data == 1) {
                    clearInterval(timer1);
                    if (async) {
                        window.location.reload();
                    }
                }
            }
        });
        return flag;
    }

    function mark(examId, paperId, ownerId, flag) {
        if (!$('.title-content').eq(4).is(":hidden")) {
            alert("正在统计请稍后");
            return false;
        }
        //点击统计启动定时器
        timer1 = setInterval("isStatisticsSuccess(" + ownerId + ", true)", 5000, ownerId);
        var i = 0, w = 0, w1 = 0;
        var speed = 1500;
        var go = function () {
            w = (i + 1) + '%';
            w1 = (i + 1 - 5) + '%';
            $(".jdt_tu p").css("width", w);
            $(".jdt_tu span").text(w);
            $(".jdt_tu span").css('left', w1)
            if (w == "99%") {
                speed = 9999999;
                window.clearInterval(timer); //进度为100时清除定时器
            }
            if (w == "100%") {
                window.clearInterval(timer); //进度为100时清除定时器
            }
            i = i + 1;
        }

        timer = window.setInterval(go, speed);
        $('.title-content').hide();
        $('.title-content').eq(4).show();

        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/statistic/index",
            data: {"examId": examId, "paperId": paperId, "ownerId": ownerId},
            beforeSend: function () {

            },
            success: function (data) {
                if (data == 1) {
                    if (time1 != null) {
                        clearInterval(timer1);
                    }
                    window.location.reload();
                }
            }
        });

    }

    //    function loading() {
    //        var i = 0, w = 0, w1 = 0;
    //        var timer;
    //        var speed = 1000;
    //        var go = function () {
    //            w = (i + 1) + '%';
    //            w1 = (i + 1 - 5) + '%';
    //            $(".jdt_tu p").css("width", w);
    //            $(".jdt_tu span").text(w);
    //            $(".jdt_tu span").css('left', w1)
    //            if (w == "99%") {
    //                speed = 9999999;
    //                window.clearInterval(timer); //进度为100时清除定时器
    //            }
    //            if (w == "100%") {
    //                window.clearInterval(timer); //进度为100时清除定时器
    //            }
    //            i = i + 1;
    //        }
    //    }

</script>

</body>
</html>