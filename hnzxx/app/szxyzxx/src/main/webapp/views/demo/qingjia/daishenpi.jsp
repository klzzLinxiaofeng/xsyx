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
		$(".sq_list .entry ul li a").click(function(){
			$(".sq_list .entry ul li").removeClass("on");
			$(this).parent().addClass("on");
		});
		$(".shenhe .s_four .cancel,.shenhe .close_div").click(function(){
			$(this).parent().parent().hide();
			$(".zhezhao").hide();
		})
	});
	function shenhe(){
		$(".shenhe,.zhezhao").show();
		var h = document.documentElement.clientHeight;
		var w= document.documentElement.clientWidth;
		var h1 = (document.documentElement.clientHeight-455)/2;
		var w1= (document.documentElement.clientWidth-900)/2;
		$(".zhezhao").css({"width":w,"height":h});
		$(".shenhe").css({"left":w1,"top":h1});
	}
	
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
				            <li><a href="javascript:void(0)" class="on">待审批</a></li>
				            <li><a href="javascript:void(0)" >已审批</a></li>
				            <li><a href="javascript:void(0)">请假统计</a></li>
				        </ul>
					</div>
					<div class="sq_list">
						<div class="search_1">
							<input type="text" placeholder="车名/车牌号">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li class="on"><a href="javascript:void(0)">全部申请（1）</a></li>
								<li><a href="javascript:void(0)">财务部（0）</a></li>
								<li><a href="javascript:void(0)">教务处（1）</a></li>
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
										<div class="p2">（事假）世界这么大，我想去看看<span>[待审批]</span></div>
										<div class="p5">上联：世界那么大，我想去看看。下联：钱包那么小，谁都走不了。横批：好好上班。已将工作移交给陈文光。</div>
										<div class="p3"><i class="fa fa-user"></i><p class="p_div">申请人</p><span>刘艳青</span></div>
										<div class="p3"><i class="fa  fa-sitemap"></i><p class="p_div">所在部门</p><span>教务处</span></div>
										<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">请假时间</p><span>6月21日 8：00-6月30日 6：00 共<b>10</b>天</span></div>
										<div class="p3"><i class="fa fa-eye"></i><p class="p_div">审核人</p><span>罗志明（教务主任）</span></div>
										<div class="p3"><i class="fa fa-file-text-o"></i><p class="p_div">审核结果</p><span>待审批</span></div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo" style="width:60px;">
										<button class="btn btn-warning" onclick="shenhe()">审批</button>
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