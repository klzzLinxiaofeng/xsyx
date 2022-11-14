<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="icon" href="${pageContext.request.contextPath}/res/plugin/falgun/ico/favicon.ico" type="image/x-icon" />
	<link type="text/css" rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/res/jxdnjy/css/cas.css">
	<link type="text/css" rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/res/jxdnjy/css/buttons.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/res/jxdnjy/css/index.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/res/jxdnjy/css/animate.min.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/res/jxdnjy/css/lanrenzhijia.css"  />
<title>江西定南教育云</title>
</head>
<body id="cas" class="fl-theme-iphone">
<div class="flc-screenNavigator-view-container">
	<div class="fl-screenNavigator-view">
		<div class="login_top">
			<div class="lt_main">
				<div class="logo"></div>
				<div class="menu f1">
					<ul class="f1">
						<li class="on"><a href="/" >首页</a></li>
						<li><a href="http://zhjyy.jxdnjy.com/xx/index.jhtml">学校</a></li>
						<li><a href="http://zhjyy.jxdnjy.com/kj/index.jhtml">空间</a></li>
						<li><a href="javascript:void(0)">资源</a></li>
						<li><a href="http://zhjyy.jxdnjy.com/yy/index.jhtml">应用</a></li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="login_btn fr">
					<button class="btn-submit button button-primary button-rounded" class="login_btn" >登陆</button>
					<div class="grxx" style="display: none">
						<img src="http://test.studyo.cn/res/images/no_pic.jpg">
						<p class="p1">罗志明</p>
						<p class="p2">定南县第四小学</p>
					</div>
				</div>
				<a class="return_dnxxw" href="http://www.jxdnjy.com/">定南教育信息网</a>
			</div>
		</div>
		<div id="content" class="fl-screenNavigator-scroll-container">
			<div class="box fl-panel" id="login">
				<form method="post" id="fm1" cssClass="fm-v clearfix" commandName="" htmlEscape="true">
					<form:errors path="*" id="msg" cssClass="errors" element="div" />
					<h2>欢迎登录江西定南教育云</h2>
					<div class="row fl-controls-left">
						<i class="i1"></i>
						<input cssClass="required" cssErrorClass="error" id="username" size="25" tabindex="1" placeholder="用户名" accesskey="${userNameAccessKey}" path="username" autocomplete="false" htmlEscape="true" />
					</div>
					<div class="row fl-controls-left">
						<i class="i2"></i>
						<input cssClass="required" cssErrorClass="error" id="password" size="25" tabindex="2" path="password"  placeholder="请输入您的密码" accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />
					</div>
					<div class="row check">
						<div class="rememberMe">
							<input id="rememberMe" name="rememberMe" value="true" tabindex="4" type="checkbox" />
							<label for="rememberMe">下次自动登录</label>
						</div>
					</div>
					<div class="row btn-row">
						<input type="hidden" name="lt" value="${loginTicket}" />
						<input type="hidden" name="execution" value="${flowExecutionKey}" />
						<input type="hidden" name="_eventId" value="submit" />
						<input class="btn-submit button button-3d button-primary button-rounded" name="submit" accesskey="l" value="登录" tabindex="4" type="submit" />
					</div>
				</form>
			</div>
		</div>
		<div class="main">
			<div id="banner">
				<div id="visual">
					<ul id="visualBanner">
						<li class="vis1" id="scene1">
							<p class="pointer1" ><a href="#"><img src="${pageContext.request.contextPath}/res/jxdnjy/images/div1_1.png" alt=""></a></p>
							<p class="pointer2" ><a href="#"><img src="${pageContext.request.contextPath}/res/jxdnjy/images/div1_2.png" alt=""></a></p>
							<p class="pointer3" ><a href="#"><img src="${pageContext.request.contextPath}/res/jxdnjy/images/div1_3.png" alt=""></a></p>
						</li>
						<li class="vis2" id="scene2" >
							<p class="pointer1" ><a href="#"><img src="${pageContext.request.contextPath}/res/jxdnjy/images/div2_1.png" alt=""></a></p>
							<p class="pointer2" ><a href="#"><img src="${pageContext.request.contextPath}/res/jxdnjy/images/div2_2.png" alt=""></a></p>
							<p class="pointer3" ><a href="#"><img src="${pageContext.request.contextPath}/res/jxdnjy/images/div2_3.png" alt=""></a></p>
						</li>
						<li class="vis3" id="scene3" >
							<p class="pointer1"><a href="#"><img src="${pageContext.request.contextPath}/res/jxdnjy/images/div3_1.png" alt=""></a></p>
							<p class="pointer2"><a href="#"><img src="${pageContext.request.contextPath}/res/jxdnjy/images/div3_2.png" alt=""></a></p>
							<p class="pointer3"><a href="#"><img src="${pageContext.request.contextPath}/res/jxdnjy/images/div3_3.png" alt=""></a></p>
						</li>
						<li class="vis4" id="scene4" >
							<p class="pointer1"><a href="#"><img src="${pageContext.request.contextPath}/res/jxdnjy/images/div4_1.png" alt=""></a></p>
							<p class="pointer2"><a href="#"><img src="${pageContext.request.contextPath}/res/jxdnjy/images/div4_2.png" alt=""></a></p>
							<p class="pointer3"><a href="#"><img src="${pageContext.request.contextPath}/res/jxdnjy/images/div4_3.png" alt=""></a></p>
						</li>
					</ul>
				</div>
				<div class="control">
					<ul id="active">
						<li class="active1 on"></li>
						<li class="active2"></li>
						<li class="active3"></li>
						<li class="active4"></li>
					</ul>
					<!-- <div id="btnPlay"></div> -->
				</div>
			</div>
			<div class="centre">
				<div class="role wrap">
					<ul>
						<li class="animated bounceInLeft">
							<a href="javascript:void(0)">
								<img src="${pageContext.request.contextPath}/res/jxdnjy/images/glz.jpg">
							</a>
						</li>
						<li class="animated bounceInLeft" style="animation-delay: 0.8s;">
							<a href="javascript:void(0)">
								<img src="${pageContext.request.contextPath}/res/jxdnjy/images/js.jpg">
							</a>
						</li>
						<li class="animated bounceInRight" style="animation-delay: 0.8s;">
							<a href="javascript:void(0)">
								<img src="${pageContext.request.contextPath}/res/jxdnjy/images/xs.jpg">
							</a>
						</li>
						<li class="animated bounceInRight">
							<a href="javascript:void(0)">
								<img src="${pageContext.request.contextPath}/res/jxdnjy/images/jz.jpg">
							</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="app-module">
				<div class="app-title">
					<a href="http://zhjyy.jxdnjy.com/yy/index.jhtml" target="_blank" class="title-more">更多&gt;&gt;</a>
					<span class="title-text">推荐应用 </span>
				</div>
				<ul class="app-list clearfix">
					<li data-zh="5" data-sy="134" data-new="8">
						<a href="http://zhjyy.jxdnjy.com/yyxq/index.jhtml?pkid=jspxpt" target="_blank">
							<img src="http://zhjyy.jxdnjy.com/r/cms/zhjyy/default/images/icon/jw_10.png" alt="">

							<div class="app-info">
								<h3>
									教师培训
								</h3>
								<span class="see_detail">查看详情</span>
							</div>
						</a>
					</li>
					<li data-zh="3" data-sy="125" data-new="4">
						<a href="http://zhjyy.jxdnjy.com/yyxq/index.jhtml?pkid=bkskxt" target="_blank">
							<img src="${pageContext.request.contextPath}/res/jxdnjy/images/js_3.png" alt="">

							<div class="app-info">
								<h3>
									备课授课工具
								</h3>
								<span class="see_detail">查看详情</span>
							</div>
						</a>
					</li>
					<li data-zh="1" data-sy="115" data-new="6">
						<a href="http://zhjyy.jxdnjy.com/yyxq/index.jhtml?pkid=ssjkxt" target="_blank">
							<img src="${pageContext.request.contextPath}/res/jxdnjy/images/yy_4.png" alt="">

							<div class="app-info">
								<h3>
									实时监控
								</h3>
								<span class="see_detail">查看详情</span>
							</div>
						</a>
					</li>

					<li data-zh="2" data-sy="126" data-new="5">
						<a href="http://zhjyy.jxdnjy.com/yyxq/index.jhtml?pkid=jyzyglxt" target="_blank">
							<img src="${pageContext.request.contextPath}/res/jxdnjy/images/jyzyglpt.png" alt="">

							<div class="app-info">
								<h3>
									教学资源管理
								</h3>
								<span class="see_detail">查看详情</span>
							</div>
						</a>
					</li>


					<li data-zh="4" data-sy="160" data-new="3">
						<a href="http://zhjyy.jxdnjy.com/yyxq/index.jhtml?pkid=znxzxt" target="_blank">
							<img src="${pageContext.request.contextPath}/res/jxdnjy/images/zygj_6.png" alt="">

							<div class="app-info">
								<h3>
									智能校证
								</h3>
								<span class="see_detail">查看详情</span>
							</div>
						</a>
					</li>




					<li data-zh="6" data-sy="298" data-new="7">
						<a href="http://zhjyy.jxdnjy.com/yyxq/index.jhtml?pkid=ksjcxt" target="_blank">
							<img src="${pageContext.request.contextPath}/res/jxdnjy/images/jw_6.png" alt="">

							<div class="app-info">
								<h3>
									考试检测
								</h3>
								<span class="see_detail">查看详情</span>
							</div>
						</a>
					</li>


				</ul>
			</div>
			<div class="app-module">
				<div class="app-title">
					<a href="http://zhjyy.jxdnjy.com/xx/index.jhtml"  target="_blank" class="title-more">更多&gt;&gt;</a>
					<span class="title-text">学校展示 </span>
				</div>
				<div class="school_list">
					<ul>
						<li>
							<a href="http://kmsxx.jxdnjy.com/" target="_blank">
								<img src="${pageContext.request.contextPath}/res/jxdnjy/images/kms_logo.png">
								<p class="first">访问：<span>4482</span></p>
								<p>师生：<span>5487</span></p>
								<p>资源：<span>75874</span></p>
								<div>岿美山中心小学</div></a>
						</li>
						<li>
							<a href="http://dn4x.jxdnjy.com" target="_blank">
								<img src="${pageContext.request.contextPath}/res/jxdnjy/images/dn4x_logo.png">
								<p class="first">访问：<span>4422</span></p>
								<p>师生：<span>3487</span></p>
								<p>资源：<span>61021</span></p>
								<div>定南县第四小学</div></a>
						</li>
						<li>
							<a href="http://dnlx.jxdnjy.com" target="_blank">
								<img src="${pageContext.request.contextPath}/res/jxdnjy/images/dnlx_logo.png">
								<p class="first">访问：<span>1482</span></p>
								<p>师生：<span>1487</span></p>
								<p>资源：<span>65874</span></p>
								<div>定南县第六小学</div></a>
						</li>
						<li>
							<a href="http://tjzx.jxdnjy.com" target="_blank">
								<img src="${pageContext.request.contextPath}/res/jxdnjy/images/tjzx_logo.png">
								<p class="first">访问：<span>3482</span></p>
								<p>师生：<span>2487</span></p>
								<p>资源：<span>45872</span></p>
								<div>定南县天九中学</div></a>
						</li>
					</ul>
					<div class="clear"></div>
				</div>
			</div>
			<div class="kj_div">
				<div class="fl app-module" style="width: 760px">
					<div class="kj_list">
						<div class="kj_top">
							<p class="title">优秀空间</p>
							<div class="kj_fl"><a href="javascript:void(0)" class="on">教师</a><a href="javascript:void(0)">学生</a><a href="javascript:void(0)">家长</a></div>
							<a href="http://zhjyy.jxdnjy.com/kj/index.jhtml" target="_blank" class="more">更多&gt;&gt;</a>
						</div>
						<div class="kj_bottom">
							<div class="yxkj_list">
								<ul>
								</ul>
							</div>
							<div class="yxkj_list" style="display: none;">
								<ul></ul>
							</div>
							<div class="yxkj_list" style="display: none;">
								<ul></ul>
							</div>
						</div>
					</div>
				</div>
				<div class="fr app-module" style="width: 340px;">
					<div class="kj_list">
						<div class="kj_top">
							<p class="title">空间动态</p><a href="http://zhjyy.jxdnjy.com/kj/index.jhtml" target="_blank" class="more">更多&gt;&gt;</a>
						</div>
						<div class="kj_bottom">
							<div class="kjdt_list">
								<ul>

								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="app-module">
				<div class="app-title">
					<span class="title-text">应用监控 </span>
				</div>
				<div class="yyjk">
					<div class="yyjk_1">
						<div class="appCont" style="border-bottom:1px solid #e9e9e9;">
							<div class="title">已开通的账号<span>9532</span>人</div>
							<div class="roleNum">
								<div class="roleNumList">
									<p class="photo">
										<img src="${pageContext.request.contextPath}/res/jxdnjy/images/lv.png">
									</p>
									<p class="name">老师</p>
									<p class="site_nummberUl" style="color:#a25ea2">
										568
									</p>
								</div>
								<div class="roleNumList">
									<p class="photo">
										<img src="${pageContext.request.contextPath}/res/jxdnjy/images/lan.png">
									</p>
									<p class="name">学生</p>
									<p class="site_nummberUl">
										8542
									</p>
								</div>
								<div class="roleNumList">
									<p class="photo">
										<img src="${pageContext.request.contextPath}/res/jxdnjy/images/zi.png">
									</p>
									<p class="name">家长</p>
									<p class="site_nummberUl" style="color:#8ec31e">
										422
									</p>
								</div>
							</div>
							<div class="scaleRect clearfix">
								<div class="scaleDiv">
									<canvas id="yhhyl"></canvas>
									<p class="mgt5 fs14">用户活跃率</p>
								</div>
								<div class="scaleDiv">
									<canvas id="kjktl"></canvas>
									<p class="mgt5 fs14">用户开通率</p>
								</div>
							</div>
						</div>
						<div class="appCont" style="border-right:0 none;border-bottom:1px solid #e9e9e9;">
							<div class="title">资源建设总量<span>916697</span>个</div>
							<div id="zyjszl" style="width: 90%; height: 291px;  margin: 0 auto"></div>
						</div>
						<div class="appCont" style="width:387px;">
							<div class="title" style="width:333px;">小学资源总量<span>181320</span>个</div>
							<div id="xxzyzl" style="width: 90%; height: 284px;  margin: 0 auto"></div>
						</div>
						<div class="appCont" style="width:387px;">
							<div class="title" style="width:333px;">初中资源总量<span>611562</span>个</div>
							<div id="czzyzl" style="width: 90%; height: 284px;  margin: 0 auto"></div>
						</div>
						<div class="appCont" style="width:387px;border-right:0 none">
							<div class="title" style="width:333px;">高中资源总量<span>123815</span>个</div>
							<div id="gzzyzl" style="width: 90%; height: 284px;  margin: 0 auto"></div>
						</div>
						<div class="appCont" style="width:100%;height:500px;border-right:0 none;border-top:1px solid #e9e9e9">
							<div id="zyzlfb" style="width: 90%; height: 470px;  margin: 30px auto 0"></div>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
		<!--                 底部 -->
		<div class="jx_bottom">
			<div class="w1200">
				<!--友情链接-->
				<div class="yq-links clearfix" style="margin-bottom: 20px;padding-left:48px;">
					<span class="fl">友情链接：</span>
					<p class="fl">
						<a target="_blank" href="http://www.moe.edu.cn/"><img src="http://static.jxeduyun.com/cloud/theme/home/index/images/jxdj.png"></a>
						<a target="_blank" href="http://www.ncet.edu.cn/zhuzhan/index.html"><img src="http://static.jxeduyun.com/cloud/theme/home/index/images/jyb.png"></a>
						<a target="_blank" href="http://www.jxedu.gov.cn/"><img src="http://static.jxeduyun.com/cloud/theme/home/index/images/jxjy.jpg"></a>
						<a target="_blank" href="http://www.jxdjg.gov.cn"><img src="http://static.jxeduyun.com/cloud/theme/home/index/images/xxw.png"></a>
						<a target="_blank" href="http://www.ict.edu.cn"><img src="http://static.jxeduyun.com/cloud/theme/home/index/images/jyg.png"></a>
						<a target="_blank" href="http://www.eduyun.cn/"><img src="http://static.jxeduyun.com/cloud/theme/home/index/images/gjjy.png"></a>
						<a target="_blank" href="http://www.jxeduyun.com/"><img src="http://www.jxeduyun.com/App.ResourceCloud/Src/apps/changyan/_static/common/images/jx_logo.png" style="height:43px;background: #fff;"></a>
					</p>
				</div>
				<!--end-->
				<p>主办单位：定南县教育体育局 | 地 址：江西省赣州市定南县公园路文体中心 | 邮编：341900</p>
				<p>赣ICP备15003447-1 |&copy;2013-2017 定南县教育体育局 版权所有</p>
				<p>技术支持：江西慧通力合信息技术有限公司</p>
				<p class="jx_bottom_fw mt10">
					<a href="http://bszs.conac.cn/sitename?method=show&amp;id=40B3816CA5F11CD0E053022819AC682B" target="_blank" style="position:relative;top:15px;"><img src="http://www.jxdnjy.com/r/cms/www/default/css/images/red.png" alt="事业单位" style="width: 80px !important;height:80px !important;"></a>
					<a href="http://www.12377.cn/" target="_blank"><img src="http://www.jxeduyun.com/App.ResourceCloud/Src/apps/changyan/_static/common/images/jx_footer_pic01.png" alt="不良信息举报中心"></a>
					<a href="http://www.cyberpolice.cn/wfjb/" target="_blank"><img src="http://www.jxeduyun.com/App.ResourceCloud/Src/apps/changyan/_static/common/images/jx_footer_pic02.png" alt="网络110报警服务"></a>
				</p>

			</div>
		</div>
	</div>
