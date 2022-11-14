<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/bootstrap.css" rel="stylesheet"> 
<link href="${pageContext.request.contextPath}/res/css/sygb/new_index.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/fullcalendar.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/date/lunar_calendar.js"></script> 
<title>个性化首页</title>
</head>
<body>
<div class="content">
	<!-- 左侧开始 -->
	<div class="index_tjdiv">
		<div class="index_tj">
			<!-- 放左侧栏目 -->
			<div class="first_nav" id="first_nav">
				<ul>
					<li ><a href="#xtgl" >系统管理</a></li>
					<li ><a href="#zhszpj">综合素质评价</a></li>
					<li ><a href="#dsjgl">大数据管理</a></li>
					<li ><a href="#sfgl" >收费管理</a></li>
					<li ><a href="#kfgl">客服管理</a></li>
					<li><a href="#ybg">云办公</a></li>
					<li><a href="#yzy">云资源</a></li>
				</ul>
			</div>
			<div class="help_text">
				<div class="help_btn"><a href="javascript:void(0)">使用手册</a><a href="javascript:void(0)">？<br>帮助</a></div>
			</div>
			<!-- 中间应用 -->
			<div class="i_left" id="div_left">
				<div class="mk_list">
					<p class="title" id="xtgl">系统管理<span style="display:none">XI_TONG_GUAN_LI</span></p>
					<ul>
						<li class="YONG_HU_GUAN_LI"><a href="javascript:void(0)"><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>用户管理</span></a></li>
						<li class="JUE_SE_GUAN_LI"><a href="javascript:void(0)"><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>角色管理</span></a></li>
					</ul>
					<div class="clear"></div>
				</div>
				<div class="mk_list">
				   <p class="title" id="zhszpj">综合素质评价<span style="display:none">JIAO_XUE_GUAN_LI</span></p>
				   <ul>
					    <li class="JIAO_XUE_JI_HUA"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划</span></a></li>
					    <li class="JIAO_XUE_JI_HUA_GUAN_LI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划管理</span></a></li>
					    <li class="JIAO_AN"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案</span></a></li>
					    <li class="JIAO_AN_GUAN_LI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案管理</span></a></li>
					    <li class="JIAO_XUE_JI_HUA_TONG_JI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划统计</span></a></li>
					    <li class="JIAO_AN_TONG_JI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案统计</span></a></li>
				   </ul>
				   <div class="clear"></div>
				</div>
				<div class="mk_list">
				   <p class="title" id="dsjgl">大数据管理<span style="display:none">JIAO_XUE_GUAN_LI</span></p>
				   <ul>
					    <li class="JIAO_XUE_JI_HUA"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划</span></a></li>
					    <li class="JIAO_XUE_JI_HUA_GUAN_LI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划管理</span></a></li>
					    <li class="JIAO_AN"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案</span></a></li>
					    <li class="JIAO_AN_GUAN_LI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案管理</span></a></li>
					    <li class="JIAO_XUE_JI_HUA_TONG_JI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划统计</span></a></li>
					    <li class="JIAO_AN_TONG_JI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案统计</span></a></li>
				   </ul>
				   <div class="clear"></div>
				</div>
				<div class="mk_list">
				   <p class="title" id="sfgl">收费管理<span style="display:none">JIAO_XUE_GUAN_LI</span></p>
				   <ul>
					    <li class="JIAO_XUE_JI_HUA"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划</span></a></li>
					    <li class="JIAO_XUE_JI_HUA_GUAN_LI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划管理</span></a></li>
					    <li class="JIAO_AN"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案</span></a></li>
					    <li class="JIAO_AN_GUAN_LI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案管理</span></a></li>
					    <li class="JIAO_XUE_JI_HUA_TONG_JI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划统计</span></a></li>
					    <li class="JIAO_AN_TONG_JI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案统计</span></a></li>
				   </ul>
				   <div class="clear"></div>
				</div>
				<div class="mk_list">
				   <p class="title" id="kfgl">客服管理<span style="display:none">JIAO_XUE_GUAN_LI</span></p>
				   <ul>
					    <li class="JIAO_XUE_JI_HUA"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划</span></a></li>
					    <li class="JIAO_XUE_JI_HUA_GUAN_LI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划管理</span></a></li>
					    <li class="JIAO_AN"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案</span></a></li>
					    <li class="JIAO_AN_GUAN_LI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案管理</span></a></li>
					    <li class="JIAO_XUE_JI_HUA_TONG_JI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划统计</span></a></li>
					    <li class="JIAO_AN_TONG_JI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案统计</span></a></li>
				   </ul>
				   <div class="clear"></div>
				</div>
				<div class="mk_list">
				   <p class="title" id="ybg">云办公<span style="display:none">JIAO_XUE_GUAN_LI</span></p>
				   <ul>
					    <li class="JIAO_XUE_JI_HUA"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划</span></a></li>
					    <li class="JIAO_XUE_JI_HUA_GUAN_LI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划管理</span></a></li>
					    <li class="JIAO_AN"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案</span></a></li>
					    <li class="JIAO_AN_GUAN_LI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案管理</span></a></li>
					    <li class="JIAO_XUE_JI_HUA_TONG_JI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划统计</span></a></li>
					    <li class="JIAO_AN_TONG_JI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案统计</span></a></li>
				   </ul>
				   <div class="clear"></div>
				</div>
				<div class="mk_list">
				   <p class="title" id="yzy">云资源<span style="display:none">JIAO_XUE_GUAN_LI</span></p>
				   <ul>
					    <li class="JIAO_XUE_JI_HUA"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划</span></a></li>
					    <li class="JIAO_XUE_JI_HUA_GUAN_LI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划管理</span></a></li>
					    <li class="JIAO_AN"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案</span></a></li>
					    <li class="JIAO_AN_GUAN_LI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案管理</span></a></li>
					    <li class="JIAO_XUE_JI_HUA_TONG_JI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教学计划统计</span></a></li>
					    <li class="JIAO_AN_TONG_JI"><a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/res/css/sygb/images/new_icon/common.png"><span>教案统计</span></a></li>
				   </ul>
				   <div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
