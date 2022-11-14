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
.course-card-wide{
	margin-top:0;
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
							<li><a href="javascript:void(0)" class="active">看过的微课</a></li>
							<li><a href="javascript:void(0)" >我的微课</a></li>
							<li><a href="javascript:void(0)">收藏的微课</a></li>
						</ul>
						<div class="gjz_select">关键字：<input type="text"><button class="btn btn-primary">搜索</button></div>
					</div>
					<div class="wrapper-right">
						<div id="discover-courses-rows">
							<div class="ud-coursecarousel course-list-wrapper collection">
								<div id="yourLike">
									<div class="discover-courses-list-mask">
										<ul id="courses" class="discover-courses-list multi-line wide">
											<li id="list_402885823fafe604013fd293c72307b4"
												class="course-card-wide">
												<div>
													<!-- 如果所收藏的微课没有被作者删除 -->
													<span class="thumb">
													<a target="_blank" href="/common/resources/frontMicroCourseAction_play.action?resMicroId=402885823fafe604013fd293c72307b4">
															<img src="http://files.jiaoxueyun.com/develop/resources/microCourse/temp/2013/07-12/media/1373627583901_thumbnail.jpg"
															onerror="this.src='/image/common/nomedia2.jpg'">
													</a>
													</span>
													 <span class="details"> 
													 <span title="金色的草地"
														class="title title-ellipsis"> 金色的草地 </span> 
														<span class="share">
														<span> 
														<a onclick="javascript:void(0);">收藏 </a>
														</span>
														<span> 
														<a onclick="javascript:void(0);"><font color="#ff7575">取消</font> </a>
														</span>
													</span> <span title="金色的草地" class="desc ellipsis"> 简介：金色的草地
													</span> <span class="bottom"> <span class="ins"> 
													<a href="#"> 
													<img src="http://files.jiaoxueyun.com/develop/portrait/20110914001/teacher/2013/07-01/1372663999064.jpg"
														onerror="this.src='/image/login/noPhoto.jpg'" alt="头像" class="ins-thumb">
															</a> <span class="ins-name ellipsisWidth">罗志明 <span
																	title="2013-12-19 10:16:02" class="ins-job-title">
																		语文 &nbsp; &nbsp; 三年级 &nbsp;&nbsp; 21分钟前浏览 </span>
															</span>
														</span> <span class="count spb"> 共 <b>1</b> 人评价 <span
																class="conver-status"></span>
														</span> <span class="small-rating"> <span
																style="width: 0.0%"></span>
														</span>
													</span>
													</span>
												</div>
											</li>
											<li id="list_402885823fafe604013fd293c72307b4"
												class="course-card-wide">
												<div>
													<!-- 如果所收藏的微课没有被作者删除 -->
													<span class="thumb">
													<a target="_blank" href="/common/resources/frontMicroCourseAction_play.action?resMicroId=402885823fafe604013fd293c72307b4">
															<img src="http://files.jiaoxueyun.com/develop/resources/microCourse/temp/2013/07-12/media/1373627583901_thumbnail.jpg"
															onerror="this.src='/image/common/nomedia2.jpg'">
													</a>
													</span>
													 <span class="details"> 
													 <span title="金色的草地"
														class="title title-ellipsis"> 金色的草地 </span> 
														<span class="share">
														<span> 
														<a onclick="javascript:void(0);">收藏 </a>
														</span>
														<span> 
														<a onclick="javascript:void(0);"><font color="#ff7575">取消</font> </a>
														</span>
													</span> <span title="金色的草地" class="desc ellipsis"> 简介：金色的草地
													</span> <span class="bottom"> <span class="ins"> 
													<a href="#"> 
													<img src="http://files.jiaoxueyun.com/develop/portrait/20110914001/teacher/2013/07-01/1372663999064.jpg"
														onerror="this.src='/image/login/noPhoto.jpg'" alt="头像" class="ins-thumb">
															</a> <span class="ins-name ellipsisWidth">罗志明 <span
																	title="2013-12-19 10:16:02" class="ins-job-title">
																		语文 &nbsp; &nbsp; 三年级 &nbsp;&nbsp; 21分钟前浏览&nbsp;&nbsp; <span class="laiyuan">来自微课星</span> </span>
															</span>
														</span> <span class="count spb"> 共 <b>1</b> 人评价 <span
																class="conver-status"></span>
														</span> <span class="small-rating"> <span
																style="width: 40.0%"></span>
														</span>
													</span>
													</span>
												</div>
											</li>
											<li id="list_402885823fafe604013fd293c72307b4"
												class="course-card-wide">
												<div>
													<!-- 如果所收藏的微课没有被作者删除 -->
													<span class="thumb">
													<a target="_blank" href="/common/resources/frontMicroCourseAction_play.action?resMicroId=402885823fafe604013fd293c72307b4">
															<img src="http://files.jiaoxueyun.com/develop/resources/microCourse/temp/2013/07-12/media/1373627583901_thumbnail.jpg"
															onerror="this.src='/image/common/nomedia2.jpg'">
													</a>
													</span>
													 <span class="details"> 
													 <span title="金色的草地"
														class="title title-ellipsis"> 金色的草地 </span> 
														<span class="share">
														<span> 
														<a onclick="javascript:void(0);">收藏 </a>
														</span>
														<span> 
														<a onclick="javascript:void(0);"><font color="#ff7575">取消</font> </a>
														</span>
													</span> <span title="金色的草地" class="desc ellipsis"> 简介：金色的草地
													</span> <span class="bottom"> <span class="ins"> 
													<a href="#"> 
													<img src="http://files.jiaoxueyun.com/develop/portrait/20110914001/teacher/2013/07-01/1372663999064.jpg"
														onerror="this.src='/image/login/noPhoto.jpg'" alt="头像" class="ins-thumb">
															</a> <span class="ins-name ellipsisWidth">罗志明 <span
																	title="2013-12-19 10:16:02" class="ins-job-title">
																		语文 &nbsp; &nbsp; 三年级 &nbsp;&nbsp; 21分钟前浏览 </span>
															</span>
														</span> <span class="count spb"> 共 <b>1</b> 人评价 <span
																class="conver-status"></span>
														</span> <span class="small-rating"> <span
																style="width: 20.0%"></span>
														</span>
													</span>
													</span>
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