<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<#assign basePath=contextPath />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微课云资源</title>
<link href="${basePath}/res/plugin/falgun/css/bootstrap.css"
	rel="stylesheet">
<link href="${basePath}/res/plugin/falgun/css/bootstrap-responsive.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${basePath}/res/plugin/falgun/css/font-awesome.css">
<link href="${basePath}/res/plugin/falgun/css/chosen.css"
	rel="stylesheet">
<link href="${basePath}/res/plugin/falgun/css/styles.css"
	rel="stylesheet">
	<script src="${basePath}/res/plugin/falgun/js/jquery.js"></script>
<link href="${basePath}/res/css/extra/xkzy.css" rel="stylesheet">
<link href="${basePath}/res/css/extra/add.css" rel="stylesheet">
<script src="${basePath}/res/plugin/falgun/js/bootstrap.js"></script>
<script src="${basePath}/res/plugin/falgun/js/accordion.nav.js"></script>
<script src="${basePath}/res/plugin/falgun/js/custom.js"></script>
<script src="${basePath}/res/plugin/falgun/js/respond.min.js"></script>
<script
	src="${basePath}/res/plugin/falgun/js/ios-orientationchange-fix.js"></script>
<script src="${basePath}/res/plugin/falgun/js/jquery.dataTables.js"></script>
<script src="${basePath}/res/plugin/falgun/js/ZeroClipboard.js"></script>
<script src="${basePath}/res/plugin/falgun/js/dataTables.bootstrap.js"></script>
<script src="${basePath}/res/plugin/falgun/js/TableTools.js"></script>
<script src="${basePath}/res/plugin/falgun/js/chosen.jquery.js"></script>
<script src="${basePath}/res/plugin/falgun/js/stepy.jquery.js"></script>
<script type="text/javascript"
	src="${basePath}/res/plugin/layer/layer.js"></script>
<script type="text/javascript"
	src="${basePath}/res/plugin/layer/extend/layer.ext.js"></script>
<script type="text/javascript"
	src="${basePath}/res/js/common/plugin/lodop/LodopFuncs.js"></script>
<script type="text/javascript" src="${basePath}/res/js/slider.js"></script>
<script src="${basePath}/res/js/common/plugin/swfobject/swfobject.js"></script>
<style>
.course-box {
	float: left;
}

.zy_list ul {
	height: 440px;
	overflow: hidden;
}

.upload a {
	display: block;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}
