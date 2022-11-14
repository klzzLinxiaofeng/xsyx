<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<title>A1-0检索</title>
</head>
<body>
<div class="qyzj_header">
	<div class="logo"></div>
	<div class="qyzj_right">
		<ul>
			<li><a href="javascript:void(0)" class="a1">创建导学案</a></li>
			<li><a href="javascript:void(0)" class="a2 btn-blue">我布置的</a></li>
			<li><a href="javascript:void(0)" class="a3">我接收的</a></li>
		</ul>
	</div>
</div>
<div class="content_main">
		<div class="ku_div">
			<div class="zjjl"><a href="javascript:void(0)">组卷记录</a></div>
			<ul>
				<li><a href="javascript:void(0)" class="a1">公共库</a></li>
				<li class="on"><a href="javascript:void(0)"  class="a2">校本库</a></li>
				<li><a href="javascript:void(0)"  class="a3">个人库</a></li>
				<li><a href="javascript:void(0)"  class="a4">收藏夹</a></li>
			</ul>
		</div>
	<div class="ku_select">
		<div class="xdkm_div">
			<div class="xd_km">
				<div class="xueduan">
					<label>学段：</label>
					<div class="xd">
						<a href="javascript:void(0)">全部</a>
						<a href="javascript:void(0)" class="btn-blue">小学</a>
						<a href="javascript:void(0)">初中</a>
						<a href="javascript:void(0)">高中</a>
					</div>
				</div>
				<div class="xueduan">
					<label>科目：</label>
					<div class="xd">
						<a href="javascript:void(0)">全部</a>
						<a href="javascript:void(0)">语文</a>
						<a href="javascript:void(0)"  class="btn-blue">数学</a>
						<a href="javascript:void(0)">英语</a>
						<a href="javascript:void(0)">物理</a>
						<a href="javascript:void(0)">化学</a>
						<a href="javascript:void(0)">生物</a>
						<a href="javascript:void(0)">地理</a>
						<a href="javascript:void(0)">历史</a>
						<a href="javascript:void(0)">政治</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="neirong_zs">
		<a href="javascript:void(0)" class="open_left"></a>
		<div class="nr_left">
			<div class="zszj_select">
				<ul>
					<li class="on"><input type="radio"> 按知识点</li>
					<li><input type="radio"> 按章节目录</li>
				</ul>
			</div>
			<div class="banben">
				<select>
					<option>人教版七年级上册</option>
					<option>人教版七年级下册</option>
				</select>
				<select>
					<option>人教版七年级上册</option>
					<option>人教版七年级下册</option>
				</select>
			</div>
			<div class="zj_mulu">
				<div class="jspContainer">
					<ul class="un_ul">
						<li class="un-item ">
							<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="un-link">第一章 内容超长怎么办啊，我也很烦恼</a></div>
							<div class="second_ul" style="display:block">
								<ul class="le-list">
									<li>
										<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link">第一节 语文文学</a></div>
									</li>
									<li>
										<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link">第二节 文言文预读</a></div>
									</li>
									<li>
										<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link ">第三节 古代诗歌鉴赏之送汪伦情古诗词</a></div>
									</li>
								</ul>
							</div>
						</li>
						<li class="un-item ">
							<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="un-link">第一章 内容超长怎么办啊，我也很烦恼</a></div>
							<div class="second_ul" style="display:block">
								<ul class="le-list">
									<li>
										<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link">第一节 语文文学</a></div>
									</li>
									<li>
										<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link">第二节 文言文预读</a></div>
									</li>
									<li>
										<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link ">第三节 古代诗歌鉴赏之送汪伦情古诗词</a></div>
									</li>
								</ul>
							</div>
						</li>
						<li class="un-item ">
							<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="un-link">第一章 内容超长怎么办啊，我也很烦恼</a></div>
							<div class="second_ul" style="display:block">
								<ul class="le-list">
									<li>
										<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link">第一节 语文文学</a></div>
									</li>
									<li>
										<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link">第二节 文言文预读</a></div>
									</li>
									<li>
										<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link ">第三节 古代诗歌鉴赏之送汪伦情古诗词</a></div>
									</li>
								</ul>
							</div>
						</li>
						<li class="un-item">
							<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="un-link">第一章 内容超长怎么办啊，我也很烦恼</a></div>
							<div class="second_ul">
								<ul class="le-list">
									<li>
									<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link">第一节 语文文学</a></div>
										<div class="second_ul">
											<ul class="le-list">
												<li>
													<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link">第一节 语文文学(1)</a></div>
													<div class="second_ul">
														<ul class="le-list">
															<li>
																<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link">第一节 语文文学(1)12121212</a></div>
															</li>
															<li>
																<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link">第一节 语文文学(1)</a></div>
															</li>
														</ul>
													</div>
												</li>
												<li>
													<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link">第一节 语文文学(1)</a></div>
												</li>
											</ul>
										</div>
									</li>
									<li>
										<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link">第二节 文言文预读</a></div>
									</li>
									<li>
										<div class="title"><a href="javascript:void(0)" class="li_close"></a><a href="javascript:void(0)" class="lesson-link ">第三节 古代诗歌鉴赏之送汪伦情古诗词</a></div>
									</li>
								</ul>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="nr_right">
			<div class="px_top">
				<div class="left">
					<a href="javascript:void(0)" class="gxsj on">更新时间<b class="fa fa-long-arrow-down"></b></a>
					<a href="javascript:void(0)" class="sccs">收藏次数<b class="fa fa-long-arrow-up"></b></a>
					<a href="javascript:void(0)" class="sycs">使用次数<b class="fa fa-long-arrow-up"></b></a>
				</div>
				<div class="right">总共有：<span>225</span>份导学案</div>
			</div>
			<div class="dxa_list" style="margin-bottom:20px;">
				<ul>
					<li data-time="2017/09/13" data-sc="152" data-sy="18">
						<p class="title">罗定邦中学高三语文第一章第一节导学案</p>
						<div class="detail">
							<p class="zf"><b class=""></b>总分：150</p>
							<p class="tm"><b class=""></b>题目数量：20</p>
							<p class="gx"><b class=""></b>更新时间：2017/09/13</p>
							<p class="sc"><b class=""></b>收藏（<span>152</span>）</p>
							<p class="sy"><b class=""></b>使用（<span>18</span>）</p>
						</div>
						<div class="cz_btn">
							<btn class="btn btn-green">查看</btn>
							<btn class="btn btn-blue">收藏</btn>
							<btn class="btn btn-orange">布置</btn>
						</div>
					</li>
					<li data-time="2017/09/11" data-sc="172" data-sy="19">
						<p class="title">罗定邦中学高三语文第一章第一节导学案</p>
						<div class="detail">
							<p class="zf"><b class=""></b>总分：150</p>
							<p class="tm"><b class=""></b>题目数量：20</p>
							<p class="gx"><b class=""></b>更新时间：2017/09/13</p>
							<p class="sc"><b class=""></b>收藏（<span>152</span>）</p>
							<p class="sy"><b class=""></b>使用（<span>18</span>）</p>
						</div>
						<div class="cz_btn">
							<btn class="btn btn-green">查看</btn>
							<btn class="btn btn-blue">收藏</btn>
							<btn class="btn btn-orange">布置</btn>
						</div>
					</li>
					<li data-time="2017/08/13" data-sc="199" data-sy="12">
						<p class="title">罗定邦中学高三语文第一章第一节导学案</p>
						<div class="detail">
							<p class="zf"><b class=""></b>总分：150</p>
							<p class="tm"><b class=""></b>题目数量：20</p>
							<p class="gx"><b class=""></b>更新时间：2017/09/13</p>
							<p class="sc"><b class=""></b>收藏（<span>152</span>）</p>
							<p class="sy"><b class=""></b>使用（<span>18</span>）</p>
						</div>
						<div class="cz_btn">
							<btn class="btn btn-green">查看</btn>
							<btn class="btn btn-blue">收藏</btn>
							<btn class="btn btn-orange">布置</btn>
						</div>
					</li>
					<li data-time="2017/08/3" data-sc="112" data-sy="32">
						<p class="title">罗定邦中学高三语文第一章第一节导学案</p>
						<div class="detail">
							<p class="zf"><b class=""></b>总分：150</p>
							<p class="tm"><b class=""></b>题目数量：20</p>
							<p class="gx"><b class=""></b>更新时间：2017/09/13</p>
							<p class="sc"><b class=""></b>收藏（<span>152</span>）</p>
							<p class="sy"><b class=""></b>使用（<span>18</span>）</p>
						</div>
						<div class="cz_btn">
							<btn class="btn btn-green">查看</btn>
							<btn class="btn btn-blue">收藏</btn>
							<btn class="btn btn-orange">布置</btn>
						</div>
					</li>
					<li data-time="2017/07/13" data-sc="197" data-sy="99">
						<p class="title">罗定邦中学高三语文第一章第一节导学案</p>
						<div class="detail">
							<p class="zf"><b class=""></b>总分：150</p>
							<p class="tm"><b class=""></b>题目数量：20</p>
							<p class="gx"><b class=""></b>更新时间：2017/09/13</p>
							<p class="sc"><b class=""></b>收藏（<span>152</span>）</p>
							<p class="sy"><b class=""></b>使用（<span>18</span>）</p>
						</div>
						<div class="cz_btn">
							<btn class="btn btn-green">查看</btn>
							<btn class="btn btn-blue">收藏</btn>
							<btn class="btn btn-orange">布置</btn>
						</div>
					</li>
					<li data-time="2017/08/3" data-sc="112" data-sy="32">
						<p class="title">罗定邦中学高三语文第一章第一节导学案</p>
						<div class="detail">
							<p class="zf"><b class=""></b>总分：150</p>
							<p class="tm"><b class=""></b>题目数量：20</p>
							<p class="gx"><b class=""></b>更新时间：2017/09/13</p>
							<p class="sc"><b class=""></b>收藏（<span>152</span>）</p>
							<p class="sy"><b class=""></b>使用（<span>18</span>）</p>
						</div>
						<div class="cz_btn">
							<btn class="btn btn-green">查看</btn>
							<btn class="btn btn-blue">收藏</btn>
							<btn class="btn btn-orange">布置</btn>
						</div>
					</li>
					<li data-time="2017/07/13" data-sc="197" data-sy="99">
						<p class="title">罗定邦中学高三语文第一章第一节导学案</p>
						<div class="detail">
							<p class="zf"><b class=""></b>总分：150</p>
							<p class="tm"><b class=""></b>题目数量：20</p>
							<p class="gx"><b class=""></b>更新时间：2017/09/13</p>
							<p class="sc"><b class=""></b>收藏（<span>152</span>）</p>
							<p class="sy"><b class=""></b>使用（<span>18</span>）</p>
						</div>
						<div class="cz_btn">
							<btn class="btn btn-green">查看</btn>
							<btn class="btn btn-blue">收藏</btn>
							<btn class="btn btn-orange">布置</btn>
						</div>
					</li>
					<li data-time="2017/08/3" data-sc="112" data-sy="32">
						<p class="title">罗定邦中学高三语文第一章第一节导学案</p>
						<div class="detail">
							<p class="zf"><b class=""></b>总分：150</p>
							<p class="tm"><b class=""></b>题目数量：20</p>
							<p class="gx"><b class=""></b>更新时间：2017/09/13</p>
							<p class="sc"><b class=""></b>收藏（<span>152</span>）</p>
							<p class="sy"><b class=""></b>使用（<span>18</span>）</p>
						</div>
						<div class="cz_btn">
							<btn class="btn btn-green">查看</btn>
							<btn class="btn btn-blue">收藏</btn>
							<btn class="btn btn-orange">布置</btn>
						</div>
					</li>
					<li data-time="2017/07/13" data-sc="197" data-sy="99">
						<p class="title">罗定邦中学高三语文第一章第一节导学案</p>
						<div class="detail">
							<p class="zf"><b class=""></b>总分：150</p>
							<p class="tm"><b class=""></b>题目数量：20</p>
							<p class="gx"><b class=""></b>更新时间：2017/09/13</p>
							<p class="sc"><b class=""></b>收藏（<span>152</span>）</p>
							<p class="sy"><b class=""></b>使用（<span>18</span>）</p>
						</div>
						<div class="cz_btn">
							<btn class="btn btn-green">查看</btn>
							<btn class="btn btn-blue">收藏</btn>
							<btn class="btn btn-orange">布置</btn>
						</div>
					</li>
					<li data-time="2017/08/3" data-sc="112" data-sy="32">
						<p class="title">罗定邦中学高三语文第一章第一节导学案</p>
						<div class="detail">
							<p class="zf"><b class=""></b>总分：150</p>
							<p class="tm"><b class=""></b>题目数量：20</p>
							<p class="gx"><b class=""></b>更新时间：2017/09/13</p>
							<p class="sc"><b class=""></b>收藏（<span>152</span>）</p>
							<p class="sy"><b class=""></b>使用（<span>18</span>）</p>
						</div>
						<div class="cz_btn">
							<btn class="btn btn-green">查看</btn>
							<btn class="btn btn-blue">收藏</btn>
							<btn class="btn btn-orange">布置</btn>
						</div>
					</li>
					<li data-time="2017/07/13" data-sc="197" data-sy="99">
						<p class="title">罗定邦中学高三语文第一章第一节导学案</p>
						<div class="detail">
							<p class="zf"><b class=""></b>总分：150</p>
							<p class="tm"><b class=""></b>题目数量：20</p>
							<p class="gx"><b class=""></b>更新时间：2017/09/13</p>
							<p class="sc"><b class=""></b>收藏（<span>152</span>）</p>
							<p class="sy"><b class=""></b>使用（<span>18</span>）</p>
						</div>
						<div class="cz_btn">
							<btn class="btn btn-green">查看</btn>
							<btn class="btn btn-blue">收藏</btn>
							<btn class="btn btn-orange">布置</btn>
						</div>
					</li> 
				</ul>
			</div>
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="studentAid_list_content" />
				<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
