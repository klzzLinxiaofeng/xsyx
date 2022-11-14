<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>会议</title>
<script type="text/javascript">
	$(function(){
		$(".sq_list .clsq ul li .caozuo .delete").click(function(){
			$(this).parent().parent().remove()
		});
		$(".oa_top .top_ul li a").click(function(){
			$(".oa_top .top_ul li a").removeClass("on");
			$(this).addClass("on");
			var i=$(this).parent().index();
			$(".sq_list").hide();
			$(".sq_list").eq(i).show();
		})
		$(".sq_list .entry ul li a").click(function(){
			$(".sq_list .entry ul li a").parent().removeClass("on");
			$(this).parent().addClass("on");
			var j=$(this).parent().index();
			$(this).parent().parent().parent().next().children().hide()
			$(this).parent().parent().parent().next().children().eq(j).show();
		})
	})
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="会议" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" class="on">与我相关</a></li>
				            <li><a href="javascript:void(0)">部门会议</a></li>
				            <li><a href="javascript:void(0)" >全部会议</a></li>
				            <li><a href="javascript:void(0)" >我组织的</a></li>
				        </ul>
					</div>
					<div class="wy_all">
						<div class="sq_list">
						<div class="search_1">
							<input type="text" placeholder="标题/发布人">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li><a href="javascript:void(0)">与我相关（2）</a></li>
							</ul>
							<button class="btn btn-success">组织会议</button>
						</div>
						<div class="clsq">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 one">
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>组织的会议</div>
										<div class="p7">关于认真做好2014—2015年度学校“优秀教师”评选表彰工作会议通知</div>
										<div class="p5">
											<i class="fa fa-clock-o"></i><p class="p_div">会议时间</p><span>6月21日 14：00 - 6月21日 16:00 </span>
											<b><span>2</span>天<span>3</span>小时后开始</b>
											<i class="fa  fa-map-marker"></i><p class="p_div">会议地点</p><span>东教学楼大会议室2层 </span>
										</div>
										<div class="p5"><i class="fa fa-user"></i><p class="p_div">主持人</p><span>刘艳青</span></div>
										<div class="p5"><i class="fa fa-users"></i><p class="p_div">参与人</p><span>罗静淼、陈冠洪、罗志明、陈文光</span></div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35</div>
									</div>
									
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 one">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p7">关于认真做好2014—2015年度学校“优秀教师”评选表彰工作会议通知</div>
										<div class="p5">
											<i class="fa fa-clock-o"></i><p class="p_div">会议时间</p><span>6月21日 14：00 - 6月21日 16:00 </span>
											<b><span>2</span>天<span>3</span>小时后开始</b>
											<i class="fa  fa-map-marker"></i><p class="p_div">会议地点</p><span>东教学楼大会议室2层 </span>
										</div>
										<div class="p5"><i class="fa fa-user"></i><p class="p_div">主持人</p><span>刘艳青</span></div>
										<div class="p5"><i class="fa fa-users"></i><p class="p_div">参与人</p><span>罗静淼、陈冠洪、罗志明、陈文光</span></div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>8/10</span></div></div>
									</div>
									
									<div class="clear"></div>
								</li>
							</ul>
							<div class="empty" style="display:none">
								<p>暂无会议</p>
							</div>
						</div>
					</div>
					<div class="sq_list" style="display:none">
						<div class="search_1">
							<input type="text" placeholder="标题/发布人">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li><a href="javascript:void(0)">部门会议（2）</a></li>
							</ul>
							<button class="btn btn-success">组织会议</button>
						</div>
						<div class="clsq">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 one">
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>组织的会议</div>
										<div class="p7">关于认真做好2014—2015年度学校“优秀教师”评选表彰工作会议通知</div>
										<div class="p5">
											<i class="fa fa-clock-o"></i><p class="p_div">会议时间</p><span>6月21日 14：00 - 6月21日 16:00 </span>
											<b><span>2</span>天<span>3</span>小时后开始</b>
											<i class="fa  fa-map-marker"></i><p class="p_div">会议地点</p><span>东教学楼大会议室2层 </span>
										</div>
										<div class="p5"><i class="fa fa-user"></i><p class="p_div">主持人</p><span>刘艳青</span></div>
										<div class="p5"><i class="fa fa-users"></i><p class="p_div">参与人</p><span>罗静淼、陈冠洪、罗志明、陈文光</span></div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35</div>
									</div>
									
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 one">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p7">关于认真做好2014—2015年度学校“优秀教师”评选表彰工作会议通知</div>
										<div class="p5">
											<i class="fa fa-clock-o"></i><p class="p_div">会议时间</p><span>6月21日 14：00 - 6月21日 16:00 </span>
											<b><span>2</span>天<span>3</span>小时后开始</b>
											<i class="fa  fa-map-marker"></i><p class="p_div">会议地点</p><span>东教学楼大会议室2层 </span>
										</div>
										<div class="p5"><i class="fa fa-user"></i><p class="p_div">主持人</p><span>刘艳青</span></div>
										<div class="p5"><i class="fa fa-users"></i><p class="p_div">参与人</p><span>罗静淼、陈冠洪、罗志明、陈文光</span></div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>8/10</span></div></div>
									</div>
									
									<div class="clear"></div>
								</li>
							</ul>
							<div class="empty" style="display:none">
								<p>暂无会议</p>
							</div>
						</div>
					</div>
					<div class="sq_list" style="display:none">
						<div class="search_1">
							<input type="text" placeholder="标题/发布人">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li class="on"><a href="javascript:void(0)">全部会议（2）</a></li>
								<li><a href="javascript:void(0)">教务处（1）</a></li>
								<li><a href="javascript:void(0)">纪律部（1）</a></li>
							</ul>
							<button class="btn btn-success">组织会议</button>
						</div>
						<div class="f_wy">
							<div class="clsq">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 one">
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>组织的会议</div>
										<div class="p7">关于认真做好2014—2015年度学校“优秀教师”评选表彰工作会议通知</div>
										<div class="p5">
											<i class="fa fa-clock-o"></i><p class="p_div">会议时间</p><span>6月21日 14：00 - 6月21日 16:00 </span>
											<b><span>2</span>天<span>3</span>小时后开始</b>
											<i class="fa  fa-map-marker"></i><p class="p_div">会议地点</p><span>东教学楼大会议室2层 </span>
										</div>
										<div class="p5"><i class="fa fa-user"></i><p class="p_div">主持人</p><span>刘艳青</span></div>
										<div class="p5"><i class="fa fa-users"></i><p class="p_div">参与人</p><span>罗静淼、陈冠洪、罗志明、陈文光</span></div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35</div>
									</div>
									
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 one">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p7">关于认真做好2014—2015年度学校“优秀教师”评选表彰工作会议通知</div>
										<div class="p5">
											<i class="fa fa-clock-o"></i><p class="p_div">会议时间</p><span>6月21日 14：00 - 6月21日 16:00 </span>
											<b><span>2</span>天<span>3</span>小时后开始</b>
											<i class="fa  fa-map-marker"></i><p class="p_div">会议地点</p><span>东教学楼大会议室2层 </span>
										</div>
										<div class="p5"><i class="fa fa-user"></i><p class="p_div">主持人</p><span>刘艳青</span></div>
										<div class="p5"><i class="fa fa-users"></i><p class="p_div">参与人</p><span>罗静淼、陈冠洪、罗志明、陈文光</span></div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>8/10</span></div></div>
									</div>
									
									<div class="clear"></div>
								</li>
							</ul>
							<div class="empty" style="display:none">
								<p>暂无公文</p>
							</div>
						</div>
						<div class="clsq" style="display:none">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 one">
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>组织的会议</div>
										<div class="p7">关于认真做好2014—2015年度学校“优秀教师”评选表彰工作会议通知</div>
										<div class="p5">
											<i class="fa fa-clock-o"></i><p class="p_div">会议时间</p><span>6月21日 14：00 - 6月21日 16:00 </span>
											<b><span>2</span>天<span>3</span>小时后开始</b>
											<i class="fa  fa-map-marker"></i><p class="p_div">会议地点</p><span>东教学楼大会议室2层 </span>
										</div>
										<div class="p5"><i class="fa fa-user"></i><p class="p_div">主持人</p><span>刘艳青</span></div>
										<div class="p5"><i class="fa fa-users"></i><p class="p_div">参与人</p><span>罗静淼、陈冠洪、罗志明、陈文光</span></div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35</div>
									</div>
									
									<div class="clear"></div>
								</li>
								
							</ul>
						</div>
						<div class="clsq" style="display:none">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 one">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p7">关于认真做好2014—2015年度学校“优秀教师”评选表彰工作会议通知</div>
										<div class="p5">
											<i class="fa fa-clock-o"></i><p class="p_div">会议时间</p><span>6月21日 14：00 - 6月21日 16:00 </span>
											<b><span>2</span>天<span>3</span>小时后开始</b>
											<i class="fa  fa-map-marker"></i><p class="p_div">会议地点</p><span>东教学楼大会议室2层 </span>
										</div>
										<div class="p5"><i class="fa fa-user"></i><p class="p_div">主持人</p><span>刘艳青</span></div>
										<div class="p5"><i class="fa fa-users"></i><p class="p_div">参与人</p><span>罗静淼、陈冠洪、罗志明、陈文光</span></div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>8/10</span></div></div>
									</div>
									
									<div class="clear"></div>
								</li>
							</ul>
						</div>
						</div>
						
					</div>
					<div class="sq_list" style="display:none">
						<div class="search_1">
							<input type="text" placeholder="标题/发布人">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li><a href="javascript:void(0)">我组织的（1）</a></li>
							</ul>
							<button class="btn btn-success">组织会议</button>
						</div>
						<div class="clsq">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 one">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p7">关于认真做好2014—2015年度学校“优秀教师”评选表彰工作会议通知</div>
										<div class="p5">
											<i class="fa fa-clock-o"></i><p class="p_div">会议时间</p><span>6月21日 14：00 - 6月21日 16:00 </span>
											<b><span>2</span>天<span>3</span>小时后开始</b>
											<i class="fa  fa-map-marker"></i><p class="p_div">会议地点</p><span>东教学楼大会议室2层 </span>
										</div>
										<div class="p5"><i class="fa fa-user"></i><p class="p_div">主持人</p><span>刘艳青</span></div>
										<div class="p5"><i class="fa fa-users"></i><p class="p_div">参与人</p><span>罗静淼、陈冠洪、罗志明、陈文光</span></div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>8/10</span></div></div>
									</div>
									
									<div class="clear"></div>
								</li>
							</ul>
							<div class="empty" style="display:none">
								<p>暂无会议</p>
							</div>
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>