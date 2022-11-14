<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>文印</title>
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
			<jsp:param value="文印" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" class="on">我申请的</a></li>
				            <li><a href="javascript:void(0)" >全部文印</a></li>
				            <li><a href="javascript:void(0)">部门文印</a></li>
				        </ul>
					</div>
					<div class="wy_all">
						<div class="sq_list">
						<div class="search_1">
							<input type="text" placeholder="车名/车牌号">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li><a href="javascript:void(0)">我的申请（2）</a></li>
							</ul>
							<button class="btn btn-success">申请文印</button>
						</div>
						<div class="clsq">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>发出的申请</div>
										<div class="p2">优秀教师评选的材料帮我打印10份<span class="chulizhong">[处理中]</span></div>
										<div class="p5">黑白打印，要装订好。</div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p3"><i class="fa fa-flag"></i><p class="p_div">部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">联系人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>13800138000</span></div>
										<div class="p3"><i class="fa fa-truck"></i><p class="p_div">提货方式</p><span>自提</span></div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>发出的申请</div>
										<div class="p2">校庆海报设计工作<span class="success">[已处理]</span></div>
										<div class="p5">6月20日校庆，请安排设计一份关于校庆的宣传海报，并印刷出来。</div>
										<div class="p3"><i class="fa fa-flag"></i><p class="p_div">部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">联系人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>13800138000</span></div>
										<div class="p3"><i class="fa fa-truck"></i><p class="p_div">提货方式</p><span>自提</span></div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo">
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="clear"></div>
								</li>
							</ul>
							<div class="empty" style="display:none">
								<p>暂无文印申请</p>
							</div>
						</div>
					</div>
					<div class="sq_list" style="display:none">
						<div class="search_1">
							<input type="text" placeholder="车名/车牌号">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li class="on"><a href="javascript:void(0)">全部文印（2）</a></li>
								<li><a href="javascript:void(0)">教务处（1）</a></li>
								<li><a href="javascript:void(0)">纪律部（1）</a></li>
							</ul>
							<button class="btn btn-success">申请文印</button>
						</div>
						<div class="f_wy">
							<div class="clsq">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>发出的申请</div>
										<div class="p2">优秀教师评选的材料帮我打印10份<span class="chulizhong">[处理中]</span></div>
										<div class="p5">黑白打印，要装订好。</div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p3"><i class="fa fa-flag"></i><p class="p_div">部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">联系人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>13800138000</span></div>
										<div class="p3"><i class="fa fa-truck"></i><p class="p_div">提货方式</p><span>自提</span></div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>发出的申请</div>
										<div class="p2">校庆海报设计工作<span class="success">[已处理]</span></div>
										<div class="p5">6月20日校庆，请安排设计一份关于校庆的宣传海报，并印刷出来。</div>
										<div class="p3"><i class="fa fa-flag"></i><p class="p_div">部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">联系人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>13800138000</span></div>
										<div class="p3"><i class="fa fa-truck"></i><p class="p_div">提货方式</p><span>自提</span></div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo">
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="clear"></div>
								</li>
							</ul>
							<div class="empty" style="display:none">
								<p>暂无文印申请</p>
							</div>
						</div>
						<div class="clsq" style="display:none">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>发出的申请</div>
										<div class="p2">校庆海报设计工作<span class="success">[已处理]</span></div>
										<div class="p5">6月20日校庆，请安排设计一份关于校庆的宣传海报，并印刷出来。</div>
										<div class="p3"><i class="fa fa-flag"></i><p class="p_div">部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">联系人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>13800138000</span></div>
										<div class="p3"><i class="fa fa-truck"></i><p class="p_div">提货方式</p><span>自提</span></div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo">
										<a class="delete" href="javascript:void(0)">删除</a>
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
									<div class="detail">
										<div class="p1"><span>刘艳青</span>发出的申请</div>
										<div class="p2">班级海报设计工作<span class="success">[已处理]</span></div>
										<div class="p5">6月20日校庆，请安排设计一份关于校庆的宣传海报，并印刷出来。</div>
										<div class="p3"><i class="fa fa-flag"></i><p class="p_div">部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">联系人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>13800138000</span></div>
										<div class="p3"><i class="fa fa-truck"></i><p class="p_div">提货方式</p><span>自提</span></div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo">
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="clear"></div>
								</li>
							</ul>
						</div>
						</div>
						
					</div>
					<div class="sq_list" style="display:none">
						<div class="search_1">
							<input type="text" placeholder="车名/车牌号">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li><a href="javascript:void(0)">部门文印（2）</a></li>
							</ul>
							<button class="btn btn-success">申请文印</button>
						</div>
						<div class="clsq">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>发出的申请</div>
										<div class="p2">优秀教师评选的材料帮我打印10份<span class="chulizhong">[处理中]</span></div>
										<div class="p5">黑白打印，要装订好。</div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p3"><i class="fa fa-flag"></i><p class="p_div">部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">联系人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>13800138000</span></div>
										<div class="p3"><i class="fa fa-truck"></i><p class="p_div">提货方式</p><span>自提</span></div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>发出的申请</div>
										<div class="p2">校庆海报设计工作<span class="success">[已处理]</span></div>
										<div class="p5">6月20日校庆，请安排设计一份关于校庆的宣传海报，并印刷出来。</div>
										<div class="p3"><i class="fa fa-flag"></i><p class="p_div">部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">联系人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>13800138000</span></div>
										<div class="p3"><i class="fa fa-truck"></i><p class="p_div">提货方式</p><span>自提</span></div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo">
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="clear"></div>
								</li>
							</ul>
							<div class="empty" style="display:none">
								<p>暂无文印申请</p>
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