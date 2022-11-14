<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>车辆</title>
<script type="text/javascript">
	$(function(){
		$(".sq_list .clsq ul li .caozuo .delete").click(function(){
			$(this).parent().parent().remove()
		});
		$(".sq_list .entry ul li a").click(function(){
			$(".sq_list .entry ul li").removeClass("on");
			$(this).parent().addClass("on");
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
				            <li><a href="javascript:void(0)" class="on">我的申请</a></li>
				            <li><a href="javascript:void(0)" >部门申请</a></li>
				            <li><a href="javascript:void(0)">全部申请</a></li>
				        </ul>
					</div>
					<div class="sq_list">
						<div class="search_1">
							<input type="text" placeholder="车名/车牌号">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li class="on"><a href="javascript:void(0)">我的申请（2）</a></li>
								<li><a href="javascript:void(0)">财务部（0）</a></li>
								<li><a href="javascript:void(0)">教务处（2）</a></li>
								<li><a href="javascript:void(0)">学生处（0）</a></li>
								<li><a href="javascript:void(0)">后勤部（0）</a></li>
							</ul>
							<button class="btn btn-success">申请用车</button>
						</div>
						<div class="clsq">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>的申请</div>
										<div class="p2">今天要去带三年级二班去博物馆参观<span>[待审批]</span></div>
										<div class="p3"><i class="fa fa-truck"></i><p class="p_div">申请车辆</p><span>黄色小型接送巴士【粤A1234567】</span></div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">申请人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>13800138000</span></div>
										<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">使用时间</p><span>6月21日-6月21日 16：00 共<b>1</b>天</span></div>
										<div class="p3"><i class="fa fa-eye"></i><p class="p_div">审核人</p><span>黄色小型接送巴士【粤A1234567】</span></div>
										<div class="p3"><i class="fa fa-file-text-o"></i><p class="p_div">审核结果</p><span>待审批</span></div>
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
										<div class="p1"><span>刘艳青</span>的申请</div>
										<div class="p2">今天要去带三年级二班去博物馆参观<span class="success">[审批通过]</span></div>
										<div class="p3"><i class="fa fa-truck"></i><p class="p_div">申请车辆</p><span>黄色小型接送巴士【粤A1234567】</span></div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">申请人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>13800138000</span></div>
										<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">使用时间</p><span>6月21日-6月21日 16：00 共<b>1</b>天</span></div>
										<div class="p3"><i class="fa fa-eye"></i><p class="p_div">审核人</p><span>黄色小型接送巴士【粤A1234567】</span></div>
										<div class="p3"><i class="fa fa-file-text-o"></i><p class="p_div">审核结果</p><span class="success">审批通过</span></div>
										<div class="p3" ><i class="fa fa-comment-o"></i><p class="p_div">备注</p><span>在张老师手上拿车钥匙。请用完车尽快归还，陈老师也等着在用车。</span></div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="clear"></div>
								</li>
							</ul>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>