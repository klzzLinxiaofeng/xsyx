<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/index.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/fullcalendar.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/fullcalendar.min.js"></script>
<title>教师首页</title>
<style type="text/css">
table.fc-border-separate{
color:#fff;
}
.fc-header-title h2{
	color:#fff;
}
.fc-header .fc-button{
	margin:6px 0;
}
.fc-header-title{
	margin-top:6px;
}
.fc-state-default{
 	opacity: 0.7;
}
.fc-event-inner{
	 text-overflow: ellipsis;
        white-space: nowrap;
        overflow: hidden;
        display: block;
       word-wrap:normal;
}
.fc-border-separate th, .fc-border-separate td{
	border-color:#999;
}
</style>
</head>
<body>
<div class="main"></div>
	<div class="content">
	<div class="change_bg"><a href="javascript:void(0)" ></a></div>
		<div class="i_left">
		</div>
		<div class="i_right">
			<div class="time">
				<div class="zl">
					<img alt="img" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
					<div class="z_right">
						<p class="name">刘艳青</p>
						<div class="js">
							<div class="js_li" style="padding-right:20px;border-right:1px solid #CCCCCC">
								<p class="p1">所属部门</p>
								<p class="p2">学生处</p>
							</div>
							<div class="js_li" style="padding-left:20px;width:40px">
								<p class="p1">角色</p>
								<p class="p2">教师</p>
							</div>
						</div>
						<p class="data">2015-07-03  &nbsp; 星期五  &nbsp; 农历五月十八</p>
					</div>
				</div>
				<div class="tianqi" style="padding:25px 5px 30px;">
					<!-- <iframe allowtransparency="true" frameborder="0" width="130" height="130" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=1&z=1&t=0&v=1&d=1&bd=0&k=&f=ffffff&q=1&e=1&a=1&c=54511&w=130&h=130&align=center"></iframe> -->
					<iframe width="130" scrolling="no" height="120" id="FF" frameborder="0" allowtransparency="true" src="https://i.tianqi.com/index.php?c=code&id=4&color=%23FFFFFF&icon=3&wind=1&num=1"></iframe>
				</div>
			</div>
			<div class="kebiao">
				<div class="kb_select">
					<a href="javascript:void(0)" class="on">我的日程</a>
					<a href="javascript:void(0)">课程表</a>
				</div>
				<div class="kb_list">
				<div class="wdkc" >
						<div id='calendar'>
							</div>
					</div>
					<div class="kcb" style="display:none;">
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
									<td><p>英语</p><p>初一(1)</p></td>
									<td><p>英语</p><p>初一(2)</p></td>
									<td><p>英语</p><p>初一(5)</p></td>
									<td></td>
									<td><p>英语</p><p>初一(1)</p></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>第2节</td>
									<td></td>
									<td><p>英语</p><p>初一(2)</p></td>
									<td></td>
									<td></td>
									<td><p>英语</p><p>初一(1)</p></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>第3节</td>
									<td></td>
									<td></td>
									<td><p>英语</p><p>初一(5)</p></td>
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
									<td><p>英语</p><p>初一(3)</p></td>
									<td><p>英语</p><p>初一(1)</p></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>第5节</td>
									<td><p>英语</p><p>初一(1)</p></td>
									<td></td>
									<td></td>
									<td></td>
									<td><p>英语</p><p>初一(1)</p></td>
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
			<div class="yyxz">
				<p class="yy_title">应用下载</p>
				<ul>
					<li>
						<a href="javascript:void(0)">
							<img alt="" src="${pageContext.request.contextPath}/res/images/yy_1.jpg">
							<span>迅云微课</span>
						</a>
					</li>
					<li>
						<a href="javascript:void(0)">
							<img alt="" src="${pageContext.request.contextPath}/res/images/yy_2.jpg">
							<span>学校数据系统</span>
						</a>
					</li>
					<li>
						<a href="javascript:void(0)">
							<img alt="" src="${pageContext.request.contextPath}/res/images/yy_3.jpg">
							<span>移动微课堂</span>
						</a>
					</li>
					<li>
						<a href="javascript:void(0)">
							<img alt="" src="${pageContext.request.contextPath}/res/images/yy_4.jpg">
							<span>无线互动</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="bizhi" style="display:none">
		<div class="b_top">
			<p>更换壁纸</p>
			<a href="javascript:void(0)" class="btn-close">x</a>
		</div>
		<div class="b_middle">
			<ul>
				<li>
					<a href="javascript:void(0)">
						<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi1.jpg" alt="壁纸">
						<span>壁纸1</span>
					</a>
				</li>
				<li class="on">
					<a href="javascript:void(0)">
						<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi2.jpg" alt="壁纸">
						<span>壁纸2</span>
					</a>
				</li>
				<li>
					<a href="javascript:void(0)">
						<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi3.jpg" alt="壁纸">
						<span>壁纸3</span>
					</a>
				</li>
				<li>
					<a href="javascript:void(0)">
						<img src="${pageContext.request.contextPath}/res/css/extra/images/bizhi4.jpg" alt="壁纸">
						<span>壁纸4</span>
					</a>
				</li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="b_bottom">
			<button class="btn btn-warning save">保存</button>
			<button class="btn btn-close">取消</button>
		</div>
	</div>
	<div class="zhezhao"></div>
	
