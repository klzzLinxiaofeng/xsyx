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
	$(".sq_list .sz_style .sz_1 a").click(function(){
			$(this).siblings().removeClass("on");
			$(this).addClass("on");
	});
});
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
				            <li><a href="javascript:void(0)" class="on">待处理文印</a></li>
				            <li><a href="javascript:void(0)" >已处理文印</a></li>
				            <li><a href="javascript:void(0)"  >文印统计</a></li>
				        </ul>
					</div>
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
									<div class="sz_style">
										<p class="sz_0">请点击按钮，选择当前的处理状态</p>
										<div class="sz_1">
											<a href="javascript:void(0)" class="a1 on">处理中</a>
											<a href="javascript:void(0)" class="a2">已处理</a>
											<a href="javascript:void(0)" class="a3">未处理</a>
										</div>
										<div class="sz_2">
											<input type="text" class="span12" placeholder="预计完成时间" />
										</div>
									</div>
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>发出的申请</div>
										<div class="p2">校庆海报设计工作</div>
										<div class="p5">6月20日校庆，请安排设计一份关于校庆的宣传海报，并印刷出来。</div>
										<div class="p3"><i class="fa fa-flag"></i><p class="p_div">部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">联系人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>13800138000</span></div>
										<div class="p3"><i class="fa fa-truck"></i><p class="p_div">提货方式</p><span>自提</span></div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="sz_style">
										<p class="sz_0">请点击按钮，选择当前的处理状态</p>
										<div class="sz_1">
											<a href="javascript:void(0)" class="a1 ">处理中</a>
											<a href="javascript:void(0)" class="a2">已处理</a>
											<a href="javascript:void(0)" class="a3 on">未处理</a>
										</div>
										<div class="sz_2">
											<textarea rows="6" cols="" placeholder="未处理事由"></textarea>
										</div>
										<div class="sz_1">
											<a href="javascript:void(0)" class="a4 on">提交</a>
										</div>
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
</body>
</html>