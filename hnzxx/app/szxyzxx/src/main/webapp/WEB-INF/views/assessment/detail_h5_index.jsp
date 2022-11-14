<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
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
    <meta name="viewport" content="width=1920, user-scalable=no, target-densitydpi=device-dpi">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_h5.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/qyjx/plugin/percircle/percircle.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts-more.js"></script>
    <title>反馈详情</title>
</head>
<body style="background-color:#e6e6e6">
<div class="fkxq_top">
    <div class="f_left">
        <%--<img src="${pageContext.request.contextPath}/res/images/no_pic.jpg">--%>
            <img src="<avatar:avatar userId='${vo.teacherUserId}'></avatar:avatar>">
        <p><span>${vo.teacherName}</span>老师</p>
    </div>
    <div class="f_right">
        <div class="r_top">
            <c:if test="${vo.evType==2}">
                <p class="p0">${vo.subjectName}</p>
            </c:if>
            <p class="p1">${vo.gradeName}&nbsp${vo.teamName}</p>
            <p class="p2">评价人数：<span>${vo.scoredUserCount}</span>/${vo.totalUserCount}</p>
            <%--<c:if test="${vo.evType==2}">--%>
                <%--<p>--%>
                <%--<c:choose>--%>
                    <%--<c:when test="${vo.isValid}">--%>
                        <%--<span style="color:#4caf50">有效</span>--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--<span>无效</span>--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
                <%--</p>--%>
            <%--</c:if>--%>
            <p class="p3">
                <c:if test="${vo.evType==1}">
                第${vo.weekNumChinese}周（<fmt:formatDate value="${vo.startDate}" type="date" pattern="yyyy-MM-dd"/> -
                <fmt:formatDate value="${vo.finishDate}" type="date" pattern="yyyy-MM-dd"/>）
                </c:if>
                <c:if test="${vo.evType==2}">
                    <fmt:formatDate value="${vo.startDate}" type="date" pattern="yyyy/MM/dd"/>（星期${vo.weekDay}）
                </c:if>
            </p>
        </div>
        <div class="r_bottom">
            <div class="r_b_left" >
                <c:forEach items="${vo.avgScores}" var="avgScore" varStatus="status">
                    <div class="c100 p<fmt:formatNumber type="number" value="${avgScore*10}" maxFractionDigits="0"></fmt:formatNumber>" >
                        <p class="p1">${items[status.index].taskItemName}</p>
                        <p class="p2">${avgScore}</p>
                        <div class="slice">
                            <div class="bar"></div>
                            <div class="fill"></div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="r_b_right">
                <div class="c100 p<fmt:formatNumber type="number" value="${vo.score*10}" maxFractionDigits="0"></fmt:formatNumber> green" id="greencircle">
                    <p class="p1">综合评分</p>
                    <p class="p2">${vo.score}</p>
                    <div class="slice">
                        <div class="bar"></div>
                        <div class="fill"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="fkxq_bottom">
    <div class="tjt">
        <div id="container" class='container' style="height:500px;width:1500px;"></div>
    </div>
    <div class="fgf">
        <p class="p1"></p>
        <p class="p2"></p>
    </div>
    <div class="fk_pl" style="margin-bottom: 150px;">
        <ul id="list_content"></ul>
        <div class="page_div">
            <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                <jsp:param name="id" value="list_content" />
                <jsp:param name="url" value="${ctp}/assessment/teacher/detail/h5?sub=list" />
                <jsp:param name="pageSize" value="${page.pageSize}" />
            </jsp:include>
            <div class="clear"></div>
        </div>
    </div>
