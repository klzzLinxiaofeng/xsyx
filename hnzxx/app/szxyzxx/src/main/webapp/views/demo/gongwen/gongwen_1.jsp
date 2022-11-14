<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>公文</title>
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
			<jsp:param value="公文" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" class="on">与我相关</a></li>
				            <li><a href="javascript:void(0)">部门公文</a></li>
				            <li><a href="javascript:void(0)" >全部公文</a></li>
				            <li><a href="javascript:void(0)" >我发布的</a></li>
				        </ul>
					</div>
					<div class="wy_all">
						<div class="sq_list">
						<div class="search_1">
							<input type="text" placeholder="标题/发布单位/发布人/摘要">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li><a href="javascript:void(0)">与我相关（10）</a></li>
							</ul>
							<button class="btn btn-success">发公文</button>
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
										<div class="p2">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作</div>
										<div class="p3">一年来，我中心学校广大教师和教育工作者以邓小平理论和“三个代表”重要思想为指导，深入学习实践科学发展观，认真学习贯彻全国人才工作会议精神，围绕“科教
兴镇”战略和建设“教育强镇”目标，积极参与基础教育课程改革，努力推进素质教育，加强学校德育工作，教书育人，勤奋工作，成绩显著。为深入贯彻实施“科教兴镇”
战略，表彰先进，树立典型，进一步加强教师队伍建设，促进我镇教育事业的改革与发展。经研究，决定表彰一批“优秀教师’’和“优秀班主任”。</div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>8/10</span></div></div>
									</div>
									
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 all">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p2">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作</div>
										<div class="p3">一年来，我中心学校广大教师和教育工作者以邓小平理论和“三个代表”重要思想为指导，深入学习实践科学发展观，认真学习贯彻全国人才工作会议精神，围绕“科教
兴镇”战略和建设“教育强镇”目标，积极参与基础教育课程改革，努力推进素质教育，加强学校德育工作，教书育人，勤奋工作，成绩显著。为深入贯彻实施“科教兴镇”
战略，表彰先进，树立典型，进一步加强教师队伍建设，促进我镇教育事业的改革与发展。经研究，决定表彰一批“优秀教师’’和“优秀班主任”。</div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>8/10</span></div></div>
									</div>
									
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 one">
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p2">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作</div>
										<div class="p3">一年来，我中心学校广大教师和教育工作者以邓小平理论和“三个代表”重要思想为指导，深入学习实践科学发展观，认真学习贯彻全国人才工作会议精神，围绕“科教
兴镇”战略和建设“教育强镇”目标，积极参与基础教育课程改革，努力推进素质教育，加强学校德育工作，教书育人，勤奋工作，成绩显著。为深入贯彻实施“科教兴镇”
战略，表彰先进，树立典型，进一步加强教师队伍建设，促进我镇教育事业的改革与发展。经研究，决定表彰一批“优秀教师’’和“优秀班主任”。</div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35</div>
									</div>
									
									<div class="clear"></div>
								</li>
							</ul>
							<div class="empty" style="display:none">
								<p>暂无公文</p>
							</div>
						</div>
					</div>
					<div class="sq_list">
						<div class="search_1">
							<input type="text" placeholder="标题/发布单位/发布人/摘要">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li><a href="javascript:void(0)">与我相关（10）</a></li>
							</ul>
							<button class="btn btn-success">发公文</button>
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
										<div class="p2">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作</div>
										<div class="p3">一年来，我中心学校广大教师和教育工作者以邓小平理论和“三个代表”重要思想为指导，深入学习实践科学发展观，认真学习贯彻全国人才工作会议精神，围绕“科教
兴镇”战略和建设“教育强镇”目标，积极参与基础教育课程改革，努力推进素质教育，加强学校德育工作，教书育人，勤奋工作，成绩显著。为深入贯彻实施“科教兴镇”
战略，表彰先进，树立典型，进一步加强教师队伍建设，促进我镇教育事业的改革与发展。经研究，决定表彰一批“优秀教师’’和“优秀班主任”。</div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>8/10</span></div></div>
									</div>
									
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 all">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p2">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作</div>
										<div class="p3">一年来，我中心学校广大教师和教育工作者以邓小平理论和“三个代表”重要思想为指导，深入学习实践科学发展观，认真学习贯彻全国人才工作会议精神，围绕“科教
兴镇”战略和建设“教育强镇”目标，积极参与基础教育课程改革，努力推进素质教育，加强学校德育工作，教书育人，勤奋工作，成绩显著。为深入贯彻实施“科教兴镇”
战略，表彰先进，树立典型，进一步加强教师队伍建设，促进我镇教育事业的改革与发展。经研究，决定表彰一批“优秀教师’’和“优秀班主任”。</div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>8/10</span></div></div>
									</div>
									
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 one">
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p2">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作</div>
										<div class="p3">一年来，我中心学校广大教师和教育工作者以邓小平理论和“三个代表”重要思想为指导，深入学习实践科学发展观，认真学习贯彻全国人才工作会议精神，围绕“科教
