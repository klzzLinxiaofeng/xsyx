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
						<h3 class="x-head content-top"><a href="javascript:void(0);">我申请的</a><a href="javascript:void(0);" class="on">全部报修</a><a href="javascript:void(0);">报修统计</a></h3>
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
					<h4 class="title">我的申请(1)</h4>
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
				  		
				  	</div>
				  	<div class="clearfix x-mright">
				  		<div class="x-load span12">
				  			<span><em class="on">申请</em></span>
				  			<span><em class="on">维修</em></span>
				  			<span><em>评价</em></span>
				  			<span style="background:none;"><em>完成</em></span>
				  		</div>
				  		<form class="form-horizontal">
							<div class="control-group">
									<label class="control-label"><i class="fa fa-user"></i>维修人</label>
									<div>
										<input type="text" placeholder="请选择或者输入维修人员" class="span2 left-stripe">
										<div class="btn-group">
											<select class="select">
												<option value="">维修人员</option>
												<option value="">小红</option>
												<option value="">小红</option>
												<option value="">小红</option>
											</select>
										</div>
									</div>
							</div>
							<div class="control-group">
									<label class="control-label"><i class="fa fa-phone"></i>联系电话</label>
									<div>
										<input type="text" placeholder="请输入维修人员电话" class="span2 left-stripe">
									</div>
							</div>
							<div class="control-group">
									<label class="control-label"><i class="fa fa-clock-o"></i>维修时间</label>
									<div>
										<div id="datetimepicker1" class="input-append date">
											<input data-format="dd/MM/yyyy hh:mm:ss" type="text" placeholder="留空是当前时间"><span class="add-on "><i data-time-icon="icon-time" data-date-icon="icon-calendar" class="icon-calendar"></i></span>
										</div>
										
									</div>
							</div>
							<div class="control-group">
									<label class="control-label"><i class="fa fa-user"></i>维修状态</label>
									<div>
										<div class="btn-group">
											<select>
												<option value="">待处理</option>
												<option value="">完成</option>
												<option value="">已处理</option>
												<option value="">已处理</option>
											</select>
										</div>
									</div>
							</div>
							<div class="control-group">
								<label class="control-label"><i class="fa fa-comment-o"></i>备注</label>
								<div>
									<textarea rows="3" class="span6"></textarea>
								</div>
							</div>
							<div class="control-group">
									<label class="control-label">添加图片</label>
									<div class="left">
										<div class="fileupload fileupload-new clearfix" data-provides="fileupload">
											<div class="fileupload-new thumbnail">
												<img src="${pageContext.request.contextPath}/res/images/w183.jpg" alt="img">
											</div>
											<div>
												<span class="btn btn-file" style="border-radius:0"><span class="fileupload-new"><i class="fa fa-upload"></i>上传图片</span>
												<input type="file">
												</span>
											</div>
											<div class="space10"></div>
											<h6>建议尺寸：950像素*500像素</h6>
										</div>
									</div>
								</div>
						</form>
				  		<p class="tc" style="padding-top:8px;border-top: #ccc 1px solid;"><button class="btn btn-success" type="button">发布</button></p>
				  	</div>
				  </div>	
			</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>