<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.education.commonResource.web.common.contants.SysContants"%>
<%@ page import="platform.education.resource.contants.ResourceType"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/xkzy.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/js/slider.js"></script>
<title>云资源</title>
<style>
.course-box {
	float: left;
}

.div2new1 .paihang {
	height: auto;
}
</style>
</head>
<body>
	<div class="container-fluid" style="margin-top:20px;">
		<div class="row-fluid" >
			<div class="span12">
				<div class="content-widgets white" style="margin:0 auto;width: 1078px;">
					<div class="div2new1">
						<div class="div2left">
							<div class="zydh">
								<div class="title">
									<span>学科资源导航</span>
								</div>
								<div class="wk_list">
									<ul>
										<li class="fl_list">
											<div class="li_hover">
												<p class="title_1">小学资源</p>
												<div class="wk_div">
													<a
														href="${ctp}/resource/searcher/index?stageCode=2&gradeCode=21">一年级上</a>
													<a
														href="${ctp}/resource/searcher/index?stageCode=2&gradeCode=22">二年级上</a>
													<!-- 													<a href="javascript:void(0)">三年级上</a>  -->
													<a
														href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=13">语文</a>
													<a
														href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=14">数学</a>
													<a
														href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=41">英文</a>
												</div>
											</div>
											<div class="njkm" style="display: none;">
												<ul class="nj_ul">
													<li>
														<p>一年级</p>
														<div class="km">
															<!-- 															<a href="">英语</a>  -->
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=21">语文</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=21">数学</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>二年级</p>
														<div class="km">
															<!-- 															<a href="javascript:void(0)">英语</a>  -->
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=22">语文</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=22">数学</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>三年级</p>
														<div class="km">
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=23">语文</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=23">数学</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=41&gradeCode=23">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>四年级</p>
														<div class="km">
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=24">语文</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=24">数学</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=41&gradeCode=24">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>五年级</p>
														<div class="km">
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=25">语文</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=25">数学</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=41&gradeCode=25">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>六年级</p>
														<div class="km">
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=26">语文</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=26">数学</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=2&subjectCode=41&gradeCode=26">英文</a>
														</div>
														<div class="clear"></div>
													</li>
												</ul>
											</div>
										</li>
										<li class="fl_list">
											<div class="li_hover ">
												<p class="title_1">初中资源</p>
												<div class="wk_div">
													<a
														href="${ctp}/resource/searcher/index?stageCode=3&gradeCode=31">七年级上</a>
													<a
														href="${ctp}/resource/searcher/index?stageCode=3&gradeCode=32">八年级上</a>
													<a
														href="${ctp}/resource/searcher/index?stageCode=3&subjectCode=13">语文</a>
													<a
														href="${ctp}/resource/searcher/index?stageCode=3&subjectCode=14">数学</a>
													<a
														href="${ctp}/resource/searcher/index?stageCode=3&subjectCode=41">英语</a>
												</div>
											</div>
											<div class="njkm" style="display: none;">
												<ul class="nj_ul">
													<li>
														<p>初一</p>
														<div class="km">
															<a
																href="${ctp}/resource/searcher/index?stageCode=3&subjectCode=13&gradeCode=31">语文</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=3&subjectCode=14&gradeCode=31">数学</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=3&subjectCode=41&gradeCode=31">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>初二</p>
														<div class="km">
															<a
																href="${ctp}/resource/searcher/index?stageCode=3&subjectCode=13&gradeCode=32">语文</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=3&subjectCode=14&gradeCode=32">数学</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=3&subjectCode=41&gradeCode=32">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>初三</p>
														<div class="km">
															<a
																href="${ctp}/resource/searcher/index?stageCode=3&subjectCode=13&gradeCode=33">语文</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=3&subjectCode=14&gradeCode=33">数学</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=3&subjectCode=41&gradeCode=33">英文</a>
														</div>
														<div class="clear"></div>
													</li>
												</ul>
											</div>
										</li>
										
										
									<c:forEach items="${sessionScope[sca:currentUserKey()].stageCodes}" var="item" varStatus="sta">
										<c:if test="${item == 4}">
											<li class="fl_list">
											<div class="li_hover ">
												<p class="title_1">高中资源</p>
												<div class="wk_div">
													
													<a
														href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=13">语文</a>
													<a
														href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=14">数学</a>
													<a
														href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=41">英语</a>
												</div>
											</div>
											<div class="njkm" style="display: none;">
												<ul class="nj_ul">
													<li>
														<p>语文</p>
														<div class="km">
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=13">必修一</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=13">必修二</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=13">必修三</a>
														</div>
														<div class="clear"></div>
													</li>
													
													<li>
														<p>数学</p>
														<div class="km">
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=14">必修一</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=14">必修二</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=14">必修三</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>英语</p>
														<div class="km">
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=41">必修一</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=41">必修二</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=41">必修三</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>物理</p>
														<div class="km">
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=16">必修一</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=16">必修二</a>
															
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>化学</p>
														<div class="km">
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=17">必修一</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=17">必修二</a>
															
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>地理</p>
														<div class="km">
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=20">必修一</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=20">必修二</a>
															<a
																href="${ctp}/resource/searcher/index?stageCode=4&subjectCode=20">必修三</a>
														</div>
														<div class="clear"></div>
													</li>
													
												</ul>
											</div>
										</li>
										</c:if>
									</c:forEach>
										
										
										
										
										<!-- 										<li class="fl_list"> -->
										<!-- 											<div class="li_hover"> -->
										<!-- 												<p class="title_1">高中资源</p> -->
										<!-- 												<div class="wk_div"> -->
										<!-- 													<a href="javascript:void(0)">高一上</a> <a -->
										<!-- 														href="javascript:void(0)">高二上</a> <a -->
										<!-- 														href="javascript:void(0)">高三下</a> <a -->
										<!-- 														href="javascript:void(0)">语文</a> <a -->
										<!-- 														href="javascript:void(0)">数学</a> <a -->
										<!-- 														href="javascript:void(0)">科学</a> <a -->
										<!-- 														href="javascript:void(0)">生物</a> -->
										<!-- 												</div> -->
										<!-- 											</div> -->
										<!-- 											<div class="njkm" style="display: none;"> -->
										<!-- 												<ul class="nj_ul"> -->
										<!-- 													<li> -->
										<!-- 														<p>初一</p> -->
										<!-- 														<div class="km"> -->
										<!-- 															<a href="javascript:void(0)">英语</a> <a -->
										<!-- 																href="javascript:void(0)">语文</a> <a -->
										<!-- 																href="javascript:void(0)">数学</a> <a -->
										<!-- 																href="javascript:void(0)">科学</a> <a -->
										<!-- 																href="javascript:void(0)">生物</a> -->
										<!-- 														</div> -->
										<!-- 														<div class="clear"></div> -->
										<!-- 													</li> -->
										<!-- 													<li> -->
										<!-- 														<p>初二</p> -->
										<!-- 														<div class="km"> -->
										<!-- 															<a href="javascript:void(0)">英语</a> <a -->
										<!-- 																href="javascript:void(0)">语文</a> <a -->
										<!-- 																href="javascript:void(0)">数学</a> <a -->
										<!-- 																href="javascript:void(0)">科学</a> <a -->
										<!-- 																href="javascript:void(0)">生物</a> -->
										<!-- 														</div> -->
										<!-- 														<div class="clear"></div> -->
										<!-- 													</li> -->
										<!-- 													<li> -->
										<!-- 														<p>初三</p> -->
										<!-- 														<div class="km"> -->
										<!-- 															<a href="javascript:void(0)">英语</a> <a -->
										<!-- 																href="javascript:void(0)">语文</a> <a -->
										<!-- 																href="javascript:void(0)">数学</a> <a -->
										<!-- 																href="javascript:void(0)">科学</a> <a -->
										<!-- 																href="javascript:void(0)">生物</a> -->
										<!-- 														</div> -->
										<!-- 														<div class="clear"></div> -->
										<!-- 													</li> -->
										<!-- 												</ul> -->
										<!-- 											</div> -->
										<!-- 										</li> -->
									</ul>
								</div>
							</div>
							<div class="paihang">
								<div class="phtitle">
									<span>本周浏览排行版</span>
								</div>
								<div class="phjoin">
									<ul>
										<li class="first"><p>1</p> 
											<a href="${ctp}/resource/viewer/14891"> 走一步，再走一步 </a> 
											<!-- 											<span>368次浏览</span> -->
										</li>
										<li class="second"><p>2</p> 
											<a href="${ctp}/resource/viewer/15221"> 沁园春-雪 </a> 
											<!-- 	 <span>360次浏览</span> -->
										</li>
										<li class="three"><p>3</p> 
											<a href="${ctp}/resource/viewer/15227"> 雨说 </a> 
											<!-- 	<span>321次浏览</span> -->
										</li>
										<li><p>4</p> 
											<a href="${ctp}/resource/viewer/13916"> 二次根式 </a>
											 <!-- <span>284次浏览</span> --></li>
										<li><p>5</p> 
											<a href="${ctp}/resource/viewer/13926"> 二次根式的乘除 </a> 
											<!-- <span>245次浏览</span> --></li>
										<li><p>6</p> 
											<a href="${ctp}/resource/viewer/15708"> 藤野先生 </a> 
											<!-- 	<span>215次浏览</span> -->
										</li>
										<li><p>7</p> 
											<a href="${ctp}/resource/viewer/7183"> Unit2 My schoolbag </a> 
												<!-- <span>184次浏览</span> -->
										</li>
										<li><p>8</p> 
											<a href="${ctp}/resource/viewer/14280">01.Starter</a>
											<!-- <span>115次浏览</span> -->
										</li>
									</ul>
								</div>
							</div>
							<div style="height: 487px;" class="paihang">
								<div class="phtitle">
									<span>最新上传</span>
								</div>
								<div class="scjoin">
									<ul>
										<li>
											<!-- 											<p class="time">6分钟前</p>  --> <img
											src="${pageContext.request.contextPath}/res/images/ckk.png">
											<p class="name">参考库</p>
											<p class="upload">
												上传了<a href="${ctp}/resource/viewer/15374">《我爱这土地》</a>
											</p>
										</li>
										<li>
											<!-- 											<p class="time">11分钟前</p>  --> <img
											src="${pageContext.request.contextPath}/res/images/ckk.png">
											<p class="name">参考库</p>
											<p class="upload">
												上传了<a href="${ctp}/resource/viewer/15372">《诗两首》</a>
											</p>
										</li>
										<li>
											<!-- 											<p class="time">25分钟前</p>  --> <img
											src="${pageContext.request.contextPath}/res/images/ckk.png">
											<p class="name">参考库</p>
											<p class="upload">
												上传了<a href="${ctp}/resource/viewer/4787">2-2.植树问题</a>
											</p>
										</li>
										<li>
											<!-- 											<p class="time">35分钟前</p>  --> <img
											src="${pageContext.request.contextPath}/res/images/ckk.png">
											<p class="name">参考库</p>
											<p class="upload">
												上传了<a href="${ctp}/resource/viewer/13555">Uint1 How do you study for a test</a>
											</p>
										</li>

									</ul>
								</div>
							</div>
							<div class="paihang">
								<div class="phtitle">
									<span>本周点赞排行版</span>
								</div>
								<div class="phjoin">
									<ul>
										<li class="first"><p>1</p> 
											<a href="${ctp}/resource/viewer/14891"> 走一步，再走一步 </a> 
											<!-- 											<span>368人赞</span> --></li>
										<li class="second"><p>2</p> 
											<a href="${ctp}/resource/viewer/15221"> 沁园春-雪 </a> 
											<!-- 											<span>360人赞</span> --></li>
										<li class="three"><p>3</p> 
											<a href="${ctp}/resource/viewer/15227"> 雨说 </a> 
											<!-- 											<span>321人赞</span> --></li>
										<li><p>4</p> 
											<a href="${ctp}/resource/viewer/13916"> 二次根式 </a>
											<!-- 											<span>284人赞</span> -->
										</li>
										<li><p>5</p> 
											<a href="${ctp}/resource/viewer/13926"> 二次根式的乘除 </a> 
											<!-- 											<span>245人赞</span> -->
										</li>
										<li><p>6</p> 
											<a href="${ctp}/resource/viewer/15708"> 藤野先生 </a> 
												<!-- 											<span>215人赞</span> --></li>
										<li><p>7</p> 
											<a href="${ctp}/resource/viewer/7183"> Unit2 My schoolbag </a> 
											<!-- 											<span>184人赞</span> -->
										</li>
										<li><p>8</p> 
											<a href="${ctp}/resource/viewer/14280">01.Starter</a>
											<!-- 											<span>115人赞</span> -->
										</li>
									</ul>
								</div>
							</div>
							<div class="paihang">
								<div class="phtitle">
									<span>本周收藏排行版</span>
								</div>
								<div class="phjoin">
									<ul>
										<li class="first"><p>1</p> 
											<a href="${ctp}/resource/viewer/14891"> 走一步，再走一步 </a> 
											<!-- 											<span>368人收藏</span> --></li>
										<li class="second"><p>2</p> 
											<a href="${ctp}/resource/viewer/14280">01.Starter</a>
											<!-- 											<span>360人收藏</span> --></li>
										<li class="three"><p>3</p> 
											<a href="${ctp}/resource/viewer/15708"> 藤野先生 </a> 
											<!-- 											<span>321人收藏</span> --></li>
										<li><p>4</p> 
											<a href="${ctp}/resource/viewer/7183"> Unit2 My schoolbag </a>  
											<!-- 											<span>284人收藏</span> -->
										</li>
										<li><p>5</p> 
											<a href="${ctp}/resource/viewer/15221"> 沁园春-雪 </a> 
											 <!-- 											<span>245人收藏</span> -->
										</li>
										<li><p>6</p> 
											<a href="${ctp}/resource/viewer/13926"> 二次根式的乘除 </a>  
										<!-- 											<span>215人收藏</span> --></li>
										<li><p>7</p> 
											<a href="${ctp}/resource/viewer/13916"> 二次根式 </a> 
										<!-- 											<span>184人收藏</span> -->
										</li>
										<li><p>8</p> 
											<a href="${ctp}/resource/viewer/15227"> 雨说 </a>  
											<!-- 											<span>115人收藏</span> -->
										</li>
										<!-- 										<li><p>9</p> <a href="javascript:void(0)">一元二次方程</a> 											<span>77人收藏</span> -->
										<!-- 										</li> -->
										<!-- 										<li><p>10</p> <a href="javascript:void(0)">函数的概念</a> 											<span>25人收藏</span> -->
										<!-- 										</li> -->
									</ul>
								</div>
							</div>
							<div class="paihang">
								<div class="phtitle">
									<span>本周下载排行版</span>
								</div>
								<div class="phjoin">
									<ul>
										<li class="first"><p>1</p> 
											<a href="${ctp}/resource/viewer/15221"> 沁园春-雪 </a> 
											<!-- 											<span>368次下载</span> --></li>
										<li class="second"><p>2</p> 
											<a href="${ctp}/resource/viewer/15227"> 雨说 </a> 
											<!-- 											<span>360次下载</span> --></li>
										<li class="three"><p>3</p> 
											<a href="${ctp}/resource/viewer/13916"> 二次根式 </a> 
											<!-- 											<span>321次下载</span> --></li>
										<li><p>4</p>
											<a href="${ctp}/resource/viewer/7183"> Unit2 My schoolbag </a>   
											<!-- 											<span>284次下载</span> -->
										</li>
										<li><p>5</p> 
											<a href="${ctp}/resource/viewer/15708"> 藤野先生 </a>  
											<!-- 											<span>245次下载</span> -->
										</li>
										<li><p>6</p> 
											<a href="${ctp}/resource/viewer/14891"> 走一步，再走一步 </a>  
										<!-- 											<span>215次下载</span> --></li>
										<li><p>7</p> 
											<a href="${ctp}/resource/viewer/14280">01.Starter</a> 
											<!-- 											<span>184次下载</span> -->
										</li>
										<li><p>8</p> <a href="${ctp}/resource/viewer/13926"> 二次根式的乘除 </a>  <!-- 											<span>115次下载</span> -->
										</li>
										<!-- 										<li><p>9</p> <a href="javascript:void(0)">一元二次方程</a> 											<span>77次下载</span> -->
										<!-- 										</li> -->
										<!-- 										<li><p>10</p> <a href="javascript:void(0)">函数的概念</a> 											<span>25次下载</span> -->
										<!-- 										</li> -->
									</ul>
								</div>
							</div>
						</div>
						<div class="slideshow">
							<div class="s_div1">
								<div class="div1_left">
									<div class="select">
										<form id="xkzy_seacher" action="${pageContext.request.contextPath}/resource/searcher/result/index" method="post" >
											<input type="text" name="title" placeholder="请输入资源名称"/>
											<a href="javascript:void(0)" onclick="$('#xkzy_seacher').submit();"></a>
										</form>
									</div>
									<div class="a">
										<div id="banner" class="pic1">
											<ul id="pic" class="pic">
												<li><img
													src="${pageContext.request.contextPath}/res/images/banner_1.jpg"></li>
												<li><img
													src="${pageContext.request.contextPath}/res/images/banner_2.jpg"></li>
												<li><img
													src="${pageContext.request.contextPath}/res/images/banner_3.jpg"></li>
											</ul>
										</div>
										<ul class="points" id="banner_btn">
											<li class="current" num="0">1</li>
											<li class="" num="1">2</li>
											<li class="" num="2">3</li>
										</ul>
									</div>
								</div>
								<div class="div1_right">
									<div class="zyzs">
										<p class="p1">学科资源总数</p>
										<p class="p2">${resource_count}</p>
									</div>
									<div class="grwk">
										<div class="g1">
											<img
												src="<avatar:avatar userId='${sessionScope[sca:currentUserKey()].userId}'></avatar:avatar>">
											<p>${sessionScope[sca:currentUserKey()].realName}</p>
											<a
												href="${pageContext.request.contextPath}/resource/myResource?index=index&resType=<%= ResourceType.LEARNING_DESIGN %>">
												>进入我的资源</a>
										</div>
										<div class="g2">
											<!-- 											<div class="wktj" style="border-right:1px solid #E8E8E8;"> -->
											<!-- 												<p class="p1">22</p> -->
											<!-- 												<p class="p2">上传资源</p> -->
											<!-- 											</div> -->
											<!-- 											<div  class="wktj"> -->
											<!-- 												<p class="p1">16</p> -->
											<!-- 												<p class="p2">收藏资源</p> -->
											<!-- 											</div> -->
											<div class="clear"></div>
											<a
												href="${pageContext.request.contextPath}/resource/uploadIndex"
												class="zysc"></a>
										</div>
									</div>
								</div>
							</div>
							<div class="wkzy yuwen">
								<div class="title_1">
									<p>
										语文资源<span>Chinese</span>
									</p>
								</div>
								<div class="zy_search">
									<div class="sea_left">
										<div class="km_lb">
											<div class="a">
												<div id="banner_1" class="pic1">
													<ul id="pic_1" class="pic">
														<li>
															<a href="${ctp}/resource/viewer/13113"><img src="${pageContext.request.contextPath}/res/images/index/niaodetiantang.png"></a>
														</li>
														<li>
															<a href="${ctp}/resource/viewer/13101"><img src="${pageContext.request.contextPath}/res/images/index/guanchao.png"></a>
														</li>
														<li>
															<a href="${ctp}/resource/viewer/13107"><img src="${pageContext.request.contextPath}/res/images/index/daxiagu.png"></a>
														</li>
													</ul>
												</div>
												<ul class="points" id="banner_btn_1">
													<li class="current" num="0">1</li>
													<li class="" num="1">2</li>
													<li class="" num="2">3</li>
												</ul>
											</div>
										</div>
										<div class="bb_div">
											<p>语文版本快速通道</p>
											<div class="td_list">
												<a href="${ctp}/resource/searcher/index?subjectCode=13">人教版</a>
												<!-- <a href="javascript:void(0)">人教新课标</a>
					                        <a href="javascript:void(0)">北师大版</a>
					                        <a href="javascript:void(0)">西师大版</a>
					                        <a href="javascript:void(0)">苏教版</a>
					                        <a href="javascript:void(0)">北京版</a>
					                        <a href="javascript:void(0)">语文A版</a>
					                        <a href="javascript:void(0)">苏教版</a>
					                        <a href="javascript:void(0)">北京版</a>
					                        <a href="javascript:void(0)">苏教版</a>
					                        <a href="javascript:void(0)">北京版</a>
					                        <a href="javascript:void(0)">语文A版</a>
					                        <a href="javascript:void(0)">人教版</a>
					                        <a href="javascript:void(0)">人教新课标</a>
					                        <a href="javascript:void(0)">北师大版</a>
					                        <a href="javascript:void(0)">语文A版</a> -->
											</div>
										</div>
										<div class="bb_div">
											<p>精品推荐</p>
											<div class="tt_list">
												<ul>
													<li><a href="${ctp}/resource/viewer/15374">·九年级上册语文《我爱这土地》|微课|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/15373">·九年级上册语文《乡愁》|微课|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/15368">·九年级上册语文《我爱这土地》|课件|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/15369">·九年级上册语文《乡愁》|课件|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/15372">·九年级上册语文《诗两首》|教案|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/15375">·九年级上册语文《诗两首》|作业|人教版</a></li>
												</ul>
											</div>
										</div>
									</div>
									<div class="sea_right">
										<div class="jiaoan">
											<div class="nj">
												<ul>
													<li class="on"><a href="javascript:void(0)">一年级</a></li>
													<li><a href="javascript:void(0)">二年级</a></li>
													<li><a href="javascript:void(0)">三年级</a></li>
													<li><a href="javascript:void(0)">四年级</a></li>
													<li><a href="javascript:void(0)">五年级</a></li>
													<li><a href="javascript:void(0)">六年级</a></li>
												</ul>
											</div>
											<div class="jc">
												<ul>
													<li><a href="${ctp}/resource/viewer/7908"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版一年级语文上册课件：《汉语拼音 1》课件1</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/7907"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版一年级语文上册课件：《汉语拼音 1》课件2</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/7906"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版一年级语文上册课件：《汉语拼音 1》课件3</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/7916"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版一年级语文上册教案：《汉语拼音1》教材简说</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/7917"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版一年级语文上册教案：《汉语拼音1》教学设计</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/7918"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版一年级语文上册教案：《汉语拼音1》教学目标</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/7910"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_img"></span>人教版一年级语文上册素材： 韵母o的插图</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none;">
													<li><a href="${ctp}/resource/viewer/11461"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版二年级语文上册课件：《1 秋天的图画》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/11464"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版二年级语文上册教案：《1 秋天的图画》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/11463"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版二年级语文上册素材：《秋天的怀念》课文朗读</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/11473"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版二年级语文上册课件：《2 黄山奇石》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/11476"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版二年级语文上册教案：《2 黄山奇石》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/11475"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版二年级语文上册素材：《黄山奇石》朗读</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/11485"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版二年级语文上册课件：《3 植物妈妈有办法》</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none;">
													<li><a href="${ctp}/resource/viewer/11235"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版三年级语文下册教案：《燕子》教案</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/11234"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版三年级语文下册素材：《燕子》课文朗读</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/10985"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版三年级语文上册课件：《1我们的民族小学》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/10995"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版三年级语文上册教案：《2 金色的草地》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/11239"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版三年级语文下册素材：《古诗两首》课文朗读</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/11242"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版三年级语文下册课件：《3荷花》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/10997"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版三年级语文上册课件：《3爬天都峰》</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none;">
													<li><a href="${ctp}/resource/viewer/13101"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版四年级语文上册微课：观潮</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13107"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版四年级语文上册微课：雅鲁藏布大峡谷</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13113"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版四年级语文上册微课：鸟的天堂</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13097"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版四年级语文上册课件：《观潮》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13100"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版四年级语文上册教案：《观潮》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13099"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版四年级语文上册素材：《观潮》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13103"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版四年级语文上册课件：雅鲁藏布大峡谷</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none;">
													<li><a href="${ctp}/resource/viewer/12643"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版五年级语文下册课件：《草原》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/12645"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版五年级语文下册素材：《草原》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/12646"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版五年级语文下册教案：《草原》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/12652"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版五年级语文下册教案：丝绸之路</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/12649"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版五年级语文下册课件：丝绸之路</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/12662"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版五年级语文下册素材：把铁路修到拉萨去</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/12667"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版五年级语文下册素材：猴王出世</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none;">
													<li><a href="${ctp}/resource/viewer/12973"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版六年级语文下册课件：《两小儿辩日》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/12974"> <span
															class="style">[试卷]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版六年级语文下册试卷：《1 文言文两则》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/12980"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版六年级语文下册课件：《匆匆》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/12976"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版六年级语文下册素材：文言文两则-两小儿辩日</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/12975"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版六年级语文下册素材：文言文两则-学奕</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/12986"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版六年级语文下册课件：《桃花心木》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/12992"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版六年级语文下册课件：《顶碗少年》</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
											</div>
										</div>
										<div class="jiaoan">
											<div class="nj">
												<ul>
													<li class="on"><a href="javascript:void(0)">初一</a></li>
													<li><a href="javascript:void(0)">初二</a></li>
													<li><a href="javascript:void(0)">初三</a></li>
												</ul>
											</div>
											<div class="jc">
												<ul>
													<li><a href="${ctp}/resource/viewer/14882"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版七年级语文上册课件：《1 在山的那边》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14885"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版七年级语文上册教案：《1 在山的那边》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14884"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版七年级语文上册素材：《1 在山的那边》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14883"> <span
															class="style">[试卷]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版七年级语文上册试卷： 《1 在山的那边》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14891"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_vide"></span>人教版七年级语文上册微课：走一步，再走一步</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14886"> <span
															class="style">[作业]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版七年级语文上册作业：《1 在山的那边》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14902"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版七年级语文上册微课：紫藤萝瀑布</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none">
													<li><a href="${ctp}/resource/viewer/15702"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版八年级语文下册课件：《1 藤野先生》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/15705"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版八年级语文下册教案：《1 藤野先生》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/15704"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版八年级语文下册素材：《1 藤野先生》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/15703"> <span
															class="style">[试卷]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版八年级语文下册试卷：《1 藤野先生》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/15708"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_vide"></span>人教版八年级语文下册微课：02-1.藤野先生</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/15709"> <span
															class="style">[作业]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版八年级语文下册作业：《1 藤野先生》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/15707"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版八年级语文下册微课：02-2.藤野先生</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none">
													<li><a href="${ctp}/resource/viewer/15217"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版九年级语文上册课件：《1 沁园春·雪》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/15220"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版九年级语文上册教案：《1 沁园春·雪》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/15219"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版九年级语文上册素材：《1 沁园春·雪》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/15218"> <span
															class="style">[试卷]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版九年级语文上册试卷：《1 沁园春·雪》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/15221"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_vide"></span>人教版九年级语文上册微课：《1 沁园春·雪》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/15222"> <span
															class="style">[作业]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版九年级语文上册作业：《1 沁园春·雪》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/15227"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版九年级语文上册微课：02.雨说</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
											</div>
										</div>
										<!-- <div class="jiaoan">
						                  <div class="nj">
						                  <ul>
						                    <li class="on"><a href="javascript:void(0)" >高一</a></li>
						                    <li><a href="javascript:void(0)" >高二</a></li>
						                    <li><a href="javascript:void(0)" >高三</a></li>
						                  </ul>
						                  </div>
						                  <div class="jc">
						                    <ul>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_doc"></span>人教版七年级语文上册教案：济南的冬天(老舍)</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_swf"></span>人教版语文七年级上册综合性学习《这就是我》</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_ppt"></span>人教版七年级语文上册教案：《世说新语》两则</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>人教版七年级语文上册教案： 散文诗两首 金色花 荷叶</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>人教版七年级语文上册课件：散步</span><span class="day">06-26</span>
						                      </li>
						                    </ul>
						                  </div>
					                  </div> -->
									</div>
								</div>
							</div>
							<div class="wkzy shuxue">
								<div class="title_1">
									<p>
										数学资源<span>Mathematics</span>
									</p>
								</div>
								<div class="zy_search">
									<div class="sea_left">
										<div class="km_lb">
											<div class="a">
												<div id="banner_2" class="pic1">
													<ul id="pic_2" class="pic">
														<li>
															<a href="${ctp}/resource/viewer/4778"><img src="${pageContext.request.contextPath}/res/images/index/budaikuohaodeszys.png"></a>
														</li>
														<li>
															<a href="${ctp}/resource/viewer/4787"><img src="${pageContext.request.contextPath}/res/images/index/zswt.png"></a>
														</li>
														<li>
															<a href="${ctp}/resource/viewer/4800"><img src="${pageContext.request.contextPath}/res/images/index/jfxz.png"></a>
														</li>
													</ul>
												</div>
												<ul class="points" id="banner_btn_2">
													<li class="current" num="0">1</li>
													<li class="" num="1">2</li>
													<li class="" num="2">3</li>
												</ul>
											</div>
										</div>
										<div class="bb_div">
											<p>数学版本快速通道</p>
											<div class="td_list">
												<a href="${ctp}/resource/searcher/index?subjectCode=14">人教版</a>
												<!-- 												<a href="javascript:void(0)">人教新课标</a>  -->
												<!-- 												<a href="javascript:void(0)">北师大版</a>  -->
												<!-- 												<a href="javascript:void(0)">西师大版</a>  -->
												<!-- 												<a href="javascript:void(0)">苏教版</a>  -->
												<!-- 												<a href="javascript:void(0)">北京版</a>  -->
												<!-- 												<a href="javascript:void(0)">语文A版</a>  -->
												<!-- 												<a href="javascript:void(0)">苏教版</a>  -->
												<!-- 												<a href="javascript:void(0)">北京版</a>  -->
												<!-- 												<a href="javascript:void(0)">苏教版</a>  -->
												<!-- 												<a href="javascript:void(0)">北京版</a>  -->
												<!-- 												<a href="javascript:void(0)">语文A版</a>  -->
												<!-- 												<a href="javascript:void(0)">人教版</a>  -->
												<!-- 												<a href="javascript:void(0)">人教新课标</a>  -->
												<!-- 												<a href="javascript:void(0)">北师大版</a>  -->
												<!-- 												<a href="javascript:void(0)">语文A版</a> -->
											</div>
										</div>
										<div class="bb_div">
											<p>精品推荐</p>
											<div class="tt_list">
												<ul>
													<li><a href="${ctp}/resource/viewer/4778">·四年级下册数学
															1-2.不带括号的四则运算|微课|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/4779">·四年级下册数学
															1-1.带括号的四则运算|微课|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/4788">·四年级下册数学
															2-1.位置与方向|微课|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/4787">·四年级下册数学
															2-2.植树问题|微课|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/4799">·四年级下册数学
															3-9.十进制计数法|微课|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/4800">·四年级下册数学
															3-8.减法的性质|微课|人教版</a></li>
												</ul>
											</div>
										</div>
									</div>
									<div class="sea_right">
										<div class="jiaoan">
											<div class="nj">
												<ul>
													<li class="on"><a href="javascript:void(0)">一年级</a></li>
													<li><a href="javascript:void(0)">二年级</a></li>
													<li><a href="javascript:void(0)">三年级</a></li>
													<li><a href="javascript:void(0)">四年级</a></li>
													<li><a href="javascript:void(0)">五年级</a></li>
													<li><a href="javascript:void(0)">六年级</a></li>
												</ul>
											</div>
											<div class="jc">
												<ul>
													<li><a href="${ctp}/resource/viewer/2897"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版一年级数学上册课件：《数一数》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/2917"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版一年级数学上册课件：《比一比》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/2977"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版一年级数学上册课件：5以内的加法</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/2915"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版一年级数学上册教案：《数一数》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/2974"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版一年级数学上册教案：《比一比》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/3066"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版一年级数学上册教案：《5以内的减法和0的认识》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/2907"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_img"></span>人教版一年级数学上册素材：《数一数》（1）</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none;">
													<li><a href="${ctp}/resource/viewer/3842"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版二年级数学上册课件：《认识长度单位厘米》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/3876"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版二年级数学上册教案：《长度单位》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/3854"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版二年级数学上册素材：《长度单位（测量）》01</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/3843"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版二年级数学上册课件：《认识线段、解决问题》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/3876"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版二年级数学上册教案：《长度单位（测量）》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/3853"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版二年级数学上册素材：《长度单位（测量）》02</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/3844"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版二年级数学上册课件：《认识米、米和厘米》</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none;">
													<li><a href="${ctp}/resource/viewer/3756"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版三年级数学下册教案：位置与方向的教学设计</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/3755"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版三年级数学下册素材：《位置与方向》操场</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/3753"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版三年级数学下册课件：《位置和方向》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/3765"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版三年级数学下册教案：总复习教案</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/3760"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版三年级数学下册素材：《总复习》水罐</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/3758"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版三年级数学下册课件：《总复习》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/3767"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版三年级数学下册课件：《口算除法》</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none;">
													<li><a href="${ctp}/resource/viewer/4678"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版四年级数学上册微课：1-3.用计算器探寻规律</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4679"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版四年级数学上册微课：1-2.亿以上数的读写法</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4680"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版四年级数学上册微课：1-1.亿以内数的读写</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4667"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版四年级数学上册课件：十进制计数法</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4677"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版四年级数学上册教案：《用计算器计算》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4670"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版四年级数学上册素材：《计算工具的认识》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4668"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版四年级数学上册课件：《亿以内数的读法》</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none;">
													<li><a href="${ctp}/resource/viewer/4440"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版五年级数学下册课件：《图形的变换》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4442"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_img"></span>人教版五年级数学下册素材：《图形的变换》之风车旋转</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4444"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版五年级数学下册教案：图形的变换</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4452"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版五年级数学下册教案：因数和倍数</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4449"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版五年级数学下册课件：因数与倍数</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4443"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_img"></span>人教版五年级数学下册素材：《图形的变换》之角度</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4460"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_img"></span>人教版五年级数学下册素材:《长方体和正方体的表面积》</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none;">
													<li><a href="${ctp}/resource/viewer/4600"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版六年级数学下册课件：《负数》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4601"> <span
															class="style">[试卷]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版六年级数学下册试卷：《负数》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4609"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版六年级数学下册课件：圆柱的表面积</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4602"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_img"></span>人教版六年级数学下册素材：《数轴的认识》气温变化</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4603"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_img"></span>人教版六年级数学下册素材：《数轴的认识》数轴的练习</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4621"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版六年级数学下册课件：比例的应用</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/4634"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版六年级数学下册课件：数学统计</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
											</div>
										</div>
										<div class="jiaoan">
											<div class="nj">
												<ul>
													<li class="on"><a href="javascript:void(0)">初一</a></li>
													<li><a href="javascript:void(0)">初二</a></li>
													<li><a href="javascript:void(0)">初三</a></li>
												</ul>
											</div>
											<div class="jc">
												<ul>
													<li><a href="${ctp}/resource/viewer/13708"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版七年级数学上册课件：《1.1 正数和负数》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13711"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版七年级数学上册教案：《1.1 正数和负数》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13710"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版七年级数学上册素材：《《1.1 正数和负数》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13709"> <span
															class="style">[试卷]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版七年级数学上册试卷：《1.1 正数和负数》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13712"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_vide"></span>人教版七年级数学上册微课：《1.1 正数和负数》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13713"> <span
															class="style">[作业]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版七年级数学上册作业：《1.1 正数和负数》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13718"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版七年级数学上册微课：有理数</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none">
													<li><a href="${ctp}/resource/viewer/14191"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版八年级数学下册课件：《20.1.1 平均数》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14194"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版八年级数学下册教案：《20.1.1 平均数》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14193"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版八年级数学下册素材：《20.1.1 平均数》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14192"> <span
															class="style">[试卷]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版八年级数学下册试卷：数据的代表</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14195"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_vide"></span>人教版八年级数学下册微课：数据的代表</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14196"> <span
															class="style">[作业]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版八年级数学下册作业：《20.1.2
																中位数和众数》</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14201"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版八年级数学下册微课：数据的波动</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none">
													<li><a href="${ctp}/resource/viewer/13916"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版九年级数学上册课件：21.1 二次根式</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13916"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版九年级数学上册教案：21.1 二次根式</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13918"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版九年级数学上册素材：21.1 二次根式</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13917"> <span
															class="style">[试卷]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版九年级数学上册试卷：21.1 二次根式</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13926"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_vide"></span>人教版九年级数学上册微课：1-6.二次根式的乘除</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13921"> <span
															class="style">[作业]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版九年级数学上册作业：《21.1 二次根式》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13920"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版九年级数学上册微课：1-1.二次根式</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
											</div>
										</div>
										<!-- 										<div class="jiaoan"> -->
										<!-- 											<div class="nj"> -->
										<!-- 												<ul> -->
										<!-- 													<li class="on"><a href="javascript:void(0)">高一</a></li> -->
										<!-- 													<li><a href="javascript:void(0)">高二</a></li> -->
										<!-- 													<li><a href="javascript:void(0)">高三</a></li> -->
										<!-- 												</ul> -->
										<!-- 											</div> -->
										<!-- 											<div class="jc"> -->
										<!-- 												<ul> -->
										<!-- 													<li><span class="style">[教案]</span><span -->
										<!-- 														class="wd_title"><span class="icon icon_doc"></span>一年级上数学专项试题-6和7的加减法|人教新课标</span><span -->
										<!-- 														class="day">06-26</span></li> -->
										<!-- 													<li><span class="style">[教案]</span><span -->
										<!-- 														class="wd_title"><span class="icon icon_swf"></span>一年级上数学专项试题-5、4、3、2加几|人教新课标</span><span -->
										<!-- 														class="day">06-26</span></li> -->
										<!-- 													<li><span class="style">[教案]</span><span -->
										<!-- 														class="wd_title"><span class="icon icon_ppt"></span>一年级上数学专项试题-1～5的认识|人教新课标</span><span -->
										<!-- 														class="day">06-26</span></li> -->
										<!-- 													<li><span class="style">[教案]</span><span -->
										<!-- 														class="wd_title"><span class="icon icon_excel"></span>一年级上数学专项试题-6和7的认识|人教新课标</span><span -->
										<!-- 														class="day">06-26</span></li> -->
										<!-- 													<li><span class="style">[教案]</span><span -->
										<!-- 														class="wd_title"><span class="icon icon_img"></span>一年级上数学专项试题-8和9的认识|人教新课标</span><span -->
										<!-- 														class="day">06-26</span></li> -->
										<!-- 												</ul> -->
										<!-- 											</div> -->
										<!-- 										</div> -->
									</div>
								</div>
							</div>
							<div class="wkzy yingyu">
								<div class="title_1">
									<p>
										英语资源<span>English</span>
									</p>
								</div>
								<div class="zy_search">
									<div class="sea_left">
										<div class="km_lb">
											<div class="a">
												<div id="banner_3" class="pic1">
													<ul id="pic_3" class="pic">
														<li>
															<a href="${ctp}/resource/viewer/13567"><img src="${pageContext.request.contextPath}/res/images/index/grammar.png"></a>
														</li>
														<li>
															<a href="${ctp}/resource/viewer/13555"><img src="${pageContext.request.contextPath}/res/images/index/gofor.png"></a>
														</li>
														<li>
															<a href="${ctp}/resource/viewer/13568"><img src="${pageContext.request.contextPath}/res/images/index/vocabulary.png"></a>
														</li>
													</ul>
												</div>
												<ul class="points" id="banner_btn_3">
													<li class="current" num="0">1</li>
													<li class="" num="1">2</li>
													<li class="" num="2">3</li>
												</ul>
											</div>
										</div>
										<div class="bb_div">
											<p>英语版本快速通道</p>
											<div class="td_list">
												<a href="${ctp}/resource/searcher/index?subjectCode=41">人教版</a>
												<!-- 												<a href="javascript:void(0)">人教新课标</a>  -->
												<!-- 												<a href="javascript:void(0)">北师大版</a>  -->
												<!-- 												<a href="javascript:void(0)">西师大版</a>  -->
												<!-- 												<a href="javascript:void(0)">苏教版</a>  -->
												<!-- 												<a href="javascript:void(0)">北京版</a>  -->
												<!-- 												<a href="javascript:void(0)">语文A版</a>  -->
												<!-- 												<a href="javascript:void(0)">苏教版</a>  -->
												<!-- 												<a href="javascript:void(0)">北京版</a>  -->
												<!-- 												<a href="javascript:void(0)">苏教版</a>  -->
												<!-- 												<a href="javascript:void(0)">北京版</a>  -->
												<!-- 												<a href="javascript:void(0)">语文A版</a>  -->
												<!-- 												<a href="javascript:void(0)">人教版</a>  -->
												<!-- 												<a href="javascript:void(0)">人教新课标</a>  -->
												<!-- 												<a href="javascript:void(0)">北师大版</a>  -->
												<!-- 												<a href="javascript:void(0)">语文A版</a> -->
											</div>
										</div>
										<div class="bb_div">
											<p>精品推荐</p>
											<div class="tt_list">
												<ul>
													<li><a href="${ctp}/resource/viewer/13555">·九年级全册英语
															01-2.Uint1 How do you study for a test|微课|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/13566">·九年级全册英语
															01-1.Uint1 How do you study for a test|微课|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/13569">·九年级全册英语
															10-1.Unit10-vocabulary|微课|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/13568">·九年级全册英语
															10-2.Unit10-vocabulary|微课|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/13567">·九年级全册英语
															10-3.Unit10-grammar|微课|人教版</a></li>
													<li><a href="${ctp}/resource/viewer/13582">·九年级全册英语
															11-1.Unit11-grammar|微课|人教版</a></li>
												</ul>
											</div>
										</div>
									</div>
									<div class="sea_right">
										<div class="jiaoan">
											<div class="nj">
												<ul>
													<!-- 													<li class="on"><a href="javascript:void(0)">一年级</a></li> -->
													<!-- 													<li><a href="javascript:void(0)">二年级</a></li> -->
													<li><a href="javascript:void(0)">三年级</a></li>
													<li><a href="javascript:void(0)">四年级</a></li>
													<li><a href="javascript:void(0)">五年级</a></li>
													<li><a href="javascript:void(0)">六年级</a></li>
												</ul>
											</div>
											<div class="jc">
												<!-- 												<ul> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/2897"> <span --%>
												<!-- 															class="style">[课件]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_ppt"></span>人教版一年级数学上册课件：《数一数》</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/2917"> <span --%>
												<!-- 															class="style">[课件]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_ppt"></span>人教版一年级数学上册课件：《比一比》</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/2977"> <span --%>
												<!-- 															class="style">[课件]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_ppt"></span>人教版一年级数学上册课件：5以内的加法</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/2915"> <span --%>
												<!-- 															class="style">[教案]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_doc"></span>人教版一年级数学上册教案：《数一数》</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/2974"> <span --%>
												<!-- 															class="style">[教案]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_doc"></span>人教版一年级数学上册教案：《比一比》</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/3066"> <span --%>
												<!-- 															class="style">[教案]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_doc"></span>人教版一年级数学上册教案：《5以内的减法和0的认识》</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/2907"> <span --%>
												<!-- 															class="style">[素材]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_img"></span>人教版一年级数学上册素材：《数一数》（1）</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<!-- 												</ul> -->
												<!-- 												<ul style="display: none;"> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/3842"> <span --%>
												<!-- 															class="style">[课件]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_ppt"></span>人教版二年级数学上册课件：《认识长度单位厘米》</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/3876"> <span --%>
												<!-- 															class="style">[教案]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_doc"></span>人教版二年级数学上册教案：《长度单位》</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/3854"> <span --%>
												<!-- 															class="style">[素材]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_music"></span>人教版二年级数学上册素材：《长度单位（测量）》01</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/3843"> <span --%>
												<!-- 															class="style">[课件]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_ppt"></span>人教版二年级数学上册课件：《认识线段、解决问题》</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/3876"> <span --%>
												<!-- 															class="style">[教案]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_doc"></span>人教版二年级数学上册教案：《长度单位（测量）》</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/3853"> <span --%>
												<!-- 															class="style">[素材]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_music"></span>人教版二年级数学上册素材：《长度单位（测量）》02</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<%-- 													<li><a href="${ctp}/resource/viewer/3844"> <span --%>
												<!-- 															class="style">[课件]</span><span class="wd_title"><span -->
												<!-- 																class="icon icon_ppt"></span>人教版二年级数学上册课件：《认识米、米和厘米》</span><span -->
												<!-- 															class="day">2015-07-10</span> -->
												<!-- 													</a></li> -->
												<!-- 												</ul> -->
												<ul>
													<li><a href="${ctp}/resource/viewer/5301"> <span
															class="style">[教案]</span> <span class="wd_title"><span
																class="icon icon_doc"></span> 人教版三年级英文下册教案：《Unit 1
																Welcome back to school》 </span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/5224"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版三年级英文下册素材：《Unit 1
																Welcome back to school》Presentation</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/5222"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版三年级英文下册课件：《Unit 1
																Welcome back to school》</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/5301"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版三年级英文下册教案：《Unit 1
																Welcome back to school》</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/5225"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版三年级英文下册素材：《Unit 1
																Welcome back to school》C Story time</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/5304"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版三年级英文下册课件：《My Family》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/5382"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版三年级英文下册课件：《At the zoo》</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none;">
													<li><a href="${ctp}/resource/viewer/7122"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版四年级英文上册微课：1.Unit1 My
																classroom</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/7183"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版四年级英文上册微课：2.Unit2 My
																schoolbag</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/7247"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版四年级英文上册微课：3.Unit3 My
																friends</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/7052"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版四年级英文上册课件：Unit 1 My
																Classroom</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/7119"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版四年级英文上册教案：Unit 1 My
																Classroom</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/7054"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版四年级英文上册素材：you</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/7124"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版四年级英文上册课件：Unit2 My
																Schoolbag</span><span class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none;">
													<li><a href="${ctp}/resource/viewer/5996"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版五年级英文下册课件：Unit 1 This is
																My Day</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/5998"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版五年级英文下册素材：work</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/6050"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版五年级英文下册教案：Unit 1 This is
																My Day</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/6051"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版五年级英文下册教案：Unit 1 This is
																My Day</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/6055"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版五年级英文下册课件：Unit 2 My
																Favourite Season</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/6000"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版五年级英文下册素材：when</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/5999"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_img"></span>人教版五年级英文下册素材：work</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none;">
													<li><a href="${ctp}/resource/viewer/6770"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版六年级英文下册课件：《How tall are
																you》课件2</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/6774"> <span
															class="style">[试卷]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版六年级英文下册试卷：《How tall are
																you》</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/6771"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版六年级英文下册课件：《How tall are
																you》课件1</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/6775"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版六年级英文下册素材：younger</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/6776"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_img"></span>人教版六年级英文下册素材：younger</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/6841"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版六年级英文下册课件：《What's the
																matter, Mike》课件1</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/6937"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版六年级英文下册课件：《Last
																Weekend》课件2</span><span class="day">2015-07-10</span>
													</a></li>
												</ul>
											</div>
										</div>
										<div class="jiaoan">
											<div class="nj">
												<ul>
													<li class="on"><a href="javascript:void(0)">初一</a></li>
													<li><a href="javascript:void(0)">初二</a></li>
													<li><a href="javascript:void(0)">初三</a></li>
												</ul>
											</div>
											<div class="jc">
												<ul>
													<li><a href="${ctp}/resource/viewer/14276"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版七年级英文上册课件：《Good
																morning》课件2</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14279"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版七年级英文上册教案：《Starter_Unit1_Good_morning!》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14296"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版七年级英文上册素材：《Unit 1 My
																name's Gina》Let's talk</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14278"> <span
															class="style">[试卷]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版七年级英文上册试卷：Starter_Unit_1_Good_morning</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14280"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_vide"></span>人教版七年级英文上册微课：01.Starter</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14281"> <span
															class="style">[作业]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版七年级英文上册作业：《Starter_Unit1_Good_morning!》</span><span
															class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14301"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版七年级英文上册微课：02.Unit1</span><span
															class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none">
													<li><a href="${ctp}/resource/viewer/14764"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版八年级英文下册课件：Unit 1 Will
																people have robots</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14771"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版八年级英文下册教案：Unit 1 Will
																people have robots</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14768"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版八年级英文下册素材：Unit 1 Will
																people have robots</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14767"> <span
															class="style">[试卷]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版八年级英文下册试卷：Unit 1 Will
																people have robots</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14772"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_vide"></span>人教版八年级英文下册微课：Unit 1 Will
																people have robots</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14773"> <span
															class="style">[作业]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版八年级英文下册作业：Unit 1 Will
																people have robots</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/14781"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版八年级英文下册微课：10.-unit10-it's
																a nice day,isn't it</span><span class="day">2015-07-10</span>
													</a></li>
												</ul>
												<ul style="display: none">
													<li><a href="${ctp}/resource/viewer/13548"> <span
															class="style">[课件]</span><span class="wd_title"><span
																class="icon icon_ppt"></span>人教版九年级英文全册课件：《Unit 1 How do
																you study for a test》</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13554"> <span
															class="style">[教案]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版九年级英文全册教案：《Unit 1 How do
																you study for a test》</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13551"> <span
															class="style">[素材]</span><span class="wd_title"><span
																class="icon icon_music"></span>人教版九年级英文全册素材：《Unit 1 How
																do you study for a test》</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13550"> <span
															class="style">[试卷]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版九年级英文全册试卷：《Unit 1 How do
																you study for a test》</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13556"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_vide"></span>人教版九年级英文全册微课：01-1.Uint1
																How do you study for a test</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13557"> <span
															class="style">[作业]</span><span class="wd_title"><span
																class="icon icon_doc"></span>人教版九年级英文全册作业：《Unit 1 How do
																you study for a test》</span><span class="day">2015-07-10</span>
													</a></li>
													<li><a href="${ctp}/resource/viewer/13555"> <span
															class="style">[微课]</span><span class="wd_title"><span
																class="icon icon_video"></span>人教版九年级英文全册微课：01-2.Uint1
																How do you study for a test</span><span class="day">2015-07-10</span>
													</a></li>
												</ul>
											</div>
										</div>
										<!-- 										<div class="jiaoan"> -->
										<!-- 											<div class="nj"> -->
										<!-- 												<ul> -->
										<!-- 													<li class="on"><a href="javascript:void(0)">高一</a></li> -->
										<!-- 													<li><a href="javascript:void(0)">高二</a></li> -->
										<!-- 													<li><a href="javascript:void(0)">高三</a></li> -->
										<!-- 												</ul> -->
										<!-- 											</div> -->
										<!-- 											<div class="jc"> -->
										<!-- 												<ul> -->
										<!-- 													<li><span class="style">[教案]</span><span -->
										<!-- 														class="wd_title"><span class="icon icon_doc"></span>一年级上数学专项试题-6和7的加减法|人教新课标</span><span -->
										<!-- 														class="day">06-26</span></li> -->
										<!-- 													<li><span class="style">[教案]</span><span -->
										<!-- 														class="wd_title"><span class="icon icon_swf"></span>一年级上数学专项试题-5、4、3、2加几|人教新课标</span><span -->
										<!-- 														class="day">06-26</span></li> -->
										<!-- 													<li><span class="style">[教案]</span><span -->
										<!-- 														class="wd_title"><span class="icon icon_ppt"></span>一年级上数学专项试题-1～5的认识|人教新课标</span><span -->
										<!-- 														class="day">06-26</span></li> -->
										<!-- 													<li><span class="style">[教案]</span><span -->
										<!-- 														class="wd_title"><span class="icon icon_excel"></span>一年级上数学专项试题-6和7的认识|人教新课标</span><span -->
										<!-- 														class="day">06-26</span></li> -->
										<!-- 													<li><span class="style">[教案]</span><span -->
										<!-- 														class="wd_title"><span class="icon icon_img"></span>一年级上数学专项试题-8和9的认识|人教新课标</span><span -->
										<!-- 														class="day">06-26</span></li> -->
										<!-- 												</ul> -->
										<!-- 											</div> -->
										<!-- 										</div> -->
									</div>
								</div>
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var objs = document.getElementById("banner_btn").getElementsByTagName("li");
	var tv = new TransformView("banner", "pic", 573, objs.length, {
		onStart : function() {
			Each(objs, function(o, i) {
				o.className = tv.Index === i ? "current" : "";
			});
		}, //按钮样式
		Up : false
	});
	tv.Start();
	Each(objs, function(o, i) {
		o.onmouseover = function() {
			o.className = "current";
			tv.Auto = false;
			tv.Index = i;
			tv.Start();
		};
		o.onmouseout = function() {
			o.className = "";
			tv.Auto = true;
			tv.Start();
		};
	});

	var objs1 = document.getElementById("banner_btn_1").getElementsByTagName(
			"li");
	var tv1 = new TransformView("banner_1", "pic_1", 250, objs1.length, {
		onStart : function() {
			Each(objs1, function(o, i) {
				o.className = tv1.Index === i ? "current" : "";
			});
		}, //按钮样式
		Up : false
	});
	tv1.Start();
	Each(objs1, function(o, i) {
		o.onmouseover = function() {
			o.className = "current";
			tv1.Auto = false;
			tv1.Index = i;
			tv1.Start();
		};
		o.onmouseout = function() {
			o.className = "";
			tv1.Auto = true;
			tv1.Start();
		};
	});

	var objs2 = document.getElementById("banner_btn_2").getElementsByTagName(
			"li");
	var tv2 = new TransformView("banner_2", "pic_2", 250, objs2.length, {
		onStart : function() {
			Each(objs2, function(o, i) {
				o.className = tv2.Index === i ? "current" : "";
			});
		}, //按钮样式
		Up : false
	});
	tv2.Start();
	Each(objs2, function(o, i) {
		o.onmouseover = function() {
			o.className = "current";
			tv2.Auto = false;
			tv2.Index = i;
			tv2.Start();
		};
		o.onmouseout = function() {
			o.className = "";
			tv2.Auto = true;
			tv2.Start();
		};
	});

	var objs3 = document.getElementById("banner_btn_3").getElementsByTagName(
			"li");
	var tv3 = new TransformView("banner_3", "pic_3", 250, objs3.length, {
		onStart : function() {
			Each(objs3, function(o, i) {
				o.className = tv3.Index === i ? "current" : "";
			});
		}, //按钮样式
		Up : false
	});
	tv3.Start();
	Each(objs3, function(o, i) {
		o.onmouseover = function() {
			o.className = "current";
			tv3.Auto = false;
			tv3.Index = i;
			tv3.Start();
		};
		o.onmouseout = function() {
			o.className = "";
			tv3.Auto = true;
			tv3.Start();
		};
	});

	$(function() {
		var timer;
		$(".li_hover").hover(function() {
			clearInterval(timer);
			$(".li_hover").parent().removeClass("on");
			$(".njkm").hide();
			$(this).parent().addClass("on");
			$(this).next().show();
		}, function() {
			timer = setTimeout(function() {
				$(".li_hover").parent().removeClass("on");
				$(".njkm").hide();
			}, 1000);
		});
		$(".njkm").hover(function() {
			clearInterval(timer);
		}, function() {
			$(".li_hover").parent().removeClass("on");
			$(".njkm").hide();
		});

		$(".zy_search .sea_right .nj li a").click(function() {
			var i = $(this).parent().index();
			$(this).parent().siblings().removeClass("on");
			$(this).parent().addClass("on");
			$_this = $(this).parent().parent().parent().next().children();
			$_this.hide();
			$_this.eq(i).show();
		});
	});
</script>
</html>