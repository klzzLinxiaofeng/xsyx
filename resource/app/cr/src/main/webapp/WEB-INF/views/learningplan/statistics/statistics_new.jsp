<%@ page language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/res/qyjx/css/statistics/dxa_statistics.css">
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/qyjx/css/statistics/a3-3.css">--%>
    <script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
    <meta name="viewport" content="user-scalable=no">
    <meta name="viewport" content="initial-scale=1,maximum-scale=1">
    <title>导学案统计</title>
</head>
<body>
<div class="a3-3" style="overflow: hidden;">
    <!--    头部                  -->
    <div class="a3-3-top">
        <span class="fl banji">导学案统计 >> ${learningPlan.title} >> ${teamName}</span>
        <span class="fr anniu">
            <a href="javascript:void(0)" class="btn-blue mgr10" onclick="exportExcel();">导出数据</a>
            <c:if test="${!empty lpTaskExamUnitList }">
                <a href="javascript:void(0)" class="btn-lightGray mgr10" onclick="tj();">刷新统计</a>
            </c:if>
            <a href="javascript:void(0)" class="btn-lightGray">返回</a>
        </span>
    </div>
    <!-- 描点 -->
    <div class="sz_miao">
        <a href="#wcqk" class="on">完成情况</a>
        <a href="#dttj">答题统计</a>
        <a href="#dtfx">单题分析</a>
    </div>
    <!-- 回到顶部 -->
    <a href="javascript:void(0)" class="go_top"></a>
    <!--    下面是主要统计区域                  -->
    <div class="a3-3-content">
        <div class="select_mk">
            <p class="m_left">*选择统计模块：</p>
            <div class="m_select">
                <c:if test="${fn:length(unitList)<=3}">
                    <a href="javascript:void(0)" class="all on">全部</a>
                </c:if>
                <c:if test="${fn:length(unitList)>3}">
                    <a href="javascript:void(0)" class="all">全部</a>
                </c:if>
                <c:forEach items="${unitList}" var="item" varStatus="xh">
                    <c:choose>
                        <c:when test="${xh.index<3}">
                            <a href="javascript:void(0)" data-unitId="${item.id}" class="on">${item.title}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:void(0)" data-unitId="${item.id}" class="">${item.title}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <div class="clear"></div>
        </div>
        <div id="content">
            <%--<!--完成情况 star-->--%>
            <%--<!--完成情况 end-->--%>
            <%--<!--答题统计 start-->--%>
            <%--<!--答题统计 end-->--%>
            <%--<!--单题分析 start-->--%>
            <%--<!--单题分析 end-->--%>
        </div>
    </div>
</div>
<!-- 统计失败了 -->
<div class="tj_fail" style="display:none">
    <p>统计失败了啦~~</p>
</div>
<!-- 五分钟内只能一次 -->
<div class="only_one" style="display:none"><p>统计在五分钟之内只能执行一次请稍后再试</p></div>
<!-- 重新统计 -->
<div class="again_tj" style="display:none"><p>重新统计需要等待较长时间统计后如果有数据改动（如学生提交答卷、重新打分等）才进行重新统计是否继续？</p></div>
<!-- 正在统计中 -->
<div class="tjing" style="display:none">
    <p class="word"><span>统计进行中</span>，请稍候</p>
    <div class="jdt_tu"><p style="width:0%"></p><span>0%</span></div>
</div>
<!-- 统计完成 -->
<div class="tj_success" style="display:none">
    <p class="word"><span>统计成功</span></p>
    <div class="jdt_tu"><p style="width:100%"></p><span style="left:96%">100%</span></div>
</div>
<!-- 点击个人查看导学案完成进度 -->
<div class="more_div" style="display:none;">
    <!--    头部                  -->
    <div class="a3-3-top">
        <span class="fl banji">完成进度-三年级(1)班</span>
        <span class="fr anniu">
            <a href="javascript:void(0)" class="btn-lightGray" onclick="back_current();">返回</a>
        </span>
    </div>
    <div class="swiper-slide">
        <div class="bjwcl_pmb dxa_wcqk">
            <p class="title"><span style="color:#2da1f8">赵志刚</span>的完成情况</p>
            <table class="table" id="table_5">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>学习任务</th>
                    <th>完成状态</th>
                    <th>完成时间</th>
                </tr>
                </thead>
                <tbody id="tbody_status">
                <tr>
                    <th>1</th>
                    <th>导学案</th>
                    <th>完成</th>
                    <th>00：50</th>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <!-- 点击查看个人试卷做的情况 -->
    <div class=" stxq" style="display:none">
        <iframe src="" allowFullScreen marginheight="0" marginwidth="0"
                frameborder="0" scrolling="yes" style="width:100%;">

        </iframe>
    </div>
