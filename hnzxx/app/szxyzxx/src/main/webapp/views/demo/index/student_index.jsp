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
<title>学生首页</title>
<style type="text/css">
</style>
</head>
<body>
<div class="main"></div>
	<div class="content">
	<div class="change_bg"><a href="javascript:void(0)"></a></div>
		<div class="i_left">
			<div class="mk_list">
				<p class="title">云学习</p>
				<ul>
					<li class="WEI_KE">
						<a href="javascript:void(0)">
							<span>微课</span>
						</a>
					</li>
					<li class="YUN_XUE_XI_ZUO_YE">
						<a href="javascript:void(0)">
							<span>作业</span>
							<b>2</b>
						</a>
					</li>
					<li class="YUN_XUE_XI_KE_JIAN">
						<a href="javascript:void(0)">
							<span>课件</span>
						</a>
					</li>
					<li class="YUN_XUE_XI_SHI_JUAN">
						<a href="javascript:void(0)">
							<span>试卷</span>
						</a>
					</li>
					<li class="YUN_XUE_XI_LIAN_XI">
						<a href="javascript:void(0)">
							<span>练习</span>
							<b>4</b>
						</a>
					</li>
				</ul>
				<div class="clear"></div>
			</div>
			<div class="mk_list">
				<p class="title">云资源</p>
				<ul>
					<li class="WEI_KE_ZI_YUAN">
						<a href="javascript:void(0)">
							<span>微课资源</span>
						</a>
					</li>
					<li class="XUE_KE_ZI_YUAN">
						<a href="javascript:void(0)">
							<span>学科资源</span>
						</a>
					</li>
					
				</ul>
				<div class="clear"></div>
			</div>
		</div>
		<div class="i_right">
			<div class="time">
				<div class="zl">
					<img alt="img" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
					<div class="z_right">
						<p class="name">刘艳青</p>
						<div class="js">
							<div class="js_li" style="padding-right:20px;border-right:1px solid #CCCCCC">
								<p class="p1">所属班级</p>
								<p class="p2">五年级（1）班</p>
							</div>
							<div class="js_li" style="padding-left:20px;width:40px">
								<p class="p1">角色</p>
								<p class="p2">学生</p>
							</div>
						</div>
						<p class="data">2015-07-03  &nbsp; 星期五  &nbsp; 农历五月十八</p>
					</div>
				</div>
				<div class="tianqi" style="padding:25px 5px 30px;">
					<!-- <iframe allowtransparency="true" frameborder="0" width="130" height="130" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=1&z=1&t=0&v=1&d=1&bd=0&k=&f=ffffff&q=1&e=1&a=1&c=54511&w=130&h=130&align=center"></iframe> -->
					<iframe width="130" scrolling="no" height="120" id="FF" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=4&color=%23FFFFFF&icon=3&wind=1&num=1"></iframe>
				</div>
			</div>
			<div class="kebiao">
				<div class="kb_select">
					<a href="javascript:void(0)">课程表</a>
				</div>
				<div class="kb_list">
					<div class="kcb" >
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
	$(window.frames["FF"].document).find("body").css("background-color","#000");
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
				title: '生日',
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
		$(".content").css("background","url("+src+") no-repeat");
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