兴镇”战略和建设“教育强镇”目标，积极参与基础教育课程改革，努力推进素质教育，加强学校德育工作，教书育人，勤奋工作，成绩显著。为深入贯彻实施“科教兴镇”
战略，表彰先进，树立典型，进一步加强教师队伍建设，促进我镇教育事业的改革与发展。经研究，决定表彰一批“优秀教师’’和“优秀班主任”。</div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35</div>
									</div>
									
									<div class="clear"></div>
								</li>
							</ul>
							<div class="empty" style="display:none">
								<p>暂无公文</p>
							</div>
						</div>
					</div>
					<div class="sq_list" style="display:none">
						<div class="search_1">
							<input type="text" placeholder="标题/发布单位/发布人/摘要">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li class="on"><a href="javascript:void(0)">全部公文（2）</a></li>
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
									<div class="caozuo_2 one">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p2">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作</div>
										<div class="p3">一年来，我中心学校广大教师和教育工作者以邓小平理论和“三个代表”重要思想为指导，深入学习实践科学发展观，认真学习贯彻全国人才工作会议精神，围绕“科教
兴镇”战略和建设“教育强镇”目标，积极参与基础教育课程改革，努力推进素质教育，加强学校德育工作，教书育人，勤奋工作，成绩显著。为深入贯彻实施“科教兴镇”
战略，表彰先进，树立典型，进一步加强教师队伍建设，促进我镇教育事业的改革与发展。经研究，决定表彰一批“优秀教师’’和“优秀班主任”。</div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>8/10</span></div></div>
									</div>
									
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 all">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p2">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作</div>
										<div class="p3">一年来，我中心学校广大教师和教育工作者以邓小平理论和“三个代表”重要思想为指导，深入学习实践科学发展观，认真学习贯彻全国人才工作会议精神，围绕“科教
兴镇”战略和建设“教育强镇”目标，积极参与基础教育课程改革，努力推进素质教育，加强学校德育工作，教书育人，勤奋工作，成绩显著。为深入贯彻实施“科教兴镇”
战略，表彰先进，树立典型，进一步加强教师队伍建设，促进我镇教育事业的改革与发展。经研究，决定表彰一批“优秀教师’’和“优秀班主任”。</div>
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
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p2">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作</div>
										<div class="p3">一年来，我中心学校广大教师和教育工作者以邓小平理论和“三个代表”重要思想为指导，深入学习实践科学发展观，认真学习贯彻全国人才工作会议精神，围绕“科教
兴镇”战略和建设“教育强镇”目标，积极参与基础教育课程改革，努力推进素质教育，加强学校德育工作，教书育人，勤奋工作，成绩显著。为深入贯彻实施“科教兴镇”
战略，表彰先进，树立典型，进一步加强教师队伍建设，促进我镇教育事业的改革与发展。经研究，决定表彰一批“优秀教师’’和“优秀班主任”。</div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>8/10</span></div></div>
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
									<div class="caozuo_2 all">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p2">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作</div>
										<div class="p3">一年来，我中心学校广大教师和教育工作者以邓小平理论和“三个代表”重要思想为指导，深入学习实践科学发展观，认真学习贯彻全国人才工作会议精神，围绕“科教
兴镇”战略和建设“教育强镇”目标，积极参与基础教育课程改革，努力推进素质教育，加强学校德育工作，教书育人，勤奋工作，成绩显著。为深入贯彻实施“科教兴镇”
战略，表彰先进，树立典型，进一步加强教师队伍建设，促进我镇教育事业的改革与发展。经研究，决定表彰一批“优秀教师’’和“优秀班主任”。</div>
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
							<input type="text" placeholder="标题/发布单位/发布人/摘要">
							<a class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li><a href="javascript:void(0)">我发布的（2）</a></li>
							</ul>
							<button class="btn btn-success">申请文印</button>
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
										<div class="p2">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作</div>
										<div class="p3">一年来，我中心学校广大教师和教育工作者以邓小平理论和“三个代表”重要思想为指导，深入学习实践科学发展观，认真学习贯彻全国人才工作会议精神，围绕“科教
兴镇”战略和建设“教育强镇”目标，积极参与基础教育课程改革，努力推进素质教育，加强学校德育工作，教书育人，勤奋工作，成绩显著。为深入贯彻实施“科教兴镇”
战略，表彰先进，树立典型，进一步加强教师队伍建设，促进我镇教育事业的改革与发展。经研究，决定表彰一批“优秀教师’’和“优秀班主任”。</div>
										<div class="p6"><i class="fa fa-paperclip"></i><p>附件</p><a href="#">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作.doc</a></div>
										<div class="p4">学生处   06月18日 18:35<div class="chayue"><i class="fa fa-eye"></i>查阅情况<span>8/10</span></div></div>
									</div>
									
									<div class="clear"></div>
								</li>
								<li>
									<div class="touxiang"> 
										<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									</div>
									<div class="caozuo_2 all">
										<a class="edit"><i class="fa fa-edit"></i>编辑</a>
										<a class="delete" href="javascript:void(0)">删除</a>
									</div>
									<div class="detail_2">
										<div class="p1"><span>刘艳青</span>发布的公文</div>
										<div class="p2">关于认真做好2014—2015年度学校“优秀教师”、“优秀班主任”评选表彰工作</div>
										<div class="p3">一年来，我中心学校广大教师和教育工作者以邓小平理论和“三个代表”重要思想为指导，深入学习实践科学发展观，认真学习贯彻全国人才工作会议精神，围绕“科教
兴镇”战略和建设“教育强镇”目标，积极参与基础教育课程改革，努力推进素质教育，加强学校德育工作，教书育人，勤奋工作，成绩显著。为深入贯彻实施“科教兴镇”
战略，表彰先进，树立典型，进一步加强教师队伍建设，促进我镇教育事业的改革与发展。经研究，决定表彰一批“优秀教师’’和“优秀班主任”。</div>
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
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>