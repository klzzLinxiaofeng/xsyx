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
/*====Select Box====*/
$(function () {
    $(".chzn-select").chosen();
    $(".sq_list .sz_style .sz_1 a").click(function(){
		$(this).siblings().removeClass("on");
		$(this).addClass("on");
	});
    $(".oa_top .top_ul li a").click(function(){
		$(".oa_top .top_ul li a").removeClass("on");
		$(this).addClass("on");
		var i=$(this).parent().index();
		$(".sq_list").hide();
		$(".sq_list").eq(i).show();
	});
	$(".sq_list .entry ul li a").click(function(){
		$(".sq_list .entry ul li a").parent().removeClass("on");
		$(this).parent().addClass("on");
		var j=$(this).parent().index();
		$(this).parent().parent().parent().next().children().hide()
		$(this).parent().parent().parent().next().children().eq(j).show();
	});
});

</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="请假" name="title" />
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
					<div class="wy_div">
						<div class="sq_list">
						<div class="search_1">
							<input type="text" placeholder="车名/车牌号">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li class="on"><a href="javascript:void(0)">全部申请（2）</a></li>
								<li><a href="javascript:void(0)">财务部（0）</a></li>
								<li><a href="javascript:void(0)">教育部（2）</a></li>
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
						<div class="clsq" style="display:none">
							<div class="empty" >
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
					<div class="sq_list" style="display:none">
						<div class="search_1">
							<input type="text" placeholder="车名/车牌号">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li class="on"><a href="javascript:void(0)">全部文印（2）</a></li>
								<li><a href="javascript:void(0)">教务处（2）</a></li>
								<li><a href="javascript:void(0)">财务部（0）</a></li>
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
										<div class="p2">优秀教师评选的材料帮我打印10份<span class="success">[已处理]</span></div>
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
						<div class="clsq"  style="display:none">
							<ul>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail">
										<div class="p1"><span>刘艳青</span>发出的申请</div>
										<div class="p2">优秀教师评选的材料帮我打印10份<span class="success">[已处理]</span></div>
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
						<div class="clsq"  style="display:none">
							<div class="empty" >
								<p>暂无文印申请</p>
							</div>
						</div>
						</div>
						
					</div>
					<div class="sq_list" style="display:none">
						<div class="tj_1">
							<div class="time">
								<span>时间范围：</span>
								<select class="chzn-select">
									<option>本月(2015/7/1-2015/7/22)</option>
									<option>六月(2015/6/1-2015/6/30)</option>
								</select>
							</div>
							<div class="people">
								<p class="p1">车辆负责人</p>
								<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
								<div class="name">
									<p><b>刘艳青</b></p>
									<p>后勤部</p>
								</div>
							</div>
						</div>
						<div class="tj_2">
							<p><span>报修申请数：</span><b class="b1">98</b></p>
							<p><span>待处理：</span><b class="b2">2</b></p>
							<p><span>处理中：</span><b class="b4">2</b></p>
							<p><span>已处理：</span><b class="b3">93</b></p>
							<p><span>未处理：</span><b class="b5">1</b></p>
						</div>
						<div class="tj_3">
							<p class="top">申请明细</p>
							<table class="responsive table table-striped">
								<thead>
									<tr>
										<th>序号</th>
										<th>申请人</th>
										<th>部门</th>
										<th>联系电话</th>
										<th>申请事由</th>
										<th>处理状态</th>
										<th>处理时间</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>打印试卷100份</td>
										<td><span class="blue_1">已处理</span></td>
										<td></td>
									</tr>
									<tr>
										<td>2</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>打印试卷100份</td>
										<td><span class="yellow_1">处理中</span></td>
										<td>2015-06-21 19:00</td>
									</tr>
									<tr>
										<td>3</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>打印试卷100份</td>
										<td>已处理</td>
										<td>2015-06-21 19:00</td>
									</tr>
									<tr>
										<td>4</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>打印试卷100份</td>
										<td>已处理</td>
										<td>2015-06-21 19:00</td>
									</tr>
									<tr>
										<td>5</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>打印试卷100份</td>
										<td>已处理</td>
										<td>2015-06-21 19:00</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	<div class="shenhe" style="display:none">
		<div><a href="javascript:void(0)" class="close_div"></a></div>
		<div class="s_top">
			<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
			<p>刘艳青的用车申请</p>
		</div>
		<div class="s_two">
			<div class="p2">今天要去带三年级二班去博物馆参观<span>[待审批]</span></div>
			<div class="p3"><i class="fa fa-truck"></i><p class="p_div">申请车辆</p><span>黄色小型接送巴士【粤A1234567】</span></div>
			<div class="p3"><i class="fa fa-user"></i><p class="p_div">申请人</p><span>刘艳青</span></div>
			<div class="p3"><i class="fa  fa-phone"></i><p class="p_div">联系电话</p><span>13800138000</span></div>
			<div class="p3"><i class="fa fa-clock-o"></i><p class="p_div">使用时间</p><span>6月21日-6月21日 16：00 共<b>1</b>天</span></div>
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