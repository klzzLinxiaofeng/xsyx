<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

<div class="biao-ul" style="padding-top:0">
<ul id="ul">
	<c:choose>
	<c:when test="${not empty bpBwInfo}">
		<li id="welcome_li">
			<div class="item">
				<div class="item-left">
					<span class="sign ban-acitve">欢迎词</span>
				</div>
				<div class="item-right" style="margin-left:150px">
					<h4><strong>${bpBwInfo.welcomeText}</strong></h4>
					<p class="texta">触发时间：<fmt:formatDate value="${bpBwInfo.welcomeTextTime}" pattern="yyyy年MM月dd日 HH:mm:ss" /></p>
					<p class="texta">结束时间：<fmt:formatDate value="${bpBwInfo.welcomeTextEndTime}" pattern="yyyy年MM月dd日 HH:mm:ss" /></p>
				</div>
			</div>
			<div class="item-bottom" style="height: 15px">
				<span ></span>
				<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
				<a href="javascript:void(0)"  onclick="deleteObj(this, '${bpBwInfo.id}');">
				<i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
				</c:if>
			</div>
		</li>
	</c:when>
	<c:otherwise>
		<li>
		 <div class="empty">
			<p class="empty_text">搜索结果为空</p>
	     </div>
	  	 </li>
	</c:otherwise>
	</c:choose>
		
	</ul>
</div>
