<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>我的投诉</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="客户投诉" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" class="on">我的投诉</a></li>
				            <li><a href="javascript:void(0)" >全部投诉</a></li>
				        </ul>
					</div>
					<div class="sq_list">
						<div class="search_1">
							<input type="text" placeholder="问题描述关键字/问题类别">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
<!-- 								<li class="on"><a href="javascript:void(0)">全部申请（1）</a></li> -->
								<li><a href="javascript:void(0)">我的投诉（2）</a></li>
							</ul>
							<button class="btn btn-success">投诉</button>
						</div>
						<div class="clsq">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>的投诉</div>
										<div class="p2"><span>[待处理]</span></div>
										<div class="p3"><i class="fa  fa-sitemap"></i><p class="p_div">所在部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">投诉时间</p><span>6月21日 8：00</span></div>
										<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">问题类别</p><span>技术支持</span></div>
										<div class="p3"><i class="fa fa-align-center"></i><p class="p_div">问题描述</p><span>软件总是自动关闭</span></div>
										<div class="p7"><img src="${pageContext.request.contextPath}/res/images/help_text.png"><img src="${pageContext.request.contextPath}/res/images/help_text.png"><img src="${pageContext.request.contextPath}/res/images/help_text.png"></div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">处理人</p><span>还没有处理</span></div>
										<div class="p3"><i class="fa fa-check-circle"></i><p class="p_div">处理进度</p><span>暂无</span></div>
										<div class="p3"><i class="fa fa-retweet"></i><p class="p_div">处理方式</p><span>暂无</span></div>
										<div class="p3"><i class="fa fa-anchor"></i><p class="p_div">处理评价</p><span>暂无</span></div>
										<div class="p7"><div class="pf"><p style="width:0"></p></div></div>
									</div>
									<div class="caozuo" style="width:auto">
										<button class="btn btn-success" onclick="shenhe()">评价</button>
										<button class="btn btn-primary" onclick="shenhe()">编辑</button>
										<button class="btn btn-danger" onclick="shenhe()">删除</button>
									</div>
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>的投诉</div>
										<div class="p2"><span class="yichuli">[已处理]</span></div>
										<div class="p3"><i class="fa  fa-sitemap"></i><p class="p_div">所在部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">投诉时间</p><span>6月21日 8：00</span></div>
										<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">问题类别</p><span>技术支持</span></div>
										<div class="p3"><i class="fa fa-align-center"></i><p class="p_div">问题描述</p><span>软件总是自动关闭</span></div>
										<div class="p7"><img src="${pageContext.request.contextPath}/res/images/help_text.png"><img src="${pageContext.request.contextPath}/res/images/help_text.png"><img src="${pageContext.request.contextPath}/res/images/help_text.png"></div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">处理人</p><span>还没有处理</span></div>
										<div class="p3"><i class="fa fa-check-circle"></i><p class="p_div">处理进度</p><span>暂无</span></div>
										<div class="p3"><i class="fa fa-retweet"></i><p class="p_div">处理方式</p><span>暂无</span></div>
										<div class="p3"><i class="fa fa-anchor"></i><p class="p_div">处理评价</p><span>暂无</span></div>
										<div class="p7"><div class="pf"><p style="width:40%"></p></div></div>
									</div>
									<div class="caozuo" style="width:auto">
										<button class="btn btn-success" onclick="shenhe()">评价</button>
										<button class="btn btn-primary" onclick="shenhe()">编辑</button>
										<button class="btn btn-danger" onclick="shenhe()">删除</button>
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
	<div class="shenhe" style="display:none">
		<div><a href="javascript:void(0)" class="close_div"></a></div>
		<div class="s_top" style="text-align:center;border-bottom:0 none">
			<p style="width:100%;padding:0;">教师请假条</p>
		</div>
		<div class="s_two" style="position:relative;">
			<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" style="position:absolute;left:0px;top:17px;width:50px;height:50px;">
			<div class="p1"><span>刘艳青</span>的申请</div>
			<div class="p2">（事假）世界那么大，我想去看看</div>
			<div class="p5">上联：世界那么大，我想去看看。下联：钱包那么小，谁都走不了。横批：好好上班。已将工作移交给陈文光。</div>
			<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">请假时间</p><span>6月21日-6月21日 16：00 共<b>1</b>天</span></div>
			<div class="p3"><i class="fa fa-user"></i><p class="p_div">代课老师</p><span>刘艳青</span></div>
			<div class="p3" style="position:absolute;top:17px;right:0;"><i class="fa  fa-sitemap"></i><p class="p_div">所在部门</p><span>教务处</span></div>
		</div>
		<div class="s_three">
			<div class="control-group">
				<label class="control-label">审核：</label>
				<div class="controls">
					<button class="btn btn-warning">通过</button><button class="btn">不通过</button>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">备注：</label>
				<div class="controls">
					<textarea style="width:98%;height:38px;margin:0;"></textarea>
				</div>
			</div>
		</div>
		<div class="s_four">
			<a href="javascript:void(0)" class="btn-success">提交</a>
			<a href="javascript:void(0)" class="cancel">取消</a>
		</div>
	</div>
	<div class="zhezhao" style="display:none"></div>
</body>
</html>