<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/views/embedded/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<head>  
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">  
        <meta http-equiv="pragma" content="no-cache">  
        <meta http-equiv="cache-control" content="no-cache">  
        <meta http-equiv="expires" content="0">  
</head> 
  <title>云资源</title> 
	<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/bootstrap-responsive.css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/falgun/css/font-awesome.css">
	<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/chosen.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/styles.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/res/css/extra/xkzy.css" rel="stylesheet" >
	<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
	<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/accordion.nav.js"></script>
	<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/custom.js"></script>
	<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/respond.min.js"></script>
	<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/ios-orientationchange-fix.js"></script>
	<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/jquery.dataTables.js"></script>
	<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/ZeroClipboard.js"></script>
	<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/dataTables.bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/TableTools.js"></script>
	<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/chosen.jquery.js"></script>
	<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/stepy.jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/layer/layer.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/layer/extend/layer.ext.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/lodop/LodopFuncs.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/slider.js"></script> 
	<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/res/js/common/plugin/swfobject/swfobject.js"></script>
<style>
.course-box {
	float: left;
}
.jiaoan{
	height:249px;
}
.div2new1 .paihang {
	height: auto;
}
.upload a{
	display: block;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;
}
</style>
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
										<li class="fl_list" id="xiaoxue" style="display: none;">
											<div class="li_hover">
												<p class="title_1">小学资源</p>
												<div class="wk_div">
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&gradeCode=21">一年级上</a>
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&gradeCode=22">二年级上</a>
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=13">语文</a>
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=14">数学</a>
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=41">英文</a>
												</div>
											</div>
											<div class="njkm" style="display: none;">
												<ul class="nj_ul">
													<li>
														<p>一年级</p>
														<div class="km">
															<!-- 															<a href="">英语</a>  -->
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=21">语文</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=21">数学</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>二年级</p>
														<div class="km">
															<!-- 															<a href="javascript:void(0)">英语</a>  -->
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=22">语文</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=22">数学</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>三年级</p>
														<div class="km">
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=23">语文</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=23">数学</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=41&gradeCode=23">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>四年级</p>
														<div class="km">
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=24">语文</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=24">数学</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=41&gradeCode=24">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>五年级</p>
														<div class="km">
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=25">语文</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=25">数学</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=41&gradeCode=25">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>六年级</p>
														<div class="km">
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=26">语文</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=26">数学</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=2&subjectCode=41&gradeCode=26">英文</a>
														</div>
														<div class="clear"></div>
													</li>
												</ul>
											</div>
										</li>
										<li class="fl_list" id="chuzhong" style="display: none;">
											<div class="li_hover ">
												<p class="title_1">初中资源</p>
												<div class="wk_div">
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&gradeCode=31">七年级上</a>
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&gradeCode=32">八年级上</a>
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&subjectCode=13">语文</a>
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&subjectCode=14">数学</a>
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&subjectCode=41">英语</a>
												</div>
											</div>
											<div class="njkm" style="display: none;">
												<ul class="nj_ul">
													<li>
														<p>初一</p>
														<div class="km">
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&subjectCode=13&gradeCode=31">语文</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&subjectCode=14&gradeCode=31">数学</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&subjectCode=41&gradeCode=31">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>初二</p>
														<div class="km">
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&subjectCode=13&gradeCode=32">语文</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&subjectCode=14&gradeCode=32">数学</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&subjectCode=41&gradeCode=32">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>初三</p>
														<div class="km">
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&subjectCode=13&gradeCode=33">语文</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&subjectCode=14&gradeCode=33">数学</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=3&subjectCode=41&gradeCode=33">英文</a>
														</div>
														<div class="clear"></div>
													</li>
												</ul>
											</div>
										</li>
										<li class="fl_list" id="gaozhong" style="display: none;">
											<div class="li_hover ">
												<p class="title_1">高中资源</p>
												<div class="wk_div">
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=4&gradeCode=0&volumnCode=57561ceb4f304aa355653938119eddc9">必修1</a>
													<a style="margin-right: 46px;"
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=4&gradeCode=3&volumnCode=d4ac9251649e4d6c8a9221976d20fb33">必修2</a>
													
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=4&gradeCode=0&subjectCode=13">语文</a>
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=4&gradeCode=0&subjectCode=14">数学</a>
													<a
														href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=4&gradeCode=0&subjectCode=41">英语</a>
												</div>
											</div>
											<div class="njkm" style="display: none;">
												<ul class="nj_ul">
													<li>
														<p>必修1</p>
														<div class="km">
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=4&subjectCode=13&gradeCode=0">语文</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=4&subjectCode=14&gradeCode=0">数学</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=4&subjectCode=41&gradeCode=0">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>必修2</p></br>
														<div class="km">
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=4&subjectCode=13&gradeCode=0">语文</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=4&subjectCode=14&gradeCode=0">数学</a>
															<a
																href="${pageContext.request.contextPath}/resource/searcher/index?stageCode=4&subjectCode=41&gradeCode=0">英文</a>
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
									<span>本周浏览排行榜</span>
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
                                                                            <form id="xkzy_seacher" action="${pageContext.request.contextPath}/resource/searcher/result/index"
											method="post" >
											<input type="text" name="title" placeholder="请输入资源名称"/>
											<a href="javascript:void(0)"
												onclick="$('#xkzy_seacher').submit();"></a>
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
										<p class="p2"></p>
									</div>
									<div class="grwk">
										<div class="g1">
											<img
												src="">
											<p></p>
											<a
												href="${pageContext.request.contextPath}/resource/myResource?index=index&resType=2">
												>进入我的资源</a>
										</div>
										<div class="g2">
											
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
															<a href="${pageContext.request.contextPath}/resource/viewer/13113"><img src="${pageContext.request.contextPath}/res/images/index/niaodetiantang.png"></a>
														</li>
														<li>
															<a href="${pageContext.request.contextPath}/resource/viewer/13101"><img src="${pageContext.request.contextPath}/res/images/index/guanchao.png"></a>
														</li>
														<li>
															<a href="${pageContext.request.contextPath}/resource/viewer/13107"><img src="${pageContext.request.contextPath}/res/images/index/daxiagu.png"></a>
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
												<a href="${pageContext.request.contextPath}/resource/searcher/index?subjectCode=13">人教版</a>
											
											</div>
										</div>
										<div class="bb_div">
											<p>精品推荐</p>
											<div class="tt_list">
												<ul>
												<c:forEach items="${data.vosChineseRecommend}" var="item">
													<li><a href="${pageContext.request.contextPath}/resource/viewer/${item.id}">·${item.gradeName}${item.volumnName}${item.subjectName}${item.title}|
														<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
														    |${item.versionName}</a></li>	</c:forEach>
												</ul>
											</div>
										</div>
									</div>
									<div class="sea_right">
										<div class="jiaoan">
											<div class="nj">
												<ul>
													<li data-gradeId= "21" class="on"><a href="javascript:void(0)">一年级</a></li>
													<li data-gradeId= "22" ><a href="javascript:void(0)">二年级</a></li>
													<li data-gradeId= "23" ><a href="javascript:void(0)">三年级</a></li>
													<li data-gradeId= "24" ><a href="javascript:void(0)">四年级</a></li>
													<li data-gradeId= "25" ><a href="javascript:void(0)">五年级</a></li>
													<li data-gradeId= "26" ><a href="javascript:void(0)">六年级</a></li>
												</ul>
											</div>
											<div id="xxyw" class="jc">
												<ul>   
												   <c:forEach items="${data.vosOneChinese}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>

												</ul>
												<ul style="display: none;">
													    <c:forEach items="${data.vosTwoChinese}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>

												</ul>
												<ul style="display: none;">
													      <c:forEach items="${data.vosThreeChinese}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													     <c:forEach items="${data.vosFourChinese}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													     <c:forEach items="${data.vosFiveChinese}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													<c:forEach items="${data.vosSixChinese}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
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
												<c:forEach items="${data.vosMiddleOneChinese}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													    <c:forEach items="${data.vosMiddleTwoChinese}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
												<c:forEach items="${data.vosMiddleThreeChinese}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
											</div>
										</div>
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
															<a href="${pageContext.request.contextPath}/resource/viewer/4778"><img src="${pageContext.request.contextPath}/res/images/index/budaikuohaodeszys.png"></a>
														</li>
														<li>
															<a href="${pageContext.request.contextPath}/resource/viewer/4787"><img src="${pageContext.request.contextPath}/res/images/index/zswt.png"></a>
														</li>
														<li>
															<a href="${pageContext.request.contextPath}/resource/viewer/4800"><img src="${pageContext.request.contextPath}/res/images/index/jfxz.png"></a>
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
												<a href="${pageContext.request.contextPath}/resource/searcher/index?subjectCode=14">人教版</a>
												
											</div>
										</div>
										<div class="bb_div">
											<p>精品推荐</p>
											<div class="tt_list">
												<ul>
														<c:forEach items="${data.vosMathRecommend}" var="item">
													<li><a href="${pageContext.request.contextPath}/resource/viewer/${item.id}">·${item.gradeName}${item.volumnName}${item.subjectName}${item.title}|
														<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
														    |${item.versionName}</a></li>	</c:forEach>							
												</ul>
											</div>
										</div>
									</div>
									<div class="sea_right">
										<div class="jiaoan">
											<div class="nj">
												<ul>
													<li data-gradeId="21" class="on"><a href="javascript:void(0)">一年级</a></li>
													<li data-gradeId="22"><a href="javascript:void(0)">二年级</a></li>
													<li data-gradeId="23"><a href="javascript:void(0)">三年级</a></li>
													<li data-gradeId="24"><a href="javascript:void(0)">四年级</a></li>
													<li data-gradeId="25"><a href="javascript:void(0)">五年级</a></li>
													<li data-gradeId="26"><a href="javascript:void(0)">六年级</a></li>
												</ul>
											</div>
											<div class="jc">
												<ul>
													<c:forEach items="${data.vosOneMath}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													    <c:forEach items="${data.vosTwoMath}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													     <c:forEach items="${data.vosThreeMath}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													     <c:forEach items="${data.vosFourMath}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													     <c:forEach items="${data.vosFiveMath}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													    <c:forEach items="${data.vosSixMath}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
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
													    <c:forEach items="${data.vosMiddleOneMath}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													     <c:forEach items="${data.vosMiddleTwoMath}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													      <c:forEach items="${data.vosMiddleThreeMath}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
											</div>
										</div>
										
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
															<a href="${pageContext.request.contextPath}/resource/viewer/13567"><img src="${pageContext.request.contextPath}/res/images/index/grammar.png"></a>
														</li>
														<li>
															<a href="${pageContext.request.contextPath}/resource/viewer/13555"><img src="${pageContext.request.contextPath}/res/images/index/gofor.png"></a>
														</li>
														<li>
															<a href="${pageContext.request.contextPath}/resource/viewer/13568"><img src="${pageContext.request.contextPath}/res/images/index/vocabulary.png"></a>
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
												<a href="${pageContext.request.contextPath}/resource/searcher/index?subjectCode=41">人教版</a>
												
											</div>
										</div>
										<div class="bb_div">
											<p>精品推荐</p>
											<div class="tt_list">
												<ul>
														<c:forEach items="${data.vosEnglishRecommend}" var="item">
													<li><a href="${pageContext.request.contextPath}/resource/viewer/${item.id}">·${item.gradeName}${item.volumnName}${item.subjectName}${item.title}|
														<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
														    |${item.versionName}</a></li>	</c:forEach>										
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
												<ul>
													     <c:forEach items="${data.vosThreeEnglish}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													    <c:forEach items="${data.vosFourEnglish}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													   <c:forEach items="${data.vosFiveEnglish}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													   <c:forEach items="${data.vosSixEnglish}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
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
												  <c:forEach items="${data.vosMiddleOneEnglish}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													     <c:forEach items="${data.vosMiddleTwoEnglish}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
												</ul>
												<ul style="display: none;">
													     <c:forEach items="${data.vosMiddleThreeEnglish}" var="item">
												   <li><a href="${pageContext.request.contextPath}/resource/viewer/${id}"> <span
															class="style">
															<c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       </span>
																	     <span class="wd_title"><span
																<c:if test="${item.iconType==3}">class="icon icon_doc"</c:if>
																<c:if test="${item.iconType==4}">class="icon icon_ppt"</c:if>
																<c:if test="${item.iconType==5}">class="icon icon_swf"</c:if>
																<c:if test="${item.iconType==2}">class="icon icon_music"</c:if>
																<c:if test="${item.iconType==8}">class="icon icon_img"</c:if>
																<c:if test="${item.iconType==12}">class="icon icon_img"</c:if>
																
																
																></span>${item.versionName }${item.gradeName }${item.subjectName }${item.volumnName}
														    <c:if test="${item.resType ==2}">[课件]</c:if>
															<c:if test="${item.resType ==3}">[作业]</c:if>
															<c:if test="${item.resType ==4}">[试卷]</c:if>
															<c:if test="${item.resType ==5}">[教材]</c:if>
															<c:if test="${item.resType ==6}">[素材]</c:if>
															<c:if test="${item.resType ==7}">[其他]</c:if>
																	       :${item.title}</span><span
															class="day"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
													</a></li>
												   
												   </c:forEach>
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

		$.ajax({
		    url: "${pageContext.request.contextPath}/ajax/resource/getAjaxHeadPic",
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadHeadPic(data);
		     }  
		});
		
		$.ajax({
		    url: "${pageContext.request.contextPath}/ajax/resource/getAjaxNearResource",
		    type:'post',    
		    cache:false,    
		    data: {resType:0},    
		    dataType:'json',    
		    success:function(data) {
		    	loadNearResource(data);
		     }  
		});
		
		$.ajax({
		    url: "${pageContext.request.contextPath}/ajax/resource/getAjaxResCount",
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadResCoun(data);
		     }  
		});
		
		$.ajax({
		    url: "${pageContext.request.contextPath}/ajax/resource/getAjaxStageCode",
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadStageCode(data);
		     }  
		});
		
		//获取浏览排行榜
	    $.ajax({
		    url: "${pageContext.request.contextPath}/ajax/resource/getAjaxResClick",
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadResClick(data);
		     }  
		 });
	   
	    //获取点赞排行榜
	    $.ajax({
		    url: "${pageContext.request.contextPath}/ajax/resource/getAjaxResLike",
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadResLike(data);
		     }  
		});
	 
	    //获取收藏排行榜
	    $.ajax({
		    url: "${pageContext.request.contextPath}/ajax/resource/getAjaxResFav",
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadResFav(data);
		     }  
		});
	 
	    //获取下载排行榜
	    $.ajax({
		    url: "${pageContext.request.contextPath}/ajax/resource/getAjaxResDown",
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadResDown(data);
		     }  
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
	
	function loadHeadPic(data){
		$(".g1 img").attr("src",data.imgSrc);
		$(".g1 p").html(data.userName);
	}
	
	function loadNearResource(data){
		if(data.length > 0) {
			$.each(data, function(index, value) {
				$("#near").append('<li> <img src="'+ value.imgSrc +'"> <p class="name">'+ value.userName +'</p> <p class="upload">上传了 <a href="${pageContext.request.contextPath}/resource/viewer/'+ value.id +'">'+ value.title +'</a></p></li>');
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
	function loadResClick(data){
		var i;
		if(data.length > 0) {
			$.each(data, function(index, value) {
				i = index+1;
				if(index==0){
					$("#click").append('<li class="first"><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==1){
					$("#click").append('<li class="second"><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==2){
					$("#click").append('<li class="three"><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else{
					$("#click").append('<li class=""><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}
			});
		}
	}
	
	//点赞
	function loadResLike(data){
		var i;
		if(data.length > 0) {
			$.each(data, function(index, value) {
				i = index+1;
				if(index==0){
					$("#like").append('<li class="first"><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==1){
					$("#like").append('<li class="second"><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==2){
					$("#like").append('<li class="three"><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else{
					$("#like").append('<li class=""><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}
			});
		}
	}
	
	function loadResFav(data){
		var i;
		if(data.length > 0) {
			$.each(data, function(index, value) {
				i = index+1;
				if(index==0){
					$("#fav").append('<li class="first"><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==1){
					$("#fav").append('<li class="second"><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==2){
					$("#fav").append('<li class="three"><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else{
					$("#fav").append('<li class=""><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}
			});
		}
	}
	
	function loadResDown(data){
		var i;
		if(data.length > 0) {
			$.each(data, function(index, value) {
				i = index+1;
				if(index==0){
					$("#down").append('<li class="first"><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==1){
					$("#down").append('<li class="second"><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else if(index==2){
					$("#down").append('<li class="three"><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}else{
					$("#down").append('<li class=""><p>'+i+'</p><a href="${pageContext.request.contextPath}/resource/viewer/'+ value.resourceId +'">'+ value.resourceTitle +'</a></li>');
				}
			});
		}
	}
</script>
</html>