</div>
</body>
<script type="text/javascript">
    //导出数据
    function exportExcel() {
        var unitIdStr = "";
        $(".m_select a").each(function (index) {
            if ($(this).hasClass("on") && !$(this).hasClass("all")) {
                unitIdStr += $(this).attr("data-unitId") + ",";
            }
        });
        unitIdStr = unitIdStr.substring(0, unitIdStr.length - 1);
        var taskId = ${param.taskId};
        window.location.href = "${pageContext.request.contextPath}/statistics/lp/export?taskId=" + taskId + "&unitIds=" + unitIdStr;
    }

    //根据模块选择获取数据
    function getData() {
        var loading = layer.load(0);
        var unitStr = '';
        var unitIdStr = "";
        $(".m_select a").each(function (index) {
            if ($(this).hasClass("on") && !$(this).hasClass("all")) {
                unitIdStr += $(this).attr("data-unitId") + ",";
                unitStr += $(this).text() + "、";
            }
        });
        unitStr = unitStr.substring(0, unitStr.length - 1);

        $.ajax({
            url: "${pageContext.request.contextPath}/statistics/lp/tj/getData",
            type: 'GET',
            async: false,
            data: {
                "taskId": ${param.taskId},
                "unitIdStr": unitIdStr,
            },
            success: function (data) {
                $('#content').html(data);
                $('#p_unitStr').text(unitStr);
                setTimeout(function () {
                    layer.closeAll();
                }, 1000);
            }
        });
        layer.close(loading);
    }

    $(window).resize(function () {
        var swiper_height = $(window).height() - $('.a3-3-top').height();
        $('.stxq iframe').height(swiper_height - 5)
    })
    $(function () {
        var swiper_height = $(window).height() - $('.a3-3-top').height();
        $('.stxq iframe').height(swiper_height - 5)
        getData();
        // 描点点击
        $('.sz_miao a').click(function (event) {
            $('.sz_miao a').removeClass('on');
            $(this).addClass('on')
        });
        // 回到顶部的显示隐藏跟的点击
        $('.go_top').hide();        //隐藏go to top按钮
        $(window).scroll(function () {
            if ($(this).scrollTop() > 500) {
                $('.go_top').fadeIn();
            } else {
                $('.go_top').fadeOut();
            }
        });
        $('.go_top').click(function () {
            $('html ,body').animate({scrollTop: 0}, 300);
            return false;
        });
        // 选择统计模块
        $('body').on('click', '.select_mk .m_select a', function () {
            if ($(this).hasClass('all')) {
                if ($(this).hasClass('on')) {

                } else {
                    $('.select_mk .m_select a').addClass('on')
                }
            } else {
                if ($(this).hasClass('on')) {
                    $('.select_mk .m_select a').removeClass('on')
                    $(this).addClass('on')
                } else {
                    $(this).addClass('on');
                    if ($('.select_mk .m_select a').length === $('.select_mk .m_select a.on').length + 1) {
                        $('.select_mk .m_select a.all').addClass('on')
                    }
                }
            }
            getData();
        })
        //柱状图颜色
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
        $(".container").css("height", '400px');
    })

    function preview_paper(id) {
        $('.more_div .banji').text('作答情况');
        var taskId =${param.taskId};
        var unitIdStr = $('#paperUnitIds').val();

        $('.stxq iframe').attr('src', '/cr/statistics/score/detail/new?taskId=' + taskId + '&userId=' + id + '&unitIds=' + unitIdStr);
        $(".more_div").show();
        $(".more_div .stxq").show();
        $('.more_div .bjwcl_pmb').hide();
        $(".a3-3").hide();
    }

    function check_wcqk(obj) {
        $(".more_div .banji").text("完成进度 - ${teamName}");
        $('.more_div .bjwcl_pmb').hide();
        $(".more_div .bjwcl_pmb.dxa_wcqk tbody").empty();

        $(".more_div .bjwcl_pmb.dxa_wcqk span").text($(obj).children('p').eq(0).text());
        var html = "";
        var i = 0;
        var status = $(obj).attr("data-status").split(',');
        var time = $(obj).attr("data-time").split(',');
        $(".m_select a").each(function (index) {
            if ($(this).hasClass("on") && !$(this).hasClass("all")) {
                if (status[i] == 1) {
                    html += " <tr><th>" + (i + 1) + "</th><th>" + $(this).text() + "</th><th>完成</th><th>" + time[i] + "</th></tr>";
                } else {
                    html += " <tr><th>" + (i + 1) + "</th><th>" + $(this).text() + "</th><th>未完成</th><th></th></tr>";
                }

                i++;
            }
        });

        $('#tbody_status').append(html);

        $(".more_div").show();
        $(".more_div .bjwcl_pmb.dxa_wcqk").show();
        $(".a3-3").hide();
    }

    //查看更多以后返回
    function back_current() {
        $('.stxq').hide();
        $(".more_div").hide();
        $(".more_div .bjwcl_pmb").hide();
        $(".a3-3").show();
    }

    function tj() {
        var stId =${stId};
        if (stId == 0) {
            var val = {};
            val.objectId =${param.taskId};
            val.type = 4;
            val.stId = stId;
            $.ajax({
                url: "${pageContext.request.contextPath}/statistics/paper/tj",
                type: "POST",
                data: val,
                async: false,
                success: function (data) {
                    go1();
                    timer = window.setInterval(go, speed);
                    timer1 = window.setInterval("isStatisticsSuccess(" + data + ")", 2000);
                }
            });
        } else {
            layer.open({
                type: 1,
                shade: false,
                area: ['460px', '255px'],
                title: '统计', //不显示标题
                content: $('.again_tj'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
                cancel: function () {
                    layer.close();
                },
                btn: ['确定', '取消'],//按钮
                btn1: function (index, layero) {
                    var val = {};
                    val.stId = stId;
                    $.ajax({
                        url: "${pageContext.request.contextPath}/statistics/check/five",
                        type: "POST",
                        data: val,
                        async: false,
                        success: function (data) {
                            if (data === 'success') {
                                val = {};
                                val.objectId =${param.taskId};
                                val.type = 4;
                                val.stId = stId;
                                $.ajax({
                                    url: "${pageContext.request.contextPath}/statistics/paper/tj",
                                    type: "POST",
                                    data: val,
                                    async: false,
                                    success: function (data) {
                                        go1();
                                        timer = window.setInterval(go, speed);
                                        timer1 = window.setInterval("isStatisticsSuccess(" + data + ")", 2000);
                                    }
                                });
                            } else {
                                layer.open({
                                    type: 1,
                                    shade: false,
                                    area: ['460px', '235px'],
                                    title: '统计', //不显示标题
                                    content: $('.only_one'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
                                    cancel: function () {
                                        layer.close();
                                    },
                                    btn: ['确定'],//按钮
                                    btn1: function (index, layero) {
                                        layer.close();
                                    }
                                });
                            }
                        }
                    });
                },
                btn2: function (index, layero) {
                    layer.close();
                }
            });
        }
    }

    var state =${go};
    var j = 0, w = 0, w1 = 0, timer, speed = 2000;
    if (state == 0) {
        go1();
        timer = window.setInterval(go, speed);
        timer1 = window.setInterval("isStatisticsSuccess(${stId})", 2000);
    }

    function go() {
        w = (j + 1) + '%';
        w1 = (j + 1 - 5) + '%';
        console.log(w)
        $(".tjing .jdt_tu p").css("width", w);
        $(".tjing .jdt_tu span").text(w);
        $(".tjing .jdt_tu span").css('left', w1)
        if (w == "96%") {
            speed = 9999999;
            window.clearInterval(timer); //进度为100时清除定时器
        }
        if (w == "100%") {
            window.clearInterval(timer); //进度为100时清除定时器
        }
        j = j + 5;

    }

    function go1() {
        layer.open({
            type: 1,
            shade: false,
            area: ['510px', '255px'],
            title: '统计', //不显示标题
            content: $('.tjing'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
            cancel: function () {
                layer.close();
            },
        });
    }

    function isStatisticsSuccess(id) {
        var flag;
        //定时去检查状态。
        $.ajax({
            async: false,
            type: "post",
            url: "${pageContext.request.contextPath}/statistics/check/tj",
            data: {"stId": id},
            success: function (data) {
                flag = data;
                if (data == 1) {
                    layer.open({
                        type: 1,
                        shade: false,
                        area: ['510px', '255px'],
                        title: '统计', //不显示标题
                        closeBtn: 0,
                        content: $('.tj_success'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
                        cancel: function () {
                            var taskId =${param.taskId};
                            var number = $('.a3-3-top ul li.select1').index() + 1;
                            window.location.href = "${pageContext.request.contextPath}/statistics/lp/tj/index?taskId=" + taskId + "&number=" + number;
                        },
                    });
                    var taskId =${param.taskId};
                    var number = $('.a3-3-top ul li.select1').index() + 1;
                    setTimeout('window.location.href="${pageContext.request.contextPath}/statistics/lp/tj/index?taskId=' + taskId + '&number=' + number + '"', 2000);
                }
            }
        });
        return flag;
    }

</script>
</html>