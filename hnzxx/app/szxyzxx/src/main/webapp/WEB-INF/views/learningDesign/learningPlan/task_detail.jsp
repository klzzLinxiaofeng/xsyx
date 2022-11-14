<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>完成统计情况</title>
     <%@ include file="/views/embedded/common.jsp"%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/dxa/arrange.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/waterbubble.js"></script>
</head>
<script type="text/javascript">
$(function(){
    $(".statistics").hide();//隐藏wenben
    $(".statistics:eq(0)").show();//显示第一个wenben
    $(".execution_class a").click(function(){
        $(".execution_class a").removeClass("execution");//移除样式
        $(this).addClass("execution");//添加样式
        var i=$(this).index();//获得下标
        $(".statistics").hide();//隐藏wenben
        $(".statistics:eq("+i+")").show();//显示第i个wenben
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
<body>
<div class="bzdxa">
    <div class="position">* 完成情况统计 - ${lpName }</div>
    <div class="spacing">
    <p class="btn_link" style="float: right;position:relative;top:5px;">
		<a href="javascript:void(0)" class="a4" onclick="javascript:history.go(-1)"><i class="fa  fa-reply"></i>返回列表</a>
	</p>
    <div class="execution_class">
        <a href="javascript:void(0);" class="execution">排名表</a>
        <a href="javascript:void(0);">本班完成状态</a>
    </div>
    <div class="statistics">
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
                <p class="spacing">完成人数</p>
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
    <div class="statistics">
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
<div class="clear"></div>
</div>
</body>
</html>