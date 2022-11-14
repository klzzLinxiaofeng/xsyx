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
				            <li><a href="javascript:void(0)">审批申请</a></li>
				            <li><a href="javascript:void(0)" class="on">车辆管理</a></li>
				            <li><a href="javascript:void(0)">用车统计</a></li>
				        </ul>
					</div>
					<div class="sq_list">
						<div class="search_1">
							<input type="text" placeholder="车名/车牌号">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li><a href="javascript:void(0)">全部车辆（5）</a></li>
							</ul>
							<button class="btn btn-info">新建车辆</button>
						</div>
						<div class="clgl">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/school.jpg">
									</div>
									<div class="detail">
										<div class="p2">黄色小型接送巴士</div>
										<div class="p3"><i class="fa fa-users"></i><p class="p_div">荷载人数</p><span>20人</span></div>
										<div class="p3"><i class="fa  fa-money"></i><p class="p_div">车牌号</p><span>粤A123456</span></div>
										<div class="p3"><i class="fa   fa-key"></i><p class="p_div">购置日期</p><span>2013年6月7日</span></div>
										<div class="p3"><i class="fa fa-exclamation-triangle"></i><p class="p_div">当前状态</p><span class="keyong">可用</span>
											<button class="btn_1 btn-keyong" disabled="disabled">设为可用</button><button class="btn_1 btn-using">使用中</button><button class="btn_1 btn-xiuli">修理中</button>
										</div>
									</div>
									<div class="caozuo">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/school.jpg">
									</div>
									<div class="detail">
										<div class="p2">黄色小型接送巴士</div>
										<div class="p3"><i class="fa fa-users"></i><p class="p_div">荷载人数</p><span>20人</span></div>
										<div class="p3"><i class="fa  fa-money"></i><p class="p_div">车牌号</p><span>粤A123456</span></div>
										<div class="p3"><i class="fa   fa-key"></i><p class="p_div">购置日期</p><span>2013年6月7日</span></div>
										<div class="p3"><i class="fa fa-exclamation-triangle"></i><p class="p_div">当前状态</p><span class="buke">使用中（6月22日 12:00 - 6月23日 12:00）</span>
											<button class="btn_1 btn-keyong">设为可用</button><button class="btn_1 btn-using" disabled="disabled">使用中</button><button class="btn_1 btn-xiuli">修理中</button>
										</div>
									</div>
									<div class="caozuo">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/cheliang.jpg">
									</div>
									<div class="detail">
										<div class="p2">黄色小型接送巴士</div>
										<div class="p3"><i class="fa fa-users"></i><p class="p_div">荷载人数</p><span>20人</span></div>
										<div class="p3"><i class="fa  fa-money"></i><p class="p_div">车牌号</p><span>粤A123456</span></div>
										<div class="p3"><i class="fa   fa-key"></i><p class="p_div">购置日期</p><span>2013年6月7日</span></div>
										<div class="p3"><i class="fa fa-exclamation-triangle"></i><p class="p_div">当前状态</p><span class="buke">使用中（6月22日 12:00 - 6月23日 12:00）</span>
											<button class="btn_1 btn-keyong">设为可用</button><button class="btn_1 btn-using" disabled="disabled">使用中</button><button class="btn_1 btn-xiuli">修理中</button>
										</div>
									</div>
									<div class="caozuo">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="clear"></div>
								</li>
							</ul>
							<div class="empty" style="display:none">
								<p>车辆为空</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>