</div>
<!--         右侧飘窗 -->
<a href="javascript:void(0)" class="lxwm_div"></a>
<div class="right_kuang" style="display:none;">
	<div class="online_cs">
		<div class="kf">
			<p>在线客服</p>
			<span><b>客服01</b><a href="javascript:void(0)"></a></span>
			<span><b>客服02</b><a href="javascript:void(0)"></a></span>
		</div>
		<div class="kf_phone">
			<p>客服电话</p>
			<span>400-4008820<br>工作日：9:00-17:30</span>

		</div>
		<div class="wechat_oa">
			<p>微信公众号</p>
			<i></i>
		</div>
	</div>
	<div class="advice_feedback">
		<i></i><a href="http://maiqituo.mikecrm.com/06I15Mx">意见反馈</a>
	</div>
	<div class="questionnaire">
		<i></i><a href="http://dypt.jxdnjy.com/">调查问卷</a>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jxdnjy/js/login/jquery-1.11.0.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jxdnjy/js/login/jquery.placeholder.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jxdnjy/js/login/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jxdnjy/js/login/prettify.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jxdnjy/js/login/jquery.ua.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jxdnjy/js/cas.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jxdnjy/js/waterbubble.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jxdnjy/js/highcharts/highcharts.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jxdnjy/js/jquery.easing.1.3.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/jxdnjy/js/lanrenzhijia.js" ></script>
