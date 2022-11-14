<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>年级VIP成绩查看</title>
		<meta name="apple-touch-fullscreen" content="YES">
	    <meta name="format-detection" content="telephone=no">
	    <meta name="apple-mobile-web-app-capable" content="yes">
	    <meta name="apple-mobile-web-app-status-bar-style" content="black">
	    <meta http-equiv="Expires" content="-1">
	    <meta http-equiv="pragram" content="no-cache">
	    <meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi"> 
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/scoreAnalysis/css/all.css "/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/css/scoreAnalysis/js/jquery-1.9.1.js"></script>
	</head>
	<body>
		<div>
		<p class="top_info">“注：有<b class="b1">${gradeExamStudentCount }</b>人参与本次统计，还有<b class="b1">${noImportStudentCount }</b>人数据未导入。”</p>
			<div class="sjxx mgb12">
				<p>
					<span class="type bgcolor_ffcd40">年级统考</span>
					<span class="title">${userExamWork.name}</span>
				</p>
				<p class="time">发布时间：<fmt:formatDate value="${userExamWork.publish_time}" pattern="yyyy年MM月dd日"/></p>
			</div>
			<div class="xsxx mgb12">
				 <p>
				 	<span>姓名：<b>${userExamWork.studentName}</b></span><span>学号：<b>
				 	<c:choose>
				 		<c:when test="${empty userExamWork.number}">
				 			无
				 		</c:when>
				 		<c:otherwise>
				 			${userExamWork.number}
				 		</c:otherwise>
				 	</c:choose>
				 	</b></span>
				 </p><%-- <fmt:formatDate value="${userExamWorks.exam_date_begin } " pattern="yyyy年MM月dd日 HH:mm"/> ~ <fmt:formatDate value="${userExamWorks.exam_date_end } " pattern="HH:mm"/> --%>
				 <p class="ks_time">考试时间段：<fmt:formatDate value="${userExamWork.exam_date}" pattern="yyyy年MM月dd日 HH:mm"/></p>
			</div>
			<div class="chengji_tu mgb12">
				<div class="all_score">
					<span class="km">总分</span>
					<span class="fen">${totalScore}</span>
					<span class="full_score">满分<b>${totalFullScore}</b>分</span>
				</div>
				<div>
					<ul>
						<c:forEach items="${userExamWorks }" var="userExamWork">
						<c:choose>
							<c:when test="${userExamWork.publish_time != null}">
								<li>
									<span class="km">${userExamWork.subjectName }</span>
									<span class="fen">${userExamWork.score}</span>
									<span class="full_score">满分<b>${userExamWork.full_score}</b>分</span>
								</li>
							</c:when>
							<c:otherwise>
								<li class="wfb">
									<span class="km">${userExamWork.subjectName }</span>
									<span class="full_score">满分<b>${userExamWork.full_score}</b>分</span>
								</li>
							</c:otherwise>
						</c:choose>
						
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="big-data " style="height: 211px;">
				<span class="use">还有班级成绩未导入，请耐心等待</span>
				<div class="progress_bar">
					<p style = " width:${progress}"></p>
				</div>
				<c:choose>
					<c:when test="${progress == '100%' or progress == '100.00%'}">
						<a href="/scoreAnalysis/WeChat/gradeVipIndex/${userExamWork.id}/${schoolId}/${userId}/${parentId}"  class="analyze">点击分析</a>
					</c:when>
					<c:otherwise>
						<a href="/scoreAnalysis/WeChat/gradeVipIndex/${userExamWork.id}/${schoolId}/${userId}/${parentId}"  class="analyze">提前查阅</a>
					</c:otherwise>
				</c:choose>
			</div>
			
		</div>
		<script>
			$(function(){
				var fw = $('.all_score .fen').width();
				var fw2 = -(fw/2);
				console.log(fw);
				if(fw>220){
					$('.all_score .fen').css({'font-size':'68.92px','top':'34%','width':fw,'margin-left':fw2});
				}else{
					$('.all_score .fen').css({'width':'228','margin-left':'-114px'});
				}
				
			})
		</script>
	</body>
</html>
