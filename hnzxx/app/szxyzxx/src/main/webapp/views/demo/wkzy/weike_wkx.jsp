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
<style>
.course-box {
	float:left;
}
</style>
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
					<div class="wrapper-right">
						<div id="discover-courses-rows">
							<div class="ud-coursecarousel course-list-wrapper collection">
								<div id="yourLike">
									<div class="courses-header">
										<div class="left-items">
											<a href="javascript:void(0)" class="show-all-toggle">
												<h4 class="collection-title ellipsis ellipsisWidth">微课星</h4>
											</a>
										</div>
										<div class="right-items">
											<select><option>年级</option><option>一年级</option><option>二年级</option><option>三年级</option></select>
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
														class="small-rating"><span style="width: 20.0%"></span></span>
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
													<span class="laiyuan">来自微课星</span>
												</div>
											</li>
										</ul>
										<div class="clear"></div>
										<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="module_list_content" />
							<jsp:param name="url"
								value="/teach/grade/gradeList?sub=list" />
							<jsp:param name="pageSize" value="${page.pageSize }" />
						</jsp:include>
									</div>
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