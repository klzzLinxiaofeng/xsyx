<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/bannercommon.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/microcourse_style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/extra/microcourse_index.css" rel="stylesheet">
<title>云资源</title>
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
			<jsp:param value="学科列表" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white" >
					<div class="weike_top">
						<p><span >首页</span><span class="s2">分类资源</span><span class="big">我的资源</span></p>
						<div class="gjz_select"><input type="text" style="width:200px;" placeholder="请输入资源关键词" value=""><button class="btn btn-primary">搜索</button><a href="javascript:void(0)" class="upload"><i class="fa fa-plus"></i> &nbsp; 上传资源</a></div>
						<div class="clear"></div>
					</div>
					<div class="kemu_nav">
						<ul>
							<li><a class="active" href="javascript:void(0)">看过的资源</a></li>
							<li><a href="javascript:void(0)">上传的资源</a></li>
							<li><a href="javascript:void(0)">收藏的资源</a></li>
						</ul>
					</div>
					<div class="wrapper-right">
						<div id="discover-courses-rows">
							<div class="ud-coursecarousel course-list-wrapper collection">
								<div id="yourLike" style="margin-bottom: 10px;">
									
									<div class="rs_platformContainer_1">
										<div class="main" style="margin-left:-1px;">
											<div class="top">
												<p class="left"><span>全部资源</span>（0个）
												<a href="javascript:void(0)" class="on">教学设计</a>
												<a href="javascript:void(0)">导学案</a>
												<a href="javascript:void(0)">课件</a>
												<a href="javascript:void(0)">素材</a>
												<a href="javascript:void(0)">作业/习题</a>
												<a href="javascript:void(0)">试卷</a>
												<a href="javascript:void(0)">其他</a>
												</p>
												<p class="right">排序：&#12288;<a class="on" href="javascript:void(0)">评分<i class="fa fa-arrow-down"></i></a><a href="javascript:void(0)">浏览量<i class="fa fa-arrow-down"></i></a><a href="javascript:void(0)">最新<i class="fa fa-arrow-down"></i></a></p>
												<div class="clear"></div>
											</div>
											<div class="zy_list">
												<div class="empty"  style="display:none">
													<p>您暂未浏览过任何资源</p>
												</div>
												<div class="xkzy_list" >
													<dl>
														<dt><img src="${ctp}/res/images/school.jpg"></dt>
														<dd>
															<div class="item-msg">
																<div class="item-title">
																	<span class="res-swf icon-file res-iconb"></span>
																	<span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
																		<a href="javascript:void(0)" target="_blank">《a o e i u ü》教学动画</a>
																	</span>
																</div>
																<div class="i1">教材目录：2 分数乘法 分数乘法</div>
																<div class="i1">上传时间：2015-1-22</div>
																<div class="i2">
																	<span class="left"><img src="${ctp}/res/images/noPhoto.jpg">李丽</span>
																	<span class="right">已有46人评价<b class="zan">49</b></span>
																</div>
																<div class="cz_btn"><a class="shoucan">收藏</a><a class="qingchu">清除</a></div>
																
															</div>
														</dd>
													</dl>
													<dl>
														<dt><img src="${ctp}/res/images/school.jpg"></dt>
														<dd>
															<div class="item-msg">
																<div class="item-title">
																	<span class="res-doc icon-file res-iconb"></span>
																	<span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
																		<a href="javascript:void(0)" target="_blank">《a o e i u ü》教学动画</a>
																	</span>
																</div>
																<div class="i1">教材目录：2 分数乘法 分数乘法</div>
																<div class="i1">上传时间：2015-1-22</div>
																<div class="i2">
																	<span class="left"><img src="${ctp}/res/images/noPhoto.jpg">李丽</span>
																	<span class="right">已有46人评价<b class="zan">49</b></span>
																</div>
																<div class="cz_btn"><a class="shoucan">收藏</a><a class="qingchu">清除</a></div>
															</div>
														</dd>
													</dl>
													<dl>
														<dt><img src="${ctp}/res/images/school.jpg"></dt>
														<dd>
															<div class="item-msg">
																<div class="item-title">
																	<span class="res-ppt icon-file res-iconb"></span>
																	<span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
																		<a href="javascript:void(0)" target="_blank">《a o e i u ü》教学动画</a>
																	</span>
																</div>
																<div class="i1">教材目录：2 分数乘法 分数乘法</div>
																<div class="i1">上传时间：2015-1-22</div>
																<div class="i2">
																	<span class="left"><img src="${ctp}/res/images/noPhoto.jpg">李丽</span>
																	<span class="right">已有46人评价<b class="zan">49</b></span>
																</div>
																<div class="cz_btn"><a class="shoucan">收藏</a><a class="qingchu">清除</a></div>
															</div>
														</dd>
													</dl>
													<dl>
														<dt><img src="${ctp}/res/images/school.jpg"></dt>
														<dd>
															<div class="item-msg">
																<div class="item-title">
																	<span class="res-doc icon-file res-iconb"></span>
																	<span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
																		<a href="javascript:void(0)" target="_blank">《a o e i u ü》教学动画</a>
																	</span>
																</div>
																<div class="i1">教材目录：2 分数乘法 分数乘法</div>
																<div class="i1">上传时间：2015-1-22</div>
																<div class="i2">
																	<span class="left"><img src="${ctp}/res/images/noPhoto.jpg">李丽</span>
																	<span class="right">已有46人评价<b class="zan">49</b></span>
																</div>
																<div class="cz_btn"><a class="shoucan">收藏</a><a class="qingchu">清除</a></div>
															</div>
														</dd>
													</dl>
													<dl>
														<dt><img src="${ctp}/res/images/school.jpg"></dt>
														<dd>
															<div class="item-msg">
																<div class="item-title">
																	<span class="res-rar icon-file res-iconb"></span>
																	<span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
																		<a href="javascript:void(0)" target="_blank">《a o e i u ü》教学动画</a>
																	</span>
																</div>
																<div class="i1">教材目录：2 分数乘法 分数乘法</div>
																<div class="i1">上传时间：2015-1-22</div>
																<div class="i2">
																	<span class="left"><img src="${ctp}/res/images/noPhoto.jpg">李丽</span>
																	<span class="right">已有46人评价<b class="zan">49</b></span>
																</div>
																<div class="cz_btn"><a class="shoucan">收藏</a><a class="qingchu">清除</a></div>
															</div>
														</dd>
													</dl>
													<dl>
														<dt><img src="${ctp}/res/images/school.jpg"></dt>
														<dd>
															<div class="item-msg">
																<div class="item-title">
																	<span class="res-html icon-file res-iconb"></span>
																	<span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
																		<a href="javascript:void(0)" target="_blank">《a o e i u ü》教学动画</a>
																	</span>
																</div>
																<div class="i1">教材目录：2 分数乘法 分数乘法</div>
																<div class="i1">上传时间：2015-1-22</div>
																<div class="i2">
																	<span class="left"><img src="${ctp}/res/images/noPhoto.jpg">李丽</span>
																	<span class="right">已有46人评价<b class="zan">49</b></span>
																</div>
																<div class="cz_btn"><a class="shoucan">收藏</a><a class="qingchu">清除</a></div>
															</div>
														</dd>
													</dl>
													<dl>
														<dt><img src="${ctp}/res/images/school.jpg"></dt>
														<dd>
															<div class="item-msg">
																<div class="item-title">
																	<span class="res-swf icon-file res-iconb"></span>
																	<span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
																		<a href="javascript:void(0)" target="_blank">《a o e i u ü》教学动画</a>
																	</span>
																</div>
																<div class="i1">教材目录：2 分数乘法 分数乘法</div>
																<div class="i1">上传时间：2015-1-22</div>
																<div class="i2">
																	<span class="left"><img src="${ctp}/res/images/noPhoto.jpg">李丽</span>
																	<span class="right">已有46人评价<b class="zan">49</b></span>
																</div>
																<div class="cz_btn"><a class="shoucan">收藏</a><a class="qingchu">清除</a></div>
															</div>
														</dd>
													</dl>
													<dl>
														<dt><img src="${ctp}/res/images/school.jpg"></dt>
														<dd>
															<div class="item-msg">
																<div class="item-title">
																	<span class="res-swf icon-file res-iconb"></span>
																	<span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
																		<a href="javascript:void(0)" target="_blank">《a o e i u ü》教学动画</a>
																	</span>
																</div>
																<div class="i1">教材目录：2 分数乘法 分数乘法</div>
																<div class="i1">上传时间：2015-1-22</div>
																<div class="i2">
																	<span class="left"><img src="${ctp}/res/images/noPhoto.jpg">李丽</span>
																	<span class="right">已有46人评价<b class="zan">49</b></span>
																</div>
																<div class="cz_btn"><a class="shoucan">收藏</a><a class="qingchu">清除</a></div>
															</div>
														</dd>
													</dl>
												</div>
												<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
													<jsp:param name="id" value="studentAid_list_content" />
													<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
													<jsp:param name="pageSize" value="${page.pageSize}" />
												</jsp:include>
												<div class="clear"></div>
											</div>
										</div>
										<div class="clear"></div>
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
	$(function(){
		$(".rs_platformContainer_1 .main .top .left a").click(function(){
			$(".rs_platformContainer_1 .main .top .left a").removeClass("on");
			$(this).addClass("on");
		});
	});
</script>
</html>