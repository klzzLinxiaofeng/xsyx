
<%--
    Created on : 2013-5-1, 18:00:10
    Author     : huangjiangnan
--%>

<%@page import="com.gzxtjy.common.config.CommonConfig"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<s:i18n name="config/struts/locales/commonResource">
	<html>
<head>
<title><s:text name="zyfxpt" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<script type="text/javascript"src="<%=request.getContextPath()%>/js/common/jquery-1.9.1.js"></script>

<link rel="stylesheet" type="text/css"
	href="/css/common/resources/default.css" media="screen" />
<link rel="stylesheet" href="/js/common/jquery-ui/jquery-ui.css"
	type="text/css"></link>


<link href="/css/common/resources/fileSearch.css" rel="stylesheet"
	type="text/css" />
	<link rel="stylesheet" type="text/css" href="/css/teacher/paper/style.css" media="screen"/>

<%@include file="/module/teacher/common/easyui.jsp"%>

<script type="text/javascript" src="/js/common/common.js">
	
</script>

<%-- 表单的jquery控件 --%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/common/jquery.form.js"></script>

<%-- 公共的js方法 --%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/common/common.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/front/common.js"></script>
<style>
.rs_platformTabArea2Content .subnav .cur {
	background-color: #009720;
	border-radius: 5px;
	color: white;
}
</style>
</head>
<body>
	<jsp:include page="/module/teacher/head.jsp" />
	<div class="default">
		<div class="title">默认项设置</div>
		<div class="content">
		<div class="property">
			<div class="left">
				<div class="top">
					<p>题目属性</p>
					<span>设置题目默认属性</span>
				</div>
				<div class="bottom">
					<div class="b1">
						<input type="checkbox"><span>教材目录：</span>
						<select><option>数学</option><option>英语</option><option>思想政治</option></select>
						<select><option>人民教育出版社（新课标）</option><option>北京教育出版社</option></select>
						<select><option>一年级</option><option>二年级</option></select>
						<select><option>上册</option><option>下册</option></select>
					</div>
					<div class="b1">
						<input type="checkbox"><span>知 &nbsp;识 &nbsp;点：</span>
						<select class="question"><option>10以内的加减法10以内的乘除法</option><option>10以内的加减法10以内的乘除法</option></select>
					</div>
					<div class="b1">
						<input type="checkbox"><span>难   &nbsp; &nbsp; &nbsp; 度：</span>
						<input type="radio"> 无  <input type="radio"> 简单  <input type="radio"> 适中 <input type="radio"> 困难
					</div>
				</div>
			</div>
			<div class="right">
				<div class="top">
					<p>得分</p>
					<span>设置题目默认得分</span>
				</div>
				<div class="bottom">
					<div class="b1">
						<span>选择题：</span><input type="text">
					</div>
					<div class="b1">
						<span>填空题：</span><input type="text">
					</div>
				</div>
			</div>
			<div class="clear"></div>
			</div>
			<div class="use">
				<div class="b2"><input type="checkbox"><span>应用到新建的题目</span></div>
				<div class="b2"><input type="checkbox"><span>应用到当前组/题目</span></div>
			</div>
			<div class="button">
				<a href="javascript:void(0)">应用</a>
				<a href="javascript:void(0)">取消</a>
			</div>
		</div>
	</div>
	
	
	
	<footer> <jsp:include page="/module/teacher/foot.jsp"></jsp:include>

	<!--注释的是只有1页时不显示分页的代码-->
</body>
	</html>
</s:i18n>
<script type="text/javascript" src="/js/common/resources/inner.js"></script>
<script type="text/javascript" src="/js/common/jquery-ui/jquery-ui.js"></script>
<script>
	$(document).ready(
					function() {
						$(function() {
							$("#isShare").val('${isShare}');
							$("#title")
									.autocomplete(
											{
												source : function(request,
														response) {
													$
															.ajax({
																url : "/common/resources/portal/fileSearchAction_getQueryList.action",
																dataType : "json",
																type : "post",
																data : {
																	title : request.term,
																},
																success : function(
																		data) {
																	response($
																			.map(
																					data,
																					function(
																							item) {
																						return item;
																					}));
																}
															});
												},

												minLength : 1,
												select : function(event, ui) {
													$("#title").val(
															ui.item.value);
													solrQuery();

												}
											});

						});
					});
	/* 
	回到顶部
	*/
	function goTop(acceleration, time) {
		
		acceleration = acceleration || 0.1;
		time = time || 16;
	 
		var x1 = 0;
		var y1 = 0;
		var x2 = 0;
		var y2 = 0;
		var x3 = 0;
		var y3 = 0;
	 
		if (document.documentElement) {
			x1 = document.documentElement.scrollLeft || 0;
			y1 = document.documentElement.scrollTop || 0;
		}
		if (document.body) {
			x2 = document.body.scrollLeft || 0;
			y2 = document.body.scrollTop || 0;
		}
		var x3 = window.scrollX || 0;
		var y3 = window.scrollY || 0; 
		
		 
		 
		// 滚动条到页面顶部的水平距离
		var x = Math.max(x1, Math.max(x2, x3));
		// 滚动条到页面顶部的垂直距离
		var y = Math.max(y1, Math.max(y2, y3));
	 
		// 滚动距离 = 目前距离 / 速度, 因为距离原来越小, 速度是大于 1 的数, 所以滚动距离会越来越小
		var speed = 1 + acceleration;
		window.scrollTo(Math.floor(x / speed), Math.floor(y / speed));
		
	 
		// 如果距离不为零, 继续调用迭代本函数
		if(x!= 0 || y != 0) {
			var invokeFunction = "goTop(" + acceleration + ", " + time + ")";
			window.setTimeout(invokeFunction, time);
			
		}
	}
	
	/* 移出组卷跟移出组卷 */
	$(function(){
		$(".paper-list-area ul li .title .add .out").click(function(){
			$(this).prev().show();
			$(this).hide();
		});
		$(".paper-list-area ul li .title .add .join").click(function(){
			$(this).next().show();
			$(this).hide();
		});
	});
	
	/* 收起解析跟打开解析 */
	$(function(){
		$(".paper-list-area ul li .analysis .hide").click(function(){
			$(this).next().show();
			$(this).hide();
			$(this).parent().next().hide();
		});
		$(".paper-list-area ul li .analysis .show").click(function(){
			$(this).prev().show();
			$(this).hide();
			$(this).parent().next().show();
		});
	});
	
	
	$(function(){
		$(".unit-link").click(function(){
			
			$(".lesson-list").hide();
			$(".unit-item ").siblings().removeClass("selected-unit");
			$(this).addClass("selected-unit");
			$(this).children(".lesson-list").show();
			
		});
	});
	
	
</script>

