<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
<#assign basePath=contextPath /> 
<head>  
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">  
        <meta http-equiv="pragma" content="no-cache">  
        <meta http-equiv="cache-control" content="no-cache">  
        <meta http-equiv="expires" content="0">  
</head> 
  <title>云资源</title> 
	<link href="${basePath}/res/plugin/falgun/css/bootstrap.css" rel="stylesheet">
	<link href="${basePath}/res/plugin/falgun/css/bootstrap-responsive.css" rel="stylesheet">
	<link rel="stylesheet" href="${basePath}/res/plugin/falgun/css/font-awesome.css">
	<link href="${basePath}/res/plugin/falgun/css/chosen.css" rel="stylesheet">
	<link href="${basePath}/res/plugin/falgun/css/styles.css" rel="stylesheet">
	<link href="${basePath}/res/css/extra/xkzy.css" rel="stylesheet" >
	<link href="${basePath}/res/css/extra/add.css" rel="stylesheet">
<script src="${basePath}/res/plugin/falgun/js/jquery.js"></script>
	<script src="${basePath}/res/plugin/falgun/js/bootstrap.js"></script>
	<script src="${basePath}/res/plugin/falgun/js/accordion.nav.js"></script>
	<script src="${basePath}/res/plugin/falgun/js/custom.js"></script>
	<script src="${basePath}/res/plugin/falgun/js/respond.min.js"></script>
	<script src="${basePath}/res/plugin/falgun/js/ios-orientationchange-fix.js"></script>
	<script src="${basePath}/res/plugin/falgun/js/jquery.dataTables.js"></script>
	<script src="${basePath}/res/plugin/falgun/js/ZeroClipboard.js"></script>
	<script src="${basePath}/res/plugin/falgun/js/dataTables.bootstrap.js"></script>
	<script src="${basePath}/res/plugin/falgun/js/TableTools.js"></script>
	<script src="${basePath}/res/plugin/falgun/js/chosen.jquery.js"></script>
	<script src="${basePath}/res/plugin/falgun/js/stepy.jquery.js"></script>
	<script type="text/javascript" src="${basePath}/res/plugin/layer/layer.js"></script>
	<script type="text/javascript" src="${basePath}/res/plugin/layer/extend/layer.ext.js"></script>
	<script type="text/javascript" src="${basePath}/res/js/common/plugin/lodop/LodopFuncs.js"></script>
	<script type="text/javascript" src="${basePath}/res/js/slider.js"></script> 
	<script src="${basePath}/res/js/common/plugin/swfobject/swfobject.js"></script>
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
.div1_right{
	background:url(../res/images/icon_ggzy.png) no-repeat;
	border: 1px solid #0d7bd5;
	height: 286px;
}
.sczy a.sczy_sc {
    border: 0;
    color: #fff;
    background: #0088cc;
    width: 130px;
    height: 41px;
    display: block;
    border-radius: 5px;
    font-weight: bold;
    font-size: 16px;
    font-family: '微软雅黑';
    line-height: 41px;
    text-align: center;
}
.sczy a.sczy_sc i{margin-right:10px}
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
														href="${basePath}/resource/searcher/index?stageCode=2&gradeCode=21">一年级上</a>
													<a
														href="${basePath}/resource/searcher/index?stageCode=2&gradeCode=22">二年级上</a>
													<a
														href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=13">语文</a>
													<a
														href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=14">数学</a>
													<a
														href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=41">英文</a>
												</div>
											</div>
											<div class="njkm" style="display: none;">
												<ul class="nj_ul">
													<li>
														<p>一年级</p>
														<div class="km">
															<!-- 															<a href="">英语</a>  -->
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=21">语文</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=21">数学</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>二年级</p>
														<div class="km">
															<!-- 															<a href="javascript:void(0)">英语</a>  -->
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=22">语文</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=22">数学</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>三年级</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=23">语文</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=23">数学</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=41&gradeCode=23">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>四年级</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=24">语文</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=24">数学</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=41&gradeCode=24">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>五年级</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=25">语文</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=25">数学</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=41&gradeCode=25">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>六年级</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=13&gradeCode=26">语文</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=14&gradeCode=26">数学</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=2&subjectCode=41&gradeCode=26">英文</a>
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
														href="${basePath}/resource/searcher/index?stageCode=3&gradeCode=31">七年级上</a>
													<a
														href="${basePath}/resource/searcher/index?stageCode=3&gradeCode=32">八年级上</a>
													<a
														href="${basePath}/resource/searcher/index?stageCode=3&subjectCode=13">语文</a>
													<a
														href="${basePath}/resource/searcher/index?stageCode=3&subjectCode=14">数学</a>
													<a
														href="${basePath}/resource/searcher/index?stageCode=3&subjectCode=41">英语</a>
												</div>
											</div>
											<div class="njkm" style="display: none;">
												<ul class="nj_ul">
													<li>
														<p>初一</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?stageCode=3&subjectCode=13&gradeCode=31">语文</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=3&subjectCode=14&gradeCode=31">数学</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=3&subjectCode=41&gradeCode=31">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>初二</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?stageCode=3&subjectCode=13&gradeCode=32">语文</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=3&subjectCode=14&gradeCode=32">数学</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=3&subjectCode=41&gradeCode=32">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>初三</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?stageCode=3&subjectCode=13&gradeCode=33">语文</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=3&subjectCode=14&gradeCode=33">数学</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=3&subjectCode=41&gradeCode=33">英文</a>
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
														href="${basePath}/resource/searcher/index?stageCode=4&gradeCode=0&volumnCode=57561ceb4f304aa355653938119eddc9">必修1</a>
													<a style="margin-right: 46px;"
														href="${basePath}/resource/searcher/index?stageCode=4&gradeCode=3&volumnCode=d4ac9251649e4d6c8a9221976d20fb33">必修2</a>
													
													<a
														href="${basePath}/resource/searcher/index?stageCode=4&gradeCode=0&subjectCode=13">语文</a>
													<a
														href="${basePath}/resource/searcher/index?stageCode=4&gradeCode=0&subjectCode=14">数学</a>
													<a
														href="${basePath}/resource/searcher/index?stageCode=4&gradeCode=0&subjectCode=41">英语</a>
												</div>
											</div>
											<div class="njkm" style="display: none;">
												<ul class="nj_ul">
													<li>
														<p>必修1</p>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?stageCode=4&subjectCode=13&gradeCode=0">语文</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=4&subjectCode=14&gradeCode=0">数学</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=4&subjectCode=41&gradeCode=0">英文</a>
														</div>
														<div class="clear"></div>
													</li>
													<li>
														<p>必修2</p></br>
														<div class="km">
															<a
																href="${basePath}/resource/searcher/index?stageCode=4&subjectCode=13&gradeCode=0">语文</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=4&subjectCode=14&gradeCode=0">数学</a>
															<a
																href="${basePath}/resource/searcher/index?stageCode=4&subjectCode=41&gradeCode=0">英文</a>
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
                                        <#--style="width: 420px;background-size: 100% 100%;float: left;"-->
                                        <form id="xkzy_seacher" action="${basePath}/resource/searcher/result/index?personType=res_region" method="post" style="margin: 0px">
                                            <input type="text" name="title" placeholder="请输入资源名称"/>
                                            <a href="javascript:void(0)" onclick="$('#xkzy_seacher').submit();"></a>
                                        </form>
                                    </div>
                                    <#--<div class="sczy" style="float: right">-->
                                        <#--<a class="sczy_sc" href="${basePath}/resource/uploadIndex?resType=&amp;resourceType=res_region&amp;dm=KE_JIAN_ZI_YUAN_SCHOOL"><i class="fa fa-plus"></i>上传资源</a>-->
                                    <#--</div>-->
                                    <#--<div class="clear"></div>-->
									<div class="a">
										<div id="banner" class="pic1">
											<ul id="pic" class="pic">
												<li><img
													src="${basePath}/res/images/banner_1.jpg"></li>
												<li><img
													src="${basePath}/res/images/banner_2.jpg"></li>
												<li><img
													src="${basePath}/res/images/banner_3.jpg"></li>
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
										<p class="p1" style="margin-top: 10px;font-size: 28px;">学科资源</p>
										<p class="p2"></p>
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
															<a href="${basePath}/resource/viewer/221904"><img src="${basePath}/res/images/index/niaodetiantang.png"></a>
														</li>
														<li>
															<a href="${basePath}/resource/viewer/221670"><img src="${basePath}/res/images/index/guanchao.png"></a>
														</li>
														<li>
															<a href="${basePath}/resource/viewer/221802"><img src="${basePath}/res/images/index/daxiagu.png"></a>
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
												<a href="${basePath}/resource/searcher/index?subjectCode=13">人教版</a>
											
											</div>
										</div>
										<div class="bb_div">
											<p>精品推荐</p>
											<div class="tt_list">
												<ul>
													<#list vosChineseRecommend as chineseRecommend>
														<li><a href="${basePath}/resource/viewer/${chineseRecommend.id?c}">·${(chineseRecommend.gradeName)!"四年级"}${(chineseRecommend.volumnName)!"上册"}${(chineseRecommend.subjectName)!"语文"}${chineseRecommend.title}|<#if (chineseRecommend.resType==2)>[课件]
																	       <#elseif (chineseRecommend.resType==3)>[作业]	
																	       <#elseif (chineseRecommend.resType==4)>[试卷]    		
																	       <#elseif (chineseRecommend.resType==5)>[教案]		
																		   <#elseif (chineseRecommend.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>|${(chineseRecommend.versionName)!"人教版"}</a></li>												
													</#list>
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
													<#list vosOneChinese as oneChinese>
													     <li><a href="${basePath}/resource/viewer/${oneChinese.id?c}"> <span
															class="style"><#if (oneChinese.resType==2)>[课件]
																	       <#elseif (oneChinese.resType==3)>[作业]	
																	       <#elseif (oneChinese.resType==4)>[试卷]    		
																	       <#elseif (oneChinese.resType==5)>[教案]		
																		   <#elseif (oneChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (oneChinese.iconType==3)>icon icon_doc
																	       <#elseif (oneChinese.iconType==4)>icon icon_ppt	
																	       <#elseif (oneChinese.iconType==5)>icon icon_swf   		
																	       <#elseif (oneChinese.iconType==2)>icon icon_music	
																		   <#elseif (oneChinese.iconType==8)>icon icon_img
																		   <#elseif (oneChinese.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${oneChinese.versionName }${oneChinese.gradeName }${oneChinese.subjectName }${oneChinese.volumnName }<#if (oneChinese.resType==2)>[课件]
																	       <#elseif (oneChinese.resType==3)>[作业]	
																	       <#elseif (oneChinese.resType==4)>[试卷]    		
																	       <#elseif (oneChinese.resType==5)>[教案]		
																		   <#elseif (oneChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${oneChinese.title }</span><span
															class="day">${oneChinese.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosTwoChinese as twoChinese>
													     <li><a href="${basePath}/resource/viewer/${twoChinese.id?c}"> <span
															class="style"><#if (twoChinese.resType==2)>[课件]
																	       <#elseif (twoChinese.resType==3)>[作业]	
																	       <#elseif (twoChinese.resType==4)>[试卷]    		
																	       <#elseif (twoChinese.resType==5)>[教案]		
																		   <#elseif (twoChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (twoChinese.iconType==3)>icon icon_doc
																	       <#elseif (twoChinese.iconType==4)>icon icon_ppt	
																	       <#elseif (twoChinese.iconType==5)>icon icon_swf   		
																	       <#elseif (twoChinese.iconType==2)>icon icon_music	
																		   <#elseif (twoChinese.iconType==8)>icon icon_img
																		   <#elseif (twoChinese.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${twoChinese.versionName }${twoChinese.gradeName }${twoChinese.subjectName }${twoChinese.volumnName }<#if (twoChinese.resType==2)>[课件]
																	       <#elseif (twoChinese.resType==3)>[作业]	
																	       <#elseif (twoChinese.resType==4)>[试卷]    		
																	       <#elseif (twoChinese.resType==5)>[教案]		
																		   <#elseif (twoChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${twoChinese.title }</span><span
															class="day">${twoChinese.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosThreeChinese as threeChinese>
													     <li><a href="${basePath}/resource/viewer/${threeChinese.id?c}"> <span
															class="style"><#if (threeChinese.resType==2)>[课件]
																	       <#elseif (threeChinese.resType==3)>[作业]	
																	       <#elseif (threeChinese.resType==4)>[试卷]    		
																	       <#elseif (threeChinese.resType==5)>[教案]		
																		   <#elseif (threeChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (threeChinese.iconType==3)>icon icon_doc
																	       <#elseif (threeChinese.iconType==4)>icon icon_ppt	
																	       <#elseif (threeChinese.iconType==5)>icon icon_swf   		
																	       <#elseif (threeChinese.iconType==2)>icon icon_music	
																		   <#elseif (threeChinese.iconType==8)>icon icon_img
																		   <#elseif (threeChinese.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${threeChinese.versionName }${threeChinese.gradeName }${threeChinese.subjectName }${threeChinese.volumnName }<#if (threeChinese.resType==2)>[课件]
																	       <#elseif (threeChinese.resType==3)>[作业]	
																	       <#elseif (threeChinese.resType==4)>[试卷]    		
																	       <#elseif (threeChinese.resType==5)>[教案]		
																		   <#elseif (threeChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${threeChinese.title }</span><span
															class="day">${threeChinese.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosFourChinese as fourChinese>
													     <li><a href="${basePath}/resource/viewer/${fourChinese.id?c}"> <span
															class="style"><#if (fourChinese.resType==2)>[课件]
																	       <#elseif (fourChinese.resType==3)>[作业]	
																	       <#elseif (fourChinese.resType==4)>[试卷]    		
																	       <#elseif (fourChinese.resType==5)>[教案]		
																		   <#elseif (fourChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (fourChinese.iconType==3)>icon icon_doc
																	       <#elseif (fourChinese.iconType==4)>icon icon_ppt	
																	       <#elseif (fourChinese.iconType==5)>icon icon_swf   		
																	       <#elseif (fourChinese.iconType==2)>icon icon_music	
																		   <#elseif (fourChinese.iconType==8)>icon icon_img
																		   <#elseif (fourChinese.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${fourChinese.versionName }${fourChinese.gradeName }${fourChinese.subjectName }${fourChinese.volumnName }<#if (fourChinese.resType==2)>[课件]
																	       <#elseif (fourChinese.resType==3)>[作业]	
																	       <#elseif (fourChinese.resType==4)>[试卷]    		
																	       <#elseif (fourChinese.resType==5)>[教案]		
																		   <#elseif (fourChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${fourChinese.title }</span><span class="day">${fourChinese.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosFiveChinese as fiveChinese>
													     <li><a href="${basePath}/resource/viewer/${fiveChinese.id?c}"> <span
															class="style"><#if (fiveChinese.resType==2)>[课件]
																	       <#elseif (fiveChinese.resType==3)>[作业]	
																	       <#elseif (fiveChinese.resType==4)>[试卷]    		
																	       <#elseif (fiveChinese.resType==5)>[教案]		
																		   <#elseif (fiveChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (fiveChinese.iconType==3)>icon icon_doc
																	       <#elseif (fiveChinese.iconType==4)>icon icon_ppt	
																	       <#elseif (fiveChinese.iconType==5)>icon icon_swf   		
																	       <#elseif (fiveChinese.iconType==2)>icon icon_music	
																		   <#elseif (fiveChinese.iconType==8)>icon icon_img
																		   <#elseif (fiveChinese.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${fiveChinese.versionName }${fiveChinese.gradeName }${fiveChinese.subjectName }${fiveChinese.volumnName }<#if (fiveChinese.resType==2)>[课件]
																	       <#elseif (fiveChinese.resType==3)>[作业]	
																	       <#elseif (fiveChinese.resType==4)>[试卷]    		
																	       <#elseif (fiveChinese.resType==5)>[教案]		
																		   <#elseif (fiveChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${fiveChinese.title }</span><span
															class="day">${fiveChinese.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosSixChinese as sixChinese>
													     <li><a href="${basePath}/resource/viewer/${sixChinese.id?c}"> <span
															class="style"><#if (sixChinese.resType==2)>[课件]
																	       <#elseif (sixChinese.resType==3)>[作业]	
																	       <#elseif (sixChinese.resType==4)>[试卷]    		
																	       <#elseif (sixChinese.resType==5)>[教案]		
																		   <#elseif (sixChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (sixChinese.iconType==3)>icon icon_doc
																	       <#elseif (sixChinese.iconType==4)>icon icon_ppt	
																	       <#elseif (sixChinese.iconType==5)>icon icon_swf   		
																	       <#elseif (sixChinese.iconType==2)>icon icon_music	
																		   <#elseif (sixChinese.iconType==8)>icon icon_img
																		   <#elseif (sixChinese.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${sixChinese.versionName }${sixChinese.gradeName }${sixChinese.subjectName }${sixChinese.volumnName }<#if (sixChinese.resType==2)>[课件]
																	       <#elseif (sixChinese.resType==3)>[作业]	
																	       <#elseif (sixChinese.resType==4)>[试卷]    		
																	       <#elseif (sixChinese.resType==5)>[教案]		
																		   <#elseif (sixChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${sixChinese.title }</span><span
															class="day">${sixChinese.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
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
													<#list vosMiddleOneChinese as middleOneChinese>
													     <li><a href="${basePath}/resource/viewer/${middleOneChinese.id?c}"> <span
															class="style"><#if (middleOneChinese.resType==2)>[课件]
																	       <#elseif (middleOneChinese.resType==3)>[作业]	
																	       <#elseif (middleOneChinese.resType==4)>[试卷]    		
																	       <#elseif (middleOneChinese.resType==5)>[教案]		
																		   <#elseif (middleOneChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (middleOneChinese.iconType==3)>icon icon_doc
																	       <#elseif (middleOneChinese.iconType==4)>icon icon_ppt	
																	       <#elseif (middleOneChinese.iconType==5)>icon icon_swf   		
																	       <#elseif (middleOneChinese.iconType==2)>icon icon_music	
																		   <#elseif (middleOneChinese.iconType==8)>icon icon_img
																		   <#elseif (middleOneChinese.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${middleOneChinese.versionName }${middleOneChinese.gradeName }${middleOneChinese.subjectName }${middleOneChinese.volumnName }<#if (middleOneChinese.resType==2)>[课件]
																	       <#elseif (middleOneChinese.resType==3)>[作业]	
																	       <#elseif (middleOneChinese.resType==4)>[试卷]    		
																	       <#elseif (middleOneChinese.resType==5)>[教案]		
																		   <#elseif (middleOneChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${middleOneChinese.title }</span><span
															class="day">${middleOneChinese.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosMiddleTwoChinese as middleTwoChinese>
													     <li><a href="${basePath}/resource/viewer/${middleTwoChinese.id?c}"> <span
															class="style"><#if (middleTwoChinese.resType==2)>[课件]
																	       <#elseif (middleTwoChinese.resType==3)>[作业]	
																	       <#elseif (middleTwoChinese.resType==4)>[试卷]    		
																	       <#elseif (middleTwoChinese.resType==5)>[教案]		
																		   <#elseif (middleTwoChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (middleTwoChinese.iconType==3)>icon icon_doc
																	       <#elseif (middleTwoChinese.iconType==4)>icon icon_ppt	
																	       <#elseif (middleTwoChinese.iconType==5)>icon icon_swf   		
																	       <#elseif (middleTwoChinese.iconType==2)>icon icon_music	
																		   <#elseif (middleTwoChinese.iconType==8)>icon icon_img
																		   <#elseif (middleTwoChinese.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${middleTwoChinese.versionName }${middleTwoChinese.gradeName }${middleTwoChinese.subjectName }${middleTwoChinese.volumnName }<#if (middleTwoChinese.resType==2)>[课件]
																	       <#elseif (middleTwoChinese.resType==3)>[作业]	
																	       <#elseif (middleTwoChinese.resType==4)>[试卷]    		
																	       <#elseif (middleTwoChinese.resType==5)>[教案]		
																		   <#elseif (middleTwoChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${middleTwoChinese.title }</span><span
															class="day">${middleTwoChinese.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosMiddleThreeChinese as middleThreeChinese>
													     <li><a href="${basePath}/resource/viewer/${middleThreeChinese.id?c}"> <span
															class="style"><#if (middleThreeChinese.resType==2)>[课件]
																	       <#elseif (middleThreeChinese.resType==3)>[作业]	
																	       <#elseif (middleThreeChinese.resType==4)>[试卷]    		
																	       <#elseif (middleThreeChinese.resType==5)>[教案]		
																		   <#elseif (middleThreeChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (middleThreeChinese.iconType==3)>icon icon_doc
																	       <#elseif (middleThreeChinese.iconType==4)>icon icon_ppt	
																	       <#elseif (middleThreeChinese.iconType==5)>icon icon_swf   		
																	       <#elseif (middleThreeChinese.iconType==2)>icon icon_music	
																		   <#elseif (middleThreeChinese.iconType==8)>icon icon_img
																		   <#elseif (middleThreeChinese.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${middleThreeChinese.versionName }${middleThreeChinese.gradeName }${middleThreeChinese.subjectName }${middleThreeChinese.volumnName }<#if (middleThreeChinese.resType==2)>[课件]
																	       <#elseif (middleThreeChinese.resType==3)>[作业]	
																	       <#elseif (middleThreeChinese.resType==4)>[试卷]    		
																	       <#elseif (middleThreeChinese.resType==5)>[教案]		
																		   <#elseif (middleThreeChinese.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${middleThreeChinese.title }</span><span
															class="day">${middleThreeChinese.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
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
															<a href="${basePath}/resource/viewer/351236"><img src="${basePath}/res/images/index/budaikuohaodeszys.png"></a>
														</li>
														<li>
															<a href="${basePath}/resource/viewer/349491"><img src="${basePath}/res/images/index/shuziyubianma.png"></a>
														</li>
														<li>
															<a href="${basePath}/resource/viewer/348927"><img src="${basePath}/res/images/index/weizhiyufangxiang.png"></a>
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
												<a href="${basePath}/resource/searcher/index?subjectCode=14">人教版</a>
												
											</div>
										</div>
										<div class="bb_div">
											<p>精品推荐</p>
											<div class="tt_list">
												<ul>
													<#list vosMathRecommend as mathRecommend>
														<li><a href="${basePath}/resource/viewer/${mathRecommend.id?c}">·${(mathRecommend.gradeName)!"五年级"}${(mathRecommend.volumnName)!"下册"}${(mathRecommend.subjectName)!"数学"}${mathRecommend.title}|<#if (mathRecommend.resType==2)>[课件]
																	       <#elseif (mathRecommend.resType==3)>[作业]	
																	       <#elseif (mathRecommend.resType==4)>[试卷]    		
																	       <#elseif (mathRecommend.resType==5)>[教案]		
																		   <#elseif (mathRecommend.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>|${(mathRecommend.versionName)!"人教版"}</a></li>												
													</#list>
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
													<#list vosOneMath as oneMath>
													     <li><a href="${basePath}/resource/viewer/${oneMath.id?c}"> <span
															class="style"><#if (oneMath.resType==2)>[课件]
																	       <#elseif (oneMath.resType==3)>[作业]	
																	       <#elseif (oneMath.resType==4)>[试卷]    		
																	       <#elseif (oneMath.resType==5)>[教案]		
																		   <#elseif (oneMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (oneMath.iconType==3)>icon icon_doc
																	       <#elseif (oneMath.iconType==4)>icon icon_ppt	
																	       <#elseif (oneMath.iconType==5)>icon icon_swf   		
																	       <#elseif (oneMath.iconType==2)>icon icon_music	
																		   <#elseif (oneMath.iconType==8)>icon icon_img
																		   <#elseif (oneMath.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${oneMath.versionName }${oneMath.gradeName }${oneMath.subjectName }${oneMath.volumnName }<#if (oneMath.resType==2)>[课件]
																	       <#elseif (oneMath.resType==3)>[作业]	
																	       <#elseif (oneMath.resType==4)>[试卷]    		
																	       <#elseif (oneMath.resType==5)>[教案]		
																		   <#elseif (oneMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${oneMath.title }</span><span
															class="day">${oneMath.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosTwoMath as twoMath>
													     <li><a href="${basePath}/resource/viewer/${twoMath.id?c}"> <span
															class="style"><#if (twoMath.resType==2)>[课件]
																	       <#elseif (twoMath.resType==3)>[作业]	
																	       <#elseif (twoMath.resType==4)>[试卷]    		
																	       <#elseif (twoMath.resType==5)>[教案]		
																		   <#elseif (twoMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (twoMath.iconType==3)>icon icon_doc
																	       <#elseif (twoMath.iconType==4)>icon icon_ppt	
																	       <#elseif (twoMath.iconType==5)>icon icon_swf   		
																	       <#elseif (twoMath.iconType==2)>icon icon_music	
																		   <#elseif (twoMath.iconType==8)>icon icon_img
																		   <#elseif (twoMath.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${twoMath.versionName }${twoMath.gradeName }${twoMath.subjectName }${twoMath.volumnName }<#if (twoMath.resType==2)>[课件]
																	       <#elseif (twoMath.resType==3)>[作业]	
																	       <#elseif (twoMath.resType==4)>[试卷]    		
																	       <#elseif (twoMath.resType==5)>[教案]		
																		   <#elseif (twoMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${twoMath.title }</span><span
															class="day">${twoMath.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosThreeMath as threeMath>
													     <li><a href="${basePath}/resource/viewer/${threeMath.id?c}"> <span
															class="style"><#if (threeMath.resType==2)>[课件]
																	       <#elseif (threeMath.resType==3)>[作业]	
																	       <#elseif (threeMath.resType==4)>[试卷]    		
																	       <#elseif (threeMath.resType==5)>[教案]		
																		   <#elseif (threeMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (threeMath.iconType==3)>icon icon_doc
																	       <#elseif (threeMath.iconType==4)>icon icon_ppt	
																	       <#elseif (threeMath.iconType==5)>icon icon_swf   		
																	       <#elseif (threeMath.iconType==2)>icon icon_music	
																		   <#elseif (threeMath.iconType==8)>icon icon_img
																		   <#elseif (threeMath.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${threeMath.versionName }${threeMath.gradeName }${threeMath.subjectName }${threeMath.volumnName }<#if (threeMath.resType==2)>[课件]
																	       <#elseif (threeMath.resType==3)>[作业]	
																	       <#elseif (threeMath.resType==4)>[试卷]    		
																	       <#elseif (threeMath.resType==5)>[教案]		
																		   <#elseif (threeMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${threeMath.title }</span><span
															class="day">${threeMath.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosFourMath as fourMath>
													     <li><a href="${basePath}/resource/viewer/${fourMath.id?c}"> <span
															class="style"><#if (fourMath.resType==2)>[课件]
																	       <#elseif (fourMath.resType==3)>[作业]	
																	       <#elseif (fourMath.resType==4)>[试卷]    		
																	       <#elseif (fourMath.resType==5)>[教案]		
																		   <#elseif (fourMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (fourMath.iconType==3)>icon icon_doc
																	       <#elseif (fourMath.iconType==4)>icon icon_ppt	
																	       <#elseif (fourMath.iconType==5)>icon icon_swf   		
																	       <#elseif (fourMath.iconType==2)>icon icon_music	
																		   <#elseif (fourMath.iconType==8)>icon icon_img
																		   <#elseif (fourMath.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${fourMath.versionName }${fourMath.gradeName }${fourMath.subjectName }${fourMath.volumnName }<#if (fourMath.resType==2)>[课件]
																	       <#elseif (fourMath.resType==3)>[作业]	
																	       <#elseif (fourMath.resType==4)>[试卷]    		
																	       <#elseif (fourMath.resType==5)>[教案]		
																		   <#elseif (fourMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${fourMath.title }</span><span
															class="day">${fourMath.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosFiveMath as fiveMath>
													     <li><a href="${basePath}/resource/viewer/${fiveMath.id?c}"> <span
															class="style"><#if (fiveMath.resType==2)>[课件]
																	       <#elseif (fiveMath.resType==3)>[作业]	
																	       <#elseif (fiveMath.resType==4)>[试卷]    		
																	       <#elseif (fiveMath.resType==5)>[教案]		
																		   <#elseif (fiveMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (fiveMath.iconType==3)>icon icon_doc
																	       <#elseif (fiveMath.iconType==4)>icon icon_ppt	
																	       <#elseif (fiveMath.iconType==5)>icon icon_swf   		
																	       <#elseif (fiveMath.iconType==2)>icon icon_music	
																		   <#elseif (fiveMath.iconType==8)>icon icon_img
																		   <#elseif (fiveMath.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${fiveMath.versionName }${fiveMath.gradeName }${fiveMath.subjectName }${fiveMath.volumnName }<#if (fiveMath.resType==2)>[课件]
																	       <#elseif (fiveMath.resType==3)>[作业]	
																	       <#elseif (fiveMath.resType==4)>[试卷]    		
																	       <#elseif (fiveMath.resType==5)>[教案]		
																		   <#elseif (fiveMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${fiveMath.title }</span><span
															class="day">${fiveMath.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosSixMath as sixMath>
													     <li><a href="${basePath}/resource/viewer/${sixMath.id?c}"> <span
															class="style"><#if (sixMath.resType==2)>[课件]
																	       <#elseif (sixMath.resType==3)>[作业]	
																	       <#elseif (sixMath.resType==4)>[试卷]    		
																	       <#elseif (sixMath.resType==5)>[教案]		
																		   <#elseif (sixMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (sixMath.iconType==3)>icon icon_doc
																	       <#elseif (sixMath.iconType==4)>icon icon_ppt	
																	       <#elseif (sixMath.iconType==5)>icon icon_swf   		
																	       <#elseif (sixMath.iconType==2)>icon icon_music	
																		   <#elseif (sixMath.iconType==8)>icon icon_img
																		   <#elseif (sixMath.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${sixMath.versionName }${sixMath.gradeName }${sixMath.subjectName }${sixMath.volumnName }<#if (sixMath.resType==2)>[课件]
																	       <#elseif (sixMath.resType==3)>[作业]	
																	       <#elseif (sixMath.resType==4)>[试卷]    		
																	       <#elseif (sixMath.resType==5)>[教案]		
																		   <#elseif (sixMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${sixMath.title }</span><span
															class="day">${sixMath.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
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
													<#list vosMiddleOneMath as middleOneMath>
													     <li><a href="${basePath}/resource/viewer/${middleOneMath.id?c}"> <span
															class="style"><#if (middleOneMath.resType==2)>[课件]
																	       <#elseif (middleOneMath.resType==3)>[作业]	
																	       <#elseif (middleOneMath.resType==4)>[试卷]    		
																	       <#elseif (middleOneMath.resType==5)>[教案]		
																		   <#elseif (middleOneMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (middleOneMath.iconType==3)>icon icon_doc
																	       <#elseif (middleOneMath.iconType==4)>icon icon_ppt	
																	       <#elseif (middleOneMath.iconType==5)>icon icon_swf   		
																	       <#elseif (middleOneMath.iconType==2)>icon icon_music	
																		   <#elseif (middleOneMath.iconType==8)>icon icon_img
																		   <#elseif (middleOneMath.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${middleOneMath.versionName }${middleOneMath.gradeName }${middleOneMath.subjectName }${middleOneMath.volumnName }<#if (middleOneMath.resType==2)>[课件]
																	       <#elseif (middleOneMath.resType==3)>[作业]	
																	       <#elseif (middleOneMath.resType==4)>[试卷]    		
																	       <#elseif (middleOneMath.resType==5)>[教案]		
																		   <#elseif (middleOneMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${middleOneMath.title }</span><span
															class="day">${middleOneMath.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosMiddleTwoMath as middleTwoMath>
													     <li><a href="${basePath}/resource/viewer/${middleTwoMath.id?c}"> <span
															class="style"><#if (middleTwoMath.resType==2)>[课件]
																	       <#elseif (middleTwoMath.resType==3)>[作业]	
																	       <#elseif (middleTwoMath.resType==4)>[试卷]    		
																	       <#elseif (middleTwoMath.resType==5)>[教案]		
																		   <#elseif (middleTwoMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (middleTwoMath.iconType==3)>icon icon_doc
																	       <#elseif (middleTwoMath.iconType==4)>icon icon_ppt	
																	       <#elseif (middleTwoMath.iconType==5)>icon icon_swf   		
																	       <#elseif (middleTwoMath.iconType==2)>icon icon_music	
																		   <#elseif (middleTwoMath.iconType==8)>icon icon_img
																		   <#elseif (middleTwoMath.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${middleTwoMath.versionName }${middleTwoMath.gradeName }${middleTwoMath.subjectName }${middleTwoMath.volumnName }<#if (middleTwoMath.resType==2)>[课件]
																	       <#elseif (middleTwoMath.resType==3)>[作业]	
																	       <#elseif (middleTwoMath.resType==4)>[试卷]    		
																	       <#elseif (middleTwoMath.resType==5)>[教案]		
																		   <#elseif (middleTwoMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${middleTwoMath.title }</span><span
															class="day">${middleTwoMath.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosMiddleThreeMath as middleThreeMath>
													     <li><a href="${basePath}/resource/viewer/${middleThreeMath.id?c}"> <span
															class="style"><#if (middleThreeMath.resType==2)>[课件]
																	       <#elseif (middleThreeMath.resType==3)>[作业]	
																	       <#elseif (middleThreeMath.resType==4)>[试卷]    		
																	       <#elseif (middleThreeMath.resType==5)>[教案]		
																		   <#elseif (middleThreeMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (middleThreeMath.iconType==3)>icon icon_doc
																	       <#elseif (middleThreeMath.iconType==4)>icon icon_ppt	
																	       <#elseif (middleThreeMath.iconType==5)>icon icon_swf   		
																	       <#elseif (middleThreeMath.iconType==2)>icon icon_music	
																		   <#elseif (middleThreeMath.iconType==8)>icon icon_img
																		   <#elseif (middleThreeMath.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${middleThreeMath.versionName }${middleThreeMath.gradeName }${middleThreeMath.subjectName }${middleThreeMath.volumnName }<#if (middleThreeMath.resType==2)>[课件]
																	       <#elseif (middleThreeMath.resType==3)>[作业]	
																	       <#elseif (middleThreeMath.resType==4)>[试卷]    		
																	       <#elseif (middleThreeMath.resType==5)>[教案]		
																		   <#elseif (middleThreeMath.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${middleThreeMath.title }</span><span
															class="day">${middleThreeMath.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list>  
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
															<a href="${basePath}/resource/viewer/126018"><img src="${basePath}/res/images/index/bananas.png"></a>
														</li>
														<li>
															<a href="${basePath}/resource/viewer/125589"><img src="${basePath}/res/images/index/backpack.png"></a>
														</li>
														<li>
															<a href="${basePath}/resource/viewer/125386"><img src="${basePath}/res/images/index/sister.png"></a>
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
												<a href="${basePath}/resource/searcher/index?subjectCode=41">人教版</a>
												
											</div>
										</div>
										<div class="bb_div">
											<p>精品推荐</p>
											<div class="tt_list">
												<ul>
													<#list vosEnglishRecommend as englishRecommend>
														<li><a href="${basePath}/resource/viewer/${englishRecommend.id?c}">·${(englishRecommend.gradeName)!"九年级"}${(englishRecommend.volumnName)!"下册"}${(englishRecommend.subjectName)!"英语"}${englishRecommend.title}|<#if (englishRecommend.resType==2)>[课件]
																	       <#elseif (englishRecommend.resType==3)>[作业]	
																	       <#elseif (englishRecommend.resType==4)>[试卷]    		
																	       <#elseif (englishRecommend.resType==5)>[教案]		
																		   <#elseif (englishRecommend.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>|${(englishRecommend.versionName)!"人教版"}</a></li>												
													</#list>
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
													<#list vosThreeEnglish as threeEnglish>
													     <li><a href="${basePath}/resource/viewer/${threeEnglish.id?c}"> <span
															class="style"><#if (threeEnglish.resType==2)>[课件]
																	       <#elseif (threeEnglish.resType==3)>[作业]	
																	       <#elseif (threeEnglish.resType==4)>[试卷]    		
																	       <#elseif (threeEnglish.resType==5)>[教案]		
																		   <#elseif (threeEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (threeEnglish.iconType==3)>icon icon_doc
																	       <#elseif (threeEnglish.iconType==4)>icon icon_ppt	
																	       <#elseif (threeEnglish.iconType==5)>icon icon_swf   		
																	       <#elseif (threeEnglish.iconType==2)>icon icon_music	
																		   <#elseif (threeEnglish.iconType==8)>icon icon_img
																		   <#elseif (threeEnglish.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${threeEnglish.versionName }${threeEnglish.gradeName }${threeEnglish.subjectName }${threeEnglish.volumnName }<#if (threeEnglish.resType==2)>[课件]
																	       <#elseif (threeEnglish.resType==3)>[作业]	
																	       <#elseif (threeEnglish.resType==4)>[试卷]    		
																	       <#elseif (threeEnglish.resType==5)>[教案]		
																		   <#elseif (threeEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${threeEnglish.title }</span><span
															class="day">${threeEnglish.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosFourEnglish as fourEnglish>
													     <li><a href="${basePath}/resource/viewer/${fourEnglish.id?c}"> <span
															class="style"><#if (fourEnglish.resType==2)>[课件]
																	       <#elseif (fourEnglish.resType==3)>[作业]	
																	       <#elseif (fourEnglish.resType==4)>[试卷]    		
																	       <#elseif (fourEnglish.resType==5)>[教案]		
																		   <#elseif (fourEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (fourEnglish.iconType==3)>icon icon_doc
																	       <#elseif (fourEnglish.iconType==4)>icon icon_ppt	
																	       <#elseif (fourEnglish.iconType==5)>icon icon_swf   		
																	       <#elseif (fourEnglish.iconType==2)>icon icon_music	
																		   <#elseif (fourEnglish.iconType==8)>icon icon_img
																		   <#elseif (fourEnglish.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${fourEnglish.versionName }${fourEnglish.gradeName }${fourEnglish.subjectName }${fourEnglish.volumnName }<#if (fourEnglish.resType==2)>[课件]
																	       <#elseif (fourEnglish.resType==3)>[作业]	
																	       <#elseif (fourEnglish.resType==4)>[试卷]    		
																	       <#elseif (fourEnglish.resType==5)>[教案]		
																		   <#elseif (fourEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${fourEnglish.title }</span><span
															class="day">${fourEnglish.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosFiveEnglish as fiveEnglish>
													     <li><a href="${basePath}/resource/viewer/${fiveEnglish.id?c}"> <span
															class="style"><#if (fiveEnglish.resType==2)>[课件]
																	       <#elseif (fiveEnglish.resType==3)>[作业]	
																	       <#elseif (fiveEnglish.resType==4)>[试卷]    		
																	       <#elseif (fiveEnglish.resType==5)>[教案]		
																		   <#elseif (fiveEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (fiveEnglish.iconType==3)>icon icon_doc
																	       <#elseif (fiveEnglish.iconType==4)>icon icon_ppt	
																	       <#elseif (fiveEnglish.iconType==5)>icon icon_swf   		
																	       <#elseif (fiveEnglish.iconType==2)>icon icon_music	
																		   <#elseif (fiveEnglish.iconType==8)>icon icon_img
																		   <#elseif (fiveEnglish.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${fiveEnglish.versionName }${fiveEnglish.gradeName }${fiveEnglish.subjectName }${fiveEnglish.volumnName }<#if (fiveEnglish.resType==2)>[课件]
																	       <#elseif (fiveEnglish.resType==3)>[作业]	
																	       <#elseif (fiveEnglish.resType==4)>[试卷]    		
																	       <#elseif (fiveEnglish.resType==5)>[教案]		
																		   <#elseif (fiveEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${fiveEnglish.title }</span><span
															class="day">${fiveEnglish.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosSixEnglish as sixEnglish>
													     <li><a href="${basePath}/resource/viewer/${sixEnglish.id?c}"> <span
															class="style"><#if (sixEnglish.resType==2)>[课件]
																	       <#elseif (sixEnglish.resType==3)>[作业]	
																	       <#elseif (sixEnglish.resType==4)>[试卷]    		
																	       <#elseif (sixEnglish.resType==5)>[教案]		
																		   <#elseif (sixEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (sixEnglish.iconType==3)>icon icon_doc
																	       <#elseif (sixEnglish.iconType==4)>icon icon_ppt	
																	       <#elseif (sixEnglish.iconType==5)>icon icon_swf   		
																	       <#elseif (sixEnglish.iconType==2)>icon icon_music	
																		   <#elseif (sixEnglish.iconType==8)>icon icon_img
																		   <#elseif (sixEnglish.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${sixEnglish.versionName }${sixEnglish.gradeName }${sixEnglish.subjectName }${sixEnglish.volumnName }<#if (sixEnglish.resType==2)>[课件]
																	       <#elseif (sixEnglish.resType==3)>[作业]	
																	       <#elseif (sixEnglish.resType==4)>[试卷]    		
																	       <#elseif (sixEnglish.resType==5)>[教案]		
																		   <#elseif (sixEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${sixEnglish.title }</span><span
															class="day">${sixEnglish.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
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
													<#list vosMiddleOneEnglish as middleOneEnglish>
													     <li><a href="${basePath}/resource/viewer/${middleOneEnglish.id?c}"> <span
															class="style"><#if (middleOneEnglish.resType==2)>[课件]
																	       <#elseif (middleOneEnglish.resType==3)>[作业]	
																	       <#elseif (middleOneEnglish.resType==4)>[试卷]    		
																	       <#elseif (middleOneEnglish.resType==5)>[教案]		
																		   <#elseif (middleOneEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (middleOneEnglish.iconType==3)>icon icon_doc
																	       <#elseif (middleOneEnglish.iconType==4)>icon icon_ppt	
																	       <#elseif (middleOneEnglish.iconType==5)>icon icon_swf   		
																	       <#elseif (middleOneEnglish.iconType==2)>icon icon_music	
																		   <#elseif (middleOneEnglish.iconType==8)>icon icon_img
																		   <#elseif (middleOneEnglish.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${middleOneEnglish.versionName }${middleOneEnglish.gradeName }${middleOneEnglish.subjectName }${middleOneEnglish.volumnName }<#if (middleOneEnglish.resType==2)>[课件]
																	       <#elseif (middleOneEnglish.resType==3)>[作业]	
																	       <#elseif (middleOneEnglish.resType==4)>[试卷]    		
																	       <#elseif (middleOneEnglish.resType==5)>[教案]		
																		   <#elseif (middleOneEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${middleOneEnglish.title }</span><span
															class="day">${middleOneEnglish.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosMiddleTwoEnglish as middleTwoEnglish>
													     <li><a href="${basePath}/resource/viewer/${middleTwoEnglish.id?c}"> <span
															class="style"><#if (middleTwoEnglish.resType==2)>[课件]
																	       <#elseif (middleTwoEnglish.resType==3)>[作业]	
																	       <#elseif (middleTwoEnglish.resType==4)>[试卷]    		
																	       <#elseif (middleTwoEnglish.resType==5)>[教案]		
																		   <#elseif (middleTwoEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (middleTwoEnglish.iconType==3)>icon icon_doc
																	       <#elseif (middleTwoEnglish.iconType==4)>icon icon_ppt	
																	       <#elseif (middleTwoEnglish.iconType==5)>icon icon_swf   		
																	       <#elseif (middleTwoEnglish.iconType==2)>icon icon_music	
																		   <#elseif (middleTwoEnglish.iconType==8)>icon icon_img
																		   <#elseif (middleTwoEnglish.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${middleTwoEnglish.versionName }${middleTwoEnglish.gradeName }${middleTwoEnglish.subjectName }${middleTwoEnglish.volumnName }<#if (middleTwoEnglish.resType==2)>[课件]
																	       <#elseif (middleTwoEnglish.resType==3)>[作业]	
																	       <#elseif (middleTwoEnglish.resType==4)>[试卷]    		
																	       <#elseif (middleTwoEnglish.resType==5)>[教案]		
																		   <#elseif (middleTwoEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${middleTwoEnglish.title }</span><span
															class="day">${middleTwoEnglish.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
												</ul>
												<ul style="display: none;">
													<#list vosMiddleThreeEnglish as middleThreeEnglish>
													     <li><a href="${basePath}/resource/viewer/${middleThreeEnglish.id?c}"> <span
															class="style"><#if (middleThreeEnglish.resType==2)>[课件]
																	       <#elseif (middleThreeEnglish.resType==3)>[作业]	
																	       <#elseif (middleThreeEnglish.resType==4)>[试卷]    		
																	       <#elseif (middleThreeEnglish.resType==5)>[教案]		
																		   <#elseif (middleThreeEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if></span>
																	     <span class="wd_title"><span
																class="
																	<#if (middleThreeEnglish.iconType==3)>icon icon_doc
																	       <#elseif (middleThreeEnglish.iconType==4)>icon icon_ppt	
																	       <#elseif (middleThreeEnglish.iconType==5)>icon icon_swf   		
																	       <#elseif (middleThreeEnglish.iconType==2)>icon icon_music	
																		   <#elseif (middleThreeEnglish.iconType==8)>icon icon_img
																		   <#elseif (middleThreeEnglish.iconType==12)>icon icon_img
																		   <#else> icon icon_doc
																	     </#if>
																"></span>${middleThreeEnglish.versionName }${middleThreeEnglish.gradeName }${middleThreeEnglish.subjectName }${middleThreeEnglish.volumnName }<#if (middleThreeEnglish.resType==2)>[课件]
																	       <#elseif (middleThreeEnglish.resType==3)>[作业]	
																	       <#elseif (middleThreeEnglish.resType==4)>[试卷]    		
																	       <#elseif (middleThreeEnglish.resType==5)>[教案]		
																		   <#elseif (middleThreeEnglish.resType==6)>[素材]
																		   <#else>[其他]
																	     </#if>:${middleThreeEnglish.title }</span><span
															class="day">${middleThreeEnglish.createDate?string("yyyy-MM-dd")}</span>
													</a></li>
													</#list> 
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
		    url: "${basePath}/ajax/resource/getAjaxNearResource",
		    type:'post',    
		    cache:false,    
		    data: {resType:0},    
		    dataType:'json',    
		    success:function(data) {
		    	loadNearResource(data);
		     }  
		});
		
		$.ajax({
		    url: "${basePath}/ajax/resource/getAjaxResCount",
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadResCoun(data);
		     }  
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
		    url: "${basePath}/ajax/resource/getAjaxResClick",
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	//loadResClick(data);
		     }  
		 });
	   
	    //获取点赞排行榜
	    $.ajax({
		    url: "${basePath}/ajax/resource/getAjaxResLike",
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadResLike(data);
		     }  
		});
	 
	    //获取收藏排行榜
	    $.ajax({
		    url: "${basePath}/ajax/resource/getAjaxResFav",
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadResFav(data);
		     }  
		});
	 
	    //获取下载排行榜
	    $.ajax({
		    url: "${basePath}/ajax/resource/getAjaxResDown",
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadResDown(data);
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
		
		$(".zy_search .sea_right .nj li a").click(function() {
			var i = $(this).parent().index();
			$(this).parent().siblings().removeClass("on");
			$(this).parent().addClass("on");
			$_this = $(this).parent().parent().parent().next().children();
			$_this.hide();
			$_this.eq(i).show();
		});
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
	
	function loadHeadPic(data){
		$(".g1 img").attr("src",data.imgSrc);
		$(".g1 p").html(data.userName);
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
	function loadResClick(data){
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
	function loadResLike(data){
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
	
	function loadResFav(data){
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
	
	function loadResDown(data){
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
