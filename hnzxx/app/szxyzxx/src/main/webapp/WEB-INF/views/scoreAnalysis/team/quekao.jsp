<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>缺考</title>
		<meta name="apple-touch-fullscreen" content="YES">
	    <meta name="format-detection" content="telephone=no">
	    <meta name="apple-mobile-web-app-capable" content="yes">
	    <meta name="apple-mobile-web-app-status-bar-style" content="black">
	    <meta http-equiv="Expires" content="-1">
	    <meta http-equiv="pragram" content="no-cache">
	    <meta name="viewport" content="width=640, user-scalable=no, target-densitydpi=device-dpi"> 
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/scoreAnalysis/css/all.css "/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/statistics/jquery-2.1.1.js"></script>
	</head>
	<body>
		<div>
			
			<div class="sjxx mgb12">
				<p>
					<span class="type bgcolor_ff8a65">班级测试</span>
					<span class="title">${userTeamExam.name}</span>
				</p>
				<p class="time">发布时间：<fmt:formatDate value="${userTeamExam.publishTime}" pattern="yyyy年MM月dd日"/></p>
			</div>
			<div class="xsxx mgb12">
				 <p>
				 	<span>姓名：<b>${userTeamExam.studentName}</b></span><span>学号：<b>
				 	<c:choose>
				 		<c:when test="${empty userTeamExam.number}">
				 			无
				 		</c:when>
				 		<c:otherwise>
				 			${userTeamExam.number}
				 		</c:otherwise>
				 	</c:choose>
				 	</b>
				 	</span>
				 </p>
				 <p class="ks_time">考试时间：<fmt:formatDate value="${userTeamExam.exam_date}" pattern="yyyy年MM月dd日 HH:mm"/></p>
			</div>
			<div class="chengji_tu mgb12">
				<div class="bg_score qk">
					<span class="score">缺考</span>
				</div>
			</div>
			
			<!-- <div class="big-data ">
				<span class="use">è®©å¤§æ°æ®å¸®æ¨åæå­å¥³åæ°èåæäº</span>
				<a href="javascript:void(0)" class="analyze">ç¹å»åæ</a>
			</div> -->
				
			
		</div>
	</body>
</html>