.div1_right{
	background:url(../res/images/icon_ggzy.png) no-repeat;
	border: 1px solid #0d7bd5;
	height: 286px;
}
</style>
</head>
<body>
	<div class="container-fluid" style="margin-top: 20px;">
		<div class="row-fluid" style="margin: 0 auto; width: 1082px;">
			<div class="span12">
				<div class="content-widgets white" >
					<div class="div2new1" style="height:2380px;">
						<div class="div2left">
							<div class="zydh">
								<div class="title">
									<span>微课资源导航</span>
								</div>
								<div class="wk_list">
									<ul>
										<li class="fl_list" id="xiaoxue" style="display: none;">
											<div class="li_hover">
												<p class="title_1">小学微课</p>
												<div class="wk_div">
													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&gradeCode=21">一年级</a>
													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&gradeCode=22">二年级</a>
													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&gradeCode=23">三年级</a>
													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&gradeCode=24">四年级</a>
													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&gradeCode=25">五年级</a>
													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&gradeCode=26">六年级</a>
													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13">语文</a>
													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14">数学</a>
													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41">英语</a>
												</div>
											</div>
											<div class="njkm" style="display: none;">
												<ul class="nj_ul">
													<li>
														<p>一年级</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13&gradeCode=21">语文</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14&gradeCode=21">数学</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41&gradeCode=21">英语</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>二年级</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13&gradeCode=22">语文</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14&gradeCode=22">数学</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41&gradeCode=22">英语</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>三年级</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13&gradeCode=23">语文</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14&gradeCode=23">数学</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41&gradeCode=23">英语</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>四年级</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13&gradeCode=24">语文</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14&gradeCode=24">数学</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41&gradeCode=24">英语</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>五年级</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13&gradeCode=25">语文</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14&gradeCode=25">数学</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41&gradeCode=25">英语</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>六年级</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13&gradeCode=26">语文</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14&gradeCode=26">数学</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41&gradeCode=26">英语</a>
														</div>
														<div class="clear"></div>
													</li>
												</ul>
											</div>
										</li>
										<li class="fl_list" id="chuzhong" style="display: none;">
											<div class="li_hover ">
												<p class="title_1">初中微课</p>
												<div class="wk_div">

													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&gradeCode=31">七年级</a>
													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&gradeCode=32">八年级</a>
													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&gradeCode=33">九年级</a>
												</div>
											</div>
											<div class="njkm" style="display: none;">
												<ul class="nj_ul">
													<li>
														<p>初一</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=13&gradeCode=31">语文</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=14&gradeCode=31">数学</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=41&gradeCode=31">英语</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>初二</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=13&gradeCode=32">语文</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=14&gradeCode=32">数学</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=41&gradeCode=32">英语</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>初三</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=13&gradeCode=33">语文</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=14&gradeCode=33">数学</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=41&gradeCode=33">英语</a>
														</div>
														<div class="clear"></div>
													</li>
												</ul>
											</div>
										</li>
										<li class="fl_list" id="gaozhong" style="display: none;">
											<div class="li_hover ">
												<p class="title_1">高中微课</p>
												<div class="wk_div">

													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&gradeCode=0">必修1</a>
													<a
														href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&gradeCode=0">必修2</a>
												</div>
											</div>
											<div class="njkm" style="display: none;">
												<ul class="nj_ul">
													<li>
														<p>必修1</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=13&gradeCode=0">语文</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=14&gradeCode=0">数学</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=41&gradeCode=0">英语</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>必修2</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=13&gradeCode=0">语文</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=14&gradeCode=0">数学</a>
															<a
																href="${basePath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=41&gradeCode=0">英语</a>
														</div>
														<div class="clear"></div>
													</li>
												</ul>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class="paihang" style="height: 387px;">
								<div class="phtitle">
									<span>资源评分榜</span>
								</div>
								<div class="phjoin">
									<ul id="click">
									</ul>
								</div>
							</div>
							<div style="height: 487px;" class="paihang">
								<div class="phtitle">
									<span>最新上传</span>
								</div>
								<div class="scjoin">
									<ul id="near">

									</ul>
								</div>
							</div>
							<div class="paihang" style="height: 387px;">
								<div class="phtitle">
									<span>本周点赞排行榜</span>
								</div>
								<div class="phjoin">
									<ul id="like">
									</ul>
								</div>
							</div>
							<div class="paihang" style="height: 387px;">
								<div class="phtitle">
									<span>本周收藏排行榜</span>
								</div>
								<div class="phjoin">
									<ul id="fav">
									</ul>
								</div>
							</div>
							<div class="paihang" style="height: 387px;">
								<div class="phtitle">
									<span>本周下载排行榜</span>
								</div>
								<div class="phjoin">
									<ul id="down">
									</ul>
								</div>
							</div>
						</div>
						<div class="slideshow">
							<div class="s_div1">
								<div class="div1_left">
									<div class="select">
										<form
											action="${basePath}/resource/searcher/result/index?isMicro=true&resType=1"
											method="post" id="searchForm" style="margin: 0">
											<input name="title" id="title" type="text"
												placeholder="请输入微课标题" /> <a
												onclick='$("#searchForm").submit();'
												href="javascript:void(0)"></a>
										</form>
									</div>
									<div class="a">
										<div id="banner" class="pic1">
											<ul id="pic" class="pic">
												<li><img src="${basePath}/res/images/banner_1.jpg"></li>
												<li><img src="${basePath}/res/images/banner_2.jpg"></li>
												<li><img src="${basePath}/res/images/banner_3.jpg"></li>
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
										<p class="p1">资源统计</p>
										<p class="p1" style="margin-top: 10px;font-size: 28px;">微课资源</p>
										<p class="p2"></p>
									</div>
							</div>
							
							<div class="s_div2" style="clear: both;">
								<div class="zy_list">
									<ul>
										<li>
											<div class="mask">
												<div class="gxqwk"></div>
											</div>
										</li>
										<#list vosInterestMicro as interest>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${interest.id?c}'><img
															src='${interest.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${interest.title}' class='ellipsis'>${interest.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>${interest.subjectName!"语文"}>${interest.gradeName!"五年级"}</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${interest.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${interest.imgSrc}"></a><span class='name'>${interest.userName}</span>
														<span class='day'>${interest.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>


									</ul>
									<div class="clear"></div>
									<div class="l_more"></div>
								</div>
							</div>
							<div class="s_div2">
								<div class="wkzy yuwen">
									<div class="title">
										<p>语文</p>
										<div style="position: relative">
											<ul>
												<li><a href="javascript:void(0)">四年级</a></li>
												<li class="on"><a href="javascript:void(0)">五年级</a></li>
												<li><a href="javascript:void(0)">六年级</a></li>
												<li><a href="javascript:void(0)">七年级</a></li>
												<li><a href="javascript:void(0)">八年级</a></li>
												<li><a href="javascript:void(0)">九年级</a></li>
											</ul>
											<div class="clear"></div>
										</div>
									</div>
									<div class="zy_list">
										<ul style="display: none">
											<#list vosFourChinese as fourChinese>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${fourChinese.id?c}'><img
															src='${fourChinese.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${fourChinese.title}' class='ellipsis'>${fourChinese.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>语文>四年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${fourChinese.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${fourChinese.imgSrc}"></a><span class='name'>${fourChinese.userName}</span>
														<span class='day'>${fourChinese.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<ul>
											<#list vosFiveChinese as fiveChinese>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${fiveChinese.id?c}'><img
															src='${fiveChinese.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${fiveChinese.title}' class='ellipsis'>${fiveChinese.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>语文>五年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${fiveChinese.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${fiveChinese.imgSrc}"></a><span class='name'>${fiveChinese.userName}</span>
														<span class='day'>${fiveChinese.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<ul style="display: none">
											<#list vosSixChinese as sixChinese>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${sixChinese.id?c}'><img
															src='${sixChinese.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${sixChinese.title}' class='ellipsis'>${sixChinese.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>语文>六年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${sixChinese.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${sixChinese.imgSrc}"></a><span class='name'>${sixChinese.userName}</span>
														<span class='day'>${sixChinese.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<ul style="display: none">
										<#list vosMiddleOneChinese as middleOneChinese>
										<li>
											<div class='mask'>
												<span class='thumbSize'><a
													href='${basePath}/resource/viewer/${middleOneChinese.id?c}'><img
														src='${middleOneChinese.thumbnailUrl!""}'></a></span>
												<div class='title'>
													<span title='${middleOneChinese.title}' class='ellipsis'>${middleOneChinese.title}</span>
													<span class='time'></span>
												</div>
												<div class='details'>
													<span class='d1'>语文>七年级</span> <span class='d2'><a
														href='${basePath}/resource/viewer/${middleOneChinese.id?c}'></a></span>
												</div>
												<div class='num'></div>
												<div class='instructors'>
													<a href='javascript:void(0)'> <img
														src="${middleOneChinese.imgSrc}"></a><span class='name'>${middleOneChinese.userName}</span>
													<span class='day'>${middleOneChinese.createDate?string("yyyy-MM-dd")}上传
													</span>
												</div>
											</div>
										</li> </#list>
										</ul>
										<ul style="display: none">
											<#list vosMiddleTwoChinese as middleTwoChinese>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${middleTwoChinese.id?c}'><img
															src='${middleTwoChinese.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${middleTwoChinese.title}' class='ellipsis'>${middleTwoChinese.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>语文>八年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${middleTwoChinese.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${middleTwoChinese.imgSrc}"></a><span class='name'>${middleTwoChinese.userName}</span>
														<span class='day'>${middleTwoChinese.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<ul style="display: none">
											<#list vosMiddleThreeChinese as middleThreeChinese>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${middleThreeChinese.id?c}'><img
															src='${middleThreeChinese.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${middleThreeChinese.title}' class='ellipsis'>${middleThreeChinese.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>语文>九年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${middleThreeChinese.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${middleThreeChinese.imgSrc}"></a><span
															class='name'>${middleThreeChinese.userName}</span> <span
															class='day'>${middleThreeChinese.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<div class="clear"></div>
										<div class="l_more"></div>
									</div>
								</div>
								<div class="wkzy shuxue">
									<div class="title">
										<p>数学</p>
										<div style="position: relative">
											<ul style="height: 33px; overflow: hidden">
												<li class="on"><a href="javascript:void(0)">四年级</a></li>
												<li><a href="javascript:void(0)">五年级</a></li>
												<li><a href="javascript:void(0)">六年级</a></li>
												<li><a href="javascript:void(0)">七年级</a></li>
												<li><a href="javascript:void(0)">八年级</a></li>
												<li><a href="javascript:void(0)">九年级</a></li>
											</ul>
											<div class="clear"></div>
										</div>
									</div>
									<div class="zy_list">
										<ul>
											<#list vosFourMath as fourMath>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${fourMath.id?c}'><img
															src='${fourMath.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${fourMath.title}' class='ellipsis'>${fourMath.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>数学>四年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${fourMath.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${fourMath.imgSrc}"></a><span class='name'>${fourMath.userName}</span>
														<span class='day'>${fourMath.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<ul style="display: none">
											<#list vosFiveMath as fiveMath>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${fiveMath.id?c}'><img
															src='${fiveMath.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${fiveMath.title}' class='ellipsis'>${fiveMath.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>数学>五年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${fiveMath.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${fiveMath.imgSrc}"></a><span class='name'>${fiveMath.userName}</span>
														<span class='day'>${fiveMath.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<ul style="display: none">
											<#list vosSixMath as sixMath>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${sixMath.id?c}'><img
															src='${sixMath.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${sixMath.title}' class='ellipsis'>${sixMath.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>数学>六年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${sixMath.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${sixMath.imgSrc}"></a><span class='name'>${sixMath.userName}</span>
														<span class='day'>${sixMath.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<ul style="display: none">
										<#list vosMiddleOneMath as middleOneMath>
										<li>
											<div class='mask'>
												<span class='thumbSize'><a
													href='${basePath}/resource/viewer/${middleOneMath.id?c}'><img
														src='${middleOneMath.thumbnailUrl!""}'></a></span>
												<div class='title'>
													<span title='${middleOneMath.title}' class='ellipsis'>${middleOneMath.title}</span>
													<span class='time'></span>
												</div>
												<div class='details'>
													<span class='d1'>数学>七年级</span> <span class='d2'><a
														href='${basePath}/resource/viewer/${middleOneMath.id?c}'></a></span>
												</div>
												<div class='num'></div>
												<div class='instructors'>
													<a href='javascript:void(0)'> <img
														src="${middleOneMath.imgSrc}"></a><span class='name'>${middleOneMath.userName}</span>
													<span class='day'>${middleOneMath.createDate?string("yyyy-MM-dd")}上传
													</span>
												</div>
											</div>
										</li> </#list>
										</ul>
										<ul style="display: none">
											<#list vosMiddleTwoMath as middleTwoMath>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${middleTwoMath.id?c}'><img
															src='${middleTwoMath.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${middleTwoMath.title}' class='ellipsis'>${middleTwoMath.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>数学>八年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${middleTwoMath.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${middleTwoMath.imgSrc}"></a><span class='name'>${middleTwoMath.userName}</span>
														<span class='day'>${middleTwoMath.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<ul style="display: none">
											<#list vosMiddleThreeMath as middleThreeMath>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${middleThreeMath.id?c}'><img
															src='${middleThreeMath.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${middleThreeMath.title}' class='ellipsis'>${middleThreeMath.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>数学>九年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${middleThreeMath.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${middleThreeMath.imgSrc}"></a><span class='name'>${middleThreeMath.userName}</span>
														<span class='day'>${middleThreeMath.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<div class="clear"></div>
										<div class="l_more"></div>
									</div>
								</div>
								<div class="wkzy yingyu">
									<div class="title">
										<p>英语</p>
										<div style="position: relative">
											<ul style="height: 33px; overflow: hidden">
												<li class="on"><a href="javascript:void(0)">四年级</a></li>

												<li><a href="javascript:void(0)">五年级</a></li>
												<li><a href="javascript:void(0)">六年级</a></li>
												<li><a href="javascript:void(0)">七年级</a></li>
												<li><a href="javascript:void(0)">八年级</a></li>
												<li><a href="javascript:void(0)">九年级</a></li>
											</ul>
											<div class="clear"></div>
										</div>
									</div>
									<div class="zy_list">
										<ul>
											<#list vosFourEnglish as fourEnglish>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${fourEnglish.id?c}'><img
															src='${fourEnglish.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${fourEnglish.title}' class='ellipsis'>${fourEnglish.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>英语>四年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${fourEnglish.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${fourEnglish.imgSrc}"></a><span class='name'>${fourEnglish.userName}</span>
														<span class='day'>${fourEnglish.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<ul style="display: none">
											<#list vosFiveEnglish as fiveEnglish>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${fiveEnglish.id?c}'><img
															src='${fiveEnglish.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${fiveEnglish.title}' class='ellipsis'>${fiveEnglish.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>英语>五年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${fiveEnglish.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${fiveEnglish.imgSrc}"></a><span class='name'>${fiveEnglish.userName}</span>
														<span class='day'>${fiveEnglish.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<ul style="display: none">
											<#list vosSixEnglish as sixEnglish>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${sixEnglish.id?c}'><img
															src='${sixEnglish.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${sixEnglish.title}' class='ellipsis'>${sixEnglish.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>英语>六年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${sixEnglish.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${sixEnglish.imgSrc}"></a><span class='name'>${sixEnglish.userName}</span>
														<span class='day'>${sixEnglish.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<ul style="display: none">
										<#list vosMiddleOneEnglish as middleOneEnglish>
										<li>
											<div class='mask'>
												<span class='thumbSize'><a
													href='${basePath}/resource/viewer/${middleOneEnglish.id?c}'><img
														src='${middleOneEnglish.thumbnailUrl!""}'></a></span>
												<div class='title'>
													<span title='${middleOneEnglish.title}' class='ellipsis'>${middleOneEnglish.title}</span>
													<span class='time'></span>
												</div>
												<div class='details'>
													<span class='d1'>英语>七年级</span> <span class='d2'><a
														href='${basePath}/resource/viewer/${middleOneEnglish.id?c}'></a></span>
												</div>
												<div class='num'></div>
												<div class='instructors'>
													<a href='javascript:void(0)'> <img
														src="${middleOneEnglish.imgSrc}"></a><span class='name'>${middleOneEnglish.userName}</span>
													<span class='day'>${middleOneEnglish.createDate?string("yyyy-MM-dd")}上传
													</span>
												</div>
											</div>
										</li> </#list>
										</ul>
										<ul style="display: none">
											<#list vosMiddleTwoEnglish as middleTwoEnglish>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${middleTwoEnglish.id?c}'><img
															src='${middleTwoEnglish.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${middleTwoEnglish.title}' class='ellipsis'>${middleTwoEnglish.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>英语>八年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${middleTwoEnglish.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${middleTwoEnglish.imgSrc}"></a><span class='name'>${middleTwoEnglish.userName}</span>
														<span class='day'>${middleTwoEnglish.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<ul style="display: none">
											<#list vosMiddleThreeEnglish as middleThreeEnglish>
											<li>
												<div class='mask'>
													<span class='thumbSize'><a
														href='${basePath}/resource/viewer/${middleThreeEnglish.id?c}'><img
															src='${middleThreeEnglish.thumbnailUrl!""}'></a></span>
													<div class='title'>
														<span title='${middleThreeEnglish.title}' class='ellipsis'>${middleThreeEnglish.title}</span>
														<span class='time'></span>
													</div>
													<div class='details'>
														<span class='d1'>英语>九年级</span> <span class='d2'><a
															href='${basePath}/resource/viewer/${middleThreeEnglish.id?c}'></a></span>
													</div>
													<div class='num'></div>
													<div class='instructors'>
														<a href='javascript:void(0)'> <img
															src="${middleThreeEnglish.imgSrc}"></a><span
															class='name'>${middleThreeEnglish.userName}</span> <span
															class='day'>${middleThreeEnglish.createDate?string("yyyy-MM-dd")}上传
														</span>
													</div>
												</div>
											</li> </#list>
										</ul>
										<div class="clear"></div>
										<div class="l_more"></div>
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
      onStart: function() {
          Each(objs, function(o, i) {
              o.className = tv.Index === i ? "current" : "";
          });
      }, //按钮样式
      Up: false
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
  $(function() {
      var timer;
      $(".li_hover").hover(function() {
          clearInterval(timer);
          $(".li_hover").removeClass("on");
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

      $(".wkzy .title").on("click", ".shouqi", function() {
          $(this).removeClass("shouqi").addClass("zhankai");
          /* $(this).html("展开"); */
          $(this).prev().prev().css("height", "33px");
      });
      $(".wkzy .title").on("click", ".zhankai", function() {
          $(this).removeClass("zhankai").addClass("shouqi");
          /* $(this).html("收起"); */
          $(this).prev().prev().css("height", "auto");
      });
      $(".wkzy .title ul li a").click(
              function() {
                  var i = $(this).parent().index();
                  $(this).parent().siblings().removeClass("on");
                  $(this).parent().addClass("on");
                  $_this = $(this).parent().parent().parent().parent().next()
                          .children();
                  $_this.hide();
                  $_this.eq(i).show();
              });
	
	$.ajax({
	    url: "${basePath}/ajax/resource/getAjaxNearResource",
	    type:'post',    
	    data: {resType:1},   
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	loadNearResource(data);
	     }  
	});
	
	$.ajax({
	    url: "${basePath}/ajax/resource/getAjaxMicCount",
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	loadResCoun(data);
	     }  
	});
   });
   
   $.ajax({
		    url: "${basePath}/ajax/resource/getAjaxStageCode",
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadStageCode(data);
		     }  
	});
	
	//获取浏览排行榜
    $.ajax({
	    url: "${basePath}/ajax/resource/getAjaxMicroClick",
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	//loadMicroClick(data);
	     }  
	 });
	 
	//获取资源评分排行榜
    $.ajax({
	    url: "${basePath}/ajax/resource/getAjaxScoreOrder",
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	loadScoreOrder(data);
	     }  
	});
   
    //获取点赞排行榜
    $.ajax({
	    url: "${basePath}/ajax/resource/getAjaxMicroLike",
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	loadMicroLike(data);
	     }  
	});
 
    //获取收藏排行榜
    $.ajax({
	    url: "${basePath}/ajax/resource/getAjaxMicroFav",
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	loadMicroFav(data);
	     }  
	});
 
    //获取下载排行榜
    $.ajax({
	    url: "${basePath}/ajax/resource/getAjaxMicroDown",
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	loadMicroDown(data);
	     }  
	});
   
   	function loadScoreOrder(data){
		var i;
		if(data.length > 0) {
			$.each(data, function(index, value) {
				i = index+1;
				if(index==0){
					$("#click").append('<li class="first"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.id +'">'+ value.title +'</a></li>');
				}else if(index==1){
					$("#click").append('<li class="second"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.id +'">'+ value.title +'</a></li>');
				}else if(index==2){
					$("#click").append('<li class="three"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.id +'">'+ value.title +'</a></li>');
				}else{
					$("#click").append('<li class=""><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.id +'">'+ value.title +'</a></li>');
				}
			});
		}
	}


	function loadNearResource(data){
		if(data.length > 0) {
			$.each(data, function(index, value) {
				$("#near").append('<li> <img src="'+ value.imgSrc +'"> <p class="name">'+ value.userName +'</p> <p class="upload">上传了 <a href="${basePath}/resource/viewer/'+ value.id +'">'+ value.title +'</a></p></li>');
			});
		}
	}

	function loadResCoun(data){
		$(".zyzs .p2").html(data);
	}
	
	function loadStageCode(data){
		if(data.toString().indexOf(2) > -1) {
			$("#xiaoxue").show();
		}
		if(data.toString().indexOf(3) > -1) {
			$("#chuzhong").show();
		}
		if(data.toString().indexOf(4) > -1) {
			$("#gaozhong").show();
		}
	}
	
	//浏览
	function loadMicroClick(data){
		var i;
		if(data.length > 0) {
			$.each(data, function(index, value) {
				i = index+1;
				if(index==0){
					$("#click").append('<li class="first"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==1){
					$("#click").append('<li class="second"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==2){
					$("#click").append('<li class="three"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else{
					$("#click").append('<li class=""><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}
			});
		}
	}
	
	//点赞
	function loadMicroLike(data){
		var i;
		if(data.length > 0) {
			$.each(data, function(index, value) {
				i = index+1;
				if(index==0){
					$("#like").append('<li class="first"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==1){
					$("#like").append('<li class="second"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==2){
					$("#like").append('<li class="three"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else{
					$("#like").append('<li class=""><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}
			});
		}
	}
	
	function loadMicroFav(data){
		var i;
		if(data.length > 0) {
			$.each(data, function(index, value) {
				i = index+1;
				if(index==0){
					$("#fav").append('<li class="first"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==1){
					$("#fav").append('<li class="second"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==2){
					$("#fav").append('<li class="three"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else{
					$("#fav").append('<li class=""><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}
			});
		}
	}
	
	function loadMicroDown(data){
		var i;
		if(data.length > 0) {
			$.each(data, function(index, value) {
				i = index+1;
				if(index==0){
					$("#down").append('<li class="first"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==1){
					$("#down").append('<li class="second"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==2){
					$("#down").append('<li class="three"><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else{
					$("#down").append('<li class=""><p>'+i+'</p><a href="${basePath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}
			});
		}
	}
	
    </script>
</html>