</body>
<script>
$(function(){
	/* var a=$(".navbar .nav li a", window.top.document).text();
	var b=$(".panel-body>ul>li", window.top.document).children().children("span").text(); */
	// 顶部li个数 
	var l=$(".navbar .nav li", window.top.document).length;
	for(var k=0;k<l-1;k++){
		var title_html=$('.navbar .nav li a', window.top.document).eq(k+1).text();
		var menu_top_id=$('.navbar .nav li', window.top.document).eq(k+1).attr("data-menu-id");
		$(".content .i_left").append("<div class='mk_list'><p class='title'>"+title_html+"<span style='display:none'>"+menu_top_id+"</span></p></div>");
		var li_html=$(".panel-body", window.top.document).eq(k).children().children();
	// 	每个左边li的个数
		var l1=li_html.length;
		$(".content .i_left .mk_list").eq(k).append("<ul></ul>")
		 for(var m=0;m<l1;m++){
			var b=li_html.eq(m).children().children("span").text();
			var menu_id=li_html.eq(m).attr("data-menu-id");
			$(".content .i_left .mk_list").eq(k).children("ul").append("<li class="+menu_id+"><a href='javascript:void(0)'><span>"+b+"</span></a></li>")
		}
		$(".content .i_left .mk_list").eq(k).append("<div class='clear'></div>")
	}
	/* $(window.frames["FF"].document).find("body").css("background-color","#000"); */
	/* $("#FF",window.children.document); 
	$('#FF').contents().find("#mobile7 .wtbg").css("color","red"); */
})

$(document).ready(function() {
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	
	$('#calendar').fullCalendar({
		height: 250,
		header: {
			left: 'prev,next today',
			center: 'title',
			right: ''
		},
		editable: true,
		
		events: [
			{
				title: '今天',
				start: new Date(y, m, 1)
			},
			{
				title: '生日生日生日',
				start: new Date(y, m, d+1, 19, 0),
				end: new Date(y, m, d+1, 22, 30),
				allDay: false
			},
			{
				title: '要去桂林出差',
				start: new Date(y, m, 28),
				end: new Date(y, m, 29),
				url: 'http://google.com/'
			}
		]
	});
	
});

var h = document.documentElement.clientHeight;
var w= document.documentElement.clientWidth;
var h1 = (document.documentElement.clientHeight-296)/2;
var w1= (document.documentElement.clientWidth-744)/2;
$(function(){
	$(".main").css("min-height",h);
	$(".content").css("min-height",h-18);
	$(".content .change_bg a").click(function(e){
		e.stopPropagation();
		$(".bizhi,.zhezhao").show();
		$(".zhezhao").css({"width":w,"height":h});
		$(".bizhi").css({"left":w1,"top":h1});
	});
	$(".btn-close").click(function(){
		$(".bizhi,.zhezhao").hide();
	})
	$(".bizhi .b_middle ul li a").click(function(){
		$(".bizhi .b_middle ul li").removeClass("on");
		$(this).parent().addClass("on")
	});
	$(".save").click(function(){
		var src=$(".bizhi .b_middle ul .on img").attr("src");
		$(".content").css("background","url("+src+") repeat-y");
		$(".bizhi,.zhezhao").hide();
	});
	$(".bizhi").click(function (e) {
		e.stopPropagation();//阻止事件向上冒泡
		});

		$(document).click(function(){
		if(!$(".bizhi").is(":hidden")){
			$(".bizhi,.zhezhao").hide();
		}
		});
		
		$(".kebiao .kb_select a").click(function(){
			$(".kebiao .kb_select a").removeClass("on");
			$(this).addClass("on");
			var i=$(this).index();
			$(".kebiao .kb_list").children().hide();
			$(".kebiao .kb_list").children().eq(i).show();
		})
})
 
 
</script>
</html>