</div>
</body>
<script>
	$(function(){
		// 展开关闭左侧菜单
		$("body").on("click",".close_left",function(){
			$(".nr_left").fadeOut("100");
			$(".nr_right").css("margin-left","0");
			$(".nicescroll-rails").hide();
			$(this).removeClass("close_left").addClass("open_left");
		})
		$("body").on("click",".open_left",function(){
			$(".nr_left").fadeIn("100");
			$(".nr_right").css("margin-left","256px");
			$(".nicescroll-rails").show();
			$(this).removeClass("open_left").addClass("close_left")
		});
		var h1=$(".neirong_zs").height();
		var h2=$(".nr_left").height()+12;
		if(h1>h2){
			var h=h1-h2+472;
			$(".jspContainer").css("height",h);
		}
		//按知识点，按目录选择
		$(".neirong_zs .nr_left .zszj_select li input[type='radio']").click(function(){
			$(".neirong_zs .nr_left .zszj_select li").removeClass("on");
			$(this).parent().addClass("on")
		});
		//右侧内容排序
		$(".nr_right .px_top .left a").click(function(){
			if($(this).hasClass("on")){
				if($(this).children("b").hasClass("fa-long-arrow-down")){
					$(this).children("b").removeClass("fa-long-arrow-down").addClass("fa-long-arrow-up");
				}else{
					$(this).children("b").removeClass("fa-long-arrow-up").addClass("fa-long-arrow-down");
				}
			}else{
				$(".nr_right .px_top .left a").removeClass("on");
				$(this).addClass("on");
			}
			var $xx_list=$(".nr_right .dxa_list ul li");
			if($(this).children("b").hasClass("fa-long-arrow-down")){
				$xx_list.sort(function(a,b){
	                if($(".nr_right .px_top .left a.on").hasClass("gxsj")){
	                    var fw1=Date.parse(a.getAttribute("data-time"));
	                    var fw2=Date.parse(b.getAttribute("data-time"));
	                }else if($(".nr_right .px_top .left a.on").hasClass("sycs")){
	                    var fw1=a.getAttribute("data-sy");
	                    var fw2=b.getAttribute("data-sy");
	                }else{
	                    var fw1=a.getAttribute("data-sc");
	                    var fw2=b.getAttribute("data-sc");
	                }
	                return fw2-fw1;
	            })
			}else{
				$xx_list.sort(function(a,b){
	                if($(".nr_right .px_top .left a.on").hasClass("gxsj")){
	                	var fw1=Date.parse(a.getAttribute("data-time"));
	                    var fw2=Date.parse(b.getAttribute("data-time"));
	                }else if($(".nr_right .px_top .left a.on").hasClass("sycs")){
	                    var fw1=a.getAttribute("data-sy");
	                    var fw2=b.getAttribute("data-sy");
	                }else{
	                    var fw1=a.getAttribute("data-sc");
	                    var fw2=b.getAttribute("data-sc");
	                }
	                return fw1-fw2;
	            })
			}
	            $xx_list.detach().appendTo(".nr_right .dxa_list ul");
			
		});		
		
        
        
		
// 		目录树打开关闭
		$(".jspContainer").on("click"," ul .li_open",function(){
			$(this).parent().next().show();
			$(this).removeClass("li_open").addClass("li_close");
		});
		$(".jspContainer").on("click"," ul .li_close",function(){
			$(this).parent().next().hide();
			$(this).removeClass("li_close").addClass("li_open");
		});
		$(".jspContainer ul .li_close").each(function(){
			if($(this).parent().next().length==0){
				$(this).hide();
			}
		});
		$(".jspContainer ul .li_open").each(function(){
			if($(this).parent().next().length==0){
				$(this).hide();
			}
		});
		$(".lesson-link").click(function(){
			$(".lesson-link").removeClass("on font-blue");
			$(".un-link").removeClass("font-blue")
			$(this).addClass("on");
			$(this).parents(".second_ul").prev(".title").children(".lesson-link").addClass("font-blue")
			$(this).parents(".second_ul").prev(".title").children(".un-link").addClass("font-blue")
		})
	});
// 	滚动条
	$(document).ready(function() {  
		$(".jspContainer").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
	}); 
</script>
</html>