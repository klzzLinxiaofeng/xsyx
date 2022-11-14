<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导学案统计</title>
<meta name="apple-touch-fullscreen" content="YES">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="pragram" content="no-cache">
<meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi">
<link href="${pageContext.request.contextPath}/res/css/statistics/questions_app.css" rel="stylesheet">
<style>
	.publish{
		width: 23%;
		margin-right: 2%;
	}
</style>
</head>
<body>
<div class="title-count">
	<div class="topic-option">
		<span>
	        <a href="javascript:void(0);" class="statistics">完成率统计</a>
	        <a href="javascript:void(0);">本班完成情况</a>
        </span>
    </div>
    <div class="fgf"></div>
    <div class="title-content" style="display: none;">
        <div class="ranking">
        	<div class="publish">
                <p>年级排名</p>
                <span>${teamOrder }</span>
            </div>
            <div class="publish">
                <p>年级完成率</p>
                <span><fmt:formatNumber type="number" value="${totalPercent }" maxFractionDigits="2"/>%</span>
            </div>
            <div class="publish">
                <p>本班完成率</p>
                <span><fmt:formatNumber type="number" value="${teamPercent }" maxFractionDigits="2"/>%</span>
            </div>
            <div class="publish" style="margin-right:0;">
                <p>完成人数</p>
                <span>${teamCount }</span>
            </div>
            <div class="clear"></div>
            <div class="head">班级完成率排名</div>
        <table border="0" cellspacing="0" cellpadding="0">
            <thead>
                <tr>
                    <th>名次</th>
                    <th>班级</th>
                    <th>完成率</th>
                    <th>完成人数</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${teamsOrder }" var="team">
	                <tr>
	                    <td>${team.order }</td>
	                    <td>${team.teamName }</td>
	                    <td><fmt:formatNumber type="number" value="${team.percent }" maxFractionDigits="2"/>%</td>
	                    <td>${team.finishCount }</td>
	                </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
   <div class="title-content" style="display:none">
   		<div class="ranking">
        <div class="population">
            <span>总体完成情况</span>
            <div class="kuang">
            <div class="already">
                <canvas id="finishCount" style="demo"></canvas>
                <p>已完成</p>
            </div>
            <div class="not">
                <canvas id="noFinishCount" style="demo"></canvas>
                <p>未完成</p>
            </div>
            </div>
        </div>
        <table border="0" cellspacing="0" cellpadding="0">
            <thead>
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>完成情况</th>
                    <th>完成时间</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${list }" var="detail">
	                <tr>
	                    <td>${detail.studentNum }</td>
	                    <td>${detail.studentName }</td>
	                    <td>${detail.hasFinish }</td>
	                    <td><fmt:formatDate value="${detail.finishTime }" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
	                </tr>
               </c:forEach>
            </tbody>
        </table>
        </div>
   </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/statistics/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/waterbubble.js"></script>
<script type="text/javascript">
$(function(){
    $(".title-content").hide();//隐藏wenben
    $(".title-content:eq(0)").show();//显示第一个wenben
    $(".topic-option span a").click(function(){
        $(".topic-option a").removeClass("statistics");//移除样式
        $(this).addClass("statistics");//添加样式
        var i=$(this).index();//获得下标
        $(".title-content").hide();//隐藏wenben
        $(".title-content:eq("+i+")").show();//显示第i个wenben
    });
    var uf_data="${unFinishPercent}";
    var f_data="${finishPercent}";
	if(uf_data==1){
		uf_data=1.01;
	}
	if(f_data==1){
		f_data=1.01;
	}
$('#finishCount').waterbubble({
    waterColor: '#0376d4',
    txt: '${finishCount}人',
    data: f_data,//数据多少
});
$('#noFinishCount').waterbubble({
    waterColor: '#f3be73',
    txt: '${unFinishCount}人',
    textColor: '#d27d06',
    data: uf_data,//数据多少
});
});
</script>
</html>