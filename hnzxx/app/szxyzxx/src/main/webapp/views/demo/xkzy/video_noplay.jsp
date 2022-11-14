<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>学校列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="微课管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							资源查看
							<p style="float:right;" class="btn_link">
						<a class="a4" href="javascript:void(0)"><i class="fa fa-reply"></i> 返回 </a>
					</p>
						</h3>
					</div>
					<div class="content-widgets" style="margin-bottom:0">
						<div class="video_div">
							<div class="title_1"><p>正在查看: &nbsp; </p>
								<div class="item-title">
									<span class="res-rar icon-file res-iconb" style="margin-top:10px;"></span>
									<span style="overflow:hidden;width:694px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
										<a href="javascript:void(0)" target="_blank">《a o e i u ü》教学动画.rar</a>
									</span>
								</div>
							</div>
							<div class="play_area_1">
								<img src="${pageContext.request.contextPath}/res/images/not_play.jpg" style="width:786px;height:619px;">
								<a href="javascript:void(0)" class="download"></a>
							</div>
							<div class="v_div">
								<div class="fx_left">
									<div class="fx_1">
										<a href="javascript:void(0)" class="a1">收藏资源</a>
										<a href="javascript:void(0)" class="a3">2125</a>
										<a href="javascript:void(0)" class="a2">下载</a>
									</div>
									<div class="fx_2">
										<p class="title">资源介绍</p>
										<div class="intro">
											<p>这里有规律，这里有方法，这里有成绩。文言文难学，原因之一是需要记忆的东西多，头绪多。
											其实文言文的学习是有规律可循的。</p>
											  <p>  这里的5节课从文学常识、文言虚词、文言实词、文言句式、翻译、文意理解等方面入手，系统阐
											释其中的规律，介绍行之有效的学习方法，可以在很大程度上减轻困难，提高效率。可以说是：把
											握规律方向明，方法对头效率高。</p>
										</div>
									</div>
								</div>
								<div class="fx_right">
									<div class="g1">
											<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
											<p class="p1">分享者：陈馨</p>
											<p class="p2">广东省佛山市惠景中学</p>
										</div>
								</div>
								<div class="clear"></div>
							</div>
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
</script>
</html>