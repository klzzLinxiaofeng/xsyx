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
/*====Select Box====*/
$(function () {
    $(".chzn-select").chosen();
});
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
				            <li><a href="javascript:void(0)">待审批</a></li>
				            <li><a href="javascript:void(0)" >已审批</a></li>
				            <li><a href="javascript:void(0)"  class="on">用车统计</a></li>
				        </ul>
					</div>
					<div class="sq_list">
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
							<p><span>申请次数：</span><b class="b1">5</b></p>
							<p><span>待处理：</span><b class="b2">2</b></p>
							<p><span>已处理：</span><b class="b3">3</b></p>
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
										<th>申请车辆</th>
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
										<td>黄色小型接送巴士[粤A1234567]</td>
										<td>带三年二班到博物馆参观</td>
										<td><span class="blue_1">待处理</span></td>
										<td></td>
									</tr>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>黄色小型接送巴士[粤A1234567]</td>
										<td>带三年二班到博物馆参观</td>
										<td><span class="blue_1">待处理</span></td>
										<td></td>
									</tr>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>黄色小型接送巴士[粤A1234567]</td>
										<td>带三年二班到博物馆参观</td>
										<td>已处理</td>
										<td>2015-06-15 19:00</td>
									</tr>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>黄色小型接送巴士[粤A1234567]</td>
										<td>带三年二班到博物馆参观</td>
										<td>已处理</td>
										<td>2015-06-15 19:00</td>
									</tr>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>黄色小型接送巴士[粤A1234567]</td>
										<td>带三年二班到博物馆参观</td>
										<td>已处理</td>
										<td>2015-06-15 19:00</td>
									</tr>
								</tbody>
							</table>
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