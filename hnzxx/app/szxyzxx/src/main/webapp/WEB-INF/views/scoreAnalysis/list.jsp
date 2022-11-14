<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
		<c:if test="${examWorks== null || fn:length(examWorks) == 0}">
			<p style="color:#999;text-align:center;font-size:18px;line-height:50px">——————————  &nbsp; 暂无数据  &nbsp;  ——————————</p>
		</c:if>
		<c:if test="${examWorks!= null || fn:length(examWorks) > 0}">
			<c:forEach items="${examWorks}" var="examWork">
		<c:choose>
			<c:when test="${examWork.isJointExam == false}">
				<div class="sjxx mgb12">
				<a href="/scoreAnalysis/WeChat/teamFreeIndex/${examWork.id}/${schoolId}/${userId}/${parentId}">
				<p>
					<span class="type bgcolor_ff8a65">班级测试</span>
					<span class="title">${examWork.name }</span>
				</p>
				<p class="time">发布时间：<fmt:formatDate value="${examWork.publishTime }" pattern="yyyy年MM月dd日"/></p>
				</a>
			</div>
			</c:when>
			<c:otherwise>
			<div class="sjxx mgb12">
			<a href="/scoreAnalysis/WeChat/gradeFreeIndex/${examWork.id}/${schoolId}/${userId}/${parentId}">
				<p>
					<span class="type bgcolor_ffcd40">年级统考</span>
					<span class="title">${examWork.name }</span>
				</p>
			<p class="time">发布时间：<fmt:formatDate value="${examWork.publishTime }" pattern="yyyy年MM月dd日"/></p>
			</a>
		</div>
			</c:otherwise>
		</c:choose>
		</c:forEach>
		</c:if>
		