</div>
<a class="go_top" href="javascript:void(0)" onclick="goTop()"></a>
<script>
	//当前班级和当前周高亮
	var teamName="${vo.teamName}";
	var weekNum=${vo.weekNum};
    $(function () {
        list();
        $(".r_b_left .c100").each(function (index) {
            $(this).attr('id', index + '_bluecircle')
        });

        var val = {};
        val.termCode ="${vo.termCode}";
        val.title ="${vo.teacherName}";
        val.type =${vo.evType};

        


        $.ajax({
            url: "${ctp}/assessment/statistics/aptsData",
            type: "POST",
            data: val,
            async: false,
            success: function (data) {
//	     	var data={"everyWeek":[[],[],[],[],[],[],[],[],[{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.2,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897},{"avgScore":9.5,"teacherName":"李谷二","teamName":"高一(1)班","teacherId":1898}],[],[],[{"avgScore":10,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897}],[{"avgScore":9.8,"teacherName":"李谷一","teamName":"高一(1)班","teacherId":1897}],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[],[]],"week":[{"teamName":"高一(1)班","list":[0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,9.2,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0],"teacherName":"李谷一"}]}
                console.log(data);
                data = eval("(" + data + ")");
                showChart(data);
// 	    	data.startDate
//                 $(".container_1").remove();
//                 $(".container_2").empty();
//                 init(data)
            }
        });
    })

    function list() {
        var id = "list_content";
        var url = "/assessment/teacher/detail/h5?sub=list";
        var data = new Object();
        data.id =${vo.id};
        myPagination(id, data, url);
    }

    function showChart(data) {
        // var data={"week":[{"teamName":"高一(1)班","list":[0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,9.2,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0],"teacherName":"李谷一"},{"teamName":"高一(2)班","list":[5.6,9.7,8.3,7.6,9.8,9.5,7.6,0.0,9.2,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0],"teacherName":"李谷2"},{"teamName":"高一(3)班","list":[0.0,5.6,9.7,8.3,7.6,9.8,9.5,7.6,9.2,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0],"teacherName":"李谷3"},{"teamName":"高一(4)班","list":[0.0,5.6,9.7,8.3,7.6,9.8,9.5,7.6,9.2,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0],"teacherName":"李谷4"}]}
        var zc = ['第一周 ', '第二周 ', '第三周', '第四周', '第五周', '第六周', '第七周', '第八周', '第九周', '第十周', '第十一周', '第十二周', '第十三周', '第十四周', '第十五周', '第十六周', '第十七周', '第十八周', '第十九周', '第二十周', '第二十一周', '第二十二周', '第二十三周', '第二十四周', '第二十五周', '第二十六周', '第二十七周', '第二十八周', '第二十九周', '第三十周', '第三十一周', '第三十二周', '第三十三周', '第三十四周', '第三十五周', '第三十六周', '第三十七周', '第三十八周', '第三十九周', '第四十周', '第四十一周', '第四十二周', '第四十三周', '第四十四周', '第四十五周', '第四十六周', '第四十七周', '第四十八周', '第四十九周', '第五十周', '第五十一周', '第五十二周']
        var line_data = [];
        for (var m = 0; m < data.week.length; m++) {
        	teamName
        	if(data.week[m].teamName==teamName){
        		var new_data1={name: data.week[m].teamName+'<br>'+data.week[m].teacherName,data:data.week[m].list}
        	}else{
         		var new_data1={name: data.week[m].teamName+'<br>'+data.week[m].teacherName,data:data.week[m].list,dashStyle: 'ShortDash'}
        	}
            //var new_data1 = {name: data.week[m].teamName + '<br>' + data.week[m].teacherName, data: data.week[m].list}
            line_data.push(new_data1);
        }
        if (data.week.length > 0) {
            console.log(data.week.length)
            var km_num1 = data.week[0].list.length;
            var week = zc.slice(0, km_num1);
            $('#container').highcharts({
            	chart: {
                    panning: true,
                   pinchType: 'x',
                   type: 'line',
                   marginRight:50,
                   resetZoomButton: {
                       position: {
                           y: -1000 
                       }
                   },
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
                    useHTML: true,
                    followTouchMove:false
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
                if (km_num1 > 11) {
                    c.xAxis[0].minRange = 10.5;
                    if(weekNum>7){
                    	console.log(weekNum)
                    	c.xAxis[0].setExtremes(weekNum-5,weekNum+5);
                    }else{
                    	c.xAxis[0].setExtremes(0,10);
                    }
                    c.xAxis[0].addPlotBand({
                        from:weekNum-1.5,
                        to: weekNum-0.5,
                        color: '#FCFFC5',
                        id: 'plot-band-1'
                    });
                }
            });
        }

    }
    /**
     * 回到页面顶部
     * @param acceleration 加速度
     * @param time 时间间隔 (毫秒)
     **/
    function goTop(acceleration, time) {

        acceleration = acceleration || 0.1;
        time = time || 16;

        var x1 = 0;
        var y1 = 0;
        var x2 = 0;
        var y2 = 0;
        var x3 = 0;
        var y3 = 0;

        if (document.documentElement) {
            x1 = document.documentElement.scrollLeft || 0;
            y1 = document.documentElement.scrollTop || 0;
        }
        if (document.body) {
            x2 = document.body.scrollLeft || 0;
            y2 = document.body.scrollTop || 0;
        }
        var x3 = window.scrollX || 0;
        var y3 = window.scrollY || 0;



        // 滚动条到页面顶部的水平距离
        var x = Math.max(x1, Math.max(x2, x3));
        // 滚动条到页面顶部的垂直距离
        var y = Math.max(y1, Math.max(y2, y3));

        // 滚动距离 = 目前距离 / 速度, 因为距离原来越小, 速度是大于 1 的数, 所以滚动距离会越来越小
        var speed = 1 + acceleration;
        window.scrollTo(Math.floor(x / speed), Math.floor(y / speed));


        // 如果距离不为零, 继续调用迭代本函数
        if(x!= 0 || y != 0) {
            var invokeFunction = "goTop(" + acceleration + ", " + time + ")";
            window.setTimeout(invokeFunction, time);

        }
    }
</script>
<script src="${pageContext.request.contextPath}/res/qyjx/plugin/percircle/percircle.js"></script>
</body>
</html>