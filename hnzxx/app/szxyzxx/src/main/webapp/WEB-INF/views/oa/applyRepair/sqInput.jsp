<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%--<%@ include file="/views/embedded/plugin/uploadify.jsp"%>--%>
	<link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
	<script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<link href="${pageContext.request.contextPath}/res/css/extra/my.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
<style type="text/css">
.form-horizontal .controls #zp .img_1 {
	float: left;
	margin: 10px;
	position: relative;
	top: 0;
	width: 233px;
	height: 130px;
}

.form-horizontal .controls #zp .img_1 img {
	width: 233px;
	height: 130px;
}

.form-horizontal .controls #zp .img_1 a {
	position: absolute;
	font-size: 22px;
	font-weight: bold;
	color: #000;
	right: 0px;
	top: 0px;
	display: block;
	width: 16px;
	height: 16px;
	line-height: 16px;
	text-align: center;
	cursor: pointer;
}
</style>
<title>报修</title>
</head>
<body>
	<h6 class="clearfix" style="font-size:14px;">${applyRepair.title}</h6>
<%--				<div class="clearfix content-widgets">--%>
<%--				<c:if test="${applyRepair.picture==null || applyRepair.picture=='' || applyRepair.picture=='undefined'}">--%>
<%--					<img src="${pageContext.request.contextPath}/res/images/no_picture.jpg" alt="img" style="width:233px;">--%>
<%--				</c:if>--%>
<%--				<c:if test="${applyRepair.picture!=null && applyRepair.picture!='' && applyRepair.picture!='undefined'}">--%>
<%--					<img src="${imgUrl}" alt="img" style="width: 233px; height: 130px;">--%>
<%--				</c:if>--%>
<%--				</div>--%>
<%--				<p class="clearfix"><span class="left"><i class="fa fa-sitemap"></i>部门</span><em>${empty department ? "无" : department}</em></p>--%>
<%--				<p class="clearfix"><span class="left"><i class="fa fa-th-list"></i>编号</span><em>${empty applyRepair.number ? "无" : applyRepair.number}</em></p>--%>
				<p class="clearfix"><span class="left"><i class="fa fa-map-marker"></i>维修地点</span><em>${placeOfCheck}  ${applyRepair.place}</em></p>
				<p class="clearfix"><span class="left"><i class="fa fa-user"></i>联系人</span><em>${empty applyRepair.contact ? applyRepair.proposerName : applyRepair.contact}</em></p>
				<p class="clearfix"><span class="left"><i class=" fa fa-phone"></i>联系电话</span><em>${empty applyRepair.phone ? phone : applyRepair.phone}</em></p>
				<p class="clearfix"><span class="left"><i class=" fa fa-list"></i>维修描述</span><em>${applyRepair.details}</em></p>
				<p class="clearfix"><span class="left"><i class=" fa fa-list"></i>维修图片</span></p>
				<img style="width:233px;height:130px;" class="ims"  src="${applyRepair.pictureUrl}"/>
				<p class="clearfix date"><fmt:formatDate pattern="yyyy年MM月dd日" value="${applyRepair.appointmentDate}"></fmt:formatDate></p>
</body>
</html>