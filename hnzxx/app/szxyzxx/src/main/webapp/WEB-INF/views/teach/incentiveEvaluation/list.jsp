<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/dygl/dygl.css" rel="stylesheet">
<title>激励评价</title>
</head>
<body>
<div style="display: block;">
	<p class="unreviewed ">
		${team.name } ${monthTime }份<span class="reviewed-cease"><!-- （已审核） --></span><!--  录入时间：2016-07-25 -->
	</p>
	<!--  <p class="unreviewed ">三年级（2）班 5月份<span class="reviewed-no">（未审核）</span> 录入时间：2016-07-25</p> -->
	
	<div class="pj_Table">
    	<div class="pj_top"><ul><li>学号</li><li>姓名</li><li>评价卡</li></ul></div>
    	<div class="pj_bottom">
    		<ul>
    		<c:forEach items="${evaScore}" var="score">
					<li>
						<p>${score.number }</p>
						<p>${score.studentName }</p>
						<p><fmt:formatNumber value="${score.count}" pattern="#" type="number"/></p>
					</li>
				</c:forEach>
    		</ul>
    	</div>
    </div>
</div>
</body>
</html>