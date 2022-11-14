<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>请假</title>
<script type="text/javascript">
	$(function(){
		$(".sq_list .clsq ul li .caozuo .delete").click(function(){
			$(this).parent().parent().remove()
		})
	})
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="车辆" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" class="on">我的请假</a></li>
				            <li><a href="javascript:void(0)" >部门请假</a></li>
				            <li><a href="javascript:void(0)">全部请假</a></li>
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
							<button class="btn btn-success">请假</button>
						</div>
						<div class="clsq">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>的申请</div>
										<div class="p2">（事假）世界这么大，我想去看看<span>[待审批]</span></div>
										<div class="p5">上联：世界那么大，我想去看看。下联：钱包那么小，谁都走不了。横批：好好上班。已将工作移交给陈文光。</div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">申请人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-sitemap"></i><p class="p_div">所在部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">请假时间</p><span>6月21日 8：00-6月30日 6：00 共<b>10</b>天</span></div>
										<div class="p3"><i class="fa fa-eye"></i><p class="p_div">审核人</p><span>罗志明（教务主任）</span></div>
										<div class="p3"><i class="fa fa-file-text-o"></i><p class="p_div">审核结果</p><span>待审批</span></div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>的申请</div>
										<div class="p2">（病假）感冒发烧流鼻涕，去医院吊针<span class="success">[审批通过]</span></div>
										<div class="p5">感冒发烧流鼻涕，去医院吊针</div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">申请人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-sitemap"></i><p class="p_div">所在部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">请假时间</p><span>6月21日 8：00-6月22日 6：00 共<b>2</b>天</span></div>
										<div class="p3"><i class="fa fa-eye"></i><p class="p_div">审核人</p><span>罗志明（教务主任）</span></div>
										<div class="p3"><i class="fa fa-file-text-o"></i><p class="p_div">审核结果</p><span class="success">审批通过</span></div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="clear"></div>
								</li>
							</ul>
							<div class="empty" style="display:none">
								<p>暂无请假</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>