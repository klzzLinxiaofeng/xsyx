<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
<title>报修</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="报修" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
		<div class="span12">
			<div class="content-widgets white">
				<div class="widget-head">
						<h3 class="x-head content-top"><a href="bxiu-sq1.html">我申请的</a><a href="bxiu-all3.html" class="on">全部报修</a><a href="bxtj.html">报修统计</a></h3>
				</div>
				<div class="widget-container">
				  <div class="clearfix list-search-bar x-search">
				  	<div>
						<span style="position:relative;display:block;float:left;">
							<input type="text" size="16" value="标题/发布人" class="input-medium">
							<i title="清空" class="fa fa-remove"></i>
						</span>
				  		<button type="button" class="btn"><i class="fa fa-search"></i></button>
                    </div>
                  </div>
				</div>
				<div class="x-main">
					<h4 class="title">我的申请(1) <button class="btn btn-success right" type="button">申请报修</button></h4>
				  <div class="list">
				  	<div class="clearfix">
				  		<div class="admin-thumb">
				  			<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"/>
				  		</div>
				  		<div class="admin-meta admin-head">
				  			<ul>
				  				<li><b>刘艳青</b> 发出的申请</li>
				  				<li><a href="#" class="title-a">办公室黑板坏了</a></li>
				  			</ul>
				  		</div>
				  		<div class="right right-btn"><a href="#" class="btn"><i class=" fa fa-edit"></i> 编辑</a><a href="#" class="btn">删除</a></div>
				  	</div>
				  	<div class="clearfix x-mright">
				  		<div class="x-load span12">
				  			<span><em class="on">申请</em></span>
				  			<span><em>维修</em></span>
				  			<span><em>评价</em></span>
				  			<span style="background:none;"><em>完成</em></span>
				  		</div>
				  		<h6 class="clearfix" style="font-size:14px;">办公室黑板坏了</h6>
				  		<div class="clearfix content-widgets"><img src="${pageContext.request.contextPath}/res/images/w183.jpg"/></div>
				  		<p class="clearfix"><span class="left"><i class="fa fa-map-marker"></i>维修地点</span><em>校长办公室门口</em></p>
				  		<p class="clearfix"><span class="left"><i class="fa fa-user"></i>联系人</span><em>刘艳青</em></p>
				  		<p class="clearfix"><span class="left"><i class=" fa fa-phone"></i>联系电话</span><em>123143546</em></p>
				  		<p class="clearfix date">06月18日 18:35</p>
				  	</div>
				  </div>	
				
				<div class="list">
				  	<div class="clearfix">
				  		<div class="admin-thumb">
				  			<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"/>
				  		</div>
				  		<div class="admin-meta admin-head">
				  			<ul>
				  				<li><b>刘艳青</b> 发出的申请</li>
				  				<li><a href="#" class="title-a">办公室黑板坏了</a></li>
				  			</ul>
				  		</div>
				  		<div class="right right-btn"><a href="#" class="btn"><i class="fa fa-edit"></i> 编辑</a><a href="#" class="btn">删除</a></div>
				  	</div>
				  	<div class="clearfix x-mright">
				  		<div class="x-load span12">
				  			<span><em class="on">申请</em></span>
				  			<span><em>维修</em></span>
				  			<span><em>评价</em></span>
				  			<span style="background:none;"><em>完成</em></span>
				  		</div>
				  		<h6 class="clearfix" style="font-size:14px;">办公室黑板坏了</h6>
				  		<div class="clearfix content-widgets"><img src="${pageContext.request.contextPath}/res/images/w183.jpg"/></div>
				  		<p class="clearfix"><span class="left"><i class="fa fa-map-marker"></i>维修地点</span><em>校长办公室门口</em></p>
				  		<p class="clearfix"><span class="left"><i class="fa fa-user"></i>联系人</span><em>刘艳青</em></p>
				  		<p class="clearfix"><span class="left"><i class=" fa fa-phone"></i>联系电话</span><em>123143546</em></p>
				  		<p class="clearfix date">06月18日 18:35</p>
				  	</div>
				  </div>
					
				</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>