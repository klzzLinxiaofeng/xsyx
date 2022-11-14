<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>通知公告</title>
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
			<jsp:param value="公告" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				             <li><a href="javascript:void(0)" class="on">全部通知</a></li>
				            <li><a href="javascript:void(0)" >部门通知</a></li>
				            <li><a href="javascript:void(0)">我发表的</a></li>
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
								<li class="on"><a href="javascript:void(0)">全部通知（10）</a></li>
								<li><a href="javascript:void(0)">财务部（5）</a></li>
								<li><a href="javascript:void(0)">教务处（5）</a></li>
							</ul>
							<button class="btn btn-success">发通知</button>
						</div>
						<div class="f_wy">
						<div class="clsq">
							<ul>
								<li>
									<div class="touxiang_1"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail_1">
										<div class="p1">岳阳市九中关于参加“岳阳市第三届美德少年评选表彰 学习宣传活动”的通知</div>
										<div class="p2">
											各班主任老师：<br/>
										　  　岳阳市文明办、团市委、市教育局决定共同主办“岳阳市第三届美德少年评选表彰和学习宣传活动”，现将有关事项通知如下，请各班认真组织相关活动。
										</div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo_1">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang_1"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail_1">
										<div class="p1">关于组织开展清明祭英烈活动的通知</div>
										<div class="p2">
											各班主任老师：<br/>
										　  　岳阳市文明办、团市委、市教育局决定共同主办“岳阳市第三届美德少年评选表彰和学习宣传活动”，现将有关事项通知如下，请各班认真组织相关活动。
										</div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo_1">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
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
									<div class="touxiang_1"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail_1">
										<div class="p1">岳阳市九中关于参加“岳阳市第三届美德少年评选表彰 学习宣传活动”的通知</div>
										<div class="p2">
											各班主任老师：<br/>
										　  　岳阳市文明办、团市委、市教育局决定共同主办“岳阳市第三届美德少年评选表彰和学习宣传活动”，现将有关事项通知如下，请各班认真组织相关活动。
										</div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo_1">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
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
									<div class="touxiang_1"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail_1">
										<div class="p1">岳阳市九中关于参加“岳阳市第三届美德少年评选表彰 学习宣传活动”的通知</div>
										<div class="p2">
											各班主任老师：<br/>
										　  　岳阳市文明办、团市委、市教育局决定共同主办“岳阳市第三届美德少年评选表彰和学习宣传活动”，现将有关事项通知如下，请各班认真组织相关活动。
										</div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo_1">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
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
					<div class="sq_list" style="display:none">
						<div class="search_1">
							<input type="text" placeholder="车名/车牌号">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li class="on"><a href="javascript:void(0)">部门通知（2）</a></li>
							</ul>
							<button class="btn btn-success">发通知</button>
						</div>
						<div class="f_wy">
							<div class="clsq">
							<ul>
								<li>
									<div class="touxiang_1"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail_1">
										<div class="p1">岳阳市九中关于参加“岳阳市第三届美德少年评选表彰 学习宣传活动”的通知</div>
										<div class="p2">
											各班主任老师：<br/>
										　  　岳阳市文明办、团市委、市教育局决定共同主办“岳阳市第三届美德少年评选表彰和学习宣传活动”，现将有关事项通知如下，请各班认真组织相关活动。
										</div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo_1">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang_1"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail_1">
										<div class="p1">岳阳市九中关于参加“岳阳市第三届美德少年评选表彰 学习宣传活动”的通知</div>
										<div class="p2">
											各班主任老师：<br/>
										　  　岳阳市文明办、团市委、市教育局决定共同主办“岳阳市第三届美德少年评选表彰和学习宣传活动”，现将有关事项通知如下，请各班认真组织相关活动。
										</div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo_1">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
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
									<div class="touxiang_1"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail_1">
										<div class="p1">岳阳市九中关于参加“岳阳市第三届美德少年评选表彰 学习宣传活动”的通知</div>
										<div class="p2">
											各班主任老师：<br/>
										　  　岳阳市文明办、团市委、市教育局决定共同主办“岳阳市第三届美德少年评选表彰和学习宣传活动”，现将有关事项通知如下，请各班认真组织相关活动。
										</div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo_1">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang_1"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="detail_1">
										<div class="p1">岳阳市九中关于参加“岳阳市第三届美德少年评选表彰 学习宣传活动”的通知</div>
										<div class="p2">
											各班主任老师：<br/>
										　  　岳阳市文明办、团市委、市教育局决定共同主办“岳阳市第三届美德少年评选表彰和学习宣传活动”，现将有关事项通知如下，请各班认真组织相关活动。
										</div>
										<div class="p4">06月18日 18:35</div>
									</div>
									<div class="caozuo_1">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
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