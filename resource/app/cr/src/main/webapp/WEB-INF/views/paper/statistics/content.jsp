<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--完成情况 star-->
<div class="mk finish-situation " style="display:block">
    <a name="wcqk"></a>
    <div class="mk_title">
        <p class="p1">完成情况</p>
        <p class="p2" id="p_unitStr">*已选择：<span>问题探究、课堂检测</span></p>
    </div>
    <!-- Swiper -->
    <div class="" style="width:1220px;margin:0 auto;max-width:100%">
        <div class="swiper-wrapper" style="display:block">
            <div class=" swiper_1" style="width:100%">
                <div class="first_d">
                    <div class="pm_div">
                        <ul>
                            <li class="l1">
                                <a href="javascript:">
                                    <p class="p2">年级排名</p>
                                    <p class="p1">${rank}</p>
                                </a>
                            </li>
                            <li class="l2">
                                <a href="javascript:">
                                    <p class="p2">年级完成率</p>
                                    <p class="p1">${gradeFinishRate}</p>
                                </a>
                            </li>
                            <li class="l3">
                                <a href="javascript:">
                                    <p class="p2">本班完成率</p>
                                    <p class="p1">${finishRateSelf}</p>
                                </a>
                            </li>
                            <li class="l4">
                                <a href="javascript:">
                                    <p class="p2">完成人数</p>
                                    <p class="p1">${peopleCount}</p>
                                </a>
                            </li>
                        </ul>
                        <div class="clear"></div>
                    </div>
                    <div class="first_one">
                        <div class="bjwcl_pmb">
                            <p class="title">班级完成率排名表</p>
                            <div class="table_1" id="table_1">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>名次</th>
                                        <th>班级</th>
                                        <th>完成率</th>
                                        <th>完成人数</th>
                                        <th>平均分</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${taskTeamVoList}" var="item" varStatus="xh">
                                        <tr>
                                            <td>${xh.index + 1}</td>
                                            <td>${item.teamName}</td>
                                            <td class="f_red">${item.finishRate}</td>
                                            <td>${item.totalOfCompleted}</td>
                                            <td>${item.averageScore}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="bjwcl_pmb xswcjd">
                            <div class="title">学生完成进度
                                <div class="wcrs fr">
                                    <p class="p1">
                                        已完成:<span class="ywc">${peopleCount}</span>/<span>${totalPeople}</span>人
                                    </p>
                                </div>
                            </div>

                            <div id="first_container_1" class="first_container_1">
                                <ul>
                                    <c:forEach items="${studentList}" var="item">
                                        <li onclick="check_wcqk(this)" data-status="${item.finishStatus}"
                                            data-time="${item.finishTime}">
                                            <p class="p_1">${item.studentName}</p>
                                            <c:choose>
                                                <c:when test="${item.personRate=='100%'}">
                                                    <div class="div_2">
                                                        <p class="p_2 all_ok_jdt" style="width:${item.personRate}"></p>
                                                    </div>
                                                    <p class="p_3 all_ok_wz">${item.personRate}</p>
                                                </c:when>
                                                <c:when test="${item.personRate=='0%'}">
                                                    <div class="div_2">
                                                        <p class="p_2 no_ok_jdt" style="width:${item.personRate}"></p>
                                                    </div>
                                                    <p class="p_3 no_ok_wz">${item.personRate}</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="div_2">
                                                        <p class="p_2 part_ok_jdt" style="width:${item.personRate}"></p>
                                                    </div>
                                                    <p class="p_3 part_ok_wz">${item.personRate}</p>
                                                </c:otherwise>
                                            </c:choose>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="swiper_2"></div>
        </div>
    </div>
