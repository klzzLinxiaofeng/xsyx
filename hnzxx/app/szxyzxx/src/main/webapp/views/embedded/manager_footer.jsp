<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="sca" uri="http://www.jiaoxueyun.com/szxy/contants" %>
<div class="scroll-top">
	<a href="#" class="tip-top" title="Go Top"><i class="icon-double-angle-up"></i></a>
</div>
<div class="footer">
	<p class="p1">
		<a href="javascript:void">联系我们</a> - <a href="javascript:void(0);">${sca:getDefaultSchoolName()}</a>
		- <a href="javascript:void">关于我们</a>
	</p>
	<p class="p2">${sca:getDefaultSchoolName()}</p>
	<p class="p3">ICP备09001711号</p>
	<p class="p4">
		Powered by <span>${sca:getDefaultSchoolName()} &copy; 2014</span>
	</p>
</div>
<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/onlineCustomer/style/default_blue.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/onlineCustomer/js/jquery.Sonline.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/onlineCustomer/js/mySonline.js"></script>
