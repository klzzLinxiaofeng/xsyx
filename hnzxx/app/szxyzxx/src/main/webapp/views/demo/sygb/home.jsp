<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/bootstrap.css" rel="stylesheet"> 
<link href="${pageContext.request.contextPath}/res/css/sygb/new_index.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/sygb/new_head.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
<title></title>
</head>
<body id="main_body" style="overflow: hidden">
<div class="layout">
<!-- 顶部start -->
	<div class="navbar navbar-inverse top-nav">
	<div class="navbar-inner">
		<div class="container">
			<!-- logo -->
			<a class="brand" href="javascript:void(0)"></a>
			<!-- 右边 -->
			<div class="btn-toolbar pull-right notification-nav">
				<div class="btn-group">
                    <div class="dropdown">
                       <div class="control-group">
                            <div class="controls input-icon">
                                <i class=""></i>
                                <input type="text" placeholder="请输入搜索">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="btn-group">
                    <div class="dropdown">
                        <a class="btn btn-notification dropdown-toggle">
                            <p class="change_skin"><b class="icon"></b>换肤</p>
                        </a>
                        <div class="dropdown-menu pull-right skin_content" style="">
                        	<div class="skin_list">
                        		<span class="span1 on"></span>
                        		<span class="span2"></span>
                        		<span class="span3"></span>
                        		<span class="span4"></span>
                        		<div class="clear"></div>
                        	</div>
							<p class="caozuo">
								<button class="btn btn-blue">保存</button>
							</p>
						</div>
                    </div>
                </div>
                <div class="btn-group">
					<div class="dropdown">
						<a class="btn btn-notification dropdown-toggle">
                            <p class="view_message"><b class="icon"></b>消息</p>
							<span class="notify-tip" id="count" style="color:#fff;">+54</span></a>
						<div class="dropdown-menu pull-right  notice1" style="padding: 0 !important;">
							<p class="p1"><span class="on">系统消息</span><span>教师消息</span></p>
							<div class="notice_list">
								<ul>
									<li>
										<span class="icon fl"></span>
										<div class="fl text">
											<h4 class="h4">通知公告<i class="new"></i></h4>
											<p class="bold">
												<span class="title">您有新的通知公告！</span>
												<a href="javascript:void(0);" class="bold">点击查看</a>
											</p>
										</div>
										<div class="fr m-time">
											<em>25天前</em>
										</div>
										<div class="clear"></div>
									</li>
									<li>
										<span class="icon fl"></span>
										<div class="fl text">
											<h4 class="h4">通知公告</h4>
											<p class="bold">
												<span class="title">您有新的通知公告！新的通知公告请查询！</span>
												<a href="javascript:void(0);" class="bold">点击查看</a>
											</p>
										</div>
										<div class="fr m-time">
											<em>25天前</em>
										</div>
										<div class="clear"></div>
									</li>
									<li>
										<span class="icon fl"></span>
										<div class="fl text">
											<h4 class="h4">通知公告</h4>
											<p class="bold">
												<span class="title">您有新的通知公告！</span>
												<a href="javascript:void(0);" class="bold">点击查看</a>
											</p>
										</div>
										<div class="fr m-time">
											<em>25天前</em>
										</div>
										<div class="clear"></div>
									</li>
								</ul>
							</div>
							<div class="notice_list" style="display: none;">
								<ul>
									<li>
										<span class="icon fl"></span>
										<div class="fl text">
											<h4 class="h4">通知公告1</h4>
											<p class="bold">
												<span class="title">您有新的通知公告！</span>
												<a href="javascript:void(0);" class="bold">点击查看</a>
											</p>
										</div>
										<div class="fr m-time">
											<em>25天前</em>
										</div>
										<div class="clear"></div>
									</li>
									<li>
										<span class="icon fl"></span>
										<div class="fl text">
											<h4 class="h4">通知公告2</h4>
											<p class="bold">
												<span class="title">您有新的通知公告！新的通知公告请查询！</span>
												<a href="javascript:void(0);" class="bold">点击查看</a>
											</p>
										</div>
										<div class="fr m-time">
											<em>25天前</em>
										</div>
										<div class="clear"></div>
									</li>
									<li>
										<span class="icon fl"></span>
										<div class="fl text">
											<h4 class="h4">通知公告3</h4>
											<p class="bold">
												<span class="title">您有新的通知公告！</span>
												<a href="javascript:void(0);" class="bold">点击查看</a>
											</p>
										</div>
										<div class="fr m-time">
											<em>25天前</em>
										</div>
										<div class="clear"></div>
									</li>
								</ul>
							</div>
						
							<p class="p2"><a href="javascript:void(0)">查看更多</a></p>
						</div>
					</div>
				</div>
				<div class="btn-group">
					<div class="dropdown">
						<a class="btn btn-notification dropdown-toggle drop_btn" data-toggle="dropdown" style="width: 155px;line-height:50px;"><img src="http://cdn.test.studyo.cn:8082/develop/test/2018-4-9/66abac09baa50107fafee7e60714d3d5.jpg" style="width: 35px; height: 35px; border-radius: 50%">
							<span class="name">
								马云弟 <b class="icon"></b>
							</span></a>
						<div class="dropdown-menu pull-right " style="">
							<a href="javascript:void(0)" onclick="SHOW_PROFILE();"> <i class="fa fa-user" style="padding-right: 5px;"></i>基本信息
							</a>
							<i class="x"></i>
							<a href="javascript:void(0)" onclick="SHOW_PASSWORD_EDITOR();"> <i class="fa fa-lock" style="padding-right: 5px;"></i>修改密码
							</a>
							<a href="javascript:void(0)" onclick="SYS_LOGOUT();"> <i class="fa fa-sign-out" style="padding-right: 5px;"></i>安全退出
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 顶部end -->
<div class="man_right">
	<iframe src="new_index.jsp" allowFullScreen marginheight="0" marginwidth="0" frameborder="0" scrolling="yes" width="100%" id="core_iframe" name="core_iframe"></iframe>
</div>
</div>
<script type="text/javascript">
$(function(){
	var h=$(parent.window).height();
	$('#core_iframe').height(h-60);
	$(window).resize(function(){
		var h=$(parent.window).height();
		$('#core_iframe').height(h-60);
	})
	
	$('.notice1 .p1 span').click(function(){
		$(this).siblings().removeClass('on');
		$(this).addClass('on');
		$('.notice_list').hide();
		$('.notice_list').eq($(this).index()).show();
	})
	
	$('.skin_list span').click(function(){
		$(this).siblings().removeClass('on');
		$(this).addClass('on');
	})
	
	var t;
	$('.dropdown-toggle').hover(function(){
		$('.dropdown-menu').hide();
		$(this).next('.dropdown-menu').slideDown('fast');
	},function(){
		var _this=$(this);
		function close_next(){
			_this.next('.dropdown-menu').hide();
		}
		t=setTimeout(close_next,500);
	})
	$('.dropdown-menu').hover(function(){
		clearTimeout(t);
	},function(){
		$(this).hide();
	})

})

</script>
</body>
</html>