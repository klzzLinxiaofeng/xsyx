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
			<jsp:param value="请假" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				            <li><a href="javascript:void(0)">待审批</a></li>
				            <li><a href="javascript:void(0)" >已审批</a></li>
				            <li><a href="javascript:void(0)"  class="on">请假统计</a></li>
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
						</div>
						<div class="tj_2">
							<p><span>请假次数：</span><b class="b1">5</b></p>
							<p><span>请假天数：</span><b class="b2">20</b></p>
						</div>
						<div class="tj_3">
							<p class="top">请假明细</p>
							<table class="responsive table table-striped">
								<thead>
									<tr>
										<th>序号</th>
										<th>申请人</th>
										<th>部门</th>
										<th>联系电话</th>
										<th>请假次数</th>
										<th>请假天数</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>3</td>
										<td>15</td>
									</tr>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>3</td>
										<td>15</td>
									</tr>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>3</td>
										<td>15</td>
									</tr>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>3</td>
										<td>15</td>
									</tr>
									<tr>
										<td>1</td>
										<td>张三</td>
										<td>学生处</td>
										<td>13800138000</td>
										<td>3</td>
										<td>15</td>
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