</div>
<!--完成情况 end-->
<!--答题统计 start-->
<div class="mk answer-statistics">
    <a name="dttj"></a>
    <div class="mk_title">
        <p class="p1">答题统计</p>
    </div>
    <div class="pm_div">
        <ul>
            <li class="l5"><a href="javascript:"><p class="p2">年级平均分</p>
                <p class="p1">${gradeAvgScore}</p></a></li>
            <li class="l6"><a href="javascript:"><p class="p2">班级平均分</p>
                <p class="p1">${teamAvgScore}</p></a></li>
            <li class="l7"><a href="javascript:"><p class="p2">已提交人数</p>
                <p class="p1">${peopleCount}</p></a></li>
            <li class="l8"><a href="javascript:"><p class="p2">年级排名</p>
                <p class="p1">${rank}</p></a></li>
        </ul>
        <div class="clear"></div>
    </div>
    <div class="bjwcl_pmb xsdtck">
        <div class="title">学生答题情况
            <div class="wcrs fr">
                <p class="p1">
                    已完成:<span class="ywc">${submitPeoples}</span>/<span class="">${fn:length(studentScoreList)}</span>人
                </p>
            </div>
        </div>
        <div class="second_container_1">
            <ul>
                <c:forEach items="${studentScoreList}" var="item" varStatus="xh">
                    <c:choose>
                        <c:when test="${item.isFinished}">
                            <li>
                                <c:choose>
                                    <c:when test="${xh.index+1<10}">
                                        <span class="rank1">${xh.index+1}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="rank2">${xh.index+1}</span>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test="${xh.index+1==1}">
                                        <img src="${pageContext.request.contextPath}/res/images/rank1.png"/>
                                    </c:when>
                                    <c:when test="${xh.index+1==2}">
                                        <img src="${pageContext.request.contextPath}/res/images/rank2.png"/>
                                    </c:when>
                                    <c:when test="${xh.index+1==3}">
                                        <img src="${pageContext.request.contextPath}/res/images/rank3.png"/>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="${pageContext.request.contextPath}/res/images/rank.png"/>
                                    </c:otherwise>
                                </c:choose>
                                <p class="p1">
                                    <span class="name">${item.name}</span>
                                    <span class="score">${item.sumScore}分</span>
                                </p>
                                <p class="p2">用时<span>${item.timeStr}</span></p>
                                <a href="javascript:void(0)" onclick="preview_paper(${item.userId})"
                                   class="view_detail">点击查看</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="miss_exam">
                                <p class="p1"><span class="name">${item.name}</span><span class="score">0分</span></p>
                                <p class="p2">用时<span>00:00:00</span></p>
                                <a href="javascript:void(0)" class="view_detail">缺考</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="bjwcl_pmb xswcjd">
        <div class="title">本班题目得分率</div>
        <div class="second_container_2"/>
    </div>