<!-- 左侧结束 -->
<!-- 右侧开始 -->
<div class="i_right">
	<div class="time">
		<div class="zl">
			<img alt="img" src="http://cdn.test.studyo.cn:8082/develop/test/2018-4-9/66abac09baa50107fafee7e60714d3d5.jpg">
			<div class="z_right">
				<p class="name">马云弟</p>
				<div class="js">
					<div class="js_li" >
						<p class="p1">
							所属部门	
						</p>
						<p class="p2" title="${depOrteamName }">行政部门</p>
					</div>
					<div class="js_li" style="padding-left:20px;padding-right:0">
						<p class="p1">角色</p>
						<p class="p2" title="${roleName }">科任教师</p>
					</div>
				</div>
				<p class="data" id="lunar">2015-07-03  &nbsp; 星期五  &nbsp; 农历五月十八</p>
			</div>
		</div>
	</div>
	<div class="kebiao">
			<div class="kb_select">
				<a href="javascript:void(0)" class="on">我的日程</a>
				<a href="javascript:void(0)" onclick="getSyllabus();">课程表</a>
			</div>
			<div class="kb_list">
				<div class="wdkc" >
					<div id='calendar'>
					</div>
				</div>
				<div style="display:none;">
					<div class="kcb">
						<table class="responsive table  table-bordered table-striped">
							<thead>
							<tr>
								<th></th>
								<th>一</th>
								<th>二</th>
								<th>三</th>
								<th>四</th>
								<th>五</th>
								<th>六</th>
								<th>日</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<td>第1节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第2节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第3节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第4节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第5节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第6节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第7节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
</div>
</div>
<script type="text/javascript">
	$(function(){
		/*左侧栏目*/
		$('.first_nav ul li a').each(function(){
			var text_size=$(this).text().length;
			if(text_size==4){
				$(this).addClass('four');
				$(this).parent().addClass('radius_21')
			}else if(text_size>4){
				$(this).parent().addClass('radius_21')
			}
		});
		$('#calendar').fullCalendar({
            height: 250,
            header: {
                left: 'prev',
                center: 'title',
                right: 'next today'
            },
            editable: false,
	       });
		$(".kebiao .kb_select a").click(function(){
            $(".kebiao .kb_select a").removeClass("on");
            $(this).addClass("on");
            var i=$(this).index();
            $(".kebiao .kb_list").children().hide();
            $(".kebiao .kb_list").children().eq(i).show();
        });
        /*左侧栏目*/
        $(".first_nav ul li a").click(function(){
        $(".first_nav ul li").removeClass("on");
        $(this).parent().addClass("on");
         $("html, body").animate({
            scrollTop: ($($(this).attr("href")).offset().top-35) + "px"
          }, {
            duration: 500,
            easing: "swing"
          });
          return false;
      });

      /*$('.content .i_left .mk_list .title').each(function(index){
      		var index=$(this).offset().top;
      		console.log('index')
      })*/
      var zhxy_top = $("#zhxy").offset().top;
      var zhcb_top = $("#zhcb").offset().top;
      var zhykt_top = $("#zhykt").offset().top;
      var zhab_top = $("#zhab").offset().top;
      var zhjx_top = $("#zhjx").offset().top;
      var zhgl_top = $("#zhgl").offset().top;
      var ydxy_top = $("#ydxy").offset().top;
      var zhyd_top = $("#zhyd").offset().top;
       
      $(window).scroll(function(){
          var this_scrollTop = $(this).scrollTop();
          $(".j_lift li").removeClass("on");
          if(this_scrollTop>=zhyd_top ){
             set_cur(".zhyd")
          }else if(this_scrollTop>=ydxy_top ){
             set_cur(".ydxy")
          }else if(this_scrollTop>=zhgl_top ){
             set_cur(".zhgl")
          }else if(this_scrollTop>=zhjx_top ){
             set_cur(".zhjx")
          }else if(this_scrollTop>=zhab_top ){
             set_cur(".zhab")
          }else if(this_scrollTop>=zhykt_top ){
             set_cur(".zhykt")
          }else if(this_scrollTop>=zhcb_top ){
             set_cur(".zhcb")
          }else if(this_scrollTop>=zhxy_top ){
             set_cur(".zhxy")
          }
      });
	})
</script>
</body>
</html>