<script>
    $(function(){
        $('.kj_div .kj_top .kj_fl a').click(function(){
            var i=$(this).index();
            $('.kj_div .kj_top .kj_fl a').removeClass('on');
            $(this).addClass('on');
            $('.yxkj_list').hide();
            $('.yxkj_list').eq(i).show();
		})
        var t;
        //$('input, textarea').placeholder();
        $(".lxwm_div").hover(function(){
            $('.right_kuang').show();
            $(this).hide();
        },function(){
            t=setTimeout("$('.right_kuang').hide()",1000);
            $(this).show();
        });
        $('.right_kuang').hover(function(){
            clearTimeout(t)
        },function(){
            $(this).hide();
            $(".lxwm_div").show();
        });
        /*判断浏览器版本是否过低*/
        $(function(){
            var b_name = navigator.appName;
            var b_version = navigator.appVersion;
            var version = b_version.split(";");
            if (b_name == "Microsoft Internet Explorer") {
                var trim_version = version[1].replace(/[ ]/g, "");
                /*如果是IE6或者IE7*/
                if (trim_version == "MSIE7.0" || trim_version == "MSIE6.0"|| trim_version == "MSIE8.0") {
                    $("#cas").load("${pageContext.request.contextPath}/views/download.jsp");
                }
            }
            if($.ua.is360se||$.ua.isLiebao||$.ua.isMaxthon||$.ua.isQQ||$.ua.isSougou){
                $("#cas").load("${pageContext.request.contextPath}/views/download.jsp");
            }

        });
    });
    $(function(){
        //水滴统计图
        $('#yhhyl').waterbubble({
            data: 0.2 ,
            txt: '20%',
            waterColor:'#8ec31e',
            textColor: '#769f1e',
            radius: 48
        });
        $('#kjktl').waterbubble({
            data: 0.15 ,
            txt: '15%',
            waterColor:'#f5a948',
            textColor: '#c68b3f',
            radius: 48
        });
        //资源建设总量
        $('#zyjszl').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false
            },
            colors:[
                '#65ad57',//第一个颜色，欢迎加入Highcharts学习交流群294191384
                '#f5a948',//第二个颜色
                '#2298ef',//第三个颜色
                '#ff5534', //。。。。
                '#b035b8',
                '#27b0ce'
            ],
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                name: "占比",
                colorByPoint: true,
                data: [{
                    name: "小学",
                    y: 181320
                }, {
                    name: "初中",
                    y: 611562
                }, {
                    name: "高中",
                    y: 123815
                }]
            }]
        });
        //小学资源总量
        $('#xxzyzl').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false
            },
            colors:[
                '#65ad57',//第一个颜色，欢迎加入Highcharts学习交流群294191384
                '#f5a948',//第二个颜色
                '#2298ef',//第三个颜色
                '#ff5534', //。。。。
                '#b035b8',
                '#27b0ce'
            ],
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                name: "占比",
                colorByPoint: true,
                data: [{
                    name: "英语",
                    y: 38331
                }, {
                    name: "语文",
                    y: 32710
                }, {
                    name: "数学",
                    y: 40216
                }, {
                    name: "品德与生活（社会）",
                    y: 7561
                }, {
                    name: "音乐",
                    y: 11677
                }, {
                    name: "信息技术",
                    y: 13163
                }, {
                    name: "科学",
                    y: 14759
                }, {
                    name: "美术",
                    y: 13308
                }, {
                    name: "艺术",
                    y: 9595
                }]
            }]
        });
        //初中资源总量
        $('#czzyzl').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false
            },
            colors:[
                '#65ad57',//第一个颜色，欢迎加入Highcharts学习交流群294191384
                '#f5a948',//第二个颜色
                '#2298ef',//第三个颜色
                '#ff5534', //。。。。
                '#b035b8',
                '#27b0ce'
            ],
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                name: "占比",
                colorByPoint: true,
                data: [{
                    name: "英语",
                    y: 87514
                }, {
                    name: "语文",
                    y: 99868
                }, {
                    name: "数学",
                    y: 93446
                }, {
                    name: "生物",
                    y: 37060
                }, {
                    name: "化学",
                    y: 38406
                }, {
                    name: "历史",
                    y: 50025
                }, {
                    name: "地理",
                    y: 44705
                }, {
                    name: "政治",
                    y: 31948
                },{
                    name: "物理",
                    y: 31556
                }, {
                    name: "其他",
                    y: 97034
                }]
            }]
        });
        //高中资源总量
        $('#gzzyzl').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: ''
            },
            credits: {
                enabled: false
            },
            colors:[
                '#65ad57',//第一个颜色，欢迎加入Highcharts学习交流群294191384
                '#f5a948',//第二个颜色
                '#2298ef',//第三个颜色
                '#ff5534', //。。。。
                '#b035b8',
                '#27b0ce'
            ],
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                name: "占比",
                colorByPoint: true,
                data: [{
                    name: "英语",
                    y: 87314
                }, {
                    name: "语文",
                    y: 92768
                }, {
                    name: "数学",
                    y: 104446
                }, {
                    name: "生物",
                    y: 37090
                }, {
                    name: "化学",
                    y: 39426
                }, {
                    name: "历史",
                    y: 54025
                }, {
                    name: "地理",
                    y: 41705
                }, {
                    name: "政治",
                    y: 29948
                },{
                    name: "物理",
                    y: 30556
                }, {
                    name: "其他",
                    y: 90034
                }]
            }]
        });
        $('#zyzlfb').highcharts({
            chart: {
                type: 'column',
                backgroundColor: 'rgba(0,0,0,0)'
            },
            title: {
                text: '资源总量分布'
            },
            subtitle: {
                text: ''
            },
            credits:{
                enabled:false // 禁用版权信息
            },
            xAxis: {
                categories: [
                    '微课',
                    '课件',
                    '作业',
                    '试卷',
                    '教案',
                    '素材',
                    '导学案',
                    '其他'
                ],
                labels: {
                    style: {
                        color: '#666',//颜色
                    }
                },
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                },
                labels: {
                    style: {
                        color: '#fff',//颜色
                    }
                },
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{point.y} </td>' +
                '</tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            legend:{
                enabled :false
            },
            plotOptions: {
                column: {
                    dataLabels:{
                        enabled:true, // dataLabels设为true
                        style:{
                            color:'#fff'
                        }
                    },
                    colorByPoint:true
                }
            },
            series: [{
                //colorByPoint:true,  或者直接写在这里
                data: [7000,364472,79241,48484,180759,173317,64187,973]
            }]
        });
        var t1;
        $('.login_btn button').hover(function(){
            clearTimeout(t1);
            $('#content').show();
        },function(){
            t1 = setTimeout("$('#content').hide()", 500);
        });
        $('#content').hover(function(){
            clearTimeout(t1);
        },function(){
            $('#content').hide();
        })
    })
	$(function(){
	    // 空间data
            $.getJSON('http://sns.studyo.cn/index.php?app=public&mod=extern&act=userInfo&user_type=7&start=0&offset=9',function(data){
                $(data.data).each(function(i){
                    if(data.data[i].icon==''){
                        data.data[i].icon='${pageContext.request.contextPath}/res/jxdnjy/images/tx.png'
					}
                    $('<li><a href="'+data.data[i].url+'" data-id="'+data.data[i].uid+'" target="_blank"><img src="'+data.data[i].icon+'"><p class="p1">'+data.data[i].name+'</p><p class="p2"></p><p class="p3">'+data.data[i].school_name+'</p></a></li>').appendTo($('.yxkj_list').eq(0).children('ul'))
                });
			});
        $.getJSON('http://sns.studyo.cn/index.php?app=public&mod=extern&act=userInfo&user_type=8&start=0&offset=9',function(data){
            $(data.data).each(function(i){
                if(data.data[i].icon==''){
                    data.data[i].icon='${pageContext.request.contextPath}/res/jxdnjy/images/tx1.png'
                }
                $('<li><a href="'+data.data[i].url+'" data-id="'+data.data[i].uid+'" target="_blank"><img src="'+data.data[i].icon+'"><p class="p1">'+data.data[i].name+'</p><p class="p2"></p><p class="p3">'+data.data[i].school_name+'</p></a></li>').appendTo($('.yxkj_list').eq(1).children('ul'))
            });
        });
        $.getJSON('http://sns.studyo.cn/index.php?app=public&mod=extern&act=userInfo&user_type=9&start=0&offset=9',function(data){
            $(data.data).each(function(i){
                if(data.data[i].icon==''){
                    data.data[i].icon='${pageContext.request.contextPath}/res/jxdnjy/images/tx.png'
                }
                $('<li><a href="'+data.data[i].url+'" data-id="'+data.data[i].uid+'" target="_blank"><img src="'+data.data[i].icon+'"><p class="p1">'+data.data[i].name+'</p><p class="p2"></p><p class="p3">'+data.data[i].school_name+'</p></a></li>').appendTo($('.yxkj_list').eq(2).children('ul'))
            });
        });
        $.getJSON('http://sns.studyo.cn/index.php?app=public&mod=extern&act=feedList&school_code=&count=4',function(data){
            $(data).each(function(i){
                if(data[i].icon==''||data[i].icon==undefined){
                    data[i].icon='${pageContext.request.contextPath}/res/jxdnjy/images/tx.png'
                }
                $('<li><p class="time">'+time_compare(data[i].publish_time)+'</p><img src="'+data[i].icon+'"><p class="teacher"><span>'+data[i].uname+'</span>老师</p><p class="paper">发表了文章<a href="'+data[i].url+'" target="_blank">'+data[i].title+'</a></p></li>').appendTo($('.kjdt_list').children('ul'))
            });
        });
        function time_compare(time){
            // var unixTimestamp = new Date(time * 1000)
			// var  commonTime = unixTimestamp.toLocaleString()
          //  var date_time = new Date(time);
			var current_time=Math.round(new Date().getTime()/1000);
            var nTime = current_time - time;
            var day = Math.floor(nTime/86400);
            var hour = Math.floor(nTime%86400000/3600);
            var minute = Math.floor(nTime%86400000%3600/60);
            console.log(nTime+"+"+day+"+"+hour+"+"+minute);
            if(nTime<3600){
                return '刚刚'
			}else if(nTime<3600*24){
                return hour+'小时前'
			}else{
                return day+'天前'
			}
			// if(day==0&&hour==0){
             //    return '刚刚'
			// }else if(data==0){
             //    return hour+'小时前'
			// }else{
             //    return day+'天前'
			// }
		}
	})
</script>
</body>
</html>