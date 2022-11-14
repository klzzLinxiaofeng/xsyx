<%@page import="platform.education.resource.contants.ResourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	 <%@ include file="/views/embedded/common.jsp"%>
    <meta charset="UTF-8">
    <title>题目正确率</title>
    <script type="text/javascript" src="${ctp}/res/js/common/jquery.tablesort.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/common/homework.css">
</head>
<body>
<div class="container-fluid">
<jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-asterisk" name="icon"/>
                <jsp:param value="试卷管理" name="title" />
                <jsp:param value="SHI_JUAN_GUAN_LI" name="menuId" />
            </jsp:include>
	<div class="homework">
	    <h3>${title}<a href="${pageContext.request.contextPath}/exampublish/publishManagerIndex?dm=SHI_JUAN_GUAN_LI"><i class="fa  fa-reply" style="margin-right: 5px;"></i>返回列表</a></h3>
	    <div class="while">班级：<span>${teamName}</span>时间：<span>${time}</span></span></div>
	</div>
	<div class="accuracy">
	    <table border="0" cellspacing="0" cellpadding="0">
	        <thead>
	            <tr>
	                <th style="width:60px;padding-left: 25px;">题号</th>
	                <th style="width:65px;">题型</th>
	                <th>正确率</th>
	                <th style="width:120px;"></th>
	            </tr>
	        </thead>
	        <tbody>
	        <c:forEach items="${items}" var="item">
	           <tr>
	               <tr>
	                <td>${item.pos}</td>
	                <td><span>${item.questionType}</span></td>
	                <td>
	                    <div class="schedule"><p style="width:${item.correctRate}%;"></p></div>
	                </td>
	                <td class="percent">${item.correctRate}%</td>
	            </tr>
	          </tr>
	        </c:forEach>
	        </tbody>
	    </table>
	</div>
	</div>
</body>
</html>