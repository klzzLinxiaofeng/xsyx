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
    <title>年级成绩</title>
</head>
<body >
<div class="kwgl">
    <div class="return_kw">
        <p>查看详情 ${examWorks.name} 考试信息</p>
        <a href="javascript:void(0)" class="btn btn-lightGray" onclick="toIndex();">返回</a>
    </div>
    <div class="pd20">
        <div class="kw_select">
            <p class="xnxq fl" style="margin:0">${title}</p>
            <p style="float: right;margin-right: 10px;">
                考试时间段：
                <fmt:formatDate value="${examWorks.examDateBegin}" pattern="yyyy/MM/dd"/>
                -
                <fmt:formatDate value="${examWorks.examDateEnd}" pattern="MM/dd"/>
            </p>
        </div>
        <div class="kwgl_main">
            <div class="ks_list" style="padding:20px;">
                <table class="table table-striped" style="border:1px solid #e4e8eb;">
                    <thead><tr><th>年级</th><th>人均总分</th><th class="caozuo">操作</th></tr></thead>
                    <tbody>
                    <c:forEach items="${list}" var="item">
                        <tr>
                            <td>${item.gradeName}</td>
                            <td>${item.totalScore}</td>
                            <td class="caozuo">
                                <button class="btn btn-green" onclick="toAnalysisGrade('${item.examWorksId}','${item.gradeId}');">年级统计</button>
                                <button class="btn btn-orange" onclick="toTeamPage('${item.examWorksId}','${item.gradeId}');">班级统计</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<c:forEach items="${list}" var="item">
    <div class="bjtj_tck" style="display:none;" data-id="${item.gradeId}">
        <div class="bj_list">
            <c:forEach items="${item.teamList}" var="team">
                <a href=javascript:void(0) data-id="${team.teamId}">${team.teamName}</a>
            </c:forEach>
        </div>
    </div>
</c:forEach>
</body>
<script>
    $(function () {
        $('.bj_list a').click(function(){
            $(this).addClass('on');
            $(this).siblings().removeClass('on');
        });
//        $(".btn-orange").click(function(){
//            layer.open({
//                type: 1,
//                shade: false,
//                area: ['505px', '303px'],
//                title: '班级统计', //不显示标题
//                content: $('.bjtj_tck'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
//                cancel: function(){
//                    layer.close();
//                },
//                btn: ['确定'],//按钮
//                btn1: function(index, layero){
//
//                }
//            });
//        })
    });

    function toAnalysisGrade(id, gradeId){
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/analysis/grade?examWorksId=" + id + "&gradeId=" + gradeId;
    }
    function toTeamPage(id, gradeId){
        var index = 0;
        $(".bjtj_tck").each(function (i) {
            if ($(this).data("id") == gradeId){
                index = i;
            }
        });
        layer.open({
            type: 1,
            shade: false,
            area: ['505px', '303px'],
            title: '班级统计',
            content: $('.bjtj_tck:eq('+index+')'),
            cancel: function(){
                layer.close();
            },
            btn: ['确定'],//按钮
            btn1: function(index, layero){
                find(id, gradeId)
            }
        });
    }

    function find(id, gradeId) {
        var teamId = $(".bj_list .on").data("id");
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/analysis/team?examWorksId="
            + id + "&gradeId=" + gradeId +  "&teamId=" + teamId;
    }

    function toIndex() {
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/exam/index";
    }

    <%--function toTeamPage(id, gradeId){--%>
        <%--$.initWinOnTopFromLeft_qyjx("班级统计", "${pageContext.request.contextPath}/teach/scoreStatistics/exam/team?examWorksId="--%>
            <%--+ id + "&gradeId=" + gradeId, '800', '400');--%>
    <%--}--%>
</script>
</html>