</div>
<!--答题统计 end-->
<!--单题分析 start-->
<div class="mk single-analysis" style="margin-bottom:20px">
    <a name="dtfx"></a>
    <div class="mk_title">
        <p class="p1">单题分析</p>
    </div>
    <c:forEach items="${singleList}" var="single" varStatus="xh1">
        <div class="container_list bjwcl_pmb">
            <table class="table">
                <thead>
                <tr>
                    <th>题号</th>
                    <th>题目类型</th>
                    <th>分值</th>
                    <th>作答人数</th>
                    <th>错误率</th>
                    <th>平均分</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${single}" var="item" varStatus="xh">
                    <tr class="row_detail">
                        <td>${xh.index+1}</td>
                        <td>${item.questionType}</td>
                        <td>${item.fullScore}</td>
                        <td>${item.answerCount-item.emptyCount}/${item.answerCount}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.answerCount==0}">
                                    0%
                                </c:when>
                                <c:otherwise>
                                    <fmt:formatNumber
                                            value="${((item.answerCount-item.rightAnswerCount)/item.answerCount)*100}"
                                            pattern="##.#" minFractionDigits="1"></fmt:formatNumber>%
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${item.averageScore}</td>
                            <%--三个统计图按钮--%>
                        <td>
                            <button data-id="${item.id}" data-unitId="${unitIds[xh1.index]}"
                                    class="answer-distribution-button">答题分布
                            </button>
                            <button data-examId="${item.examId}" data-unitId="${unitIds[xh1.index]}"
                                    data-questionUuid="${item.questionUuid}"
                                    class="grade-contrast-button">年级对比
                            </button>
                            <button data-id="" class="single-details-button">题目详情</button>
                        </td>
                    </tr>
                    <%--题目详情--%>
                    <tr class="single-details">
                        <td colspan='7'>
                            <p class='title'>题目详情</p>
                            <ul>
                                    <%--用xh1.index，保证展示顺序是依照unitId的--%>
                                <c:forEach items="${singleDetails[xh1.index]}" var="node" varStatus="unitStatus">
                                    <c:forEach items="${node.childrens}" var="parentQuestion"
                                               varStatus="parentQuestionStatus">
                                        <c:choose>
                                            <c:when test="${!empty parentQuestion.childrens}">
                                                <c:forEach items="${parentQuestion.childrens}" var="question"
                                                           varStatus="questionStatus">
                                                    <c:if test="${question.obj.uuid == item.questionUuid}">
                                                        <li class="st">
                                                            <div class="timu">
                                                                <span> ${parentQuestion.obj.nodeOrder+1}、${parentQuestion.obj.content} </span>
                                                                <div class="timu-choose">
                                                                    <c:if test="${question.obj.uuid == item.questionUuid}">
                                                                        <c:if test="${parentQuestion.obj.questionType eq 'complex' }">
                                                                            <span> (${question.obj.nodeOrder+1})、${question.obj.content} </span><br>
                                                                        </c:if>
                                                                        <c:choose>
                                                                            <c:when test="${question.obj.questionType eq 'checkbox' || question.obj.questionType eq 'radio'}">
                                                                                <div>
                                                                                    <span class="order">(${question.obj.nodeOrder+1})</span>
                                                                                    <ul>
                                                                                        <c:forEach
                                                                                                items="${question.obj.answers}"
                                                                                                var="name1"
                                                                                                varStatus="status1">
                                                                                            <c:if test="${question.obj.uuid == item.questionUuid}">
                                                                                                <li>
                                                                                                <span class="xuanxiang"
                                                                                                      data-a="${status1.index}"></span>${name1}
                                                                                                </li>
                                                                                            </c:if>
                                                                                        </c:forEach>
                                                                                    </ul>
                                                                                </div>
                                                                            </c:when>
                                                                            <c:when test="${question.obj.questionType eq 'trueOrFalse' }">
                                                                                <ul>
                                                                                    <li><span class="xuanxiang"></span>×
                                                                                    </li>
                                                                                    <li><span class="xuanxiang"></span>√
                                                                                    </li>
                                                                                </ul>
                                                                            </c:when>
                                                                            <c:otherwise/>
                                                                        </c:choose>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                            <div class="jiexi">
                                                                <p class="j1">
                                                                    【答案】
                                                                    <span>${!empty parentQuestion.anwerList[questionStatus.index] ? parentQuestion.anwerList[questionStatus.index] : '无'}</span><br>
                                                                </p>
                                                                <p class="j2">
                                                                    【解析】
                                                                    <span>${!empty parentQuestion.exp[questionStatus.index] ? parentQuestion.exp[questionStatus.index] : '无'}</span><br>
                                                                </p>
                                                            </div>
                                                        </li>
                                                    </c:if>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <%--匹配大题目uuid--%>
                                                <c:if test="${parentQuestion.obj.uuid == item.questionUuid}">
                                                    <li class="st">
                                                        <div class="timu">
                                                            <span> ${parentQuestion.obj.nodeOrder+1}、${parentQuestion.obj.content} </span>
                                                            <div class="timu-choose">
                                                                <c:choose>
                                                                    <c:when test="${parentQuestion.obj.questionType eq 'checkbox' || parentQuestion.obj.questionType eq 'radio'||parentQuestion.obj.questionType eq 'multichoise'}">
                                                                        <ul>
                                                                            <c:forEach
                                                                                    items="${parentQuestion.obj.answers}"
                                                                                    var="name" varStatus="status">
                                                                                <li>
                                                                                        <span class="xuanxiang"
                                                                                              data-a="${status.index}"></span>${name}
                                                                                </li>
                                                                            </c:forEach>
                                                                        </ul>
                                                                    </c:when>
                                                                    <c:when test="${parentQuestion.obj.questionType eq 'trueOrFalse' }">
                                                                        <ul>
                                                                            <li><span class="xuanxiang"></span>×
                                                                            </li>
                                                                            <li><span class="xuanxiang"></span>√
                                                                            </li>
                                                                        </ul>
                                                                    </c:when>
                                                                    <c:otherwise/>
                                                                </c:choose>
                                                            </div>
                                                        </div>
                                                        <div class="jiexi">
                                                            <p class="j1">
                                                                【答案】
                                                                <span>${!empty parentQuestion.obj.correctAnswer ? parentQuestion.obj.correctAnswer : '无'}</span>
                                                            </p>
                                                            <p class="j2">
                                                                【解析】
                                                                <span>${!empty parentQuestion.obj.explanation ? parentQuestion.obj.explanation : '无'}</span>
                                                            </p>
                                                        </div>
                                                    </li>
                                                </c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:forEach>
