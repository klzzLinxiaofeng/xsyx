<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/bannercommon.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/microcourse_style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/extra/microcourse_index.css" rel="stylesheet">
<title>学校列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="微课管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white" style="width:1084px;">
					<div class="weike_top">
						<p><span>公共微课库</span>我的微课</p>
					</div>
					<div class="kemu_nav">
						<ul>
							<li><a href="javascript:void(0)" class="active">首页</a></li>
							<li><a href="javascript:void(0)">语文</a></li>
							<li><a href="javascript:void(0)">数学</a></li>
							<li><a href="javascript:void(0)">英语</a></li>
							<li><a href="javascript:void(0)">历史</a></li>
							<li><a href="javascript:void(0)">地理</a></li>
							<li><a href="javascript:void(0)">政治</a></li>
							<li><a href="javascript:void(0)">科学</a></li>
						</ul>
						<div class="gjz_select">关键字：<input type="text"><button class="btn btn-primary">搜索</button></div>
					</div>
					<div style="margin-top:10px" class="banner">
						<div style="width: 1028px; height: auto;" class="index_focus">
								<!-- <a href="javascript:;" class="index_focus_pre J_toggle" data-direction="prev" title="上一步 ">下一步 </a>
								<a href="javascript:;" class="index_focus_next J_toggle" data-direction="next" title="下一步">下一步 </a> -->
								<ul style="margin:0">
									<li style="" data-show="true">
										<a href=" #6" class="pic" target="_blank">
											 <img style="width: 1028px;" class="pic" src="${pageContext.request.contextPath}/res/images/guanggaotu.jpg" alt=""> 
										</a>
									</li>
									<!-- <li style="display: none;" data-show="false">
										<a href=" #6" class="pic" target="_blank">
											 	<img style="width: 1028px;" class="pic" src="/css/teacher/microcourse/images/guanggaotu_1.jpg" alt=""> 
										</a>
									</li> -->
								</ul>
								<!-- <div style="margin-left: -166px;" class="slide_nav J_nav">
									<a href="javascript:;" data-show="true" data-index="0" style=" color: #99CC00; opacity: 0.8; text-decoration: none;">●</a>
									<a href="javascript:;" data-index="1" style="color:white">●</a>
								</div> -->
						</div>
					</div>
					<div class="wrapper-right">
						<div id="discover-courses-rows">
							<div class="ud-coursecarousel course-list-wrapper collection">
								<div id="yourLike">
									<div class="courses-header">
										<div class="left-items">
											<a href="javascript:void(0)" class="show-all-toggle">
												<h4 class="collection-title ellipsis ellipsisWidth">你感兴趣的微课</h4>
											</a>
										</div>
										<div class="right-items">
											<div class="nav-container">
												<input type="hidden" id="yourLike_count" value="385" name="pagination.count">
												<input type="hidden" id="yourLike_current" value="1" name="pagination.current">
													<a href="javascript:pageYourLike(com.opensymphony.xwork2.TextProviderSupport@b11606b);" class="course-nav-btn prev btn"><i class="icon-chevron-left"></i></a>
													<a href="javascript:pageYourLike(2);" class="course-nav-btn next btn"><i class="icon-chevron-right"></i></a>
											</div>
											<a href="/common/resources/frontMicroCourseAction_search.action?condition.grade=08&amp;condition.topicId=" class="collapse-btn view-all btn">更多</a>
										</div>
									</div>
									<div class="discover-courses-list-mask">
										<ul class="discover-courses-list one-line" style="margin-left: 0px;">
											<li class="course-box small">
												<div class="mask">
													<span class="thumbSize"> <a target="_blank"
														href="/common/resources/frontMicroCourseAction_play.action?resMicroId=2c91829148bbbc4f0148ecb4bdac0d04&amp;topicId=">
															<img src="http://files.jiaoxueyun.com/develop/resources/microCourse/temp/2014/10-08/media/1412720484675_thumbnail.jpg"
															onerror="this.src='/image/common/nomedia2.jpg'">
													</a>
													</span> <span title="02.众多的人口" class="title ellipsis">
														02.众多的人口 </span> <span class="details"> <span
														class="small-rating"><span style="width: 0.0%"></span></span>
														<span class="pinjia">（<b>0</b>人评价）
													</span> <span style="float: right; padding-top: 3px;"> <span
															id="insterest_2c91829148bbbc4f0148ecb4bdac0d04"> <a
																onclick="onFavoriteInsterest('yes', '2c91829148bbbc4f0148ecb4bdac0d04');">收藏
															</a>
														</span>
													</span> <br>
													<br> <span class="subjectGrade"> 地理 &gt; 八年级 </span> <br>

													</span> <span class="instructors"> <a href="#"> <img
															src="/image/login/noPhoto.jpg" alt="头像" class="ins-thumb">
													</a> <span class="r"> <span class="ins-name ellipsis">参考库</span>
															<span title="2014-10-08 06:22:21" class="ins-job-title">
																2014-10-08上传 </span>
													</span>
													</span>
													<span class="laiyuan">来自微课星</span>
												</div>
											</li>
											<li class="course-box small">
												<div class="mask">
													<span class="thumbSize"> <a target="_blank"
														href="/common/resources/frontMicroCourseAction_play.action?resMicroId=2c91829148bbbc4f0148ecb4bdac0d04&amp;topicId=">
															<img src="http://files.jiaoxueyun.com/develop/resources/microCourse/temp/2014/10-08/media/1412720484675_thumbnail.jpg"
															onerror="this.src='/image/common/nomedia2.jpg'">
													</a>
													</span> <span title="02.众多的人口" class="title ellipsis">
														02.众多的人口 </span> <span class="details"> <span
														class="small-rating"><span style="width: 0.0%"></span></span>
														<span class="pinjia">（<b>0</b>人评价）
													</span> <span style="float: right; padding-top: 3px;"> <span
															id="insterest_2c91829148bbbc4f0148ecb4bdac0d04"> <a
																onclick="onFavoriteInsterest('yes', '2c91829148bbbc4f0148ecb4bdac0d04');">收藏
															</a>
														</span>
													</span> <br>
													<br> <span class="subjectGrade"> 地理 &gt; 八年级 </span> <br>

													</span> <span class="instructors"> <a href="#"> <img
															src="/image/login/noPhoto.jpg" alt="头像" class="ins-thumb">
													</a> <span class="r"> <span class="ins-name ellipsis">参考库</span>
															<span title="2014-10-08 06:22:21" class="ins-job-title">
																2014-10-08上传 </span>
													</span>
													</span>
													<span class="laiyuan">来自微课星</span>
												</div>
											</li>
											<li class="course-box small">
												<div class="mask">
													<span class="thumbSize"> <a target="_blank"
														href="/common/resources/frontMicroCourseAction_play.action?resMicroId=2c91829148bbbc4f0148ecb4bdac0d04&amp;topicId=">
															<img src="http://files.jiaoxueyun.com/develop/resources/microCourse/temp/2014/10-08/media/1412720484675_thumbnail.jpg"
															onerror="this.src='/image/common/nomedia2.jpg'">
													</a>
													</span> <span title="02.众多的人口" class="title ellipsis">
														02.众多的人口 </span> <span class="details"> <span
														class="small-rating"><span style="width: 0.0%"></span></span>
														<span class="pinjia">（<b>0</b>人评价）
													</span> <span style="float: right; padding-top: 3px;"> <span
															id="insterest_2c91829148bbbc4f0148ecb4bdac0d04"> <a
																onclick="onFavoriteInsterest('yes', '2c91829148bbbc4f0148ecb4bdac0d04');">收藏
															</a>
														</span>
													</span> <br>
													<br> <span class="subjectGrade"> 地理 &gt; 八年级 </span> <br>

													</span> <span class="instructors"> <a href="#"> <img
															src="/image/login/noPhoto.jpg" alt="头像" class="ins-thumb">
													</a> <span class="r"> <span class="ins-name ellipsis">参考库</span>
															<span title="2014-10-08 06:22:21" class="ins-job-title">
																2014-10-08上传 </span>
													</span>
													</span>
												</div>
											</li>
											<li class="course-box small">
												<div class="mask">
													<span class="thumbSize"> <a target="_blank"
														href="/common/resources/frontMicroCourseAction_play.action?resMicroId=2c91829148bbbc4f0148ecb4bdac0d04&amp;topicId=">
															<img src="http://files.jiaoxueyun.com/develop/resources/microCourse/temp/2014/10-08/media/1412720484675_thumbnail.jpg"
															onerror="this.src='/image/common/nomedia2.jpg'">
													</a>
													</span> <span title="02.众多的人口" class="title ellipsis">
														02.众多的人口 </span> <span class="details"> <span
														class="small-rating"><span style="width: 0.0%"></span></span>
														<span class="pinjia">（<b>0</b>人评价）
													</span> <span style="float: right; padding-top: 3px;"> <span
															id="insterest_2c91829148bbbc4f0148ecb4bdac0d04"> <a
																onclick="onFavoriteInsterest('yes', '2c91829148bbbc4f0148ecb4bdac0d04');">收藏
															</a>
														</span>
													</span> <br>
													<br> <span class="subjectGrade"> 地理 &gt; 八年级 </span> <br>

													</span> <span class="instructors"> <a href="#"> <img
															src="/image/login/noPhoto.jpg" alt="头像" class="ins-thumb">
													</a> <span class="r"> <span class="ins-name ellipsis">参考库</span>
															<span title="2014-10-08 06:22:21" class="ins-job-title">
																2014-10-08上传 </span>
													</span>
													</span>
												</div>
											</li>
										</ul>
									</div>
								</div>
								<div id="resmicrogroup">
									<div class="courses-header">
										<div class="left-items">
											<a href="javascript:void(0)" class="show-all-toggle">
												<h4 class="collection-title ellipsis ellipsisWidth">微课星</h4>
											</a>
										</div>
										<div class="right-items">
											<div class="nav-container">
												<input type="hidden" id="all_count" value="6"
													name="pagination.count"> <input type="hidden"
													id="all_current" value="1" name="pagination.current">
												<a href="javascript:pageAll(com.opensymphony.xwork2.TextProviderSupport@298a5663);"
													class="course-nav-btn prev btn"> <i
													class="icon-chevron-left"></i>
												</a> <a href="javascript:pageAll(2);"
													class="course-nav-btn next btn"> <i
													class="icon-chevron-right"></i>
												</a>
											</div>
											<a href="/common/resources/frontMicroCourseAction_search.action?condition.grade=08&amp;condition.topicId=" class="collapse-btn view-all btn">更多</a>
										</div>
									</div>
									<div class="discover-courses-list-mask">
										<ul class="discover-courses-list one-line" style="margin-left: 0px;">
											<li class="course-box small">
												<div class="mask">
													<span class="thumbSize"> <a target="_blank"
														href="/common/resources/frontMicroCourseAction_play.action?resMicroId=2c91829148bbbc4f0148ecb4bdac0d04&amp;topicId=">
															<img src="http://files.jiaoxueyun.com/develop/resources/microCourse/temp/2014/10-08/media/1412720484675_thumbnail.jpg"
															onerror="this.src='/image/common/nomedia2.jpg'">
													</a>
													</span> <span title="02.众多的人口" class="title ellipsis">
														02.众多的人口 </span> <span class="details"> <span
														class="small-rating"><span style="width: 0.0%"></span></span>
														<span class="pinjia">（<b>0</b>人评价）
													</span> <span style="float: right; padding-top: 3px;"> <span
															id="insterest_2c91829148bbbc4f0148ecb4bdac0d04"> <a
																onclick="onFavoriteInsterest('yes', '2c91829148bbbc4f0148ecb4bdac0d04');">收藏
															</a>
														</span>
													</span> <br>
													<br> <span class="subjectGrade"> 地理 &gt; 八年级 </span> <br>

													</span> <span class="instructors"> <a href="#"> <img src="/image/login/noPhoto.jpg" alt="头像" class="ins-thumb">
													</a> <span class="r"> <span class="ins-name ellipsis">参考库</span>
															<span title="2014-10-08 06:22:21" class="ins-job-title">
																2014-10-08上传 </span>
													</span>
													</span>
												</div>
											</li>
											<li class="course-box small">
												<div class="mask">
													<span class="thumbSize"> <a target="_blank"
														href="/common/resources/frontMicroCourseAction_play.action?resMicroId=2c91829148bbbc4f0148ecb4bdac0d04&amp;topicId=">
															<img src="http://files.jiaoxueyun.com/develop/resources/microCourse/temp/2014/10-08/media/1412720484675_thumbnail.jpg"
															onerror="this.src='/image/common/nomedia2.jpg'">
													</a>
													</span> <span title="02.众多的人口" class="title ellipsis">
														02.众多的人口 </span> <span class="details"> <span
														class="small-rating"><span style="width: 0.0%"></span></span>
														<span class="pinjia">（<b>0</b>人评价）
													</span> <span style="float: right; padding-top: 3px;"> <span
															id="insterest_2c91829148bbbc4f0148ecb4bdac0d04"> <a
																onclick="onFavoriteInsterest('yes', '2c91829148bbbc4f0148ecb4bdac0d04');">收藏
															</a>
														</span>
													</span> <br>
													<br> <span class="subjectGrade"> 地理 &gt; 八年级 </span> <br>

													</span> <span class="instructors"> <a href="#"> <img src="/image/login/noPhoto.jpg" alt="头像" class="ins-thumb">
													</a> <span class="r"> <span class="ins-name ellipsis">参考库</span>
															<span title="2014-10-08 06:22:21" class="ins-job-title">
																2014-10-08上传 </span>
													</span>
													</span>
												</div>
											</li>
											<li class="course-box small">
												<div class="mask">
													<span class="thumbSize"> <a target="_blank"
														href="/common/resources/frontMicroCourseAction_play.action?resMicroId=2c91829148bbbc4f0148ecb4bdac0d04&amp;topicId=">
															<img src="http://files.jiaoxueyun.com/develop/resources/microCourse/temp/2014/10-08/media/1412720484675_thumbnail.jpg"
															onerror="this.src='/image/common/nomedia2.jpg'">
													</a>
													</span> <span title="02.众多的人口" class="title ellipsis">
														02.众多的人口 </span> <span class="details"> <span
														class="small-rating"><span style="width: 0.0%"></span></span>
														<span class="pinjia">（<b>0</b>人评价）
													</span> <span style="float: right; padding-top: 3px;"> <span
															id="insterest_2c91829148bbbc4f0148ecb4bdac0d04"> <a
																onclick="onFavoriteInsterest('yes', '2c91829148bbbc4f0148ecb4bdac0d04');">收藏
															</a>
														</span>
													</span> <br>
													<br> <span class="subjectGrade"> 地理 &gt; 八年级 </span> <br>

													</span> <span class="instructors"> <a href="#"> <img src="/image/login/noPhoto.jpg" alt="头像" class="ins-thumb">
													</a> <span class="r"> <span class="ins-name ellipsis">参考库</span>
															<span title="2014-10-08 06:22:21" class="ins-job-title">
																2014-10-08上传 </span>
													</span>
													</span>
												</div>
											</li>
											<li class="course-box small">
												<div class="mask">
													<span class="thumbSize"> <a target="_blank"
														href="/common/resources/frontMicroCourseAction_play.action?resMicroId=2c91829148bbbc4f0148ecb4bdac0d04&amp;topicId=">
															<img src="http://files.jiaoxueyun.com/develop/resources/microCourse/temp/2014/10-08/media/1412720484675_thumbnail.jpg"
															onerror="this.src='/image/common/nomedia2.jpg'">
													</a>
													</span> <span title="02.众多的人口" class="title ellipsis">
														02.众多的人口 </span> <span class="details"> <span
														class="small-rating"><span style="width: 0.0%"></span></span>
														<span class="pinjia">（<b>0</b>人评价）
													</span> <span style="float: right; padding-top: 3px;"> <span
															id="insterest_2c91829148bbbc4f0148ecb4bdac0d04"> <a
																onclick="onFavoriteInsterest('yes', '2c91829148bbbc4f0148ecb4bdac0d04');">收藏
															</a>
														</span>
													</span> <br>
													<br> <span class="subjectGrade"> 地理 &gt; 八年级 </span> <br>

													</span> <span class="instructors"> <a href="#"> <img src="/image/login/noPhoto.jpg" alt="头像" class="ins-thumb">
													</a> <span class="r"> <span class="ins-name ellipsis">参考库</span>
															<span title="2014-10-08 06:22:21" class="ins-job-title">
																2014-10-08上传 </span>
													</span>
													</span>
												</div>
											</li>
										</ul>
									</div>
								</div>
								<div id="chineseMicros">
									<div class="context-and-filters single-header clearfix">
										<div class="l">
											<h4 class="collection-title">最新上传</h4>
										</div>
										<div class="r"></div>
									</div>
									<ul id="courses" class="discover-courses-list wide" style="margin-left: 0px;">
										<li class="course-box small">
												<div class="mask">
													<span class="thumbSize"> <a target="_blank"
														href="/common/resources/frontMicroCourseAction_play.action?resMicroId=2c91829148bbbc4f0148ecb4bdac0d04&amp;topicId=">
															<img src="http://files.jiaoxueyun.com/develop/resources/microCourse/temp/2014/10-08/media/1412720484675_thumbnail.jpg"
															onerror="this.src='/image/common/nomedia2.jpg'">
													</a>
													</span> <span title="02.众多的人口" class="title ellipsis">
														02.众多的人口 </span> <span class="details"> <span
														class="small-rating"><span style="width: 0.0%"></span></span>
														<span class="pinjia">（<b>0</b>人评价）
													</span> <span style="float: right; padding-top: 3px;"> <span
															id="insterest_2c91829148bbbc4f0148ecb4bdac0d04"> <a
																onclick="onFavoriteInsterest('yes', '2c91829148bbbc4f0148ecb4bdac0d04');">收藏
															</a>
														</span>
													</span> <br>
													<br> <span class="subjectGrade"> 地理 &gt; 八年级 </span> <br>

													</span> <span class="instructors"> <a href="#"> <img src="/image/login/noPhoto.jpg" alt="头像" class="ins-thumb">
													</a> <span class="r"> <span class="ins-name ellipsis">参考库</span>
															<span title="2014-10-08 06:22:21" class="ins-job-title">
																2014-10-08上传 </span>
													</span>
													</span>
												</div>
											</li>
									</ul>
								</div>
								<div id="mathMicros">
									<div class="context-and-filters single-header clearfix">
										<div class="l">
											<h4 class="collection-title">最热微课</h4>
										</div>
										<div class="r"></div>
									</div>
									<ul id="courses" class="discover-courses-list wide" style="margin-left: 0px;">
										<li class="course-box small">
												<div class="mask">
													<span class="thumbSize"> <a target="_blank"
														href="/common/resources/frontMicroCourseAction_play.action?resMicroId=2c91829148bbbc4f0148ecb4bdac0d04&amp;topicId=">
															<img src="http://files.jiaoxueyun.com/develop/resources/microCourse/temp/2014/10-08/media/1412720484675_thumbnail.jpg"
															onerror="this.src='/image/common/nomedia2.jpg'">
													</a>
													</span> <span title="02.众多的人口" class="title ellipsis">
														02.众多的人口 </span> <span class="details"> <span
														class="small-rating"><span style="width: 0.0%"></span></span>
														<span class="pinjia">（<b>0</b>人评价）
													</span> <span style="float: right; padding-top: 3px;"> <span
															id="insterest_2c91829148bbbc4f0148ecb4bdac0d04"> <a
																onclick="onFavoriteInsterest('yes', '2c91829148bbbc4f0148ecb4bdac0d04');">收藏
															</a>
														</span>
													</span> <br>
													<br> <span class="subjectGrade"> 地理 &gt; 八年级 </span> <br>

													</span> <span class="instructors"> <a href="#"> <img src="/image/login/noPhoto.jpg" alt="头像" class="ins-thumb">
													</a> <span class="r"> <span class="ins-name ellipsis">参考库</span>
															<span title="2014-10-08 06:22:21" class="ins-job-title">
																2014-10-08上传 </span>
													</span>
													</span>
												</div>
											</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
</script>
</html>