
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
	<jsp:include page="/module/common/resources/resources_head.jsp" />
	<div style="width: 1000px; margin: 0 auto;">
		<div class="subSearch" style="padding-right: 0px;">
			<img src="/css/common/resources/images/search.jpg"> <input
				type="text" id="title" value="${queryKey}" /> <input id="button"
				type="button" class="formBtn" onclick="solrQuery();"
				value="<s:text name="search" />" />
			<div id="auto"></div>
			<span id="span"></span>
			<!-- 
				<span id="span2"></span>
				<span id="span4"></span>
				<span name="queryKey" id="span3"></span> -->
			<span id="key1"></span> <span id="key2"></span> <span id="type"></span>
			<span id="code"></span> <span id="resType"></span> <span id="isShare"></span>
		</div>
	</div>
	<div class="rs_platformTabArea">
		<%@include file="/module/common/resources/resources_index_menu.jsp"%>
	</div>

	<!--**************rs_platformContainer****************-->
	<div class="new_main">
		<div class="question_select" style="width:956px;">
    		<div class="nav">
    			<ul>
    				<li ><a href="#">常规</a></li>
    				<li><a href="#">题目导出</a></li>
    				<li class="on"><a href="#">题库选题</a></li>
    				<li><a href="#">预览</a></li>
    			</ul>
    		</div>
    		<div class="tk_xuanti">
    		<div class="i1">
    			<a class="a1" href="#">题库</a>
    			<a class="a2" href="#">试卷库</a>
    			<a class="a3" href="#">作业库</a>
    			<p>公共题库</p>
    		</div>
    		<div class="i2">
    			<a class="b1" href="#">我的题库</a>
    			<a class="b2" href="#">我的试卷库</a>
    			<a class="b3" href="#">我的作业库</a>
    			<p>个人库</p>
    		</div>
    		<div class="i3">
    			<a class="c1" href="#">随机组题</a>
    			<a class="c2" href="#">随机选卷</a>
    			<p>智能推荐</p>
    		</div>
    		
    		
    		</div>
    	</div>
		<div monkey="selectArea" class="select-area mb10" id="select-area">
			<div class="nav_1">
				<ul>
					<li class="on"><a href="#">平台题库</a></li>
					<li><a href="#">作业库</a></li>
					<li><a href="#">试卷库</a></li>
				</ul>
				<div class="number"><p>15</p></div>
			</div>
			<table>
				<tbody>
					<tr>
						<td class="a" style="width:15%;">题目范围</td>
						<td class="b" style="width:85%;">
							<span>广东省</span>
							<span>数学</span>
							<span>九年级</span>
							<span>人教版</span>
							<span style="padding-left:15px;border-left:1px solid #E2E6E5;"><a class="alter" href="#">修改</a></span>
						</td>
					</tr>
					<tr >
						<td class="a">题目难度</td>
						<td class="b">
							<span><input type="checkbox">简单</span>
							<span><input type="checkbox">中等</span>
							<span><input type="checkbox">困难</span>
							<span><input type="checkbox">压轴</span>
						</td>
					</tr>
					<tr >
						<td class="a">题型</td>
						<td class="b">
							<span><input type="checkbox">全部</span>
							<span><input type="checkbox">单选题</span>
							<span><input type="checkbox">多选题</span>
							<span><input type="checkbox">判断题</span>
							<span><input type="checkbox">填空题</span>
							<span><input type="checkbox">简答题</span>
							<span><input type="checkbox">匹配题</span>
							<span><input type="checkbox">排序题</span>
							<span><input type="checkbox">拖动题</span>
							<span><input type="checkbox">数值题</span>
							<span><input type="checkbox">选择填空题</span>
							<span><input type="checkbox">论述题</span>
							<span><input type="checkbox">热区题</span>
							<span><input type="checkbox">语音作答题</span>
							<span><input type="checkbox">拍照作答题</span>
							<span><input type="checkbox">摄像作答题</span>
						</td>
					</tr>
				</tbody>
			</table>

		</div>

		<div class="rs_platformContainer_1">

			<div class="unit-area">
				<div class="hd">
					<h1>语文必修1人教版</h1>
				</div>
				<div id="unit-list" class="bd jspScrollable">
					<div class="jspContainer">
						<div class="jspPane">
							<ul class="unit-list mb20">
								<li class="unit-item"><a  title="全部资源"
									class="unit-link strong ib" href="#"><b>全部资源</b></a></li>
								<li class="unit-item selected selected-unit">
								<a title="第一单元" class="unit-link strong ib"
									href="#"><b>第一单元</b></a>
									<ul class="lesson-list">
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="1 在山的那边"
											href="#">1 在山的那边</a></li>
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="2 走一步，再走一步"
											href="#">2 走一步，再走一步</a></li>
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="3.1 蝉"
											href="#">3.1 蝉</a></li>
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="3.2 贝壳"
											href="#">3.2 贝壳</a></li>
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="4 藤萝瀑布"
											href="#">4 藤萝瀑布</a></li>
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="5 童趣"
											href="#">5 童趣</a></li>
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="单元测试"
											href="#">单元测试</a></li>
									</ul></li>
									<li class="unit-item ">
								<a title="第一单元" class="unit-link strong ib"
									href="#"><b>第二单元</b></a>
									<ul class="lesson-list">
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="1 在山的那边"
											href="#">1 在山的那边</a></li>
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="2 走一步，再走一步"
											href="#">2 走一步，再走一步</a></li>
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="3.1 蝉"
											href="#">3.1 蝉</a></li>
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="3.2 贝壳"
											href="#">3.2 贝壳</a></li>
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="4 藤萝瀑布"
											href="#">4 藤萝瀑布</a></li>
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="5 童趣"
											href="#">5 童趣</a></li>
										<li class="lesson-item"><a class="lesson-link ib pl5 pt5" title="单元测试"
											href="#">单元测试</a></li>
									</ul></li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div class="main" style=" float: left">
				<ul id="tabUl" class="rs_platformTab">
					<li id="all" class="cur"><a
						onclick="queryByKey2('','','','all');"><s:text name="all" />
					</a></li>
					<li id="txt" class=""><a
						onclick="queryByKey2('materials','电子课本','','txt');"><s:text
								name="dzkb" /> </a></li>
					<li id="picture" class=""><a
						onclick="queryByKey2('materials','','','picture');"><s:text
								name="jxsc" /> </a></li>
					<!--                    <li id="mingshikeliTabLi" class=""><a onclick="changeTab('mingshikeli')">名师课例</a></li>-->
					<li id="media" class=""><a
						onclick="queryByKey2('animationLearningDesign','','','media');"><s:text
								name="jxdh" /> </a></li>
					<li id="lianxi" class=""><a
						onclick="queryByKey2('exam','练习','','lianxi');"><s:text
								name="homework" /> </a></li>
					<li id="swf" class=""><a
						onclick="queryByKey2('teachingPlan','','','swf');"><s:text
								name="teachPlan" /></a></li>
					<li id="shijuan" class=""><a
						onclick="queryByKey2('exam','试卷','','shijuan');"><s:text
								name="testPaper" /> </a></li>
					<li id="doc" class=""><a
						onclick="queryByKey2('materials','','flv','doc');"><s:text
								name="mskl" /></a></li>
					<li id="other" class=""><a
						onclick="queryByKey2('','other','','other');"><s:text
								name="otherCourseware" /> </a></li>
					<!--  <li id="other" class="">
							<a onclick="queryByKey2('','','other','other');"><s:text name="otherCourseware" /> </a>
						</li>  -->

				</ul>
				<!-- <div id="loading" style="display: none" ><img src="/image/teacher/loading.gif" alt="加载中"/>文件加载中,请耐心等待</div> -->

				<div class="paper-list-area">
					<ul>
						<li>
							<div class="title">
								<span class="type">[单选题]</span>
								<span class="small-rating"><span style="width: 90.0000%"></span></span><span class="score">4.5</span>
								<a class="num" href="#">( <b id="docValueCount-2">108</b> 人评价)</a>
								<p class="add">
									<a class="a1" href="#">报错</a>
									<a class="a2" href="#">收藏</a>
									<a class="a3" href="#">预览</a>
									<a class="join" href="javascript:void();" style="display:none;">加入组卷</a>
									<a class="out" href="javascript:void();" >移出组卷</a>
								</p>
								<div class="top">下列根式是最简单的根式的是（）</div>
							</div>
							<div class="option">
								<a href="#"><p><span>A.</span>1</p></a>
								<a href="#"><p><span>B.</span>2</p></a>
								<a href="#"><p><span>C.</span>3</p></a>
								<a href="#"><p class="on"><span>D.</span>4</p></a>
							</div>
							<div class="analysis">
								<a class="hide">收起解析</a>
								<a class="show" style="display:none;">展开解析</a>
								<p><span>kuku </span> 创建于  2013-11-12
								<img src="/image/common/resources/pic1.jpg" alt=""></p>
							</div>
							<div class="answer">
								<div>
									<p>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源</p>
									<a href="#">2013-11-15一元一次方程家庭作业题</a><span>&nbsp;&nbsp;第三题</span>
								</div>
								<div>
									<p>全站统计</p>
									<span>本题共被作答699次  正确60%</span>
								</div>
								<div>
									<p>解 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;析</p>
									<span>无</span>
								</div>
								<div>
									<p>知&nbsp;识&nbsp;点</p>
									<span>一元二次方程</span>
								</div>
							</div>
							
						</li>
						<li>
							<div class="title">
								<span class="type">[解答题]</span>
								<span class="small-rating"><span style="width: 90.0000%"></span></span><span class="score">4.5</span>
								<a class="num" href="#">( <b id="docValueCount-2">108</b> 人评价)</a>
								<p class="add">
									<a class="a1" href="#">报错</a>
									<a class="a2" href="#">收藏</a>
									<a class="a3" href="#">预览</a>
									<a class="join" href="javascript:void();">加入组卷</a>
									<a class="out" href="javascript:void();" style="display:none;">移出组卷</a>
								</p>
								<div class="top">设函数f(x)=x+1,求x的值。</div>
							</div>
							<div class="option">
								<div class="answer_1"><span >[答案]</span>x=1;</div>
							</div>
							<div class="analysis">
								<a class="hide"style="display:none;">收起解析</a>
								<a class="show" >展开解析</a>
								<p><span>kuku </span> 创建于  2013-11-12
								<img src="/image/common/resources/pic1.jpg" alt=""></p>
							</div>
							<div class="answer" style="display:none;">
								<div>
									<p>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源</p>
									<a href="#">2013-11-15一元一次方程家庭作业题</a><span>&nbsp;&nbsp;第三题</span>
								</div>
								<div>
									<p>全站统计</p>
									<span>本题共被作答699次  正确60%</span>
								</div>
								<div>
									<p>解 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;析</p>
									<span>无</span>
								</div>
								<div>
									<p>知&nbsp;识&nbsp;点</p>
									<span>一元二次方程</span>
								</div>
							</div>
							
						</li>
						<li>
							<div class="title">
								<span class="type">[填空题]</span>
								<span class="small-rating"><span style="width: 90.0000%"></span></span><span class="score">4.5</span>
								<a class="num" href="#">( <b id="docValueCount-2">108</b> 人评价)</a>
								<p class="add">
									<a class="a1" href="#">报错</a>
									<a class="a2" href="#">收藏</a>
									<a class="a3" href="#">预览</a>
									<a class="join" href="javascript:void();">加入组卷</a>
									<a class="out" href="javascript:void();" style="display:none;">移出组卷</a>
								</p>
								<div class="top">设6=x+1,所以x等于（）。</div>
							</div>
							<div class="option">
								<div class="answer_1"><span >[答案]</span>5</div>
							</div>
							<div class="analysis">
								<a class="hide"style="display:none;">收起解析</a>
								<a class="show" >展开解析</a>
								<p><span>kuku </span> 创建于  2013-11-12
								<img src="/image/common/resources/pic1.jpg" alt=""></p>
							</div>
							<div class="answer" style="display:none;">
								<div>
									<p>来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源</p>
									<a href="#">2013-11-15一元一次方程家庭作业题</a><span>&nbsp;&nbsp;第三题</span>
								</div>
								<div>
									<p>全站统计</p>
									<span>本题共被作答699次  正确60%</span>
								</div>
								<div>
									<p>解 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;析</p>
									<span>无</span>
								</div>
								<div>
									<p>知&nbsp;识&nbsp;点</p>
									<span>一元二次方程</span>
								</div>
							</div>
							
						</li>
					</ul>
				</div>
				<div class="number_1"><p>15</p></div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	</div>
	<div class="gotop">
		<a class="top" href="javascript:void();" onclick="goTop();"></a>
		<a><div class="number_2"><p>15</p></div></a>
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