</div>
<!--单题分析 end-->
<%--答题项分布饼图提示--%>
<div class="bt_ry" style="display: none">
    <p class="p1">统计项：<span class="s1">错误</span> &nbsp;&nbsp;作答人数：<span class="s2"></span>人 &nbsp;&nbsp;作答比例：<span
            class="s3">10</span>%</p>
    <div class="p2"></div>
</div>

<script>
    //单例模式引入highcharts
    if (!$.fn.highcharts) {
        var script = document.createElement("script");
        script.type = "text/javascript";
        script.src = "${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js";
        document.body.appendChild(script);
    }
    var question_list = '${questionList}';
    $(function () {
        $('.second_container_2').empty();
        if (question_list !== '') {
            question_list = eval("(" + question_list + ")");
            $.each(question_list, function (index, n) {
                var data = question_list[index];
                //答题统计的柱状图，每15题分割为一个统计图
                var categories_number, categories_rank, grade_number, team_number, k;
                k = Math.ceil(data.number.length / 15);
                var h = data.unitName;
                for (var i = 0; i < k; i++) {
                    var k1 = 15 * i;
                    var k2 = 15 * (i + 1);
                    var k3 = data.number.length;
                    categories_number = data.number.slice(k1, k2);
                    categories_rank = data.rank.slice(k1, k2);
                    grade_number = data.gradeRate.slice(k1, k2);
                    team_number = data.teamRate.slice(k1, k2);
                    var categories1 = categories_number;
                    var categories2 = categories_rank;
                    if (i === k - 1) {
                        $('<div class="container_list"><div class="title_center">' + (k1 + 1) + '-' + k3 + '题得分率对比图</div><div id="container_' + index + i + '" class="container" style="width:876px;margin: 0 auto"></div></div>').appendTo(".second_container_2")
                    } else {
                        $('<div class="container_list"><div class="title_center">' + (k1 + 1) + '-' + k2 + '题得分率对比图</div><div id="container_' + index + i + '" class="container" style="width:876px;margin: 0 auto"></div></div>').appendTo(".second_container_2")
                    }
                    $(".container").css("height", '400px');
                    $('#container_' + index + i).highcharts({
                        chart: {
                            borderRadius: 5,
                            alignTicks: false,
                            type: 'column',
                            spacing: [29, 50, 0, 0]
                        },
                        title: {
                            text: null
                        },
                        //图例
                        legend: {
                            align: 'right',
                            verticalAlign: 'top',
                            symbolHeight: 16,
                            symbolWidth: 16,
                            symbolRadius: 60,
                        },
                        exporting: {
                            enabled: false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示
                        },
                        credits: {
                            enabled: false
                        },
                        xAxis: [
                            {
                                type: "category",
                                categories: categories1,
                                useHTML: true,
                                title: {
                                    text: '题号',
                                    align: 'low',
                                    x: -60,
                                    y: -20
                                },
                                lineWidth: 0,
                                labels: {
                                    y: 25
                                },
                                tickWidth: 0,
                                gridLineWidth: 0
                            }, {
                                type: "category",
                                categories: categories2,
                                title: {
                                    text: '年级排名',
                                    align: 'low',
                                    x: -80,
                                    y: -21
                                },
                                lineWidth: 0,
                                labels: {
                                    y: 5,
                                    style: {
                                        color: '#1795ef',
                                        fontWeight: 'bold',
                                    }
                                },
                                tickWidth: 0,
                                linkedTo: 0
                            }
                        ],
                        yAxis: [
                            {
                                title: {
                                    "text": "正答率",
                                    align: 'high',
                                    rotation: 0,
                                    x: 45,
                                    y: -20
                                },
                                min: 0,
                                max: 100,
                                labels: {
                                    format: '{value} %'
                                },
                                gridLineDashStyle: 'Dash',
                                gridLineColor: '#d6d6d6'
                            }
                        ],
                        //数据点提示框
                        tooltip: {
                            headerFormat: '<span style="font-size:10px">第{point.key}题</span><table>',
                            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td><td style="padding:0"><b>{point.y:.1f} %</b></td></tr>',
                            footerFormat: '</table>',
                            shared: true,
                            useHTML: true
                        },
                        //针对不同类型图表的配置
                        plotOptions: {
                            column: {
                                pointPadding: 0.2,
                                borderWidth: 0
                            },
                            series: {
                                borderRadius: 7
                            }
                        },
                        //数据列
                        series: [
                            {
                                name: "年级",
                                data: grade_number
                            },
                            {
                                name: "本班",
                                data: team_number
                            }
                        ]
                    });
                }
            })
        }
        // 单题分析
        $(".row_detail").mouseenter(function () {
            $(".row_detail").children("td").children("button").css("visibility", "hidden")
            $(this).children("td").children("button").css("visibility", "visible")
        });
        // 点击答题分布按钮
        $(".row_detail .answer-distribution-button").mouseup(function () {
            // 关闭其他单题的统计图
            $(".statistical_chart").remove()
            $(".grade-contrast").remove()
            $(".single-details").css('display', 'none')

            // 获取当前单题的examQuestionId和unitId
            let examQuestionId = $(this).attr("data-id")
            let unitId = $(this).attr("data-unitId")
            $(this).parents(".row_detail")
                .after($("<tr class='statistical_chart'><td colspan='7'><p class='title'>答题项应答次数分布图</p><div id='container1' style='width:876px;margin: 0 auto'></div></td></tr>"))
            // 渲染答题分布统计图
            answerDistribution(examQuestionId, unitId)
        });
        // 点击年级对比按钮
        $(".row_detail .grade-contrast-button").mouseup(function () {
            // 关闭其他单题的统计图
            $(".statistical_chart").remove()
            $(".grade-contrast").remove()
            $(".single-details").css('display', 'none')
            let examId = $(this).attr("data-examId")
            let unitId = $(this).attr("data-unitId")
            let questionUuid = $(this).attr("data-questionUuid")
            // 传入this，方便插入
            let obj = this
            // 渲染年级对比统计图
            gradeContrast(examId, questionUuid, unitId, obj)
        });
        // 点击题目详情按钮
        $(".row_detail .single-details-button").mouseup(function () {
            // 关闭其他单题的统计图
            $(".statistical_chart").remove()
            $(".grade-contrast").remove()
            $(".single-details").css('display', 'none')
            // 显示或隐藏题目详情
            $(this).parents(".row_detail").next().toggle()


        });
        /**处理波浪线*/
        $("span").each(function () {
            var style = $(this).attr("style");
            if (style != undefined && style != "") {
                if (style.indexOf("symbol:waveline") != -1) {
                    $(this).addClass("waveline");
                }
            }
        })
        /*选项加载图*/
        var zm = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
        $(".xuanxiang").each(function () {
            var index = $(this).data("a");
            $(this).text(zm[index]);
        });
    });
    // 渲染答题分布统计图
    let answerDistribution = function (examQuestionId, unitId) {
        $.getJSON("${pageContext.request.contextPath}/statistics/tj/anwers?examQuestionId=" + examQuestionId + "&unitId=" + unitId, function (data) {
            var jsonArr = [];
            for (var key in data) {
                jsonArr.push([key, data[key].length])
            }
            //单题分析第一屏
            var color1 = ['#03a9f5', '#ff6a88', '#16b2eb', '#6e7cff', '#999999'];
            var color2 = ['#90caf8', '#fca599', '#2af598', '#fd83ec', '#999999'];
            // Radialize the colors
            Highcharts.getOptions().colors = Highcharts.map(color1, function (color) {
                var v = color;
                var j = 0;
                var color_end = '#fff';
                for (var i = 0; i < color1.length; i++) {
                    if (color1[i] == v) {
                        j = i;
                        color_end = color2[j]
                    }
                }
                return {
                    linearGradient: {x1: 0, y1: 1, y2: 0, x2: 0}, //横向渐变效果 如果将x2和y2值交换将会变成纵向渐变效果
                    stops: [
                        [0, color],
                        [1, color_end] // darken
                    ]
                };
            });
            $('#container1').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },
                title: {
                    text: ''
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                credits: {
                    enabled: false // 禁用版权信息
                },
                plotOptions: {
                    // pie: {
                    //     allowPointSelect: true,
                    //     cursor: 'pointer',
                    //     dataLabels: {
                    //         enabled: true,
                    //         format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    //         style: {
                    //             color: '#2a9ff7',
                    //             fontWeight: 'bold',
                    //             fontSize:'18px',
                    //             fontFamily:"微软雅黑",
                    //             textShadow: '0px 0px 0px #fff'
                    //         }
                    //     }
                    // },
                    series: {
                        cursor: 'pointer',
                        events: {
                            click: function (e) {
                                $('.bt_ry .p1 .s1').text(e.point.name);
                                $('.bt_ry .p1 .s2').text(e.point.y);
                                $('.bt_ry .p1 .s3').text(e.point.percentage.toFixed(1));
                                $('.bt_ry .p2').empty();
                                $('.bt_ry .p2').css({'height': 'auto', 'margin-top': '0'})
                                for (var key in data[e.point.name]) {
                                    $('<span>' + data[e.point.name][key] + '</span>').appendTo($('.bt_ry .p2'))
                                }
                                var index = layer.open({
                                    type: 1,
                                    shade: [0.01, '#fff'],
                                    shadeClose: true,
                                    closeBtn: 0,
                                    area: ['50%', '50%'],
                                    title: false, //不显示标题
                                    content: $('.bt_ry'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
                                });
                                layer.style(index, {
                                    backgroundColor: 'rgba(255,255,255,0)'
                                });
                                var height_1 = $('.bt_ry').height() - 60;
                                var height_2 = $('.bt_ry .p2').height();
                                if (height_1 > height_2) {
                                    var height_3 = (height_1 - height_2) / 2;
                                    $('.bt_ry .p2').css('margin-top', height_3)
                                } else {
                                    $('.bt_ry .p2').height(height_1);
                                    $('.bt_ry .p2').niceScroll({
                                        "styler": "fb",
                                        "cursorcolor": "#BDBDBD",
                                        "cursorwidth": "5"
                                    });
                                }

                            }
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: '作答',
                    innerSize: '70%',
                    data: jsonArr
                }],

            });
        })
    }
    // 渲染年级对比统计图
    let gradeContrast = function (examId, questionUuid, unitId, obj) {
        $.getJSON("${pageContext.request.contextPath}/statistics/new/tj/questionRate?examId=" + examId + "&questionUuid=" + questionUuid, function (data) {
            let start = "<tr class='grade-contrast'><td colspan='7'><p class='title'>年级各班平均分对比图</p><ul>"
            let end = "</ul><br></td></tr>"
            let content = ''
            data.forEach((item, index) => {
                let rank = ''
                let img = ''
                let info = ''
                let nowRank = index + 1
                if (nowRank < 10) {
                    rank = "<span class='rank1'>" + nowRank + "</span>"
                } else {
                    rank = "<span class='rank2'>" + nowRank + "</span>"
                }
                let path = '${pageContext.request.contextPath}'
                if (nowRank === 1) {
                    img = "<img src='" + path + "/res/images/rank1.png'/>"
                } else if (nowRank === 2) {
                    img = "<img src='" + path + "/res/images/rank2.png'/>"
                } else if (nowRank === 3) {
                    img = "<img src='" + path + "/res/images/rank3.png'/>"
                } else {
                    img = "<img src='" + path + "/res/images/rank.png'/>"
                }
                info = "<p class='p1'><span class='name'>" + item.name + "</span><span class='score'>" + item.avg + "分" + "</span></p>"
                // 拼接每个班的内容
                content = content + "<li>" + info + rank + img + "</li>"
            })
            // 各班平均分对比图
            let gradeContrast = start + content + end
            // 插入
            $(obj).parents(".row_detail").after($(gradeContrast))
        })
    }
</script>