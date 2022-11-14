<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/xkzy.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/slider.js"></script>
<title>云资源</title>
<style>
.course-box {
	float:left;
}
.zy_search .sea_right .jiaoan{
	height:auto;
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
				<div class="content-widgets white">
					<div class="div2new1">
						<div class="div2left">
							<div class="zydh">
								<div class="title">
									<span>微课资源导航</span>
								</div>
								<div class="wk_list">
									<ul>
										<li class="fl_list">
											<div class="li_hover">
												<p class="title_1">小学微课</p>
												<div class="wk_div">
													<a href="javascript:void(0)">一年级上</a>
													<a href="javascript:void(0)">二年级上</a>
													<a href="javascript:void(0)">四年级上</a>
													<a href="javascript:void(0)">语文</a>
													<a href="javascript:void(0)">数学</a>
												</div>
											</div>
											<div class="njkm" style="display:none;">
												<ul class="nj_ul">
													<li>
														<p>一年级</p>
														<div class="km">
															<a href="javascript:void(0)">英语</a>
															<a href="javascript:void(0)">语文</a>
															<a href="javascript:void(0)">数学</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>二年级</p>
														<div class="km">
															<a href="javascript:void(0)">英语</a>
															<a href="javascript:void(0)">语文</a>
															<a href="javascript:void(0)">数学</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>三年级</p>
														<div class="km">
															<a href="javascript:void(0)">英语</a>
															<a href="javascript:void(0)">语文</a>
															<a href="javascript:void(0)">数学</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>四年级</p>
														<div class="km">
															<a href="javascript:void(0)">英语</a>
															<a href="javascript:void(0)">语文</a>
															<a href="javascript:void(0)">数学</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>五年级</p>
														<div class="km">
															<a href="javascript:void(0)">英语</a>
															<a href="javascript:void(0)">语文</a>
															<a href="javascript:void(0)">数学</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>六年级</p>
														<div class="km">
															<a href="javascript:void(0)">英语</a>
															<a href="javascript:void(0)">语文</a>
															<a href="javascript:void(0)">数学</a>
														</div>
														<div class="clear"></div>
													</li>
												</ul>
											</div>
										</li>
										<li class="fl_list">
											<div class="li_hover ">
												<p class="title_1">初中微课</p>
												<div class="wk_div">
													<a href="javascript:void(0)">七年级上</a>
													<a href="javascript:void(0)">八年级上</a>
													<a href="javascript:void(0)">英语</a>
													<a href="javascript:void(0)">语文</a>
													<a href="javascript:void(0)">数学</a>
													<a href="javascript:void(0)">科学</a>
												</div>
											</div>
											<div class="njkm" style="display:none;">
												<ul class="nj_ul">
													<li>
														<p>初一</p>
														<div class="km">
															<a href="javascript:void(0)">英语</a>
															<a href="javascript:void(0)">语文</a>
															<a href="javascript:void(0)">数学</a>
															<a href="javascript:void(0)">科学</a>
															<a href="javascript:void(0)">生物</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>初二</p>
														<div class="km">
															<a href="javascript:void(0)">英语</a>
															<a href="javascript:void(0)">语文</a>
															<a href="javascript:void(0)">数学</a>
															<a href="javascript:void(0)">科学</a>
															<a href="javascript:void(0)">生物</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>初三</p>
														<div class="km">
															<a href="javascript:void(0)">英语</a>
															<a href="javascript:void(0)">语文</a>
															<a href="javascript:void(0)">数学</a>
															<a href="javascript:void(0)">科学</a>
															<a href="javascript:void(0)">生物</a>
														</div>
														<div class="clear"></div>
													</li>
												</ul>
											</div>
										</li>
										<li class="fl_list">
											<div class="li_hover">
												<p class="title_1">高中微课</p>
												<div class="wk_div">
													<a href="javascript:void(0)">高一上</a>
													<a href="javascript:void(0)">高二上</a>
													<a href="javascript:void(0)">高三下</a>
													<a href="javascript:void(0)">语文</a>
													<a href="javascript:void(0)">数学</a>
													<a href="javascript:void(0)">科学</a>
													<a href="javascript:void(0)">生物</a>
												</div>
											</div>
											<div class="njkm" style="display:none;">
												<ul class="nj_ul">
													<li>
														<p>初一</p>
														<div class="km">
															<a href="javascript:void(0)">英语</a>
															<a href="javascript:void(0)">语文</a>
															<a href="javascript:void(0)">数学</a>
															<a href="javascript:void(0)">科学</a>
															<a href="javascript:void(0)">生物</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>初二</p>
														<div class="km">
															<a href="javascript:void(0)">英语</a>
															<a href="javascript:void(0)">语文</a>
															<a href="javascript:void(0)">数学</a>
															<a href="javascript:void(0)">科学</a>
															<a href="javascript:void(0)">生物</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>初三</p>
														<div class="km">
															<a href="javascript:void(0)">英语</a>
															<a href="javascript:void(0)">语文</a>
															<a href="javascript:void(0)">数学</a>
															<a href="javascript:void(0)">科学</a>
															<a href="javascript:void(0)">生物</a>
														</div>
														<div class="clear"></div>
													</li>
												</ul>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<div class="paihang">
					            <div class="phtitle">
					                  <span>本周浏览排行版</span>
					              </div>
					              <div class="phjoin">
					                <ul>
					                    <li class="first"><p>1</p><a href="javascript:void(0)">古都的秋</a><span>368次浏览</span></li>
					                    <li class="second"><p>2</p><a href="javascript:void(0)">沁园春-长沙</a><span>360次浏览</span></li>
					                    <li class="three"><p>3</p><a href="javascript:void(0)">交集并集和合集</a><span>321次浏览</span></li>
					                    <li><p>4</p><a href="javascript:void(0)">细胞多样化分裂</a><span>284次浏览</span></li>
					                    <li><p>5</p><a href="javascript:void(0)">商品的基本价值</a><span>245次浏览</span></li>
					                    <li><p>6</p><a href="javascript:void(0)">Unit1 My Friend</a><span>215次浏览</span></li>
					                    <li><p>7</p><a href="javascript:void(0)">古都</a><span>184次浏览</span></li>
					                    <li><p>8</p><a href="javascript:void(0)">背影</a><span>115次浏览</span></li>
					                    <li><p>9</p><a href="javascript:void(0)">一元二次方程</a><span>77次浏览</span></li>
					                    <li><p>10</p><a href="javascript:void(0)">函数的概念</a><span>25次浏览</span></li>
					                </ul>
					              </div>
					          </div>
					          <div style="height:487px;" class="paihang">
					            <div class="phtitle">
					                  <span>最新上传</span>
					              </div>
					              <div class="scjoin">
					                <ul>
					                  <li>
					                    <p class="time">6分钟前</p>
					                    <img src="${ctp}/res/images/school.jpg">
					                    <p class="name">李丽</p>
					                    <p class="upload">上传了<a href="javascripr:void(0)">《汽车启动问题》</a></p>
					                  </li>
					                  <li>
					                    <p class="time">11分钟前</p>
					                    <img src="${ctp}/res/images/school.jpg">
					                    <p class="name">李丽</p>
					                    <p class="upload">上传了<a href="javascripr:void(0)">《圆的面积》</a></p>
					                  </li>
					                  <li>
					                    <p class="time">25分钟前</p>
					                    <img src="${ctp}/res/images/school.jpg">
					                    <p class="name">李丽</p>
					                    <p class="upload">上传了<a href="javascripr:void(0)">《函数的概念》</a></p>
					                  </li>
					                  <li>
					                    <p class="time">35分钟前</p>
					                    <img src="${ctp}/res/images/school.jpg">
					                    <p class="name">李丽</p>
					                    <p class="upload">上传了<a href="javascripr:void(0)">《故都的秋》</a></p>
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
					                    <li class="first"><p>1</p><a href="javascript:void(0)">古都的秋</a><span>368人赞</span></li>
					                    <li class="second"><p>2</p><a href="javascript:void(0)">沁园春-长沙</a><span>360人赞</span></li>
					                    <li class="three"><p>3</p><a href="javascript:void(0)">交集并集和合集</a><span>321人赞</span></li>
					                    <li><p>4</p><a href="javascript:void(0)">细胞多样化分裂</a><span>284人赞</span></li>
					                    <li><p>5</p><a href="javascript:void(0)">商品的基本价值</a><span>245人赞</span></li>
					                    <li><p>6</p><a href="javascript:void(0)">Unit1 My Friend</a><span>215人赞</span></li>
					                    <li><p>7</p><a href="javascript:void(0)">古都</a><span>184人赞</span></li>
					                    <li><p>8</p><a href="javascript:void(0)">背影</a><span>115人赞</span></li>
					                    <li><p>9</p><a href="javascript:void(0)">一元二次方程</a><span>77人赞</span></li>
					                    <li><p>10</p><a href="javascript:void(0)">函数的概念</a><span>25人赞</span></li>
					                </ul>
					              </div>
					          </div>
					          <div class="paihang">
					            <div class="phtitle">
					                  <span>本周收藏排行版</span>
					              </div>
					              <div class="phjoin">
					                <ul>
					                    <li class="first"><p>1</p><a href="javascript:void(0)">古都的秋</a><span>368人收藏</span></li>
					                    <li class="second"><p>2</p><a href="javascript:void(0)">沁园春-长沙</a><span>360人收藏</span></li>
					                    <li class="three"><p>3</p><a href="javascript:void(0)">交集并集和合集</a><span>321人收藏</span></li>
					                    <li><p>4</p><a href="javascript:void(0)">细胞多样化分裂</a><span>284人收藏</span></li>
					                    <li><p>5</p><a href="javascript:void(0)">商品的基本价值</a><span>245人收藏</span></li>
					                    <li><p>6</p><a href="javascript:void(0)">Unit1 My Friend</a><span>215人收藏</span></li>
					                    <li><p>7</p><a href="javascript:void(0)">古都</a><span>184人收藏</span></li>
					                    <li><p>8</p><a href="javascript:void(0)">背影</a><span>115人收藏</span></li>
					                    <li><p>9</p><a href="javascript:void(0)">一元二次方程</a><span>77人收藏</span></li>
					                    <li><p>10</p><a href="javascript:void(0)">函数的概念</a><span>25人收藏</span></li>
					                </ul>
					              </div>
					          </div>
					          <div class="paihang">
					            <div class="phtitle">
					                  <span>本周下载排行版</span>
					              </div>
					              <div class="phjoin">
					                <ul>
					                    <li class="first"><p>1</p><a href="javascript:void(0)">古都的秋</a><span>368次下载</span></li>
					                    <li class="second"><p>2</p><a href="javascript:void(0)">沁园春-长沙</a><span>360次下载</span></li>
					                    <li class="three"><p>3</p><a href="javascript:void(0)">交集并集和合集</a><span>321次下载</span></li>
					                    <li><p>4</p><a href="javascript:void(0)">细胞多样化分裂</a><span>284次下载</span></li>
					                    <li><p>5</p><a href="javascript:void(0)">商品的基本价值</a><span>245次下载</span></li>
					                    <li><p>6</p><a href="javascript:void(0)">Unit1 My Friend</a><span>215次下载</span></li>
					                    <li><p>7</p><a href="javascript:void(0)">古都</a><span>184次下载</span></li>
					                    <li><p>8</p><a href="javascript:void(0)">背影</a><span>115次下载</span></li>
					                    <li><p>9</p><a href="javascript:void(0)">一元二次方程</a><span>77次下载</span></li>
					                    <li><p>10</p><a href="javascript:void(0)">函数的概念</a><span>25次下载</span></li>
					                </ul>
					              </div>
					          </div>
						</div>
						<div class="slideshow">
							<div class="s_div1">
								<div class="div1_left">
									<div class="select">
										<input type="text" placeholder="请输入微课关键字">
										<a href="javascript:void(0)"></a>
									</div>
									<div class="a">
						                <div  id="banner" class="pic1">
						                    <ul id="pic" class="pic" >
						                        <li><img src="${pageContext.request.contextPath}/res/images/banner_1.jpg"></li>
												<li><img src="${pageContext.request.contextPath}/res/images/banner_2.gif"></li>
												<li><img src="${pageContext.request.contextPath}/res/images/banner_3.jpg"></li>
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
										<p class="p1">微课资源总数</p>
										<p class="p2">213,214</p>
									</div>
									<div class="grwk">
										<div class="g1">
											<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
											<p>罗志明</p>
											<a href="javascript:void(0)">>进入我的微课</a>
										</div>
										<div class="g2">
											<div class="wktj" style="border-right:1px solid #E8E8E8;">
												<p class="p1">22</p>
												<p class="p2">上传微课</p>
											</div>
											<div  class="wktj">
												<p class="p1">16</p>
												<p class="p2">收藏微课</p>
											</div>
											<div class="clear"></div>
											<a href="javascript:void(0)" class="zysc"></a>
										</div>
									</div>
								</div>
							</div>
					         <div class="wkzy yuwen">
					            <div class="title_1">
					                <p>语文资源<span>Chinese</span></p>
					            </div>
					            <div class="zy_search">
					                <div class="sea_left">
					                <div class="km_lb">
					                	<div class="a">
							                <div  id="banner_1" class="pic1">
							                    <ul id="pic_1" class="pic" >
							                        <li><img src="${pageContext.request.contextPath}/res/images/banner_1.jpg"></li>
													<li><img src="${pageContext.request.contextPath}/res/images/banner_2.gif"></li>
													<li><img src="${pageContext.request.contextPath}/res/images/banner_3.jpg"></li>
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
					                        <a href="javascript:void(0)">人教版</a>
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
					                           <li><a href="javascript:void(0)">·六年级下语文听课反思-1学弈|人教版</a></li>
					                           <li><a href="javascript:void(0)">·六年级下语文听课反思-1学弈|人教版</a></li>
					                           <li><a href="javascript:void(0)">·六年级下语文听课反思-1学弈|人教版</a></li>
					                           <li><a href="javascript:void(0)">·六年级下语文听课反思-1学弈|人教版</a></li>
					                           <li><a href="javascript:void(0)">·六年级下语文听课反思-1学弈|人教版</a></li>
					                           <li><a href="javascript:void(0)">·六年级下语文听课反思-1学弈|人教版</a></li>
					                         </ul>
					                      </div>
					                  </div>
					                </div>
					                <div class="sea_right">
					                <div class="jiaoan">
						                  <div class="nj">
						                  <ul>
						                    <li class="on"><a href="javascript:void(0)" >一年级</a></li>
						                    <li><a href="javascript:void(0)" >二年级</a></li>
						                    <li><a href="javascript:void(0)" >三年级</a></li>
						                    <li><a href="javascript:void(0)" >四年级</a></li>
						                    <li><a href="javascript:void(0)" >五年级</a></li>
						                    <li><a href="javascript:void(0)" >六年级</a></li>
						                  </ul>
						                  </div>
						                  <div class="jc">
						                    <ul>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_video"></span>人教版一年级语文上册教案：济南的冬天(老舍)</span><span class="day">06-26</span>
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
						                       <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>人教版七年级语文上册教案： 散文诗两首 金色花 荷叶</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>人教版七年级语文上册课件：散步</span><span class="day">06-26</span>
						                      </li>
						                    </ul>
						                    <ul style="display:none;">
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_doc"></span>人教版二年级语文上册教案：济南的冬天(老舍)</span><span class="day">06-26</span>
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
						                       <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>人教版七年级语文上册教案： 散文诗两首 金色花 荷叶</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>人教版七年级语文上册课件：散步</span><span class="day">06-26</span>
						                      </li>
						                    </ul>
						                    <ul style="display:none;">
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_doc"></span>人教版三年级语文上册教案：济南的冬天(老舍)</span><span class="day">06-26</span>
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
						                       <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>人教版七年级语文上册教案： 散文诗两首 金色花 荷叶</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>人教版七年级语文上册课件：散步</span><span class="day">06-26</span>
						                      </li>
						                    </ul>
						                    <ul style="display:none;">
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_doc"></span>人教版四年级语文上册教案：济南的冬天(老舍)</span><span class="day">06-26</span>
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
						                       <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>人教版七年级语文上册教案： 散文诗两首 金色花 荷叶</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>人教版七年级语文上册课件：散步</span><span class="day">06-26</span>
						                      </li>
						                    </ul>
						                    <ul style="display:none;">
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_doc"></span>人教版五年级语文上册教案：济南的冬天(老舍)</span><span class="day">06-26</span>
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
						                       <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>人教版七年级语文上册教案： 散文诗两首 金色花 荷叶</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>人教版七年级语文上册课件：散步</span><span class="day">06-26</span>
						                      </li>
						                    </ul>
						                    <ul style="display:none;">
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_doc"></span>人教版六年级语文上册教案：济南的冬天(老舍)</span><span class="day">06-26</span>
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
						                       <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>人教版七年级语文上册教案： 散文诗两首 金色花 荷叶</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>人教版七年级语文上册课件：散步</span><span class="day">06-26</span>
						                      </li>
						                    </ul>
						                  </div>
					                  </div>
					                   <div class="jiaoan">
						                  <div class="nj">
						                  <ul>
						                    <li class="on"><a href="javascript:void(0)" >初一</a></li>
						                    <li><a href="javascript:void(0)" >初二</a></li>
						                    <li><a href="javascript:void(0)" >初三</a></li>
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
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>人教版七年级语文上册教案： 散文诗两首 金色花 荷叶</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>人教版七年级语文上册课件：散步</span><span class="day">06-26</span>
						                      </li>
						                    </ul>
						                    <ul style="display:none">
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_doc"></span>人教版八年级语文上册教案：济南的冬天(老舍)</span><span class="day">06-26</span>
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
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>人教版七年级语文上册教案： 散文诗两首 金色花 荷叶</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>人教版七年级语文上册课件：散步</span><span class="day">06-26</span>
						                      </li>
						                    </ul>
						                    <ul style="display:none">
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_doc"></span>人教版九年级语文上册教案：济南的冬天(老舍)</span><span class="day">06-26</span>
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
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>人教版七年级语文上册教案： 散文诗两首 金色花 荷叶</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>人教版七年级语文上册课件：散步</span><span class="day">06-26</span>
						                      </li>
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
					                <p>数学资源<span>Mathematics</span></p>
					            </div>
					            <div class="zy_search">
					                <div class="sea_left">
					                <div class="km_lb">
					                	<div class="a">
							                <div  id="banner_2" class="pic1">
							                    <ul id="pic_2" class="pic" >
							                        <li><img src="${pageContext.request.contextPath}/res/images/banner_1.jpg"></li>
													<li><img src="${pageContext.request.contextPath}/res/images/banner_2.gif"></li>
													<li><img src="${pageContext.request.contextPath}/res/images/banner_3.jpg"></li>
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
					                        <a href="javascript:void(0)">人教版</a>
					                      </div>
					                  </div>
					                  <div class="bb_div">
					                      <p>精品推荐</p>
					                      <div class="tt_list">
					                         <ul>
					                           <li><a href="javascript:void(0)">·六年级下数学听课反思-1学弈|人教版</a></li>
					                           <li><a href="javascript:void(0)">·2015小升初数学名校招生预测卷1</a></li>
					                           <li><a href="javascript:void(0)">·2015小升初数学高频考点过关演练8</a></li>
					                           <li><a href="javascript:void(0)">·六年级下数学听课反思-1学弈|人教版</a></li>
					                           <li><a href="javascript:void(0)">·六年级下语文听课反思-1学弈|人教版</a></li>
					                           <li><a href="javascript:void(0)">·六年级下语文听课反思-1学弈|人教版</a></li>
					                         </ul>
					                      </div>
					                  </div>
					                </div>
					                <div class="sea_right">
					                <div class="jiaoan">
						                  <div class="nj">
						                  <ul>
						                    <li class="on"><a href="javascript:void(0)" >一年级</a></li>
						                    <li><a href="javascript:void(0)" >二年级</a></li>
						                    <li><a href="javascript:void(0)" >三年级</a></li>
						                    <li><a href="javascript:void(0)" >四年级</a></li>
						                    <li><a href="javascript:void(0)" >五年级</a></li>
						                    <li><a href="javascript:void(0)" >六年级</a></li>
						                  </ul>
						                  </div>
						                  <div class="jc">
						                    <ul>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_doc"></span>一年级上数学专项试题-6和7的加减法|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_swf"></span>一年级上数学专项试题-5、4、3、2加几|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_ppt"></span>一年级上数学专项试题-1～5的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>一年级上数学专项试题-6和7的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>一年级上数学专项试题-8和9的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>一年级上数学专项试题-6和7的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>一年级上数学专项试题-8和9的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                    </ul>
						                  </div>
					                  </div>
					                   <div class="jiaoan">
						                  <div class="nj">
						                  <ul>
						                    <li class="on"><a href="javascript:void(0)" >初一</a></li>
						                    <li><a href="javascript:void(0)" >初二</a></li>
						                    <li><a href="javascript:void(0)" >初三</a></li>
						                  </ul>
						                  </div>
						                  <div class="jc">
						                    <ul>
						                     <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_flash"></span>一年级上数学专项试题-6和7的加减法|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_t"></span>一年级上数学专项试题-5、4、3、2加几|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_music"></span>一年级上数学专项试题-1～5的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>一年级上数学专项试题-6和7的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>一年级上数学专项试题-8和9的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>一年级上数学专项试题-6和7的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>一年级上数学专项试题-8和9的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                    </ul>
						                  </div>
					                  </div>
					                </div>
					            </div>
					         </div>
					         <div class="wkzy yingyu">
					            <div class="title_1">
					                <p>英语资源<span>English</span></p>
					            </div>
					            <div class="zy_search">
					                <div class="sea_left">
					                <div class="km_lb">
					                	<div class="a">
							                <div  id="banner_3" class="pic1">
							                    <ul id="pic_3" class="pic" >
							                        <li><img src="${pageContext.request.contextPath}/res/images/banner_1.jpg"></li>
													<li><img src="${pageContext.request.contextPath}/res/images/banner_2.gif"></li>
													<li><img src="${pageContext.request.contextPath}/res/images/banner_3.jpg"></li>
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
					                        <a href="javascript:void(0)">人教版</a>
					                      </div>
					                  </div>
					                  <div class="bb_div">
					                      <p>精品推荐</p>
					                      <div class="tt_list">
					                         <ul>
					                           <li><a href="javascript:void(0)">·新人教PEP六年级下英语期末复习演示</a></li>
					                           <li><a href="javascript:void(0)">·新人教PEP四年级下英语期末复习演示</a></li>
					                           <li><a href="javascript:void(0)">·新人教PEP三年级下英语期末复习演示</a></li>
					                           <li><a href="javascript:void(0)">·新人教PEP3-6年级下英语期末复习演示</a></li>
					                           <li><a href="javascript:void(0)">·小学英语精美论文-小学英语教师随笔</a></li>
					                            <li><a href="javascript:void(0)">·小学英语精美论文-小学英语教师随笔</a></li>
					                         </ul>
					                      </div>
					                  </div>
					                </div>
					                <div class="sea_right">
					                <div class="jiaoan">
						                  <div class="nj">
						                  <ul>
						                    <li class="on"><a href="javascript:void(0)" >一年级</a></li>
						                    <li><a href="javascript:void(0)" >二年级</a></li>
						                    <li><a href="javascript:void(0)" >三年级</a></li>
						                    <li><a href="javascript:void(0)" >四年级</a></li>
						                    <li><a href="javascript:void(0)" >五年级</a></li>
						                    <li><a href="javascript:void(0)" >六年级</a></li>
						                  </ul>
						                  </div>
						                  <div class="jc">
						                    <ul>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_doc"></span>一年级下英语导学案-Unit6 Farmer and fisherm</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_swf"></span>一年级下英语导学案-Unit4 Postman and police</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_ppt"></span>一年级下英语导学案-Unit2The story of Piggy</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>一年级下英语导学案-Unit2 Small animals|沪教版</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>一年级下英语导学案-Unit1New Year5|沪教版</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>一年级下英语导学案-Unit2 Small animals|沪教版</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>一年级下英语导学案-Unit1New Year5|沪教版</span><span class="day">06-26</span>
						                      </li>
						                    </ul>
						                  </div>
					                  </div>
					                   <div class="jiaoan">
						                  <div class="nj">
						                  <ul>
						                    <li class="on"><a href="javascript:void(0)" >初一</a></li>
						                    <li><a href="javascript:void(0)" >初二</a></li>
						                    <li><a href="javascript:void(0)" >初三</a></li>
						                  </ul>
						                  </div>
						                  <div class="jc">
						                    <ul>
						                     <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_flash"></span>一年级上数学专项试题-6和7的加减法|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_t"></span>一年级上数学专项试题-5、4、3、2加几|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_music"></span>一年级上数学专项试题-1～5的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>一年级上数学专项试题-6和7的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>一年级上数学专项试题-8和9的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_excel"></span>一年级上数学专项试题-6和7的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                      <li>
						                          <span class="style">[教案]</span><span class="wd_title"><span class="icon icon_img"></span>一年级上数学专项试题-8和9的认识|人教新课标</span><span class="day">06-26</span>
						                      </li>
						                    </ul>
						                  </div>
					                  </div>
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
	onStart: function () {
	Each(objs, function (o, i) {
	o.className = tv.Index === i ? "current" : "";
	});
	}, //按钮样式
	Up: false
	});
	tv.Start();
	Each(objs, function (o, i) {
	o.onmouseover = function () {
	o.className = "current";
	tv.Auto = false;
	tv.Index = i;
	tv.Start();
	};
	o.onmouseout = function () {
	o.className = "";
	tv.Auto = true;
	tv.Start();
	};
	});
	var objs = document.getElementById("banner_btn_1").getElementsByTagName("li");
	var tv = new TransformView("banner_1", "pic_1", 250, objs.length, {
	onStart: function () {
	Each(objs, function (o, i) {
	o.className = tv.Index === i ? "current" : "";
	});
	}, //按钮样式
	Up: false
	});
	tv.Start();
	Each(objs, function (o, i) {
	o.onmouseover = function () {
	o.className = "current";
	tv.Auto = false;
	tv.Index = i;
	tv.Start();
	};
	o.onmouseout = function () {
	o.className = "";
	tv.Auto = true;
	tv.Start();
	};
	}); 
	var objs = document.getElementById("banner_btn_2").getElementsByTagName("li");
	var tv = new TransformView("banner_2", "pic_2", 250, objs.length, {
	onStart: function () {
	Each(objs, function (o, i) {
	o.className = tv.Index === i ? "current" : "";
	});
	}, //按钮样式
	Up: false
	});
	tv.Start();
	Each(objs, function (o, i) {
	o.onmouseover = function () {
	o.className = "current";
	tv.Auto = false;
	tv.Index = i;
	tv.Start();
	};
	o.onmouseout = function () {
	o.className = "";
	tv.Auto = true;
	tv.Start();
	};
	}); 
	var objs = document.getElementById("banner_btn_3").getElementsByTagName("li");
	var tv = new TransformView("banner_3", "pic_3", 250, objs.length, {
	onStart: function () {
	Each(objs, function (o, i) {
	o.className = tv.Index === i ? "current" : "";
	});
	}, //按钮样式
	Up: false
	});
	tv.Start();
	Each(objs, function (o, i) {
	o.onmouseover = function () {
	o.className = "current";
	tv.Auto = false;
	tv.Index = i;
	tv.Start();
	};
	o.onmouseout = function () {
	o.className = "";
	tv.Auto = true;
	tv.Start();
	};
	}); 
	
	$(function(){
		/* 左上角小学，初中高中微课 */
		var timer;
		$(".li_hover").hover(function(){
			clearInterval(timer);
			$(".li_hover").removeClass("on");
			$(".njkm").hide();
			$(this).parent().addClass("on");
			$(this).next().show();
		},function(){
			 timer = setTimeout(function () {
				 $(".li_hover").parent().removeClass("on");
				$(".njkm").hide(); 
             }, 1000);
		});
		$(".njkm").hover(function(){
			clearInterval(timer);
		},function(){
			$(".li_hover").parent().removeClass("on");
			$(".njkm").hide(); 
		});
		
		$(".zy_search .sea_right .nj li a").click(function(){
			var i=$(this).parent().index();
			$(this).parent().siblings().removeClass("on");
			$(this).parent().addClass("on");
			$_this=$(this).parent().parent().parent().next().children();
			$_this.hide();
			$_this.eq(i).show();
		});
	});
</script>
</html>