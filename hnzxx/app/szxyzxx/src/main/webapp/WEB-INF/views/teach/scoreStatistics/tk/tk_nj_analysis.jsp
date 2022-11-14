<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/kwgl.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts.js"></script>
    <script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.1.8/js/highcharts-more.js"></script>
    <title>查看详情</title>
</head>
<body>
<div class="kwgl">
    <div class="return_kw">
        <p>[年级统考] ${gradeName} ${examWorks.name}</p>
        <div class="njtk" style="width: 500px;">
            <a href="javascript:void(0)" class="on"  id="1">年级成绩单</a>
            <a href="javascript:void(0)" id="2">年级综合分析</a>
            <a href="javascript:void(0)" id="3">年级单科分析</a>
            <a href="javascript:void(0)" id="4">年级趋势分析</a>
        </div>
        <a href="javascript:void(0)" class="btn btn-grey" onclick="toIndex();">返回</a>
        <%--<a href="javascript:void(0)" class="btn btn-blue">导出分析报告</a>--%>

    </div>
    <iframe src="/teach/scoreStatistics/analysis/grade?examWorksId=${examWorksId}&gradeId=${gradeId}&sub=1"
            id="njfx_iframe" width="100%" height="auto" frameborder="0"></iframe>

</div>
</body>
<script>
    $(function(){
        $('.njtk a').click(function(){
            var i=$(this).index();
            $(this).addClass('on');
            $(this).siblings().removeClass('on');
            var id = $(this).attr('id');
            $('#njfx_iframe').attr('src', "/teach/scoreStatistics/analysis/grade?examWorksId=${examWorksId}&gradeId=${gradeId}&sub=" + id);
        });
    });
    $('#njfx_iframe').load(function() {
        var iframeHeight=$(this).contents().find('html').height();
        $(this).height(iframeHeight+'px');
    });

//    $(function () {
//        $('.tkym:eq(2) .njfx_km a').click(function(){
//            $(this).addClass('choose');
//            $(this).siblings().removeClass('choose');
//            searchDK();
//        });
//        $('.tkym:eq(3) .njfx_km a').click(function(){
//            $(this).addClass('choose');
//            $(this).siblings().removeClass('choose');
//            searchQS();
//        });
//    });

//    searchDK();
//    searchQS();
    function searchDK(){
        var val = {
            "examWorksId" : ${examWorks.id},
            "gradeId" : ${gradeId},
            "subjectCode" : $('.tkym:eq(2) .njfx_km .choose').data("code")
        }
        $.get("/teach/scoreStatistics/analysis/grade/dk", val, function (data, status) {
            $("#njdk").html("").html(data);
        });
    }
    function searchQS() {
        var val = {
            "examWorksId" : ${examWorks.id},
            "gradeId" : ${gradeId},
            "subjectCode" : $('.tkym:eq(3) .njfx_km .choose').data("code")
        }
        $.get("/teach/scoreStatistics/analysis/grade/qs", val, function (data, status) {
            $("#njqs").html("").html(data);
        });
    }

    function toIndex(){
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/exam/grade?examWorksId=${examWorks.id}";
    }

</script>
</html>