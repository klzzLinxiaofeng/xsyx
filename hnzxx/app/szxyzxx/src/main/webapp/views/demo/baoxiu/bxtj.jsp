<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/date.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/daterangepicker.js"></script>
<script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
<title>报修</title>
<script>
$(function () {
    $('#reservation').daterangepicker();
});
</script>
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
						<h3 class="x-head content-top"><a href="bxiu-sq1.html">我申请的</a><a href="bxiu-all3.html">全部报修</a><a href="bxtj.html"  class="on">报修统计</a></h3>
				</div>
				<div class="widget-container clearfix x-tongji">
					<form class="form-horizontal span7 left" style="padding:0">
						<div>
							<label class="control-label">时间范围:</label>
							<div class="date">
								<div class="input-prepend">
											<span class="add-on"><i class="fa fa-calendar"></i></span>
											<input type="text" id="reservation" name="reservation">
										</div>
								<!-- 时间范围选择的另外一种形式
								<div id="datetimepicker1" class="input-append date">
									<input data-format="dd/MM/yyyy hh:mm:ss" type="text" placeholder="起始时间"><span class="add-on "><i data-time-icon="icon-time" data-date-icon="icon-calendar" class="icon-calendar"></i></span>
								</div>
								到
								<div id="datetimepicker2" class="input-append date">
									<input data-format="dd/MM/yyyy hh:mm:ss" type="text" placeholder="结束时间"><span class="add-on "><i data-time-icon="icon-time" data-date-icon="icon-calendar" class="icon-calendar"></i></span>
								</div> -->
							</div>
						</div>
					</form>
					 <div class="clearfix right"  style="margin-top:-10px;">
					 	<div class="admin-thumb">
					 		<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"/>
					 	</div>
					 	<div class="admin-meta admin-head">
					 		<ul>
					 			<li><b>刘艳青</b></li>
					 			<li><a href="#" class="title-b">后勤部部长</a></li>
					 		</ul>
					 	</div>
					 </div>
					<span class="right title-c">报修负责人</span>
				</div>
				<div class="x-main clearfix">
					<div class="x-pingjia">
						<span class="numa">报修申请数<i>98</i></span>
						<span class="numb">待处理<i>2</i></span>
						<span class="numc">处理中<i>9</i></span>
						<span class="numd">已处理<i>98</i></span>
						<span class="nume">未修好<i>98</i></span>
					</div>
					<div class="tc span12 x-mark" style="margin-left:0">
						<span class="fl fen">总体评价</span>
						<em class="m-star">
							<i class="fa fa-star active" title="差"></i>
							<i class="fa fa-star active" title="一般"></i>
							<i class="fa fa-star active" title="较好"></i>
							<i class="fa fa-star" title="好"></i>
							<i class="fa fa-star" title="非常好"></i>
						</em>
						<span class="s_result fl pj">较好</span>
					</div>
				</div>
				
				<div class="content-widgets">
				<div class="widget-head" style="margin:40px 0 0 0;border-top: #ccc 1px solid;">
						<h3>评价明细</h3>
				</div>
					<div class="widget-container" style="padding: 0 20px 1px 20px;">
						<table class="responsive table table-striped" id="data-table">
							<thead>
								<tr role="row">
									<th>序号</th>	
									<th>维修人员</th>
									<th>联系电话</th>
									<th>维修种类</th>
									<th>维修次数</th>
									<th>总体评价</th>
								</tr>
							</thead>
							<tbody>
								<tr>
    	                           <td>1</td>
    	                           <td>3800000000</td>
    	                           <td>空调维修</td>
    	                           <td>33</td>
    	                           <td>33</td>
								   <td>
									<div class="x-mark">
										<em class="m-star">
										   <i class="fa fa-star active" title="差"></i>
										   <i class="fa fa-star active" title="一般"></i>
										   <i class="fa fa-star active" title="较好"></i>
										   <i class="fa fa-star" title="好"></i>
										   <i class="fa fa-star" title="非常好"></i>
										</em>
									<span class="s_result fl pj">较好</span>
									</div>
								</td>
								</tr>
							<tr>
    	                           <td>1</td>
    	                           <td>3800000000</td>
    	                           <td>空调维修</td>
    	                           <td>33</td>
    	                           <td>33</td>
								   <td>
									<div class="x-mark">
										<em class="m-star">
										   <i class="fa fa-star" title="差"></i>
										   <i class="fa fa-star" title="一般"></i>
										   <i class="fa fa-star" title="较好"></i>
										   <i class="fa fa-star" title="好"></i>
										   <i class="fa fa-star" title="非常好"></i>
										</em>
									<span class="s_result fl pj">较好</span>
									</div>
								</td>
								</tr>
								<tr>
    	                           <td>1</td>
    	                           <td>3800000000</td>
    	                           <td>空调维修</td>
    	                           <td>33</td>
    	                           <td>33</td>
								   <td>
									<div class="x-mark">
										<em class="m-star">
										   <i class="fa fa-star" title="差"></i>
										   <i class="fa fa-star" title="一般"></i>
										   <i class="fa fa-star" title="较好"></i>
										   <i class="fa fa-star" title="好"></i>
										   <i class="fa fa-star" title="非常好"></i>
										</em>
									<span class="s_result fl pj">较好</span>
									</div>
								</td>
								</tr>
								<tr>
    	                           <td>1</td>
    	                           <td>3800000000</td>
    	                           <td>空调维修</td>
    	                           <td>33</td>
    	                           <td>33</td>
								   <td>
									<div class="x-mark">
										<em class="m-star">
										   <i class="fa fa-star" title="差"></i>
										   <i class="fa fa-star" title="一般"></i>
										   <i class="fa fa-star" title="较好"></i>
										   <i class="fa fa-star" title="好"></i>
										   <i class="fa fa-star" title="非常好"></i>
										</em>
									<span class="s_result fl pj">较好</span>
									</div>
								</td>
								</tr>
								<tr>
    	                           <td>1</td>
    	                           <td>3800000000</td>
    	                           <td>空调维修</td>
    	                           <td>33</td>
    	                           <td>33</td>
								   <td>
									<div class="x-mark">
										<em class="m-star">
										   <i class="fa fa-star" title="差"></i>
										   <i class="fa fa-star" title="一般"></i>
										   <i class="fa fa-star" title="较好"></i>
										   <i class="fa fa-star" title="好"></i>
										   <i class="fa fa-star" title="非常好"></i>
										</em>
									<span class="s_result fl pj">较好</span>
									</div>
								</td>
								</tr>
								<tr>
    	                           <td>1</td>
    	                           <td>3800000000</td>
    	                           <td>空调维修</td>
    	                           <td>33</td>
    	                           <td>33</td>
								   <td>
									<div class="x-mark">
										<em class="m-star">
										   <i class="fa fa-star" title="差"></i>
										   <i class="fa fa-star" title="一般"></i>
										   <i class="fa fa-star" title="较好"></i>
										   <i class="fa fa-star" title="好"></i>
										   <i class="fa fa-star" title="非常好"></i>
										</em>
									<span class="s_result fl pj">较好</span>
									</div>
								</td>
								</tr>
								<tr>
    	                           <td>1</td>
    	                           <td>3800000000</td>
    	                           <td>空调维修</td>
    	                           <td>33</td>
    	                           <td>33</td>
								   <td>
									<div class="x-mark">
										<em class="m-star">
										   <i class="fa fa-star" title="差"></i>
										   <i class="fa fa-star" title="一般"></i>
										   <i class="fa fa-star" title="较好"></i>
										   <i class="fa fa-star" title="好"></i>
										   <i class="fa fa-star" title="非常好"></i>
										</em>
									<span class="s_result fl pj">较好</span>
									</div>
								</td>
								</tr>
							</tbody>
							</table>
						</div>
					</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>