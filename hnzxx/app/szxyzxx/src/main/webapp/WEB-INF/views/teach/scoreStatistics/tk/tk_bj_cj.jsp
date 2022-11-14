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
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
    <title>班级学生成绩单</title>
    <style type="text/css">
        .fd_table .table th, .fd_table .table td{
            text-align: center;
        }
    </style>
</head>
<body >
<div class="kwgl">
    <div class="pd20">
        <div class="kw_select">
            <p class="xnxq fl" style="margin:0">${title}</p>
            <p class="fr" style="color:#86939d">
                考试时间：
                <fmt:formatDate value="${examWorks.examDateBegin}" pattern="yyyy/MM/dd"/>
                -
                <fmt:formatDate value="${examWorks.examDateEnd}" pattern="MM/dd"/>
            </p>
        </div>
        <div class="kwgl_main">
            <div class="ks_list" style="padding:0">
                <div class="all_table">
                    <jsp:include page="./tk_bj_cj_list.jsp" />
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="all_table" />
                        <jsp:param name="url" value="/teach/scoreStatistics/analysis/team?examWorksId=${examWorksId}&gradeId=${gradeId}&teamId=${teamId}&sub=1&form=1" />
                        <jsp:param name="pageSize" value="${page.pageSize}" />
                    </jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function(){
        var w=$(".all_table").width();
        var w1=w-670;
        $(".fd_table").width(w1);
        var i=$(".fd_table table th").length;
        var th_width=83*i;
        if(i<=8){
            th_width=108*i
        }
        $(".fd_table table").width(th_width);
        $(".fd_table").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
        $(".fd_table table tr td").hover(function(){
            var j=$(this).parent().index();
            $(".table1 tr").removeClass("blue_1");
            $(".table1 tbody tr button").css("z-index","-1");
            $(".table1 tbody tr").eq(j).addClass("blue_1");
            $(".table1 tbody tr").eq(j).find("button").css("z-index","1");
        },function(){
        });
        $(".table1 tr td").hover(function(){
            var k=$(this).parent().index();
            $(".fd_table table tr").removeClass("blue_1");
            $(".fd_table table tbody tr button").css("z-index","-1");
            $(".fd_table table tbody tr").eq(k).addClass("blue_1");
            $(".fd_table table tbody tr").eq(k).find("button").css("z-index","1");
        },function(){
        });
        /* $(".fd_table table tr td").click(function(){
         var j=$(this).parent().index();
         $(".table1 tbody tr").eq(j).addClass("blue_1");
         }) */
    });
    $(window).resize(function(){
        var w=$(".all_table").width();
        var w1=w-670;
        $(".fd_table").width(w1)
        var i=$(".fd_table table th").length;
        var th_width=83*i;
        if(i<=8){
            th_width=108*i
        }
        $(".fd_table table").width(th_width);
        $(".fd_table").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
    })

    function toStudent(userId){
        window.parent.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/analysis/student?examWorksId=${examWorks.id}&userId=" + userId + "&teamId=${teamId}&backSign=3";
    }
</script>
</html>