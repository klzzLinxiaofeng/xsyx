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
<!-- <style type="text/css">
	.pj_Table .pj_bottom {
	    max-height: 94200px;
	    overflow: auto;
	        overflow-x: auto;
	        overflow-y: auto;
	}
</style> -->
</head>
<body>
	<div style="display: block;">
		<p class="unreviewed ">
			${team.name }&nbsp;&nbsp;&nbsp;&nbsp;<span class="reviewed-cease"></span>
		</p>
		<div class="pj_Table"> 
    	<div class="pj_top"><ul><li>用户名</li><li>姓名</li><li>学号</li></ul></div>
    	<div class="pj_bottom" style=" max-height: 99940px; overflow: auto;">
    		<ul>
    		<c:forEach items="${studentVoList}" var="student">
					<li>
					<p>${student.userName }</p>
						<p>${student.name }</p>
						<p>${student.number }</p>
					</li>
				</c:forEach>
    		</ul>
    	</div>
    </div>
	</div>
</body>
</html>